package com.bancoazteca.elite.ejb.chequera;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class ChequeraRule extends EliteRules {
	
	private static final Logger $log = Logger.getLogger(ChequeraRule.class);

	protected ChequeraRule(MessageElement messageElement) throws EliteDataException {
		super(messageElement);
	}

	public void validaRoboExtravioInicio() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			else if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/bloqueo/initBloqueo.jsp")){
				$log.error("El path obentido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path", "El path recibido no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e){
			throw new ChequeraException(e);
		}
	}
	
	public void validaExtravioObtencionChequera() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			else if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/bloqueo/initBloqueo.jsp")){
				$log.error("El path obentido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path", "El path recibido no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e){
			throw new ChequeraException(e);
		}
	}
	public void validaExtravioTipoOperacion() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			else if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/bloqueo/chequeraSeleccion.jsp" ) &&
					!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/bloqueo/chequeSeleccion.jsp" ) ){
				$log.error("El path obentido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path", "El path recibido no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e){
			throw new ChequeraException(e);
		}
	}
	public void validaExtravioFecha() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			else if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/bloqueo/initBloqueo.jsp")){
				$log.error("El path obentido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path", "El path recibido no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e){
			throw new ChequeraException(e);
		}
	}
	public void validaExtravioValidaInformacion() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			else if(!datosEliteTO.getJspForward().equalsIgnoreCase("/waitCheques.jsp")){
				$log.error("El path obentido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path", "El path recibido no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e){
			throw new ChequeraException(e);
		}
	}
	public void validaExtravioEjecucion() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			else if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/bloqueo/chequeBloqueo.jsp")){
				$log.error("El path obentido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path", "El path recibido no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e){
			throw new ChequeraException(e);
		}
	}
	
	public void validateLiberacionChequesSolicitaCuenta() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/liberacion/initLiberacion.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validateLiberacionChequesSolicitaChequeras() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/liberacion/initLiberacion.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validateLiberacionChequesSolicitaCheque() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/liberacion/initLiberacion.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validateLiberacionChequesSolicitaPWD() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de action", errors, dispositivoHuellaTO, LEVEL_ACTION_ERRORS );
			}
			else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/liberacion/confirmaClave.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validateLiberacionChequesEjecutaPWD() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error de action", errors, LEVEL_ACTION_ERRORS );
			}
			else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/liberacion/liberacionOk.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}

	public void validateActivacionChequeSolicitud() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}
			else if( !datosEliteTO.getJspForward().equalsIgnoreCase( "/cheques/activacion/initActivacion.jsp" ) ){
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validateActivacionChequeValidacion() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
			if( errors != null && !errors.isEmpty() ){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de action", errors, dispositivoHuellaTO, LEVEL_ACTION_ERRORS );
			}
			else if( !datosEliteTO.getJspForward().equalsIgnoreCase( "/cheques/activacion/confirmaClave.jsp" ) ){
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}

	public void validateActivacionChequeEjecucion() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}
			else if( !datosEliteTO.getJspForward().equalsIgnoreCase( "/cheques/activacion/activacionOk.jsp" ) ){
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validaPresolicitudChequesInicio() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			Map<String, String> errors=super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validaPresolicitudChequesInicioPre() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			Map<String, String> errors=super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/solicitud/initSolicitud.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validaPresolicitudChequesDetalleCuenta() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			Map<String, String> errors=super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validaPresolicitudChequesDetalleCuentaPre() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			Map<String, String> errors=super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/solicitud/initSolicitud.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validaPresolicitudValidacion() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			Map<String, String> errors=super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/solicitud/vistaPreviaPdfSolicitud.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validaPresolicitudEjecucion() throws EliteDataException, ChequeraException{
		try {
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			Map<String, String> errors=super.getActionErrros();
			errors.put("cuenta_cargo", "La cuenta cargo es incorrecta");
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/solicitud/resumenSolicitud.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	public void validaSolicitudConsultarChequera() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error de Action  ",errors,LEVEL_ACTION_ERRORS);
			} else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/consulta/inicio.jsp")){
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	public void validaDetalleVariasCuentasChequera() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error de Action  ",errors,LEVEL_ACTION_ERRORS);
			} else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/consulta/cuentaSeleccionada.jsp")){
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}
	
	public void validaConsultaChequera() throws EliteDataException, ChequeraException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error de Action  ",errors,LEVEL_ACTION_ERRORS);
			} else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cheques/consulta/detalleCheques.jsp")){
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new ChequeraException(e);
		}
	}

}
