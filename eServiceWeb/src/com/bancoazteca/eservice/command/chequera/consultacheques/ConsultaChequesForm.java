package com.bancoazteca.eservice.command.chequera.consultacheques;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ConsultaChequesForm extends FormBean {

	private static final long serialVersionUID = -6443747532846141116L;

	private String tipo_solicitud;
	private String numero_cuenta;
	private String descripcion_chequera;
	private String estado_cheques;
	private String cheque_final;
	private String cheque_inicial;

	public String getCheque_final() {
		return cheque_final;
	}

	public void setCheque_final(String cheque_final) {
		this.cheque_final = cheque_final;
	}

	public String getCheque_inicial() {
		return cheque_inicial;
	}

	public void setCheque_inicial(String cheque_inicial) {
		this.cheque_inicial = cheque_inicial;
	}

	public String getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

	public String getEstado_cheques() {
		return estado_cheques;
	}

	public void setEstado_cheques(String estado_cheques) {
		this.estado_cheques = estado_cheques;
	}

	public String getDescripcion_chequera() {
		return descripcion_chequera;
	}

	public void setDescripcion_chequera(String descripcion_chequera) {
		this.descripcion_chequera = descripcion_chequera;
	}

	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();

		if (getComando().equalsIgnoreCase("solicitud")) {
			if(!Validator.isEmptyData(tipo_solicitud)){
				
				if(tipo_solicitud.equalsIgnoreCase("obten_cuentas") ){
				}else if(tipo_solicitud.equalsIgnoreCase("listado_chequeras")){
					if(Validator.isEmptyData(numero_cuenta)){
						errors.add("numero_cuenta",VALIDATOR_CONSULTA_CHEQUES_NUMERO_CUENTA_EMPTY);
					}else{
						if(!Validator.checkNumeric(numero_cuenta) || numero_cuenta.length() != 14){
							errors.add("numero_cuenta",VALIDATOR_CONSULTA_CHEQUES_NUMERO_CUENTA_DECIMAL);
						}
					}
				}else {
					errors.add("tipo_solicitud",VALIDATOR_CONSULTA_CHEQUES_TIPO_SOLICITUD_INCORRECT);
				}
				
			}else{
				errors.add("tipo_solicitud",VALIDATOR_CONSULTA_CHEQUES_TIPO_SOLICITUD_EMPTY);
			}
			
		}
		if (getComando().equalsIgnoreCase("validacion")) {
			if(	Validator.isEmptyData(descripcion_chequera)	|| 
				Validator.isEmptyData(cheque_final)	||
				Validator.isEmptyData(cheque_inicial)){
				errors.add("descripcion_chequera",VALIDATOR_CONSULTA_CHEQUES_DESCRIPCION_CHEQUERA_EMPTY);
			}
		}
		if (getComando().equalsIgnoreCase("ejecucion")) {
			if(Validator.isEmptyData(estado_cheques)){
				errors.add("estado_cheques",VALIDATOR_CONSULTA_CHEQUES_ESTADO_CHEQUES_EMPTY);
			}
			
		}
		return errors;
	}

	public String getTipo_solicitud() {
		return tipo_solicitud;
	}

	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}
}
