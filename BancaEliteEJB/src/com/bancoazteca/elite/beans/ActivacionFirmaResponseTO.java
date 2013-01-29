package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class ActivacionFirmaResponseTO implements Serializable {

	private static final long serialVersionUID = -1228235454748841125L;
	private String numeroSerie;
	private String preToken;
	private ListaTokenVO tokenVO;
	private String validarNip;
	private Collection<CuentaLoTO> cuentas;
	private DispositivoHuellaTO dispositivoHuellaTO;
	private String carrier;
	private String email;
	private String telefonoCel;
	private String telefono;
	private String fechaAct;
	
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefonoCel() {
		return telefonoCel;
	}
	public void setTelefonoCel(String telefonoCel) {
		this.telefonoCel = telefonoCel;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechaAct() {
		return fechaAct;
	}
	public void setFechaAct(String fechaAct) {
		this.fechaAct = fechaAct;
	}
	public String getPreToken() {
		return preToken;
	}
	public void setPreToken(String preToken) {
		this.preToken = preToken;
	}
	public ListaTokenVO getTokenVO() {
		return tokenVO;
	}
	public void setTokenVO(ListaTokenVO tokenVO) {
		this.tokenVO = tokenVO;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getValidarNip() {
		return validarNip;
	}
	public void setValidarNip(String validarNip) {
		this.validarNip = validarNip;
	}
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public Collection<CuentaLoTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaLoTO> cuentas) {
		this.cuentas = cuentas;
	}

}
