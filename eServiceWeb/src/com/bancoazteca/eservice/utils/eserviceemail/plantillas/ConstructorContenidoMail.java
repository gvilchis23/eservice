package com.bancoazteca.eservice.utils.eserviceemail.plantillas;

import java.util.Map;

public class ConstructorContenidoMail {
	
	private String _body;
	
	
	public boolean setBody(Map<String, String> parametros , String plantilla) {
		try{
			BodyMail bodyMail =new BodyMail( parametros , plantilla);
			_body=bodyMail.getContenido();
			return true;
		}catch(Exception exception){
			return false;
		}
	}
	public String getFullMail() {
		StringBuffer buffer= new StringBuffer();
		buffer.append(_body);
		return buffer.toString();
	}
	
}
