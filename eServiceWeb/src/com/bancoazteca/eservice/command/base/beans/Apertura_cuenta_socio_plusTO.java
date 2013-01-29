package com.bancoazteca.eservice.command.base.beans;

import java.util.Collection;
public class Apertura_cuenta_socio_plusTO {
	private String cuenta_cargo;
	private String monto_invertir;
	private String comision;
	private String iva_comision;
	private String total_unidades;
	private String referencia;
	private String valor_unidad;
	private String fecha_valor_unidad;
	private Collection<BeneficiarioTO> coleccion_beneficiarios;
	private String folio_apertura;
	private String numero_cuenta_socio_plus;
	private String unidades_adquiridas;
	public String getNumero_cuenta_socio_plus() {
		return numero_cuenta_socio_plus;
	}
	public void setNumero_cuenta_socio_plus(String numero_cuenta_socio_plus) {
		this.numero_cuenta_socio_plus = numero_cuenta_socio_plus;
	}
	public String getUnidades_adquiridas() {
		return unidades_adquiridas;
	}
	public void setUnidades_adquiridas(String unidades_adquiridas) {
		this.unidades_adquiridas = unidades_adquiridas;
	}
	public String getCuenta_cargo() {
		return cuenta_cargo;
	}
	public void setCuenta_cargo(String cuenta_cargo) {
		this.cuenta_cargo = cuenta_cargo;
	}
	public String getMonto_invertir() {
		return monto_invertir;
	}
	public void setMonto_invertir(String monto_invertir) {
		this.monto_invertir = monto_invertir;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getIva_comision() {
		return iva_comision;
	}
	public void setIva_comision(String iva_comision) {
		this.iva_comision = iva_comision;
	}
	public String getTotal_unidades() {
		return total_unidades;
	}
	public void setTotal_unidades(String total_unidades) {
		this.total_unidades = total_unidades;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getValor_unidad() {
		return valor_unidad;
	}
	public void setValor_unidad(String valor_unidad) {
		this.valor_unidad = valor_unidad;
	}
	public String getFecha_valor_unidad() {
		return fecha_valor_unidad;
	}
	public void setFecha_valor_unidad(String fecha_valor_unidad) {
		this.fecha_valor_unidad = fecha_valor_unidad;
	}
	public Collection<BeneficiarioTO> getColeccion_beneficiarios() {
		return coleccion_beneficiarios;
	}
	public void setColeccion_beneficiarios(
			Collection<BeneficiarioTO> coleccion_beneficiarios) {
		this.coleccion_beneficiarios = coleccion_beneficiarios;
	}
	public String getFolio_apertura() {
		return folio_apertura;
	}
	public void setFolio_apertura(String folio_apertura) {
		this.folio_apertura = folio_apertura;
	}
}
