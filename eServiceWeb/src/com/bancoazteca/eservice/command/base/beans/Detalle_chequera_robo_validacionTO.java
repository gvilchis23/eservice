package com.bancoazteca.eservice.command.base.beans;

import java.io.Serializable;


public class Detalle_chequera_robo_validacionTO extends Detalle_chequera_roboTO implements Serializable{
	
	private static final long serialVersionUID = 8271603564709159484L;
	private String cheque_bloquear;
	private String rango_cheques_bloquear;

	public String getCheque_bloquear() {
		return cheque_bloquear;
	}

	public void setCheque_bloquear(String cheque_bloquear) {
		this.cheque_bloquear = cheque_bloquear;
	}

	public String getRango_cheques_bloquear() {
		return rango_cheques_bloquear;
	}

	public void setRango_cheques_bloquear(String rango_cheques_bloquear) {
		this.rango_cheques_bloquear = rango_cheques_bloquear;
	}
	
}
