package com.bancoazteca.eservice.command.enviodineroexpress.util;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class EnvioCorreoDineroExpressCommand extends CommandBase {

	public Response ejecucion(Session session)throws Exception{
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		Response response=new Response();
		
	 	EnvioCorreoDineroExpressForm form=(EnvioCorreoDineroExpressForm)getFormBean();
		
	 	EnvioDineroExpressRequestTO requestTO=new EnvioDineroExpressRequestTO();
	 	try{
	 		requestTO.setEmail(form.getCuenta_correo());
	 		requestTO.setMensajeMail(form.getMensaje_correo());
	 		requestTO.setUser(clienteTO.getUserName());
	 		
	 		EnvioDineroExpressResponseTO responseTO=getDelegate().enviaCorreoConfirmacionDineroExpress(requestTO);	 		
	 		response.addAttribute(responseTO.getMensajeCorreo());	 			 		
	 		
	 	}catch(EliteDataException e){
	 		buildErrorResponse(e, response);		 		
	 	}
		return response;
	}
}
