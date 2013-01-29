package com.bancoazteca.elite.ejb.usuario;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalObject;

import org.apache.http.HttpException;

import com.bancoazteca.elite.beans.ActivarCuentaExpressRequestTO;
import com.bancoazteca.elite.beans.ActivarCuentaExpressResponseTO;
import com.bancoazteca.elite.beans.ActualizaDatosRequestTO;
import com.bancoazteca.elite.beans.ActualizaDatosResponseTO;
import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.BibliotecaRecibosRequestTO;
import com.bancoazteca.elite.beans.BibliotecaRecibosTO;
import com.bancoazteca.elite.beans.ChangePasswordResponseTO;
import com.bancoazteca.elite.beans.ChangePaswordRequestTO;
import com.bancoazteca.elite.beans.ChangeSecurityLevelRequestTO;
import com.bancoazteca.elite.beans.ChangeSecurityLevelResponseTO;
import com.bancoazteca.elite.beans.ConsultaMonitoreoRequestTO;
import com.bancoazteca.elite.beans.FotoUnicaRequestTO;
import com.bancoazteca.elite.beans.FotoUnicaResponseTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.beans.LlaveSeguridadRequestTO;
import com.bancoazteca.elite.beans.LlaveSeguridadResponseTO;
import com.bancoazteca.elite.beans.LocalizaSucursalRequestTO;
import com.bancoazteca.elite.beans.LocalizaSucursalResponseTO;
import com.bancoazteca.elite.beans.LoginRequestTO;
import com.bancoazteca.elite.beans.LoginResponseTO;
import com.bancoazteca.elite.beans.MonitoreoAdministradorRequestTO;
import com.bancoazteca.elite.beans.MonitoreoResponseTO;
import com.bancoazteca.elite.beans.ParameterOnSessionTO;
import com.bancoazteca.elite.beans.RecuperaPasswordRequestTO;
import com.bancoazteca.elite.beans.RecuperaPasswordResponseTO;
import com.bancoazteca.elite.beans.TokenTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.LoginException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.monitoreo.db.dao.MonitoreoAdministradorResponseTO;
import com.bancoazteca.elite.beans.UsuarioOperacionesTO;
import com.bancoazteca.elite.beans.UsuariosTO;
import com.bancoazteca.elite.beans.DetalleMonitoreoTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesRequestTO;
import com.bancoazteca.elite.beans.OperacionesFrecuentesResponseTO;
/**
 * @author Banco Azteca S.A. [JFAV] Agosto 25, 2008.
 */
public interface UsuarioSL extends EJBLocalObject {

	/**
	 * 
	 */
	public void setXmlBeanRules();
	
	/**
	 * @param loginRequestTO
	 * @return
	 * @throws EJBException
	 * @throws LoginException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public LoginResponseTO login(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException;

	/**
	 * @param loginRequestTO
	 * @return	
	 * @throws EJBException
	 * @throws LoginException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
//	public Login_clienteTO loginCliente(LoginRequestTO loginRequestTO) throws UsuarioException, LoginException, SessionExpiredException, EliteDataException;
	public LoginResponseTO loginCliente(LoginRequestTO loginRequestTO) throws UsuarioException, LoginException, SessionExpiredException, EliteDataException;
	/**
	 * 
	 * @param loginRequestTO
	 * @return
	 * @throws UsuarioException
	 * @throws LoginException
	 * @throws SessionExpiredException
	 * @throws EliteDataException
	 */
	public LoginResponseTO getOnDemandAccounts(LoginRequestTO loginRequestTO) throws UsuarioException, LoginException, SessionExpiredException, EliteDataException;
	
