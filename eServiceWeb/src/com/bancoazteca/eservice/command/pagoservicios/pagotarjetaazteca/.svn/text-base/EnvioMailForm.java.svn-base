package com.bancoazteca.eservice.command.pagoservicios.pagotarjetaazteca;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EnvioMailForm extends FormBean{

		private String correo_electronico_destino;
		private String mensaje;
		private String tipo_envio;			   					   	

		public String getCorreo_electronico_destino() {
			return correo_electronico_destino;
		}

		public void setCorreo_electronico_destino(String correo_electronico_destino) {
			this.correo_electronico_destino = correo_electronico_destino;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public String getTipo_envio() {
			return tipo_envio;
		}

		public void setTipo_envio(String tipo_envio) {
			this.tipo_envio = tipo_envio;
		}

		public MessageErrors validate(){		
			MessageErrors error = new MessageErrors();	
			
	
		   if (getComando().equalsIgnoreCase("EJECUCION")) {

			if (Validator.isEmptyData(correo_electronico_destino)){
				error.add("correo_electronico_destino", VALIDATOR_ENVIO_MAIL_CORREO_ELECTRONICO);
				
			}
													
			if (Validator.isEmptyData(mensaje)) {
				error.add("mensaje",VALIDATOR_ENVIO_MAIL_MENSAJE);
			}

			if (Validator.isEmptyData(tipo_envio)) {
				error.add("tipo_envio",VALIDATOR_ENVIO_MAIL_TIPO_ENVIO);
			}
		  }		   
			return error;
		}
		
}
