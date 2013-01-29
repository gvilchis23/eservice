package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaRequest;

public class B011_BusquedaCentroContableRequest extends AlnovaRequest {

	private static final String[] parameters = {"SUCURSA","CUENTA"};
	
	public B011_BusquedaCentroContableRequest() {
		super("B011",parameters);
	}
	
	public void setSucursal(String value){
		addParameter("SUCURSA",value);
	}
	
	public void setCuenta(String value){
		addParameter("CUENTA", value);
	}
	
	public String getSucursal(){	
		return getAttribute("SUCURSA");
	}
	
	public String getCuenta(){
		return getAttribute("CUENTA");
	}
	
	
}
