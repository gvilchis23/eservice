package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;

public class Conciliacion_ResponseTO extends InversionesResponseTO implements Serializable {
	private static final long serialVersionUID = -6976197255991889351L;
	Collection<Conciliacion_Concentrado_ResponseTO> coleccion_conciliacion;
	public Collection<Conciliacion_Concentrado_ResponseTO> getColeccion_conciliacion() {
		return coleccion_conciliacion;
	}
	public void setColeccion_conciliacion(
			Collection<Conciliacion_Concentrado_ResponseTO> coleccion_conciliacion) {
		this.coleccion_conciliacion = coleccion_conciliacion;
	}		
}