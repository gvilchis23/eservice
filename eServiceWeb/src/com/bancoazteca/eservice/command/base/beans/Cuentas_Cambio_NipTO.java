package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Cuentas_Cambio_NipTO {
	private Collection <Cuenta_NipTO> coleccion_cuentas;

	public Collection<Cuenta_NipTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}

	public void setColeccion_cuentas(Collection<Cuenta_NipTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}
	
}
