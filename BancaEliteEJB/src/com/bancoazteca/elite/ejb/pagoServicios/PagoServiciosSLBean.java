package com.bancoazteca.elite.ejb.pagoServicios;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import com.bancoazteca.elite.beans.AgenteDEXTO;
import com.bancoazteca.elite.beans.BeneficiarioDineroExpresTO;
import com.bancoazteca.elite.beans.FrecuentesTiempoAireRequestTO;
import com.bancoazteca.elite.beans.FrecuentesTiempoAireResponseTO;
import com.bancoazteca.elite.beans.LocalidadDEXTO;
import com.bancoazteca.elite.beans.MovimientoTarjetaTO;
import com.bancoazteca.elite.beans.BeneficiarioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.BeneficiarioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.ConfirmacionPagoServiciosTO;
import com.bancoazteca.elite.beans.ConfirmacionPagoTarjetaOtrosBancosTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaTransaccionesTO;
import com.bancoazteca.elite.beans.CuentaVO;
import com.bancoazteca.elite.beans.CuentaVisibleDineroExpressTO;
import com.bancoazteca.elite.beans.DatosAdicionalesPagoServiciosTO;
import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.DonativosRequestTO;
import com.bancoazteca.elite.beans.DonativosResponseTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesResponseTO;
import com.bancoazteca.elite.beans.PagoServicioAztecaWebRequestTO;
import com.bancoazteca.elite.beans.PagoServicioAztecaWebResponseTO;
import com.bancoazteca.elite.beans.PagoServicioLuzRequestTO;
import com.bancoazteca.elite.beans.PagoServicioLuzResponsetTO;
import com.bancoazteca.elite.beans.PagoServiciosAvicolaRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosAvicolaResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosIusacellRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosIusacellResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosMaxiGasRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosMaxiGasResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosMovistarRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosMovistarResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosParametrizableRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosParametrizableResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosSkyRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosSkyResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosTelmexRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosTelmexResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosTiempoAireRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosTiempoAireResponseTO;
import com.bancoazteca.elite.beans.PagoServiciosToditoCardRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosToditoCardResponseTO;
import com.bancoazteca.elite.beans.PagoTarjetaCreditoRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaCreditoResponseTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosFrecuentesTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosResponseTO;
import com.bancoazteca.elite.beans.TarjetaCreditoOtrosBancosTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.transferencias.TransferenciasException;
import com.bancoazteca.elite.util.DigitalFingerUtil;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaResponseTO;
import com.bancoazteca.elite.beans.TarjetaCreditoAztecaTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaRequestTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaResponseTO;
import com.bancoazteca.elite.beans.AltaTarjetaFrecuenteTO;

/**
 * 
 * @author Jorge Bringas
 * 
 */
 
public class PagoServiciosSLBean implements SessionBean {

	private static final long serialVersionUID = -7533869742049711232L;
	
	private static final Logger log = Logger.getLogger(PagoServiciosSLBean.class);

