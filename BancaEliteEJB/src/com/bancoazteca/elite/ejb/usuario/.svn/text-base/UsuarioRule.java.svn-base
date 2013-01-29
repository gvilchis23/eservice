package com.bancoazteca.elite.ejb.usuario;

import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class UsuarioRule extends EliteRules {
	
	private static final Logger $log = Logger.getLogger(UsuarioRule.class);

	public UsuarioRule(MessageElement messageElement) throws EliteDataException {
		super(messageElement);
	}

	public void validateSendAccountOrCreditCardNumber() throws EliteDataException, UsuarioException {
		this.validateByActionErrors();
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/contrasena/Datos.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}

	public void validateSendPersonalData() throws EliteDataException, UsuarioException {
		this.validateByActionErrors();
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/contrasena/confirmacionDatosUsuario.do?method=datos")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validateChangePersonalData() throws EliteDataException, UsuarioException {
		this.validateByActionErrors();
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/contrasena/passwordAcceso.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}

	public void validateSendPersonalData1() throws EliteDataException, UsuarioException {
		this.validateByActionErrors();
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/contrasena/datosUsuario.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	
	public void validateSendNewPassword() throws EliteDataException, UsuarioException {
		this.validateByActionErrors();
		DatosEliteTO datosEliteTO;
		try {
			datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/contrasena/exito.do?method=fin")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
		
	}
	
	public void validateEndingNewPassword() throws EliteDataException, UsuarioException {
		this.validateByActionErrors();
		DatosEliteTO datosEliteTO;
		try {
			datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/contrasena/recupera_cont_mensaje1.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
		
	}

	public void validateByActionErrors() throws EliteDataException, UsuarioException {
		try {
			Map<String, String> actionErrorsMap = super.validateStrutsActionErrors();
//			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if (!actionErrorsMap.isEmpty()) {
				throw new EliteDataException("Error", actionErrorsMap, LEVEL_ACTION_ERRORS);
			}
//			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/contrasena/confirmacionDatosUsuario.do?method=datos")){
//				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
//				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
//			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	
	public void validateBibliotecaRecibos() throws EliteDataException, UsuarioException {
		try {
			Map<String, String> actionErrorsMap = super.validateStrutsActionErrors();
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if (!actionErrorsMap.isEmpty()) {
				throw new EliteDataException("Error", actionErrorsMap, LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/bibliotecaRecibos/inicio.do?method=inicio")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	

	public void validateChangePasswordErrors() throws EliteDataException, UsuarioException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors != null && errors.size()>0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validateSessionManagment()throws EliteDataException, UsuarioException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validateModifySecurityLevel() throws EliteDataException, UsuarioException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if(errors != null && !errors.isEmpty()) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	public void validateChangeSecurityLevel() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			XmlDecoder decoder=new XmlDecoder();
			
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/seguridad/FinalizaCambioNivel.jsp")) {
				Map<String, String> errors = super.getActionErrros();
				if(errors != null && !errors.isEmpty() ) {
					
					DispositivoHuellaTO dispositivoHuellaTO=new DispositivoHuellaTO();
					
					String llave=(String)decoder.toDeserialize("lop", messageElement, null);
					String lonitud=(String)decoder.toDeserialize("llpub", messageElement, null);
					
					dispositivoHuellaTO.setLlavePublica(llave);
					dispositivoHuellaTO.setLongitudHuella(lonitud);
					
					EliteDataException exeption=new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
					exeption.setDispositivoHuellaTO(dispositivoHuellaTO);
					
					throw exeption;
					
				} else {
					throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
				}
			}
		} catch(XmlDecodeException xmldex) {
			throw new UsuarioException(xmldex);
		}
	}
	
	
	public void validarActivacionExpressInicio() throws EliteDataException, UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacionExpress/activacionExpressInicio.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}


	public void validarActivacionExpressCuenta() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				XmlDecoder decoder = new XmlDecoder();			
				String llavePublica = (String) decoder.toDeserialize("llpub", messageElement, null);
				String longitudHuella = (String) decoder.toDeserialize("lop", messageElement, null);
				if(llavePublica != null && longitudHuella != null){
					DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
					dispositivoHuellaTO.setLlavePublica(llavePublica);
					dispositivoHuellaTO.setLongitudHuella(longitudHuella);
					throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
				}else{
					throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
				}
				
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacionExpress/contrato.do")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validarContratoActivacionExpress() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacionExpress/activacionExpressContrato.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validarEjecucionActivacionExpress() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0 && !errors.containsKey("errorToken")) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacionExpress/activacionExpressConfirmacion.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validarActualizarDatosInit() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/usuarios/cambioDetalles.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validarActualizarDatos() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/usuarios/cambioDetallesConfirmacion.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validarActualizarDatosEjecutar() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/usuarios/cambioDetallesNotificacion.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
//	validarCambioContrasenaInitial
	public void validarCambioContrasenaInitial() throws EliteDataException, UsuarioException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/usuarios/cambioPasswordInicio.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
	public void validateExecChangePasswordErrors() throws EliteDataException, UsuarioException {
		try {
			Map<String, String> errors = super.getActionErrros();
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (errors != null && errors.size()>0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/usuarios/cambioPasswordConfirmar.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException(e);
		}
	}
	
}
