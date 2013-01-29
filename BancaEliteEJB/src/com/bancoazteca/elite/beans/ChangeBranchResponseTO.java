package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class ChangeBranchResponseTO implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -7706226228995893629L;
	
	private Collection<CancelacionElementTO> tarjetas;
	private Collection<CodeTO> municipios;
	private Collection<ResultadoBusquedaSucursalTO> sucursales;
	
	private String llavePublica;
	private String longitudHuella;
	
	
	
	public String getLlavePublica() {
		return llavePublica;
	}
	public void setLlavePublica(String llavePublica) {
		this.llavePublica = llavePublica;
	}
	public String getLongitudHuella() {
		return longitudHuella;
	}
	public void setLongitudHuella(String longitudHuella) {
		this.longitudHuella = longitudHuella;
	}
	/**
	 * @return the tarjetas
	 */
	public Collection<CancelacionElementTO> getTarjetas() {
		return tarjetas;
	}
	/**
	 * @param tarjetas the tarjetas to set
	 */
	public void setTarjetas(Collection<CancelacionElementTO> tarjetas) {
		this.tarjetas = tarjetas;
	}
	/**
	 * @return the municipios
	 */
	public Collection<CodeTO> getMunicipios() {
		return municipios;
	}
	/**
	 * @param municipios the municipios to set
	 */
	public void setMunicipios(Collection<CodeTO> municipios) {
		this.municipios = municipios;
	}
	/**
	 * @return the sucursales
	 */
	public Collection<ResultadoBusquedaSucursalTO> getSucursales() {
		return sucursales;
	}
	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(Collection<ResultadoBusquedaSucursalTO> sucursales) {
		this.sucursales = sucursales;
	}

}
