package com.bancoazteca.elite.ejb.inversiones.beans;

import java.util.ArrayList;
import java.util.List;

import com.bancoazteca.elite.beans.BeneficiarioTO;

public class AperturaCuentaEjeEliteEntradaTO {
	private static final int NUMERO_MAXIMO_BENEFICIARIOS=4;
	
	private String numeroCliente;
	private String moneda;
	private String monto;
	private List<BeneficiarioTO> beneficiarios= new ArrayList<BeneficiarioTO>(NUMERO_MAXIMO_BENEFICIARIOS);
	
	public String getNumeroCliente() {
		return numeroCliente;
	}
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public List<BeneficiarioTO> getBeneficiarios() {
		return beneficiarios;
	}
	
	public void addBeneficiario(BeneficiarioTO beneficiarioTO){
		beneficiarios.add(beneficiarioTO);
	}
	 
	
}
