package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Movimiento_inversionTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numero_movimiento;
	private String fecha;
	private String balance;
	private String concepto;
	private String importe;
	private String tasa_bruta_anual;
	
	public String getTasa_bruta_anual() {
		return tasa_bruta_anual;
	}
	public void setTasa_bruta_anual(String tasa_bruta_anual) {
		this.tasa_bruta_anual = tasa_bruta_anual;
	}
	public String getNumero_movimiento() {
		return numero_movimiento;
	}
	public void setNumero_movimiento(String numero) {
		this.numero_movimiento = numero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	@Override
	public boolean equals(Object obj) {
		Movimiento_inversionTO temp=(Movimiento_inversionTO)obj;
		
		Boolean bandera=false;
		bandera=numero_movimiento.equals(temp.getNumero_movimiento());
		bandera=fecha.equals(temp.getFecha()) && bandera;
		bandera=balance.equals(temp.getBalance())&& bandera;
		bandera=concepto.equals(temp.getConcepto()) && bandera;
		bandera=importe.equals(temp.getImporte()) && bandera;
		
		if(bandera){
			return true;
		}
		return false;
	}	
}