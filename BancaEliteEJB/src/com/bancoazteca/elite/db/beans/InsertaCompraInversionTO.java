package com.bancoazteca.elite.db.beans;


public class InsertaCompraInversionTO {

	private String folioOperacion=" ";
	private String MontoInversion=" ";
	private String plazo=" ";
	private String tasa=" ";
	private String idTransaccion=" ";
	private String idTransaccionAlnova=" ";
	private String numeroContrato=" ";
	private String usuarioModifico= " ";
	public String getFolioOperacion() {
		return folioOperacion;
	}
	public void setFolioOperacion(String folioOperacion) {
		this.folioOperacion = folioOperacion;
	}
	public String getMontoInversion() {
		return MontoInversion;
	}
	public void setMontoInversion(String montoInversion) {
		MontoInversion = montoInversion;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getIdTransaccionAlnova() {
		return idTransaccionAlnova;
	}
	public void setIdTransaccionAlnova(String idTransaccionAlnova) {
		this.idTransaccionAlnova = idTransaccionAlnova;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getUsuarioModifico() {
		return usuarioModifico;
	}
	public void setUsuarioModifico(String usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}
}
