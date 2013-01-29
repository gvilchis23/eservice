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
import com.bancoazteca.eservice.command.base.beans.Sucursales_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class SucursalesDineroExpressCommand extends CommandBase {

	public static final String SUCURSALES_DEX="AgentesDineroExpressCommand.SUCURSALES";
	
	
	@SuppressWarnings("unchecked")
	public Response ejecucion(Session session)throws Exception{
		
		SucursalesDineroExpressForm form=(SucursalesDineroExpressForm) getFormBean();
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		Response response=new Response();
		try{
			
			Collection<LocalidadDEXTO>ciudades=(Collection<LocalidadDEXTO>)session.getAttribute(CiudadesDineroExpressCommand.LISTA_CIUDADES_DEX);
			Collection<LocalidadDEXTO>estados=(Collection<LocalidadDEXTO>)session.getAttribute(EstadosDineroExpresCommand.MAPA_ESTADOS);
			Collection<LocalidadDEXTO>paises=(Collection<LocalidadDEXTO>)session.getAttribute(PaisesDineroExpressCommand.PAISES_DEX);
			Collection<AgenteDEXTO>agentes=(Collection<AgenteDEXTO>)session.getAttribute(AgentesDineroExpressCommand.AGENTES_DEX);
			
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
			
			for (AgenteDEXTO agenteDEXTO : agentes) {
				if(agenteDEXTO.getValue().equalsIgnoreCase(form.getAgente())){
					requestTO.setAgente(agenteDEXTO.getId()+"");
					break;
				}
			}
			
			requestTO.setUser(clienteTO.getUserName());
			EnvioDineroExpressResponseTO responseTO = getDelegate().envioDineroExpressSucursal(requestTO);
			
			session.addAttribute(SUCURSALES_DEX, responseTO.getSucursalesDEX());
			
			
			Collection<String>sucursales=new ArrayList<String>();
			
			for (LocalidadDEXTO agenteDEXTO : responseTO.getSucursalesDEX()) {
				sucursales.add(agenteDEXTO.getValue());
			}
			
			Sucursales_Dinero_ExpressTO sucursalesDineroExpressTO=new Sucursales_Dinero_ExpressTO();
			sucursalesDineroExpressTO.setColeccion_sucursales(sucursales);
			
			response.addAttribute(sucursalesDineroExpressTO);
			
		}catch(EliteDataException dataException){
			buildErrorResponse(dataException, response);							
		}
		
		return response;
	}
}
