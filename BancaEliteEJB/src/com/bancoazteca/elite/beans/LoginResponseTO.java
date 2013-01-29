package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class LoginResponseTO implements Serializable{
	

	private static final long serialVersionUID = -3774734600467189804L;
	
	private ClienteTO clienteTO;

	public ClienteTO getClienteTO() {
		return clienteTO;
	}

	public void setClienteTO(ClienteTO clienteTO) {
		this.clienteTO = clienteTO;
	}

}
