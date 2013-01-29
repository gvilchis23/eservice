package com.bancoazteca.elite.ejb.facade;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.http.HttpException;

import com.bancoazteca.elite.beans.*;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.cuentas.CuentasException;
import com.bancoazteca.elite.ejb.cuentas.CuentasSL;
import com.bancoazteca.elite.ejb.cuentas.CuentasSLHome;
import com.bancoazteca.elite.ejb.dispositivos.DispositivoException;
import com.bancoazteca.elite.ejb.dispositivos.DispositivoSL;
import com.bancoazteca.elite.ejb.dispositivos.DispositivoSLHome;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.EliteException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.InversionesSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesSLHome;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaResponseTO;
import com.bancoazteca.elite.ejb.pagoServicios.PagoServiciosException;
import com.bancoazteca.elite.ejb.pagoServicios.PagoServiciosSL;
import com.bancoazteca.elite.ejb.pagoServicios.PagoServiciosSLHome;
import com.bancoazteca.elite.ejb.preferencias.PreferenciasSL;
import com.bancoazteca.elite.ejb.preferencias.PreferenciasSLHome;
import com.bancoazteca.elite.ejb.transferencias.TransferenciasException;
import com.bancoazteca.elite.ejb.transferencias.TransferenciasSL;
import com.bancoazteca.elite.ejb.transferencias.TransferenciasSLHome;
import com.bancoazteca.elite.ejb.traspasos.TraspasosException;
import com.bancoazteca.elite.ejb.traspasos.TraspasosSL;
import com.bancoazteca.elite.ejb.traspasos.TraspasosSLHome;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.ejb.usuario.UsuarioSL;
import com.bancoazteca.elite.ejb.usuario.UsuarioSLHome;
import com.bancoazteca.elite.monitoreo.db.dao.MonitoreoAdministradorResponseTO;
import com.bancoazteca.elite.util.JNDINames;
import com.bancoazteca.elite.util.ServiceLocator;
import com.bancoazteca.elite.util.ServiceLocatorException;
/**
 * @author Banco Azteca S.A. [JFAV] Agosto 20, 2008.
 */

public class ResourceFacadeSLBean implements SessionBean {

	private static final long serialVersionUID = 5040696197901868927L;

	private SessionContext context;

	private UsuarioSL usuarioSession;
	private CuentasSL cuentasSession;
	private TraspasosSL traspasosSession;
	private PreferenciasSL preferenciasSession;
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
	
