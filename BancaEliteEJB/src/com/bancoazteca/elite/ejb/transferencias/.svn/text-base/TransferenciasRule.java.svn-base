package com.bancoazteca.elite.ejb.transferencias;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class TransferenciasRule extends EliteRules{
	
	private static final Logger $log = Logger.getLogger(TransferenciasRule.class);

	public TransferenciasRule(MessageElement messageElement)throws EliteDataException {
		super(messageElement);	
	}
	

	public void validateTransferenciaTercerosInvocacion() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/terceros.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("Error", MESSAGE_ERROR_AUTORIZACION);
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}

	public void validateTransferenciaTercerosEnvioDatos() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/tercerosContrasena.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateEjecutarTransferenciaTerceros() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();			
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/tercerosEjecutar.jsp") && !datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/tercerosRetencion.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateEjecutarTransferenciaTercerosGeneral() throws TransferenciasException,EliteDataException{
		try {
			Map<String, String> actionErrorsMap = getActionErrros();
			if (actionErrorsMap != null && actionErrorsMap.size()>0 || actionErrorsMap.containsKey("mensaje")){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",actionErrorsMap, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}


	public void validateEnvioMailTerceros() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/tercerosEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateTransferenciaSpeiInvocacion() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transSpei.do?method=inicio") && !datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/speiProgramado.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("Error", "Por tu seguridad el Servicio de Transferencias está temporalmente desactivado. Todas tus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los dispositivos de seguridad.");
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateTransferenciaSpeiInicio() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/spei.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				if(datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/sinAutorizacionSpei.jsp")){
					errors.put("Error", "Por tu seguridad el Servicio de Transferencias está temporalmente desactivado. Todas tus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los dispositivos de seguridad.");
				}								
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateTransferenciaTefInicio() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
//			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/agregaFrecuente.jsp")){
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("Error", "Por tu seguridad el Servicio de Transferencias está temporalmente desactivado. Todas tus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los dispositivos de seguridad.");
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateTransferenciaSpeiEnvioDatos() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/speiConfirmar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				if(datosEliteTO.getJspForward().equalsIgnoreCase("/agendaCuentas/registraAntesConfirmar.do?method=inicio")){
					errors = new HashMap<String, String>();
					errors.put("Error", "Por disposición de la Comisión Nacional Bancaria y de Valores, todas las cuentas destino en las operaciones de transferencia deberán ser registradas previamente en el listado de cuentas frecuentes.");
					throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
				}else{
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}				
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateEnvioMailSpei() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/speiEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateEjecutarTransferenciaSpei() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/speiEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}

	public void validateTransferenciaTEFInvocacion() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/tefs.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				if(datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/sinAutorizacionTefs.jsp")){
					errors.put("Error", "Por tu seguridad el Servicio de Transferencias está temporalmente desactivado. Todas tus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los dispositivos de seguridad.");
				}		
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateTransferenciaTEFEnvioDatos() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/tefsContrasena.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}

	public void validateEjecutarTransferenciaTEF() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/tefsEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateEnvioMailTef() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/tefsEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}

	public void validateTransferenciaInternacionalInvocacion() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transfInternal.jsp") && !datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transInterFueraHorario.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				if(datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/sinAutorizacionTransfInternal.jsp")){
					errors.put("Error", "Por tu seguridad el Servicio de Transferencias está temporalmente desactivado. Todas tus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los dispositivos de seguridad.");
				}	
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}

	public void validateTransferenciaInternacionalEnvioDatos() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transfInternalContrasena.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateEjecutarTransferenciaInternacional() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transfInternalEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateEnvioMailInternacional() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transfInternalEjecutar.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}
	
	public void validateTransferenciaTefIntenationalesPreparacionAgregarCuenta() throws TransferenciasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/agregaFrecuente.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("Error", "Por tu seguridad el Servicio de Transferencias está temporalmente desactivado. Todas tus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los dispositivos de seguridad.");
				throw new EliteDataException("Error de path ",errors,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
	}

	
}
