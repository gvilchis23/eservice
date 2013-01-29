package com.bancoazteca.eservice.command.base.beans;

public class Activacion_Firma_AztecaTO {

	private String correo_electronico;
	private String numero_telefono;
	private String numero_celular;
	private String compania_celular;
	private String validar_nip;
	
	public String getValidar_nip() {
		return validar_nip;
	}
	public void setValidar_nip(String validar_nip) {
		this.validar_nip = validar_nip;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getNumero_telefono() {
		return numero_telefono;
	}
	public void setNumero_telefono(String numero_telefono) {
		this.numero_telefono = numero_telefono;
	}
	public String getNumero_celular() {
		return numero_celular;
	}
	public void setNumero_celular(String numero_celular) {
		this.numero_celular = numero_celular;
	}
	public String getCompania_celular() {
		return compania_celular;
	}
	public void setCompania_celular(String compania_celular) {
		this.compania_celular = compania_celular;
	}
	
}