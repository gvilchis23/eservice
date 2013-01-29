package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class LockUnlockCardsRequestTO implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -2168050415369946293L;
	
	//Fields
	private String user;
	private int indexCard;
	private String nombre;
	private String numTarjeta;
	private String estadoTarjeta;
	private String operacion;
	private String optionDispositive;
	private String tokenCode;
	private String clave;
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the indexCard
	 */
	public int getIndexCard() {
		return indexCard;
	}
	/**
	 * @param indexCard the indexCard to set
	 */
	public void setIndexCard(int indexCard) {
		this.indexCard = indexCard;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the numTarjeta
	 */
	public String getNumTarjeta() {
		return numTarjeta;
	}
	/**
	 * @param numTarjeta the numTarjeta to set
	 */
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	/**
	 * @return the estadoTarjeta
	 */
	public String getEstadoTarjeta() {
		return estadoTarjeta;
	}
	/**
	 * @param estadoTarjeta the estadoTarjeta to set
	 */
	public void setEstadoTarjeta(String estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}
	
	/**
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}
	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getOptionDispositive() {
		return optionDispositive;
	}
	public void setOptionDispositive(String optionDispositive) {
		this.optionDispositive = optionDispositive;
	}
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

}
