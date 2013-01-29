package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Tarjetas_FrecuentesTO {
	private Collection<Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentes;

	public Collection<Tarjeta_FrecuenteTO> getColeccion_tarjetas_frecuentes() {
		return coleccion_tarjetas_frecuentes;
	}

	public void setColeccion_tarjetas_frecuentes(
			Collection<Tarjeta_FrecuenteTO> coleccion_tarjetas_frecuentes) {
		this.coleccion_tarjetas_frecuentes = coleccion_tarjetas_frecuentes;
	}
	
}
