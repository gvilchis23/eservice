package com.bancoazteca.elite.ejb.inversiones.beans;

public class AperturaCuentasResponseTO extends InversionesResponseTO{
	
	private String clabe;
	private String cuentaEje;
	private String saldo;
	private String folioOperacion;
	
	public String getClabe() {
		return clabe;
	}
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	public String getCuentaEje() {
		return cuentaEje;
	}
	public void setCuentaEje(String cuentaEje) {
		this.cuentaEje = cuentaEje;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getFolioOperacion() {
		return folioOperacion;
	}
	public void setFolioOperacion(String folioOperacion) {
		this.folioOperacion = folioOperacion;
	}
}