	public void ejbActivate() throws EJBException, RemoteException {
	}
	public void ejbPassivate() throws EJBException, RemoteException {
	}
	public void ejbRemove() throws EJBException, RemoteException {
	}
	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
	}
	public void ejbCreate() {
	}

	@SuppressWarnings("unchecked")
	public PagoServiciosTelmexResponseTO setInitialTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = new PagoServiciosTelmexResponseTO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			//Invocacion para validar que se tengan permisos para realizar pago de servicios.
			MessageElement message = pagoServiciosDAO.validaNivelSeguridad(pagoServiciosTelmexRequestTO.getUser());
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(message);
			pagoServiciosRule.validaNivelSeguridad();
			
			MessageElement messageElement = pagoServiciosDAO.setInitialTelmexPayment(pagoServiciosTelmexRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialTelmexPayment();
			messageElement = pagoServiciosDAO.setInitialServiceTelmexPayment(pagoServiciosTelmexRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialServiceTelmexPayment();
			messageElement = pagoServiciosDAO.setStartInitialServiceTelmexPayment(pagoServiciosTelmexRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialStartServiceTelmexPayment();

			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);					
			
			Collection<String> cuentas = (Collection<String>) decode.toDeserialize("cuentas", messageElement,null);			
			Map<String, String> mapCuentas = new HashMap<String, String>();					
			for (String llaveValor: cuentas){
				mapCuentas.put(llaveValor, llaveValor);
			}
			
			Collection<String> servicios = (Collection<String>)decode.toDeserialize("servicios", messageElement,null);
			Iterator<String> iteratorServicios = servicios.iterator();
			Map<String, String> mapServicios = new HashMap<String, String>();
			while (iteratorServicios.hasNext()) {
				String llaveValor = iteratorServicios.next();
				mapServicios.put(llaveValor, llaveValor);
			}
			pagoServiciosTelmexResponseTO.setMapServicios(mapServicios);
			
			pagoServiciosTelmexResponseTO.setCuentas(cuentas);
			pagoServiciosTelmexResponseTO.setMapCuentas(mapCuentas);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} 
		return pagoServiciosTelmexResponseTO;
	}

	public PagoServiciosTelmexResponseTO setDataTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = null;
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setDataTelmexPayment(pagoServiciosTelmexRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateDataTelmexPayment();
			XmlDecoder decode = new XmlDecoder();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosTelmexResponseTO");			
			pagoServiciosTelmexResponseTO = (PagoServiciosTelmexResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			pagoServiciosTelmexResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return pagoServiciosTelmexResponseTO;
	}

	public PagoServiciosTelmexResponseTO setConfimTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = new PagoServiciosTelmexResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			XmlDecoder decode = new XmlDecoder();
			MessageElement messageElement = pagoServiciosDAO.setConfimTelmexPayment(pagoServiciosTelmexRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);			
			pagoServiciosRule.validateconfimTelmexPayment();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosTelmexResponseTO");			
			pagoServiciosTelmexResponseTO = (PagoServiciosTelmexResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			pagoServiciosDAO.setExecuteWaitTelmexPayment(pagoServiciosTelmexRequestTO);
			messageElement = pagoServiciosDAO.setExecuteTelmexPayment(pagoServiciosTelmexRequestTO);			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ebanking_web_BeanKey", "com.bancoazteca.elite.beans.DatosAdicionalesPagoServiciosTO");
			DatosAdicionalesPagoServiciosTO datosAdicionales = (DatosAdicionalesPagoServiciosTO) decode.toDeserialize("ebanking_web_BeanKey", messageElement, map);
			
			pagoServiciosTelmexResponseTO.setFolio(datosAdicionales.getNumeroOperacion());
			pagoServiciosTelmexResponseTO.setFechaAplicacion(datosAdicionales.getFechaAplicacion());
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return pagoServiciosTelmexResponseTO;
	}

	@SuppressWarnings("unchecked")
	public PagoServiciosIusacellResponseTO setInitialIusacellPayment(PagoServiciosIusacellRequestTO requestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosIusacellResponseTO responseTO = new PagoServiciosIusacellResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		try {
			//Invocacion para validar que se tengan permisos para realizar pago de servicios.
			MessageElement message = dao.validaNivelSeguridad(requestTO.getUser());
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(message);
			pagoServiciosRule.validaNivelSeguridad();
			
			MessageElement messageElement = dao.setInitialIusacellPayment(requestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateInitialIusacellPayment();
			messageElement = dao.setInitialServiceIusacellPayment(requestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateInitialServiceIusacellPayment();
			messageElement = dao.setStartInitialServiceIusacellPayment(requestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateStartInitialServiceIusacellPayment();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);

			Collection<String> cuentas = (Collection<String>)decode.toDeserialize("cuentas", messageElement,null);
			Iterator<String> iteratorCuentas = cuentas.iterator();
			Map<String, String> mapCuentas = new HashMap<String, String>();
			while (iteratorCuentas.hasNext()) {
				String llaveValor = iteratorCuentas.next();
				mapCuentas.put(llaveValor, llaveValor);
			}

			Collection<String> servicios = (Collection<String>)decode.toDeserialize("servicios", messageElement,null);
			Iterator<String> iteratorServicios = servicios.iterator();
			Map<String, String> mapServicios = new HashMap<String, String>();
			while (iteratorServicios.hasNext()) {
				String llaveValor = iteratorServicios.next();
				mapServicios.put(llaveValor, llaveValor);
			}
			responseTO.setMapServicios(mapServicios);
			responseTO.setMapCuentas(mapCuentas);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	public PagoServiciosIusacellResponseTO setDataIusacellPayment(PagoServiciosIusacellRequestTO requestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosIusacellResponseTO responseTO = new PagoServiciosIusacellResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try {
			MessageElement messageElement = dao.setFormDataIusacellPayment(requestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateFormDataIusacellPayment();

			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosIusacellResponseTO");
			responseTO = (PagoServiciosIusacellResponseTO)decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return responseTO;
	}

	public PagoServiciosIusacellResponseTO setConfirmIusacellPayment(PagoServiciosIusacellRequestTO requestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosIusacellResponseTO responseTO = new PagoServiciosIusacellResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();

		try {
			MessageElement messageElement = dao.setTokenIusacellPayment(requestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateTokenIusacellPayment();
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosIusacellResponseTO");
			responseTO = (PagoServiciosIusacellResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			messageElement = dao.setExecuteIusacellPayment(requestTO);
			messageElement = dao.setExecuteWaitIusacellPayment(requestTO);
			PagoServiciosIusacellResponseTO responseTO2 = (PagoServiciosIusacellResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ebanking_web_BeanKey", "com.bancoazteca.elite.beans.DatosAdicionalesPagoServiciosTO");
			DatosAdicionalesPagoServiciosTO datosAdicionales = (DatosAdicionalesPagoServiciosTO) decode.toDeserialize("ebanking_web_BeanKey", messageElement, map);
			responseTO.setFolio(datosAdicionales.getNumeroOperacion());
			responseTO.setFechaAplicacion(datosAdicionales.getFechaAplicacion());
			
			responseTO.setOperacionIusacell(responseTO2.getOperacionIusacell());
			rule = new PagoServiciosRule(messageElement);
			rule.validateConfirmIusacellPayment();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} 
		return responseTO;
	}

	@SuppressWarnings("unchecked")
	public PagoServicioLuzResponsetTO setInitialLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioLuzResponsetTO pagoServicioLuzResponsetTO = new PagoServicioLuzResponsetTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		try {
			
			//Invocacion para validar que se tengan permisos para realizar pago de servicios.
			MessageElement message = pagoServiciosDAO.validaNivelSeguridad(pagoServicioLuzRequestTO.getUser());
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(message);
			pagoServiciosRule.validaNivelSeguridad();
			
			MessageElement messageElement = pagoServiciosDAO.setInitialLuzPayment(pagoServicioLuzRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateInitialLuzPayment();
			messageElement = pagoServiciosDAO.setStartInitialLuzPayment(pagoServicioLuzRequestTO);	
			rule = new PagoServiciosRule(messageElement);
			rule.validateStartInitialLuzPayment();
			
			messageElement = pagoServiciosDAO.setStartInitialServiceLuzPaymen(pagoServicioLuzRequestTO);	
			rule = new PagoServiciosRule(messageElement);
			rule.validateStartDataInitialLuzPayment();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			
			Collection<String> cuentas = (Collection<String>) decode.toDeserialize("cuentas", messageElement,null);			
			Map<String, String> mapCuentas = new HashMap<String, String>();					
			for (String llaveValor: cuentas){
				mapCuentas.put(llaveValor, llaveValor);
			}

			Collection<String> servicios = (Collection<String>)decode.toDeserialize("servicios", messageElement,null);
			Iterator<String> iteratorServicios = servicios.iterator();
			Map<String, String> mapServicios = new HashMap<String, String>();
			while (iteratorServicios.hasNext()) {
				String llaveValor = iteratorServicios.next();
				mapServicios.put(llaveValor, llaveValor);
			}
			pagoServicioLuzResponsetTO.setMapServicios(mapServicios);
			pagoServicioLuzResponsetTO.setMapCuentas(mapCuentas);			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return pagoServicioLuzResponsetTO;
	}

	public PagoServicioLuzResponsetTO setDataLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioLuzResponsetTO pagoServicioLuzResponsetTO = null;
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setDataLuzPayment(pagoServicioLuzRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateDataLuzPayment();			
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServicioLuzResponsetTO");
			pagoServicioLuzResponsetTO = (PagoServicioLuzResponsetTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			pagoServicioLuzResponsetTO.setDispositivoHuellaTO(dispositivoHuellaTO);						
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return pagoServicioLuzResponsetTO;
	}

	public PagoServicioLuzResponsetTO setConfirmLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioLuzResponsetTO pagoServicioLuzResponsetTO = null;
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setConfimLuzPayment(pagoServicioLuzRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateConfirmLuzPayment();
			messageElement = pagoServiciosDAO.setWaitExecuteLuzPayment(pagoServicioLuzRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateWaitExecuteLuzPayment();
			messageElement = pagoServiciosDAO.setExecuteLuzPayment(pagoServicioLuzRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateExecuteLuzPayment();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ebanking_web_BeanKey", "com.bancoazteca.elite.beans.PagoServicioLuzResponsetTO");
			pagoServicioLuzResponsetTO = (PagoServicioLuzResponsetTO) decode.toDeserialize("ebanking_web_BeanKey", messageElement, mappedBeans);			
					
			log.info("numero de operacion:ejb "+pagoServicioLuzResponsetTO.getNumeroOperacion());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} 
		
		return pagoServicioLuzResponsetTO;
	}
	
	public void setEndLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {	
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			pagoServiciosDAO.setEndLuzPayment(pagoServicioLuzRequestTO);			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}			
	}
	
	@SuppressWarnings("unchecked")
	public PagoServiciosSkyResponseTO setInitialSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosSkyResponseTO skyResponseTO = new PagoServiciosSkyResponseTO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XMLDecode decode = new XMLDecode();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			//Invocacion para validar que se tengan permisos para realizar pago de servicios.
			MessageElement message = pagoServiciosDAO.validaNivelSeguridad(skyRequestTO.getUser());
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(message);
			pagoServiciosRule.validaNivelSeguridad();
			
			MessageElement messageElement = pagoServiciosDAO.setInitialSkyPayment(skyRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialSkyPayment();
			messageElement = pagoServiciosDAO.setInitialServiceSkyPayment(skyRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialServiceSkyPayment();
			messageElement = pagoServiciosDAO.setStartInitialServiceSkyPayment(skyRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialStartServiceSkyPayment();

			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);

			Collection<String> cuentas = (Collection<String>) decode.buildSimpleStringCollection(messageElement, "cuentas");
			Iterator<String> iteratorCuentas = cuentas.iterator();
			Map<String, String> mapCuentas = new HashMap<String, String>();
			while (iteratorCuentas.hasNext()) {
				String llaveValor = iteratorCuentas.next();
				mapCuentas.put(llaveValor, llaveValor);
			}
			
			Collection<String> servicios = (Collection<String>) decode.buildSimpleStringCollection(messageElement, "servicios");
			Iterator<String> iteratorServicios = servicios.iterator();
			Map<String, String> mapServicios = new HashMap<String, String>();
			while (iteratorServicios.hasNext()) {
				String llaveValor = iteratorServicios.next();
				mapServicios.put(llaveValor, llaveValor);
			}
			String tituloReferencia = decode.getString(messageElement, "referenceTitle");

			skyResponseTO.setCuentas(cuentas);
			skyResponseTO.setMapCuentas(mapCuentas);
			skyResponseTO.setServicios(servicios);
			skyResponseTO.setMapServicios(mapServicios);
			skyResponseTO.setTituloReferencia(tituloReferencia);

		}catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return skyResponseTO;
	}

	public PagoServiciosSkyResponseTO setDataSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosSkyResponseTO skyResponseTO = new PagoServiciosSkyResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setDataSkyPayment(skyRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateDataSkyPayment();
			XMLDecode decode = new XMLDecode();
			ConfirmacionPagoServiciosTO confirmacionPagoServicios = (ConfirmacionPagoServiciosTO) decode.buildBean(ConfirmacionPagoServiciosTO.class, messageElement, "pagoServicioForm");
			String cuentaCargo = confirmacionPagoServicios.getCuentaCargo().substring(0, 14);
			log.info("cuentaCargo: " + cuentaCargo);
			String cuentaCargoFormateada = Formatter.formatSplittedCuenta(cuentaCargo);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			skyResponseTO.setConfirmacionPagoServicios(confirmacionPagoServicios);
			skyResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			skyResponseTO.setCuentaCargoFormateada(cuentaCargoFormateada);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return skyResponseTO;
	}

	public PagoServiciosSkyResponseTO setConfirmSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosSkyResponseTO skyResponseTO = new PagoServiciosSkyResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			
			MessageElement messageElement = pagoServiciosDAO.setConfirmSkyPayment(skyRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateConfirmSkyPayment();
			messageElement = pagoServiciosDAO.setExecuteWaitSkyPayment(skyRequestTO);
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.ConfirmacionPagoServiciosTO");
			ConfirmacionPagoServiciosTO confirmacionPagoServicios = (ConfirmacionPagoServiciosTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateWaitConfirmSkyPayment();
			messageElement = pagoServiciosDAO.setExecuteSkyPayment(skyRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateExecuteSkyPayment();
			//ConfirmacionPagoServiciosTO confirmacionPagoServicios = (ConfirmacionPagoServiciosTO) decoder.buildBean(ConfirmacionPagoServiciosTO.class, messageElement, "pagoServicioForm");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ebanking_web_BeanKey", "com.bancoazteca.elite.beans.DatosAdicionalesPagoServiciosTO");
			DatosAdicionalesPagoServiciosTO datosAdicionales = (DatosAdicionalesPagoServiciosTO) decode.toDeserialize("ebanking_web_BeanKey", messageElement, map);
			confirmacionPagoServicios.setFolio(datosAdicionales.getNumeroOperacion());
			confirmacionPagoServicios.setFechaAplicacion(datosAdicionales.getFechaAplicacion());
			skyResponseTO.setConfirmacionPagoServicios(confirmacionPagoServicios);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return skyResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoServiciosMovistarResponseTO setInitialMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosMovistarResponseTO responseTO = new PagoServiciosMovistarResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		try {
			//Invocacion para validar que se tengan permisos para realizar pago de servicios.
			MessageElement message = dao.validaNivelSeguridad(movistarRequestTO.getUser());
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(message);
			pagoServiciosRule.validaNivelSeguridad();
			
			MessageElement messageElement = dao.setInitialMovistarPayment(movistarRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateInitialMovistarPayment();
			messageElement = dao.setInitialServiceMovistarPayment(movistarRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateInitialServiceMovistarPayment();
			messageElement = dao.setStartInitialServiceMovistarPayment(movistarRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateStartInitialServiceMovistarPayment();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);

			
			Collection<String> cuentas = (Collection<String>)decode.toDeserialize("cuentas", messageElement,null);
			Iterator<String> iteratorCuentas = cuentas.iterator();
			Map<String, String> mapCuentas = new HashMap<String, String>();
			while (iteratorCuentas.hasNext()) {
				String llaveValor = iteratorCuentas.next();
				mapCuentas.put(llaveValor, llaveValor);
			}

			Collection<String> servicios = (Collection<String>)decode.toDeserialize("servicios", messageElement,null);
			Iterator<String> iteratorServicios = servicios.iterator();
			Map<String, String> mapServicios = new HashMap<String, String>();
			while (iteratorServicios.hasNext()) {
				String llaveValor = iteratorServicios.next();
				mapServicios.put(llaveValor, llaveValor);
			}
			
			responseTO.setMapCuentas(mapCuentas);
			responseTO.setMapServicios(mapServicios);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	public PagoServiciosMovistarResponseTO setDataMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosMovistarResponseTO responseTO = new PagoServiciosMovistarResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try {
			MessageElement messageElement = dao.setFormDataMovistarPayment(movistarRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateFormDataMovistarPayment();

			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosMovistarResponseTO");
			responseTO = (PagoServiciosMovistarResponseTO)decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			String cuentaCargoFormateada = (String) responseTO.getCuentaCargo().subSequence(0, 14);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			responseTO.setCuentaCargoFormateada(cuentaCargoFormateada);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return responseTO;
	}
	
	public PagoServiciosMovistarResponseTO setConfirmMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosMovistarResponseTO responseTO = new PagoServiciosMovistarResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();

		try {
			MessageElement messageElement = dao.setTokenMovistarPayment(movistarRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateTokenMovistarPayment();
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosMovistarResponseTO");
			responseTO = (PagoServiciosMovistarResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			String cuentaCargoFormateada = (String) responseTO.getCuentaCargo().subSequence(0, 14);
			responseTO.setCuentaCargoFormateada(cuentaCargoFormateada);
			messageElement = dao.setExecuteMovistarPayment(movistarRequestTO);
			messageElement = dao.setExecuteWaitMovistarPayment(movistarRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConfirmMovistarPayment();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ebanking_web_BeanKey", "com.bancoazteca.elite.beans.DatosAdicionalesPagoServiciosTO");
			DatosAdicionalesPagoServiciosTO datosAdicionales = (DatosAdicionalesPagoServiciosTO) decode.toDeserialize("ebanking_web_BeanKey", messageElement, map);
			responseTO.setFolio(datosAdicionales.getNumeroOperacion());
			responseTO.setFechaAplicacion(datosAdicionales.getFechaAplicacion());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} 
		return responseTO;
	}

	public PagoServiciosTiempoAireResponseTO setMenuTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = new PagoServiciosTiempoAireResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try{
			MessageElement messageElement = dao.setMenuTiempoAirePayment(tiempoAireRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateMenuTiempoAirePayment();

			messageElement = dao.setMenuServiceTiempoAirePayment(tiempoAireRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateMenuServiceTiempoAirePayment();
			
			messageElement = dao.setStartMenuTiempoAirePayment(tiempoAireRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateStartMenuTiempoAirePayment();
			
		}catch(DAOException exception){
			exception.printStackTrace();
			throw new PagoServiciosException();
		}
		
		return responseTO;
	}
	
	public PagoServiciosTiempoAireResponseTO setInitialTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = null;
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuenta in //elite/tiempoAireForm return $cuenta";
		try{
			MessageElement messageElement = dao.setInitialTiempoAirePayment(tiempoAireRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialTiempoAirePayment();
			XMLDecode decode = new XMLDecode();
			XmlDecoder decoder = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("tiempoAireForm", "com.bancoazteca.elite.beans.PagoServiciosTiempoAireResponseTO");
			responseTO = (PagoServiciosTiempoAireResponseTO)decoder.toDeserialize("tiempoAireForm", messageElement, mappedBeans);
			responseTO.setMontos((Collection<String>)decoder.toDeserialize("allMontos", messageElement,null));
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			List<String> cuentas = (List<String>)decoder.toDeserialize("cuentas", messageElement,null);
			List<String> idsCuentas = (List<String>)decoder.toDeserialize("idsCuentas", messageElement,null);
			Map<String, String> mapCuentas = new HashMap<String, String>();
			for(int i = 0; i< cuentas.size(); i++){
				mapCuentas.put(cuentas.get(i), idsCuentas.get(i));
			}
			responseTO.setMapCuentas(mapCuentas);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return responseTO;
	}
	
	public PagoServiciosTiempoAireResponseTO setDataTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = null;
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try{
			MessageElement messageElement = dao.setDataTiempoAirePayment(tiempoAireRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateDataTiempoAirePayment();
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("tiempoAireForm", "com.bancoazteca.elite.beans.PagoServiciosTiempoAireResponseTO");
			responseTO = (PagoServiciosTiempoAireResponseTO)decode.toDeserialize("tiempoAireForm", messageElement, mappedBeans);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return responseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoServiciosTiempoAireResponseTO setConfirmTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = new PagoServiciosTiempoAireResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try{
			MessageElement messageElement = dao.setConfirmTiempoAirePayment(tiempoAireRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateConfirmTiempoAirePayment();
			messageElement = dao.setExecuteTiempoAirePayment(tiempoAireRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConfirmTiempoAirePayment();
			
			messageElement = dao.setExecuteWaitTiempoAirePayment(tiempoAireRequestTO);
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("tiempoAireForm", "com.bancoazteca.elite.beans.PagoServiciosTiempoAireResponseTO");
			responseTO=(PagoServiciosTiempoAireResponseTO)decode.toDeserialize("tiempoAireForm", messageElement, mappedBeans);
			
			if(responseTO != null)
			{
				String cuentaCargoFormateada = formatearCuentaUnica(responseTO.getCuenta());
				responseTO.setCuentaCargoFormateada(cuentaCargoFormateada);
			}
			
			rule = new PagoServiciosRule(messageElement);
			rule.validateExecuteWaitTiempoAirePayment();
			
		}catch(DAOException exception){
			exception.printStackTrace();
			throw new PagoServiciosException();
		}catch(EliteDataException e){
			Map<String,String> errors =(Map<String,String>) e.getErrorData();
			if(!((String)errors.get("mensaje")).contains("exito")){
				throw new EliteDataException("Error",e.getErrorData(),1);
			}else{
				responseTO.setMensaje(errors.get("mensaje"));
			}
		}

		return responseTO;
	}
	
	
	public FrecuentesResponseTO setInitialFrecuentesParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $serviciosFrecuentes in //elite/ServiciosCtasFrecuentesForm return $serviciosFrecuentes";
		try{
			dao.setInitialFrecuentes(frecuentesRequestTO);
			MessageElement messageElement = dao.setStartInitialFrecuentes(frecuentesRequestTO);
			XmlDecoder decoder = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			MessageElement messageElement2 = new MessageElement(element);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ServiciosCtasFrecuentesVO", "com.bancoazteca.elite.beans.FrecuentesResponseTO");
			Collection<FrecuentesResponseTO> frecuentes = (Collection<FrecuentesResponseTO>)decoder.toDeserialize("serviciosFrecuentes", messageElement2, mappedBeans);
			if(frecuentes == null){
				frecuentes = new ArrayList<FrecuentesResponseTO>();
			}
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ServiciosCtasFrecuentesForm", "com.bancoazteca.elite.beans.FrecuentesResponseTO");
			FrecuentesResponseTO beanUser = (FrecuentesResponseTO)decoder.toDeserialize("ServiciosCtasFrecuentesForm", messageElement, mappedBeans);
			frecuentesResponseTO.setFrecuentes(frecuentes);
			frecuentesResponseTO.setUser_id(beanUser.getUser_id());
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	
	public FrecuentesResponseTO setAgregarFrecuenteParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $serviciosFrecuentes in //elite/ServiciosCtasFrecuentesForm return <cuentasFrecuentes>{$serviciosFrecuentes}</cuentasFrecuentes>";
		try{
			MessageElement messageElementAdd = dao.setAgregarFrecuenteParametrizado(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElementAdd);
			rule.validateAgregarFrecuente();
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();//
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElementAdd);
			
			frecuentesResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setAgregarFrecuenteEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $serviciosFrecuentes in //elite/ServiciosCtasFrecuentesForm return <cuentasFrecuentes>{$serviciosFrecuentes}</cuentasFrecuentes>";
		try{
			MessageElement messageElementAdd = dao.setAgregarFrecuenteEjecucionParametrizado(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElementAdd);
			rule.validateAgregarFrecuente();

		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}

	@SuppressWarnings("unchecked")
	public PagoTarjetaCreditoResponseTO setInitialTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = new PagoTarjetaCreditoResponseTO();
		
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement= pagoServiciosDAO.setInitialTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialTarjetaCreditoPayment();
			messageElement = pagoServiciosDAO.setInitialServiceTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialServiceTarjetaCreditoPayment();
			messageElement = pagoServiciosDAO.setStartInitialTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateStartInitialTarjetaCreditoPayment();
			
			messageElement = pagoServiciosDAO.setInitialWaitTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialWaitTarjetaCreditoPayment();
			XMLDecode decode = new XMLDecode();
			Collection<PagoTarjetaCreditoResponseTO> tarjetasCredito = (Collection<PagoTarjetaCreditoResponseTO>) decode.buildCollectionBeans(PagoTarjetaCreditoResponseTO.class, messageElement, "TarjetaBaseAlnovaVO");
			pagoTarjetaCreditoResponseTO.setTarjetasCredito(tarjetasCredito);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return pagoTarjetaCreditoResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoTarjetaCreditoResponseTO setDataTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = new PagoTarjetaCreditoResponseTO();
		
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setDataTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateDataTarjetaCreditoPayment();
			XMLDecode decode = new XMLDecode();
			Collection<PagoTarjetaCreditoResponseTO> tarjetasCredito = (Collection<PagoTarjetaCreditoResponseTO>) decode.buildCollectionBeans(PagoTarjetaCreditoResponseTO.class, messageElement, "TarjetaBaseAlnovaVO");
			Collection<PagoTarjetaCreditoResponseTO> cuentas = (Collection<PagoTarjetaCreditoResponseTO>) decode.buildCollectionBeans(PagoTarjetaCreditoResponseTO.class, messageElement, "CuentaLO");
			pagoTarjetaCreditoResponseTO = (PagoTarjetaCreditoResponseTO) decode.buildBean(PagoTarjetaCreditoResponseTO.class, messageElement, "pagoTarjetaAztecaForm");
			pagoTarjetaCreditoResponseTO.setCuentas(cuentas);
			pagoTarjetaCreditoResponseTO.setTarjetasCredito(tarjetasCredito);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return pagoTarjetaCreditoResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoTarjetaCreditoResponseTO setConfirmTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = new PagoTarjetaCreditoResponseTO();
		
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setConfirmTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateConfirmTarjetaCreditoPayment();
			
			messageElement = pagoServiciosDAO.setConfirmWaitTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateConfirmWaitTarjetaCreditoPayment();
			
			XMLDecode decode = new XMLDecode();
			Collection<PagoTarjetaCreditoResponseTO> tarjetasCredito = (Collection<PagoTarjetaCreditoResponseTO>) decode.buildCollectionBeans(PagoTarjetaCreditoResponseTO.class, messageElement, "TarjetaBaseAlnovaVO");
			Collection<PagoTarjetaCreditoResponseTO> cuentas = (Collection<PagoTarjetaCreditoResponseTO>) decode.buildCollectionBeans(PagoTarjetaCreditoResponseTO.class, messageElement, "CuentaVO");
			pagoTarjetaCreditoResponseTO.setCuentas(cuentas);
			pagoTarjetaCreditoResponseTO.setTarjetasCredito(tarjetasCredito);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return pagoTarjetaCreditoResponseTO;
	}
		
	@SuppressWarnings("unchecked")
	public PagoTarjetaOtrosBancosResponseTO getInitialTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = new PagoTarjetaOtrosBancosResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		PagoServiciosRule pagoServiciosRule = null;
		try {
			//Invocacion para validar que se tengan permisos para realizar pago de servicios.
			MessageElement message = pagoServiciosDAO.validaNivelSeguridad(tarjetaOtrosBancosRequestTO.getUser());
			pagoServiciosRule = new PagoServiciosRule(message);
			pagoServiciosRule.validaNivelSeguridad();
			MessageElement messageElementInitialMenu = pagoServiciosDAO.setInitialMenuTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElementInitialMenu);
			pagoServiciosRule.validateInitialMenuTarjetaPaymentOthersBank();
			
			MessageElement messageElementWaitInitial = pagoServiciosDAO.setWaitInitialTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElementWaitInitial);
			pagoServiciosRule.validateWaitInitialTarjetaPaymentOthersBank();
			
			MessageElement messageElementInitial = pagoServiciosDAO.getInitialTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElementInitial);
			pagoServiciosRule.validateInitialTarjetaPaymentOthersBank();
			
			MessageElement messageElementStartService = pagoServiciosDAO.getStartServiceTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElementStartService);
			pagoServiciosRule.validateStartServiceTarjetaPaymentOthersBank();
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} 

		return tarjetaOtrosBancosResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoTarjetaOtrosBancosResponseTO getDataTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = new PagoTarjetaOtrosBancosResponseTO();
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		XmlDecoder decoder = new XmlDecoder();
		try {
			MessageElement messageElement = pagoServiciosDAO.getDataTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateDataTarjetaPaymentOthersBank();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoTarjetasForm", "com.bancoazteca.elite.beans.ConfirmacionPagoTarjetaOtrosBancosTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaTransaccionesTO");
			ConfirmacionPagoTarjetaOtrosBancosTO confirmacionPagoTarjetaOtrosBancosTO = (ConfirmacionPagoTarjetaOtrosBancosTO)decoder.toDeserialize("pagoTarjetasForm", messageElement, mappedBeans);
			formatearCuentas(confirmacionPagoTarjetaOtrosBancosTO.getCuentas());
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("tarjetaCredito", "com.bancoazteca.elite.beans.TarjetaCreditoOtrosBancosTO");
			TarjetaCreditoOtrosBancosTO tarjetaCredito = (TarjetaCreditoOtrosBancosTO)decoder.toDeserialize("tarjetaCredito", messageElement, mappedBeans);
			
			tarjetaOtrosBancosResponseTO.setConfirmacionPagoTarjetaOtrosBancosTO(confirmacionPagoTarjetaOtrosBancosTO);
			tarjetaOtrosBancosResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			tarjetaOtrosBancosResponseTO.setTarjetaCredito(tarjetaCredito);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return tarjetaOtrosBancosResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoTarjetaOtrosBancosResponseTO getConfirmTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = new PagoTarjetaOtrosBancosResponseTO();
		
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		XmlDecoder decoder = new XmlDecoder();
		try {
			MessageElement messageElement = pagoServiciosDAO.getConfirmTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateConfirmTarjetaPaymentOthersBank();
			Map<String, String> mensajes = (Map<String, String>) decoder.toDeserialize("org.apache.struts.action.ERROR", messageElement, null);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("frequentForm", "com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosFrecuentesTO");
			PagoTarjetaOtrosBancosFrecuentesTO otrosBancosFrecuentesTO = (PagoTarjetaOtrosBancosFrecuentesTO) decoder.toDeserialize("frequentForm", messageElement, mappedBeans);
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("PagoTarjetasForm", "com.bancoazteca.elite.beans.ConfirmacionPagoTarjetaOtrosBancosTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaTransaccionesTO");
			ConfirmacionPagoTarjetaOtrosBancosTO confirmacionPagoTarjetaOtrosBancosTO = (ConfirmacionPagoTarjetaOtrosBancosTO)decoder.toDeserialize("PagoTarjetasForm", messageElement, mappedBeans);
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("TarjetaCreditoVO", "com.bancoazteca.elite.beans.TarjetaCreditoOtrosBancosTO");
			TarjetaCreditoOtrosBancosTO tarjetaCredito = (TarjetaCreditoOtrosBancosTO)decoder.toDeserialize("TarjetaCreditoVO", messageElement, mappedBeans);
			
			
			tarjetaOtrosBancosResponseTO.setOtrosBancosFrecuentesTO(otrosBancosFrecuentesTO);
			tarjetaOtrosBancosResponseTO.setMensajesEjecucion(mensajes);
			tarjetaOtrosBancosResponseTO.setConfirmacionPagoTarjetaOtrosBancosTO(confirmacionPagoTarjetaOtrosBancosTO);
			tarjetaOtrosBancosResponseTO.setTarjetaCredito(tarjetaCredito);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} 

		return tarjetaOtrosBancosResponseTO;
	}	

	public String setSendMailTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {	
		String mensaje = "";
		try {
			PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
			MessageElement messageElement = pagoServiciosDAO.setSendMailTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");			
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/pagoTarjetas/tarjetasEjecutar.jsp")){				
				mensaje = "El E-Mail fue enviado correctamente.";
			}else{
				mensaje = "Lo sentimos, NO fue posible enviar el E-Mail.";
			}		
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return mensaje;
	}	
		
	private void formatearCuentas(Collection<CuentaTransaccionesTO> cuentas) {
		int i =0;
		for (CuentaTransaccionesTO cuentaTO : cuentas) {
			i = i + 1;
			cuentaTO.setCuentaFormateada(Formatter.formatSplittedCuenta(cuentaTO.getNumero()));
			log.info(" cuenta formateada: "+cuentaTO.getCuentaFormateada());			
		}
	}
	
	private String formatearCuentaUnica(String cuenta) {
		String cuentaFormateada = null;
		if(cuenta != null)
		{
			cuentaFormateada = Formatter.formatSplittedCuenta(cuenta);
			cuentaFormateada = Formatter.removeSpaces(cuentaFormateada);	
		}
		return cuentaFormateada;
	}

	public FrecuentesResponseTO setConsultaCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		try{
			dao.setConsultaCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
			MessageElement messageElement = dao.setStartConsultaCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTarjetasOtrosBancos();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>)decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes==null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setAgregaCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		try{
			MessageElement messageElement = dao.setAgregaCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateAgregaCuentasFrecuentesTarjetasOtrosBancos();
			messageElement = dao.setAgregaWaitCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTarjetasOtrosBancos();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>)decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes==null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO iniciarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos (FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		Map<String, String> params = new HashMap<String, String>();
		try{
			if(frecuentesRequestTO.getMethod().equals("editar"))
				params.put("method", "preupdateAccount");
			else if(frecuentesRequestTO.getMethod().equals("eliminar"))
				params.put("method", "deleteFrequentAccountPre");
			frecuentesRequestTO.setParametros(params);
			
			MessageElement messageElement = dao.iniciarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
			
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			if(frecuentesRequestTO.getMethod().equals("editar"))
				rule.validateIniciarEditarCuentasFrecuentesTarjetasOtrosBancos();
			else if(frecuentesRequestTO.getMethod().equals("eliminar"))
				rule.validateIniciarEliminarCuentasFrecuentesTarjetasOtrosBancos();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes==null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}

	public FrecuentesResponseTO ejecutarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		Map<String, String> params = new HashMap<String, String>();
		
		try{
			if(frecuentesRequestTO.getMethod().equals("editar")){
				params.put("method", "updateAccount");
				params.put("submit", "Modificar");
			}else if(frecuentesRequestTO.getMethod().equals("eliminar")){
				params.put("method", "deleteFrequentAccount");
				params.put("bank", frecuentesRequestTO.getBanco());
				params.put("bank", frecuentesRequestTO.getBanco());
				params.put("bank", frecuentesRequestTO.getBanco());
				params.put("tipoTDC", frecuentesRequestTO.getTipoTarjeta());
			}
			frecuentesRequestTO.setParametros(params);
			
			MessageElement messageElement = dao.prepararEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validatePrepararEditarCuentasFrecuentesTarjetasOtrosBancos();
			
			params.clear();
			if(frecuentesRequestTO.getMethod().equals("editar")){
				params.put("method", "updateAccount");
			}else if(frecuentesRequestTO.getMethod().equals("eliminar")){
				params.put("method", "deleteFrequentAccount");
			}
			frecuentesRequestTO.setParametros(params);
			
			messageElement = dao.ejecutarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateEjecutarEditarCuentasFrecuentesTarjetasOtrosBancos();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes == null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setConsultaCuentasFrecuentesTiempoAire(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $telefonosFrecuentes in //elite/frequentInfo return $telefonosFrecuentes";
		try{
			dao.setConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			MessageElement messageElement = dao.setStartConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTiempoAire();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<Map> telefonosFrecuentes =(Collection<Map>)decode.toDeserialize("key_telefonos", messageElement, null);
			if(telefonosFrecuentes == null){
				telefonosFrecuentes = new ArrayList<Map>();
			}
			frecuentesResponseTO.setTelefonosFrecuentes(telefonosFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	
	//nuevo referencias frecuentes tiempo aire
	@SuppressWarnings({ "unchecked" })
	public FrecuentesTiempoAireResponseTO referenciasFrecuentesTiempoAireInit(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		PagoServiciosTiempoAireRequestTO tiempoAireRequestTO = new PagoServiciosTiempoAireRequestTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		String query = "for $telefonosFrecuentes in //elite/frequentInfo return $telefonosFrecuentes";
		
		try{
			tiempoAireRequestTO.setCarrier(frecuentesTiempoAireRequestTO.getCompania());
			tiempoAireRequestTO.setUser(frecuentesTiempoAireRequestTO.getUser());
			
			setMenuTiempoAirePayment(tiempoAireRequestTO);
			
			MessageElement messageElement = pagoServiciosDAO.setInitialTiempoAirePayment(tiempoAireRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialTiempoAirePayment();
			
			frecuentesRequestTO.setUser(frecuentesTiempoAireRequestTO.getUser());
			
			messageElement = pagoServiciosDAO.setConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateConsultaCuentasFrecuentesTiempoAireInit();
			
			messageElement = pagoServiciosDAO.setStartConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateConsultaCuentasFrecuentesTiempoAire();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<Map> telefonosFrecuentes =(Collection<Map>)decode.toDeserialize("key_telefonos", messageElement, null);
			if(telefonosFrecuentes == null){
				telefonosFrecuentes = new ArrayList<Map>();
			}
			
			frecuentesTiempoAireResponseTO.setTelefonosFrecuentes(telefonosFrecuentes);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
			
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setAgregaReferenciasFrecuentesTiempoAire(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		String query = "for $telefonosFrecuentes in //elite/frequentInfo return $telefonosFrecuentes";
		try{
			MessageElement messageElement = pagoServiciosDAO.setAgregaReferenciasFrecuentesTiempoAire(frecuentesTiempoAireRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateAgregaCuentasFrecuentesTiempoAire();
			
			frecuentesRequestTO.setUser(frecuentesTiempoAireRequestTO.getUser());
			
			messageElement = pagoServiciosDAO.setStartConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTiempoAire();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);

			Collection<Map> telefonosFrecuentes =(Collection<Map>)decode.toDeserialize("key_telefonos", messageElement, null);
			if(telefonosFrecuentes == null){
				telefonosFrecuentes = new ArrayList<Map>();
			}
			frecuentesTiempoAireResponseTO.setTelefonosFrecuentes(telefonosFrecuentes);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setBorraReferenciasFrecuentesTiempoAireValidacion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		String query = "for $telefonosFrecuentes in //elite/frequentInfo return $telefonosFrecuentes";
		try{
			MessageElement messageElement = pagoServiciosDAO.setBorraReferenciasFrecuentesTiempoAireValidacion(frecuentesTiempoAireRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateBorraCuentasFrecuentesTiempoAireValidacion();
			
//			XmlDecoder decode = new XmlDecoder();
//			Element element = XMLFinder.getElement(messageElement.toString(), query);
//			messageElement = new MessageElement(element);
//			Collection<Map> telefonosFrecuentes =(Collection<Map>)decode.toDeserialize("key_telefonos", messageElement, null);
//			if(telefonosFrecuentes == null){
//				telefonosFrecuentes = new ArrayList<Map>();
//			}
//			frecuentesResponseTO.setTelefonosFrecuentes(telefonosFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
//		}catch(IOException e){
//			e.printStackTrace();
//			throw new PagoServiciosException(e);
		}
		return frecuentesTiempoAireResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public FrecuentesTiempoAireResponseTO setBorraReferenciasFrecuentesTiempoAireEjecucion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		XmlDecoder decode = new XmlDecoder();
		String query = "for $telefonosFrecuentes in //elite/frequentInfo return <tiempo_aire>{$telefonosFrecuentes}</tiempo_aire>";
		try{
			MessageElement messageElement = pagoServiciosDAO.setBorraReferenciasFrecuentesTiempoAireEjecucion(frecuentesTiempoAireRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateBorraCuentasFrecuentesTiempoAireEjecucion();
			
			frecuentesRequestTO.setUser(frecuentesTiempoAireRequestTO.getUser());
			
			messageElement = pagoServiciosDAO.setConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTiempoAireInit();
			
			messageElement = pagoServiciosDAO.setStartConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTiempoAire();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);  
			Collection<Map> telefonosFrecuentes =(Collection<Map>)decode.toDeserialize("key_telefonos", messageElement, null);
			if(telefonosFrecuentes == null){
				telefonosFrecuentes = new ArrayList<Map>();
			}
			
			frecuentesTiempoAireResponseTO.setTelefonosFrecuentes(telefonosFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setModificaReferenciasFrecuentesTiempoAireValidacion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		String query = "for $telefonosFrecuentes in //elite/frequentInfo return $telefonosFrecuentes";
		try{
			MessageElement messageElement = pagoServiciosDAO.setModificaReferenciasFrecuentesTiempoAireValidacion(frecuentesTiempoAireRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateEditarReferenciasFrecuentesTiempoAireValidacion();			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesTiempoAireResponseTO;		
	}
	
	public FrecuentesTiempoAireResponseTO setModificaReferenciasFrecuentesTiempoAireEjecucion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = new FrecuentesTiempoAireResponseTO();
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();
		FrecuentesRequestTO frecuentesRequestTO = new FrecuentesRequestTO();
		String query = "for $telefonosFrecuentes in //elite/frequentInfo return $telefonosFrecuentes";
		try{
			MessageElement messageElement = pagoServiciosDAO.setModificaReferenciasFrecuentesTiempoAireEjecucion(frecuentesTiempoAireRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateEditarReferenciasFrecuentesTiempoAireEjecucion();	
			
			frecuentesRequestTO.setUser(frecuentesTiempoAireRequestTO.getUser());
			
			messageElement = pagoServiciosDAO.setConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTiempoAireInit();
			
			messageElement = pagoServiciosDAO.setStartConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateConsultaCuentasFrecuentesTiempoAire();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);  
			Collection<Map> telefonosFrecuentes =(Collection<Map>)decode.toDeserialize("key_telefonos", messageElement, null);
			if(telefonosFrecuentes == null){
				telefonosFrecuentes = new ArrayList<Map>();
			}
			frecuentesTiempoAireResponseTO.setTelefonosFrecuentes(telefonosFrecuentes);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);			
		}
		return frecuentesTiempoAireResponseTO;		
	}
	//nuevo referencias frecuentes tiempo aire
	
	@SuppressWarnings("unchecked")
	public PagoServiciosMaxiGasResponseTO setInitialMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = new PagoServiciosMaxiGasResponseTO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {

			MessageElement messageElement = pagoServiciosDAO.setInitialMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialMaxiGasPayment();
			
			messageElement = pagoServiciosDAO.setInitialServiceMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialServiceMaxiGasPayment();
			
			messageElement = pagoServiciosDAO.setStartInitialServiceMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialStartServiceMaxiGasPayment();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);					
			
			Collection<String> cuentas = (Collection<String>) decode.toDeserialize("cuentas", messageElement,null);			
			Map<String, String> mapCuentas = new HashMap<String, String>();					
			for (String llaveValor: cuentas){
				mapCuentas.put(llaveValor, llaveValor);
			}
			
			Collection<String> servicios = (Collection<String>) decode.toDeserialize("servicios", messageElement,null);			
			Map<String, String> mapServicios = new HashMap<String, String>();					
			for (String llaveValor: servicios){
				mapServicios.put(llaveValor, llaveValor);
			}
			
			pagoServiciosMaxiGasResponseTO.setMapCuentas(mapCuentas);
			pagoServiciosMaxiGasResponseTO.setMapServicios(mapServicios);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} 
		return pagoServiciosMaxiGasResponseTO;
	}
	
	public PagoServiciosMaxiGasResponseTO setDataMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = null;
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setDataMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateDataMaxiGasPayment();
			XmlDecoder decode = new XmlDecoder();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosMaxiGasResponseTO");			
			pagoServiciosMaxiGasResponseTO = (PagoServiciosMaxiGasResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			
			String cuentaCargo = pagoServiciosMaxiGasResponseTO.getCuentaCargo();
			if (cuentaCargo.length()>14){
				pagoServiciosMaxiGasResponseTO.setCuentaCargo(cuentaCargo.substring(0,14));
			}
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			pagoServiciosMaxiGasResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return pagoServiciosMaxiGasResponseTO;
	}
	
	public PagoServiciosMaxiGasResponseTO setConfimMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = new PagoServiciosMaxiGasResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setConfimMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateconfimTelmexPayment();
			pagoServiciosDAO.setExecuteWaitMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			pagoServiciosDAO.setExecuteMaxiGasPayment(pagoServiciosMaxiGasRequestTO);
			XmlDecoder decode = new XmlDecoder();

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosMaxiGasResponseTO");			
			pagoServiciosMaxiGasResponseTO = (PagoServiciosMaxiGasResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			
			String cuentaCargo = pagoServiciosMaxiGasResponseTO.getCuentaCargo();
			if (cuentaCargo.length()>14){
				pagoServiciosMaxiGasResponseTO.setCuentaCargo(cuentaCargo.substring(0,14));
			}
		
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return pagoServiciosMaxiGasResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoServicioAztecaWebResponseTO setInitialAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioAztecaWebResponseTO pagoServicioAztecaWebResponseTO = new PagoServicioAztecaWebResponseTO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {

			MessageElement messageElement = pagoServiciosDAO.setInitialAztecaWebPayment(pagoServicioAztecaWebRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialAztecaWebPayment();
			
			messageElement = pagoServiciosDAO.setInitialServiceAztecaWebPayment(pagoServicioAztecaWebRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialServiceAztecaWebPayment();
			
			messageElement = pagoServiciosDAO.setStartInitialServiceAztecaWebPayment(pagoServicioAztecaWebRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateInitialStartServiceAztecaWebPayment();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);					
			
			Collection<String> cuentas = (Collection<String>) decode.toDeserialize("cuentas", messageElement,null);			
			Map<String, String> mapCuentas = new HashMap<String, String>();					
			for (String llaveValor: cuentas){
				mapCuentas.put(llaveValor, llaveValor);
			}
			
			Collection<String> servicios = (Collection<String>) decode.toDeserialize("servicios", messageElement,null);			
			Map<String, String> mapServicios = new HashMap<String, String>();					
			for (String llaveValor: servicios){
				mapServicios.put(llaveValor, llaveValor);
			}
			
			pagoServicioAztecaWebResponseTO.setMapCuentas(mapCuentas);
			pagoServicioAztecaWebResponseTO.setMapServicios(mapServicios);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} 
		return pagoServicioAztecaWebResponseTO;
	}
	
	public PagoServicioAztecaWebResponseTO setDataAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioAztecaWebResponseTO pagoServicioAztecaWebResponseTO = null;
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.setDataAztecaWebPayment(pagoServicioAztecaWebRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateDataAztecaWebPayment();
			XmlDecoder decode = new XmlDecoder();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServicioAztecaWebResponseTO");			
			pagoServicioAztecaWebResponseTO = (PagoServicioAztecaWebResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			
			String cuentaCargo = pagoServicioAztecaWebResponseTO.getCuentaCargo();
			if (cuentaCargo.length()>14){
				pagoServicioAztecaWebResponseTO.setCuentaCargo(cuentaCargo.substring(0,14));
			}
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			pagoServicioAztecaWebResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return pagoServicioAztecaWebResponseTO;
	}
	
	public PagoServicioAztecaWebResponseTO setConfimAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioAztecaWebResponseTO pagoServicioAztecaWebResponseTO = new PagoServicioAztecaWebResponseTO();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
//		String query ="for $numeroOperacion in //elite/ebanking_web_BeanKey return $numeroOperacion";

		try {
			MessageElement messageElement = pagoServiciosDAO.setConfimAztecaWebPayment(pagoServicioAztecaWebRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateconfimTelmexPayment();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			XmlDecoder decode = new XmlDecoder();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServicioAztecaWebResponseTO");			
			pagoServicioAztecaWebResponseTO = (PagoServicioAztecaWebResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			pagoServiciosDAO.setExecuteWaitAztecaWebPayment(pagoServicioAztecaWebRequestTO);
			messageElement= pagoServiciosDAO.setExecuteAztecaWebPayment(pagoServicioAztecaWebRequestTO);		
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ebanking_web_BeanKey", "com.bancoazteca.elite.beans.DatosAdicionalesPagoServiciosTO");
			DatosAdicionalesPagoServiciosTO datosAdicionales = (DatosAdicionalesPagoServiciosTO) decode.toDeserialize("ebanking_web_BeanKey", messageElement, map);
			pagoServicioAztecaWebResponseTO.setFolio(datosAdicionales.getNumeroOperacion());
			pagoServicioAztecaWebResponseTO.setFechaAplicacion(datosAdicionales.getFechaAplicacion());
			
			
			
			String cuentaCargo = pagoServicioAztecaWebResponseTO.getCuentaCargo();
			if (cuentaCargo.length()>14){
				pagoServicioAztecaWebResponseTO.setCuentaCargo(cuentaCargo.substring(0,14));
			}
		
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return pagoServicioAztecaWebResponseTO;
	}
	
	
	public PagoServiciosAvicolaResponseTO setInitialAvicolaPayment(PagoServiciosAvicolaRequestTO requestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {

		PagoServiciosAvicolaResponseTO responseTO = new PagoServiciosAvicolaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuenta in //elite/pagoServicioForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = dao.setInitialAvicolaPayment(requestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateInitialAvicolaPayment();
			messageElement = dao.setInitialServiceAvicolaPayment(requestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateInitialServiceAvicolaPayment();
			messageElement = dao.setStartInitialServiceAvicolaPayment(requestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateStartInitialServiceAvicolaPayment();
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);

			Collection<String> cuentas = (Collection<String>)decode.toDeserialize("cuentas", messageElement,null);
			Iterator<String> iteratorCuentas = cuentas.iterator();
			Map<String, String> mapCuentas = new HashMap<String, String>();
			while (iteratorCuentas.hasNext()) {
				String llaveValor = iteratorCuentas.next();
				mapCuentas.put(llaveValor, llaveValor.substring(0, 14));
			}

			Collection<String> servicios = (Collection<String>)decode.toDeserialize("servicios", messageElement,null);
			Iterator<String> iteratorServicios = servicios.iterator();
			Map<String, String> mapServicios = new HashMap<String, String>();
			while (iteratorServicios.hasNext()) {
				String llaveValor = iteratorServicios.next();
				mapServicios.put(llaveValor, llaveValor);
			}
			responseTO.setMapServicios(mapServicios);
			responseTO.setMapCuentas(mapCuentas);
		
		
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
		

		
		
	}

	public PagoServiciosAvicolaResponseTO setDataAvicolaPayment(PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		
		PagoServiciosAvicolaResponseTO responseTO = new PagoServiciosAvicolaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try {
			MessageElement messageElement = dao.setFormDataAvicolaPayment(pagoServiciosAvicolaRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateFormDataAvicolaPayment();
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosAvicolaResponseTO");
			responseTO = (PagoServiciosAvicolaResponseTO)decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return responseTO;
		
	}

	public PagoServiciosAvicolaResponseTO setConfirmAvicolaPayment(PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		
		PagoServiciosAvicolaResponseTO responseTO = new PagoServiciosAvicolaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		
		try {
			
			MessageElement messageElement = dao.setTokenAvicolaPayment(pagoServiciosAvicolaRequestTO);
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosAvicolaResponseTO");
			responseTO = (PagoServiciosAvicolaResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			messageElement = dao.setExecuteAvicolaPayment(pagoServiciosAvicolaRequestTO);
			messageElement = dao.setExecuteWaitAvicolaPayment(pagoServiciosAvicolaRequestTO);
			String numeroOperacion = (String) decode.toDeserialize("numeroOperacion", messageElement,null);	
			responseTO.setFolioAclaracion(numeroOperacion);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} 
		return responseTO;
	}
	
	
		public FrecuentesResponseTO setEliminarFrecuentesValidacionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
			FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
			PagoServiciosDAO dao = new PagoServiciosDAO();
			String query = "for $serviciosFrecuentes in //elite/ServiciosCtasFrecuentesForm return <cuentasFrecuentes>{$serviciosFrecuentes}</cuentasFrecuentes>";
			try{
				
				MessageElement messageElement = dao.setAgregarFrecuenteParametrizado(frecuentesRequestTO);
				PagoServiciosRule rule = new PagoServiciosRule(messageElement);
				rule.validatePreEliminarFrecuente();
				
			}catch(DAOException e){
				e.printStackTrace();
				throw new PagoServiciosException(e);
			}
			return frecuentesResponseTO;
		}
		
		public FrecuentesResponseTO setEliminarFrecuentesEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
			FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
			PagoServiciosDAO dao = new PagoServiciosDAO();
			String query = "for $serviciosFrecuentes in //elite/ServiciosCtasFrecuentesForm return <cuentasFrecuentes>{$serviciosFrecuentes}</cuentasFrecuentes>";
			try{

				MessageElement messageElement=dao.setEliminarFrecuentesEjecucionParametrizado(frecuentesRequestTO);
				PagoServiciosRule rule = new PagoServiciosRule(messageElement);
				rule.validateEliminarFrecuente();
				
				messageElement = dao.setInitialFrecuentes(frecuentesRequestTO);
				rule = new PagoServiciosRule(messageElement);
				rule.validateSetInitialFrecuentes();
				
				messageElement = dao.setStartInitialFrecuentes(frecuentesRequestTO);
				rule = new PagoServiciosRule(messageElement);
				rule.validateSetStartInitialFrecuentes();
				
				XmlDecoder decoder = new XmlDecoder();
				Element element = XMLFinder.getElement(messageElement.toString(), query);
				MessageElement messageElement2 = new MessageElement(element);
				HashMap<String, String> mappedBeans = new HashMap<String, String>();
				mappedBeans.put("ServiciosCtasFrecuentesVO", "com.bancoazteca.elite.beans.FrecuentesResponseTO");
				Collection<FrecuentesResponseTO> frecuentes = (Collection<FrecuentesResponseTO>)decoder.toDeserialize("serviciosFrecuentes", messageElement2, mappedBeans);
				if(frecuentes == null){
					frecuentes = new ArrayList<FrecuentesResponseTO>();
				}
				frecuentesResponseTO.setFrecuentes(frecuentes);
				
			}catch(DAOException e){
				e.printStackTrace();
				throw new PagoServiciosException(e);
			}catch(IOException e){
				e.printStackTrace();
				throw new PagoServiciosException(e);
			}
			return frecuentesResponseTO;
		}
		
	public FrecuentesResponseTO setModificarFrecuentesParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try{
			
			MessageElement messageElement = dao.setModificarFrecuentesParametrizado(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validatePreModificarFrecuente();
		
		}catch(DAOException e){
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setModificarFrecuentesValidacionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try{
			MessageElement messageElement=dao.setModificarFrecuentesValidacionParametrizado(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateModificarFrecuente();
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			frecuentesResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setModificarFrecuentesEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = new FrecuentesResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try{
			
			//revisar final modificar
			
			MessageElement messageElement=dao.setModificarFrecuentesEjecucionParametrizado(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateModificarFrecuenteInitEjecucion();
			
			messageElement = dao.setInitialFrecuentes(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateSetInitialFrecuentes();
			
			messageElement = dao.setStartInitialFrecuentes(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateSetStartInitialFrecuentes();
			
		}catch(DAOException e){
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}

	public DonativosResponseTO setInitialDonativo(DonativosRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		DonativosResponseTO responseTO = new DonativosResponseTO();
		String query = "for $cuenta in //elite/jugetonForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			pagoServiciosDAO.setInicioDonativo(donativosRequestTO);
			MessageElement messageElement = pagoServiciosDAO.setDonativo(donativosRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSetInicioDonativo();
			
			messageElement = pagoServiciosDAO.setDonativoPre(donativosRequestTO);
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSetDonativoPre();
			
			HashMap<String, String> mappedBeans ;
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("jugetonForm", "com.bancoazteca.elite.beans.DonativosResponseTO");
			String nombreCompleto= (String) decode.toDeserialize("nombreCompleto", messageElement, mappedBeans);
			responseTO = (DonativosResponseTO) decode.toDeserialize("jugetonForm", messageElement, mappedBeans);
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			log.info("messagege Element: "+ messageElement);
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			Collection<CuentaLoTO> cuentas = (Collection<CuentaLoTO>) decode.toDeserialize("cuentas", messageElement, mappedBeans);
			responseTO.setCuentas(cuentas);
			responseTO.setNombreCompleto(nombreCompleto);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} 
		catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} 
		return responseTO;
	}
	
	public DonativosResponseTO setDataDonativo(DonativosRequestTO requestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		DonativosResponseTO responseTO = new DonativosResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		DonativosResponseTO DonativosResponseTO=new DonativosResponseTO();
		String query = "for $cuenta in //elite/jugetonForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = dao.setDonativoConfirmar(requestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSetDonativoConfirmar();
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			//DonativosResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		
			
			HashMap<String, String> mappedBeans ;
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("jugetonForm", "com.bancoazteca.elite.beans.DonativosResponseTO");
			responseTO = (DonativosResponseTO) decode.toDeserialize("jugetonForm", messageElement, mappedBeans);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlDecodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return responseTO;
		
	}
	
	
	public DonativosResponseTO setConfirmDonativo(DonativosRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		DonativosResponseTO responseTO = new DonativosResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement;
			
			messageElement = dao.setDonativoEjecutar(donativosRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSetDonativoEjecutar();
			
			messageElement = dao.setDonativoEjecutarCmd(donativosRequestTO);
			
			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSetDonativoEjecutarCmd();
	
			HashMap<String, String> mappedBeans ;
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("JugetonForm", "com.bancoazteca.elite.beans.DonativosResponseTO");
			responseTO = (DonativosResponseTO) decode.toDeserialize("JugetonForm", messageElement, mappedBeans);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}
	
	public BeneficiarioDineroExpressResponseTO setDataEnvioDineroExpressAltaFrecuente( BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException {
		BeneficiarioDineroExpressResponseTO responseTO = new BeneficiarioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();

		try {
			MessageElement messageElement;
			messageElement = dao.setDataEnvioDineroExpressAltaFrecuente(frecuenteRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(
					messageElement);

			pagoServiciosRule.validateSetEnvioDineroExpressAltaFrecuenteConfirmar();

			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return responseTO;
	}

	public BeneficiarioDineroExpressResponseTO setEnvioDineroExpressAltaFrecuenteEjecutar( BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		BeneficiarioDineroExpressResponseTO responseTO = new BeneficiarioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();

		try {
			MessageElement messageElement;
			messageElement = dao.setEnvioDineroExpressAltaFrecuenteEjecutar(frecuenteRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSetEnvioDineroExpresAltaFrecuenteWait();

			messageElement = dao.setEnvioDineroExpressAltaFrecuenteEjecutarCmd(frecuenteRequestTO);

			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSetEnvioDineroExpressBuscaFrecuenteEjecutar();

			responseTO = setEnvioDineroExpressConsultarFrecuentesEjecutar(frecuenteRequestTO);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	public BeneficiarioDineroExpressResponseTO setEnvioDineroExpressConsultarFrecuentesEjecutar( BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		BeneficiarioDineroExpressResponseTO responseTO = new BeneficiarioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();

		try {
			MessageElement messageElement;
			messageElement = dao.setEnvioDineroExpressConsultarFrecuentesEjecutar(frecuenteRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule( messageElement);
			pagoServiciosRule.validateSetEnvioDineroExpressAltaFrecuenteEjecutar();
			HashMap<String, String> mappedBeans;
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("beneficiariosDineroExpressForm", BeneficiarioDineroExpressResponseTO.class.getCanonicalName());
			mappedBeans.put("BeneficiarioTO", BeneficiarioDineroExpresTO.class.getCanonicalName());

			responseTO = (BeneficiarioDineroExpressResponseTO) decode.toDeserialize("beneficiariosDineroExpressForm",messageElement, mappedBeans);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}

		return responseTO;
	}

	@SuppressWarnings("unchecked")
	public EnvioDineroExpressResponseTO setEnvioDineroExpressInicio(EnvioDineroExpressRequestTO dineroExpressRequestTO)throws PagoServiciosException, SessionExpiredException,EliteDataException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XMLDecode decode = new XMLDecode();
		try {
			MessageElement messageElement;
			boolean contratoAceptar=false;
			messageElement = dao.setEnvioDineroExpressInicio(dineroExpressRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			try {
				pagoServiciosRule.validateContratoEnvioDineroExpressSolicitud();
			} catch (EliteDataException dataException) {
				if (dineroExpressRequestTO.getAceptarContrato() == null || !dineroExpressRequestTO.getAceptarContrato().trim().equalsIgnoreCase("SI")) {					
					throw dataException;
				}
				contratoAceptar=true;
			}

			messageElement = dao.setEnvioDineroExpressSolicitud(dineroExpressRequestTO,contratoAceptar);

			pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateEnvioDineroExpressSolicitud();

			Collection<CuentaVisibleDineroExpressTO> cuentasDineroExpress = decode.buildCollectionBeans(CuentaVisibleDineroExpressTO.class,messageElement, "LabelValueBean");

			Collection<LocalidadDEXTO> PaisesDineroExpress = decode.buildCollectionBeans(LocalidadDEXTO.class, messageElement,"LocalidadBean");

			responseTO.setCuentaVisibleDineroExpress(cuentasDineroExpress);
			responseTO.setPaisesDEX(PaisesDineroExpress);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	public EnvioDineroExpressResponseTO setEnvioDineroExpressValidacion( EnvioDineroExpressRequestTO dineroExpressRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement;

			messageElement = dao.setEnvioDineroExpressValidacion(dineroExpressRequestTO);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule( messageElement);
			pagoServiciosRule.validateEnvioDineroExpressValidacion();

			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			String query = "for $dineroExpressEnvioForm in //elite return $dineroExpressEnvioForm";
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			System.out.println("onlyForm: " + messageElement.toString());

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("dineroExpressEnvioForm", EnvioDineroExpressResponseTO.class.getCanonicalName());
			responseTO = (EnvioDineroExpressResponseTO) decode.toDeserialize("dineroExpressEnvioForm", messageElement, mappedBeans);

			responseTO.setLlavePublica(dispositivoHuellaTO.getLlavePublica());
			responseTO.setLongitudHuella(dispositivoHuellaTO.getLongitudHuella());
			

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	public EnvioDineroExpressResponseTO setEnvioDineroExpressEjecucion( EnvioDineroExpressRequestTO dineroExpressRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement;

			messageElement = dao.setEnvioDineroExpressEjecucion(dineroExpressRequestTO);

			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateEnvioDineroExpressEjecucion();

			String query = "for $dineroExpressEnvioForm in //elite return $dineroExpressEnvioForm";
			Element element = XMLFinder.getElement(messageElement.toString(),query);
			messageElement = new MessageElement(element);
			System.out.println("onlyForm: " + messageElement.toString());

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("dineroExpressEnvioForm",EnvioDineroExpressResponseTO.class.getCanonicalName());
			responseTO = (EnvioDineroExpressResponseTO) decode.toDeserialize("dineroExpressEnvioForm", messageElement, mappedBeans);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	public EnvioDineroExpressResponseTO setEnvioDineroExpressEstados( EnvioDineroExpressRequestTO dineroExpressRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XMLDecode decoder = new XMLDecode();
		try {

			MessageElement messageElement = dao.setEnvioDineroExpressEstados(dineroExpressRequestTO);

			String query = "for $LocalidadBean in //elite/DineroExpressEnvioAction.ESTADO return $LocalidadBean";

			try {

				Element element = XMLFinder.getElement(messageElement.toString(), query);
				messageElement = new MessageElement(element);

			} catch (Throwable parseException) {
				throw new EliteDataException("Pais sin envios dirigidos.",null, 8);
			}

			Collection<LocalidadDEXTO> estadosDineroExpress = decoder.buildCollectionBeans(LocalidadDEXTO.class, messageElement,"LocalidadBean");

			responseTO.setEstadosDEX(estadosDineroExpress);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	@SuppressWarnings("unchecked")
	public EnvioDineroExpressResponseTO setEnvioDineroExpressCiudades(EnvioDineroExpressRequestTO dineroExpressRequestTO)throws PagoServiciosException, SessionExpiredException,EliteDataException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XMLDecode decoder = new XMLDecode();
		try {

			MessageElement messageElement = dao.setEnvioDineroExpressCiudades(dineroExpressRequestTO);

			String query = "for $LocalidadBean in //elite/DineroExpressEnvioAction.CIUDAD return $LocalidadBean";

			try {

				Element element = XMLFinder.getElement(messageElement.toString(), query);
				messageElement = new MessageElement(element);

			} catch (Throwable parseException) {
				throw new EliteDataException("Pais sin envios dirigidos.",null, 8);
			}

			Collection<LocalidadDEXTO> ciudadesDineroExpress = decoder.buildCollectionBeans(LocalidadDEXTO.class, messageElement,"LocalidadBean");
			responseTO.setCiudadesDEX(ciudadesDineroExpress);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	@SuppressWarnings("unchecked")
	public EnvioDineroExpressResponseTO setEnvioDineroExpressAgentes(EnvioDineroExpressRequestTO dineroExpressRequestTO)throws PagoServiciosException, SessionExpiredException,EliteDataException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XMLDecode decoder = new XMLDecode();
		try {

			MessageElement messageElement = dao.setEnvioDineroExpressAgente(dineroExpressRequestTO);

			String query = "for $AgenteTO in //elite/DineroExpressEnvioAction.AGENTE return $AgenteTO";

			try {

				Element element = XMLFinder.getElement(messageElement.toString(), query);
				messageElement = new MessageElement(element);

			} catch (Throwable parseException) {
				throw new EliteDataException("Pais sin envios dirigidos.",null, 8);
			}

			Collection<AgenteDEXTO> ciudadesDineroExpress = decoder.buildCollectionBeans(AgenteDEXTO.class, messageElement,"AgenteTO");
			responseTO.setAgentesDEX(ciudadesDineroExpress);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	@SuppressWarnings("unchecked")
	public EnvioDineroExpressResponseTO setEnvioDineroExpressSucursales(EnvioDineroExpressRequestTO dineroExpressRequestTO)throws PagoServiciosException, SessionExpiredException,EliteDataException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XMLDecode decoder = new XMLDecode();
		try {

			MessageElement messageElement = dao.setEnvioDineroExpressSucursal(dineroExpressRequestTO);

			String query = "for $SucursalTO in //elite/DineroExpressEnvioAction.SUCURSAL return $SucursalTO";

			try {

				Element element = XMLFinder.getElement(messageElement.toString(), query);
				messageElement = new MessageElement(element);

			} catch (Throwable parseException) {
				throw new EliteDataException("Pais sin envios dirigidos.",null, 8);
			}

			Collection<LocalidadDEXTO> ciudadesDineroExpress = decoder.buildCollectionBeans(LocalidadDEXTO.class, messageElement,"SucursalTO");
			responseTO.setSucursalesDEX(ciudadesDineroExpress);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}
		return responseTO;
	}

	public EnvioDineroExpressResponseTO enviaCorreoConfirmacionDineroExpress(EnvioDineroExpressRequestTO requestTO)throws SessionExpiredException, EliteDataException,PagoServiciosException {
		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();

		try {

			MessageElement messageElement = dao.enviaCorreoConfirmacionDineroExpress(requestTO);

			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateEnvioCorreoDineroExpressEjecucion();

		} catch (EliteDataException dataException) {

			if (dataException.getLevel() == EliteRules.LEVEL_ACTION_ERRORS) {

				Map<String, String> map = (Map<String, String>) dataException.getErrorData();
				String email = map.get("email");
				System.out.println("Mensaje envio correo: " + email);
				// Su mensaje se envió correctamente.
				if (email==null || !email.equalsIgnoreCase("Su mensaje se envió correctamente.")) {
					throw dataException;
				} else {
					responseTO.setMensajeCorreo(email);
				}
			}
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new PagoServiciosException();
		}

		return responseTO;

	}

	public EnvioDineroExpressResponseTO setEnvioDineroExpressCalculoComision(
			EnvioDineroExpressRequestTO dineroExpressRequestTO)
			throws PagoServiciosException, SessionExpiredException,
			EliteDataException {

		EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();

		try {
			responseTO = dao
					.setEnvioDineroExpressCalculoComision(dineroExpressRequestTO);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		}

		return responseTO;
	}
	
	public PagoServiciosParametrizableResponseTO setInitialParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosParametrizableResponseTO responseTO = new PagoServiciosParametrizableResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		
		String query = "for $cuenta in //elite/pagoServicioFormParam return $cuenta";
		XmlDecoder decode = new XmlDecoder();
//		String query = "for $cuenta in //elite/pagoServicioFormParam/servicios return <rootelement>{$cuenta}</rootelement>";
//		XMLDecode decode = new XMLDecode();
		try {
			
			
			
			//Invocacion para validar que se tengan permisos para realizar pago de servicios.
			MessageElement message = dao.validaNivelSeguridad(parametrizableRequestTO.getUser());
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(message);
			pagoServiciosRule.validaNivelSeguridad();
			
			
			MessageElement messageElement = dao.setInitialParametrizablePayment(parametrizableRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateInitialParametrizablePayment();
			messageElement = dao.setInitialServiceParametrizablePayment(parametrizableRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateInitialServiceParametrizablePayment();
			messageElement = dao.setStartInitialServiceParametrizablePayment(parametrizableRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateStartInitialServiceParametrizablePayment();
			

			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			
			Collection<String> cuentas = (Collection<String>)decode.toDeserialize("cuentas", messageElement,null);
			Iterator<String> iteratorCuentas = cuentas.iterator();
			Map<String, String> mapCuentas = new HashMap<String, String>();
			while (iteratorCuentas.hasNext()) {
				String llaveValor = iteratorCuentas.next();
				mapCuentas.put(llaveValor, llaveValor);
			}
			
			String registroTitle = (String) decode.toDeserialize("registroTitle", messageElement,null);
			responseTO.setReferenciaFrecuente(registroTitle);
			
			Collection<String> servicios = (Collection<String>)decode.toDeserialize("servicios", messageElement,null);
			Iterator<String> iteratorServicios = servicios.iterator();
			Map<String, String> mapServicios = new HashMap<String, String>();
			while (iteratorServicios.hasNext()) {
				String llaveValor = iteratorServicios.next();
				mapServicios.put(llaveValor, llaveValor);
			}
			
			responseTO.setMapCuentas(mapCuentas);
			responseTO.setMapServicios(mapServicios);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
//		} catch( XmlDecodeException e ){
//			throw new PagoServiciosException( e );
		}
		return responseTO;
	}
	public PagoServiciosParametrizableResponseTO setDataParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosParametrizableResponseTO responseTO = new PagoServiciosParametrizableResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		try {
			MessageElement messageElement = dao.setFormDataParametrizablePayment(parametrizableRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateFormDataParametrizablePayment();

			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosParametrizableResponseTO");
			responseTO = (PagoServiciosParametrizableResponseTO)decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			String cuentaCargoFormateada = (String) responseTO.getCuentaCargo().subSequence(0, 14);
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			responseTO.setCuentaCargoFormateada(cuentaCargoFormateada);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return responseTO;
	}
	public PagoServiciosParametrizableResponseTO setConfirmParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosParametrizableResponseTO responseTO = new PagoServiciosParametrizableResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();

		try {
			MessageElement messageElement = dao.setTokenParametrizablePayment(parametrizableRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateTokenParametrizablePayment();
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("pagoServicioForm", "com.bancoazteca.elite.beans.PagoServiciosParametrizableResponseTO");
			responseTO = (PagoServiciosParametrizableResponseTO) decode.toDeserialize("pagoServicioForm", messageElement, mappedBeans);
			String cuentaCargoFormateada = (String) responseTO.getCuentaCargo().subSequence(0, 14);
			responseTO.setCuentaCargoFormateada(cuentaCargoFormateada);
			
			messageElement = dao.setExecuteParametrizablePayment(parametrizableRequestTO);
			messageElement = dao.setExecuteWaitParametrizablePayment(parametrizableRequestTO);
			
			rule = new PagoServiciosRule(messageElement);
			rule.validateConfirmParametrizablePayment();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ebanking_web_BeanKey", "com.bancoazteca.elite.beans.DatosAdicionalesPagoServiciosTO");
			DatosAdicionalesPagoServiciosTO datosAdicionales = (DatosAdicionalesPagoServiciosTO) decode.toDeserialize("ebanking_web_BeanKey", messageElement, map);
			responseTO.setNumeroOperacion( datosAdicionales.getNumeroOperacion() );
			responseTO.setFechaAplicacion( datosAdicionales.getFechaAplicacion() );
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} 
		return responseTO;

	}
	
	public PagoServiciosToditoCardResponseTO getInitialToditoCard(PagoServiciosToditoCardRequestTO toditoCardRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosToditoCardResponseTO responseTO = new  PagoServiciosToditoCardResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		XmlDecoder decode = new XmlDecoder();
		
		try {
			MessageElement messageElement = dao.getInitialToditoCardDao(toditoCardRequestTO);
			
//			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
//			rule.validateConfirmParametrizablePayment();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaVO");
			Collection<CuentaVO> cuentas = (Collection<CuentaVO>) decode.toDeserialize("cuentas", messageElement,mappedBeans);			
			responseTO.setCuentas(cuentas);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException();
		} 
		
		return responseTO;
	}
	
	
//Pago de Tarjeta Azteca
	

	@SuppressWarnings("unchecked")
	public PagoTarjetaAztecaResponseTO solicitudPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = new PagoTarjetaAztecaResponseTO();
		String queryCuentasTemp = "for $cuentas in //elite/ebanking_web_ClienteVO/cuentas  return $cuentas";
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement= pagoServiciosDAO.solicitudPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateSolicitudPagoTarjetaAzteca();
			XMLDecode decode = new XMLDecode();
			Element element = XMLFinder.getElement(messageElement.toString(), queryCuentasTemp);
			messageElement = new MessageElement(element);					
			Collection<CuentaVO> cuentas = (Collection<CuentaVO>) decode.buildCollectionBeans(CuentaVO.class, messageElement, "CuentaVO");
			pagoTarjetaAztecaResponseTO.setCuentas(cuentas);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlDecodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pagoTarjetaAztecaResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoTarjetaAztecaResponseTO validacionPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = new PagoTarjetaAztecaResponseTO();
		
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.validacionPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validacionTarjetaAzteca();
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			
			XMLDecode decode = new XMLDecode();
			Collection<TarjetaCreditoAztecaTO> datosTarjeta = (Collection<TarjetaCreditoAztecaTO>) decode.buildCollectionBeans(TarjetaCreditoAztecaTO.class, messageElement,"tarjetaCredito");
			pagoTarjetaAztecaResponseTO.setTarjetasCredito(datosTarjeta);
			pagoTarjetaAztecaResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}

		return pagoTarjetaAztecaResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public PagoTarjetaAztecaResponseTO confirmacionPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = new PagoTarjetaAztecaResponseTO();
		String queryCuentasTemp = "for $cuentas in //elite/ebanking_web_ClienteVO/cuentas  return $cuentas";
		XmlDecoder decoder = new XmlDecoder();
		XMLDecode decode = new XMLDecode();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.confirmacionPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validacionConfirmacionPagoTarjetaAzteca();
		

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("PagoTarjetasForm", "com.bancoazteca.elite.beans.ConfirmacionPagoTarjetaOtrosBancosTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaTransaccionesTO");
			ConfirmacionPagoTarjetaOtrosBancosTO confirmacionPagoTarjetaAzteca = (ConfirmacionPagoTarjetaOtrosBancosTO)decoder.toDeserialize("PagoTarjetasForm", messageElement, mappedBeans);
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("TarjetaCreditoVO", "com.bancoazteca.elite.beans.TarjetaCreditoAztecaTO");
			TarjetaCreditoAztecaTO  tarjetaCredito = (TarjetaCreditoAztecaTO)decoder.toDeserialize("TarjetaCreditoVO", messageElement, mappedBeans);
			
			
			//Obtenemos el concepto del movimiento
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("MovimientoVO", "com.bancoazteca.elite.beans.MovimientoTarjetaTO");
			MovimientoTarjetaTO  movimiento= (MovimientoTarjetaTO)decoder.toDeserialize("MovimientoVO", messageElement, mappedBeans);
			
			
			
			//Obtenemos el disponible de la cuenta
				
			Element element = XMLFinder.getElement(messageElement.toString(), queryCuentasTemp);
			messageElement = new MessageElement(element);
			Collection<CuentaVO> cuentas = (Collection<CuentaVO>) decode.buildCollectionBeans(CuentaVO.class, messageElement, "CuentaVO");
	
			
			
			pagoTarjetaAztecaResponseTO.setCuentas(cuentas);
			pagoTarjetaAztecaResponseTO.setConfirmacionPagoTarjetaOtrosBancosTO(confirmacionPagoTarjetaAzteca);
			pagoTarjetaAztecaResponseTO.setConfirmacionTarjetaCredito(tarjetaCredito);
			pagoTarjetaAztecaResponseTO.setMovimientoTarjetaTO(movimiento);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlDecodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pagoTarjetaAztecaResponseTO;
	}

	
	//Tarjetas Azteca Frecuentes (Consulta)
	
	
	
	public FrecuentesAztecaResponseTO consultaCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
	
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement= pagoServiciosDAO.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateFrecuentesPagoTarjetaAzteca();
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			
			messageElement = new MessageElement(element);					
			log.info(messageElement.toString());
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes==null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}		
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
	
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frecuentesResponseTO;
	}
	
	
	//Alta de cuentas frecuentes
	
	public FrecuentesAztecaResponseTO AgregaCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		 String query = "for $cuentasFrecuentes in //elite/cuentasFrecuentesForm return <elite>{$cuentasFrecuentes}</elite>";
//		 String query = "for $cuentasFrecuentes in //elite/frequentForm return <elite>{$cuentasFrecuentes}</elite>";
			
		 try{
			MessageElement messageElement = dao.AgregaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			XmlDecoder decoder = new XmlDecoder();
			rule.ValidateAgregaCuentasFrecuentesTarjetasAzteca();
			messageElement = dao.EjecutarAgregaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateEjecutarConsultaCuentasFrecuentesTarjetasAzteca();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			//Collection<Map> tarjetasFrecuentes = (Collection<Map>)decode.toDeserialize("frequentAccounts", messageElement, null);
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("cuentasFrecuentesForm", "com.bancoazteca.elite.beans.AltaTarjetaFrecuenteTO");
			AltaTarjetaFrecuenteTO tarjetasAlta = (AltaTarjetaFrecuenteTO)decoder.toDeserialize("cuentasFrecuentesForm", messageElement, mappedBeans);
			
//			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
//			if(tarjetasFrecuentes==null){
//				tarjetasFrecuentes = new ArrayList<Map>();
//			}		
//			

			frecuentesResponseTO.setAltaTarjetaFrecuenteTO(tarjetasAlta);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}
	
	
	//Edición de una cuenta frecuente para pago de Tarjeta Azteca
	
	public FrecuentesAztecaResponseTO iniciarEditarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		Map<String, String> params = new HashMap<String, String>();
		try{
			if(frecuentesRequestTO.getMethod().equals("editar"))
				params.put("method", "preupdateAccount");
			frecuentesRequestTO.setParametros(params);
			
			MessageElement messageElement = dao.iniciarEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			if(frecuentesRequestTO.getMethod().equals("editar"))
				rule.validateIniciarEditarCuentasFrecuentesTarjetasAzteca();
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes==null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}

	public FrecuentesAztecaResponseTO ejecutarEditarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		Map<String, String> params = new HashMap<String, String>();
		
		try{
			if(frecuentesRequestTO.getMethod().equals("editar")){
				params.put("method", "verificaModificada");
			frecuentesRequestTO.setParametros(params);
			}
			//Prepara para ejecutar
			
			MessageElement messageElement = dao.prepararEjecutarEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validatePrepararEditarCuentasFrecuentesTarjetasAzteca();
			
			params.clear();
			if(frecuentesRequestTO.getMethod().equals("editar")){
				params.put("method", "updateAccount");
			}
			frecuentesRequestTO.setParametros(params);
			
			//Ejecuta la tarjeta frecuente
			
			messageElement = dao.ejecutarEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateEjecutarEditarCuentasFrecuentesTarjetasAzteca();
			
			//Seria el paso extra
			messageElement = dao.ejecutarExtraEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateEjecutarExtraEditarCuentasFrecuentesTarjetasAzteca();
			
			
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes == null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		
		return frecuentesResponseTO;
	}
	
	
	
	//Eliminacion de tarjetas frecuentes
	

	
	public FrecuentesAztecaResponseTO iniciarEliminarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		Map<String, String> params = new HashMap<String, String>();
		try{
			params.put("method", "deleteFrequentAccountPre");
			frecuentesRequestTO.setParametros(params);
			
			MessageElement messageElement = dao.iniciarEliminarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validateEliminarCuentasFrecuentesTarjetasOtrosBancos();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes==null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
			
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		return frecuentesResponseTO;
	}

	public FrecuentesAztecaResponseTO ejecutarEliminarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		Map<String, String> params = new HashMap<String, String>();
		
		try{
			params.put("method", "deleteFrequentAccount");
				params.put("bank", frecuentesRequestTO.getBanco());
				params.put("bank", frecuentesRequestTO.getBanco());
				params.put("bank", frecuentesRequestTO.getBanco());
				params.put("tipoTDC", frecuentesRequestTO.getTipoTarjeta());
			
			frecuentesRequestTO.setParametros(params);
			
			//Prepara para ejecutar
			
			MessageElement messageElement = dao.prepararEjecutarEliminarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validatePrepararEliminarCuentasFrecuentesTarjetasAzteca();
			
			params.clear();
			params.put("method", "deleteFrequentAccount");
			
			frecuentesRequestTO.setParametros(params);
			
			//Elimina la tarjeta frecuente
			
			messageElement = dao.ejecutarEliminarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
			rule = new PagoServiciosRule(messageElement);
			rule.validateEjecutarEliminarCuentasFrecuentesTarjetasAzteca();
			
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes == null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
		}catch(DAOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}catch(IOException e){
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		
		return frecuentesResponseTO;
	}
	
	
	
	//Envio de emal
	
	@SuppressWarnings("unchecked")
	public PagoTarjetaAztecaResponseTO envioMail(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = new PagoTarjetaAztecaResponseTO();
		String queryEnvioMail = "for $cliente in //elite/ebanking_web_DataKey return $cliente";
		String queryMensaje = "for $valor in //elite/mensaje return <elite>{$valor}</elite>";
		XMLDecode decode = new XMLDecode();
		XmlDecoder decoder = new XmlDecoder();
		String mensaje="";
		HashMap<String, String> mappedBeans = new HashMap<String, String>();
		PagoServiciosDAO pagoServiciosDAO = new PagoServiciosDAO();
		try {
			MessageElement messageElement = pagoServiciosDAO.envioMail(pagoTarjetaAztecaRequestTO);
			PagoServiciosRule pagoServiciosRule = new PagoServiciosRule(messageElement);
			pagoServiciosRule.validateEjecutarEnviaMail();
			
			
			/*Obtenemos el mensaje del correo*/

			Element elementValorMensaje = XMLFinder.getElement(messageElement.toString(), queryMensaje);
			MessageElement messageValorMensaje= new MessageElement(elementValorMensaje);
			mensaje = decode.getString(messageValorMensaje, "mensaje");
			
		
			Element elementDatosCliente = XMLFinder.getElement(messageElement.toString(), queryEnvioMail);
			MessageElement messageElementCliente = new MessageElement(elementDatosCliente);
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("PagoTarjetasForm", "com.bancoazteca.elite.beans.ConfirmacionPagoTarjetaOtrosBancosTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaTransaccionesTO");
			ConfirmacionPagoTarjetaOtrosBancosTO confirmacionEnvioMail = (ConfirmacionPagoTarjetaOtrosBancosTO)decoder.toDeserialize("PagoTarjetasForm", messageElementCliente, mappedBeans);
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("TarjetaCreditoVO", "com.bancoazteca.elite.beans.TarjetaCreditoAztecaTO");
			TarjetaCreditoAztecaTO  tarjetaCredito = (TarjetaCreditoAztecaTO)decoder.toDeserialize("TarjetaCreditoVO", messageElementCliente, mappedBeans);
										
			
			//Obtenemos el concepto del movimiento
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("MovimientoVO", "com.bancoazteca.elite.beans.MovimientoTarjetaTO");
			MovimientoTarjetaTO  movimiento= (MovimientoTarjetaTO)decoder.toDeserialize("MovimientoVO", messageElementCliente, mappedBeans);
			
		
			pagoTarjetaAztecaResponseTO.setMensaje(mensaje);
			pagoTarjetaAztecaResponseTO.setConfirmacionPagoTarjetaOtrosBancosTO(confirmacionEnvioMail);
			pagoTarjetaAztecaResponseTO.setConfirmacionTarjetaCredito(tarjetaCredito);
			pagoTarjetaAztecaResponseTO.setMovimientoTarjetaTO(movimiento);
			
			
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}

		return pagoTarjetaAztecaResponseTO;
	}

	
	//Validacion de Alta y Edicion de tarjetas frecuentes
	
	
	
	public FrecuentesAztecaResponseTO validarAltaCuentasFrecuentes(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		 String query = "for $cuentasFrecuentes in //elite/cuentasFrecuentesForm return <elite>{$cuentasFrecuentes}</elite>";
//		 String query = "for $cuentasFrecuentes in //elite/frequentForm return <elite>{$cuentasFrecuentes}</elite>";
			
		 try{
			MessageElement messageElement = dao.validarAltaCuentasFrecuentes(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			XmlDecoder decoder = new XmlDecoder();
			rule.ValidateAgregaCuentasFrecuentesTarjetasAzteca();
			
			

			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			//Collection<Map> tarjetasFrecuentes = (Collection<Map>)decode.toDeserialize("frequentAccounts", messageElement, null);
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("cuentasFrecuentesForm", "com.bancoazteca.elite.beans.AltaTarjetaFrecuenteTO");
			AltaTarjetaFrecuenteTO tarjetasAlta = (AltaTarjetaFrecuenteTO)decoder.toDeserialize("cuentasFrecuentesForm", messageElement, mappedBeans);
			
//			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
//			if(tarjetasFrecuentes==null){
//				tarjetasFrecuentes = new ArrayList<Map>();
//			}		
//			

			frecuentesResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			frecuentesResponseTO.setAltaTarjetaFrecuenteTO(tarjetasAlta);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new PagoServiciosException(e);
			} catch (XmlDecodeException e) {
				e.printStackTrace();
				throw new PagoServiciosException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new PagoServiciosException(e);
			}
			
		return frecuentesResponseTO;
	}
	
	
	
	public FrecuentesAztecaResponseTO validarEdicionCuentasFrecuentes(FrecuentesAztecaRequestTO frecuentesRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = new FrecuentesAztecaResponseTO();
		PagoServiciosDAO dao = new PagoServiciosDAO();
		String query = "for $cuentasFrecuentes in //elite/frequentForm return $cuentasFrecuentes";
		Map<String, String> params = new HashMap<String, String>();
		
		try{
			
		    params.put("method", "verificaModificada");
			frecuentesRequestTO.setParametros(params);
			
			//Prepara para ejecutar
			
			MessageElement messageElement = dao.validarEdicionCuentasFrecuentes(frecuentesRequestTO);
			PagoServiciosRule rule = new PagoServiciosRule(messageElement);
			rule.validatePrepararEditarCuentasFrecuentesTarjetasAzteca();
			
			XmlDecoder decode = new XmlDecoder();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			Collection<Map> tarjetasFrecuentes = (Collection<Map>) decode.toDeserialize("frequentAccounts", messageElement, null);
			if(tarjetasFrecuentes == null){
				tarjetasFrecuentes = new ArrayList<Map>();
			}
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			frecuentesResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);		
			frecuentesResponseTO.setTarjetasFrecuentes(tarjetasFrecuentes);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PagoServiciosException(e);
		}
		
		return frecuentesResponseTO;
	}
	

	
	
	
}
	