package com.bancoazteca.elite.ejb.dispositivos;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.XPathException;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.Element;

import com.bancoazteca.elite.beans.ActivacionFirmaRequestTO;
import com.bancoazteca.elite.beans.ActivacionFirmaResponseTO;
import com.bancoazteca.elite.beans.BloqueoFirmaRequestTO;
import com.bancoazteca.elite.beans.BloqueoFirmaResponseTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.ListaTokenVO;
import com.bancoazteca.elite.beans.LugarEntregaTO;
import com.bancoazteca.elite.beans.PedidoTO;
import com.bancoazteca.elite.beans.RastreoPedidoRequestTO;
import com.bancoazteca.elite.beans.RastreoPedidoResponseTO;
import com.bancoazteca.elite.beans.SincronizacionFirmaRequestTO;
import com.bancoazteca.elite.beans.SincronizacionFirmaResponseTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoRequestTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoResponseTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.dispositivos.util.DispositivosTipoValidacionEnum;
import com.bancoazteca.elite.ejb.dispositivos.util.TipoOperacionTokenEnum;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.DigitalFingerUtil;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 25, 2008.
 */
public class DispositivoSLBean implements SessionBean {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -768094163205223097L;
	// private static Logger log = Logger.getLogger(DispositivoSLBean.class);

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

	public void setXmlBeanRules() {
		EliteDAO eliteDAO = new DispositivoDAO();
		eliteDAO.setXmlBeanRules();
	}
	
