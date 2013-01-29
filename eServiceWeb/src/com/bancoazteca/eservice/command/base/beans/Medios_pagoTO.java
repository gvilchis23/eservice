package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Medios_pagoTO {
	private Collection<Detalle_TarjetaTO> coleccion_medios_pago;

	public Collection<Detalle_TarjetaTO> getColeccion_medios_pago() {
		return coleccion_medios_pago;
	}

	public void setColeccion_medios_pago(
			Collection<Detalle_TarjetaTO> coleccion_medios_pago) {
		this.coleccion_medios_pago = coleccion_medios_pago;
	}
}
