package com.bancoazteca.eservice.command.mediospago.alertaCelular;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.AlertCompaniaTO;
import com.bancoazteca.elite.beans.AlertsDataRequest;
import com.bancoazteca.elite.beans.AlertsDataResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Alerta_Celular_Datos_InicialTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.Medios_pagoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;

public class AlertaCelularActivarCommand extends CommandBase{
	
	private ResourceFacadeSL facade;

	public Response solicitud(Session session) throws Exception{
		AlertsDataResponseTO alertaCelularResponse = null;
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();		
		Response response= new Response();
		HashMap<String, String> errors = null;		
		Alerta_Celular_Datos_InicialTO datosIniciales = new Alerta_Celular_Datos_InicialTO();
		
		try{
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
			facade = getDelegate();
			
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			Medios_pagoTO medios_pagos = (Medios_pagoTO) session.getAttribute(MEDIOS_PAGO_TARJETAS);
			
			alertsDataRequest.setUser(clienteTO.getUserName());
			ArrayList<Detalle_TarjetaTO> coleccionMediosPagos = (ArrayList<Detalle_TarjetaTO>)medios_pagos.getColeccion_medios_pago();
			
			alertsDataRequest.setIndexAcount("");
			//barremos la lista que viene en session, despues de consultar medios pagos
			for(int indice=0; indice<coleccionMediosPagos.size(); indice++){
				Detalle_TarjetaTO detalleTarjetaTO = (Detalle_TarjetaTO)coleccionMediosPagos.get(indice);

				//verificamos que el no de cuenta coincida con el que aparece en lista de cuentas de session
				if(detalleTarjetaTO.getNumero_cuenta().equals(alertaCelularForm.getNumero_cuenta())){

					//verificamos que el tipo de operacion coincida con el solicitado
					if(detalleTarjetaTO.getAlerta_celular().equals(ACTIVAR_SERVICIO)){
						alertsDataRequest.setIndexAcount(""+indice);
					}
				}
			}
			if(alertsDataRequest.getIndexAcount().equals("")){
//				regresamos mensaje de error en caso de que el tipo de operacion no coincida con el de la cuenta
				errors = new HashMap<String, String>();
				errors.put("tipo_operacion","El tipo de servicio para esta cuenta es incorrecto, verifique su petición e inténtelo nuevamente.");
				returnErrorMap(response, errors);
			}else{
				alertaCelularResponse = facade.setAlertsAcountsSelectedLink(alertsDataRequest);
				if(alertaCelularResponse != null){
					if(alertaCelularResponse.getInitialData() != null){	
						datosIniciales.setCuenta_seleccionada_formateada(alertaCelularResponse.getInitialData().get(CUENTA_SELECCIONADA));
						datosIniciales.setProducto(alertaCelularResponse.getInitialData().get(TIPO_PRODUCTO));					
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
						session.addAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR, alertaCelularResponse.getInitialCompCel());
					}
				}				
			}
			response.addAttribute(datosIniciales);
		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		return response;		
	}
	
