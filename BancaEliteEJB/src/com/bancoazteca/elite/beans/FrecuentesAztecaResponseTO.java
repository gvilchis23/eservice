package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class FrecuentesAztecaResponseTO implements Serializable{

	private static final long serialVersionUID = -862464562324500l;
	private Collection<FrecuentesAztecaResponseTO> frecuentes;
	private String index;
	private String referencia;
	private String telefono;
	private String user_id;
	private String verifyDigit;
	private String refLenght;
	private String canal;
	private String ultima_actualizacion;
	private String status;
	private Collection tarjetasFrecuentes;
	private Collection telefonosFrecuentes;
	private String tarjeta;
	private String tipoTarjeta;
	private String banco;
	private AltaTarjetaFrecuenteTO  altaTarjetaFrecuenteTO;
	private DispositivoHuellaTO dispositivoHuellaTO;
	
	public DispositivoHuellaTO getDispositivoHuellaTO() {
		return dispositivoHuellaTO;
	}
	public void setDispositivoHuellaTO(DispositivoHuellaTO dispositivoHuellaTO) {
		this.dispositivoHuellaTO = dispositivoHuellaTO;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Collection getTarjetasFrecuentes() {
		return tarjetasFrecuentes;
	}
	public void setTarjetasFrecuentes(Collection tarjetasFrecuentes) {
		this.tarjetasFrecuentes = tarjetasFrecuentes;
	}
	public Collection getTelefonosFrecuentes() {
		return telefonosFrecuentes;
	}
	public void setTelefonosFrecuentes(Collection telefonosFrecuentes) {
		this.telefonosFrecuentes = telefonosFrecuentes;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getVerifyDigit() {
		return verifyDigit;
	}
	public void setVerifyDigit(String verifyDigit) {
		this.verifyDigit = verifyDigit;
	}
	public Collection<FrecuentesAztecaResponseTO> getFrecuentes() {
		return frecuentes;
	}
	public void setFrecuentes(Collection<FrecuentesAztecaResponseTO> frecuentes) {
		this.frecuentes = frecuentes;
	}
	public String getRefLenght() {
		return refLenght;
	}
	public void setRefLenght(String refLenght) {
		this.refLenght = refLenght;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getUltima_actualizacion() {
		return ultima_actualizacion;
	}
	public void setUltima_actualizacion(String ultima_actualizacion) {
		this.ultima_actualizacion = ultima_actualizacion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public AltaTarjetaFrecuenteTO getAltaTarjetaFrecuenteTO() {
		return altaTarjetaFrecuenteTO;
	}
	public void setAltaTarjetaFrecuenteTO(
			AltaTarjetaFrecuenteTO altaTarjetaFrecuenteTO) {
		this.altaTarjetaFrecuenteTO = altaTarjetaFrecuenteTO;
	}
	
}
