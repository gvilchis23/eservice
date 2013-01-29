package com.bancoazteca.elite.ejb.alnova.utils;

import java.util.Collection;
import java.util.Map;

public class AlnovaExceptionTO {

	private String message;
	private String descripcionError;
	
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	private String level;
	private Map<String,String> errorMap;
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Map<String, String> getErrorMap() {
		return errorMap;
	}
	public void setErrorMap(Map<String, String> mapaErrores) {
		this.errorMap = mapaErrores;
	}
	
	public Collection<String> getErrorKeys(){
		if(errorMap!=null){
			return errorMap.keySet();
		}
		return null;
	}
	
	public Collection<String> getErrorValues(){
		if(errorMap!=null){
			return errorMap.values();
		}
		return null;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
	
}
