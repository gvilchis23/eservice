package com.bancoazteca.elite.ejb.alnova.beans;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaRequest;

public class B402_ActualizacionSaldoPorCuentaRequest extends AlnovaRequest {

	private static final long serialVersionUID = -3370048273818974879L;
	private static final String[] parameters = {"OPTION","CAC"};

	public B402_ActualizacionSaldoPorCuentaRequest() {
		super("B402", parameters);
	}

	public String getOpcion() {
		return getAttribute("OPTION");
	}
	public void setOpcion(String value) {
		addParameter("OPTION",value);
	}
	
	public String getNumeroCuenta() {
		return getAttribute("CAC");
	}
	public void setNumeroCuenta(String value) {
		addParameter("CAC",value);
	}
	
}
