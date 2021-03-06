package com.bancoazteca.eservice.command.base;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DetalleMonitoreoTO;
import com.bancoazteca.elite.beans.UsuarioOperacionesTO;
import com.bancoazteca.elite.beans.UsuariosTO;
import com.bancoazteca.elite.commons.xml.CipherArgumentException;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.beans.EservicesTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.ResponseTO;
import com.bancoazteca.eservice.command.base.beans.StatusTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.base.session.SessionManager;
import com.bancoazteca.eservice.command.login.LoginForm;
import com.bancoazteca.eservice.command.redireccion.WSCliente;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.filters.ConstantesFiltro;
import com.bancoazteca.eservice.filters.FilterManager;
import com.bancoazteca.eservice.util.xml.XMLEncoderEService;
import com.bancoazteca.eservice.util.xml.XmlDecoderEService;
import com.bancoazteca.eservice.validator.CommandErrorHandler;
import com.bancoazteca.eservice.validator.Errors;
import com.bancoazteca.eservice.validator.MessageErrors;

public class CommandService {

	private static final Logger log = Logger.getLogger(CommandService.class);

	private final String COMMAND_NAME = "idservicio";
	private final String COMMAND_IDSESSION = "idsesion";
	private final String OPERATION_TYPE_TAG = "tipo_operacion";
	private final String REQUEST_TAG = "request";

	private final String ROOT_TAG = "bancoazteca";
	private final String E_SERVICE = "eservices";
	
	private final String INSTANCIA_ALTERNA_PROPERTIE="realiza.sincronizado";
	private final String INSTANCIA_ALTERNA_ARCHIVO="InstanciaAlterna.properties";
	
	private final String SEPARADOR_IDSERVICIO = ".";

	private boolean REALIZO_REDIRECCION=false;
	
	private XmlDecoderEService decoder;
	private XMLEncoderEService encoder;
	private boolean flag;

	public CommandService() {
		Collection<String> rules = new ArrayList<String>();
		rules.add("TO");
		decoder = new XmlDecoderEService();
		encoder = new XMLEncoderEService(rules, null);
	}

