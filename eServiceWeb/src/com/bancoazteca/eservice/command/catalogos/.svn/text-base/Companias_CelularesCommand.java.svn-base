package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Companias_celularesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class Companias_CelularesCommand extends CommandBase{

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		
		Companias_celularesTO compania_Collection =new Companias_celularesTO();
		Collection<String> carriers = new ArrayList<String>();
		carriers.add("telcel");
		carriers.add("iusacell");
		carriers.add("movistar");
		carriers.add("unefon");
		
		
		com.bancoazteca.eservice.command.base.beans.Companias_celularesTO compania = new com.bancoazteca.eservice.command.base.beans.Companias_celularesTO();
		
		for(String obj:carriers){ 
			compania_Collection.addCarrier(obj);
		}
		
		compania.setColeccion_companias(compania_Collection.getColeccion_companias());
		response.addAttribute(compania);

		return response;
	}
	
}
