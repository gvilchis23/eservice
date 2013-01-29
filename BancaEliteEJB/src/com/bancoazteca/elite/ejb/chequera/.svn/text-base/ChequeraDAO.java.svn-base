package com.bancoazteca.elite.ejb.chequera;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ActivacionChequeraRequestTO;
import com.bancoazteca.elite.beans.ChequeraRoboExtravioRequestTO;
import com.bancoazteca.elite.beans.ConsultaChequesRequestTO;
import com.bancoazteca.elite.beans.ConsultaPresolicitudDaoTO;
import com.bancoazteca.elite.beans.LiberacionChequesRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.connector.DigitalFinger;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;

/**
 * En la clase ChequeraDAO se declaran todos los paths y los parámetros,<br/> 
 * necesarios para que el servlet del conector elite invoque las acciones correspondientes a los pagos de servicios desde el portal como CFE, AXTEL, telcel, etc.<br/>
 * @return <b>org.apache.axis.message.MessageElement </b><br/>
 * 			En la invocación de las acciones se tiene como respuesta un MessageElement con la información recopilada.
 */

public class ChequeraDAO extends EliteDAO  {
	
	private static final Logger log = Logger.getLogger(ChequeraDAO.class);
	
	private final String LIBERACION_CHEQUES_SOLICITUD_1 = "/cheques/LiberacionPre.elite";
	private final String ACTIVACION_CHEQUERA_1_1 = "/cheques/ActivacionPre.elite";
	
	private static final String PRESOLICITUD_CHEQUERA_INICIO="/privada/cheques/solicitud/initSolicitudPriv3.jsp";
	private static final String PRESOLICITUD_CHEQUERA_INICIO_1="/cheques/Solicitud.elite";
	private static final String PRESOLICITUD_CHEQUERA_INICIO_PRE="/cheques/SolicitudPre.elite";
	private static final String PRESOLICITUD_CHEQUERA_INICIO_DETALLE_CUENTA="/cheques/Solicitud.elite";
	private static final String PRESOLICITUD_CHEQUERA_INICIO_DETALLE_CUENTA_PRE="/cheques/SolicitudPre.elite";
	
	private final String CHEQUERA_ROBO_BLOQUEO = "/cheques/Bloqueo.elite";
	private final String CHEQUERA_ROBO_BLOQUEO_PRE = "/cheques/BloqueoPre.elite";
	
	private static final String CONSULTA_CHEQUES_JSP_01 ="/privada/cheques/solicitud/initSolicitudPriv3.jsp";
	private static final String CONSULTA_CHEQUES_01="/cheques/Solicitud.elite";
	private static final String CONSULTA_CHEQUES_02="/cheques/SolicitudPre.elite";
	private static final String CONSULTA_CHEQUES_03="/chequera/consultar.elite";
	private static final String CONSULTA_CHEQUES_04="/cheques/consulta/detalleCheques.jsp";

