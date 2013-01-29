package com.bancoazteca.elite.ejb.facade.segundo;


import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

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
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaResponseTO;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;
import com.bancoazteca.elite.ejb.chequera.ChequeraException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaCacheTO;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionResponse;

/**
 * @author Banco Azteca S.A. [JFAV] Agosto 20, 2008.
 */

public interface ResourceFacadeSegundoSL extends EJBLocalObject {
	
	public InversionesResponseTO dispatchManager(InversionesRequestTO request) throws InversionesGenericException;	
	public void setDatosPlazoTasa(PlazoTasaCacheTO plazoTasaCacheTO);
	public EjbAlnovaResponseTO ejecutaTransaccionAlnova(EjbAlnovaRequestTO ejbAlnovaRequestTO) throws IOException, AlnovaException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	public Collection<PlazoTasaTO> getPlazoTasaBD() throws InversionesGenericException;
	public LoginConciliacionResponseTO getLoginConciliacion(LoginConciliacionRequestTO loginConciliacionRequestTO) throws InversionesGenericException, SessionExpiredException, EliteDataException;
	public B706_RegistroRetencionResponseTO retencion_alnova(B706_RegistroRetencionRequestTO registroRetencionRequestTO) throws InversionesGenericException, IOException;
	public B756_QuitarRetencionResponse quitar_retencion(B756_QuitarRetencionRequest requestWrapper,String idAlnova,String portal) throws InversionesGenericException;
	//inicializa chequera
	public LiberacionChequesResponseTO liberacionChequesSolicitudCuenta(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesSolicitudChequera(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesSolicitudCheque(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesValidacion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesEjecucion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	
	public ChequeraRoboExtravioResponseTO getRoboExtravioInicio(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioObtencionChequera(ChequeraRoboExtravioRequestTO requestTO)throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioTipoOperacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioValidaInformacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioEjecucion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	
	public ActivacionChequeraResponseTO setActivacionChequeSolicitud( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ActivacionChequeraResponseTO setActivacionChequeSeleccionCuenta( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ActivacionChequeraResponseTO getActivacionChequeValidacion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ActivacionChequeraResponseTO getActivacionChequeEjecucion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	
	public PresolicitudChequesResponseTO presolicitudChequeraInicio(PresolicitudChequesRequestTO requestTO) throws  ChequeraException, SessionExpiredException, EliteDataException;
	public PresolicitudChequesResponseTO presolicitudChequeraInicioDetalleCuenta(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException;
	public PresolicitudChequesResponseTO presolicitudChequeraValidacion(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException;
	public PresolicitudChequesResponseTO presolicitudChequeraEjecucion(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException;
	
	public ConsultaChequesResponseTO solicitudConsultarChequera(ConsultaChequesRequestTO chequesRequestTO) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ConsultaChequesResponseTO detalleVariasCuentasChequera(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
	public ConsultaChequesResponseTO consultaChequera(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
	public ConsultaChequesResponseTO consultaChequeraDetalle(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
}