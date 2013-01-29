package com.bancoazteca.eservice.command.actualizaDatosUsuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.bancoazteca.elite.beans.ActualizaDatosRequestTO;
import com.bancoazteca.elite.beans.ActualizaDatosResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CompaniaViewTO;

import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Actualizar_Datos_UsuarioTO;
import com.bancoazteca.eservice.command.base.beans.Actualizar_Datos_Usuario_ConfirmacionTO;
import com.bancoazteca.eservice.command.base.beans.Compania_celularTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class ActualizaDatosCommand extends CommandBase {
	
	public Response solicitud(Session session)throws Exception{
		System.out.println("Solicitud - ActualizaCambios");
		Response response=new Response();
		ActualizaDatosRequestTO datosUsuarioRequestTO = new ActualizaDatosRequestTO();
		Actualizar_Datos_UsuarioTO usuariosCambioDetallesTO = new Actualizar_Datos_UsuarioTO();
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL = getDelegate();		
			datosUsuarioRequestTO.setUser(clienteTO.getUserName());
			ActualizaDatosResponseTO actualizaDatosResponseTO = resourceFacadeSL.getInitialActualizaDatos(datosUsuarioRequestTO);
			
			if(actualizaDatosResponseTO != null){
								
				usuariosCambioDetallesTO.setPregunta_secreta(actualizaDatosResponseTO.getPregunta());
				usuariosCambioDetallesTO.setRespuesta_pregunta_secreta(Cipher.valueOf(actualizaDatosResponseTO.getRespuesta()));
				usuariosCambioDetallesTO.setConfirmar_respuesta_pregunta_secreta(Cipher.valueOf(actualizaDatosResponseTO.getConfirmacionRespuesta()));
				usuariosCambioDetallesTO.setEmail(actualizaDatosResponseTO.getEmail());
				usuariosCambioDetallesTO.setCelular(actualizaDatosResponseTO.getCelular());
				usuariosCambioDetallesTO.setRecibir_correos_informativos(actualizaDatosResponseTO.isRecibirMail());
								
				if(actualizaDatosResponseTO.getCompaniasCelulares()!=null){
					
					session.addAttribute("COMPANIAS_CELULARES_ACTUALIZAR_DATOS", actualizaDatosResponseTO.getCompaniasCelulares());
					
					ArrayList<Compania_celularTO> coleccionCompaniaCelularTO =  new ArrayList<Compania_celularTO>();
					
					for(CompaniaViewTO compania : actualizaDatosResponseTO.getCompaniasCelulares()){
						
						coleccionCompaniaCelularTO.add(new Compania_celularTO(compania.getDesc()));
						
						if(actualizaDatosResponseTO.getCompania().equals(compania.getId())){
							
							usuariosCambioDetallesTO.setCompania_celular(compania.getDesc());
							
						}

					}
					
					usuariosCambioDetallesTO.setColeccion_companias_celulares(coleccionCompaniaCelularTO);
					
				}
				
			}
			response.addAttribute(usuariosCambioDetallesTO);

		} catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response validacion(Session session)throws Exception{
		System.out.println("validacion - ActualizaCambios");
		Response response=new Response();
		ActualizaDatosRequestTO datosUsuarioRequestTO = new ActualizaDatosRequestTO();
		Actualizar_Datos_UsuarioTO usuariosCambioDetallesTO = new Actualizar_Datos_UsuarioTO();
		
		try {
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ActualizaDatosForm actualizaDatosForm = (ActualizaDatosForm)getFormBean();
			ResourceFacadeSL resourceFacadeSL = getDelegate();	
			HashMap<String, String> errores = null;
			
			datosUsuarioRequestTO.setUser(clienteTO.getUserName());
			datosUsuarioRequestTO.setPregunta(actualizaDatosForm.getPregunta_secreta());
			datosUsuarioRequestTO.setRespuesta(actualizaDatosForm.getRespuesta_pregunta_secreta().toString());
			datosUsuarioRequestTO.setConfirmacion(actualizaDatosForm.getConfirmar_respuesta_pregunta_secreta().toString());
			datosUsuarioRequestTO.setEmail(actualizaDatosForm.getEmail());
			datosUsuarioRequestTO.setCelular(actualizaDatosForm.getCelular());
			if(actualizaDatosForm.getRecibir_correos_informativos().equals("true")){
				datosUsuarioRequestTO.setRecibirMail("on");
			}else{
				datosUsuarioRequestTO.setRecibirMail("off");
			}			
			Collection<CompaniaViewTO> companias = (Collection<CompaniaViewTO>) session.getAttribute("COMPANIAS_CELULARES_ACTUALIZAR_DATOS");
			datosUsuarioRequestTO.setCompania("");
			for(CompaniaViewTO compania : companias){
				if(compania.getDesc().equalsIgnoreCase(actualizaDatosForm.getCompania_celular())){
					datosUsuarioRequestTO.setCompania(compania.getId());
				}
			}
			if(datosUsuarioRequestTO.getCompania().equals("")){
				errores = new HashMap<String, String>();
				errores.put("compania_celular","El tipo de compania celular introducido es invalido" );
				return returnErrorMap(response, errores);
			}else{						
				ActualizaDatosResponseTO actualizaDatosResponseTO = resourceFacadeSL.getActualizaDatos(datosUsuarioRequestTO);
				
				HuellaTO huellaTO = new HuellaTO();
				DispositivoHuellaTO dispositivoHuellaTO = actualizaDatosResponseTO.getDispositivoHuellaTO();
				if( dispositivoHuellaTO != null ) {
					huellaTO.setLlave_publica( dispositivoHuellaTO.getLlavePublica() );
					huellaTO.setLongitud_huella( dispositivoHuellaTO.getLongitudHuella() );
				}		
				
				if(actualizaDatosResponseTO != null){
									
					usuariosCambioDetallesTO.setPregunta_secreta(actualizaDatosResponseTO.getPregunta());
					usuariosCambioDetallesTO.setRespuesta_pregunta_secreta(Cipher.valueOf(actualizaDatosResponseTO.getRespuestaControl()));
					usuariosCambioDetallesTO.setConfirmar_respuesta_pregunta_secreta(Cipher.valueOf(actualizaDatosResponseTO.getConfirmacionRespuesta()));
					usuariosCambioDetallesTO.setEmail(actualizaDatosResponseTO.getEmail());
					usuariosCambioDetallesTO.setCelular(actualizaDatosResponseTO.getCelular());
					for(CompaniaViewTO compania : companias){
						if(actualizaDatosResponseTO.getCompania().equalsIgnoreCase(compania.getId())){
							usuariosCambioDetallesTO.setCompania_celular(compania.getDesc());
						}
					}
					usuariosCambioDetallesTO.setRecibir_correos_informativos(actualizaDatosResponseTO.isRecibirMail());
					usuariosCambioDetallesTO.setColeccion_companias_celulares(null);								
				}
				response.addAttribute( huellaTO );
			}
			response.addAttribute(usuariosCambioDetallesTO);			
			session.addAttribute("DATOS_USUARIO_ACTUALIZAR_DATOS", usuariosCambioDetallesTO);

		} catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response ejecucion(Session session)throws Exception{
		System.out.println("EJECUCION - ActualizaCambios");
		Response response=new Response();
		ActualizaDatosRequestTO datosUsuarioRequestTO = new ActualizaDatosRequestTO();
		
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ActualizaDatosForm actualizaDatosForm = (ActualizaDatosForm)getFormBean();
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			
			datosUsuarioRequestTO.setUser(clienteTO.getUserName());
			
						
			if( actualizaDatosForm.getOpcion_seguridad().equalsIgnoreCase( TAG_TOKEN ) ) {
				datosUsuarioRequestTO.setOptionDispositive( OPCION_TOKEN );
				datosUsuarioRequestTO.setTokencode( actualizaDatosForm.getClave_seguridad().toString() );
			} else if (actualizaDatosForm.getOpcion_seguridad().equalsIgnoreCase( TAG_HUELLA ) ) {
				datosUsuarioRequestTO.setOptionDispositive( OPCION_HUELLA );
				datosUsuarioRequestTO.setClave( actualizaDatosForm.getHuella_seguridad().toString() );
			}
						
			ActualizaDatosResponseTO actualizaDatosResponseTO = resourceFacadeSL.getEjecutaActualizaDatos(datosUsuarioRequestTO);
	
			Actualizar_Datos_Usuario_ConfirmacionTO actualizar_Datos_Usuario_ConfirmacionTO = new Actualizar_Datos_Usuario_ConfirmacionTO();

			Actualizar_Datos_UsuarioTO usuariosCambioDetallesTO = (Actualizar_Datos_UsuarioTO)session.getAttribute("DATOS_USUARIO_ACTUALIZAR_DATOS");
			
			usuariosCambioDetallesTO.setColeccion_companias_celulares(null);
			
			actualizar_Datos_Usuario_ConfirmacionTO.setActualizar_Datos_UsuarioTO(usuariosCambioDetallesTO);
			actualizar_Datos_Usuario_ConfirmacionTO.setMensaje_Confirmacion(actualizaDatosResponseTO.getMensaje());
			
			response.addAttribute(actualizar_Datos_Usuario_ConfirmacionTO);
			session.deleteAttribute("COMPANIAS_CELULARES_ACTUALIZAR_DATOS");
			session.deleteAttribute("DATOS_USUARIO_ACTUALIZAR_DATOS");
			
		} catch (EliteDataException e){
			buildErrorResponse(e, response);
		}
		return response;		
	}

}
