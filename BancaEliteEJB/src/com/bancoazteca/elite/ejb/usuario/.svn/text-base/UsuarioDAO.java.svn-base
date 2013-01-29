package com.bancoazteca.elite.ejb.usuario;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ActivarCuentaExpressRequestTO;
import com.bancoazteca.elite.beans.ActualizaDatosRequestTO;
import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.BibliotecaRecibosRequestTO;
import com.bancoazteca.elite.beans.ChangePasswordResponseTO;
import com.bancoazteca.elite.beans.ChangePaswordRequestTO;
import com.bancoazteca.elite.beans.ChangeSecurityLevelRequestTO;
import com.bancoazteca.elite.beans.DatosPdfBibliotecaServiciosTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.beans.LlaveSeguridadRequestTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesRequestTO;
import com.bancoazteca.elite.beans.ParameterOnSessionTO;
import com.bancoazteca.elite.beans.RecuperaPasswordRequestTO;
import com.bancoazteca.elite.beans.TokenTO;
import com.bancoazteca.elite.beans.XmlRulesRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.connector.DigitalFinger;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.InvalidCertificateException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.Validator;

/**
 * En la clase UsuarioDAO se declaran todos los paths y los parámetros,<br/> 
 * necesarios para que el servlet del conector elite invoque las acciones correspondientes a operaciones con el usuario, <br>
 * como login, logout, consulta en biblioteca de recibos, cambio de contraseña, activacion express de cuenta en el portal y niveles de seguridad en el portal.<br/>
 * @return <b>org.apache.axis.message.MessageElement </b><br/>
 * 			En la invocación de las acciones se tiene como respuesta un MessageElement con la información recopilada.
 */

public class UsuarioDAO extends EliteDAO {
	
	private static final Logger log = Logger.getLogger(UsuarioDAO.class);
	
	private final String SET_PARAMETER_ON_SESSION="/seguridad/sessionParameter.elite";
	
	private final String BIBLIOTECA_RECIBOS_MENU = "/servicios/bibliotecaRecibosMenu.elite";
	private final String BIBLIOTECA_RECIBOS = "/servicios/bibliotecaRecibos.elite";
	private final String BIBLIOTECA_RECIBOS_PRE = "/servicios/bibliotecaRecibosPre.do";
	private final String CONSULTA_BLIBLIOTECA_RECIBOS = "/bibliotecaRecibos/consulta.elite";
	private final String PDF_BIBLIOTECA_RECIBOS="/bibliotecaRecibos/generaPdf.elite";
	private final String BIBLIOTECA_RECIBOS_INICIO="/bibliotecaRecibos/inicio.elite";
	
	
	private final String LOGOUT = "/logout";
	private final String LOGIN = "/login";
	private final String MANEJO_SESSION = "/session/manejoSesion.elite";
	private final String INICIO_MENU = "/consultas/inicioMenu.do";
	
	private final String CUENTAS = "/consultas/cuentasPre.elite";
//	private final String SET_VARIABLE_RELOAD_SALDOS = "/consulta/saldosReload.elite";
	private final String CAMBIAR_PASSWORD = "/usuarios/cambiarPasswordInicio.elite";	

	private final String OLVIDO_USER_PWD_STEP1 = "/contrasena/recuperacionConfirmaCta.elite";
	private final String OLVIDO_USER_PWD_STEP2 = "/contrasena/ValidaDatos.elite";
	private final String OLVIDO_USER_PWD_STEP2_1="/contrasena/confirmacionDatosUsuario.elite";
	private final String OLVIDO_USER_PWD_STEP3 = "/contrasena/confirmacionDatosUsuario.elite";
	private final String OLVIDO_USER_PWD_STEP4 = "/contrasena/validacionDatosUsuario.elite";
	private final String OLVIDO_USER_PWD_STEP5 = "/contrasena/passwordAcceso.elite";
	private final String OLVIDO_USER_PWD_STEP5_1 ="/contrasena/exito.elite";
	
	private final String CAMBIAR_NIVEL_SEGURIDAD_CONFIRMA = "/seguridad/redirigeNivelSolicitadoDispatch.elite";
	private final String CAMBIAR_NIVEL_SEGURIDAD_WAIT = "/seguridad/redirigeNivelSolicitadoDispatchEjecuta.elite";
	private final String CAMBIAR_NIVEL_SEGURIDAD = "/seguridad/redirigeNivelSolicitadoDispatchEjecutaCmd.elite";
	
	private final String OBTENER_HUELLA="/seguridad/init/validaHuella.elite";
	private final String VALIDAR_HUELLA="/seguridad/validaHuella.elite";

