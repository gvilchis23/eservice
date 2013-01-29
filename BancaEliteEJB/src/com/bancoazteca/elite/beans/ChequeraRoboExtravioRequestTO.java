package com.bancoazteca.elite.beans;

import java.io.Serializable;

import com.bancoazteca.elite.ejb.chequera.util.ChequesEnum;

public class ChequeraRoboExtravioRequestTO implements Serializable {

	private static final long serialVersionUID = 62362270153647560L;
	
	private String user;
	private String fechaSolicitud;
	private String numeroCuenta;
	private String idChequera;
	private String motivoBloqueoRobo;
	private String fechaRobo;
	private String seleccionOperacion;
	private String numeroCheque;
	private String numeroChequeInicial;
	private String numeroChequeFinal;
	private String confirmacionBloqueo;
	private String tipoOperacionCheque;
	private String tipoOperacionChequera;
	private ChequesEnum.TipoSolicitudEnum tipoOperacion;
	private ChequesEnum.TipoReporteRoboEnum tipoReporteCheque;
	private String statusChequera;
	
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getIdChequera() {
		return idChequera;
	}
	public void setIdChequera(String idChequera) {
		this.idChequera = idChequera;
	}
	public String getMotivoBloqueoRobo() {
		return motivoBloqueoRobo;
	}
	public void setMotivoBloqueoRobo(String motivoBloqueoRobo) {
		this.motivoBloqueoRobo = motivoBloqueoRobo;
	}
	public String getFechaRobo() {
		return fechaRobo;
	}
	public void setFechaRobo(String fechaRobo) {
		this.fechaRobo = fechaRobo;
	}
	public String getSeleccionOperacion() {
		return seleccionOperacion;
	}
	public void setSeleccionOperacion(String seleccionOperacion) {
		this.seleccionOperacion = seleccionOperacion;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public String getNumeroChequeInicial() {
		return numeroChequeInicial;
	}
	public void setNumeroChequeInicial(String numeroChequeInicial) {
		this.numeroChequeInicial = numeroChequeInicial;
	}
	public String getNumeroChequeFinal() {
		return numeroChequeFinal;
	}
	public void setNumeroChequeFinal(String numeroChequeFinal) {
		this.numeroChequeFinal = numeroChequeFinal;
	}
	public String getConfirmacionBloqueo() {
		return confirmacionBloqueo;
	}
	public void setConfirmacionBloqueo(String confirmacionBloqueo) {
		this.confirmacionBloqueo = confirmacionBloqueo;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTipoOperacionCheque() {
		return tipoOperacionCheque;
	}
	public void setTipoOperacionCheque(String tipoOperacionCheque) {
		this.tipoOperacionCheque = tipoOperacionCheque;
	}
	public String getTipoOperacionChequera() {
		return tipoOperacionChequera;
	}
	public void setTipoOperacionChequera(String tipoOperacionChequera) {
		this.tipoOperacionChequera = tipoOperacionChequera;
	}
	public ChequesEnum.TipoSolicitudEnum getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(ChequesEnum.TipoSolicitudEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getStatusChequera() {
		return statusChequera;
	}
	public void setStatusChequera(String statusChequera) {
		this.statusChequera = statusChequera;
	}
	public ChequesEnum.TipoReporteRoboEnum getTipoReporteCheque() {
		return tipoReporteCheque;
	}
	public void setTipoReporteCheque(ChequesEnum.TipoReporteRoboEnum tipoReporteCheque) {
		this.tipoReporteCheque = tipoReporteCheque;
	}
	
	
}
