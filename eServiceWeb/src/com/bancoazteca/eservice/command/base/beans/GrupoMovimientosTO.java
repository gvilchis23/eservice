package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class GrupoMovimientosTO implements Serializable {
	
	private String fecha_inicial;
	private String fecha_final;
	private String total_movimientos;
	
	private Collection<Detalle_Movimiento_InfiniteTO> coleccion_movimientos;
	public String getFecha_inicial() {
		return fecha_inicial;
	}
	public void setFecha_inicial(String fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}
	public String getFecha_final() {
		return fecha_final;
	}
	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}
	public String getTotal_movimientos() {
		return total_movimientos;
	}
	public void setTotal_movimientos(String total_movimientos) {
		this.total_movimientos = total_movimientos;
	}
	public Collection<Detalle_Movimiento_InfiniteTO> getColeccion_movimientos() {
		return coleccion_movimientos;
	}
	public void setColeccion_movimientos(
			Collection<Detalle_Movimiento_InfiniteTO> coleccionMovimientos) {
		this.coleccion_movimientos = coleccionMovimientos;
	}
}
