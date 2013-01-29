
package com.bancoazteca.elite.ejb.alnova.beans;

public class B011BusquedaCentroContableRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = -7578682399357653994L;
	private static final String[] parameters = {"SUCURSA","CUENTA"};
	
	public B011BusquedaCentroContableRequestTO() {
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
