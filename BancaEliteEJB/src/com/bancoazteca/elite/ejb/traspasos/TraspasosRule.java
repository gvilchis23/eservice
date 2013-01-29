package com.bancoazteca.elite.ejb.traspasos;

import java.util.Map;

import org.apache.axis.message.MessageElement;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class TraspasosRule extends EliteRules {

	protected TraspasosRule(MessageElement messageElement) throws EliteDataException {
		super(messageElement);		
	}
	
	public void validateByActionErrors() throws EliteDataException, TraspasosException {
		try {
			Map<String, String> actionErrorsMap = super.validateStrutsActionErrors();
			if (!actionErrorsMap.isEmpty()) {
				throw new EliteDataException("Error", actionErrorsMap, LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new TraspasosException(e);
		}
	}
	
	public void validatePreparePropiasPath() throws TraspasosException,EliteDataException{		 
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> actionErrorsMap = super.getActionErrros();
			if (actionErrorsMap!=null && !actionErrorsMap.isEmpty() && !actionErrorsMap.containsKey("submit")) {
				throw new EliteDataException("Error", actionErrorsMap, LEVEL_ACTION_ERRORS);
			
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/propiasConfirmar.jsp") && !datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/propias.jsp")){
				throw new EliteDataException("Error","No se pudo realizar la preparacion del traspaso",LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TraspasosException(e);
		}		
	}
	
	public void validatePreparePropiasEjecutaPath() throws TraspasosException,EliteDataException{		 
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> actionErrorsMap = super.getActionErrros();
			if (actionErrorsMap!=null && !actionErrorsMap.isEmpty() ) {
				throw new EliteDataException("Error", actionErrorsMap, LEVEL_ACTION_ERRORS);
			
			}
			else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/propiasEjecutar.jsp")){
				throw new EliteDataException("Error","No se pudo realizar la preparacion del traspaso",LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new TraspasosException(e);
		}		
	}

}
