package com.bancoazteca.eservice.command.base.beans;

public class BeneficiarioTO {
	
	private String nombre_beneficiario;
	private String primer_apellido;
	private String segundo_apellido;
	private String porcentaje;
	private String edad;
	private String parentesco;
	private String segundo_nombre_beneficiario;
	
	public String getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}
	public void setNombre_beneficiario(String nombre) {
		this.nombre_beneficiario = nombre;
	}
	@Override
	public String toString() {
		return porcentaje+primer_apellido+segundo_apellido+nombre_beneficiario;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	public String getSegundo_nombre_beneficiario() {
		return segundo_nombre_beneficiario;
	}
	public void setSegundo_nombre_beneficiario(String segundo_nombre_beneficiario) {
		this.segundo_nombre_beneficiario = segundo_nombre_beneficiario;
	}
	public String getPrimer_apellido() {
		return primer_apellido;
	}
	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}
	public String getSegundo_apellido() {
		return segundo_apellido;
	}
	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}	
}
