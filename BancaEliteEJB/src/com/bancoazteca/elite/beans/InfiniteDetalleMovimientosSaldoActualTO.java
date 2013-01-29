package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class InfiniteDetalleMovimientosSaldoActualTO implements Serializable {


	private static final long serialVersionUID = -5805376554230656617L;
	
	private String numGrupos;
	private InfiniteTitularTO titular;
	private Collection<InfiniteAdicionalTO> adicionales;	
	private Collection<String> monedaExtranjeraAbonos;	
	private Collection<String> secPart;
	private Collection<String> monedaExtranjeraCargos;
	private String participe;
	
	public String getNumGrupos() {
		return numGrupos;
	}
	public void setNumGrupos(String numGrupos) {
		this.numGrupos = numGrupos;
	}

	public InfiniteTitularTO getTitular() {
		return titular;
	}
	public void setTitular(InfiniteTitularTO titular) {
		this.titular = titular;
	}
	public Collection<InfiniteAdicionalTO> getAdicionales() {
		return adicionales;
	}
	public void setAdicionales(Collection<InfiniteAdicionalTO> adicionales) {
		this.adicionales = adicionales;
	}
	public Collection<String> getMonedaExtranjeraAbonos() {
		return monedaExtranjeraAbonos;
	}
	public void setMonedaExtranjeraAbonos(Collection<String> monedaExtranjeraAbonos) {
		this.monedaExtranjeraAbonos = monedaExtranjeraAbonos;
	}
	public Collection<String> getSecPart() {
		return secPart;
	}
	public void setSecPart(Collection<String> secPart) {
		this.secPart = secPart;
	}
	public Collection<String> getMonedaExtranjeraCargos() {
		return monedaExtranjeraCargos;
	}
	public void setMonedaExtranjeraCargos(Collection<String> monedaExtranjeraCargos) {
		this.monedaExtranjeraCargos = monedaExtranjeraCargos;
	}
	public String getParticipe() {
		return participe;
	}
	public void setParticipe(String participe) {
		this.participe = participe;
	}
	
	
}
