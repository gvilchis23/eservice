package com.bancoazteca.eservice.command.preguntasfrecuentes;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.PreguntasFrecuentesResponseTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Preguntas_FrecuentesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PreguntasFrecuentesCommand extends CommandBase {
	public static final Logger log = Logger.getLogger(PreguntasFrecuentesCommand.class);
	
	public Response ejecucion(Session session)throws Exception, XmlDecodeException{
		log.info("Método solicitud Preguntas Frecuentes");
		Response response = new Response();
		ResourceFacadeSL bean = getDelegate();
		
		PreguntasFrecuentesResponseTO responseTO = new PreguntasFrecuentesResponseTO();
		Preguntas_FrecuentesTO preguntas = new Preguntas_FrecuentesTO();

		responseTO = bean.getPreguntasFrecuentes();
		preguntas.setColeccion_preguntas_frecuentes(responseTO.getColeccion_preguntas_frecuentes());
		response.addAttribute(preguntas);
		return response; 
	}
}
