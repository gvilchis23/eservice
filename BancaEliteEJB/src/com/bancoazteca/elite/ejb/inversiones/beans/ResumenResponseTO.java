package com.bancoazteca.elite.ejb.inversiones.beans;
import java.util.Collection;

public class ResumenResponseTO  extends InversionesResponseTO{
	
	Collection<ResumenOperacionesResponseTO> resumenOperaciones;

	public Collection<ResumenOperacionesResponseTO> getResumenOperaciones() {
		return resumenOperaciones;
	}

	public void setResumenOperaciones(
			Collection<ResumenOperacionesResponseTO> resumenOperaciones) {
		this.resumenOperaciones = resumenOperaciones;
	}

}
