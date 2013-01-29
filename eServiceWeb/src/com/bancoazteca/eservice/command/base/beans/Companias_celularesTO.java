package com.bancoazteca.eservice.command.base.beans;

import java.util.ArrayList;
import java.util.Collection;

public class Companias_celularesTO {

	private Collection<Compania_celularTO> coleccion_companias = new ArrayList<Compania_celularTO>();

	public Collection<Compania_celularTO> getColeccion_companias() {
		return coleccion_companias;
	}

	public void setColeccion_companias(Collection<Compania_celularTO> coleccion_companias) {
		this.coleccion_companias = coleccion_companias;
	}
	public void addCarrier(Compania_celularTO carrier){
		this.coleccion_companias.add(carrier);
	}
	public void addCarrier(String carrier){
		Compania_celularTO compania = new Compania_celularTO();
		compania.setCompania(carrier);
		this.coleccion_companias.add(compania);
	}
}
