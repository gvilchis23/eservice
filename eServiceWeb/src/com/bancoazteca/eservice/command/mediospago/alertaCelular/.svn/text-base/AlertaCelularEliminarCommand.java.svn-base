package com.bancoazteca.eservice.command.mediospago.alertaCelular;

import java.util.HashMap;

import com.bancoazteca.elite.beans.AlertsDataRequest;
import com.bancoazteca.elite.beans.AlertsDataResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Alerta_Celular_Datos_InicialTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class AlertaCelularEliminarCommand extends CommandBase{
	
	private ResourceFacadeSL facade;
	
	public Response validacion(Session session) throws Exception{
		Response response = new Response();
		HashMap<String, String> errores = null;
		Alerta_Celular_Datos_InicialTO acDatos = new Alerta_Celular_Datos_InicialTO();
		Alerta_Celular_Datos_InicialTO acDatosMostrar = null;
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
		AlertsDataResponseTO alertaCelularResponse = null;
		
		try{
			facade = getDelegate();
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			
			acDatos = (Alerta_Celular_Datos_InicialTO)session.getAttribute(INIT_DATA_ALERTA_CELULAR);
			
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
			
			alertsDataRequest.setUser(clienteTO.getUserName());
			alertsDataRequest.setPhone(acDatos.getNumero_celular());
			
			if(acDatos.getEstatus_deposito().equalsIgnoreCase(ACTIVADO_ALERTA_CELULAR)){
				alertsDataRequest.setDeposito("true");
			}else{
				alertsDataRequest.setDeposito("false");
			}
			
			if(acDatos.getEstatus_retiro().equalsIgnoreCase(ACTIVADO_ALERTA_CELULAR)){
				alertsDataRequest.setCuenta("true");
			}else{
				alertsDataRequest.setCuenta("false");
			}

			alertsDataRequest.setMinDeposito(acDatos.getCantidad_minimo_deposito());
			alertsDataRequest.setMinCuenta(acDatos.getCantidad_minimo_retiro());
			alertsDataRequest.setCompany(acDatos.getCompania_celular());	
			alertsDataRequest.setBtnSUBMIT("");
						
			alertaCelularResponse = facade.getDataForUpdateAlertFirstStep(alertsDataRequest);
			
			acDatosMostrar = new Alerta_Celular_Datos_InicialTO();
			
			acDatosMostrar.setCuenta_seleccionada_formateada(alertaCelularResponse.getInitialData().get("cuentaSeleccionadaFormateada"));
			acDatosMostrar.setProducto(alertaCelularResponse.getInitialData().get("tipo"));
			acDatosMostrar.setNumero_celular(alertaCelularResponse.getInitialData().get("telefono"));
			acDatosMostrar.setCompania_celular(alertaCelularResponse.getInitialData().get("compania"));
			
			HuellaTO huellaTO = new HuellaTO();
			if(alertaCelularResponse.getDispositivoHuellaTO() != null) {
				huellaTO.setLlave_publica(alertaCelularResponse.getDispositivoHuellaTO().getLlavePublica());
				huellaTO.setLongitud_huella(alertaCelularResponse.getDispositivoHuellaTO().getLongitudHuella());
			}
			response.addAttribute(huellaTO);				
			response.addAttribute(acDatosMostrar);
			
			session.addAttribute(DATA_DELETE_ALERTA_CELULAR, acDatosMostrar);				
			session.addAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR, alertaCelularResponse.getSClaveConfirmacion());
			
		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}
		
		return response;
	}
	
	public Response ejecucion(Session session) throws Exception{
		Response response = new Response();
		Alerta_Celular_Datos_InicialTO acDatosMostrar = null;
		AlertsDataRequest alertsDataRequest = new AlertsDataRequest();
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			acDatosMostrar = (Alerta_Celular_Datos_InicialTO)session.getAttribute(DATA_DELETE_ALERTA_CELULAR);
			facade = getDelegate();
			AlertaCelularForm alertaCelularForm = (AlertaCelularForm)getFormBean();
					
			if (alertaCelularForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){				
				alertsDataRequest.setOptionDispositive(OPCION_HUELLA);
				alertsDataRequest.setClave(alertaCelularForm.getHuella_seguridad().toString());
				alertsDataRequest.setFlagSecurity(false);
			
			}else if (alertaCelularForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				alertsDataRequest.setOptionDispositive(OPCION_TOKEN);
				alertsDataRequest.setTokencode(alertaCelularForm.getClave_seguridad().toString());
				alertsDataRequest.setFlagSecurity(true);
				
			}
			
			String claveActivacionBack = (String) session.getAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR);
			
			alertsDataRequest.setUser(clienteTO.getUserName());
			alertsDataRequest.setClaveActivacion(alertaCelularForm.getClave_activacion());
			alertsDataRequest.setCompany(acDatosMostrar.getCompania_celular());
			alertsDataRequest.setClaveActivacionBack(claveActivacionBack);
			
			AlertsDataResponseTO alertaCelularResponse = facade.getDataForDeleteAccounts(alertsDataRequest);
			
			String mensaje = CONFIRMACION_ELIMINACION_ALERTA_CELULAR;
			response.addAttribute(mensaje);
			
			session.deleteAttribute(CLAVE_CONFIRMACION_ALERTA_CELULAR);
			session.deleteAttribute(DATA_DELETE_ALERTA_CELULAR);
			session.deleteAttribute(INIT_DATA_ALERTA_CELULAR);
			
		}catch( EliteDataException e ){
			buildErrorResponse( e, response );
		}		
		return response;
	}

}
