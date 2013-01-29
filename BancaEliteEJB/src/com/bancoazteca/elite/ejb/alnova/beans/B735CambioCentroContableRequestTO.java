package com.bancoazteca.elite.ejb.alnova.beans;

public class B735CambioCentroContableRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] keys={"CAC","ENTCEN"};

	public B735CambioCentroContableRequestTO() {
		super("B735", keys);
	}
	
	public void setNumeroCuenta(String value){
		super.addParameter("CAC",value);
	}
	
	public void setEntidadNumeroCentro(String value){
		super.addParameter("ENTCEN",value);
	}
	
}