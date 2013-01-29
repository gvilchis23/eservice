package com.bancoazteca.eservice.ws;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.eservice.command.base.CommandService;


public class EService {
	 private static final Logger log = Logger.getLogger(EService.class);
	 
	 private static final String genericResponse = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><bancoazteca><eservices><response><data_service /><status><idservicio /><error_sistema /><descripcion_codigo	value=\"Ha ocurrido un problema de comunicación con la aplicación.\" /><idsesion /><codigo_operacion value=\"-1\" /><tipo_operacion /></status></response></eservices></bancoazteca>";
	 
	 public String eServices(String xml) {
	
		log.info("\n\n***** eServices Petición al WS: "+xml);
		xml=xml.replaceAll(">( )*<", "><").trim();
		MessageElement messageElement = null;
		String respuesta =null;
		try {
			
			CommandService commandService = null;
			synchronized(this){
				messageElement = XMLDecode.buildXMLMessageElement(xml);
				commandService = new CommandService();
			}
			respuesta = commandService.service(messageElement);
			respuesta = respuesta.replaceAll("itemValue", "value");
			
			
		}catch (XmlDecodeException e) {
			respuesta = genericResponse;
			e.printStackTrace();
		} catch (Exception e) {
			respuesta = genericResponse;
			e.printStackTrace();
		}		
		log.info("\n\n***** eServices Salida del WS: "+respuesta);
		return respuesta;
	}
	
}