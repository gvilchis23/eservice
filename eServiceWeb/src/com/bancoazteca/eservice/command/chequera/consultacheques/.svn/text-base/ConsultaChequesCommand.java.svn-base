package com.bancoazteca.eservice.command.chequera.consultacheques;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bancoazteca.elite.beans.ChequeTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ConsultaChequesRequestTO;
import com.bancoazteca.elite.beans.ConsultaChequesResponseTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.TalonarioTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Catalogo_estatus_chequesTO;
import com.bancoazteca.eservice.command.base.beans.ChequeraTO;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequerasTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_chequesTO;
import com.bancoazteca.eservice.command.base.beans.Informacion_chequeraTO;
import com.bancoazteca.eservice.command.base.beans.Listado_detalle_chequeraTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ConsultaChequesCommand extends CommandBase {	
	
	@SuppressWarnings("unchecked")
	
	
	public Response solicitud(Session session) throws Exception {	
		ConsultaChequesForm forma=(ConsultaChequesForm)getFormBean();
		Response response=new Response();
		try {
			Class<?> clazz=this.getClass();
			Class<?>[] types=new Class[]{Session.class};
			Method metodo=clazz.getMethod(forma.getTipo_solicitud().toLowerCase(), types);
			Object[] args = new Object[]{session};
			response=(Response)metodo.invoke(this, args);
		} catch (Exception e) {
			if(e.getCause() instanceof SessionExpiredException){
				throw new SessionExpiredException(e);
			}else{
				throw e;				
			}
		} 
		return response;
	}
	
	/**
	 * solicitud
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Response obten_cuentas(Session session) throws Exception {	
		Response response = new Response();
		ResourceFacadeSegundoSL resourceFacadeSL = getDelegateSegundo();
		
		try{
			ClienteTO clienteTO = (ClienteTO ) session.getAttribute(CLIENTE_TO);
			ConsultaChequesRequestTO chequesRequestTO= new ConsultaChequesRequestTO();
			
			chequesRequestTO.setUser(clienteTO.getUserName());
			
			ConsultaChequesResponseTO consultaChequesResponseTO = resourceFacadeSL.solicitudConsultarChequera(chequesRequestTO);
			Collection<CuentaLoTO> cuentas = consultaChequesResponseTO.getColeccionCuentas();
			
			
			Cuentas_CargoTO cuentas_CargoTO = new Cuentas_CargoTO();
			
			Collection<Cuenta_CargoTO> collection_cuentas_cheques=new ArrayList<Cuenta_CargoTO>();
			Cuenta_CargoTO cuenta_cargoTO;
			Map<String, String> mapCuentas = new HashMap<String, String>();
			for(CuentaLoTO cuenta:cuentas){
					CuentaTO cuentaClieteTO = super.getAccountsPrdicate(clienteTO.getCuentas(), Formatter.removeSpaces(Formatter.formatCuenta(cuenta.getNumero())));
					if(cuentaClieteTO!=null){
						cuenta_cargoTO=new Cuenta_CargoTO();
						cuenta_cargoTO.setNumero_cuenta(cuentaClieteTO.getNumeroCuenta14());
						cuenta_cargoTO.setProducto(cuentaClieteTO.getDescripcion());
						cuenta_cargoTO.setSaldo_disponible(cuentaClieteTO.getDisponible().toString());	
						mapCuentas.put(cuentaClieteTO.getNumeroCuenta14(),cuenta.getCuenta());
						collection_cuentas_cheques.add(cuenta_cargoTO);
					}
			}
			session.addAttribute(CONSULTA_CHEQUERAS_CUENTAS, mapCuentas );
			cuentas_CargoTO.setColeccion_cuentas(collection_cuentas_cheques);
			response.addAttribute(cuentas_CargoTO);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	/**
	 * solicitud
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Response listado_chequeras(Session session) throws Exception {		
		ConsultaChequesForm forma = (ConsultaChequesForm) getFormBean();
		ResourceFacadeSegundoSL resourceFacadeSL = getDelegateSegundo();
		Response response=new Response();
		Detalle_chequerasTO resumen_detalle_cheques=new Detalle_chequerasTO();
		Map<String, String> mapCuentas =(Map<String, String>)session.getAttribute(CONSULTA_CHEQUERAS_CUENTAS);
		ClienteTO clienteTO = (ClienteTO ) session.getAttribute(CLIENTE_TO);
		CuentaTO cuenta_cargo = null ;
		try{
			cuenta_cargo = super.getAccountsPrdicate(clienteTO.getCuentas(), Formatter.removeSpaces(Formatter.formatCuenta(forma.getNumero_cuenta())));

			ConsultaChequesRequestTO chequesRequestTO= new ConsultaChequesRequestTO();
			
			chequesRequestTO.setUser(clienteTO.getUserName());
			
			session.addAttribute(CONSULTA_CHEQUERAS_CUENTA, mapCuentas.get(forma.getNumero_cuenta()));
			
			chequesRequestTO.setCuenta(mapCuentas.get(forma.getNumero_cuenta()));
			ConsultaChequesResponseTO consultaChequesResponseTO = resourceFacadeSL.detalleVariasCuentasChequera(chequesRequestTO);
			List<TalonarioTO> coleccionChequeras=consultaChequesResponseTO.getColeccionChequeras();
			List<ChequeraTO> listDetalle_chequesTO= new ArrayList<ChequeraTO>();
			ChequeraTO resumen_chequerasTO;
			Map<String, String> mapChequeras= new HashMap<String, String>();
			int indice=0;
			for(TalonarioTO obj:coleccionChequeras){
				resumen_chequerasTO= new ChequeraTO();
				resumen_chequerasTO.setCheque_final(String.valueOf(obj.getChequeFinal()));
				resumen_chequerasTO.setCheque_inicial(String.valueOf(obj.getChequeInicial()));
				resumen_chequerasTO.setDescripcion(obj.getDescripcionChequera().trim());
				resumen_chequerasTO.setRegimen_firmas(obj.getRegimenFirmas().trim());
				resumen_chequerasTO.setImporte_piso(String.valueOf(obj.getImportePiso()));
				resumen_chequerasTO.setProteccion_chequera(String.valueOf(obj.getProteccion().trim()));
				resumen_chequerasTO.setEstado(obj.getDescStatus().trim());
				listDetalle_chequesTO.add(resumen_chequerasTO);
				mapChequeras.put(obj.getChequeInicial()+"-"+obj.getChequeFinal()+","+obj.getDescripcionChequera().trim(), String.valueOf(indice));
				indice++;
			}
			session.addAttribute(CONSULTA_CHEQUERAS_CHEQUERAS, mapChequeras);
			
			resumen_detalle_cheques.setColeccion_chequeras(listDetalle_chequesTO);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		Cuenta_CargoTO cuenta_cargoTO=new Cuenta_CargoTO();
		cuenta_cargoTO.setNumero_cuenta(cuenta_cargo.getNumeroCuenta14());
		cuenta_cargoTO.setProducto(cuenta_cargo.getDescripcion());
		cuenta_cargoTO.setSaldo_disponible(cuenta_cargo.getDisponible().toString());	
		response.addAttribute(cuenta_cargoTO);
		response.addAttribute(resumen_detalle_cheques);
		return response;
	}
	/**
	 * validacion
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Response validacion(Session session) throws Exception {		
		ConsultaChequesForm forma = (ConsultaChequesForm) getFormBean();
		ResourceFacadeSegundoSL resourceFacadeSL = getDelegateSegundo();
		Response response=new Response();
		Map<String, String> mapChequeras =(Map<String, String>)session.getAttribute(CONSULTA_CHEQUERAS_CHEQUERAS);
		String cuenta =(String)session.getAttribute(CONSULTA_CHEQUERAS_CUENTA);
		Informacion_chequeraTO informacion_chequera= new Informacion_chequeraTO();
		try{
			ClienteTO clienteTO = (ClienteTO ) session.getAttribute(CLIENTE_TO);
			ConsultaChequesRequestTO chequesRequestTO= new ConsultaChequesRequestTO();
			chequesRequestTO.setUser(clienteTO.getUserName());
			
			chequesRequestTO.setIndiceChequera(mapChequeras.get(forma.getCheque_inicial()+"-"+forma.getCheque_final()+","+forma.getDescripcion_chequera().trim()));
			chequesRequestTO.setCuenta(cuenta);
			
			ConsultaChequesResponseTO chequesResponseTO = resourceFacadeSL.consultaChequera(chequesRequestTO);
			
			String []estatusCheques = chequesResponseTO.getColeccionEstatus();
			
			List<Catalogo_estatus_chequesTO> listEstatusCheques= new ArrayList<Catalogo_estatus_chequesTO>();
			
			Map<String, String> mapEstatus = new HashMap<String, String>();
			String value;
			String label;
			for(int i = 0; i <= estatusCheques.length - 1; i++){
				int j = estatusCheques[i].indexOf(":");
				value=estatusCheques[i].substring(j + 1);
				label=estatusCheques[i].substring(0,j);
				listEstatusCheques.add(new Catalogo_estatus_chequesTO(value,label));
				mapEstatus.put(label,value);
			}
			informacion_chequera.setColeccion_estatus_cheques(listEstatusCheques);
			informacion_chequera.setNombre_cliente(clienteTO.getNombreCompleto());
			informacion_chequera.setNumero_chequera(chequesResponseTO.getNumeroChequera());
			informacion_chequera.setNumero_cuenta(chequesResponseTO.getNumeroCuenta());
			informacion_chequera.setTipo_chequera(chequesResponseTO.getTipoChequera());
			informacion_chequera.setTipo_cuenta(chequesResponseTO.getTipoCuenta());
			
			session.addAttribute(LISTA_ESTADOS_CHEQUES, mapEstatus);
			session.addAttribute(CONSULTA_CHEQUERA_DATOS, informacion_chequera);
			response.addAttribute(informacion_chequera);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	/**
	 * ejecucion
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public Response ejecucion(Session session) throws Exception {		
		ConsultaChequesForm forma = (ConsultaChequesForm) getFormBean();
		ResourceFacadeSegundoSL resourceFacadeSL = getDelegateSegundo();
		Response response=new Response();
		Map<String, String> mapEstatus=(Map<String, String>)session.getAttribute(LISTA_ESTADOS_CHEQUES);
		Informacion_chequeraTO datos=(Informacion_chequeraTO)session.getAttribute(CONSULTA_CHEQUERA_DATOS);
		try{
			ClienteTO clienteTO = (ClienteTO ) session.getAttribute(CLIENTE_TO);
			ConsultaChequesRequestTO chequesRequestTO= new ConsultaChequesRequestTO();
			
			chequesRequestTO.setOpcionConsulta(mapEstatus.get(forma.getEstado_cheques()));
			chequesRequestTO.setUser(clienteTO.getUserName());
			
			ConsultaChequesResponseTO chequesResponseTO=resourceFacadeSL.consultaChequeraDetalle(chequesRequestTO);
			Listado_detalle_chequeraTO resumen_detalle_chequesTO= new Listado_detalle_chequeraTO();
			List<Detalle_chequesTO> listDetalleCheques= new ArrayList<Detalle_chequesTO>();
			if(chequesResponseTO.getColeccionCheques() != null) 
			for (ChequeTO obj:chequesResponseTO.getColeccionCheques()){
				if(obj!=null)
				listDetalleCheques.add(new Detalle_chequesTO(String.valueOf(obj.getNumeroCheque()),obj.getStatus()));
			}
			resumen_detalle_chequesTO.setColeccion_detalle_cheques(listDetalleCheques);
			resumen_detalle_chequesTO.setColeccion_estatus_cheques(datos.getColeccion_estatus_cheques());
			resumen_detalle_chequesTO.setNombre_cliente(datos.getNombre_cliente());
			resumen_detalle_chequesTO.setNumero_chequera(datos.getNumero_chequera());
			resumen_detalle_chequesTO.setNumero_cuenta(datos.getNumero_cuenta());
			resumen_detalle_chequesTO.setTipo_chequera(datos.getTipo_chequera());
			resumen_detalle_chequesTO.setTipo_cuenta(datos.getTipo_cuenta());
			response.addAttribute(resumen_detalle_chequesTO);
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
}