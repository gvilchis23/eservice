package com.bancoazteca.elite.ejb.usuario;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.axis.message.MessageElement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bancoazteca.elite.WsInformacionTiendas.Estado;
import com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasDAO;
import com.bancoazteca.elite.WsInformacionTiendas.Municipio;
import com.bancoazteca.elite.WsInformacionTiendas.Tienda;
import com.bancoazteca.elite.beans.ActivarCuentaExpressRequestTO;
import com.bancoazteca.elite.beans.ActivarCuentaExpressResponseTO;
import com.bancoazteca.elite.beans.ActualizaDatosRequestTO;
import com.bancoazteca.elite.beans.ActualizaDatosResponseTO;
import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.BibliotecaRecibosRequestTO;
import com.bancoazteca.elite.beans.BibliotecaRecibosTO;
import com.bancoazteca.elite.beans.ChangePasswordResponseTO;
import com.bancoazteca.elite.beans.ChangePaswordRequestTO;
import com.bancoazteca.elite.beans.ChangeSecurityLevelRequestTO;
import com.bancoazteca.elite.beans.ChangeSecurityLevelResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CompaniaViewTO;
import com.bancoazteca.elite.beans.ConsultaMonitoreoRequestTO;
import com.bancoazteca.elite.beans.CuentaInversionTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DatosPdfBibliotecaServiciosTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.FotoUnicaRequestTO;
import com.bancoazteca.elite.beans.FotoUnicaResponseTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.beans.LlaveSeguridadRequestTO;
import com.bancoazteca.elite.beans.LlaveSeguridadResponseTO;
import com.bancoazteca.elite.beans.LocalizaSucursalRequestTO;
import com.bancoazteca.elite.beans.LocalizaSucursalResponseTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.beans.MonitoreoAdministradorRequestTO;
import com.bancoazteca.elite.beans.MonitoreoResponseTO;
import com.bancoazteca.elite.beans.OperacionFrecuenteTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesRequestTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesResponseTO;
import com.bancoazteca.elite.beans.ParameterOnSessionTO;
import com.bancoazteca.elite.beans.RecuperaPasswordRequestTO;
import com.bancoazteca.elite.beans.RecuperaPasswordResponseTO;
import com.bancoazteca.elite.beans.TarjetaBaseAlnova;
import com.bancoazteca.elite.beans.TokenTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.GeneralRules;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.inversiones.db.dao.InversionesDAO;
import com.bancoazteca.elite.monitoreo.db.dao.ConsultaMonitoreoTO;
import com.bancoazteca.elite.monitoreo.db.dao.MonitorServiceLiteDAO;
import com.bancoazteca.elite.monitoreo.db.dao.MonitoreoAdminLiteDAO;
import com.bancoazteca.elite.monitoreo.db.dao.MonitoreoAdministradorResponseTO;
import com.bancoazteca.elite.monitoreo.db.dao.MonitoreoLiteDAO;
import com.bancoazteca.elite.util.DigitalFingerUtil;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.elite.wsfotounica.FotoUnicaDAO;
import com.bancoazteca.elite.beans.UsuarioOperacionesTO;
import com.bancoazteca.elite.beans.UsuariosTO;
import com.bancoazteca.elite.beans.DetalleMonitoreoTO;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 25, 2008.
 */
public class UsuarioSLBean implements SessionBean {

	/**loginCliente
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -768094163205223097L;

	private static Logger log = Logger.getLogger(UsuarioSLBean.class);

	@SuppressWarnings("unused")
	private SessionContext context;

	/**
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext aContext) {
		context = aContext;
	}

	/**
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() {

	}

	/**
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() {

	}

	/**
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() {

	}

	/**
	 * See section 7.10.3 of the EJB 2.0 specification See section 7.11.3 of the
	 * EJB 2.1 specification
	 */
	public void ejbCreate() {
	}
	
