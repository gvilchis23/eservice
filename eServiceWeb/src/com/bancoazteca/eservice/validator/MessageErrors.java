package com.bancoazteca.eservice.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bancoazteca.eservice.command.base.beans.MensajeTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;

public class MessageErrors {
	Map <String, String> errors;	
	
	public MessageErrors(){
		errors = new HashMap<String, String>();
	}

	public void add(String key, String value){
		String messageError = MessageResources.getPropertie(value);		
		errors.put(key, messageError);
		
	}
	public void add(String key, String value, String param0){
		String messageError = MessageResources.getPropertie(value);
		messageError = messageError.replace("{0}", param0);
		errors.put(key, messageError);
		
	}
	public void add(String key, String value, String param0, String param1){
		String messageError = MessageResources.getPropertie(value);
		messageError = messageError.replace("{0}", param0);
		messageError = messageError.replace("{1}", param1);
		errors.put(key, messageError);
		
	}
	public void add(String key, String value, String param0, String param1, String param2){
		String messageError = MessageResources.getPropertie(value);
		messageError = messageError.replace("{0}", param0);
		messageError = messageError.replace("{1}", param1);
		messageError = messageError.replace("{2}", param2);
		errors.put(key, messageError);
		
	}
	
	public void add(String key, String value, String []params){
		String messageError = MessageResources.getPropertie(value);
		StringBuilder cadena = new StringBuilder();
		for(int i=0; i<params.length; i++){
			cadena.append(params[i]);
			if(i+1 != params.length){
				cadena.append(" o ");
			}
		}
		cadena.append(" dígitos");
		messageError += cadena.toString();
		errors.put(key, messageError);		
	}
	
	public Map<String, String> getErrors(){
		return this.errors;
	}
	
	public boolean isErrors(){
		if(errors!=null && errors.size()>0){
			return true;
		}else{
			return false;
		}		
	}
	
	public MensajesTO getMessages(){
		MensajesTO mensajesTO = new MensajesTO();		
		if (isErrors()){
			Collection<MensajeTO> col = new ArrayList<MensajeTO>();
			for (String key : this.errors.keySet()){
				String value = this.errors.get(key);
				MensajeTO error = new MensajeTO();
				error.setPropiedad(key);
				error.setDescripcion(value);
				col.add(error);
			}
			mensajesTO.setColeccion_mensajes(col);
		}		
		return mensajesTO;
	}
}
