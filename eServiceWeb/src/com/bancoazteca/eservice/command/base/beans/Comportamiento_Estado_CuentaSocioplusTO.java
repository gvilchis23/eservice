package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class Comportamiento_Estado_CuentaSocioplusTO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Collection<Detalle_Movimiento_SocioplusTO> coleccion_movimientos;
	// Datos del cliente
	private String nombre_cliente;
	private String calle_cliente;
	private String colonia;
	private String codigo_postal;
	private String rfc;
	//Datos del estado de cuenta
	private String periodo_cuenta;
	private String fecha_emision_cuenta;
	private String numero_cuenta;
	private String sucursal_cuenta;
	
	private String saldo_unidades_mes_anterior;
	private String compra_unidades_mes_actual;
	private String venta_unidades_mes_actual;
	private String saldo_unidades_mes_actual;
	private String precio_unidad_dia_corte;
	private String importe_actual_moneda_nacional;
	
	private String precio_unidad_mes_anterior;
	private String precio_unidad_dia_corriente;
	
	private String saldo_anterior;
	private String total_depositos;
	private String total_retiros;
	private String interes_dia_corte;
	private String saldo_actual;
	
	private String rendimiento_ultimos_dias;
	private String  ISR_retenido;
	
	
	
	public String getRendimiento_ultimos_dias() {
		return rendimiento_ultimos_dias;
	}

	public void setRendimiento_ultimos_dias(String rendimiento_ultimos_dias) {
		this.rendimiento_ultimos_dias = rendimiento_ultimos_dias;
	}

	public String getISR_retenido() {
		return ISR_retenido;
	}

	public void setISR_retenido(String isr_retenido) {
		ISR_retenido = isr_retenido;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getCalle_cliente() {
		return calle_cliente;
	}

	public void setCalle_cliente(String calle_cliente) {
		this.calle_cliente = calle_cliente;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getPeriodo_cuenta() {
		return periodo_cuenta;
	}

	public void setPeriodo_cuenta(String periodo_cuenta) {
		this.periodo_cuenta = periodo_cuenta;
	}

	public String getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

	public String getFecha_emision_cuenta() {
		return fecha_emision_cuenta;
	}

	public void setFecha_emision_cuenta(String fecha_emision_cuenta) {
		this.fecha_emision_cuenta = fecha_emision_cuenta;
	}

	public String getSucursal_cuenta() {
		return sucursal_cuenta;
	}

	public void setSucursal_cuenta(String sucursal_cuenta) {
		this.sucursal_cuenta = sucursal_cuenta;
	}

	public String getSaldo_unidades_mes_anterior() {
		return saldo_unidades_mes_anterior;
	}

	public void setSaldo_unidades_mes_anterior(String saldo_unidades_mes_anterior) {
		this.saldo_unidades_mes_anterior = saldo_unidades_mes_anterior;
	}

	public String getCompra_unidades_mes_actual() {
		return compra_unidades_mes_actual;
	}

	public void setCompra_unidades_mes_actual(String compra_unidades_mes_actual) {
		this.compra_unidades_mes_actual = compra_unidades_mes_actual;
	}

	public String getVenta_unidades_mes_actual() {
		return venta_unidades_mes_actual;
	}

	public void setVenta_unidades_mes_actual(String venta_unidades_mes_actual) {
		this.venta_unidades_mes_actual = venta_unidades_mes_actual;
	}

	public String getSaldo_unidades_mes_actual() {
		return saldo_unidades_mes_actual;
	}

	public void setSaldo_unidades_mes_actual(String saldo_unidades_mes_actual) {
		this.saldo_unidades_mes_actual = saldo_unidades_mes_actual;
	}

	public String getPrecio_unidad_dia_corte() {
		return precio_unidad_dia_corte;
	}

	public void setPrecio_unidad_dia_corte(String precio_unidad_dia_corte) {
		this.precio_unidad_dia_corte = precio_unidad_dia_corte;
	}

	public String getImporte_actual_moneda_nacional() {
		return importe_actual_moneda_nacional;
	}

	public void setImporte_actual_moneda_nacional(
			String importe_actual_moneda_nacional) {
		this.importe_actual_moneda_nacional = importe_actual_moneda_nacional;
	}

	public String getPrecio_unidad_mes_anterior() {
		return precio_unidad_mes_anterior;
	}

	public void setPrecio_unidad_mes_anterior(String precio_unidad_mes_anterior) {
		this.precio_unidad_mes_anterior = precio_unidad_mes_anterior;
	}

	public String getPrecio_unidad_dia_corriente() {
		return precio_unidad_dia_corriente;
	}

	public void setPrecio_unidad_dia_corriente(String precio_unidad_dia_corriente) {
		this.precio_unidad_dia_corriente = precio_unidad_dia_corriente;
	}

	public String getSaldo_anterior() {
		return saldo_anterior;
	}

	public void setSaldo_anterior(String saldo_anterior) {
		this.saldo_anterior = saldo_anterior;
	}

	public String getTotal_depositos() {
		return total_depositos;
	}

	public void setTotal_depositos(String total_depositos) {
		this.total_depositos = total_depositos;
	}

	public String getTotal_retiros() {
		return total_retiros;
	}

	public void setTotal_retiros(String total_retiros) {
		this.total_retiros = total_retiros;
	}

	public String getInteres_dia_corte() {
		return interes_dia_corte;
	}

	public void setInteres_dia_corte(String interes_dia_corte) {
		this.interes_dia_corte = interes_dia_corte;
	}

	public String getSaldo_actual() {
		return saldo_actual;
	}

	public void setSaldo_actual(String saldo_actual) {
		this.saldo_actual = saldo_actual;
	}

	public Collection<Detalle_Movimiento_SocioplusTO> getColeccion_movimientos() {
		return coleccion_movimientos;
	}

	public void setColeccion_movimientos(
			Collection<Detalle_Movimiento_SocioplusTO> coleccion_movimientos) {
		this.coleccion_movimientos = coleccion_movimientos;
	}
	
}
