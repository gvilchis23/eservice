package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class LlaveSeguridadResponseTO implements Serializable {
	
	private String llave;
	private String logitud;
	
	public String getLlave() {
		return llave;
	}
	public void setLlave(String llave) {
		this.llave = llave;
	}
	public String getLogitud() {
		return logitud;
	}
	public void setLogitud(String logitud) {
		this.logitud = logitud;
	}
}
