package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Detalle_Otros_CreditosTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1753065548021853391L;
	
	private Collection<Detalle_Historal_CreditoTO> colleccion_historial;
	private Collection<Detalle_pago_creditoTO> colleccion_pagos;
	
	
	
	public Collection<Detalle_pago_creditoTO> getColleccion_pagos() {
		return colleccion_pagos;
	}
	public void setColleccion_pagos(
			Collection<Detalle_pago_creditoTO> colleccion_pagos) {
		this.colleccion_pagos = colleccion_pagos;
	}
	public Collection<Detalle_Historal_CreditoTO> getColleccion_historial() {
		return colleccion_historial;
	}
	public void setColleccion_historial(
			Collection<Detalle_Historal_CreditoTO> colleccion_historial) {
		this.colleccion_historial = colleccion_historial;
	}


	
}
