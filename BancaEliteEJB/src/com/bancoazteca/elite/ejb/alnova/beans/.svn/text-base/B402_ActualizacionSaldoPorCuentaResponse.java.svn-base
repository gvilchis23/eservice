package com.bancoazteca.elite.ejb.alnova.beans;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;

public class B402_ActualizacionSaldoPorCuentaResponse {

	AlnovaResponse response;
	
	public B402_ActualizacionSaldoPorCuentaResponse(AlnovaResponse response) {
		this.response=response;
	}
	

	public String getSaldoDisponible() {
		return response.getAttribute("AVABAL");
	}
	
	public String getSaldoRetenido() {
		return response.getAttribute("RETBAL");
	}
		
}
