package com.bancoazteca.elite.ejb.cuentas;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class ValidaPathsConsulta extends CuentasRule implements ValidaPathsERecibos {

	protected ValidaPathsConsulta(MessageElement messageElement)throws EliteDataException {
		super(messageElement);
	}

	public void validateReleaseVouchersInicio()throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/consultaNominaRecibosInicio.do?method=contrato")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					Map<String, String> error=new HashMap<String,String>();
					error.put("NO_DISPONIBLE", "El servicio no se encuentra disponible");
					throw new EliteDataException("No disponible",error,LEVEL_ACTION_ERRORS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}
				
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}

	public void validateReleaseVouchersContrato()throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/consultaNominaRecibosInicio.do?method=consulta")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					throw new EliteDataException("No disponible",null,LEVEL_PATHS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}
				
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}

	public void validateReleaseVouchersConsulta()throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errors ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/sobre.jsp")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/sinRecibos.jsp")) {
					errors=new HashMap<String, String>();
					errors.put("sinR", "Estimado usuario no tienes recibos por liberar");
					throw new EliteDataException("Action errors",errors,LEVEL_ACTION_ERRORS);
				}
				else 
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					throw new EliteDataException("No disponible",null,LEVEL_PATHS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_ACTION_MESSAGES);
				}
			
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}

	public void validateReleaseVouchersSeleccion()throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/consultaRec.jsp")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					throw new EliteDataException("No disponible",null,LEVEL_PATHS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}
				
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	
}
