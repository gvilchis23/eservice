package com.bancoazteca.elite.ejb.pagoServicios;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.transferencias.TransferenciasException;

public class PagoServiciosRule extends EliteRules {
	
	private static final Logger $log = Logger.getLogger(PagoServiciosRule.class);

	protected PagoServiciosRule(MessageElement messageElement) throws EliteDataException {
		super(messageElement);
	}
	
	public void validaNivelSeguridad() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/spei.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				if(datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/sinAutorizacionSpei.jsp")){
					errors.put("Error", MESSAGE_ERROR_AUTORIZACION );
				}								
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialTelmexPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialServiceTelmexPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialStartServiceTelmexPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateDataTelmexPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if( datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoSinSaldo.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				errors = new HashMap<String, String>();
				errors.put("errorCuenta", "No cuenta con fondos suficientes para realizar el pago");
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateconfimTelmexPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialIusacellPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialServiceIusacellPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateStartInitialServiceIusacellPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateFormDataIusacellPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if( datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoSinSaldo.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				errors = new HashMap<String, String>();
				errors.put("errorCuenta", "No cuenta con fondos suficientes para realizar el pago");
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateTokenIusacellPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateConfirmIusacellPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioTerminado.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialLuzPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateStartInitialLuzPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateStartDataInitialLuzPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	

	public void validateDataLuzPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateConfirmLuzPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateWaitExecuteLuzPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/waitTransactionToken.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateExecuteLuzPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioTerminado.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialSkyPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialServiceSkyPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialStartServiceSkyPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateDataSkyPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if( datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoSinSaldo.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				errors = new HashMap<String, String>();
				errors.put("errorCuenta", "No cuenta con fondos suficientes para realizar el pago");
				throw new EliteDataException("Error",errors,LEVEL_ACTION_ERRORS);
			}	else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateConfirmSkyPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateWaitConfirmSkyPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/waitTransactionToken.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateExecuteSkyPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioTerminado.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialMovistarPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateInitialServiceMovistarPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateStartInitialServiceMovistarPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateFormDataMovistarPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if( datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoSinSaldo.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				errors = new HashMap<String, String>();
				errors.put("errorCuenta", "No cuenta con fondos suficientes para realizar el pago");
				throw new EliteDataException("Error",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateTokenMovistarPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateConfirmMovistarPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioTerminado.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateMenuTiempoAirePayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/privada/pagos/tiempo/paso1.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateMenuServiceTiempoAirePayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateStartMenuTiempoAirePayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/proveedoresTiempoAire.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateInitialTiempoAirePayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/tiempoAire.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				if(datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/sinAutorizacion.jsp")){
					errors.put("Error", MESSAGE_ERROR_AUTORIZACION);
				}												
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateDataTiempoAirePayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/tiempoAireConfirmacion.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateConfirmTiempoAirePayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/waitTransactionToken.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateExecuteWaitTiempoAirePayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/tiempoAireFolio.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	public void validateInitialTarjetaCreditoPayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/privada/pagos/infinite/paso1.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateInitialServiceTarjetaCreditoPayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateStartInitialTarjetaCreditoPayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetaAzteca/datos.do?method=inicio")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateInitialWaitTarjetaCreditoPayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetaAzteca.jsp")){
				String pathObtenido = datosEliteTO.getJspForward();
				if(pathObtenido.equalsIgnoreCase("/pagoTarjetas/sinTDCBAZ.jsp")){
					errors = new HashMap<String, String>();
					errors.put("sinTarjeta", "Estimado usuario usted no cuenta con Tarjetas de Crédito de Banco Azteca.");
					throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
				}else{
					throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
				}
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateDataTarjetaCreditoPayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetaAztecaContrasena.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateConfirmTarjetaCreditoPayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	public void validateConfirmWaitTarjetaCreditoPayment()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetaAztecaEjecutar.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateInitialMenuTarjetaPaymentOthersBank() throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/privada/pagos/otrastarjetas/paso1.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateWaitInitialTarjetaPaymentOthersBank() throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	public void validateInitialTarjetaPaymentOthersBank() throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/datos.do?method=inicio")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	public void validateStartServiceTarjetaPaymentOthersBank() throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetas.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	public void validateDataTarjetaPaymentOthersBank() throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetasContrasena.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	public void validateConfirmTarjetaPaymentOthersBank() throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty() && !errors.containsKey("org.apache.struts.action.ERROR")){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetasEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	public void validateSendMailTarjetaPaymentOthersBank() throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetasEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateAgregarFrecuente()throws EliteDataException,PagoServiciosException{
		try {
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0 && !errors.containsKey("yaexiste")){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);
			}
			
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateAgregarFrecuenteEjecucion()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);														
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/ctasFrecuentes/exitoFrecuentesServicios.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validatePreModificarFrecuente()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);	
																	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/ctasFrecuentes/modificarFreceuntesServicios.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validatePreEliminarFrecuente()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);	
																		
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/ctasFrecuentes/eliminaFreceuntesServicios.jsp")){///servicios/consultaReferencias.do?method=consultaReferencias")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateModificarFrecuente()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/ctasFrecuentes/verificarFrecuentesServicios.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateEliminarFrecuente()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);																	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/consultaReferencias.do?method=consultaReferencias")){		
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	public void validateConsultaCuentasFrecuentesTarjetasOtrosBancos() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccounts.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateAgregaCuentasFrecuentesTarjetasOtrosBancos() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccounts.do?tipo=tdc")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateIniciarEditarCuentasFrecuentesTarjetasOtrosBancos()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/modificafrequentAccounts.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	public void validateIniciarEliminarCuentasFrecuentesTarjetasOtrosBancos()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/eliminafrequentAccounts.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	public void validatePrepararEditarCuentasFrecuentesTarjetasOtrosBancos()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccounts.do?tipo=tdc")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEjecutarEditarCuentasFrecuentesTarjetasOtrosBancos()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccounts.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	public void validateConsultaCuentasFrecuentesTiempoAire() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/telefonosfrecuentes.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateAgregaCuentasFrecuentesTiempoAire() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/frequentTelephones.do?method=start")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateBorraCuentasFrecuentesTiempoAireValidacion() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/eraseTelephone.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateBorraCuentasFrecuentesTiempoAireEjecucion() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/frequentTelephones.do?method=start")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateInitialMaxiGasPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateInitialServiceMaxiGasPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateInitialStartServiceMaxiGasPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				throw new EliteDataException("Error de path", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateDataMaxiGasPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if(datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoSinSaldo.jsp")){
				Map<String, String> errorsPath = new HashMap<String, String>();
					
					errorsPath.put("cuenta_cargo", "*La cuenta de cargo tiene saldo insuficiente");
				throw new EliteDataException("Error", errorsPath, LEVEL_ACTION_ERRORS);
				
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateconfimMaxiGasPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	public void validateInitialAztecaWebPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateInitialServiceAztecaWebPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateInitialStartServiceAztecaWebPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				throw new EliteDataException("Error de path", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateDataAztecaWebPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if(datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoSinSaldo.jsp")){
				Map<String, String> errorsPath = new HashMap<String, String>();
					
					errorsPath.put("sinSaldo", "*La cuenta de cargo tiene saldo insuficiente");
				throw new EliteDataException("Error", errorsPath, LEVEL_ACTION_ERRORS);
				
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateconfimAztecaWebPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	

	public void validateInitialAvicolaPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	
	public void validateInitialServiceAvicolaPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	
	public void validateStartInitialServiceAvicolaPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicio.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	
	public void validateFormDataAvicolaPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioPassword.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateTokenAvicolaPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioEjecutar.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateConfirmAvicolaPayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioTerminado.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	public void validateSetInicioDonativo()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/donativos/inicio.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateSetDonativoPre()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/donativos/jugeton.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateSetDonativoConfirmar()throws EliteDataException, PagoServiciosException  {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/donativos/jugetonContrasena.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateSetDonativoEjecutar()throws EliteDataException, PagoServiciosException   {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				//throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/waitTransactionToken.jsp")) {
				System.out.println("Valido waitTransactionToken.jsp");
			//} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/donativos/jugetonContrasena.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateSetDonativoEjecutarCmd()throws EliteDataException, PagoServiciosException   {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/donativos/jugetonEjecutar.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateSetEnvioDineroExpresAltaFrecuenteWait()throws EliteDataException, PagoServiciosException  {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateSetEnvioDineroExpressAltaFrecuenteConfirmar()throws EliteDataException, PagoServiciosException  {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/dineroExpress/beneficiariosFrecuentes/confirmarBeneficiarios.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateSetEnvioDineroExpressAltaFrecuenteEjecutar()throws EliteDataException, PagoServiciosException  {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/dineroExpress/beneficiariosFrecuentes/frecuentes.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	public void validateSetEnvioDineroExpressBuscaFrecuenteEjecutar()throws EliteDataException, PagoServiciosException  {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/agendaDineroExpress.do?method=inicio")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEnvioDineroExpressSolicitud() throws EliteDataException, PagoServiciosException   {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0 && !errors.containsKey("errorToken")) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dex/envios/paso1.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateContratoEnvioDineroExpressSolicitud() throws EliteDataException, PagoServiciosException   {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0 && !errors.containsKey("errorToken")) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (datosEliteTO.getJspForward().equalsIgnoreCase("/dex/envios/contrato.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("AceptarContratoDEX",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEnvioDineroExpressValidacion() throws EliteDataException, PagoServiciosException   {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0 && !errors.containsKey("errorToken")) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dex/envios/paso2.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEnvioDineroExpressEjecucion() throws EliteDataException, PagoServiciosException   {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0 && !errors.containsKey("errorToken")) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dex/envios/paso3.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			} 
		} catch (XmlDecodeException e) { 
			throw new PagoServiciosException(e);
		}
	}
	
	
	public void validateEnvioCorreoDineroExpressEjecucion() throws EliteDataException, PagoServiciosException   {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0 && !errors.containsKey("errorToken")) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/dex/envios/paso3.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) { 
			throw new PagoServiciosException(e);
		}
	}

	public void validateInitialParametrizablePayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosElegir.do")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateInitialServiceParametrizablePayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/parametrizable/elektraps/inicio.do?method=inicio")) {
				
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateStartInitialServiceParametrizablePayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if( errors != null && !errors.isEmpty() )
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			else
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/pagoServicioParam.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateFormDataParametrizablePayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && !errors.isEmpty() ) {
				for( String str : errors.keySet() ){
					if( str.equalsIgnoreCase( "importe" ) || str.equalsIgnoreCase( "cuentaReferencia" )) {
						String query = "for $cuentaReferencia in //elite/pagoServicioFormParam return $cuentaReferencia";
						XmlDecoder decode = new XmlDecoder();
						try {
							XMLFinder.getElement(messageElement.toString(), query);
							String strError = (String)decode.toDeserialize("strReturn", messageElement,null);
							errors.put(str, strError);
							break;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}				
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if( datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/elektraps/pagoServicioParam.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				errors = new HashMap<String, String>();
				errors.put("errorCuenta", "No cuenta con fondos suficientes para realizar el pago");
				throw new EliteDataException("Error",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/elektraps/pagoServicioPasswordParam.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateTokenParametrizablePayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/parametrizable/ejecutar.do?method=isTokenValido")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateConfirmParametrizablePayment() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/elektraps/pagoServicioTerminadoParam.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateSetInitialFrecuentes()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);																	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/ctasFrecuentes/wait.jsp")){		
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateSetStartInitialFrecuentes()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);																	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/ctasFrecuentes/ctasFreceuntesServicios.jsp")){		
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validateModificarFrecuenteInitEjecucion()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
					throw new EliteDataException("Action errros ",errors, LEVEL_ACTION_ERRORS);	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/consultaReferencias.do?method=consultaReferencias")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}

	
//Pago de Tarjeta Azteca
	
	public void validateSolicitudPagoTarjetaAzteca()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/sinTDCBAZ.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	
	
	
	public void validateFrecuentesPagoTarjetaAzteca()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccountsAzteca.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	
	public void validacionTarjetaAzteca()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetasAztecaContrasena.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	public void validacionConfirmacionPagoTarjetaAzteca()throws EliteDataException,PagoServiciosException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetasAztecaEjecutar.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException (e);
		}
	}
	
	
	//Validaciones para dar de alta, editar y eliminar cuentas frecuentes
	
	public void validateEjecutarConsultaCuentasFrecuentesTarjetasAzteca() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/exitoAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateIniciarEditarCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/modificafrequentAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}

	
	public void validatePrepararEditarCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/verificafrequentAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	
	public void validateEjecutarEditarCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccountsAzteca.do?tipo=tdc")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	
	
	public void validateEjecutarExtraEditarCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void ValidateAgregaCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/verificafrequentAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEliminarCuentasFrecuentesTarjetasOtrosBancos()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/eliminafrequentAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validatePrepararEliminarCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccountsAzteca.do?tipo=tdc")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEjecutarEliminarCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEjecutarExtraEliminarCuentasFrecuentesTarjetasAzteca()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/frequentAccountsAzteca.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	
	public void validateEjecutarEnviaMail()throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetasAztecaEjecutar.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEditarReferenciasFrecuentesTiempoAireValidacion() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/modificaTelephone.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateEditarReferenciasFrecuentesTiempoAireEjecucion() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/frequentTelephones.do?method=start")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
	public void validateConsultaCuentasFrecuentesTiempoAireInit() throws EliteDataException, PagoServiciosException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/tiempoAire/wait.jsp")) {
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new PagoServiciosException(e);
		}
	}
	
}
