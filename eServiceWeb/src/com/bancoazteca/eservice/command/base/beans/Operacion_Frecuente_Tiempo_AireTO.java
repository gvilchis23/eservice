package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Operacion_Frecuente_Tiempo_AireTO implements Serializable{
	
	private static final long serialVersionUID = -6359345441010133039L;
	
	private String usuario_alnova;
	private String operacion_frecuente;
	private String alias_operacion_frecuente;
	private String clave_operacion;

	private String monto;
	private String iva;
	private String folio_aclaracion;
	private String comision;
	private String numero_telefonico;
	private String carrier;
	private String cuenta_cargo;
	
	public String getUsuario_alnova() {
		return usuario_alnova;
	}
	public void setUsuario_alnova(String usuario_alnova) {
		this.usuario_alnova = usuario_alnova;
	}
	public String getOperacion_frecuente() {
		return operacion_frecuente;
	}
	public void setOperacion_frecuente(String operacion_frecuente) {
		this.operacion_frecuente = operacion_frecuente;
	}
	public String getAlias_operacion_frecuente() {
		return alias_operacion_frecuente;
	}
	public void setAlias_operacion_frecuente(String alias_operacion_frecuente) {
		this.alias_operacion_frecuente = alias_operacion_frecuente;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getFolio_aclaracion() {
		return folio_aclaracion;
	}
	public void setFolio_aclaracion(String folio_aclaracion) {
		this.folio_aclaracion = folio_aclaracion;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getNumero_telefonico() {
		return numero_telefonico;
	}
	public void setNumero_telefonico(String numero_telefonico) {
		this.numero_telefonico = numero_telefonico;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getClave_operacion() {
		return clave_operacion;
	}
	public void setClave_operacion(String clave_operacion) {
		this.clave_operacion = clave_operacion;
	}
}