	/**
	 * @param loginRequestTO
	 * @return
	 * @throws EJBException
	 * @throws LoginException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public LoginResponseTO sessionManagment(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException;

	/**
	 * @param loginRequestTO
	 * @return
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public LoginResponseTO sessionManagmentCliente(LoginRequestTO loginRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException;
	/**
	 * @param loginRequestTO
	 * @return
	 * @throws EJBException
	 * @throws LoginException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public LoginResponseTO getCuentasUsuario(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException;

	/** Obtiene el saldo actualizado del número de cuenta proporcionado.
	 * @param loginRequestTO
	 * @return
	 * @throws EJBException
	 * @throws LoginException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public LoginResponseTO getActualizarCuentaUsuario( BalanceRequestTO balanceRequestTO ) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException;

	public LoginResponseTO getCuentasUsuarioParalelo(LoginRequestTO loginRequestTO) throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException;
		
	/**
	 * Realiza el cambio de password de Banca por Internet a trav&eacute;s de
	 * eBanking.
	 * 
	 * @param username
	 * @param oldPassword
	 * @param password
	 * @param vpassword
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public ChangePasswordResponseTO changePassword(ChangePaswordRequestTO changePaswordRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException;

	/**
	 * Sale de la aplicaci&oacute;n.
	 * 
	 * @param username
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public void logout(String username) throws EJBException, SessionExpiredException, UsuarioException;

	/**
	 * Verifica si tarjeta o cuenta existe, correcta y si esta activa
	 * 
	 * @param recuperaUsuarioPasswordTO
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public RecuperaPasswordResponseTO sendAccountOrCreditCardNumber(RecuperaPasswordRequestTO recuperaUsuarioPasswordTO) throws EJBException, EliteDataException, UsuarioException, SessionExpiredException;

	/**
	 * Verifica si datos personales son correctos
	 * 
	 * @param recuperaUsuarioPasswordTO
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public RecuperaPasswordResponseTO sendPersonalData(RecuperaPasswordRequestTO recuperaPasswordTO) throws EJBException, EliteDataException, UsuarioException, SessionExpiredException;
	
	public void sendChangePersonalData(RecuperaPasswordRequestTO passwordRequestTO)	throws EJBException, EliteDataException, UsuarioException, SessionExpiredException;

	/**
	 * Envia nuevo password
	 * 
	 * @param recuperaUsuarioPasswordTO
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 */
	public RecuperaPasswordResponseTO updateToNewPassword(RecuperaPasswordRequestTO recuperaUsuarioPasswordTO) throws EJBException, EliteDataException, UsuarioException, SessionExpiredException;

	/**
	 * Confirma el cambio de Nivel de Seguridad a trav&eacute;s de ebanking.
	 * 
	 * @param changeSecurityLevelRequestTO
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public ChangeSecurityLevelResponseTO modifySecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException;

	/**
	 * Confirma el cambio de Nivel de Seguridad a trav&eacute;s de ebanking.
	 * 
	 * @param changeSecurityLevelRequestTO
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public ChangeSecurityLevelResponseTO waitChangeSecurityLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException;

	/**
	 * Realiza el cambio de Nivel de Seguridad a trav&eacute;s de ebanking.
	 * 
	 * @param changeSecurityLevelRequestTO
	 * @return
	 * @throws EJBException
	 * @throws SessionExpiredException
	 * @throws UsuarioException
	 * @throws EliteDataException
	 */
	public ChangeSecurityLevelResponseTO executeChangeLevel(ChangeSecurityLevelRequestTO changeSecurityLevelRequestTO) throws EJBException, SessionExpiredException, UsuarioException, EliteDataException;
	
	public LoginResponseTO getOnDemandDetailAccounts(LoginRequestTO loginRequestTO)throws EJBException, LoginException, SessionExpiredException, UsuarioException, EliteDataException;
	
	public LlaveSeguridadResponseTO getLlaveSeguridad(LlaveSeguridadRequestTO llaveSeguridadRequestTO) throws EliteDataException, UsuarioException;
	
	public Boolean validaHuella(HuellaDigitalTO huellaDigitalTO) throws UsuarioException;
	
	public boolean validaToken(TokenTO tokenTO) throws SessionExpiredException, EliteDataException, UsuarioException;
	
	public ActivarCuentaExpressResponseTO activacionExpressInicio(ActivarCuentaExpressRequestTO activarCuentaTO) throws UsuarioException, SessionExpiredException, EliteDataException;

	public ActivarCuentaExpressResponseTO activacionExpressValidacion(ActivarCuentaExpressRequestTO activarCuentaTO) throws UsuarioException, SessionExpiredException, EliteDataException;
	
	public BibliotecaRecibosTO getServiciosBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException;
	public BibliotecaRecibosTO getBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException;
	public BibliotecaRecibosTO getDatosPDFBibliotecaRecibos(BibliotecaRecibosRequestTO user) throws SessionExpiredException, UsuarioException, EliteDataException;
	
