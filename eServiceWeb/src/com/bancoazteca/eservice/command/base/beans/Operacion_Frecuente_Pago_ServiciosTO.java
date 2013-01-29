package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;

public class Operacion_Frecuente_Pago_ServiciosTO implements Serializable{
	
	private static final long serialVersionUID = -5842701912814817579L;
	
	private String usuario_alnova;
	private String operacion_frecuente;
	private String alias_operacion_frecuente;
	private String clave_operacion;
	
	private String numero_referencia;
	private String cuenta_cargo;
	private String importe;
	private String concepto_pago;
	private String digito_verificador;
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
	public String getNumero_referencia() {
		return numero_referencia;
	}
	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getConcepto_pago() {
		return concepto_pago;
	}
	public void setConcepto_pago(String concepto_pago) {
		this.concepto_pago = concepto_pago;
	}
	public String getDigito_verificador() {
		return digito_verificador;
	}
	public void setDigito_verificador(String digito_verificador) {
		this.digito_verificador = digito_verificador;
	}
	public String getClave_operacion() {
		return clave_operacion;
	}
	public void setClave_operacion(String clave_operacion) {
		this.clave_operacion = clave_operacion;
	}
}
