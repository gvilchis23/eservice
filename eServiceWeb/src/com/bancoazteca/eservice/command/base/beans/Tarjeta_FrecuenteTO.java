package com.bancoazteca.eservice.command.base.beans;

public class Tarjeta_FrecuenteTO {
	private String alias_beneficiario;
	private String numero_tarjeta ;
	private String nombre_banco;
	private String tipo_tarjeta;
	private String Estado;
	
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getAlias_beneficiario() {
		return alias_beneficiario;
	}
	public void setAlias_beneficiario(String alias_beneficiario) {
		this.alias_beneficiario = alias_beneficiario;
	}
	public String getNumero_tarjeta() {
		return numero_tarjeta;
	}
	public void setNumero_tarjeta(String numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}
	public String getNombre_banco() {
		return nombre_banco;
	}
	public void setNombre_banco(String nombre_banco) {
		this.nombre_banco = nombre_banco;
	}
	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}
	public void setTipo_tarjeta(String tipo_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
	}
	
	
}
