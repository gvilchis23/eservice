package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.LocalidadDEXTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Ciudades_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class CiudadesDineroExpressCommand extends CommandBase{
	
	
	public static final String LISTA_CIUDADES_DEX="CiudadesDineroExpressCommand.CIUDADES_DEX";
	
	
	public Response ejecucion(Session session) throws Exception{
		 
		 Response response = new Response();
	     CiudadesDineroExpressForm ciudadesForm = (CiudadesDineroExpressForm) getFormBean();	    
		 EnvioDineroExpressResponseTO dineroExpressResponseTO = new EnvioDineroExpressResponseTO();
		 EnvioDineroExpressRequestTO requestTO =new EnvioDineroExpressRequestTO();
		 
		 
		 
	     try{

	    	 Collection<LocalidadDEXTO>estados=(ArrayList<LocalidadDEXTO>)session.getAttribute(EstadosDineroExpresCommand.MAPA_ESTADOS);
	    	 Collection<LocalidadDEXTO>paises=(ArrayList<LocalidadDEXTO>)session.getAttribute(PaisesDineroExpressCommand.PAISES_DEX);
	    
	    	 if(estados==null || paises==null){
	    		 Map<String, String>mapaError=new HashMap<String, String>();
	    		 mapaError.put("ServicioEstadosDEX", "Debe invocar al servicio de estados");
	    		 mapaError.put("ServicioPaisesDEX", "Debe invocar al servicio de paises");	    		
	    		 throw new EliteDataException("Error en llamado a servicio",mapaError,EliteRules.LEVEL_ACTION_ERRORS);	    		 
	    	 }
	    	 
	    	 for (LocalidadDEXTO localidadDEXTO : paises) {
				if(localidadDEXTO.getValue().equalsIgnoreCase(ciudadesForm.getPais())){
					requestTO.setPais(localidadDEXTO.getId()+"");
					break;
				}
	    	 }
	    	 
	    	 for (LocalidadDEXTO localidadDEXTO : estados) {
				if(localidadDEXTO.getValue().equalsIgnoreCase(ciudadesForm.getEstado())){
					requestTO.setEstado(localidadDEXTO.getId()+"");	
					break;
				} 
			}
	    	 ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
	    	 requestTO.setUser(clienteTO.getUserName());
	    	dineroExpressResponseTO=getDelegate().envioDineroExpressCiudades(requestTO);
	    	 
	    	Collection<LocalidadDEXTO> ciudades=dineroExpressResponseTO.getCiudadesDEX();
	    	
	    	session.addAttribute(LISTA_CIUDADES_DEX,ciudades);
	    	
	    	Collection<String>ciudadesLabel=new ArrayList<String>();
	    	
	    	for (LocalidadDEXTO localidadDEXTO : ciudades) {
				ciudadesLabel.add(localidadDEXTO.getValue());	    		
			}
	    	
			Ciudades_Dinero_ExpressTO ciudadesTO = new Ciudades_Dinero_ExpressTO();
			ciudadesTO.setColeccion_ciudades(ciudadesLabel);
			response.addAttribute(ciudadesTO);

	     }catch(EliteDataException e){
	    	 buildErrorResponse(e, response);
	     }
	     return response;
	}	
}