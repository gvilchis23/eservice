package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.AgenteDEXTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.LocalidadDEXTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Agentes_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class AgentesDineroExpressCommand extends CommandBase {

	public static final String AGENTES_DEX="AgentesDineroExpressCommand.AGENTES";
	
	
	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session)throws Exception{
		
		AgentesDineroExpressForm form=(AgentesDineroExpressForm) getFormBean();
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		Response response=new Response();
		try{
			
			Collection<LocalidadDEXTO>ciudades=(Collection<LocalidadDEXTO>)session.getAttribute(CiudadesDineroExpressCommand.LISTA_CIUDADES_DEX);
			Collection<LocalidadDEXTO>estados=(Collection<LocalidadDEXTO>)session.getAttribute(EstadosDineroExpresCommand.MAPA_ESTADOS);
			Collection<LocalidadDEXTO>paises=(Collection<LocalidadDEXTO>)session.getAttribute(PaisesDineroExpressCommand.PAISES_DEX);
			
			EnvioDineroExpressRequestTO requestTO=new EnvioDineroExpressRequestTO();
			
			 if(estados==null || paises==null || ciudades==null){
	    		 Map<String, String>mapaError=new HashMap<String, String>();
	    		 mapaError.put("ServicioEstadosDEX", "Debe invocar al servicio de estados");
	    		 mapaError.put("ServicioPaisesDEX", "Debe invocar al servicio de paises");	 
	    		 mapaError.put("ServicioCiudadesDEX", "Debe invocar al servicio de paises");	 
	    		 throw new EliteDataException("Error en llamado a servicio",mapaError,EliteRules.LEVEL_ACTION_ERRORS);	    		 
	    	 }
						
			for (LocalidadDEXTO localidadDEXTO : ciudades) {
				if(localidadDEXTO.getValue().equalsIgnoreCase(form.getCiudad())){
					requestTO.setCiudad(localidadDEXTO.getId()+"");
					break;
				}
			}
			
			for (LocalidadDEXTO localidadDEXTO : estados) {
				if(localidadDEXTO.getValue().equalsIgnoreCase(form.getEstado())){
					requestTO.setEstado(localidadDEXTO.getId()+"");
					break;
				}
			}
			
			for (LocalidadDEXTO localidadDEXTO : paises) {
				if(localidadDEXTO.getValue().equalsIgnoreCase(form.getPais())){
					requestTO.setPais(localidadDEXTO.getId()+"");
					break;
				}
			}
			requestTO.setUser(clienteTO.getUserName());
			EnvioDineroExpressResponseTO responseTO = getDelegate().envioDineroExpressAgentes(requestTO);
			
			session.addAttribute(AGENTES_DEX, responseTO.getAgentesDEX());
			
			
			Collection<String>agentes=new ArrayList<String>();
			
			for (AgenteDEXTO agenteDEXTO : responseTO.getAgentesDEX()) {
				agentes.add(agenteDEXTO.getValue());
			}
			
			Agentes_Dinero_ExpressTO agentesDineroExpressTO=new Agentes_Dinero_ExpressTO();
			agentesDineroExpressTO.setColeccion_agentes(agentes);
			
			response.addAttribute(agentesDineroExpressTO);
			
		}catch(EliteDataException dataException){
			buildErrorResponse(dataException, response);							
		}
		
		return response;
	}
}
