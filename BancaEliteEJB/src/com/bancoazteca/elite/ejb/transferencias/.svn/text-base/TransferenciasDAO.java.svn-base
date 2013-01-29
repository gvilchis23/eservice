package com.bancoazteca.elite.ejb.transferencias;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ConsultaTransferenciasRequestTO;
import com.bancoazteca.elite.beans.EnvioMailRequestTO;
import com.bancoazteca.elite.beans.InternacionalesBancosRequestTO;
import com.bancoazteca.elite.beans.SpeiProgramadoTO;
import com.bancoazteca.elite.beans.TransferenciaTercerosRequestTO;
import com.bancoazteca.elite.beans.TransferenciasInternacionalesRequestTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiRequestTO;
import com.bancoazteca.elite.beans.TransferenciasTEFRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.connector.DigitalFinger;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

/**
 * En la clase TransferenciasDAO se declaran todos los paths y los parámetros,<br/> 
 * necesarios para que el servlet del conector elite invoque las acciones correspondientes a las transferencias de dinero entre cuentas de otros bancos (spei y tef), internacionales y terceros banco azteca.<br/>
 * @return <b>org.apache.axis.message.MessageElement </b><br/>
 * 			En la invocación de las acciones se tiene como respuesta un MessageElement con la información recopilada.
 */

public class TransferenciasDAO extends EliteDAO{
	
	private static final Logger log = Logger.getLogger(TransferenciasDAO.class);
	
	private final String TRANSFERENCIA_TERCEROS_INVOCACION = "/traspasos/tercerosPre.elite";
	private final String TRANSFERENCIA_TERCEROS_ENVIO_DATOS = "/traspasos/tercerosConfirmar.elite";
	private final String EJECUTA_TRANSFERENCIA_TERCEROS = "/traspasos/tercerosEjecutarCmd.elite";
	private final String TRANSFERENCIA_TERCEROS_ENVIO_MAIL = "/traspasos/tercerosSendMail.elite";
	
	private final String TRANSFERENCIA_SPEI_INVOCACION = "/transferencias/speiPre.elite";
	private final String TRANSFERENCIA_SPEI_INICIO = "/transferencias/transSpei.elite";
	private final String TRANSFERENCIA_SPEI_ENVIO_DATOS = "/transferencias/transSpeiConfirmar.elite";
	private final String EJECUTA_TRANSFERENCIA_SPEI = "/transferencias/transSpeiTransferirCmd.elite";
	private final String TRANSFERENCIA_SPEI_ENVIO_MAIL = "/transferencias/speiSendMail.elite";
	private final String TRANSFERENCIA_TEF_INICIO = "/transferencias/tefs.elite";
	
	private final String TRANSFERENCIA_TEF_INVOCACION = "/transferencias/tefsPre.elite";
	private final String TRANSFERENCIA_TEF_ENVIO_DATOS = "/transferencias/tefsConfirmar.elite";
	private final String EJECUTA_TRANSFERENCIA_TEF = "/transferencias/tefsEjecutar.elite";
	
	private final String EJECUTA_TRANSFERENCIA_TEF_1 = "/transferencias/tefsEjecutarCmd.elite";
	
	private final String TRANSFERENCIA_TEF_ENVIO_MAIL = "/transferencias/tefsSendMail.elite";
	
//	private final String TRANSFERENCIA_INTERNACIONAL_INVOCACION_1 = "/transferencias/transfInternal.elite";
																	
	private final String TRANSFERENCIA_INTERNACIONAL_INVOCACION_2 = "/transferencias/transfInternalPre.elite";
	
//	private final String TRANSFERENCIA_INTERNACIONAL_CUENTAS_FRECUENTES_1 = "/transferencias/agendaCuentas.elite";
//	private final String TRANSFERENCIA_INTERNACIONAL_CUENTAS_FRECUENTES_2 = "/transferencias/agendaCuentasPre.elite";
	
