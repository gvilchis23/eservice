package com.bancoazteca.elite.ejb.cuentas;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import com.bancoazteca.elite.beans.AlertsDataRequest;
import com.bancoazteca.elite.beans.AlertsDataResponseTO;
import com.bancoazteca.elite.beans.AperturaCuentaPlataRequestTO;
import com.bancoazteca.elite.beans.AperturaCuentaPlataResponseTO;
import com.bancoazteca.elite.beans.AperturaCuentaSocioRequestTO;
import com.bancoazteca.elite.beans.AperturaCuentaSocioResponseTO;
import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.BalanceResponseTO;
import com.bancoazteca.elite.beans.CambioNipRequestTO;
import com.bancoazteca.elite.beans.CambioNipResponseTO;
import com.bancoazteca.elite.beans.CambioSucursalTO;
import com.bancoazteca.elite.beans.ChangeBranchRequestTO;
import com.bancoazteca.elite.beans.ChangeBranchResponseTO;
import com.bancoazteca.elite.beans.ChequeraPreaperturaRequestTO;
import com.bancoazteca.elite.beans.ChequeraPreaperturaResponseTO;
import com.bancoazteca.elite.beans.CreditosRequestTO;
import com.bancoazteca.elite.beans.CreditosResponseTO;
import com.bancoazteca.elite.beans.CuentaSocioPlusRequestTO;
import com.bancoazteca.elite.beans.CuentaSocioPlusResponseTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesResponseTO;
import com.bancoazteca.elite.beans.DisposicionEfectivoCajeroRequestTO;
import com.bancoazteca.elite.beans.DisposicionEfectivoCajeroResponseTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.ERecibosNominaRequestTO;
import com.bancoazteca.elite.beans.ERecibosNominaResponseTO;
import com.bancoazteca.elite.beans.EstadoCuentaRequestTO;
import com.bancoazteca.elite.beans.EstadoCuentaResponseTO;
import com.bancoazteca.elite.beans.InfiniteMovimientosRequestTO;
import com.bancoazteca.elite.beans.InfiniteMovimientosResponseTO;
import com.bancoazteca.elite.beans.InternetSalesRequestTO;
import com.bancoazteca.elite.beans.InternetSalesResponseTO;
import com.bancoazteca.elite.beans.LockUnlockCardsRequestTO;
import com.bancoazteca.elite.beans.LockUnlockCardsResponseTO;
import com.bancoazteca.elite.beans.MisFinanzasResponseTO;
import com.bancoazteca.elite.beans.MovimientosCuentaInversionRequest;
import com.bancoazteca.elite.beans.MovimientosCuentasInversionTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaCreditoRequestTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaCreditoResponseTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaDebitoRequestTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaDebitoResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.EliteException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.pagoServicios.PagoServiciosException;
import com.bancoazteca.elite.beans.RetencionesRequestTO;
import com.bancoazteca.elite.beans.RetencionesResponseTO;

/**
 * 
 * @author Banco Azteca S.A. [JFAV] Agosto 25, 2008.
 */
public interface CuentasSL extends EJBLocalObject {

