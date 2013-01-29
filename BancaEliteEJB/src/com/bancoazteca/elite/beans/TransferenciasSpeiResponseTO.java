package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class TransferenciasSpeiResponseTO implements Serializable{

	private static final long serialVersionUID = 5984032472062048097L;
	
	private String curp;
	private ConfirmarTransferenciaSpeiTO confirmarTransferenciaSpeiTO;
	private Collection<BancoSpeiTO> bancos;
	private Collection<CuentaVisibleSpeiTO> cuentasSpei;
	private Collection<CuentaTransaccionesTO> cuentas;
	private String fechaProgramacion;
	private String fechaProgramacionFormateada;
	private String tipoReal;
	private FechaSpeiTO fechaSpeiTO;
	private String mensajeMail;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private String message;
	private String cuentaDestinoFormateda;
	
	public String getFechaProgramacionFormateada() {
		return fechaProgramacionFormateada;
	}
	public void setFechaProgramacionFormateada(String fechaProgramacionformateada) {
		this.fechaProgramacionFormateada = fechaProgramacionformateada;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public ConfirmarTransferenciaSpeiTO getConfirmarTransferenciaSpeiTO() {
		return confirmarTransferenciaSpeiTO;
	}
	public void setConfirmarTransferenciaSpeiTO(
			ConfirmarTransferenciaSpeiTO confirmarTransferenciaSpeiTO) {
		this.confirmarTransferenciaSpeiTO = confirmarTransferenciaSpeiTO;
	}
	public Collection<BancoSpeiTO> getBancos() {
		return bancos;
	}
	public void setBancos(Collection<BancoSpeiTO> bancos) {
		this.bancos = bancos;
	}
	public String getFechaProgramacion() {
		return fechaProgramacion;
	}
	public void setFechaProgramacion(String fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public String getTipoReal() {
		return tipoReal;
	}
	public void setTipoReal(String tipoReal) {
		this.tipoReal = tipoReal;
	}
	public FechaSpeiTO getFechaSpeiTO() {
		return fechaSpeiTO;
	}
	public void setFechaSpeiTO(FechaSpeiTO fechaSpeiTO) {
		this.fechaSpeiTO = fechaSpeiTO;
	}
	public String getMensajeMail() {
		return mensajeMail;
	}
	public void setMensajeMail(String mensajeMail) {
		this.mensajeMail = mensajeMail;
	}
	public Collection<CuentaVisibleSpeiTO> getCuentasSpei() {
		return cuentasSpei;
	}
	public void setCuentasSpei(Collection<CuentaVisibleSpeiTO> cuentasSpei) {
		this.cuentasSpei = cuentasSpei;
	}
	public Collection<CuentaTransaccionesTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaTransaccionesTO> cuentas) {
		this.cuentas = cuentas;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public String getCuentaDestinoFormateda() {
		return cuentaDestinoFormateda;
	}
	public void setCuentaDestinoFormateda(String cuentaDestinoFormateda) {
		this.cuentaDestinoFormateda = cuentaDestinoFormateda;
	}
	
	
}
