package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class TransferenciasInternacionalesResponseTO implements Serializable{

	private static final long serialVersionUID = 879831494415147630L;
	
	private Collection<CuentaTransaccionesTO> cuentas;
	private String valorDolar;
	private String tipoCambioFormateado;
	private String restriccionHorario;	
	private ConfirmarTransferenciaInternacionalTO confirmarTransferenciaInternacionalTO;
	
	private String clavePanama;
	private String tipoTransferencia;	
	private String mensajeMail;
	private String cuentaDestinoFormateda;
	
	private DispositivoHuellaTO dispositivoHuellaTO;

	
		
	public Collection<CuentaTransaccionesTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaTransaccionesTO> cuentas) {
		this.cuentas = cuentas;
	}
	public String getValorDolar() {
		return valorDolar;
	}
	public void setValorDolar(String valorDolar) {
		this.valorDolar = valorDolar;
	}
	public ConfirmarTransferenciaInternacionalTO getConfirmarTransferenciaInternacionalTO() {
		return confirmarTransferenciaInternacionalTO;
	}
	public void setConfirmarTransferenciaInternacionalTO(
			ConfirmarTransferenciaInternacionalTO confirmarTransferenciaInternacionalTO) {
		this.confirmarTransferenciaInternacionalTO = confirmarTransferenciaInternacionalTO;
	}
	public String getClavePanama() {
		return clavePanama;
	}
	public void setClavePanama(String clavePanama) {
		this.clavePanama = clavePanama;
	}
	public String getTipoTransferencia() {
		return tipoTransferencia;
	}
	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
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
	public String getRestriccionHorario() {
		return restriccionHorario;
	}
	public void setRestriccionHorario(String restriccionHorario) {
		this.restriccionHorario = restriccionHorario;
	}
	public String getTipoCambioFormateado() {
		return tipoCambioFormateado;
	}
	public void setTipoCambioFormateado(String tipoCambioFormateado) {
		this.tipoCambioFormateado = tipoCambioFormateado;
	}	
	
}
