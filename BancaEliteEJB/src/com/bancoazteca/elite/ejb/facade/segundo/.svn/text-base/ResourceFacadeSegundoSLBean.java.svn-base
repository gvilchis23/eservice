package com.bancoazteca.elite.ejb.facade.segundo;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.bancoazteca.elite.beans.ActivacionChequeraRequestTO;
import com.bancoazteca.elite.beans.ActivacionChequeraResponseTO;
import com.bancoazteca.elite.beans.ChequeraRoboExtravioRequestTO;
import com.bancoazteca.elite.beans.ChequeraRoboExtravioResponseTO;
import com.bancoazteca.elite.beans.ConsultaChequesRequestTO;
import com.bancoazteca.elite.beans.ConsultaChequesResponseTO;
import com.bancoazteca.elite.beans.LiberacionChequesRequestTO;
import com.bancoazteca.elite.beans.LiberacionChequesResponseTO;
import com.bancoazteca.elite.beans.LoginConciliacionRequestTO;
import com.bancoazteca.elite.beans.LoginConciliacionResponseTO;
import com.bancoazteca.elite.beans.PresolicitudChequesRequestTO;
import com.bancoazteca.elite.beans.PresolicitudChequesResponseTO;
import com.bancoazteca.elite.db.beans.InsertaPlazoTasaRequesTO;
import com.bancoazteca.elite.ejb.alnova.AlnovaEjbSL;
import com.bancoazteca.elite.ejb.alnova.AlnovaEjbSLHome;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaResponseTO;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;
import com.bancoazteca.elite.ejb.chequera.ChequeraException;
import com.bancoazteca.elite.ejb.chequera.ChequeraSL;
import com.bancoazteca.elite.ejb.chequera.ChequeraSLHome;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.InversionesSL;
import com.bancoazteca.elite.ejb.inversiones.InversionesSLHome;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaCacheTO;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionResponse;
import com.bancoazteca.elite.inversiones.db.dao.InversionesDAO;
import com.bancoazteca.elite.util.DispatchManager;
import com.bancoazteca.elite.util.JNDINames;
import com.bancoazteca.elite.util.ServiceLocator;

public class ResourceFacadeSegundoSLBean implements SessionBean {

	private static final long serialVersionUID = 5040696197901868927L;
	
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

	
	private InversionesSL getInversionesSL(){
		InversionesSL inversionesSL = null;
		try {
			InversionesSLHome inversionesSLHome = (InversionesSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.INVERSIONES);
			inversionesSL = inversionesSLHome.create();
		} catch (Exception e){}
		return inversionesSL;
	}
	
	private ChequeraSL getChequeraSL(){
		ChequeraSL chequeraSL = null;
		try {
			ChequeraSLHome inversionesSLHome = (ChequeraSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.CHEQUERA);
			chequeraSL = inversionesSLHome.create();
		} catch (Exception e){}
		return chequeraSL;
	}
	
	public InversionesResponseTO dispatchManager(InversionesRequestTO request)throws InversionesGenericException{
		InversionesSL inversionesSL=getInversionesSL();
		DispatchManager dispatchManager=new DispatchManager();
		return dispatchManager.dispatchManager(request, inversionesSL);
	}
	
	public void setDatosPlazoTasa(PlazoTasaCacheTO plazoTasaCacheTO)
	{
		InsertaPlazoTasaRequesTO insertaPlazoTasa=new InsertaPlazoTasaRequesTO();
		InversionesDAO inversionesDAO=new InversionesDAO();
		
		/*synchronized (this.cacheTO) {
			this.cacheTO.setPlazoTasaCacheTO(plazoTasaCacheTO);
			insertaPlazoTasa.setFecha(this.cacheTO.getPlazoTasaCacheTO().getFecha_operacion());
			insertaPlazoTasa.setHora(this.cacheTO.getPlazoTasaCacheTO().getHora_operacion());
		}*/
		
		StringBuilder stringPalzo=new StringBuilder();
		StringBuilder stringTasa=new StringBuilder();

		

		for (PlazoTasaTO plazoTasa : plazoTasaCacheTO.getPlazosTasas()) {
			stringPalzo.append(plazoTasa.getPlazo()+" "+plazoTasa.getTipoPlazo()+", ");
			stringTasa.append(plazoTasa.getTasa()+", ");
		}
		
		stringPalzo.deleteCharAt(stringPalzo.lastIndexOf(","));
		stringTasa.deleteCharAt(stringTasa.lastIndexOf(","));
		
		insertaPlazoTasa.setPlazo(stringPalzo.toString());	
		insertaPlazoTasa.setTasa(stringTasa.toString());
		insertaPlazoTasa.setUsuarioModifico("TAS");
		
		try {
			inversionesDAO.insertaMonitoreoPlazoTasa(insertaPlazoTasa);
		} catch (InversionesGenericException e) {
			e.printStackTrace();
		}	
	}
	
	private AlnovaEjbSL getEjbAlnovaSL(){
		AlnovaEjbSL alnovaEjbSL = null;
		try {
			AlnovaEjbSLHome inversionesSLHome = (AlnovaEjbSLHome) ServiceLocator.getInstance().getLocalHome(JNDINames.ALNOVA);
			alnovaEjbSL = inversionesSLHome.create();
		} catch (Exception e){}
		return alnovaEjbSL;
	}
	
