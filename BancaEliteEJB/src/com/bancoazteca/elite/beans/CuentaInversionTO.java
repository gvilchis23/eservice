package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class CuentaInversionTO implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<MovimientoInversionTO> movimientos;
	private String diasFaltantes;
	private String montoInvertido;
	private String plazoInversion;
	private String tipo;
	private String clabe;
	private String interesNetoGanar;
	private String disponible;
	private String comisionServChequera;
	private String instruccion;
	private String unidadesComp;
	private String fechaVencimiento;
	private String numeroCuenta;
	private String diasTranscurridos;
	private String fechaInicioInversion;
	private String interesGeneradoSAFecha;
	private String tasaBruta;
	private String comisionLibCheques;
	private String retenido;
	private String tasaNeta;
	private String interesBrutoGanar;
	private String balance;
	private String tipoCambioVenta;
	private String totalNPesos;
	private String indice;
	private String apertura;
	private String nombreMoneda;
	private String numero;
	private String ganare;
	private String subproducto;
	private String autorizados;
	private String chequeras;
	private String descripcion;
	private String cotitulares;
	private String totalNMonedas;
	private String corte;
	private String inversion;
	private String chequera;
	private String producto;
	private String tipoCambioCompra;
	private String contrato;
	private String moneda;
	private String inversionAzteca;
	private String sucursal;
	private String totalAbonos;
	private String totalCargos;
	
	public Collection<MovimientoInversionTO> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(Collection<MovimientoInversionTO> movimientos) {
		this.movimientos = movimientos;
	}
	public String getDiasFaltantes() {
		return diasFaltantes;
	}
	public void setDiasFaltantes(String diasFaltantes) {
		this.diasFaltantes = diasFaltantes;
	}
	public String getMontoInvertido() {
		return montoInvertido;
	}
	public void setMontoInvertido(String montoInvertido) {
		this.montoInvertido = montoInvertido;
	}
	public String getPlazoInversion() {
		return plazoInversion;
	}
	public void setPlazoInversion(String plazoInversion) {
		this.plazoInversion = plazoInversion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getClabe() {
		return clabe;
	}
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	public String getInteresNetoGanar() {
		return interesNetoGanar;
	}
	public void setInteresNetoGanar(String interesNetoGanar) {
		this.interesNetoGanar = interesNetoGanar;
	}
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	public String getComisionServChequera() {
		return comisionServChequera;
	}
	public void setComisionServChequera(String comisionServChequera) {
		this.comisionServChequera = comisionServChequera;
	}
	public String getInstruccion() {
		return instruccion;
	}
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}
	public String getUnidadesComp() {
		return unidadesComp;
	}
	public void setUnidadesComp(String unidadesComp) {
		this.unidadesComp = unidadesComp;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getDiasTranscurridos() {
		return diasTranscurridos;
	}
	public void setDiasTranscurridos(String diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}
	public String getFechaInicioInversion() {
		return fechaInicioInversion;
	}
	public void setFechaInicioInversion(String fechaInicioInversion) {
		this.fechaInicioInversion = fechaInicioInversion;
	}
	public String getInteresGeneradoSAFecha() {
		return interesGeneradoSAFecha;
	}
	public void setInteresGeneradoSAFecha(String interesGeneradoSAFecha) {
		this.interesGeneradoSAFecha = interesGeneradoSAFecha;
	}
	public String getTasaBruta() {
		return tasaBruta;
	}
	public void setTasaBruta(String tasaBruta) {
		this.tasaBruta = tasaBruta;
	}
	public String getComisionLibCheques() {
		return comisionLibCheques;
	}
	public void setComisionLibCheques(String comisionLibCheques) {
		this.comisionLibCheques = comisionLibCheques;
	}
	public String getRetenido() {
		return retenido;
	}
	public void setRetenido(String retenido) {
		this.retenido = retenido;
	}
	public String getTasaNeta() {
		return tasaNeta;
	}
	public void setTasaNeta(String tasaNeta) {
		this.tasaNeta = tasaNeta;
	}
	public String getInteresBrutoGanar() {
		return interesBrutoGanar;
	}
	public void setInteresBrutoGanar(String interesBrutoGanar) {
		this.interesBrutoGanar = interesBrutoGanar;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getTipoCambioVenta() {
		return tipoCambioVenta;
	}
	public void setTipoCambioVenta(String tipoCambioVenta) {
		this.tipoCambioVenta = tipoCambioVenta;
	}
	public String getTotalNPesos() {
		return totalNPesos;
	}
	public void setTotalNPesos(String totalNPesos) {
		this.totalNPesos = totalNPesos;
	}
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	public String getApertura() {
		return apertura;
	}
	public void setApertura(String apertura) {
		this.apertura = apertura;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getGanare() {
		return ganare;
	}
	public void setGanare(String ganare) {
		this.ganare = ganare;
	}
	public String getSubproducto() {
		return subproducto;
	}
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}
	public String getAutorizados() {
		return autorizados;
	}
	public void setAutorizados(String autorizados) {
		this.autorizados = autorizados;
	}
	public String getChequeras() {
		return chequeras;
	}
	public void setChequeras(String chequeras) {
		this.chequeras = chequeras;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCotitulares() {
		return cotitulares;
	}
	public void setCotitulares(String cotitulares) {
		this.cotitulares = cotitulares;
	}
	public String getTotalNMonedas() {
		return totalNMonedas;
	}
	public void setTotalNMonedas(String totalNMonedas) {
		this.totalNMonedas = totalNMonedas;
	}
	public String getCorte() {
		return corte;
	}
	public void setCorte(String corte) {
		this.corte = corte;
	}
	public String getInversion() {
		return inversion;
	}
	public void setInversion(String inversion) {
		this.inversion = inversion;
	}
	public String getChequera() {
		return chequera;
	}
	public void setChequera(String chequera) {
		this.chequera = chequera;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getTipoCambioCompra() {
		return tipoCambioCompra;
	}
	public void setTipoCambioCompra(String tipoCambioCompra) {
		this.tipoCambioCompra = tipoCambioCompra;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getInversionAzteca() {
		return inversionAzteca;
	}
	public void setInversionAzteca(String inversionAzteca) {
		this.inversionAzteca = inversionAzteca;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getTotalAbonos() {
		return totalAbonos;
	}
	public void setTotalAbonos(String totalAbonos) {
		this.totalAbonos = totalAbonos;
	}
	public String getTotalCargos() {
		return totalCargos;
	}
	public void setTotalCargos(String totalCargos) {
		this.totalCargos = totalCargos;
	}

	
}