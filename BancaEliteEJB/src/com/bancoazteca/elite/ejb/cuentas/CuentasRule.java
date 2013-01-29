package com.bancoazteca.elite.ejb.cuentas;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.DatosEliteVO;
import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;


public class CuentasRule extends EliteRules {
	
	private static final Logger $log = Logger.getLogger(CuentasRule.class);

	protected CuentasRule(MessageElement messageElement) throws EliteDataException {
		super(messageElement);		
	}
	
	public void validateByActionErrors() throws EliteDataException,CuentasException{
		try {
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	
	public void validateGetCardsInternetSales()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion_internet/activacionInternetEjecutar.jsp")
				&& !datosEliteTO.getJspForward().equalsIgnoreCase("/activacion_internet/activacionInternetInicio.jsp")
				){												  
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validateStateInternetSales()throws EliteDataException,CuentasException{
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion_internet/activacionInternetEjecutar.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}			
		}catch(XmlDecodeException e){
			throw new CuentasException(e);
		}		
	}
	
	public void validateSetActivationCardInternetSales()throws EliteDataException,CuentasException{		
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/activacion_internet/activacionInternetConfirmar.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}			
		}catch(XmlDecodeException e){
			throw new CuentasException(e);
		}
	}
	
	
	public void validateAlertsLoadInitialData() throws EliteDataException,CuentasException{
	/*	try {
			Map<String, String> actionErrorsMap = decode.buildMapBeans(xmlResponse, ACTION_ERROR_TAG);
			if( !actionErrorsMap.isEmpty()) {
				throw new EliteDataException("Error", actionErrorsMap, LEVEL_ACTION_ERRORS);			
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}*/
	}

	public void validateGetDetalleMovimientosInfinite()throws EliteDataException,CuentasException{		
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/tarjetaInfinite/estadoCuentaInfinite.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}			
		}catch(XmlDecodeException e){
			throw new CuentasException(e);
		}
	}

	public void validateGetCardsChangeBranch() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_centrodestino/inicio.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
	public void validateDatosCambioSucursal() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_centrodestino/datosTarjeta.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
	public void validateBusquedaDatosSucursal() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_centrodestino/busqueda.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
	public void validateConfirmaCambioSucursal() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_centrodestino/exito.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
	public void validateGetMunicipChangeBranch() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_centrodestino/busqueda.jsp"));
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	
	public void validateSearchChangeBrach() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_centrodestino/resultadobusqueda.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
	public void validateListCardsToLockAndUnlock() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cancelacion/cancelacionInicio.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
	public void validatePrepareGetState() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cancelacion/cancelacionEjecutar.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
	public void validaInformacionTarjetaWait() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	public void validaInformacionTarjeta() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cancelacion/cancelacionEjecutar.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	public void validateConfirmCancelDispatch() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/cancelacion/cancelacionConfirmacion.jsp")) {
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmldex) {
			throw new CuentasException(xmldex);
		}
	}
	
		
	public void validateGetTarjetaCorporativaCredito()throws EliteDataException,CuentasException{		
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/tarjetacorporativa/muestraSaldos.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}			
		}catch(XmlDecodeException e){
			throw new CuentasException(e);
		}
	}
	
	public void validateGetTarjetaCorporativaDebito()throws EliteDataException,CuentasException{		
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/tarjetacorporativa/debito/detalleSaldo.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}			
		}catch(XmlDecodeException e){
			throw new CuentasException(e);
		}
	}
	
	public void validateDataAlertFirstStep() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/activa/acAAC.do?seleccion=inicioAAC")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	
	public void validateDataAlertThirdStep() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/activa/acActivaExitosa.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void validateconfirmAlertFirstStep() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/activa/acActivaAlertasCelular.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	public void validateDataAlertSecondStep()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/activa/acAACC.do?seleccion=inicioAACC")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	public void validateconfirmAlertSecondStep()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/activa/acActivaAlertasCelularCuenta.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	public void validateAlertsAcountsSelectedLinkUpdate()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acModificaPrincipal.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	
	public void validateDataForUpdateAlertFirstStep() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acMEC.do?seleccion=inicioMEC") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateDataForUpdateAlertFirstStepInicioMec() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acModificaEliminaConfirma.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateDataForUpdateAlertFirstStepModifica() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acMMC.do?seleccion=inicioMMC") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateDataForUpdateAlertFirstStepModificaConfirmacion() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acModificaModificaConfirma.jsp") ){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	
	public void validateDataForUpdateAlertSecondStep()throws CuentasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acMM.do?seleccion=inicioMM")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	public void validateConfirmDataForUpdateAlertSecondStep()throws CuentasException,EliteDataException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acModificaModifica.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void validateJSPDataForUpdateAlertFirstStep() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/alertas/celular/modifica/acModificaModificaConfirma.jsp")){
				throw new EliteDataException("Error", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	public void validatePreparacionAgregaCuentasFrecuentes() throws EliteDataException,CuentasException{
			try {
				Map<String, String> errors = super.getActionErrros();
				if (errors!=null && !errors.isEmpty()){
					throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
				}
			} catch (XmlDecodeException e) {
				throw new CuentasException (e);
			}
		}
	
	public void validatePrepararAgregaCuentasFrecuentes() throws EliteDataException,CuentasException{
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/agregaFrecuente.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	/**
	 * Valida paths para el metodo de ejecucion de alta de frecuentes terceros
	 * @throws EliteDataException
	 * @throws CuentasException
	 */
	public void validateTercerosConfirmaAltaFrecuente() throws EliteDataException,CuentasException{
		try {
			
			validateByActionErrors();
			
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	/**
	 * Segundo validador para el metodo de ejecucion de alta de frecuentes terceros.
	 * @throws EliteDataException
	 * @throws CuentasException
	 */
	public void validateTercerosConfirmaAltaFrecuentePre() throws EliteDataException,CuentasException{
		try {
			
			validateByActionErrors();
			
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/exitoFrecuente.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	/**
	 * Validador para el paso de validacion de alta de frecuentes terceros
	 * @throws EliteDataException
	 * @throws CuentasException
	 */
	public void validatePreparacionFrecuentesTerceros() throws EliteDataException,CuentasException{
		try {
			
			validateByActionErrors();
			
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/agregaFrecuente.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	/**
	 * Segundo validador para el paso de validacion de alta de frecuentes terceros
	 * @throws EliteDataException
	 * @throws CuentasException
	 */
	public void validateAgregaDatosFrecuentesTerceros() throws EliteDataException,CuentasException{
		try {
			
			validateByActionErrors();
			
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	/**
	 * Tercer validador para el paso de validacion de alta de frecuentes terceros
	 * @throws EliteDataException
	 * @throws CuentasException
	 */
	public void validateAgregaDatosFrecuentesPreTerceros() throws EliteDataException,CuentasException{
		try {

			validateByActionErrors();
			
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/agregaFrecuenteContrasenia.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	
	
	/**
	 * Metodo usado en Otros Bancos
	 * @throws EliteDataException
	 * @throws CuentasException
	 */
	public void validateAgregaCuentasFrecuentes() throws EliteDataException,CuentasException{
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validateAgregaCuentasFrecuentesPre() throws EliteDataException,CuentasException{
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/agregaFrecuenteContrasenia.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validateGuardaCuentasFrecuentes() throws EliteDataException,CuentasException{
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			DatosEliteTO datosEliteTO=getDatosEliteTO();
			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validateGuardaCuentasFrecuentesPre() throws EliteDataException,CuentasException{
		try {
			System.out.println("Entro a validateGuardaCuentasFrecuentesPre metodo que comentamos el validador de paths");
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
//			DatosEliteTO datosEliteTO=getDatosEliteTO();
//			if(!datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/exitoFrecuente.jsp")){
//				throw new EliteDataException("error de paths",null,LEVEL_PATHS);
//			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validateEliminaCuentasFrecuentes() throws EliteDataException,CuentasException{
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
//	descomentar
	public void validateModificaCuentasFrecuentes() throws EliteDataException,CuentasException{
			validateByActionErrors();
	}
	
	public void validateModificaCuentasFrecuentesValidacion() throws EliteDataException,CuentasException{
		try {
			validateByActionErrors();
			
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(datosEliteTO!=null && !datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
			
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	
	public void validateModificaCuentasFrecuentesValidacion01() throws EliteDataException,CuentasException{
		try {
			validateByActionErrors();
			
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if(datosEliteTO!=null && !datosEliteTO.getJspForward().equalsIgnoreCase("/transferencias/cuentasFrecuentes/modificaFrecuenteContrasenia.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
			
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	
	
	public void validateGuardaModificaCuenta() throws EliteDataException,CuentasException{
		try {
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	
	
	public void validatePartnerPlusBeneficiary() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else {
				XMLDecode decode = new XMLDecode();
				String message = decode.getString(messageElement,"msgPlus");
				$log.info("mensaje "+message);
				if (message!=null && message.length()!=0){
					errors = new HashMap<String, String>();
					errors.put("error", message);
					throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
				}
			}
			if (datosEliteTO.getJspForward().equals("/aperturas/fideicomiso/apertura.jsp")){
				errors = new HashMap<String, String>();
				errors.put("error", "Has alcanzado el número máximo de aperturas de Cuenta Socio Plus.");
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equals("/aperturas/fideicomiso/apertura2.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
			
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validatePartnerPlusOpenAccount() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equals("/aperturas/fideicomiso/designacion.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validatePartnerPlusData() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equals("/aperturas/fideicomiso/cartaadhesion.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}		
	
	public void validatePartnerPlusWaitConfirmData() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equals("/wait.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
			
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	public void validateCuentaSocioPlus() throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
	
	public void validatePartnerPlusConfirmData() throws EliteDataException,CuentasException{
		try {			
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}
			
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}

	public void validateReleaseVouchersInicio() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/consultaNominaRecibosInicio.do?method=contrato")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					Map<String, String> error=new HashMap<String,String>();
					error.put("NO_DISPONIBLE", "El servicio no se encuentra disponible");
					throw new EliteDataException("No disponible",error,LEVEL_ACTION_ERRORS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}
				
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}

	public void validateReleaseVouchersContrato() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/consultaNominaRecibosInicio.do?method=consulta")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					throw new EliteDataException("No disponible",null,LEVEL_PATHS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}
				
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	
	public void validateReleaseVouchersConsulta() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errors ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/sobre.jsp")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/sinRecibos.jsp")) {
					errors=new HashMap<String, String>();
					errors.put("sinR", "Estimado usuario no tienes recibos por liberar");
					throw new EliteDataException("Action errors",errors,LEVEL_ACTION_ERRORS);
				}
				else 
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					throw new EliteDataException("No disponible",null,LEVEL_PATHS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_ACTION_MESSAGES);
				}
			
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	public void validateReleaseVouchersSeleccion() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/consultaRec.jsp")){
				if(datosEliteTO.getJspForward().equals("/snomina/recibos/noDisponible.jsp")) {
					throw new EliteDataException("No disponible",null,LEVEL_PATHS);
				} else {
					System.out.println(datosEliteTO.getJspForward());
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}
				
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}

	public void validateReleaseVouchersEjecutar(String noConfirmacion) throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			
			if (datosEliteTO.getJspForward().equals("/snomina/recibos/liberadoRec.jsp")){
				if(noConfirmacion!=null && noConfirmacion.equalsIgnoreCase("NO_CONFIRMACION")){
					throw new LiberacionIncompletaExcepion("La liberacion fue exitosa pero algunos recibos no se liberaron");
				}
			}else{
				if (errors!=null && !errors.isEmpty()){
					throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
				}else{
					throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
				}
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	
	
	public void validateReleaseVouchersConsultaLiberados() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errors ",errors,LEVEL_ACTION_ERRORS);
			}										  
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/sinRecibos.jsp")&&!datosEliteTO.getJspForward().equals("/snomina/recibos/sobre.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_ACTION_MESSAGES);
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	public void validateReleaseVouchersQuery() throws EliteDataException, CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/consulta.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
		
	}
	
	public void validateReleaseVouchersDetail()throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/detalleRec.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	
	public void validateReleaseVouchersExecute() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}
			if (!datosEliteTO.getJspForward().equals("/snomina/recibos/liberado.jsp")){
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch(XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
	}
	
	public void validateCuentaSocioInvocacion() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/aperturas/cuentaSocioExt/Bienvenida.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				if(datosEliteTO.getJspForward().equals("/aperturas/cuentaSocioExt/SinNomina.jsp")){
					Map<String, String> errorMap=new HashMap<String, String>();
					errorMap.put("Restricción de cuenta","Lo sentimos usted no puede abrir una cuenta socio nomina.");
					throw new EliteDataException("Sin nomina en banco azteca",errorMap, LEVEL_ACTION_ERRORS);
				}
				//throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCuentaSocioInicio() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/aperturas/cuentaSocioExt/apertura.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCuentaSocioEnvioDatos() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equals("/aperturas/cuentaSocioExt/confirmacion.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCuentaSocioConfirmacion() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}/*else if (!datosEliteTO.getJspForward().equals("/aperturas/cuentaSocioExt/confirmacion.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}*/
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCuentaPlataInvocacion() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/wait.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCuentaPlataInicio() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/aperturas/plataBoveda/bienvenida.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCtaPlataInvocacionContratos() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/aperturas/plataBoveda/contratos.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCtaPlataAceptarContratoWait() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/wait.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCtaPlataAceptarContrato() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equals("/aperturas/plataBoveda/completeAperturaPlata.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCtaPlataEnvioDatosWait() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equals("/aperturas/plataBoveda/completeWaitAperturaPlata.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCtaPlataCotizacion() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equals("/aperturas/plataBoveda/completeAperturaPlata.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCtaPlataEnvioDatos() throws EliteDataException, CuentasException {
		try {
			Map<String, String> errors = super.getActionErrros();
			DatosEliteTO datosEliteTO = super.getDatosEliteTO();
			if (errors!=null && !errors.isEmpty()){
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ", errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equals("/aperturas/plataBoveda/validateAperturaPlata.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateMediosPagoInvocation() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/medios/medios.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateSolicitudEfectivoCajero() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cajeroLimiteDisposicion/muestraTarjetas.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateValidacionEfectivoCajero() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cajeroLimiteDisposicion/contrasena.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateEjecucionEfectivoCajero() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cajeroLimiteDisposicion/confirmacion.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	public void validateCambioNipInvocation() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_nip/nipchange.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	
	
	public void validateCambioNipExecution() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_nip/secondsign.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	public void validateCambioNipConfirmation() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				DispositivoHuellaTO dispositivoHuellaTO  = getFingerPrint();
				throw new EliteDataException("Error de Action  ",errors, dispositivoHuellaTO,LEVEL_ACTION_ERRORS);
			}   else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/cambio_nip/confirmacion.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	protected void estadoCuentaMenuRule() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			/*if (!datosEliteTO.getJspForward().equalsIgnoreCase("/servicios/serviciosFill.do")) {
				throw new EliteDataException("Error", null, LEVEL_PATHS);
			}*/
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	protected void estadoCuentaRule() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/wait.jsp")) {
				throw new EliteDataException("Error", null, LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException();
		}
	}
	
	public void validateInvokeMisFinanzas() throws EliteDataException, CuentasException{
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if(errors != null && errors.size() > 0){
				throw new EliteDataException("Action erros", errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equals("/consultas/misFinanzas.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		}catch (XmlDecodeException e){
			throw new CuentasException (e);
		}
	}
	
	public void validateConsultarMisFinanzas() throws EliteDataException, CuentasException{
		try{
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if(errors != null && errors.size() > 0){
				throw new EliteDataException("Action erros", errors,LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equals("/consultas/misFinanzas.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de Path", null , LEVEL_PATHS);
			}
		}catch (XmlDecodeException e){
			throw new CuentasException (e);
		}
	}
	
	protected void estadoCuentaCmdRule() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			if (!datosEliteTO.getJspForward().equalsIgnoreCase("/estadocuenta/estadocuenta.jsp")) {
				throw new EliteDataException("Error", null, LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	protected void estadoCuentaConsulta() throws EliteDataException, CuentasException {
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = getActionErrros();
			if (errors != null && errors.size() > 0) {
				throw new EliteDataException("Error", errors, LEVEL_ACTION_ERRORS);
			} else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/estadocuenta/estadocuenta.jsp")) {
				throw new EliteDataException("Error", "El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException(e);
		}
	}
	
	/*Activar cuentas - validacion de path*/
	public void validateAlertsAcountsSelectedLinkActive()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/activa/acActivaPrincipal.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	/*borrar cuentas - validacion de path*/
	public void validateDeleteAlertaCelular()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acME.do?seleccion=inicioME")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	/*borrar cuentas confirmacion - validacion de path*/
	public void validateDeleteAlertaCelularConfirmacion()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String, String> errors = super.getActionErrros();
			if (errors!=null && !errors.isEmpty()){
				throw new EliteDataException("Action errros ",errors,LEVEL_ACTION_ERRORS);	
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/alertas/celular/modifica/acModificaElimina.jsp")){
				$log.error("El path obtenido fue: " + datosEliteTO.getJspForward());
				throw new EliteDataException("Error de path ",null,LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			e.printStackTrace();
		}
	}
	
	public void validateCartaRetenciones()throws EliteDataException,CuentasException{
		try {
			DatosEliteTO datosEliteTO = getDatosEliteTO();
			Map<String , String> errors = getActionErrros();
			if (errors!=null && errors.size()>0){
				throw new EliteDataException("Error",errors, LEVEL_ACTION_ERRORS);
			}else if (!datosEliteTO.getJspForward().equalsIgnoreCase("/estadocuenta/stateRetenciones.jsp")){
				throw new EliteDataException("Error","El path de exito no es el esperado", LEVEL_PATHS);
			}
		} catch (XmlDecodeException e) {
			throw new CuentasException (e);
		}
	}
}
