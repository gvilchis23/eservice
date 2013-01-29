package com.bancoazteca.elite.beans;

import java.io.Serializable;
import java.util.Collection;

public class BloqueoFirmaResponseTO implements Serializable {

	private static final long serialVersionUID = -1228235454748841125L;
	
	private Collection<ListaTokenVO> listaTokenVO;
	private ListaTokenVO tokenVO;
	private String estatusToken;
	private String tokenEspera;
	

	public String getTokenEspera() {
		return tokenEspera;
	}

	public void setTokenEspera(String tokenEspera) {
		this.tokenEspera = tokenEspera;
	}

	public ListaTokenVO getTokenVO() {
		return tokenVO;
	}

	public void setTokenVO(ListaTokenVO tokenVO) {
		this.tokenVO = tokenVO;
	}

	public Collection<ListaTokenVO> getListaTokenVO() {
		return listaTokenVO;
	}

	public void setListaTokenVO(Collection<ListaTokenVO> listaTokenVO) {
		this.listaTokenVO = listaTokenVO;
	}

	public String getEstatusToken() {
		return estatusToken;
	}

	public void setEstatusToken(String estatusToken) {
		this.estatusToken = estatusToken;
	}
}
