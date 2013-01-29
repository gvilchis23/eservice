package com.bancoazteca.eservice.command.frecuentes.pagoservicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Frecuente_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Servicio_ParametrizadoTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class FrecuenteServiciosModificaCommand extends CommandBase{
	
	public static final Logger log = Logger.getLogger(FrecuenteServiciosModificaCommand.class);
	
	public Response solicitud(Session session)throws Exception{
		Response response = new Response();
		FrecuenteServiciosConsultaCommand commando = new FrecuenteServiciosConsultaCommand();		
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		Frecuente_Servicio_ParametrizadoTO frecuentesPagoServicios = new Frecuente_Servicio_ParametrizadoTO();
		HashMap<String, String> errors = null;		
		try{
			FrecuenteServiciosModificaForm forma = (FrecuenteServiciosModificaForm)getFormBean();
			
			if(forma.getEstatus().equalsIgnoreCase("DESBLOQUEADA")){
//				OBTENEMOS REFERENCIAS FRECUENTES
				FrecuentesResponseTO solicitudFrecuentesResponseTO = commando.SolicitudPagoServicios(forma.getTipo_servicio().toLowerCase(), session);
				
//				VALIDAMOS REFERENCIAS FRECUENTES
				int seleccion = -1;
				int i = 0;
				for (FrecuentesResponseTO actual:solicitudFrecuentesResponseTO.getFrecuentes()){
					if (actual.getReferencia().equalsIgnoreCase(forma.getNumero_referencia())){
						if(actual.getStatus().equals("2")){
							errors = new HashMap<String, String>();
							errors.put("numero_referencia", "Por el momento esta referencia esta desactivada.");
						}else{
							seleccion=i;
							session.addAttribute(REFERENCIA_FRECUENTE_MODIFICAR, forma.getNumero_referencia());
						}
					}
					i++;
				}
				
				if(errors!=null){
					returnErrorMap(response, errors);
				}else if(seleccion == -1){
					/*
					 * mensaje de error en caso de que el numero de 
					 * referencia no coincida con las referencias dadas de alta en la cuenta
					 * */
					errors = new HashMap<String, String>();
					errors.put("numero_referencia","El numero de referencia para esta cuenta es incorrecto, verifique su petición e inténtelo nuevamente.");
					returnErrorMap(response, errors);
				}else{
					String userId = (String)session.getAttribute(USER_ID);
					ResourceFacadeSL resourceFacadeSL = getDelegate();
					
					//obtenemos del properties el id Servicio.
					PropertiesService propertiesService = PropertiesService.getInstance();
					String idServicio = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO + forma.getTipo_servicio().toLowerCase() );
					
					ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);				
					frecuentesRequestTO.setUser(clienteTO.getUserName());
					
					frecuentesRequestTO.setPaso("2");
					frecuentesRequestTO.setReferencia(""+seleccion);
					frecuentesRequestTO.setServicio(idServicio);
					frecuentesRequestTO.setUserId(userId);
					frecuentesRequestTO.setMethod("premodifica");
					
					//Obtenemos el tipo de referencia 
					String tipoReferencia = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO_REFERENCIA_TIPO + forma.getTipo_servicio().toLowerCase() );
					frecuentesRequestTO.setTipoServicio(tipoReferencia);
					frecuentesRequestTO.setReferenciaServicio("");
									
					resourceFacadeSL.setModificarFrecuentesParametrizado(frecuentesRequestTO);
					
					frecuentesPagoServicios.setNumero_referencia(forma.getNumero_referencia());
					frecuentesPagoServicios.setTipo_servicio(forma.getTipo_servicio().toUpperCase());
					response.addAttribute(frecuentesPagoServicios);
				}
			}else{
				errors = new HashMap<String, String>();
				errors.put("numero_referencia", "Por el momento esta referencia esta desactivada.");
				returnErrorMap(response, errors);
			}
			
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}	
		return response;
	}
	
	public Response validacion(Session session)throws Exception{
		Response response = new Response();
		FrecuenteServiciosConsultaCommand commando = new FrecuenteServiciosConsultaCommand();		
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		Frecuente_Servicio_ParametrizadoTO frecuentesPagoServicios = new Frecuente_Servicio_ParametrizadoTO();
		HashMap<String, String> errors = null;		
		try{
			FrecuenteServiciosModificaForm forma = (FrecuenteServiciosModificaForm)getFormBean();
			
			String noReferenciaSession = (String) session.getAttribute(REFERENCIA_FRECUENTE_MODIFICAR);
			
			if(noReferenciaSession == null){
				/*
				 * mensaje de error en caso de que el numero de 
				 * referencia no ha sido escogido en el paso anterior.
				 * */
				errors = new HashMap<String, String>();
				errors.put("numero_referencia","Estimado usuario el tipo de solicitud que esta " +
						"intentando de ejecutar es incorrecto, favor de seleccionar una referencia valida e inténtelo nuevamente.");
				returnErrorMap(response, errors);
			}else{
//				OBTENEMOS REFERENCIAS FRECUENTES
				FrecuentesResponseTO solicitudFrecuentesResponseTO = commando.SolicitudPagoServicios(forma.getTipo_servicio().toLowerCase(), session);
				
//				VALIDAMOS REFERENCIAS FRECUENTES
				
//				String noReferenciaSession = (String) session.getAttribute(REFERENCIA_FRECUENTE_MODIFICAR);
				int seleccion = -1;
				int i = 0;
				String referenciaSeleccionada = "";
				for (FrecuentesResponseTO actual:solicitudFrecuentesResponseTO.getFrecuentes()){
					if (actual.getReferencia().equalsIgnoreCase(forma.getNumero_referencia())){
						seleccion=i;
					}
					if(actual.getReferencia().equalsIgnoreCase(noReferenciaSession)){
						referenciaSeleccionada = "" +  i;
					}
					i++;
				}
				
				if(seleccion != -1){
					/*
					 * mensaje de error en caso de que el numero de 
					 * referencia no coincida con las referencias dadas de alta en la cuenta
					 * */
					errors = new HashMap<String, String>();
					errors.put("numero_referencia","Referencia existente en su lista de cuentas.");
					returnErrorMap(response, errors);
				}else{
					String userId = (String)session.getAttribute(USER_ID);
					ResourceFacadeSL resourceFacadeSL = getDelegate();
					
					//obtenemos del properties el id Servicio.
					PropertiesService propertiesService = PropertiesService.getInstance();
					String idServicio = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO + forma.getTipo_servicio().toLowerCase() );
					
					ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);				
					frecuentesRequestTO.setUser(clienteTO.getUserName());
					
					frecuentesRequestTO.setPaso("3");
					frecuentesRequestTO.setReferencia(referenciaSeleccionada);
					frecuentesRequestTO.setServicio(idServicio);
					frecuentesRequestTO.setUserId(userId);
					frecuentesRequestTO.setMethod("verificaModificada");
					
					//Obtenemos el tipo de referencia 
					String tipoReferencia = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO_REFERENCIA_TIPO + forma.getTipo_servicio().toLowerCase() );
					frecuentesRequestTO.setTipoServicio(tipoReferencia);
					frecuentesRequestTO.setReferenciaServicio(forma.getNumero_referencia());
									
					//metodo setModificarFrecuentesValidacion --- setModificarFrecuentes
					frecuentesResponseTO = resourceFacadeSL.setModificarFrecuentesValidacionParametrizado(frecuentesRequestTO);
					
					HuellaTO huellaTO = new HuellaTO();
					if(frecuentesResponseTO.getDispositivoHuellaTO() != null) {
						huellaTO.setLlave_publica(frecuentesResponseTO.getDispositivoHuellaTO().getLlavePublica());
						huellaTO.setLongitud_huella(frecuentesResponseTO.getDispositivoHuellaTO().getLongitudHuella());
					}
					
					frecuentesPagoServicios.setNumero_referencia(forma.getNumero_referencia());
					frecuentesPagoServicios.setTipo_servicio(forma.getTipo_servicio().toUpperCase());
					
					response.addAttribute(huellaTO);
					response.addAttribute(frecuentesPagoServicios);
				}			
			}
			

			
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}	
		return response;
	}
	
	public Response ejecucion(Session session)throws Exception{
		Response response = new Response();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		Frecuente_Servicio_ParametrizadoTO frecuente = new Frecuente_Servicio_ParametrizadoTO();
		
		try{
			FrecuenteServiciosModificaForm forma = (FrecuenteServiciosModificaForm)getFormBean();
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();			
			frecuentesRequestTO.setUser(clienteTO.getUserName());
			
			if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)){
				frecuentesRequestTO.setOptionDispositive(OPCION_HUELLA);
				frecuentesRequestTO.setClave(forma.getHuella_seguridad().toString());
			}else if (forma.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				frecuentesRequestTO.setOptionDispositive(OPCION_TOKEN);
				frecuentesRequestTO.setTokencode(forma.getClave_seguridad().toString());
			}
			
			frecuentesRequestTO.setMethod("modifica");			
			
			resourceFacadeSL.setModificarFrecuentesEjecucionParametrizado(frecuentesRequestTO);
			
			session.deleteAttribute(USER_ID);	
			frecuente.setTipo_servicio(forma.getTipo_servicio());
			frecuente.setNumero_referencia((String)session.getAttribute(REFERENCIA_FRECUENTE_MODIFICAR));
			session.deleteAttribute(REFERENCIA_FRECUENTE_MODIFICAR);
			response.addAttribute(frecuente);
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}	
		return response;
	}
}