package com.bancoazteca.elite.commons;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.Element;

import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.util.EscapeUtils;

/**
 * 
 * @author Jorge Bringas
 *
 */
public abstract class EliteRules {
	
	protected final String DATOS_ELITE_TAG = "DatosEliteVO";
	protected final String ELITE_ERRORS = "EliteRules.ELITE_ERRORS";
	protected final String ERROR_GENERAL = "Error general";
	protected final String MAX_INTENTOS_PATH = "/maximoIntentos.jsp";
	protected final String MAX_INTENTOS_PATH_HUELLA = "/passwordFailureMaxAttempts.jsp";
	protected final String ERROR_PATH = "/error.jsp";
	protected final String ACTION_ERROR_TAG = "org.apache.struts.action.ERROR";
	public static final int LEVEL_ACTION_ERRORS = 1;
	public static final int LEVEL_ACTION_MESSAGES = 2;
	public static final int LEVEL_OBJECTS = 3;
	public static final int LEVEL_PATHS = 4;
	public static final String MESSAGE_ERROR_AUTORIZACION="Por su seguridad el Servicio de Transferencias está temporalmente desactivado. &#13; Todas sus operaciones de Movimientos de Dinero tendrán que ser confirmadas con uno de los dispositivos de seguridad."; 
	
	protected XMLDecode decode= new XMLDecode();
	protected MessageElement messageElement;
	
	
	
	protected EliteRules(MessageElement messageElement) throws EliteDataException{
		this.messageElement = messageElement;
		validatePaths(messageElement);
	}
	
	@SuppressWarnings("unchecked")
	protected Map<String, String> getActionErrros() throws  XmlDecodeException{		
		Map<String, String> actionErrorsMap = (Map<String, String>) decode.buildMapBeans(messageElement, ACTION_ERROR_TAG);
		
		if(actionErrorsMap!=null && verificaExepcionesActionErrors(actionErrorsMap)){
			return null;
		}
		
		if (actionErrorsMap!=null && actionErrorsMap.size()>0){
			return EscapeUtils.unescapeActionErrors(actionErrorsMap);
		}else {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	protected Map<String, String> validateStrutsActionErrors() throws EliteDataException, XmlDecodeException{		
		Map<String, String> actionErrorsMap = (Map<String, String>) decode.buildMapBeans(messageElement, ACTION_ERROR_TAG);

		if (!actionErrorsMap.isEmpty()&&!verificaExepcionesActionErrors(actionErrorsMap)) {
			throw new EliteDataException("Error proveniente de action errors", EscapeUtils.unescapeActionErrors(actionErrorsMap), LEVEL_ACTION_ERRORS);
		}		
		return actionErrorsMap;
	}
	
	
	private  boolean verificaExepcionesActionErrors(Map<String, String> actionErrorMap){
		boolean exito=true;
		
		if(actionErrorMap==null){
			return exito;
		}
		
		Set<Entry<String, String>> entrySet = actionErrorMap.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,String> entry = iterator.next();
			String value=entry.getValue();
//			No se ha podido enviar el correo de confirmación, pero su operación ha sido exitosa.
			if(value.matches(".*ha sido exitosa.*")){
				exito =true;
				actionErrorMap.clear();
			}else{
				exito=false;
			}
		}
		return exito;
	}
	
	
	protected DatosEliteTO getDatosEliteTO() throws XmlDecodeException {
		XMLDecode decode = new XMLDecode();
		DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
		return datosEliteTO;
	}
	
	protected DispositivoHuellaTO getFingerPrint() throws XmlDecodeException{
		final String querySecurityTemp = "for $a in //elite/ebanking_web_ClienteVO/securityTemp  return $a";
		DispositivoHuellaTO dispositivoHuellaTO = null;
//		String mensaje = null;
		try {
//			mensaje = decode.getString(messageElement, "key_mensaje");
//			if(mensaje != null && mensaje.indexOf("huella") >= 0 ){
				Element securityTempXml = null;
				MessageElement messageSecurityTemp = null;
				dispositivoHuellaTO = new DispositivoHuellaTO();
				HuellaDigitalTO huellaMuerta = null;
				String lop = null;
				String llpub = null;
				lop = decode.getString(messageElement, "lop");
				llpub = decode.getString(messageElement, "llpub");
				
				securityTempXml = XMLFinder.getElement(messageElement.toString(), querySecurityTemp);
				
				messageSecurityTemp = new MessageElement(securityTempXml);
				huellaMuerta = (HuellaDigitalTO) decode.buildBean(HuellaDigitalTO.class, messageSecurityTemp, "huellaMuerta");
				
				
				dispositivoHuellaTO.setLongitudHuella(lop);
				dispositivoHuellaTO.setLlavePublica(llpub);
				dispositivoHuellaTO.setHuellaMuerta(huellaMuerta);						
//			} 	
		} catch (IOException e) {
			e.printStackTrace();
			throw new XmlDecodeException(e);
		}
		return dispositivoHuellaTO;
	}
	
	private void validatePaths(MessageElement messageElement) throws EliteDataException{

		Map<String, String> params = new HashMap<String, String>();
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (datosEliteTO.getJspForward().equalsIgnoreCase(MAX_INTENTOS_PATH) || datosEliteTO.getJspForward().equalsIgnoreCase( MAX_INTENTOS_PATH_HUELLA ) ){
				params.put(ELITE_ERRORS, "Lo sentimos, ha superado el número máximo de intentos para la operación");
				throw new EliteDataException(ELITE_ERRORS,params, LEVEL_PATHS);
			}else if (datosEliteTO.getJspForward().equalsIgnoreCase(ERROR_PATH)){
				params.put(ERROR_GENERAL,"Estimado usuario, lo sentimos, por el momento no es posible procesar su petición. Nuestros ingenieros ya estan trabajando para darle solución. Por su comprensión GRACIAS.");
				throw new EliteDataException(ERROR_GENERAL,params, LEVEL_PATHS);
			}			
		} catch (XmlDecodeException e) {
			params.put(ELITE_ERRORS, "XmlDecodeException");
			throw new EliteDataException(ELITE_ERRORS,params, LEVEL_PATHS);
		}
	}
}