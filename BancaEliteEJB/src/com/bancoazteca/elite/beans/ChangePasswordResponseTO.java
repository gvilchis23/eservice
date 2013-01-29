package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ChangePasswordResponseTO implements Serializable {
	
	
	private static final long serialVersionUID = -2856139922934165817L;
	
	private  String messaje;
	
	private DispositivoHuellaTO dispositivoHuellaTO;

	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}

	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}

	public String getMessaje() {
		return messaje;
	}

	public void setMessaje(String messaje) {
		this.messaje = messaje;
	}
	
	

}
