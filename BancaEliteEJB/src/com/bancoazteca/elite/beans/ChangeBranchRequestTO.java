package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class ChangeBranchRequestTO implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 4360041823885265083L;
	
	private boolean esBusquedaMunicipio;
	private String opcion_seguridad;
	private String indiceSucursal;
	private String indice;
	private String user;
	private String opcionCambioSucursal;
	private int estado;
	private int municipio;
	private int tipoBusqueda;
	private String codigoPostal;
	private int tipoValidacion;
	private String clave_seguridad;
	private String tokenCode;
	
	
	public String getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(String claveSeguridad) {
		clave_seguridad = claveSeguridad;
	}

	public String getOpcion_seguridad() {
		return opcion_seguridad;
	}

	public void setOpcion_seguridad(String opcionSeguridad) {
		opcion_seguridad = opcionSeguridad;
	}

	public String getIndiceSucursal() {
		return indiceSucursal;
	}

	public void setIndiceSucursal(String indiceSucursal) {
		this.indiceSucursal = indiceSucursal;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public boolean isEsBusquedaMunicipio() {
		return esBusquedaMunicipio;
	}

	public void setEsBusquedaMunicipio(boolean esBusquedaMunicipio) {
		this.esBusquedaMunicipio = esBusquedaMunicipio;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

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
	 * @return the opcionCambioSucursal
	 */
	public String getOpcionCambioSucursal() {
		return opcionCambioSucursal;
	}

	/**
	 * @param opcionCambioSucursal the opcionCambioSucursal to set
	 */
	public void setOpcionCambioSucursal(String opcionCambioSucursal) {
		this.opcionCambioSucursal = opcionCambioSucursal;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the municipio
	 */
	public int getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the tipoBusqueda
	 */
	public int getTipoBusqueda() {
		return tipoBusqueda;
	}

	/**
	 * @param tipoBusqueda the tipoBusqueda to set
	 */
	public void setTipoBusqueda(int tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the tipoValidacion
	 */
	public int getTipoValidacion() {
		return tipoValidacion;
	}

	/**
	 * @param tipoValidacion the tipoValidacion to set
	 */
	public void setTipoValidacion(int tipoValidacion) {
		this.tipoValidacion = tipoValidacion;
	}
}
