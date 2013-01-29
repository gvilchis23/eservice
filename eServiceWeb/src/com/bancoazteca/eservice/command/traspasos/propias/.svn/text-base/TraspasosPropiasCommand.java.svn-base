package com.bancoazteca.eservice.command.traspasos.propias;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ConfirmarTraspasoPropioTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.TraspasosPropiasRequestTO;
import com.bancoazteca.elite.beans.TraspasosPropiasResponsetTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Traspasos_PropiasTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.command.saldos.SaldosCommand;
import com.bancoazteca.eservice.validator.Errors;

public class TraspasosPropiasCommand extends CommandBase{
	public static final Logger log = Logger.getLogger(SaldosCommand.class);
	
	public Response solicitud(Session session)throws Exception{
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		Collection<CuentaTO> cuentas = clienteTO.getCuentas();		

		TraspasosPropiasRequestTO traspasosPropiasRequestTO = new TraspasosPropiasRequestTO();
		traspasosPropiasRequestTO.setUser(clienteTO.getUserName());
		
		ResourceFacadeSL facadeSL = getDelegate();
		TraspasosPropiasResponsetTO traspasosPropiasResponseTO = facadeSL.propiasInvocaTraspaso(traspasosPropiasRequestTO);
				
		Cuentas_CargoTO traspasosCuentasTO = new Cuentas_CargoTO();				
		Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
		
		PropertiesService propertiesService = PropertiesService.getInstance();
		String productoPlata = propertiesService.getPropertie( CUENTA_PLATA_PROPS , CUENTA_PLATA_PRODUCTO );
		String subproductoPlata = propertiesService.getPropertie( CUENTA_PLATA_PROPS , CUENTA_PLATA_SUBPRODUCTO );

		for(CuentaTO cuentaEliteTO: traspasosPropiasResponseTO.getTraspasoPropioTO().getCuentasTO() ){

			String numeroCuenta=Formatter.removeSpaces(cuentaEliteTO.getCuentaFormateada());
			
			if(isContrato(numeroCuenta)){
				continue;
			}
			
			Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();
			
			cuentaCargoTO.setNumero_cuenta(numeroCuenta);			
			cuentaCargoTO.setSaldo_disponible(cuentaEliteTO.getDisponible().toString());
			cuentaCargoTO.setProducto(cuentaEliteTO.getDescripcion());
			if( !cuentaEliteTO.getProducto().equals( productoPlata ) || !cuentaEliteTO.getSubproducto().equals( subproductoPlata ) )
				cuentaCollectionTO.add( cuentaCargoTO );
		}
				
		traspasosCuentasTO.setValor_unidad(traspasosPropiasResponseTO.getValorUnidad());		
		traspasosCuentasTO.setColeccion_cuentas(cuentaCollectionTO);
		response.addAttribute(traspasosCuentasTO);
		
		session.addAttribute(TRASPASOS_PROPIAS_RESPONSE, traspasosPropiasResponseTO);
		
		return response;
	}
	
