package com.bancoazteca.elite.ejb.preferencias;

import java.io.IOException;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import com.bancoazteca.elite.beans.AltasUsuariosRequestTO;
import com.bancoazteca.elite.beans.ConsultaExpressRequestTO;
import com.bancoazteca.elite.beans.ConsultaExpressResponseTO;
import com.bancoazteca.elite.beans.EstatusCuentaRequestTO;
import com.bancoazteca.elite.beans.EstatusCuentaResponseTO;
import com.bancoazteca.elite.beans.FiltroCuentaRequestTO;
import com.bancoazteca.elite.beans.FiltroCuentaResponseTO;
import com.bancoazteca.elite.beans.PreguntasFrecuentesResponseTO;
import com.bancoazteca.elite.beans.TerminalAlnovaResponseTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;

public interface PreferenciasSL  extends EJBLocalObject{
	public EstatusCuentaResponseTO getCuentasUsuarioStatus(EstatusCuentaRequestTO statusCuentaRequestTO)  throws DAOException;
	public EstatusCuentaResponseTO getCambiarEstatus(EstatusCuentaRequestTO statusCuentaRequestTO)  throws DAOException;
	public FiltroCuentaResponseTO filtraCuentasOcultas(FiltroCuentaRequestTO filtroCuentaRequestTO) throws DAOException;
	public PreguntasFrecuentesResponseTO getPreguntasFrecuentes() throws IOException, XmlDecodeException;
	
	//altas usuarios
	public void consultarCuentaActivar(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public void validarDatosActivar(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public void validarContrato(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public void consultarUsuarioDisponible(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public void registrarUsuario(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public void activarUsuario(AltasUsuariosRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	//fin altas usuarios
	
	public TerminalAlnovaResponseTO getTerminalAlnova(String user) throws SessionExpiredException,DAOException;
	public void liberaTerminalAlnova(String user,String terminal) throws SessionExpiredException,DAOException, EJBException, UsuarioException;
	
	//consulta express
	public ConsultaExpressResponseTO consultarCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public ConsultaExpressResponseTO validarCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public ConsultaExpressResponseTO cerrarCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	public ConsultaExpressResponseTO getRecibosCuentaExpress(ConsultaExpressRequestTO requestTO) throws UsuarioException, SessionExpiredException, EliteDataException;

}