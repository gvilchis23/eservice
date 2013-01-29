package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class TransferenciaTercerosResponseTO implements Serializable{

	private static final long serialVersionUID = 8178409536283855997L;
	
	private Collection<CuentaTransaccionesTO> cuentas;
	private ConfirmarTransferenciaTercerosTO confirmarTercerosTO;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private String mensajeRetencion;
	private String mensajeMail;
	private String cuentaDestinoFormateda;
	
	public Collection<CuentaTransaccionesTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaTransaccionesTO> cuentas) {
		this.cuentas = cuentas;
	}
	public ConfirmarTransferenciaTercerosTO getConfirmarTercerosTO() {
		return confirmarTercerosTO;
	}
	public void setConfirmarTercerosTO(
			ConfirmarTransferenciaTercerosTO confirmarTercerosTO) {
		this.confirmarTercerosTO = confirmarTercerosTO;
	}
	public String getMensajeRetencion() {
		return mensajeRetencion;
	}
	public void setMensajeRetencion(String mensajeRetencion) {
		this.mensajeRetencion = mensajeRetencion;
	}
	/**
	 * @return the dispositivoHuellaTO
	 */
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	/**
	 * @param dispositivoHuellaTO the dispositivoHuellaTO to set
	 */
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public String getMensajeMail() {
		return mensajeMail;
	}
	public void setMensajeMail(String mensajeMail) {
		this.mensajeMail = mensajeMail;
	}
	public String getCuentaDestinoFormateda() {
		return cuentaDestinoFormateda;
	}
	public void setCuentaDestinoFormateda(String cuentaDestinoFormateda) {
		this.cuentaDestinoFormateda = cuentaDestinoFormateda;
	}
	
}
