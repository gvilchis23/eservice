package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class Detalle_CuentaTO implements Serializable {

	private static final long serialVersionUID = -3093481835639577156L;
	
	private String cliente;
	private String cuenta;
	private String tipo_cuenta;
	private String fecha_inicio;
	private String fecha_final;
	private String saldo_disponible;
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getTipo_cuenta() {
		return tipo_cuenta;
	}
	public void setTipo_cuenta(String tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_final() {
		return fecha_final;
	}
	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}
	public String getSaldo_disponible() {
		return saldo_disponible;
	}
	public void setSaldo_disponible(String saldo_disponible) {
		this.saldo_disponible = saldo_disponible;
	}
	
	
	
}