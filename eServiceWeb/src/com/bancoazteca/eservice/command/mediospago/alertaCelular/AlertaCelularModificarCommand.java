package com.bancoazteca.eservice.command.mediospago.alertaCelular;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.bancoazteca.elite.beans.AlertsDataRequest;
import com.bancoazteca.elite.beans.AlertsDataResponseTO;
import com.bancoazteca.elite.beans.BancoTefTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Alerta_Celular_Datos_InicialTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Medios_pagoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class AlertaCelularModificarCommand extends CommandBase{
	
	private ResourceFacadeSL facade;	
	
	public Response solicitud(Session session) throws Exception{
		
		Response response= new Response();
		facade = getDelegate();
		AlertsDataResponseTO alertaCelularResponse = null;
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
		AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
		Alerta_Celular_Datos_InicialTO datosIniciales = new Alerta_Celular_Datos_InicialTO();
		HashMap<String, String> errors = null;
		
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			Medios_pagoTO medios_pagos = (Medios_pagoTO) session.getAttribute(MEDIOS_PAGO_TARJETAS);
			
			alertsDataRequest.setUser(clienteTO.getUserName());
			ArrayList<Detalle_TarjetaTO> coleccionMediosPagos = (ArrayList<Detalle_TarjetaTO>)medios_pagos.getColeccion_medios_pago();
			
			//barremos la lista que viene en session, despues de consultar medios pagos
			for(int indice=0; indice<coleccionMediosPagos.size(); indice++){
				Detalle_TarjetaTO detalleTarjetaTO = (Detalle_TarjetaTO)coleccionMediosPagos.get(indice);
				//verificamos que el no de cuenta coincida con el que aparece en lista de cuentas de session
				
				if(detalleTarjetaTO.getNumero_cuenta().equals(alertaCelularForm.getNumero_cuenta())){
				//verificamos que el tipo de operacion coincida con el solicitado
					if(!detalleTarjetaTO.getAlerta_celular().equals(ACTIVAR_SERVICIO)){
				//obtenemos el indice en el que aparece en la lista.
						alertsDataRequest.setIndexAcount(""+indice);
						alertaCelularResponse = facade.getAlertsAcountsSelectedLinkUpdate(alertsDataRequest);
					}else{
				//regresamos mensaje de error en caso de que el tipo de operacion no coincida con el de la cuenta
						errors = new HashMap<String, String>();
						errors.put("numero_cuenta","Estimado usuario debe activar el servicio de alerta celular para intentar esta operación." );	
					}
				}				
			}
			
			if(errors!=null && errors.size()>0){
				returnErrorMap(response, errors);				
			}else{
			
				if(alertaCelularResponse.getInitialData() != null){							
					datosIniciales.setCuenta_seleccionada_formateada(alertaCelularResponse.getInitialData().get("cuentaSeleccionadaFormateada"));
					datosIniciales.setNumero_celular(alertaCelularResponse.getInitialData().get("telefono"));
					datosIniciales.setCompania_celular(alertaCelularResponse.getInitialData().get("compania"));
					datosIniciales.setCantidad_minimo_deposito(alertaCelularResponse.getInitialData().get("deposito"));
					datosIniciales.setCantidad_minimo_retiro(alertaCelularResponse.getInitialData().get("retiro"));
					if(alertaCelularResponse.getInitialData().get("modo")!=null){
						String tipoModo = (String)alertaCelularResponse.getInitialData().get("modo");
						if(tipoModo.equalsIgnoreCase("MX")){
							datosIniciales.setEstatus_deposito("ACTIVADO");
							datosIniciales.setEstatus_retiro("ACTIVADO");
						}else if(tipoModo.equalsIgnoreCase("RT")){
							datosIniciales.setEstatus_deposito("DESACTIVADO");
							datosIniciales.setEstatus_retiro("ACTIVADO");							
						}else if(tipoModo.equalsIgnoreCase("DP")){
							datosIniciales.setEstatus_deposito("ACTIVADO");
							datosIniciales.setEstatus_retiro("DESACTIVADO");
						}
					}
				}
				if(alertaCelularResponse.getInitialCompCel() != null){
					String total = (String) alertaCelularResponse.getInitialCompCel().get(COMPANIAS_TOTALES);
					int totalInt = Integer.parseInt(total);
					String compania = null;
					ArrayList<String> coleccionCompaniaCelularTO =  new ArrayList<String>();
					for(int i=1; i<=totalInt ; i++){
						compania = (String)alertaCelularResponse.getInitialCompCel().get(""+i);
						coleccionCompaniaCelularTO.add(compania);								
					}
					datosIniciales.setColeccion_companias_celulares(coleccionCompaniaCelularTO);
					session.addAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR, coleccionCompaniaCelularTO);
				}
			}
			
			response.addAttribute(datosIniciales);	
			session.addAttribute(INIT_DATA_ALERTA_CELULAR, datosIniciales);
		}catch(EliteDataException e){
			e.printStackTrace();
			buildErrorResponse(e, response);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return response;
	}
	
	public Response validacion(Session session) throws Exception{
		Response response = new Response();				
		Alerta_Celular_Datos_InicialTO datos = null;
		Map<String,String> errors= null;
		AlertsDataResponseTO alertsDataResponseTO = null;
		Alerta_Celular_Datos_InicialTO acDatosMostrar = null;
		
		try{
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
			facade = getDelegate();
			AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			
			Collection<String> coleccionCompaniaCelularTO = 
				(Collection<String>)session.getAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR);
			
			alertsDataRequest.setCmbCompany("");
			for(String companiaCelular : coleccionCompaniaCelularTO){
				if(alertaCelularForm.getCompania_celular().equals(companiaCelular)){
					alertsDataRequest.setCompany(alertaCelularForm.getCompania_celular());
				}
			}
			if(alertsDataRequest.getCompany().equals("")){
				errors = new HashMap<String, String>();
				errors.put("compania_celular","La compañia celular elegida es incorrecta para esta operación, por favor intente nuevamente." );
				returnErrorMap(response, errors);			
			}else{
				alertsDataRequest.setUser(clienteTO.getUserName());
				alertsDataRequest.setPhone(alertaCelularForm.getNumero_celular());
				alertsDataRequest.setDeposito(alertaCelularForm.getChkout_deposito());
				alertsDataRequest.setCuenta(alertaCelularForm.getChkout_retiro());
				alertsDataRequest.setMinDeposito(alertaCelularForm.getDeposito_minimo());
				alertsDataRequest.setMinCuenta(alertaCelularForm.getRetiro_minimo());
				alertsDataRequest.setBtnSUBMIT("Continuar");
				
				alertsDataResponseTO = facade.getDataForUpdateAlertFirstStepModificar(alertsDataRequest);
				
				acDatosMostrar = new Alerta_Celular_Datos_InicialTO();
				
				acDatosMostrar.setCuenta_seleccionada_formateada(alertsDataResponseTO.getInitialData().get("cuentaSeleccionadaFormateada"));
				acDatosMostrar.setProducto(alertsDataResponseTO.getInitialData().get("tipo"));
				acDatosMostrar.setNumero_celular(alertsDataResponseTO.getInitialData().get("telefono"));
				acDatosMostrar.setCompania_celular(alertsDataResponseTO.getInitialData().get("compania"));
				response.addAttribute(acDatosMostrar);
				session.addAttribute(MODIFICA_DATOS_ALERTA_CELULAR, acDatosMostrar);
				
				if(alertsDataResponseTO.getInitialData().get("montoDeposito")!=null){
					String cantidadDeposito = alertsDataResponseTO.getInitialData().get("montoDeposito");
					StringBuilder cantidad = new StringBuilder();
					cantidad.append(cantidadDeposito.substring(0, cantidadDeposito.length()-2));
					cantidad.append(".");
					cantidad.append(cantidadDeposito.substring(cantidadDeposito.length()-2, cantidadDeposito.length()));
					acDatosMostrar.setCantidad_minimo_deposito(cantidad.toString());
					acDatosMostrar.setEstatus_deposito(ACTIVADO_ALERTA_CELULAR);
				}else{
					acDatosMostrar.setCantidad_minimo_deposito(CANTIDAD_MINIMO_DEPOSITO);
					acDatosMostrar.setEstatus_deposito(DESACTIVADO_ALERTA_CELULAR);
				}
				if(alertsDataResponseTO.getInitialData().get("montoRetiro")!=null){
					String cantidadRetiro = alertsDataResponseTO.getInitialData().get("montoRetiro");
					StringBuilder cant = new StringBuilder();
					cant.append(cantidadRetiro.substring(0, cantidadRetiro.length()-2));
					cant.append(".");
					cant.append(cantidadRetiro.substring(cantidadRetiro.length()-2, cantidadRetiro.length()));
					acDatosMostrar.setCantidad_minimo_retiro(cant.toString());
					acDatosMostrar.setEstatus_retiro(ACTIVADO_ALERTA_CELULAR);					
				}else{
					acDatosMostrar.setCantidad_minimo_retiro(CANTIDAD_MINIMO_RETIRO);
					acDatosMostrar.setEstatus_retiro(DESACTIVADO_ALERTA_CELULAR);					
				}
				
				session.addAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR, alertsDataResponseTO.getSClaveConfirmacion());	

				HuellaTO huellaTO = new HuellaTO();
				DispositivoHuellaTO dispositivoHuellaTO = alertsDataResponseTO.getDispositivoHuellaTO();
				if( dispositivoHuellaTO != null ) {
					huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
					huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
				}
				
				response.addAttribute(huellaTO);

			}
		}catch(EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;		
	}
	
	public Response ejecucion(Session session) throws Exception{

		Response response = new Response();
		Alerta_Celular_Datos_InicialTO acDatosMostrar = null;
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
		AlertsDataResponseTO alertaCelularResponse = null;
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			acDatosMostrar = (Alerta_Celular_Datos_InicialTO)session.getAttribute(MODIFICA_DATOS_ALERTA_CELULAR);
			facade = getDelegate();
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
					
			if (alertaCelularForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				alertsDataRequest.setOptionDispositive(OPCION_HUELLA);
				alertsDataRequest.setClave(alertaCelularForm.getHuella_seguridad().toString());
			}else if (alertaCelularForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				alertsDataRequest.setOptionDispositive(OPCION_TOKEN);
				alertsDataRequest.setTokencode(alertaCelularForm.getClave_seguridad().toString());
			}
			
			alertsDataRequest.setUser(clienteTO.getUserName());
			alertsDataRequest.setClaveActivacion(alertaCelularForm.getClave_activacion());
			alertsDataRequest.setCompany(acDatosMostrar.getCompania_celular());
			alertsDataRequest.setPhone(acDatosMostrar.getNumero_celular());
			String claveBack = (String)session.getAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR);
			alertsDataRequest.setClaveActivacionBack(claveBack);
			
			alertaCelularResponse = facade.getDataForUpdateAlertFinalStep(alertsDataRequest);
			
			String mensaje = CONFIRMACION_ELIMINACION_ALERTA_CELULAR;
			response.addAttribute(mensaje);
			
			session.deleteAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR);
			session.deleteAttribute(MODIFICA_DATOS_ALERTA_CELULAR);
			session.deleteAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR);
			session.deleteAttribute(INIT_DATA_ALERTA_CELULAR);
			
		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}		
		return response;
	}	
	
	private class ColeccionMediosPagosPredicate implements Predicate {
		
		private String numCuenta;

		public boolean evaluate(Object obj) {
			Detalle_TarjetaTO detalleTarjetaTO = (Detalle_TarjetaTO)obj;
			if(detalleTarjetaTO.getNumero_cuenta().equals(getNumCuenta())){
				return true;
			}else{
				return false;
			}
		}

		private void setNumCuenta(String numCuenta) {
			this.numCuenta = numCuenta;
		}

		private String getNumCuenta() {
			return numCuenta;
		}	
	}	
}
