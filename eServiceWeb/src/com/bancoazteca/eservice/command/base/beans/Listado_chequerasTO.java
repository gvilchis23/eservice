package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;

public class Listado_chequerasTO {
	private Collection<ChequeraTO> coleccion_resumen_chequeras;

	public Collection<ChequeraTO> getColeccion_resumen_chequeras() {
		return coleccion_resumen_chequeras;
	}

	public void setColeccion_resumen_chequeras(
			Collection<ChequeraTO> coleccion_resumen_chequeras) {
		this.coleccion_resumen_chequeras = coleccion_resumen_chequeras;
	}
}
