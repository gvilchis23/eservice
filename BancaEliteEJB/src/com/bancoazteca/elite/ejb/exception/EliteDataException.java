package com.bancoazteca.elite.ejb.exception;

import com.bancoazteca.elite.beans.DispositivoHuellaTO;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class EliteDataException extends Exception {
	
	
	private static final long serialVersionUID = 2305992080384956325L;
	
	private Object errorData;
	private int level;
	private DispositivoHuellaTO dispositivoHuellaTO;
	

	public EliteDataException(Throwable e,Object errorData, int level){
		super(e);
		this.errorData = errorData;	
		this.level = level;
	}
	
	public EliteDataException(String message,Throwable e,Object errorData, int level){
		super(message,e);
		this.errorData = errorData;
		this.level = level;
	}
	
	public EliteDataException(String message,Object errorData, int level){
		super(message);
		this.errorData = errorData;
		this.level = level;
	}
	
	public EliteDataException(String message,Object errorData,DispositivoHuellaTO dispositivoHuellaTO, int level){
		super(message);
		this.errorData = errorData;
		this.dispositivoHuellaTO = dispositivoHuellaTO;
		this.level = level;
	}
	
	
	public Object getErrorData() {
		return errorData;
	}

	public int getLevel() {
		return level;
	}

	/**
	 * @return the dispositivoHuellaTO
	 */
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}

	/**
	 * @param dispositivoHuellaTO the dispositivoHuellaTO to set
	 */
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}	

}
