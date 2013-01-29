package com.bancoazteca.elite.beans;

import java.io.Serializable;

import com.bancoazteca.elite.WsInformacionTiendas.Estado;
import com.bancoazteca.elite.WsInformacionTiendas.Municipio;
import com.bancoazteca.elite.WsInformacionTiendas.Tienda;

public class LocalizaSucursalResponseTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4174228211169351998L;
	
	private Estado[] estados;
	private Municipio[] municipios;
	private Tienda[] tienda;

	public Estado[] getEstados() {
		return estados;
	}

	public void setEstados(Estado[] estados) {
		this.estados = estados;
	}

	public Municipio[] getMunicipios() {
		return municipios;
	}

	public void setMunicipios(Municipio[] municipios) {
		this.municipios = municipios;
	}

	public Tienda[] getTienda() {
		return tienda;
	}

	public void setTienda(Tienda[] tienda) {
		this.tienda = tienda;
	}
}
