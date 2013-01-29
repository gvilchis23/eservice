package com.bancoazteca.eservice.command.response;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.beans.StatusTO;
import com.bancoazteca.eservice.command.base.session.Session;


public class Response {	
	
	private Collection <Object> dataService;			
	private StatusTO statusTO;
		
	public Response(){
		this.dataService = new ArrayList<Object>();
		this.statusTO = new StatusTO();
	}
		
	public void addAttribute(Object arg){	
		dataService.add(arg);
	}	
	
	public Collection<?> getDataService(){
		return dataService;
	}
	
	public void setStatus(int code,String message,String internalMessage){		
		statusTO.setCodigo_operacion(String.valueOf(code));
		statusTO.setDescripcion_codigo(message);
		statusTO.setError_sistema(internalMessage);		
	}
	
	public void setStatus(int code,Throwable e){		
		statusTO.setCodigo_operacion(String.valueOf(code));
		statusTO.setDescripcion_codigo("Exception "+e);
		statusTO.setError_sistema(stackTraceToString(e));		
	}
	
	public void setStatus(StatusTO statusTO) {
		this.statusTO = statusTO;
	}
	
	public StatusTO getStatus(){
		return statusTO;
	}
			
	public void setIdSession(Session session){
		this.statusTO.setIdsesion(Cipher.valueOf(session.getIdSession()));
	}
	
	private String stackTraceToString(Throwable e){
		StackTraceElement[] stackTraceElements = e.getStackTrace();
    	StringBuffer buffer = new StringBuffer();
    	for (int j=0;j<stackTraceElements.length;j++){
    		StackTraceElement stackTraceElement = stackTraceElements[j];
    		buffer.append(stackTraceElement.toString()+";");
    	} 
    	return buffer.toString();
	}
	
}
