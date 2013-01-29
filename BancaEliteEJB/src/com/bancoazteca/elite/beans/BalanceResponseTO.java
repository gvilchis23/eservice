package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class BalanceResponseTO implements Serializable {
	

	private static final long serialVersionUID = 5231501528154852729L;
	
	private Collection<MovimientosTO> movimientos;
	private String plusvalia;
	private String rendimientos;
	private List<BigDecimal> posicionActual;
	private DetalleMovimientosCuentasTO detalleMovimientosCuentas;
	private String fecha_inicio;
	private String fecha_final;
	private String valorUni;

	public String getValorUni() {
		return valorUni;
	}

	public void setValorUni(String valorUni) {
		this.valorUni = valorUni;
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

	public Collection<MovimientosTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Collection<MovimientosTO> movimientos) {
		this.movimientos = movimientos;
	}


	public String getPlusvalia() {
		return plusvalia;
	}

	public void setPlusvalia(String plusvalia) {
		this.plusvalia = plusvalia;
	}

	public String getRendimientos() {
		return rendimientos;
	}

	public void setRendimientos(String rendimientos) {
		this.rendimientos = rendimientos;
	}

	public List<BigDecimal> getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(List<BigDecimal> posicionActual) {
		this.posicionActual = posicionActual;
	}

	public DetalleMovimientosCuentasTO getDetalleMovimientosCuentas() {
		return detalleMovimientosCuentas;
	}

	public void setDetalleMovimientosCuentas(
			DetalleMovimientosCuentasTO detalleMovimientosCuentas) {
		this.detalleMovimientosCuentas = detalleMovimientosCuentas;
	}
	
	
}
