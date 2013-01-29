package com.bancoazteca.elite.ejb.traspasos;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ConfirmarTraspasoPropioTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.TraspasosPropiasRequestTO;
import com.bancoazteca.elite.beans.TraspasosPropiasResponsetTO;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.Formatter;
/**
 * @author Banco Azteca S.A. [JFAV] Agosto 25, 2008.
 */
public class TraspasosSLBean implements SessionBean {


	private static final long serialVersionUID = 5561712127450232560L;
	private static final Logger log = Logger.getLogger(TraspasosSLBean.class);

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
	
	public TraspasosPropiasResponsetTO propiasInvocaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException {
		TraspasosPropiasResponsetTO propiasResponseTO = new TraspasosPropiasResponsetTO();
		try {
			TraspasosDAO traspasosDao = new TraspasosDAO();
			MessageElement messageElementInvocacion = traspasosDao.setInvocaTraspaso(traspasosPropiasRequestTO);
			TraspasosRule traspasosRule = new TraspasosRule(messageElementInvocacion);
			traspasosRule.validateByActionErrors();
			
			MessageElement messageElementStart = traspasosDao.setIniciaTraspaso(traspasosPropiasRequestTO);
			traspasosRule = new TraspasosRule(messageElementStart);
			traspasosRule.validateByActionErrors();
			
			MessageElement messageElement = traspasosDao.setPropiasPreparaTraspaso(traspasosPropiasRequestTO);
			
			XmlDecoder decoder = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("traspasosPropiasForm", "com.bancoazteca.elite.beans.ConfirmarTraspasoPropioTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");

			ConfirmarTraspasoPropioTO traspasoPropioTO = (ConfirmarTraspasoPropioTO) decoder.toDeserialize("traspasosPropiasForm", messageElement, mappedBeans);
			Collection<CuentaTO> cuentas = ( Collection<CuentaTO> ) decoder.toDeserialize( "saldosAccounts", messageElement, mappedBeans );
			String valorUnidad = (String) decoder.toDeserialize("valorUni", messageElement, null);
			propiasResponseTO.setValorUnidad(valorUnidad);
			traspasoPropioTO.setCuentas( cuentas );
			formatearCuentasLO(traspasoPropioTO.getCuentasDestino());
			formatearCuentasLO(traspasoPropioTO.getCuentasOrigen());
			formatearCuentasTO( traspasoPropioTO.getCuentasTO() );

			propiasResponseTO.setTraspasoPropioTO(traspasoPropioTO);
			
		} catch (DAOException e) {
			throw new TraspasosException(e);
		} 
		return propiasResponseTO;
	}


