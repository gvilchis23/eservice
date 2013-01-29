package com.bancoazteca.elite.ejb.chequera;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import com.bancoazteca.elite.beans.ActivacionChequeraRequestTO;
import com.bancoazteca.elite.beans.ActivacionChequeraResponseTO;
import com.bancoazteca.elite.beans.ChequeTO;
import com.bancoazteca.elite.beans.ChequeraRoboExtravioRequestTO;
import com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO;
import com.bancoazteca.elite.beans.ConnectorDataTO;
import com.bancoazteca.elite.beans.ConsultaChequesRequestTO;
import com.bancoazteca.elite.beans.ConsultaChequesResponseTO;
import com.bancoazteca.elite.beans.ConsultaPresolicitudDaoTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentasChequesTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.LiberacionChequesRequestTO;
import com.bancoazteca.elite.beans.LiberacionChequesResponseTO;
import com.bancoazteca.elite.beans.PresolicitudChequesRequestTO;
import com.bancoazteca.elite.beans.PresolicitudChequesResponseTO;
import com.bancoazteca.elite.beans.RegistroFirmasVO;
import com.bancoazteca.elite.beans.TalonarioTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.DigitalFingerUtil;
import com.bancoazteca.elite.util.Formatter;

/**
 * 
 * @author Mary Carmen De La Cruz
 * 
 */

public class ChequeraSLBean implements SessionBean {

	private static final long serialVersionUID = -7533869742049711232L;
	
	private static final Logger log = Logger.getLogger(ChequeraSLBean.class);

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
	
