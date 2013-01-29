package com.bancoazteca.elite.util;

import static com.bancoazteca.elite.ejb.inversiones.InversionesGenericException.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.InversionesSL;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;

public class DispatchManager {	
	
	Logger log=Logger.getLogger(DispatchManager.class);
	
	public InversionesResponseTO dispatchManager(InversionesRequestTO request,InversionesSL inversionesSL) throws InversionesGenericException{
		InversionesResponseTO response=null;
		String type = request.getType().toLowerCase();
		String operation = request.getOperation().toLowerCase();
		String methodName = getMethodName(operation,type);							
		
		try{
			Class clase = Class.forName(inversionesSL.getClass().getCanonicalName());
			Class[] types = new Class[] {InversionesRequestTO.class };
			Method method;
			method = clase.getMethod(methodName,types);
			Object[] args = new Object[]{request};		 
			response = (InversionesResponseTO)method.invoke(inversionesSL,args);
			
		}catch(InvocationTargetException ex)
		{
			Throwable exception=ex.getCause();
			if(exception instanceof InversionesGenericException){
				InversionesGenericException genericException=(InversionesGenericException)exception;
//				if(!genericException.getInversionesExceptionTO().getLevel().equals(InversionesGenericException.LEVEL_VALIDATION))
				log.info(genericException.getInversionesExceptionTO().getLevel());	
				log.info(genericException.getInversionesExceptionTO().getMessage());
//					genericException.getInversionesExceptionTO().setMessage("Lo sentimos, por el momento no podemos atenderle. Intente mas tarde gracias.");
				throw genericException;
			}else{
				ex.printStackTrace();
				InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
				exceptionTO.setLevel(LEVEL_DISPATCHING);
				exceptionTO.setMessage("Lo sentimos por el momento no podemos atenderle, por favor intente mas tarde");
				throw new InversionesGenericException(exceptionTO);
			}
		}catch(Exception e){
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_DISPATCHING);
			exceptionTO.setMessage("Lo sentimos por el momento no podemos atenderle. Por favor intente mas tarde.");
			throw new InversionesGenericException(exceptionTO);
		}
		return response;
	}
	
	public String getMethodName(String operation,String type){
		StringBuffer methodName=new StringBuffer();
		methodName.append(operation.toLowerCase());
		methodName.append("_");
		methodName.append(type.toLowerCase());
		return methodName.toString(); 		
	}		
}