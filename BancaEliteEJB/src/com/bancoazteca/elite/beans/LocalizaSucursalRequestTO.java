package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class LocalizaSucursalRequestTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 211145338133567338L;
	
	private int idPais;
	private int idEstado;
	private String canales;
	private int idMunicipio;
	private int numeroTienda;
	private String codigoPostal;
	private String palabra;
	
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getCanales() {
		return canales;
	}
	public void setCanales(String canales) {
		this.canales = canales;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public int getNumeroTienda() {
		return numeroTienda;
	}
	public void setNumeroTienda(int numeroTienda) {
		this.numeroTienda = numeroTienda;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getPalabra() {
		return palabra;
	}
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
}
