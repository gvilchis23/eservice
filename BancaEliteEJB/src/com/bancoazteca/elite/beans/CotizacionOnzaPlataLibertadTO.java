package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class CotizacionOnzaPlataLibertadTO implements Serializable{

	private static final long serialVersionUID = -8637415555311244661L;

	private String precioCompra;
	private String precioVenta;
	private String rango;
	private String rangoMin;
	private String rangoMax;
	private String numRango;
	
	public String getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(String precioCompra) {
		this.precioCompra = precioCompra;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getRangoMin() {
		return rangoMin;
	}
	public void setRangoMin(String rangoMin) {
		this.rangoMin = rangoMin;
	}
	public String getRangoMax() {
		return rangoMax;
	}
	public void setRangoMax(String rangoMax) {
		this.rangoMax = rangoMax;
	}
	public String getNumRango() {
		return numRango;
	}
	public void setNumRango(String numRango) {
		this.numRango = numRango;
	}
	
	
}
