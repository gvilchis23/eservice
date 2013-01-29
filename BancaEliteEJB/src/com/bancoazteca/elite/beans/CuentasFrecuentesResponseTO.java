package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class CuentasFrecuentesResponseTO implements Serializable{
	

	private static final long serialVersionUID = 4168426363248989922L;
	
	private Collection<CuentasFrecuentesTO> cuentasFrecuentes;
	private Collection<CodeTO> bancos;
	private String bankName;
	private ConfirmarEliminacionCuentaFrecuenteTO confirmarEliminacionCuentaFrecuenteTO;
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	
	public ConfirmarEliminacionCuentaFrecuenteTO getConfirmarEliminacionCuentaFrecuenteTO() {
		return confirmarEliminacionCuentaFrecuenteTO;
	}

	public void setConfirmarEliminacionCuentaFrecuenteTO(
			ConfirmarEliminacionCuentaFrecuenteTO confirmarEliminacionCuentaFrecuenteTO) {
		this.confirmarEliminacionCuentaFrecuenteTO = confirmarEliminacionCuentaFrecuenteTO;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Collection<CuentasFrecuentesTO> getCuentasFrecuentes() {
		return cuentasFrecuentes;
	}

	public void setCuentasFrecuentes(Collection<CuentasFrecuentesTO> cuentasFrecuentes) {
		this.cuentasFrecuentes = cuentasFrecuentes;
	}

	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}

	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}

	public Collection<CodeTO> getBancos() {
		return bancos;
	}

	public void setBancos(Collection<CodeTO> bancos) {
		this.bancos = bancos;
	}

	
	
}
