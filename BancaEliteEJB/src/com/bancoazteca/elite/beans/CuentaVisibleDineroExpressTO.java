package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class CuentaVisibleDineroExpressTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String value;
	private String label;
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

}
