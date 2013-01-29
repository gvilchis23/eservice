package com.bancoazteca.eservice.command.validahuella;

import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Validacion_huellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ValidaHuellaCommand extends CommandBase {

	public Response solicitud (Session session) throws Exception {
		Response response = new Response();
		Map<String,String> errors= new HashMap<String,String>();
		try {
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
			
			if(clienteTO != null) {
				String nivelSeguridad=clienteTO.getSecurityData().getSecLevelVO().getNivelMovimientos();
				if(nivelSeguridad.equals("4")||nivelSeguridad.equals("6")){
					obtenLlavePublica(response,clienteTO);
				}else {
					errors= new HashMap<String,String>();
					errors.put("seguirdad", "Lo sentimos, no cuenta con el nivel de seguridad de huella.");
					throw new EliteDataException("Error", errors, 1);
				}
			}else {
				errors= new HashMap<String,String>();
				errors.put("sesion", "Lo sentimos, existio un error al validar la huella.");
				throw new EliteDataException("Error", errors, 1);
			}
			
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
			
		return response;
	}
	
	public Response ejecucion (Session session) throws Exception {
		Response response = new Response();
		
		ValidaHuellaForm forma = (ValidaHuellaForm) getFormBean();
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
 		String huellaClave=forma.getHuella_seguridad();
		HuellaDigitalTO huellaDigitalTO = new HuellaDigitalTO();
		huellaDigitalTO.setHuella(huellaClave);
		huellaDigitalTO.setUser(clienteTO.getUserName());
		
		Validacion_huellaTO validacion_huellaTO = new Validacion_huellaTO();
		boolean isValid = getDelegate().validaHuella(huellaDigitalTO);
		
		if(!isValid){				
			try {
				obtenLlavePublica(response,clienteTO);
				validacion_huellaTO.setHuella_valida(false);
				response.addAttribute(validacion_huellaTO);
			} catch (EliteDataException e){
				buildErrorResponse(e, response);
				return response;
			}
			
		}else {
			validacion_huellaTO.setHuella_valida(true);
			response.addAttribute(validacion_huellaTO);
		}
			
		
		return response;
	}
}