	public EjbAlnovaResponseTO ejecutaTransaccionAlnova(EjbAlnovaRequestTO ejbAlnovaRequestTO) throws IOException, AlnovaException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		AlnovaEjbSL alnovaSL = getEjbAlnovaSL();
		return alnovaSL.ejecutaTransaccion(ejbAlnovaRequestTO);
	}
	
	public Collection<PlazoTasaTO> getPlazoTasaBD() throws InversionesGenericException{
		
		InversionesDAO inversionesDAO = new InversionesDAO();
		Collection<PlazoTasaTO> plazoTasa = null;
		plazoTasa = inversionesDAO.busquedaPlazoTasa();
		
		return plazoTasa;
	}
	 
	public LoginConciliacionResponseTO getLoginConciliacion(LoginConciliacionRequestTO loginConciliacionRequestTO) throws InversionesGenericException, SessionExpiredException, EliteDataException {
		InversionesSL inversionesSL = getInversionesSL();
		return inversionesSL.getLoginConciliacion(loginConciliacionRequestTO);
	}
	
	public B706_RegistroRetencionResponseTO retencion_alnova(B706_RegistroRetencionRequestTO registroRetencionRequestTO) throws InversionesGenericException, IOException{
		InversionesSL inversionesSL = getInversionesSL();
		return inversionesSL.retencion_alnova(registroRetencionRequestTO);
	}
	
	public B756_QuitarRetencionResponse quitar_retencion(B756_QuitarRetencionRequest requestWrapper,String idAlnova,String portal) throws InversionesGenericException{
		InversionesSL inversionesSL = getInversionesSL();
		return inversionesSL.quitar_retencion(requestWrapper,idAlnova,portal);
	}
	public ConsultaChequesResponseTO solicitudConsultarChequera(ConsultaChequesRequestTO chequesRequestTO) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.solicitudConsultarChequera(chequesRequestTO);
	}
	
	public ConsultaChequesResponseTO detalleVariasCuentasChequera(ConsultaChequesRequestTO chequesRequestTO) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.detalleVariasCuentasChequera(chequesRequestTO);
	}
	public ConsultaChequesResponseTO consultaChequera(ConsultaChequesRequestTO chequesRequestTO) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.consultaChequera(chequesRequestTO);
	}
	public ConsultaChequesResponseTO consultaChequeraDetalle(ConsultaChequesRequestTO chequesRequestTO) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.consultaChequeraDetalle(chequesRequestTO);
	}
	public LiberacionChequesResponseTO liberacionChequesSolicitudCuenta(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraSL chequera = getChequeraSL();
		return chequera.liberacionChequesSolicitudCuenta(request);
	}
	
	public LiberacionChequesResponseTO liberacionChequesSolicitudChequera(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraSL chequera = getChequeraSL();
		return chequera.liberacionChequesSolicitudChequera(request);
	}
	
	public LiberacionChequesResponseTO liberacionChequesSolicitudCheque(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraSL chequera = getChequeraSL();
		return chequera.liberacionChequesSolicitudCheque(request);
	}

	public LiberacionChequesResponseTO liberacionChequesValidacion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraSL chequera = getChequeraSL();
		return chequera.liberacionChequesValidacion(request);
	}
	
	public LiberacionChequesResponseTO liberacionChequesEjecucion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException {
		ChequeraSL chequera = getChequeraSL();
		return chequera.liberacionChequesEjecucion(request);
	}
	
	
	/*
	 * Inicio de Chequera
	 * */
	
	public ChequeraRoboExtravioResponseTO getRoboExtravioInicio(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.getRoboExtravioInicio(extravioRequestTO);
	}
	
	public ChequeraRoboExtravioResponseTO getExtravioObtencionChequera(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.getExtravioObtencionChequera(extravioRequestTO);
	}
	
	public ChequeraRoboExtravioResponseTO getExtravioTipoOperacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.getExtravioTipoOperacion(extravioRequestTO);
		
	}
	
	public ChequeraRoboExtravioResponseTO getExtravioValidaInformacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.getExtravioValidaInformacion(extravioRequestTO);
	}
	
	public ChequeraRoboExtravioResponseTO getExtravioEjecucion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.getExtravioEjecucion(extravioRequestTO); 
	}
	
	
	public ActivacionChequeraResponseTO setActivacionChequeSolicitud( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.setActivacionChequeSolicitud( requestTO );
	}

	public ActivacionChequeraResponseTO setActivacionChequeSeleccionCuenta( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.setActivacionChequeSeleccionCuenta( requestTO );
	}

	public ActivacionChequeraResponseTO getActivacionChequeValidacion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.getActivacionChequeValidacion( requestTO );
	}

	public ActivacionChequeraResponseTO getActivacionChequeEjecucion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL = getChequeraSL();
		return chequeraSL.getActivacionChequeEjecucion( requestTO );
	}
	
	public PresolicitudChequesResponseTO presolicitudChequeraInicio(PresolicitudChequesRequestTO requestTO) throws  ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL=getChequeraSL();
		return chequeraSL.presolicitudChequeraInicio(requestTO);
	}
	
	public PresolicitudChequesResponseTO presolicitudChequeraInicioDetalleCuenta(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL=getChequeraSL();
		return chequeraSL.presolicitudChequeraInicioDetalleCuenta(requestTO);
	}
	
	public PresolicitudChequesResponseTO presolicitudChequeraValidacion(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL=getChequeraSL();
		return chequeraSL.presolicitudChequeraValidacion(requestTO);
	}

	public PresolicitudChequesResponseTO presolicitudChequeraEjecucion(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException{
		ChequeraSL chequeraSL=getChequeraSL();
		return chequeraSL.presolicitudChequeraEjecucion(requestTO);
	}

}