package com.bancoazteca.elite.ejb.alnova.beans;

public class MP00ConsultaDatosTarjetasRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] keys={"OPTION","CRDNUM"};

	public MP00ConsultaDatosTarjetasRequestTO() {
		super("MP00", keys);
	}
	
	public void setOpcion(String value){
		super.addParameter("OPTION",value);
	}
	
	public void setNumeroTarjeta(String value){
		super.addParameter("CRDNUM",value);
	}
	
}