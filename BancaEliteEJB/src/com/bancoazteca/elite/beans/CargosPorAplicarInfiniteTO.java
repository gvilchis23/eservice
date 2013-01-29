package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class CargosPorAplicarInfiniteTO implements Serializable{

	private static final long serialVersionUID = 652643004042434571L;
	
	private BigDecimal monto;
	private String descrpcion;
	private String fechaOperacion;
	
	
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getDescrpcion() {
		return descrpcion;
	}
	public void setDescrpcion(String descrpcion) {
		this.descrpcion = descrpcion;
	}
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	
}
