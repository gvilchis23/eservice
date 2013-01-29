package com.bancoazteca.elite.ejb.preferencias;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;

import com.bancoazteca.elite.beans.AltasUsuariosRequestTO;
import com.bancoazteca.elite.beans.ConsultaExpressRequestTO;
import com.bancoazteca.elite.beans.RecuperaPasswordRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.connector.DigitalFinger;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.InvalidCertificateException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

public class PreferenciasDAO extends EliteDAO  {
	
	//private final String PAQUETES="/Rma/Paquetes.elite";
	private final String ALTAS_USUARIOS="/activacion/inicio.elite";
	private final String ALTAS_USUARIOS_VALIDACION="/activacion/Verifica.elite";
	private final String ALTAS_USUARIOS_CONTRATO="/activacion/registro.elite";
	private final String ALTAS_USUARIOS_ALIAS="/activacion/registrarDatosUsuario.elite";
	private final String ALTAS_USUARIOS_FINAL="/activacion/registrarPasswordAcceso.elite";
	private final String CONSULTA_EXPRESS_1="/consultaExpress/saldo.elite";
	
	
	
	
	
	
	public MessageElement consultarCuentaActivar(AltasUsuariosRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("cuenta", requestTO.getCuenta_cargo());
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.registerConnector(requestTO.getCuenta_cargo());
			xml = con.sendRequest( ALTAS_USUARIOS, params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (InvalidCertificateException e) {
			throw new DAOException(e);
		}
		return msg;
	}
	
	public MessageElement validarDatosActivar(AltasUsuariosRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("nip", requestTO.getNip());
		params.put("nipv", requestTO.getConfirm_nip());
		params.put("ndia", requestTO.getDia());
		params.put("nmes", requestTO.getMes());
		params.put("nano", requestTO.getA�o());
		params.put("nombre", requestTO.getNombre());
		params.put("paterno", requestTO.getApellido_paterno());
		params.put("materno", requestTO.getApellido_materno());
		
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getCuenta_cargo());
			xml = con.sendRequest( ALTAS_USUARIOS_VALIDACION, params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 

		return msg;
	}
	
	public MessageElement validarContrato(AltasUsuariosRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getCuenta_cargo());
			xml = con.sendRequest( ALTAS_USUARIOS_CONTRATO, params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 

		return msg;
	}
	
	public MessageElement consultarUsuarioDisponible(AltasUsuariosRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "Disponibilidad");
		params.put("alias", requestTO.getAlias());
		
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getCuenta_cargo());
			xml = con.sendRequest( ALTAS_USUARIOS_ALIAS, params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 

		return msg;
	}
	
	public MessageElement registrarUsuario(AltasUsuariosRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "datosUsuario");
		params.put("alias", requestTO.getAlias());
		params.put("pregunta", requestTO.getPregunta());
		params.put("respuesta", requestTO.getRespuesta());
		params.put("email", requestTO.getEmail());
		params.put("celular", "5532896712");
		params.put("company", "1");
		params.put("compania", "1");
		params.put("telefonoOficina", "55786212");
		
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getCuenta_cargo());
			xml = con.sendRequest( ALTAS_USUARIOS_ALIAS, params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 

		return msg;
	}
	
	public MessageElement activarUsuario(AltasUsuariosRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordAcceso");
		params.put("password", requestTO.getContrasena());
		params.put("vpassword", requestTO.getConfirmarContrasena());
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getCuenta_cargo());
			xml = con.sendRequest( ALTAS_USUARIOS_FINAL, params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 

		return msg;
	}
	
	//consultas express
	
	public MessageElement consultarCuentaExpress(ConsultaExpressRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("tarjeta", requestTO.getTarjetaCuenta());
		params.put("action", requestTO.getOperacion());
		//params.put("tokencode", "123456");
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.registerConnector(requestTO.getTarjetaCuenta());
			//xml = con.sendRequest(CONSULTA_EXPRESS_1, params);
			xml = con.sendRequest("/consultaExpress2/saldo.elite", params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (InvalidCertificateException e) {
			throw new DAOException(e);
		}
		return msg;
	}
	
	public MessageElement validarCuentaExpress(ConsultaExpressRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("tokencode", "123456");
		//validacion del dispositivo de seguridad
		if(requestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", requestTO.getTokencode());
		}else if(requestTO.getOptionDispositive().equals(String.valueOf(FINGER_DISPOSITIVE))){
			params.put("clave", requestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, requestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "ConsultaExpressForm");
		}else if(requestTO.getOptionDispositive().equals(String.valueOf(NIP_DISPOSITIVE))){
			params.put("nipForm", requestTO.getNip());
		}
		
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getTarjetaCuenta());
			//xml = con.sendRequest(CONSULTA_EXPRESS_1, params);
			xml = con.sendRequest("/consultaExpress2/saldo.elite", params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 
		return msg;
	}
	
	public MessageElement cerrarCuentaExpress(ConsultaExpressRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("close", "true");
		
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getTarjetaCuenta());
			//xml = con.sendRequest(CONSULTA_EXPRESS_1, params);
			xml = con.sendRequest("/consultaExpress/saldo.elite", params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 
		return msg;
	}
	
	public MessageElement getRecibosCuentaExpress(ConsultaExpressRequestTO requestTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "seleccion");
		
		
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.getConnector(requestTO.getTarjetaCuenta());
			//xml = con.sendRequest(CONSULTA_EXPRESS_1, params);
			xml = con.sendRequest("/snominaExpress/recibos/consultaNominaRecibosSeleccion.elite", params);
			msg = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} 
		return msg;
	}
	

	
	



	

	
	

}
