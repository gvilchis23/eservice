package com.bancoazteca.elite.ejb.inversiones;

import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;


public class InversionesGenericException extends Exception{
	
	
	private static final long serialVersionUID = 1L;
	
	public static transient final String LEVEL_DISPATCHING="DISPATCHING";
	public static transient final String LEVEL_WEB_SERVICES="WEB_SERVICES";
	public static transient final String LEVEL_VALIDATION="VALIDATION";
	public static transient final String LEVEL_BASE_DATOS="BASE_DATOS";
	public static transient final String LEVEL_WEB_SERVICE_CONNECTION="WEB_SERVICE_CONNECTION";
	
	public static final String LEVEL_IO_EXCEPTION = "IO_EXCEPTION";
	
	private InversionesExceptionTO inversionesExceptionTO;
	
	public InversionesGenericException(){}	
	
	public InversionesGenericException(InversionesExceptionTO inversionesExceptionTO){
		this.inversionesExceptionTO=inversionesExceptionTO;
	}

	public InversionesExceptionTO getInversionesExceptionTO() {
		return inversionesExceptionTO;
	}
}
