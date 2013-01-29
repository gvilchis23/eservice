package com.bancoazteca.eservice.command.frecuentes.transferencias;

import com.bancoazteca.eservice.command.base.FormBean;

public class EliminarFrecuentesTransferenciasForm  extends FormBean{
	
	private String tipo_transferencia;
	private String cuenta;
	
	
	public String getTipo_transferencia() {
		return tipo_transferencia;
	}
	public void setTipo_transferencia(String tipo_transferencia) {
		this.tipo_transferencia = tipo_transferencia;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	
	
}
