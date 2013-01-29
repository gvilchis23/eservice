package com.bancoazteca.eservice.command.dispositivos.rastreopedido;

import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class RastreoPedidoForm extends FormBean{

	private String numero_pedido;	
	
	public MessageErrors validate(){
		MessageErrors errores= new MessageErrors();
		
		if(getComando().equalsIgnoreCase("EJECUCION")){			
			if(Validator.isEmptyData(numero_pedido)){
				errores.add("numero_pedido", VALIDA_NUMERO_PEDIDO);
			}else{
				if(!Validator.checkNumeric(numero_pedido)){
					errores.add("numero_pedido", VALIDA_NUMERO_PEDIDO_DECIMAL);
				}
			}
		}
		return  errores;
	}

	public String getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(String numero_pedido) {
		this.numero_pedido = numero_pedido;
	}
}
