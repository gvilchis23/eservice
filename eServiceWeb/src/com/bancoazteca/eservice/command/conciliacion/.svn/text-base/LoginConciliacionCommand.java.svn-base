package com.bancoazteca.eservice.command.conciliacion;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.LoginConciliacionRequestTO;
import com.bancoazteca.elite.beans.LoginConciliacionResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.ejb.inversiones.utils.ValidacionReportos;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Login_ConciliacionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class LoginConciliacionCommand extends CommandBase{
	public static final Logger log = Logger.getLogger(LoginConciliacionCommand.class);
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
	
		LoginConciliacionForm forma = (LoginConciliacionForm)getFormBean();
		LoginConciliacionRequestTO conciliacionRequestTO = new LoginConciliacionRequestTO();
		conciliacionRequestTO.setIdUsuario(forma.getId_usuario());
		
		try {
			ResourceFacadeSegundoSL facadeSL = getDelegateSegundo();
			LoginConciliacionResponseTO loginConciliacionResponseTO = facadeSL.getLoginConciliacion(conciliacionRequestTO);
			
			Login_ConciliacionTO conciliacionTO = new Login_ConciliacionTO();
			conciliacionTO.setTiene_acceso(loginConciliacionResponseTO.getTieneAcceso());
			if (!conciliacionTO.getTiene_acceso().equalsIgnoreCase("true")) {
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("Acceso Denegado", "El usuario no tiene permisos para esta operación");
				throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);				
			}
			response.addAttribute( conciliacionTO );
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
}