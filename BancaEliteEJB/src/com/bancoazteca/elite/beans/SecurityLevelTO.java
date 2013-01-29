package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class SecurityLevelTO implements Serializable {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 3593538268595799047L;
	
	//Constants
	public static final int TRANFERS_LEVEL_LOW = 0;
	public static final int TRANFERS_LEVEL_HIGH = 1;
	
	public static final int SECURITY_LEVEL_LOW = 5;
	public static final int SECURITY_LEVEL_MEDIUM = 6;
	public static final int SECURITY_LEVEL_HIGH = 4;
	//Fields
	private HuellaDigitalTO huellaMuerta;
	private String securityLevel;
	private String nivelFrecuentes;
	private String nivelTransferencias;
	private String tokenstatus;
	private String nivelMovimientos;
	//TODO Verificar si estos campos se usan... se dejan temporalmente... JFAV
	private String llpub;
	private String lop;
	
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
	/**
	 * @return the securityLevel
	 */
	public String getSecurityLevel() {
		return securityLevel;
	}
	/**
	 * @param securityLevel the securityLevel to set
	 */
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	/**
	 * @return the nivelFrecuentes
	 */
	public String getNivelFrecuentes() {
		return nivelFrecuentes;
	}
	/**
	 * @param nivelFrecuentes the nivelFrecuentes to set
	 */
	public void setNivelFrecuentes(String nivelFrecuentes) {
		this.nivelFrecuentes = nivelFrecuentes;
	}
	/**
	 * @return the nivelTransferencias
	 */
	public String getNivelTransferencias() {
		return nivelTransferencias;
	}
	/**
	 * @param nivelTransferencias the nivelTransferencias to set
	 */
	public void setNivelTransferencias(String nivelTransferencias) {
		this.nivelTransferencias = nivelTransferencias;
	}
	/**
	 * @return the tokenstatus
	 */
	public String getTokenstatus() {
		return tokenstatus;
	}
	/**
	 * @param tokenstatus the tokenstatus to set
	 */
	public void setTokenstatus(String tokenstatus) {
		this.tokenstatus = tokenstatus;
	}
	/**
	 * @return the nivelMovimientos
	 */
	public String getNivelMovimientos() {
		return nivelMovimientos;
	}
	/**
	 * @param nivelMovimientos the nivelMovimientos to set
	 */
	public void setNivelMovimientos(String nivelMovimientos) {
		this.nivelMovimientos = nivelMovimientos;
	}
	/**
	 * @return the llpub
	 */
	public String getLlpub() {
		return llpub;
	}
	/**
	 * @param llpub the llpub to set
	 */
	public void setLlpub(String llpub) {
		this.llpub = llpub;
	}
	/**
	 * @return the lop
	 */
	public String getLop() {
		return lop;
	}
	/**
	 * @param lop the lop to set
	 */
	public void setLop(String lop) {
		this.lop = lop;
	}
	
}
