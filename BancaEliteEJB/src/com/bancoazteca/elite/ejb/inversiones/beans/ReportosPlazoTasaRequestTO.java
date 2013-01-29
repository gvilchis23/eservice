package com.bancoazteca.elite.ejb.inversiones.beans;

public class ReportosPlazoTasaRequestTO extends InversionesRequestTO{
	private String fechaRegistro;

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
