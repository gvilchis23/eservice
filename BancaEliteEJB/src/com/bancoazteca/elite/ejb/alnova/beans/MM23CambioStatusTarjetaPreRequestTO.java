package com.bancoazteca.elite.ejb.alnova.beans;

public class MM23CambioStatusTarjetaPreRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] keys={"OPCION","NUMCARD"};

	public MM23CambioStatusTarjetaPreRequestTO() {
		super("MM23", keys);
	}
	
	public void setOpcion(String value){
		super.addParameter("OPCION",value);
	}
	
	public void setNumeroTarjeta(String value){
		super.addParameter("NUMCARD",value);
	}
	
}