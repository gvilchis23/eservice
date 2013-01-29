package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ConsultaMonitoreoRequestTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String consultaFechaInicio;
	private String consultaFechaFin;
	private boolean consultaPorFecha;
	
	public String getConsultaFechaInicio() {
		return consultaFechaInicio;
	}
	public void setConsultaFechaInicio(String consultaFechaInicio) {
		this.consultaFechaInicio = consultaFechaInicio;
	}
	public String getConsultaFechaFin() {
		return consultaFechaFin;
	}
	public void setConsultaFechaFin(String consultaFechaFin) {
		this.consultaFechaFin = consultaFechaFin;
	}
	public boolean isConsultaPorFecha() {
		return consultaPorFecha;
	}
	public void setConsultaPorFecha(boolean consultaPorFecha) {
		this.consultaPorFecha = consultaPorFecha;
	}
		
}
