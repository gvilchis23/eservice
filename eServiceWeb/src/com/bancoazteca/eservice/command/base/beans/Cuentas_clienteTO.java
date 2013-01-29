package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Cuentas_clienteTO {
	
	private Collection<CuentaTO> coleccion_cuentas;

	public Collection<CuentaTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}

	public void setColeccion_cuentas(Collection<CuentaTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}
}
