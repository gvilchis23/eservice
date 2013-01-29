package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;



public class B065_AltaDatosComplementariosResponse{
	
	AlnovaResponse response;
	
	public B065_AltaDatosComplementariosResponse(AlnovaResponse response) {
		this.response=response;
	}

	public String getMensaje(){
		return response.getMessage();
	}
	
	public String getCodigoMensaje(){
		return response.getMessageCode();
	}
	
	public String getMensajeReal(){
		return response.getMessageReal();
	}
	
}