	public void setXmlBeanRules(){
		EliteDAO eliteDAO = new UsuarioDAO();
		eliteDAO.setXmlBeanRules();
	}

	
	public LoginResponseTO loginCliente(LoginRequestTO loginRequestTO) throws UsuarioException, LoginException, SessionExpiredException, EliteDataException 
	{
		
		
//		Login_clienteTO login_clienteTO = new Login_clienteTO();
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		MessageElement messageElement;
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			log.info("logeando ........");
			log.info(loginRequestTO.getUser());
			String html = usuarioDao.login(loginRequestTO);
			
			if (html.indexOf("/consultas/inicio.do") != -1) {
//				login_clienteTO.setMensaje("login exitoso para el usuario: "+loginRequestTO.getUser());
				
				
				messageElement = usuarioDao.getOnDemandAccounts(loginRequestTO);
				
				GeneralRules.validateGeneralRules(messageElement);
				ClienteTO clienteTO = buildClienteTO(messageElement);
				loginResponseTO.setClienteTO(clienteTO);
				
			} else if (html.indexOf("/session/manejoSesion.do") != -1) {
				log.info("Manejo de session ");
				throw new EliteDataException("no cerro adecuadamente la session", "1", EliteRules.LEVEL_PATHS);
			} else if (html.indexOf("maximosIntentos") != -1) {
				log.info("Maximo numero de intentos ");
				throw new EliteDataException("Maximo intentos", "-1", EliteRules.LEVEL_PATHS);
			} else if (html.indexOf("errorLogin") != -1) {
				log.info("Login ");
				throw new LoginException("Error de logeo");
			} else {
				log.info("Error ");
				throw new LoginException("Error de logeo");
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		return loginResponseTO;
	}
	
	public LoginResponseTO getOnDemandAccounts(LoginRequestTO loginRequestTO) throws UsuarioException, LoginException, SessionExpiredException, EliteDataException {	
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			log.info(loginRequestTO.getUser());
			usuarioDao.getOnDemandAccounts(loginRequestTO);
				
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		return loginResponseTO;
	}
	
	
	@SuppressWarnings("unchecked")
	public LoginResponseTO login(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		LoginResponseTO loginResponseTO = new LoginResponseTO();

		MessageElement messageElement = null;
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();

			log.info("logeando ........");
			log.info(loginRequestTO.getUser());
			String html = usuarioDao.login(loginRequestTO);						
			
			if (html.indexOf("/consultas/inicio.do") != -1) {
				
//				ParameterOnSessionTO dd=new ParameterOnSessionTO();
//				dd.setNombreValor("typeName");
//				dd.setUsuario(loginRequestTO.getUser());
//				dd.setValor("crmovil");
//				
//				try {
//					usuarioDao.setParameterOnSession(dd);
//				} catch (URISyntaxException e) {
//					e.printStackTrace();
//				} catch (HttpException e) {
//					e.printStackTrace();
//				}
				
				if (loginRequestTO.isParallel()){																												
					messageElement = usuarioDao.getOnDemandAccounts(loginRequestTO);												
				}else{			
					messageElement = usuarioDao.getCuentas(loginRequestTO);
				}
						
			} else if (html.indexOf("/session/manejoSesion.do") != -1) {
				log.info("Manejo de session ");
				throw new EliteDataException("no cerro adecuadamente la session", "1", EliteRules.LEVEL_PATHS);
			} else if (html.indexOf("maximosIntentos") != -1) {
				log.info("Maximo numero de intentos ");
				throw new EliteDataException("Maximo intentos", "-1", EliteRules.LEVEL_PATHS);
			} else if (html.indexOf("errorLogin") != -1) {
				log.info("Login ");
				throw new LoginException("Error de logeo");
			} else {
				log.info("Error ");
				throw new LoginException("Error de logeo");
			}

			GeneralRules.validateGeneralRules(messageElement);
			ClienteTO clienteTO = buildClienteTO(messageElement);

			loginResponseTO.setClienteTO(clienteTO);

		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		return loginResponseTO;
	}
	
	
	
	public LoginResponseTO getOnDemandDetailAccounts(LoginRequestTO loginRequestTO)throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		MessageElement messageElement = null;
		ClienteTO clienteTO = null;
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();		
			messageElement = usuarioDAO.getOnDemandMultipleAccounts(loginRequestTO,0);		
			
			clienteTO = buildClienteTO(messageElement);					
			loginResponseTO.setClienteTO(clienteTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		
		return loginResponseTO;
	}
	
	public LoginResponseTO sessionManagmentCliente(LoginRequestTO loginRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
//		Login_clienteTO login_clienteTO = new Login_clienteTO();
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		try {
			String html = usuarioDao.setSessionManagment(loginRequestTO);
			if (html.indexOf("/consultas/inicio.do") != -1) {
//				login_clienteTO.setMensaje("login exitoso para el usuario: "+loginRequestTO.getUser());
				MessageElement messageElement = usuarioDao.getOnDemandAccounts(loginRequestTO);
				
				GeneralRules.validateGeneralRules(messageElement);
				ClienteTO clienteTO = buildClienteTO(messageElement);
				loginResponseTO.setClienteTO(clienteTO);
				
			} else {
				throw new UsuarioException("Error de logeo");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		return loginResponseTO;
	}

	public LoginResponseTO sessionManagment(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		MessageElement messageElement = null;
		UsuarioDAO usuarioDao = new UsuarioDAO();
		try {
			String html = usuarioDao.setSessionManagment(loginRequestTO);
//			Linea que funciona con el anterior login - antes 12/julio/2011
//			if (html.indexOf("/consultas/inicio.do") != -1) {
			
//			Linea que funciona con el nuevo login - despues 12/julio/2011
			if (html.indexOf("/consultas/inicioMenu.do") != -1) {
				messageElement = usuarioDao.getCuentas(loginRequestTO);
			} else {
				throw new UsuarioException("Error de logeo");
			}
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateSessionManagment();
			ClienteTO clienteTO = buildClienteTO(messageElement);

			loginResponseTO.setClienteTO(clienteTO);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} 

		return loginResponseTO;
	}
	
	

	public LoginResponseTO getCuentasUsuario(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		MessageElement messageElement = null;
		UsuarioDAO usuarioDao = new UsuarioDAO();
		try {
			messageElement = usuarioDao.getCuentas(loginRequestTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateSessionManagment();
			ClienteTO clienteTO = buildClienteTO(messageElement);
			loginResponseTO.setClienteTO(clienteTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} 

		return loginResponseTO;
	}
	
	/** Obtiene el saldo actualizado del número de cuenta proporcionado.
	 * @param loginRequestTO
	 * @return
	 * @throws EJBException
	 * @throws LoginException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public LoginResponseTO getActualizarCuentaUsuario( BalanceRequestTO balanceRequestTO ) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		MessageElement messageElement = null;
		UsuarioDAO usuarioDao = new UsuarioDAO();
		XmlDecoder xmlDecoder = new XmlDecoder();
		try {
			messageElement = usuarioDao.getActualizarCuenta( balanceRequestTO );
			String estaActualizada = ( String ) xmlDecoder.toDeserialize( "actualizaCuenta", messageElement, null );
			ClienteTO clienteTO = buildClienteTO(messageElement);
			clienteTO.setActualizacionCuenta( Boolean.valueOf( estaActualizada ) );
			loginResponseTO.setClienteTO(clienteTO);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} 

		return loginResponseTO;
	}

	public LoginResponseTO getCuentasUsuarioParalelo(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		LoginResponseTO loginResponseTO = new LoginResponseTO();
		MessageElement messageElement = null;
		UsuarioDAO usuarioDao = new UsuarioDAO();
		try {
			messageElement = usuarioDao.getCuentasParalelo(loginRequestTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateSessionManagment();
			ClienteTO clienteTO = buildClienteTO(messageElement);
			loginResponseTO.setClienteTO(clienteTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} 

		return loginResponseTO;
	}

	@SuppressWarnings("unchecked")
	public synchronized ClienteTO buildClienteTO(MessageElement message) throws XmlDecodeException, UsuarioException, IOException {
		final MessageElement messageElement = new MessageElement(message);
		XmlDecoder decode = new XmlDecoder();		
		HashMap<String,String> mappedBeans = new HashMap<String, String>();
		synchronized (mappedBeans) {
			mappedBeans.put("ebanking_web_ClienteVO", "com.bancoazteca.elite.beans.ClienteTO");
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");
			mappedBeans.put("TarjetaBaseAlnovaVO", "com.bancoazteca.elite.beans.TarjetaBaseAlnova");
			mappedBeans.put("tarjetacorporativa", "com.bancoazteca.elite.beans.TarjetaCorporativaCreditoTO");
			mappedBeans.put("DatosEliteVO", "com.bancoazteca.elite.beans.DatosEliteTO");
			mappedBeans.put("CreditoVO", "com.bancoazteca.elite.beans.CreditoTO");
			mappedBeans.put("securityData", "com.bancoazteca.elite.beans.SecurityDataTO");
			mappedBeans.put("amountsVO", "com.bancoazteca.elite.beans.SecurityAmountsTO");
			mappedBeans.put("secLevelVO", "com.bancoazteca.elite.beans.SecurityLevelTO");
			mappedBeans.put("debitoCorp", "com.bancoazteca.elite.beans.TarjetaCorporativaDebitoTO");
			mappedBeans.put("huellaMuerta", "com.bancoazteca.elite.beans.HuellaDigitalTO");
			mappedBeans.put("CuentaInversionVO", "com.bancoazteca.elite.beans.CuentaInversionTO");
			mappedBeans.put("GanareMasVO", "com.bancoazteca.elite.beans.GanareMasTO");
		}
		
		ClienteTO clienteTO = (ClienteTO) decode.toDeserialize("ebanking_web_ClienteVO", messageElement, mappedBeans);
		DatosEliteTO datosEliteTO = (DatosEliteTO) decode.toDeserialize("DatosEliteVO",messageElement,mappedBeans);
		String tieneOtrosCreditos = (String) decode.toDeserialize("tieneOtrosCreditos",messageElement,mappedBeans);
		
		Collection<TarjetaBaseAlnova> tarjetasBaseAlnova = clienteTO.getTarjetasBaseAlnova();
		if (tarjetasBaseAlnova==null){
			tarjetasBaseAlnova = new ArrayList<TarjetaBaseAlnova>();
		}else{
			Collection<TarjetaBaseAlnova> infiniteCards = getInfiniteCards(tarjetasBaseAlnova);
				
			for(TarjetaBaseAlnova tarjetaInfinite : infiniteCards){
				tarjetaInfinite.setFechaActivacion(Formatter.formatDate(tarjetaInfinite.getFechaActivacion()));
				tarjetaInfinite.setFechaDeCorte(Formatter.formatDate(tarjetaInfinite.getFechaDeCorte()));
				tarjetaInfinite.setFechaDeCorte_Nueva(Formatter.formatDate(tarjetaInfinite.getFechaDeCorte_Nueva()));
				tarjetaInfinite.setFechaFinalDeCorte(Formatter.formatDate(tarjetaInfinite.getFechaFinalDeCorte()));
				tarjetaInfinite.setFechaInicialDeCorte(Formatter.formatDate(tarjetaInfinite.getFechaInicialDeCorte()));
				tarjetaInfinite.setFechaLimiteDePago(Formatter.formatDate(tarjetaInfinite.getFechaLimiteDePago()));
				tarjetaInfinite.setFechaLimiteDePago_Nueva(Formatter.formatDate(tarjetaInfinite.getFechaLimiteDePago_Nueva()));
				tarjetaInfinite.setFechaProximoCorte(Formatter.formatDate(tarjetaInfinite.getFechaProximoCorte()));
				tarjetaInfinite.setFechaProximoPago(Formatter.formatDate(tarjetaInfinite.getFechaProximoPago()));
			}
			Collection<TarjetaBaseAlnova> goldenCards = getGoldenCard(tarjetasBaseAlnova);
			
			Collection<TarjetaBaseAlnova> tarjetasBase = CollectionUtils.subtract(tarjetasBaseAlnova, infiniteCards); 		
			tarjetasBase = CollectionUtils.subtract(tarjetasBase, goldenCards);
			clienteTO.setTarjetasInifite(infiniteCards);
			clienteTO.setGoldenCards(goldenCards);
			clienteTO.setTarjetasBaseAlnova(tarjetasBase);
		}
		int index = 0;
		Collection<LabelValueBean> presentationAccounts = new ArrayList<LabelValueBean>();
		if(clienteTO.getCuentas()!=null){
			clienteTO.setCuentas(formatearCuentas(clienteTO.getCuentas()));	
			
			for (CuentaTO cuentaTO : clienteTO.getCuentas()) {
				cuentaTO.setIndex(String.valueOf(index++)); 
				presentationAccounts.add(new LabelValueBean(cuentaTO.getCuentaFormateada() + " " + cuentaTO.getDescripcion() + " " + Formatter.formatMontoPesos(cuentaTO.getDisponible().doubleValue()), String.valueOf(cuentaTO.getNumero())));
			}
		}
			
		if(clienteTO.getInversiones() != null){
			index = 0;
			Collection<CuentaInversionTO> cuentasInversiones = new ArrayList<CuentaInversionTO>();
			for (CuentaInversionTO cuentaInversionTO : clienteTO.getInversiones()) {
				if(cuentaInversionTO != null){
					cuentaInversionTO.setIndice(String.valueOf(index));
					cuentaInversionTO.setNumeroCuenta(Formatter.formatSplittedCuenta(cuentaInversionTO.getNumeroCuenta()));
				}
				cuentasInversiones.add(cuentaInversionTO);
				index++;
			}
			clienteTO.setInversiones(cuentasInversiones);
		}
		
		clienteTO.setTieneOtrosCreditos(tieneOtrosCreditos);
		clienteTO.setLabelValueBeans(presentationAccounts);
		
		log.info("requiere solicitud de dispositivos "+datosEliteTO.getJspForward());
		if (datosEliteTO.getJspForward().endsWith("/dispositivos/recordatorio.jsp")){
			log.info("requiere solicitud de dispositivos true");
			clienteTO.setDispositives(true);
		}
		clienteTO.setUserName(clienteTO.getAlias());
		
		/*
		 * TODO Esta modificacion en solo para la Mercado de Dinero
		 * */
	
		InversionesDAO inversiones=new InversionesDAO();

		int numeroContrato = 0; 
		try {
			numeroContrato = inversiones.busquedaNumeroContrato(clienteTO.getNumero());
		} catch (InversionesGenericException e) {
			e.printStackTrace();
		}
		clienteTO.setNumContratoInvReportos(String.valueOf(numeroContrato));
	
		return clienteTO;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<TarjetaBaseAlnova> getInfiniteCards(Collection<TarjetaBaseAlnova> tarjetasbaseAlnova)throws XmlDecodeException{
		Collection<TarjetaBaseAlnova> cards = new ArrayList<TarjetaBaseAlnova>();
		final String card = "TARJETA INFINITE";
		final String idTitular = "T";
		final String idAdicional = "A";
		
		InfinitePredicate infinitePredicate = new InfinitePredicate(card,idTitular);
		TarjetaBaseAlnova titular = (TarjetaBaseAlnova) CollectionUtils.find(tarjetasbaseAlnova,infinitePredicate);
		log.info("titular "+titular);
		infinitePredicate.setId(idAdicional);
		Collection<TarjetaBaseAlnova> adicionales = (Collection<TarjetaBaseAlnova>) CollectionUtils.select(tarjetasbaseAlnova, infinitePredicate);
		log.info("adicional "+adicionales);
		if (titular!=null){
			cards.add(titular);
		}
		if (adicionales!=null && adicionales.size()>0){
			cards.addAll(adicionales);
		}
		
		log.info("total de tarjetas infinite "+cards.size());
		return cards;
	}
	
	private Collection<TarjetaBaseAlnova> getGoldenCard(Collection<TarjetaBaseAlnova> tarjetasbaseAlnova)throws XmlDecodeException{
		Collection<TarjetaBaseAlnova> cards = new ArrayList<TarjetaBaseAlnova>();
		final String card = "CREDITO ORO";
		final String idTitular = "T";
		Predicate predicate = new InfinitePredicate(card,idTitular);		
		TarjetaBaseAlnova titular = (TarjetaBaseAlnova) CollectionUtils.find(tarjetasbaseAlnova,predicate);
		log.info("Titular ORO "+titular);
		if (titular!=null){
			cards.add(titular);
		}
		log.info("total de tarjetas ORO " + cards.size());
		return cards;
	}

	public ChangePasswordResponseTO changePassword(ChangePaswordRequestTO changePaswordRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException {
		ChangePasswordResponseTO changePasswordResponseTO = new ChangePasswordResponseTO();
		log.info("method: changePassword");
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			
			
			MessageElement messageElement = usuarioDAO.cambiarPassword(changePaswordRequestTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateChangePasswordErrors();
			
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			changePasswordResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		return changePasswordResponseTO;
	}

	/**
	 * proceso olvido usuario y contraseña paso 1 recibe numero de cuenta y
	 * valida que exista y sea de 14 o 16 digitos
	 * 
	 * @param recuperaPasswordTO
	 *            el objeto que contiene el no. cuenta
	 * @throws EliteDataException
	 * @throws UsuarioException
	 * @throws SessionExpiredException
	 */
	public RecuperaPasswordResponseTO sendAccountOrCreditCardNumber(RecuperaPasswordRequestTO recuperaUsuarioPasswordTO) throws EJBException, EliteDataException, UsuarioException, SessionExpiredException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RecuperaPasswordResponseTO responseTO=new RecuperaPasswordResponseTO();
		try {
			MessageElement messageElement = usuarioDAO.findAccountOrCreditCardNumber(recuperaUsuarioPasswordTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateSendAccountOrCreditCardNumber();

			String querySecurityTemp = "for $a in //com.bancoazteca.ebanking.web.Constants-activacion_key  return <elite><a>{$a}</a></elite>";
			Element element=XMLFinder.getElement(messageElement.toString(), querySecurityTemp);
			messageElement = new MessageElement(element);
			
			XmlDecoder decoder=new XmlDecoder();
			HashMap<String, String> mappedBeans=new HashMap<String, String>();
			mappedBeans.put("com.bancoazteca.ebanking.web.Constants-activacion_key",  RecuperaPasswordResponseTO.class.getCanonicalName());
			
			responseTO=(RecuperaPasswordResponseTO)decoder.toDeserialize("com.bancoazteca.ebanking.web.Constants-activacion_key", messageElement, mappedBeans);
			

		} catch (DAOException e) {
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return responseTO;
	}

	/**
	 * proceso olvido usuario y contraseña paso 2 recibe numero de cuenta y
	 * valida que exista y sea de 14 o 16 digitos
	 * 
	 * @param recuperaPasswordTO
	 *            el objeto que contiene el no. cuenta
	 * @throws EliteDataException
	 * @throws UsuarioException
	 * @throws SessionExpiredException
	 */
	public RecuperaPasswordResponseTO sendPersonalData(RecuperaPasswordRequestTO recuperaPasswordTO) throws EJBException, EliteDataException, UsuarioException, SessionExpiredException {
		RecuperaPasswordResponseTO passwordResponseTO = new RecuperaPasswordResponseTO(); 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {	
			MessageElement messageElement = usuarioDAO.findPersonalData(recuperaPasswordTO);
			
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateSendPersonalData();
			
			
			messageElement=usuarioDAO.findPersonalData1(recuperaPasswordTO);
			usuarioRule=new UsuarioRule(messageElement);
			usuarioRule.validateSendPersonalData1();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();

			mappedBeans.put("datosUsuarioForm", RecuperaPasswordResponseTO.class.getCanonicalName());
			passwordResponseTO = (RecuperaPasswordResponseTO) decode.toDeserialize("datosUsuarioForm", messageElement, mappedBeans);
			
		} catch (DAOException e) {
			throw new UsuarioException(e);
		} 
		return passwordResponseTO;

	}

	public void sendChangePersonalData(RecuperaPasswordRequestTO passwordRequestTO)	throws EJBException, EliteDataException, UsuarioException, SessionExpiredException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		XmlDecoder xmlDecoder = new XmlDecoder();
		RecuperaPasswordResponseTO passwordResponseTO = new RecuperaPasswordResponseTO(); 
		try {
			MessageElement messageElement = usuarioDAO.findChangePersonalData(passwordRequestTO);
			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validateChangePersonalData();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();

			mappedBeans.put("datosUsuarioForm", RecuperaPasswordResponseTO.class.getCanonicalName());
			passwordResponseTO = (RecuperaPasswordResponseTO) decode.toDeserialize("datosUsuarioForm", messageElement, mappedBeans);
			
			
		} catch (DAOException e) {
			throw new UsuarioException(e);
		} 
	}
	
	/**
	 * proceso olvido usuario y contraseña paso 3 recibe to con contraseña nueva
	 * y verificacion
	 * 
	 * @param recuperaPasswordTO
	 *            el objeto que contiene el no. cuenta
	 * @throws EliteDataException
	 * @throws UsuarioException
	 * @throws SessionExpiredException
	 */
	public RecuperaPasswordResponseTO updateToNewPassword(RecuperaPasswordRequestTO recuperaPasswordTO) throws EJBException, EliteDataException, UsuarioException, SessionExpiredException {	
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RecuperaPasswordResponseTO responseTO=new RecuperaPasswordResponseTO();
		RecuperaPasswordResponseTO passwordResponseTO = new RecuperaPasswordResponseTO(); 
		try {

			MessageElement messageElement = usuarioDAO.updateNewPassword(recuperaPasswordTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateSendNewPassword();
			
			messageElement =usuarioDAO.endingNewPassword(recuperaPasswordTO);
			usuarioRule=new UsuarioRule(messageElement);
			usuarioRule.validateEndingNewPassword();
			
			HashMap<String, String> mappedBeans=new HashMap<String, String>();
			XmlDecoder decoder=new XmlDecoder();
			
			mappedBeans.put("datosUsuarioForm", RecuperaPasswordResponseTO.class.getCanonicalName());
			passwordResponseTO = (RecuperaPasswordResponseTO) decoder.toDeserialize("datosUsuarioForm", messageElement, mappedBeans);
			
			
			String querySecurityTemp = "for $a in //com.bancoazteca.ebanking.web.Constants-activacion_key  return <elite><a>{$a}</a></elite>";
			Element element=XMLFinder.getElement(messageElement.toString(), querySecurityTemp);
			messageElement = new MessageElement(element);
			
			
			mappedBeans.clear();
			mappedBeans.put("com.bancoazteca.ebanking.web.Constants-activacion_key",  RecuperaPasswordResponseTO.class.getCanonicalName());
			
			responseTO=(RecuperaPasswordResponseTO)decoder.toDeserialize("com.bancoazteca.ebanking.web.Constants-activacion_key", messageElement, mappedBeans);
			
			passwordResponseTO.setCorreo(responseTO.getCorreo());
			passwordResponseTO.setCelular(responseTO.getCelular());
			passwordResponseTO.setListaCompanias(responseTO.getListaCompanias());
			passwordResponseTO.setCompania(responseTO.getCompania());
			passwordResponseTO.setCarrier(responseTO.getCarrier());
			

		} catch (DAOException e) {
			throw new UsuarioException(e);
		} catch (IOException e) {
			throw new UsuarioException(e);
		} 
		return passwordResponseTO;
	}

	/**
	 * Confirma el cambio de Nivel de Seguridad a trav&eacute;s de ebanking.
	 * 
	 * @param changeSecurityLevelRequestTO
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public ChangeSecurityLevelResponseTO modifySecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException {
		log.info("Method: modifySecurityLevel");
		ChangeSecurityLevelResponseTO changeSecurityLevelResponseTO = new ChangeSecurityLevelResponseTO();
		final String querySecurityTemp = "for $a in //elite/ebanking_web_ClienteVO/securityTemp  return $a";
		Element huellaXml = null;
		MessageElement messageSecurityTemp = null;
		XmlDecoder decoder = null;
		HuellaDigitalTO huellaMuerta = null;
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		HashMap<String, String> mappedBeans = new HashMap<String, String>();
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			MessageElement messageElement = usuarioDAO.modifySecurityLevel(changeSecurityLevelRequestTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateModifySecurityLevel();
			huellaXml = XMLFinder.getElement(messageElement.toString(), querySecurityTemp);
			messageSecurityTemp = new MessageElement(huellaXml);
			decoder = new XmlDecoder();
			mappedBeans.put("huellaMuerta", "com.bancoazteca.elite.beans.HuellaDigitalTO");
			huellaMuerta = (HuellaDigitalTO)decoder.toDeserialize("huellaMuerta", messageSecurityTemp, mappedBeans);
			String lop = (String) decoder.toDeserialize("lop", messageElement, null);
			String llpub = (String) decoder.toDeserialize("llpub", messageElement, null);
			dispositivoHuellaTO.setLongitudHuella(lop);
			dispositivoHuellaTO.setLlavePublica(llpub);
			dispositivoHuellaTO.setHuellaMuerta(huellaMuerta);
			changeSecurityLevelResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error en el acceso a datos", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error decodificando el XML de la huella muerta", e);
		}
		return changeSecurityLevelResponseTO;
	}
	
	
	public BibliotecaRecibosTO getServiciosBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException{
		BibliotecaRecibosTO bibliotecaRecibosTO=new BibliotecaRecibosTO();
		
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			bibliotecaRecibosTO.setMapaServicios(usuarioDAO.getServiciosBiblioecaRecibos(user));
			
		}catch(HttpException e){
			throw new UsuarioException("Ocurrio un error de conección para Biblioteca de Recibos", e);
		}catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurrio un error decodificando el XML de Biblioteca de Recibos", e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}catch (URISyntaxException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		return bibliotecaRecibosTO;
	}
	
	public BibliotecaRecibosTO getBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException{
		BibliotecaRecibosTO bibliotecaRecibosTO=null;
		
		final String querySecurityTemp = "for $a in //elite/bibliotecaServiciosForm return <temp>{$a}</temp>";
		
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			MessageElement response = usuarioDAO.getMenuBiblioecaRecibos(user);
			
			UsuarioRule rules=new UsuarioRule(response);
			rules.validateByActionErrors();
			
			Element xml = XMLFinder.getElement(response.toString(),querySecurityTemp);
			response = new MessageElement(xml);
			
			XmlDecoder decoder=new XmlDecoder();

			HashMap<String, String> mappedBeans=new HashMap<String, String>();
			mappedBeans.put("bibliotecaServiciosForm","com.bancoazteca.elite.beans.BibliotecaRecibosTO");
			mappedBeans.put("BibliotecaVO","com.bancoazteca.elite.beans.ReciboBibliotecaTO");

			bibliotecaRecibosTO=(BibliotecaRecibosTO)decoder.toDeserialize("bibliotecaServiciosForm",response,mappedBeans);

			if(bibliotecaRecibosTO==null || bibliotecaRecibosTO.getBiblioVO()==null || bibliotecaRecibosTO.getBiblioVO().isEmpty()){
				Map<String,String>mapa=new HashMap<String, String>();
				mapa.put("fecha","Recibos no encontrados para el periodo solicitado.");
				throw new EliteDataException("No se han encontrado recibos para esas fechas.",mapa,UsuarioRule.LEVEL_ACTION_ERRORS);
			}
			
			
		}catch(HttpException e){
			throw new UsuarioException("Ocurri\00F3 un error de conección para Biblioteca de Recibos", e);
		}catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error decodificando el XML de Biblioteca de Recibos", e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}catch (URISyntaxException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		return bibliotecaRecibosTO;
	}
	
	@SuppressWarnings("unchecked")
	public BibliotecaRecibosTO getDatosPDFBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException{
		BibliotecaRecibosTO bibliotecaRecibosTO=new BibliotecaRecibosTO();
		
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			MessageElement response = usuarioDAO.getDatosPDFBibliotecaRecibos(user);
			
			UsuarioRule rules=new UsuarioRule(response);
			rules.validateByActionErrors();
			@SuppressWarnings("unused")
			XMLDecode xmlDecode=new XMLDecode();
			XmlDecoder decoder=new XmlDecoder();
			ArrayList arrayReciboImpuestos=null;
			try{
				
				arrayReciboImpuestos=(ArrayList)decoder.toDeserialize("infoPdfImpuestos", response, null);
				bibliotecaRecibosTO.setCollectionReciboImpuestos(arrayReciboImpuestos);
			}catch(Throwable element){
				System.out.println("Al parecer no hay recibos de impuestos...");
			}
			
			try{
				HashMap<String, String> mapeos=new HashMap<String, String>();			
				mapeos.put("loadPDFServicios",DatosPdfBibliotecaServiciosTO.class.getCanonicalName());
				DatosPdfBibliotecaServiciosTO  reciboServicios=(DatosPdfBibliotecaServiciosTO)decoder.toDeserialize("loadPDFServicios", response, mapeos);
				bibliotecaRecibosTO.setReciboServicios(reciboServicios);
			}catch(Throwable throwable){
				System.out.println("Al parecer no hay recibos de servicios...");
			}
			
			
			ParameterOnSessionTO param=new ParameterOnSessionTO();
			if(arrayReciboImpuestos!=null)
				param.setNombreValor("infoPdfImpuestos");
			else
				param.setNombreValor("loadPDFServicios");
				
			param.setValor(null);
			param.setUsuario(user.getUsuario());
			usuarioDAO.setParameterOnSession(param);
			
		}catch(HttpException e){
			throw new UsuarioException("Ocurri\00F3 un error de conección para Biblioteca de Recibos", e);
		}catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error decodificando el XML de Biblioteca de Recibos", e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}catch (URISyntaxException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		return bibliotecaRecibosTO;
	}
	
	/**
	 * Confirma el cambio de Nivel de Seguridad a trav&eacute;s de ebanking.
	 * 
	 * @param changeSecurityLevelRequestTO
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public ChangeSecurityLevelResponseTO waitChangeSecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException {
		log.info("Method: initChangeSecurityLevel");
		ChangeSecurityLevelResponseTO changeSecurityLevelResponseTO = new ChangeSecurityLevelResponseTO();
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			MessageElement messageElement = usuarioDAO.waitChangeSecurityLevel(changeSecurityLevelRequestTO);
			log.info("MessageElement: " + messageElement);
			changeSecurityLevelResponseTO.setMessage(messageElement.toString());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		return changeSecurityLevelResponseTO;
	}

	/**
	 * Realiza el cambio de Nivel de Seguridad a trav&eacute;s de ebanking.
	 * 
	 * @param changeSecurityLevelRequestTO
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public ChangeSecurityLevelResponseTO executeChangeLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException {
		log.info("Method: changeSecurityLevel");
		ChangeSecurityLevelResponseTO changeSecurityLevelResponseTO = new ChangeSecurityLevelResponseTO();
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			MessageElement messageElement = usuarioDAO.executeChangeLevel(changeSecurityLevelRequestTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateChangeSecurityLevel();
			XmlDecoder decoder = new XmlDecoder();
			String message = (String) decoder.toDeserialize("key_mensaje", messageElement, null);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			changeSecurityLevelResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			changeSecurityLevelResponseTO.setMessage(message);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		return changeSecurityLevelResponseTO;
	}

	/**
	 * Sale de la aplicaci&oacute;n.
	 * 
	 * @param username
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public void logout(String username) throws EJBException, SessionExpiredException, UsuarioException {
		UsuarioDAO usuarioDao = null;
		usuarioDao = new UsuarioDAO();
		usuarioDao.logout(username);
	}

	/**
	 * Realizar el formateo de los n&uacute;meros de cuenta de la
	 * colecci&oacute;n que recibe.
	 * 
	 * @param cuentas
	 */
	private Collection<CuentaTO> formatearCuentas(Collection<CuentaTO> cuentas) {
		for (CuentaTO cuentaTO : cuentas) {
			cuentaTO.setCuentaFormateada(Formatter.formatSplittedCuenta(cuentaTO.getNumero()));
			cuentaTO.setCuenctaClabeFormateada(Formatter.formatSplittedCuentaClabe((cuentaTO.getClabe())));
		}
		return cuentas;
	}


	
	private class InfinitePredicate implements Predicate{
		
		private String cardName;
		private String id;
		
		public InfinitePredicate(String cardName,String id){
			this.id = id;
			this.cardName = cardName;
		}
		
		public boolean evaluate(Object obj) {
			boolean flag = false;
			TarjetaBaseAlnova tarjetaBaseAlnova = (TarjetaBaseAlnova) obj;
			String name = tarjetaBaseAlnova.getTipo();
			String idName = tarjetaBaseAlnova.getTitularidad();
			if (name!=null && idName!=null){
				if (name.equalsIgnoreCase(this.cardName) && idName.equalsIgnoreCase(this.id)){
					flag = true;
				}
			}
			return flag;
		}

		public void setCardName(String cardName) {
			this.cardName = cardName;
		}

		public void setId(String id) {
			this.id = id;
		}
		
		
	}
	
	public LlaveSeguridadResponseTO getLlaveSeguridad(LlaveSeguridadRequestTO llaveSeguridadRequestTO) throws EliteDataException, UsuarioException{
		
		UsuarioDAO usuarioDAO=new UsuarioDAO();
		log.info("Inversiones  dao para obtener llave publica");
		MessageElement messageElement=usuarioDAO.getLlaveSeguridad(llaveSeguridadRequestTO);
		UsuarioRule rule=new UsuarioRule(messageElement);
		rule.validateByActionErrors();
		LlaveSeguridadResponseTO llaveSeguridadResponseTO=null;
		String queryLlavePublica="for $llavePublica in //elite/llpub return <llave_publica>{$llavePublica}</llave_publica>";
		String queryLongitud="for $longitudLlavePublica in //elite/lop return <long_llave>{$longitudLlavePublica}</long_llave>";
		try {
			
			Element element=XMLFinder.getElement(messageElement.toString(),queryLlavePublica);
			MessageElement messageElementLlave=new MessageElement(element);
			element=XMLFinder.getElement(messageElement.toString(),queryLongitud);
			MessageElement messageElementLongitudLlave=new MessageElement(element);
			
			XmlDecoder decoder=new XmlDecoder();
			String lop = (String) decoder.toDeserialize("lop", messageElementLongitudLlave, null);
			String llpub = (String) decoder.toDeserialize("llpub", messageElementLlave, null);
			llaveSeguridadResponseTO=new LlaveSeguridadResponseTO();
			llaveSeguridadResponseTO.setLlave(llpub);
			llaveSeguridadResponseTO.setLogitud(lop);
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NullPointerException exception){
			Map<String, String> errors=new HashMap<String, String>();
			errors.put("llavePublica","Ocurrió un error al obtener su huella por favor intente de nuevo.");
			throw new EliteDataException("Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
		}
		return llaveSeguridadResponseTO;
	}
	
	public Boolean validaHuella(HuellaDigitalTO huellaDigitalTO) throws UsuarioException{
		
		UsuarioDAO usuarioDAO=new UsuarioDAO();
		Boolean booleanValido=null;
		try {
			
			MessageElement messageElement=usuarioDAO.validaHuella(huellaDigitalTO);
			
			String queryValidacion="for $valido in //elite/Security_validated return <valido>{$valido}</valido>";
			Element element=XMLFinder.getElement(messageElement.toString(),queryValidacion);
			
			MessageElement messageElementValidacion=new MessageElement(element);
			XmlDecoder decoder=new XmlDecoder();
			String valido = (String) decoder.toDeserialize("Security_validated",messageElementValidacion, null);
			
			booleanValido=Boolean.valueOf(valido);
			
		} catch (SessionExpiredException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error al validar la huella", e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error al validar la huella", e);
		} catch (HttpException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error al validar la huella", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException("Ocurri\00F3 un error al validar la huella", e);
		}
		return booleanValido;
	}
	
	public boolean validaToken(TokenTO tokenTO) throws SessionExpiredException, EliteDataException, UsuarioException
	{
		UsuarioDAO usuarioDAO=new UsuarioDAO();
		boolean booleanValido=false;
		try {
			MessageElement messageElement= usuarioDAO.validaToken(tokenTO);
			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validateByActionErrors();
			
			String queryValidacion="for $valido in //elite/Security_validated return <valido>{$valido}</valido>";
			Element element=XMLFinder.getElement(messageElement.toString(),queryValidacion);
			
			MessageElement messageElementValidacion=new MessageElement(element);
			XmlDecoder decoder=new XmlDecoder();
			String valido = (String) decoder.toDeserialize("Security_validated",messageElementValidacion, null);
			
			booleanValido=Boolean.valueOf(valido);
			
		} catch (UsuarioException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (HttpException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		return booleanValido;
	}

	
	public ActivarCuentaExpressResponseTO activacionExpressInicio(ActivarCuentaExpressRequestTO activarCuentaTO) throws UsuarioException, SessionExpiredException, EliteDataException
	{		
		ActivarCuentaExpressResponseTO cuentaExpressResponseTO = new ActivarCuentaExpressResponseTO();
		try
		{
			UsuarioDAO usuarioDAO=new UsuarioDAO();			
			MessageElement messageElement= usuarioDAO.solicitudCuentaActivacion(activarCuentaTO);	
			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validarActivacionExpressInicio();
			XmlDecoder decoder = new XmlDecoder();			
			String llavePublica = (String) decoder.toDeserialize("llpub", messageElement, null);
			String longitudHuella = (String) decoder.toDeserialize("lop", messageElement, null);
			
			cuentaExpressResponseTO.setLlavePublica(llavePublica);
			cuentaExpressResponseTO.setLongitudHuella(longitudHuella);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} 
		return cuentaExpressResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public ActivarCuentaExpressResponseTO activacionExpressValidacion(ActivarCuentaExpressRequestTO activarCuentaTO) throws UsuarioException, SessionExpiredException, EliteDataException
	{
		ActivarCuentaExpressResponseTO cuentaExpressResponseTO = new ActivarCuentaExpressResponseTO();
		try
		{
//			XmlDecoder decoder = new XmlDecoder();
			UsuarioDAO usuarioDAO=new UsuarioDAO();
			
			MessageElement messageElement= usuarioDAO.validaCuentaActivacion(activarCuentaTO);
			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validarActivacionExpressCuenta();
			
			messageElement = usuarioDAO.contratoActivacionExpress(activarCuentaTO);
			rule = new UsuarioRule(messageElement);
			rule.validarContratoActivacionExpress();
						
//			MessageElement messageElement=usuarioDAO.validaTokenActivacionExpress(activarCuentaTO);
			messageElement =usuarioDAO.validaDisponibilidadAlias(activarCuentaTO);

			NodeList respuesta = messageElement.getElementsByTagName("valido");
			String usuarioValido = respuesta.item(0).getFirstChild().getNodeValue();
						
			if(!usuarioValido.equalsIgnoreCase("true")){
				respuesta = messageElement.getElementsByTagName("resultado");
				String validacionError = respuesta.item(0).getFirstChild().getNodeValue();
				
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("alias", validacionError);
				throw new EliteDataException("Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS);
			}
									
			messageElement =usuarioDAO.ejecutarActivacionExpress(activarCuentaTO);
			rule = new UsuarioRule(messageElement);
			rule.validarEjecucionActivacionExpress();
			
			XMLDecode decode= new XMLDecode();
			Map<String, String> actionErrorsMap = (Map<String, String>) decode.buildMapBeans(messageElement, "org.apache.struts.action.ERROR");
			
			if (actionErrorsMap.containsKey("errorToken")) {
				String status = actionErrorsMap.get("errorToken");
				cuentaExpressResponseTO.setStatusRespuesta(status);
			}else{
				cuentaExpressResponseTO.setStatusRespuesta("Activación realizada con éxito.");
			}
			
			usuarioDAO.logoutActivacionExpress(activarCuentaTO);
			
		}catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} 
				
		return cuentaExpressResponseTO;
	}

	
	public void setParameterOnEBankSession(ParameterOnSessionTO onSessionTO) throws SessionExpiredException, URISyntaxException, HttpException, IOException{
		UsuarioDAO usuarioDAO=new UsuarioDAO();		
		usuarioDAO.setParameterOnSession(onSessionTO);
	}
	
	public void insertaUsuarios(UsuariosTO usuarios){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		monitorServiceDAO.insertaUsuarios(usuarios);
	}
	
	public void insertaUsuarioOperacion(UsuarioOperacionesTO usuarioOperacion){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		monitorServiceDAO.insertaUsuarioOperacion(usuarioOperacion);
	}
	
	
	public void insertXml(DetalleMonitoreoTO detalle){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		monitorServiceDAO.insertXml(detalle);
		
	}
	
	public void insertMapa(Integer idMapa,Integer idTracking,String campo,String valor){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		monitorServiceDAO.insertMapa(idMapa,idTracking,campo,valor);
		
	}
	
	public void insertError(UsuarioOperacionesTO usuarioOperacion,DetalleMonitoreoTO detalle){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		monitorServiceDAO.insertError(usuarioOperacion,detalle);
		
	}
	
	
	
	public Integer getIdUsuario(String userName,String aplicacion){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Integer idUsuario=monitorServiceDAO.getIdUsuarioMonitoreo(userName, aplicacion);
		return idUsuario;
		
	}
	
	public Integer getIdTracking(String idUsuarioOperacion){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Integer idTracking=monitorServiceDAO.getIdTracking(idUsuarioOperacion);
		return idTracking;
		
	}
	
	
	
	public Integer getIdUsuarioOperacion(String userName, String aplicacion){
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Integer idUsuarioOperacion=monitorServiceDAO.getIdUsuarioOperacion(userName,aplicacion);
		return idUsuarioOperacion;
		
	}
	
	public MonitoreoResponseTO getTotalUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getTotalUsuariosAplicacion(requestTO);
		
		
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getUsuariosAplicacion(requestTO);
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getTotalOperacionesByUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getTotalOperacionesByUsuarios(requestTO);
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getTrackingUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getTrackingUsuariosAplicacion(requestTO);
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getOperacionMonto(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getOperacionMonto(requestTO);
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getAllTotalUsuariosAplicacion()throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getAllTotalUsuariosAplicacion();
		
		
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getAllUsuariosAplicacion()throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getAllUsuariosAplicacion();
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getAllTotalOperaByUser()throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getAllTotalOperaByUser();
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getAllTrackingUserAplica()throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		result = monitorServiceDAO.getAllTrackingUserAplica();
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
		
	public Map<String, String> getRangoFechasConsulta (String fechaInicio, String fechaFin) throws EliteDataException {
		Map<String, String> mapFechas = new HashMap<String, String>();
		
		DateFormat formatValidate = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
		    Date getInicio = formatValidate.parse(fechaInicio);
		    Date getFin = formatValidate.parse(fechaFin);
		    if( getInicio.getTime() <= getFin.getTime() ){		    	
		    	long fechaBusquedaFin = getFin.getTime() + 86400000L;
		    	getFin = new Date( fechaBusquedaFin );
		    	log.info( "**************fechaInicio: " + formatSQL.format( getInicio ) );
		    	log.info( "**************fechaFin: " + formatSQL.format( getFin ) );
				
				mapFechas.put( "fechaInicio", formatSQL.format( getInicio ) );
				mapFechas.put( "fechaFin", formatSQL.format( getFin ) );
				
		    }else{
		    	log.info( "Existio un error al formar las fechas del monitoreo" );
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "fechas", "Las fechas de consulta son incorrectas, intente nuevamente" );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
		    }
		} catch (Exception e){
			log.info( "Existio un error al formar las fechas del monitoreo" );
			Map<String, String> errors = new HashMap<String, String>();
			errors.put( "fechas", "Lo sentimos, las fechas de consulta son incorrectas, intente nuevamente" );
			throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
		}
				
		return mapFechas;		
	}
	
	public Map<String, String> getFechaActualConsulta(String fechaInicio, String fechaFin) throws EliteDataException {
		Map<String, String> mapFechas = new HashMap<String, String>();
		try {
			DateFormat formatValidate = new SimpleDateFormat("yyyy/MM/dd");
			if( Validator.isEmptyData( fechaInicio ) || Validator.isEmptyData( fechaFin ) ){
				Date getInicio = formatValidate.parse("25/08/2010");
				Date now = new Date();
				fechaInicio = formatValidate.format(getInicio);
				fechaFin = formatValidate.format(now);
			}
			
			mapFechas.put( "fechaInicio", fechaInicio );
			mapFechas.put( "fechaFin", fechaFin );
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return mapFechas;		
	}
	
	
	public MonitoreoResponseTO getTotalUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		if(requestTO.isConsultaPorFecha()){
			Map<String, String> consultaFechas = getRangoFechasConsulta(requestTO.getConsultaFechaInicio(), requestTO.getConsultaFechaFin());
			result = monitorServiceDAO.getTotalUsuariosPorAplicacionFecha( consultaFechas.get("fechaInicio"), consultaFechas.get("fechaFin") );
		}else{
			result = monitorServiceDAO.getTotalUsuariosPorAplicacion();
		}
		
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();		
		if(requestTO.isConsultaPorFecha()){
			Map<String, String> consultaFechas = getRangoFechasConsulta(requestTO.getConsultaFechaInicio(), requestTO.getConsultaFechaFin());
			result = monitorServiceDAO.getUsuariosPorAplicacionFecha( consultaFechas.get("fechaInicio"), consultaFechas.get("fechaFin") );
		}else{
			result = monitorServiceDAO.getUsuariosPorAplicacion();
		}
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getTrackingUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		if(requestTO.isConsultaPorFecha()){
			Map<String, String> consultaFechas = getRangoFechasConsulta(requestTO.getConsultaFechaInicio(), requestTO.getConsultaFechaFin());
			result = monitorServiceDAO.getTrackingUsuariosPorAplicacionFecha( consultaFechas.get("fechaInicio"), consultaFechas.get("fechaFin") );
		}else{
			result = monitorServiceDAO.getTrackingUsuariosPorAplicacion();
		}
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getTotalOperacionesUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		if(requestTO.isConsultaPorFecha()){
			Map<String, String> consultaFechas = getRangoFechasConsulta(requestTO.getConsultaFechaInicio(), requestTO.getConsultaFechaFin());		
			result = monitorServiceDAO.getTotalOperacionesUsuarioFecha( consultaFechas.get("fechaInicio"), consultaFechas.get("fechaFin") );
		}else{
			result = monitorServiceDAO.getTotalOperacionesUsuario();
		}
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public MonitoreoResponseTO getTotalConexionesUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		MonitoreoResponseTO monitoreoResponse = new MonitoreoResponseTO();
		MonitorServiceLiteDAO monitorServiceDAO = new MonitorServiceLiteDAO();
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		if(requestTO.isConsultaPorFecha()){
			Map<String, String> consultaFechas = getRangoFechasConsulta(requestTO.getConsultaFechaInicio(), requestTO.getConsultaFechaFin());						
			result = monitorServiceDAO.getTotalConexionesUsuarioFecha( consultaFechas.get("fechaInicio"), consultaFechas.get("fechaFin") );
		}else{
			result = monitorServiceDAO.getTotalConexionesUsuario();
		}
		monitoreoResponse.setResultadoConsulta(result);
		
		return monitoreoResponse;
	}
	
	public void insertaUsuariosAdministrador(MonitoreoAdministradorRequestTO administradorRequestTO)throws EJBException, UsuarioException{
		MonitoreoAdminLiteDAO monitoreoAdminDAO = new MonitoreoAdminLiteDAO();
		monitoreoAdminDAO.insertaAdministrador(administradorRequestTO.getUsuario(), administradorRequestTO.getNombre(), administradorRequestTO.getPassword());
	}
	
	public MonitoreoAdministradorResponseTO validarUsuarioAdministrador(MonitoreoAdministradorRequestTO administradorRequestTO) throws EJBException, UsuarioException{
		MonitoreoAdminLiteDAO monitoreoAdminDAO = new MonitoreoAdminLiteDAO();
		return monitoreoAdminDAO.obtieneUsuario(administradorRequestTO.getUsuario(), administradorRequestTO.getPassword());
	}
	
//	TODO LOGICA DE INSERCION HACIA MONITOREO PAUL
	
	public MonitoreoResponseTO getDatosMonitoreoPorUsuario(ConsultaMonitoreoRequestTO monitoreoRequestTO)throws EJBException, UsuarioException{
		MonitoreoResponseTO to=new MonitoreoResponseTO();
		MonitoreoLiteDAO dao=new MonitoreoLiteDAO();
		ArrayList<ConsultaMonitoreoTO> result=null;
		if(monitoreoRequestTO.isConsultaPorFecha()){
			result=dao.obtieneUsuariosPorSessionFecha(monitoreoRequestTO.getConsultaFechaInicio(), monitoreoRequestTO.getConsultaFechaFin());
		}else{
			result=dao.obtieneUsuariosPorSession();
		}
		to.setResultadoConsulta(result);
		return to;
	}
	
	public MonitoreoResponseTO getDatosMonitoreoPorAplicacion(ConsultaMonitoreoRequestTO monitoreoRequestTO)throws EJBException, UsuarioException{
		MonitoreoResponseTO to=new MonitoreoResponseTO();
		MonitoreoLiteDAO dao=new MonitoreoLiteDAO();
		ArrayList<ConsultaMonitoreoTO> result=null;
		if(monitoreoRequestTO.isConsultaPorFecha()){
			result=dao.obtieneUsuariosPorAplicacionFecha(monitoreoRequestTO.getConsultaFechaInicio(), monitoreoRequestTO.getConsultaFechaFin());
		}else{
			result=dao.obtieneUsuariosPorAplicacion();
		}
		to.setResultadoConsulta(result);
		return to;
	}
	
	public ActualizaDatosResponseTO getInitialActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		
		ActualizaDatosResponseTO actualizaDatosTO = new ActualizaDatosResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = usuarioDao.getInitialActualizaDatosDao(datosUsuarioRequestTO);
			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validarActualizarDatosInit();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("usuariosCambioDetallesForm", "com.bancoazteca.elite.beans.ActualizaDatosResponseTO");
			actualizaDatosTO = (ActualizaDatosResponseTO) decode.toDeserialize("usuariosCambioDetallesForm", messageElement, mappedBeans);
			
			mappedBeans.put("CompaniaView", "com.bancoazteca.elite.beans.CompaniaViewTO");
			Collection<CompaniaViewTO> companiasCelulares = (Collection<CompaniaViewTO>) decode.toDeserialize("companias", messageElement, mappedBeans);
			actualizaDatosTO.setCompaniasCelulares(companiasCelulares);
		}catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} 
		
		return actualizaDatosTO;
	}	
	
	public ActualizaDatosResponseTO getActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		
		ActualizaDatosResponseTO actualizaDatosTO = new ActualizaDatosResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		XmlDecoder decode = new XmlDecoder();
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		try {
			MessageElement messageElement = usuarioDao.getActualizaDatosDao(datosUsuarioRequestTO);
			
			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validarActualizarDatos();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("usuariosCambioDetallesForm", "com.bancoazteca.elite.beans.ActualizaDatosResponseTO");
			actualizaDatosTO = (ActualizaDatosResponseTO) decode.toDeserialize("usuariosCambioDetallesForm", messageElement, mappedBeans);			
			mappedBeans.put("CompaniaView", "com.bancoazteca.elite.beans.CompaniaViewTO");
			Collection<CompaniaViewTO> companiasCelulares = (Collection<CompaniaViewTO>) decode.toDeserialize("companias", messageElement, mappedBeans);
			actualizaDatosTO.setCompaniasCelulares(companiasCelulares);
			
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			actualizaDatosTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		}catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		return actualizaDatosTO;
	}
	
	public ActualizaDatosResponseTO getEjecutaActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		
		ActualizaDatosResponseTO actualizaDatosTO = new ActualizaDatosResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		XmlDecoder decode = new XmlDecoder();
		try{
			MessageElement messageElement = usuarioDao.getEjecutaActualizaDatosDao(datosUsuarioRequestTO);

			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validarActualizarDatosEjecutar();
			actualizaDatosTO.setMensaje("Estimado usuario, tu información personal ha sido actualizada con éxito.");
		}catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		
		return actualizaDatosTO;
	}	
	
	public ChangePasswordResponseTO getInitialCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		ChangePasswordResponseTO cambiarContrasenaResponseTO = new ChangePasswordResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		try{
			
			MessageElement messageElement = usuarioDao.getInitialCambiarContrasena(cambiarContrasenaRequestTO);
			UsuarioRule rule = new UsuarioRule(messageElement);
			rule.validarCambioContrasenaInitial();
			
		}catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		return cambiarContrasenaResponseTO;
	}
	
	public ChangePasswordResponseTO getEjecutaCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		ChangePasswordResponseTO cambiarContrasenaResponseTO = new ChangePasswordResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		try{
			MessageElement messageElement = usuarioDao.getEjecutaCambiarContrasena(cambiarContrasenaRequestTO);
			UsuarioRule usuarioRule = new UsuarioRule(messageElement);
			usuarioRule.validateExecChangePasswordErrors();
			cambiarContrasenaResponseTO.setMessaje("Estimado usuario, su contraseña ha sido cambiada con éxito.");
		}catch (DAOException e) {
			e.printStackTrace();
			throw new UsuarioException(e);
		}
		return cambiarContrasenaResponseTO;		
	}	
	
	//localiza tu sucursal
	public LocalizaSucursalResponseTO getObtieneEstados(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();	
		InformacionTiendasDAO informacionTiendasDAO = new InformacionTiendasDAO();
		try{
			
			Estado [] estados = informacionTiendasDAO.getObtieneEstadosDAO(localizaSucursalRequestTO);
			localizaSucursalResponseTO.setEstados(estados);
		}catch(DAOException e){
			throw new UsuarioException(e);
		}
		return localizaSucursalResponseTO;
	}
	
	public LocalizaSucursalResponseTO getObtieneMunicipios(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();	
		InformacionTiendasDAO informacionTiendasDAO = new InformacionTiendasDAO();
		try{
			
			Municipio [] municipios = informacionTiendasDAO.getObtieneMunicipiosDAO(localizaSucursalRequestTO);
			localizaSucursalResponseTO.setMunicipios(municipios);
		}catch(DAOException e){
			throw new UsuarioException(e);
		}
		return localizaSucursalResponseTO;
	}
	
	public LocalizaSucursalResponseTO getObtieneTienda(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();	
		InformacionTiendasDAO informacionTiendasDAO = new InformacionTiendasDAO();
		try{
			
			Tienda []tienda = informacionTiendasDAO.getObtieneTiendaDAO(localizaSucursalRequestTO);
			localizaSucursalResponseTO.setTienda(tienda);
		}catch(DAOException e){
			throw new UsuarioException(e);
		}
		return localizaSucursalResponseTO;
	}
	
	public LocalizaSucursalResponseTO getObtieneTiendaCP(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();	
		InformacionTiendasDAO informacionTiendasDAO = new InformacionTiendasDAO();
		try{
			
			Tienda []tienda = informacionTiendasDAO.getObtieneTiendaCPDAO(localizaSucursalRequestTO);
			localizaSucursalResponseTO.setTienda(tienda);
		}catch(DAOException e){
			throw new UsuarioException(e);
		}
		return localizaSucursalResponseTO;
	}
	
	public LocalizaSucursalResponseTO getObtieneTiendaPalabra(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();	
		InformacionTiendasDAO informacionTiendasDAO = new InformacionTiendasDAO();
		try{
			
			Tienda []tienda = informacionTiendasDAO.getObtieneTiendaPalabraDAO(localizaSucursalRequestTO);
			localizaSucursalResponseTO.setTienda(tienda);
		}catch(DAOException e){
			throw new UsuarioException(e);
		}
		return localizaSucursalResponseTO;
	}
	
	public LocalizaSucursalResponseTO getObtieneTiendas(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		LocalizaSucursalResponseTO localizaSucursalResponseTO = new LocalizaSucursalResponseTO();	
		InformacionTiendasDAO informacionTiendasDAO = new InformacionTiendasDAO();
		try{
			
			Tienda []tienda = informacionTiendasDAO.getObtieneTiendasDAO(localizaSucursalRequestTO);
			localizaSucursalResponseTO.setTienda(tienda);
		}catch(DAOException e){
			throw new UsuarioException(e);
		}		
		return localizaSucursalResponseTO;
	}
	//localiza tu sucursal
	
	

	//operaciones frecuentes
	public OperacionesFrecuentesResponseTO setDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		OperacionesFrecuentesResponseTO operacionesFrecuentesResponseTO = new OperacionesFrecuentesResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		XmlDecoder decoder = new XmlDecoder();
		try {
			
			log.info("operacionesFrecuentesRequestTO.getAlias-"+operacionesFrecuentesRequestTO.getAlias());
			log.info("operacionesFrecuentesRequestTO.getCadenaDatos-"+operacionesFrecuentesRequestTO.getCadenaDatos());
			log.info("operacionesFrecuentesRequestTO.getOperacion-"+operacionesFrecuentesRequestTO.getOperacion());
			log.info("operacionesFrecuentesRequestTO.getUsuarioAlnova-"+operacionesFrecuentesRequestTO.getUsuarioAlnova());
			
			MessageElement messageElement = usuarioDao.insertaOperacionFrecuente(operacionesFrecuentesRequestTO);
			String mensaje = (String) decoder.toDeserialize("mensaje_agrega_operacion_frecuente", messageElement, null);
			operacionesFrecuentesResponseTO.setMensaje(mensaje);
		} catch (EJBException e) {
			throw new UsuarioException(e);
		} catch (DAOException e) {
			throw new UsuarioException(e);
		}
		return operacionesFrecuentesResponseTO;
	}
	
	public OperacionesFrecuentesResponseTO getDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		OperacionesFrecuentesResponseTO operacionesFrecuentesResponseTO = new OperacionesFrecuentesResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		XmlDecoder decoder = new XmlDecoder();
		HashMap<String,String> mappedBeans = new HashMap<String, String>();
		try {	
			log.info("operacionesFrecuentesRequestTO.getUsuarioAlnova-"+operacionesFrecuentesRequestTO.getUsuarioAlnova());
			MessageElement messageElement = usuarioDao.consultaOperacionFrecuente(operacionesFrecuentesRequestTO);
			mappedBeans.put("OperacionFrecuenteTO", "com.bancoazteca.elite.beans.OperacionFrecuenteTO");
			Collection <OperacionFrecuenteTO> listaDatos = (Collection <OperacionFrecuenteTO>)decoder.toDeserialize("coleccion_operacion_frecuentes", messageElement, mappedBeans);
			operacionesFrecuentesResponseTO.setListaDatos(listaDatos);
		} catch (EJBException e) {
			throw new UsuarioException(e);
		} catch (DAOException e) {
			throw new UsuarioException(e);
		}
		return operacionesFrecuentesResponseTO;
	}
	
	public OperacionesFrecuentesResponseTO eliminaDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		OperacionesFrecuentesResponseTO operacionesFrecuentesResponseTO = new OperacionesFrecuentesResponseTO();
		UsuarioDAO usuarioDao = new UsuarioDAO();
		XmlDecoder decoder = new XmlDecoder();
		try {
			log.info("operacionesFrecuentesRequestTO.getClaveOperacion-"+operacionesFrecuentesRequestTO.getClave_operacion());
			log.info("operacionesFrecuentesRequestTO.getUsuarioAlnova-"+operacionesFrecuentesRequestTO.getUsuarioAlnova());
			MessageElement messageElement = usuarioDao.eliminaOperacionFrecuente(operacionesFrecuentesRequestTO);
			String mensaje = (String) decoder.toDeserialize("mensaje_elimina_operacion_frecuente", messageElement, null);
			operacionesFrecuentesResponseTO.setMensaje(mensaje);
		} catch (EJBException e) {
			throw new UsuarioException(e);
		} catch (DAOException e) {
			throw new UsuarioException(e);
		}
		return operacionesFrecuentesResponseTO;
	}
	
//	operaciones frecuentes
	
//  web service foto unica
	public FotoUnicaResponseTO consultaFotoUnica(FotoUnicaRequestTO fotoUnicaRequestTO)throws SessionExpiredException, UsuarioException, EliteDataException{
		FotoUnicaResponseTO fotoUnicaResponseTO = new FotoUnicaResponseTO();
		FotoUnicaDAO fotoUnicaDAO = new FotoUnicaDAO();
		MessageElement messageElement = null;
		try {
			messageElement = fotoUnicaDAO.consultaFotoUnicaDAO(fotoUnicaRequestTO);
			String error = messageElement.toString();
			if(error.contains("Error xmlns=\"\"") ){
				fotoUnicaResponseTO.setFotoUsuario("");
				fotoUnicaResponseTO.setStatus("ERROR CONEXION");
			}else{
				String idStatus = (String) toDeserialize("IDSTATUS", messageElement);
				String status = (String) toDeserialize("STATUS", messageElement);
				if(idStatus.equals("1")){
					String mensaje = (String) toDeserialize("FCFOTO", messageElement);
					fotoUnicaResponseTO.setFotoUsuario(mensaje);	
				}else if(idStatus.equals("2")){
					fotoUnicaResponseTO.setFotoUsuario("");	
				}
				fotoUnicaResponseTO.setStatus(status);
			}
			
		} catch (DAOException e) {
			throw new UsuarioException(e);
		} catch (Exception e) {
			throw new UsuarioException(e);
		}
		
		return fotoUnicaResponseTO;
	}	
	
	public Object toDeserialize(String tagName, MessageElement messageElement)throws Exception{
	    Object objectBase = null;
	    
	    Node nodeBase = messageElement.getElementsByTagName(tagName).item(0);
	    if(nodeBase != null){
	    	objectBase = nodeBase.getChildNodes().item(0).toString();
	    }
	    
	    return objectBase;
  }

//  web service foto unica
}