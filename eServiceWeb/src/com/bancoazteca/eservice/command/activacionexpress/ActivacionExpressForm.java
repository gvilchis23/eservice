package com.bancoazteca.eservice.command.activacionexpress;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.FormBean;
import com.bancoazteca.eservice.validator.MessageErrors;

public class ActivacionExpressForm extends FormBean{

		
		
		private Cipher idaplicacion;
		private String numero_tarjeta;
		private String operacion;
		private String huella_seguridad;
		private String alias;
		private String contrasenia;
		private String confirmacion_contrasenia;
		private String numero_token;
		private String numero_token_confirmacion;
		private String numero_celular;
		private String compania_celular;
		private String correo_electronico;
		private String identificador_sesion;
					   					   	

		public MessageErrors validate(){		
			MessageErrors error = new MessageErrors();	
			
	
		   if (getComando().equalsIgnoreCase("EJECUCION")) {

			if (Validator.isEmptyData(identificador_sesion)){
				error.add("identificador_sesion", VALIDATOR_ACTIVACION_EXPRESS_IDENTIFICADOR);
			}
				
			if (idaplicacion==null || idaplicacion.toString().trim().length()==0){
				error.add("idaplicacion", APPLICATION_SESSION_ERROR);
			}
						
			if (Validator.isEmptyData(numero_tarjeta)) {
				error.add("numero_tarjeta",VALIDATOR_ACTIVACION_EXPRESS_TARJETA_INVALIDO);
			}else if (numero_tarjeta.trim().length() != 14 && numero_tarjeta.trim().length() != 16) {
				error.add("numero_tarjeta",VALIDATOR_ACTIVACION_EXPRESS_LONGITUD_TARJETA_INVALIDO);
			}
			if (Validator.isEmptyData(operacion)) {
				error.add("operacion",VALIDATOR_ACTIVACION_EXPRESS_OPERACION_INVALIDO);
			}
			if (Validator.isEmptyData(huella_seguridad)){
				error.add("huella_seguridad", VALIDATOR_ACTIVACION_EXPRESS_CLAVE_INVALIDO);
			}
			if (Validator.isEmptyData(alias)) {
				error.add("alias", VALIDATOR_ACTIVACION_EXPRESS_ALIAS_INVALIDO);
			}
			if (Validator.isEmptyData(contrasenia)) {
				error.add("contrasenia",VALIDATOR_ACTIVACION_EXPRESS_CONTRASENA_INVALIDO);
			}else if (Validator.isEmptyData(confirmacion_contrasenia)) {
				error.add("confirmacion_contrasenia",VALIDATOR_ACTIVACION_EXPRESS_CONFIRMACION_INVALIDO);
			}else if (!confirmacion_contrasenia.equals(contrasenia)) {
				error.add("confirmacion_contrasenia",VALIDATOR_ACTIVACION_EXPRESS_COMPARACION_CONTRASENA_INVALIDO);
			}
			
			if (Validator.isEmptyData(numero_token)) {
				error.add("numero_token",VALIDATOR_ACTIVACION_EXPRESS_NUMEROTOKEN_INVALIDO);
			}else if (Validator.isEmptyData(numero_token_confirmacion)){
				error.add("numero_token_confirmacion",VALIDATOR_ACTIVACION_EXPRESS_NUMEROTOKEN_INVALIDO);
			}else if (!numero_token_confirmacion.equals(numero_token)) {
				System.out.println("1: "+numero_token_confirmacion+"  2: "+numero_token);
				error.add("numero_token_confirmacion","Los numeros de token tienen que ser iguales");
				
			}
			if (Validator.isEmptyData(numero_celular)) {
				error.add("numero_celular",VALIDATOR_ACTIVACION_EXPRESS_CELULAR_INVALIDO);
			}
			if (Validator.isEmptyData(compania_celular)) {
				error.add("compania_celular",VALIDATOR_ACTIVACION_EXPRESS_COMPANIA_INVALIDO);
			}
		  }		   
		   
		   
			return error;
		}





		public Cipher getIdaplicacion() {
			return idaplicacion;
		}





		public void setIdaplicacion(Cipher idaplicacion) {
			this.idaplicacion = idaplicacion;
		}





		public String getNumero_tarjeta() {
			return numero_tarjeta;
		}





		public void setNumero_tarjeta(String numero_tarjeta) {
			this.numero_tarjeta = numero_tarjeta;
		}





		public String getOperacion() {
			return operacion;
		}





		public void setOperacion(String operacion) {
			this.operacion = operacion;
		}





		public String getHuella_seguridad() {
			return huella_seguridad;
		}





		public void setHuella_seguridad(String huella_seguridad) {
			this.huella_seguridad = huella_seguridad;
		}





		public String getAlias() {
			return alias;
		}





		public void setAlias(String alias) {
			this.alias = alias;
		}





		public String getContrasenia() {
			return contrasenia;
		}





		public void setContrasenia(String contrasenia) {
			this.contrasenia = contrasenia;
		}





		public String getConfirmacion_contrasenia() {
			return confirmacion_contrasenia;
		}





		public void setConfirmacion_contrasenia(String confirmacion_contrasenia) {
			this.confirmacion_contrasenia = confirmacion_contrasenia;
		}





		public String getNumero_token() {
			return numero_token;
		}





		public void setNumero_token(String numero_token) {
			this.numero_token = numero_token;
		}





		public String getNumero_celular() {
			return numero_celular;
		}





		public void setNumero_celular(String numero_celular) {
			this.numero_celular = numero_celular;
		}





		public String getCompania_celular() {
			return compania_celular;
		}





		public void setCompania_celular(String compania_celular) {
			this.compania_celular = compania_celular;
		}





		public String getCorreo_electronico() {
			return correo_electronico;
		}





		public void setCorreo_electronico(String correo_electronico) {
			this.correo_electronico = correo_electronico;
		}





		public String getNumero_token_confirmacion() {
			return numero_token_confirmacion;
		}





		public void setNumero_token_confirmacion(String numero_token_confirmacion) {
			this.numero_token_confirmacion = numero_token_confirmacion;
		}





		public String getIdentificador_sesion() {
			return identificador_sesion;
		}





		public void setIdentificador_sesion(String identificador_sesion) {
			this.identificador_sesion = identificador_sesion;
		}


		

		
	
}
