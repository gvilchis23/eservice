package com.bancoazteca.elite.ejb.alnova.beans;

import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaRequest;
import com.bancoazteca.eservice.beans.Cipher;

public class EjbAlnovaRequestTO extends AlnovaRequest {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EjbAlnovaRequestTO(String nombre,String[] keys) {
		super(nombre, keys);
	}
	
	private String idtransaccion;
	private String descripcionTransaccion;
	private String idAplicacion;
	private Cipher certificado;
	private String configuracionTerminal;
	private String configuracionSucursal;
	
	public String getIdtransaccion() {
		return idtransaccion;
	}
	public void setIdtransaccion(String idtransaccion) {
		this.idtransaccion = idtransaccion;
	}
	public String getDescripcionTransaccion() {
		return descripcionTransaccion;
	}
	public void setDescripcionTransaccion(String descripcionTransaccion) {
		this.descripcionTransaccion = descripcionTransaccion;
	}
	public String getIdAplicacion() {
		return idAplicacion;
	}
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public Cipher getCertificado() {
		return certificado;
	}
	public void setCertificado(Cipher certificado) {
		this.certificado = certificado;
	}
	public String getConfiguracionTerminal() {
		return configuracionTerminal;
	}
	public void setConfiguracionTerminal(String configuracionTerminal) {
		this.configuracionTerminal = configuracionTerminal;
	}
	public String getConfiguracionSucursal() {
		return configuracionSucursal;
	}
	public void setConfiguracionSucursal(String configuracionSucursal) {
		this.configuracionSucursal = configuracionSucursal;
	}
	
	
	
}