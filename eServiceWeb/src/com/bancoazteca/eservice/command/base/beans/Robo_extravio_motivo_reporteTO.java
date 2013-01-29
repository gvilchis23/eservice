package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Robo_extravio_motivo_reporteTO implements Serializable{

	private static final long serialVersionUID = -3014022276359835051L;

	private Collection<String> coleccion_motivos_reporte;

	public Collection<String> getColeccion_motivos_reporte() {
		return coleccion_motivos_reporte;
	}

	public void setColeccion_motivos_reporte(
			Collection<String> coleccion_motivo_reporte) {
		this.coleccion_motivos_reporte = coleccion_motivo_reporte;
	}

	
}
