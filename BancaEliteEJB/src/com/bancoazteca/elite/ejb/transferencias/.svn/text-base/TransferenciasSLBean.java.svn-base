package com.bancoazteca.elite.ejb.transferencias;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.axis.message.MessageElement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;


import com.bancoazteca.ebanking.mail.EnviarMail;
import com.bancoazteca.ebanking.mail.MailSenderException;
import com.bancoazteca.elite.beans.BancoSpeiTO;
import com.bancoazteca.elite.beans.BancoTefTO;
import com.bancoazteca.elite.beans.ConfirmarTransferenciaInternacionalTO;
import com.bancoazteca.elite.beans.ConfirmarTransferenciaSpeiTO;
import com.bancoazteca.elite.beans.ConfirmarTransferenciaTefTO;
import com.bancoazteca.elite.beans.ConfirmarTransferenciaTercerosTO;
import com.bancoazteca.elite.beans.ConsultaTransferenciasRequestTO;
import com.bancoazteca.elite.beans.ConsultaTransferenciasResponseTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.CuentaVisibleSpeiTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesTO;
import com.bancoazteca.elite.beans.CuentasTransferenciasTO;
import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.EnvioMailRequestTO;
import com.bancoazteca.elite.beans.EnvioMailResponseTO;
import com.bancoazteca.elite.beans.InternacionalesBancoTO;
import com.bancoazteca.elite.beans.InternacionalesBancosRequestTO;
import com.bancoazteca.elite.beans.InternacionalesBancosResponseTO;
import com.bancoazteca.elite.beans.InternacionalesCiudadTO;
import com.bancoazteca.elite.beans.InternacionalesPaisTO;
import com.bancoazteca.elite.beans.LabelValueBeanTO;
import com.bancoazteca.elite.beans.SpeiProgramadoRequestTO;
import com.bancoazteca.elite.beans.SpeiProgramadoTO;
import com.bancoazteca.elite.beans.TransferenciaTercerosRequestTO;
import com.bancoazteca.elite.beans.TransferenciaTercerosResponseTO;
import com.bancoazteca.elite.beans.TransferenciasInternacionalesRequestTO;
import com.bancoazteca.elite.beans.TransferenciasInternacionalesResponseTO;
import com.bancoazteca.elite.beans.TransferenciasOtrosBancosTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiRequestTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiResponseTO;
import com.bancoazteca.elite.beans.TransferenciasTEFRequestTO;
import com.bancoazteca.elite.beans.TransferenciasTEFResponseTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.db.JDNIConnection;
import com.bancoazteca.elite.ejb.cuentas.CuentasDAO;
import com.bancoazteca.elite.ejb.cuentas.CuentasException;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.DigitalFingerUtil;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.GeneradorRFC;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;

/**
 * @author Diana Camacho.
 */
public class TransferenciasSLBean implements SessionBean {

	private static final Logger log = Logger.getLogger(TransferenciasSLBean.class);
	private static final long serialVersionUID = -3423500818523544157L;

	private static final PropertiesService properties=PropertiesService.getInstance();
	private static final String TIPO_CAMBIO_PROPERTIES="TipoCambio.properties";
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private SessionContext context;

