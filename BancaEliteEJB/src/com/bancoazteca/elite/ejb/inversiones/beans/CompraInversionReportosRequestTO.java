package com.bancoazteca.elite.ejb.inversiones.beans;

public class CompraInversionReportosRequestTO extends InversionesRequestTO{
	
	private String cuentaCargo;
	private String monto;
	private String plazo;
	private String tasaBruta;
	
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getTasaBruta() {
		return tasaBruta;
	}
	public void setTasaBruta(String tasaBruta) {
		this.tasaBruta = tasaBruta;
	}
	
}