	public ChequeraRoboExtravioResponseTO getRoboExtravioInicio(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraRoboExtravioResponseTO chequeraRoboExtravioResponseTO = new ChequeraRoboExtravioResponseTO();
		ChequeraDAO chequeraDAO = new ChequeraDAO();
		
		try {
			MessageElement messageElement = chequeraDAO.findRoboExtravioInicio(extravioRequestTO);
			ChequeraRule chequeraRule = new ChequeraRule(messageElement);
			chequeraRule.validaRoboExtravioInicio();
			
			messageElement = chequeraDAO.findExtravioObtencionChequera(extravioRequestTO);
			chequeraRule = new ChequeraRule(messageElement);
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("bloqueoChequesForm", "com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO");
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");
			chequeraRoboExtravioResponseTO = (ChequeraRoboExtravioResponseTO) decode.toDeserialize("bloqueoChequesForm", messageElement, mappedBeans);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ChequeraException(e);
		}

		
		return chequeraRoboExtravioResponseTO;
	}
	public ChequeraRoboExtravioResponseTO getExtravioObtencionChequera(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraRoboExtravioResponseTO chequeraRoboExtravioResponseTO = new ChequeraRoboExtravioResponseTO();
		ChequeraDAO chequeraDAO = new ChequeraDAO();
				
		try {
			MessageElement messageElement = chequeraDAO.findExtravioObtencionChequera(extravioRequestTO);
			ChequeraRule chequeraRule = new ChequeraRule(messageElement);
			chequeraRule.validaExtravioObtencionChequera();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("bloqueoChequesForm", "com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO");
			mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioTO");
			/*mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");*/
			chequeraRoboExtravioResponseTO = (ChequeraRoboExtravioResponseTO) decode.toDeserialize("bloqueoChequesForm", messageElement, mappedBeans);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ChequeraException(e);
		}

		
		return chequeraRoboExtravioResponseTO;
	}
	public ChequeraRoboExtravioResponseTO getExtravioTipoOperacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraRoboExtravioResponseTO chequeraRoboExtravioResponseTO = new ChequeraRoboExtravioResponseTO();
		ChequeraDAO chequeraDAO = new ChequeraDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = chequeraDAO.findExtravioObtencionMotivoReporte(extravioRequestTO);
			ChequeraRule chequeraRule = new ChequeraRule(messageElement);
			chequeraRule.validaExtravioTipoOperacion();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			
			mappedBeans.put("bloqueoChequesForm", "com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO");
			/*mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioTO");
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");*/
			chequeraRoboExtravioResponseTO = (ChequeraRoboExtravioResponseTO) decode.toDeserialize("bloqueoChequesForm", messageElement, mappedBeans);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ChequeraException(e);
		}

		
		return chequeraRoboExtravioResponseTO;
	}
	
	public ChequeraRoboExtravioResponseTO getExtravioValidaInformacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraRoboExtravioResponseTO chequeraRoboExtravioResponseTO = new ChequeraRoboExtravioResponseTO();
		ChequeraDAO chequeraDAO = new ChequeraDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = chequeraDAO.findExtravioValidaInformacion(extravioRequestTO);
			ChequeraRule chequeraRule = new ChequeraRule(messageElement);
			chequeraRule.validaExtravioValidaInformacion();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			
			mappedBeans.put("bloqueoChequesForm", "com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO");
			mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioTO");
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");
			chequeraRoboExtravioResponseTO = (ChequeraRoboExtravioResponseTO) decode.toDeserialize("bloqueoChequesForm", messageElement, mappedBeans);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ChequeraException(e);
		}

		
		return chequeraRoboExtravioResponseTO;
	}
	public ChequeraRoboExtravioResponseTO getExtravioEjecucion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraRoboExtravioResponseTO chequeraRoboExtravioResponseTO = new ChequeraRoboExtravioResponseTO();
		ChequeraDAO chequeraDAO = new ChequeraDAO();
		
		try {
			MessageElement messageElement = chequeraDAO.findExtravioEjecucion(extravioRequestTO);
			ChequeraRule chequeraRule = new ChequeraRule(messageElement);
			chequeraRule.validaExtravioEjecucion();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			
			mappedBeans.put("bloqueoChequesForm", "com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO");
			mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioTO");
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");
			XmlDecoder xmlDecoder = new XmlDecoder();
			chequeraRoboExtravioResponseTO = (ChequeraRoboExtravioResponseTO) xmlDecoder.toDeserialize("bloqueoChequesForm", messageElement, mappedBeans);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ChequeraException(e);
		}
		
		return chequeraRoboExtravioResponseTO;
	}
	
	public LiberacionChequesResponseTO liberacionChequesSolicitudCuenta(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		LiberacionChequesResponseTO responseTO = new LiberacionChequesResponseTO();
		ChequeraDAO dao = new ChequeraDAO();
		XmlDecoder decode = new XmlDecoder();
		log.info("liberacionChequesSolicitudCuenta");
		try {

			MessageElement messageElement = dao.liberacionChequesSolicitaCuenta(request);
			ChequeraRule rule = new ChequeraRule(messageElement);
			rule.validateLiberacionChequesSolicitaCuenta();

			messageElement = dao.liberacionChequesSolicitaChequeras( request );
			rule = new ChequeraRule( messageElement );
			rule.validateLiberacionChequesSolicitaCuenta();

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put( "liberacionChequesForm", "com.bancoazteca.elite.beans.LiberacionChequesResponseTO" );
			mappedBeans.put( "CuentaVO", "com.bancoazteca.elite.beans.CuentaTO" );

			responseTO = ( LiberacionChequesResponseTO ) decode.toDeserialize("liberacionChequesForm", messageElement, mappedBeans);
			if( responseTO == null || responseTO.getCuentas().isEmpty() ){
				log.debug( "No se encontraron chequeras por liberar." );
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "chequera", "Estimado Usuario No existen Chequeras por Liberar." );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );

			}
		} catch (DAOException e) {
			throw new ChequeraException();
		} 
		return responseTO;

	}
	
	public LiberacionChequesResponseTO liberacionChequesSolicitudChequera(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		LiberacionChequesResponseTO responseTO = new LiberacionChequesResponseTO();
		ChequeraDAO dao = new ChequeraDAO();
		XmlDecoder decode = new XmlDecoder();
		try {
			MessageElement messageElement = dao.liberacionChequesSolicitaChequeras(request);
			ChequeraRule rule = new ChequeraRule( messageElement );
			rule.validateLiberacionChequesSolicitaChequeras();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put( "liberacionChequesForm", "com.bancoazteca.elite.beans.LiberacionChequesResponseTO" );
			mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioTO");
			responseTO = ( LiberacionChequesResponseTO ) decode.toDeserialize("liberacionChequesForm", messageElement, mappedBeans);
			
			String cuenta = responseTO.getCuenta();
			responseTO.setTipoCuenta( cuenta.substring( cuenta.indexOf( "-" )+1 ) );
			responseTO.setCuenta( Formatter.formatSplittedCuenta( cuenta.substring( 0, cuenta.indexOf( "-" ) ) ) );
			
		} catch (DAOException e) {
			throw new ChequeraException();
		} 
		return responseTO;
	}
	
	public LiberacionChequesResponseTO liberacionChequesSolicitudCheque(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		LiberacionChequesResponseTO responseTO = new LiberacionChequesResponseTO();
		ChequeraDAO dao = new ChequeraDAO();		
		XmlDecoder decode = new XmlDecoder();
		log.info("liberacionChequesSolicitudCheque");
		try {
			MessageElement messageElement = dao.liberacionChequesSolicitaCheque(request);
			ChequeraRule rule = new ChequeraRule(messageElement);
			rule.validateLiberacionChequesSolicitaCheque();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put( "liberacionChequesForm", "com.bancoazteca.elite.beans.LiberacionChequesResponseTO" );
			mappedBeans.put("ChequeVO", "com.bancoazteca.elite.beans.ChequeTO");

			responseTO = ( LiberacionChequesResponseTO ) decode.toDeserialize("liberacionChequesForm", messageElement, mappedBeans);
			
			String cuenta = responseTO.getCuenta();
			responseTO.setTipoCuenta( cuenta.substring( cuenta.indexOf( "-" )+1 ) );
			responseTO.setCuenta( cuenta.substring( 0, cuenta.indexOf( "-" ) ) );
			
		} catch (DAOException e) {
			throw new ChequeraException();
		} 
		return responseTO;
	}
	
	public LiberacionChequesResponseTO liberacionChequesValidacion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		LiberacionChequesResponseTO responseTO = new LiberacionChequesResponseTO();
		ChequeraDAO dao = new ChequeraDAO();
		log.info("liberacionChequesValidacion");
		try {
			MessageElement messageElement = dao.liberacionChequesSolicitaPWD(request);
			ChequeraRule rule = new ChequeraRule(messageElement);
			rule.validateLiberacionChequesSolicitaPWD();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("liberacionChequesForm", "com.bancoazteca.elite.beans.LiberacionChequesResponseTO");
			responseTO = (LiberacionChequesResponseTO) decode.toDeserialize("liberacionChequesForm", messageElement, mappedBeans);

			String cuenta = responseTO.getCuenta();
			responseTO.setTipoCuenta( cuenta.substring( cuenta.indexOf( "-" )+1 ) );
			responseTO.setCuenta( cuenta.substring( 0, cuenta.indexOf( "-" ) ) );

			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ChequeraException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ChequeraException();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new ChequeraException();
		} 
		return responseTO;
	}
	
	public LiberacionChequesResponseTO liberacionChequesEjecucion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		LiberacionChequesResponseTO responseTO = new LiberacionChequesResponseTO();
		ChequeraDAO dao = new ChequeraDAO();

		try {
			MessageElement messageElement = dao.liberacionChequesEjecutaPWD(request);
			ChequeraRule rule = new ChequeraRule(messageElement);
			rule.validateLiberacionChequesEjecutaPWD();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("liberacionChequesForm", "com.bancoazteca.elite.beans.LiberacionChequesResponseTO");
			responseTO = (LiberacionChequesResponseTO) decode.toDeserialize("liberacionChequesForm", messageElement, mappedBeans);

			String cuenta = responseTO.getCuenta();
			responseTO.setTipoCuenta( cuenta.substring( cuenta.indexOf( "-" )+1 ) );
			responseTO.setCuenta( cuenta.substring( 0, cuenta.indexOf( "-" ) ) );
		} catch (DAOException e) {
			throw new ChequeraException();
		} 
		return responseTO;
	}

	public ActivacionChequeraResponseTO setActivacionChequeSolicitud( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ActivacionChequeraResponseTO responseTO = new ActivacionChequeraResponseTO();
		ChequeraDAO dao = new ChequeraDAO();
		
		XmlDecoder decode = new XmlDecoder();

		try {
			MessageElement messageElement = dao.getActivacionChequeSolicitud( requestTO );
			ChequeraRule rule = new ChequeraRule( messageElement );
			rule.validateActivacionChequeSolicitud();

			messageElement = dao.getActivacionChequeSeleccionCuenta( requestTO );
			rule = new ChequeraRule( messageElement );
			rule.validateActivacionChequeSolicitud();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("activacionChequesForm", "com.bancoazteca.elite.beans.ActivacionChequeraResponseTO");
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");
			 
			responseTO = (ActivacionChequeraResponseTO) decode.toDeserialize("activacionChequesForm", messageElement, mappedBeans);
			if( responseTO == null || responseTO.getCuentas().isEmpty() ){
				log.debug( "No se encontraron chequeras por activar." );
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "chequera", "Estimado Usuario No existen Chequeras por Activar." );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );
			}
		} catch (DAOException e) {
			throw new ChequeraException( e );
		}
		return responseTO;
	}

	public ActivacionChequeraResponseTO setActivacionChequeSeleccionCuenta( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ActivacionChequeraResponseTO responseTO = new ActivacionChequeraResponseTO();
		ChequeraDAO dao = new ChequeraDAO();
		
		XmlDecoder decode = new XmlDecoder();

		try {
			MessageElement messageElement = dao.getActivacionChequeSeleccionCuenta(requestTO);
			ChequeraRule rule = new ChequeraRule( messageElement );
			rule.validateActivacionChequeSolicitud();

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("activacionChequesForm", "com.bancoazteca.elite.beans.ActivacionChequeraResponseTO");
			mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioTO");
			responseTO = (ActivacionChequeraResponseTO) decode.toDeserialize("activacionChequesForm", messageElement, mappedBeans);
			
			if( responseTO == null || responseTO.getChequerascuenta() == null || responseTO.getChequerascuenta().isEmpty() ){
				log.debug( "No se encontraron chequeras por activar." );
				Map<String, String> errors = new HashMap<String, String>();
				errors.put( "chequera", "Estimado Usuario No existen Chequeras por Activar." );
				throw new EliteDataException( "Action Error", errors, EliteRules.LEVEL_ACTION_ERRORS );

			}
		} catch (DAOException e) {
			throw new ChequeraException( e );
		}
		return responseTO;
	}

	public ActivacionChequeraResponseTO getActivacionChequeValidacion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ActivacionChequeraResponseTO responseTO = new ActivacionChequeraResponseTO();
		ChequeraDAO dao = new ChequeraDAO();
		
		XmlDecoder decode = new XmlDecoder();

		try {
			MessageElement messageElement = dao.getActivacionChequeValidacion( requestTO );
			ChequeraRule rule = new ChequeraRule( messageElement );
			rule.validateActivacionChequeValidacion();

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("activacionChequesForm", "com.bancoazteca.elite.beans.ActivacionChequeraResponseTO");
			mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioTO");
			responseTO = (ActivacionChequeraResponseTO) decode.toDeserialize("activacionChequesForm", messageElement, mappedBeans);

 			DispositivoHuellaTO dispositivoHuellaTO = null;
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			responseTO.setDispositivoHuellaTO(dispositivoHuellaTO);

		} catch (DAOException e) {
			throw new ChequeraException( e );
		} catch (IOException e) {
			throw new ChequeraException( e );
		} catch (XmlDecodeException e) {
			throw new ChequeraException( e );
		}
		return responseTO;
	}

	public ActivacionChequeraResponseTO getActivacionChequeEjecucion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ActivacionChequeraResponseTO responseTO = new ActivacionChequeraResponseTO();
		ChequeraDAO dao = new ChequeraDAO();
		
		XmlDecoder decode = new XmlDecoder();

		try {
			MessageElement messageElement = dao.getActivacionChequeEjecucion( requestTO );
			ChequeraRule rule = new ChequeraRule( messageElement );
			rule.validateActivacionChequeEjecucion();

			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("activacionChequesForm", "com.bancoazteca.elite.beans.ActivacionChequeraResponseTO");
			responseTO = (ActivacionChequeraResponseTO) decode.toDeserialize("activacionChequesForm", messageElement, mappedBeans);
			
		} catch (DAOException e) {
			throw new ChequeraException( e );
		}
		return responseTO;
	}
	
	public PresolicitudChequesResponseTO presolicitudChequeraInicio(PresolicitudChequesRequestTO requestTO)throws ChequeraException, SessionExpiredException, EliteDataException{
		log.info("Iniciado consultas en chequera Inicio: "+ requestTO.getUsuario());
		ChequeraDAO dao=new ChequeraDAO();
		MessageElement messageElement=null;
		ChequeraRule validatorRule;
		PresolicitudChequesResponseTO presolicitudChequesResponseTO=null;
		try {
			dao.presolicitudChequeraInicioJSP(requestTO.getUsuario());
			
			messageElement=dao.presolicitudChequeraInicio(requestTO.getUsuario());
			validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudChequesInicio();
			
			messageElement=dao.presolicitudChequeraInicioPre(requestTO.getUsuario());
			validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudChequesInicioPre();
			
			CuentasChequesTO cuentasChequesTO=serializaPresolicitudInicio(messageElement);
			
			presolicitudChequesResponseTO=new PresolicitudChequesResponseTO();
			presolicitudChequesResponseTO.setCuentas(cuentasChequesTO.getCuentas());
			presolicitudChequesResponseTO.setComision(cuentasChequesTO.getComision());
		} catch (DAOException e) {
			new ChequeraException(e);
		} catch (IOException e) {
			new ChequeraException(e);
		}
		log.info("finalizando consulta de chequeras Inicio: "+requestTO.getUsuario());
		return presolicitudChequesResponseTO;
	}

	public PresolicitudChequesResponseTO presolicitudChequeraInicioDetalleCuenta(PresolicitudChequesRequestTO requestTO)throws ChequeraException, SessionExpiredException, EliteDataException{
		PresolicitudChequesResponseTO presolicitudChequesResponseTO=null;
		ConsultaPresolicitudDaoTO to=new ConsultaPresolicitudDaoTO();
		to.setCuenta(requestTO.getCuenta());
		to.setUsuario(requestTO.getUsuario());
		MessageElement messageElement=null;
		try{
			ChequeraDAO dao=new ChequeraDAO();
			messageElement=dao.presolicitudChequeraInicioDetalleCuenta(to);
			ChequeraRule validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudChequesDetalleCuenta();
			
			messageElement=dao.presolicitudChequeraInicioDetalleCuentaPre(requestTO.getUsuario());
			validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudChequesDetalleCuentaPre();
			
			CuentasChequesTO cuentasChequesTO=serializaPresolicitudInicio(messageElement);
			
			presolicitudChequesResponseTO=new PresolicitudChequesResponseTO();
			presolicitudChequesResponseTO.setCuentas(cuentasChequesTO.getCuentas());
			presolicitudChequesResponseTO.setComision(cuentasChequesTO.getComision());
			presolicitudChequesResponseTO.setTiposChequeras(cuentasChequesTO.getTiposChequeras());
			
		} catch (DAOException e) {
			new ChequeraException(e);
		} catch (IOException e) {
			new ChequeraException(e);
		} 
		return presolicitudChequesResponseTO;
		
	}

	public PresolicitudChequesResponseTO presolicitudChequeraValidacion(PresolicitudChequesRequestTO requestTO)throws ChequeraException, SessionExpiredException, EliteDataException{
		PresolicitudChequesResponseTO resolicitudChequesResponseTO=new PresolicitudChequesResponseTO();
		try {
			ConsultaPresolicitudDaoTO to=new ConsultaPresolicitudDaoTO();
			to.setCuenta(requestTO.getCuenta());
			to.setFechaSolicitud(Formatter.formatFecha(new java.util.Date()));
			to.setMontoPiso(requestTO.getMontoPiso());
			to.setProteccion(requestTO.getProteccion());
			to.setRegimen(requestTO.getRegimen());
			to.setTipoChequera(requestTO.getTipoChequera());
			to.setUsuario(requestTO.getUsuario());
			ChequeraDAO dao=new ChequeraDAO();
			
			MessageElement messageElement=dao.presolicitudChequesValidacion(to);
			ChequeraRule validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudChequesInicio();
			
			messageElement=dao.presolicitudChequesValidacionPre(to.getUsuario());
			validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudValidacion();
			
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			resolicitudChequesResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
			RegistroFirmasVO registroFirmasVO = serializaPresolicitudRegistroFirmasVO(messageElement);
			
			resolicitudChequesResponseTO.setRegistroFirmasVO(registroFirmasVO);
			
		} catch (DAOException e) {
			new ChequeraException(e);
		} catch (IOException e) {
			new ChequeraException(e);
		} catch (XmlDecodeException e) {
			new ChequeraException(e);
		}
		return resolicitudChequesResponseTO;
		
	}
	
	public PresolicitudChequesResponseTO presolicitudChequeraEjecucion(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException{
		PresolicitudChequesResponseTO presolicitudChequesResponseTO=new PresolicitudChequesResponseTO();
		ChequeraDAO dao=new ChequeraDAO();
		ConsultaPresolicitudDaoTO to = new ConsultaPresolicitudDaoTO();
		try {
			to.setClave(requestTO.getClave());
			to.setOptionDispositive(requestTO.getOptionDispositive());
			to.setUsuario(requestTO.getUsuario());
			to.setTokenCode(requestTO.getTokenCode());
			MessageElement messageElement=dao.presolicitudChequesEjecucion(to);
			ChequeraRule validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudChequesInicio();
			
			
			messageElement=dao.presolicitudChequesEjecucionPre(requestTO.getUsuario());
			validatorRule=new ChequeraRule(messageElement);
			validatorRule.validaPresolicitudEjecucion();
			
			RegistroFirmasVO registroFirmasVO = serializaPresolicitudRegistroFirmasVO(messageElement);
			
			if("true".equalsIgnoreCase(registroFirmasVO.getFolioPreSolicitud())){
				HashMap<String, String> errors = new HashMap<String, String>();
				errors.put("folioPreSolicitud", "Estimado usuario, No se pudo realizar la presolicitud del servicio debido a que cuenta con un folio anterior: "+registroFirmasVO.getFolioPreSolicitud());
				new EliteDataException("Error de folio: ",errors,EliteRules.LEVEL_ACTION_ERRORS);
			}
			
			presolicitudChequesResponseTO.setRegistroFirmasVO(registroFirmasVO);
			
		} catch (DAOException e) {
			new ChequeraException(e);
		} catch (IOException e) {
			new ChequeraException(e);
		} 
		return presolicitudChequesResponseTO;
	}
	
	private RegistroFirmasVO serializaPresolicitudRegistroFirmasVO(MessageElement messageElement)throws IOException {
		XmlDecoder decoder = new XmlDecoder();
		String query="for $registroFirmasVO in //solicitudChequesForm/registroFirmasVO return <root>{$registroFirmasVO}</root>";
		Element element = XMLFinder.getElement(messageElement.toString(), query);
		messageElement = new MessageElement(element);
		
		HashMap<String, String> mappedBeans=new HashMap<String, String>();
		mappedBeans.put("registroFirmasVO", "com.bancoazteca.elite.beans.RegistroFirmasVO");
		
		RegistroFirmasVO registroFirmasVO = (RegistroFirmasVO) decoder.toDeserialize("registroFirmasVO", messageElement, mappedBeans);
		return registroFirmasVO;
	}
	
	private CuentasChequesTO serializaPresolicitudInicio(MessageElement messageElement) throws IOException{
		XmlDecoder decoder = new XmlDecoder();
		String query = "for $solicitudChequesForm in //solicitudChequesForm return <cuentas_cheques>{$solicitudChequesForm}</cuentas_cheques>";
		log.info("query" + query);
		Element element = XMLFinder.getElement(messageElement.toString(), query);
		messageElement = new MessageElement(element);
		
		HashMap<String, String> mappedBeans = new HashMap<String, String>();
		mappedBeans.put("solicitudChequesForm", "com.bancoazteca.elite.beans.CuentasChequesTO");
		mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaVO");
		mappedBeans.put("TalonarioVO", "com.bancoazteca.elite.beans.TalonarioVO");
		
		mappedBeans.put("cuentas", "java.util.ArrayList");
		mappedBeans.put("tiposChequeras", "java.util.ArrayList");
		
		CuentasChequesTO cuentasChequesTO = (CuentasChequesTO) decoder.toDeserialize("solicitudChequesForm", messageElement, mappedBeans);
		log.info("cuentasChequesTO: " + cuentasChequesTO);
		return cuentasChequesTO;
	}
	
	public ConsultaChequesResponseTO solicitudConsultarChequera(ConsultaChequesRequestTO chequesRequestTO) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraDAO dao = new ChequeraDAO();
		ConsultaChequesResponseTO consultaChequesResponseTO = null;
		MessageElement messageElement = null;
		MessageElement message = null;
		
		List<ConnectorDataTO> listParametros= new ArrayList<ConnectorDataTO>();
		listParametros.add(new ConnectorDataTO("acciones","inicio"));
		XmlDecoder xmlDecoder = new XmlDecoder();
		ChequeraRule chequeraRule ;
		String query="for $a in //elite/cuentasSeleccionables return <temp>{$a}</temp>";
		
		try {
			dao.jspInitSolicitudPriv3(chequesRequestTO.getUser());
			dao.solicitudConsultarChequera01(chequesRequestTO.getUser());
			dao.solicitudConsultarChequera02(chequesRequestTO.getUser());
			
			chequesRequestTO.setParametros(listParametros);
			messageElement = dao.ejecutarConsultaCheques(chequesRequestTO);
			
			chequeraRule= new ChequeraRule(messageElement);
			chequeraRule.validaSolicitudConsultarChequera();

			consultaChequesResponseTO = new ConsultaChequesResponseTO();
			Element element=XMLFinder.getElement(messageElement.toString(),query);
			message = new MessageElement(element);
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CuentaLO","com.bancoazteca.elite.beans.CuentaLoTO");	
			Collection<CuentaLoTO> coleccionCuentas = ( Collection<CuentaLoTO> ) xmlDecoder.toDeserialize( "cuentasSeleccionables", message, mappedBeans );
			
			consultaChequesResponseTO.setColeccionCuentas((List<CuentaLoTO>)coleccionCuentas);
		} catch (LoginException e) {
			throw new ChequeraException(e);
		} catch (DAOException e) {
			throw new ChequeraException(e);
		} catch (SessionExpiredException e) {
			throw new ChequeraException(e);
		} catch (IOException e) {
			throw new ChequeraException(e);
		}
		return consultaChequesResponseTO;
	}
	
	public ConsultaChequesResponseTO detalleVariasCuentasChequera(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraDAO dao = new ChequeraDAO();
		ConsultaChequesResponseTO consultaChequesResponseTO = null;
		MessageElement messageElement = null;
		MessageElement message = null;
		XmlDecoder xmlDecoder = new XmlDecoder();
		ChequeraRule chequeraRule;
		String query="for $a in //elite/chequeras return <temp>{$a}</temp>";
		try {
			consultaChequesResponseTO = new ConsultaChequesResponseTO();
			Collection<ConnectorDataTO> parametros= new ArrayList<ConnectorDataTO>();
			parametros.add(new ConnectorDataTO("acciones","detalleChequeraVariasCuentas"));
			parametros.add(new ConnectorDataTO("cuenta",chequesRequestTO.getCuenta()));
			
			chequesRequestTO.setParametros(parametros);
			
			messageElement = dao.ejecutarConsultaCheques(chequesRequestTO);
			chequeraRule= new ChequeraRule(messageElement);
			chequeraRule.validaDetalleVariasCuentasChequera();

			Element element=XMLFinder.getElement(messageElement.toString(),query);
			message = new MessageElement(element);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("TalonarioVO","com.bancoazteca.elite.beans.TalonarioTO");	
			Collection<TalonarioTO> coleccionChequeras = ( Collection<TalonarioTO> ) xmlDecoder.toDeserialize( "chequeras", message, mappedBeans );
			if(coleccionChequeras != null && coleccionChequeras.size() > 0 ){
				consultaChequesResponseTO.setColeccionChequeras((List<TalonarioTO>)coleccionChequeras);
			}else{
				throw new EliteDataException("Error", "Estimado usuario, la cuenta seleccionada no cuenta con chequeras disponibles favor de intentarlo nuevamente.", EliteRules.LEVEL_ACTION_ERRORS);
			}
		} catch (LoginException e) {
			throw new ChequeraException(e);
		} catch (DAOException e) {
			throw new ChequeraException(e);
		} catch (SessionExpiredException e) {
			throw new ChequeraException(e);
		} catch (IOException e) {
			throw new ChequeraException(e);
		}
		return consultaChequesResponseTO;
	}
	public ConsultaChequesResponseTO consultaChequera(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraDAO dao = new ChequeraDAO();
		ConsultaChequesResponseTO consultaChequesResponseTO = null;
		MessageElement messageElement = null;
		MessageElement messageForm = null;
		XmlDecoder xmlDecoder = new XmlDecoder();
		ChequeraRule chequeraRule;
		String queryForm ="for $a in //elite/consultaChequesForm return <temp>{$a}</temp>";
		try {
			Collection<ConnectorDataTO> parametros= new ArrayList<ConnectorDataTO>();
			parametros.add(new ConnectorDataTO("acciones","toDetail"));
			parametros.add(new ConnectorDataTO("indiceChequera",chequesRequestTO.getIndiceChequera()));
			parametros.add(new ConnectorDataTO("cuenta",chequesRequestTO.getCuenta()));
			
			chequesRequestTO.setParametros(parametros);
			
			messageElement = dao.ejecutarConsultaCheques(chequesRequestTO);
			
			parametros= new ArrayList<ConnectorDataTO>();
			parametros.add(new ConnectorDataTO("acciones","detalle"));
			
			chequesRequestTO.setParametros(parametros);
			
			messageElement = dao.ejecutarConsultaCheques(chequesRequestTO);
			
			Element element=XMLFinder.getElement(messageElement.toString(),queryForm);
			messageForm =new MessageElement(element);
			
			chequeraRule = new ChequeraRule(messageElement);
			chequeraRule.validaConsultaChequera();
			
			consultaChequesResponseTO = new ConsultaChequesResponseTO();
			String []coleccionEstatus = (String[]) xmlDecoder.toDeserialize("estatusCheques", messageElement, null);
			String numeroChequera=(String)xmlDecoder.toDeserialize( "chequeInicial", messageForm, null ) + " - " +(String)xmlDecoder.toDeserialize( "chequeFinal", messageForm, null );
			consultaChequesResponseTO.setNumeroChequera(numeroChequera);
			consultaChequesResponseTO.setTipoCuenta((String)xmlDecoder.toDeserialize( "tipoCuenta", messageForm, null ));
			consultaChequesResponseTO.setTipoChequera((String)xmlDecoder.toDeserialize( "descripcionChequera", messageForm, null ));
			consultaChequesResponseTO.setNumeroCuenta((String)xmlDecoder.toDeserialize( "cuentaFormat", messageForm, null ));
			consultaChequesResponseTO.setColeccionEstatus(coleccionEstatus);
		} catch (LoginException e) {
			throw new ChequeraException(e);
		} catch (DAOException e) {
			throw new ChequeraException(e);
		} catch (SessionExpiredException e) {
			throw new ChequeraException(e);
		} catch (IOException e) {
			throw new ChequeraException(e);
		}
		return consultaChequesResponseTO;
	}
	public ConsultaChequesResponseTO consultaChequeraDetalle(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraDAO dao = new ChequeraDAO();
		ConsultaChequesResponseTO consultaChequesResponseTO = null;
		MessageElement messageElement = null;
		XmlDecoder xmlDecoder = new XmlDecoder();
		try {
			Collection<ConnectorDataTO> parametros= new ArrayList<ConnectorDataTO>();
			parametros.add(new ConnectorDataTO("acciones","toDetail"));
			parametros.add(new ConnectorDataTO("indiceATraer",""));
			parametros.add(new ConnectorDataTO("opcionConsulta",chequesRequestTO.getOpcionConsulta()));
			
			chequesRequestTO.setParametros(parametros);
			
			messageElement = dao.ejecutarConsultaCheques(chequesRequestTO);
			
			parametros= new ArrayList<ConnectorDataTO>();
			parametros.add(new ConnectorDataTO("acciones","detalle"));
			
			chequesRequestTO.setParametros(parametros);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ChequeVO","com.bancoazteca.elite.beans.ChequeTO");	
			messageElement = dao.ejecutarConsultaCheques(chequesRequestTO);
			dao.ejecutarJsp(chequesRequestTO.getUser());
			messageElement = dao.ejecutarConsultaCheques(chequesRequestTO);
			consultaChequesResponseTO = new ConsultaChequesResponseTO();
			List<ChequeTO> aux=(List<ChequeTO>)xmlDecoder.toDeserialize("chequesPagina", messageElement, mappedBeans);
			
			if(aux != null && aux.size() > 0 ){
				List<ChequeTO> listadetalleCheques= new ArrayList<ChequeTO>();
				for(ChequeTO obj:aux){
					if(obj != null)
						listadetalleCheques.add(obj);
				}
				consultaChequesResponseTO.setColeccionCheques(listadetalleCheques);
				
			}
			else{
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("data_empty", "Estimado usuario, no se encontraron registros para el filtro seleccionado inténtelo nuevamente.");
				throw new EliteDataException("Error de Action  ",errors,EliteRules.LEVEL_ACTION_ERRORS);
			}
			
		} catch (LoginException e) {
			throw new ChequeraException(e);
		} catch (DAOException e) {
			throw new ChequeraException(e);
		} catch (SessionExpiredException e) {
			throw new ChequeraException(e);
		}
		return consultaChequesResponseTO;
	}
}
	