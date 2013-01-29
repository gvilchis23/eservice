package com.bancoazteca.eservice.command.conciliacion;

import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.ConciliacionRequestTO;
import com.bancoazteca.elite.beans.Conciliacion_ResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ConciliacionCommand extends CommandBase {
	
	public Response ejecucion(Session session) throws Exception {
	
		Response response = new Response();
		ConciliacionForm forma = (ConciliacionForm) getFormBean();
		ResourceFacadeSL bean = getDelegate();
		
		try{
			ConciliacionRequestTO conciliacionRequestTO = new ConciliacionRequestTO();			
			conciliacionRequestTO.setFechaInicial(forma.getFecha_inicial());
			conciliacionRequestTO.setPortalSolicitante(forma.getIdportal());
						
			Conciliacion_ResponseTO conciliacionResponseTO = bean.conciliacionOperacionesMercadoDinero(conciliacionRequestTO);
			response.addAttribute(conciliacionResponseTO);
		}catch (EliteDataException e){
			buildErrorResponse(e, response);
		}catch (InversionesGenericException e){			
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("Error TAS", e.getInversionesExceptionTO().getMessage());
			buildErrorResponse(new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS),response);
		}
		return response;
	}
}
