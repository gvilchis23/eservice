package com.bancoazteca.elite.ejb.inversiones.beans;

import java.util.Collection;

public class FechaAperturaReportosResponseTO extends InversionesResponseTO {
	
	private String fechaApertura;
	private Collection<String> anios;
	private Collection<String> meses;

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Collection<String> getAnios() {
		return anios;
	}

	public void setAnios(Collection<String> anios) {
		this.anios = anios;
	}

	public Collection<String> getMeses() {
		return meses;
	}

	public void setMeses(Collection<String> meses) {
		this.meses = meses;
	}
	
}
