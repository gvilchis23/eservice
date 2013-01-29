package com.bancoazteca.eservice.command.frecuentes.transferencias;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ConsultaFrecuentesTransferenciasForm extends FormBean{

	private String tipo_transferencia;
	
	@Override
	public MessageErrors validate() {
		MessageErrors errors = new MessageErrors();
		
		if (Validator.isEmptyData(tipo_transferencia)){
			errors.add("tipo_transferencia", CONSULTA_FRECUENTES_TRANSFERENCIAS_TIPO_NULO);

		}else if(!(tipo_transferencia.equalsIgnoreCase("terceros")||tipo_transferencia.equalsIgnoreCase("spei")||tipo_transferencia.equalsIgnoreCase("tef")||tipo_transferencia.equalsIgnoreCase("internacional")
				||tipo_transferencia.equalsIgnoreCase("todos"))){

			errors.add("tipo_transferencia", CONSULTA_FRECUENTES_TRANSFERENCIAS_TIPO_INVALIDO);
		}
		

		return errors;
	}

	public String getTipo_transferencia() {
		return tipo_transferencia;
	}

	public void setTipo_transferencia(String tipo_frecuente) {
		this.tipo_transferencia = tipo_frecuente;
	}
	
}
