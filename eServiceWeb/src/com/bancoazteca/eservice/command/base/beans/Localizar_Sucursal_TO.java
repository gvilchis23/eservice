package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Localizar_Sucursal_TO {
	
	private Collection<Entidad_FederativaTO> coleccion_entidades_federativas;
	private Collection<MunicipioTO> coleccion_municipios;
	private TiendaTO tienda;
	private Collection<TiendaTO> coleccion_tiendas;

	public Collection<Entidad_FederativaTO> getColeccion_entidades_federativas() {
		return coleccion_entidades_federativas;
	}
	public void setColeccion_entidades_federativas(
			Collection<Entidad_FederativaTO> coleccion_entidades_federativas) {
		this.coleccion_entidades_federativas = coleccion_entidades_federativas;
	}
	public Collection<MunicipioTO> getColeccion_municipios() {
		return coleccion_municipios;
	}
	public void setColeccion_municipios(Collection<MunicipioTO> coleccion_municipios) {
		this.coleccion_municipios = coleccion_municipios;
	}
	public TiendaTO getTienda() {
		return tienda;
	}
	public void setTienda(TiendaTO tienda) {
		this.tienda = tienda;
	}
	public Collection<TiendaTO> getColeccion_tiendas() {
		return coleccion_tiendas;
	}
	public void setColeccion_tiendas(Collection<TiendaTO> coleccion_tiendas) {
		this.coleccion_tiendas = coleccion_tiendas;
	}
}
