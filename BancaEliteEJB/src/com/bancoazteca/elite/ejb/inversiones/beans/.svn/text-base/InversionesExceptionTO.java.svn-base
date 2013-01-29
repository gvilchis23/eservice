package com.bancoazteca.elite.ejb.inversiones.beans;

import java.util.Collection;
import java.util.Map;

public class InversionesExceptionTO extends InversionesResponseTO {
	
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
	
}