	public void setParameterOnEBankSession(ParameterOnSessionTO onSessionTO) throws SessionExpiredException, URISyntaxException, HttpException, IOException;
	
	public void insertaUsuarios(UsuariosTO usuarios);
	
	public void insertaUsuarioOperacion(UsuarioOperacionesTO usuarioOperacion);
	
	public void insertXml(DetalleMonitoreoTO detalle);
	
	public void insertError(UsuarioOperacionesTO usuarioOperacion,DetalleMonitoreoTO detalle);
	
	public void insertMapa(Integer idMapa,Integer idTracking,String campo,String valor);
	
	public Integer getIdUsuario(String userName,String aplicacion);
	
	public Integer getIdTracking(String idUsuarioOperacion);
	
	
	public Integer getIdUsuarioOperacion(String userName, String aplicacion);
	
	//nuevos reportes
	
	public MonitoreoResponseTO getTotalUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getTotalOperacionesByUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getTrackingUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getOperacionMonto(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getAllTotalUsuariosAplicacion()throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getAllUsuariosAplicacion()throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getAllTotalOperaByUser()throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getAllTrackingUserAplica()throws EJBException, UsuarioException, EliteDataException;
	//termina nuevos reportes

	
	public MonitoreoResponseTO getTotalUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getTrackingUsuariosPorAplicacion(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
	
	public MonitoreoResponseTO getTotalOperacionesUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;
		
	public MonitoreoResponseTO getTotalConexionesUsuarios(ConsultaMonitoreoRequestTO requestTO)throws EJBException, UsuarioException, EliteDataException;

	public void insertaUsuariosAdministrador(MonitoreoAdministradorRequestTO administradorRequestTO)throws EJBException, UsuarioException;
	
	public MonitoreoAdministradorResponseTO validarUsuarioAdministrador(MonitoreoAdministradorRequestTO administradorRequestTO) throws EJBException, UsuarioException;

//	TODO LOGICA DE INSERCION HACIA MONITOREO PAUL

	public MonitoreoResponseTO getDatosMonitoreoPorUsuario(ConsultaMonitoreoRequestTO monitoreoRequestTO)throws EJBException, UsuarioException;

	public MonitoreoResponseTO getDatosMonitoreoPorAplicacion(ConsultaMonitoreoRequestTO monitoreoRequestTO)throws EJBException, UsuarioException;
	
	public ActualizaDatosResponseTO getInitialActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException;
	
	public ActualizaDatosResponseTO getActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException;
	
	public ActualizaDatosResponseTO getEjecutaActualizaDatos(ActualizaDatosRequestTO datosUsuarioRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException;
	
	//cambio contrasena
	public ChangePasswordResponseTO getInitialCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException;
	
	public ChangePasswordResponseTO getEjecutaCambiarContrasena(ChangePaswordRequestTO cambiarContrasenaRequestTO) throws EJBException, UsuarioException, EliteDataException, SessionExpiredException;

	//localiza tu sucursal
	public LocalizaSucursalResponseTO getObtieneEstados(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException;
	
	public LocalizaSucursalResponseTO getObtieneMunicipios(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException;
	
	public LocalizaSucursalResponseTO getObtieneTienda(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException;
	
	public LocalizaSucursalResponseTO getObtieneTiendaCP(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException;
	
	public LocalizaSucursalResponseTO getObtieneTiendaPalabra(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException;
	
	public LocalizaSucursalResponseTO getObtieneTiendas(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws EJBException, UsuarioException, SessionExpiredException, EliteDataException;
	//localiza tu sucursal
	//operaciones frecuentes
	public OperacionesFrecuentesResponseTO setDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException;
	
	public OperacionesFrecuentesResponseTO getDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException;
	
	public OperacionesFrecuentesResponseTO eliminaDatosOperacionesFrecuentes(OperacionesFrecuentesRequestTO operacionesFrecuentesRequestTO) throws SessionExpiredException, UsuarioException, EliteDataException;
	//operaciones frecuentes
	
//  web service foto unica
	public FotoUnicaResponseTO consultaFotoUnica(FotoUnicaRequestTO fotoUnicaRequestTO)throws SessionExpiredException, UsuarioException, EliteDataException;
//	web service foto unica
	
}
