package com.bancoazteca.eservice.command.frecuentes.pagoservicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Frecuente_ServicioTO;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Servicio_ParametrizadoTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class FrecuenteServiciosEliminaCommand extends CommandBase{
	public static final Logger log = Logger.getLogger(FrecuenteServiciosEliminaCommand.class);
	
	public Response validacion(Session session)throws Exception{
		Response response = new Response();
		FrecuenteServiciosConsultaCommand commando = new FrecuenteServiciosConsultaCommand();		
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		Frecuente_Servicio_ParametrizadoTO frecuentesPagoServicios = new Frecuente_Servicio_ParametrizadoTO();
		HashMap<String, String> errors = null;
		try{
			FrecuenteServiciosEliminaForm forma = (FrecuenteServiciosEliminaForm)getFormBean();
			
			
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
							session.addAttribute(REFERENCIA_FRECUENTE_ELIMINAR, forma.getNumero_referencia());
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
						frecuentesRequestTO.setMethod("preelimina");
						
						//Obtenemos el tipo de referencia 
						String tipoReferencia = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO_REFERENCIA_TIPO + forma.getTipo_servicio().toLowerCase() );
						frecuentesRequestTO.setTipoServicio(tipoReferencia);
						frecuentesRequestTO.setReferenciaServicio("");
										
						//setEliminarFrecuentesValidacion
						frecuentesResponseTO = resourceFacadeSL.setEliminarFrecuentesValidacionParametrizado(frecuentesRequestTO);
						
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
	
	public Response ejecucion(Session session)throws Exception{
		
		Response response = new Response();
		FrecuenteServiciosConsultaCommand commando = new FrecuenteServiciosConsultaCommand();		
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		Frecuente_Servicio_ParametrizadoTO frecuentesPagoServicios = new Frecuente_Servicio_ParametrizadoTO();
		HashMap<String, String> errors = null;		
		try{
			FrecuenteServiciosEliminaForm forma = (FrecuenteServiciosEliminaForm)getFormBean();
			
			String seleccion = (String) session.getAttribute(REFERENCIA_FRECUENTE_ELIMINAR);
			
			if(seleccion == null){
				/*
				 * mensaje de error en caso de que el numero de 
				 * referencia no ha sido escogido en el paso anterior.
				 * */
				errors = new HashMap<String, String>();
				errors.put("numero_referencia","Estimado usuario el tipo de solicitud que esta " +
						"intentando de ejecutar es incorrecto, favor de seleccionar una referencia valida e inténtelo nuevamente.");
				returnErrorMap(response, errors);
			}else if(!seleccion.equals(forma.getNumero_referencia())){
				/*
				 * mensaje de error en caso de que el numero de 
				 * referencia no coincida con las referencias dadas de alta en la cuenta
				 * */
				errors = new HashMap<String, String>();
				errors.put("numero_referencia","El numero de referencia para esta operacion es incorrecto, verifique su petición e inténtelo nuevamente.");
				returnErrorMap(response, errors);
			}else{
				String userId = (String)session.getAttribute(USER_ID);
				ResourceFacadeSL resourceFacadeSL = getDelegate();
				
				//obtenemos del properties el id Servicio.
				PropertiesService propertiesService = PropertiesService.getInstance();
				String idServicio = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO + forma.getTipo_servicio().toLowerCase() );
				
				ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);				
				frecuentesRequestTO.setUser(clienteTO.getUserName());
				
				frecuentesRequestTO.setPaso("4");
				frecuentesRequestTO.setReferencia(""+seleccion);
				frecuentesRequestTO.setServicio(idServicio);
				frecuentesRequestTO.setUserId(userId);
				frecuentesRequestTO.setMethod("elimina");
				
				//Obtenemos el tipo de referencia 
				String tipoReferencia = propertiesService.getPropertie( PAGO_SERVICIO_PARAMETRIZABLE , PAGO_SERVICIO_REFERENCIA_TIPO + forma.getTipo_servicio().toLowerCase() );
				frecuentesRequestTO.setTipoServicio(tipoReferencia);
				frecuentesRequestTO.setReferenciaServicio("");
								
				frecuentesResponseTO = resourceFacadeSL.setEliminarFrecuentesEjecucionParametrizado(frecuentesRequestTO);
							
				if(frecuentesResponseTO.getFrecuentes()!=null && frecuentesResponseTO.getFrecuentes().size()>0){
					Collection<Frecuente_ServicioTO> coleccion = new ArrayList <Frecuente_ServicioTO>();
					Frecuente_ServicioTO frecuente = null;
					for (FrecuentesResponseTO frecuenteResponse:frecuentesResponseTO.getFrecuentes()){
						frecuente = new Frecuente_ServicioTO();
						frecuente.setNumero_referencia(frecuenteResponse.getReferencia());
						coleccion.add(frecuente);
					}
					frecuentesPagoServicios.setColeccion_frecuentes(coleccion);
				}
				frecuentesPagoServicios.setTipo_servicio(forma.getTipo_servicio().toUpperCase());
				session.deleteAttribute(USER_ID);	
				session.deleteAttribute(REFERENCIA_FRECUENTE_ELIMINAR);
				response.addAttribute(frecuentesPagoServicios);
			}
			
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}	
		return response;
	}
}