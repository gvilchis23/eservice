package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Pago_tarjetasTO {
	private Collection<Cuenta_CargoTO> coleccion_cuentas;
	private Collection<Tarjetas_creditoTO>coleccion_tarjetas_credito;
	public Collection<Cuenta_CargoTO> getColeccion_cuentas() {
		return coleccion_cuentas;
	}
	public void setColeccion_cuentas(Collection<Cuenta_CargoTO> coleccion_cuentas) {
		this.coleccion_cuentas = coleccion_cuentas;
	}
	public Collection<Tarjetas_creditoTO> getColeccion_tarjetas_credito() {
		return coleccion_tarjetas_credito;
	}
	public void setColeccion_tarjetas_credito(
			Collection<Tarjetas_creditoTO> coleccion_tarjetas_credito) {
		this.coleccion_tarjetas_credito = coleccion_tarjetas_credito;
	}
}
