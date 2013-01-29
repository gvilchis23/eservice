package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Todito_CardTO {
	
	private Collection <CuentaTO> coleccion_cuentas_cargo;

	public Collection<CuentaTO> getColeccion_cuentas_cargo() {
		return coleccion_cuentas_cargo;
	}

	public void setColeccion_cuentas_cargo(
			Collection<CuentaTO> coleccion_cuentas_cargo) {
		this.coleccion_cuentas_cargo = coleccion_cuentas_cargo;
	}
	
	

}
