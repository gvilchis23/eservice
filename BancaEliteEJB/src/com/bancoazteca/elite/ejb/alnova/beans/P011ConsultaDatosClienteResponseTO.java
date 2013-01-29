package com.bancoazteca.elite.ejb.alnova.beans;


public class P011ConsultaDatosClienteResponseTO extends EjbAlnovaResponseTO{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String getNumeroDireccion(){
		return super.alnovaResponse.getAttribute("ADRNUM");
	}
	
	
	public String getCalle(){
		return super.alnovaResponse.getAttribute("ADDRESS");
	}

	public String getActividadEconomica(){
		return super.alnovaResponse.getAttribute("NOC");
	}


	public String getApellidoMaterno(){
		return super.alnovaResponse.getAttribute("SFNM");
	}


	public String getApellidoPaterno(){
		return super.alnovaResponse.getAttribute("SURNM");
	}


	public String getNombre(){
		return super.alnovaResponse.getAttribute("CUSNM");
	}


	public String getCodigoPostal(){
		return super.alnovaResponse.getAttribute("ZIPCOD");
	}


	public String getCiudad(){
		return super.alnovaResponse.getAttribute("CITY");
	}


	public String getColonia(){
		return super.alnovaResponse.getAttribute("DOOR");
	}


	public String getCorreoElectronico(){
		return super.alnovaResponse.getAttribute("EMAIL");
	}


	public String getCURP(){
		return super.alnovaResponse.getAttribute("CURPTIT");
	}


	public String getDelegacion(){
		return super.alnovaResponse.getAttribute("DESADR");
	}


	public String getFechaRegistro(){
		return super.alnovaResponse.getAttribute("HEADDAT");
	}


	public String getRFC(){
		return super.alnovaResponse.getAttribute("RFCTIT");
	}
}