	public RastreoPedidoResponseTO rastreoPedido(RastreoPedidoRequestTO rastreoPedidoRequestTO) throws SessionExpiredException, DispositivoException, EliteDataException {
		RastreoPedidoResponseTO responseTO = new RastreoPedidoResponseTO();
		XMLDecode decode = new XMLDecode();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		try {
			MessageElement messageElement = dispositivoDAO.resultadoRastreoCmd(rastreoPedidoRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateInitialServiceRastreoPedido();
			responseTO = (RastreoPedidoResponseTO) decode.buildBean(RastreoPedidoResponseTO.class, messageElement, "resRastreo");
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return responseTO;
	}

	@SuppressWarnings("unchecked")
	public BloqueoFirmaResponseTO solicitaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		BloqueoFirmaResponseTO bloqueoFirmaResponseTO = new BloqueoFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();

		String query = "for $lista in //elite/listaToken return $lista";

		try {
			MessageElement messageElement = dispositivoDAO.solicitaBloqueoToken(bloqueoFirmaRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateSolicitaBloqueoToken();

			XmlDecoder decode = new XmlDecoder();
			String tokenEspera = (String) decode.toDeserialize("tokenEspera",	messageElement, null);
			tokenEspera = tokenEspera == null ? "No" : tokenEspera;
			bloqueoFirmaResponseTO.setTokenEspera(tokenEspera);
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			XMLDecode decoder = new XMLDecode();
				
			Collection<ListaTokenVO> lista = (Collection<ListaTokenVO>) decoder.buildCollectionBeans(ListaTokenVO.class, messageElement, "ListaTokenVO");
			bloqueoFirmaResponseTO.setListaTokenVO(lista);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return bloqueoFirmaResponseTO;
	}

	public BloqueoFirmaResponseTO validaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		BloqueoFirmaResponseTO bloqueoFirmaResponseTO = new BloqueoFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();

		try {
			MessageElement messageElement = dispositivoDAO.validaBloqueoToken(bloqueoFirmaRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateBloqueoToken();

			XMLDecode decoder = new XMLDecode();

			ListaTokenVO tokenVO = (ListaTokenVO) decoder.buildBean(ListaTokenVO.class, messageElement, "ListaTokenVO");
			bloqueoFirmaResponseTO.setTokenVO(tokenVO);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return bloqueoFirmaResponseTO;
	}

	public BloqueoFirmaResponseTO ejecutaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException,	DispositivoException, SessionExpiredException, EliteDataException {
		BloqueoFirmaResponseTO bloqueoFirmaResponseTO = new BloqueoFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();

		try {

			MessageElement messageElement = null;
			if (bloqueoFirmaRequestTO.getTipoServicio() == TipoOperacionTokenEnum.BLOQUEAR) {
				messageElement = dispositivoDAO.ejecutaBloqueoToken(bloqueoFirmaRequestTO);
				DispositivoRule rule = new DispositivoRule(messageElement);
				rule.validateEjecutaBloqueoToken();
				
				bloqueoFirmaResponseTO.setEstatusToken("BLOQUEADO");
			}
			if (bloqueoFirmaRequestTO.getTipoServicio() == TipoOperacionTokenEnum.DESBLOQUEAR) {
				messageElement = dispositivoDAO.ejecutaDesbloqueo(bloqueoFirmaRequestTO);
				DispositivoRule rule = new DispositivoRule(messageElement);
				rule.validateEjecutaDesbloqueo();
				
				bloqueoFirmaResponseTO.setEstatusToken("BLOQUEADO *");
			}
			if (bloqueoFirmaRequestTO.getTipoServicio() == TipoOperacionTokenEnum.CANCELAR) {
				messageElement = dispositivoDAO.ejecutaCancelacion(bloqueoFirmaRequestTO);
				DispositivoRule rule = new DispositivoRule(messageElement);
				rule.validateEjecutaCancelacion();
				
				bloqueoFirmaResponseTO.setEstatusToken("CANCELADO");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return bloqueoFirmaResponseTO;
	}

	public ActivacionFirmaResponseTO solicitaActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO)	throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		ActivacionFirmaResponseTO activacionFirmaResponseTO = new ActivacionFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();

		try {

			MessageElement messageElement = dispositivoDAO.solicitaActivacion(activacionFirmaRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateSolicitaActivacion();

		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return activacionFirmaResponseTO;
	}

	@SuppressWarnings("unchecked")
	public ActivacionFirmaResponseTO validaActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		ActivacionFirmaResponseTO activacionFirmaResponseTO = new ActivacionFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		XmlDecoder decode = new XmlDecoder();

		try {

			HashMap<String, String> map = new HashMap<String, String>();
			MessageElement messageElement = null;
			if (activacionFirmaRequestTO.getMetodo() == DispositivosTipoValidacionEnum.NUMERO_DE_SERIE) {
				messageElement = dispositivoDAO.validaNumeroSerieActivacionToken(activacionFirmaRequestTO);
				DispositivoRule rule = new DispositivoRule(messageElement);
				rule.validateNumeroSerieActivacionToken();
			} else {
				messageElement = dispositivoDAO.validaTokenActivacion(activacionFirmaRequestTO);
				DispositivoRule rule = new DispositivoRule(messageElement);
				rule.validateTokenActivacion();

				messageElement = dispositivoDAO.validaTokenActivacionPre(activacionFirmaRequestTO);
				rule = new DispositivoRule(messageElement);
				rule.validateTokenActivacionPre();
			}
			if (activacionFirmaRequestTO.getMetodo() == DispositivosTipoValidacionEnum.TOKEN2) {
				String validarNIP = (String) decode.toDeserialize("validarNIP",	messageElement, null);
				map.put("syncroTokenForm", "com.bancoazteca.elite.beans.ActivacionFirmaResponseTO");
				activacionFirmaResponseTO = (ActivacionFirmaResponseTO) decode.toDeserialize("syncroTokenForm", messageElement, map);
	
				if (validarNIP != null && validarNIP.equalsIgnoreCase("SI")) {
					activacionFirmaResponseTO.setValidarNip(validarNIP);
						messageElement = dispositivoDAO.validaTokenActivacionJSP(activacionFirmaRequestTO);
						messageElement = dispositivoDAO.validaNumeroSerieActivacionToken(activacionFirmaRequestTO);

						map.put("cuentasDispositivos", "java.lang.Collection");
						map.put("CuentaLO",	"com.bancoazteca.elite.beans.CuentaLoTO");
						Collection<CuentaLoTO> lista = (Collection<CuentaLoTO>) decode.toDeserialize("cuentasDispositivos",	messageElement, map);
						activacionFirmaResponseTO.setCuentas(lista);
				} else {
					activacionFirmaResponseTO.setValidarNip("NO");
					DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
					DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
					dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
					activacionFirmaResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
				}
			}

			String numero_serie = (String) decode.toDeserialize("numSerie",	messageElement, null);
			activacionFirmaResponseTO.setNumeroSerie(numero_serie);

			String preToken = (String) decode.toDeserialize("preToken",	messageElement, null);
			activacionFirmaResponseTO.setPreToken(preToken);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return activacionFirmaResponseTO;
	}

	public ActivacionFirmaResponseTO ejecutaActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		ActivacionFirmaResponseTO activacionFirmaResponseTO = new ActivacionFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		XmlDecoder decode = new XmlDecoder();
		
		try {
			MessageElement messageElement = dispositivoDAO.ejecutaActivacionToken(activacionFirmaRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateEjecutaActivacion();
			
			Date fechaActual = new Date();
			DateFormatSymbols symbols = new DateFormatSymbols();
			SimpleDateFormat dateFormatGuiones = new SimpleDateFormat( "dd-MMMM-yyyy", symbols );
			
			activacionFirmaResponseTO.setFechaAct(dateFormatGuiones.format(fechaActual));
			String numero_serie = (String) decode.toDeserialize("numSerie",	messageElement, null);
			activacionFirmaResponseTO.setNumeroSerie(numero_serie);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return activacionFirmaResponseTO;
	}

	@SuppressWarnings("unchecked")
	public SincronizacionFirmaResponseTO solicitaSincronizacionToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException,	EliteDataException {
		SincronizacionFirmaResponseTO sincronizacionFirmaResponseTO = new SincronizacionFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();

		String query = "for $lista in //elite/listaToken return $lista";

		try {
			MessageElement messageElement = dispositivoDAO.solicitaSincronizaToken(sincronizacionFirmaRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateSolicitaSincronizacionToken();
			
			XmlDecoder decode = new XmlDecoder();
			String tokenEspera = (String) decode.toDeserialize("tokenEspera",	messageElement, null);
			tokenEspera = tokenEspera == null ? "No" : tokenEspera;
			sincronizacionFirmaResponseTO.setTokenEspera(tokenEspera);

			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			XMLDecode decoder = new XMLDecode();

			Collection<ListaTokenVO> lista = (Collection<ListaTokenVO>) decoder.buildCollectionBeans(ListaTokenVO.class, messageElement, "ListaTokenVO");
			sincronizacionFirmaResponseTO.setListaTokenVO(lista);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return sincronizacionFirmaResponseTO;
	}

	public SincronizacionFirmaResponseTO validaSincronizacionToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		SincronizacionFirmaResponseTO sincronizacionFirmaResponseTO = new SincronizacionFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		XmlDecoder decode = new XmlDecoder();

		try {

			MessageElement messageElement = dispositivoDAO.validaNumeroSerieSincronizaToken(sincronizacionFirmaRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateNumeroSerieSincronizacionToken();

			messageElement = dispositivoDAO.ejecutaSincronizaTokenPre(sincronizacionFirmaRequestTO);
			rule = new DispositivoRule(messageElement);
			rule.validateEjecutaSincronizacionTokenPre();

			String numero_serie = (String) decode.toDeserialize("numSerie",	messageElement, null);
			sincronizacionFirmaResponseTO.setNumeroSerie(numero_serie);

			String preToken = (String) decode.toDeserialize("preToken",	messageElement, null);
			sincronizacionFirmaResponseTO.setPreToken(preToken);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return sincronizacionFirmaResponseTO;
	}

	public SincronizacionFirmaResponseTO ejecutaSincronizacionToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		SincronizacionFirmaResponseTO sincronizacionFirmaResponseTO = new SincronizacionFirmaResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();

		try {
			MessageElement messageElement = dispositivoDAO.ejecutaSincronizaTokenPre(sincronizacionFirmaRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validateEjecutaSincronizacionTokenPre();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return sincronizacionFirmaResponseTO;
	}

	@SuppressWarnings("unchecked")
	public SolicitudDispositivoResponseTO setInitialSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		SolicitudDispositivoResponseTO responseTO = new SolicitudDispositivoResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		XmlDecoder xmlDecoder = new XmlDecoder();
		MessageElement messageDireccion=null;
		String query="for $a in //elite/direccion return <temp>{$a}</temp>";
		
		try {

			MessageElement messageElement = dispositivoDAO.setInitialSolicitudDispositivo(solicitudDispositivoRequestTO);
			messageElement = dispositivoDAO.getSesionSolicitudDispositivo(solicitudDispositivoRequestTO);
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validaInicialSolicitudDispositivo();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("cuentasDispositivos", "java.lang.Collection");
			map.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			map.put("direccion", "java.util.Map");
			Collection<CuentaLoTO> lista = (Collection<CuentaLoTO>) xmlDecoder.toDeserialize("cuentasDispositivos", messageElement, map);

			responseTO.setCostoToken( ( String ) xmlDecoder.toDeserialize("costoToken", messageElement, null) );
			responseTO.setCostoLector( ( String ) xmlDecoder.toDeserialize("costoLector", messageElement, null ) );
			responseTO.setCuentas( lista );
			responseTO.setPresentarToken( ( String ) xmlDecoder.toDeserialize("presentarToken", messageElement, null ) );
			
			Element element=XMLFinder.getElement(messageElement.toString(),query);
			messageDireccion = new MessageElement(element);
			Map<String, String> mapaDireccion = ( Map<String, String> ) xmlDecoder.toDeserialize( "direccion", messageDireccion, map );
			responseTO.setEstado( mapaDireccion.get( "estado" ) );
			responseTO.setDireccion( mapaDireccion.get( "direccion" ) );
			responseTO.setCiudad( mapaDireccion.get( "ciudad" ) );
			responseTO.setColonia( mapaDireccion.get( "colonia" ) );
			responseTO.setCodigoPostal( mapaDireccion.get( "cp" ) );
			responseTO.setTelefono( mapaDireccion.get( "telefono" ) );
			
		} catch(DAOException e) {
			throw new DispositivoException( e );
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return responseTO;
	}

	@SuppressWarnings("unchecked")
	public SolicitudDispositivoResponseTO setDataSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		SolicitudDispositivoResponseTO responseTO = new SolicitudDispositivoResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("lugarEntregaForm", "com.bancoazteca.elite.beans.LugarEntregaTO");
		map.put("pedidoDis", "com.bancoazteca.elite.beans.PedidoTO");
		map.put( "estadosM", "java.util.Collection" );
		map.put( "municipios", "java.util.Collection" );
		XmlDecoder xmlDecoder = new XmlDecoder();
		try {
			MessageElement messageElement;
			if(solicitudDispositivoRequestTO.isModificarDireccion()){
				messageElement = getCatalogoEstados(solicitudDispositivoRequestTO, dispositivoDAO);
			}
			solicitudDispositivoRequestTO.setSubmith("CONTINUAR");
			messageElement = dispositivoDAO.setDataSolicitudDispositivo( solicitudDispositivoRequestTO );
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validaDatosSolicitudDispositivo();

			llenarDatosSolicitudDispositivo( responseTO, map, xmlDecoder, messageElement );
		} catch(DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();			
			throw new DispositivoException(e);
		}
		return responseTO;
	}

	/**
	 * Obtiene la lista de estados y municipios requeridos para realizar 
	 * el cambio de dirección en la solicitud de dispositivos de seguridad.
	 * @param solicitudDispositivoRequestTO
	 * @param dispositivoDAO 
	 * @return Objeto que representa la respuesta XML.
	 * @throws DAOException Si ocurre un error al establecer la conexión.
	 * @throws SessionExpiredException Cuando la sesión del usuario ha expirado.
	 */
	private MessageElement getCatalogoEstados(
			SolicitudDispositivoRequestTO solicitudDispositivoRequestTO,
			DispositivoDAO dispositivoDAO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement;
		solicitudDispositivoRequestTO.setSubmith("MODIFICA_DIRECCION");
		messageElement = dispositivoDAO.setDataSolicitudDispositivo( solicitudDispositivoRequestTO );
		return messageElement;
	}

	
	@SuppressWarnings("unchecked")
	public SolicitudDispositivoResponseTO getCatalogoEstadosDispositivos(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		SolicitudDispositivoResponseTO responseTO = new SolicitudDispositivoResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put( "estadosM", "java.util.Collection" );
		map.put( "municipios", "java.util.Collection" );
		XmlDecoder xmlDecoder = new XmlDecoder();
		try {
			MessageElement messageElement = getCatalogoEstados( solicitudDispositivoRequestTO, dispositivoDAO );
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validaCatalogoEstados();

			Collection<String> estados = ( Collection<String> ) xmlDecoder.toDeserialize("estadosM", messageElement, map);

			messageElement = dispositivoDAO.getCatalogoMunicipiosSolicitudDispositivos();
			ArrayList<String> municipios = new ArrayList<String>();

			String xml = messageElement.toString();
			while( xml != null && !xml.equals( "" ) )
				xml = generaListaMunicipios( xml, 1000, municipios );

			responseTO.setListaEstados( estados );
			responseTO.setListaMunicipios( municipios );
		} catch(DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return responseTO;
	}

	
	public SolicitudDispositivoResponseTO setCuentaSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		SolicitudDispositivoResponseTO responseTO = new SolicitudDispositivoResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("lugarEntregaForm", "com.bancoazteca.elite.beans.LugarEntregaTO");
		map.put("pedidoDis", "com.bancoazteca.elite.beans.PedidoTO");
		map.put("direccion", "java.util.Map" );
		XmlDecoder xmlDecoder = new XmlDecoder();
		try {
			MessageElement messageElement;
			
			messageElement = dispositivoDAO.setCuentaSolicitudDispositivo( solicitudDispositivoRequestTO );
			DispositivoRule rule = new DispositivoRule(messageElement);
			rule.validaCuentaSolicitudDispositivo();
			llenarDatosSolicitudDispositivosCuenta( responseTO, map, xmlDecoder, messageElement );
			
		} catch(DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return responseTO;
	}
	
	@SuppressWarnings("unchecked")
	private void llenarDatosSolicitudDispositivosCuenta( SolicitudDispositivoResponseTO responseTO, HashMap<String, String> map, 
			XmlDecoder xmlDecoder, MessageElement messageElement) throws IOException {
		MessageElement message=null;
		String query="for $a in //elite/direccion return <temp>{$a}</temp>";
		Element element=XMLFinder.getElement(messageElement.toString(),query);
		
		message = new MessageElement(element);
		Map<String, String> direccion= (Map<String, String>)xmlDecoder.toDeserialize("direccion", message, map );
		if(direccion != null){
			responseTO.setDireccion( direccion.get("direccion"));
			responseTO.setEstado( direccion.get("estado"));
			responseTO.setCiudad( direccion.get("ciudad"));
			responseTO.setColonia( direccion.get("colonia"));
			responseTO.setCodigoPostal( direccion.get("cp"));
			responseTO.setTelefono( direccion.get("telefono"));
		}		
	}

	private void llenarDatosSolicitudDispositivo(SolicitudDispositivoResponseTO responseTO,HashMap<String, String> map, 
			XmlDecoder xmlDecoder,MessageElement messageElement) throws IOException, XmlDecodeException {
		responseTO.setComision( "0.00" );
		
		LugarEntregaTO lugarEntrega= ( LugarEntregaTO ) xmlDecoder.toDeserialize("lugarEntregaForm", messageElement, map );
		PedidoTO pedidoTO = (PedidoTO) xmlDecoder.toDeserialize("pedidoDis", messageElement, map);
		String validarNIP = ( String ) xmlDecoder.toDeserialize("validarNIP", messageElement, null );
		if(pedidoTO != null){
			responseTO.setDireccion( pedidoTO.getDireccion() );
			responseTO.setEstado( pedidoTO.getEstado() );
			responseTO.setCiudad( pedidoTO.getCiudad() );
			responseTO.setColonia( pedidoTO.getColonia() );
			responseTO.setCodigoPostal( pedidoTO.getCp() );
			responseTO.setTelefono( pedidoTO.getTelefono() );
			responseTO.setPedidoTO( pedidoTO );
		}
		if(lugarEntrega != null){
			responseTO.setNombre1(lugarEntrega.getNombre1());
			responseTO.setApellidoPaterno1(lugarEntrega.getApPat1());
			responseTO.setApellidoMaterno1(lugarEntrega.getApMat1());
			
			responseTO.setNombre2(lugarEntrega.getNombre2());
			responseTO.setApellidoPaterno2(lugarEntrega.getApPat2());
			responseTO.setApellidoMaterno2(lugarEntrega.getApMat2());
		}
		
		if (validarNIP != null && validarNIP.equalsIgnoreCase("SI")) {
			responseTO.setValidarNIP(validarNIP);
		} else {
			responseTO.setValidarNIP("NO");
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		}
	}
	
	public SolicitudDispositivoResponseTO setConfirmSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		SolicitudDispositivoResponseTO responseTO = new SolicitudDispositivoResponseTO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		MessageElement element;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("lugarEntregaForm", "com.bancoazteca.elite.beans.LugarEntregaTO");
		map.put("pedidoDis", "com.bancoazteca.elite.beans.PedidoTO");
		map.put( "estadosM", "java.util.Collection" );
		map.put( "municipios", "java.util.Collection" );
		XmlDecoder xmlDecoder = new XmlDecoder();
		try {

			element = dispositivoDAO.setConfirmSolicitudDispositivo( solicitudDispositivoRequestTO );
			DispositivoRule rule = new DispositivoRule( element );
			rule.validaConfirmacionSolicitudDispositivo();
			llenarDatosSolicitudDispositivo( responseTO, map, xmlDecoder, element );
		} catch(DAOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DispositivoException(e);
		}
		return responseTO;
	}

	public String generaListaMunicipios( String xml, int numeroNodos, Collection<String> lista ) throws DAOException {
		Builder builder = new Builder();
	    try {
	      Document doc = builder.build(xml, null);
	      nu.xom.Element root = doc.getRootElement();
	      Nodes nodoCuentas = doc.query("//municipios/item[position()<=" +numeroNodos+ "]");
	      nu.xom.Element nodo;
	      if( nodoCuentas.size() > 0 ){
		      for( int i = 0; i < nodoCuentas.size(); i++ ){
		    	  nodo = ( nu.xom.Element ) nodoCuentas.get( i );
		    	  lista.add( nodo.getAttributeValue( "itemValue" ) );
		    	  nodo.detach();
		      }
		      xml = root.toXML();
	      }
	      else
	    	  xml = null;
	    }catch (XPathException e) {
			throw new DAOException(e);
	    }
	    catch (ParsingException e) { 
			throw new DAOException(e);
	    }  
	    catch (IOException e) { 
			throw new DAOException(e);
	    }
  	  return xml;
	}
}