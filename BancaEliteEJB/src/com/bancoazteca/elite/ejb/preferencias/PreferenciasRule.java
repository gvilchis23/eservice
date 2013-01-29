package com.bancoazteca.elite.ejb.preferencias;


import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;

public class PreferenciasRule extends EliteRules {
	
	private static final Logger log = Logger.getLogger(PreferenciasRule.class);

	protected PreferenciasRule(MessageElement messageElement) throws EliteDataException {
		super(messageElement);
	}
	
	
	
	public void validarObtenerPaquetes()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/aperturas/Rma_content2/Paquetes_content.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void validarFechaCuentaPaquetes()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/privada/aperturas/Rma2/ContratoServicio.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	
	public void confirmarActivacionPaquetes()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/aperturas/Rma_content/Exito_Operacion.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	
		
	public void validarConsultaPaquetesCliente()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/aperturas/Rma_content2/ConfirmaCancelacion.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void validarCancelacionPaquetes()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/aperturas/Rma_content/ExitoCancelacion.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void validarConsultarCuenta()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion/validarCtaOTarj.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void validarDatosActivar()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion/aceptacion.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void validarContrato()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion/datosUsuario.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void consultarUsuarioDisponible()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			
		if(errors.get("ok")==null){
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion/datosUsuario.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void registrarUsuario()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion/passwordAcceso.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void activarUsuario()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion/terminar.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void consultarCuentaExpress()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/security/consultaSaldos.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	public void validarCuentaExpress()throws EliteDataException,UsuarioException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("ErrorAction",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/snominaExpress/historicoRecibosElectronicos.jsp")){
				throw new EliteDataException("ErrorPath","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new UsuarioException (e);
		}
	}
	
	
	
}
