package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class SocioPlusTO implements Serializable{

	
	private static final long serialVersionUID = -8137581321404315091L;
	
	
	private BigDecimal monto;
	private String descCuenta;
	private String numUnidadesF;	
	private String numUnidades;
	private String valorUnidad;
	private Collection<BeneficiarioTO> beneficiarios;
	
	
	public Collection<BeneficiarioTO> getBeneficiarios() {
		return beneficiarios;
	}
	public void setBeneficiarios(Collection<BeneficiarioTO> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getDescCuenta() {
		return descCuenta;
	}
	public void setDescCuenta(String descCuenta) {
		this.descCuenta = descCuenta;
	}
	public String getNumUnidadesF() {
		return numUnidadesF;
	}
	public void setNumUnidadesF(String numUnidadesF) {
		this.numUnidadesF = numUnidadesF;
	}
	public String getNumUnidades() {
		return numUnidades;
	}
	public void setNumUnidades(String numUnidades) {
		this.numUnidades = numUnidades;
	}
	public String getValorUnidad() {
		return valorUnidad;
	}
	public void setValorUnidad(String valorUnidad) {
		this.valorUnidad = valorUnidad;
	}
	
	
	

}