	public void setXmlBeanRules() throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		usuarioSL.setXmlBeanRules();
	}
	

	public LoginResponseTO login(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		UsuarioSL usuarioSL  = getUsuarioSession();
		return usuarioSL.login(loginRequestTO);
	}
	
	public LoginResponseTO loginCliente(LoginRequestTO loginRequestTO) throws UsuarioException, LoginException, SessionExpiredException, EliteDataException
	{
		UsuarioSL usuarioSL  = getUsuarioSession();
		return usuarioSL.loginCliente(loginRequestTO);
	}
	
	public LoginResponseTO getOnDemandAccounts(LoginRequestTO loginRequestTO) throws UsuarioException, LoginException, SessionExpiredException, EliteDataException
	{
		UsuarioSL usuarioSL  = getUsuarioSession();
		return usuarioSL.getOnDemandAccounts(loginRequestTO);
	}

	public LoginResponseTO sessionManagment(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.sessionManagment(loginRequestTO);
	}

	public LoginResponseTO sessionManagmentCliente(LoginRequestTO loginRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.sessionManagmentCliente(loginRequestTO);
	}
	
	public LoginResponseTO getCuentasUsuario(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getCuentasUsuario(loginRequestTO);
	}
	
	public LoginResponseTO getActualizarCuentaUsuario( BalanceRequestTO balanceRequestTO ) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getActualizarCuentaUsuario( balanceRequestTO );
	}

	public LoginResponseTO getCuentasUsuarioParalelo(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getCuentasUsuarioParalelo(loginRequestTO);
	}

	public ChangePasswordResponseTO changePassword(ChangePaswordRequestTO changePaswordRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException {
		usuarioSession = getUsuarioSession();
		return usuarioSession.changePassword(changePaswordRequestTO);
	}

	public void logout(String username) throws EJBException, SessionExpiredException, UsuarioException {
		usuarioSession = getUsuarioSession();
		usuarioSession.logout(username);
	}

	public BalanceResponseTO getMovimientosByIndex(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.getMovimientosByIndex(balanceRequestTO);
	}
	
	public BalanceResponseTO getMovimientosByDate(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		cuentasSession = getCuentasSession();
		return cuentasSession.getMovimientosByDate(balanceRequestTO);
	}
	
	public BalanceResponseTO getPartnerPlusBalanceAccount(BalanceRequestTO balanceRequestTO)throws  CuentasException, SessionExpiredException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.getPartnerPlusBalanceAccount(balanceRequestTO);
	}

	public TraspasosPropiasResponsetTO propiasInvocaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException{
		traspasosSession = geTraspasosSession();
		return traspasosSession.propiasInvocaTraspaso(traspasosPropiasRequestTO);
	}
	
	public TraspasosPropiasResponsetTO propiasPreparaTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException {
		traspasosSession = geTraspasosSession();
		return traspasosSession.propiasPreparaTraspaso(traspasosPropiasRequestTO);
	}

	public TraspasosPropiasResponsetTO ejecutaPropiasTraspaso(TraspasosPropiasRequestTO traspasosPropiasRequestTO) throws EJBException, TraspasosException, SessionExpiredException, EliteDataException {
		traspasosSession = geTraspasosSession();
		return traspasosSession.ejecutaPropiasTraspaso(traspasosPropiasRequestTO);
	}

	public InternetSalesResponseTO getTarjetasComprasInternet(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.getTarjetasComprasInternet(internetSalesRequestTO);
	}

	public InternetSalesResponseTO getComprasInternetEstadoTarjeta(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, EliteDataException, CuentasException, SessionExpiredException {
		cuentasSession = getCuentasSession();
		return cuentasSession.getComprasInternetEstadoTarjeta(internetSalesRequestTO);
	}

	public InternetSalesResponseTO setComprasInternetActivacion(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.setComprasInternetActivacion(internetSalesRequestTO);
	}

	public LockUnlockCardsResponseTO listCardsToLock(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.findAllCardsLockandUnlock(lockUnlockCardsRequestTO);
	}

	public LockUnlockCardsResponseTO initBlockingCards(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.initBlockingCards(lockUnlockCardsRequestTO);
	}

	public LockUnlockCardsResponseTO initUnlockingCards(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.initUnlockingCards(lockUnlockCardsRequestTO);
	}

	public LockUnlockCardsResponseTO confirmCardBlocking(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EJBException, EliteException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.confirmCardBlocking(lockUnlockCardsRequestTO);
	}

	public LockUnlockCardsResponseTO confirmCardUnlock(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EJBException, EliteException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.confirmCardUnlock(lockUnlockCardsRequestTO);
	}

	/**
	 * Recupera las tarjetas que puede ser cambiada la sucursal donde se
	 * entregan a través del EJB de Cuentas.
	 * 
	 * @param clientId
	 * @return ChangeBranchRsponseTO CambiosSucursalTO.
	 * @throws EJBException
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 */
	public CambioSucursalTO getCambioSucursalInicio(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.getCambioSucursalInicio(changeBranchRequestTO);
	}
	
	public CambioSucursalTO getDatosSucursalTarjeta(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		cuentasSession = getCuentasSession();
		return cuentasSession.getDatosSucursalTarjeta(user);
	}

	public CambioSucursalTO getDatosBusquedaSucursalesTarjeta(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		cuentasSession = getCuentasSession();
		return cuentasSession.getDatosBusquedaSucursalesTarjeta(user);
	}
	
	public CambioSucursalTO confirmaCambioSucursalTarjeta(ChangeBranchRequestTO branchRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		cuentasSession = getCuentasSession();
		return cuentasSession.confirmaCambioSucursalTarjeta(branchRequestTO);
	}
	
	/**
	 * Devuelve una lista de los municpios del estado que se haya elegido en la
	 * clave del estado.
	 * 
	 * @param clientId
	 * @param estado
	 * @return
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 */
	public ChangeBranchResponseTO getMunicipiosEntidad(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {

		cuentasSession = getCuentasSession();
		return cuentasSession.getMunicipiosEntidad(changeBranchRequestTO);
	}

	/**
	 * Recupera las sucursales que se pueden buscar por medio del C.P. o por
	 * medio de Estado y municipio. atrav&eacute;s del EJB de Cuentas.
	 * 
	 * @param clientId
	 * @param tipoBusqueda
	 * @param codigoPostal
	 * @param estado
	 * @param municipio
	 * @return Collection<ResultadoBusquedaSucursalTO> sucursales.
	 * @throws EJBException
	 * @throws EliteDataException
	 */
	public ChangeBranchResponseTO buscarCentrosCambioSucursal(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {

		cuentasSession = getCuentasSession();
		return cuentasSession.buscarCentrosCambioSucursal(changeBranchRequestTO);
	}
	
	public EstatusCuentaResponseTO getCuentasUsuarioStatus(EstatusCuentaRequestTO statusCuentaRequestTO) throws EJBException, UsuarioException, DAOException{
		preferenciasSession = getPreferenciasSession();
		return preferenciasSession.getCuentasUsuarioStatus(statusCuentaRequestTO);
	}
	
	public EstatusCuentaResponseTO getCambiarEstatus(EstatusCuentaRequestTO statusCuentaRequestTO) throws EJBException, UsuarioException, DAOException{
		preferenciasSession = getPreferenciasSession();
		return preferenciasSession.getCambiarEstatus(statusCuentaRequestTO);
	}
	
	public FiltroCuentaResponseTO filtraCuentasOcultas(FiltroCuentaRequestTO filtroCuentaRequestTO) throws EJBException, UsuarioException, DAOException, SessionExpiredException, EliteDataException {
		preferenciasSession = getPreferenciasSession();
		return preferenciasSession.filtraCuentasOcultas(filtroCuentaRequestTO); 
	}
	 
	public PreguntasFrecuentesResponseTO getPreguntasFrecuentes() throws EJBException, UsuarioException, SessionExpiredException, EliteDataException, IOException, XmlDecodeException {
		preferenciasSession = getPreferenciasSession();
		return preferenciasSession.getPreguntasFrecuentes();
	}

	private PreferenciasSL getPreferenciasSession() throws EJBException, UsuarioException {
		try {
			PreferenciasSLHome usuarioHome = (PreferenciasSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.PREFERENCIAS);
			return (PreferenciasSL) usuarioHome.create();

		} catch (ServiceLocatorException slex) {
			slex.printStackTrace();
			throw new UsuarioException(slex.getMessage());
		} catch (EJBException rex) {
			rex.printStackTrace();
			throw new UsuarioException(rex.getMessage());
		} catch (CreateException cex) {
			cex.printStackTrace();
			throw new UsuarioException(cex.getMessage());
		}
	}
	
	
	private UsuarioSL getUsuarioSession() throws EJBException, UsuarioException {
		try {
			UsuarioSLHome usuarioHome = (UsuarioSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.USUARIO);
			return (UsuarioSL) usuarioHome.create();

		} catch (ServiceLocatorException slex) {
			slex.printStackTrace();
			throw new UsuarioException(slex.getMessage());
		} catch (EJBException rex) {
			rex.printStackTrace();
			throw new UsuarioException(rex.getMessage());
		} catch (CreateException cex) {
			cex.printStackTrace();
			throw new UsuarioException(cex.getMessage());
		}
	}

	/**
	 * Obtiene el EJB de Cuentas.
	 * 
	 * @return CuentasSession
	 * @throws EJBException
	 * @throws CuentasException
	 */
	private CuentasSL getCuentasSession() throws EJBException, CuentasException {
		try {
			CuentasSLHome cuentasHome = (CuentasSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.CUENTAS);
			return (CuentasSL) cuentasHome.create();

		} catch (ServiceLocatorException slex) {
			slex.printStackTrace();
			throw new CuentasException(slex.getMessage());
		} catch (CreateException cex) {
			cex.printStackTrace();
			throw new CuentasException(cex.getMessage());
		}
	}

	/**
	 * Obtiene el EJB de Traspasos.
	 * 
	 * @return TraspasosSession
	 * @throws EJBException
	 * @throws TraspasosException
	 */
	private TraspasosSL geTraspasosSession() throws EJBException, TraspasosException {
		try {
			TraspasosSLHome traspasosHome = (TraspasosSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.TRASPASOS);
			return (TraspasosSL) traspasosHome.create();
		} catch (ServiceLocatorException slex) {
			throw new TraspasosException(slex);
		} catch (CreateException cex) {
			throw new TraspasosException(cex);
		}

	}

	private TransferenciasSL getTransferenciasSession() throws EJBException, TransferenciasException {
		try {
			TransferenciasSLHome transferenciasSLHome = (TransferenciasSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.TRANSFERENCIAS);
			return (TransferenciasSL) transferenciasSLHome.create();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			throw new TransferenciasException(e);
		} catch (CreateException e) {
			e.printStackTrace();
			throw new TransferenciasException(e);
		}
	}
	
	private InversionesSL getInversionesSL() throws EJBException, InversionesGenericException {
		InversionesSL inversionesSL = null;
		try {
			InversionesSLHome inversionesSLHome = (InversionesSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.INVERSIONES);			
			inversionesSL = (InversionesSL) inversionesSLHome.create();
		} catch (ServiceLocatorException slex) {
			slex.printStackTrace();
			throw new InversionesGenericException();
		} catch (EJBException rex) {
			rex.printStackTrace();
			throw new InversionesGenericException();
		} catch (CreateException cex) {
			cex.printStackTrace();
			throw new InversionesGenericException();
		}
		return inversionesSL;
	}
	
	private DispositivoSL getDispositivoSession() throws EJBException, DispositivoException {
		try {
			DispositivoSLHome dispositivoHome = (DispositivoSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.DISPOSITIVOS);
			return (DispositivoSL) dispositivoHome.create();

		} catch (ServiceLocatorException slex) {
			slex.printStackTrace();
			throw new DispositivoException(slex.getMessage());
		} catch (EJBException rex) {
			rex.printStackTrace();
			throw new DispositivoException(rex.getMessage());
		} catch (CreateException cex) {
			cex.printStackTrace();
			throw new DispositivoException(cex.getMessage());
		}
	}


	public RecuperaPasswordResponseTO findAccountOrCreditCardNumber(RecuperaPasswordRequestTO recuperaUsuarioPasswordTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException {
		usuarioSession = getUsuarioSession();
		return usuarioSession.sendAccountOrCreditCardNumber(recuperaUsuarioPasswordTO);
	}

	public RecuperaPasswordResponseTO findPersonalData(RecuperaPasswordRequestTO recuperaUsuarioPasswordTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException {
		usuarioSession = getUsuarioSession();
		return usuarioSession.sendPersonalData(recuperaUsuarioPasswordTO);
	}

	public void findChangePersonalData(RecuperaPasswordRequestTO passwordRequestTO)	throws EJBException, EliteDataException, UsuarioException, SessionExpiredException {
		usuarioSession = getUsuarioSession();
		usuarioSession.sendChangePersonalData(passwordRequestTO);
	}
	public RecuperaPasswordResponseTO updateToNewPassword(RecuperaPasswordRequestTO recuperaUsuarioPasswordTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException {
		usuarioSession = getUsuarioSession();
		return usuarioSession.updateToNewPassword(recuperaUsuarioPasswordTO);
	}

	public AlertsDataResponseTO alertsLoadInitialData(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		cuentasSession = getCuentasSession();
		return cuentasSession.alertsLoadInitialData(user);
	}

	public AlertsDataResponseTO setAlertsAcountsSelectedLink(AlertsDataRequest request) throws EJBException, CuentasException, EliteDataException, SessionExpiredException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.setAlertsAcountsSelectedLink(request);
	}

	public AlertsDataResponseTO getDataAlertFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getDataAlertFirstStep(request);
	}

	public AlertsDataResponseTO getDataAlertSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getDataAlertSecondStep(request);
	}

	public void getDataAlertThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataAlertThirdStep(request);

	}

	public AlertsDataResponseTO getAlertsAcountsSelectedLinkUpdate(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getAlertsAcountsSelectedLinkUpdate(request);
	}

	public AlertsDataResponseTO getDataForUpdateAlertFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getDataForUpdateAlertFirstStep(request);
	}
	
	public AlertsDataResponseTO getDataForUpdateAlertFirstStepModificar(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getDataForUpdateAlertFirstStepModificar(request);
	}

	public AlertsDataResponseTO getDataForUpdateAlertFinalStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getDataForUpdateAlertFinalStep(request);
	}

	public void getDataForUpdateAlertThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataForUpdateAlertThirdStep(request);
	}

	public void getAlertCardAcountsSelectedLink(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getAlertCardAcountsSelectedLink(request);
	}

	public void getDataForActivateCardsFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataForActivateCardsFirstStep(request);
	}

	public void getDataForActivateCardsSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataForActivateCardsSecondStep(request);
	}

	public void getDataForActivateCardsThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataForActivateCardsThirdStep(request);
	}

	public void getAlertsCardSelectedLinkUpdate(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getAlertsCardSelectedLinkUpdate(request);

	}

	public void getDataForModifyCardsFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataForModifyCardsFirstStep(request);
	}

	public void getDataForModifyCardsSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataForModifyCardsSecondStep(request);
	}

	/*
	 * public void getDataModifyCardsThirdStep (AlertsDataRequest request)
	 * throws RemoteException, CuentasException, SessionExpiredException,
	 * EliteDataException{ CuentasSL cuentasSL = getCuentasSession();
	 * cuentasSL.getDataModifyCardsThirdStep(request); }
	 */

	public void getDataForDeleteCards(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.getDataForDeleteCards(request);
	}

	public AlertsDataResponseTO getDataForDeleteAccounts(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getDataForDeleteAccounts(request);
	}

	public InfiniteMovimientosResponseTO getInfiniteDetalleMovimientos(InfiniteMovimientosRequestTO infiniteMovimientosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getInfiniteDetalleMovimientos(infiniteMovimientosRequestTO);
	}

	public InfiniteMovimientosResponseTO getInfiniteAdicionalesDetalleMovimientos(InfiniteMovimientosRequestTO infiniteMovimientosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getInfiniteAdicionalesDetalleMovimientos(infiniteMovimientosRequestTO);
	}

	public TarjetaCorporativaCreditoResponseTO getTarjetaCorporativaCredito(TarjetaCorporativaCreditoRequestTO corporativaCreditoRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getTarjetaCorporativaCredito(corporativaCreditoRequestTO);
	}

	public TarjetaCorporativaDebitoResponseTO getTarjetaCorporativaDebito(TarjetaCorporativaDebitoRequestTO tarjetaCorporativaDebitoRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getTarjetaCorporativaDebito(tarjetaCorporativaDebitoRequestTO);
	}

	public TransferenciaTercerosResponseTO getTransferenciaTercerosInvocacion(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaTercerosInvocacion(transferenciaTercerosRequestTO);
	}

	public TransferenciaTercerosResponseTO getTransferenciaTercerosEnvioDatos(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaTercerosEnvioDatos(transferenciaTercerosRequestTO);
	}

	public TransferenciaTercerosResponseTO getEjecutarTransferenciaTerceros(TransferenciaTercerosRequestTO transferenciaTercerosRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getEjecutarTransferenciaTerceros(transferenciaTercerosRequestTO);
	}

	public EnvioMailResponseTO envioMailTerceros(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.envioMailTerceros(envioMailRequestTO);
	}
	
	public TransferenciasSpeiResponseTO getTransferenciaSpeiInvocacion(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaSpeiInvocacion(transferenciasSpeiRequestTO);
	}

	public TransferenciasSpeiResponseTO getTransferenciaSpeiEnvioDatos(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaSpeiEnvioDatos(transferenciasSpeiRequestTO);
	}

	public TransferenciasSpeiResponseTO getEjecutarTransferenciaSpei(TransferenciasSpeiRequestTO transferenciasSpeiRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getEjecutarTransferenciaSpei(transferenciasSpeiRequestTO);
	}

	public EnvioMailResponseTO envioMailSpei(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.envioMailSpei(envioMailRequestTO);
	}

	public EnvioMailResponseTO envioMailSpei30(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.envioMailSpei30(envioMailRequestTO);
	}
	
	public TransferenciasTEFResponseTO getTransferenciaTefInvocacion(TransferenciasTEFRequestTO transferenciasTEFRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaTefInvocacion(transferenciasTEFRequestTO);
	}

	public TransferenciasTEFResponseTO getTransferenciaTefEnvioDatos(TransferenciasTEFRequestTO transferenciasTEFRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaTefEnvioDatos(transferenciasTEFRequestTO);
	}

	public TransferenciasTEFResponseTO getEjecutarTransferenciaTef(TransferenciasTEFRequestTO transferenciasTEFRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getEjecutarTransferenciaTef(transferenciasTEFRequestTO);
	}

	public EnvioMailResponseTO envioMailTef(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.envioMailTef(envioMailRequestTO);
	}

	public TransferenciasInternacionalesResponseTO getTransferenciaInternacionalInvocacion(TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaInternacionalInvocacion(transferenciasInternacionalesRequestTO);
	}

	public TransferenciasInternacionalesResponseTO getTransferenciaInternacionalEnvioDatos(TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getTransferenciaInternacionalEnvioDatos(transferenciasInternacionalesRequestTO);
	}
	
	public TransferenciasInternacionalesResponseTO getEjecutarTransferenciaInternacional (TransferenciasInternacionalesRequestTO transferenciasInternacionalesRequestTO)throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		TransferenciasInternacionalesResponseTO internacionalesResponseTO = transferenciasSL.getEjecutarTransferenciaInternacional(transferenciasInternacionalesRequestTO);
		return internacionalesResponseTO;
	}
	
	public  EnvioMailResponseTO envioMailInternacional(EnvioMailRequestTO envioMailRequestTO) throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.envioMailInternacional(envioMailRequestTO);
	} 
	
	public CuentasFrecuentesResponseTO findAllFrequentAccounts(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {

		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.findAllFrequentAccounts(cuentasFrecuentesRequestTO);
	}

	public CuentasFrecuentesResponseTO getCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = cuentasSL.getCuentasFrecuentes(cuentasFrecuentesRequestTO);
		return cuentasFrecuentesResponseTO;
	}

	public CuentasFrecuentesResponseTO getHistoricasCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = cuentasSL.getHistoricasCuentasFrecuentes(cuentasFrecuentesRequestTO);
		return cuentasFrecuentesResponseTO;
	}

	public CuentasFrecuentesResponseTO setOtrosBancosPreparacionAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = cuentasSL.setOtrosBancosPreparacionAgregarCuenta(cuentasFrecuentesRequestTO);
		return cuentasFrecuentesResponseTO;

	}

	public DispositivoHuellaTO setIntenationalesDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.setIntenationalesDatosAgregarCuenta(cuentasFrecuentesRequestTO);
	}

	public void setOtrosBancosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.setOtrosBancosAgregarCuenta(cuentasFrecuentesRequestTO);
	}

	public void setTercerosConfirmarAltaFrecuente(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.setTercerosConfirmarAltaFrecuente(cuentasFrecuentesRequestTO);
	}
	
	public DispositivoHuellaTO setTercerosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.setTercerosDatosAgregarCuenta(cuentasFrecuentesRequestTO);
	}

	public DispositivoHuellaTO setOtrosBancosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.setOtrosBancosDatosAgregarCuenta(cuentasFrecuentesRequestTO);
	}

	public CuentasFrecuentesResponseTO getIntenationalesDatosEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = cuentasSL.getIntenationalesDatosEliminaCuenta(cuentasFrecuentesRequestTO);
		return cuentasFrecuentesResponseTO;
	}

	public void setIntenationalesEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.setIntenationalesEliminaCuenta(cuentasFrecuentesRequestTO);
	}

	public CuentasFrecuentesResponseTO getModificaDatosCuentaFrecuentesTraspasos(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		CuentasFrecuentesResponseTO cuentasFrecuentesResponseTO = cuentasSL.getModificaDatosCuentaFrecuentesTraspasos(cuentasFrecuentesRequestTO);
		return cuentasFrecuentesResponseTO;
	}
	
	public CuentasFrecuentesResponseTO setModificaDatosCuentaFrecuentesTraspasosConf(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.setModificaDatosCuentaFrecuentesTraspasosConf(cuentasFrecuentesRequestTO);
	}
	
	public void setGuardaModificaCuentaFrecuentesTraspasos(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		cuentasSL.setGuardaModificaCuentaFrecuentesTraspasos(cuentasFrecuentesRequestTO);
	}

	public InternacionalesBancosResponseTO getInternacionalesPaises(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		InternacionalesBancosResponseTO internacionalesBancosResponseTO = transferenciasSL.getInternacionalesPaises(internacionalesBancosRequestTO);
		return internacionalesBancosResponseTO;
	}

	public InternacionalesBancosResponseTO getInternacionalesCiudades(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		InternacionalesBancosResponseTO internacionalesBancosResponseTO = transferenciasSL.getInternacionalesCiudades(internacionalesBancosRequestTO);
		return internacionalesBancosResponseTO;
	}

	public InternacionalesBancosResponseTO getInternacionalesBancos(InternacionalesBancosRequestTO internacionalesBancosRequestTO) throws SessionExpiredException, TransferenciasException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		InternacionalesBancosResponseTO internacionalesBancosResponseTO = transferenciasSL.getInternacionalesBancos(internacionalesBancosRequestTO);
		return internacionalesBancosResponseTO;
	}

	public Collection<CuentasTransferenciasTO> getListadoCuentasSuggest(Collection<LabelValueBeanTO> cuentasTodas,TransferenciaTercerosResponseTO tercerosResponseTO,TransferenciasTEFResponseTO tefResponseTO,TransferenciasSpeiResponseTO speiResponseTO,TransferenciasInternacionalesResponseTO internacionalesResponseTO)throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		Collection<CuentasTransferenciasTO> collectionCuentasSuggest = transferenciasSL.getListadoCuentasSuggest(cuentasTodas, tercerosResponseTO, tefResponseTO, speiResponseTO, internacionalesResponseTO);
		return collectionCuentasSuggest;
	}
	
	public ChangeSecurityLevelResponseTO modifySecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException {
		usuarioSession = getUsuarioSession();
		return usuarioSession.modifySecurityLevel(changeSecurityLevelRequestTO);
	}

	public ChangeSecurityLevelResponseTO waitChangeSecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException {
		usuarioSession = getUsuarioSession();
		return usuarioSession.waitChangeSecurityLevel(changeSecurityLevelRequestTO);
	}
	
	public Collection<TransferenciasOtrosBancosTO> getListadoBancosTefSpeiResponseTO(TransferenciasTEFResponseTO transferenciasTEFResponseTO,TransferenciasSpeiResponseTO transferenciasSpeiResponseTO)throws EJBException, TransferenciasException, SessionExpiredException, EliteDataException {
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.getListadoBancosTefSpeiResponseTO(transferenciasTEFResponseTO, transferenciasSpeiResponseTO);
	}

	public ChangeSecurityLevelResponseTO executeChangeLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException {

		usuarioSession = getUsuarioSession();
		return usuarioSession.executeChangeLevel(changeSecurityLevelRequestTO);
	}
	
	public PagoServiciosTelmexResponseTO setInitialTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosTelmexResponseTO = pagoServiciosSL.setInitialTelmexPayment(pagoServiciosTelmexRequestTO);		
		return pagoServiciosTelmexResponseTO;
	}
	public PagoServiciosTelmexResponseTO setDataTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosTelmexResponseTO = pagoServiciosSL.setDataTelmexPayment(pagoServiciosTelmexRequestTO);		
		return pagoServiciosTelmexResponseTO;
	}
	public PagoServiciosTelmexResponseTO setConfimTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosTelmexResponseTO pagoServiciosTelmexResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosTelmexResponseTO = pagoServiciosSL.setConfimTelmexPayment(pagoServiciosTelmexRequestTO);		
		return pagoServiciosTelmexResponseTO;
	}
	public FrecuentesResponseTO setEliminarFrecuentesEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setEliminarFrecuentesEjecucionParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setEliminarFrecuentesValidacionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setEliminarFrecuentesValidacionParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setModificarFrecuentesParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setModificarFrecuentesParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setModificarFrecuentesValidacionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setModificarFrecuentesValidacionParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setModificarFrecuentesEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setModificarFrecuentesEjecucionParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public PagoServiciosIusacellResponseTO setInitialIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosIusacellResponseTO pagoServiciosIusacellResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosIusacellResponseTO = pagoServiciosSL.setInitialIusacellPayment(iusacellRequestTO);
		return pagoServiciosIusacellResponseTO;
	}
	
	public PagoServiciosIusacellResponseTO setDataIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosIusacellResponseTO pagoServiciosIusacellResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosIusacellResponseTO = pagoServiciosSL.setDataIusacellPayment(iusacellRequestTO);
		return pagoServiciosIusacellResponseTO;
	}
	
	public PagoServiciosIusacellResponseTO setConfirmIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO)throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosIusacellResponseTO pagoServiciosIusacellResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosIusacellResponseTO = pagoServiciosSL.setConfirmIusacellPayment(iusacellRequestTO);
		return pagoServiciosIusacellResponseTO;
	}
		
	public PagoServicioLuzResponsetTO setInitialLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setInitialLuzPayment(pagoServicioLuzRequestTO);
	}
	
	public PagoServicioLuzResponsetTO setDataLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setDataLuzPayment(pagoServicioLuzRequestTO);
	}
	
	public PagoServicioLuzResponsetTO setConfirmLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setConfirmLuzPayment(pagoServicioLuzRequestTO);
	}
	
	public void setEndLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosSL.setEndLuzPayment(pagoServicioLuzRequestTO);
	}

	public PagoServiciosSkyResponseTO setInitialSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setInitialSkyPayment(skyRequestTO);
	}
	
	public PagoServiciosSkyResponseTO setDataSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setDataSkyPayment(skyRequestTO);
	}
	
	public PagoServiciosSkyResponseTO setConfirmSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setConfirmSkyPayment(skyRequestTO);
	}
	
	public PagoServiciosMovistarResponseTO setInitialMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServicioSL = getPagoServicioSL();
		return pagoServicioSL.setInitialMovistarPayment(movistarRequestTO);
	}
	
	public PagoServiciosMovistarResponseTO setDataMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setDataMovistarPayment(movistarRequestTO);
	}
	
	public PagoServiciosMovistarResponseTO setConfirmMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setConfirmMovistarPayment(movistarRequestTO);
	}
	
	public PagoServiciosTiempoAireResponseTO setMenuTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = null;
		PagoServiciosSL serviciosSL = getPagoServicioSL();
		responseTO = serviciosSL.setMenuTiempoAirePayment(tiempoAireRequestTO);
		return responseTO;
	}
	
	public PagoServiciosTiempoAireResponseTO setInitialTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = null;
		PagoServiciosSL serviciosSL = getPagoServicioSL();
		responseTO = serviciosSL.setInitialTiempoAirePayment(tiempoAireRequestTO);
		return responseTO;
	}
	
	public PagoServiciosTiempoAireResponseTO setDataTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = null;
		PagoServiciosSL serviciosSL = getPagoServicioSL();
		responseTO = serviciosSL.setDataTiempoAirePayment(tiempoAireRequestTO);
		return responseTO;
	}
	
	public PagoServiciosTiempoAireResponseTO setConfirmTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosTiempoAireResponseTO responseTO = null;
		PagoServiciosSL serviciosSL = getPagoServicioSL();
		responseTO = serviciosSL.setConfirmTiempoAirePayment(tiempoAireRequestTO);
		return responseTO;
	}


	public FrecuentesResponseTO setInitialFrecuentesParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setInitialFrecuentesParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setAgregarFrecuenteParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setAgregarFrecuenteParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setAgregarFrecuenteEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setAgregarFrecuenteEjecucionParametrizado(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public CuentaSocioPlusResponseTO setPartnerPlusStartOpenAccount(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		cuentaSocioPlusResponseTO = cuentasSL.setPartnerPlusStartOpenAccount(cuentaSocioPlusRequestTO);
		return cuentaSocioPlusResponseTO;
	}
	
	public CuentaSocioPlusResponseTO validaCuentaSocioPlus(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO)throws  CuentasException, SessionExpiredException, EliteDataException{
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		cuentaSocioPlusResponseTO = cuentasSL.validaCuentaSocioPlus(cuentaSocioPlusRequestTO);
		return cuentaSocioPlusResponseTO;
	}
	
	public CuentaSocioPlusResponseTO setPartnerPlusStartAceptConditions(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		cuentaSocioPlusResponseTO = cuentasSL.setPartnerPlusStartAceptConditions(cuentaSocioPlusRequestTO);
		return cuentaSocioPlusResponseTO;
	}
	
	public CuentaSocioPlusResponseTO setPartnerPlusData(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		cuentaSocioPlusResponseTO = cuentasSL.setPartnerPlusData(cuentaSocioPlusRequestTO);
		return cuentaSocioPlusResponseTO;
	}
	
	public CuentaSocioPlusResponseTO setPartnerPlusConfirmData(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		cuentaSocioPlusResponseTO = cuentasSL.setPartnerPlusConfirmData(cuentaSocioPlusRequestTO);
		return cuentaSocioPlusResponseTO;
	}
	
	public CuentaSocioPlusResponseTO getSocioPlusPDF(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException {
		CuentaSocioPlusResponseTO cuentaSocioPlusResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		cuentaSocioPlusResponseTO = cuentasSL.getSocioPlusPDF(cuentaSocioPlusRequestTO);
		return cuentaSocioPlusResponseTO;
	}

	
	private PagoServiciosSL getPagoServicioSL() throws EJBException, PagoServiciosException {
		PagoServiciosSL pagoServiciosSL = null;
		try {
			PagoServiciosSLHome pagoServiciosSLHome = (PagoServiciosSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.PAGO_SERVICIOS);
			pagoServiciosSL = pagoServiciosSLHome.create();
		} catch (ServiceLocatorException slex) {
			slex.printStackTrace();
			throw new PagoServiciosException(slex.getMessage());
		} catch (EJBException rex) {
			rex.printStackTrace();
			throw new PagoServiciosException(rex.getMessage());
		} catch (CreateException cex) {
			cex.printStackTrace();
			throw new PagoServiciosException(cex.getMessage());
		}
		return pagoServiciosSL;
	}

	public PagoTarjetaCreditoResponseTO setInitialTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoTarjetaCreditoResponseTO = pagoServiciosSL.setInitialTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
		return pagoTarjetaCreditoResponseTO;
	}
	
	public PagoTarjetaCreditoResponseTO setDataTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoTarjetaCreditoResponseTO = pagoServiciosSL.setDataTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
		return pagoTarjetaCreditoResponseTO;
	}
	
	public PagoTarjetaCreditoResponseTO setConfirmTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoTarjetaCreditoResponseTO pagoTarjetaCreditoResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoTarjetaCreditoResponseTO = pagoServiciosSL.setConfirmTarjetaCreditoPayment(pagoTarjetaCreditoRequestTO);
		return pagoTarjetaCreditoResponseTO;
	}
	
	public PagoTarjetaOtrosBancosResponseTO getInitialTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = pagoServiciosSL.getInitialTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
		return tarjetaOtrosBancosResponseTO;
	}
	
	public PagoTarjetaOtrosBancosResponseTO getDataTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = pagoServiciosSL.getDataTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
		return tarjetaOtrosBancosResponseTO;
	}
	
	public PagoTarjetaOtrosBancosResponseTO getConfirmTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		PagoTarjetaOtrosBancosResponseTO tarjetaOtrosBancosResponseTO = pagoServiciosSL.getConfirmTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
		return tarjetaOtrosBancosResponseTO;
	}
	
	public String setSendMailTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		String mensaje = pagoServiciosSL.setSendMailTarjetaPaymentOthersBank(tarjetaOtrosBancosRequestTO);
		return mensaje;
	}

	public ConsultaTransferenciasResponseTO findInitialHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws SessionExpiredException, TransferenciasException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.findInitialHistoricoTransferencias(consultaTransferenciasRequestTO);
	}
	
	public ConsultaTransferenciasResponseTO findDataHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws SessionExpiredException, TransferenciasException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.findDataHistoricoTransferencias(consultaTransferenciasRequestTO);
	}
	
	public ConsultaTransferenciasResponseTO findInitialHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws SessionExpiredException, TransferenciasException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.findInitialHistoricoTraspasos(consultaTransferenciasRequestTO);
	}
	
	public ConsultaTransferenciasResponseTO findDataHistoricoTraspasos(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws SessionExpiredException, TransferenciasException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.findDataHistoricoTraspasos(consultaTransferenciasRequestTO);
	}
	
	public ConsultaTransferenciasResponseTO findInitialTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws SessionExpiredException, TransferenciasException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.findInitialTransferenciasProgramadas(consultaTransferenciasRequestTO);
	}
	
	public ConsultaTransferenciasResponseTO findDataTransferenciasProgramadas(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws SessionExpiredException, TransferenciasException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.findDataTransferenciasProgramadas(consultaTransferenciasRequestTO);
	}

	public AperturaCuentaSocioResponseTO getCuentaSocioInvocacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaSocioInvocacion(cuentaSocioRequestTO);
	}
	public AperturaCuentaSocioResponseTO getCuentaSocioEnvioDatos(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaSocioEnvioDatos(cuentaSocioRequestTO);
	}
	public AperturaCuentaSocioResponseTO getCuentaSocioConfirmacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaSocioConfirmacion(cuentaSocioRequestTO);
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataInvocacion(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaPlataInvocacion(cuentaPlataRequestTO);
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataContrato(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaPlataContrato(cuentaPlataRequestTO);
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataAceptarContrato(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaPlataAceptarContrato(cuentaPlataRequestTO);
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataEnvioDatos(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaPlataEnvioDatos(cuentaPlataRequestTO);
	}
	
	public AperturaCuentaPlataResponseTO getCuentaPlataEjecutar(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getCuentaPlataEjecutar(cuentaPlataRequestTO);
	}
	
	public ConsultaTransferenciasResponseTO findDetalleHistoricoTransferencias(ConsultaTransferenciasRequestTO consultaTransferenciasRequestTO)throws SessionExpiredException, TransferenciasException, EliteDataException{
		TransferenciasSL transferenciasSL = getTransferenciasSession();
		return transferenciasSL.findDetalleHistoricoTransferencias(consultaTransferenciasRequestTO);
	}
	
	public FrecuentesResponseTO setConsultaCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setConsultaCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setAgregaCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setAgregaCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
		return frecuentesResponseTO;
	}	
	public FrecuentesResponseTO iniciarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.iniciarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	public FrecuentesResponseTO ejecutarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.ejecutarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	public FrecuentesResponseTO setConsultaCuentasFrecuentesTiempoAire(FrecuentesRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.setConsultaCuentasFrecuentesTiempoAire(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	//nuevo referencias frecuentes tiempo aire
	public FrecuentesTiempoAireResponseTO referenciasFrecuentesTiempoAireInit(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesTiempoAireResponseTO = pagoServiciosSL.referenciasFrecuentesTiempoAireInit(frecuentesTiempoAireRequestTO);
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setAgregaReferenciasFrecuentesTiempoAire(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesTiempoAireResponseTO = pagoServiciosSL.setAgregaReferenciasFrecuentesTiempoAire(frecuentesTiempoAireRequestTO);
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setBorraReferenciasFrecuentesTiempoAireValidacion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesTiempoAireResponseTO = pagoServiciosSL.setBorraReferenciasFrecuentesTiempoAireValidacion(frecuentesTiempoAireRequestTO);
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setBorraReferenciasFrecuentesTiempoAireEjecucion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesTiempoAireResponseTO = pagoServiciosSL.setBorraReferenciasFrecuentesTiempoAireEjecucion(frecuentesTiempoAireRequestTO);
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setModificaReferenciasFrecuentesTiempoAireValidacion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesTiempoAireResponseTO = pagoServiciosSL.setModificaReferenciasFrecuentesTiempoAireValidacion(frecuentesTiempoAireRequestTO);
		return frecuentesTiempoAireResponseTO;
	}
	
	public FrecuentesTiempoAireResponseTO setModificaReferenciasFrecuentesTiempoAireEjecucion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesTiempoAireResponseTO frecuentesTiempoAireResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesTiempoAireResponseTO = pagoServiciosSL.setModificaReferenciasFrecuentesTiempoAireEjecucion(frecuentesTiempoAireRequestTO);
		return frecuentesTiempoAireResponseTO;
	}
	//nuevo referencias frecuentes tiempo aire
	
	public ERecibosNominaResponseTO liberaERecibosNominaInit(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.liberaERecibosNominaInit(eRecibosNominaRequestTO);
	}
	
	public ERecibosNominaResponseTO liberaERecibosNominaDetalle(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.liberaERecibosNominaDetalle(eRecibosNominaRequestTO);
	}
	
	public ERecibosNominaResponseTO liberaERecibosNominaEjecutar(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws CuentasException, SessionExpiredException, EliteDataException {
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.liberaERecibosNominaEjecutar(eRecibosNominaRequestTO);
	}
	
	public CreditosResponseTO detailOtherCredits(CreditosRequestTO creditosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.detailOtherCredits(creditosRequestTO);
	}
	
	public PagoServiciosMaxiGasResponseTO setInitialMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosMaxiGasResponseTO = pagoServiciosSL.setInitialMaxiGasPayment(pagoServiciosMaxiGasRequestTO);		
		return pagoServiciosMaxiGasResponseTO;
	}
	
	public PagoServiciosMaxiGasResponseTO setDataMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosMaxiGasResponseTO = pagoServiciosSL.setDataMaxiGasPayment(pagoServiciosMaxiGasRequestTO);		
		return pagoServiciosMaxiGasResponseTO;
	}
	public PagoServiciosMaxiGasResponseTO setConfimMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosMaxiGasResponseTO pagoServiciosMaxiGasResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosMaxiGasResponseTO = pagoServiciosSL.setConfimMaxiGasPayment(pagoServiciosMaxiGasRequestTO);		
		return pagoServiciosMaxiGasResponseTO;
	}
		
	public PagoServicioAztecaWebResponseTO setInitialAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioAztecaWebResponseTO pagoServicioAztecaWebResponseTO = null;
		PagoServiciosSL pagoServiciosSL= getPagoServicioSL();
		pagoServicioAztecaWebResponseTO=pagoServiciosSL.setInitialAztecaWebPayment(pagoServicioAztecaWebRequestTO);
		return pagoServicioAztecaWebResponseTO;
	}
	
	public PagoServicioAztecaWebResponseTO setDataAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioAztecaWebResponseTO pagoServicioAztecaWebResponseTO = null;
		PagoServiciosSL pagoServiciosSL= getPagoServicioSL();
		pagoServicioAztecaWebResponseTO=pagoServiciosSL.setDataAztecaWebPayment(pagoServicioAztecaWebRequestTO);
		return pagoServicioAztecaWebResponseTO;
	}
	
	public PagoServicioAztecaWebResponseTO setConfimAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException {
		PagoServicioAztecaWebResponseTO pagoServicioAztecaWebResponseTO = null;
		PagoServiciosSL pagoServiciosSL= getPagoServicioSL();
		pagoServicioAztecaWebResponseTO=pagoServiciosSL.setConfimAztecaWebPayment(pagoServicioAztecaWebRequestTO);
		return pagoServicioAztecaWebResponseTO;
	}				
	
	public CambioNipResponseTO getMediosPagoInvocation(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getMediosPagoInvocation(cambioNipRequestTO);
	}
	public DisposicionEfectivoCajeroResponseTO getEjecucionDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getEjecucionDisposicionEfectivoCajero(cajeroRequestTO);
	}
	public DisposicionEfectivoCajeroResponseTO getSolicitudDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getSolicitudDisposicionEfectivoCajero(cajeroRequestTO);
	}
	public DisposicionEfectivoCajeroResponseTO getValidacionDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getValidacionDisposicionEfectivoCajero(cajeroRequestTO);
	}
	
	public CambioNipResponseTO getNipChangeInvocation(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getNipChangeInvocation(cambioNipRequestTO);
	}
	
	public CambioNipResponseTO setNipChangeExecution(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.setNipChangeExecution(cambioNipRequestTO);
	}
	
	public CambioNipResponseTO setNipChangeConfirmation(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.setNipChangeConfirmation(cambioNipRequestTO);
	}
	public LockUnlockCardsResponseTO informacionBloquearDesbloquear(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.informacionBloquearDesbloquear(lockUnlockCardsRequestTO);
	}

	public LockUnlockCardsResponseTO aceptarCardBlocking(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.aceptarCardBlocking(lockUnlockCardsRequestTO);
	}
	
	public LoginResponseTO getOnDemandDetailAccounts(LoginRequestTO loginRequestTO)throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException {
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getOnDemandDetailAccounts(loginRequestTO);	
	}
	
	public EstadoCuentaResponseTO estadoCuentaGetCuentas(EstadoCuentaRequestTO estadoCuentaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.estadoCuentaGetCuentas(estadoCuentaRequestTO);
	}
	
	public EstadoCuentaResponseTO estadoCuentaSeleccionaCuenta(EstadoCuentaRequestTO estadoCuentaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.estadoCuentaSeleccionaCuenta(estadoCuentaRequestTO);
	}
	
	public EstadoCuentaResponseTO estadoCuentaEjecutar(EstadoCuentaRequestTO estadoCuentaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.estadoCuentaEjecutar(estadoCuentaRequestTO);
	}

	public PagoServiciosAvicolaResponseTO setInitialAvicolaPayment(PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosAvicolaResponseTO pagoServiciosAvicolaResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosAvicolaResponseTO = pagoServiciosSL.setInitialAvicolaPayment(pagoServiciosAvicolaRequestTO);		
		return pagoServiciosAvicolaResponseTO;
	}
	
	public PagoServiciosAvicolaResponseTO setDataAvicolaPayment(PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosAvicolaResponseTO pagoServiciosAvicolaResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosAvicolaResponseTO = pagoServiciosSL.setDataAvicolaPayment(pagoServiciosAvicolaRequestTO);		
		return pagoServiciosAvicolaResponseTO;
	}
 
	public PagoServiciosAvicolaResponseTO setConfirmAvicolaPayment(PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		PagoServiciosAvicolaResponseTO pagoServiciosAvicolaResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoServiciosAvicolaResponseTO = pagoServiciosSL.setConfirmAvicolaPayment(pagoServiciosAvicolaRequestTO);		
		return pagoServiciosAvicolaResponseTO;
	}
	public MisFinanzasResponseTO invokeMisFinanzas(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{		
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.invokeMisFinanzas(balanceRequestTO);
	}
	
	public MisFinanzasResponseTO invokeMisFinanzasGrafica(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException{		
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.invokeMisFinanzasGrafica(balanceRequestTO);
	}
	
	public MisFinanzasResponseTO consultarMisFinanzas(BalanceRequestTO balanceRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.consultarMisFinanzas(balanceRequestTO);
	}
	public DonativosResponseTO setInitialDonativo(DonativosRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		DonativosResponseTO donativosResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		donativosResponseTO = pagoServiciosSL.setInitialDonativo(donativosRequestTO);		
		return donativosResponseTO;
	}
	
	public DonativosResponseTO setDataDonativo(DonativosRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		DonativosResponseTO donativosResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		donativosResponseTO = pagoServiciosSL.setDataDonativo(donativosRequestTO);		
		return donativosResponseTO;
	}
 
	public DonativosResponseTO setConfirmDonativo(DonativosRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		DonativosResponseTO donativosResponseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		donativosResponseTO = pagoServiciosSL.setConfirmDonativo(donativosRequestTO);		
		return donativosResponseTO;
	}
	
	public LlaveSeguridadResponseTO obtenerLlaveSeguridad(LlaveSeguridadRequestTO llaveSeguridadRequestTO) throws EJBException, UsuarioException, EliteDataException{
		
		UsuarioSL user = getUsuarioSession();
		return user.getLlaveSeguridad(llaveSeguridadRequestTO);
	}
	
	public Boolean validaHuella(HuellaDigitalTO huellaDigitalTO) throws EJBException, UsuarioException{	
		UsuarioSL user = getUsuarioSession();
		return user.validaHuella(huellaDigitalTO);
	}

	public boolean validaToken(TokenTO tokenTO) throws SessionExpiredException, EliteDataException, UsuarioException{
		UsuarioSL user = getUsuarioSession();
		return user.validaToken(tokenTO);
	}
	
	public ActivarCuentaExpressResponseTO activacionExpressInicio(ActivarCuentaExpressRequestTO activarCuentaTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.activacionExpressInicio(activarCuentaTO);
	}
	
	public ActivarCuentaExpressResponseTO activacionExpressValidacion(ActivarCuentaExpressRequestTO activarCuentaTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.activacionExpressValidacion(activarCuentaTO);
	}

	public BibliotecaRecibosTO getServiciosBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL userEJB=getUsuarioSession();
		return userEJB.getServiciosBibliotecaRecibos(user);
	}
	
	public BibliotecaRecibosTO getBibliotecaRecibos(BibliotecaRecibosRequestTO bibliotecaRecibosRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL user=getUsuarioSession();		
		return user.getBibliotecaRecibos(bibliotecaRecibosRequestTO);
	}
	
	public BibliotecaRecibosTO getDatosPDFBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL userEJB=getUsuarioSession();
		return userEJB.getDatosPDFBibliotecaRecibos(user);
	}

	
	public void setParameterOnEBankSession(ParameterOnSessionTO onSessionTO) throws SessionExpiredException, URISyntaxException, HttpException, IOException, EJBException, UsuarioException{
		UsuarioSL user=getUsuarioSession();
		user.setParameterOnEBankSession(onSessionTO);
	}
	
	public BeneficiarioDineroExpressResponseTO setDataEnvioDineroExpressAltaFrecuente(BeneficiarioDineroExpressRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		BeneficiarioDineroExpressResponseTO responseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		responseTO = pagoServiciosSL.setDataEnvioDineroExpressAltaFrecuente( donativosRequestTO );
		return responseTO;
	}
		
	public BeneficiarioDineroExpressResponseTO setEnvioDineroExpressAltaFrecuenteEjecutar(BeneficiarioDineroExpressRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		BeneficiarioDineroExpressResponseTO responseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		responseTO = pagoServiciosSL.setEnvioDineroExpressAltaFrecuenteEjecutar( donativosRequestTO );
		return responseTO;
	}

