package com.bancoazteca.elite.ejb.alnova.beans;

public class B023CuentaConRedMovilAztecaWEBRequestTO extends EjbAlnovaRequestTO{
	
	private static final long serialVersionUID = 1L;
	
	private static String[] keys={"CUENTA","NUMCTE","OPCION"};

	public B023CuentaConRedMovilAztecaWEBRequestTO() {
		super("B023", keys);
		setIdtransaccion("B023");
		setDescripcionTransaccion("CuentaConRedMovilAztecaWEB");
	}
	
	public void setCuentaAlnova(String user){
		super.addParameter("CUENTA",user);
	}
	
	public void setNumeroClienteAlnova(String user){
		super.addParameter("NUMCTE",user);
	}
	public void setOpcion(String user){
		super.addParameter("OPCION",user);
	}
	
	public String getCuentaAlnova(){
		return (String) super.getAttribute("CUENTA");
	}
	
	public String getNumeroClienteAlnova(){
		return (String) super.getAttribute("NUMCTE");
	}
	
	public String getOpcion(){
		return (String) super.getAttribute("OPCION");
	}
}
