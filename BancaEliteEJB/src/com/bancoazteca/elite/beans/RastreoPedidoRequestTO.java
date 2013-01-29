package com.bancoazteca.elite.beans;

import java.io.Serializable;

public class RastreoPedidoRequestTO implements Serializable {

	private static final long serialVersionUID = -1387958903012190276L;

	private String user;
	private String numeroPedido;

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numero_pedido) {
		this.numeroPedido = numero_pedido;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
