package com.bancoazteca.eservice.command.operacionesFrecuentes;

import java.util.HashMap;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesRequestTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Operaciones_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class OperacionesFrecuentesEliminarCommand extends CommandBase{

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		
		//OPERACIONES FRECUENTES
		OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO = new OperacionesFrecuentesRequestTO();
		OperacionesFrecuentesResponseTO operacionesFrecuentesResponseTO = new OperacionesFrecuentesResponseTO();
		Operaciones_FrecuentesTO operacionFrecuente = new Operaciones_FrecuentesTO();
		HashMap<String, String> errors = null;
		try{
			ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
			ResourceFacadeSL resourceFacadeSL=getDelegate();
			OperacionesFrecuentesForm forma = (OperacionesFrecuentesForm)getFormBean();
			operacionesFrecuentesRequestTO.setClave_operacion(forma.getClave_operacion());
			operacionesFrecuentesRequestTO.setUsuarioAlnova(clienteTO.getNumero());
			operacionesFrecuentesRequestTO.setUser(clienteTO.getUserName());
			operacionesFrecuentesResponseTO = resourceFacadeSL.eliminaDatosOperacionesFrecuentes(operacionesFrecuentesRequestTO);
			
			if(operacionesFrecuentesResponseTO.getMensaje().equalsIgnoreCase(OPERACION_FRECUENTE_EXITO)){
				operacionFrecuente.setMensaje_operacion_frecuente("La operacion frecuente ha sido eliminada con exito.");
			}else{
				errors = new HashMap<String, String>();
				errors.put("mensaje_operacion_frecuente","Estimado usuario, lo sentimos por el momento no es posible procesar su petición. " +
							"Nuestros ingenieros ya estan trabajando para darle solución. Por su comprensión GRACIAS.");
				return returnErrorMap(response, errors);
			}
			response.addAttribute(operacionFrecuente);
		}catch(EliteDataException e) {
			buildErrorResponse(e, response);			
		}
		return response;
		
		
	}

}
