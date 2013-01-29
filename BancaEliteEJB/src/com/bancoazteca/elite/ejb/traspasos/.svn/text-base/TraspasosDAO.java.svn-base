package com.bancoazteca.elite.ejb.traspasos;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;

import com.bancoazteca.elite.beans.TraspasosPropiasRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

/**
 * En la clase TranspasosDAO se declaran todos los paths y los parámetros,<br/> 
 * necesarios para que el servlet del conector elite invoque las acciones correspondientes a los traspasos de dinero entre cuentas propias.<br/>
 * @return <b>org.apache.axis.message.MessageElement </b><br/>
 * 			En la invocación de las acciones se tiene como respuesta un MessageElement con la información recopilada.
 */

public class TraspasosDAO extends EliteDAO{
	
	private final String INVOCA_TRASPASO = "/traspasos/propias.elite";
	private final String INICIA_TRASPASO = "/traspasos/propiasPre.elite";
	private final String PREPARA_TRASPASO = "/traspasos/propiasConfirmar.elite";
	private final String EJECUTA_TRASPASO = "/traspasos/propiasEjecutarCmd.elite";
		
	
	public MessageElement setInvocaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws SessionExpiredException, DAOException{
		MessageElement messageElement = new MessageElement();
		String xml = null;
		
		try {
			Connector connector = ConnectorManager.getConnector(traspasosPropiasRequestTO.getUser());			
			xml = connector.sendRequest(INVOCA_TRASPASO, null);			
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);						
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException (e);
		}
		
		return messageElement;
	}
	
	public MessageElement setIniciaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws SessionExpiredException, DAOException{
		MessageElement messageElement = new MessageElement();
		String xml = null;
		
		try {
			Connector connector = ConnectorManager.getConnector(traspasosPropiasRequestTO.getUser());			
			xml = connector.sendRequest(INICIA_TRASPASO, null);			
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);						
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException (e);
		}
		
		return messageElement;
	}
	
	public MessageElement setPropiasPreparaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws SessionExpiredException, DAOException{
		MessageElement messageElement = new MessageElement();
		Map<String, String> params = new HashMap<String, String>();
		params.put("destino", traspasosPropiasRequestTO.getCuentaDestinoIndex());
		params.put("origen", traspasosPropiasRequestTO.getCuentaOrigenIndex());
		params.put("importe", traspasosPropiasRequestTO.getImporte());
		params.put("concepto", traspasosPropiasRequestTO.getConcepto());
		params.put(traspasosPropiasRequestTO.getNombreSubmit(), traspasosPropiasRequestTO.getValorSubmit());
		String xml = null;
		
		try {
			Connector connector = ConnectorManager.getConnector(traspasosPropiasRequestTO.getUser());			
			xml = connector.sendRequest(PREPARA_TRASPASO, params);			
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);						
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException (e);
		}
		
		return messageElement;
	}
	
	public MessageElement setPropiasEjecutaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws SessionExpiredException, DAOException{
		MessageElement messageElement = new MessageElement();
		Map<String, String> params = new HashMap<String, String>();
		params.put("destino", traspasosPropiasRequestTO.getCuentaDestinoIndex());
		params.put("origen", traspasosPropiasRequestTO.getCuentaOrigenIndex());
		params.put("importe", traspasosPropiasRequestTO.getImporte());
		params.put("concepto", traspasosPropiasRequestTO.getConcepto());
		params.put("clave", "1");
		String xml = null;
		
		try {
			Connector connector = ConnectorManager.getConnector(traspasosPropiasRequestTO.getUser());
			xml = connector.sendRequest(EJECUTA_TRASPASO, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);				
		} catch (URISyntaxException e) {
			throw new DAOException(e.getCause());
		} catch (HttpException e) {
			throw new DAOException(e.getCause());
		} catch (IOException e) {
			throw new DAOException(e.getCause());
		} catch (XmlDecodeException e) {
			throw new DAOException(e.getCause());
		}
		return messageElement;
	}
	
	
}
