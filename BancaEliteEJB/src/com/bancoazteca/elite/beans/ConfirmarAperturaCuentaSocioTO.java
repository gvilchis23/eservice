package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class ConfirmarAperturaCuentaSocioTO implements Serializable{

	private static final long serialVersionUID = 502387282050903362L;

	private String tokencode;
	private BigDecimal monto;
	private String nombreBeneficiario1;
	private String nombreBeneficiario2;
	private String nombreBeneficiario3;
	private String nombreBeneficiario4;
	private String nexttoken;
	private String nip;
	private String tipoOperacion;
	private String nss;
	private String cuentaCargo;
	private String folio;
	private String referencia;
	private Collection<CuentaLoTO> cuentasLO;
	private String disponible;
	private String cta_Nueva;
	private String cuentas;
	private String index;
	private String confnewpin;
	private String fechaOperacion;
	private String porcentaje1;
	private String porcentaje2;
	private String porcentaje3;
	private String porcentaje4;
	private String apBeneficiario1;
	private String apBeneficiario2;
	private String apBeneficiario3;
	private String apBeneficiario4;
	private String nipc;
	private String clave;
	private String titular;
	private String beneficiario1;
	private String beneficiario4;
	private String beneficiario3;
	private String beneficiario2;
	private String newpin;
	private String amBeneficiario2;
	private String amBeneficiario3;
	private String tasaBrutaAnual;
	private String amBeneficiario4;
	private String movCtaAbono;
	private String numMovimiento;
	private String amBeneficiario1;
	private String servletWrapper;
	private String suma;
	private String multipartRequestHandler;
	private String cuentaOrigen;
	
	
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getNombreBeneficiario1() {
		return nombreBeneficiario1;
	}
	public void setNombreBeneficiario1(String nombreBeneficiario1) {
		this.nombreBeneficiario1 = nombreBeneficiario1;
	}
	public String getNombreBeneficiario2() {
		return nombreBeneficiario2;
	}
	public void setNombreBeneficiario2(String nombreBeneficiario2) {
		this.nombreBeneficiario2 = nombreBeneficiario2;
	}
	public String getNombreBeneficiario3() {
		return nombreBeneficiario3;
	}
	public void setNombreBeneficiario3(String nombreBeneficiario3) {
		this.nombreBeneficiario3 = nombreBeneficiario3;
	}
	public String getNombreBeneficiario4() {
		return nombreBeneficiario4;
	}
	public void setNombreBeneficiario4(String nombreBeneficiario4) {
		this.nombreBeneficiario4 = nombreBeneficiario4;
	}
	public String getNexttoken() {
		return nexttoken;
	}
	public void setNexttoken(String nexttoken) {
		this.nexttoken = nexttoken;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getNss() {
		return nss;
	}
	public void setNss(String nss) {
		this.nss = nss;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	public String getCta_Nueva() {
		return cta_Nueva;
	}
	public void setCta_Nueva(String cta_Nueva) {
		this.cta_Nueva = cta_Nueva;
	}
	public Collection<CuentaLoTO> getCuentasLO() {
		return cuentasLO;
	}
	public void setCuentasLO(Collection<CuentaLoTO> cuentasLO) {
		this.cuentasLO = cuentasLO;
	}
	public String getCuentas() {
		return cuentas;
	}
	public void setCuentas(String cuentas) {
		this.cuentas = cuentas;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getConfnewpin() {
		return confnewpin;
	}
	public void setConfnewpin(String confnewpin) {
		this.confnewpin = confnewpin;
	}
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getPorcentaje1() {
		return porcentaje1;
	}
	public void setPorcentaje1(String porcentaje1) {
		this.porcentaje1 = porcentaje1;
	}
	public String getPorcentaje2() {
		return porcentaje2;
	}
	public void setPorcentaje2(String porcentaje2) {
		this.porcentaje2 = porcentaje2;
	}
	public String getPorcentaje3() {
		return porcentaje3;
	}
	public void setPorcentaje3(String porcentaje3) {
		this.porcentaje3 = porcentaje3;
	}
	public String getPorcentaje4() {
		return porcentaje4;
	}
	public void setPorcentaje4(String porcentaje4) {
		this.porcentaje4 = porcentaje4;
	}
	public String getApBeneficiario1() {
		return apBeneficiario1;
	}
	public void setApBeneficiario1(String apBeneficiario1) {
		this.apBeneficiario1 = apBeneficiario1;
	}
	public String getApBeneficiario2() {
		return apBeneficiario2;
	}
	public void setApBeneficiario2(String apBeneficiario2) {
		this.apBeneficiario2 = apBeneficiario2;
	}
	public String getApBeneficiario3() {
		return apBeneficiario3;
	}
	public void setApBeneficiario3(String apBeneficiario3) {
		this.apBeneficiario3 = apBeneficiario3;
	}
	public String getApBeneficiario4() {
		return apBeneficiario4;
	}
	public void setApBeneficiario4(String apBeneficiario4) {
		this.apBeneficiario4 = apBeneficiario4;
	}
	public String getNipc() {
		return nipc;
	}
	public void setNipc(String nipc) {
		this.nipc = nipc;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getBeneficiario1() {
		return beneficiario1;
	}
	public void setBeneficiario1(String beneficiario1) {
		this.beneficiario1 = beneficiario1;
	}
	public String getBeneficiario4() {
		return beneficiario4;
	}
	public void setBeneficiario4(String beneficiario4) {
		this.beneficiario4 = beneficiario4;
	}
	public String getBeneficiario3() {
		return beneficiario3;
	}
	public void setBeneficiario3(String beneficiario3) {
		this.beneficiario3 = beneficiario3;
	}
	public String getBeneficiario2() {
		return beneficiario2;
	}
	public void setBeneficiario2(String beneficiario2) {
		this.beneficiario2 = beneficiario2;
	}
	public String getNewpin() {
		return newpin;
	}
	public void setNewpin(String newpin) {
		this.newpin = newpin;
	}
	public String getAmBeneficiario2() {
		return amBeneficiario2;
	}
	public void setAmBeneficiario2(String amBeneficiario2) {
		this.amBeneficiario2 = amBeneficiario2;
	}
	public String getAmBeneficiario3() {
		return amBeneficiario3;
	}
	public void setAmBeneficiario3(String amBeneficiario3) {
		this.amBeneficiario3 = amBeneficiario3;
	}
	public String getTasaBrutaAnual() {
		return tasaBrutaAnual;
	}
	public void setTasaBrutaAnual(String tasaBrutaAnual) {
		this.tasaBrutaAnual = tasaBrutaAnual;
	}
	public String getAmBeneficiario4() {
		return amBeneficiario4;
	}
	public void setAmBeneficiario4(String amBeneficiario4) {
		this.amBeneficiario4 = amBeneficiario4;
	}
	public String getMovCtaAbono() {
		return movCtaAbono;
	}
	public void setMovCtaAbono(String movCtaAbono) {
		this.movCtaAbono = movCtaAbono;
	}
	public String getNumMovimiento() {
		return numMovimiento;
	}
	public void setNumMovimiento(String numMovimiento) {
		this.numMovimiento = numMovimiento;
	}
	public String getAmBeneficiario1() {
		return amBeneficiario1;
	}
	public void setAmBeneficiario1(String amBeneficiario1) {
		this.amBeneficiario1 = amBeneficiario1;
	}
	public String getServletWrapper() {
		return servletWrapper;
	}
	public void setServletWrapper(String servletWrapper) {
		this.servletWrapper = servletWrapper;
	}
	public String getSuma() {
		return suma;
	}
	public void setSuma(String suma) {
		this.suma = suma;
	}
	public String getMultipartRequestHandler() {
		return multipartRequestHandler;
	}
	public void setMultipartRequestHandler(String multipartRequestHandler) {
		this.multipartRequestHandler = multipartRequestHandler;
	}
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	
	
}
