package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers;

import java.util.Map;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;

public class ReportosFechaAperturaResponseTO extends ReportosResponse {

	public ReportosFechaAperturaResponseTO(Map<String, String> parametros) {
		super(parametros);
	}
	
	public String getFechaInicioInversion() {
		return getAttribute("FOPER");
	}
	
	
	

}
