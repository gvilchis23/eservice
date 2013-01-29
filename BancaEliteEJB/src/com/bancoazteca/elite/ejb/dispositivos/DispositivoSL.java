package com.bancoazteca.elite.ejb.dispositivos;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import com.bancoazteca.elite.beans.ActivacionFirmaRequestTO;
import com.bancoazteca.elite.beans.ActivacionFirmaResponseTO;
import com.bancoazteca.elite.beans.BloqueoFirmaRequestTO;
import com.bancoazteca.elite.beans.BloqueoFirmaResponseTO;
import com.bancoazteca.elite.beans.RastreoPedidoRequestTO;
import com.bancoazteca.elite.beans.RastreoPedidoResponseTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoRequestTO;
import com.bancoazteca.elite.beans.SolicitudDispositivoResponseTO;
import com.bancoazteca.elite.beans.SincronizacionFirmaRequestTO;
import com.bancoazteca.elite.beans.SincronizacionFirmaResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

public interface DispositivoSL extends EJBLocalObject {

	public void setXmlBeanRules();
	
	public RastreoPedidoResponseTO rastreoPedido(RastreoPedidoRequestTO rastreoPedidoRequestTO) throws SessionExpiredException, DispositivoException, EliteDataException;
	
	public BloqueoFirmaResponseTO solicitaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;
	
	public BloqueoFirmaResponseTO validaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;

	public BloqueoFirmaResponseTO ejecutaBloqueoToken(BloqueoFirmaRequestTO bloqueoFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;
	
	public ActivacionFirmaResponseTO solicitaActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;

	public ActivacionFirmaResponseTO validaActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;

	public ActivacionFirmaResponseTO ejecutaActivacionToken(ActivacionFirmaRequestTO activacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;

	public SincronizacionFirmaResponseTO solicitaSincronizacionToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;

	public SincronizacionFirmaResponseTO validaSincronizacionToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;
	
	public SincronizacionFirmaResponseTO ejecutaSincronizacionToken(SincronizacionFirmaRequestTO sincronizacionFirmaRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;

	public SolicitudDispositivoResponseTO setInitialSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;
	
	public SolicitudDispositivoResponseTO setCuentaSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;
	
	public SolicitudDispositivoResponseTO setDataSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;
	
	public SolicitudDispositivoResponseTO setConfirmSolicitudDispositivo(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;

	public SolicitudDispositivoResponseTO getCatalogoEstadosDispositivos(SolicitudDispositivoRequestTO solicitudDispositivoRequestTO) throws EJBException, DispositivoException, SessionExpiredException, EliteDataException;
}
