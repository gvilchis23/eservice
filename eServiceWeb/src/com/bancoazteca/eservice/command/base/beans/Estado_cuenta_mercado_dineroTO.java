package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Estado_cuenta_mercado_dineroTO implements Serializable {

	private static final long serialVersionUID = -2796905694915507813L;
	
	private String nombre_cliente;
	private String fecha_inicio_periodo;
	private String fecha_fin_periodo;
	private String tipo_moneda;
	private String nombre_asesor;
	private String rfc_cliente;
	private String numero_contrato;
	private String calle;
	private String colonia;
	private String ciudad;
	private String codigo_postal;
	private String total_invertido_reporto;
	private String total_valor_corte_reporto;
	private String total_invertido_directo_mes_actual;
	private String total_invertido_directo_mes_anterior;
	
	private Estructura_carteraTO estructura_cartera;
	
	private Regimen_operativo_fiscalTO regimen_operativo_fiscal; 
	
	private Collection<Movimientos_inversion_directoTO> coleccion_inversion_directo;
	
	private Collection<Movimiento_inversion_reportoTO> coleccion_inversion_reporto;
	
	private Collection<Movimiento_Mercado_DineroTO> coleccion_movimientos;

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getNombre_asesor() {
		return nombre_asesor;
	}

	public void setNombre_asesor(String nombre_asesor) {
		this.nombre_asesor = nombre_asesor;
	}

	public String getNumero_contrato() {
		return numero_contrato;
	}

	public void setNumero_contrato(String numero_contrato) {
		this.numero_contrato = numero_contrato;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public Estructura_carteraTO getEstructura_cartera() {
		return estructura_cartera;
	}

	public void setEstructura_cartera(Estructura_carteraTO estructura_cartera) {
		this.estructura_cartera = estructura_cartera;
	}

	public Regimen_operativo_fiscalTO getRegimen_operativo_fiscal() {
		return regimen_operativo_fiscal;
	}

	public void setRegimen_operativo_fiscal(
			Regimen_operativo_fiscalTO regimen_operativo_fiscal) {
		this.regimen_operativo_fiscal = regimen_operativo_fiscal;
	}

	public Collection<Movimientos_inversion_directoTO> getColeccion_inversion_directo() {
		return coleccion_inversion_directo;
	}

	public void setColeccion_inversion_directo(
			Collection<Movimientos_inversion_directoTO> coleccion_inversion_directo) {
		this.coleccion_inversion_directo = coleccion_inversion_directo;
	}

	public Collection<Movimiento_inversion_reportoTO> getColeccion_inversion_reporto() {
		return coleccion_inversion_reporto;
	}

	public void setColeccion_inversion_reporto(
			Collection<Movimiento_inversion_reportoTO> coleccion_inversion_reporto) {
		this.coleccion_inversion_reporto = coleccion_inversion_reporto;
	}

	public Collection<Movimiento_Mercado_DineroTO> getColeccion_movimientos() {
		return coleccion_movimientos;
	}

	public void setColeccion_movimientos(
			Collection<Movimiento_Mercado_DineroTO> coleccion_movimientos) {
		this.coleccion_movimientos = coleccion_movimientos;
	}

	public String getTipo_moneda() {
		return tipo_moneda;
	}

	public void setTipo_moneda(String tipo_moneda) {
		this.tipo_moneda = tipo_moneda;
	}

	public String getRfc_cliente() {
		return rfc_cliente;
	}

	public void setRfc_cliente(String rfc_cliente) {
		this.rfc_cliente = rfc_cliente;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getTotal_invertido_reporto() {
		return total_invertido_reporto;
	}

	public void setTotal_invertido_reporto(String total_invertido_reporto) {
		this.total_invertido_reporto = total_invertido_reporto;
	}

	public String getTotal_valor_corte_reporto() {
		return total_valor_corte_reporto;
	}

	public void setTotal_valor_corte_reporto(String total_valor_corte_reporto) {
		this.total_valor_corte_reporto = total_valor_corte_reporto;
	}

	public String getFecha_inicio_periodo() {
		return fecha_inicio_periodo;
	}

	public void setFecha_inicio_periodo(String fecha_inicio_periodo) {
		this.fecha_inicio_periodo = fecha_inicio_periodo;
	}

	public String getFecha_fin_periodo() {
		return fecha_fin_periodo;
	}

	public void setFecha_fin_periodo(String fecha_fin_periodo) {
		this.fecha_fin_periodo = fecha_fin_periodo;
	}

	public String getTotal_invertido_directo_mes_actual() {
		return total_invertido_directo_mes_actual;
	}

	public void setTotal_invertido_directo_mes_actual(
			String total_invertido_directo_mes_actual) {
		this.total_invertido_directo_mes_actual = total_invertido_directo_mes_actual;
	}

	public String getTotal_invertido_directo_mes_anterior() {
		return total_invertido_directo_mes_anterior;
	}

	public void setTotal_invertido_directo_mes_anterior(
			String total_invertido_directo_mes_anterior) {
		this.total_invertido_directo_mes_anterior = total_invertido_directo_mes_anterior;
	}
	
}
