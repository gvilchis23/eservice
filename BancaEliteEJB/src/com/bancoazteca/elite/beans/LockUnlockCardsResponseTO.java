package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class LockUnlockCardsResponseTO implements Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -5876212752902548341L;
	
	private Collection<CancelacionElementTO> tarjetasValidas;
	private CancelacionElementTO tarjeta;
	private Map<String, String> actionErrors;
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public Map<String, String> getActionErrors() {
		return actionErrors;
	}
	public void setActionErrors(Map<String, String> actionErrors) {
		this.actionErrors = actionErrors;
	}
	
	/**
	 * @return the tarjetasValidas
	 */
	public Collection<CancelacionElementTO> getTarjetasValidas() {
		return tarjetasValidas;
	}

	/**
	 * @param tarjetasValidas the tarjetasValidas to set
	 */
	public void setTarjetasValidas(Collection<CancelacionElementTO> tarjetasValidas) {
		this.tarjetasValidas = tarjetasValidas;
	}

	/**
	 * @return the tarjeta
	 */
	public CancelacionElementTO getTarjeta() {
		return tarjeta;
	}

	/**
	 * @param tarjeta the tarjeta to set
	 */
	public void setTarjeta(CancelacionElementTO tarjeta) {
		this.tarjeta = tarjeta;
	}

}
