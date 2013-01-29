package com.bancoazteca.elite.ejb.dispositivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;

import com.bancoazteca.elite.beans.ActivacionFirmaRequestTO;
import com.bancoazteca.elite.beans.BloqueoFirmaRequestTO;
import com.bancoazteca.elite.beans.RastreoPedidoRequestTO;
import com.bancoazteca.elite.beans.SincronizacionFirmaRequestTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;

/**
 * En la clase DispositivoDAO se declaran todos los paths y los parámetros,<br/> 
 * necesarios para que el servlet del conector elite invoque las acciones correspondientes con los dispositivos de seguridad.<br/>
 * @return <b>org.apache.axis.message.MessageElement </b><br/>
 * 			En la invocación de las acciones se tiene como respuesta un MessageElement con la información recopilada.
 */
public class DispositivoDAO extends EliteDAO {
	
//	private static final Logger log = Logger.getLogger(DispositivoDAO.class);
	
	private final String RASTREO_PEDIDO_1 ="/dispositivos/resultadoRastreoCmd.elite";
	
	private final String BLOQUEO_DESBLOQUEO_CANCELACION_1 ="/dispositivos/BloDesCanFAPre.elite";
	private final String BLOQUEO_DESBLOQUEO_CANCELACION_2 = "/dispositivos/ConfirmaBDC.elite";
	private final String BLOQUEO_DESBLOQUEO_CANCELACION_3_1 = "/dispositivos/BloqueoPre.elite";
	private final String BLOQUEO_DESBLOQUEO_CANCELACION_3_2 = "/dispositivos/DesbloqueoPre.elite";
	private final String BLOQUEO_DESBLOQUEO_CANCELACION_3_3 = "/dispositivos/CancelarPre.elite";
	
	private final String ACTIVACION_FIRMA_SOLICITUD_1 = "/dispositivos/firmaAztecaInicioPre.elite";
	private final String ACTIVACION_FIRMA_VALIDACION_2_1 = "/dispositivos/firmaAztecaContrato.elite";
	private final String ACTIVACION_FIRMA_VALIDACION_2_2 = "/dispositivos/firmaAztecaSyncro.elite";
	private final String ACTIVACION_FIRMA_VALIDACION_2_3 = "/dispositivos/firmaAztecaSyncroPre.elite";
	private final String ACTIVACION_FIRMA_VALIDACION_2_4 = "/dispositivos/solicitudToken/paso1.jsp";
	private final String ACTIVACION_FIRMA_VALIDACION_3 = "/dispositivos/firmaAztecaConfirmaPre.elite";
	
	private final String SOLICITUD_DISPOSITIVOS_1_1 = "/privada/administracion/dispositivos/solicitud.jsp";
	private final String SOLICITUD_DISPOSITIVOS_1_2 = "/dispositivos/solicitudInicioCmd.elite";
	private final String SOLICITUD_DISPOSITIVOS_1_3 = "/dispositivos/solicitudToken/paso1.jsp";
	private final String SOLICITUD_DISPOSITIVOS_1_4 = "/seguridad/dispositivosMenu.elite";
	
	private final String SOLICITUD_DISPOSITIVOS_2_1 = "/dispositivos/solicitudInicioCmd.elite";
	private final String SOLICITUD_DISPOSITIVOS_3_1 = "/dispositivos/lugarEntrega.elite";
	private final String SOLICITUD_DISPOSITIVOS_4_1 = "/dispositivos/confirmacionCmd.elite";
	
	private final String SINCRONIZACION_FIRMA_SOLICITUD_1 = "/dispositivos/SyncroFA.elite";
	private final String SINCRONIZACION_FIRMA_VALIDACION_1 = "/dispositivos/Sincroniza.elite";
	private final String SINCRONIZACION_FIRMA_EJECUCION_1_1 = "/dispositivos/SyncroFin.elite";
	private final String SINCRONIZACION_FIRMA_EJECUCION_1_2 = "/dispositivos/SyncroFinPre.elite";

