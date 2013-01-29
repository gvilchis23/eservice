package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class ConsultaTransferenciasResponseTO implements Serializable{

	private static final long serialVersionUID = -2222335681L;
	private String label;
	private String value;
	private Collection<ConsultaTransferenciasResponseTO> cuentas;
	private String nombreCompleto;
	private Collection<ConsultaTransferenciasResponseTO> transferencias;
	private Collection<ConsultaTransferenciasResponseTO> traspasos;
	private Collection<ConsultaTransferenciasResponseTO> transferenciasProgramadas;
	private String beneficiario;
	private String importeSinFormato;
	private String numeroReferencia;
	private String estadoTransferencia;
	private String cuentaDestino;
	private String textoDestino;
	private String fechaRetencion;
	private String textoOrigen;
	private String nombreDestino;
	private String cuentaOrigen;
	private String idScheduled;
	private String fechaAplicacion;
	private String fechaProgramacion;
	private String referenciaPago;
	private String index;
	private String status;
	
	private String nombreOrdenante;
	private String cuentaOrdenante;
	private String nombreBeneficiario;
	private String nombreBancoDeposito;
	private String tipoCtaBeneficiario;
	private String numCtaBeneficiario;
	private String rfcBeneficiario;
	private String porcentajeIVA;
	private String numeroSecuencia;
	private String codigoDivisa;
	private String importeOperacion;
	private String importeComision;
	private String importeIVA;
	private String acumuladorTotales;
	private String fechaValor;
	private String destxt;
	private String descripcionEstatus;
	private String horaenv;
	private String horaAutenticacion;
	private String descripcionDevolucion;
	private String fechaDevolucion;
	private String cuenta;
	private String cuentaFormateada;
	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getCuentaFormateada() {
		return cuentaFormateada;
	}
	public void setCuentaFormateada(String cuentaFormateada) {
		this.cuentaFormateada = cuentaFormateada;
	}
	public String getNombreOrdenante() {
		return nombreOrdenante;
	}
	public void setNombreOrdenante(String nombreOrdenante) {
		this.nombreOrdenante = nombreOrdenante;
	}
	public String getCuentaOrdenante() {
		return cuentaOrdenante;
	}
	public void setCuentaOrdenante(String cuentaOrdenante) {
		this.cuentaOrdenante = cuentaOrdenante;
	}
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}
	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}
	public String getNombreBancoDeposito() {
		return nombreBancoDeposito;
	}
	public void setNombreBancoDeposito(String nombreBancoDeposito) {
		this.nombreBancoDeposito = nombreBancoDeposito;
	}
	public String getTipoCtaBeneficiario() {
		return tipoCtaBeneficiario;
	}
	public void setTipoCtaBeneficiario(String tipoCtaBeneficiario) {
		this.tipoCtaBeneficiario = tipoCtaBeneficiario;
	}
	public String getNumCtaBeneficiario() {
		return numCtaBeneficiario;
	}
	public void setNumCtaBeneficiario(String numCtaBeneficiario) {
		this.numCtaBeneficiario = numCtaBeneficiario;
	}
	public String getRfcBeneficiario() {
		return rfcBeneficiario;
	}
	public void setRfcBeneficiario(String rfcBeneficiario) {
		this.rfcBeneficiario = rfcBeneficiario;
	}
	public String getImporteIVA() {
		return importeIVA;
	}
	public void setImporteIVA(String importeIVA) {
		this.importeIVA = importeIVA;
	}
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}
	public String getCodigoDivisa() {
		return codigoDivisa;
	}
	public void setCodigoDivisa(String codigoDivisa) {
		this.codigoDivisa = codigoDivisa;
	}
	public String getImporteOperacion() {
		return importeOperacion;
	}
	public void setImporteOperacion(String importeOperacion) {
		this.importeOperacion = importeOperacion;
	}
	public String getImporteComision() {
		return importeComision;
	}
	public void setImporteComision(String importeComision) {
		this.importeComision = importeComision;
	}
	public String getPorcentajeIVA() {
		return porcentajeIVA;
	}
	public void setPorcentajeIVA(String porcentajeIVA) {
		this.porcentajeIVA = porcentajeIVA;
	}
	public String getAcumuladorTotales() {
		return acumuladorTotales;
	}
	public void setAcumuladorTotales(String acumuladorTotales) {
		this.acumuladorTotales = acumuladorTotales;
	}
	public String getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(String fechaValor) {
		this.fechaValor = fechaValor;
	}
	public String getDestxt() {
		return destxt;
	}
	public void setDestxt(String destxt) {
		this.destxt = destxt;
	}
	public String getHoraenv() {
		return horaenv;
	}
	public void setHoraenv(String horaenv) {
		this.horaenv = horaenv;
	}
	public String getHoraAutenticacion() {
		return horaAutenticacion;
	}
	public void setHoraAutenticacion(String horaAutenticacion) {
		this.horaAutenticacion = horaAutenticacion;
	}
	public String getDescripcionDevolucion() {
		return descripcionDevolucion;
	}
	public void setDescripcionDevolucion(String descripcionDevolucion) {
		this.descripcionDevolucion = descripcionDevolucion;
	}
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getIdScheduled() {
		return idScheduled;
	}
	public void setIdScheduled(String idScheduled) {
		this.idScheduled = idScheduled;
	}
	public String getFechaAplicacion() {
		return fechaAplicacion;
	}
	public void setFechaAplicacion(String fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	public String getFechaProgramacion() {
		return fechaProgramacion;
	}
	public void setFechaProgramacion(String fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public String getReferenciaPago() {
		return referenciaPago;
	}
	public void setReferenciaPago(String referenciaPago) {
		this.referenciaPago = referenciaPago;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTextoDestino() {
		return textoDestino;
	}
	public void setTextoDestino(String textoDestino) {
		this.textoDestino = textoDestino;
	}
	public String getFechaRetencion() {
		return fechaRetencion;
	}
	public void setFechaRetencion(String fechaRetencion) {
		this.fechaRetencion = fechaRetencion;
	}
	public String getTextoOrigen() {
		return textoOrigen;
	}
	public void setTextoOrigen(String textoOrigen) {
		this.textoOrigen = textoOrigen;
	}
	public String getNombreDestino() {
		return nombreDestino;
	}
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public Collection<ConsultaTransferenciasResponseTO> getTransferencias() {
		return transferencias;
	}
	public void setTransferencias(
			Collection<ConsultaTransferenciasResponseTO> transferencias) {
		this.transferencias = transferencias;
	}
	public Collection<ConsultaTransferenciasResponseTO> getTraspasos() {
		return traspasos;
	}
	public void setTraspasos(Collection<ConsultaTransferenciasResponseTO> traspasos) {
		this.traspasos = traspasos;
	}
	public Collection<ConsultaTransferenciasResponseTO> getTransferenciasProgramadas() {
		return transferenciasProgramadas;
	}
	public void setTransferenciasProgramadas(
			Collection<ConsultaTransferenciasResponseTO> transferenciasProgramadas) {
		this.transferenciasProgramadas = transferenciasProgramadas;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getImporteSinFormato() {
		return importeSinFormato;
	}
	public void setImporteSinFormato(String importeSinFormato) {
		this.importeSinFormato = importeSinFormato;
	}
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public String getEstadoTransferencia() {
		return estadoTransferencia;
	}
	public void setEstadoTransferencia(String estadoTransferencia) {
		this.estadoTransferencia = estadoTransferencia;
	}
	public String getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public String getDescripcionEstatus() {
		return descripcionEstatus;
	}
	public void setDescripcionEstatus(String descripcionEstatus) {
		this.descripcionEstatus = descripcionEstatus;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Collection<ConsultaTransferenciasResponseTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<ConsultaTransferenciasResponseTO> cuentas) {
		this.cuentas = cuentas;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}
