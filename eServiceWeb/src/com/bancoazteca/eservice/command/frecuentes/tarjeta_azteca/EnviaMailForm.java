package com.bancoazteca.eservice.command.frecuentes.tarjeta_azteca;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class EnviaMailForm extends FormBean{

		private String email;
		private String mensaje;
				   					   	

		

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public MessageErrors validate(){		
			MessageErrors error = new MessageErrors();	
			
	
		   if (getComando().equalsIgnoreCase("EJECUCION")) {

			if (Validator.isEmptyData(email)){
				error.add("email", VALIDATOR_ENVIO_MAIL_CORREO_ELECTRONICO);
				
			}
													
			if (Validator.isEmptyData(mensaje)) {
				error.add("mensaje",VALIDATOR_ENVIO_MAIL_MENSAJE);
			}

			
		  }		   
			return error;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
}
