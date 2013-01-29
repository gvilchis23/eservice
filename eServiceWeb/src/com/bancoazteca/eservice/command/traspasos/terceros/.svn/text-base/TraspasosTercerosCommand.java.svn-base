package com.bancoazteca.eservice.command.traspasos.terceros;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.TransferenciaTercerosRequestTO;
import com.bancoazteca.elite.beans.TransferenciaTercerosResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.CommandConstantes;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_FrecuenteTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.beans.Envio_DineroTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Traspasos_Terceros_EjecucionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.frecuentes.transferencias.ConsultaFrecuentesTransferenciasCommand;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class TraspasosTercerosCommand extends CommandBase {
	
	private static final String TERCEROS = "terceros";
	
	public Response solicitud(Session session) throws Exception{
		TransferenciaTercerosRequestTO tercerosRequestTO = new TransferenciaTercerosRequestTO();
		TransferenciaTercerosResponseTO tercerosResponseTO = null;		
		Response response = new Response();
		ResourceFacadeSL bean = getDelegate();			
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		String userName = clienteTO.getUserName();			
		tercerosRequestTO.setUser(userName);
		try{
			tercerosResponseTO = bean.getTransferenciaTercerosInvocacion(tercerosRequestTO);
			Envio_DineroTO envioDineroTO= new Envio_DineroTO();
			Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
			for(CuentaTransaccionesTO cuentaTransaccionesTO: tercerosResponseTO.getCuentas()){
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();		
				String cuenta=Formatter.removeSpaces(cuentaTransaccionesTO.getCuentaFormateada());
				CuentaTO cuentaTO = (CuentaTO) super.getAccountsPrdicate(clienteTO.getCuentas(),cuenta);//CollectionUtils.find(clienteTO.getCuentas(), predicate );
				if (cuentaCargoTO!=null){
					cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));			
					cuentaCargoTO.setSaldo_disponible(cuentaTO.getDisponible().toString());
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());	
					cuentaCollectionTO.add(cuentaCargoTO);
				}
			}
			envioDineroTO.setColeccion_cuentas(cuentaCollectionTO);
			session.addAttribute(CommandConstantes.TRANSFERENCIA_TERCEROS_RESPONSE, tercerosResponseTO);
			response.addAttribute(envioDineroTO);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}	
	
	public Response validacion(Session session) throws Exception{
		Response response = new Response();	
		ResourceFacadeSL bean = getDelegate();
		TraspasosTercerosForm mismoBancoForm = (TraspasosTercerosForm) getFormBean();
		
		ConsultaFrecuentesTransferenciasCommand consultaFrecuntes = new ConsultaFrecuentesTransferenciasCommand();
		
		Collection<Cuenta_FrecuenteTO> coleccion_cuentas_frecuentes = consultaFrecuntes.listaFrecuentes(session,TERCEROS).getColeccion_cuentas_frecuentes();
		for (Cuenta_FrecuenteTO cuenta_FrecuenteTO : coleccion_cuentas_frecuentes) {
			if(cuenta_FrecuenteTO.getNumero_cuenta().equalsIgnoreCase(mismoBancoForm.getCuenta_destino())&&
					cuenta_FrecuenteTO.getEstado().equalsIgnoreCase("bloqueada")){
				response=new Response();
				response.setStatus(Errors.VALIDATION_CODE,"Por el momento esta referencia esta desactivada.", null);
				return response;
			}
		}
		
		
		TransferenciaTercerosRequestTO transferenciaTercerosRequestTO = new TransferenciaTercerosRequestTO();
		TransferenciaTercerosResponseTO transferenciaTercerosResponseTO= (TransferenciaTercerosResponseTO)session.getAttribute(TRANSFERENCIA_TERCEROS_RESPONSE);
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			transferenciaTercerosRequestTO.setUser(clienteTO.getUserName());
			String cuenta=mismoBancoForm.getCuenta_cargo();
			if (cuenta.length()==14){
				String cuentaFormateada = Formatter.split4CharsTokens(cuenta);
//				cuenta=cuenta.substring(0,4)+" "+cuenta.substring(4,8)+" "+cuenta.substring(8,12)+" "+cuenta.substring(12,14);
				CuentaPredicate predicate1 = new CuentaPredicate();
				predicate1.setCuenta(cuentaFormateada);
				CuentaTransaccionesTO cuentaTransaccionesTO = (CuentaTransaccionesTO) CollectionUtils.find(transferenciaTercerosResponseTO.getCuentas(), predicate1 );
				transferenciaTercerosRequestTO.setOrigen(cuentaTransaccionesTO.getIndex());
			}			
			transferenciaTercerosRequestTO.setDestino(mismoBancoForm.getCuenta_destino());
			transferenciaTercerosRequestTO.setReferencia(mismoBancoForm.getReferencia());
	   		transferenciaTercerosRequestTO.setConcepto(mismoBancoForm.getConcepto());
			transferenciaTercerosRequestTO.setImporte(mismoBancoForm.getImporte());
			transferenciaTercerosRequestTO.setEmailConfirmacion("");
			transferenciaTercerosResponseTO = bean.getTransferenciaTercerosEnvioDatos(transferenciaTercerosRequestTO);

			session.addAttribute(CommandConstantes.TRANSFERENCIA_TERCEROS_RESPONSE, transferenciaTercerosResponseTO);	

			HuellaTO huella=new HuellaTO();
			huella.setLlave_publica(transferenciaTercerosResponseTO.getDispositivoHuellaTO().getLlavePublica());
			huella.setLongitud_huella(transferenciaTercerosResponseTO.getDispositivoHuellaTO().getLongitudHuella());
			
			Traspasos_Terceros_EjecucionTO tercerosEjecucionTO=new Traspasos_Terceros_EjecucionTO();
			tercerosEjecucionTO.setConcepto(transferenciaTercerosResponseTO.getConfirmarTercerosTO().getConcepto());
			tercerosEjecucionTO.setCuenta_cargo(cuenta);
			tercerosEjecucionTO.setCuenta_destino(transferenciaTercerosResponseTO.getConfirmarTercerosTO().getDestino());
			tercerosEjecucionTO.setFolio_aclaracion(transferenciaTercerosResponseTO.getConfirmarTercerosTO().getFolio());
			tercerosEjecucionTO.setImporte(transferenciaTercerosResponseTO.getConfirmarTercerosTO().getImporte().toString());
			tercerosEjecucionTO.setIva_comision(transferenciaTercerosResponseTO.getConfirmarTercerosTO().getComisionMasIva());//no sale
			tercerosEjecucionTO.setReferencia(transferenciaTercerosResponseTO.getConfirmarTercerosTO().getReferencia());
			tercerosEjecucionTO.setTitular_cuenta_destino(transferenciaTercerosResponseTO.getConfirmarTercerosTO().getTitular());
			tercerosEjecucionTO.setTitular_cuentas(clienteTO.getNombreCompleto());
			
			response.addAttribute(tercerosEjecucionTO);
			response.addAttribute(huella);

			
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}

	public Response ejecucion(Session session) throws Exception {
		ResourceFacadeSL bean = getDelegate();
		TraspasosTercerosForm mismoBancoForm = (TraspasosTercerosForm) getFormBean();
		TransferenciaTercerosRequestTO transferenciaTercerosRequestTO = new TransferenciaTercerosRequestTO();
		Response response = new Response();
		TransferenciaTercerosResponseTO responseTO=(TransferenciaTercerosResponseTO) session.getAttribute(CommandConstantes.TRANSFERENCIA_TERCEROS_RESPONSE);
		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		try{				
			transferenciaTercerosRequestTO.setUser(clienteTO.getUserName());
			transferenciaTercerosRequestTO.setEmailConfirmacion("");
			if (mismoBancoForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				transferenciaTercerosRequestTO.setOptionDispositive(OPCION_HUELLA);
				transferenciaTercerosRequestTO.setClave(mismoBancoForm.getHuella_seguridad().toString());
			}else if (mismoBancoForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				transferenciaTercerosRequestTO.setOptionDispositive(OPCION_TOKEN);
				transferenciaTercerosRequestTO.setTokencode(mismoBancoForm.getClave_seguridad().toString());
			}
			TransferenciaTercerosResponseTO tercerosResponseTO = bean.getEjecutarTransferenciaTerceros(transferenciaTercerosRequestTO);
			super.updateBalance(session);	
			
			String cuenta=new String();
			for (CuentaTO cuentas:clienteTO.getCuentas()){
				if (cuentas.getIndex().equals(responseTO.getConfirmarTercerosTO().getOrigen())){
					cuenta=Formatter.removeSpaces(cuentas.getCuentaFormateada());
					break;
				}
			}
			
			Traspasos_Terceros_EjecucionTO tercerosEjecucionTO=new Traspasos_Terceros_EjecucionTO();
			tercerosEjecucionTO.setConcepto(responseTO.getConfirmarTercerosTO().getConcepto());
			tercerosEjecucionTO.setCuenta_cargo(cuenta);
			tercerosEjecucionTO.setCuenta_destino(responseTO.getConfirmarTercerosTO().getDestino());
			tercerosEjecucionTO.setFolio_aclaracion(tercerosResponseTO.getConfirmarTercerosTO().getFolio());
			tercerosEjecucionTO.setImporte(responseTO.getConfirmarTercerosTO().getImporte().toString());
			tercerosEjecucionTO.setIva_comision(responseTO.getConfirmarTercerosTO().getComisionMasIva());//no sale
			tercerosEjecucionTO.setReferencia(responseTO.getConfirmarTercerosTO().getReferencia());
			tercerosEjecucionTO.setTitular_cuenta_destino(responseTO.getConfirmarTercerosTO().getTitular());
			tercerosEjecucionTO.setTitular_cuentas(clienteTO.getNombreCompleto());
			response.addAttribute(tercerosEjecucionTO);
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;		
	}

	private class CuentaPredicate implements Predicate {
		private String cuenta;	
		public boolean evaluate(Object obj) {
			CuentaTransaccionesTO cuentaTransaccionesTO=(CuentaTransaccionesTO) obj;
			String temp=cuentaTransaccionesTO.getCuentaFormateada();
			if (temp.equals(cuenta)) {
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
