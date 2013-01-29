package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class CreditosResponseTO implements Serializable{

	private static final long serialVersionUID = 8862972257014459329L;

	private Collection<DetallePagoCreditoTO> detallesCredito;
	private Collection<DetallePagoOtrosCreditosTO>detallePagosCredito;
	
	public Collection<DetallePagoOtrosCreditosTO> getDetallePagosCredito() {
		return detallePagosCredito;
	}
	public void setDetallePagosCredito(
			Collection<DetallePagoOtrosCreditosTO> detallePagosCredito) {
		this.detallePagosCredito = detallePagosCredito;
	}
	private Map<String, String> actionErrors;

	public Collection<DetallePagoCreditoTO> getDetallesCredito() {
		return detallesCredito;
	}
	public void setDetallesCredito(Collection<DetallePagoCreditoTO> detallesCredito) {
		this.detallesCredito = detallesCredito;
	}
	public Map<String, String> getActionErrors() {
		return actionErrors;
	}
	public void setActionErrors(Map<String, String> actionErrors) {
		this.actionErrors = actionErrors;
	}
	
}
