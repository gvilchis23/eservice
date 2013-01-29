package com.bancoazteca.elite.ejb.alnova.beans;

public class QG83ConsultaTerminalResponseTO extends EjbAlnovaResponseTO {
	
   private static final long serialVersionUID = 1L;
	
	public String getNetname(){
		return alnovaResponse.getAttribute("NETNAME");
	}

	public String getSucurs(){
		return alnovaResponse.getAttribute("SUCURS");
	}
	


}