	public BalanceResponseTO getMovimientosByIndex(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public BalanceResponseTO getMovimientosByDate(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
		
	public InternetSalesResponseTO getTarjetasComprasInternet(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public InternetSalesResponseTO getComprasInternetEstadoTarjeta(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, EliteDataException, CuentasException, SessionExpiredException;

	public InternetSalesResponseTO setComprasInternetActivacion(InternetSalesRequestTO internetSalesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public LockUnlockCardsResponseTO findAllCardsLockandUnlock(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public LockUnlockCardsResponseTO initBlockingCards(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteException, EliteDataException;

	public LockUnlockCardsResponseTO initUnlockingCards(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteException, EliteDataException;

	public LockUnlockCardsResponseTO confirmCardBlocking(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EliteException, EliteDataException;

	public LockUnlockCardsResponseTO confirmCardUnlock(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws CuentasException, SessionExpiredException, EliteException, EliteDataException;

	public CambioSucursalTO getCambioSucursalInicio(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	public CambioSucursalTO getDatosSucursalTarjeta(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	public CambioSucursalTO getDatosBusquedaSucursalesTarjeta(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	public CambioSucursalTO confirmaCambioSucursalTarjeta(ChangeBranchRequestTO branchRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public ChangeBranchResponseTO getMunicipiosEntidad(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public ChangeBranchResponseTO buscarCentrosCambioSucursal(ChangeBranchRequestTO changeBranchRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public AlertsDataResponseTO alertsLoadInitialData(String user) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public AlertsDataResponseTO setAlertsAcountsSelectedLink(AlertsDataRequest request) throws EJBException, CuentasException, EliteDataException, SessionExpiredException;

	public AlertsDataResponseTO getDataAlertFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public AlertsDataResponseTO getDataAlertSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataAlertThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public AlertsDataResponseTO getAlertsAcountsSelectedLinkUpdate(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public AlertsDataResponseTO getDataForUpdateAlertFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public AlertsDataResponseTO getDataForUpdateAlertFirstStepModificar(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public AlertsDataResponseTO getDataForUpdateAlertFinalStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataForUpdateAlertThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getAlertCardAcountsSelectedLink(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataForActivateCardsFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataForActivateCardsSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataForActivateCardsThirdStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getAlertsCardSelectedLinkUpdate(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataForModifyCardsFirstStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataForModifyCardsSecondStep(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void getDataForDeleteCards(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public AlertsDataResponseTO getDataForDeleteAccounts(AlertsDataRequest request) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public InfiniteMovimientosResponseTO getInfiniteDetalleMovimientos(InfiniteMovimientosRequestTO infiniteMovimientosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public InfiniteMovimientosResponseTO getInfiniteAdicionalesDetalleMovimientos(InfiniteMovimientosRequestTO infiniteMovimientosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public TarjetaCorporativaCreditoResponseTO getTarjetaCorporativaCredito(TarjetaCorporativaCreditoRequestTO corporativaCreditoRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public TarjetaCorporativaDebitoResponseTO getTarjetaCorporativaDebito(TarjetaCorporativaDebitoRequestTO tarjetaCorporativaDebitoRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public CuentasFrecuentesResponseTO findAllFrequentAccounts(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public CuentasFrecuentesResponseTO getCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public CuentasFrecuentesResponseTO getHistoricasCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public CuentasFrecuentesResponseTO setOtrosBancosPreparacionAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public DispositivoHuellaTO setIntenationalesDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void setOtrosBancosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public void setTercerosConfirmarAltaFrecuente(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public DispositivoHuellaTO setTercerosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public DispositivoHuellaTO setOtrosBancosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public CuentasFrecuentesResponseTO getIntenationalesDatosEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public void setIntenationalesEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public CuentasFrecuentesResponseTO getModificaDatosCuentaFrecuentesTraspasos(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public CuentasFrecuentesResponseTO setModificaDatosCuentaFrecuentesTraspasosConf(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public void setGuardaModificaCuentaFrecuentesTraspasos(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public CuentaSocioPlusResponseTO validaCuentaSocioPlus(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException;
	
	public CuentaSocioPlusResponseTO setPartnerPlusStartOpenAccount(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException;
	
	public CuentaSocioPlusResponseTO setPartnerPlusStartAceptConditions(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException;
	
	public CuentaSocioPlusResponseTO setPartnerPlusData(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException;
	
	public CuentaSocioPlusResponseTO setPartnerPlusConfirmData(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException;
	
	public CuentaSocioPlusResponseTO getSocioPlusPDF(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws  CuentasException, SessionExpiredException, EliteDataException;
	
	public AperturaCuentaSocioResponseTO getCuentaSocioInvocacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException ;
	
	public AperturaCuentaSocioResponseTO getCuentaSocioEnvioDatos(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException ;
	
	public AperturaCuentaSocioResponseTO getCuentaSocioConfirmacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException ;
	
	public AperturaCuentaPlataResponseTO getCuentaPlataInvocacion(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public AperturaCuentaPlataResponseTO getCuentaPlataContrato(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public AperturaCuentaPlataResponseTO getCuentaPlataAceptarContrato(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
		
	public AperturaCuentaPlataResponseTO getCuentaPlataEnvioDatos(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public AperturaCuentaPlataResponseTO getCuentaPlataEjecutar(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public BalanceResponseTO getPartnerPlusBalanceAccount(BalanceRequestTO balanceRequestTO)throws  CuentasException, SessionExpiredException, EliteDataException;
	
	public ERecibosNominaResponseTO liberaERecibosNominaInit(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public ERecibosNominaResponseTO liberaERecibosNominaDetalle(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public ERecibosNominaResponseTO liberaERecibosNominaEjecutar(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public CreditosResponseTO detailOtherCredits(CreditosRequestTO creditosRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public LockUnlockCardsResponseTO informacionBloquearDesbloquear(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;

	public LockUnlockCardsResponseTO aceptarCardBlocking(LockUnlockCardsRequestTO lockUnlockCardsRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public CambioNipResponseTO getMediosPagoInvocation(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	public DisposicionEfectivoCajeroResponseTO getEjecucionDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	public DisposicionEfectivoCajeroResponseTO getSolicitudDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	public DisposicionEfectivoCajeroResponseTO getValidacionDisposicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
		
	
	public CambioNipResponseTO getNipChangeInvocation(CambioNipRequestTO cambioNipRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public CambioNipResponseTO setNipChangeExecution(CambioNipRequestTO cambioNipRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public CambioNipResponseTO setNipChangeConfirmation(CambioNipRequestTO cambioNipRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public EstadoCuentaResponseTO estadoCuentaGetCuentas(EstadoCuentaRequestTO estadoCuentaRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public EstadoCuentaResponseTO estadoCuentaSeleccionaCuenta(EstadoCuentaRequestTO estadoCuentaRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public EstadoCuentaResponseTO estadoCuentaEjecutar(EstadoCuentaRequestTO estadoCuentaRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;	

	public MisFinanzasResponseTO invokeMisFinanzas(BalanceRequestTO balanceRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public MisFinanzasResponseTO invokeMisFinanzasGrafica(BalanceRequestTO balanceRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public MisFinanzasResponseTO consultarMisFinanzas(BalanceRequestTO balanceRequestTO) throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	public MovimientosCuentasInversionTO getMovimientosCuentasInversion(MovimientosCuentaInversionRequest movimientosCuentaInversionRequest) throws CuentasException, SessionExpiredException;
	
	public ChequeraPreaperturaResponseTO getChequeraSolicitud(ChequeraPreaperturaRequestTO chequeraRequestTO)throws EJBException, CuentasException, SessionExpiredException, EliteDataException;
	
	//Consulta de Carta de Retenciones
	public RetencionesResponseTO consultarCartaRetenciones(RetencionesRequestTO retencionesRequestTO) throws CuentasException, SessionExpiredException, EliteDataException;

	
	//Consulta de movimientos por fecha

	public BalanceResponseTO consultarMovimientosFecha(BalanceRequestTO balanceRequestTO) throws CuentasException, SessionExpiredException, EliteDataException;

	
	//Consulta de movimientos por fecha para cuentas de Nomina y Guardadito

	public BalanceResponseTO consultarMovimientosFechaOtrasCuentas(BalanceRequestTO balanceRequestTO) throws CuentasException, SessionExpiredException, EliteDataException;

}