	public MessageElement resultadoRastreoCmd(RastreoPedidoRequestTO rastreoPedidoTO) throws DAOException, SessionExpiredException {
		String xml = null;
		XMLDecode decoder = new XMLDecode();

		Map<String, String> params = new HashMap<String, String>();
		params.put("numPedido", rastreoPedidoTO.getNumeroPedido());
		
		MessageElement messageElement = null;
		try {
			Connector con = ConnectorManager.getConnector(rastreoPedidoTO.getUser());
			xml = con.sendRequestPost(RASTREO_PEDIDO_1, params);
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
	
	public MessageElement solicitaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();
		
		try {
			Connector con = ConnectorManager.getConnector(bloqueoFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(BLOQUEO_DESBLOQUEO_CANCELACION_1, null);
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
	
	public MessageElement validaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();
		String tipoServicio = bloqueoFirmaRequestTO.getTipoServicio().toString().toLowerCase();
		String inicial = ""+tipoServicio.charAt(0);
		tipoServicio = tipoServicio.replace(inicial, inicial.toUpperCase());
		String submit = tipoServicio + " Firma Azteca";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("numToken", bloqueoFirmaRequestTO.getNumeroSerie());
		params.put("submit", submit);

		try {
			Connector con = ConnectorManager.getConnector(bloqueoFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(BLOQUEO_DESBLOQUEO_CANCELACION_2, params);
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
	
	public MessageElement ejecutaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("submit", "Confirmar Bloqueo");
		
		try {
			Connector con = ConnectorManager.getConnector(bloqueoFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(BLOQUEO_DESBLOQUEO_CANCELACION_3_1 , params);
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
	
	public MessageElement ejecutaDesbloqueo(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", bloqueoFirmaRequestTO.getTokenCode());
		params.put("submit", "Confirmar");
		
		try {
			Connector con = ConnectorManager.getConnector(bloqueoFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(BLOQUEO_DESBLOQUEO_CANCELACION_3_2 , params);
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
	
	public MessageElement ejecutaCancelacion(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("submit", "Confirmar Cancelación");
		
		try {
			Connector con = ConnectorManager.getConnector(bloqueoFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(BLOQUEO_DESBLOQUEO_CANCELACION_3_3 , params);
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
	
	public MessageElement solicitaActivacion(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		
		try {
			Connector con = ConnectorManager.getConnector(activacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(ACTIVACION_FIRMA_SOLICITUD_1 , null);
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
	
	public MessageElement validaNumeroSerieActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("numSerie", activacionFirmaRequestTO.getNumeroSerie());
		params.put("submit", "Confirmar");
		
		try {
			Connector con = ConnectorManager.getConnector(activacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(ACTIVACION_FIRMA_VALIDACION_2_1 , params);
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
	
	public MessageElement validaTokenActivacion(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("numSerie", activacionFirmaRequestTO.getNumeroSerie());
		params.put("preToken", activacionFirmaRequestTO.getPreToken());
		params.put("actToken", activacionFirmaRequestTO.getActToken());
		params.put("submit", "Confirmar");
		
		try {
			Connector con = ConnectorManager.getConnector(activacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(ACTIVACION_FIRMA_VALIDACION_2_2 , params);
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
	
	public MessageElement validaTokenActivacionPre(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("numSerie", activacionFirmaRequestTO.getNumeroSerie());
		params.put("preToken", activacionFirmaRequestTO.getPreToken());
		params.put("actToken", activacionFirmaRequestTO.getActToken());
		params.put("submit", "Confirmar");
		
		try {
			Connector con = ConnectorManager.getConnector(activacionFirmaRequestTO.getUser());
			con.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = con.sendRequestPost(ACTIVACION_FIRMA_VALIDACION_2_3 , params);
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
	
	public MessageElement validaTokenActivacionJSP(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;	
		try {
			Connector con = ConnectorManager.getConnector(activacionFirmaRequestTO.getUser());
			con.sendJSPRequest(ACTIVACION_FIRMA_VALIDACION_2_4, null);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			//Error que se cachar a propósito y se ignora
		} catch (IOException e) {
			throw new DAOException(e);
		} 

		return messageElement;
	}
	
	public MessageElement ejecutaActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", activacionFirmaRequestTO.getEmail());
		params.put("telefono", activacionFirmaRequestTO.getTelefonoParticular());
		params.put("telefonoCel", activacionFirmaRequestTO.getTelefonoCelular());
		params.put("carrier", activacionFirmaRequestTO.getCompaniaCelular());
		params.put("acepto", activacionFirmaRequestTO.getAceptoContrato());
		params.put("submit", "Confirmar");
		
		if(activacionFirmaRequestTO.getOpcionActivacion().equals(String.valueOf(NIP_DISPOSITIVE))){
			params.put("cuentas", activacionFirmaRequestTO.getCuentaCargo());
			params.put("nip", activacionFirmaRequestTO.getNip());
		}else{
			params.put("clave", activacionFirmaRequestTO.getClave());
		}
		
		try {
			Connector con = ConnectorManager.getConnector(activacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(ACTIVACION_FIRMA_VALIDACION_3 , params);
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
	
	public MessageElement solicitaSincronizaToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();
		
		try {
			Connector con = ConnectorManager.getConnector(sincronizacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(SINCRONIZACION_FIRMA_SOLICITUD_1, null);
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
	
	public MessageElement validaNumeroSerieSincronizaToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("numToken", sincronizacionFirmaRequestTO.getNumeroSerie());
		params.put("submit", "Sincroniza");

		try {
			Connector con = ConnectorManager.getConnector(sincronizacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(SINCRONIZACION_FIRMA_VALIDACION_1, params);
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
	
	public MessageElement ejecutaSincronizaToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("preToken", sincronizacionFirmaRequestTO.getPreToken());
		params.put("actToken", sincronizacionFirmaRequestTO.getActToken());
		params.put("submit", "Continuar");

		try {
			Connector con = ConnectorManager.getConnector(sincronizacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(SINCRONIZACION_FIRMA_EJECUCION_1_1, params);
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

	public MessageElement ejecutaSincronizaTokenPre(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("preToken", sincronizacionFirmaRequestTO.getPreToken());
		params.put("actToken", sincronizacionFirmaRequestTO.getActToken());
		params.put("submit", "Continuar");

		try {
			Connector con = ConnectorManager.getConnector(sincronizacionFirmaRequestTO.getUser());
			String xml = con.sendRequestPost(SINCRONIZACION_FIRMA_EJECUCION_1_2, params);
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
	
	public MessageElement setInitialSolicitudDispositivo( SolicitudDispositivoRequestTO request ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		
		try {
			Connector con = ConnectorManager.getConnector( request.getUser() );
			con.sendJSPRequest( SOLICITUD_DISPOSITIVOS_1_1, null );
			String xml = con.sendRequestPost(SOLICITUD_DISPOSITIVOS_1_2 , null );
			con.sendJSPRequest( SOLICITUD_DISPOSITIVOS_1_3, null );
			messageElement = decoder.buildMessageElement(xml);
			
		} catch ( URISyntaxException e ) {
			throw new DAOException( e );
		} catch ( HttpException e ) {
			throw new DAOException( e );
		} catch ( IOException e ) {
			throw new DAOException( e );
		} catch ( XmlDecodeException e ) {
			throw new DAOException( e );
		} 
		return messageElement;
	}
	
	public MessageElement getSesionSolicitudDispositivo( SolicitudDispositivoRequestTO request ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		
		try {
			Connector con = ConnectorManager.getConnector( request.getUser() );
			String xml = con.sendRequestPost( SOLICITUD_DISPOSITIVOS_1_4 , null );
			messageElement = decoder.buildMessageElement(xml);
		} catch ( URISyntaxException e ) {
			throw new DAOException( e );
		} catch ( HttpException e ) {
			throw new DAOException( e );
		} catch ( IOException e ) {
			throw new DAOException( e );
		} catch ( XmlDecodeException e ) {
			throw new DAOException( e );
		} 
		return messageElement;
	}

	public MessageElement setCuentaSolicitudDispositivo( SolicitudDispositivoRequestTO request ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("cuentas", request.getCuenta());
		params.put("chLh", request.getTipoDispositivo());

		try {
			Connector con = ConnectorManager.getConnector( request.getUser() );
			con.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml =  con.sendRequestPost(SOLICITUD_DISPOSITIVOS_2_1 , params );
			messageElement = decoder.buildMessageElement(xml);
		} catch ( URISyntaxException e ) {
			throw new DAOException( e );
		} catch ( HttpException e ) {
			throw new DAOException( e );
		} catch ( IOException e ) {
			throw new DAOException( e );
		} catch ( XmlDecodeException e ) {
			throw new DAOException( e );
		} 
		return messageElement;
	}
	
	public MessageElement setDataSolicitudDispositivo( SolicitudDispositivoRequestTO request ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("direccion", Validator.isEmptyData(request.getDireccion())?"":request.getDireccion());
		
		params.put("estado", Validator.isEmptyData(request.getEstado())?"":request.getEstado());
		params.put("ciudad", Validator.isEmptyData(request.getCiudad())?"":request.getCiudad());
		params.put("colonia", Validator.isEmptyData(request.getColonia())?"":request.getColonia());
		params.put("cp", Validator.isEmptyData(request.getCodigoPostal())?"":request.getCodigoPostal());
		params.put("telefono", Validator.isEmptyData(request.getTelefono())?"":request.getTelefono());
		params.put("nombre1", Validator.isEmptyData(request.getNombre1())?"":request.getNombre1());
		params.put("apPat1", Validator.isEmptyData(request.getApellidoPaterno1())?"":request.getApellidoPaterno1());
		params.put("apMat1", Validator.isEmptyData(request.getApellidoMaterno1())?"":request.getApellidoMaterno1());
		params.put("nombre2", Validator.isEmptyData(request.getNombre2())?"":request.getNombre2());
		params.put("apPat2", Validator.isEmptyData(request.getApellidoPaterno2())?"":request.getApellidoPaterno2());
		params.put("apMat2", Validator.isEmptyData(request.getApellidoMaterno2())?"":request.getApellidoMaterno2());
		
		if(request.getSubmith().equals("MODIFICA_DIRECCION")){
			params.put("submit1", "Modificar dirección");
		}else if (request.getSubmith().equals("CONTINUAR")){
			params.put("submit", "Continuar");
		}

		try {
			Connector con = ConnectorManager.getConnector( request.getUser() );
			String xml =  con.sendRequestPost(SOLICITUD_DISPOSITIVOS_3_1 , params );
			messageElement = decoder.buildMessageElement(xml);
		} catch ( URISyntaxException e ) {
			throw new DAOException( e );
		} catch ( HttpException e ) {
			throw new DAOException( e );
		} catch ( IOException e ) {
			throw new DAOException( e );
		} catch ( XmlDecodeException e ) {
			throw new DAOException( e );
		} 
		return messageElement;
	}
	public MessageElement setConfirmSolicitudDispositivo( SolicitudDispositivoRequestTO request ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();	
		Map<String, String> params = new HashMap<String, String>();
		params.put("submit", "Continuar");
		
		if(request.getOpcionActivacion().equals(String.valueOf(NIP_DISPOSITIVE))){
			params.put( "nip", request.getNip() );
		}else{
			params.put("clave", request.getClave());
		}

		try {
			Connector con = ConnectorManager.getConnector( request.getUser() );
			String xml =  con.sendRequestPost(SOLICITUD_DISPOSITIVOS_4_1 , params );
			messageElement = decoder.buildMessageElement(xml);
		} catch ( URISyntaxException e ) {
			throw new DAOException( e );
		} catch ( HttpException e ) {
			throw new DAOException( e );
		} catch ( IOException e ) {
			throw new DAOException( e );
		} catch ( XmlDecodeException e ) {
			throw new DAOException( e );
		} 
		return messageElement;
	}
	
	public MessageElement getCatalogoMunicipiosSolicitudDispositivos(){
		MessageElement messageElement = null;
		XMLDecode decoder = new XMLDecode();
		try {
			File file = new File(PropertiesService.getInstance().getPath()+File.separator+"CatalogoMunicipiosSolicitudDispositivos.xml");
			FileReader fileReader;
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String xml = "";
			while (bufferedReader.ready()){
				xml += bufferedReader.readLine().trim();
			}
			messageElement = decoder.buildMessageElement(xml);
		}catch(Exception e){
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		return messageElement;
	}
}