package com.bancoazteca.elite.beans;

import java.util.Collection;

public class ConsultaChequesRequestTO {
	
	private String user;
	private Collection<ConnectorDataTO> parametros;
	private String cuenta;
	private String indiceChequera;
	private String indiceTraer;
	private String opcionConsulta;

	public String getIndiceTraer() {
		return indiceTraer;
	}

	public void setIndiceTraer(String indiceTraer) {
		this.indiceTraer = indiceTraer;
	}

	public String getOpcionConsulta() {
		return opcionConsulta;
	}

	public void setOpcionConsulta(String opcionConsulta) {
		this.opcionConsulta = opcionConsulta;
	}

	public String getIndiceChequera() {
		return indiceChequera;
	}

	public void setIndiceChequera(String indiceChequera) {
		this.indiceChequera = indiceChequera;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Collection<ConnectorDataTO> getParametros() {
		return parametros;
	}

	public void setParametros(Collection<ConnectorDataTO> parametros) {
		this.parametros = parametros;
	}
}
