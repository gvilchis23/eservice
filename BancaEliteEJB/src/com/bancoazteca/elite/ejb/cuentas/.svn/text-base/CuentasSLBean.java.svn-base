package com.bancoazteca.elite.ejb.cuentas;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import com.bancoazteca.elite.beans.AlertCompaniaTO;
import com.bancoazteca.elite.beans.AlertsAcountsTO;
import com.bancoazteca.elite.beans.AlertsCardsTO;
import com.bancoazteca.elite.beans.AlertsDataRequest;
import com.bancoazteca.elite.beans.AlertsDataResponseTO;
import com.bancoazteca.elite.beans.AperturaCuentaPlataRequestTO;
import com.bancoazteca.elite.beans.AperturaCuentaPlataResponseTO;
import com.bancoazteca.elite.beans.AperturaCuentaSocioRequestTO;
import com.bancoazteca.elite.beans.AperturaCuentaSocioResponseTO;
import com.bancoazteca.elite.beans.ArchivoNominaTO;
import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.BalanceResponseTO;
import com.bancoazteca.elite.beans.BancoSpeiTO;
import com.bancoazteca.elite.beans.CambioNipRequestTO;
import com.bancoazteca.elite.beans.CambioNipResponseTO;
import com.bancoazteca.elite.beans.CambioSucursalTO;
import com.bancoazteca.elite.beans.CancelacionElementTO;
import com.bancoazteca.elite.beans.CargosPorAplicarInfiniteTO;
import com.bancoazteca.elite.beans.ChangeBranchRequestTO;
import com.bancoazteca.elite.beans.ChangeBranchResponseTO;
import com.bancoazteca.elite.beans.ChequeraPreaperturaRequestTO;
import com.bancoazteca.elite.beans.ChequeraPreaperturaResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CodeTO;
import com.bancoazteca.elite.beans.ConfirmacionAperturaCuentaPlataTO;
import com.bancoazteca.elite.beans.ConfirmarAperturaCuentaSocioTO;
import com.bancoazteca.elite.beans.ConfirmarEliminacionCuentaFrecuenteTO;
import com.bancoazteca.elite.beans.ConnectorDataTO;
import com.bancoazteca.elite.beans.ConsultaMovimientoInvAztTO;
import com.bancoazteca.elite.beans.ConsultaRecibosNominaFormTO;
import com.bancoazteca.elite.beans.CorporativaCreditoCargosAplicarTO;
import com.bancoazteca.elite.beans.CorporativaCreditoDetalleMovimientosTO;
import com.bancoazteca.elite.beans.CorporativaDebitoMovimientosTO;
import com.bancoazteca.elite.beans.CotizacionOnzaPlataLibertadTO;
import com.bancoazteca.elite.beans.CreditosMovimientosTO;
import com.bancoazteca.elite.beans.CreditosRequestTO;
import com.bancoazteca.elite.beans.CreditosResponseTO;
import com.bancoazteca.elite.beans.CuentaInversionTO;
import com.bancoazteca.elite.beans.CuentaLoTO;
import com.bancoazteca.elite.beans.CuentaMisFinanzasTO;
import com.bancoazteca.elite.beans.CuentaSocioPlusRequestTO;
import com.bancoazteca.elite.beans.CuentaSocioPlusResponseTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaVO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesResponseTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesTO;
import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DetalleMovimientosCuentasTO;
import com.bancoazteca.elite.beans.DetalleMovimientosMisFinanzasTO;
import com.bancoazteca.elite.beans.DetallePagoCreditoTO;
import com.bancoazteca.elite.beans.DetallePagoOtrosCreditosTO;
import com.bancoazteca.elite.beans.DisposicionEfectivoCajeroRequestTO;
import com.bancoazteca.elite.beans.DisposicionEfectivoCajeroResponseTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.ERecibosNominaRequestTO;
import com.bancoazteca.elite.beans.ERecibosNominaResponseTO;
import com.bancoazteca.elite.beans.EstadoCuentaRequestTO;
import com.bancoazteca.elite.beans.EstadoCuentaResponseTO;
import com.bancoazteca.elite.beans.InfiniteAdicionalTO;
import com.bancoazteca.elite.beans.InfiniteDetalleMovimientosSaldoActualTO;
import com.bancoazteca.elite.beans.InfiniteDetalleMovimientosSaldoCorteTO;
import com.bancoazteca.elite.beans.InfiniteMovimientosRequestTO;
import com.bancoazteca.elite.beans.InfiniteMovimientosResponseTO;
import com.bancoazteca.elite.beans.InfiniteTitularTO;
import com.bancoazteca.elite.beans.InternetSalesRequestTO;
import com.bancoazteca.elite.beans.InternetSalesResponseTO;
import com.bancoazteca.elite.beans.LabelValueBeanTO;
import com.bancoazteca.elite.beans.LiberacionRecibosTO;
import com.bancoazteca.elite.beans.LockUnlockCardsRequestTO;
import com.bancoazteca.elite.beans.LockUnlockCardsResponseTO;
import com.bancoazteca.elite.beans.MediosPagoTO;
import com.bancoazteca.elite.beans.MisFinanzasResponseTO;
import com.bancoazteca.elite.beans.MovimientoInversionTO;
import com.bancoazteca.elite.beans.MovimientosCuentaInversionRequest;
import com.bancoazteca.elite.beans.MovimientosCuentasInversionTO;
import com.bancoazteca.elite.beans.MovimientosInversionAztecaTO;
import com.bancoazteca.elite.beans.MovimientosTO;
import com.bancoazteca.elite.beans.ReciboTO;
import com.bancoazteca.elite.beans.ResultadoBusquedaSucursalTO;
import com.bancoazteca.elite.beans.RetencionesRequestTO;
import com.bancoazteca.elite.beans.RetencionesResponseTO;
import com.bancoazteca.elite.beans.SecurityLevelTO;
import com.bancoazteca.elite.beans.SocioPlusTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaCreditoRequestTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaCreditoResponseTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaCreditoTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaDebitoRequestTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaDebitoResponseTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaDebitoTO;
import com.bancoazteca.elite.beans.TarjetaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.EliteException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.ejb.usuario.UsuarioSLBean;
import com.bancoazteca.elite.util.DigitalFingerUtil;
import com.bancoazteca.elite.util.EscapeUtils;
import com.bancoazteca.elite.util.Formatter;

/**
 * 
 * @author Banco Azteca S.A. [JFAV] Agosto 25, 2008.
 */
public class CuentasSLBean implements SessionBean {
	
	private static final Logger log = Logger.getLogger(CuentasSLBean.class);
	private static final long serialVersionUID = 1779828637738430376L;
		
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
	
	
	protected SessionContext getContext() {
		return context;
	}

