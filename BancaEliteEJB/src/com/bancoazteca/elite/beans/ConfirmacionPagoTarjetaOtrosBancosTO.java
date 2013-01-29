package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class ConfirmacionPagoTarjetaOtrosBancosTO implements Serializable{

	private static final long serialVersionUID = 7286174432848215091L;

	private String tokencode;
	private String origen;
	private String importeCheck;
	private String clave;
	private String nexttoken;
	private String bancoEmisor;
	private String tipo;
	private String cuentaCargo;
	private String origenCheck;
	private String folio;
	private String source;
	private String newpin;
	private BigDecimal amount;
	private BigDecimal importeTotal;
	private String tarjeta;
	private BigDecimal comision;
	private String fechaAplicacion;
	private String servletWrapper;
	private Collection<CuentaTransaccionesTO> cuentas;
	private String tarjetaCheck;
	private String confnewpin;
	private String multipartRequestHandler;
	private BigDecimal ivaComision;
	private BigDecimal importe;
	
	
	public String getTokencode() {
		return tokencode;
	}
	public void setTokencode(String tokencode) {
		this.tokencode = tokencode;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getImporteCheck() {
		return importeCheck;
	}
	public void setImporteCheck(String importeCheck) {
		this.importeCheck = importeCheck;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNexttoken() {
		return nexttoken;
	}
	public void setNexttoken(String nexttoken) {
		this.nexttoken = nexttoken;
	}
	public String getBancoEmisor() {
		return bancoEmisor;
	}
	public void setBancoEmisor(String bancoEmisor) {
		this.bancoEmisor = bancoEmisor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCuentaCargo() {
		return cuentaCargo;
	}
	public void setCuentaCargo(String cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}
	public String getOrigenCheck() {
		return origenCheck;
	}
	public void setOrigenCheck(String origenCheck) {
		this.origenCheck = origenCheck;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getNewpin() {
		return newpin;
	}
	public void setNewpin(String newpin) {
		this.newpin = newpin;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public BigDecimal getComision() {
		return comision;
	}
	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getServletWrapper() {
		return servletWrapper;
	}
	public void setServletWrapper(String servletWrapper) {
		this.servletWrapper = servletWrapper;
	}
	public Collection<CuentaTransaccionesTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<CuentaTransaccionesTO> cuentas) {
		this.cuentas = cuentas;
	}
	public String getTarjetaCheck() {
		return tarjetaCheck;
	}
	public void setTarjetaCheck(String tarjetaCheck) {
		this.tarjetaCheck = tarjetaCheck;
	}
	public String getConfnewpin() {
		return confnewpin;
	}
	public void setConfnewpin(String confnewpin) {
		this.confnewpin = confnewpin;
	}
	public String getMultipartRequestHandler() {
		return multipartRequestHandler;
	}
	public void setMultipartRequestHandler(String multipartRequestHandler) {
		this.multipartRequestHandler = multipartRequestHandler;
	}
	public BigDecimal getIvaComision() {
		return ivaComision;
	}
	public void setIvaComision(BigDecimal ivaComision) {
		this.ivaComision = ivaComision;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	
}
