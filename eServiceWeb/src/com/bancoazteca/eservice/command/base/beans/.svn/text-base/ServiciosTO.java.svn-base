package com.bancoazteca.eservice.command.base.beans;

public class ServiciosTO {
	private String concepto_pago;

	public String getConcepto_pago() {
		return concepto_pago;
	}

	public void setConcepto_pago(String concepto_pago) {
		if(concepto_pago!=null && concepto_pago.matches(".*IUSACELL SECSA")){
			concepto_pago="Pago del Servicio a IUSACELL";
		}
		this.concepto_pago = concepto_pago;
	}
}
