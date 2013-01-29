package com.bancoazteca.eservice.command.base.beans;

public class Cuenta_CargoTO {
	private String numero_cuenta;
	private String producto;
	private String saldo_disponible;
	
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getSaldo_disponible() {
		return saldo_disponible;
	}
	public void setSaldo_disponible(String saldo_disponible) {
		this.saldo_disponible = saldo_disponible;
	}
	

}
