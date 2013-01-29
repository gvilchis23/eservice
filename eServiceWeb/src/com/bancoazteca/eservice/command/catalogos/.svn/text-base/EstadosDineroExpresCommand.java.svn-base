package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.LocalidadDEXTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.DineroExpressUtils;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Estados_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class EstadosDineroExpresCommand extends CommandBase{

	public static final String MAPA_ESTADOS="EstadosDineroExpresCommand.MAPA_ESTADOS";
	
	public Response ejecucion(Session session) throws Exception{
		 
		 Response response = new Response();
	     EstadosDineroExpresForm estadosForm = (EstadosDineroExpresForm) getFormBean();
		 EnvioDineroExpressResponseTO dineroExpressResponseTO = new EnvioDineroExpressResponseTO();
	     
		
		 try {
	    	dineroExpressResponseTO =getEstadosDineroExpress(session,estadosForm.getPais());
		
		    Estados_Dinero_ExpressTO estados = new Estados_Dinero_ExpressTO();
		    session.addAttribute(MAPA_ESTADOS, dineroExpressResponseTO.getEstadosDEX());		
			
		    Collection<String>estadosColl=new ArrayList<String>();
		    for(LocalidadDEXTO localidadDEXTO:dineroExpressResponseTO.getEstadosDEX())
		    	estadosColl.add(localidadDEXTO.getValue());		    			    		    
		    
		    estados.setColeccion_estados(estadosColl);
		    response.addAttribute(estados);
		    
		 } catch (EliteDataException e) {
			 if(e.getLevel()==8){
				 response.addAttribute("El pais no tiene envios dirigidos.");
			 }else
				 buildErrorResponse(e, response);
		 }	     
	     return response;
	}     
	
	
	public EnvioDineroExpressResponseTO getEstadosDineroExpress(Session session, String nombrePais) throws EliteDataException,  Exception{
		
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		EnvioDineroExpressRequestTO dineroExpressRequestTO = new EnvioDineroExpressRequestTO();
		EnvioDineroExpressResponseTO dineroExpressResponseTO = new EnvioDineroExpressResponseTO();
		
		if(session.getAttribute(PaisesDineroExpressCommand.PAISES_DEX)== null){
			 PaisesDineroExpressCommand paisesCommand = new PaisesDineroExpressCommand();
			 paisesCommand.ejecucion(session);
		 }
		 Collection<LocalidadDEXTO> listaPaises= (Collection<LocalidadDEXTO>)session.getAttribute(PaisesDineroExpressCommand.PAISES_DEX);
		 
		 String idPais = DineroExpressUtils.findIdCatalogoDineroExpress(listaPaises,nombrePais);		 
		 dineroExpressRequestTO.setPais(idPais);
		 dineroExpressRequestTO.setUser(clienteTO.getUserName());
		 ResourceFacadeSL facadeSL = getDelegate();
		 dineroExpressResponseTO = facadeSL.envioDineroExpressEstados(dineroExpressRequestTO);
	     
		return dineroExpressResponseTO;
	}
}