	/**
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext aContext) {
		this.context = aContext;
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

	@SuppressWarnings("unchecked")
	public TransferenciaTercerosResponseTO getTransferenciaTercerosInvocacion(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciaTercerosResponseTO tercerosResponseTO = new TransferenciaTercerosResponseTO();
		String query = "for $cuenta in //elite/ebanking_web_DataKey/cuentas return $cuenta";
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.findTransferenciaTercerosInvocacion(transferenciaTercerosRequestTO);
			TransferenciasRule rule = new TransferenciasRule(messageElement);
			rule.validateTransferenciaTercerosInvocacion();
			XMLDecode decode = new XMLDecode();

			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);

			log.info("cuentas " + messageElement.toString());
			Collection<CuentaTransaccionesTO> cuentas = decode.buildCollectionBeans(CuentaTransaccionesTO.class, messageElement, "CuentaLO");
			formatearCuentas(cuentas);
			tercerosResponseTO.setCuentas(cuentas);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new TransferenciasException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new TransferenciasException(e);
		}
		return tercerosResponseTO;
	}

	@SuppressWarnings("unchecked")
	public TransferenciaTercerosResponseTO getTransferenciaTercerosEnvioDatos(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciaTercerosResponseTO tercerosResponseTO = new TransferenciaTercerosResponseTO();
		XMLDecode decode = new XMLDecode();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.findTransferenciaTercerosEnvioDatos(transferenciaTercerosRequestTO);

			TransferenciasRule rule = new TransferenciasRule(messageElement);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			rule.validateTransferenciaTercerosEnvioDatos();

			String mensajeRetencion = decode.getString(messageElement, "mensajeRetencion");
			ConfirmarTransferenciaTercerosTO confirmarTercerosTO = (ConfirmarTransferenciaTercerosTO) decode.buildBean(ConfirmarTransferenciaTercerosTO.class, messageElement, "ebanking_web_DataKey");

			Collection<CuentaTransaccionesTO> cuentas = decode.buildCollectionBeans(CuentaTransaccionesTO.class, messageElement, "CuentaLO");
			formatearCuentas(cuentas);

			tercerosResponseTO.setCuentas(cuentas);
			tercerosResponseTO.setMensajeRetencion(mensajeRetencion);
			tercerosResponseTO.setConfirmarTercerosTO(confirmarTercerosTO);

			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);

			tercerosResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (IOException e) {
			throw new TransferenciasException(e);
		}
		return tercerosResponseTO;
	}

	public TransferenciaTercerosResponseTO getEjecutarTransferenciaTerceros(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciaTercerosResponseTO transferenciaMismoBancoResponseTO = new TransferenciaTercerosResponseTO();

		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.findEjecutarTransferenciaTerceros(transferenciaTercerosRequestTO);
			TransferenciasRule rule = new TransferenciasRule(messageElement);

			rule.validateEjecutarTransferenciaTerceros();
			// rule.validateEjecutarTransferenciaTercerosGeneral();
			
			XmlDecoder decoder = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("TercerosForm", "com.bancoazteca.elite.beans.ConfirmarTransferenciaTercerosTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaTransaccionesTO");
			ConfirmarTransferenciaTercerosTO confirmarTransferenciaTercerosTO = (ConfirmarTransferenciaTercerosTO) decoder.toDeserialize("TercerosForm", messageElement, mappedBeans);
			if( confirmarTransferenciaTercerosTO != null ){
				Collection<CuentaTransaccionesTO> cuentas = confirmarTransferenciaTercerosTO.getCuentas();
				formatearCuentas(cuentas);
				transferenciaMismoBancoResponseTO.setConfirmarTercerosTO(confirmarTransferenciaTercerosTO);	
				transferenciaMismoBancoResponseTO.setCuentas(cuentas);
			}

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}

		return transferenciaMismoBancoResponseTO;
	}

	public EnvioMailResponseTO envioMailTerceros(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		EnvioMailResponseTO envioMailResponseTO = new EnvioMailResponseTO();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.envioMailTerceros(envioMailRequestTO);
			XMLDecode decode = new XMLDecode();

			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/traspasos/tercerosEjecutar.jsp")) {
				envioMailResponseTO.setEstatus_envio( "El correo electrónico fue enviado correctamente." );
			} else {
				envioMailResponseTO.setEstatus_envio( "Lo sentimos, no fue posible enviar el correo electrónico." );
			}
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
		return envioMailResponseTO;
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public TransferenciasSpeiResponseTO getTransferenciaSpeiInvocacion(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException{
		String query = "for $cuenta in //elite/cuentasVisibles return <cuentasCombo>{$cuenta}</cuentasCombo>";
		TransferenciasSpeiResponseTO transferenciasSpeiResponseTO = new TransferenciasSpeiResponseTO();
		TransferenciasRule rule = null;
		XMLDecode decode = new XMLDecode();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement message = transferenciasDAO.findTransferenciaSpeiInvocacion(transferenciasSpeiRequestTO);

			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, message, "DatosEliteVO");
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/speiProgramado.jsp")) {
				transferenciasSpeiResponseTO.setMessage("true");
			}

			rule = new TransferenciasRule(message);
			rule.validateTransferenciaSpeiInvocacion();
			String fecha = decode.getString(message, "tomorrow");
			String fechaProgramacion = "";
			Date date = null;
			try {
				date = new Date(fecha);
			} catch (Exception e) {
				fecha = fecha.replace("-", "/");
				date = new Date(fecha);
			}
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			fechaProgramacion = formato.format(date);
			String fechaProgramacionFormateada = Formatter.formatDate(fechaProgramacion);
			
			String curp = decode.getString(message, "idkey");
			String nombre = "";
			String aPaterno = "";
			String aMaterno = "";
			String fechaNacimiento = ""; 
			try {
				nombre = decode.getString(message, "nombre");
				aPaterno = decode.getString(message, "paterno");
				aMaterno = decode.getString(message, "materno");
				fechaNacimiento = decode.getString(message, "nacimiento");
			} catch (Exception e) {
				log.info( "No se obtuvieron todos los datos necesarios para la generación del RFC" );
			}
								
			if(Validator.checkNumeric(curp)){
				curp = GeneradorRFC.obtenRFC(nombre, aPaterno, aMaterno, fechaNacimiento);
			}
			
			MessageElement messageElement = transferenciasDAO.findTransferenciaSpeiInicio(transferenciasSpeiRequestTO);
			rule = new TransferenciasRule(messageElement);
			rule.validateTransferenciaSpeiInicio();

			Collection<BancoSpeiTO> bancos = decode.buildCollectionContainerBeans(BancoSpeiTO.class, messageElement, "bancos");
			Collection<BancoSpeiTO> codigoBancos = new ArrayList<BancoSpeiTO>();
			
			for (BancoSpeiTO speiTO : bancos) {
				if(speiTO.getKey() != null)
				{
					BancoSpeiTO bancoSpeiTO = new BancoSpeiTO();
					bancoSpeiTO.setDescription(speiTO.getDescription());
					bancoSpeiTO.setIndex(speiTO.getIndex());
					int tamaño = speiTO.getKey().length();
					String codigo = speiTO.getKey().substring(tamaño - 4, tamaño);
					bancoSpeiTO.setKey(codigo);
					codigoBancos.add(bancoSpeiTO);
				}
			}
			
			String tipoReal = decode.getString(messageElement, "tipoReal");
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<CuentaVisibleSpeiTO> cuentasSpei = decode.buildCollectionBeans(CuentaVisibleSpeiTO.class, messageElement, "LabelValueBean");
			Collection<CuentaTransaccionesTO> cuentas = new ArrayList<CuentaTransaccionesTO>();
			String labelCuenta = "";
			for (CuentaVisibleSpeiTO visibleSpeiTO : cuentasSpei) {
				labelCuenta = visibleSpeiTO.getLabel();
				CuentaTransaccionesTO cuentaTransaccionesTO = new CuentaTransaccionesTO();
				cuentaTransaccionesTO.setCuenta(labelCuenta);
				cuentaTransaccionesTO.setIndex(visibleSpeiTO.getValue());
				String[] numeroCuenta = labelCuenta.split(" ");
				cuentaTransaccionesTO.setNumero(numeroCuenta[0]);
				cuentas.add(cuentaTransaccionesTO);
			}
			formatearCuentas(cuentas);

			
			transferenciasSpeiResponseTO.setFechaProgramacion(fechaProgramacion);
			transferenciasSpeiResponseTO.setFechaProgramacionFormateada(fechaProgramacionFormateada);
			// transferenciasSpeiResponseTO.setBancos(bancos);
			transferenciasSpeiResponseTO.setBancos(codigoBancos);
			transferenciasSpeiResponseTO.setCuentasSpei(cuentasSpei);
			transferenciasSpeiResponseTO.setCuentas(cuentas);
			transferenciasSpeiResponseTO.setCurp(curp);
			transferenciasSpeiResponseTO.setTipoReal(tipoReal);

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}catch(IOException e){
			throw new TransferenciasException(e);
		}
		return transferenciasSpeiResponseTO;
	}

	@SuppressWarnings("unchecked")
	public TransferenciasSpeiResponseTO getTransferenciaSpeiEnvioDatos(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSpeiResponseTO transferenciasSpeiResponseTO = new TransferenciasSpeiResponseTO();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.findTransferenciaSpeiEnvioDatos(transferenciasSpeiRequestTO);
			TransferenciasRule rule = new TransferenciasRule(messageElement);
			rule.validateTransferenciaSpeiEnvioDatos();
			XMLDecode decode = new XMLDecode();
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();

			Collection<BancoSpeiTO> bancos = decode.buildCollectionBeans(BancoSpeiTO.class, messageElement, "BancosSpeiBean");
			ConfirmarTransferenciaSpeiTO confirmarTransferenciaSpeiTO = (ConfirmarTransferenciaSpeiTO) decode.buildBean(ConfirmarTransferenciaSpeiTO.class, messageElement, "spei_form");
			
			if(confirmarTransferenciaSpeiTO.getFechaProgramada() != null || confirmarTransferenciaSpeiTO.getFechaProgramada().equalsIgnoreCase("true")){
				confirmarTransferenciaSpeiTO.setFechaOperation(confirmarTransferenciaSpeiTO.getDatapli());
				confirmarTransferenciaSpeiTO.setDatapli(confirmarTransferenciaSpeiTO.getFechaProgramacion());
			}


			Collection<CuentaVisibleSpeiTO> cuentas = decode.buildCollectionBeans(CuentaVisibleSpeiTO.class, messageElement, "LabelValueBean");
			transferenciasSpeiResponseTO.setCuentasSpei(cuentas);
			transferenciasSpeiResponseTO.setBancos(bancos);
			transferenciasSpeiResponseTO.setConfirmarTransferenciaSpeiTO(confirmarTransferenciaSpeiTO);

			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);

			transferenciasSpeiResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (IOException ioe) {
			throw new TransferenciasException(ioe);
		}
		return transferenciasSpeiResponseTO;
	}

	@SuppressWarnings("unchecked")
	public TransferenciasSpeiResponseTO getEjecutarTransferenciaSpei(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSpeiResponseTO transferenciasSpeiResponseTO = new TransferenciasSpeiResponseTO();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.findEjecutarTransferenciaSpei(transferenciasSpeiRequestTO);
			TransferenciasRule rule = new TransferenciasRule(messageElement);
			rule.validateEjecutarTransferenciaSpei();

			XMLDecode decode = new XMLDecode();
			ConfirmarTransferenciaSpeiTO confirmarTransferenciaSpeiTO = (ConfirmarTransferenciaSpeiTO) decode.buildBean(ConfirmarTransferenciaSpeiTO.class, messageElement, "spei_form");
			transferenciasSpeiResponseTO.setConfirmarTransferenciaSpeiTO(confirmarTransferenciaSpeiTO);
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}
		return transferenciasSpeiResponseTO;
	}

	public EnvioMailResponseTO envioMailSpei(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		EnvioMailResponseTO envioMailResponseTO = new EnvioMailResponseTO();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.envioMailSpei(envioMailRequestTO);
			XMLDecode decode = new XMLDecode();

			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/speiEjecutar.jsp")) {
				envioMailResponseTO.setEstatus_envio( "El correo electrónico fue enviado correctamente." );
			} else {
				envioMailResponseTO.setEstatus_envio( "Lo sentimos, no fue posible enviar el correo electrónico." );
			}

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		return envioMailResponseTO;
	}

	public EnvioMailResponseTO envioMailSpei30(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		EnvioMailResponseTO envioMailResponseTO = new EnvioMailResponseTO();
		
		SpeiProgramadoRequestTO speiRequest30TO = envioMailRequestTO.getSpeiRequest30TO();
		
		ConfirmarTransferenciaSpeiTO ejecucionTO = speiRequest30TO.getEjecucionTO();
		TransferenciasSpeiRequestTO requestTO = speiRequest30TO.getRequestTO();
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		Date fecha;
		try {
			fecha = dateFormat.parse(speiRequest30TO.getFechaAplicacionAlnova());
		} catch (ParseException e) {
			fecha=Calendar.getInstance().getTime();
		}
		
		String nombreBeneficiario=ejecucionTO.getBeneficiario();
		String nombreTitular=ejecucionTO.getNomord();
		
		String cuentaDestino;
		String cuentaTitular=ejecucionTO.getNumCta();
		String montoTraspaso=requestTO.getImporte();
		String concepto=requestTO.getCobranza();
		String mensaje=envioMailRequestTO.getMensaje();
		String destinatario=envioMailRequestTO.getCorreo_electronico_destino();
		
		
		List<String> to=new ArrayList<String>();
		to.add(destinatario);
		
		if(requestTO.getTipo().equalsIgnoreCase("1")){
			cuentaDestino="0"+ejecucionTO.getBankCode().substring(3)+ejecucionTO.getDestino();
		}else{
			cuentaDestino=ejecucionTO.getDestino();
		}
		
		
		Hashtable<String, String> parametros=new Hashtable<String, String>();
		parametros.put( "#FECHA_PROGRAMADA#", com.bancoazteca.ebanking.util.Formatter.formatoFecha( fecha, "d MMM yyyy" ) );
		parametros.put( "#FECHA_APLICACION#", com.bancoazteca.ebanking.util.Formatter.formatoFecha( fecha, "d MMM yyyy" ) );
		parametros.put( "#TITULAR_CTA_ABONO#",  nombreBeneficiario);
		parametros.put( "#TITULAR_CTA_CARGO#",  nombreTitular);
		parametros.put( "#CTA_ABONO#", com.bancoazteca.ebanking.util.Formatter.formatoXXCuenta( cuentaDestino ) );
		parametros.put( "#CTA_CARGO#", com.bancoazteca.ebanking.util.Formatter.formatoXXCuenta( cuentaTitular ) );
		parametros.put( "#IMPORTE#", montoTraspaso );
		parametros.put( "#REFERENCIA#", concepto );
		parametros.put( "#MENSAJE#", mensaje );
		
		try {
			EnviarMail.enviaMail(parametros, PropertiesService.getInstance(), to, null, null, "respuesta-exitosa");
			envioMailResponseTO.setEstatus_envio( "El correo electrónico fue enviado correctamente." );
		} catch (MailSenderException e) {
			envioMailResponseTO.setEstatus_envio( "Lo sentimos, no fue posible enviar el correo electrónico." );
		}
		return envioMailResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public TransferenciasTEFResponseTO getTransferenciaTefInvocacion(TransferenciasTEFRequestTO transferenciasTefRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		
		TransferenciasTEFResponseTO transferenciasTefResponseTO = new TransferenciasTEFResponseTO();
		String query = "for $cuenta in //elite/cuentasVisibles return <cuentasCombo>{$cuenta}</cuentasCombo>";
		TransferenciasRule rule = null;
		XMLDecode decode = new XMLDecode();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			
			//traemos la fecha para el caso de tef
			MessageElement messageFechaTef = transferenciasDAO.findTransferenciaTefInvocacion(transferenciasTefRequestTO);
			rule = new TransferenciasRule(messageFechaTef);
			rule.validateTransferenciaTEFInvocacion();
			
			String fecha = decode.getString(messageFechaTef, "tomorrow");
			StringBuilder fechaTef = new StringBuilder();
			fechaTef.append(fecha.substring(8, 10));
			fechaTef.append("-");
			fechaTef.append(fecha.substring(5, 7));
			fechaTef.append("-");
			fechaTef.append(fecha.substring(0, 4));
			transferenciasTefResponseTO.setFechaprogramacion(fechaTef.toString());
			
			
			//traemos las cuentas visibles
			MessageElement messageSpei = transferenciasDAO.findTransferenciaTefInicio(transferenciasTefRequestTO);
			rule = new TransferenciasRule(messageSpei);
			rule.validateTransferenciaTefInicio();
			
			Element element = XMLFinder.getElement(messageSpei.toString(), query);
			MessageElement messageCuentas = new MessageElement(element);
			Collection<CuentaVisibleSpeiTO> cuentasTef = decode.buildCollectionBeans(CuentaVisibleSpeiTO.class, messageCuentas, "LabelValueBean");
			Collection<CuentaTransaccionesTO> cuentas = new ArrayList<CuentaTransaccionesTO>();
			String labelCuenta = "";
			for (CuentaVisibleSpeiTO visibleSpeiTO : cuentasTef) {
				labelCuenta = visibleSpeiTO.getLabel();
				CuentaTransaccionesTO cuentaTransaccionesTO = new CuentaTransaccionesTO();
				cuentaTransaccionesTO.setCuenta(labelCuenta);
				cuentaTransaccionesTO.setIndex(visibleSpeiTO.getValue());
				String[] numeroCuenta = labelCuenta.split(" ");
				cuentaTransaccionesTO.setNumero(numeroCuenta[0]);
				cuentas.add(cuentaTransaccionesTO);
			}
			formatearCuentas(cuentas);
			
			//concluimos los paths de transferencias tef
			MessageElement messageElementTef = transferenciasDAO.findTransferenciaTefInvocacion(transferenciasTefRequestTO);
			rule = new TransferenciasRule(messageElementTef);
			rule.validateTransferenciaTEFInvocacion();
			
			//invocamos las cuentas frecuentes para la transferencias tef
			CuentasDAO cuentasDAO = new CuentasDAO();
			CuentasFrecuentesRequestTO cuentasFecuentesRequestTO = new  CuentasFrecuentesRequestTO();
			cuentasFecuentesRequestTO.setUser(transferenciasTefRequestTO.getUser());
			cuentasFecuentesRequestTO.setType(cuentasFecuentesRequestTO.TEF);
			MessageElement messageElement = cuentasDAO.findCuentasFrecuentes(cuentasFecuentesRequestTO);
			messageElement = cuentasDAO.setIntenationalesPreparacionAgregarCuenta(cuentasFecuentesRequestTO);
			rule = new TransferenciasRule(messageElement);
			rule.validateTransferenciaTefIntenationalesPreparacionAgregarCuenta();
			transferenciasTefResponseTO.setCuentasTef(cuentas);
			Collection<BancoTefTO> bancos = decode.buildCollectionContainerBeans(BancoTefTO.class, messageElement, "banks");
			transferenciasTefResponseTO.setBancosTef(bancos);
			
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		}catch(IOException e){
			throw new TransferenciasException(e);
		}
		return transferenciasTefResponseTO;

	}

	@SuppressWarnings("unchecked")
	public TransferenciasTEFResponseTO getTransferenciaTefEnvioDatos(TransferenciasTEFRequestTO transferenciasTefRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {

		
		TransferenciasTEFResponseTO transferenciasTefResponseTO = new TransferenciasTEFResponseTO();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();//
			MessageElement messageElement = transferenciasDAO.findTransferenciaTefEnvioDatos(transferenciasTefRequestTO);//
			TransferenciasRule rule = new TransferenciasRule(messageElement);//
			rule.validateTransferenciaTEFEnvioDatos();//
			XMLDecode decode = new XMLDecode();//
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();//

			//Collection<BancoSpeiTO> bancos = decode.buildCollectionBeans(BancoSpeiTO.class, messageElement, "BancosSpeiBean");
			ConfirmarTransferenciaTefTO confirmarTransferenciaTefTO = (ConfirmarTransferenciaTefTO) decode.buildBean(ConfirmarTransferenciaTefTO.class, messageElement, "transferenciasTefsForm");//"spei_form");
			//Collection<CuentaTransaccionesTO> cuentas = decode.buildCollectionBeans(CuentaVisibleSpeiTO.class, messageElement, "LabelValueBean");
			//Collection<CuentaTransaccionesTO> cuentas = decode.buildCollectionBeans(CuentaTransaccionesTO.class, messageElement, "LabelValueBean");
			
			//transferenciasTefResponseTO.setCuentasTef(cuentas);
			//transferenciasTefResponseTO.setBancosTef(bancos);
			transferenciasTefResponseTO.setConfirmarTransferenciaTefTO(confirmarTransferenciaTefTO);

			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);

			transferenciasTefResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (IOException ioe) {
			throw new TransferenciasException(ioe);
		}
		return transferenciasTefResponseTO;
	}

	public TransferenciasTEFResponseTO getEjecutarTransferenciaTef(TransferenciasTEFRequestTO transferenciasTEFRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasTEFResponseTO transferenciasTefResponseTO = new TransferenciasTEFResponseTO();
		String query = "for $numero in //elite/ebanking_web_DataKey/array/MovimientoVO return $numero";
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			//transferenciasDAO.findTefTest(transferenciasTEFRequestTO);
			MessageElement messageElement = transferenciasDAO.findEjecutarTransferenciaTef(transferenciasTEFRequestTO);
			TransferenciasRule rule = new TransferenciasRule(messageElement);
			rule.validateEjecutarTransferenciaTEF();
			
			

			XMLDecode decode = new XMLDecode();
			ConfirmarTransferenciaTefTO confirmarTransferenciaTefTO = (ConfirmarTransferenciaTefTO) decode.buildBean(ConfirmarTransferenciaTefTO.class, messageElement, "TefsForm");
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);

			String claveRecuperacion = decode.getString(messageElement, "numero");
			
			confirmarTransferenciaTefTO.setClave(claveRecuperacion);
			transferenciasTefResponseTO.setConfirmarTransferenciaTefTO(confirmarTransferenciaTefTO);

			

		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}catch(IOException e){
			throw new TransferenciasException(e);
		}
		return transferenciasTefResponseTO;
	}

	public EnvioMailResponseTO envioMailTef(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		EnvioMailResponseTO envioMailResponseTO = new EnvioMailResponseTO();
		try {
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.envioMailTef(envioMailRequestTO);
			XMLDecode decode = new XMLDecode();

			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/tefsEjecutar.jsp")) {
				envioMailResponseTO.setEstatus_envio("El correo electrónico fue enviado correctamente.");
			} else {
				envioMailResponseTO.setEstatus_envio("Lo sentimos, no fue posible enviar el correo electrónico.");
			}

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		return envioMailResponseTO;
	}

	@SuppressWarnings("unchecked")
	public TransferenciasInternacionalesResponseTO getTransferenciaInternacionalInvocacion(TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasInternacionalesResponseTO transferenciasInternacionalesResponseTO = new TransferenciasInternacionalesResponseTO();
		XMLDecode decode = new XMLDecode();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();		
		String query = "for $cuenta in //elite/ebanking_web_DataKey/cuentas return $cuenta";
		String queryTipoCambio = "for $tc in //elite/tc return <tipoCambio>{$tc}</tipoCambio>";
		try {
			MessageElement messageElement = transferenciasDAO.findTransferenciaInternacionalInvocacion(transferenciasInternacionalesRequestTO);
			TransferenciasRule rule = new TransferenciasRule(messageElement);
			rule.validateTransferenciaInternacionalInvocacion();
			
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transInterFueraHorario.jsp")){
				log.info("Transferencia internacional fuera de horario ");
				transferenciasInternacionalesResponseTO.setRestriccionHorario("Lo sentimos, le recordamos que el horario para realizar tus Transferencias Internacionales es de lunes a viernes de 8:30 a.m. a 14:00 p.m. ");
			}else{
				log.info("Transferencia internacional dentro de horario ");
				//obtenemos las cuentas
				Element element = XMLFinder.getElement(messageElement.toString(), query);
				MessageElement messageElementCuentas = new MessageElement(element);
				Collection<CuentaTransaccionesTO> cuentas = decode.buildCollectionBeans(CuentaTransaccionesTO.class, messageElementCuentas, "CuentaLO");
				formatearCuentas(cuentas);
				transferenciasInternacionalesResponseTO.setCuentas(cuentas);
				
				//obtenemos el tipo de cambio
				BigDecimal tipoCambio = null;
				Element elementTipoCambio = XMLFinder.getElement(messageElement.toString(), queryTipoCambio);
				MessageElement messageElementTipoCambio = new MessageElement(elementTipoCambio);
				String valorDolar = decode.getString(messageElementTipoCambio, "tc");
				
				int cantidadEnteros = Integer.valueOf(properties.getPropertie(TIPO_CAMBIO_PROPERTIES,"elite.transferencia.internacional.tipocambio.entero"));
				int cantidadDecimal = Integer.valueOf(properties.getPropertie(TIPO_CAMBIO_PROPERTIES,"elite.transferencia.internacional.tipocambio.longdecimal"));
				
				if( (!Validator.isEmptyData(valorDolar)) && (valorDolar.length() > cantidadEnteros+cantidadDecimal) ){
					String parteEntera = valorDolar.substring(0, cantidadEnteros); 
		            String parteDecimal = valorDolar.substring(cantidadEnteros, (cantidadEnteros+cantidadDecimal));
		            tipoCambio = new BigDecimal(parteEntera + "." + parteDecimal);
		            String tipoCambioFormateado = tipoCambio.toString();
		            transferenciasInternacionalesResponseTO.setValorDolar(valorDolar);
					transferenciasInternacionalesResponseTO.setTipoCambioFormateado(tipoCambioFormateado);
				}
				
				//esta parte es para las cuentas frecuentes del servicio transferencias internacionales			
//				MessageElement messageElementFrecuentes = transferenciasDAO.findTransferenciaCuentasFrecuentesInvocacion(transferenciasInternacionalesRequestTO);
//				rule = new TransferenciasRule(messageElementFrecuentes);
//				rule.validateTransferenciaInternacionalInvocacion();
			}
			
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (IOException e) {
			throw new TransferenciasException(e);
		}
		return transferenciasInternacionalesResponseTO;
	}

	@SuppressWarnings("unchecked")
	public TransferenciasInternacionalesResponseTO getTransferenciaInternacionalEnvioDatos(TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasInternacionalesResponseTO transferenciasInternacionalesResponseTO = new TransferenciasInternacionalesResponseTO();
		String query = "for $cuenta in //elite/ebanking_web_DataKey/cuentas return $cuenta";
		String queryTipoCambio = "for $tc in //elite/tc return <tipoCambio>{$tc}</tipoCambio>";
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		XMLDecode decode = new XMLDecode();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		try {
			
			MessageElement messageElement = transferenciasDAO.findTransferenciaInternacionalEnvioDatos(transferenciasInternacionalesRequestTO);
			TransferenciasRule rule = new TransferenciasRule(messageElement);
			rule.validateTransferenciaInternacionalEnvioDatos();
			
			//obtenemos las cuentas
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			MessageElement messageElementCuentas = new MessageElement(element);
			Collection<CuentaTransaccionesTO> cuentas = decode.buildCollectionBeans(CuentaTransaccionesTO.class, messageElementCuentas, "CuentaLO");
			formatearCuentas(cuentas);
			transferenciasInternacionalesResponseTO.setCuentas(cuentas);
			
			//obtenemos datos a mostrar
			ConfirmarTransferenciaInternacionalTO confirmarTransferenciaInternacionalTO =
				(ConfirmarTransferenciaInternacionalTO) decode.buildBean(ConfirmarTransferenciaInternacionalTO.class, messageElement, "ebanking_web_DataKey");
			
			
			//obtenemos el tipo de cambio
			BigDecimal tipoCambio = null;
			Element elementTipoCambio = XMLFinder.getElement(messageElement.toString(), queryTipoCambio);
			MessageElement messageElementTipoCambio = new MessageElement(elementTipoCambio);
			String valorDolar = decode.getString(messageElementTipoCambio, "tc");
			
			int cantidadEnteros = Integer.valueOf(properties.getPropertie(TIPO_CAMBIO_PROPERTIES,"elite.transferencia.internacional.tipocambio.entero"));
			int cantidadDecimal = Integer.valueOf(properties.getPropertie(TIPO_CAMBIO_PROPERTIES,"elite.transferencia.internacional.tipocambio.longdecimal"));
			
			if( (!Validator.isEmptyData(valorDolar)) && (valorDolar.length() > cantidadEnteros+cantidadDecimal) ){
				String parteEntera = valorDolar.substring(0, cantidadEnteros); 
	            String parteDecimal = valorDolar.substring(cantidadEnteros, (cantidadEnteros+cantidadDecimal));
	            tipoCambio = new BigDecimal(parteEntera + "." + parteDecimal);
	            String tipoCambioFormateado = tipoCambio.toString();
	            confirmarTransferenciaInternacionalTO.setTipoCambio(tipoCambioFormateado);
	            log.info("tipoCambioFormateado.-"+confirmarTransferenciaInternacionalTO.getTipoCambio());
			}
						
			//obtenemos la fecha en formato dd-mm-yyyy
			String fecha = confirmarTransferenciaInternacionalTO.getOperation();
			String fechaOperacion = "";
			Date date = null;
			try {
				date = new Date(fecha);
			} catch (Exception e) {
				fecha = fecha.replace("-", "/");
				date = new Date(fecha);
			}
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			fechaOperacion = formato.format(date);
			String fechaOperacionFormateada = Formatter.formatDate(fechaOperacion);
			confirmarTransferenciaInternacionalTO.setOperation(fechaOperacionFormateada);
			
			//enviamos los datos a mostrar en pantalla
			transferenciasInternacionalesResponseTO.setConfirmarTransferenciaInternacionalTO(
					confirmarTransferenciaInternacionalTO);
			
			//obtenemos la huella
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			transferenciasInternacionalesResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (IOException e) {
			throw new TransferenciasException(e);
		}
		return transferenciasInternacionalesResponseTO;
	}
	//
	public TransferenciasInternacionalesResponseTO getEjecutarTransferenciaInternacional (TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO)throws EJBException, TransferenciasException, SessionExpiredException{
		TransferenciasInternacionalesResponseTO transferenciasInternacionalesResponseTO =  new TransferenciasInternacionalesResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		try{
			MessageElement messageElement = transferenciasDAO.findEjecutarTransferenciaInternacional(transferenciasInternacionalesRequestTO);
			TransferenciasRule rule =  new TransferenciasRule(messageElement);
			rule.validateEjecutarTransferenciaInternacional();
			
			XMLDecode decode = new XMLDecode();
			ConfirmarTransferenciaInternacionalTO confirmarTransferenciaInternacionalTO = (ConfirmarTransferenciaInternacionalTO) decode.buildBean(ConfirmarTransferenciaInternacionalTO.class, messageElement, "TransfInternalForm");
			transferenciasInternacionalesResponseTO.setConfirmarTransferenciaInternacionalTO(confirmarTransferenciaInternacionalTO);
			
		}catch (DAOException e) {
			throw new TransferenciasException(e);
		}catch (EliteDataException e) {
			throw new TransferenciasException(e);
		}catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} 
		return transferenciasInternacionalesResponseTO;
	}
	
	public EnvioMailResponseTO envioMailInternacional (EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException{
		EnvioMailResponseTO envioMailResponseTO = new EnvioMailResponseTO();
		try{
			TransferenciasDAO transferenciasDAO =  new TransferenciasDAO();
			MessageElement messageElement = transferenciasDAO.envioMailInternacional(envioMailRequestTO);
			XMLDecode decode = new XMLDecode();
			
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/transfInternalEjecutar.jsp")){
				envioMailResponseTO.setEstatus_envio("El correo electrónico fue enviado correctamente.");
			}else{
				envioMailResponseTO.setEstatus_envio( "Lo sentinos, no fue posible enviar el correo electrónico." );
			}
		}catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		return envioMailResponseTO;
	}
	///

	public Collection<TransferenciasOtrosBancosTO> getListadoBancosTefSpeiResponseTO(TransferenciasTEFResponseTO transferenciasTEFResponseTO, TransferenciasSpeiResponseTO transferenciasSpeiResponseTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {

		Collection<BancoSpeiTO> bancosSpei = transferenciasSpeiResponseTO.getBancos();
		Collection<BancoTefTO> bancosTef = transferenciasTEFResponseTO.getBancosTef();
		Collection<TransferenciasOtrosBancosTO> bancosList = new ArrayList<TransferenciasOtrosBancosTO>();

		Set<String> keys = new HashSet<String>();

		for (BancoSpeiTO spei : bancosSpei) {
			keys.add(spei.getKey());
		}

		for (BancoTefTO tef : bancosTef) {
			keys.add(tef.getCodigo());
		}

		BancoTefPredicate tefPredicate = new BancoTefPredicate();
		BancoSpeiPredicate SpeiPredicate = new BancoSpeiPredicate();

		for (String key : keys) {
			tefPredicate.setCodigoBanco(key);
			SpeiPredicate.setCodigoBanco(key);
			BancoTefTO bancoTefTO = (BancoTefTO) CollectionUtils.find(bancosTef, tefPredicate);
			BancoSpeiTO bancoSpeiTO = (BancoSpeiTO) CollectionUtils.find(bancosSpei, SpeiPredicate);

			TransferenciasOtrosBancosTO transferenciasOtrosBancosTO = new TransferenciasOtrosBancosTO();
			transferenciasOtrosBancosTO.setCodigoBanco(key);
			if (bancoSpeiTO != null) {
				transferenciasOtrosBancosTO.setSpei(true);
				transferenciasOtrosBancosTO.setIndexSpei(bancoSpeiTO.getIndex());
			} else {
				transferenciasOtrosBancosTO.setIndexSpei("-1");
			}

			if (bancoTefTO != null) {
				transferenciasOtrosBancosTO.setTef(true);
				transferenciasOtrosBancosTO.setIndexTef(bancoTefTO.getIndex());
			} else {
				transferenciasOtrosBancosTO.setIndexTef("-1");
			}

			if (bancoTefTO != null && bancoSpeiTO != null) {
				transferenciasOtrosBancosTO.setAmbos(true);
			}
			bancosList.add(transferenciasOtrosBancosTO);
		}

		return bancosList;
	}

	public Collection<CuentasTransferenciasTO> getListadoCuentasSuggest(Collection<LabelValueBeanTO> cuentasTodas, TransferenciaTercerosResponseTO tercerosResponseTO, TransferenciasTEFResponseTO tefResponseTO, TransferenciasSpeiResponseTO speiResponseTO, TransferenciasInternacionalesResponseTO internacionalesResponseTO) throws EJBException, TransferenciasException, SessionExpiredException,
			EliteDataException {
		Collection<CuentasTransferenciasTO> collectionCuentasSuggest = new ArrayList<CuentasTransferenciasTO>();
		Collection<CuentaTransaccionesTO> cuentasTerceros = tercerosResponseTO.getCuentas();
		Collection<CuentaTransaccionesTO> cuentasSpei = speiResponseTO.getCuentas();
		Collection<CuentaTransaccionesTO> cuentasTef = tefResponseTO.getCuentasTef();
		Collection<CuentaTransaccionesTO> cuentasInternacional = internacionalesResponseTO.getCuentas();

		CuentasTransferenciasPredicate cuentasPredicate = new CuentasTransferenciasPredicate();
		int count = 0;
		for (LabelValueBeanTO cuentasBean : cuentasTodas) {
			String numeroCuenta = cuentasBean.getCuentaFormateada();
			cuentasPredicate.setNumeroCuenta(numeroCuenta);
			CuentaTransaccionesTO ctaTerceros = (CuentaTransaccionesTO) CollectionUtils.find(cuentasTerceros, cuentasPredicate);
			CuentaTransaccionesTO ctaSpei = (CuentaTransaccionesTO) CollectionUtils.find(cuentasSpei, cuentasPredicate);
			CuentaTransaccionesTO ctaTef = (CuentaTransaccionesTO) CollectionUtils.find(cuentasTef, cuentasPredicate);
			CuentaTransaccionesTO ctaInternacional = (CuentaTransaccionesTO) CollectionUtils.find(cuentasInternacional, cuentasPredicate);

			CuentasTransferenciasTO cuentaSuggest = new CuentasTransferenciasTO();
			cuentaSuggest.setIndex(String.valueOf(count));
			cuentaSuggest.setNumeroCuenta(numeroCuenta);
			cuentaSuggest.setCuentaCombos(cuentasBean.getLabel());
			cuentaSuggest.setCuentaFormateada(cuentasBean.getCuentaFormateada());
			cuentaSuggest.setDescripcion(cuentasBean.getDescripcion());
			// cuentaSuggest.setIndexPropias(cuentasBean.getNumeroCuenta());
			cuentaSuggest.setIndexPropias(String.valueOf(count));
			if (ctaTerceros != null) {
				cuentaSuggest.setCtaTerceros(true);
				cuentaSuggest.setIndexTerceros(ctaTerceros.getIndex());
			} else {
				cuentaSuggest.setCtaTerceros(false);
				cuentaSuggest.setIndexTerceros("-2");
			}

			if (ctaSpei != null) {
				cuentaSuggest.setCtaSpei(true);
				cuentaSuggest.setIndexSpei(ctaSpei.getIndex());
			} else {
				cuentaSuggest.setCtaSpei(false);
				cuentaSuggest.setIndexSpei("-2");
			}

			if (ctaTef != null) {
				cuentaSuggest.setCtaTef(true);
				cuentaSuggest.setIndexTef(ctaTef.getIndex());
			} else {
				cuentaSuggest.setCtaTef(false);
				cuentaSuggest.setIndexTef("-2");
			}

			if (ctaInternacional != null) {
				cuentaSuggest.setCtaInternacional(true);
				cuentaSuggest.setIndexInternacional(ctaInternacional.getIndex());
			} else {
				cuentaSuggest.setCtaInternacional(false);
				cuentaSuggest.setIndexInternacional("-2");
			}

			collectionCuentasSuggest.add(cuentaSuggest);
			count++;
		}

		return collectionCuentasSuggest;

	}

	@SuppressWarnings("unchecked")
	public InternacionalesBancosResponseTO getInternacionalesPaises(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		InternacionalesBancosResponseTO internacionalesBancosResponseTO = new InternacionalesBancosResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XMLDecode decode = new XMLDecode();
		try {
			MessageElement messageElement = transferenciasDAO.findInternacionalesPaises(internacionalesBancosRequestTO);
			Collection<InternacionalesPaisTO> paises = (Collection<InternacionalesPaisTO>) decode.buildCollectionBeans(InternacionalesPaisTO.class, messageElement, "PaisesBean");
			internacionalesBancosResponseTO.setPaises(paises);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return internacionalesBancosResponseTO;
	}

	@SuppressWarnings("unchecked")
	public InternacionalesBancosResponseTO getInternacionalesCiudades(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		InternacionalesBancosResponseTO internacionalesBancosResponseTO = new InternacionalesBancosResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XMLDecode decode = new XMLDecode();
		try {
			MessageElement messageElement = transferenciasDAO.findInternacionalesCiudades(internacionalesBancosRequestTO);
			Collection<InternacionalesPaisTO> paises = (Collection<InternacionalesPaisTO>) decode.buildCollectionBeans(InternacionalesPaisTO.class, messageElement, "PaisesBean");
			Collection<InternacionalesCiudadTO> ciudades = (Collection<InternacionalesCiudadTO>) decode.buildCollectionBeans(InternacionalesCiudadTO.class, messageElement, "CiudadesBean");
			internacionalesBancosResponseTO.setPaises(paises);
			internacionalesBancosResponseTO.setCiudades(ciudades);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return internacionalesBancosResponseTO;
	}

	@SuppressWarnings("unchecked")
	public InternacionalesBancosResponseTO getInternacionalesBancos(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		InternacionalesBancosResponseTO internacionalesBancosResponseTO = new InternacionalesBancosResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XMLDecode decode = new XMLDecode();
		try {
			MessageElement messageElement = transferenciasDAO.findInternacionalesBancos(internacionalesBancosRequestTO);
			Collection<InternacionalesPaisTO> paises = (Collection<InternacionalesPaisTO>) decode.buildCollectionBeans(InternacionalesPaisTO.class, messageElement, "PaisesBean");
			Collection<InternacionalesCiudadTO> ciudades = (Collection<InternacionalesCiudadTO>) decode.buildCollectionBeans(InternacionalesCiudadTO.class, messageElement, "CiudadesBean");
			Collection<InternacionalesBancoTO> bancos = (Collection<InternacionalesBancoTO>) decode.buildCollectionBeans(InternacionalesBancoTO.class, messageElement, "ClavesBancosBean");
			internacionalesBancosResponseTO.setPaises(paises);
			internacionalesBancosResponseTO.setCiudades(ciudades);
			internacionalesBancosResponseTO.setBancos(bancos);
		} catch (XmlDecodeException e) {
			throw new TransferenciasException(e);
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return internacionalesBancosResponseTO;
	}

	public ConsultaTransferenciasResponseTO findInitialHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		ConsultaTransferenciasResponseTO consultaTransferenciasResponseTO = new ConsultaTransferenciasResponseTO();
		String query = "for $cuentas in //elite/colCuentas return <tagRoot>{$cuentas}</tagRoot>";
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			transferenciasDAO.findInitialHistoricoTransferencias(consultaTransferenciasRequestTO);
			transferenciasDAO.invokeJspTransferencias(consultaTransferenciasRequestTO);
			transferenciasDAO.findInitialServicioHistoricoTransferencias(consultaTransferenciasRequestTO);
			MessageElement messageElement = transferenciasDAO.findStartInitialHistoricoTransferencias(consultaTransferenciasRequestTO);
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("LabelValueBean", "com.bancoazteca.elite.beans.ConsultaTransferenciasResponseTO");
			ArrayList<ConsultaTransferenciasResponseTO> cuentas = (ArrayList<ConsultaTransferenciasResponseTO>)decode.toDeserialize("colCuentas", messageElement, mappedBeans);
			for (int i = 1; i < cuentas.size(); i++) {
				ConsultaTransferenciasResponseTO cuenta = (ConsultaTransferenciasResponseTO) cuentas.get(i);
				StringTokenizer str = new StringTokenizer(cuenta.getLabel());
				String cuentaFormateada = "";
				while (str.hasMoreElements()) {
					String tmp = str.nextToken();
					if (str.hasMoreElements()) {
						cuentaFormateada = cuentaFormateada + " " + tmp;
					} else {
						cuentaFormateada = cuentaFormateada + " $" + tmp;
					}
				}
				cuenta.setLabel(cuentaFormateada);
				cuentas.set(i, cuenta);
			}
			consultaTransferenciasResponseTO.setCuentas(cuentas);
		}catch (DAOException e) {
			throw new TransferenciasException(e);
		} catch (Exception e) {
			throw new TransferenciasException(e);
		}
		return consultaTransferenciasResponseTO;
	}
	
	public ConsultaTransferenciasResponseTO findInitialHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		ConsultaTransferenciasResponseTO consultaTransferenciasResponseTO = new ConsultaTransferenciasResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		try {
			transferenciasDAO.findInitialHistoricoTraspasos(consultaTransferenciasRequestTO);
			transferenciasDAO.findInitialServicioHistoricoTraspasos(consultaTransferenciasRequestTO);
			transferenciasDAO.findStartInitialHistoricoTraspasos(consultaTransferenciasRequestTO);

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return consultaTransferenciasResponseTO;
	}
	
	public ConsultaTransferenciasResponseTO findInitialTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		ConsultaTransferenciasResponseTO consultaTransferenciasResponseTO = new ConsultaTransferenciasResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		try {
			transferenciasDAO.findInitialTransferenciasProgramadas(consultaTransferenciasRequestTO);
			transferenciasDAO.invokeJspTransferenciasProgramadas(consultaTransferenciasRequestTO);
			transferenciasDAO.findInitialServicioTransferenciasProgramadas(consultaTransferenciasRequestTO);
			transferenciasDAO.findStartInitialTransferenciasProgramadas(consultaTransferenciasRequestTO);

		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return consultaTransferenciasResponseTO;
	}

	public ConsultaTransferenciasResponseTO findDataHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		ConsultaTransferenciasResponseTO consultaTransferenciasResponseTO = new ConsultaTransferenciasResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = transferenciasDAO.findDataHistoricoTransferencias(consultaTransferenciasRequestTO);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("TransferenciaVO", "com.bancoazteca.elite.beans.ConsultaTransferenciasResponseTO");
			Collection<ConsultaTransferenciasResponseTO> transferencias = (Collection<ConsultaTransferenciasResponseTO>)decode.toDeserialize("colTransferencias", messageElement, mappedBeans);
			if(transferencias==null){
				transferencias = new ArrayList<ConsultaTransferenciasResponseTO>();
			}
			consultaTransferenciasResponseTO.setTransferencias(transferencias);
		}catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return consultaTransferenciasResponseTO;
	}

	public ConsultaTransferenciasResponseTO findDataHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		ConsultaTransferenciasResponseTO consultaTransferenciasResponseTO = new ConsultaTransferenciasResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = transferenciasDAO.findDataHistoricoTraspasos(consultaTransferenciasRequestTO);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("TransaccionVO", "com.bancoazteca.elite.beans.ConsultaTransferenciasResponseTO");
			Collection<ConsultaTransferenciasResponseTO> traspasos = (Collection<ConsultaTransferenciasResponseTO>)decode.toDeserialize("col", messageElement, mappedBeans);
			if(traspasos==null){
				traspasos = new ArrayList<ConsultaTransferenciasResponseTO>();
			}
			consultaTransferenciasResponseTO.setTraspasos(traspasos);
		}catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return consultaTransferenciasResponseTO;
	}

	public ConsultaTransferenciasResponseTO findDataTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		ConsultaTransferenciasResponseTO consultaTransferenciasResponseTO = new ConsultaTransferenciasResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			transferenciasDAO.findPreviousDataTransferenciasProgramadas(consultaTransferenciasRequestTO);
			MessageElement messageElement = transferenciasDAO.findDataTransferenciasProgramadas(consultaTransferenciasRequestTO);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ConsultaSpeiProgramadosVO", "com.bancoazteca.elite.beans.ConsultaTransferenciasResponseTO");
			Collection<ConsultaTransferenciasResponseTO> transferenciasProgramadas = (Collection<ConsultaTransferenciasResponseTO>)decode.toDeserialize("speiProg", messageElement, mappedBeans);
			if(transferenciasProgramadas==null){
				transferenciasProgramadas = new ArrayList<ConsultaTransferenciasResponseTO>();
			}
			consultaTransferenciasResponseTO.setTransferenciasProgramadas(transferenciasProgramadas);
		}catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return consultaTransferenciasResponseTO;
	}

	public ConsultaTransferenciasResponseTO findDetalleHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		ConsultaTransferenciasResponseTO consultaTransferenciasResponseTO = new ConsultaTransferenciasResponseTO();
		TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			transferenciasDAO.findDataHistoricoTransferencias(consultaTransferenciasRequestTO);
			consultaTransferenciasRequestTO.setCuenta(consultaTransferenciasRequestTO.getIndex());
			MessageElement messageElement = transferenciasDAO.findDetalleHistoricoTransferencias(consultaTransferenciasRequestTO);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("detalleTransferencia", "com.bancoazteca.elite.beans.ConsultaTransferenciasResponseTO");
			consultaTransferenciasResponseTO = (ConsultaTransferenciasResponseTO)decode.toDeserialize("detalleTransferencia", messageElement, mappedBeans);
		}catch (DAOException e) {
			throw new TransferenciasException(e);
		}
		return consultaTransferenciasResponseTO;
	}

	public void formatearCuentas(Collection<CuentaTransaccionesTO> cuentas) {
		int i = 0;
		for (CuentaTransaccionesTO cuentaTO : cuentas) {
			i = i + 1;
			cuentaTO.setCuentaFormateada(Formatter.formatSplittedCuenta(cuentaTO.getNumero()));
			log.info(" cuenta formateada: " + cuentaTO.getCuentaFormateada());
		}
	}

	private class BancoTefPredicate implements Predicate {

		private String codigoBanco;

		public boolean evaluate(Object obj) {
			BancoTefTO bancoTefTO = (BancoTefTO) obj;
			if (bancoTefTO.getCodigo().equals(codigoBanco)) {
				return true;
			} else {
				return false;
			}
		}

		public String getCodigoBanco() {
			return codigoBanco;
		}

		public void setCodigoBanco(String codigoBanco) {
			this.codigoBanco = codigoBanco;
		}

	}

	private class BancoSpeiPredicate implements Predicate {

		private String codigoBanco;

		public boolean evaluate(Object obj) {
			BancoSpeiTO bancoSpeiTO = (BancoSpeiTO) obj;
			if (bancoSpeiTO.getKey().equals(codigoBanco)) {
				return true;
			} else {
				return false;
			}
		}

		public String getCodigoBanco() {
			return codigoBanco;
		}

		public void setCodigoBanco(String codigoBanco) {
			this.codigoBanco = codigoBanco;
		}

	}

	private class CuentasTransferenciasPredicate implements Predicate {

		private String numeroCuenta;

		public boolean evaluate(Object obj) {
			CuentaTransaccionesTO cuentaTransaccionesTO = (CuentaTransaccionesTO) obj;
			if (cuentaTransaccionesTO.getCuentaFormateada().equals(numeroCuenta)) {
				return true;
			} else {
				return false;
			}
		}

		public String getNumeroCuenta() {
			return numeroCuenta;
		}

		public void setNumeroCuenta(String numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
		}
	}

	public TransferenciasSpeiResponseTO insertaSpei30(SpeiProgramadoRequestTO speiRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException,TransferenciasException {
		System.out.println("BEAN: inserta Spei");
		ConfirmarTransferenciaSpeiTO ejecucionTO=speiRequestTO.getEjecucionTO();
		TransferenciasSpeiRequestTO requestTO=speiRequestTO.getRequestTO();
		TransferenciasSpeiResponseTO responseTO=new TransferenciasSpeiResponseTO();
		XmlDecoder decode = new XmlDecoder();
		
		try {
			
			String typinst=ejecucionTO.getBankCode().substring(0, 3);
			String numref="4037";
			String importe=requestTO.getImporte();
			String destcac=null;
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
			
			Date fecha=dateFormat.parse(speiRequestTO.getFechaAplicacionAlnova());
			dateFormat.applyPattern("yyyy-MM-dd");
			
			
			String fechaAplicacion=dateFormat.format(fecha);
			
			importe=AlnovaUtils.formatMontoAlnova(new BigDecimal(importe), 16);
			
			if(requestTO.getTipo().equalsIgnoreCase("1")){
				destcac="0"+ejecucionTO.getBankCode().substring(3)+ejecucionTO.getDestino();
			}else{
				destcac=ejecucionTO.getDestino();
			}
			
			AlnovaUtils alnovaUtils=new AlnovaUtils();
			alnovaUtils.addAtributo("RVRFLG", "N");//hardcode en ebank
			alnovaUtils.addAtributo("ASSNDES", ejecucionTO.getNomord());
			alnovaUtils.addAtributo("CAC20", ejecucionTO.getNumCta());//"Numero de cuenta en 20 digitos"
			alnovaUtils.addAtributo("BENDES", ejecucionTO.getBeneficiario());//"Nombre del beneficiario"
			alnovaUtils.addAtributo("DESTCAC", destcac);//"Se verifica que la cuenta sea 1 (debito) y se concatena 0 los 3 digitos del codigo del banco y el destino"
			alnovaUtils.addAtributo("DATAPLI", fechaAplicacion);//"Fecha de aplicacion"
			alnovaUtils.addAtributo("DESTXT", ejecucionTO.getReferencia());
			alnovaUtils.addAtributo("OPEAMT", importe);//"monto alnova, recordar 16 ceros sin punto decimal"
			alnovaUtils.addAtributo("FCCFLG", "E");//"no se que eso speiForm.getFccflg()"
			alnovaUtils.addAtributo("TRFCPT", "I");//"no se sabe speiForm.getTrfcpt()"
			alnovaUtils.addAtributo("ASSNTIN", ejecucionTO.getRfcord());//"no se sabe que es!!! speiForm.getRfcord()"
			alnovaUtils.addAtributo("TYPPAGO", ejecucionTO.getTyppago()); //"no sepo que es!! speiForm.getTyppago()" -- Hardcode 01 en ebank
			alnovaUtils.addAtributo("TYPINST", typinst);//"no sepo que es!! speiForm.getTypinst()"
			alnovaUtils.addAtributo("NUMREF", numref); //Existe un hardcode en ebank
			alnovaUtils.addAtributo("IDBENE", requestTO.getBenefRFC());//"RFC del beneficiario"
			alnovaUtils.addAtributo("TAX", requestTO.getBenefIVA());//"iva o comision o algo asi "
			alnovaUtils.addAtributo("REFCOB", ejecucionTO.getCobranza());
			alnovaUtils.addAtributo("DEBUSR", ejecucionTO.getNomord());
			alnovaUtils.addAtributo("CREUSR", ejecucionTO.getBeneficiario());
			alnovaUtils.addAtributo("DETAIL", "SPEI ip: "+speiRequestTO.getIpCliente());
			alnovaUtils.addAtributo("VALIDPWD", speiRequestTO.getNivelSeguridad());
			alnovaUtils.addAtributo("ASSNRESCTRY", "INT");//hardcode en ebank
			
			String peticionAlnova=alnovaUtils.convertirAtributos();
			System.out.println("Peticion a Alnova:"+peticionAlnova);
			
			String textoAdjunto=PropertiesService.getInstance().getPropertie("BaseDatos.properties", "db.texto.ebank");
			fecha=null;
			SimpleDateFormat format=new SimpleDateFormat(CuentasFrecuentesTO.FORMAT_DATE);
			fecha =format.parse(speiRequestTO.getHoraAplicacionFrecuente());
			
			
			textoAdjunto=textoAdjunto.replace("{nombre_usuario}", ejecucionTO.getNomord());
			textoAdjunto=textoAdjunto.replace("{monto}", requestTO.getImporte());
			textoAdjunto=textoAdjunto.replace("{fecha}", Formatter.formatFechaEbank(fecha));
			textoAdjunto=textoAdjunto.replace("{cuenta_beneficiario}", ejecucionTO.getDestino());
			textoAdjunto=textoAdjunto.replace("{alias_beneficiario}", ejecucionTO.getBeneficiario());
			textoAdjunto=textoAdjunto.replace("{concepto_pago}", ejecucionTO.getCobranza());
			
			SpeiProgramadoTO bean=new SpeiProgramadoTO();

			bean.setEstado("POR ENVIAR");
			bean.setNombre_transaccion("SPEI");
			bean.setNum_transaccion("F648");
			bean.setPeticion(peticionAlnova);
			bean.setId_usuario(speiRequestTO.getIdUsuario());
			bean.setId_email(speiRequestTO.getIdEmail());
			bean.setHora_aplicacion(transformaFecha(speiRequestTO.getHoraAplicacionFrecuente()));
			bean.setTexto_adjunto(textoAdjunto);
			bean.setId_usuario(speiRequestTO.getIdUsuario());
			bean.setUser(speiRequestTO.getUser());
			
			TransferenciasDAO transferenciasDAO = new TransferenciasDAO();
			
			MessageElement element=transferenciasDAO.insertaSPei30(bean);
			
			String mensaje=(String)decode.toDeserialize("mensaje_spei_30", element, null);
			
			System.out.println("Respuesta de connector: "+mensaje);
			
			if("error de parser".equalsIgnoreCase(mensaje)){
				throw new EliteDataException("Error de fecha ",null,EliteRules.LEVEL_ACTION_MESSAGES);
			}else if(mensaje.indexOf("error") != -1){
				throw new EliteDataException("Error de insercion ",null,EliteRules.LEVEL_ACTION_MESSAGES);
			}
			
			responseTO.setConfirmarTransferenciaSpeiTO(ejecucionTO);
			
		} catch (IOException e) {
			throw new TransferenciasException(e);
		} catch (ParseException e) {
			throw new TransferenciasException(e);
		} catch (DAOException e) {
			throw new TransferenciasException(e);
		}

		return responseTO;
	}
	
	private String transformaFecha(String fechaAplicacion){
		String fecha=null;
		
		PropertiesService propertiesService=PropertiesService.getInstance();
		log.info("Hora de modificacion de frecuente: "+fechaAplicacion);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String minutosFrecuenteBloqueo=propertiesService.getPropertie("Spei30.properties", "spei.30.minutos.bloqueo");
			
			Date fechaApp=dateFormat.parse(fechaAplicacion);
			
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(fechaApp);
			calendar.add(Calendar.MINUTE, Integer.parseInt(minutosFrecuenteBloqueo));
			
			fechaApp=calendar.getTime();
//			dd-mm-yyyy-hh24:mi:ss -> formato del Oracle
			dateFormat.applyPattern("dd-MM-yyyy-HH:mm:ss");
			
			fecha=dateFormat.format(fechaApp);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("Hora de modificacion de frecuente con suma de tiempo: "+fecha);
		
		return fecha;
	}
	
}