	private final String TRANSFERENCIA_INTERNACIONAL_ENVIO_DATOS = "/transferencias/transfInternalConfirmar.elite";
	private final String EJECUTA_TRANSFERENCIA_INTERNACIONAL = "/transferencias/transfInternalEjecutar.elite";
	private final String EJECUTA_TRANSFERENCIA_INTERNACIONAL_CMD = "/transferencias/transfInternalEjecutarCmd.elite";
	private final String TRANSFERENCIA_INTERNACIONAL_ENVIO_MAIL = "/transferencias/transfInternalSendMail.elite";

	private final String INTERNACIONALES_CONSULTA_BANCOS = "/transferencias/cargarDatosCatalogo.elite";
	private final String INTERNACIONALES_CONSULTA_PAISES = "/transferencias/catalogoSwiftAbaPre.elite";

	private final String CONSULTA_TRANSFERENCIAS_1_1 = "/transferencias/consultaMenu.elite";
	private final String CONSULTA_TRANSFERENCIAS_1_2 = "/transferencias/consulta.elite";
	private final String CONSULTA_TRANSFERENCIAS_1_3 = "/transferencias/consultaCmd.elite";
	private final String CONSULTA_TRANSFERENCIAS_2 = "/transferencias/consultaCmd.elite";
	
	private final String CONSULTA_TRASPASOS_1_1 = "/traspasos/cuentasMenu.elite";
	private final String CONSULTA_TRASPASOS_1_2 = "/traspasos/cuentas.elite";
	private final String CONSULTA_TRASPASOS_1_3 = "/traspasos/cuentasPre.elite";
	private final String CONSULTA_TRASPASOS_2 = "/traspasos/estatus.elite";
	
	private final String CONSULTA_TRANSFERENCIAS_PROGRAMADAS_1_1 = "/programados/consultaprogramadosMenu.elite";
	private final String CONSULTA_TRANSFERENCIAS_PROGRAMADAS_1_2 = "/transferencias/consulta.elite";
	private final String CONSULTA_TRANSFERENCIAS_PROGRAMADAS_1_3 = "/transferencias/consultaCmd.elite";
	private final String CONSULTA_TRANSFERENCIAS_PROGRAMADAS_2_1 = "/transferencias/consulta.elite";
	private final String CONSULTA_TRANSFERENCIAS_PROGRAMADAS_2_2 = "/transferencias/consultaCmd.elite";
	
	private final String JSP_TRANSFERENCIAS = "/privada/transferencias/consulta/paso1.jsp";
	private final String JSP_TRANSFERENCIAS_PROGRAMADAS = "/privada/transferencias/consulta/programadas.jsp";	

	private final String DETALLE_CONSULTA_TRANSFERENCIAS = "/transferencias/consultas/detalles.elite";	
	
	private final String PATH_SESSION_REMOVE_PARAMETER_MOVIMIENTO = "/seguridad/sessionRemoveParameter.elite";
	
	private final String SPEI_PROGRAMADO_30="/transferenciasProgramadas/speiProgramado.elite";
	
	public MessageElement findTransferenciaTercerosInvocacion(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try {			
				Connector connector = ConnectorManager.getConnector(transferenciaTercerosRequestTO.getUser());
				log.error("connector "+connector);
				String xml = connector.sendRequest(TRANSFERENCIA_TERCEROS_INVOCACION, null);
				messageElement = XMLDecode.buildXMLMessageElement(xml);
			} catch (URISyntaxException e) {
				throw new DAOException(e);
			} catch (HttpException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			} catch (XmlDecodeException e) {
				throw new DAOException(e);
			}
			return messageElement;
	}

