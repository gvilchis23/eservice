package com.bancoazteca.elite.ejb.chequera;

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
import com.bancoazteca.elite.beans.PresolicitudChequesRequestTO;
import com.bancoazteca.elite.beans.PresolicitudChequesResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;


public interface ChequeraSL extends EJBLocalObject{
	public ChequeraRoboExtravioResponseTO getRoboExtravioInicio(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioObtencionChequera(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioTipoOperacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioValidaInformacion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	public ChequeraRoboExtravioResponseTO getExtravioEjecucion(ChequeraRoboExtravioRequestTO extravioRequestTO) throws EJBException, ChequeraException, SessionExpiredException, EliteDataException;
	
	public LiberacionChequesResponseTO liberacionChequesSolicitudCuenta(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesSolicitudChequera(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesSolicitudCheque(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesValidacion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	public LiberacionChequesResponseTO liberacionChequesEjecucion(LiberacionChequesRequestTO request) throws ChequeraException, SessionExpiredException, EliteDataException;
	
	public ActivacionChequeraResponseTO setActivacionChequeSolicitud( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ActivacionChequeraResponseTO setActivacionChequeSeleccionCuenta( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ActivacionChequeraResponseTO getActivacionChequeValidacion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ActivacionChequeraResponseTO getActivacionChequeEjecucion( ActivacionChequeraRequestTO requestTO ) throws ChequeraException, SessionExpiredException, EliteDataException;
	
	public PresolicitudChequesResponseTO presolicitudChequeraInicio(PresolicitudChequesRequestTO requestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
	public PresolicitudChequesResponseTO presolicitudChequeraInicioDetalleCuenta(PresolicitudChequesRequestTO requestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
	public PresolicitudChequesResponseTO presolicitudChequeraValidacion(PresolicitudChequesRequestTO requestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
	public PresolicitudChequesResponseTO presolicitudChequeraEjecucion(PresolicitudChequesRequestTO requestTO)throws  ChequeraException, SessionExpiredException, EliteDataException;
	
	public ConsultaChequesResponseTO solicitudConsultarChequera(ConsultaChequesRequestTO chequesRequestTO) throws ChequeraException, SessionExpiredException, EliteDataException;
	public ConsultaChequesResponseTO detalleVariasCuentasChequera(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
	public ConsultaChequesResponseTO consultaChequera(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
	public ConsultaChequesResponseTO consultaChequeraDetalle(ConsultaChequesRequestTO chequesRequestTO)throws ChequeraException, SessionExpiredException, EliteDataException;
}
