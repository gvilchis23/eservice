package com.bancoazteca.elite.ejb.alnova.beans;

public class P011ConsultaDatosClienteRequestTO extends EjbAlnovaRequestTO {

	private static final long serialVersionUID = 1L;
	private static final String[] keys={"OPECOD","CUSNUM"};
	
	
	public P011ConsultaDatosClienteRequestTO() {
		super("P011",keys);
		addParameter("OPECOD","C");
	}
	
	public void setNumeroCliente(String value){
		addParameter("CUSNUM",value);
	}
	
	
}