	/**
	 * Prepara el trapaso entre cuentas con base al id del cliente y sus
	 * correspondientes cuentas e importe, a trav&eacute;s del TraspasosDAO.
	 * 
	 * @param clientId
	 * @param cuentaOrigenIndex
	 * @param cuentaDestinoIndex
	 * @param importe
	 * @param concepto
	 * @throws EJBException
	 * @throws TraspasosException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	public TraspasosPropiasResponsetTO propiasPreparaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException {
		TraspasosPropiasResponsetTO propiasResponseTO = new TraspasosPropiasResponsetTO();
		try {

			TraspasosDAO traspasosDao = new TraspasosDAO();
			MessageElement messageElement = traspasosDao.setPropiasPreparaTraspaso(traspasosPropiasRequestTO);
			TraspasosRule traspasosRule = new TraspasosRule(messageElement);
			traspasosRule.validatePreparePropiasPath();
			XmlDecoder decoder = new XmlDecoder();
			String valorUnidad = (String) decoder.toDeserialize("valorUni", messageElement, null);
			String referencia = (String) decoder.toDeserialize("ctaP", messageElement, null);
			String restriccion = (String) decoder.toDeserialize("ctaNoPlus", messageElement, null);
			String totalUnidades = (String) decoder.toDeserialize("totuni", messageElement, null);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("traspasosPropiasForm", "com.bancoazteca.elite.beans.ConfirmarTraspasoPropioTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			ConfirmarTraspasoPropioTO traspasoPropioTO = (ConfirmarTraspasoPropioTO) decoder.toDeserialize("traspasosPropiasForm", messageElement, mappedBeans);
			formatearCuentasLO(traspasoPropioTO.getCuentasDestino());
			formatearCuentasLO(traspasoPropioTO.getCuentasOrigen());
			propiasResponseTO.setTotalUnidades(totalUnidades);
			propiasResponseTO.setValorUnidad(valorUnidad);
			propiasResponseTO.setReferencia(referencia);
			propiasResponseTO.setRestriccion(restriccion);
			propiasResponseTO.setTraspasoPropioTO(traspasoPropioTO);
		} catch (DAOException e) {
			throw new TraspasosException(e);
		} 
		return propiasResponseTO;
	}

	/**
	 * Ejecuta el trapaso entre cuentas con base al id del cliente y sus
	 * correspondientes cuentas e importe, a trav&eacute;s del TraspasosDAO.
	 * 
	 * @param clientId
	 * @param cuentaOrigenIndex
	 * @param cuentaDestinoIndex
	 * @param importe
	 * @param concepto
	 * @return
	 * @throws EJBException
	 * @throws TraspasosException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	@SuppressWarnings("unchecked")
	public TraspasosPropiasResponsetTO ejecutaPropiasTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException {
		TraspasosPropiasResponsetTO traspasosPropiasResponsetTO = new TraspasosPropiasResponsetTO();
		try {
			TraspasosDAO traspasosDao = new TraspasosDAO();
			MessageElement messageElement = traspasosDao.setPropiasEjecutaTraspaso(traspasosPropiasRequestTO);
			TraspasosRule traspasosRule = new TraspasosRule(messageElement);
			traspasosRule.validatePreparePropiasEjecutaPath();
			XmlDecoder decoder = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("PropiasForm", "com.bancoazteca.elite.beans.ConfirmarTraspasoPropioTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			ConfirmarTraspasoPropioTO traspasoPropioTO = (ConfirmarTraspasoPropioTO) decoder.toDeserialize("PropiasForm", messageElement, mappedBeans);
			
			if(traspasoPropioTO!=null){
				formatearCuentasLO(traspasoPropioTO.getCuentasDestino());
				formatearCuentasLO(traspasoPropioTO.getCuentasOrigen());
				traspasosPropiasResponsetTO.setTraspasoPropioTO(traspasoPropioTO);
				traspasosPropiasResponsetTO.setEBankingCom(false);
			}else{
				String []arregloDatos =(String[])decoder.toDeserialize("ebanking_web_DataKey", messageElement, null);
				
				String folio=arregloDatos[arregloDatos.length-1];
				System.out.println("El folio: "+folio);
				traspasosPropiasResponsetTO.setFolio(folio);
				traspasosPropiasResponsetTO.setEBankingCom(true);
			}
			
		} catch (DAOException e) {
			throw new TraspasosException(e);
		} 
		return traspasosPropiasResponsetTO;
	}
	
	private void formatearCuentasLO(Collection<CuentaLoTO> cuentas) {
		if(cuentas != null || cuentas.size() > 0)
		{
			int i =0;
			for (CuentaLoTO cuentaTO : cuentas) {
				i = i + 1;
				cuentaTO.setCuentaFormateada(Formatter.formatSplittedCuenta(cuentaTO.getNumero()));
				log.info(" cuenta formateada: "+cuentaTO.getCuentaFormateada());			
			}
		}
	}

	private void formatearCuentasTO(Collection<CuentaTO> cuentas) {
		if(cuentas != null || cuentas.size() > 0)
		{
			int i =0;
			for (CuentaTO cuentaTO : cuentas) {
				i = i + 1;
				cuentaTO.setCuentaFormateada(Formatter.formatSplittedCuenta(cuentaTO.getNumero()));
				log.info(" cuenta formateada: "+cuentaTO.getCuentaFormateada());			
			}
		}
	}
}
