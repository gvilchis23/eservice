package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class DispositivoHuellaTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8825947144694968984L;
	
	//Fields
	private String llavePublica;
	private String longitudHuella;
	private HuellaDigitalTO huellaMuerta;
	
	/**
	 * @return the llavePublica
	 */
	public String getLlavePublica() {
		return llavePublica;
	}
	/**
	 * @param llavePublica the llavePublica to set
	 */
	public void setLlavePublica(String llavePublica) {
		this.llavePublica = llavePublica;
	}
	/**
	 * @return the longitudHuella
	 */
	public String getLongitudHuella() {
		return longitudHuella;
	}
	/**
	 * @param longitudHuella the longitudHuella to set
	 */
	public void setLongitudHuella(String longitudHuella) {
		this.longitudHuella = longitudHuella;
	}
	/**
	 * @return the huellaMuerta
	 */
	public HuellaDigitalTO getHuellaMuerta() {
		return huellaMuerta;
	}
	/**
	 * @param huellaMuerta the huellaMuerta to set
	 */
	public void setHuellaMuerta(HuellaDigitalTO huellaMuerta) {
		this.huellaMuerta = huellaMuerta;
	}
	

}
