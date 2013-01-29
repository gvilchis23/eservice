package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.List;

import com.bancoazteca.elite.beans.LocalidadDEXTO;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Paises_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PaisesDineroExpressCommand extends CommandBase{
	
	public static final String PAISES_DEX="PaisesDineroExpressCommand.PAISES_DEX";
	
	 public Response ejecucion(Session session) throws Exception{
		 
		 Response response = new Response();
	     
		  
		 List<LocalidadDEXTO> listaPaises=(List<LocalidadDEXTO>)session.getAttribute(PAISES_DEX);
		 List<String> listaPaisesNombres=new ArrayList<String>();

		 for(LocalidadDEXTO dexto:listaPaises){
			 listaPaisesNombres.add(dexto.getValue());
		 }
		 
		 Paises_Dinero_ExpressTO paises = new Paises_Dinero_ExpressTO();
		 paises.setColeccion_paises(listaPaisesNombres);
		 response.addAttribute(paises);	 
	     
	     return response;
	 }
}