	public Response validacion(Session session)throws Exception{
		Response response = new Response();
		
		try{
			Traspasos_PropiasTO  traspasos_PropiasTO = new Traspasos_PropiasTO();
			traspasos_PropiasTO = validacionPropias(session);
			response.addAttribute(traspasos_PropiasTO);
		
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		TraspasosPropiasForm traspasosForm = (TraspasosPropiasForm) getFormBean();
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		TraspasosPropiasResponsetTO traspasosPropiasResponsetTO = (TraspasosPropiasResponsetTO) session.getAttribute(TRASPASOS_PROPIAS_RESPONSE);
		
		ConfirmarTraspasoPropioTO confirmarTraspasoPropioTO = traspasosPropiasResponsetTO.getTraspasoPropioTO();
		Collection <CuentaLoTO> cuentasOrigenTO = confirmarTraspasoPropioTO.getCuentasOrigen();
		Collection <CuentaLoTO> cuentasDestinoTO = confirmarTraspasoPropioTO.getCuentasDestino();
		
		CuentaPredicate cuentaPredicate = new CuentaPredicate();
				
		cuentaPredicate.setCuenta(traspasosForm.getCuenta_cargo());
		CuentaLoTO cuentaOrigenTO = (CuentaLoTO) CollectionUtils.find(cuentasOrigenTO, cuentaPredicate);

		cuentaPredicate.setCuenta(traspasosForm.getCuenta_destino());
		CuentaLoTO cuentaDestinoTO = (CuentaLoTO) CollectionUtils.find(cuentasDestinoTO, cuentaPredicate);
		
		if(cuentaOrigenTO == null ){
			Map<String, String> error = new HashMap<String, String>();
			error.put("cuenta_cargo", "La número de cuenta cargo es incorrecto.");
			throw new EliteDataException(Errors.VALIDATION, error, Errors.VALIDATION_CODE);
		}
		
		if(cuentaDestinoTO == null ){
			Map<String, String> error = new HashMap<String, String>();
			error.put("cuenta_destino", "La número de cuenta destino es incorrecto.");
			throw new EliteDataException(Errors.VALIDATION, error, Errors.VALIDATION_CODE);
		}
		
		TraspasosPropiasRequestTO traspasosPropiasRequestTO =  new TraspasosPropiasRequestTO();
		traspasosPropiasRequestTO.setUser( clienteTO.getUserName());
		traspasosPropiasRequestTO.setConcepto(traspasosForm.getConcepto());
		traspasosPropiasRequestTO.setImporte(traspasosForm.getImporte());
		traspasosPropiasRequestTO.setCuentaDestinoIndex(cuentaDestinoTO.getIndex());
		traspasosPropiasRequestTO.setCuentaOrigenIndex(cuentaOrigenTO.getIndex());
		
		try{
			ResourceFacadeSL facade = getDelegate();
			TraspasosPropiasResponsetTO traspasosPropiasResponsetValidateTO = (TraspasosPropiasResponsetTO) session.getAttribute(TRASPASOS_PROPIAS_VALIDATE_RESPONSE);	
			
			if(traspasosPropiasResponsetValidateTO == null){
				validacionPropias(session);
			}
			
			TraspasosPropiasResponsetTO traspasosPropiasEjecutarResponseTO = facade.ejecutaPropiasTraspaso(traspasosPropiasRequestTO);
			
			Traspasos_PropiasTO  traspasos_PropiasTO = new Traspasos_PropiasTO();
			
		    	
			if(!traspasosPropiasEjecutarResponseTO.isEBankingCom()){
				traspasos_PropiasTO.setConcepto(traspasosPropiasEjecutarResponseTO.getTraspasoPropioTO().getConcepto());
				traspasos_PropiasTO.setCuenta_cargo(Formatter.formatCuenta(traspasosPropiasEjecutarResponseTO.getTraspasoPropioTO().getCuentaCargo()));
				traspasos_PropiasTO.setCuenta_destino(Formatter.formatCuenta(traspasosPropiasEjecutarResponseTO.getTraspasoPropioTO().getCuentaAbono()));			
				traspasos_PropiasTO.setFolio_aclaracion(traspasosPropiasEjecutarResponseTO.getTraspasoPropioTO().getFolio());
				traspasos_PropiasTO.setImporte(traspasosPropiasEjecutarResponseTO.getTraspasoPropioTO().getImporte());
				traspasos_PropiasTO.setTipo_cuenta_cargo(traspasosPropiasEjecutarResponseTO.getTraspasoPropioTO().getDescriCargo());
				traspasos_PropiasTO.setTipo_cuenta_destino(traspasosPropiasEjecutarResponseTO.getTraspasoPropioTO().getDescriAbono());
				traspasos_PropiasTO.setTitular_cuentas(clienteTO.getNombreCompleto());
			}else{
				traspasos_PropiasTO.setConcepto(traspasosPropiasRequestTO.getConcepto());
				traspasos_PropiasTO.setCuenta_cargo(Formatter.formatCuenta(cuentaOrigenTO.getNumero()));
				traspasos_PropiasTO.setCuenta_destino(Formatter.formatCuenta(cuentaDestinoTO.getNumero()));			
				traspasos_PropiasTO.setFolio_aclaracion(traspasosPropiasEjecutarResponseTO.getFolio());
				traspasos_PropiasTO.setImporte(traspasosPropiasRequestTO.getImporte());
				traspasos_PropiasTO.setTipo_cuenta_cargo(cuentaOrigenTO.getDescripcion());
				traspasos_PropiasTO.setTipo_cuenta_destino(cuentaDestinoTO.getDescripcion());
				traspasos_PropiasTO.setTitular_cuentas(clienteTO.getNombreCompleto());
			}
			
						
			super.updateBalance(session);
			
			response.addAttribute(traspasos_PropiasTO);
			
			session.deleteAttribute(TRASPASOS_PROPIAS_RESPONSE);	
			session.deleteAttribute(TRASPASOS_PROPIAS_VALIDATE_RESPONSE);
			
		
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		
		return response;
	}

	public Traspasos_PropiasTO validacionPropias(Session session)throws Exception{
		TraspasosPropiasForm traspasosForm = (TraspasosPropiasForm) getFormBean();
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		TraspasosPropiasResponsetTO traspasosPropiasResponsetTO = (TraspasosPropiasResponsetTO) session.getAttribute(TRASPASOS_PROPIAS_RESPONSE);
		ConfirmarTraspasoPropioTO confirmarTraspasoPropioTO = traspasosPropiasResponsetTO.getTraspasoPropioTO();
		Collection <CuentaLoTO> cuentasOrigenTO = confirmarTraspasoPropioTO.getCuentasOrigen();
		Collection <CuentaLoTO> cuentasDestinoTO = confirmarTraspasoPropioTO.getCuentasDestino();
		
		CuentaPredicate cuentaPredicate = new CuentaPredicate();
				
		cuentaPredicate.setCuenta(traspasosForm.getCuenta_cargo());
		CuentaLoTO cuentaOrigenTO = (CuentaLoTO) CollectionUtils.find(cuentasOrigenTO, cuentaPredicate);

		cuentaPredicate.setCuenta(traspasosForm.getCuenta_destino());
		CuentaLoTO cuentaDestinoTO = (CuentaLoTO) CollectionUtils.find(cuentasDestinoTO, cuentaPredicate);
		
		if(cuentaOrigenTO == null ){
			Map<String, String> error = new HashMap<String, String>();
			error.put("cuenta_cargo", "La número de cuenta cargo es incorrecto.");
			throw new EliteDataException(Errors.VALIDATION, error, EliteRules.LEVEL_ACTION_ERRORS);
		}
		
		if(cuentaDestinoTO == null ){
			Map<String, String> error = new HashMap<String, String>();
			error.put("cuenta_destino", "La número de cuenta destino es incorrecto.");
			throw new EliteDataException(Errors.VALIDATION, error, EliteRules.LEVEL_ACTION_ERRORS);
		}
		
		TraspasosPropiasRequestTO traspasosPropiasRequestTO =  new TraspasosPropiasRequestTO();
		traspasosPropiasRequestTO.setUser( clienteTO.getUserName());
		traspasosPropiasRequestTO.setConcepto(traspasosForm.getConcepto());
		traspasosPropiasRequestTO.setImporte(traspasosForm.getImporte());
		traspasosPropiasRequestTO.setCuentaDestinoIndex(cuentaDestinoTO.getIndex());
		traspasosPropiasRequestTO.setCuentaOrigenIndex(cuentaOrigenTO.getIndex());
		traspasosPropiasRequestTO.setNombreSubmit("submit");
		traspasosPropiasRequestTO.setValorSubmit("Traspasar");
		
		if(	!Validator.isEmptyData( traspasosForm.getValor_submit() ) 
			&& traspasosForm.getValor_submit().equalsIgnoreCase("combo")){
			traspasosPropiasRequestTO.setNombreSubmit("submitCombo");
			traspasosPropiasRequestTO.setValorSubmit("submitCombo");
		}
		
		ResourceFacadeSL facade = getDelegate();
		TraspasosPropiasResponsetTO traspasosPropiasResponseTO = new TraspasosPropiasResponsetTO();
		traspasosPropiasResponseTO = facade.propiasPreparaTraspaso(traspasosPropiasRequestTO);
		
		String cuentaCargo = Formatter.formatCuenta(cuentaOrigenTO.getNumero());
		String cuentaDestino = Formatter.formatCuenta(cuentaDestinoTO.getNumero());
		
		CuentaTO cuentaCargoTO = (CuentaTO) super.getAccountsPrdicate(clienteTO.getCuentas(), Formatter.removeSpaces(cuentaCargo));
		CuentaTO cuentaDepositoTO= (CuentaTO) super.getAccountsPrdicate(clienteTO.getCuentas(), Formatter.removeSpaces(cuentaDestino));
		
		
		Traspasos_PropiasTO  traspasos_PropiasTO = new Traspasos_PropiasTO();
		
		if(traspasosPropiasResponseTO.getReferencia()==null){
			traspasos_PropiasTO.setConcepto(traspasosPropiasResponseTO.getTraspasoPropioTO().getConcepto());
			traspasos_PropiasTO.setValor_unidad(null);
		}else {
			traspasos_PropiasTO.setValor_unidad(traspasosPropiasResponseTO.getValorUnidad());
			traspasos_PropiasTO.setConcepto(traspasosPropiasResponseTO.getReferencia());
			if(traspasosPropiasResponseTO.getRestriccion()!=null){
				Map<String, String> error = new HashMap<String, String>();
				error.put("restriccion_horario", traspasosPropiasResponseTO.getRestriccion());
				throw new EliteDataException(Errors.VALIDATION, error, EliteRules.LEVEL_ACTION_ERRORS);
			}
		}
		
		
		traspasos_PropiasTO.setCuenta_cargo(Formatter.formatCuenta(cuentaCargoTO.getNumero()));
		traspasos_PropiasTO.setCuenta_destino(Formatter.formatCuenta(cuentaDepositoTO.getNumero()));			
		traspasos_PropiasTO.setImporte(traspasosPropiasResponseTO.getTraspasoPropioTO().getImporte());
		traspasos_PropiasTO.setTipo_cuenta_cargo(cuentaCargoTO.getDescripcion());
		traspasos_PropiasTO.setTipo_cuenta_destino(cuentaDepositoTO.getDescripcion());
		traspasos_PropiasTO.setTitular_cuentas(clienteTO.getNombreCompleto());
		traspasos_PropiasTO.setTotal_unidades_inversion(traspasosPropiasResponseTO.getTotalUnidades());
		
		session.addAttribute(TRASPASOS_PROPIAS_VALIDATE_RESPONSE, traspasosPropiasResponseTO);

		return traspasos_PropiasTO;
	}
	
	private class CuentaPredicate implements Predicate {
		private String cuenta;
		CuentaLoTO cuentaLoTO;
		
		public boolean evaluate(Object obj) {
			CuentaLoTO cuentaLoTO = (CuentaLoTO) obj;
			if (cuentaLoTO.getCuentaFormateada().replace(" ", "").equals(cuenta)) {
				return true;
			} else {
				return false;
			}
		}

		public String getCuenta() {
			return cuenta;
		}

		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
	}	
}