	public MessageElement findTransferenciaTercerosEnvioDatos(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("origen", transferenciaTercerosRequestTO.getOrigen());
		params.put("destino", transferenciaTercerosRequestTO.getDestino());
		params.put("referencia", transferenciaTercerosRequestTO.getReferencia());
		params.put("concepto", transferenciaTercerosRequestTO.getConcepto());
		params.put("importe", transferenciaTercerosRequestTO.getImporte());
		params.put("emailDestino", transferenciaTercerosRequestTO.getEmailConfirmacion());
		params.put("submit", "Transpasar");
				
		
		log.info("params: " + params.toString());
		
		try {
			Connector connector = ConnectorManager.getConnector(transferenciaTercerosRequestTO.getUser());		
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);		
			String xml = connector.sendRequest(TRANSFERENCIA_TERCEROS_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findEjecutarTransferenciaTerceros(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("emailDestino", transferenciaTercerosRequestTO.getEmailConfirmacion());
		//validacion del dispositivo de seguridad
		if(transferenciaTercerosRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", transferenciaTercerosRequestTO.getTokencode());
		}else{
			params.put("clave", transferenciaTercerosRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, transferenciaTercerosRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "traspasosTercerosForm");
		}	
		try {
			Connector connector = ConnectorManager.getConnector(transferenciaTercerosRequestTO.getUser());
			String xml = connector.sendRequest(EJECUTA_TRANSFERENCIA_TERCEROS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement envioMailTerceros(EnvioMailRequestTO envioMailRequestTO )throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("guardar", "false");
		params.put("email", envioMailRequestTO.getCorreo_electronico_destino());
		params.put("mensaje", envioMailRequestTO.getMensaje());
				
		try {
			Connector connector = ConnectorManager.getConnector(envioMailRequestTO.getUser());
			String xml = connector.sendRequest(TRANSFERENCIA_TERCEROS_ENVIO_MAIL, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findTransferenciaSpeiInvocacion(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try {
				Connector connector = ConnectorManager.getConnector(transferenciasSpeiRequestTO.getUser());
				String xml = connector.sendRequest(TRANSFERENCIA_SPEI_INVOCACION, null);
				messageElement = XMLDecode.buildXMLMessageElement(xml);
			} catch (URISyntaxException e) {
				throw new DAOException(e);
			} catch (HttpException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			} catch (XmlDecodeException e) {
				throw new DAOException(e);
			}
			return messageElement;
	}
	
	public MessageElement findTransferenciaSpeiInicio(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try {			
				Connector connector = ConnectorManager.getConnector(transferenciasSpeiRequestTO.getUser());
				Map<String, String> params = new HashMap<String, String>();
				params.put("method", "inicio");
				String xml = connector.sendRequest(TRANSFERENCIA_SPEI_INICIO, params);
				messageElement = XMLDecode.buildXMLMessageElement(xml);
			} catch (URISyntaxException e) {
				throw new DAOException(e);
			} catch (HttpException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			} catch (XmlDecodeException e) {
				throw new DAOException(e);
			}
			return messageElement;
	}
	
	public MessageElement findTransferenciaSpeiEnvioDatos(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method","confirmar");
		params.put("tipotefspeiStr",transferenciasSpeiRequestTO.getTipotefspeiStr());
		params.put("emailDestino","");
		params.put("origen",transferenciasSpeiRequestTO.getOrigen());
		params.put("rfcord",transferenciasSpeiRequestTO.getRfcord());
		params.put("destino",transferenciasSpeiRequestTO.getDestino());
		params.put("bancos",transferenciasSpeiRequestTO.getBancos());
		params.put("tipo",transferenciasSpeiRequestTO.getTipo());
		params.put("beneficiario",transferenciasSpeiRequestTO.getBeneficiario());
		params.put("impuesto",transferenciasSpeiRequestTO.getImpuesto());
		if(transferenciasSpeiRequestTO.getImpuesto().equals("1")){
			params.put("benefRFC",transferenciasSpeiRequestTO.getBenefRFC());
			params.put("benefIVA",transferenciasSpeiRequestTO.getBenefIVA());
		}else{
			params.put("benefRFC","");
			params.put("benefIVA","11");
		}
		params.put("importe",transferenciasSpeiRequestTO.getImporte());
		params.put("referencia",transferenciasSpeiRequestTO.getReferencia());
		params.put("cobranza",transferenciasSpeiRequestTO.getCobranza());
		params.put("fechaProgramacion",transferenciasSpeiRequestTO.getFechaProgramacion());
		params.put("submit","Continuar");
		
		try {
			Connector connector = ConnectorManager.getConnector(transferenciasSpeiRequestTO.getUser());
			//TODO Validar sólo cuando sea nivel medio.
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			log.error("--------------" + params);
			String xml = connector.sendRequest(TRANSFERENCIA_SPEI_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findEjecutarTransferenciaSpei(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		//params.put("tokencode", transferenciasSpeiRequestTO.getTokencode());
		params.put("emailDestino", transferenciasSpeiRequestTO.getEmailDestino());
		params.put("method", "transferir");
		//validacion del dispositivo de seguridad
		if(transferenciasSpeiRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))) {
			params.put("tokencode", transferenciasSpeiRequestTO.getTokencode());
		}else{
			params.put("clave", transferenciasSpeiRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, transferenciasSpeiRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "spei_form");
		}
		try {
			Connector connector = ConnectorManager.getConnector(transferenciasSpeiRequestTO.getUser());
			String xml = connector.sendRequest(EJECUTA_TRANSFERENCIA_SPEI, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement envioMailSpei(EnvioMailRequestTO envioMailRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", envioMailRequestTO.getCorreo_electronico_destino());
		params.put("mensaje", envioMailRequestTO.getMensaje());
		params.put("submit", "Enviar");
		
		try {
			Connector connector = ConnectorManager.getConnector(envioMailRequestTO.getUser());
			String xml = connector.sendRequest(TRANSFERENCIA_SPEI_ENVIO_MAIL, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	
	public MessageElement findTransferenciaTefInvocacion(TransferenciasTEFRequestTO transferenciasTEFRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
				Connector connector = ConnectorManager.getConnector(transferenciasTEFRequestTO.getUser());
				params.put("sessionAttributeName", "tomorrow");
				connector.sendRequest(PATH_SESSION_REMOVE_PARAMETER_MOVIMIENTO, params);
				params.clear();
				String xml = connector.sendRequest(TRANSFERENCIA_TEF_INVOCACION, null);
				messageElement = XMLDecode.buildXMLMessageElement(xml);
			} catch (URISyntaxException e) {
				throw new DAOException(e);
			} catch (HttpException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			} catch (XmlDecodeException e) {
				throw new DAOException(e);
			}
			return messageElement;
	}
	
	public MessageElement findTransferenciaTefEnvioDatos(TransferenciasTEFRequestTO transferenciasTefRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tiporealStr",transferenciasTefRequestTO.getTiporealStr());//.getTipotefspeiStr());
		params.put("emailDestino","");
		params.put("origen",transferenciasTefRequestTO.getOrigen());
		params.put("destino",transferenciasTefRequestTO.getDestino());
		params.put("banco",transferenciasTefRequestTO.getBanco());
		params.put("tipo",transferenciasTefRequestTO.getTipo());
		params.put("beneficiario",transferenciasTefRequestTO.getBeneficiario());
		params.put("importe",transferenciasTefRequestTO.getImporte());
		params.put("referencia",transferenciasTefRequestTO.getReferencia());
		params.put("fechaProgramacion",transferenciasTefRequestTO.getFechaProgramacion());
		params.put("submit","Continuar");
		
		try {
			Connector connector = ConnectorManager.getConnector(transferenciasTefRequestTO.getUser());
			//TODO Validar sólo cuando sea nivel medio.
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			log.error("--------------" + params);
			String xml = connector.sendRequest(TRANSFERENCIA_TEF_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findTefTest(TransferenciasTEFRequestTO transferenciasTEFRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		//validacion del dispositivo de seguridad
		if(transferenciasTEFRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", transferenciasTEFRequestTO.getTokencode());	
		}else{
			params.put("clave", transferenciasTEFRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, transferenciasTEFRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "transferenciasTefsForm");
		}
	try {
			Connector connector = ConnectorManager.getConnector(transferenciasTEFRequestTO.getUser());
			String xml = connector.sendRequest(TRANSFERENCIA_TEF_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findEjecutarTransferenciaTef(TransferenciasTEFRequestTO transferenciasTEFRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("emailDestino", transferenciasTEFRequestTO.getEmailDestino());
		
		//params.put("method", "transferir");//?????
		//validacion del dispositivo de seguridad
		if(transferenciasTEFRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", transferenciasTEFRequestTO.getTokencode());	
		}else{
			params.put("clave", transferenciasTEFRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, transferenciasTEFRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "transferenciasTefsForm");
		}
		
		try {
			Connector connector = ConnectorManager.getConnector(transferenciasTEFRequestTO.getUser());
			String xml = connector.sendRequest(EJECUTA_TRANSFERENCIA_TEF, params);
			xml = connector.sendRequest(EJECUTA_TRANSFERENCIA_TEF_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement envioMailTef(EnvioMailRequestTO envioMailRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", envioMailRequestTO.getCorreo_electronico_destino());
		params.put("mensaje", envioMailRequestTO.getMensaje());
		params.put("submit", "Enviar");
//		params.put("numTxProgramada", "762");
		
		try {
			Connector connector = ConnectorManager.getConnector(envioMailRequestTO.getUser());
			String xml = connector.sendRequest(TRANSFERENCIA_TEF_ENVIO_MAIL, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement findTransferenciaInternacionalInvocacion(TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try {
				Connector connector = ConnectorManager.getConnector(transferenciasInternacionalesRequestTO.getUser());
				String xml = connector.sendRequest(TRANSFERENCIA_INTERNACIONAL_INVOCACION_2, null);
				messageElement = XMLDecode.buildXMLMessageElement(xml);
			} catch (URISyntaxException e) {
				throw new DAOException(e);
			} catch (HttpException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			} catch (XmlDecodeException e) {
				throw new DAOException(e);
			}
			return messageElement;
	}
	
	public MessageElement findTransferenciaInternacionalEnvioDatos(TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();				
		try {
			Connector connector = ConnectorManager.getConnector(transferenciasInternacionalesRequestTO.getUser());
			//TODO Validar sólo cuando sea nivel medio.
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			params.put("origen",transferenciasInternacionalesRequestTO.getOrigen());//transferenciasInternacionalesRequestTO.getClave());
			params.put("destino",transferenciasInternacionalesRequestTO.getDestino());
			params.put("claveBanco",transferenciasInternacionalesRequestTO.getClaveBanco());
			params.put("beneficiario",transferenciasInternacionalesRequestTO.getBeneficiario());
			params.put("apellidoPaterno", transferenciasInternacionalesRequestTO.getApellidoPaterno());
			params.put("apellidoMaterno", transferenciasInternacionalesRequestTO.getApellidoMaterno());
			params.put("nacionalidad", transferenciasInternacionalesRequestTO.getNacionalidad());
			params.put("domicilio", transferenciasInternacionalesRequestTO.getDomicilio());
			if(transferenciasInternacionalesRequestTO.getDia()!=null){
				params.put("dia", transferenciasInternacionalesRequestTO.getDia());
			}else{
				params.put("dia", "0");
			}			
			if(transferenciasInternacionalesRequestTO.getMes()!=null){
				params.put("mes", transferenciasInternacionalesRequestTO.getMes());
			}else{
				params.put("mes", "0");
			}			
			if(transferenciasInternacionalesRequestTO.getAnio()!=null){
				params.put("anio", transferenciasInternacionalesRequestTO.getAnio());
			}else{
				params.put("anio", "0");
			}			
			params.put("lugarNacimiento", transferenciasInternacionalesRequestTO.getLugarNacimiento());
			params.put("referencia",transferenciasInternacionalesRequestTO.getReferencia());
			params.put("importedlls", transferenciasInternacionalesRequestTO.getImporteDlls());
			params.put("tipoCambio", transferenciasInternacionalesRequestTO.getTipoCambio());
			params.put("submit", "Continuar");
			String xml = connector.sendRequest(TRANSFERENCIA_INTERNACIONAL_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	public MessageElement findTransferenciaInternacionalTest (TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		if(transferenciasInternacionalesRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))) {
			params.put("tokencode", transferenciasInternacionalesRequestTO.getTokencode());
		}else{
			params.put("clave", transferenciasInternacionalesRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, transferenciasInternacionalesRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "transferenciasInternacionalesForm");
		}
		
		try{
			Connector connector = ConnectorManager.getConnector(transferenciasInternacionalesRequestTO.getUser());
			connector.sendRequest(EJECUTA_TRANSFERENCIA_INTERNACIONAL, params);
			String xml = connector.sendRequest(EJECUTA_TRANSFERENCIA_INTERNACIONAL_CMD, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		}catch (URISyntaxException e) {
			throw new DAOException(e);
		}catch (HttpException e){
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
	public MessageElement findEjecutarTransferenciaInternacional (TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try{
			Connector connector = ConnectorManager.getConnector(transferenciasInternacionalesRequestTO.getUser());
			//validacion del dispositivo de seguridad
			if(transferenciasInternacionalesRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
				params.put("tokencode", transferenciasInternacionalesRequestTO.getTokencode());	
			}else{
				params.put("clave", transferenciasInternacionalesRequestTO.getClave());
				params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
				params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
				params.put(DigitalFinger.DIGITAL_FINGET_USER, transferenciasInternacionalesRequestTO.getUser());
				params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "transferenciasTefsForm");
			}
			
			connector.sendRequest(EJECUTA_TRANSFERENCIA_INTERNACIONAL, params);
			String xml = connector.sendRequest(EJECUTA_TRANSFERENCIA_INTERNACIONAL_CMD, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		}catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
	public MessageElement envioMailInternacional (EnvioMailRequestTO envioMailRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map <String, String> params = new HashMap<String, String>();
		params.put("email", envioMailRequestTO.getCorreo_electronico_destino());
		params.put("mensaje", envioMailRequestTO.getMensaje());
		params.put("submit", "Enviar");
		
		try{
			Connector connector = ConnectorManager.getConnector(envioMailRequestTO.getUser());
			String xml = connector.sendRequest(TRANSFERENCIA_INTERNACIONAL_ENVIO_MAIL, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		}catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		
		return messageElement;
	}
	 
	
	
	public MessageElement findInternacionalesBancos(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "cargarDatos");
		params.put("pais", internacionalesBancosRequestTO.getPais());
		params.put("ciudad", internacionalesBancosRequestTO.getCiudad());
		try{		
			Connector connector = ConnectorManager.getConnector(internacionalesBancosRequestTO.getUser());
			String xml = connector.sendRequest(INTERNACIONALES_CONSULTA_BANCOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);			
		}catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findInternacionalesPaises(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("control", "0");		
		try{		
			Connector connector = ConnectorManager.getConnector(internacionalesBancosRequestTO.getUser());
			String xml = connector.sendRequest(INTERNACIONALES_CONSULTA_PAISES, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);			
		}catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	
	public MessageElement findInternacionalesCiudades(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "cargarDatos");
		params.put("pais", internacionalesBancosRequestTO.getPais());
		try{		
			Connector connector = ConnectorManager.getConnector(internacionalesBancosRequestTO.getUser());
			String xml = connector.sendRequest(INTERNACIONALES_CONSULTA_BANCOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);			
		}catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findInternacionalesClavesBancos(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "cargarDatos");
		params.put("pais", internacionalesBancosRequestTO.getPais());
		params.put("ciudad", internacionalesBancosRequestTO.getCiudad());
		
		try{		
			Connector connector = ConnectorManager.getConnector(internacionalesBancosRequestTO.getUser());
			String xml = connector.sendRequest(INTERNACIONALES_CONSULTA_BANCOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);			
		}catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement findInitialHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_1_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findInitialServicioHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "init");
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_1_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findStartInitialHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "init");
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_1_3, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findDataHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "consulta");
		params.put("submit", "Consultar");
		params.put("cuenta", consultaTransferenciasRequestTO.getCuenta());
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement findInitialHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRASPASOS_1_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findInitialServicioHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRASPASOS_1_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findStartInitialHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRASPASOS_1_3, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findDataHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("submit", "Consultar");
		params.put("origen", consultaTransferenciasRequestTO.getOrigen());
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRASPASOS_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findInitialTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_PROGRAMADAS_1_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findInitialServicioTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "init");
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_PROGRAMADAS_1_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findStartInitialTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "init");
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_PROGRAMADAS_1_3, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findPreviousDataTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "consulta");
		params.put("submit", "Consultar");
		params.put("cuenta", consultaTransferenciasRequestTO.getCuenta());
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_PROGRAMADAS_2_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findDataTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "consulta");
		params.put("submit", "Consultar");
		params.put("cuenta", consultaTransferenciasRequestTO.getCuenta());
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(CONSULTA_TRANSFERENCIAS_PROGRAMADAS_2_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public void invokeJspTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws DAOException, SessionExpiredException {

		Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());
		Map<String, String> params = new HashMap<String, String>();
		try {
			connector.sendJSPRequest(JSP_TRANSFERENCIAS, params);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}
	
	public void invokeJspTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws DAOException, SessionExpiredException {

		Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());
		Map<String, String> params = new HashMap<String, String>();
		try {
			connector.sendJSPRequest(JSP_TRANSFERENCIAS_PROGRAMADAS, params);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}

	public MessageElement findDetalleHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "generaDetalle");
		params.put("cuenta", consultaTransferenciasRequestTO.getCuenta());
		try {
			Connector connector = ConnectorManager.getConnector(consultaTransferenciasRequestTO.getUser());		
			String xml = connector.sendRequest(DETALLE_CONSULTA_TRANSFERENCIAS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
		
	public MessageElement findTransferenciaTefInicio(TransferenciasTEFRequestTO transferenciasTefRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try {			
				Connector connector = ConnectorManager.getConnector(transferenciasTefRequestTO.getUser());
				Map<String, String> params = new HashMap<String, String>();
				params.put("method", "inicio");
				connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
				String xml = connector.sendRequest(TRANSFERENCIA_SPEI_INICIO, params);
				params.clear();
				xml = connector.sendRequest(TRANSFERENCIA_TEF_INICIO, params);

				messageElement = XMLDecode.buildXMLMessageElement(xml);
			} catch (URISyntaxException e) {
				throw new DAOException(e);
			} catch (HttpException e) {
				throw new DAOException(e);
			} catch (IOException e) {
				throw new DAOException(e);
			} catch (XmlDecodeException e) {
				throw new DAOException(e);
			}
			return messageElement;
	}
	
	public MessageElement insertaSPei30(SpeiProgramadoTO bean)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		
		Connector connector=ConnectorManager.getConnector(bean.getUser());
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("codigo_transaccion", bean.getNum_transaccion());
		params.put("peticion", bean.getPeticion());
		params.put("estado", bean.getEstado());
		params.put("hora_aplicacion", bean.getHora_aplicacion());
		params.put("texto_adjunto", bean.getTexto_adjunto());
		params.put("id_usuario", bean.getId_usuario());
		params.put("id_email", bean.getId_email());
		params.put("nombre_transaccion", bean.getNombre_transaccion());
		XMLDecode decoder = new XMLDecode();
		try {
			String xml=connector.sendRequest(SPEI_PROGRAMADO_30, params);
			messageElement = decoder.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
}