	protected void setContext(SessionContext context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public BalanceResponseTO getMovimientosByIndex(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		BalanceResponseTO balanceResponseTO = new BalanceResponseTO();
		try {
			
			CuentasDAO cuentasDao = new CuentasDAO();
			XMLDecode decode = new XMLDecode();
			MessageElement messageElement = cuentasDao.findMovimientosByIndex(balanceRequestTO);
			CuentasRule usuarioRule = new CuentasRule(messageElement);
			usuarioRule.validateByActionErrors();
			//cuentasDao.invokeJspMovimientosByIndex(balanceRequestTO);
			/*
			XmlDecoder decoder = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ConsultaMovimientosBean", "com.bancoazteca.elite.beans.DetalleMovimientosCuentasTO");
			DetalleMovimientosCuentasTO detalleMovimientosCuentas = (DetalleMovimientosCuentasTO) decoder.toDeserialize("ConsultaMovimientosBean", messageElement, mappedBeans);
			if(detalleMovimientosCuentas!=null){
				String fechaInicio = detalleMovimientosCuentas.getInicio();
				String fechaFin = detalleMovimientosCuentas.getTermino();
				detalleMovimientosCuentas.setInicio(Formatter.formatDate(fechaInicio));
				detalleMovimientosCuentas.setTermino(Formatter.formatDate(fechaFin));
			}
			*/
			//consulta de saldos 
			/*Obtenemos los siguientes datos:
			 * retenido
			 * disponible
			 * total
			 * total cargos
			 * total abonos*/
			XmlDecoder decoder = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ConsultaMovimientosBean", "com.bancoazteca.elite.beans.DetalleMovimientosCuentasTO");
			DetalleMovimientosCuentasTO detalleMovimientosCuentas = (DetalleMovimientosCuentasTO) decoder.toDeserialize("ConsultaMovimientosBean", messageElement, mappedBeans);
			
			mappedBeans.clear();
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaVO");
			CuentaVO cuentaVO = (CuentaVO) decoder.toDeserialize("CuentaVO", messageElement, mappedBeans);

			//consulta de saldos
			
			
			String query = "for $a in //elite/ebanking_web_ClienteVO/cuentas/CuentaVO where $a/numero[@value = " + balanceRequestTO.getAcountNumber() + "] return $a/movimientos";
			log.info("query" + query);
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			log.info(messageElement.toString());
			Collection<MovimientosTO> movimientos = (Collection<MovimientosTO>) decode.buildCollectionBeans(MovimientosTO.class, messageElement, "MovimientoVO");
			log.info("total de movimientos " + movimientos.size());
			Collection<MovimientosTO> movimientosFormateados = new ArrayList<MovimientosTO>();
			for(MovimientosTO tmp: movimientos){
				tmp.setFecha(Formatter.formatDate(tmp.getFecha()));
				movimientosFormateados.add(tmp);
			}
									
			balanceResponseTO.setMovimientos(movimientosFormateados);
//			balanceResponseTO.setDetalleMovimientosCuentas(detalleMovimientosCuentas);
			
			//consulta de saldos	
			balanceResponseTO.setDetalleMovimientosCuentas(detalleMovimientosCuentas);
			//consulta de saldos	
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return balanceResponseTO;
	}
	
	public BalanceResponseTO getMovimientosByDate(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		BalanceResponseTO balanceResponseTO = new BalanceResponseTO();
		try {			
			CuentasDAO cuentasDao = new CuentasDAO();
			XMLDecode decode = new XMLDecode();
			MessageElement messageElementWait = cuentasDao.findWaitMovimientosByRango(balanceRequestTO);
		//	CuentasRule usuarioRule = new CuentasRule(messageElementWait);
			//usuarioRule.validateByActionErrors();
			
			MessageElement messageElement = cuentasDao.findMovimientosByRango(balanceRequestTO);
		//	usuarioRule = new CuentasRule(messageElement);
			//usuarioRule.validateByActionErrors();
						
		} catch (DAOException e) {
			throw new CuentasException(e);
		} 
		return balanceResponseTO;
	}
	
	public BalanceResponseTO getPartnerPlusBalanceAccount(BalanceRequestTO balanceRequestTO)throws  CuentasException, SessionExpiredException, EliteDataException {
		BalanceResponseTO balanceResponseTO = new BalanceResponseTO();
		CuentasDAO cuentasDao = new CuentasDAO();
		XMLDecode decode = new XMLDecode();
		String query = "for $a in //elite/ebanking_web_ClienteVO/cuentas/CuentaVO where $a/numero[@value = " + balanceRequestTO.getAcountNumber() + "] return $a";
		//Para consulta de datos del cliente
		String queryDatosCliente = "for $cliente in //elite/ebanking_web_DataKey/array/ConsultaMovimientosVO return $cliente";
		
		String plusvaliaValorUni = "for $valor in //elite/valorUni return <elite>{$valor}</elite>";
		
		try {
			cuentasDao.findWaitPartnerPlusBalanceAccount(balanceRequestTO);
			MessageElement messageElement = cuentasDao.findPartnerPlusBalanceAccount(balanceRequestTO);
			
					
			
			//Para los datos del cliente
			String fecha_inicio="";
			String fecha_final="";
			String valorUnitario="";
			
			
			Element elementDatosCliente = XMLFinder.getElement(messageElement.toString(), queryDatosCliente);
			MessageElement messageElementCliente = new MessageElement(elementDatosCliente);
			fecha_inicio = decode.getString(messageElementCliente, "inicio");
			fecha_final = decode.getString(messageElementCliente, "termino");
			
			
			String rendimientos = decode.getArrayString(messageElement,"rendimientos");
			//List<BigDecimal> plusvalia = buildBigDecimalList(decode.getArrayString(messageElement,"plusvalia"));
			//List<BigDecimal> posicionActual = new ArrayList<BigDecimal>();
			String plusvalia = decode.getArrayString(messageElement,"plusvalia");
			
			
			/*Obtenemos el valor de Precio Actual de la unidad*/

			Element elementValorUni = XMLFinder.getElement(messageElement.toString(), plusvaliaValorUni);
			MessageElement messageValorUnitario= new MessageElement(elementValorUni);
			valorUnitario = decode.getString(messageValorUnitario, "valorUni");
			
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<MovimientosTO> movimientos = (Collection<MovimientosTO>) decode.buildCollectionBeans(MovimientosTO.class, messageElement, "DetalleMovimientosVO");
			
			
			
			for(MovimientosTO movSocioPlus: movimientos){
				movSocioPlus.setFecha(Formatter.formatDate(movSocioPlus.getFecha()));
			}
//			try{
//				posicionActual.add(plusvalia.get(4));
//				posicionActual.add(plusvalia.get(5));
//				posicionActual.add(plusvalia.get(1));
//			}catch(Throwable e){}
//			
			log.info("total de movimientos " + movimientos.size());
			log.info("rendimientos "+rendimientos);
			log.info("plusvalia "+plusvalia);
			
			balanceResponseTO.setMovimientos(movimientos);
			//balanceResponseTO.setPosicionActual(posicionActual);
			balanceResponseTO.setRendimientos(rendimientos);
			balanceResponseTO.setPlusvalia(plusvalia);
			balanceResponseTO.setFecha_inicio(fecha_inicio);
			balanceResponseTO.setFecha_final(fecha_final);
			balanceResponseTO.setValorUni(valorUnitario);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return balanceResponseTO;
	}
	
	private List<BigDecimal> buildBigDecimalList(String data){
		StringTokenizer stringTokenizer = new StringTokenizer(data,",");
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		while(stringTokenizer.hasMoreElements()){
			String token = stringTokenizer.nextToken();
			BigDecimal bigDecimal = new BigDecimal(token);
			list.add(bigDecimal);
		}
		return list;
	}

	/**
	 * Recupera las tarjetas que se pueden activar para hacer compras por
	 * Internet.
	 * 
	 * @param clientId
	 * @return
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	@SuppressWarnings("unchecked")
	public InternetSalesResponseTO getTarjetasComprasInternet(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		InternetSalesResponseTO internetSalesResponseTO = new InternetSalesResponseTO();
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.getTarjetasComprasInternet(internetSalesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGetCardsInternetSales();
			XMLDecode decode = new XMLDecode();
			Collection<LabelValueBeanTO> tarjetas = (Collection<LabelValueBeanTO>) decode.buildCollectionBeans(LabelValueBeanTO.class, messageElement, "LabelValueBean");
			TarjetaTO tarjetaTO = (TarjetaTO) decode.buildBean(TarjetaTO.class, messageElement, "tarjetaVO");
			internetSalesResponseTO.setTarjetaTO(tarjetaTO);
			internetSalesResponseTO.setLabelValueCollection(tarjetas);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return internetSalesResponseTO;
	}

	/**
	 * Recupera el estado de la tarjeta para ser activado para hacer compras por
	 * internet.
	 * 
	 * @param clientId
	 * @param opcion
	 * @param tarjeta
	 * @return
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 */
	public InternetSalesResponseTO getComprasInternetEstadoTarjeta(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, EliteDataException, CuentasException, SessionExpiredException {
		InternetSalesResponseTO internetSalesResponseTO = new InternetSalesResponseTO();

		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.getComprasInternetEstadoTarjeta(internetSalesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateStateInternetSales();
			XMLDecode decode = new XMLDecode();
			TarjetaTO tarjetaTO = (TarjetaTO) decode.buildBean(TarjetaTO.class, messageElement, "tarjetaVO");
			internetSalesResponseTO.setTarjetaTO(tarjetaTO);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return internetSalesResponseTO;
	}

	/**
	 * Recupera el mensaje despu&eacute;s de la ejecuci&oacute;n de activar,
	 * desactivar y cambiar tarjetas para hacer compras por Internet.
	 * 
	 * @param clientId
	 * @param opcion
	 * @param tarjeta
	 * @return
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	@SuppressWarnings("unchecked")
	public InternetSalesResponseTO setComprasInternetActivacion(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {

		InternetSalesResponseTO internetSalesResponseTO = new InternetSalesResponseTO();
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.setComprasInternetActivacion(internetSalesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateSetActivationCardInternetSales();
			XMLDecode decode = new XMLDecode();
			Map<String, String> messages = (Map<String, String>) decode.buildMapBeans(messageElement, "org.apache.struts.action.ERROR");
			internetSalesResponseTO.setMensaje(messages.get("key_mensaje"));
			TarjetaTO tarjetaTO = (TarjetaTO) decode.buildBean(TarjetaTO.class, messageElement, "tarjetaVO");			
			internetSalesResponseTO.setTarjetaTO(tarjetaTO);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return internetSalesResponseTO;
	}

	@SuppressWarnings("unchecked")
	public LockUnlockCardsResponseTO findAllCardsLockandUnlock(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		LockUnlockCardsResponseTO lockUnlockCardsResponseTO = new LockUnlockCardsResponseTO();
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.listCardsToLockAndUnlock(lockUnlockCardsRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateListCardsToLockAndUnlock();
			XMLDecode decode = new XMLDecode();
			Collection<CancelacionElementTO> validCards = decode.buildCollectionBeans(CancelacionElementTO.class, messageElement, "CancelacionElement");
			lockUnlockCardsResponseTO.setTarjetasValidas(validCards);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		
		return lockUnlockCardsResponseTO;
	}

	public LockUnlockCardsResponseTO initBlockingCards(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteException, EliteDataException {
		return prepareGetEstado(lockUnlockCardsRequestTO);
	}

	public LockUnlockCardsResponseTO initUnlockingCards(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteException, EliteDataException {
		return prepareGetEstado(lockUnlockCardsRequestTO);

	}

	public LockUnlockCardsResponseTO confirmCardBlocking(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EliteException, EliteDataException {
		return confirmCancelacionDispatch(lockUnlockCardsRequestTO);
	}

	public LockUnlockCardsResponseTO confirmCardUnlock(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EliteException, EliteDataException {
		return confirmCancelacionDispatch(lockUnlockCardsRequestTO);
	}

	protected LockUnlockCardsResponseTO confirmCancelacionDispatch(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EliteException, EliteDataException {
		LockUnlockCardsResponseTO lockUnlockCardsResponseTO = new LockUnlockCardsResponseTO();
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.confirmCancelacionDispatch(lockUnlockCardsRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateConfirmCancelDispatch();
			XMLDecode decode = new XMLDecode();
			CancelacionElementTO tarjeta = new CancelacionElementTO();
			decode.buildBean(tarjeta, messageElement);
			lockUnlockCardsResponseTO.setTarjeta(tarjeta);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}

		return lockUnlockCardsResponseTO;
	}

	protected LockUnlockCardsResponseTO prepareGetEstado(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EliteException, EliteDataException {
		if (lockUnlockCardsRequestTO.getIndexCard() < -1) {
			throw new CuentasException("Index must be greater than 0");
		}
		LockUnlockCardsResponseTO lockUnlockCardsResponseTO = new LockUnlockCardsResponseTO();
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.prepareLockingState(lockUnlockCardsRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validatePrepareGetState();
			XMLDecode decode = new XMLDecode();
			CancelacionElementTO tarjeta = new CancelacionElementTO();
			decode.buildBean(tarjeta, messageElement);
			SecurityLevelTO securityLevelTO = new SecurityLevelTO();
			decode.buildBean(securityLevelTO, messageElement);
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return lockUnlockCardsResponseTO;
	}

	/**
	 * Recupera las tarjetas que puede ser cambiada la sucursal donde se
	 * entregan.
	 * 
	 * @param clientId
	 * @return ChangeBranchResponseTO
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 */
	@SuppressWarnings("unchecked")
	public CambioSucursalTO getCambioSucursalInicio(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		ChangeBranchResponseTO changeBranchResponse = new ChangeBranchResponseTO();
		CambioSucursalTO response=null;
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.getCambioSucursalInicio(changeBranchRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGetCardsChangeBranch();
			///cambio_centrodestino/noPermitido.jsp
			XMLDecode decode = new XMLDecode();
			
			String query="for $data in //elite return $data";
			Element element=XMLFinder.getElement(messageElement.toString(), query);
			messageElement=new MessageElement(element);
			response =(CambioSucursalTO) decode.buildBean(CambioSucursalTO.class, messageElement, "CambioCentroDestinoForm");
			
		} catch (DAOException de) {
			throw new CuentasException(de);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}catch (IOException xmle) {
			throw new CuentasException(xmle);
		}
		return response;
	}
	
	public CambioSucursalTO getDatosSucursalTarjeta(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CambioSucursalTO response=null;
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.getDatosSucursalTarjeta(user);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDatosCambioSucursal();
			
			XMLDecode decode = new XMLDecode();
			XmlDecoder decoder=new XmlDecoder();
			String query="for $data in //elite return $data";
			Element element=XMLFinder.getElement(messageElement.toString(), query);
			messageElement=new MessageElement(element);
			response =(CambioSucursalTO) decode.buildBean(CambioSucursalTO.class, messageElement, "CambioCentroDestinoForm");
			
			String cuentaSucursal=(String)decoder.toDeserialize("cuentaSucursal", messageElement,null);
			String tarjetaSucursal=(String)decoder.toDeserialize("tarjetaSucursal", messageElement,null);
			String tipoCuentaSucursal=(String)decoder.toDeserialize("tipoCuentaSucursal", messageElement,null);
			String estado="Activa";
			
			response.setEstadoTarjeta(estado);
			response.setCuentaSucursal(cuentaSucursal);
			response.setTarjetaSucursal(tarjetaSucursal);
			response.setTipoCuentaSucursal(tipoCuentaSucursal);
			
			
		} catch (DAOException de) {
			throw new CuentasException(de);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}catch (IOException xmle) {
			throw new CuentasException(xmle);
		}
		return response;
	}
	
	
	public CambioSucursalTO getDatosBusquedaSucursalesTarjeta(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		
		CambioSucursalTO cambioSucursalTO=new CambioSucursalTO();
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.getDatosBusquedaSucursales(user);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateBusquedaDatosSucursal();
			
			XMLDecode decode = new XMLDecode();
			String query="for $data in //elite/centroDestinoForm return <temp>{$data}</temp>";
			Element element=XMLFinder.getElement(messageElement.toString(), query);
			messageElement=new MessageElement(element);
			Collection<String>estados= decode.buildSimpleStringCollection(messageElement, "estados");
			
			cambioSucursalTO.setEstados(estados);
		} catch (DAOException de) {
			throw new CuentasException(de);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}catch (IOException xmle) {
			throw new CuentasException(xmle);
		}
		return cambioSucursalTO;
	}

	
	
	/**
	 * Recupera un Colletion<CodeTO>
	 * con las lista de los municipios del estado que se ha pasado como parametro en la clave.
	 * @param clientId
	 * @param estado
	 * @return 
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 */
	@SuppressWarnings("unchecked")
	public ChangeBranchResponseTO getMunicipiosEntidad(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		ChangeBranchResponseTO changeBranchResponse = new ChangeBranchResponseTO();
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.getMunicipiosEntidad(changeBranchRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGetMunicipChangeBranch();
			XMLDecode decode = new XMLDecode();
			Collection<CodeTO> municipios = decode.buildCollectionBeans(CodeTO.class, messageElement, "CodeLO");
			changeBranchResponse.setMunicipios(municipios);

		} catch (DAOException dex) {
			dex.printStackTrace();
			throw new CuentasException(dex);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
		return changeBranchResponse;
	}

	/**
	 * Recupera las sucursales que se encuentren en cierto rango; ya sea por
	 * C.P. o por Municipio.
	 * 
	 * @param clientId
	 * @param tipoBusqueda
	 * @param codigoPostal
	 * @param estado
	 * @param municipio
	 * @return
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	@SuppressWarnings("unchecked")
	public ChangeBranchResponseTO buscarCentrosCambioSucursal(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		ChangeBranchResponseTO changeBranchResponse = new ChangeBranchResponseTO();

		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			MessageElement messageElement = cuentasDao.buscarCentrosCambioSucursal(changeBranchRequestTO);
			
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			XMLDecode decode = new XMLDecode();
			
			if(changeBranchRequestTO.isEsBusquedaMunicipio()){
				cuentasRule.validateBusquedaDatosSucursal();	
				
				String query="for $data in //elite/centroDestinoForm return <temp>{$data}</temp>";
				Element element=XMLFinder.getElement(messageElement.toString(), query);		
				messageElement=new MessageElement(element);
				Collection<CodeTO>codTOCollection = decode.buildCollectionBeans(CodeTO.class, messageElement, "CodeLO");
				changeBranchResponse.setMunicipios(codTOCollection);
				
			}else{
				cuentasRule.validateSearchChangeBrach();
				
				DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
				DispositivoHuellaTO dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
				
				decode = new XMLDecode();
				Collection<ResultadoBusquedaSucursalTO> sucursales = decode.buildCollectionBeans(ResultadoBusquedaSucursalTO.class, messageElement, "ResultadoBusqueda");
				
				
				changeBranchResponse.setLlavePublica(dispositivoHuellaTO.getLlavePublica());
				changeBranchResponse.setLongitudHuella(dispositivoHuellaTO.getLongitudHuella());
				
				changeBranchResponse.setSucursales(sucursales);
				
			}
		} catch (DAOException dex) {
			throw new CuentasException(dex);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}catch(IOException ioException){
			throw new CuentasException(ioException);
		}
		return changeBranchResponse;
	}
	
	
	public CambioSucursalTO confirmaCambioSucursalTarjeta(ChangeBranchRequestTO branchRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		
		CuentasDAO cuentasDAO=new CuentasDAO();
		CambioSucursalTO response=null;
		try{
			
			MessageElement messageElement=cuentasDAO.confirmaCambioSucursal(branchRequestTO);
			
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			
			cuentasRule.validateConfirmaCambioSucursal();
			XMLDecode decode = new XMLDecode();
			String query="for $data in //elite return $data";
			Element element=XMLFinder.getElement(messageElement.toString(), query);
			messageElement=new MessageElement(element);
			response =(CambioSucursalTO) decode.buildBean(CambioSucursalTO.class, messageElement, "CambioCentroDestinoForm");						
			response.setEstadoTarjeta("Activa");
			
			
		}catch (DAOException e) {
			throw new CuentasException(e);
		}catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}catch (IOException e) {
			throw new CuentasException(e);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public AlertsDataResponseTO alertsLoadInitialData(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO = null;
		try {

			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.findAlertsLoadInitialData(user);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAlertsLoadInitialData();
			XMLDecode decode = new XMLDecode();

			Collection<AlertsAcountsTO> initialDataCollection = new ArrayList<AlertsAcountsTO>();

			Map<String, String> initialData = decode.buildMapBeans(messageElement, "datosIniciales");
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			Collection<AlertsCardsTO> alertsCardsCollection = (Collection<AlertsCardsTO>) decode.buildCollectionBeans(AlertsCardsTO.class, messageElement, "AlertaTarjetaBAVO");
			
			int size = Integer.parseInt(initialData.get("datosExistentes"));
			for (int j = 1; j <= size; j++) {
				AlertsAcountsTO alertsAcountsTO = new AlertsAcountsTO();
				alertsAcountsTO.setCompania(initialData.get("compania" + j));
				alertsAcountsTO.setCuenta(initialData.get("cuenta" + j));
				alertsAcountsTO.setRetiro(initialData.get("retiro" + j));
				log.info("-------------Retiro" + initialData.get("retiro" + j));
				alertsAcountsTO.setDeposito(initialData.get("deposito" +j));
				alertsAcountsTO.setTelefono(initialData.get("telefono" + j));
				alertsAcountsTO.setCuentaFormateada(initialData.get("cuentaFormateada" + j));
				alertsAcountsTO.setModo(initialData.get("modo" + j));
				alertsAcountsTO.setTipo(initialData.get("tipo" + j));
				alertsAcountsTO.setDatosExistentes(initialData.get("datosExistentes"));// Sin
				// j
				initialDataCollection.add(alertsAcountsTO);
			}

			log.info("CuentasSLBean.alertsLoadInitialData()  ---------------- " + alertsCardsCollection.size());

			alertsDataResponseTO = new AlertsDataResponseTO();
			alertsDataResponseTO.setInitialData(initialData);
			alertsDataResponseTO.setDatosEliteTO(datosEliteTO);
			alertsDataResponseTO.setAlertsAcounts(initialDataCollection);
			alertsDataResponseTO.setAlertsCards(alertsCardsCollection);
			
			for (AlertsCardsTO to : alertsCardsCollection) {
				log.info(to.getStatus());
				log.info(to.getNumeroTarjeta());
				log.info(to.getTipoTarjeta());
			}

		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return alertsDataResponseTO;
	}

	public AlertsDataResponseTO setAlertsAcountsSelectedLink(AlertsDataRequest request) throws EJBException, CuentasException, EliteDataException, SessionExpiredException {
		AlertsDataResponseTO alertsDataResponseTO = new AlertsDataResponseTO();
		HashMap<String, String> mappedBeans = null;
		XmlDecoder decoder = new XmlDecoder();
		XMLDecode decode = new XMLDecode();
		CuentasDAO cuentasDAO = new CuentasDAO();
		Map<String, String> mapDatosMostrar = null;
		Map<String, String> mapCompania = null;
		try {

			MessageElement messageElement = cuentasDAO.getAlertsAcountsSelectedLink(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAlertsAcountsSelectedLinkActive();
			
			mapDatosMostrar = (Map) decoder.toDeserialize("datosMostrar", messageElement, mappedBeans);
			alertsDataResponseTO.setInitialData(mapDatosMostrar);
			
			Collection<AlertCompaniaTO> compañias = (Collection<AlertCompaniaTO>) decode.buildCollectionBeans(AlertCompaniaTO.class, messageElement, "DatosAlertasCelular");
			
			mapCompania = new HashMap<String, String>();
			
			int indice = 0;
			int contador = 0;
			for(AlertCompaniaTO compañiasCollection: compañias){
				mapCompania.put(compañiasCollection.getIdCompania(), compañiasCollection.getCompania());
				
				contador ++;
			}
			
			indice = contador;
			mapCompania.put("total", ""+indice);
			
			alertsDataResponseTO.setInitialCompCel(mapCompania);

		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return alertsDataResponseTO;
	}

	public AlertsDataResponseTO getDataAlertFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO= new AlertsDataResponseTO();
		XmlDecoder decoder = new XmlDecoder();
		CuentasDAO cuentasDAO = new CuentasDAO();
		Map<String, String> mapDatosMostrar = null;
		HashMap<String, String> mappedBeans = null;
		try {
			MessageElement messageElement = cuentasDAO.getDataAlertFirstStep(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataAlertFirstStep();
			
			messageElement = cuentasDAO.confirmAlertFirstStep(request);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateconfirmAlertFirstStep();
			
			mapDatosMostrar = (Map) decoder.toDeserialize("datosMostrar", messageElement, mappedBeans);
			alertsDataResponseTO.setInitialData(mapDatosMostrar);
			
			XMLDecode decode = new XMLDecode();
			String clave = decode.getString(messageElement, "sssCLAVEACTIVACION");			
			alertsDataResponseTO.setSClaveConfirmacion(clave);
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} 
		return alertsDataResponseTO;
	}

	public AlertsDataResponseTO getDataAlertSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO = new AlertsDataResponseTO();
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataAlertSecondStep(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataAlertSecondStep();
			
			
			messageElement = cuentasDAO.confirmAlertSecondStep(request);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateconfirmAlertSecondStep();
			
			// TODO Validar sólo cuando sea nivel medio.
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			alertsDataResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);		

		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} 
		return alertsDataResponseTO;
	}

	public void getDataAlertThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataAlertThirdStep(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataAlertThirdStep();			
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} 
	}

/**Modifica Cuentas**/
	public AlertsDataResponseTO getAlertsAcountsSelectedLinkUpdate(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO = new AlertsDataResponseTO();
		HashMap<String, String> mappedBeans = null;
		XmlDecoder decoder = new XmlDecoder();	
		XMLDecode decode = new XMLDecode();
		try {			
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getAlertsAcountsSelectedLinkUpdate(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAlertsAcountsSelectedLinkUpdate();	
			Map<String, String> map = (Map) decoder.toDeserialize("datosMostrar", messageElement, mappedBeans);
			alertsDataResponseTO.setInitialData(map);
			
			Collection<AlertCompaniaTO> compañias = (Collection<AlertCompaniaTO>) decode.buildCollectionBeans(AlertCompaniaTO.class, messageElement, "DatosAlertasCelular");
			
			Map<String, String> mapCompania = new HashMap<String, String>();
			
			int indice = 0;
			int contador = 0;
			for(AlertCompaniaTO compañiasCollection: compañias){
				mapCompania.put(compañiasCollection.getIdCompania(), compañiasCollection.getCompania());
				
				contador ++;
			}
			
			indice = contador;
			mapCompania.put("total", ""+indice);
			
			alertsDataResponseTO.setInitialCompCel(mapCompania);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} 
		return alertsDataResponseTO;
	}

	
	public AlertsDataResponseTO getDataForUpdateAlertFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO= new AlertsDataResponseTO();
		XmlDecoder decoder = new XmlDecoder();
		XMLDecode decode = new XMLDecode();
		HashMap<String, String> mappedBeans = null;
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			
			MessageElement messageElement = cuentasDAO.getDataForUpdateAlertFirstStep(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataForUpdateAlertFirstStep();	
			
			messageElement = cuentasDAO.getDataForUpdateAlertFirstStepInicioMec(request);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataForUpdateAlertFirstStepInicioMec();
			
			//obtenemos la clave de activacion
			String clave = decode.getString(messageElement, "sssCLAVEACTIVACION");
			Map<String, String> map = (Map) decoder.toDeserialize("datosMostrar", messageElement, mappedBeans);
			alertsDataResponseTO.setInitialData(map);			
			alertsDataResponseTO.setSClaveConfirmacion(clave);		
			
			//obtenemos la huella
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			alertsDataResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);			
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		}
		return alertsDataResponseTO;
	}
	
	public AlertsDataResponseTO getDataForUpdateAlertFirstStepModificar(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO= new AlertsDataResponseTO();
		XmlDecoder decoder = new XmlDecoder();
		XMLDecode decode = new XMLDecode();
		HashMap<String, String> mappedBeans = null;
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			
			MessageElement messageElement = cuentasDAO.getDataForUpdateAlertFirstStepModificar(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataForUpdateAlertFirstStepModifica();		
			
			messageElement = cuentasDAO.confirmDataForUpdateAlertFirstStep(request);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataForUpdateAlertFirstStepModificaConfirmacion();
			
			//obtenemos la clave de activacion
			String clave = decode.getString(messageElement, "sssCLAVEACTIVACION");
			Map<String, String> map = (Map) decoder.toDeserialize("datosMostrar", messageElement, mappedBeans);
			alertsDataResponseTO.setInitialData(map);			
			alertsDataResponseTO.setSClaveConfirmacion(clave);		
			
			//Obtenemos la huella
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			alertsDataResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);			

		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		}
		return alertsDataResponseTO;
	}

	public AlertsDataResponseTO getDataForUpdateAlertFinalStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO = new AlertsDataResponseTO();
		try {
			
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForUpdateAlertFinalStep(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDataForUpdateAlertSecondStep();
			
			messageElement = cuentasDAO.confirmDataForUpdateAlertSecondStep(request);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateConfirmDataForUpdateAlertSecondStep();
		} catch (DAOException e) {
			throw new CuentasException(e);
		} 
		return alertsDataResponseTO;
	}

	public void getDataForUpdateAlertThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForUpdateAlertThirdStep(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acMM.do?seleccion=inicioMM")) {
				throw new CuentasException("El path no fue el esperado . . . paso 3");
			} else {
				messageElement = cuentasDAO.confirmDataForUpdateAlertSecondStep(request);
				datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
				if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acModificaModifica.jsp")) {
					throw new CuentasException("El path no fue el esperado . . . confirmar paso 3");
				}
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
/*End Modifica cuentas*/
	public void getAlertCardAcountsSelectedLink(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getAlertCardAcountsSelectedLink(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/activacion.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . paso 1 de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	public void getDataForActivateCardsFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForActivateCardsFirstStep(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/mensajeMTA.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . paso 2 de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	public void getDataForActivateCardsSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForActivateCardsSecondStep(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/confirmaMTA.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . paso 3 de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	public void getDataForActivateCardsThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForActivateCardsThirdStep(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/exitoMTA.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . paso 4 de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	public void getAlertsCardSelectedLinkUpdate(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getAlertsCardSelectedLinkUpdate(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/modifica/modPrincipal.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . paso 4 de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	public void getDataForModifyCardsFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForModifyCardsFirstStep(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/modifica/modConfirma.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . paso 4 de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	public void getDataForModifyCardsSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForModifyCardsSecondStep(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/modifica/modifica.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . paso 4 de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	/*
	 * public void getDataModifyCardsThirdStep (AlertsDataRequest request)
	 * throws RemoteException, CuentasException, SessionExpiredException,
	 * EliteDataException{ try { CuentasDAO cuentasDAO = new CuentasDAO();
	 * MessageElement messageElement =
	 * cuentasDAO.getDataModifyCardsThirdStep(request); XMLDecode decode = new
	 * XMLDecode(); DatosEliteTO datosEliteTO = (DatosEliteTO)
	 * decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO"); if
	 * (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acMON.do?seleccion=inicioMON")) {
	 * throw new CuentasException("El path no fue el esperado . . . paso 4 de
	 * tarjetas"); }else { messageElement =
	 * cuentasDAO.confirmModifyCardsThirdStep(request); datosEliteTO =
	 * (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement,
	 * "DatosEliteVO"); if
	 * (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acModificaOtroNumero.jsp")) {
	 * throw new CuentasException("El path no fue el esperado . . . al confirmar
	 * paso 1"); } } } catch (DAOException e) { throw new CuentasException(e); }
	 * catch (XmlDecodeException e) { throw new CuentasException(e); } }
	 */

	// Elimina alertas de tarjetas
	public void getDataForDeleteCards(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.getDataForDeleteCards(request);
			XMLDecode decode = new XMLDecode();
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, messageElement, "DatosEliteVO");
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/tarjetaBA/elimina/eliminaConfirma.jsp")) {
				throw new CuentasException("El path no fue el esperado . . . al borrar alertas de tarjetas");
			}
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}

	public AlertsDataResponseTO getDataForDeleteAccounts(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AlertsDataResponseTO alertsDataResponseTO= new AlertsDataResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {			
			MessageElement messageElement = cuentasDAO.getDataForDeleteAccounts(request);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDeleteAlertaCelular();
			
			messageElement = cuentasDAO.confirmDeleteAccounts(request);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateDeleteAlertaCelularConfirmacion();
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		}
		return alertsDataResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public InfiniteMovimientosResponseTO getInfiniteDetalleMovimientos(InfiniteMovimientosRequestTO infiniteMovimientosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		InfiniteMovimientosResponseTO infiniteMovimientosResponseTO = new InfiniteMovimientosResponseTO();
		InfiniteDetalleMovimientosSaldoActualTO saldoActualTO = new InfiniteDetalleMovimientosSaldoActualTO();
		InfiniteDetalleMovimientosSaldoCorteTO saldoCorteTO = new InfiniteDetalleMovimientosSaldoCorteTO();
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.findInfiniteMovimientosCuenta(infiniteMovimientosRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGetDetalleMovimientosInfinite();
			XMLDecode decode = new XMLDecode();
			XmlDecoder decoder = new XmlDecoder();
//			Map<String, ?> map = decode.builMixMap(CreditosMovimientosTO.class, messageElement, "detalleMovimientos");
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CreditosMovimientosBean", "com.bancoazteca.elite.beans.CreditosMovimientosTO");
			Map<String, ?> map = (Map)decoder.toDeserialize("detalleMovimientos", messageElement, mappedBeans);
			
			saldoActualTO = getInfiniteDetalleMovimientosSaldoActual(map);
			saldoCorteTO = getInfiniteDetalleMovimientosSaldoCorte(map);
			
			Collection<CreditosMovimientosTO> movimientosInfinite=saldoCorteTO.getTitular().getTitular();
			if(saldoCorteTO != null){
				saldoCorteTO.getTitular().setTitular(formatDateInfinite(movimientosInfinite));
			}
			saldoCorteTO.setAntesCorte(formatDateInfinite(saldoCorteTO.getAntesCorte()));
			Collection<InfiniteAdicionalTO> adicionales = saldoCorteTO.getAdicionales();
			saldoCorteTO.setAdicionales(formatDateAdicionales(adicionales));
			if(saldoActualTO!= null){
				saldoActualTO.getTitular().setTitular(formatDateInfinite(saldoActualTO.getTitular().getTitular()));
			}
//			InfiniteMovimientosResponseTO infiniteMovimientosResponseTO2 = decoder.toDeserialize("CargosAplicarVO", messageElement, mappedBeans)
			Collection<CargosPorAplicarInfiniteTO> cargosPorAplicar = decode.buildCollectionBeans(CargosPorAplicarInfiniteTO.class, messageElement, "CargosAplicarVO");
			infiniteMovimientosResponseTO = getInfiniteSaldosTotales(decode, messageElement);
			infiniteMovimientosResponseTO.setSaldoActualTO(saldoActualTO);
			infiniteMovimientosResponseTO.setSaldoCorteTO(saldoCorteTO);
			infiniteMovimientosResponseTO.setCargosPorAplicar(cargosPorAplicar);
						
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return infiniteMovimientosResponseTO;
	}
	
	private Collection<CreditosMovimientosTO> formatDateInfinite(Collection<CreditosMovimientosTO> movimientos){
		if(movimientos==null){
			return movimientos;
		}
		ArrayList<CreditosMovimientosTO> movimientosFormateados = (ArrayList<CreditosMovimientosTO>)movimientos;
		ArrayList<CreditosMovimientosTO> movimientosSinFormatear = (ArrayList<CreditosMovimientosTO>)movimientos;
		for(int i=0; i<movimientosSinFormatear.size(); i++){
			movimientosFormateados.get(i).setFechaDeAplicacion(Formatter.formatDate(movimientosSinFormatear.get(i).getFechaDeAplicacion()));
			movimientosFormateados.get(i).setFechaDeOperacion(Formatter.formatDate(movimientosSinFormatear.get(i).getFechaDeOperacion()));
		}
		return movimientosFormateados;
	}
	
	private Collection<InfiniteAdicionalTO> formatDateAdicionales(Collection<InfiniteAdicionalTO> infiniteAdicionales){
		if(infiniteAdicionales==null){
			return infiniteAdicionales;
		}
		ArrayList<InfiniteAdicionalTO> adicionales = (ArrayList<InfiniteAdicionalTO>)infiniteAdicionales;
		for(int i=0; i<adicionales.size(); i++){
			adicionales.get(i).setAdicionales(formatDateInfinite(adicionales.get(i).getAdicionales()));
		}
		return adicionales;
	}
	
	@SuppressWarnings("unchecked")
	public InfiniteMovimientosResponseTO getInfiniteAdicionalesDetalleMovimientos(InfiniteMovimientosRequestTO infiniteMovimientosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		InfiniteMovimientosResponseTO infiniteMovimientosResponseTO = new InfiniteMovimientosResponseTO();
		InfiniteDetalleMovimientosSaldoActualTO saldoActualTO = new InfiniteDetalleMovimientosSaldoActualTO();
		InfiniteDetalleMovimientosSaldoCorteTO saldoCorteTO = new InfiniteDetalleMovimientosSaldoCorteTO();
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.findInfiniteMovimientosCuenta(infiniteMovimientosRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGetDetalleMovimientosInfinite();
			XMLDecode decode = new XMLDecode();
			Map<String, ?> map = decode.builMixMap(CreditosMovimientosTO.class, messageElement, "detalleMovimientos");
			saldoActualTO = getInfiniteDetalleMovimientosSaldoActual(map);
			saldoCorteTO = getInfiniteDetalleMovimientosSaldoCorte(map);
			infiniteMovimientosResponseTO = getInfiniteSaldosTotales(decode, messageElement);
			
			Collection<CreditosMovimientosTO> movimientosInfinite=saldoCorteTO.getTitular().getTitular();
			saldoCorteTO.getTitular().setTitular(formatDateInfinite(movimientosInfinite));
			
			Collection<InfiniteAdicionalTO> adicionales = saldoCorteTO.getAdicionales();
			saldoCorteTO.setAdicionales(formatDateAdicionales(adicionales));
			
			infiniteMovimientosResponseTO.setSaldoActualTO(saldoActualTO);
			infiniteMovimientosResponseTO.setSaldoCorteTO(saldoCorteTO);
						
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return infiniteMovimientosResponseTO;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private InfiniteDetalleMovimientosSaldoActualTO getInfiniteDetalleMovimientosSaldoActual(Map<String, ?> map){		
		InfiniteDetalleMovimientosSaldoActualTO saldoActualTO = new InfiniteDetalleMovimientosSaldoActualTO();
		Map<String, ?> saldoActualMap = (Map<String, ?>) map.get("detalleMovimientosSaldoActual");
		if (saldoActualMap!=null && saldoActualMap.size()>0){
			String numGrupos = (String) saldoActualMap.get("NUMGRUPOS");
			int numeroGrupos = Integer.parseInt(numGrupos);
				
			Collection<String> monedaExtranjeraAbonos = new ArrayList<String>();		
			Collection<String> secPart = new ArrayList<String>();
			Collection<String> monedaExtranjeraCargos = new ArrayList<String>();
			
			Collection<Collection<CreditosMovimientosTO>> adicionalesData = new ArrayList<Collection<CreditosMovimientosTO>>();
			Collection<CreditosMovimientosTO> titularData = new ArrayList<CreditosMovimientosTO>();
			
			Collection<InfiniteTitularTO> titular = new ArrayList<InfiniteTitularTO>();
			Collection<InfiniteAdicionalTO> adicionales = new ArrayList<InfiniteAdicionalTO>();
			
			for(int j=1;j<=numeroGrupos;j++){
				if(j > 1){
					Collection<CreditosMovimientosTO> adicionalCollection = (Collection<CreditosMovimientosTO>) saldoActualMap.get("GRUPO"+j);
					addModenaOriginal(adicionalCollection);
					adicionalesData.add(adicionalCollection);
				}
				else{
					titularData = (Collection<CreditosMovimientosTO>) saldoActualMap.get("GRUPO"+j);
					addModenaOriginal(titularData);
				}		
			}
			InfiniteTitularTO infiniteTitularTO = null;
			for(int k=1;k<=numeroGrupos;k++){
				BigDecimal cargo = new BigDecimal((String) saldoActualMap.get("CARGOS"+k));
				BigDecimal abono = new BigDecimal((String) saldoActualMap.get("ABONOS"+k));
				String extranjeraAbonos = (String) saldoActualMap.get("monedaExtranjeraAbonos"+k);
				monedaExtranjeraAbonos.add(extranjeraAbonos);				
				String secParts = (String) saldoActualMap.get("SECPART"+k);
				secPart.add(secParts);
				String extranjeraCargos = (String) saldoActualMap.get("monedaExtranjeraCargos"+k);
				monedaExtranjeraCargos.add(extranjeraCargos);
							
				List<Collection<CreditosMovimientosTO>> adicionalDataList = (List<Collection<CreditosMovimientosTO>>) adicionalesData;
				
				if(k==1){
					infiniteTitularTO = new InfiniteTitularTO();
					infiniteTitularTO.setTitular(titularData);
					infiniteTitularTO.setCargos(cargo);
					infiniteTitularTO.setAbonos(abono);
					titular.add(infiniteTitularTO);
				}else{
					InfiniteAdicionalTO infiniteAdicionalTO = new InfiniteAdicionalTO();
					infiniteAdicionalTO.setAdicionales(adicionalDataList.get(k-2));
					infiniteAdicionalTO.setCargos(cargo);
					infiniteAdicionalTO.setAbonos(abono);
					adicionales.add(infiniteAdicionalTO);
				}
			}
			
			String participe = (String) saldoActualMap.get("PARTICIPE1");
			saldoActualTO.setNumGrupos(numGrupos);
			saldoActualTO.setTitular(infiniteTitularTO);
			saldoActualTO.setAdicionales(adicionales);		
			saldoActualTO.setMonedaExtranjeraAbonos(monedaExtranjeraAbonos);		
			saldoActualTO.setSecPart(secPart);
			saldoActualTO.setMonedaExtranjeraCargos(monedaExtranjeraCargos);
			saldoActualTO.setParticipe(participe);
		}else{
			saldoActualTO = null;
		}
				
		return saldoActualTO;
	}

	@SuppressWarnings("unchecked")
	private InfiniteDetalleMovimientosSaldoCorteTO getInfiniteDetalleMovimientosSaldoCorte(Map<String, ?> map){
		InfiniteDetalleMovimientosSaldoCorteTO saldoCorteTO = new InfiniteDetalleMovimientosSaldoCorteTO();
		Map<String, ?> saldoCorteMap = (Map<String, ?>) map.get("detalleMovimientosSaldoCorte");
		String numGrupos = (String) saldoCorteMap.get("NUMGRUPOS");
		int numeroGrupos = Integer.parseInt(numGrupos);				
		Collection<String> monedaExtranjeraAbonos = new ArrayList<String>();		
		Collection<String> secPart = new ArrayList<String>();
		Collection<String> monedaExtranjeraCargos = new ArrayList<String>();
		Collection<Collection<CreditosMovimientosTO>> adicionalesData = new ArrayList<Collection<CreditosMovimientosTO>>();
		Collection<CreditosMovimientosTO> titularData = new ArrayList<CreditosMovimientosTO>();
		Collection<InfiniteTitularTO> titular = new ArrayList<InfiniteTitularTO>();
		Collection<InfiniteAdicionalTO> adicionales = new ArrayList<InfiniteAdicionalTO>();
		
		for(int j=1;j<=numeroGrupos;j++){
			if(j > 1){
				Collection<CreditosMovimientosTO> adicionalCollection = (Collection<CreditosMovimientosTO>) saldoCorteMap.get("GRUPO"+j);
				addModenaOriginal(adicionalCollection);
				adicionalesData.add(adicionalCollection);
			}
			else{				
				titularData = (Collection<CreditosMovimientosTO>) saldoCorteMap.get("GRUPO"+j);
				addModenaOriginal(titularData);
			}		
		}
		InfiniteTitularTO infiniteTitularTO = null;
		for(int k=1;k<=numeroGrupos;k++){
			BigDecimal cargo = new BigDecimal((String) saldoCorteMap.get("CARGOS"+k));
			BigDecimal abono = new BigDecimal((String) saldoCorteMap.get("ABONOS"+k));
			String extranjeraAbonos = (String) saldoCorteMap.get("monedaExtranjeraAbonos"+k);
			
			monedaExtranjeraAbonos.add(extranjeraAbonos);				
			String secParts = (String) saldoCorteMap.get("SECPART"+k);
			secPart.add(secParts);
			String extranjeraCargos = (String) saldoCorteMap.get("monedaExtranjeraCargos"+k);
			monedaExtranjeraCargos.add(extranjeraCargos);
			List<Collection<CreditosMovimientosTO>> adicionalDataList = (List<Collection<CreditosMovimientosTO>>) adicionalesData;
			if(k==1){
				infiniteTitularTO = new InfiniteTitularTO();
				infiniteTitularTO.setTitular(titularData);
				infiniteTitularTO.setCargos(cargo);
				infiniteTitularTO.setAbonos(abono);
				titular.add(infiniteTitularTO);
			}else{
				InfiniteAdicionalTO infiniteAdicionalTO = new InfiniteAdicionalTO();
				infiniteAdicionalTO.setAdicionales(adicionalDataList.get(k-2));
				infiniteAdicionalTO.setCargos(cargo);
				infiniteAdicionalTO.setAbonos(abono);
				adicionales.add(infiniteAdicionalTO);
			}
		}			
		Map<String, ?> detalleCorteMap = (Map<String, ?>) saldoCorteMap.get("divPagos");
		if(detalleCorteMap != null){
			if(detalleCorteMap.size()>0){
				BigDecimal addMonto = new BigDecimal(0);
				Collection<CreditosMovimientosTO> antesCorte = null;
				Collection<CreditosMovimientosTO> despuesCorte = null;
				try{
					antesCorte = (ArrayList<CreditosMovimientosTO>) detalleCorteMap.get("antes");
					if(antesCorte!=null){
						for(CreditosMovimientosTO movimientosTO : antesCorte ){
							addMonto = addMonto.add(movimientosTO.getAbonos());
						}
						saldoCorteTO.setSumaCargosAntes(addMonto);
						log.info("sumatoria antes del corte: " + addMonto);
						addMonto = new BigDecimal(0);
					}
				}catch (Throwable e){
					log.info("Se genero un error al obtener la coleccion del saldo al Corte antes de la fecha limite: " + e.getMessage());
				}	
				try{
					despuesCorte = (ArrayList<CreditosMovimientosTO>) detalleCorteMap.get("despues");
					if(despuesCorte!=null){
						for(CreditosMovimientosTO movimientosTO : despuesCorte ){
							addMonto = addMonto.add(movimientosTO.getAbonos());
						}
						saldoCorteTO.setSumaCargosDespues(addMonto);
						log.info("sumatoria despues del corte: " + addMonto);
					}
				}catch (Throwable e){
					log.info("Se genero un error al obtener la coleccion del saldo al Corte despues de la fecha limite:" + e.getMessage());
				}
				saldoCorteTO.setDespuesCorte(despuesCorte);
				saldoCorteTO.setAntesCorte(antesCorte);
			}
		}
		String participe = (String) saldoCorteMap.get("PARTICIPE1");
		saldoCorteTO.setNumGrupos(numGrupos);
		saldoCorteTO.setTitular(infiniteTitularTO);
		saldoCorteTO.setAdicionales(adicionales);		
		saldoCorteTO.setMonedaExtranjeraAbonos(monedaExtranjeraAbonos);	
		saldoCorteTO.setSecPart(secPart);
		saldoCorteTO.setMonedaExtranjeraCargos(monedaExtranjeraCargos);
		saldoCorteTO.setParticipe(participe);
		
		return saldoCorteTO;
	}
	
	private InfiniteMovimientosResponseTO getInfiniteSaldosTotales(XMLDecode decode, MessageElement messageElement)throws XmlDecodeException{		
		InfiniteMovimientosResponseTO infiniteMovimientosResponseTO = new InfiniteMovimientosResponseTO();
	
		String fechaInicial = decode.getString(messageElement, "fechaInicialFormateada");
		String fechaFinal = decode.getString(messageElement, "fechaFinalFormateada");
		String fechaFinalMas = decode.getString(messageElement, "fechaFinalFormateadaMas");
		String fechaHoy = decode.getString(messageElement, "fechaHoy");
		String numCorte = decode.getString(messageElement, "corteMostrar");
		log.info("numCorte: " + numCorte);
		String consultaPorCorte = decode.getString(messageElement, "consultaPorCorte");
				
		BigDecimal saldoAnterior = new BigDecimal(decode.getString(messageElement, "saldoAnterior"));
		BigDecimal sumaCargosCorte = new BigDecimal(decode.getString(messageElement, "sumaCargosSaldoAlCorte"));
		BigDecimal sumaAbonosCorte = new BigDecimal(decode.getString(messageElement, "sumaAbonosSaldoAlCorte"));
		BigDecimal sumaCargosActual = new BigDecimal(decode.getString(messageElement, "sumaCargos"));
		BigDecimal sumaAbonosActual = new BigDecimal(decode.getString(messageElement, "sumaAbonos"));
		
		infiniteMovimientosResponseTO.setFechaInicial(fechaInicial);
		infiniteMovimientosResponseTO.setFechaFinal(fechaFinal);
		infiniteMovimientosResponseTO.setFechaFinalMas(fechaFinalMas);
		infiniteMovimientosResponseTO.setFechaHoy(fechaHoy);
		infiniteMovimientosResponseTO.setNumCorte(numCorte);
		infiniteMovimientosResponseTO.setConsultaPorCorte(consultaPorCorte);	
		infiniteMovimientosResponseTO.setSaldoAnterior(saldoAnterior);
		infiniteMovimientosResponseTO.setSumaCargosCorte(sumaCargosCorte);
		infiniteMovimientosResponseTO.setSumaAbonosCorte(sumaAbonosCorte);
		infiniteMovimientosResponseTO.setSumaCargosActual(sumaCargosActual);
		infiniteMovimientosResponseTO.setSumaAbonosActual(sumaAbonosActual);
			
		return infiniteMovimientosResponseTO;
	}
	
	private void addModenaOriginal(Collection<CreditosMovimientosTO> collection){
		for (CreditosMovimientosTO creditosMovimientosTO : collection){
			try{		
				creditosMovimientosTO.setMonedaOriginal(new BigDecimal(creditosMovimientosTO.getCargos().doubleValue()/creditosMovimientosTO.getTipoCambio().doubleValue()));
			}catch (Throwable e){}
		}
	}
	
	public TarjetaCorporativaCreditoResponseTO getTarjetaCorporativaCredito(TarjetaCorporativaCreditoRequestTO corporativaCreditoRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		TarjetaCorporativaCreditoResponseTO corporativaCreditoResponseTO = new TarjetaCorporativaCreditoResponseTO();
		CorporativaCreditoDetalleMovimientosTO creditoDetalleMovimientosTO = new CorporativaCreditoDetalleMovimientosTO();
		CorporativaCreditoCargosAplicarTO creditoCargosAplicarTO = new CorporativaCreditoCargosAplicarTO();
		TarjetaCorporativaCreditoTO tarjetaCorporativaCreditoTO = new TarjetaCorporativaCreditoTO();
		
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.findTarjetaCorporativaCredito(corporativaCreditoRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGetTarjetaCorporativaCredito();
			XMLDecode decode = new XMLDecode();
			//Map<String, ?> map = decode.builMixMap(CreditosMovimientosTO.class, messageElement, "detalleMovimientos");
			corporativaCreditoResponseTO = getCorporativaCreditoSaldosTotales(decode, messageElement);
			
			corporativaCreditoResponseTO.setTarjetaCorporativaTO(tarjetaCorporativaCreditoTO);
			corporativaCreditoResponseTO.setCorporativaCreditoDetalleMovimientosTO(creditoDetalleMovimientosTO);
			corporativaCreditoResponseTO.setCorporativaCreditoCargosAplicarTO(creditoCargosAplicarTO);
						
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return corporativaCreditoResponseTO;
	}
	
	private TarjetaCorporativaCreditoResponseTO getCorporativaCreditoSaldosTotales(XMLDecode decode, MessageElement messageElement)throws XmlDecodeException{		
		TarjetaCorporativaCreditoResponseTO corporativaCreditoResponseTO = new TarjetaCorporativaCreditoResponseTO();
	
		String tipoTarjeta = decode.getString(messageElement, "tipoTarjeta");
		BigDecimal total = new BigDecimal(decode.getString(messageElement, "total"));
		BigDecimal totalisimo = new BigDecimal(decode.getString(messageElement, "totalisimo"));
		BigDecimal totalCorte = new BigDecimal(decode.getString(messageElement, "totalCorte"));
		String puedePagar = decode.getString(messageElement, "puedePagar");
		String tipoPago = decode.getString(messageElement, "tipoPago");
		BigDecimal totalAbono = new BigDecimal(decode.getString(messageElement, "totalAbono"));
		String fechaEncabezado = decode.getString(messageElement, "fechaEncabezado");
		
		corporativaCreditoResponseTO.setTipoTarjeta(tipoTarjeta);
		corporativaCreditoResponseTO.setTotal(total);
		corporativaCreditoResponseTO.setTotalisimo(totalisimo);
		corporativaCreditoResponseTO.setTotalCorte(totalCorte);
		corporativaCreditoResponseTO.setPuedePagar(puedePagar);
		corporativaCreditoResponseTO.setTipoPago(tipoPago);
		corporativaCreditoResponseTO.setTotalAbono(totalAbono);
		corporativaCreditoResponseTO.setFechaEncabezado(fechaEncabezado);
		
		return corporativaCreditoResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public TarjetaCorporativaDebitoResponseTO getTarjetaCorporativaDebito(TarjetaCorporativaDebitoRequestTO tarjetaCorporativaDebitoRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		TarjetaCorporativaDebitoResponseTO corporativaDebitoResponseTO = new TarjetaCorporativaDebitoResponseTO();
		CorporativaDebitoMovimientosTO debitoMovimientosTO = new CorporativaDebitoMovimientosTO();
		TarjetaCorporativaDebitoTO tarjetaCorporativaDebitoTO = new TarjetaCorporativaDebitoTO();
		
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			MessageElement messageElement = cuentasDAO.findTarjetaCorporativaDebito(tarjetaCorporativaDebitoRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGetTarjetaCorporativaDebito();
			XMLDecode decode = new XMLDecode();
			
			@SuppressWarnings("unused")
			Map<String, ?> map = decode.builMixMap(CreditosMovimientosTO.class, messageElement, "detalleMovimientos");
			
			corporativaDebitoResponseTO.setCorporativaDebitMovimientosTO(debitoMovimientosTO);
			corporativaDebitoResponseTO.setCorporativaDebitoTO(tarjetaCorporativaDebitoTO);
			
									
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return corporativaDebitoResponseTO;
	}	
	
	public CuentasFrecuentesResponseTO findAllFrequentAccounts(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
		ArrayList<CuentasFrecuentesTO> frequentAccounts = new ArrayList<CuentasFrecuentesTO>();
		ArrayList<String> types = new ArrayList<String>();
		types.add(CuentasFrecuentesRequestTO.TERCEROS);
		types.add(CuentasFrecuentesRequestTO.SPEI);
		types.add(CuentasFrecuentesRequestTO.INTERNACIONAL);
		
		for (String type : types) {
			cuentasFrecuentesRequestTO.setType(type);
			Collection<CuentasFrecuentesTO> tempAccounts = getCuentasFrecuentes(cuentasFrecuentesRequestTO).getCuentasFrecuentes();
			if(tempAccounts != null && !tempAccounts.isEmpty()) {
				frequentAccounts.addAll(tempAccounts);
			}
		}
		cuentasFrecuentesResponseTO.setCuentasFrecuentes(frequentAccounts);

		return cuentasFrecuentesResponseTO;
	}
	
	
	@SuppressWarnings("unchecked")
	public CuentasFrecuentesResponseTO getCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
		try {
			MessageElement messageElement = cuentasDAO.findCuentasFrecuentes(cuentasFrecuentesRequestTO);
			XMLDecode decode = new XMLDecode();
			Collection<CuentasFrecuentesTO> cuentasFrecuentes = (Collection<CuentasFrecuentesTO>) decode.buildCollectionContainerBeans(CuentasFrecuentesTO.class, messageElement, "frecuentes");
			cuentasFrecuentesResponseTO.setCuentasFrecuentes(cuentasFrecuentes);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return cuentasFrecuentesResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public CuentasFrecuentesResponseTO getHistoricasCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
		try {
			MessageElement messageElement = cuentasDAO.findCuentasFrecuentes(cuentasFrecuentesRequestTO);
			XMLDecode decode = new XMLDecode();
			Collection<CuentasFrecuentesTO> cuentasFrecuentes = (Collection<CuentasFrecuentesTO>) decode.buildCollectionContainerBeans(CuentasFrecuentesTO.class, messageElement, "frecuentes");
			cuentasFrecuentesResponseTO.setCuentasFrecuentes(cuentasFrecuentes);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return cuentasFrecuentesResponseTO;
	}
	

	/**
	 * Paso de solicitud agregar las frecuentes de SPEI. Obtiene la lista de los
	 * bancos.
	 * 
	 * @param cuentasFrecuentesRequestTO
	 * @return
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	@SuppressWarnings("unchecked")
	public CuentasFrecuentesResponseTO setOtrosBancosPreparacionAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)throws EJBException, CuentasException, SessionExpiredException,EliteDataException {
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			MessageElement messageElement = cuentasDAO.setIntenationalesPreparacionAgregarCuenta(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validatePrepararAgregaCuentasFrecuentes();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CodeLO", "com.bancoazteca.elite.beans.CodeTO");

			XmlDecoder decoder = new XmlDecoder();
			Collection<CodeTO> bancos = (Collection<CodeTO>) decoder.toDeserialize("banks", messageElement, mappedBeans);
			cuentasFrecuentesResponseTO.setBancos(bancos);

		} catch (DAOException e) {
			throw new CuentasException(e);
		}
		return cuentasFrecuentesResponseTO;
	}

	public DispositivoHuellaTO setIntenationalesDatosAgregarCuenta(
			CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)
			throws EJBException, CuentasException, SessionExpiredException,
			EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		DispositivoHuellaTO dispositivoHuellaTO;
		try {
			MessageElement messageElement = cuentasDAO
					.setIntenationalesDatosAgregarCuenta(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAgregaCuentasFrecuentes();
			dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();

			dispositivoHuellaTO = digitalFingerUtil
					.getDigitalFingerUtil(messageElement);

		} catch (Throwable e) {
			throw new CuentasException(e);
		}
		return dispositivoHuellaTO;
	}

	public void setOtrosBancosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)throws EJBException, CuentasException, SessionExpiredException,EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			MessageElement messageElement = cuentasDAO.setOtrosBancosGuardaAgregarCuenta(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGuardaCuentasFrecuentes();

			messageElement = cuentasDAO
					.setOtrosBancosGuardaAgregarCuentaPre(cuentasFrecuentesRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGuardaCuentasFrecuentesPre();

		} catch (DAOException e) {
			throw new CuentasException(e);
		}
	}

	/**
	 * Metodo llamada en el paso de ejecucion de alta de frecuentes terceros.
	 * 
	 * @param cuentasFrecuentesRequestTO
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	public void setTercerosConfirmarAltaFrecuente(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasDAO cuentasDAO=new CuentasDAO();
		try {
			MessageElement messageElement=cuentasDAO.setTercerosConfirmaAltaFrecuente(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule=new CuentasRule(messageElement);
			cuentasRule.validateTercerosConfirmaAltaFrecuente();
			
			messageElement=cuentasDAO.setTercerosConfirmaAltaFrecuentePre(cuentasFrecuentesRequestTO);
			cuentasRule=new CuentasRule(messageElement);
			cuentasRule.validateTercerosConfirmaAltaFrecuentePre();
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		}
	}
	
	/**
	 * Metodo que es llamado en el paso de solicitud de alta de frecuentes terceros. 
	 * Este metodo hace el llamado a los DAOs correspondientes.
	 * 
	 * @param cuentasFrecuentesRequestTO
	 * @return
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	public DispositivoHuellaTO setTercerosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)throws EJBException, CuentasException, SessionExpiredException,EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		try {
			
			MessageElement messageElement=cuentasDAO.setTercerosPreparacionAltaFrecuente(cuentasFrecuentesRequestTO.getUser());
			CuentasRule cuentasRule=new CuentasRule(messageElement);
			cuentasRule.validatePreparacionFrecuentesTerceros();
			
			
			messageElement = cuentasDAO.setTercerosDatosAgregarCuenta(cuentasFrecuentesRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAgregaDatosFrecuentesTerceros();

			
			messageElement=cuentasDAO.setTercerosDatosAgregarCuentaPre(cuentasFrecuentesRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAgregaDatosFrecuentesPreTerceros();
			
			
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil
					.getDigitalFingerUtil(messageElement);

		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		return dispositivoHuellaTO;
	}
	
	public DispositivoHuellaTO setOtrosBancosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		DispositivoHuellaTO dispositivoHuellaTO;
		try {
			MessageElement messageElement = cuentasDAO.setOtrosBancosDatosAgregarCuenta(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAgregaCuentasFrecuentes();

			messageElement = cuentasDAO.setOtrosBancosDatosAgregarCuentaPre(cuentasFrecuentesRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateAgregaCuentasFrecuentesPre();

			dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
		}catch(IOException e){
			throw new CuentasException(e);
		}catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return dispositivoHuellaTO;
	}
		
	public CuentasFrecuentesResponseTO getIntenationalesDatosEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			
			cuentasDAO.findCuentasFrecuentes(cuentasFrecuentesRequestTO);
			
			MessageElement messageElement = cuentasDAO.setIntenationalesDatosEliminaCuenta(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateEliminaCuentasFrecuentes();
			XMLDecode decode = new XMLDecode();
			ConfirmarEliminacionCuentaFrecuenteTO eliminacionCuentaFrecuenteTO = (ConfirmarEliminacionCuentaFrecuenteTO) decode.buildBean(ConfirmarEliminacionCuentaFrecuenteTO.class, messageElement, "agendaForm");
			cuentasFrecuentesResponseTO.setConfirmarEliminacionCuentaFrecuenteTO(eliminacionCuentaFrecuenteTO);
			
			Collection<CodeTO> bancos = decode.buildCollectionBeans(BancoSpeiTO.class, messageElement, "BancosSpeiBean");
			cuentasFrecuentesResponseTO.setBancos(bancos);
			
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} 
		return cuentasFrecuentesResponseTO;
	}
	
	public void setIntenationalesEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			MessageElement messageElement = cuentasDAO.setIntenationalesEliminaCuenta(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateEliminaCuentasFrecuentes();
		} catch (DAOException e) {
			throw new CuentasException(e);
		} 
	}
	public CuentasFrecuentesResponseTO getModificaDatosCuentaFrecuentesTraspasos(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {	
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		
		try {
			
			cuentasDAO.findCuentasFrecuentes(cuentasFrecuentesRequestTO);
			
			MessageElement messageElement = cuentasDAO.getModificaDatosCuentaFrecuentesTraspasos(cuentasFrecuentesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateModificaCuentasFrecuentes();
			
			XMLDecode decode = new XMLDecode();
			ConfirmarEliminacionCuentaFrecuenteTO eliminacionCuentaFrecuenteTO = (ConfirmarEliminacionCuentaFrecuenteTO) decode.buildBean(ConfirmarEliminacionCuentaFrecuenteTO.class, messageElement, "agendaForm");
			cuentasFrecuentesResponseTO.setConfirmarEliminacionCuentaFrecuenteTO(eliminacionCuentaFrecuenteTO);
			
			Collection<CodeTO> bancos = decode.buildCollectionBeans(BancoSpeiTO.class, messageElement, "BancosSpeiBean");
			cuentasFrecuentesResponseTO.setBancos(bancos);
			
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} 
		
		return cuentasFrecuentesResponseTO;
	}
	
	public CuentasFrecuentesResponseTO setModificaDatosCuentaFrecuentesTraspasosConf(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = new CuentasFrecuentesResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			Map<String, String> params = new HashMap<String, String>();
			
			if(cuentasFrecuentesRequestTO.getType().equals(CuentasFrecuentesRequestTO.INTERNACIONAL)){
				params.put("method", "confirmar");
				params.put("tipo", cuentasFrecuentesRequestTO.getType());
				params.put("beneficiario",cuentasFrecuentesRequestTO.getBeneficiario());								   
				params.put("destino", cuentasFrecuentesRequestTO.getCuenta());
				params.put("claveBanco", cuentasFrecuentesRequestTO.getClaveBanco());
			}else if(cuentasFrecuentesRequestTO.getType().equals(CuentasFrecuentesRequestTO.TERCEROS)){
				params.put("method", "confirmar");
				params.put("tipo", cuentasFrecuentesRequestTO.getType());
				params.put("apodo",cuentasFrecuentesRequestTO.getAlias());								   
				params.put("destino", cuentasFrecuentesRequestTO.getCuenta());	
				params.put("email", cuentasFrecuentesRequestTO.getEmail());
				params.put("celular", cuentasFrecuentesRequestTO.getCelular());
				params.put("carrier", cuentasFrecuentesRequestTO.getCompania());
				params.put("telCasa", cuentasFrecuentesRequestTO.getTelCasa());
				params.put("telOficina", cuentasFrecuentesRequestTO.getTelOficina());
			}else {
				params.put("method", "confirmar");
				params.put("tipo", cuentasFrecuentesRequestTO.getType());
				params.put("beneficiario",cuentasFrecuentesRequestTO.getBeneficiario());								   
				params.put("destino", cuentasFrecuentesRequestTO.getCuenta());	
				params.put("apodo", cuentasFrecuentesRequestTO.getAlias());
				params.put("email", cuentasFrecuentesRequestTO.getEmail());
				params.put("celular", cuentasFrecuentesRequestTO.getCelular());
				params.put("carrier", cuentasFrecuentesRequestTO.getCompania());
				params.put("telCasa", cuentasFrecuentesRequestTO.getTelCasa());
				params.put("telOficina", cuentasFrecuentesRequestTO.getTelOficina());
				params.put("bank", cuentasFrecuentesRequestTO.getBanco());
			}

			MessageElement messageElement = cuentasDAO.setModificaDatosCuentaFrecuentesTraspasosConf(cuentasFrecuentesRequestTO, params);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateModificaCuentasFrecuentesValidacion();

			
			messageElement=cuentasDAO.setModificaDatosCuentaFrecuentesTraspasosConf01(cuentasFrecuentesRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateModificaCuentasFrecuentesValidacion01();
			
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			cuentasFrecuentesResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} 
		
		return cuentasFrecuentesResponseTO;
	}
	
	public void setGuardaModificaCuentaFrecuentesTraspasos(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			MessageElement messageElement = cuentasDAO.setGuardaModificaCuentaFrecuentesTraspasos(cuentasFrecuentesRequestTO);	
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateGuardaModificaCuenta();
		} catch (DAOException e) {
			throw new CuentasException(e);
		} 
	}
	
	public CuentaSocioPlusResponseTO setPartnerPlusStartOpenAccount(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentasFrecuentesResponseTO = new CuentaSocioPlusResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			MessageElement messageElement = cuentasDAO.setPartnerPlusStartOpenAccount(cuentaSocioPlusRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validatePartnerPlusBeneficiary();
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} 	
		return cuentasFrecuentesResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public CuentaSocioPlusResponseTO setPartnerPlusStartAceptConditions(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = new CuentaSocioPlusResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			XmlDecoder decode = new XmlDecoder();
			MessageElement messageElement = cuentasDAO.setPartnerPlusOpenAccount(cuentaSocioPlusRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validatePartnerPlusOpenAccount();
		
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CuentaLO","com.bancoazteca.elite.beans.CuentaLoTO");		
			String valorUnidad = (String) decode.toDeserialize("valorUni", messageElement, null);
			String montoMinimo = (String) decode.toDeserialize("montoMinimo", messageElement, null);
			
			Collection<CuentaLoTO> cuentas = (Collection<CuentaLoTO>) decode.toDeserialize("ctsOrigenSocioP", messageElement, mappedBeans);
			log.debug("cuentas para apertura "+cuentas.size());
			cuentaSocioPlusResponseTO.setValorUnidad(valorUnidad);
			cuentaSocioPlusResponseTO.setCuentas(cuentas);
			cuentaSocioPlusResponseTO.setMontoMinimo(montoMinimo);
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyy",new Locale("es"));
			String fecha = dateFormat.format(date);
			cuentaSocioPlusResponseTO.setDate(fecha);
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		}	
		return cuentaSocioPlusResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public CuentaSocioPlusResponseTO setPartnerPlusData(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = new CuentaSocioPlusResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {		
			String[] primerNombre = cuentaSocioPlusRequestTO.getPrimerNombre() ;
			String[] segundoNombre = cuentaSocioPlusRequestTO.getSegundoNombre();
			String[] apellidoPaterno = cuentaSocioPlusRequestTO.getPrimerApellido();
			String[] apellidoMaterno = cuentaSocioPlusRequestTO.getSegundoApellido();
			String[] parentesco = cuentaSocioPlusRequestTO.getParentesco();
			String[] edad = cuentaSocioPlusRequestTO.getEdad();
			String[] porcentaje = cuentaSocioPlusRequestTO.getPorcentaje();
			
			Collection<ConnectorDataTO> data = new ArrayList<ConnectorDataTO>();
			for (int j=0;j<primerNombre.length;j++){
				if (primerNombre[j]!=null && primerNombre[j].length()>0){
					data.add(new ConnectorDataTO("nomBen",primerNombre[j]));
					data.add(new ConnectorDataTO("nomBen2",segundoNombre[j]));
					data.add(new ConnectorDataTO("apPaterno",apellidoPaterno[j]));
					data.add(new ConnectorDataTO("apMaterno",apellidoMaterno[j]));
					data.add(new ConnectorDataTO("parentesco",parentesco[j]));
					data.add(new ConnectorDataTO("edad",edad[j]));
					data.add(new ConnectorDataTO("porcentaje",porcentaje[j]));	
				}else{
					data.add(new ConnectorDataTO("nomBen",""));
					data.add(new ConnectorDataTO("nomBen2",""));
					data.add(new ConnectorDataTO("apPaterno",""));
					data.add(new ConnectorDataTO("apMaterno",""));
					data.add(new ConnectorDataTO("parentesco","-1"));
					data.add(new ConnectorDataTO("edad",""));
					data.add(new ConnectorDataTO("porcentaje",""));
				}
			}
			cuentaSocioPlusRequestTO.setCadena(data);
		
			MessageElement messageElement = cuentasDAO.setPartnerPlusBeneficiary(cuentaSocioPlusRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validatePartnerPlusData();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("BeneficiarioVO","com.bancoazteca.elite.beans.BeneficiarioTO");
			mappedBeans.put("cuentaPlusVO","com.bancoazteca.elite.beans.SocioPlusTO");
			SocioPlusTO socioPlusTO = (SocioPlusTO) decode.toDeserialize("cuentaPlusVO", messageElement,mappedBeans);
			cuentaSocioPlusResponseTO.setSocioPlusTO(socioPlusTO);
			
			DispositivoHuellaTO dispositivoHuellaTO;
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			cuentaSocioPlusResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}	
		return cuentaSocioPlusResponseTO;
	}
	
	public CuentaSocioPlusResponseTO setPartnerPlusConfirmData(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentasFrecuentesResponseTO = new CuentaSocioPlusResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		XmlDecoder decoder = new XmlDecoder();
		
		String query="for $a in //elite/cuentaPlusVO return {$a}";
		
		try {
			MessageElement messageElement = cuentasDAO.setPartnerPlusWaitConfirmData(cuentaSocioPlusRequestTO);
			MessageElement message;
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validatePartnerPlusWaitConfirmData();
			
			Element element=XMLFinder.getElement(messageElement.toString(),query);
			
			message = new MessageElement(element);
			
			messageElement = cuentasDAO.setPartnerPlusConfirmData(cuentaSocioPlusRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validatePartnerPlusConfirmData();
			
			String folioApertura = (String) decoder.toDeserialize("folioTran", messageElement, null);
			String numeroCuentaSocioPlus =(String) decoder.toDeserialize("numCtaPlus", messageElement, null);
			String unidadesAdquiridas =(String) decoder.toDeserialize("numUnidadesF", message, null);
			String cuentaCargo= (String) decoder.toDeserialize("descCuenta", message, null);
			
			ClienteTO clienteTO = new UsuarioSLBean().buildClienteTO(messageElement);
			cuentasFrecuentesResponseTO.setClienteTO(clienteTO);
			cuentasFrecuentesResponseTO.setFolioApertura(folioApertura);
			cuentasFrecuentesResponseTO.setNumeroCuentaSocioPlus(numeroCuentaSocioPlus);
			cuentasFrecuentesResponseTO.setUnidadesAdquiridas(unidadesAdquiridas);
			cuentasFrecuentesResponseTO.setCuentaCargo(cuentaCargo);
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (UsuarioException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		} 	
		return cuentasFrecuentesResponseTO;
	}
	
	public CuentaSocioPlusResponseTO getSocioPlusPDF(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentasFrecuentesResponseTO = new CuentaSocioPlusResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			String pdf = cuentasDAO.getProspectoFideicomisoPDF(cuentaSocioPlusRequestTO);
			cuentasFrecuentesResponseTO.setPdf(pdf.getBytes());
		} catch (DAOException e) {
			throw new CuentasException(e);
		}
		
		return cuentasFrecuentesResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public AperturaCuentaSocioResponseTO getCuentaSocioInvocacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaSocioResponseTO cuentaSocioResponseTO = new AperturaCuentaSocioResponseTO();
		String query = "for $monto in //elite/monto return <montoMinimo>{$monto}</montoMinimo>";
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasRule cuentasRule = null;
		XmlDecoder decoder = new XmlDecoder();
		try {
			MessageElement messageElementInvocacion = cuentasDAO.findCuentaSocioInvocacion(cuentaSocioRequestTO);
			cuentasRule = new CuentasRule(messageElementInvocacion);
			cuentasRule.validateCuentaSocioInvocacion();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("ebanking_web_DataKey", "com.bancoazteca.elite.beans.ConfirmarAperturaCuentaSocioTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			ConfirmarAperturaCuentaSocioTO aperturaCuentaSocioTO = (ConfirmarAperturaCuentaSocioTO) decoder.toDeserialize("ebanking_web_DataKey", messageElementInvocacion, mappedBeans);
			formatearCuentas(aperturaCuentaSocioTO.getCuentasLO());
			
			MessageElement messageElementInicio = cuentasDAO.findCuentaSocioInicio(cuentaSocioRequestTO);
			cuentasRule = new CuentasRule(messageElementInicio);
			cuentasRule.validateCuentaSocioInicio();
			
			Element element = XMLFinder.getElement(messageElementInvocacion.toString(), query);
			MessageElement messageElement = new MessageElement(element);
			String montoMinimo = (String) decoder.toDeserialize("monto", messageElement, null);
			
			cuentaSocioResponseTO.setAperturaCuentaSocioTO(aperturaCuentaSocioTO);
			cuentaSocioResponseTO.setMontoMinimo(montoMinimo);
			
		}catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} 
		return cuentaSocioResponseTO;
	}
	
	public AperturaCuentaSocioResponseTO getCuentaSocioEnvioDatos(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaSocioResponseTO cuentaSocioResponseTO = new AperturaCuentaSocioResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			MessageElement messageElement = cuentasDAO.findCuentaSocioEnvioDatos(cuentaSocioRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCuentaSocioEnvioDatos();
			XmlDecoder decoder = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CtaSocioForm", "com.bancoazteca.elite.beans.ConfirmarAperturaCuentaSocioTO");
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			ConfirmarAperturaCuentaSocioTO aperturaCuentaSocioTO = (ConfirmarAperturaCuentaSocioTO) decoder.toDeserialize("CtaSocioForm", messageElement, mappedBeans);
			formatearCuentas(aperturaCuentaSocioTO.getCuentasLO());
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			cuentaSocioResponseTO.setAperturaCuentaSocioTO(aperturaCuentaSocioTO);
			cuentaSocioResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
		}catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} 
		return cuentaSocioResponseTO;
	}
	
	public AperturaCuentaSocioResponseTO getCuentaSocioConfirmacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaSocioResponseTO cuentaSocioResponseTO = new AperturaCuentaSocioResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		try {
			MessageElement messageElementWait = cuentasDAO.findCuentaSocioConfirmacionWait(cuentaSocioRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElementWait);
			cuentasRule.validateCuentaSocioConfirmacion();
			
			MessageElement messageElement = cuentasDAO.findCuentaSocioConfirmacion(cuentaSocioRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCuentaSocioConfirmacion();
		}catch (DAOException e) {
			throw new CuentasException(e);
		} 
		return cuentaSocioResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public AperturaCuentaPlataResponseTO getCuentaPlataInvocacion(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaPlataResponseTO cuentaPlataResponseTO = new AperturaCuentaPlataResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasRule cuentasRule = null;
		try {
			MessageElement messageElementInvocacion = cuentasDAO.findCuentaPlataInvocacion(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElementInvocacion);
			cuentasRule.validateCuentaPlataInvocacion();
			
			MessageElement messageElementInicio = cuentasDAO.findCuentaPlataInicio(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElementInicio);
			cuentasRule.validateCuentaPlataInicio();
			
		}catch (DAOException e) {
			throw new CuentasException(e);
		} 
		return cuentaPlataResponseTO;
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataContrato(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaPlataResponseTO cuentaPlataResponseTO = new AperturaCuentaPlataResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasRule cuentasRule = null;
		try {
			MessageElement messageElementInvocacion = cuentasDAO.findCuentaPlataContratosInvocacion(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElementInvocacion);
			cuentasRule.validateCtaPlataInvocacionContratos();
		}catch (DAOException e) {
			throw new CuentasException(e);
		} 
		return cuentaPlataResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public AperturaCuentaPlataResponseTO getCuentaPlataAceptarContrato(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaPlataResponseTO cuentaPlataResponseTO = new AperturaCuentaPlataResponseTO();
		String query = "for $apPlata in //elite/ApPlataVO return $apPlata";
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasRule cuentasRule = null;
		XmlDecoder decoder = new XmlDecoder();
		try {
			MessageElement messageElementWait = cuentasDAO.findCuentaPlataContratosWait(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElementWait);
			cuentasRule.validateCtaPlataAceptarContratoWait();
			
			MessageElement messageElement = cuentasDAO.findCuentaPlataContratos(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCtaPlataAceptarContrato();
			
			Element elementCuentas = XMLFinder.getElement(messageElement.toString(), query);
			MessageElement messageElementApPlata = new MessageElement(elementCuentas);
			String referencia = (String) decoder.toDeserialize("referencia", messageElementApPlata, null);		
			String minimoApertura = (String) decoder.toDeserialize("minimoApertura", messageElementApPlata, null);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CuentaLO", "com.bancoazteca.elite.beans.CuentaLoTO");
			Collection<CuentaLoTO> cuentas = (Collection<CuentaLoTO>) decoder.toDeserialize("cuentasCargo", messageElementApPlata, mappedBeans);
			formatearCuentas(cuentas);
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("VentaPlataBean", "com.bancoazteca.elite.beans.CotizacionOnzaPlataLibertadTO");
			Collection<CotizacionOnzaPlataLibertadTO> cotizaciones = (Collection<CotizacionOnzaPlataLibertadTO>) decoder.toDeserialize("cotizaciones", messageElementApPlata, mappedBeans);
			cuentaPlataResponseTO.setReferencia(referencia);
			cuentaPlataResponseTO.setMinimoApertura(minimoApertura);
			cuentaPlataResponseTO.setCuentas(cuentas);
			cuentaPlataResponseTO.setCotizaciones(cotizaciones);
			
		}catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} 
		return cuentaPlataResponseTO;
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataEnvioDatos(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaPlataResponseTO cuentaPlataResponseTO = new AperturaCuentaPlataResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasRule cuentasRule = null;
		XmlDecoder decoder = new XmlDecoder();
		try {
			MessageElement messageElement = cuentasDAO.findCuentaPlataEnvioDatos(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCtaPlataEnvioDatosWait();
			MessageElement messageElementConfirmacion = cuentasDAO.findCuentaPlataConfirmacionEnvioDatos(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElementConfirmacion);
			if(cuentaPlataRequestTO.getUtilConfirmacion().equals("getData")){
				cuentasRule.validateCtaPlataEnvioDatos();
			}else{
				cuentasRule.validateCtaPlataCotizacion();
			}
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("aperturaPlataForm", "com.bancoazteca.elite.beans.ConfirmacionAperturaCuentaPlataTO");
			ConfirmacionAperturaCuentaPlataTO aperturaCuentaPlataTO = (ConfirmacionAperturaCuentaPlataTO) decoder.toDeserialize("aperturaPlataForm", messageElementConfirmacion, mappedBeans);
			String precioMoneda = (String) decoder.toDeserialize("precioMoneda", messageElementConfirmacion, null);
			String montoNPesos = (String) decoder.toDeserialize("montoNPesos", messageElementConfirmacion, null);
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElementConfirmacion);
			
			cuentaPlataResponseTO.setAperturaCuentaPlataTO(aperturaCuentaPlataTO);
			cuentaPlataResponseTO.setPrecioMoneda(precioMoneda);
			cuentaPlataResponseTO.setMontoNPesos(montoNPesos);
			cuentaPlataResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		}catch (DAOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		}
		return cuentaPlataResponseTO;
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataEjecutar(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		AperturaCuentaPlataResponseTO cuentaPlataResponseTO = new AperturaCuentaPlataResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		CuentasRule cuentasRule = null;
		XmlDecoder decoder = new XmlDecoder();
		try {
			MessageElement messageElementWait= cuentasDAO.findCuentaPlataEjecucionWait(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElementWait);
			//cuentasRule.validateCuentaSocioInvocacion();
			MessageElement messageElement= cuentasDAO.findCuentaPlataEjecucion(cuentaPlataRequestTO);
			cuentasRule = new CuentasRule(messageElement);
			//cuentasRule.validateCuentaSocioInvocacion();
			
			String ctaPlata = (String) decoder.toDeserialize("ctaPlata", messageElement, null);
			String folioPlata = (String) decoder.toDeserialize("folioPlata", messageElement, null);
			cuentaPlataResponseTO.setCtaPlata(ctaPlata);
			cuentaPlataResponseTO.setFolioPlata(folioPlata);
		}catch (DAOException e) {
			throw new CuentasException(e);
		} 
		return cuentaPlataResponseTO;
	}
	
	public void formatearCuentas(Collection<CuentaLoTO> cuentas) {
		int i =0;
		for (CuentaLoTO cuentaTO : cuentas) {
			i = i + 1;
			cuentaTO.setCuentaFormateada(Formatter.formatSplittedCuenta(cuentaTO.getNumero()));
			log.info(" cuenta formateada: "+cuentaTO.getCuentaFormateada());			
		}
	}
	
	public ERecibosNominaResponseTO liberaERecibosNominaInit(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		ERecibosNominaResponseTO eRecibosNominaResponseTO = new ERecibosNominaResponseTO();
//		String method = null;
		String tarjetaNomina = null;
		String liberacionMultiple = null;
		String coleccionLlaves = "";
		HashMap<String, String> mappedBeans = null;
		LiberacionRecibosTO to=null;
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			XmlDecoder decoder = new XmlDecoder();
			
			MessageElement messageElement = ejecutaNominaConsulta(eRecibosNominaRequestTO, cuentasDao,"solicitud","");
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("consultaRecibosNominaForm", "com.bancoazteca.elite.beans.ConsultaRecibosNominaFormTO");
			mappedBeans.put("nominaLo", "com.bancoazteca.elite.beans.CuentaLoTO");
			mappedBeans.put("nomina", "com.bancoazteca.elite.beans.CuentaTO");
			mappedBeans.put("ret", "com.bancoazteca.elite.beans.ResponseConsultaNominaRetencionesTO");
			mappedBeans.put("ArchivoNomina", "com.bancoazteca.elite.beans.ArchivoNominaTO");
			
			mappedBeans.put("listaDeRecibos", "java.util.ArrayList");
			mappedBeans.put("ArrayList", "java.util.ArrayList");
			
			ConsultaRecibosNominaFormTO consultaRecibosNominaFormTO = (ConsultaRecibosNominaFormTO) decoder.toDeserialize("consultaRecibosNominaForm", messageElement, mappedBeans);
			log.info("ConsultaRecibosNominaFormTO: " + consultaRecibosNominaFormTO);
			tarjetaNomina = (String)decoder.toDeserialize("TARJETA_NOMINA_RECIBOS", messageElement, null);
			liberacionMultiple = (String)decoder.toDeserialize("liberacionMultiple", messageElement, null);
			if (consultaRecibosNominaFormTO.getRet() != null && consultaRecibosNominaFormTO.getRet().getElement() != null) {
				for (ArchivoNominaTO archivoNominaTO : consultaRecibosNominaFormTO.getRet().getElement()) {
					coleccionLlaves += archivoNominaTO.getLlave() + ",";
				}
			}
			if(coleccionLlaves.endsWith(",")) {
				coleccionLlaves = coleccionLlaves.substring(0, coleccionLlaves.length() - 1);
			}
			
			mappedBeans=new HashMap<String, String>();
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");
			ArrayList<CuentaTO> cuentas=(ArrayList<CuentaTO>)decoder.toDeserialize("saldosAccounts", messageElement, mappedBeans);
			
			log.info("Coleccion de Llaves: " + coleccionLlaves);
			eRecibosNominaResponseTO.setLiberacionMultiple(liberacionMultiple);
			eRecibosNominaResponseTO.setCuentaLoTO(consultaRecibosNominaFormTO.getNominaLo());
			eRecibosNominaResponseTO.setCuentaTO(consultaRecibosNominaFormTO.getNomina());
			eRecibosNominaResponseTO.setNominaLo(consultaRecibosNominaFormTO.getNominaLo());
			eRecibosNominaResponseTO.setResponseConsultaNominaRetencionesTO(consultaRecibosNominaFormTO.getRet());
			eRecibosNominaResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			eRecibosNominaResponseTO.setColeccionLlaves(coleccionLlaves);
			eRecibosNominaResponseTO.setTiposRecibos(consultaRecibosNominaFormTO.getListaDeRecibos());
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (XmlDecodeException xmle) {
			xmle.printStackTrace();
			throw new CuentasException(xmle);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new CuentasException(ioe);
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		return eRecibosNominaResponseTO;
	}
	
	private MessageElement ejecutaNominaConsulta(
			ERecibosNominaRequestTO eRecibosNominaRequestTO,
			CuentasDAO cuentasDao,String paso,String parametrosValidacion) throws DAOException,
			SessionExpiredException, EliteDataException, CuentasException {

		String method;
		method = "inicio"; //Invocación al primer método del Dispatch de eBanking.
		eRecibosNominaRequestTO.setMethod(method);
		MessageElement messageElement = cuentasDao.liberaERecibosNominaInit(eRecibosNominaRequestTO);
		ValidaPathsERecibos validador=FabricaValidadorErecibosPaths.getInstance(paso, messageElement);
		validador.validateReleaseVouchersInicio();
		
		method = "contrato"; //Invocación al segundo método del Dispatch de eBanking.
		eRecibosNominaRequestTO.setMethod(method);
		messageElement = cuentasDao.liberaERecibosNominaInit(eRecibosNominaRequestTO);
		validador=FabricaValidadorErecibosPaths.getInstance(paso, messageElement);
		validador.validateReleaseVouchersContrato();

		method = "consulta"; //Invocación al tercer método del Dispatch de eBanking.
		eRecibosNominaRequestTO.setMethod(method);
		messageElement = cuentasDao.liberaERecibosNominaInit(eRecibosNominaRequestTO);
		validador=FabricaValidadorErecibosPaths.getInstance(paso, messageElement);
		validador.validateReleaseVouchersConsulta();
		
		
		method = "seleccion"; //Invocación al cuarto método del Dispatch de eBanking.
		eRecibosNominaRequestTO.setMethod(method);
		messageElement = cuentasDao.liberaERecibosNominaSeleccion(eRecibosNominaRequestTO);
		validador=FabricaValidadorErecibosPaths.getInstance(paso, messageElement);
		validador.validateReleaseVouchersSeleccion();
		return messageElement;
	}
	
	public ERecibosNominaResponseTO liberaERecibosNominaEjecutar(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		ERecibosNominaResponseTO eRecibosNominaResponseTO = new ERecibosNominaResponseTO();
		HashMap<String, String> mappedBeans = null;
		String coleccionLlaves = "";
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			XmlDecoder decoder = new XmlDecoder();
			MessageElement messageElement= cuentasDao.liberaERecibosNominaEjecutar(eRecibosNominaRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			String noConfirmado = (String)decoder.toDeserialize("NO_CONFIRMACION", messageElement, null);
			
			try{
				eRecibosNominaResponseTO.setCompletaLiberacion(true);
				cuentasRule.validateReleaseVouchersEjecutar(noConfirmado);
			}catch(LiberacionIncompletaExcepion e){
				eRecibosNominaResponseTO.setCompletaLiberacion(false);
			}catch (EliteDataException e) {
				Map<String, String> errors = (Map<String, String>) e.getErrorData();
				if( errors != null ){
					if( errors.containsKey("mensaje") && errors.get("mensaje").contains("huella") ){
						errors = new HashMap<String, String>();
						errors.put("liberacion_recibo", "Lo sentimos, existio un error al liberar su nomina.");
						throw new EliteDataException("Error de validación", errors, EliteRules.LEVEL_ACTION_ERRORS);
					}else{
						throw e;
					}			
				}
				
			}
			
//			String method = "consulta"; //Invocación al tercer método del Dispatch de eBanking.
//			eRecibosNominaRequestTO.setMethod(method);
//			messageElement = cuentasDao.liberaERecibosNominaInit(eRecibosNominaRequestTO);
//			cuentasRule = new CuentasRule(messageElement);
//			cuentasRule.validateReleaseVouchersConsultaLiberados();
			
			messageElement = ejecutaNominaConsulta(eRecibosNominaRequestTO, cuentasDao,"liberacion",noConfirmado);
			
			
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("consultaRecibosNominaForm", "com.bancoazteca.elite.beans.ConsultaRecibosNominaFormTO");
			mappedBeans.put("nominaLo", "com.bancoazteca.elite.beans.CuentaLoTO");
			mappedBeans.put("nomina", "com.bancoazteca.elite.beans.CuentaTO");
			mappedBeans.put("ret", "com.bancoazteca.elite.beans.ResponseConsultaNominaRetencionesTO");
			mappedBeans.put("ArchivoNomina", "com.bancoazteca.elite.beans.ArchivoNominaTO");
			
			mappedBeans.put("listaDeRecibos", "java.util.ArrayList");
			mappedBeans.put("ArrayList", "java.util.ArrayList");
			
			ConsultaRecibosNominaFormTO consultaRecibosNominaFormTO = (ConsultaRecibosNominaFormTO) decoder.toDeserialize("consultaRecibosNominaForm", messageElement, mappedBeans);
			
//			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
//			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
//			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
//			eRecibosNominaResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
			mappedBeans=new HashMap<String, String>();
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaTO");
			ArrayList<CuentaTO> cuentas=(ArrayList<CuentaTO>)decoder.toDeserialize("saldosAccounts", messageElement, mappedBeans);
			
			String tarjetaNomina = (String)decoder.toDeserialize("TARJETA_NOMINA_RECIBOS", messageElement, null);
			String liberacionMultiple = (String)decoder.toDeserialize("liberacionMultiple", messageElement, null);
			if (consultaRecibosNominaFormTO.getRet() != null && consultaRecibosNominaFormTO.getRet().getElement() != null) {
				for (ArchivoNominaTO archivoNominaTO : consultaRecibosNominaFormTO.getRet().getElement()) {
					coleccionLlaves += archivoNominaTO.getLlave() + ",";
				}
			}
			if(coleccionLlaves.endsWith(",")) {
				coleccionLlaves = coleccionLlaves.substring(0, coleccionLlaves.length() - 1);
			}

			
			
			eRecibosNominaResponseTO.setLiberacionMultiple(liberacionMultiple);
			eRecibosNominaResponseTO.setCuentaLoTO(consultaRecibosNominaFormTO.getNominaLo());
			eRecibosNominaResponseTO.setCuentaTO(consultaRecibosNominaFormTO.getNomina());
			eRecibosNominaResponseTO.setNominaLo(consultaRecibosNominaFormTO.getNominaLo());
			eRecibosNominaResponseTO.setResponseConsultaNominaRetencionesTO(consultaRecibosNominaFormTO.getRet());
			eRecibosNominaResponseTO.setColeccionLlaves(coleccionLlaves);
			eRecibosNominaResponseTO.setTiposRecibos(consultaRecibosNominaFormTO.getListaDeRecibos());

		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} /*catch (XmlDecodeException xmle) {
			xmle.printStackTrace();
			throw new CuentasException(xmle);
		}*/ /*catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}*/
		return eRecibosNominaResponseTO;
	}
	private HashSet<String> convierteLlaves(String coleccionLlaves){
		StringTokenizer token=new StringTokenizer(coleccionLlaves,",");
		HashSet<String> llaves=new HashSet<String>();
		while(token.hasMoreElements()){
			String llave=(String)token.nextElement();
			llaves.add(llave);
		}
		return llaves;
	}
	
	public ERecibosNominaResponseTO liberaERecibosNominaDetalle(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		ERecibosNominaResponseTO eRecibosNominaResponseTO = new ERecibosNominaResponseTO();
		ReciboTO ReciboTO=new ReciboTO();
		String method = null;
		String liberacionMultiple = null;
		HashMap<String, String> mappedBeans = null;
		CuentaLoTO cuentaLoTO = new CuentaLoTO();
		try {
			CuentasDAO cuentasDao = new CuentasDAO();
			XmlDecoder decoder = new XmlDecoder();
			method = "detalle";
			eRecibosNominaRequestTO.setMethod(method);
			MessageElement messageElement = cuentasDao.liberaERecibosNominaDetalle(eRecibosNominaRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateReleaseVouchersDetail();
			
			
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			liberacionMultiple = (String)decoder.toDeserialize("liberacionMultiple", messageElement, null);
			mappedBeans = new HashMap<String, String>();
			mappedBeans.put("consultaRecibosNominaForm", "com.bancoazteca.elite.beans.ConsultaRecibosNominaFormTO");
			mappedBeans.put("nominaLo", "com.bancoazteca.elite.beans.CuentaLoTO");
			mappedBeans.put("nomina", "com.bancoazteca.elite.beans.CuentaTO");
			mappedBeans.put("ret", "com.bancoazteca.elite.beans.ResponseConsultaNominaRetencionesTO");
			mappedBeans.put("ArchivoNomina", "com.bancoazteca.elite.beans.ArchivoNominaTO");
			mappedBeans.put("recib", "com.bancoazteca.elite.beans.ResponseConsultaRecibosTO");
			mappedBeans.put("ReciboMensaje", "com.bancoazteca.elite.beans.ReciboMensajeTO");
			mappedBeans.put("cabecera", "com.bancoazteca.elite.beans.ReciboCabeceraTO");
			mappedBeans.put("ReciboDetalle", "com.bancoazteca.elite.beans.ReciboDetalleTO");
			mappedBeans.put("Recibo", "com.bancoazteca.elite.beans.ReciboTO");		
			ReciboTO = (ReciboTO) decoder.toDeserialize("Recibo", messageElement, mappedBeans);
			cuentaLoTO = (CuentaLoTO)decoder.toDeserialize("nominaLo", messageElement, mappedBeans);
			
			eRecibosNominaResponseTO.setReciboDetalle(ReciboTO);
			eRecibosNominaResponseTO.setCuentaLoTO(cuentaLoTO);
			eRecibosNominaResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			

			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} 
		catch (XmlDecodeException xmle) {
			xmle.printStackTrace();
			throw new CuentasException(xmle);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}
		return eRecibosNominaResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public CreditosResponseTO detailOtherCredits(CreditosRequestTO creditosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CreditosResponseTO creditosResponseTO = new CreditosResponseTO();
		
		final String queryColeccionMovimientos = "for $a in //elite/detalles return <temp>{$a}</temp>";
		
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			XMLDecode decode = new XMLDecode();
			MessageElement messageElement = cuentasDAO.getDetailOtherCredits(creditosRequestTO);
			Map<String, String> actionErrors = (Map<String, String>) decode.buildMapBeans(messageElement, "org.apache.struts.action.ERROR");
			if (actionErrors!=null && actionErrors.size()>0){
				actionErrors = EscapeUtils.unescapeActionErrors(actionErrors);
			}
			
			
			if(creditosRequestTO.getMethod().equals("proyeccion")){
				Collection<DetallePagoCreditoTO> detallesCredito = decode.buildCollectionBeans(DetallePagoCreditoTO.class, messageElement, "DetalleProyeccionCreditoVO");
				creditosResponseTO.setDetallesCredito(detallesCredito);
			}else if(creditosRequestTO.getMethod().equals("pagosRealizados")){

				Element  element=XMLFinder.getElement(messageElement.toString(), queryColeccionMovimientos);
				messageElement=new MessageElement(element);
				
				Collection<DetallePagoOtrosCreditosTO> detallesCredito = decode.buildCollectionBeans(DetallePagoOtrosCreditosTO.class, messageElement, "DetallePagoCreditoVO");
				creditosResponseTO.setDetallePagosCredito(detallesCredito);
			}
			
			creditosResponseTO.setActionErrors(actionErrors);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}
		
		
		return creditosResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public CambioNipResponseTO getMediosPagoInvocation(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CambioNipResponseTO cambioNipResponseTO = new CambioNipResponseTO();
		CuentasDAO cuentasDAO =  new CuentasDAO();
		XmlDecoder decoder = new XmlDecoder();
		
		try{
			MessageElement messageElement = cuentasDAO.getMediosPagoInvocation(cambioNipRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateMediosPagoInvocation();
						
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("MediosPagoVO", "com.bancoazteca.elite.beans.MediosPagoTO");
			mappedBeans.put("alertaTarjetaBAVO", "com.bancoazteca.elite.beans.AlertaTarjetaBATO");
			Collection<MediosPagoTO> tarjetas = (Collection<MediosPagoTO>) decoder.toDeserialize("listaCuentas", messageElement, mappedBeans);
			cambioNipResponseTO.setTarjetasMediosPago(tarjetas);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (CuentasException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} 
		return cambioNipResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public DisposicionEfectivoCajeroResponseTO getSolicitudDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasDAO cuentasDAO =  new CuentasDAO();
		XmlDecoder decoder = new XmlDecoder();
		DisposicionEfectivoCajeroResponseTO cajeroResponseTO=null;
		try{
			
			MessageElement messageElement = cuentasDAO.getSolicitudDispocicionEfectivoCajero(cajeroRequestTO);
			CuentasRule cuentasRule=new CuentasRule(messageElement);
			cuentasRule.validateSolicitudEfectivoCajero();
			
			String query = "for $tarjeta in //elite return $tarjeta";
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			
			String object=(String)decoder.toDeserialize("tarjeta", messageElement, null);
			
		    cajeroResponseTO=new DisposicionEfectivoCajeroResponseTO();
			
		    cajeroResponseTO.setNumeroTarjeta(object);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}
		return cajeroResponseTO;
	}

	
	@SuppressWarnings("unchecked")
	public DisposicionEfectivoCajeroResponseTO getValidacionDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasDAO cuentasDAO =  new CuentasDAO();
		XmlDecoder decoder = new XmlDecoder();
		DisposicionEfectivoCajeroResponseTO cajeroResponseTO=null;
		try{
			
			MessageElement messageElement = cuentasDAO.getValidacionDispocicionEfectivoCajero(cajeroRequestTO);
			
			CuentasRule cuentasRule=new CuentasRule(messageElement);
			cuentasRule.validateValidacionEfectivoCajero();
			
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			DispositivoHuellaTO dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			
			HashMap<String, String> mappedBeans=new HashMap<String, String>();
			mappedBeans.put("dispVO",DisposicionEfectivoCajeroResponseTO.class.getCanonicalName());
			
			cajeroResponseTO=(DisposicionEfectivoCajeroResponseTO)decoder.toDeserialize("dispVO", messageElement, mappedBeans);
			
			
			cajeroResponseTO.setLlavePublica(dispositivoHuellaTO.getLlavePublica());
			cajeroResponseTO.setLongitudHuella(dispositivoHuellaTO.getLongitudHuella());
			
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}
		
		return cajeroResponseTO;
	}
	

	public DisposicionEfectivoCajeroResponseTO getEjecucionDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasDAO cuentasDAO =  new CuentasDAO();
		XmlDecoder decoder = new XmlDecoder();
		DisposicionEfectivoCajeroResponseTO cajeroResponseTO=null;
		try{
			
			
			MessageElement messageElement = cuentasDAO.getEjecucionDispocicionEfectivoCajero(cajeroRequestTO);
			
			HashMap<String, String> mappedBeans=new HashMap<String, String>();
			mappedBeans.put("dispVO",DisposicionEfectivoCajeroResponseTO.class.getCanonicalName());
			
			cajeroResponseTO=(DisposicionEfectivoCajeroResponseTO)decoder.toDeserialize("dispVO", messageElement, mappedBeans);
			
			
			if(cajeroResponseTO!=null && cajeroResponseTO.getErrorMessage()!=null){
				Map<String,String> mapError=new HashMap<String, String>();
				mapError.put("Error",cajeroResponseTO.getErrorMessage());
				throw new EliteDataException("Error", mapError,CuentasRule.LEVEL_ACTION_ERRORS);
			}else{
				CuentasRule cuentasRule=new CuentasRule(messageElement);
				cuentasRule.validateEjecucionEfectivoCajero();				
			}
			
		}catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}
		return cajeroResponseTO;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public CambioNipResponseTO getNipChangeInvocation(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CambioNipResponseTO cambio_NipResponseTO = new CambioNipResponseTO();
		CuentasDAO cuentasDAO =  new CuentasDAO();
		//String query = "for $medPago in //elite/tarjetasValidas return <elite>{$medPago}</elite>";
		String query = "for $cliente in //elite/tarjetasValidas return <elite>{$cliente}</elite>";
		
		XmlDecoder decode = new XmlDecoder();
		
		try{
			MessageElement messageElement = cuentasDAO.getNipChangeInvocation(cambioNipRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCambioNipInvocation();
								
			Element elementCuentas = XMLFinder.getElement(messageElement.toString(), query);
			MessageElement messageElementMedPago = new MessageElement(elementCuentas);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CancelacionElement", "com.bancoazteca.elite.beans.CancelacionElementTO");
			Collection<CancelacionElementTO> tarjetas = (Collection<CancelacionElementTO>) decode.toDeserialize("tarjetasValidas", messageElementMedPago, mappedBeans);
			
			
			
			cambio_NipResponseTO.setTarjetasCambioNip(tarjetas);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (CuentasException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}	
		return cambio_NipResponseTO;
		
	}
	
	@SuppressWarnings("unchecked")
	public CambioNipResponseTO setNipChangeExecution(CambioNipRequestTO cambioNipRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		
		CambioNipResponseTO cambioNipResponseTO = new CambioNipResponseTO();
		CuentasDAO cuentasDAO =  new CuentasDAO();
		String query = "for $medPago in //elite/listaCuentas return <elite>{$medPago}</elite>";
		XmlDecoder decoder = new XmlDecoder();
		
		try{
			MessageElement messageElement = cuentasDAO.setNipChangeExecution(cambioNipRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCambioNipExecution();
								
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);
			cambioNipResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (CuentasException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}	
		return cambioNipResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public CambioNipResponseTO setNipChangeConfirmation(CambioNipRequestTO cambioNipRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CambioNipResponseTO cambioNipResponseTO = new CambioNipResponseTO();
		CuentasDAO cuentasDAO =  new CuentasDAO();
		String query = "for $medPago in //elite/listaCuentas return <elite>{$medPago}</elite>";
		XmlDecoder decoder = new XmlDecoder();
		
		try{
			MessageElement messageElement = cuentasDAO.setNipChangeConfirmation(cambioNipRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCambioNipConfirmation();
												
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (CuentasException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}		
		return cambioNipResponseTO;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public LockUnlockCardsResponseTO informacionBloquearDesbloquear(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) 
	throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		
		LockUnlockCardsResponseTO lockUnlockCardsResponseTO = null;
		CancelacionElementTO tarjeta = null;
		String fechaUltimoMovimiento = "";
		
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();
			DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
			MessageElement messageElement = cuentasDAO.informacionBloquearDesbloquearWait(lockUnlockCardsRequestTO);
									
			CuentasRule cuentasRuleWait = new CuentasRule(messageElement);				
			cuentasRuleWait.validaInformacionTarjetaWait();
			
			messageElement = cuentasDAO.informacionBloquearDesbloquear(lockUnlockCardsRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateByActionErrors();
			cuentasRule.validaInformacionTarjeta();
			
			XmlDecoder decoder = new XmlDecoder();
			fechaUltimoMovimiento = (String)decoder.toDeserialize("fechaUltimoMov", messageElement, null);
			
			fechaUltimoMovimiento = Formatter.formatoFechaHora(fechaUltimoMovimiento);
										
			tarjeta = new CancelacionElementTO();
			tarjeta.setEstado(lockUnlockCardsRequestTO.getEstadoTarjeta());
			tarjeta.setFechaUltimoMovimiento(fechaUltimoMovimiento);
			tarjeta.setTarjeta(lockUnlockCardsRequestTO.getNumTarjeta());
			tarjeta.setSituacion(lockUnlockCardsRequestTO.getEstadoTarjeta());				
			tarjeta.setNombre(lockUnlockCardsRequestTO.getNombre());
			tarjeta.setOpcion(lockUnlockCardsRequestTO.getOperacion());
			
			lockUnlockCardsResponseTO = new LockUnlockCardsResponseTO();
			
			DigitalFingerUtil digitalFingerUtil = new DigitalFingerUtil();
			dispositivoHuellaTO = digitalFingerUtil.getDigitalFingerUtil(messageElement);

			//SOLO MIENTRAS SON PRUEBAS
			//dispositivoHuellaTO.setLlavePublica("22");
			//dispositivoHuellaTO.setLongitudHuella("34");

			lockUnlockCardsResponseTO.setTarjeta(tarjeta);	
			lockUnlockCardsResponseTO.setDispositivoHuellaTO(dispositivoHuellaTO);
			
		} catch (DAOException e) {			
			e.printStackTrace();
			throw new CuentasException(e);			
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new CuentasException(e);		
		
		} 
		return lockUnlockCardsResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public LockUnlockCardsResponseTO aceptarCardBlocking(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) 
	throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		LockUnlockCardsResponseTO lockUnlockCardsResponseTO = null;
		MessageElement messageElement = null;
		String folio = "";
		String fechaUltimoMovimiento = null;
		try {
			CuentasDAO cuentasDAO = new CuentasDAO();	
			lockUnlockCardsResponseTO = new LockUnlockCardsResponseTO();
			CancelacionElementTO tarjeta = new CancelacionElementTO();
			
			messageElement = cuentasDAO.aceptarCardBlocking(lockUnlockCardsRequestTO);
						
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			
			cuentasRule.validateByActionErrors();
			cuentasRule.validateConfirmCancelDispatch();
					
			XmlDecoder decoder = new XmlDecoder();
			folio = (String)decoder.toDeserialize("folio", messageElement, null);				
			fechaUltimoMovimiento = (String)decoder.toDeserialize("fechaUltimoMov", messageElement, null);
					
			fechaUltimoMovimiento = Formatter.formatoFechaHora(fechaUltimoMovimiento);
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("cancelacionForm", "com.bancoazteca.elite.beans.CancelacionElementTO");
	 
			tarjeta = (CancelacionElementTO)decoder.toDeserialize("cancelacionForm", messageElement, mappedBeans);
			tarjeta.setFolio(folio);
			tarjeta.setFechaUltimoMovimiento(fechaUltimoMovimiento);
			tarjeta.setEstadoAnterior(lockUnlockCardsRequestTO.getEstadoTarjeta());
			lockUnlockCardsResponseTO.setTarjeta(tarjeta);						
			
						
		} catch (DAOException e) {
			throw new CuentasException(e);		
		} 
		return lockUnlockCardsResponseTO;
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public MisFinanzasResponseTO invokeMisFinanzas(BalanceRequestTO balanceRequestTO) 
	throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		
		MisFinanzasResponseTO misFinanzasResponse = new MisFinanzasResponseTO();
		MessageElement messageElement = null;
		String query = "for $medPago in //elite/saldosAccounts return <elite>{$medPago}</elite>";
		try {
			
			CuentasDAO cuentasDAO = new CuentasDAO();
			messageElement = cuentasDAO.invokeMisFinanzas(balanceRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateInvokeMisFinanzas();		
			
			XMLDecode decode = new XMLDecode();
			Element elementCuentas = XMLFinder.getElement(messageElement.toString(), query);
			MessageElement messageElementCuentas = new MessageElement(elementCuentas);
			Collection<CuentaMisFinanzasTO> collectionCuentaTO = decode.buildCollectionBeans(CuentaMisFinanzasTO.class, messageElementCuentas, "CuentaVO");					
			
			misFinanzasResponse.setCuentas(collectionCuentaTO);
			
		} catch (DAOException e) {
			throw new CuentasException(e);		
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} 
		return misFinanzasResponse;
	}
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	public MisFinanzasResponseTO invokeMisFinanzasGrafica(BalanceRequestTO balanceRequestTO) 
	throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		
		MisFinanzasResponseTO misFinanzasResponse = new MisFinanzasResponseTO();
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Collection<CuentaMisFinanzasTO> collectionCuentaTO = null;
		XmlDecoder decoder = new XmlDecoder();
		
		try {
			String query = "for $medPago in //elite/ebanking_web_ClienteVO/cuentas return <elite>{$medPago}</elite>";
			CuentasDAO cuentasDAO = new CuentasDAO();
			messageElement = cuentasDAO.invokeMisFinanzasGrafica(balanceRequestTO);
			
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateInvokeMisFinanzas();
			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaMisFinanzasTO");
			mappedBeans.put("MovimientoMisFinanzasVO", "com.bancoazteca.elite.beans.MovimientosMisFinanzasTO");
			
			Element elementTotales = XMLFinder.getElement(messageElement.toString(), query);
			MessageElement messageElementTotales = new MessageElement(elementTotales);
	 
			collectionCuentaTO =(Collection<CuentaMisFinanzasTO>) decoder.toDeserialize("cuentas", messageElementTotales, mappedBeans);
			
			misFinanzasResponse.setCuentas(collectionCuentaTO);
			
		} catch (DAOException e) {
			throw new CuentasException(e);		
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} 
		return misFinanzasResponse;		
	}
	
	@SuppressWarnings("unchecked")
	public MisFinanzasResponseTO consultarMisFinanzas(BalanceRequestTO balanceRequestTO) 
	throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		
		MessageElement messageElement = null;
		MisFinanzasResponseTO misFinanzasResponse = new MisFinanzasResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		
		XmlDecoder decoder = new XmlDecoder();
		XMLDecode decode = new XMLDecode();
		Collection<CuentaMisFinanzasTO> collectionCuentaTO = null;
		DetalleMovimientosMisFinanzasTO detalleMovimientosMisFinanzasTO = null;
		HashMap<String, String> mappedBeans = new HashMap<String, String>();
		
		try {			
			messageElement = cuentasDAO.consultarMisFinanzas(balanceRequestTO);
			String queryTotales = "for $medPago in //elite/ebanking_web_BeanKey/array/ConsultaMovimientosBean return <elite>{$medPago}</elite>";
			
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateConsultarMisFinanzas();		
			
			mappedBeans.put("CuentaVO", "com.bancoazteca.elite.beans.CuentaMisFinanzasTO");
			mappedBeans.put("MovimientoMisFinanzasVO", "com.bancoazteca.elite.beans.MovimientosMisFinanzasTO");
	 
			collectionCuentaTO =(Collection<CuentaMisFinanzasTO>) decoder.toDeserialize("saldosAccounts", messageElement, mappedBeans);
			
			misFinanzasResponse.setCuentas(collectionCuentaTO);
			
			Element elementTotales = XMLFinder.getElement(messageElement.toString(), queryTotales);
			MessageElement messageElementTotales = new MessageElement(elementTotales);
			
			mappedBeans.clear();
			
			mappedBeans.put("ConsultaMovimientosBean", "com.bancoazteca.elite.beans.DetalleMovimientosMisFinanzasTO");
			detalleMovimientosMisFinanzasTO =(DetalleMovimientosMisFinanzasTO) decoder.toDeserialize("ConsultaMovimientosBean", messageElementTotales, mappedBeans);
			
			misFinanzasResponse.setDetalleMovimientosMisFinanzasTO(detalleMovimientosMisFinanzasTO);
			
		} catch (DAOException e) {
			misFinanzasResponse.setCuentas(new ArrayList<CuentaMisFinanzasTO>());
			e.printStackTrace();
//			throw new CuentasException(e);	
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		} 
		return misFinanzasResponse;
		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<CuentaMisFinanzasTO> getIndexCuenta(Collection<CuentaMisFinanzasTO> collectionCuentaTO){
		
		int index = 0;
		Collection<CuentaMisFinanzasTO> collectionCuentaIndexTO = new ArrayList();
		
		for(CuentaMisFinanzasTO cuentaMisFinanzasTO: collectionCuentaTO){
			cuentaMisFinanzasTO.setIndex(String.valueOf(index));
			cuentaMisFinanzasTO.setCuentaFormateadaCombos(Formatter.formatCuenta(cuentaMisFinanzasTO.getNumero())+" "+cuentaMisFinanzasTO.getDescripcion()+ " $"+cuentaMisFinanzasTO.getDisponible());
			collectionCuentaIndexTO.add(cuentaMisFinanzasTO);				
			index++;
		}
		return collectionCuentaIndexTO;
	}
	
	public EstadoCuentaResponseTO estadoCuentaGetCuentas(EstadoCuentaRequestTO estadoCuentaRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		EstadoCuentaResponseTO estadoCuentaResponseTO = new EstadoCuentaResponseTO();
		CuentasDAO dao = new CuentasDAO();
		String query = "for $cuenta in //elite/estadoCuentaForm return $cuenta";
		XmlDecoder decode = new XmlDecoder();
		
		try{
			MessageElement messageElement = dao.estadoCuentaMenu(estadoCuentaRequestTO);
			CuentasRule rule = new CuentasRule(messageElement);
			rule.estadoCuentaMenuRule();
			messageElement = dao.estadoCuenta(estadoCuentaRequestTO);
			rule = new CuentasRule(messageElement);
			rule.estadoCuentaRule();
			messageElement = dao.estadoCuentaCmd(estadoCuentaRequestTO);
			rule = new CuentasRule(messageElement);
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("LabelValueBean", "com.bancoazteca.elite.beans.LabelValueBeanTO");
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			
			Collection<LabelValueBeanTO> cuentas = (Collection<LabelValueBeanTO>)decode.toDeserialize("cuentas", messageElement,mappedBeans);			
			Map<String, String> mapCuentas = new HashMap<String, String>();
			for (LabelValueBeanTO llaveValor: cuentas){
				mapCuentas.put(String.valueOf(llaveValor.getValue()), llaveValor.getLabel());
			}
			
			estadoCuentaResponseTO.setMapCuentas(mapCuentas);
			
		}catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException();
		}
		return estadoCuentaResponseTO;
	}
	
	@SuppressWarnings("unchecked")
	public EstadoCuentaResponseTO estadoCuentaSeleccionaCuenta(EstadoCuentaRequestTO estadoCuentaRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		EstadoCuentaResponseTO estadoCuentaResponseTO = new EstadoCuentaResponseTO();		
		CuentasDAO dao = new CuentasDAO();
		String query = "for $item in //elite/estadoCuentaForm return $item";
		try{
			MessageElement messageElement = dao.consultaCuenta(estadoCuentaRequestTO);
			CuentasRule rule = new CuentasRule(messageElement);
			rule.estadoCuentaConsulta();
			
			XmlDecoder decode = new XmlDecoder();
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("estadoCuentaForm", "com.bancoazteca.elite.beans.EstadoCuentaResponseTO");
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			
			Collection<String> periodos = (Collection<String>)decode.toDeserialize("periodos", messageElement,null);
			Collection<String> meses = (Collection<String>)decode.toDeserialize("meses", messageElement,null);
			Collection<String> anios = (Collection<String>)decode.toDeserialize("anios", messageElement,null);
			
			Map<String, String> mapPeriodos = new TreeMap<String, String>();
			
			if( periodos != null && periodos.size() > 0 ){
				Object[] temp = periodos.toArray();
				for (int i=0;i< temp.length;i++){
					mapPeriodos.put(String.valueOf(i), (String)temp[i]);
				}	
			}
								
			estadoCuentaResponseTO.setMapPeriodos(mapPeriodos);		
			estadoCuentaResponseTO.setPeriodos(periodos);
			estadoCuentaResponseTO.setMeses(meses);
			estadoCuentaResponseTO.setAnios(anios);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException(e);		
		} catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException(e);
		}				
		return estadoCuentaResponseTO;
	}
	
	public EstadoCuentaResponseTO estadoCuentaEjecutar(EstadoCuentaRequestTO estadoCuentaRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		EstadoCuentaResponseTO estadoCuentaResponseTO = new EstadoCuentaResponseTO();
		CuentasDAO dao = new CuentasDAO();
		String query = "for $estadoCuentaForm in //elite return $estadoCuentaForm";
		try{
			MessageElement messageElement = dao.consultaCuentaEjecutar(estadoCuentaRequestTO);
			CuentasRule rule = new CuentasRule(messageElement);
			rule.estadoCuentaCmdRule();
			
			XmlDecoder decode = new XmlDecoder();			
			HashMap<String, String> mappedBeans = new HashMap<String, String>();
			mappedBeans.put("estadoCuentaForm", "com.bancoazteca.elite.beans.EstadoCuentaResponseTO");
			mappedBeans.put("DetalleMovimientosVO", "com.bancoazteca.elite.beans.DetalleMovimientosTO");
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			
			estadoCuentaResponseTO = (EstadoCuentaResponseTO) decode.toDeserialize("estadoCuentaForm", messageElement, mappedBeans);
			 
//			Collection<DetalleMovimientosTO> coleccionMovCtaSocPluss = new ArrayList<DetalleMovimientosTO>();
//			coleccionMovCtaSocPluss = (Collection<DetalleMovimientosTO>) decode.toDeserialize("movimientos", messageElement, mappedBeans);
//			
//			System.out.println("coleccionMovCtaSocPluss: " + coleccionMovCtaSocPluss);
			
			if (estadoCuentaResponseTO.getEstadoCuenta() != null){
				estadoCuentaResponseTO.setReportName(
						Formatter.getReportName(estadoCuentaResponseTO.getEstadoCuenta().get("account"),
						estadoCuentaResponseTO.getEstadoCuenta().get("period"))
				);
			}
		}catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException();
		} catch (IOException e) {			
			e.printStackTrace();
			throw new CuentasException();
		}catch (Exception e) {			
			e.printStackTrace();
			throw new CuentasException();
		}		
		return estadoCuentaResponseTO;
		
	}	
	
	public MovimientosCuentasInversionTO getMovimientosCuentasInversion(MovimientosCuentaInversionRequest movimientosCuentaInversionRequest) throws CuentasException, SessionExpiredException{
		
		final String queryColeccionMovimientos = "for $a in //elite/ebanking_web_ClienteVO/inversiones return <temp><movimientosInversion type=\"bean\">{$a}</movimientosInversion></temp>";
		CuentasDAO cuentasDAO=new CuentasDAO();
		XmlDecoder decoder=new XmlDecoder();
		MovimientosCuentasInversionTO cuentasInversionTO=null;
		try{	
			MessageElement messageElement=cuentasDAO.getMovimientosGanareMas(movimientosCuentaInversionRequest);
			Collection<MovimientoInversionTO>collectionMovimientosStandard=null;
			Double monto = new Double(0.0);
			double total_cargos = 0.0;
			double total_abonos = 0.0;
			
			if(movimientosCuentaInversionRequest.getProducto().equals("14")){
				try {
				
					MessageElement respadoMessageElementRetiros=XMLDecode.buildXMLMessageElement(messageElement.toString());
					MessageElement respadoMessageElementDesposito=XMLDecode.buildXMLMessageElement(messageElement.toString());
					MessageElement respadoMessageElementInteres=XMLDecode.buildXMLMessageElement(messageElement.toString());
					
					HashMap<String,String>mappedBeans=new HashMap<String, String>(); 
					mappedBeans.put("ConsultaMovInvAztVO","com.bancoazteca.elite.beans.ConsultaMovimientoInvAztTO");
					mappedBeans.put("movimientosInversion","com.bancoazteca.elite.beans.MovimientosInversionAztecaTO");
					
					
					String queryColeccionMovimientosInvAzt = "for $a in //elite/depositoInvAzt return <temp><movimientosInversion type=\"bean\">{$a}</movimientosInversion></temp>";
					Element elem=XMLFinder.getElement(respadoMessageElementDesposito.toString(),queryColeccionMovimientosInvAzt);
					respadoMessageElementDesposito =new MessageElement(elem);
					MovimientosInversionAztecaTO movimientosInversionAztecaTO=(MovimientosInversionAztecaTO)decoder.toDeserialize("movimientosInversion",respadoMessageElementDesposito, mappedBeans);
					
					queryColeccionMovimientosInvAzt = "for $a in //elite/intresComsnvAzt return <temp><movimientosInversion type=\"bean\">{$a}</movimientosInversion></temp>";
					elem=XMLFinder.getElement(respadoMessageElementInteres.toString(),queryColeccionMovimientosInvAzt);
					respadoMessageElementInteres =new MessageElement(elem);
					MovimientosInversionAztecaTO movimientosInversionAztecaTO2=(MovimientosInversionAztecaTO)decoder.toDeserialize("movimientosInversion",respadoMessageElementInteres, mappedBeans);
					
					queryColeccionMovimientosInvAzt = "for $a in //elite/retiroInvAzt return <temp><movimientosInversion type=\"bean\">{$a}</movimientosInversion></temp>";
					elem=XMLFinder.getElement(respadoMessageElementRetiros.toString(),queryColeccionMovimientosInvAzt);
					respadoMessageElementRetiros =new MessageElement(elem);
					MovimientosInversionAztecaTO movimientosInversionAztecaTO3=(MovimientosInversionAztecaTO)decoder.toDeserialize("movimientosInversion",respadoMessageElementRetiros, mappedBeans);
										
					collectionMovimientosStandard=new ArrayList<MovimientoInversionTO>();
					MovimientoInversionTO movInversionTO=null;
					if(movimientosInversionAztecaTO.getDepositoInvAzt()!=null){
						for (ConsultaMovimientoInvAztTO aztTO  : movimientosInversionAztecaTO.getDepositoInvAzt()) {
							movInversionTO=new MovimientoInversionTO();
							movInversionTO.setConcepto(aztTO.getConcepto());
							movInversionTO.setFecha(aztTO.getFechaOperacion());
							movInversionTO.setImporte(aztTO.getMonto());
							movInversionTO.setTasaBrutaAnual(aztTO.getTasa());
							movInversionTO.setTipoMovimiento("deposito");
							collectionMovimientosStandard.add(movInversionTO);
							
							monto = Double.valueOf(aztTO.getMonto());
							total_abonos += monto;
						}
					}

					if(movimientosInversionAztecaTO2.getIntresComsnvAzt()!=null){
						for (ConsultaMovimientoInvAztTO aztTO  : movimientosInversionAztecaTO2.getIntresComsnvAzt()) {
							movInversionTO=new MovimientoInversionTO();
							movInversionTO.setConcepto(aztTO.getConcepto());
							movInversionTO.setFecha(aztTO.getFechaOperacion());
							movInversionTO.setImporte(aztTO.getMonto());
							movInversionTO.setTasaBrutaAnual(aztTO.getTasa());
							movInversionTO.setTipoMovimiento("interes");
							collectionMovimientosStandard.add(movInversionTO);
							
							monto = Double.valueOf(aztTO.getMonto());
							total_abonos += monto;
						}
					}
					if(movimientosInversionAztecaTO3.getRetiroInvAzt()!=null){						
						for (ConsultaMovimientoInvAztTO aztTO  : movimientosInversionAztecaTO3.getRetiroInvAzt()) {
							movInversionTO=new MovimientoInversionTO();
							movInversionTO.setConcepto(aztTO.getConcepto());
							movInversionTO.setFecha(aztTO.getFechaOperacion());
							movInversionTO.setImporte(aztTO.getMonto());
							movInversionTO.setTasaBrutaAnual(aztTO.getTasa());
							movInversionTO.setTipoMovimiento("retiro");
							collectionMovimientosStandard.add(movInversionTO);
							
							monto = Double.valueOf(aztTO.getMonto());
							total_cargos += monto;
						}
					}
					
				} catch (XmlDecodeException e) {
					e.printStackTrace();
				}
			}
			
			decoder=new XmlDecoder();
			Element xml = XMLFinder.getElement(messageElement.toString(),queryColeccionMovimientos);
			messageElement = new MessageElement(xml);
			
			
			HashMap<String, String> mappedBeans=new HashMap<String, String>();
			
			
			
			mappedBeans.put("movimientosInversion","com.bancoazteca.elite.beans.MovimientosCuentasInversionTO");
			mappedBeans.put("CuentaInversionVO","com.bancoazteca.elite.beans.CuentaInversionTO");
			mappedBeans.put("MovimientoVO","com.bancoazteca.elite.beans.MovimientoInversionTO");			
			cuentasInversionTO=(MovimientosCuentasInversionTO)decoder.toDeserialize("movimientosInversion",messageElement,mappedBeans);
			
			if(collectionMovimientosStandard!=null){
				int index=Integer.parseInt(movimientosCuentaInversionRequest.getIndiceCuenta());
				Collection<CuentaInversionTO> inv = cuentasInversionTO.getInversiones();
				int i=0;
				for (CuentaInversionTO cuentaInversionTO : inv) {
					if(i==index){
						if(cuentaInversionTO!=null){
							cuentaInversionTO.setMovimientos(collectionMovimientosStandard);	
							cuentaInversionTO.setTotalAbonos(String.valueOf(total_abonos));
							cuentaInversionTO.setTotalCargos(String.valueOf(total_cargos));
						}
						break;
					}
					i++;
				}
			}
			
		}catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException();
		}catch (IOException e) {
			e.printStackTrace();
			throw new CuentasException();
		}		
		return cuentasInversionTO;
	}
	
	public ChequeraPreaperturaResponseTO getChequeraSolicitud(ChequeraPreaperturaRequestTO chequeraRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		ChequeraPreaperturaResponseTO chequeraResponseTO = new ChequeraPreaperturaResponseTO();
		CuentasDAO dao = new CuentasDAO();
		String query = "for $estadoCuentaForm in //elite return $estadoCuentaForm";
		try{
			MessageElement messageElement = dao.getCuentasChequeraPresolicitud(chequeraRequestTO);
			System.out.println("$$$$$  @@@@@  Message Element: "+messageElement.toString());
			//CuentasRule rule = new CuentasRule(messageElement);
			//rule.estadoCuentaCmdRule();
			
			XmlDecoder decode = new XmlDecoder();			
			//HashMap<String, String> mappedBeans = new HashMap<String, String>();
			//mappedBeans.put("estadoCuentaForm", "com.bancoazteca.elite.beans.EstadoCuentaResponseTO");			
			
			//Element element = XMLFinder.getElement(messageElement.toString(), query);
			//messageElement = new MessageElement(element);
			
			//estadoCuentaResponseTO = (EstadoCuentaResponseTO) decode.toDeserialize("estadoCuentaForm", messageElement, mappedBeans);
			//if (estadoCuentaResponseTO.getEstadoCuenta() != null){
			//	estadoCuentaResponseTO.setReportName(Formatter.getReportName(estadoCuentaResponseTO.getEstadoCuenta().get("account"),estadoCuentaResponseTO.getEstadoCuenta().get("period")));
			//}
		}catch (DAOException e) {
			e.printStackTrace();
			throw new CuentasException();
//		} catch (IOException e) {			
//			e.printStackTrace();
//			throw new CuentasException();
		}catch (Exception e) {			
			e.printStackTrace();
			throw new CuentasException();
		}		
		return chequeraResponseTO;
		
	}	
	
	
	public CuentaSocioPlusResponseTO validaCuentaSocioPlus(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentasFrecuentesResponseTO = new CuentaSocioPlusResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		MessageElement messageCliente=null;
		XmlDecoder xmlDecoder = new XmlDecoder();
		String query="for $a in //elite/ebanking_web_ClienteVO return <temp>{$a}</temp>";
		try {
			MessageElement messageElement = cuentasDAO.validaCuentaSocioPlus(cuentaSocioPlusRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			
			Element element=XMLFinder.getElement(messageElement.toString(),query);
			messageCliente = new MessageElement(element);
			String validaCuentaSocioPlus = ( String ) xmlDecoder.toDeserialize( "socioPlus", messageCliente, null );
			cuentasFrecuentesResponseTO.setValidaCuentaSocioPlus(validaCuentaSocioPlus);
			cuentasRule.validateCuentaSocioPlus();
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} 	
		return cuentasFrecuentesResponseTO;
	}
	

	public RetencionesResponseTO consultarCartaRetenciones(RetencionesRequestTO retencionesRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		RetencionesResponseTO retencionesResponseTO = new RetencionesResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		XmlDecoder decode = new XmlDecoder();
		String query="for $a in //elite/infoRetenSocP return <temp>{$a}</temp>";
		try {
			MessageElement messageElement = cuentasDAO.consultarCartaRetenciones(retencionesRequestTO);
			CuentasRule cuentasRule = new CuentasRule(messageElement);
			cuentasRule.validateCartaRetenciones();
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			
			messageElement = new MessageElement(element);
			HashMap<String, String> retenciones = new HashMap<String, String>();
			retenciones= (HashMap<String, String>) decode.toDeserialize("infoRetenSocP", messageElement,null);
			
			retencionesResponseTO.setTarjetasFrecuentes(retenciones);
			
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} 	
		return retencionesResponseTO;
	}
	
	
//Para consultar movimientos por fecha
	
	
	@SuppressWarnings("unchecked")
	public BalanceResponseTO consultarMovimientosFecha(BalanceRequestTO balanceRequestTO) 
	throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		
		String query = "for $a in //elite/ebanking_web_ClienteVO/cuentas/CuentaVO where $a/numero[@value = " + balanceRequestTO.getAcountNumber() + "] return $a";
		//Para consulta de datos del cliente
		String queryDatosCliente = "for $cliente in //elite/ebanking_web_DataKey/array/ConsultaMovimientosVO return $cliente";
		
		String plusvaliaValorUni = "for $valor in //elite/valorUni return <elite>{$valor}</elite>";
		
		
		MessageElement messageElement = null;
		BalanceResponseTO balanceResponseTO = new BalanceResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
		
		XMLDecode decode = new XMLDecode();
		
		try {			
			messageElement = cuentasDAO.consultarMovimientosFecha(balanceRequestTO);
			
			//Para los datos del cliente
			String fecha_inicio="";
			String fecha_final="";
			String valorUnitario="";
			
			
			Element elementDatosCliente = XMLFinder.getElement(messageElement.toString(), queryDatosCliente);
			MessageElement messageElementCliente = new MessageElement(elementDatosCliente);
			fecha_inicio = decode.getString(messageElementCliente, "inicio");
			fecha_final = decode.getString(messageElementCliente, "termino");
			
			
			String rendimientos = decode.getArrayString(messageElement,"rendimientos");
			String plusvalia = decode.getArrayString(messageElement,"plusvalia");
			
			
			/*Obtenemos el valor de Precio Actual de la unidad*/

			Element elementValorUni = XMLFinder.getElement(messageElement.toString(), plusvaliaValorUni);
			MessageElement messageValorUnitario= new MessageElement(elementValorUni);
			valorUnitario = decode.getString(messageValorUnitario, "valorUni");
			
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<MovimientosTO> movimientos = (Collection<MovimientosTO>) decode.buildCollectionBeans(MovimientosTO.class, messageElement, "DetalleMovimientosVO");
			
			
			
			for(MovimientosTO movSocioPlus: movimientos){
				movSocioPlus.setFecha(Formatter.formatDate(movSocioPlus.getFecha()));
			}

			log.info("total de movimientos " + movimientos.size());
			log.info("rendimientos "+rendimientos);
			log.info("plusvalia "+plusvalia);
			
			balanceResponseTO.setMovimientos(movimientos);
			balanceResponseTO.setRendimientos(rendimientos);
			balanceResponseTO.setPlusvalia(plusvalia);
			balanceResponseTO.setFecha_inicio(fecha_inicio);
			balanceResponseTO.setFecha_final(fecha_final);
			balanceResponseTO.setValorUni(valorUnitario);
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return balanceResponseTO;
		
	}
	
	
	
	//Para consultar movimientos por fecha para cuenta de Nomina y Guardadito
	
	
	@SuppressWarnings("unchecked")
	public BalanceResponseTO consultarMovimientosFechaOtrasCuentas(BalanceRequestTO balanceRequestTO) 
	throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		
		String query = "for $a in //elite/ebanking_web_ClienteVO/cuentas/CuentaVO where $a/numero[@value = " + balanceRequestTO.getAcountNumber() + "] return $a";
		//Para consulta de datos del cliente
		String queryDatosCliente = "for $cliente in //elite/ebanking_web_DataKey/array/ConsultaMovimientosVO return $cliente";
		
		String plusvaliaValorUni = "for $valor in //elite/valorUni return <elite>{$valor}</elite>";
		
		
		MessageElement messageElement = null;
		BalanceResponseTO balanceResponseTO = new BalanceResponseTO();
		CuentasDAO cuentasDAO = new CuentasDAO();
	
		XMLDecode decode = new XMLDecode();
		
		try {			
			messageElement = cuentasDAO.consultarMovimientosFechaOtrasCuentas(balanceRequestTO);
			
			String fecha_inicio="";
			String fecha_final="";
			String valorUnitario="";
			
			
			Element elementDatosCliente = XMLFinder.getElement(messageElement.toString(), queryDatosCliente);
			MessageElement messageElementCliente = new MessageElement(elementDatosCliente);
			fecha_inicio = decode.getString(messageElementCliente, "inicio");
			fecha_final = decode.getString(messageElementCliente, "termino");
			
			
			String rendimientos = decode.getArrayString(messageElement,"rendimientos");
			
			String plusvalia = decode.getArrayString(messageElement,"plusvalia");
			
			
			/*Obtenemos el valor de Precio Actual de la unidad*/

			Element elementValorUni = XMLFinder.getElement(messageElement.toString(), plusvaliaValorUni);
			MessageElement messageValorUnitario= new MessageElement(elementValorUni);
			valorUnitario = decode.getString(messageValorUnitario, "valorUni");
			
			
			Element element = XMLFinder.getElement(messageElement.toString(), query);
			messageElement = new MessageElement(element);
			Collection<MovimientosTO> movimientos = (Collection<MovimientosTO>) decode.buildCollectionBeans(MovimientosTO.class, messageElement, "DetalleMovimientosVO");
		
			for(MovimientosTO movSocioPlus: movimientos){
				movSocioPlus.setFecha(Formatter.formatDate(movSocioPlus.getFecha()));
			}

			log.info("total de movimientos " + movimientos.size());
			log.info("rendimientos "+rendimientos);
			log.info("plusvalia "+plusvalia);
			
			balanceResponseTO.setMovimientos(movimientos);
			balanceResponseTO.setRendimientos(rendimientos);
			balanceResponseTO.setPlusvalia(plusvalia);
			balanceResponseTO.setFecha_inicio(fecha_inicio);
			balanceResponseTO.setFecha_final(fecha_final);
			balanceResponseTO.setValorUni(valorUnitario);
			
		} catch (DAOException e) {
			throw new CuentasException(e);
		} catch (IOException e) {
			throw new CuentasException(e);
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
		return balanceResponseTO;
		
	}
	
}
