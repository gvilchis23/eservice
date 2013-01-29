package com.bancoazteca.eservice.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.bancoazteca.elite.commons.xml.CipherArgumentException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.eservice.filters.AlnovaRedMovilFilterException;
import com.bancoazteca.eservice.filters.CuentaFilterException;

public class CommandErrorHandler extends Exception{
	
	private static final long serialVersionUID = 7954939867380431L;	
	private int exceptionCode;
	
	public static final Map<Integer,String> errorMap;	
	
	static{
		errorMap = new HashMap<Integer,String>();								
		errorMap.put(Errors.EXCEPTION_COMMAND_INVALID, "El servicio que intenta invocar no existe, por favor verifique su petición.");		
		errorMap.put(Errors.EXCEPTION_USER_INVALID, "Estimado usuario, el nombre de usuario y/o password son incorrectos.");
		errorMap.put(Errors.EXCEPTION_USER_BLOCKED, "Estimado usuario, ha superado el máximo número de intentos para ingresar al sistema.");
		errorMap.put(Errors.EXCEPTION_USER_EXPIRED, "Estimado Cliente, por su seguridad la sesión ha expirado, le solicitamos volver a ingresar su usuario y contraseña. Muchas gracias.");
		errorMap.put(Errors.EXCEPTION_CIPHER, "");
		errorMap.put(Errors.EXCEPTION_CODE, "Ha ocurrido un problema al invocar el servicio, por favor intente mas tarde.");
		errorMap.put(Errors.EXCEPTION_NEED_ANOTHER_COMMAND, "Estimado usuario, su petición no pudo ser procesada, es posible que se haya omitido la invocación de un paso anterior en su servicio.");
		errorMap.put(Errors.EXCEPTION_USER_NO_ACCOUNT_RMA, "");
		errorMap.put(Errors.EXCEPTION_USER_NO_PAYMENT_RMA, "");
	}

	public CommandErrorHandler(){
		super();
	}
	
	public CommandErrorHandler(String message){
		super(message);
	}
	
	public CommandErrorHandler(Throwable exception){						
		if (exception instanceof NoSuchBeanDefinitionException){	
			setExceptionCode(Errors.EXCEPTION_COMMAND_INVALID);					
		}
		else if (exception instanceof LoginException){
			setExceptionCode(Errors.EXCEPTION_USER_INVALID);			
		}
		else if (exception instanceof UsuarioException){
			setExceptionCode(Errors.EXCEPTION_USER_BLOCKED);			
		}
		else if (exception instanceof SessionExpiredException){
			setExceptionCode(Errors.EXCEPTION_USER_EXPIRED);			
		}
		else if(exception instanceof CuentaFilterException){
			setExceptionCode(Errors.EXCEPTION_USER_NO_ACCOUNT_RMA);
		}
		else if(exception instanceof AlnovaRedMovilFilterException){
			setExceptionCode(Errors.EXCEPTION_USER_NO_PAYMENT_RMA);
//			setExceptionCode( exception.getMessage() );
		}
		else if (exception instanceof CipherArgumentException){			
			setExceptionCode(Errors.EXCEPTION_CIPHER);		
		}
		else if(exception instanceof NullPointerException){
			setExceptionCode(Errors.EXCEPTION_NEED_ANOTHER_COMMAND);		
		}else{
			setExceptionCode(Errors.EXCEPTION_CODE);
		}		
		exception.printStackTrace();
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}
}
