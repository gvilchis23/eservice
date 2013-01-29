package com.bancoazteca.eservice.command.enviomail;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.EnvioMailRequestTO;
import com.bancoazteca.elite.beans.EnvioMailResponseTO;
import com.bancoazteca.elite.beans.SpeiProgramadoRequestTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Envio_Mail_EjecucionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.command.transferencias.spei.TransferenciaSpeiCommand;

public class EnvioMailCommand extends CommandBase {
		
	public Response ejecucion(Session session) throws Exception {	
		Response response = new Response();

		try {
			EnvioMailForm forma = (EnvioMailForm) getFormBean();
			ResourceFacadeSL resourceFacadeSL = getDelegate();
			EnvioMailRequestTO envioMailRequestTO = new EnvioMailRequestTO();
			Envio_Mail_EjecucionTO envioMailEjecucion = new Envio_Mail_EjecucionTO();
			ClienteTO clienteTO =(ClienteTO) session.getAttribute(CLIENTE_TO);
			
			envioMailRequestTO.setCorreo_electronico_destino(forma.getCorreo_electronico_destino());
			envioMailRequestTO.setMensaje(forma.getMensaje());
			envioMailRequestTO.setTipo_envio(forma.getTipo_envio());
			envioMailRequestTO.setUser(clienteTO.getUserName());
			
			SpeiProgramadoRequestTO speiRequest30TO=(SpeiProgramadoRequestTO)session.getAttribute(TransferenciaSpeiCommand.DATOS_FINALES);
			if(speiRequest30TO!=null){
				envioMailRequestTO.setTipo_envio("Spei30");
				envioMailRequestTO.setSpeiRequest30TO(speiRequest30TO);
			}
			
			 
			Method metodo = resourceFacadeSL.getClass().getDeclaredMethod("envioMail" 
																		+ toReflection(envioMailRequestTO.getTipo_envio()),
																		new Class[]{EnvioMailRequestTO.class}); 
			metodo.setAccessible(true);
			EnvioMailResponseTO responseTO = (EnvioMailResponseTO)metodo.invoke(resourceFacadeSL, new Object[]{envioMailRequestTO});
			envioMailEjecucion.setEstatus_envio(responseTO.getEstatus_envio());
			response.addAttribute(envioMailEjecucion);
		} catch(Exception e){
			if(e instanceof MalformedParameterizedTypeException 
					|| e instanceof UndeclaredThrowableException
					|| e instanceof NoSuchMethodException){
				System.out.println( e.getStackTrace());
				Throwable exception= new Throwable();
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("error_tipo_envio ", "El tipo de envío solicitado no existe");
				Object errorData = map;
				buildErrorResponse( new EliteDataException(exception,errorData,EliteRules.LEVEL_ACTION_ERRORS), response);
			}else if(  e instanceof EliteDataException ){
				buildErrorResponse( (EliteDataException)e, response );
			}else{
				throw e;
			}
			
		}
		return response;
	}
	
	public static void main(String[] args) {
		
	}
	
	public  String toReflection(String metodo){
		metodo = metodo.substring(0,1).toUpperCase() +  metodo.substring(1).toLowerCase();
		return metodo;
	}
	
}
