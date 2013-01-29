package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Biblioteca_recibosTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha_inicio;
	private String fecha_final;
	
	
	private Collection<ReciboTO> colleccion_recibos;

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public Collection<ReciboTO> getColleccion_recibos() {
		return colleccion_recibos;
	}

	public void setColleccion_recibos(Collection<ReciboTO> colleccion_recibos) {
		this.colleccion_recibos = colleccion_recibos;
	}
}
