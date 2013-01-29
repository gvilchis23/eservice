package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;

public class B011_BusquedaCentroContableResponse {
	
	AlnovaResponse alnovaResponse;
	
	public B011_BusquedaCentroContableResponse(AlnovaResponse alnovaResponse) {
		this.alnovaResponse=alnovaResponse;
	}
	
	public String getCentroContable() {
		return alnovaResponse.getAttribute("SUCURSA");
	}
	
	public String getCuenta() {
		return alnovaResponse.getAttribute("CUENTA");
	}
	
}
