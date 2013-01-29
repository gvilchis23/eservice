package com.bancoazteca.elite.ejb.alnova.beans;

public class TC99ConsultaCodigoPostalRequestTO extends EjbAlnovaRequestTO {
	
	private static final long serialVersionUID = 1L;
	
	private static String[] keys={"USER","LLAVTAB"};

	public TC99ConsultaCodigoPostalRequestTO() {
		super("TC99", keys);
	}
	
	public void setUsuario(String user){
		super.addParameter("USER",user);
	}
	
	public void setCodigoPostal(String cp){
		super.addParameter("LLAVTAB",cp);
	}
	
}