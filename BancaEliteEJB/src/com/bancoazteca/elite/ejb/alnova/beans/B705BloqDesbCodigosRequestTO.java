package com.bancoazteca.elite.ejb.alnova.beans;

public class B705BloqDesbCodigosRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] keys={"CAC","COD","ACTION","OBSERV","PAP"};

	public B705BloqDesbCodigosRequestTO() {
		super("B705", keys);
	}
	
	public void setNumeroCuenta(String value){
		super.addParameter("CAC",value);
	}
	
	public void setCodigoOperacion(String value){
		super.addParameter("COD",value);
	}
	
	public void setBloqueoDesbloqueo(String value){
		super.addParameter("ACTION",value);
	}
	
	public void setObservaciones(String value){
		super.addParameter("OBSERV",value);
	}
	
	public void setImpresion(String value){
		super.addParameter("PAP",value);
	}
	
}