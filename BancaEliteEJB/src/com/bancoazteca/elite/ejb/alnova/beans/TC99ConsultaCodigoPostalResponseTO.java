package com.bancoazteca.elite.ejb.alnova.beans;

public class TC99ConsultaCodigoPostalResponseTO extends EjbAlnovaResponseTO {

	private static final long serialVersionUID = 1L;
	
	public String getColonia(){
		return alnovaResponse.getAttribute("COLONIA");
	}

	public String getMunicipio(){
		return alnovaResponse.getAttribute("MUNICI");
	}
	
	public String getEstado(){
		return alnovaResponse.getAttribute("ESTADO");
	}
}