	private final String VALIDAR_TOKEN="/seguridad/validaToken.elite";
	private final String ACTIVAR_SERVICIO_EXPRESS_0 = "/activacionExpress.elite";
	private final String ACTIVAR_SERVICIO_EXPRESS_1 = "/activacionExpress/inicio.elite";
	private final String ACTIVAR_SERVICIO_EXPRESS_2 = "/activacionExpress/contrato.elite";
	private final String ACTIVAR_SERVICIO_EXPRESS_3 = "/activacionExpress/registro.elite";
	private final String ACTIVAR_SERVICIO_EXPRESS_TOKEN="/activacionExpress/consultaToken.elite";
	
	private final String PATH_UPDATE_VALUES_ACCOUNT="/update/eliteAccount.elite";
	
	private final String ACTUALIZA_DATOS_USUARIO_MENU = "/usuarios/cambioDetallesEjecutarMenu.elite";
	private final String ACTUALIZA_DATOS_USUARIO_EJECUTAR = "/usuarios/cambioDetallesEjecutar.elite";
	
	private final String PATH_XML_BEAN_RULES = "/contrasena/xmlBeanRules.elite";
	
//	Cambio Contrasena
	private final String CAMBIA_CONTRASENA_INITIAL_0 = "/usuarios/cambiarInicioMenu.elite";
	private final String CAMBIA_CONTRASENA_INITIAL_1 = "/usuarios/cambiarInicio.elite";
	private final String CAMBIA_CONTRASENA_INITIAL_2 = "/usuarios/cambiarInicioPre.elite";
	private final String CAMBIA_CONTRASENA_EJECUTA_0 = "/usuarios/cambiarPasswordConfirmacionHuella.elite";
	
