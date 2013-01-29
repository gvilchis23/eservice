package com.bancoazteca.eservice.command.validatoken;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.TokenTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Token_ValidacionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ValidaTokenCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(ValidaTokenCommand.class);
	
	public Response ejecutar(Session session)throws Exception, XmlDecodeException{
		log.info("Metodo validación de token");
		Response response = new Response();

		TokenTO tokenTO = new TokenTO();
		Token_ValidacionTO tokenResponseTO = new Token_ValidacionTO(); 
		ValidaTokenForm forma = (ValidaTokenForm) getFormBean();
		
		String user = forma.getUser();
		tokenTO.setTokenCode(forma.getToken_code().toString());
		tokenTO.setUser(user);
		
		ResourceFacadeSL bean = getDelegate();
			
		try {
			boolean respuesta = bean.validaToken(tokenTO);
			tokenResponseTO.setValido(String.valueOf(respuesta));
			
			response.addAttribute(tokenResponseTO);
			
			log.info("--------- Validacion de token ----- " + respuesta);
			
		}catch(EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

}