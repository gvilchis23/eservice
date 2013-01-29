package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Cuentas_CargoTO {
	
	private Collection <Cuenta_CargoTO> coleccion_cuentas;
	
	private String valor_unidad;

	public Collection<Cuenta_CargoTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}

	public void setColeccion_cuentas(Collection<Cuenta_CargoTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}

	public String getValor_unidad() {
		return valor_unidad;
	}

	public void setValor_unidad(String valor_unidad) {
		this.valor_unidad = valor_unidad;
	}

}