//	public BeneficiarioDineroExpressResponseTO setEnvioDineroExpressBuscaFrecuenteEjecutar(BeneficiarioDineroExpressRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
//		BeneficiarioDineroExpressResponseTO responseTO = null;		
//		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
//		responseTO = pagoServiciosSL.setDataEnvioDineroExpressBuscaFrecuente( donativosRequestTO );
//		return responseTO;
//	}

	public BeneficiarioDineroExpressResponseTO setEnvioDineroExpressConsultarFrecuentesEjecutar(BeneficiarioDineroExpressRequestTO donativosRequestTO) throws PagoServiciosException, SessionExpiredException,EliteDataException{
		BeneficiarioDineroExpressResponseTO responseTO = null;		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		responseTO = pagoServiciosSL.setEnvioDineroExpressConsultarFrecuentesEjecutar( donativosRequestTO );
		return responseTO;
	}
	
		public MovimientosCuentasInversionTO getMovimientosCuentasInversion(MovimientosCuentaInversionRequest movimientosCuentaInversionRequest) throws EJBException, CuentasException, SessionExpiredException{
		CuentasSL cuentasSL = getCuentasSession();
		MovimientosCuentasInversionTO response = cuentasSL.getMovimientosCuentasInversion(movimientosCuentaInversionRequest);
		return response;
	}
	
	public ChequeraPreaperturaResponseTO getChequeraSolicitud(ChequeraPreaperturaRequestTO chequeraRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException{		
		CuentasSL cuentasSL = getCuentasSession();
		return cuentasSL.getChequeraSolicitud(chequeraRequestTO);
	}
	
	public EnvioDineroExpressResponseTO envioDineroExpressInicio(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws EJBException, PagoServiciosException, SessionExpiredException, EliteDataException{		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setEnvioDineroExpressInicio(dineroExpressRequestTO);
	}
	
	public EnvioDineroExpressResponseTO envioDineroExpressValidacion(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws EJBException, PagoServiciosException, SessionExpiredException, EliteDataException{		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setEnvioDineroExpressValidacion(dineroExpressRequestTO);
	}
	
	public EnvioDineroExpressResponseTO envioDineroExpressEjecucion(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws EJBException, PagoServiciosException, SessionExpiredException, EliteDataException{		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setEnvioDineroExpressEjecucion(dineroExpressRequestTO);
	}
	

	
	public EnvioDineroExpressResponseTO envioDineroExpressEstados(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws EJBException, PagoServiciosException, SessionExpiredException, EliteDataException{		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setEnvioDineroExpressEstados(dineroExpressRequestTO);
	}
	
	public EnvioDineroExpressResponseTO envioDineroExpressCiudades(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws EJBException, PagoServiciosException, SessionExpiredException, EliteDataException{		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setEnvioDineroExpressCiudades(dineroExpressRequestTO);
	}
	
	public EnvioDineroExpressResponseTO envioDineroExpressAgentes(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setEnvioDineroExpressAgentes(dineroExpressRequestTO);
	}
	
	public EnvioDineroExpressResponseTO envioDineroExpressSucursal(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL(); 
		return pagoServiciosSL.setEnvioDineroExpressSucursales(dineroExpressRequestTO);
	}
	
	public EnvioDineroExpressResponseTO enviaCorreoConfirmacionDineroExpress(EnvioDineroExpressRequestTO requestTO)throws SessionExpiredException,EliteDataException,PagoServiciosException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL(); 
		return pagoServiciosSL.enviaCorreoConfirmacionDineroExpress(requestTO);
	}
	
	public EnvioDineroExpressResponseTO envioDineroExpressCalculoComision(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws EJBException, PagoServiciosException, SessionExpiredException, EliteDataException{		
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setEnvioDineroExpressCalculoComision(dineroExpressRequestTO);
	}
	
	public PagoServiciosParametrizableResponseTO setInitialParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServicioSL = getPagoServicioSL();
		return pagoServicioSL.setInitialParametrizablePayment(parametrizableRequestTO);
	}
	
	public PagoServiciosParametrizableResponseTO setDataParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setDataParametrizablePayment(parametrizableRequestTO);
	}
	
	public PagoServiciosParametrizableResponseTO setConfirmParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		return pagoServiciosSL.setConfirmParametrizablePayment(parametrizableRequestTO);

	}
	
	public Conciliacion_ResponseTO conciliacionOperacionesMercadoDinero(ConciliacionRequestTO conciliacionRequestTO) throws EJBException,InversionesGenericException, SessionExpiredException, EliteDataException{
		Conciliacion_ResponseTO conciliacionResponseTO = new Conciliacion_ResponseTO();
		InversionesSL inversionesSL = getInversionesSL();
		conciliacionResponseTO = inversionesSL.conciliacionOperacionesMercadoDinero(conciliacionRequestTO);
		return conciliacionResponseTO;
	}

	public ReportosEstadoCuentaResponseTO estadoCuentaMercadoDineroEjecucion(ReportosEstadoCuentaRequestTO requestTO) throws EJBException,InversionesGenericException, SessionExpiredException, EliteDataException {
		InversionesSL inversionesSL = getInversionesSL();
		ReportosEstadoCuentaResponseTO responseTO = inversionesSL.estadoCuentaMercadoDineroEjecucion(requestTO);
		return responseTO;
	}
	
	public RastreoPedidoResponseTO rastreoPedido(RastreoPedidoRequestTO rastreoPedidoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.rastreoPedido(rastreoPedidoRequestTO);
	}
	
	public BloqueoFirmaResponseTO solicitaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.solicitaBloqueoToken(bloqueoFirmaRequestTO);
	}

	public BloqueoFirmaResponseTO validaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.validaBloqueoToken(bloqueoFirmaRequestTO);
	}
	
	public BloqueoFirmaResponseTO ejecutaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.ejecutaBloqueoToken(bloqueoFirmaRequestTO);
	}
	
	public ActivacionFirmaResponseTO solicitudActivacionFirma(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.solicitaActivacionToken(activacionFirmaRequestTO);
	}
	
	public ActivacionFirmaResponseTO validacionActivacionFirma(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.validaActivacionToken(activacionFirmaRequestTO);
	}
	
	public ActivacionFirmaResponseTO ejecucionActivacionFirma(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.ejecutaActivacionToken(activacionFirmaRequestTO);
	}
	public SolicitudDispositivoResponseTO setInitialSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.setInitialSolicitudDispositivo(solicitudDispositivoRequestTO);
	}
	
	public SolicitudDispositivoResponseTO setCuentaSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.setCuentaSolicitudDispositivo( solicitudDispositivoRequestTO);
	}
	
	public SolicitudDispositivoResponseTO setDataSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.setDataSolicitudDispositivo( solicitudDispositivoRequestTO);
	}
	
	public SolicitudDispositivoResponseTO setConfirmSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.setConfirmSolicitudDispositivo( solicitudDispositivoRequestTO);
	}	
	
	public SincronizacionFirmaResponseTO solicitaSincronizaFirma(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.solicitaSincronizacionToken(sincronizacionFirmaRequestTO);
	}
	
	public SincronizacionFirmaResponseTO validaSincronizaFirma(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.validaSincronizacionToken(sincronizacionFirmaRequestTO);
	}
	
	public SincronizacionFirmaResponseTO ejecutaSincronizaFirma(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException {
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.ejecutaSincronizacionToken(sincronizacionFirmaRequestTO);
	}
	
	public SolicitudDispositivoResponseTO getCatalogoEstadosDispositivos(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException{
		DispositivoSL dispositivoSL = getDispositivoSession();
		return dispositivoSL.getCatalogoEstadosDispositivos(solicitudDispositivoRequestTO);
	}
	
	public void insertaUsuarios(UsuariosTO usuarios) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		usuarioSL.insertaUsuarios(usuarios);
	}
	
	public void insertaUsuarioOperacion(UsuarioOperacionesTO usuarioOperacion) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		usuarioSL.insertaUsuarioOperacion(usuarioOperacion);
	}
	
	public void insertXml(DetalleMonitoreoTO detalle) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		usuarioSL.insertXml(detalle);
		
	}
	
	public void insertError(UsuarioOperacionesTO usuarioOperacion,DetalleMonitoreoTO detalle) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		usuarioSL.insertError(usuarioOperacion, detalle);
		
	}
	
	
	public void insertMapa(Integer idMapa,Integer idTracking,String campo,String valor) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		usuarioSL.insertMapa(idMapa,idTracking,campo,valor);
	}
	
	public Integer getIdUsuario(String userName, String aplicacion) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		Integer idUsuario=usuarioSL.getIdUsuario(userName,aplicacion);
		return idUsuario;
	}
	
	public Integer getIdTracking(String idUsuarioOperacion) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		Integer idTracking=usuarioSL.getIdTracking(idUsuarioOperacion);
		return idTracking;
	}
	
	
	
	public Integer getIdUsuarioOperacion(String userName, String aplicacion) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		Integer idUsuarioOperacion=usuarioSL.getIdUsuarioOperacion(userName, aplicacion);
		return idUsuarioOperacion;
	}
	
	public MonitoreoResponseTO getTotalUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getTotalUsuariosAplicacion(requestTO);
	}
	
	public MonitoreoResponseTO getTotalOperacionesByUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getTotalOperacionesByUsuarios(requestTO);
	}
	
	public MonitoreoResponseTO getUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getUsuariosAplicacion(requestTO);
	}
	
	public MonitoreoResponseTO getTrackingUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getTrackingUsuariosAplicacion(requestTO);
	}
	
	public MonitoreoResponseTO getOperacionMonto(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getOperacionMonto(requestTO);
	}
	
	public MonitoreoResponseTO getAllTotalUsuariosAplicacion()throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getAllTotalUsuariosAplicacion();
	}
	
	public MonitoreoResponseTO getAllUsuariosAplicacion()throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getAllUsuariosAplicacion();
	}
	
	public MonitoreoResponseTO getAllTotalOperaByUser()throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getAllTotalOperaByUser();
	}
	
	public MonitoreoResponseTO getAllTrackingUserAplica()throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getAllTrackingUserAplica();
	}
	
	// terminan nuevos
	
		
	public MonitoreoResponseTO getTotalUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getTotalUsuariosPorAplicacion(requestTO);
	}
	
	public MonitoreoResponseTO getUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getUsuariosPorAplicacion(requestTO);
	}
	
	public MonitoreoResponseTO getTrackingUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getTrackingUsuariosPorAplicacion(requestTO);
	}

	public MonitoreoResponseTO getTotalOperacionesUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getTotalOperacionesUsuarios(requestTO);
	}
		
	public MonitoreoResponseTO getTotalConexionesUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getTotalConexionesUsuarios(requestTO);
	}
	
	public void insertaUsuariosAdministrador(MonitoreoAdministradorRequestTO administradorRequestTO)throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		usuarioSL.insertaUsuariosAdministrador(administradorRequestTO);
	}
	
	public MonitoreoAdministradorResponseTO validarUsuarioAdministrador(MonitoreoAdministradorRequestTO administradorRequestTO) throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.validarUsuarioAdministrador(administradorRequestTO);
	}

