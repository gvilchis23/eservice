package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Inversion_directoTO implements Serializable{

	private static final long serialVersionUID = 3747470685556363297L;

	private String numero_titulos;
	private String precio;
	private String intereses;
	private String tasa;
	private String total;
	
	public String getNumero_titulos() {
		return numero_titulos;
	}
	public void setNumero_titulos(String numero_titulos) {
		this.numero_titulos = numero_titulos;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getIntereses() {
		return intereses;
	}
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
}
