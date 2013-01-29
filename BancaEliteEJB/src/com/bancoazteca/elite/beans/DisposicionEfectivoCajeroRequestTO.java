package com.bancoazteca.elite.beans;

public class DisposicionEfectivoCajeroRequestTO {

	private int indiceTarjeta;
	private double monto;
	private String tokenCode;
	private String clave;
	private String userName;
	private String optionDispositive;
	
	
	
	public String getOptionDispositive() {
		return optionDispositive;
	}
	public void setOptionDispositive(String optionDispositive) {
		this.optionDispositive = optionDispositive;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getIndiceTarjeta() {
		return indiceTarjeta;
	}
	public void setIndiceTarjeta(int indiceTarjeta) {
		this.indiceTarjeta = indiceTarjeta;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