	public MessageElement liberacionChequesSolicitaCuenta(LiberacionChequesRequestTO request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goInit");

		Connector connector = ConnectorManager.getConnector(request.getUser());
		
		try {
		String xml = connector.sendRequestPost( LIBERACION_CHEQUES_SOLICITUD_1, params );
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

	public MessageElement liberacionChequesSolicitaChequeras(LiberacionChequesRequestTO request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put( "method", "goConsultaCuenta" );
		params.put( "fechaSolicitud", request.getFechaSolicitud() );
		params.put( "cuenta", request.getCuenta() );
		Connector connector = ConnectorManager.getConnector( request.getUser() );
		
		try {
			String xml = connector.sendRequestPost(LIBERACION_CHEQUES_SOLICITUD_1, params);
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
	
	public MessageElement liberacionChequesSolicitaCheque(LiberacionChequesRequestTO request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put( "method", "goConsultaCheques" );
		params.put( "fechaSolicitud", request.getFechaSolicitud() );
		params.put( "activar", request.getActivar() );
		Connector connector = ConnectorManager.getConnector( request.getUser() );
		
		try {
			String xml = connector.sendRequestPost(LIBERACION_CHEQUES_SOLICITUD_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement liberacionChequesSolicitaPWD(LiberacionChequesRequestTO request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goSolicitudPWD");
		params.put("fechaSolicitud", request.getFechaSolicitud());
		params.put("activar", request.getActivar());	
		params.put("numeroCheque", request.getNumeroCheque());
		params.put("montoCheque", request.getMontoCheque());
		Connector connector = ConnectorManager.getConnector(request.getUser());
		
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequestPost(LIBERACION_CHEQUES_SOLICITUD_1, params);
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
	
	public MessageElement liberacionChequesEjecutaPWD(LiberacionChequesRequestTO request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goValidaPWD");
		params.put("fechaSolicitud", request.getFechaSolicitud());

		if(request.getOpcionActivacion().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", request.getClave());
		}else{
			params.put("clave", request.getClave());
		}
		Connector connector = ConnectorManager.getConnector(request.getUser());
		
		try {
			String xml = connector.sendRequestPost(LIBERACION_CHEQUES_SOLICITUD_1, params);
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

	public MessageElement findRoboExtravioInicio(ChequeraRoboExtravioRequestTO extravioRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goInit");
				
		try {			
				Connector connector = ConnectorManager.getConnector(extravioRequestTO.getUser());
				String xml = connector.sendRequestPost(CHEQUERA_ROBO_BLOQUEO, params);
				xml = connector.sendRequestPost(CHEQUERA_ROBO_BLOQUEO_PRE, params);
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
	
	public MessageElement findExtravioObtencionChequera(ChequeraRoboExtravioRequestTO extravioRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goConsultaCuenta");
		params.put("fechaSolicitud", extravioRequestTO.getFechaSolicitud());
		params.put("cuenta", extravioRequestTO.getNumeroCuenta());
		
		try {			
				Connector connector = ConnectorManager.getConnector(extravioRequestTO.getUser());
				String xml = connector.sendRequest(CHEQUERA_ROBO_BLOQUEO_PRE, params);
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

	public MessageElement findExtravioObtencionMotivoReporte(ChequeraRoboExtravioRequestTO extravioRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("fechaSolicitud", extravioRequestTO.getFechaSolicitud());
		params.put("chequera", extravioRequestTO.getIdChequera());
		switch( extravioRequestTO.getTipoOperacion() ){
			case CHEQUE:
				params.put("method","goChequeMotivo");
				params.put("tipoOperacion", "goChequeMotivo");
				break;
			case CHEQUERAS:
				params.put("method","goChequeraMotivo");
				params.put("tipoOperacion", "goChequeraMotivo");
				break;
		}
						
		try {			
				Connector connector = ConnectorManager.getConnector(extravioRequestTO.getUser());
				String xml = connector.sendRequest(CHEQUERA_ROBO_BLOQUEO_PRE, params );
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

	public MessageElement findExtravioValidaInformacion(ChequeraRoboExtravioRequestTO extravioRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("fechaIni", extravioRequestTO.getFechaSolicitud());
		switch(extravioRequestTO.getTipoOperacion() ){
		case CHEQUE:
			params.put("method","goSeleccionCheques");
			params.put("tipoOperacion", "goSeleccionCheques");
			params.put("seleccionOper", "on");
			switch( extravioRequestTO.getTipoReporteCheque()){
			case INDIVIDUAL:
				params.put("motivoBloqueoRoboExtravio","0");
				params.put("numeroCheque", extravioRequestTO.getNumeroCheque());
				params.put("chequeInicialB", "0");
				params.put("chequeFinalB", "0");
				break;
			case BLOQUE:
				params.put("motivoBloqueoRoboExtravio","1");
				params.put("numeroCheque", "0");
				params.put("chequeInicialB", extravioRequestTO.getNumeroChequeInicial());
				params.put("chequeFinalB", extravioRequestTO.getNumeroChequeFinal());
				break;
			}
			break;
		case CHEQUERAS:
			params.put("method","goSeleccion");
			params.put("tipoOperacion", "goSeleccion");
			params.put("motivoBloqueoRoboExtravio", "1");
			break;
		}
				
		try {			
				Connector connector = ConnectorManager.getConnector(extravioRequestTO.getUser());
				String xml = connector.sendRequest(CHEQUERA_ROBO_BLOQUEO, params );
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
	
	public MessageElement findExtravioEjecucion(ChequeraRoboExtravioRequestTO extravioRequestTO)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("confirmacion", "S");
		switch( extravioRequestTO.getTipoOperacion() ){
		case CHEQUE:
			params.put("method","goChequeConfirmacion");
			break;
		case CHEQUERAS:
			params.put("method","goChequeraConfirmacion");
			break;
		}
							
		try {			
				Connector connector = ConnectorManager.getConnector(extravioRequestTO.getUser());
				String xml = connector.sendRequest(CHEQUERA_ROBO_BLOQUEO_PRE, params);
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
	
	public MessageElement getActivacionChequeSolicitud( ActivacionChequeraRequestTO requestTO ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goInit" );

		Connector connector = ConnectorManager.getConnector( requestTO.getUser() );
		try {			
			String xml = connector.sendRequestPost( ACTIVACION_CHEQUERA_1_1,  params );
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

	public MessageElement getActivacionChequeSeleccionCuenta( ActivacionChequeraRequestTO requestTO ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		String cuenta = requestTO.getNumeroCuenta() + "-" + requestTO.getDescripcionCuenta();
		params.put("method", "goConsultaCuenta" );
		params.put( "fechaSolicitud", requestTO.getFechaSolicitud() );
		params.put( "cuenta", cuenta );

		Connector connector = ConnectorManager.getConnector( requestTO.getUser() );
		try {
			String xml = connector.sendRequestPost( ACTIVACION_CHEQUERA_1_1,  params );
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

	public MessageElement getActivacionChequeValidacion( ActivacionChequeraRequestTO requestTO ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goSolicitudPWD" );
		String chequera = requestTO.getChequeInicial() + "-" + requestTO.getChequeFinal() + "," + requestTO.getDescripcionChequera();
		params.put( "activar", chequera );

		Connector connector = ConnectorManager.getConnector( requestTO.getUser() );
		try {
			connector.sendRequestPost( PATH_UPLOAD_VAR_SESSION, null );
			String xml = connector.sendRequestPost( ACTIVACION_CHEQUERA_1_1,  params );
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

	public MessageElement getActivacionChequeEjecucion( ActivacionChequeraRequestTO requestTO ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "goValidaPWD" );
		params.put("fechaActivacion", requestTO.getFechaActivacion() );

		if( requestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", requestTO.getTokencode());
		}else{
			params.put("clave", requestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, requestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "activacionChequesForm");
		}
		
		Connector connector = ConnectorManager.getConnector( requestTO.getUser() );
		try {
			String xml = connector.sendRequestPost( ACTIVACION_CHEQUERA_1_1,  params );
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

	public void presolicitudChequeraInicioJSP(String usuario) throws DAOException,SessionExpiredException{
		Connector connector=ConnectorManager.getConnector(usuario);
		try {
			connector.sendJSPRequest(PRESOLICITUD_CHEQUERA_INICIO, null);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}
	
	public MessageElement presolicitudChequeraInicio(String usuario) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goInit");
		Connector connector=ConnectorManager.getConnector(usuario);
		try {
			xml=connector.sendRequest(PRESOLICITUD_CHEQUERA_INICIO_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement presolicitudChequeraInicioPre(String usuario) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goInit");
		Connector connector=ConnectorManager.getConnector(usuario);
		try {
			xml=connector.sendRequest(PRESOLICITUD_CHEQUERA_INICIO_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	
	public MessageElement presolicitudChequeraInicioDetalleCuenta(ConsultaPresolicitudDaoTO to) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goConsultaCuenta");
		params.put("fechaSolicitud", Formatter.formatFecha(new java.util.Date()));
		params.put("cuenta", to.getCuenta());
		params.put("regimen", "-1");
		params.put("tipoChequera", "-1");
		params.put("proteccion", "N");
		params.put("montoPiso", "0.0");
		Connector connector=ConnectorManager.getConnector(to.getUsuario());
		try {
			xml=connector.sendRequestPost(PRESOLICITUD_CHEQUERA_INICIO_DETALLE_CUENTA, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	public MessageElement presolicitudChequeraInicioDetalleCuentaPre(String usuario) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goConsultaCuenta");
		Connector connector=ConnectorManager.getConnector(usuario);
		try {
			xml=connector.sendRequest(PRESOLICITUD_CHEQUERA_INICIO_DETALLE_CUENTA_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement presolicitudChequesValidacion(ConsultaPresolicitudDaoTO to) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goGeneraPdf");
		params.put("fechaSolicitud", to.getFechaSolicitud());
		params.put("cuenta", to.getCuenta());
		params.put("regimen", to.getRegimen());
		params.put("tipoChequera", to.getTipoChequera());
		params.put("proteccion", to.getProteccion());
		params.put("montoPiso", to.getMontoPiso());
		Connector connector=ConnectorManager.getConnector(to.getUsuario());
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			xml=connector.sendRequest(PRESOLICITUD_CHEQUERA_INICIO_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement presolicitudChequesValidacionPre(String usuario) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goGeneraPdf");
		Connector connector=ConnectorManager.getConnector(usuario);
		try {
			xml=connector.sendRequest(PRESOLICITUD_CHEQUERA_INICIO_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement presolicitudChequesEjecucion(ConsultaPresolicitudDaoTO to) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goGeneraPdfSistema");
		try {
			Connector connector=ConnectorManager.getConnector(to.getUsuario());
			
			if(to.getOptionDispositive().equals( "" + TOKEN_DISPOSITIVE ) ){
				params.put( "tokencode", to.getTokenCode() );
			}else{
				params.put( "clave", to.getClave() );
				params.put( DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET );
				params.put( DigitalFinger.DIGITAL_FINGET_VALUE, "clave" );
				params.put( DigitalFinger.DIGITAL_FINGET_USER, to.getUsuario() );
				params.put( DigitalFinger.DIGITAL_FINGET_OBJECT, "solicitudChequesForm" );
			}
			
			xml=connector.sendRequest(PRESOLICITUD_CHEQUERA_INICIO_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement presolicitudChequesEjecucionPre(String usuario) throws DAOException,SessionExpiredException{
		MessageElement messageElement = null;
		String xml="";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "goGeneraPdfSistema");
		Connector connector=ConnectorManager.getConnector(usuario);
		try {
			xml=connector.sendRequest(PRESOLICITUD_CHEQUERA_INICIO_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	/////////////////////////////////////////
	
	
	public void jspInitSolicitudPriv3( String user)throws LoginException, DAOException, SessionExpiredException{
		 Map<String , String> map = new HashMap<String, String>();
		try {
			map.put("method","goInit");
			Connector connector = ConnectorManager.getConnector(user);	
			connector.sendJSPRequest(CONSULTA_CHEQUES_JSP_01,null);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}  
	}
	public MessageElement solicitudConsultarChequera01( String user)throws LoginException, DAOException, SessionExpiredException{
		MessageElement messageElement  = null;
		String xml= null;
		 Map<String , String> map = new HashMap<String, String>();
		try {
			map.put("method","goInit");
			Connector connector = ConnectorManager.getConnector(user);	
			xml= connector.sendRequestPost(CONSULTA_CHEQUES_01, map);
			if(xml != null && xml.trim() != "")
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}   catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	public MessageElement solicitudConsultarChequera02( String user)throws LoginException, DAOException, SessionExpiredException{
		MessageElement messageElement  = null;
		String xml= null;
		Map<String , String> map = new HashMap<String, String>();
		try {
			map.put("method","goInit");
			Connector connector = ConnectorManager.getConnector(user);				
			xml= connector.sendRequestPost(CONSULTA_CHEQUES_02, map);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}   catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	public MessageElement ejecutarConsultaCheques( ConsultaChequesRequestTO chequesRequestTO )throws LoginException, DAOException, SessionExpiredException{
		MessageElement messageElement  = null;
		String xml= null;
		try {
			Connector connector = ConnectorManager.getConnector(chequesRequestTO.getUser());
			xml= connector.sendMultiKeyRequest(CONSULTA_CHEQUES_03, chequesRequestTO.getParametros());
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}   catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	public void ejecutarJsp( String user )throws LoginException, DAOException, SessionExpiredException{
		try {
			Connector connector = ConnectorManager.getConnector( user );
			connector.sendJSPRequest(CONSULTA_CHEQUES_04, null);
		} catch (URISyntaxException e) {
		} catch (HttpException e) {
		} catch (IOException e) {
		}
		return ;
	}
}