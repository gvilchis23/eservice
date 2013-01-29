package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class InfiniteDetalleMovimientosSaldoCorteTO implements Serializable {


	private static final long serialVersionUID = 1494930401128070736L;
	
	private String numGrupos;
	private InfiniteTitularTO titular;
	private Collection<InfiniteAdicionalTO> adicionales;	
	private Collection<String> monedaExtranjeraAbonos;	
	private Collection<String> secPart;
	private Collection<String> monedaExtranjeraCargos;
	private String participe;
	private Collection<CreditosMovimientosTO> antesCorte;
	private Collection<CreditosMovimientosTO> despuesCorte;
	private BigDecimal sumaCargosAntes;
	private BigDecimal sumaCargosDespues;
	
	
	
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
	public Collection<CreditosMovimientosTO> getDespuesCorte() {
		return despuesCorte;
	}
	public void setDespuesCorte(Collection<CreditosMovimientosTO> despuesCorte) {
		this.despuesCorte = despuesCorte;
	}
	public Collection<CreditosMovimientosTO> getAntesCorte() {
		return antesCorte;
	}
	public void setAntesCorte(Collection<CreditosMovimientosTO> antesCorte) {
		this.antesCorte = antesCorte;
	}
	public BigDecimal getSumaCargosAntes() {
		return sumaCargosAntes;
	}
	public void setSumaCargosAntes(BigDecimal sumaCargosAntes) {
		this.sumaCargosAntes = sumaCargosAntes;
	}
	public BigDecimal getSumaCargosDespues() {
		return sumaCargosDespues;
	}
	public void setSumaCargosDespues(BigDecimal sumaCargosDespues) {
		this.sumaCargosDespues = sumaCargosDespues;
	}

}