//	TODO LOGICA DE INSERCION HACIA MONITOREO PAUL

	public MonitoreoResponseTO getDatosMonitoreoPorUsuario(ConsultaMonitoreoRequestTO monitoreoRequestTO)throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getDatosMonitoreoPorUsuario(monitoreoRequestTO);
	}

	public MonitoreoResponseTO getDatosMonitoreoPorAplicacion(ConsultaMonitoreoRequestTO monitoreoRequestTO)throws EJBException, UsuarioException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getDatosMonitoreoPorAplicacion(monitoreoRequestTO);		
	}
	
	public ActualizaDatosResponseTO getInitialActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getInitialActualizaDatos(datosUsuarioRequestTO);
	}
	
	public ActualizaDatosResponseTO getActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getActualizaDatos(datosUsuarioRequestTO);
	}
	
	public ActualizaDatosResponseTO getEjecutaActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getEjecutaActualizaDatos(datosUsuarioRequestTO);
	}
	
	public ChangePasswordResponseTO getInitialCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getInitialCambiarContrasena(cambiarContrasenaRequestTO);
	}
	
	public ChangePasswordResponseTO getEjecutaCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException{
		UsuarioSL usuarioSL = getUsuarioSession();
		return usuarioSL.getEjecutaCambiarContrasena(cambiarContrasenaRequestTO);
	}
	
	public PagoServiciosToditoCardResponseTO getInitialToditoCard(PagoServiciosToditoCardRequestTO toditoCardRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{

		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		PagoServiciosToditoCardResponseTO pagoServiciosToditoCardResponseTO = pagoServiciosSL.getInitialToditoCard(toditoCardRequestTO);		
		return pagoServiciosToditoCardResponseTO;
	}

	//Metodos de Pago de Tarjeta Azteca
	

	public PagoTarjetaAztecaResponseTO solicitudPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoTarjetaAztecaResponseTO = pagoServiciosSL.solicitudPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
		return pagoTarjetaAztecaResponseTO;
	}
	
	public PagoTarjetaAztecaResponseTO validacionPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoTarjetaAztecaResponseTO = pagoServiciosSL.validacionPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
		return pagoTarjetaAztecaResponseTO;
	}
	
	public PagoTarjetaAztecaResponseTO confirmacionPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoTarjetaAztecaResponseTO = pagoServiciosSL.confirmacionPagoTarjetaAzteca(pagoTarjetaAztecaRequestTO);
		return pagoTarjetaAztecaResponseTO;
	}
	
	public FrecuentesAztecaResponseTO consultaCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.consultaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	//Tarjetas Frecuentes para Pago de Tarjeta Azteca
	
	
	public FrecuentesAztecaResponseTO AgregaCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.AgregaCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
		return frecuentesResponseTO;
	}	
	public FrecuentesAztecaResponseTO iniciarEditarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.iniciarEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	public FrecuentesAztecaResponseTO ejecutarEditarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.ejecutarEditarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	
	public PagoTarjetaAztecaResponseTO envioMail(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		PagoTarjetaAztecaResponseTO pagoTarjetaAztecaResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		pagoTarjetaAztecaResponseTO = pagoServiciosSL.envioMail(pagoTarjetaAztecaRequestTO);
		return pagoTarjetaAztecaResponseTO;
	}
	
	
	//Validacion de alta y edicion de tarjetas frecuentes
	
	public FrecuentesAztecaResponseTO validarAltaCuentasFrecuentes(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.validarAltaCuentasFrecuentes(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	
	public FrecuentesAztecaResponseTO validarEdicionCuentasFrecuentes(FrecuentesAztecaRequestTO frecuentesAztecaRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesAztecaResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesAztecaResponseTO = pagoServiciosSL.validarEdicionCuentasFrecuentes(frecuentesAztecaRequestTO);
		return frecuentesAztecaResponseTO;
	}
	
	
	
	
	
	//Eliminacion de tarjetas frecuentes
	
	

	
	public FrecuentesAztecaResponseTO iniciarEliminarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.iniciarEliminarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	public FrecuentesAztecaResponseTO ejecutarEliminarCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws PagoServiciosException, SessionExpiredException, EliteDataException{
		FrecuentesAztecaResponseTO frecuentesResponseTO = null;
		PagoServiciosSL pagoServiciosSL = getPagoServicioSL();
		frecuentesResponseTO = pagoServiciosSL.ejecutarEliminarCuentasFrecuentesTarjetasAzteca(frecuentesRequestTO);
		return frecuentesResponseTO;
	}
	
	
	//Retenciones
	
	public RetencionesResponseTO consultarCartaRetenciones(RetencionesRequestTO retencionesRequestTO) throws CuentasException, SessionExpiredException, EliteDataException, EJBException, CuentasException{
		RetencionesResponseTO retencionesResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		retencionesResponseTO = cuentasSL.consultarCartaRetenciones(retencionesRequestTO);
		return retencionesResponseTO;
	}
	
//Movimientos por Fecha para socio Plus
	
	public BalanceResponseTO consultarMovimientosFecha(BalanceRequestTO balanceRequestTO) throws CuentasException, SessionExpiredException, EliteDataException, EJBException, CuentasException{
		BalanceResponseTO balanceResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		balanceResponseTO = cuentasSL.consultarMovimientosFecha(balanceRequestTO);
		return balanceResponseTO;
	}
	
	//Movimientos por Fecha para cuenta de Nomina y Guardadito
	
	public BalanceResponseTO consultarMovimientosFechaOtrasCuentas(BalanceRequestTO balanceRequestTO) throws CuentasException, SessionExpiredException, EliteDataException, EJBException, CuentasException{
		BalanceResponseTO balanceResponseTO = null;
		CuentasSL cuentasSL = getCuentasSession();
		balanceResponseTO = cuentasSL.consultarMovimientosFechaOtrasCuentas(balanceRequestTO);
		return balanceResponseTO;
	}
	
	//localiza tu sucursal
	public LocalizaSucursalResponseTO getObtieneEstados(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		usuarioSession = getUsuarioSession();
		return usuarioSession.getObtieneEstados(localizaSucursalRequestTO);
	}
	
	public LocalizaSucursalResponseTO getObtieneMunicipios(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		usuarioSession = getUsuarioSession();
		return usuarioSession.getObtieneMunicipios(localizaSucursalRequestTO);
	}
	
	public LocalizaSucursalResponseTO getObtieneTienda(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		usuarioSession = getUsuarioSession();
		return usuarioSession.getObtieneTienda(localizaSucursalRequestTO);
	}
	
	public LocalizaSucursalResponseTO getObtieneTiendaCP(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		usuarioSession = getUsuarioSession();
		return usuarioSession.getObtieneTiendaCP(localizaSucursalRequestTO);
	}
	
	public LocalizaSucursalResponseTO getObtieneTiendaPalabra(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		usuarioSession = getUsuarioSession();
		return usuarioSession.getObtieneTiendaPalabra(localizaSucursalRequestTO);
	}
	
	public LocalizaSucursalResponseTO getObtieneTiendas(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException{
		usuarioSession = getUsuarioSession();
		return usuarioSession.getObtieneTiendas(localizaSucursalRequestTO);
	}
	//localiza tu sucursal
	
	//altas usuarios
	public void consultarCuentaActivar(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		serviciosSL.consultarCuentaActivar(requestTO);
	}
	
	public void validarDatosActivar(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		serviciosSL.validarDatosActivar(requestTO);
	}
	
	public void validarContrato(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		serviciosSL.validarContrato(requestTO);
	}
	
	public void consultarUsuarioDisponible(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		serviciosSL.consultarUsuarioDisponible(requestTO);
	}
	public void registrarUsuario(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		serviciosSL.registrarUsuario(requestTO);
	}
	public void activarUsuario(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		serviciosSL.activarUsuario(requestTO);
	}
	
	
	
	
	
	//fin usuarios
	//operaciones frecuentes
	public OperacionesFrecuentesResponseTO setDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL  = getUsuarioSession();
		return usuarioSL.setDatosOperacionesFrecuentes(operacionesFrecuentesRequestTO);
	}
	
	public OperacionesFrecuentesResponseTO getDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL  = getUsuarioSession();
		return usuarioSL.getDatosOperacionesFrecuentes(operacionesFrecuentesRequestTO);
	}
	
	public OperacionesFrecuentesResponseTO eliminaDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException{
		UsuarioSL usuarioSL  = getUsuarioSession();
		return usuarioSL.eliminaDatosOperacionesFrecuentes(operacionesFrecuentesRequestTO);
	}
	//operaciones frecuentes
	
	public TransferenciasSpeiResponseTO insertaSpei30(SpeiProgramadoRequestTO speiRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException, EJBException, TransferenciasException {
		TransferenciasSL transferenciasSL=getTransferenciasSession();
		TransferenciasSpeiResponseTO responseTO=transferenciasSL.insertaSpei30(speiRequestTO);
		return responseTO;
	}
	
	
	public TerminalAlnovaResponseTO obtieneTerminalAlnova(String user) throws EJBException, UsuarioException, SessionExpiredException, DAOException{
		PreferenciasSL preferenciasSL=getPreferenciasSession();
		TerminalAlnovaResponseTO terminalAlnova = preferenciasSL.getTerminalAlnova(user);
		return terminalAlnova;
	}
	
	public void liberaTerminalAlnova(String user,String terminal) throws SessionExpiredException,DAOException, EJBException, UsuarioException{
		PreferenciasSL preferenciasSL=getPreferenciasSession();
		preferenciasSL.liberaTerminalAlnova(user, terminal);
	}
	
	//  web service foto unica
	public FotoUnicaResponseTO consultaFotoUnica(FotoUnicaRequestTO fotoUnicaRequestTO)throws SessionExpiredException, UsuarioException, EliteDataException{
		usuarioSession = getUsuarioSession();
		return usuarioSession.consultaFotoUnica(fotoUnicaRequestTO);
	}
	//	web service foto unica
	
	// consulta express
	
	public ConsultaExpressResponseTO consultarCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		ConsultaExpressResponseTO response= serviciosSL.consultarCuentaExpress(requestTO);
		return response;
	}
	
	public ConsultaExpressResponseTO validarCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		ConsultaExpressResponseTO response= serviciosSL.validarCuentaExpress(requestTO);
		return response;
	}
	public ConsultaExpressResponseTO cerrarCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		ConsultaExpressResponseTO response= serviciosSL.cerrarCuentaExpress(requestTO);
		return response;
	}
	public ConsultaExpressResponseTO getRecibosCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException{
		PreferenciasSL serviciosSL = getPreferenciasSession();
		ConsultaExpressResponseTO response= serviciosSL.getRecibosCuentaExpress(requestTO);
		return response;
	}
	
}