	public Response validacion(Session session) throws Exception{
		Response response = new Response();						
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
		Alerta_Celular_Datos_InicialTO alertaCelularDatos = new Alerta_Celular_Datos_InicialTO();
		AlertsDataResponseTO alertsDataResponseTO = new AlertsDataResponseTO();
		HashMap<String, String> errors = null;
		try{
			
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
			facade = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			
			Map<String, String> coleccionCompaniaCelularTO = 
				(Map<String, String>) session.getAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR);
			
			if(coleccionCompaniaCelularTO != null){
				String total = (String) coleccionCompaniaCelularTO.get(COMPANIAS_TOTALES);
				int totalInt = Integer.parseInt(total);
				String claveCompania = null;
				String companiaCelular = null;
				alertsDataRequest.setCompany("");
				for(int index=1; index<=totalInt ; index++){
					companiaCelular = (String)coleccionCompaniaCelularTO.get(""+index);
					if(companiaCelular.equalsIgnoreCase(alertaCelularForm.getCompania_celular())){
						alertsDataRequest.setCompany(""+index);
					}
				}
				if(alertsDataRequest.getCompany().equals("")){
					errors = new HashMap<String, String>();
					errors.put("compania_celular","La compañia de celular es incorrecta, verifique su petición e inténtelo nuevamente." );
					returnErrorMap(response, errors);
				}else{
					alertsDataRequest.setUser(clienteTO.getUserName());
					alertsDataRequest.setPhone(alertaCelularForm.getNumero_celular());
					alertsDataRequest.setDeposito(alertaCelularForm.getChkout_deposito());
					alertsDataRequest.setRetiro(alertaCelularForm.getChkout_retiro());
					alertsDataRequest.setMinDeposito(alertaCelularForm.getDeposito_minimo());
					alertsDataRequest.setMinCuenta(alertaCelularForm.getRetiro_minimo());
					
					alertsDataResponseTO = facade.getDataAlertFirstStep(alertsDataRequest);
					
					if(alertsDataResponseTO!= null){
						if(alertsDataResponseTO.getInitialData() != null){
							alertaCelularDatos.setCuenta_seleccionada_formateada(alertsDataResponseTO.getInitialData().get("cuentaSeleccionada"));
							alertaCelularDatos.setProducto(alertsDataResponseTO.getInitialData().get("tipoUsuario"));
							alertaCelularDatos.setNumero_celular(alertsDataResponseTO.getInitialData().get("telefono"));
							alertaCelularDatos.setCompania_celular(alertsDataResponseTO.getInitialData().get("compania"));
							
							if(alertsDataResponseTO.getInitialData().get("montoDeposito")!=null){
								String cantidadDeposito = alertsDataResponseTO.getInitialData().get("montoDeposito");
								StringBuilder cantidad = new StringBuilder();
								cantidad.append(cantidadDeposito.substring(0, cantidadDeposito.length()-2));
								cantidad.append(".");
								cantidad.append(cantidadDeposito.substring(cantidadDeposito.length()-2, cantidadDeposito.length()));
								alertaCelularDatos.setCantidad_minimo_deposito(cantidad.toString());
								alertaCelularDatos.setEstatus_deposito(ACTIVADO_ALERTA_CELULAR);
							}else{
								alertaCelularDatos.setCantidad_minimo_deposito(CANTIDAD_MINIMO_RETIRO);
								alertaCelularDatos.setEstatus_deposito(DESACTIVADO_ALERTA_CELULAR);
							}
							if(alertsDataResponseTO.getInitialData().get("montoRetiro")!=null){
								String cantidadRetiro = alertsDataResponseTO.getInitialData().get("montoRetiro");
								StringBuilder cant = new StringBuilder();
								cant.append(cantidadRetiro.substring(0, cantidadRetiro.length()-2));
								cant.append(".");
								cant.append(cantidadRetiro.substring(cantidadRetiro.length()-2, cantidadRetiro.length()));
								alertaCelularDatos.setCantidad_minimo_retiro(cant.toString());
								alertaCelularDatos.setEstatus_retiro(ACTIVADO_ALERTA_CELULAR);					
							}else{
								alertaCelularDatos.setCantidad_minimo_retiro(CANTIDAD_MINIMO_DEPOSITO);
								alertaCelularDatos.setEstatus_retiro(DESACTIVADO_ALERTA_CELULAR);					
							}							
						}
					}
				}
			}
			
			//guardamos clave de confirmacion en session.
			session.addAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR, alertsDataResponseTO.getSClaveConfirmacion());
			session.addAttribute(ACTIVA_DATOS_ALERTA_CELULAR, alertaCelularDatos);
			response.addAttribute(alertaCelularDatos);	
			
		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		
		return response;		
	}
	
	/*paso 3*/
	public Response solicitud_clave_confirmacion(Session session) throws Exception{
		Response response = new Response();						
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
		AlertsDataResponseTO alertsDataResponseTO = new AlertsDataResponseTO();
		HashMap<String, String> errores = null;
		
		try{
				
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
			facade = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			Alerta_Celular_Datos_InicialTO alertaCelularDatos = (Alerta_Celular_Datos_InicialTO)session.getAttribute(ACTIVA_DATOS_ALERTA_CELULAR);
			String cConfirmacion = (String)session.getAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR);
			Map<String, String> coleccionCompaniaCelularTO = 
				(Map<String, String>) session.getAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR);
			
			alertsDataRequest.setUser(clienteTO.getUserName());
			alertsDataRequest.setPhone(alertaCelularDatos.getNumero_celular());
			alertsDataRequest.setCompany(alertaCelularDatos.getCompania_celular());
			if(coleccionCompaniaCelularTO != null){
				String total = (String) coleccionCompaniaCelularTO.get(COMPANIAS_TOTALES);
				int totalInt = Integer.parseInt(total);
				String claveCompania = null;
				String companiaCelular = null;
				for(int index=1; index<=totalInt ; index++){
					companiaCelular = (String)coleccionCompaniaCelularTO.get(""+index);
					if(companiaCelular.equalsIgnoreCase(alertaCelularDatos.getCompania_celular())){
						alertsDataRequest.setCmbCompany(""+index);
					}
				}
			}
			
			alertsDataRequest.setClaveActivacionBack(cConfirmacion);
			alertsDataRequest.setClaveActivacion(alertaCelularForm.getClave_activacion());
			
			alertsDataResponseTO = facade.getDataAlertSecondStep(alertsDataRequest);
			session.addAttribute(DATA_REQUEST_ALERTA_CELULAR, alertsDataRequest);
			HuellaTO huellaTO = new HuellaTO();
			if(alertsDataResponseTO.getDispositivoHuellaTO() != null) {
				huellaTO.setLlave_publica(alertsDataResponseTO.getDispositivoHuellaTO().getLlavePublica());
				huellaTO.setLongitud_huella(alertsDataResponseTO.getDispositivoHuellaTO().getLongitudHuella());
			}
			response.addAttribute(huellaTO);
			response.addAttribute(alertaCelularDatos);
			
		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		
		return response;
	}
	
	/*paso 4*/
	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
		HashMap<String, String> errores = null;
		
		try{
			
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
			facade = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			Alerta_Celular_Datos_InicialTO alertaCelularDatos = (Alerta_Celular_Datos_InicialTO)session.getAttribute(ACTIVA_DATOS_ALERTA_CELULAR);
			String cConfirmacion = (String)session.getAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR);
			
			if (alertaCelularForm.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ){
				alertsDataRequest.setOptionDispositive(OPCION_HUELLA);
				alertsDataRequest.setClave(alertaCelularForm.getHuella_seguridad().toString());
				alertsDataRequest.setFlagSecurity(false);

			}else if (alertaCelularForm.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ){
				alertsDataRequest.setOptionDispositive(OPCION_TOKEN);
				alertsDataRequest.setTokencode(alertaCelularForm.getClave_seguridad().toString());
				alertsDataRequest.setFlagSecurity(true);

			}
			
			alertsDataRequest.setUser(clienteTO.getUserName());
			alertsDataRequest.setPhone(alertaCelularDatos.getNumero_celular());
			alertsDataRequest.setClaveActivacionBack(cConfirmacion);
			alertsDataRequest.setCompany(alertaCelularDatos.getCompania_celular());
			Map<String, String> coleccionCompaniaCelularTO = 
				(Map<String, String>) session.getAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR);
			
			if(coleccionCompaniaCelularTO != null){
				String total = (String) coleccionCompaniaCelularTO.get(COMPANIAS_TOTALES);
				int totalInt = Integer.parseInt(total);
				String claveCompania = null;
				String companiaCelular = null;
				for(int index=1; index<=totalInt ; index++){					
					companiaCelular = (String)coleccionCompaniaCelularTO.get(""+index);
					if(companiaCelular.equalsIgnoreCase(alertaCelularDatos.getCompania_celular())){
						alertsDataRequest.setCmbCompany(""+index);
					}
				}
			}
			
			facade.getDataAlertThirdStep(alertsDataRequest);
			
			response.addAttribute(alertaCelularDatos);
			
			session.deleteAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR);
			session.deleteAttribute(COLECCION_COMPANIAS_ALERTA_CELULAR);
			session.deleteAttribute(ACTIVA_DATOS_ALERTA_CELULAR);

		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}		
		return response;
	}
}
