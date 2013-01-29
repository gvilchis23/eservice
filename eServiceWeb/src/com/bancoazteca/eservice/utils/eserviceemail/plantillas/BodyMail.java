package com.bancoazteca.eservice.utils.eserviceemail.plantillas;

import java.util.Iterator;
import java.util.Map;

public class BodyMail {
	private String contenido;
	public BodyMail(Map<String, String > parametros, String plantilla) {
		contenido=getPlantilla(plantilla);
		Iterator<Map.Entry<String, String>> it = parametros.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> tag = (Map.Entry<String, String>)it.next();
	        contenido = contenido.replace( tag.getKey() , tag.getValue() );
	    }
	}
	private String getPlantilla(String plantilla) {
		String  contenidoPlantilla= AdministradorPlantillasSession.getInstance().getPlantilla(plantilla);
		return contenidoPlantilla;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
