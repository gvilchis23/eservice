package com.bancoazteca.elite.ejb.inversiones.beans;

import java.util.Collection;

public class PlazoTasaCacheTO {
	private String fecha_operacion;
	private String hora_operacion;
	private Collection<PlazoTasaTO> plazosTasas;
	
	public Collection<PlazoTasaTO> getPlazosTasas() {
		return plazosTasas;
	}
	public void setPlazosTasas(Collection<PlazoTasaTO> plazosTasas) {
		this.plazosTasas = plazosTasas;
	}
	public String getFecha_operacion() {
		return fecha_operacion;
	}
	public void setFecha_operacion(String fecha_operacion) {
		this.fecha_operacion = fecha_operacion;
	}
	public String getHora_operacion() {
		return hora_operacion;
	}
	public void setHora_operacion(String hora_operacion) {
		this.hora_operacion = hora_operacion;
	}
}
