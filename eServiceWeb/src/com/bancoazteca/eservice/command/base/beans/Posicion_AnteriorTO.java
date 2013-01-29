package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class Posicion_AnteriorTO implements Serializable  {

	private static final long serialVersionUID = -5722477955034852877L;
	
	private String total_unidades;
	private String costo_promedio_unidad;
	private String importe_al_costo;
	private String precio_unidad_cierre;
	private String importe_total;
	private String plusvalia;
	
	public String getTotal_unidades() {
		return total_unidades;
	}
	public void setTotal_unidades(String total_unidades) {
		this.total_unidades = total_unidades;
	}
	public String getCosto_promedio_unidad() {
		return costo_promedio_unidad;
	}
	public void setCosto_promedio_unidad(String costo_promedio_unidad) {
		this.costo_promedio_unidad = costo_promedio_unidad;
	}
	public String getImporte_al_costo() {
		return importe_al_costo;
	}
	public void setImporte_al_costo(String importe_al_costo) {
		this.importe_al_costo = importe_al_costo;
	}
	public String getPrecio_unidad_cierre() {
		return precio_unidad_cierre;
	}
	public void setPrecio_unidad_cierre(String precio_unidad_cierre) {
		this.precio_unidad_cierre = precio_unidad_cierre;
	}
	public String getImporte_total() {
		return importe_total;
	}
	public void setImporte_total(String importe_total) {
		this.importe_total = importe_total;
	}
	public String getPlusvalia() {
		return plusvalia;
	}
	public void setPlusvalia(String plusvalia) {
		this.plusvalia = plusvalia;
	}

	
}