	//OPERACIONES FRECUENTES
	private final String AGREGA_OPERACION_FRECUENTE = "/operacionesFrecuentes/agregarFrecuente.elite";
	private final String CONSULTAR_OPERACIONES_FRECUENTES = "/operacionesFrecuentes/consultarFrecuente.elite";
	private final String ELIMINAR_OPERACION_FRECUENTE = "/operacionesFrecuentes/eliminarFrecuente.elite";
	public String login(LoginRequestTO loginRequestTO) throws LoginException, DAOException, SessionExpiredException {		
		String html = null;				
		Map<String, String> params = new Hashtable<String, String>();	
		
		params.put("xmlEncodeBeans", XmlRulesRequestTO.BEAN_RULES);
		params.put("xmlNotEncodeBeans", XmlRulesRequestTO.NOT_LOAD_BEAN_RULES);
				
		try {
			Connector connector = ConnectorManager.registerConnector(loginRequestTO.getUser());		
			connector.sendRequest(PATH_XML_BEAN_RULES, params);
			params.clear();
			params.put("j_username", loginRequestTO.getUser());
			params.put("j_password", loginRequestTO.getPassword());
			//type crmovil
			params.put("typeName", loginRequestTO.getAplicacion());
			
			html = connector.sendServletRequest(LOGIN, params);
			//html = connector.sendServletRequestPost(LOGIN, params);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} catch (HttpException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}  catch (InvalidCertificateException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return html;
	}
	
	public String setSessionManagment(LoginRequestTO loginRequestTO) throws SessionExpiredException, DAOException {
		String html = null;			
		Map<String, String> params = new HashMap<String, String>();
		params.put("accion", loginRequestTO.getAction());
		try {			
			Connector connector = ConnectorManager.getConnector(loginRequestTO.getUser());			
			html = connector.sendServletRequest(MANEJO_SESSION, params);								

		} catch (URISyntaxException e) {
			throw new DAOException(e.getCause());
		} catch (HttpException e) {
			throw new DAOException(e.getCause());
		} catch (IOException e) {
			throw new DAOException(e.getCause());
		} 
		return html;
	}
	
	public MessageElement getOnDemandMultipleAccounts(LoginRequestTO loginRequestTO,int index) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;
		log.info("--> Solicitando informacion de la cuenta "+loginRequestTO.getAccountOnDemand());				
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("method", "getDetalleCuenta");
			params.put("numeroCuenta", loginRequestTO.getAccountOnDemand());
						
			Connector connector = ConnectorManager.getConnector(loginRequestTO.getUser());
//			connector = ConnectorUtil.clone(connector);
			String xml = connector.sendRequest("/consultas/eliteDetailAccounts.elite", params);					
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
	
	public  MessageElement getOnDemandAccounts(LoginRequestTO loginRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;
				
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("method", "init");
			if (loginRequestTO.isReload()){
				params.put("reload", "true");
			}
			Connector connector = ConnectorManager.getConnector(loginRequestTO.getUser());
			String xml = connector.sendRequest("/consultas/eliteAccounts.elite", params);
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
	
	public MessageElement getCuentasParalelo(LoginRequestTO loginRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;		
		String xml = null;
		try {
			Connector connector = ConnectorManager.getConnector(loginRequestTO.getUser());
		
			Map< String, String> map = new HashMap<String, String>();
			map.put("sessionAttributeName", "test");
			map.put("sessionAttributeValue", "true");
			map.put("sessionAttributeType", "java.lang.Boolean");
			connector.sendRequest("/seguridad/sessionParameter.elite", map);
			
			//connector.sendRequest("/consultas/eliteAccounts.elite", null);	
			xml = connector.sendRequest("/cuentas/initParalelo.elite", null);
				
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
	
	public MessageElement getCuentas(LoginRequestTO loginRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;		
		String xml = null;
		try {
			long inicio= System.currentTimeMillis();
			Connector connector = ConnectorManager.getConnector(loginRequestTO.getUser());
		
			Map< String, String> map = new HashMap<String, String>();
			map.put("sessionAttributeName", "test");
			map.put("sessionAttributeValue", "true");
			map.put("sessionAttributeType", "java.lang.Boolean");
			connector.sendRequestPost("/seguridad/sessionParameter.elite", map);
						
			xml = connector.sendRequest(CUENTAS, null);
				
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
			log.info("TIEMPO: " + ((System.currentTimeMillis()-inicio)/1000));
			
			
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
	
	public MessageElement getActualizarCuenta( BalanceRequestTO balanceRequestTO ) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;		
		String xml = null;
		try {
			Connector connector = ConnectorManager.getConnector( balanceRequestTO.getUser());
			Map< String, String> map = new HashMap<String, String>();
			map.put( "numeroCuenta", balanceRequestTO.getAcountNumber() );
			xml = connector.sendRequestPost( PATH_UPDATE_VALUES_ACCOUNT, map);
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
	
	public String getInicioMenu(LoginRequestTO loginRequestTO) throws SessionExpiredException, DAOException {
		log.info("InicioMenu "+INICIO_MENU);
		String html = null;		

		try {
			Connector connector = ConnectorManager.getConnector(loginRequestTO.getUser());
			html = connector.sendServletRequest(INICIO_MENU, null);								
		
		} catch (URISyntaxException e) {
			throw new DAOException(e.getCause());
		} catch (HttpException e) {
			throw new DAOException(e.getCause());
		} catch (IOException e) {
			throw new DAOException(e.getCause());
		}
		return html;
	}

	/**
	 * Proceso olvido usuario y pwd paso 1 metodo que recibe cuenta y valida
	 * existente y valida sea 14 o 16 digitos
	 * 
	 * @param recuperaPasswordTO
	 *            el objeto que contiene el o los parametros a enviar
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws IOException
	 * @throws SessionExpiredException
	 */
	public MessageElement findAccountOrCreditCardNumber(RecuperaPasswordRequestTO recuperaPasswordTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("cuenta", recuperaPasswordTO.getTarjetaCuenta());
		MessageElement msg = null;
		try {
			Connector con = ConnectorManager.registerConnector(recuperaPasswordTO.getTarjetaCuenta());
			xml = con.sendRequest( OLVIDO_USER_PWD_STEP1, params);
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

	/**
	 * Proceso olvido usuario y pwd paso 2 metodo que recibe datos personales
	 * 
	 * @param recuperaPasswordTO
	 *            el objeto que contiene el o los parametros a enviar
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws IOException
	 * @throws SessionExpiredException
	 */
	public MessageElement findPersonalData(RecuperaPasswordRequestTO recuperaPasswordTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("nip", recuperaPasswordTO.getNip());
		params.put("vnip", recuperaPasswordTO.getConfirmacionNip());
		params.put("dia", recuperaPasswordTO.getDiaNacimiento());
		params.put("mes", recuperaPasswordTO.getMesNacimiento());
		params.put("anio", recuperaPasswordTO.getAnioNacimiento());
		params.put("nombre", recuperaPasswordTO.getNombre());
		params.put("paterno", recuperaPasswordTO.getPaterno());
		params.put("materno", recuperaPasswordTO.getMaterno());

		MessageElement msg = null;

		try {
			Connector con = ConnectorManager.getConnector(recuperaPasswordTO.getTarjetaCuenta());
			xml = con.sendRequest( OLVIDO_USER_PWD_STEP2, params);
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

	
	public MessageElement findPersonalData1(RecuperaPasswordRequestTO recuperaPasswordTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();

		MessageElement msg = null;

		try {
			Connector con = ConnectorManager.getConnector(recuperaPasswordTO.getTarjetaCuenta());
			
			params=new HashMap<String, String>();
			params.put("method", "datos");
			xml=con.sendRequest(OLVIDO_USER_PWD_STEP2_1, params);
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
	
	
	public MessageElement findConfirmPersonalData() throws DAOException, SessionExpiredException {
		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "datos");

		MessageElement msg = null;

		try {
			Connector con = ConnectorManager.getConnector(ConnectorManager.GENERAL_USER);
			xml = con.sendRequest( OLVIDO_USER_PWD_STEP3, params);
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
	
	public MessageElement findChangePersonalData(RecuperaPasswordRequestTO requestTO) throws DAOException, SessionExpiredException {
		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "verificacion");
		params.put("correo", requestTO.getCorreoElectronico());
		params.put("celular", requestTO.getNumeroCelular());
		params.put("compania", requestTO.getCompaniaCelular());

		MessageElement msg = null;

		try {
			Connector con = ConnectorManager.getConnector(requestTO.getTarjetaCuenta());
			xml = con.sendRequest( OLVIDO_USER_PWD_STEP4, params);
			msg = decoder.buildMessageElement(xml);
			
			System.out.println("antes de termina");
			
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
		
	/**
	 * Proceso olvido usuario y pwd paso 3 metodo que recibe la nueva contraseña
	 * 
	 * @param recuperaPasswordTO
	 *            el objeto que contiene el o los parametros a enviar
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws IOException
	 * @throws SessionExpiredException
	 */
	public MessageElement updateNewPassword(RecuperaPasswordRequestTO recuperaPasswordTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordAcceso");
		params.put("hide", "2");
		params.put("password", recuperaPasswordTO.getPassword());
		params.put("vpassword", recuperaPasswordTO.getConfirmacionPassword());

		MessageElement msg = null;

		try {
			Connector con = ConnectorManager.getConnector(recuperaPasswordTO.getTarjetaCuenta());
			xml = con.sendRequest( OLVIDO_USER_PWD_STEP5, params);
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

	public MessageElement endingNewPassword(RecuperaPasswordRequestTO recuperaPasswordTO)throws DAOException, SessionExpiredException {
		String xml=null;
		XMLDecode decode=new XMLDecode();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "fin");
		MessageElement msg=null;
		Connector connector=ConnectorManager.getConnector(recuperaPasswordTO.getTarjetaCuenta());
		try {
			xml=connector.sendRequest(OLVIDO_USER_PWD_STEP5_1, params);
			msg=decode.buildMessageElement(xml);
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
	
	public void logout(String user) throws SessionExpiredException, UsuarioException {
		if (ConnectorManager.containsConnector(user)) {
			Connector connector = (Connector) ConnectorManager.getConnector(user);

			try {
				if (connector != null) {
					connector.sendServletRequest(LOGOUT,null);
				}
				ConnectorManager.removeConnector(user);
			} catch (URISyntaxException e) {	
				log.error(e.getMessage());
				throw new UsuarioException(e);
			} catch (HttpException e) {
				log.error(e.getMessage());
				throw new UsuarioException(e);
			} catch (IOException e) {		
				log.error(e.getMessage());
				throw new UsuarioException(e);
			}

		}
	}

	/**
	 * Realiza el cambio de password de Banca por Internet a trav&eacute;s de
	 * eBanking.
	 * 
	 * @param username
	 * @param oldPassword
	 * @param password
	 * @param vpassword
	 * @return EliteResponse
	 * @throws DAOException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public MessageElement cambiarPassword(ChangePaswordRequestTO changePaswordRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();

		params.put("hide", "0");
		params.put("method", "cambiarPasswordInicio");
		params.put("oldPassword", changePaswordRequestTO.getContrasenia_actual());
		params.put("password", changePaswordRequestTO.getNueva_contrasenia());
		params.put("vpassword", changePaswordRequestTO.getConfirmacion_nueva_contrasenia());
		params.put("submit", "Cambiar");		

		try {
			Connector connector = ConnectorManager.getConnector(changePaswordRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest( CAMBIAR_PASSWORD, params);
			messageElement = decode.buildMessageElement(xml);
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
	
	/**
	 * Confirma el cambio de Nivel de Seguridad de Banca por Internet a
	 * trav&eacute;s de eBanking.
	 * 
	 * @param username
	 * @param oldPassword
	 * @param password
	 * @param vpassword
	 * @return EliteResponse
	 * @throws DAOException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	
	public MessageElement modifySecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();		
		params.put("method", changeSecurityLevelRequestTO.getMethod());
		params.put("paso", changeSecurityLevelRequestTO.getPaso());
		params.put("nivelMovimientos", changeSecurityLevelRequestTO.getNivelMovimientos());
		params.put("nivelTransferencias", changeSecurityLevelRequestTO.getNivelTransferencias());
		params.put("nivelFrecuentes", changeSecurityLevelRequestTO.getNivelFrecuentes());
		params.put("transfMismo", changeSecurityLevelRequestTO.getTransfMismo());
		params.put("transfOtro", changeSecurityLevelRequestTO.getTransfOtro());
		params.put("transfInter", changeSecurityLevelRequestTO.getTransfInter());
		params.put("pagoServicios", changeSecurityLevelRequestTO.getPagoServicios());
		
		try {
			Connector connector = ConnectorManager.getConnector(changeSecurityLevelRequestTO.getUser());		
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);		
			String xml = connector.sendRequest(CAMBIAR_NIVEL_SEGURIDAD_CONFIRMA, params);
			messageElement = decode.buildMessageElement(xml);
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
	
	/**
	 * WAIT
	 * 
	 * @param username
	 * @param oldPassword
	 * @param password
	 * @param vpassword
	 * @return EliteResponse
	 * @throws DAOException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	
	public MessageElement waitChangeSecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "wait");

		try {
			Connector connector = ConnectorManager.getConnector(changeSecurityLevelRequestTO.getUser());
			String xml = connector.sendRequest( CAMBIAR_NIVEL_SEGURIDAD_WAIT, params);
			messageElement = decode.buildMessageElement(xml);
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
	
	
	/**
	 * Realiza el cambio de Nivel de Seguridad de Banca por Internet a
	 * trav&eacute;s de eBanking.
	 * 
	 * @param username
	 * @param oldPassword
	 * @param password
	 * @param vpassword
	 * @return EliteResponse
	 * @throws DAOException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public MessageElement executeChangeLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("method", changeSecurityLevelRequestTO.getMethod());
		params.put("paso", changeSecurityLevelRequestTO.getPaso());
//		params.put("clave", changeSecurityLevelRequestTO.getClave());
//		params.put("tokencode", changeSecurityLevelRequestTO.getTokenCode());
		params.put("newpin", changeSecurityLevelRequestTO.getNewpin());
		params.put("confnewpin", changeSecurityLevelRequestTO.getConfnewpin());
		params.put("nivelMovimientos", changeSecurityLevelRequestTO.getNivelMovimientos());
		params.put("nivelTransferencias", changeSecurityLevelRequestTO.getNivelTransferencias());
		params.put("nivelFrecuentes", changeSecurityLevelRequestTO.getNivelFrecuentes());
		params.put("transfMismo", changeSecurityLevelRequestTO.getTransfMismo());
		params.put("transfOtro", changeSecurityLevelRequestTO.getTransfOtro());
		params.put("transfInter", changeSecurityLevelRequestTO.getTransfInter());
		params.put("pagoServicios", changeSecurityLevelRequestTO.getPagoServicios());
		
		if(!Validator.isEmptyData(changeSecurityLevelRequestTO.getTokenCode())){
			params.put("tokencode", changeSecurityLevelRequestTO.getTokenCode());
		}else{
			params.put("clave", changeSecurityLevelRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, changeSecurityLevelRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "forma");
		}
		
//		params.put(DigitalFinger.DIGITAL_FINGET_OPTION, DigitalFinger.GET_DIGITAL_FINGET);
//		params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
//		params.put(DigitalFinger.DIGITAL_FINGET_USER, changeSecurityLevelRequestTO.getUser());
//		params.put(DigitalFinger.DIGITAL_FINGET_OBJECT,"forma");
//		

		try {
			Connector connector = ConnectorManager.getConnector(changeSecurityLevelRequestTO.getUser());
			String xml = connector.sendRequest( CAMBIAR_NIVEL_SEGURIDAD, params);
			messageElement = decode.buildMessageElement(xml);
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
	
	public MessageElement getLlaveSeguridad(LlaveSeguridadRequestTO llaveSeguridadRequestTO){
		
		MessageElement messageElement=null;
		XMLDecode decode=new XMLDecode();
		Connector connector=ConnectorManager.getConnector(llaveSeguridadRequestTO.getUser());
		try {
			
			String xml=connector.sendRequest(OBTENER_HUELLA,null);
			messageElement=decode.buildMessageElement(xml);
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@xml llave");
			log.info("message: "+messageElement.toString());
			
		} catch (SessionExpiredException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(XmlDecodeException ex) {
			ex.printStackTrace();
		}
		
		return messageElement;
	}
	
	public MessageElement validaHuella(HuellaDigitalTO huellaDigitalTO) throws SessionExpiredException, URISyntaxException, HttpException, IOException {

		DigitalFinger digitalFinger=new DigitalFinger(huellaDigitalTO.getHuella());
		
		Connector connector=ConnectorManager.getConnector(huellaDigitalTO.getUser());
		
		digitalFinger.sendDigitalFingerParts(connector,huellaDigitalTO.getUser());
		
		MessageElement messageElement=null;
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("getDigitaloOption","getDigitalFinger");
		params.put("digitalFingerUser",huellaDigitalTO.getUser());
		params.put("digitalFingerValue","clave");
		
		XMLDecode decode=new XMLDecode();
		String xml=connector.sendRequest(VALIDAR_HUELLA, params);
		log.info("XML: "+xml);
		try {
			messageElement=decode.buildMessageElement(xml);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		return messageElement;
	}

	public MessageElement validaToken(TokenTO tokenTO) throws SessionExpiredException, URISyntaxException, HttpException, IOException, XmlDecodeException{
		
		Connector connector=ConnectorManager.getConnector(tokenTO.getUser());
		
		Map<String, String> params=new HashMap<String,String>();
		params.put("tokenCode",tokenTO.getTokenCode());
		String xml=connector.sendRequest(VALIDAR_TOKEN,params);
		XMLDecode decode=new XMLDecode();
		MessageElement messageElement = decode.buildMessageElement(xml);
		return messageElement;
	}
	
	public void setParameterOnSession(ParameterOnSessionTO parameter) throws SessionExpiredException, URISyntaxException, HttpException, IOException {
		
		Connector connector=ConnectorManager.getConnector(parameter.getUsuario());
		Map<String,String> params=new HashMap<String,String>();
		params.put("sessionAttributeName",parameter.getNombreValor());
		params.put("sessionAttributeValue",parameter.getValor());
		params.put("sessionAttributeType","java.lang.String");
		
		connector.sendRequest(SET_PARAMETER_ON_SESSION,params);
		
	}
	
	
	public Map<String,String> getServiciosBiblioecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, URISyntaxException, HttpException, IOException, XmlDecodeException{
		
		Connector connector=ConnectorManager.getConnector(user.getUsuario());

		Map<String,String> params=new HashMap<String,String>();
		params.put("method", "inicio");
		
		//connector.sendRequest(this.BIBLIOTECA_RECIBOS_INICIO,params);
		String response=connector.sendServletRequest(this.BIBLIOTECA_RECIBOS_PRE,params);
		
		response=response.substring(response.indexOf("<select"),response.indexOf("</select>")+9);
		System.out.println(response);
		
		Map<String,String>mapa=getHTMLSelectData(response);
		mapa.remove("------------------------------------");
		mapa.remove("Ver todos los recibos");
		mapa.remove("- SELECCIONE EL SERVICIO A FILTRAR -");
		
		return mapa;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getHTMLSelectData(String xml)throws XmlDecodeException {
		
		XMLDecode decode=new XMLDecode();
		
		MessageElement element=decode.buildMessageElement(xml.replace("&","").replace("\t",""));
		
		List listaChild=element.getChildren();
		MessageElement element2;
		HashMap<String,String>hashMap=new HashMap<String, String>();
		for (Object object :listaChild) {
			if(object instanceof MessageElement){
				element2=(MessageElement)object;				
				System.out.println(element2.getAttributeValue("value")+", "+element2.getFirstChild());
				hashMap.put(element2.getFirstChild().toString(),element2.getAttributeValue("value"));				
			}
		}
		return hashMap;
	}
	
	public MessageElement getMenuBiblioecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, URISyntaxException, HttpException, IOException, XmlDecodeException{
		
		Connector connector=ConnectorManager.getConnector(user.getUsuario());
		Map<String,String> params=new HashMap<String,String>();
		String response=null;
		
		params.put("method", "consulta");
		params.put("servicio", user.getIdServicio());
		params.put("fechaInicio", user.getFechaInicio());
		params.put("fechaFinal", user.getFechaFinal());
		params.put("submit", "Consultar");
		
		response =connector.sendRequest(this.CONSULTA_BLIBLIOTECA_RECIBOS,params);
		
		XMLDecode decode=new XMLDecode();
		MessageElement messageElement = decode.buildMessageElement(response);
		
		return messageElement;
	}
	
	public MessageElement getDatosPDFBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, URISyntaxException, HttpException, IOException, XmlDecodeException{
		
		Connector connector=ConnectorManager.getConnector(user.getUsuario());
		Map<String,String> params=new HashMap<String,String>();
		String response=null;
		
		params.put("method", "generaPdf");		
		params.put("index",user.getReciboIndex());
		
		connector.sendRequest(this.PDF_BIBLIOTECA_RECIBOS,params);
		
		response=connector.sendRequest(this.BIBLIOTECA_RECIBOS_MENU,null);
		
		XMLDecode decode=new XMLDecode();
		MessageElement messageElement = decode.buildMessageElement(response);
		
		
		return messageElement;
	}

	

	public MessageElement solicitudCuentaActivacion(ActivarCuentaExpressRequestTO activarCuentaTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		MessageElement messageElement = null;
		try {
			
			Connector con = ConnectorManager.registerConnector(activarCuentaTO.getUser());
			xml = con.sendRequestPost( ACTIVAR_SERVICIO_EXPRESS_0, null);
			
			messageElement = decoder.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		} catch (InvalidCertificateException e) {
			e.printStackTrace();
		} 
		return messageElement;
	}
			
	public MessageElement validaCuentaActivacion(ActivarCuentaExpressRequestTO activarCuentaTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("tarjeta", activarCuentaTO.getTarjetaCuenta());
		params.put("operacion", "valida");		
		
		params.put("clave", activarCuentaTO.getClave());
		
		MessageElement messageElement = null;
		try {
			Connector con = ConnectorManager.getConnector(activarCuentaTO.getUser());
			xml = con.sendRequestPost( ACTIVAR_SERVICIO_EXPRESS_1, params);
			
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
	
	public MessageElement contratoActivacionExpress(ActivarCuentaExpressRequestTO activarCuentaTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		MessageElement messageElement = null;
		try {
			
			Connector con = ConnectorManager.getConnector(activarCuentaTO.getUser());
			xml = con.sendRequestPost( ACTIVAR_SERVICIO_EXPRESS_2, null);
			
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
	
	/**
	 * Proceso para activar la cuenta que recibe cuenta o tarjeta y valida
	 * existente y valida sea 14 o 16 digitos respectivamente
	 * 
	 * @param activarCuentaTO
	 *            el objeto que contiene el o los parametros a enviar
	 * @throws URISyntaxException
	 * @throws HttpException
	 * @throws IOException
	 * @throws SessionExpiredException
	 */
	public MessageElement ejecutarActivacionExpress(ActivarCuentaExpressRequestTO activarCuentaTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "datosUsuario");
		params.put("alias", activarCuentaTO.getAlias());
		params.put("contrasena", activarCuentaTO.getContrasena());
		params.put("confirmContra", activarCuentaTO.getContrasena_confirmacion());
		params.put("numeroToken", activarCuentaTO.getNumeroToken());
		params.put("numeroTokenConfirm", activarCuentaTO.getNumeroToken_confirmacion());
		params.put("celular", activarCuentaTO.getCelular());
		params.put("compania", activarCuentaTO.getCompania());
		params.put("correo", activarCuentaTO.getCorreo());
		params.put("mailUser", activarCuentaTO.getMailUser());
		params.put("mailDominio", activarCuentaTO.getMailDominio());
		MessageElement messageElement = null;
		try {
			Connector con = ConnectorManager.getConnector(activarCuentaTO.getUser());
			xml = con.sendRequestPost( ACTIVAR_SERVICIO_EXPRESS_3, params);
			
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
	
	public MessageElement validaDisponibilidadAlias(ActivarCuentaExpressRequestTO activarCuentaTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "Disponibilidad");
		params.put("alias", activarCuentaTO.getAlias());

		MessageElement messageElement = null;
		try {
			Connector con = ConnectorManager.getConnector(activarCuentaTO.getUser());
			xml = con.sendRequestPost( ACTIVAR_SERVICIO_EXPRESS_3, params);
			
			xml="<?xml version='1.0' encoding='iso-8859-1'?>"+xml;
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
	
	public MessageElement validaTokenActivacionExpress(ActivarCuentaExpressRequestTO activarCuentaTO) throws DAOException, SessionExpiredException {

		String xml = null;
		XMLDecode decoder = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", activarCuentaTO.getNumeroToken());

		MessageElement messageElement = null;
		try {
			Connector con = ConnectorManager.getConnector(activarCuentaTO.getUser());
			xml = con.sendRequestPost( ACTIVAR_SERVICIO_EXPRESS_TOKEN, params);
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
	
	public void logoutActivacionExpress(ActivarCuentaExpressRequestTO activarCuentaTO) {
		ConnectorManager.removeConnector(activarCuentaTO.getUser());
	}
	
	public MessageElement getInitialActualizaDatosDao(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try {

			Connector con = ConnectorManager.getConnector(datosUsuarioRequestTO.getUser());
			con.sendRequestPost( ACTUALIZA_DATOS_USUARIO_MENU , null);
			xml = con.sendRequestPost( ACTUALIZA_DATOS_USUARIO_EJECUTAR , null);
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
	
	public MessageElement getActualizaDatosDao(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("metodo", "verificaDatos");
		params.put("pregunta", datosUsuarioRequestTO.getPregunta());
		log.info("datosUsuarioRequestTO.getRespuesta()"+datosUsuarioRequestTO.getRespuesta());
		params.put("respuesta", datosUsuarioRequestTO.getRespuesta());
		log.info("datosUsuarioRequestTO.getConfirmacion()"+datosUsuarioRequestTO.getConfirmacion());
		params.put("confirmacionRespuesta", datosUsuarioRequestTO.getConfirmacion());
		params.put("email", datosUsuarioRequestTO.getEmail());
		params.put("celular", datosUsuarioRequestTO.getCelular());
		params.put("compania", datosUsuarioRequestTO.getCompania());
		if(datosUsuarioRequestTO.getRecibirMail().equals("on")){
			params.put("recibirMail", datosUsuarioRequestTO.getRecibirMail());
		}		
		params.put("accion", "Cambiar");
		
		try {
			Connector con = ConnectorManager.getConnector(datosUsuarioRequestTO.getUser());
			con.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			xml = con.sendRequest( ACTUALIZA_DATOS_USUARIO_EJECUTAR , params);
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
	
	public MessageElement getEjecutaActualizaDatosDao(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("metodo", "cambiaDatos");
		
		//validacion del dispositivo de seguridad
		if(datosUsuarioRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", datosUsuarioRequestTO.getTokencode());
		}else{											  
			params.put("clave", datosUsuarioRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, datosUsuarioRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		try {
			Connector con = ConnectorManager.getConnector(datosUsuarioRequestTO.getUser());
			xml = con.sendRequest( ACTUALIZA_DATOS_USUARIO_EJECUTAR , params);
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
	
	//cambio Contrasena	
	public MessageElement getInitialCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO)throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
					
		try {
			
			Connector con = ConnectorManager.getConnector(cambiarContrasenaRequestTO.getUser());
			xml = con.sendRequest( CAMBIA_CONTRASENA_INITIAL_0 , params);
			xml = con.sendRequest( CAMBIA_CONTRASENA_INITIAL_1 , params);
			xml = con.sendRequest( CAMBIA_CONTRASENA_INITIAL_2 , params);
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
	
	public MessageElement getEjecutaCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO)throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
					
		try {
			params.put("method", "validacionHuella");
			
			//validacion del dispositivo de seguridad
			if(cambiarContrasenaRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
				params.put("tokencode", cambiarContrasenaRequestTO.getTokencode());
			}else{											  
				params.put("clave", cambiarContrasenaRequestTO.getClave());
				params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
				params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
				params.put(DigitalFinger.DIGITAL_FINGET_USER, cambiarContrasenaRequestTO.getUser());
				params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
			}
			
			Connector con = ConnectorManager.getConnector(cambiarContrasenaRequestTO.getUser());
			xml = con.sendRequest( CAMBIA_CONTRASENA_EJECUTA_0 , params);
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
	//operaciones frecuentes
	public MessageElement insertaOperacionFrecuente(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO)throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try {

			Connector con = ConnectorManager.getConnector(operacionesFrecuentesRequestTO.getUser());
			
			params.put("usuario_alnova", operacionesFrecuentesRequestTO.getUsuarioAlnova());
			params.put("cadena_datos",operacionesFrecuentesRequestTO.getCadenaDatos());
			params.put("alias", operacionesFrecuentesRequestTO.getAlias());
			params.put("operacion", operacionesFrecuentesRequestTO.getOperacion());
			
			xml = con.sendRequestPost( AGREGA_OPERACION_FRECUENTE , params);
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
	
	public MessageElement consultaOperacionFrecuente(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO)throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try {

			Connector con = ConnectorManager.getConnector(operacionesFrecuentesRequestTO.getUser());
			params.put("usuario_alnova_cadena", operacionesFrecuentesRequestTO.getUsuarioAlnova());
						
			xml = con.sendRequestPost( CONSULTAR_OPERACIONES_FRECUENTES , params);
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
	
	public MessageElement eliminaOperacionFrecuente(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO)throws EJBException, EliteDataException, DAOException, SessionExpiredException{
		String xml = null;
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try {

			Connector con = ConnectorManager.getConnector(operacionesFrecuentesRequestTO.getUser());
			params.put("clave_operacion", operacionesFrecuentesRequestTO.getClave_operacion());
			params.put("usuario_alnova_cadena", operacionesFrecuentesRequestTO.getUsuarioAlnova());
						
			xml = con.sendRequestPost( ELIMINAR_OPERACION_FRECUENTE , params);
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
	//operaciones frecuentes
}