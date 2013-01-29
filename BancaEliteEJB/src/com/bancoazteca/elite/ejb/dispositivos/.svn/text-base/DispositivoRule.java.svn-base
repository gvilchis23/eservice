package com.bancoazteca.elite.ejb.dispositivos;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
/**
 * La clase DispositivoRule es la encargada de validar 2 tipos de errores <br/>
 * si se encuentra genera una EliteDataExceptio.<br/>
 * Los errores que se buscan son:<br/>
 * <b>Error de paths:</b><br/> 
 * Este es cuando el resultado del forward es hacia un path no esperado,<br/>
 * entonces se genera la excepción, el nivel de error es de paths.<br/> 
 * <br/>
 * <b>Error de validación en el form:</b><br/>
 *  Este es cuando el xml de regreso contiene elementos de tipo 'org.apache.struts.action.ERROR',esto nos indica que fueron errores de validación <br/>
 *  en el form y se deberán de retornar en la respuesta, el nivel de error en este caso será de ActionErrors <br/>
 */
public class DispositivoRule extends EliteRules {
	
//	private static final Logger $log = Logger.getLogger(DispositivoRule.class);

	public DispositivoRule(MessageElement messageElement) throws EliteDataException {
		super(messageElement);
	}

	public void validateInitialServiceRastreoPedido() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/solicitudToken/infoRastreo.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateSolicitaBloqueoToken() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/bdc/blodescan.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateBloqueoToken() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/bdc/confirmaB.jsp") &&
				!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/bdc/confirmaD.jsp") &&
				!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/bdc/confirmaC.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateEjecutaBloqueoToken() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/bdc/bloqueado.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateEjecutaDesbloqueo() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/bdc/desbloqueo.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateEjecutaCancelacion() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/bdc/cancelado.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateSolicitaActivacion() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/realizoActiva.jsp")) {
				errors = new HashMap<String, String>();
				errors.put("RealizoActivacion", "Tu Firma Azteca ya se encuentra activa");
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/adminTokenIni.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateNumeroSerieActivacionToken() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/sync.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateTokenActivacion() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null ) {
				errors.remove("preToken");
				if(!errors.isEmpty() ) {
					throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
				}
			}
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateTokenActivacionPre() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ) {
					throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
				}
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/contrato.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateEjecutaActivacion() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )		{
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}
			else
				if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/exitoActiva.jsp")) {
					throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}	
	
	public void validateSolicitaSincronizacionToken() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/syncro/syncro.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateNumeroSerieSincronizacionToken() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/syncro/sincroniza.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validateEjecutaSincronizacionTokenPre() throws EliteDataException, DispositivoException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null ) {
				errors.remove("preToken");
				if(!errors.isEmpty() ) {
					throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
				}
			}
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dispositivos/administracionToken/syncro/exitoSync.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
		
	public void validaInicialSolicitudDispositivo() throws EliteDataException, DispositivoException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!(datosEliteTO.getJspForward().equalsIgnoreCase( "/dispositivos/solicitudToken/paso1.jsp" ) ||
					datosEliteTO.getJspForward().equalsIgnoreCase( "/privada/administracion/dispositivos/dispositivos.jsp" ))	) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}

	public void validaDatosSolicitudDispositivo() throws EliteDataException, DispositivoException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase( "/dispositivos/solicitudToken/confirmacion.jsp" ) ) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validaCuentaSolicitudDispositivo() throws EliteDataException, DispositivoException{
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				throw new EliteDataException( "Error", errors, LEVEL_ACTION_ERRORS );
			}
			else if( !datosEliteTO.getJspForward().equalsIgnoreCase( "/dispositivos/solicitudToken/lugarEntrega.jsp" ) ){
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS );
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}

	public void validaConfirmacionSolicitudDispositivo() throws EliteDataException, DispositivoException{
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() ){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}
			else if( !datosEliteTO.getJspForward().equalsIgnoreCase( "/dispositivos/solicitudToken/resumen.jsp" ) ){
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS );
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}
	
	public void validaCatalogoEstados() throws EliteDataException, DispositivoException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null ){
				errors.remove( "afasdfa" );
				if( !errors.isEmpty() )
					throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase( "/dispositivos/solicitudToken/lugarEntrega.jsp" ) ) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new DispositivoException(e);
		}
	}

}
