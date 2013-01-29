package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class DatosEliteTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5680865613912090156L;
	

	
	private String errorCode;
	private String errorDescription;
    private String jspForward;
    private Object errorObject;
    
	public Object getErrorObject() {
		return errorObject;
	}
	public void setErrorObject(Object errorObject) {
		this.errorObject = errorObject;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getJspForward() {
		return jspForward;
	}
	public void setJspForward(String jspForward) {
		this.jspForward = jspForward;
	}
	
	

}