	public String service(MessageElement messageElement) {
		EservicesTO eservicesTO = new EservicesTO();
		ResponseTO responseTO = new ResponseTO();
		String xml = "";
		Response response = new Response();
		String beanId = null;
		Session session = null;
		FormBean formBean = null;
		String idsession = null;
		String idPortal = null;
		Command command = null;//djarlo en metodo si no funciona
		CommandMonitorService monitoreoService=null;
		ClienteTO clienteTO=null;
		
		

		try {
			MessageErrors messageErrors = null;
			BeansService beansService = BeansService.getInstance();

			idsession = getIdSession(messageElement);
			idPortal = getIdPortal(messageElement);

			beanId = getIdCommand(messageElement);

			if ((beanId.equalsIgnoreCase("apertura_cuenta.plata")
					|| beanId.equalsIgnoreCase("apertura_cuenta.socio")
					|| beanId.equalsIgnoreCase("apertura_cuenta.eje_elite") || beanId
					.equalsIgnoreCase("apertura_cuenta.socio_plus"))
					&& esValidacion(messageElement)) {
				String xmlS = messageElement.getAsString();
				xmlS = xmlS.replace("<beneficiario>",
						"<beneficiario type=\"bean\">");
				xmlS = xmlS.replace("<coleccion_beneficiarios>",
						"<coleccion_beneficiarios type=\"collection\">");
				messageElement = XMLDecode.buildXMLMessageElement(xmlS);
			}

			command = beansService.getCommand(beanId);
			
			//TODO quitar el if, las variables globales y el if que esta en el bloque finally.
			String realizaSincronizado=PropertiesService.getInstance().getPropertie(INSTANCIA_ALTERNA_ARCHIVO, INSTANCIA_ALTERNA_PROPERTIE);
			if(new Boolean(realizaSincronizado)&&!WSCliente.existeTagIntercambio(messageElement)){
				log.info("A realizar sincronizado");
				if(!WSCliente.verificaEstadoSesion(idsession, command)){
					log.info("Sin sesion en esta instancia a invocar el cliente:");
					xml=messageElement.getAsString();
					String xmlTemp=WSCliente.insertaTagIntercambio(xml);
					String xmlResponse=WSCliente.callService(xmlTemp);
					this.REALIZO_REDIRECCION=true;
					return xmlResponse;
				}
			}
			
			formBean = command.getFormBean();

			if (formBean != null) {

				formBean = buildRequestBean(messageElement, formBean);
				command.setFormBean(formBean);

				if (command.isValidate()) {
					messageErrors = formBean.validate();
				}
			} else {
				formBean = new GenericForm();
				formBean = buildRequestBean(messageElement, formBean);
				command.setFormBean(formBean);
			}

			if (messageErrors != null && messageErrors.isErrors()) {
				response = buildValidationError(messageErrors);
			} else {
				response = execute(command);
			}
			if (beanId.equalsIgnoreCase("logout")) {
				response.getStatus().setIdsesion(null);
			}
			if (response != null && response.getDataService() != null) {

				Collection<?> data = response.getDataService();
				if (data != null) {
					responseTO.setData_service(data);
				}
			}
			
			if( response != null ) {
				
				StatusTO statusTO = new StatusTO();
				if( response.getStatus() != null ) {
					statusTO = response.getStatus();
				}
				
				int index = beanId.indexOf(SEPARADOR_IDSERVICIO);
				String idServicio = null;
				String tipoOperacion = null;
				if ( index > -1 ) {
					idServicio = beanId.substring(0, index);
					tipoOperacion = beanId.substring(index + 1);
				} else {
					idServicio = beanId;
				}
				
				statusTO.setIdservicio(idServicio);
				statusTO.setTipo_operacion(tipoOperacion);
				
				if (statusTO.getCodigo_operacion().equals("0")) {
					statusTO.setCodigo_operacion("0");
					statusTO.setDescripcion_codigo("Transaccion Exitosa");
					statusTO.setError_sistema(null);
				}		
			
				response.setStatus(statusTO);

			}
			
			
		} catch (Throwable t) {
			int code = new CommandErrorHandler(t).getExceptionCode();
			String message = CommandErrorHandler.errorMap.get(code);
			if (code == Errors.EXCEPTION_COMMAND_INVALID
					|| code == Errors.EXCEPTION_CODE) {
				message += " [" + beanId + "]";
			} else if (code == Errors.EXCEPTION_CIPHER) {
				CipherArgumentException cipherException = (CipherArgumentException) t;
				message += cipherException.getPropiedad();
			}
			response.setStatus(code, message, t.getMessage());
		} finally {
			//bandera de redireccionamiento
			if(!this.REALIZO_REDIRECCION){

				if (resetSessionID(idPortal)) {
					String sessionTemporal = formBean.getIdsesion() != null ? formBean.getIdsesion().toString() : null;
					if (formBean != null && !Validator.isEmptyData(sessionTemporal)) {
						generateNewSessionId(formBean.getIdsesion().toString(),	response);
					} else {
						generateNewSessionId(idsession, response);
					}
				} else {
					if (Validator.isEmptyData(idsession)) {
						idsession = response.getStatus() != null ? response.getStatus().toString() : null;
					}
					session = SessionManager.getInstance().getSession(idsession);
					if (session.getIdSession() != null) {
						response.setIdSession(session);
					}
				}

				StatusTO statusTO = response.getStatus();

				if (statusTO.getIdsesion() != null && !statusTO.getCodigo_operacion().equals("0")) {
					String idApp = obtenIdApplication(statusTO.getIdsesion().string);
					idApp = SessionManager.getInstance().getKeyApplication(idApp);
					appendLeyenda(statusTO, idApp);
				} else if (statusTO.getIdsesion() == null && !statusTO.getCodigo_operacion().equals("0")) {
					
					if( response.getStatus().getCodigo_operacion().equalsIgnoreCase(String.valueOf( Errors.EXCEPTION_USER_NO_PAYMENT_RMA)) 
							|| response.getStatus().getCodigo_operacion().equalsIgnoreCase(String.valueOf( Errors.EXCEPTION_USER_NO_ACCOUNT_RMA) )){
						response.getStatus().setIdservicio(null);
						response.getStatus().setError_sistema(statusTO.getDescripcion_codigo());
					}else{
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("idaplicacion","com.bancoazteca.eservice.beans.Cipher");
						Cipher idaplicacion = (Cipher) decoder.toDeserialize("idaplicacion", messageElement, map);
						// log.info("AplicacionXX:"+idaplicacion.string);
						if (idaplicacion != null) {
							String portal = SessionManager.getInstance().getKeyApplication(idaplicacion.string);
							appendLeyenda(statusTO, portal);
						} else {
								String portal = (String) decoder.toDeserialize("aplicacion", messageElement, null);
								appendLeyenda(statusTO, portal);	
						}
					}				
					
				}
				
//				TODO Feregrino Monitor
				
				statusTO = response.getStatus();
				if ( statusTO.getIdsesion() != null && statusTO.getCodigo_operacion().equals("0")) {
					String idComando =  statusTO.getIdservicio();
					if( statusTO.getTipo_operacion() != null ){
						idComando = idComando + "." + statusTO.getTipo_operacion();
					}
					
					if( formBean != null ){
						
						String metodoComando = formBean.getComando();
						metodoComando = Formatter.removeSpaces( metodoComando );
							try{
										
										session = SessionManager.getInstance().getSession(statusTO.getIdsesion().toString());
										clienteTO = (ClienteTO) session.getAttribute(CommandConstantes.CLIENTE_TO);
										String idApplication = SessionManager.getInstance().getAplicationName(session.getIdSession());
										monitoreoService = new CommandMonitorService();
										
										//String idUsuario=String.valueOf(monitoreoService.getIdUsuario(clienteTO.getUserName(), idApplication));
										if(idComando.equals("login")){
											UsuariosTO usuario = new UsuariosTO();
											usuario.setUserName(clienteTO.getUserName());
											usuario.setUserAlnova(clienteTO.getNumero());
											usuario.setAplicacion(idApplication);
											usuario.setNombreCompleto(clienteTO.getNombreCompleto());
											monitoreoService.insertaUsuarios(usuario);
											
										}if(metodoComando.contains("EJECU") && !idComando.equals("mostrar_frecuentes_trans")
												&& !idComando.equals("consulta_frecuentes_tarjetas.pago_credito") 
												&& !idComando.equals("consulta_frecuentes_servicios") 
												&& !idComando.equals("mostrar_frecuentes_tiempo_aire")
												|| idComando.equals("login")){
											
											 //idUsuario=monitoreoService.getIdUsuario(clienteTO.getUserName(), idApplication);
											 String idUsuarioString = String.valueOf(monitoreoService.getIdUsuario(clienteTO.getUserName(), idApplication));
											 if(!idUsuarioString.equals("0")){
												 UsuarioOperacionesTO usuarioOperacion = new UsuarioOperacionesTO();
													usuarioOperacion.setIdUsuarioOperacion(0);
													usuarioOperacion.setOperacion(idComando);
													usuarioOperacion.setIdUsuario(idUsuarioString);
													monitoreoService.insertaUsuarioOperacion(usuarioOperacion);
											 }
											 
									}if(formBean.getComando()!=null && formBean.getComando().contains("EJECU")
												&& Integer.valueOf(response.getStatus().getCodigo_operacion()) == 0
												&& !beanId.equals("logout") && !beanId.equals("mostrar_frecuentes_trans")
												&& !beanId.equals("consulta_frecuentes_tarjetas.pago_credito")
												&& !beanId.equals("consulta_frecuentes_servicios") && !beanId.equals("mostrar_frecuentes_tiempo_aire")
												|| beanId.equals("login") && session.getIdSession()!=null ){
													
													idApplication = SessionManager.getInstance().getAplicationName(session.getIdSession());
													String idUsuarioOperacion= String.valueOf(monitoreoService.getIdUsuarioOperacion(clienteTO.getUserName(),idApplication));
												
													if(!idUsuarioOperacion.equals("0") ){
														DetalleMonitoreoTO detalle = new DetalleMonitoreoTO();
														detalle.setIdDetalle(0);
														detalle.setIdUsuarioOperacion(idUsuarioOperacion);
														detalle.setCodigoOperacion(Integer.valueOf(response.getStatus().getCodigo_operacion()));
														detalle.setError(response.getStatus().getDescripcion_codigo());
														monitoreoService.insertXml(detalle);
														if(!beanId.equals("saldos") && !beanId.equals("movimientos") && !beanId.equals("login")){
															Integer idTracking = monitoreoService.getIdTracking(idUsuarioOperacion);
															toString(response.getDataService(),idTracking);
														}
														
													}
												
												
													
												}else if(Integer.valueOf(response.getStatus().getCodigo_operacion()) < 0 
														&& !beanId.equals("login")
														&& formBean.getComando()!=null && formBean.getComando().contains("EJECU")){
													
													
													clienteTO = (ClienteTO) session.getAttribute(CommandConstantes.CLIENTE_TO);
													idApplication = SessionManager.getInstance().getAplicationName(session.getIdSession());
													monitoreoService = new CommandMonitorService();
													
													String idUsuario=String.valueOf(monitoreoService.getIdUsuario(clienteTO.getUserName(), idApplication));
													 if(!idUsuario.equals("0")){
														 UsuarioOperacionesTO usuarioOperacion = new UsuarioOperacionesTO();
														 DetalleMonitoreoTO detalle = new DetalleMonitoreoTO();
														 usuarioOperacion.setIdUsuarioOperacion(0);
														 usuarioOperacion.setIdUsuario(idUsuario);
														 usuarioOperacion.setOperacion(response.getStatus().getIdservicio());
														 //String idUsuarioOperacion=String.valueOf(monitoreoService.getIdUsuarioOperacion(clienteTO.getUserName(), responseTO.getStatus().getIdservicio()));
														 detalle.setIdDetalle(0);
														 //detalle.setIdUsuarioOperacion(idUsuarioOperacion);
														 detalle.setCodigoOperacion(Integer.valueOf(response.getStatus().getCodigo_operacion()));
														 detalle.setError(response.getStatus().getDescripcion_codigo());
														 monitoreoService.insertError(usuarioOperacion, detalle);
													 }
													
												}
												
												//fin monitoreo
													
							}catch(Exception exception){
								exception.printStackTrace();
							}
						}
					}
							
//				TODO fin feregrino

			//QUITAR LA LLAVE DEL IF PARA BANDERA DE REDIRECCION	
			}
		}
		try {
			responseTO.setStatus(response.getStatus());
			eservicesTO.setResponse(responseTO);
			encoder.add(E_SERVICE, eservicesTO);
			xml = encoder.parseBean(ROOT_TAG);
			//cachar mal acento de fundacion azteca
			if(xml.matches(".*Donativo Fundaci.*Azteca.*")){
				xml=cambioAcentosDonativos(xml);
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

		log.info("-- Antes del Filtro --");
	
		HashMap<String, Object> configuracion = new HashMap<String, Object>();
		String idAplicacion = null;
		if(idsession!=null){
			String resultado = idsession.replaceAll("@@[0-9]*", "");
			idAplicacion = SessionManager.getInstance().getKeyApplication( resultado );
			Cipher cifer = response.getStatus().getIdsesion();
			if(cifer != null){
				session = SessionManager.getInstance().getSession( cifer.toString() );
			}			
		}
		if(idAplicacion!=null){
			configuracion.put("xml", xml);
			configuracion.put("path", ConstantesFiltro.FILTROS_CUENTAS_PATH);
			log.info(ConstantesFiltro.FILTROS_CUENTAS_PATH);
			configuracion.put("session", session);
			configuracion.put(ConstantesFiltro.ID_APLICACION, idAplicacion);
			configuracion.put("idServicio", beanId);
			FilterManager filterManager = new FilterManager( configuracion );
			if(filterManager.filtrarAplicacion(idAplicacion)){
				try {
					filterManager.ejecutaFiltros();
					String xmlRespuesta = (String)configuracion.get("respuesta");
					xml = (String)configuracion.get("xml");
					log.info("Respuesta de filtrado: " + xmlRespuesta);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			log.info("-- Depu�s del Filtro --");
		}
		
		return xml;
	}

	private String cambioAcentosDonativos(String xml) {
		String tmp=null;
		tmp=xml.replaceAll("Donativo Fundación Azteca", "Donativo Fundacion Azteca");
		return tmp;
		
	}

	
	private void appendLeyenda(StatusTO statusTO, String idApp) {

		String leyenda = null;
		try {
			leyenda = PropertiesService.getInstance().getPropertie("eServiceUsers.properties","user.proxyelite." + idApp + ".mensaje");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (leyenda != null) {
			statusTO.setDescripcion_codigo(statusTO.getDescripcion_codigo()
					+ " " + leyenda);
		}
	}

	private String obtenIdApplication(String session) {
		int indexArroba = session.indexOf("@@");
		return session.substring(0, indexArroba);
	}

	private boolean resetSessionID(String idPortal) {
		boolean resetSessionIDFlag = true;

		if (!Validator.isEmptyData(idPortal)) {

			String propertie = "";
			try {

				propertie = "user.proxyelite.reset.id." + idPortal;
				String value = PropertiesService.getInstance().getPropertie(
						PropertiesService.USERS, propertie);
				resetSessionIDFlag = Boolean.parseBoolean(value);

			} catch (Exception e) {

				log.info("Error al obtener la propiedad " + propertie
						+ "\nMessageError: " + e.getMessage());

			}

		}
		return resetSessionIDFlag;
	}

	private Response buildValidationError(MessageErrors messageErrors) {
		Response response = new Response();
		MensajesTO errors = messageErrors.getMessages();
		response.addAttribute(errors);
		response.setStatus(Errors.VALIDATION_CODE, Errors.VALIDATION, null);
		return response;
	}

	private Response execute(Command command) throws Exception {
		Response response = null;
		Session session = null;
		FormBean formBean = command.getFormBean();
		try {
			if (command.isNotSession()) {
				response = command.execute(null);
			} else {
				session = SessionManager.getInstance().getSession(formBean.getIdsesion().toString());
				if (session.getIdSession() != null) {
					if (session.isTimeValidateApplicationSession()
							&& session.isFoundApplicationSession()) {
						response = command.execute(session);
						// response.setIdSession(SessionManager.getInstance().generateNewSessionId(session));
					} else {
						response = new Response();
						if (!session.isFoundApplicationSession()) {
							response.setStatus(
									Errors.SESSION_APPLICATION_NOT_FOUND_CODE,
									"Sesi�n de aplicaci�n incorrecta", null);
						} else if (!session.isTimeValidateApplicationSession()) {
							response.setStatus(
									Errors.SESSION_APPLICATION_EXPIRED,
									"Sesi�n de aplicaci�n expirada", null);
						}
					}
				} else {
					response = new Response();
					response.setStatus(Errors.SESSION_USER_NOT_FOUND_CODE,
							"Sesi�n de cliente incorrecta", null);
				}
			}
		} catch (SessionExpiredException ex) {
			session.invalidate();
			throw new SessionExpiredException(ex);
		}

		return response;
	}

	private String getIdCommand(MessageElement messageElement)
			throws CipherArgumentException {
		String idCommand = (String) decoder.toDeserialize(COMMAND_NAME,
				messageElement, null);
		String operationType = (String) decoder.toDeserialize(
				OPERATION_TYPE_TAG, messageElement, null);
		if (operationType != null && operationType.length() > 0) {
			idCommand = idCommand + SEPARADOR_IDSERVICIO + operationType;
		}
		idCommand = idCommand.toLowerCase();
		return idCommand;
	}

	private String getIdSession(MessageElement messageElement)
			throws CipherArgumentException {
		LoginForm loginForm = new LoginForm();
		FormBean formBean = buildRequestBean(messageElement, loginForm);
		Object idsesion = formBean.getIdsesion();
		String id = null;
		if (idsesion != null) {
			id = idsesion.toString();
		}
		return id;
	}

	private String getIdPortal(MessageElement messageElement)
			throws CipherArgumentException {
		LoginForm loginForm = new LoginForm();
		FormBean formBean = buildRequestBean(messageElement, loginForm);
		Object idportal = formBean.getIdportal();
		String id = null;
		if (idportal != null) {
			id = idportal.toString();
		}
		return id;
	}

	private FormBean buildRequestBean(MessageElement messageElement,
			FormBean formBean) throws CipherArgumentException {
		HashMap<String, String> mapped = new HashMap<String, String>();
		mapped.put(REQUEST_TAG, formBean.getClass().getName());

		if (flag) {
			mapped
					.put("beneficiario",
							"com.bancoazteca.eservice.command.base.beans.BeneficiarioTO");
			mapped.put("coleccion_beneficiarios", "java.util.ArrayList");
		}

		formBean = (FormBean) decoder.toDeserialize(REQUEST_TAG,
				messageElement, mapped);
		return formBean;
	}

	private void generateNewSessionId(String idSession, Response response) {
		if (idSession != null) {
			Session session = SessionManager.getInstance().getSession(idSession);
			String usuario=(String)session.getAttribute(CommandConstantes.NOMBRE_USUARIO);
			if ((session.getIdSession() != null) && (session.isTimeValidateApplicationSession())) {
				response.setIdSession(SessionManager.getInstance().generateNewSessionId(session,usuario));
			}
		}
	}

	private boolean esValidacion(MessageElement messageElement) {
		NodeList nodeList = messageElement.getElementsByTagName(REQUEST_TAG);
		Node node = nodeList.item(0);
		nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			node = nodeList.item(i);
			if (node.toString().equalsIgnoreCase(
					"<comando value=\"validacion\"/>")) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	private String toString(Object objeto,Integer idTracking){
	StringBuffer string = new StringBuffer();
		try {
			String direccion=objeto.toString().substring(1, objeto.toString().indexOf("@"));
			Class cls = Class.forName(direccion);
			Object objectParametrizable=cls.newInstance();
			objectParametrizable=((Collection<String>) objeto).iterator().next();
			Field declaredFieldList[] = cls.getDeclaredFields();
			Field fieldList[] = cls.getSuperclass().getDeclaredFields();
			ArrayList<String> arrayList=new ArrayList<String>();
			for (Field field : declaredFieldList) {
				arrayList.add(field.getName());
			}
			for(Field field:fieldList){
				arrayList.add(field.getName());	
			}
			
			String campo;
			String firstCharacter;
			String metodo;
			String value;
			
			Iterator<String> iteradorFields = arrayList.iterator();
			string.append("/*--------------------  "+cls.getSimpleName()+"  -----------------------*/\n");
			while(iteradorFields.hasNext()) {
			
				try{
					
					campo = iteradorFields.next();                                     
					firstCharacter=campo.substring(0,1).toUpperCase();
					metodo = campo.substring(1);
					metodo=firstCharacter+metodo;
					String metod="get"+metodo;
					Method mtd = cls.getMethod(metod);            	   
					value=(String)mtd.invoke(objectParametrizable,(Object[])null);
					string.append(campo+"=");
					string.append(value+"\n");
					if(value instanceof String && idTracking!=0){
						CommandMonitorService monitor = new CommandMonitorService();
						monitor.insertMapa(0, idTracking, campo, value);
					}
					
				}catch(Exception e){}
			} 
			string.append("--------------------------------------------------------------------\n");
		}
		catch (Throwable e){
			System.out.println("Error");
		}
		return string.toString();
	}

}