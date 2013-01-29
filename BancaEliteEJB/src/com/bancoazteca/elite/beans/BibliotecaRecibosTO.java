package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class BibliotecaRecibosTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String fechaInicio;
	private String servicio;
	private String fechaFinal;
	
	private Map mapaServicios;
	
	private Collection collectionReciboImpuestos;
	private DatosPdfBibliotecaServiciosTO reciboServicios;
	
	
	
	
	public Collection getCollectionReciboImpuestos() {
		return collectionReciboImpuestos;
	}

	public void setCollectionReciboImpuestos(Collection collectionReciboImpuestos) {
		this.collectionReciboImpuestos = collectionReciboImpuestos;
	}

	public DatosPdfBibliotecaServiciosTO getReciboServicios() {
		return reciboServicios;
	}

	public void setReciboServicios(DatosPdfBibliotecaServiciosTO reciboServicios) {
		this.reciboServicios = reciboServicios;
	}

	private Collection<ReciboBibliotecaTO> biblioVO;

	

	public Map getMapaServicios() {
		return mapaServicios;
	}

	public void setMapaServicios(Map mapaServicios) {
		this.mapaServicios = mapaServicios;
	}

	public Collection<ReciboBibliotecaTO> getBiblioVO() {
		return biblioVO;
	}

	public void setBiblioVO(Collection<ReciboBibliotecaTO> biblioVO) {
		this.biblioVO = biblioVO;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
}