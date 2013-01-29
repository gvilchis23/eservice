package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class SincronizacionFirmaResponseTO implements Serializable {

	private static final long serialVersionUID = -1228235454748841125L;
	private Collection<ListaTokenVO> listaTokenVO;
	private ListaTokenVO tokenVO;
	private String numeroSerie;
	private String preToken;
	private String tokenEspera;
	
	public String getTokenEspera() {
		return tokenEspera;
	}
	public void setTokenEspera(String tokenEspera) {
		this.tokenEspera = tokenEspera;
	}
	public Collection<ListaTokenVO> getListaTokenVO() {
		return listaTokenVO;
	}
	public void setListaTokenVO(Collection<ListaTokenVO> listaTokenVO) {
		this.listaTokenVO = listaTokenVO;
	}
	public ListaTokenVO getTokenVO() {
		return tokenVO;
	}
	public void setTokenVO(ListaTokenVO tokenVO) {
		this.tokenVO = tokenVO;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getPreToken() {
		return preToken;
	}
	public void setPreToken(String preToken) {
		this.preToken = preToken;
	}
}
