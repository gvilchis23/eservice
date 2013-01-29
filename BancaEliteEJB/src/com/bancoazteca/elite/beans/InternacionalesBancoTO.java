package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class InternacionalesBancoTO implements Serializable{
	

	private static final long serialVersionUID = -7303951769998490001L;
	
	private String tipoClave;
	private String claveBanco;
	private String nombreBanco;
	
	
	public String getTipoClave() {
		return tipoClave;
	}
	public void setTipoClave(String tipoClave) {
		this.tipoClave = tipoClave;
	}
	public String getClaveBanco() {
		return claveBanco;
	}
	public void setClaveBanco(String claveBanco) {
		this.claveBanco = claveBanco;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	
	
	

}
