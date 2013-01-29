package com.bancoazteca.elite.beans;

import java.io.Serializable;


public class TraspasosPropiasResponsetTO implements Serializable{
	

	private static final long serialVersionUID = -8029243605119022603L;
	
	private String folio;
	public String message;
	private String valorUnidad;
	private String referencia;
	private String restriccion;
	private ConfirmarTraspasoPropioTO traspasoPropioTO;
	private String pasoBloqueado;
	private String totalUnidades;
	private boolean eBankingCom;
	
	
	public boolean isEBankingCom() {
		return eBankingCom;
	}
	public void setEBankingCom(boolean bankingCom) {
		eBankingCom = bankingCom;
	}
	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	/*public Collection<LabelValueBeanTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Collection<LabelValueBeanTO> cuentas) {
		this.cuentas = cuentas;
	}*/
	public String getValorUnidad() {
		return valorUnidad;
	}
	public void setValorUnidad(String valorUnidad) {
		this.valorUnidad = valorUnidad;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getRestriccion() {
		return restriccion;
	}
	public void setRestriccion(String restriccion) {
		this.restriccion = restriccion;
	}
	public ConfirmarTraspasoPropioTO getTraspasoPropioTO() {
		return traspasoPropioTO;
	}
	public void setTraspasoPropioTO(ConfirmarTraspasoPropioTO traspasoPropioTO) {
		this.traspasoPropioTO = traspasoPropioTO;
	}
	public String getPasoBloqueado() {
		return pasoBloqueado;
	}
	public void setPasoBloqueado(String pasoBloqueado) {
		this.pasoBloqueado = pasoBloqueado;
	}
	public String getTotalUnidades() {
		return totalUnidades;
	}
	public void setTotalUnidades(String totalUnidades) {
		this.totalUnidades = totalUnidades;
	}
	 	

}
