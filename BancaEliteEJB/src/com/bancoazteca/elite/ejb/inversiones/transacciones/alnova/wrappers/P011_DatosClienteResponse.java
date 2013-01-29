package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;

public class P011_DatosClienteResponse {
	
	AlnovaResponse alnovaResponse;
	

	public P011_DatosClienteResponse(AlnovaResponse alnovaResponse) {
		this.alnovaResponse=alnovaResponse;
	}
	
	public String getNumeroDireccion(){
		return this.alnovaResponse.getAttribute("ADRNUM");
	}


	public String getCalle(){
		return this.alnovaResponse.getAttribute("ADDRESS");
	}


	public String getActividadEconomica(){
		return this.alnovaResponse.getAttribute("NOC");
	}


	public String getApellidoMaterno(){
		return this.alnovaResponse.getAttribute("SFNM");
	}


	public String getApellidoPaterno(){
		return this.alnovaResponse.getAttribute("SURNM");
	}


	public String getNombre(){
		return this.alnovaResponse.getAttribute("CUSNM");
	}


	public String getCodigoPostal(){
		return this.alnovaResponse.getAttribute("ZIPCOD");
	}


	public String getCiudad(){
		return this.alnovaResponse.getAttribute("CITY");
	}


	public String getColonia(){
		return this.alnovaResponse.getAttribute("DOOR");
	}


	public String getCorreoElectronico(){
		return this.alnovaResponse.getAttribute("EMAIL");
	}


	public String getCURP(){
		return this.alnovaResponse.getAttribute("CURPTIT");
	}


	public String getDelegacion(){
		return this.alnovaResponse.getAttribute("DESADR");
	}


	public String getFechaRegistro(){
		return this.alnovaResponse.getAttribute("HEADDAT");
	}


	public String getRFC(){
		return this.alnovaResponse.getAttribute("RFCTIT");
	}
	
}