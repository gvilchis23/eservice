package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class Carta_RetencionesTO implements Serializable {
	
	
	private static final long serialVersionUID = 6519576669858105826L;
	
	//Informacion del cliente
	
	private String nombre_cliente;
	private String calle ;
	private String colonia;
	private String cp;
	private String rfc_cliente;
	
	//Datos generales
	
	private String periodo;
	private String numero_cuenta ;
	private String fecha_hora_emision;
	private String sucursal;
	
	//Retenedor
	
	private String retenedor_azteca;
	private String rfc_banco;
	private String interes_nominal_banco;
	private String interes_real_banco;
	private String isr_banco;
	
	private String retenedor_arrendadora;
	private String rfc_arrendadora;
	private String interes_nominal_arrendadora;
	private String interes_real_arrendadora;
	private String isr_arrendadora;
	
	private String retenedor_iusacell;
	private String rfc_iusacell;
	private String interes_nominal_iusacell;
	private String interes_real_iusacell;
	private String isr_iusacell;

	
	private String retenedor_tvazteca;
	private String rfc_tvazteca;
	private String interes_nominal_tvazteca;
	private String interes_real_tvazteca;
	private String isr_tvazteca;
	
	
	//Gastos y honorarios
	
	private String importe_gastos;
	private String iva_gastos;
	
	private String importe_honorarios;
	private String iva_honorarios;
	
	//Resumen Ejercicio Fiscal 2010
	
	private String total_interes_nominal;
	private String total_interes_real;
	private String total_isr_retenido_acreditable;
	
	
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getRfc_cliente() {
		return rfc_cliente;
	}
	public void setRfc_cliente(String rfc_cliente) {
		this.rfc_cliente = rfc_cliente;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getFecha_hora_emision() {
		return fecha_hora_emision;
	}
	public void setFecha_hora_emision(String fecha_hora_emision) {
		this.fecha_hora_emision = fecha_hora_emision;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getRetenedor_azteca() {
		return retenedor_azteca;
	}
	public void setRetenedor_azteca(String retenedor_azteca) {
		this.retenedor_azteca = retenedor_azteca;
	}
	public String getRfc_banco() {
		return rfc_banco;
	}
	public void setRfc_banco(String rfc_banco) {
		this.rfc_banco = rfc_banco;
	}
	public String getInteres_nominal_banco() {
		return interes_nominal_banco;
	}
	public void setInteres_nominal_banco(String interes_nominal_banco) {
		this.interes_nominal_banco = interes_nominal_banco;
	}
	public String getInteres_real_banco() {
		return interes_real_banco;
	}
	public void setInteres_real_banco(String interes_real_banco) {
		this.interes_real_banco = interes_real_banco;
	}
	public String getIsr_banco() {
		return isr_banco;
	}
	public void setIsr_banco(String isr_banco) {
		this.isr_banco = isr_banco;
	}
	public String getRetenedor_arrendadora() {
		return retenedor_arrendadora;
	}
	public void setRetenedor_arrendadora(String retenedor_arrendadora) {
		this.retenedor_arrendadora = retenedor_arrendadora;
	}
	public String getRfc_arrendadora() {
		return rfc_arrendadora;
	}
	public void setRfc_arrendadora(String rfc_arrendadora) {
		this.rfc_arrendadora = rfc_arrendadora;
	}
	public String getInteres_nominal_arrendadora() {
		return interes_nominal_arrendadora;
	}
	public void setInteres_nominal_arrendadora(String interes_nominal_arrendadora) {
		this.interes_nominal_arrendadora = interes_nominal_arrendadora;
	}
	public String getInteres_real_arrendadora() {
		return interes_real_arrendadora;
	}
	public void setInteres_real_arrendadora(String interes_real_arrendadora) {
		this.interes_real_arrendadora = interes_real_arrendadora;
	}
	public String getIsr_arrendadora() {
		return isr_arrendadora;
	}
	public void setIsr_arrendadora(String isr_arrendadora) {
		this.isr_arrendadora = isr_arrendadora;
	}
	public String getRetenedor_iusacell() {
		return retenedor_iusacell;
	}
	public void setRetenedor_iusacell(String retenedor_iusacell) {
		this.retenedor_iusacell = retenedor_iusacell;
	}
	public String getRfc_iusacell() {
		return rfc_iusacell;
	}
	public void setRfc_iusacell(String rfc_iusacell) {
		this.rfc_iusacell = rfc_iusacell;
	}

	public String getInteres_real_iusacell() {
		return interes_real_iusacell;
	}
	public void setInteres_real_iusacell(String interes_real_iusacell) {
		this.interes_real_iusacell = interes_real_iusacell;
	}
	public String getIsr_iusacell() {
		return isr_iusacell;
	}
	public void setIsr_iusacell(String isr_iusacell) {
		this.isr_iusacell = isr_iusacell;
	}
	public String getRetenedor_tvazteca() {
		return retenedor_tvazteca;
	}
	public void setRetenedor_tvazteca(String retenedor_tvazteca) {
		this.retenedor_tvazteca = retenedor_tvazteca;
	}
	public String getRfc_tvazteca() {
		return rfc_tvazteca;
	}
	public void setRfc_tvazteca(String rfc_tvazteca) {
		this.rfc_tvazteca = rfc_tvazteca;
	}

	public String getInteres_nominal_iusacell() {
		return interes_nominal_iusacell;
	}
	public void setInteres_nominal_iusacell(String interes_nominal_iusacell) {
		this.interes_nominal_iusacell = interes_nominal_iusacell;
	}
	public String getInteres_nominal_tvazteca() {
		return interes_nominal_tvazteca;
	}
	public void setInteres_nominal_tvazteca(String interes_nominal_tvazteca) {
		this.interes_nominal_tvazteca = interes_nominal_tvazteca;
	}
	public String getInteres_real_tvazteca() {
		return interes_real_tvazteca;
	}
	public void setInteres_real_tvazteca(String interes_real_tvazteca) {
		this.interes_real_tvazteca = interes_real_tvazteca;
	}
	public String getIsr_tvazteca() {
		return isr_tvazteca;
	}
	public void setIsr_tvazteca(String isr_tvazteca) {
		this.isr_tvazteca = isr_tvazteca;
	}
	public String getImporte_gastos() {
		return importe_gastos;
	}
	public void setImporte_gastos(String importe_gastos) {
		this.importe_gastos = importe_gastos;
	}
	public String getIva_gastos() {
		return iva_gastos;
	}
	public void setIva_gastos(String iva_gastos) {
		this.iva_gastos = iva_gastos;
	}
	public String getImporte_honorarios() {
		return importe_honorarios;
	}
	public void setImporte_honorarios(String importe_honorarios) {
		this.importe_honorarios = importe_honorarios;
	}
	public String getIva_honorarios() {
		return iva_honorarios;
	}
	public void setIva_honorarios(String iva_honorarios) {
		this.iva_honorarios = iva_honorarios;
	}
	public String getTotal_interes_nominal() {
		return total_interes_nominal;
	}
	public void setTotal_interes_nominal(String total_interes_nominal) {
		this.total_interes_nominal = total_interes_nominal;
	}
	public String getTotal_interes_real() {
		return total_interes_real;
	}
	public void setTotal_interes_real(String total_interes_real) {
		this.total_interes_real = total_interes_real;
	}
	public String getTotal_isr_retenido_acreditable() {
		return total_isr_retenido_acreditable;
	}
	public void setTotal_isr_retenido_acreditable(
			String total_isr_retenido_acreditable) {
		this.total_isr_retenido_acreditable = total_isr_retenido_acreditable;
	}
	
	
}
