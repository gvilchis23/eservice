package com.bancoazteca.elite.ejb.cuentas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.AlertsDataRequest;
import com.bancoazteca.elite.beans.AperturaCuentaPlataRequestTO;
import com.bancoazteca.elite.beans.AperturaCuentaSocioRequestTO;
import com.bancoazteca.elite.beans.BalanceRequestTO;
import com.bancoazteca.elite.beans.CambioNipRequestTO;
import com.bancoazteca.elite.beans.ChangeBranchRequestTO;
import com.bancoazteca.elite.beans.ChequeraPreaperturaRequestTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.ConnectorDataTO;
import com.bancoazteca.elite.beans.CreditosRequestTO;
import com.bancoazteca.elite.beans.CuentaSocioPlusRequestTO;
import com.bancoazteca.elite.beans.CuentasFrecuentesRequestTO;
import com.bancoazteca.elite.beans.DatosEliteTO;
import com.bancoazteca.elite.beans.DisposicionEfectivoCajeroRequestTO;
import com.bancoazteca.elite.beans.ERecibosNominaRequestTO;
import com.bancoazteca.elite.beans.EliteResponse;
import com.bancoazteca.elite.beans.EstadoCuentaRequestTO;
import com.bancoazteca.elite.beans.InfiniteMovimientosRequestTO;
import com.bancoazteca.elite.beans.InternetSalesRequestTO;
import com.bancoazteca.elite.beans.LockUnlockCardsRequestTO;
import com.bancoazteca.elite.beans.MovimientosCuentaInversionRequest;
import com.bancoazteca.elite.beans.RetencionesRequestTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaCreditoRequestTO;
import com.bancoazteca.elite.beans.TarjetaCorporativaDebitoRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.connector.DigitalFinger;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

public class CuentasDAO extends EliteDAO {

	private static final Logger log = Logger.getLogger(CuentasDAO.class);

	private static final String GET_MOVIMIENTOS_GANAREMAS_1="/consultas/movimientosInvAzt.elite";//cuenta=3&method=inicio 
	private static final String GET_MOVIMIENTOS_GANAREMAS_2="/consultas/movimientosInvAztCmd.elite";//cuenta=3&method=inicio
	
	private final String SET_SESSION_FORM_PARAMETER = "/seguridad/sessionFormParameter.elite";
	private final String SET_SESSION_PARAMETER = "/seguridad/sessionParameter.elite";
	 	
	private final String CONSULTA_MOVIMIENTOS_INIT = "/consultas/movimientosini.elite";
	private final String CONSULTA_MOVIMIENTOS = "/consultas/movimientosiniCmd.elite";
	private final String CONSULTA_MOVIMIENTOS_JSP = "/consultas/movimientosini.jsp";
	private final String CONSULTA_MOVIMIENTOS_SOCIO_PLUS_1 = "/consultas/movimientosSocPlus.elite";
	private final String CONSULTA_MOVIMIENTOS_SOCIO_PLUS_1_1 = "/consultas/movimientosSocPlusCmd.elite";
	private final String CONSULTA_MOVIMIENTOS_POR_FECHA_WAIT = "/consultas/movimientosXfechaini.elite";
	private final String CONSULTA_MOVIMIENTOS_POR_FECHA = "/consultas/movimientosXfechainiCmd.elite";
	private final String CONSULTA_MOVIMIENTOS_POR_RANGO_WAIT = "/consultas/movimientosXfecha.elite";
	private final String CONSULTA_MOVIMIENTOS_POR_RANGO = "/consultas/movimientosXfechaCmd.elite";
	private final String INICIO_SUCURSAL_TARJETAS = "/cambio_centrodestino/gettarjetasCmd.elite";
	private final String BUSCAR_MUNICPIOS_SUCURSAL_TARJETAS = "/cambio_centrodestino/resultadoBusqueda.elite";

	private final String BUSCAR_SUCURSAL_TARJETAS = "/cambio_centrodestino/resultadoBusqueda.elite";
	private final String MOSTAR_SUCURSAL_TARJETAS = "/cambio_centrodestino/getdata.elite";	
	private final String DATOS_BUSQUEDA_SUCURSALES ="/cambio_centrodestino/busqueda.elite";
	
	private final String CONFIRMACION_SUCURSAL_TARJETAS = "/cambio_centrodestino/exito.elite";

	private final String ACTIVAR_TARJETA_COMPRAS_INTERNET = "/activacionInternet/activacionPre.elite";
	private final String GET_ESTADO_TARJETAS_COMPRAS_INTERNET = "/activacionInternet/getEstado.elite";
	private final String EJECUTAR_ACTIVACION_COMPRAS_INTERNET = "/activacionInternet/dispatch.elite";

	private final String LISTA_TARJETAS_BLOQUEO_DES = "/cancelacion/cancelacionPre.elite";
	private final String OPERACION_TARJETAS = "/cancelacion/cancelacionGetEstado.elite";
	private final String CONFIRMA_BLOQUEO_DISPATCH = "/cancelacion/cancelacionDispatch.elite";

	private final String CARGA_DATOS = "/alertas/celular/acPre.elite";
	private final String LINK_SELECCIONADO = "/alertas/celular/activa/acAP.elite";
	private final String PASO1_ALERTAS = "/alertas/celular/activa/acAP.elite";
	private final String CONFIRM_PASO1_ALERTAS = "/alertas/celular/activa/acAAC.elite";
	private final String PASO2_ALERTAS = "/alertas/celular/activa/acAAC.elite";
	private final String CONFIRM_PASO2_ALERTAS = "/alertas/celular/activa/acAACC.elite";
	private final String PASO3_ALERTAS = "/alertas/celular/activa/acAACC.elite";
	private final String CARGA_DATOS_UPDATE_INICIALES = "/alertas/celular/acPrincipal.jsp";
	private final String CARGA_DATOS_UPDATE = "/alertas/celular/modifica/acMP.elite";
	private final String PASO1_ALERTAS_UPDATE = "/alertas/celular/modifica/acMP.elite";
	private final String PASO1_ALERTAS_UPDATE_2 = "/alertas/celular/modifica/acMEC.elite";
	private final String PASO1_ALERTAS_UPDATE_CONFIRM = "/alertas/celular/modifica/acMMC.elite";
	private final String PASO2_ALERTAS_UPDATE = "/alertas/celular/modifica/acMMC.elite";
	private final String PASO2_ALERTAS_UPDATE_CONFIRM = "/alertas/celular/modifica/acMM.elite";
	private final String PASO3_ALERTAS_UPDATE = "/alertas/celular/modifica/acMMC.elite";
	private final String PASO3_ALERTAS_UPDATE_CONFIRM = "/alertas/celular/modifica/acMM.elite";
	private final String PASO1_ALERTAS_TARJETAS = "/alertas/celular/aperTBA.elite";
	private final String PASO2_ALERTAS_TARJETAS = "/alertas/celular/aperConfTBA.elite";
	private final String PASO3_ALERTAS_TARJETAS = "/alertas/celular/aperConfMenTBA.elite";
	private final String PASO4_ALERTAS_TARJETAS = "/alertas/celular/aperConfPassTBA.elite";

	private final String CARGA_DATOS_UPDATE_CARDS = "/alertas/celular/modificaTBA.elite";
	private final String PASO1_ALERTAS_UPDATE_CARDS = "/alertas/celular/modificaConfTBA.elite";
	private final String PASO2_ALERTAS_UPDATE_CARDS = "/alertas/celular/modificadoTBA.elite";
	private final String PASO3_ALERTAS_UPDATE_CARDS = "/alertas/celular/modifica/acME.elite";
	private final String PASO3_ALERTAS_UPDATE_CARDS_CONFIRM = "/alertas/celular/modifica/acMON.elite";
	private final String ALERTAS_DELETE_CARDS = "/alertas/celular/eliminaConfTBA.elite";

	private final String ALERTAS_DELETE_ACCOUNT = "/alertas/celular/modifica/acMEC.elite";
	private final String ALERTAS_DELETE_ACCOUNT_CONFIRM = "/alertas/celular/modifica/acME.elite";

	private final String CONSULTA_MOVIMIENTOS_INFINITI = "/consultas/nec_movimientosTBACmd.elite";
	private final String CONSULTA_TARJETA_CORPORATIVA_CREDITO = "/tarjetacorporativa/saldoPre.elite";
	private final String CONSULTA_TARJETA_CORPORATIVA_DEBITO = "/debitoCorporativa/inicioPre.elite";

	private final String CUENTAS_FRECUENTES = "/transferencias/agendaCuentasPre.elite";
	private final String CUENTAS_FRECUENTES_HISTORICAS = "/agendaCuentas/obtenerHistoricas.elite";
	private final String INTERNACIONALES_CONFIRMA_CUENTA = "/agendaCuentas/agregaFrecuentePre.elite";

	private final String OTROS_BANCOS_AGREGA_FRECUENTE_PRE="/agendaCuentas/agregaFrecuentePre.elite";
	private final String OTROS_BANCOS_AGREGA_FRECUENTE="/agendaCuentas/agregaFrecuente.elite";
	
	private final String FRECUENTES_TRASPASOS_ELIMINA_CUENTA_PRE = "/agendaCuentas/preRegistro.elite";
	private final String FRECUENTES_TRASPASOS_ELIMINA_CUENTA = "/agendaCuentas/eliminaFrecuentePre.elite";

	private final String FRECUENTES_TRASPASOS_MODIFICA_CUENTA_PRE = "/agendaCuentas/preRegistro.elite";
	private final String FRECUENTES_TRASPASOS_MODIFICA_CUENTA_CONF = "/agendaCuentas/modificaFrecuente.elite";
	private final String FRECUENTES_TRASPASOS_MODIFICA_CUENTA = "/agendaCuentas/modificaFrecuentePre.elite";

	private final String FRECUENTES_TRASPASOS_MODIFICA_CUENTA_EJECUCION_1="/seguridad/redireccion.elite";
	private final String FRECUENTES_TRASPASOS_MODIFICA_CUENTA_EJECUCION_1_1="/agendaCuentas/modificaFrecuentePre.elite";
	
	private final String TERCEROS_ALTA_FRECUENTE_2="/agendaCuentas/agregaFrecuente.elite";
	private final String TERCEROS_ALTA_FRECUENTE_1="/agendaCuentas/preRegistro.elite";
	private final String TERCEROS_ALTA_FRECUENTE_1_1="/agendaCuentas/agregaFrecuente.elite";
	private final String TERCEROS_ALTA_FRECUENTE_1_2="/agendaCuentas/agregaFrecuentePre.elite";
	private final String TERCEROS_ALTA_FRECUENTE_2_2="/agendaCuentas/agregaFrecuentePre.elite";
	
	private final String VALIDA_APERTURA_SOCIO_PLUS_1="/usuarios/cambioDetallesEjecutarMenu.elite";
	private final String VALIDA_APERTURA_SOCIO_PLUS_2="/aperturas/Bienvenida.elite";
	private final String VALIDA_APERTURA_SOCIO_PLUS_3="/aperturas/BienvenidaPre.elite";
	private final String SOCIO_PLUS_1 = "/aperturas/fideicomiso/inicio.elite";
	private final String SOCIO_PLUS_2 = "/aperturas/fideicomiso/aceptaContrato.elite";
	private final String SOCIO_PLUS_3 = "/aperturas/fideicomiso/cartaAdhesion.elite";
	private final String SOCIO_PLUS_3_1 = "/aperturas/fideicomiso/compra.elite";
	private final String SOCIO_PLUS_3_2 = "/aperturas/fideicomiso/compraCmd.elite";
	private final String PDF_SOCIO_PLUS_1 = "/aperturas/fideicomiso/fideicomisoPDF/Prospecto_Fideicomiso.pdf";
	
	private final String CUENTA_SOCIO_INVOCACION = "/aperturas/BienvenidaPre.elite";
	private final String CUENTA_SOCIO_INICIO = "/aperturas/CuentaSocioExternoPre.elite";
	private final String CUENTA_SOCIO_ENVIO_DATOS = "/aperturas/ConfirmacionCtaSocio.elite";
	private final String CUENTA_SOCIO_CONFIRMACION_WAIT = "/aperturas/EjecutarCtaSocio.elite";
	private final String CUENTA_SOCIO_CONFIRMACION = "/aperturas/EjecutarCtaSocioCmd.elite";

	private final String CUENTA_PLATA_INVOCACION_1 = "/aperturaPlata/bienvenida.elite";
	private final String CUENTA_PLATA_INVOCACION_2 = "/aperturaPlata/bienvenidaPre.elite";
	private final String CUENTA_PLATA_CONTRATO_1 = "/aperturaPlata/contratos.elite";
	private final String CUENTA_PLATA_CONTRATO_2 = "/aperturaPlata/begin.elite";
	private final String CUENTA_PLATA_CONTRATO_3 = "/aperturaPlata/beginPre.elite";
	private final String CUENTA_PLATA_ENVIO_DATOS = "/aperturaPlata/complete.elite";
	private final String CUENTA_PLATA_EJECUCION_1 = "/aperturaPlata/validate.elite";
	private final String CUENTA_PLATA_EJECUCION_2 = "/aperturaPlata/validatePre.elite";
	
//	private final String E_RECIBOS_NOMINA = "/snomina/recibos/consultaRecibosNominaPre.elite";
	private final String E_RECIBOS_NOMINA = "/snomina/recibos/consultaNominaRecibosPre.elite";
	private final String E_RECIBOS_NOMINA_INICIO ="/snomina/recibos/consultaNominaRecibosInicio.elite";
	private final String E_RECIBOS_NOMINA_SELECCION ="/snomina/recibos/consultaNominaRecibosSeleccion.elite";
	private final String E_RECIBOS_NOMINA_DETALLE = "/snomina/recibos/consultaNominaRecibosDetallePre.elite";
	private final String E_RECIBOS_NOMINA_EJECUCION = "/snomina/recibos/consultaNominaRecibosLiberaFromConsulta.elite";

	private final String DETALLE_MOVIMIENTOS_OTROS_CREDITOS = "/consultas/detalleCredito.elite";
	
	private final String MEDIOS_PAGO_INVOCACION = "/medios/pagosCmdPre.elite";
	private final String SOLICITUD_DISPOCICION_EFECTIVO_CAJERO = "/cajeros/limiteDisposicionInicioPre.elite";	
	
	private final String VALIDACION_DISPOCICION_EFECTIVO_CAJERO_1 = "/cajeros/contrasena.elite";
	private final String VALIDACION_DISPOCICION_EFECTIVO_CAJERO_2 = "/cajeros/contrasenaPre.elite";
	
	private final String EJECUCION_DISPOCICION_EFECTIVO_CAJERO_1 = "/cajeros/confirmar.elite";
	private final String EJECUCION_DISPOCICION_EFECTIVO_CAJERO_2 = "/cajeros/confirmarPre.elite";
	
	private final String CAMBIO_NIP_INVOCACION = "/cambio_nip/getactualnipCmd.elite";// ?tarjeta="+valor
	private final String CAMBIO_NIP_EJECUCION = "/cambio_nip/nipvalidations.elite";
	private final String CAMBIO_NIP_CONFIRMACION = "/cambio_nip/nipchange.elite";
		
	private final String PREPARAR_INFORMACION_TARJETA_WAIT = "/cancelacion/cancelacion.elite";
	private final String PREPARAR_INFORMACION_TARJETA = "/cancelacion/cancelacionPre.elite";
	private final String BLOQUEAR_TARJETA = "/cancelacion/cancelacionDispatch.elite";

//	TODO ERC cambio socio plus
	private final String ESTADO_CUENTA_1_1 = "/estadocuenta/estadocuentaMenu.elite";
	private final String ESTADO_CUENTA_1_2 = "/estadocuenta/estadocuenta.elite";
	private final String ESTADO_CUENTA_1_3 = "/estadocuenta/estadocuentaCmd.elite";
	private final String ESTADO_CUENTA_2_1 = "/estadocuenta/consultacuenta.elite";
	private final String ESTADO_CUENTA_3_1 = "/estadocuenta/consultacuenta.elite";
	
	private final String FINANZAS_MENU = "/consultas/finanzasMenu.elite";
	private final String MOVIMIENTOS_FECHA = "/consultas/movimientosFecha.elite";
	private final String MOVIMIENTOS_FECHA_PRE = "/consultas/movimientosFechaPre.elite";
	private final String CONSULTA_MIS_FINANZAS = "/consultas/movimientosFechaValidar.elite";
	private final String FINANZAS_GRAFICA = "/consultas/movimientosFechaValidar.elite";
	
	private final String CHEQUERA_PRESOLICITUD_1 = "/cheques/SolicitudPre.elite";
	private final String CHEQUERA_PRESOLICITUD_2 = "/cheques/Solicitud.elite";
	
	private final String PATH_UPLOAD_VAR_SESSION_CHANGE = "/seguridad/middleValue/true.elite";
	private final String PATH_SESSION_REMOVE_PARAMETER_MOVIMIENTO = "/seguridad/sessionRemoveParameter.elite";
	
//Carta de retencion de Socio Plus
	
	private final String CARTA_RETENCION_MENU = "/estadocuenta/cartaInfoRetMenu.elite";
	private final String CARTA_RETENCION_INFORMACION = "/estadocuenta/cartaInfoRet.elite";
	
	//Consulta de movimientos por fecha Socio Plus
	
	private final String CONSULTA_MOVIMIENTOS_FECHA_JSP = "/consultas/movimientosSocPlus.jsp";
	private final String CONSULTA_MOVIMIENTOS_SOCIO_PLUS_FECHA_INI = "/consultas/fideicomiso/movimientosXfechaPlusini.elite";
	private final String CONSULTA_MOVIMIENTOS_SOCIO_PLUS_FECHA_CMD = "/consultas/fideicomiso/movimientosXfechaPlusiniCmd.elite";
	
	//Paths de prueba de movimientos socio plus
	
	private final String CONSULTA_MOVIMIENTOS_FECHAS_NOMINA_GUARDADITO= "/consultas/movimientosXfechaini.elite";
	private final String CONSULTA_MOVIMIENTOS_FECHAS_NOMINA_GUARDADITO_CMD= "/consultas/movimientosXfechainiCmd.elite";
	
		
	public MessageElement findMovimientosByIndex(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		

		try {
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			params.put("sessionAttributeName", "ebanking_web_DataKey");
			connector.sendRequest(PATH_SESSION_REMOVE_PARAMETER_MOVIMIENTO, params);
			params.clear();
			params.put("cuenta", balanceRequestTO.getIndexAcount());
			connector.sendRequest(CONSULTA_MOVIMIENTOS_INIT, params);
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS, params);
			
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public void invokeJspMovimientosByIndex(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException {
		Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
		try {
			connector.sendJSPRequest(CONSULTA_MOVIMIENTOS_JSP, null);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}
		
	public MessageElement findWaitMovimientosByRango(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("fechaIni", balanceRequestTO.getFechaIni());
		params.put("fechaFin", balanceRequestTO.getFechaIni());
		//params.put("rango", balanceRequestTO.getRango());
		params.put("submit", "Consultar");		
		try {
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS_POR_RANGO_WAIT, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findMovimientosByRango(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("fechaIni", balanceRequestTO.getFechaIni());
		params.put("fechaFin", balanceRequestTO.getFechaIni());
		params.put("rango", balanceRequestTO.getRango());
		params.put("submit", "Consultar");	
		try {
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS_POR_RANGO, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
		
	public MessageElement findWaitPartnerPlusBalanceAccount(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("cuenta", balanceRequestTO.getIndexAcount());
		
		try {
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS_SOCIO_PLUS_1, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement findPartnerPlusBalanceAccount(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("cuenta", balanceRequestTO.getIndexAcount());
		
		try {
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS_SOCIO_PLUS_1_1, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	

	public MessageElement listCardsToLockAndUnlock(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();

		try {
			Connector connector = ConnectorManager.getConnector(lockUnlockCardsRequestTO.getUser());
			String xml = connector.sendRequest(LISTA_TARJETAS_BLOQUEO_DES, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement confirmCancelacionDispatch(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("opcion", lockUnlockCardsRequestTO.getOperacion());
		params.put("nombre", lockUnlockCardsRequestTO.getNombre());
		params.put("tarjeta", lockUnlockCardsRequestTO.getNumTarjeta());
		params.put("estado", lockUnlockCardsRequestTO.getEstadoTarjeta());
		params.put("method", "cancelar");
		/*if (lockUnlockCardsRequestTO.getValidacion().length() == 6)
			params.put("tokencode", lockUnlockCardsRequestTO.getValidacion());// token
		else
			params.put("clave", lockUnlockCardsRequestTO.getValidacion());// huella
		*/
		try {
			Connector connector = ConnectorManager.getConnector(lockUnlockCardsRequestTO.getUser());
			String xml = connector.sendRequest(CONFIRMA_BLOQUEO_DISPATCH, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/**
	 * Preparacion de la operaci&oacute;n de bloqueo o desbloqueo por medio del
	 * &iacute;ndice de la tarjeta.
	 * 
	 * @param indexCard
	 * @param operacion
	 * @return cancelacionElementTO en el response
	 * @throws CuentasException
	 * @throws SessionExpiredException
	 * @throws DAOException
	 */
	public MessageElement prepareLockingState(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("opcion", lockUnlockCardsRequestTO.getOperacion());
		params.put("method", "getEstadoTarjeta");
		params.put("tarjeta", String.valueOf(lockUnlockCardsRequestTO.getIndexCard()));
		try {
			Connector connector = ConnectorManager.getConnector(lockUnlockCardsRequestTO.getUser());
			String xml = connector.sendRequest(OPERACION_TARJETAS, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/**
	 * Recupera las tarjetas para activaci&oacite;n de compras por Internet.
	 * 
	 * @return
	 * @throws SessionExpiredException
	 * @throws DAOException
	 * @throws CuentasException
	 * @throws XmlDecodeException
	 */
	public MessageElement getTarjetasComprasInternet(InternetSalesRequestTO internetSalesRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("estado", internetSalesRequestTO.getEstado());
		params.put("opcion", internetSalesRequestTO.getOpcion());
		params.put("indice", internetSalesRequestTO.getIndice());
		
		
		params.put("tarjeta", internetSalesRequestTO.getTarjetaCompraInternet());
		
		
		try {
			Connector connector = ConnectorManager.getConnector(internetSalesRequestTO.getUser());
			String xml = connector.sendRequest(ACTIVAR_TARJETA_COMPRAS_INTERNET, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/**
	 * Recupera esta de una tarjeta que se desea activar para hacer compras por
	 * Internet.
	 * 
	 * @param opcion
	 * @param tarjeta
	 * @return
	 * @throws SessionExpiredException
	 * @throws DAOException
	 * @throws CuentasException
	 */
	public MessageElement getComprasInternetEstadoTarjeta(InternetSalesRequestTO internetSalesRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = new MessageElement();
		Map<String, String> params = new HashMap<String, String>();
		log.info("internetSalesRequestTO.getOpcionCompraInternet() "+internetSalesRequestTO.getOpcionCompraInternet());
		
		params.put("method", "detalle");
		params.put("opcion", internetSalesRequestTO.getOpcionCompraInternet());
		params.put("tarjeta", internetSalesRequestTO.getTarjetaCompraInternet());
		try {
			Connector connector = ConnectorManager.getConnector(internetSalesRequestTO.getUser());
			String xml = connector.sendRequest(GET_ESTADO_TARJETAS_COMPRAS_INTERNET, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/**
	 * Ejecuta las operaciones activaci&oacute;n, desactivaci&oacute;n y cambiar
	 * tarjetas para realizar compras por Internet.
	 * 
	 * @param opcion
	 * @param tokenCode
	 * @return
	 * @throws SessionExpiredException
	 * @throws DAOException
	 * @throws CuentasException
	 */
	public MessageElement setComprasInternetActivacion(InternetSalesRequestTO internetSalesRequestTO) throws SessionExpiredException, DAOException, CuentasException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", internetSalesRequestTO.getOpcionCompraInternet());		
		params.put("monto", internetSalesRequestTO.getMontoOperacion());
		params.put("tokencode", internetSalesRequestTO.getTokenCode());
		
		try {
			Connector connector = ConnectorManager.getConnector(internetSalesRequestTO.getUser());
			String xml = connector.sendRequest(EJECUTAR_ACTIVACION_COMPRAS_INTERNET, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/**
	 * Recupera las tarjetas que seles puede cambiar la sucursal de entrega.
	 * 
	 * @return
	 * @throws SessionExpiredException
	 * @throws DAOException
	 * @throws CuentasException
	 */
	public MessageElement getCambioSucursalInicio(ChangeBranchRequestTO changeBranchRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;
		Map<String, String> params = new WeakHashMap<String, String>();
		params.put("method", changeBranchRequestTO.getOpcionCambioSucursal());
		params.put("indice", changeBranchRequestTO.getIndice());
		try {
			Connector connector = ConnectorManager.getConnector(changeBranchRequestTO.getUser());
			String xml = connector.sendRequest(INICIO_SUCURSAL_TARJETAS, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException uex) {
			throw new DAOException(uex);
		} catch (HttpException httpe) {
			throw new DAOException(httpe);
		} catch (IOException ioe) {
			throw new DAOException(ioe);
		} catch (XmlDecodeException xmle) {
			throw new DAOException(xmle);
		}
		return messageElement;
	}

	/**
	 * Recupera los municipios de un estado, en los cu&aactue;les hay sucursales
	 * del Banco, para realizar el cambio de sucursal para entrega de tarjeta.
	 * 
	 * @param estado
	 * @param user
	 * @return MessageElement TO con municipios
	 * @throws DAOException
	 * @throws SessionExpiredException
	 * @throws CuentasException
	 */
	public MessageElement getMunicipiosEntidad(ChangeBranchRequestTO changeBranchRequestTO) throws DAOException, SessionExpiredException, CuentasException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", changeBranchRequestTO.getOpcionCambioSucursal());
		params.put("busquedaEdo", String.valueOf(changeBranchRequestTO.getEstado()));
		try {
			Connector connector = ConnectorManager.getConnector(changeBranchRequestTO.getUser());
			String xml = connector.sendRequest(BUSCAR_MUNICPIOS_SUCURSAL_TARJETAS, params);
			log.info("XML: " + xml);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException usex) {
			throw new CuentasException(usex);
		} catch (HttpException httpex) {
			throw new CuentasException(httpex);
		} catch (IOException ioex) {
			throw new CuentasException(ioex);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
		return messageElement;
	}
	
	public MessageElement getDatosBusquedaSucursales(ChangeBranchRequestTO changeBranchRequestTO) throws DAOException, SessionExpiredException, CuentasException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", changeBranchRequestTO.getOpcionCambioSucursal());
		params.put("busquedaEdo", String.valueOf(changeBranchRequestTO.getEstado()));
		try {
			Connector connector = ConnectorManager.getConnector(changeBranchRequestTO.getUser());
			String xml = connector.sendRequest(BUSCAR_MUNICPIOS_SUCURSAL_TARJETAS, params);
			log.info("XML: " + xml);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException usex) {
			throw new CuentasException(usex);
		} catch (HttpException httpex) {
			throw new CuentasException(httpex);
		} catch (IOException ioex) {
			throw new CuentasException(ioex);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
		return messageElement;
	}

	/**
	 * Recupera los datos de la tarjeta seleccionada para hacer el cambio de
	 * sucursal para entrega.
	 * 
	 * @return MessageElement con las tarjetas o el error asociado.
	 * @throws SessionExpiredException
	 * @throws DAOException
	 * @throws CuentasException
	 */
	public MessageElement buscarCentrosCambioSucursal(ChangeBranchRequestTO changeBranchRequestTO) throws SessionExpiredException, DAOException, CuentasException {
		MessageElement messageElement = null;

		Map<String, String> params = new WeakHashMap<String, String>();
		params.put("method", changeBranchRequestTO.getOpcionCambioSucursal());
		params.put("tipoValidacion","1");
		if (changeBranchRequestTO.getTipoBusqueda() == 0) {
			params.put("tipoBusqueda", "0");
			params.put("method", "busqueda");
			params.put("busquedaCP", changeBranchRequestTO.getCodigoPostal());
		} else {
			params.put("tipoBusqueda","1");
			
			if(!changeBranchRequestTO.isEsBusquedaMunicipio())
				params.put("method","busqueda");
			else params.put("method", "getentidad");
			
			params.put("busquedaEdo", String.valueOf(changeBranchRequestTO.getEstado()));
			params.put("busquedaDelMuni", changeBranchRequestTO.getMunicipio()+"");
		}
		try {
			Connector connector = ConnectorManager.getConnector(changeBranchRequestTO.getUser());
			String xml = connector.sendRequest(BUSCAR_SUCURSAL_TARJETAS, params);			
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);

		} catch (URISyntaxException usex) {
			throw new CuentasException(usex);
		} catch (HttpException httpex) {
			throw new CuentasException(httpex);
		} catch (IOException ioex) {
			throw new CuentasException(ioex);
		} catch (XmlDecodeException xmle) {
			throw new CuentasException(xmle);
		}
		return messageElement;
	}

	public EliteResponse setCambioSucuarsalTarjetas(String user) throws DAOException, SessionExpiredException {
		Map<String, String> params = new HashMap<String, String>();
		EliteResponse eliteResponse = new EliteResponse();
		try {
			Connector connector = ConnectorManager.getConnector(user);
			String xml = connector.sendRequest(INICIO_SUCURSAL_TARJETAS, params);
			XMLDecode decode = new XMLDecode();
			MessageElement element = decode.buildMessageElement(xml);
			DatosEliteTO datosEliteTO = (DatosEliteTO) decode.buildBean(DatosEliteTO.class, element, "DatosEliteVO");
			if (datosEliteTO.getJspForward().equalsIgnoreCase("/privada/medios/telectronicos/cambio.jsp")) {
				Collection<ClienteTO> data = decode.buildCollectionBeans(ClienteTO.class, element, "ebanking_web_ClienteVO");
			} else {
				Map<String, ?> map = decode.buildMapBeans(decode.buildMessageElement(xml), "org.apache.struts.action.ERROR");
				log.info("----->" + map.get("tarjetas"));
				eliteResponse.setError(true);
				eliteResponse.setResponse(map);
			}
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return eliteResponse;
	}
	
	@SuppressWarnings("unchecked")
	public MessageElement getDatosSucursalTarjeta(String user) throws DAOException, SessionExpiredException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("tipoValidacion", "0");
		params.put("method", "getData");
		MessageElement element=null;
		try {
			Connector connector = ConnectorManager.getConnector(user);
			String xml = connector.sendRequest(MOSTAR_SUCURSAL_TARJETAS, params);
			XMLDecode decode = new XMLDecode();
			element = decode.buildMessageElement(xml);
			
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return element;
	}
	
	public MessageElement getDatosBusquedaSucursales(String user) throws DAOException, SessionExpiredException {
		
		MessageElement element=null;
		try {
			Connector connector = ConnectorManager.getConnector(user);
			String xml = connector.sendRequest(DATOS_BUSQUEDA_SUCURSALES, null);
			XMLDecode decode = new XMLDecode();
			element = decode.buildMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return element;
	}

	public MessageElement confirmaCambioSucursal(ChangeBranchRequestTO branchRequestTO) throws DAOException, SessionExpiredException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("tipoValidacion", "2");
		params.put("method", "confirmacion");
		params.put("indiceResultado", branchRequestTO.getIndiceSucursal());
		if(branchRequestTO.getOpcion_seguridad().equals(TOKEN_DISPOSITIVE+"")){
			params.put("tokencode", branchRequestTO.getTokenCode());
		}else{
			params.put("clave", branchRequestTO.getClave_seguridad());
		}
		
		MessageElement element=null;
		try {
			Connector connector = ConnectorManager.getConnector(branchRequestTO.getUser());
			String xml = connector.sendRequest(CONFIRMACION_SUCURSAL_TARJETAS, params);
			XMLDecode decode = new XMLDecode();
			element=decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}catch(XmlDecodeException decodeException){
			throw new DAOException(decodeException);
		}
		return element;
	}

	public MessageElement findInfiniteMovimientosCuenta(InfiniteMovimientosRequestTO infiniteMovimientosRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("idxAl", infiniteMovimientosRequestTO.getIndexTarjeta());

		try {
			Connector connector = ConnectorManager.getConnector(infiniteMovimientosRequestTO.getUser());
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS_INFINITI, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}

		return messageElement;

	}

	public MessageElement findTarjetaCorporativaCredito(TarjetaCorporativaCreditoRequestTO corporativaCreditoRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;

		try {
			Connector connector = ConnectorManager.getConnector(corporativaCreditoRequestTO.getUser());
			String xml = connector.sendRequest(CONSULTA_TARJETA_CORPORATIVA_CREDITO, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}

		return messageElement;
	}

	public MessageElement findTarjetaCorporativaDebito(TarjetaCorporativaDebitoRequestTO tarjetaCorporativaDebitoRequestTO) throws SessionExpiredException, DAOException {
		MessageElement messageElement = null;

		try {
			Connector connector = ConnectorManager.getConnector(tarjetaCorporativaDebitoRequestTO.getUser());
			String xml = connector.sendRequest(CONSULTA_TARJETA_CORPORATIVA_DEBITO, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}

		return messageElement;
	}

	/**
	 * Alertas por celular
	 * 
	 * 
	 * 
	 */
	public MessageElement findAlertsLoadInitialData(String user) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(user);
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(CARGA_DATOS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/* Activar Cuentas */
	public MessageElement getAlertsAcountsSelectedLink(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;		
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());
			params.put("seleccion", "inicioAP");
			params.put("indice", request.getIndexAcount());

			String xml = connector.sendRequest(LINK_SELECCIONADO, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataAlertFirstStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());
			params.put("seleccion", "agregarAP");
			params.put("txtTELEFONO", request.getPhone());
			params.put("cmbCOMPANIA", request.getCompany());// estaba en 1
			if (request.getDeposito().equalsIgnoreCase("true")) {
				params.put("chkDeposito", "on");
			}
			if(request.getRetiro().equalsIgnoreCase("true")){
				params.put("chkRetiro", "on");
			}
			params.put("txtMinDeposito", request.getMinDeposito());
			params.put("txtMinRetiro", request.getMinCuenta());
			params.put("btnAGREGAR", "Continuar");
			String xml = connector.sendRequest(PASO1_ALERTAS, params);
			
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement confirmAlertFirstStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("seleccion", "inicioAAC");
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(CONFIRM_PASO1_ALERTAS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataAlertSecondStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;		
		Map<String, String> params = new HashMap<String, String>();		
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());

			params.put("seleccion", "siguienteAAC");
			params.put("txtLADA", "");
			params.put("txtTELEFONO", request.getPhone());
			params.put("cmbCOMPANIA", request.getCompany()); // estaba en 1
			params.put("cmbIDCOMPANIA", request.getCmbCompany());// estaba en 1
			params.put("hddCLAVEACTIVACION", request.getClaveActivacionBack());
			params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
			params.put("btnSUBMIT","Continuar");
			String xml = connector.sendRequest(PASO2_ALERTAS, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement confirmAlertSecondStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("seleccion", "inicioAACC");
		try {
			XMLDecode decode = new XMLDecode();
			// TODO Validar sólo cuando sea nivel medio.
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);

			String xml = connector.sendRequest(CONFIRM_PASO2_ALERTAS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataAlertThirdStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		
		try {
			
			Connector connector = ConnectorManager.getConnector(request.getUser());
			Map<String, String> params = new HashMap<String, String>();
			params.put("seleccion", "activarAACC");
			params.put("txtLADA", "");
			params.put("txtTELEFONO", request.getPhone());
			params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
			params.put("cmbCOMPANIA", request.getCompany());
			params.put("cmbIDCOMPANIA", request.getCmbCompany());
			params.put("strCUENTAS", "");
			//validacion del dispositivo de seguridad
			if(request.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
				params.put("tokencode", request.getTokencode());
			}else{
				params.put("clave", request.getClave());
				params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
				params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
				params.put(DigitalFinger.DIGITAL_FINGET_USER, request.getUser());
				params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "acActivaAlertasCelularCuentaForm");
			}
			
			//validacion para "remover" huella
			if(request.isFlagSecurity()){
				validaToken(request);
			}			
			String xml = connector.sendRequest(PASO3_ALERTAS, params);
			messageElement = decode.buildMessageElement(xml);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/* Modificar Cuentas */
	public void setInitialDataForUpdateAlertFirstStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {

		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		try {
			connector.sendJSPRequest(CARGA_DATOS_UPDATE_INICIALES, params);

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}

	public MessageElement getAlertsAcountsSelectedLinkUpdate(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;		
		Map<String, String> params = new HashMap<String, String>();
		try {	
			Connector connector = ConnectorManager.getConnector(request.getUser());
			params.put("seleccion", "inicioMP");
			params.put("indice", request.getIndexAcount());
			String xml = connector.sendRequest(CARGA_DATOS_UPDATE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}

		return messageElement;
	}

	public MessageElement getDataForUpdateAlertFirstStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		XMLDecode decode = new XMLDecode();
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());			
			
			params.put("seleccion", "siguienteMP");
			params.put("txtTELEFONO", request.getPhone());
			params.put("cmbCOMPANIA", request.getCompany());
			
			if (request.getDeposito().equalsIgnoreCase("true")) {
				params.put("chkDeposito", "on");
			}
			if(request.getCuenta().equalsIgnoreCase("true")){
				params.put("chkRetiro", "on");
			}
			params.put("txtMinDeposito", request.getMinDeposito());
			params.put("txtMinRetiro", request.getMinCuenta());
			if(!request.getBtnSUBMIT().equals("")){
				params.put("btnSUBMIT", request.getBtnSUBMIT());
			}
		
			//TODO Validar sólo cuando sea nivel medio.
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PASO1_ALERTAS_UPDATE, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement getDataForUpdateAlertFirstStepInicioMec(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		XMLDecode decode = new XMLDecode();
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());			
			params.put("seleccion", "inicioMEC");
			String xml = connector.sendRequest(PASO1_ALERTAS_UPDATE_2, params);//PASO1_ALERTAS_UPDATE_2
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement getDataForUpdateAlertFirstStepModificar(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		XMLDecode decode = new XMLDecode();
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());			
			
			params.put("seleccion", "siguienteMP");
			params.put("txtTELEFONO", request.getPhone());
			params.put("cmbCOMPANIA", request.getCompany());
			
			if (request.getDeposito().equalsIgnoreCase("true")) {
				params.put("chkDeposito", "on");
			}
			if(request.getCuenta().equalsIgnoreCase("true")){
				params.put("chkRetiro", "on");
			}
			params.put("txtMinDeposito", request.getMinDeposito());
			params.put("txtMinRetiro", request.getMinCuenta());
			params.put("btnSUBMIT", request.getBtnSUBMIT());
			
			String xml = connector.sendRequest(PASO1_ALERTAS_UPDATE, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement confirmDataForUpdateAlertFirstStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();		
		try {			
			Connector connector = ConnectorManager.getConnector(request.getUser());
			// TODO Validar sólo cuando sea nivel medio.
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			params.put("seleccion", "inicioMMC");
			String xml = connector.sendRequest(PASO1_ALERTAS_UPDATE_CONFIRM, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataForUpdateAlertFinalStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());
			params.put("seleccion", "siguienteMMC");
			params.put("txtLADA", "");
			params.put("txtTELEFONO", request.getPhone());
			params.put("cmbCOMPANIA", request.getCompany());
			params.put("hddCLAVEACTIVACION", request.getClaveActivacionBack());
			params.put("chkDEPOSITO", "false");
			params.put("chkRETIRO", "false");
			params.put("txtDEPOSITO", "");
			params.put("txtRETIRO", "");
			params.put("chkCUENTA", "false");
			params.put("cmbIDCOMPANIAS", "");
			params.put("bolPASO", "false");
			params.put("hddLADA", "");
			params.put("hddTELEFONO", "");
			params.put("hddCOMPANIA", "");
			params.put("hddCOMPANIAID", "");
			params.put("hddCUENTA", "");
			params.put("hddTIPO", "");
			params.put("hddIDENTIFICADOR", "");
			params.put("hddchkDEPOSITO", "false");
			params.put("hddchkRETIRO", "false");
			params.put("hddtxtDEPOSITO", "");
			params.put("hddtxtRETIRO", "");
			params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
			//validacion del dispositivo de seguridad
			if(request.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
				params.put("tokencode", request.getTokencode());
			}else{
				params.put("clave", request.getClave());
				params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
				params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
				params.put(DigitalFinger.DIGITAL_FINGET_USER, request.getUser());
				params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "acActivaAlertasCelularCuentaForm");
			}
			
			//validacion para "remover" huella
			if(request.isFlagSecurity()){
				validaToken(request);
			}	
			String xml = connector.sendRequest(PASO2_ALERTAS_UPDATE, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}

		return messageElement;
	}

	public MessageElement confirmDataForUpdateAlertSecondStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();		
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());
			params.put("seleccion", "inicioMM");
			String xml = connector.sendRequest(PASO2_ALERTAS_UPDATE_CONFIRM, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataForUpdateAlertThirdStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("seleccion", "eliminarME");
		params.put("txtCLAVEACTIVACION", "");
		params.put("txtCONFIRMA", "");
		params.put("txtLADA", "");
		params.put("txtTELEFONO", "");
		params.put("cmbCOMPANIA", "");
		params.put("hddCLAVEACTIVACION", "");
		params.put("chkDEPOSITO", "false");
		params.put("chkRETIRO", "false");
		params.put("txtDEPOSITO", "");
		params.put("txtRETIRO", "");
		params.put("chkCUENTA", "false");
		params.put("cmbIDCOMPANIAS", "");
		params.put("hddLADA", "");
		params.put("hddTELEFONO", "");
		params.put("hddCOMPANIA", "");
		params.put("hddCOMPANIAID", "");
		params.put("hddCUENTA", "");
		params.put("hddTIPO", "");
		params.put("hddIDENTIFICADOR", "");
		params.put("hddchkDEPOSITO", "false");
		params.put("hddchkRETIRO", "false");
		params.put("hddtxtDEPOSITO", "");
		params.put("hddtxtRETIRO", "true");
		params.put("bolPASO", "");
		params.put("btnREGRESAR", "Aceptar");
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO3_ALERTAS_UPDATE, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement confirmDataForUpdateAlertThirdStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("seleccion", "inicioMM");
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO3_ALERTAS_UPDATE_CONFIRM, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/** End Modifica* */

	public MessageElement getAlertCardAcountsSelectedLink(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("tarjeta", request.getIndexAcount());
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO1_ALERTAS_TARJETAS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataForActivateCardsFirstStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		String confirma = "on";
		String confirmaAbonos="";
		String confirmaCargos="";
		String confirmaFecha="";
		confirmaAbonos=request.getAbonos().toString();
		confirmaCargos=request.getCargos().toString();
		confirmaFecha=request.getFechaPago().toString();
		params.put("txtTELEFONO", request.getPhone());
		params.put("compania", request.getCompany());//
		params.put("cmbCOMPANIA", request.getCompany());//
		if (confirmaAbonos.compareTo(confirma)==0) {
			params.put("chkAbono", request.getAbonos());
		}
		if(confirmaCargos.compareTo(confirma)==0){
			params.put("chkCargo", request.getCargos());
		}
		if(confirmaFecha.compareTo(confirma)==0){
			params.put("chkFecha", request.getFechaPago());
		}
		params.put("txtMinAbono", request.getMinAbonos());
		params.put("txtMinCargo", request.getMinCargos());
		params.put("btnAGREGAR", request.getBtnSUBMIT());

		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO2_ALERTAS_TARJETAS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataForActivateCardsSecondStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("txtTELEFONO", request.getPhone());
		params.put("compania", request.getCompany());//
		params.put("cmbCOMPANIA", request.getCompany());//
		params.put("chkAbono", request.getAbonos());
		params.put("chkCargo", request.getCargos());
		params.put("chkFecha", request.getFechaPago());
		params.put("txtMinAbono", request.getMinAbonos());
		params.put("txtMinCargo", request.getMinCargos());
		params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
		params.put("btnSUBMIT", request.getBtnSUBMIT());
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO3_ALERTAS_TARJETAS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataForActivateCardsThirdStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("txtTELEFONO", request.getPhone());
		params.put("compania", request.getCompany());//
		params.put("cmbCOMPANIA", request.getCompany());//
		params.put("chkAbono", request.getAbonos());
		params.put("chkCargo", request.getCargos());
		params.put("chkFecha", request.getFechaPago());
		params.put("txtMinAbono", request.getMinAbonos());
		params.put("txtMinCargo", request.getMinCargos());
		params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
		params.put("tokencode", request.getTokencode());
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO4_ALERTAS_TARJETAS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/** Modificar Tarjetas* */
	// Click al seleccionar el link de actualizar tarjetas y carga paso 1
	public MessageElement getAlertsCardSelectedLinkUpdate(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("tarjeta", request.getIndexAcount());
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(CARGA_DATOS_UPDATE_CARDS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	// Carga paso 2 con los parametros que se le enviaron en el paso 1
	public MessageElement getDataForModifyCardsFirstStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		boolean resultado;
		String confirma = "on";
		String confirmaAbonos="";
		String confirmaCargos="";
		String confirmaFecha="";
		params.put("txtTELEFONO", request.getPhone());
		params.put("cmbCOMPANIA", request.getCompany()); //
		params.put("compania", request.getCompany()); //
		log.info("toString-----------------------" + request.getAbonos().toString());
		confirmaAbonos = request.getAbonos().toString();
		confirmaCargos = request.getCargos().toString();
		confirmaFecha = request.getFechaPago().toString();

		log.info("--------Confirma Abonos " + confirmaAbonos);
		log.info("--------Confirma Cargos " + confirmaCargos);
		log.info("--------Confirma Fecha " + confirmaFecha);

		if (confirmaAbonos.compareTo(confirma) == 0) {
			params.put("chkAbono", request.getAbonos());
		}
		if (confirmaCargos.compareTo(confirma) == 0) {
			params.put("chkCargo", request.getCargos());
		}
		if (confirmaFecha.compareTo(confirma) == 0) {
			params.put("chkFecha", request.getFechaPago());
		}

		params.put("txtMinAbono", request.getMinAbonos());
		params.put("txtMinCargo", request.getMinCargos());

		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO1_ALERTAS_UPDATE_CARDS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	// carga paso 3 con los parametros que se le enviaron en el paso 2
	public MessageElement getDataForModifyCardsSecondStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
		params.put("tokencode", request.getTokencode());
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO2_ALERTAS_UPDATE_CARDS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getDataModifyCardsThirdStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("seleccion", "eliminarME");
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO3_ALERTAS_UPDATE_CARDS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement confirmModifyCardsThirdStep(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("seleccion", "inicioMON");
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(PASO3_ALERTAS_UPDATE_CARDS_CONFIRM, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/** Eliminar alerta tarjetas* */

	public MessageElement getDataForDeleteCards(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(request.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
		params.put("tokencode", request.getTokencode());
		try {
			XMLDecode decode = new XMLDecode();
			String xml = connector.sendRequest(ALERTAS_DELETE_CARDS, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	/** Elimina cuentas* */
	public MessageElement getDataForDeleteAccounts(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;		
		Map<String, String> params = new HashMap<String, String>();
		XMLDecode decode = new XMLDecode();
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());
			params.put("seleccion", "siguienteMEC");
			params.put("txtLADA", "");
			params.put("txtTELEFONO", "");
			params.put("cmbCOMPANIA", request.getCompany());
			params.put("hddCLAVEACTIVACION", request.getClaveActivacionBack());
			params.put("chkDEPOSITO", "false");
			params.put("chkRETIRO", "false");
			params.put("txtDEPOSITO", "");
			params.put("txtRETIRO", "");		
			params.put("chkCUENTA", "false");
			params.put("cmbIDCOMPANIAS", "");
			params.put("bolPASO", "false");		
			params.put("hddLADA", "");
			params.put("hddTELEFONO", "");
			params.put("hddCOMPANIA", "");
			params.put("hddCOMPANIAID", "");
			params.put("hddCUENTA", "");
			params.put("hddTIPO", "");
			params.put("hddIDENTIFICADOR", "");
			params.put("hddchkDEPOSITO", "false");
			params.put("hddchkRETIRO", "false");
			params.put("hddtxtDEPOSITO", "");
			params.put("hddtxtRETIRO", "");
			params.put("txtCLAVEACTIVACION", request.getClaveActivacion());
			//validacion del dispositivo de seguridad
			if(request.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
				params.put("tokencode", request.getTokencode());
			}else{
				params.put("clave", request.getClave());
				params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
				params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
				params.put(DigitalFinger.DIGITAL_FINGET_USER, request.getUser());
				params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "acActivaAlertasCelularCuentaForm");
			}
			
			//validacion para "remover" huella
			if(request.isFlagSecurity()){
				validaToken(request);
			}
			
			String xml = connector.sendRequest(ALERTAS_DELETE_ACCOUNT, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement confirmDeleteAccounts(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		Map<String, String> params = new HashMap<String, String>();		
		try {
			Connector connector = ConnectorManager.getConnector(request.getUser());
			params.put("seleccion", "inicioME");
			String xml = connector.sendRequest(ALERTAS_DELETE_ACCOUNT_CONFIRM, params);
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	
	public MessageElement findCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
		Map<String, String> params = new HashMap<String, String>();
		params.put("tipo", cuentasFrecuentesRequestTO.getType());
		try {		
			String xml = connector.sendRequest(CUENTAS_FRECUENTES, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	public MessageElement findHistoricasCuentasFrecuentes(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());		
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_HISTORICAS, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
			
	/**
	 * Dao para la invocacion del paso de solicitud agregar las frecuentes de SPEI.
	 * 
	 * @param cuentasFrecuentesRequestTO
	 * @return
	 * @throws DAOException
	 * @throws SessionExpiredException
	 */
	public MessageElement setIntenationalesPreparacionAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "preparacion");
		params.put("origenRegistro", "pagFrecuentes");
//		params.put("submit", "Agregar nuevo contacto");
		
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
//			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest("/agendaCuentas/preRegistro.elite", params);
			messageElement = XMLDecode.buildXMLMessageElement(xml); 
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement setIntenationalesDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		params.put("tipo", cuentasFrecuentesRequestTO.getType());
		params.put("beneficiario",cuentasFrecuentesRequestTO.getBeneficiario());								   
		params.put("destino", cuentasFrecuentesRequestTO.getCuenta());
		params.put("claveBanco", cuentasFrecuentesRequestTO.getClaveBanco());
		params.put("action", "Agregar");
		
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(INTERNACIONALES_CONFIRMA_CUENTA, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	
	public MessageElement setOtrosBancosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
//		params.put("tipo", cuentasFrecuentesRequestTO.getType());
		params.put("beneficiario",cuentasFrecuentesRequestTO.getBeneficiario());
		params.put("apodo", cuentasFrecuentesRequestTO.getAlias());
		params.put("destino", cuentasFrecuentesRequestTO.getCuenta());
		params.put("bank", cuentasFrecuentesRequestTO.getBanco());
		params.put("email", cuentasFrecuentesRequestTO.getEmail());
		params.put("celular", cuentasFrecuentesRequestTO.getCelular());
		params.put("carrier", cuentasFrecuentesRequestTO.getCompania());
		params.put("telCasa", cuentasFrecuentesRequestTO.getTelCasa());
		params.put("telOficina", cuentasFrecuentesRequestTO.getTelOficina());
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequestPost(OTROS_BANCOS_AGREGA_FRECUENTE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
//	public MessageElement setOtrosBancosDatosAgregarCuentaPre(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
//		MessageElement messageElement = null;
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("method", "confirmar");
//				
//		try {
//			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
//			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
//			String xml = connector.sendRequest(OTROS_BANCOS_AGREGA_FRECUENTE_PRE, params);
//			messageElement = XMLDecode.buildXMLMessageElement(xml);
//		} catch (URISyntaxException e) {
//			throw new DAOException(e);
//		} catch (HttpException e) {
//			throw new DAOException(e);
//		} catch (IOException e) {
//			throw new DAOException(e);
//		} catch (XmlDecodeException e) {
//			throw new DAOException(e);
//		}
//		return messageElement;
//	}
	
	
	public MessageElement setOtrosBancosDatosAgregarCuentaPre(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(OTROS_BANCOS_AGREGA_FRECUENTE_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	

	
	public MessageElement setOtrosBancosGuardaAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "registra");		
	
		if(cuentasFrecuentesRequestTO.getOptionDispositive() == null){
			params.put("tokencode", cuentasFrecuentesRequestTO.getTokencode());
		}else if(cuentasFrecuentesRequestTO.getOptionDispositive().equalsIgnoreCase(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", cuentasFrecuentesRequestTO.getTokencode());
		}else if(cuentasFrecuentesRequestTO.getOptionDispositive().equalsIgnoreCase(String.valueOf(FINGER_DISPOSITIVE))){
			params.put("clave", cuentasFrecuentesRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, cuentasFrecuentesRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "traspasosTercerosForm");
		}else{
			throw new DAOException("Dispositivo no valido: "+cuentasFrecuentesRequestTO.getTokencode());
		}
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(OTROS_BANCOS_AGREGA_FRECUENTE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement setOtrosBancosGuardaAgregarCuentaPre(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "registra");		
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(OTROS_BANCOS_AGREGA_FRECUENTE_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	

	/**
	 * DAO de la solicitud para alta de frecuentes tranferencias tercero
	 * Este DAO se ejecuta desde el paso de Validacion por que el comando consta
	 * solo de dos pasos logicos.
	 * @param user
	 * @return
	 * @throws DAOException
	 * @throws SessionExpiredException
	 */
	public MessageElement setTercerosPreparacionAltaFrecuente(String user) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "preparacion");
		params.put("origenRegistro", "pagFrecuentes");
				
		try {
			Connector connector = ConnectorManager.getConnector(user);
			String xml = connector.sendRequest(TERCEROS_ALTA_FRECUENTE_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	

	/**
	 * DAO del metodo de validacion de alta de frecuentes terceros.
	 * Primer metodo.
	 * @param cuentasFrecuentesRequestTO
	 * @return
	 * @throws DAOException
	 * @throws SessionExpiredException
	 */
	public MessageElement setTercerosDatosAgregarCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		System.out.println("En terceros");
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		params.put("apodo",cuentasFrecuentesRequestTO.getAlias());								   
		params.put("destino", cuentasFrecuentesRequestTO.getCuenta());	
		params.put("email", cuentasFrecuentesRequestTO.getEmail());
		params.put("celular", cuentasFrecuentesRequestTO.getCelular());
		params.put("carrier", cuentasFrecuentesRequestTO.getCompania());
		params.put("telCasa", cuentasFrecuentesRequestTO.getTelCasa());
		params.put("telOficina", cuentasFrecuentesRequestTO.getTelOficina());
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(TERCEROS_ALTA_FRECUENTE_1_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	/**
	 * DAO para el metodo de validacion de alta de frecuentes terceros. 
	 * Segundo metodo.
	 * @param cuentasFrecuentesRequestTO
	 * @return
	 * @throws DAOException
	 * @throws SessionExpiredException
	 */
	public MessageElement setTercerosDatosAgregarCuentaPre(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");							   
	
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(TERCEROS_ALTA_FRECUENTE_1_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	/**
	 * DAO que se ejecuta para el metodo de ejecucion.
	 * Primer Metodo.
	 * @param cuentasFrecuentesRequestTO
	 * @return
	 * @throws DAOException
	 * @throws SessionExpiredException
	 */
	public MessageElement setTercerosConfirmaAltaFrecuente(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "registra");		
	
		if(cuentasFrecuentesRequestTO.getOptionDispositive() == null){
			params.put("tokencode", cuentasFrecuentesRequestTO.getTokencode());
		}else if(cuentasFrecuentesRequestTO.getOptionDispositive().equalsIgnoreCase(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", cuentasFrecuentesRequestTO.getTokencode());
		}else if(cuentasFrecuentesRequestTO.getOptionDispositive().equalsIgnoreCase(String.valueOf(FINGER_DISPOSITIVE))){
			params.put("clave", cuentasFrecuentesRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, cuentasFrecuentesRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "traspasosTercerosForm");
		}else{
			throw new DAOException("Dispositivo no valido: "+cuentasFrecuentesRequestTO.getTokencode());
		}
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(TERCEROS_ALTA_FRECUENTE_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	/**
	 * DAO para el metodo de la ejecucion de alta de frecuentes de transferencias terceros.
	 * Metodo segundo.
	 * @param cuentasFrecuentesRequestTO
	 * @return
	 * @throws DAOException
	 * @throws SessionExpiredException
	 */
	public MessageElement setTercerosConfirmaAltaFrecuentePre(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "registra");		
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(TERCEROS_ALTA_FRECUENTE_2_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	
	
	public MessageElement setIntenationalesDatosEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "eliminarConfirmacion");
		params.put("origenRegistro", "pagFrecuentes");
		params.put("idxDestino", cuentasFrecuentesRequestTO.getIndex());
		params.put("tipo", cuentasFrecuentesRequestTO.getType());
		
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(FRECUENTES_TRASPASOS_ELIMINA_CUENTA_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	public MessageElement setIntenationalesEliminaCuenta(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", cuentasFrecuentesRequestTO.getType());
		try {
			Connector connector = ConnectorManager
					.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(
					FRECUENTES_TRASPASOS_ELIMINA_CUENTA, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement getModificaDatosCuentaFrecuentesTraspasos(
			CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)
			throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "preparaModificacionFrecuentes");
		params.put("origenRegistro", "pagFrecuentes");
		params.put("idxDestino", cuentasFrecuentesRequestTO.getIndex());

		try {
			Connector connector = ConnectorManager
					.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(
					FRECUENTES_TRASPASOS_MODIFICA_CUENTA_PRE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement setModificaFrecuenteTraspasosPre(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO/*, Map<String, String> params*/) throws DAOException{
		MessageElement messageElement=null;
		Connector connector=ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
		
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("method", "preparaModificacionFrecuentes");
		params.put("progenRegistro", "pagFrecuentes");
		params.put("idxDestino", cuentasFrecuentesRequestTO.getIndex());
		
		try {
			String xml=connector.sendRequest("/agendaCuentas/preRegistro.elite", params);
			
			messageElement=XMLDecode.buildXMLMessageElement(xml);
			
			
		} catch (SessionExpiredException e) {
			throw new DAOException(e);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	

	
	public MessageElement setModificaDatosCuentaFrecuentesTraspasosConf(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO, Map<String, String> params) throws DAOException,	SessionExpiredException {
		MessageElement messageElement = null;

		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(FRECUENTES_TRASPASOS_MODIFICA_CUENTA_CONF, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	public MessageElement setModificaDatosCuentaFrecuentesTraspasosConf01(CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO) throws DAOException,	SessionExpiredException {
		MessageElement messageElement = null;
		HashMap<String, String> params=new HashMap<String, String>();
		
		params.put("method", "confirmar");
		
		try {
			Connector connector = ConnectorManager.getConnector(cuentasFrecuentesRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			connector.sendRequest(FRECUENTES_TRASPASOS_MODIFICA_CUENTA_EJECUCION_1, null);
			String xml = connector.sendRequest(FRECUENTES_TRASPASOS_MODIFICA_CUENTA_EJECUCION_1_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	public MessageElement setGuardaModificaCuentaFrecuentesTraspasos(
			CuentasFrecuentesRequestTO cuentasFrecuentesRequestTO)
			throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "modifica");
		params.put("destino", cuentasFrecuentesRequestTO.getCuenta());
		
		if(cuentasFrecuentesRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", cuentasFrecuentesRequestTO.getTokencode());
		}else{
			params.put("clave", cuentasFrecuentesRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, cuentasFrecuentesRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "traspasosTercerosForm");
		}

		try {
			Connector connector = ConnectorManager
					.getConnector(cuentasFrecuentesRequestTO.getUser());
			String xml = connector.sendRequest(
					FRECUENTES_TRASPASOS_MODIFICA_CUENTA, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}

	public MessageElement setPartnerPlusStartOpenAccount(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "Abre aquí tu Cuenta Socio Plus");
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioPlusRequestTO.getUser());
			String xml = connector.sendRequestPost(SOCIO_PLUS_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement setPartnerPlusOpenAccount (CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("acepta", cuentaSocioPlusRequestTO.getAceptar() );
		params.put("aceptaContrato", "Continuar");
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioPlusRequestTO.getUser());
			String xml = connector.sendRequestPost(SOCIO_PLUS_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement setPartnerPlusBeneficiary (CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Collection<ConnectorDataTO> params= new ArrayList<ConnectorDataTO>();
		
		params.add(new ConnectorDataTO("montoInvertir",cuentaSocioPlusRequestTO.getMontoInvertir()));
		params.add(new ConnectorDataTO("continuar","Continuar"));
		params.add(new ConnectorDataTO("origen",cuentaSocioPlusRequestTO.getCuentaOrigen()));
		params.addAll(cuentaSocioPlusRequestTO.getCadena());
			
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioPlusRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendMultiKeyRequest(SOCIO_PLUS_3, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement setPartnerPlusWaitConfirmData (CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("montoInvertir", cuentaSocioPlusRequestTO.getMontoInvertir());
		params.put("continuar", "Continuar");
		params.put("origen", cuentaSocioPlusRequestTO.getCuentaOrigen());
		params.put("acepta", "on");		
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioPlusRequestTO.getUser());
			String xml = connector.sendRequestPost(SOCIO_PLUS_3_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement setPartnerPlusConfirmData(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("acepta", "on");
		log.info("option dispositive "+cuentaSocioPlusRequestTO.getOptionDispositive());
		if(cuentaSocioPlusRequestTO.getOptionDispositive()==TOKEN_DISPOSITIVE){
			params.put("tokencode", cuentaSocioPlusRequestTO.getTokenCode());
		}else{
			params.put("clave", cuentaSocioPlusRequestTO.getClave());
//			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
//			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
//			params.put(DigitalFinger.DIGITAL_FINGET_USER, cuentaSocioPlusRequestTO.getUser());
//			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "compraForm");
		}
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioPlusRequestTO.getUser());
			String xml = connector.sendRequestPost(SOCIO_PLUS_3_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public String getProspectoFideicomisoPDF(CuentaSocioPlusRequestTO cuentaSocioPlusRequestTO) throws DAOException, SessionExpiredException {
		String stream = null;
		
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioPlusRequestTO.getUser());
			stream = connector.sendServletRequest(PDF_SOCIO_PLUS_1, null);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
		return stream;
	}
	
	public MessageElement findCuentaSocioInvocacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_SOCIO_INVOCACION, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaSocioInicio(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(cuentaSocioRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_SOCIO_INICIO, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaSocioEnvioDatos(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("cuentaOrigen", cuentaSocioRequestTO.getCuentaOrigen());
		params.put("monto", cuentaSocioRequestTO.getMonto());
		params.put("tasaBrutaAnual", cuentaSocioRequestTO.getTasaBrutaAnual());
		params.put("referencia", cuentaSocioRequestTO.getReferencia());
		params.put("nombreBeneficiario1", cuentaSocioRequestTO.getNombreBeneficiario1());
		params.put("apBeneficiario1", cuentaSocioRequestTO.getApBeneficiario1());
		params.put("amBeneficiario1", cuentaSocioRequestTO.getAmBeneficiario1());
		params.put("porcentaje1", cuentaSocioRequestTO.getPorcentaje1());
		params.put("nombreBeneficiario2", cuentaSocioRequestTO.getNombreBeneficiario2());
		params.put("apBeneficiario2", cuentaSocioRequestTO.getApBeneficiario2());
		params.put("amBeneficiario2", cuentaSocioRequestTO.getAmBeneficiario2());
		params.put("porcentaje2", cuentaSocioRequestTO.getPorcentaje2());
		params.put("nombreBeneficiario3", cuentaSocioRequestTO.getNombreBeneficiario3());
		params.put("apBeneficiario3", cuentaSocioRequestTO.getApBeneficiario3());
		params.put("amBeneficiario3", cuentaSocioRequestTO.getAmBeneficiario3());
		params.put("porcentaje3", cuentaSocioRequestTO.getPorcentaje3());
		params.put("nombreBeneficiario4", cuentaSocioRequestTO.getNombreBeneficiario4());
		params.put("apBeneficiario4", cuentaSocioRequestTO.getApBeneficiario4());
		params.put("amBeneficiario4", cuentaSocioRequestTO.getAmBeneficiario4());
		params.put("porcentaje4", cuentaSocioRequestTO.getPorcentaje4());
		params.put("nip", cuentaSocioRequestTO.getNip());
		params.put("nipc", cuentaSocioRequestTO.getNipc());
		params.put("submit", "Continuar");
				
		Connector connector = ConnectorManager.getConnector(cuentaSocioRequestTO.getUser());
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);	
			String xml = connector.sendRequest(CUENTA_SOCIO_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaSocioConfirmacionWait(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		//validacion del dispositivo de seguridad
		if(cuentaSocioRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", cuentaSocioRequestTO.getTokencode());
		}else{
			params.put("clave", cuentaSocioRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, cuentaSocioRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "CtaSocioForm");
		}	
		
		Connector connector = ConnectorManager.getConnector(cuentaSocioRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTA_SOCIO_CONFIRMACION_WAIT, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaSocioConfirmacion(AperturaCuentaSocioRequestTO cuentaSocioRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		//validacion del dispositivo de seguridad
		if(cuentaSocioRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", cuentaSocioRequestTO.getTokencode());
		}else{
			params.put("clave", cuentaSocioRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, cuentaSocioRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "CtaSocioForm");
		}	
		
		Connector connector = ConnectorManager.getConnector(cuentaSocioRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTA_SOCIO_CONFIRMACION, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}

	public MessageElement findCuentaPlataInvocacion(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_PLATA_INVOCACION_1, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataInicio(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_PLATA_INVOCACION_2, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataContratosInvocacion(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_PLATA_CONTRATO_1, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataContratosWait(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("aceptarContrato", cuentaPlataRequestTO.getAceptarContrato());
		params.put("aceptar", cuentaPlataRequestTO.getAceptar());
		
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_PLATA_CONTRATO_2, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataContratos(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_PLATA_CONTRATO_3, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataEnvioDatos(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("util", cuentaPlataRequestTO.getUtil());
		params.put("cuentaCargo", cuentaPlataRequestTO.getCuentaCargo());
		params.put("montoMonedas", cuentaPlataRequestTO.getMontoMonedas());
		params.put("nombresBenUno", cuentaPlataRequestTO.getNombresBenUno());
		params.put("paternoBenUno", cuentaPlataRequestTO.getPaternoBenUno());
		params.put("maternoBenUno", cuentaPlataRequestTO.getMaternoBenUno());
		params.put("porcentajeBenUno", cuentaPlataRequestTO.getPorcentajeBenUno());
		params.put("nombresBenDos", cuentaPlataRequestTO.getNombresBenDos());
		params.put("paternoBenDos", cuentaPlataRequestTO.getPaternoBenDos());
		params.put("maternoBenDos", cuentaPlataRequestTO.getMaternoBenDos());
		params.put("porcentajeBenDos", cuentaPlataRequestTO.getPorcentajeBenDos());
		params.put("nombresBenTres", cuentaPlataRequestTO.getNombresBenTres());
		params.put("paternoBenTres", cuentaPlataRequestTO.getPaternoBenTres());
		params.put("maternoBenTres", cuentaPlataRequestTO.getMaternoBenTres());
		params.put("porcentajeBenTres", cuentaPlataRequestTO.getPorcentajeBenTres());
		params.put("nombresBenCuatro", cuentaPlataRequestTO.getNombresBenCuatro());
		params.put("paternoBenCuatro", cuentaPlataRequestTO.getPaternoBenCuatro());
		params.put("maternoBenCuatro", cuentaPlataRequestTO.getMaternoBenCuatro());
		params.put("porcentajeBenCuatro", cuentaPlataRequestTO.getPorcentajeBenCuatro());
		params.put("nipPlata", cuentaPlataRequestTO.getNipPlata());
		params.put("nipPlataAgain", cuentaPlataRequestTO.getNipPlataAgain());
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);	
			String xml = connector.sendRequest(CUENTA_PLATA_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataConfirmacionEnvioDatos(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("util", cuentaPlataRequestTO.getUtilConfirmacion());
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);	
			String xml = connector.sendRequest(CUENTA_PLATA_ENVIO_DATOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataEjecucionWait(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("util", "");
		//validacion del dispositivo de seguridad
		if(cuentaPlataRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", cuentaPlataRequestTO.getTokencode());
		}else{
			params.put("clave", cuentaPlataRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, cuentaPlataRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "aperturaPlataForm");
		}	
				
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_PLATA_EJECUCION_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement findCuentaPlataEjecucion(AperturaCuentaPlataRequestTO cuentaPlataRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(cuentaPlataRequestTO.getUser());
			String xml = connector.sendRequest(CUENTA_PLATA_EJECUCION_2, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement liberaERecibosNominaPreInit(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try {
			Connector connector = ConnectorManager.getConnector(eRecibosNominaRequestTO.getUser());
			String xml=connector.sendRequest(E_RECIBOS_NOMINA, null);
			
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement liberaERecibosNominaInit(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		String xml="";
		params.put("method", eRecibosNominaRequestTO.getMethod());
		try {
			Connector connector = ConnectorManager.getConnector(eRecibosNominaRequestTO.getUser());
			xml=connector.sendRequest(E_RECIBOS_NOMINA_INICIO, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement liberaERecibosNominaSeleccion(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		String xml="";
		params.put("method", eRecibosNominaRequestTO.getMethod());
		try {
			Connector connector = ConnectorManager.getConnector(eRecibosNominaRequestTO.getUser());
			if(!eRecibosNominaRequestTO.isFlagEjecucion()){
				connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			}			
			xml=connector.sendRequest(E_RECIBOS_NOMINA_SELECCION, params);		
			
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	public MessageElement liberaERecibosNominaDetalle(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", eRecibosNominaRequestTO.getMethod());
		params.put("llave", eRecibosNominaRequestTO.getLlave());
//		params.put("leyenda", eRecibosNominaRequestTO.getLeyenda());
		params.put("sessionFormName", "consultaRecibosNominaForm");
		params.put("sessionAttributeName", "llave");
		params.put("sessionAttributeValue", eRecibosNominaRequestTO.getLlave()); 
		try {
			Connector connector = ConnectorManager.getConnector(eRecibosNominaRequestTO.getUser());
			//TODO Validar sólo cuando sea nivel medio.
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			//TODO Validar si se encriptará esta parte.
			connector.sendRequest(SET_SESSION_FORM_PARAMETER, params);
			String xml = connector.sendRequest(E_RECIBOS_NOMINA_DETALLE, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	public MessageElement liberaERecibosNominaEjecutar(ERecibosNominaRequestTO eRecibosNominaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		try {
			Connector connector = ConnectorManager.getConnector(eRecibosNominaRequestTO.getUser());
			params.put( "method", eRecibosNominaRequestTO.getMethod() );
			params.put( "paso", "1" );
			params.put( "coleccionLlaves", eRecibosNominaRequestTO.getColeccionLlaves() );
			params.put( "Image21.x", "25" );
			params.put( "Image21.y", "15" );
			if(eRecibosNominaRequestTO.getOptionDispositive().equals( "" + TOKEN_DISPOSITIVE ) ){
				params.put( "tokencode", eRecibosNominaRequestTO.getTokenCode() );
			}else{
				params.put( "clave", eRecibosNominaRequestTO.getClave() );
				params.put( DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET );
				params.put( DigitalFinger.DIGITAL_FINGET_VALUE, "clave" );
				params.put( DigitalFinger.DIGITAL_FINGET_USER, eRecibosNominaRequestTO.getUser() );
				params.put( DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm" );
			}
			
			if(eRecibosNominaRequestTO.isFlagEjecucion()){
				connector.sendRequest(PATH_UPLOAD_VAR_SESSION_CHANGE, null);
			}
			
			String xml = connector.sendRequest( E_RECIBOS_NOMINA_EJECUCION, params );
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
// public MessageElement liberaERecibosNominaEjecutar(ERecibosNominaRequestTO
// eRecibosNominaRequestTO) throws DAOException, SessionExpiredException {
// MessageElement messageElement = null;
// Map<String, String> params = new HashMap<String, String>();
// Map<String, String> secLevelParams = new HashMap<String, String>();
// StringBuffer completeEmpNumber = null;
// int difLenghtEmpNumber = 0;
// try {
// Connector connector =
// ConnectorManager.getConnector(eRecibosNominaRequestTO.getUser());
// if(eRecibosNominaRequestTO.getMethod().equals("liberar")) {
// params.put("sessionFormName", "consultaRecibosNominaForm");
// params.put("sessionAttributeName", "llave");
// params.put("sessionAttributeValue", eRecibosNominaRequestTO.getLlave());
// connector.sendRequest(SET_SESSION_FORM_PARAMETER, params);
//				
// params = new HashMap<String, String>();
// params.put("sessionFormName", "consultaRecibosNominaForm");
// params.put("sessionAttributeName", "coleccionLlaves");
// params.put("sessionAttributeValue",
// eRecibosNominaRequestTO.getColeccionLlaves());
// connector.sendRequest(SET_SESSION_FORM_PARAMETER, params);
//					
// params = new HashMap<String, String>();
// } else {
// params.put("llave",eRecibosNominaRequestTO.getLlave() );
// params.put("coleccionLlaves",eRecibosNominaRequestTO.getColeccionLlaves());
// }
// params.put("method", eRecibosNominaRequestTO.getMethod());
// difLenghtEmpNumber = 8 - eRecibosNominaRequestTO.getNumEmpleado().length();
// if(difLenghtEmpNumber > 0) {
// log.debug("Incomplete Employee Number, size: " +
// eRecibosNominaRequestTO.getNumEmpleado().length());
// completeEmpNumber = new
// StringBuffer(eRecibosNominaRequestTO.getNumEmpleado());
// for (int i = 0; i < difLenghtEmpNumber; i++) {
// completeEmpNumber.insert(0, "0");
// }
// } else {
// completeEmpNumber = new
// StringBuffer(eRecibosNominaRequestTO.getNumEmpleado());
// }
// log.info("Complete Employee Number: " + completeEmpNumber.toString());
// params.put("numEmpleado", completeEmpNumber.toString());
// if(eRecibosNominaRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
// secLevelParams.put("sessionAttributeName", "valormedioToken");
// secLevelParams.put("sessionAttributeValue", "true");
// secLevelParams.put("sessionAttributeType", "java.lang.Boolean");
// connector.sendRequest(SET_SESSION_PARAMETER, secLevelParams);
//				
// params.put("tokencode", eRecibosNominaRequestTO.getTokenCode());
// }else{
// connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
// params.put("clave", eRecibosNominaRequestTO.getClave());
// params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
// params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
// params.put(DigitalFinger.DIGITAL_FINGET_USER,
// eRecibosNominaRequestTO.getUser());
// params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "consultaRecibosNominaForm");
// }
// String xml = connector.sendRequest(E_RECIBOS_NOMINA, params);
// messageElement = XMLDecode.buildXMLMessageElement(xml);
//
// } catch (URISyntaxException e) {
// throw new DAOException(e);
// } catch (HttpException e) {
// throw new DAOException(e);
// } catch (IOException e) {
// throw new DAOException(e);
// } catch (XmlDecodeException e) {
// throw new DAOException(e);
// }
// return messageElement;
// }
	
	public MessageElement getDetailOtherCredits(CreditosRequestTO creditosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", creditosRequestTO.getMethod());
		params.put("credito", creditosRequestTO.getCredito());
		try {
			Connector connector = ConnectorManager.getConnector(creditosRequestTO.getUser());
			String xml = connector.sendRequest(DETALLE_MOVIMIENTOS_OTROS_CREDITOS, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}	
	
	public MessageElement getMediosPagoInvocation(CambioNipRequestTO cambioNipRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
				
		try{
			Connector connector =  ConnectorManager.getConnector(cambioNipRequestTO.getUser());
			String xml = connector.sendRequest(MEDIOS_PAGO_INVOCACION, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
	
	public MessageElement getSolicitudDispocicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String>params=new HashMap<String, String>();
		try{
			Connector connector =  ConnectorManager.getConnector(cajeroRequestTO.getUserName());
			params.put("indice",cajeroRequestTO.getIndiceTarjeta()+"");
			String xml = connector.sendRequest(SOLICITUD_DISPOCICION_EFECTIVO_CAJERO, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
	public MessageElement getValidacionDispocicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String>params=new HashMap<String, String>();
		try{
			Connector connector =  ConnectorManager.getConnector(cajeroRequestTO.getUserName());
			params.put("monto",cajeroRequestTO.getMonto()+"");
			connector.sendRequest(VALIDACION_DISPOCICION_EFECTIVO_CAJERO_1, params);
			String xml = connector.sendRequest(VALIDACION_DISPOCICION_EFECTIVO_CAJERO_2,null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
	public MessageElement getEjecucionDispocicionEfectivoCajero(DisposicionEfectivoCajeroRequestTO cajeroRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String>params=new HashMap<String, String>();
		try{
			Connector connector =  ConnectorManager.getConnector(cajeroRequestTO.getUserName());
			
			if(Integer.parseInt(cajeroRequestTO.getOptionDispositive())==TOKEN_DISPOSITIVE){
				params.put("tokencode",cajeroRequestTO.getTokenCode()+"");
			}else{
				params.put("clave",cajeroRequestTO.getClave());
			}
			
			connector.sendRequest(EJECUCION_DISPOCICION_EFECTIVO_CAJERO_1, params);
			String xml = connector.sendRequest(EJECUCION_DISPOCICION_EFECTIVO_CAJERO_2, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement getNipChangeInvocation(CambioNipRequestTO cambioNipRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tarjeta", cambioNipRequestTO.getTarjeta());
				
		try{
			Connector connector =  ConnectorManager.getConnector(cambioNipRequestTO.getUser());
			String xml = connector.sendRequest(CAMBIO_NIP_INVOCACION, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
		public MessageElement setNipChangeExecution(CambioNipRequestTO cambioNipRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tarjeta", cambioNipRequestTO.getTarjeta());
		params.put("actualNip", cambioNipRequestTO.getActualNip());
		params.put("newNip1", cambioNipRequestTO.getNewNip1());
		params.put("newNip2", cambioNipRequestTO.getNewNip2());
		
		try{
			Connector connector =  ConnectorManager.getConnector(cambioNipRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(CAMBIO_NIP_EJECUCION, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
		public MessageElement setNipChangeConfirmation(CambioNipRequestTO cambioNipRequestTO)throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tarjeta", cambioNipRequestTO.getTarjeta());
		params.put("actualNip", cambioNipRequestTO.getActualNip());
		params.put("newNip1", cambioNipRequestTO.getNewNip1());
		params.put("newNip2", cambioNipRequestTO.getNewNip2());
		
		if(cambioNipRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", cambioNipRequestTO.getTokencode());
		}else{
			params.put("clave", cambioNipRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, cambioNipRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "cambioNipForm");
		}	
		
		try{
			Connector connector =  ConnectorManager.getConnector(cambioNipRequestTO.getUser());
			String xml = connector.sendRequest(CAMBIO_NIP_CONFIRMACION, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		
		return messageElement;
	}
	
	public MessageElement informacionBloquearDesbloquearWait(LockUnlockCardsRequestTO lockUnlockCardsRequestTO)
		throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("opcion", lockUnlockCardsRequestTO.getOperacion());
		params.put("estado", lockUnlockCardsRequestTO.getEstadoTarjeta());
		params.put("indice", String.valueOf(lockUnlockCardsRequestTO.getIndexCard()));
		try{
			Connector connector = ConnectorManager.getConnector(lockUnlockCardsRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PREPARAR_INFORMACION_TARJETA_WAIT, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement informacionBloquearDesbloquear(LockUnlockCardsRequestTO lockUnlockCardsRequestTO)
	throws DAOException, SessionExpiredException {
	MessageElement messageElement = null;		
	Map<String, String> params = new HashMap<String, String>();
	
	params.put("opcion", lockUnlockCardsRequestTO.getOperacion());
	params.put("estado", lockUnlockCardsRequestTO.getEstadoTarjeta());
	params.put("indice", String.valueOf(lockUnlockCardsRequestTO.getIndexCard()));
	
	try{
		Connector connector = ConnectorManager.getConnector(lockUnlockCardsRequestTO.getUser());
//		connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
		String xml = connector.sendRequest(PREPARAR_INFORMACION_TARJETA, params);
		connector.sendJSPRequest("/seguridad/redireccion.elite", null);
		xml = connector.sendRequest(PREPARAR_INFORMACION_TARJETA, params);
		messageElement = XMLDecode.buildXMLMessageElement(xml);
		
	} catch (URISyntaxException e) {
		throw new DAOException(e);
	} catch (HttpException e) {
		throw new DAOException(e);
	} catch (IOException e) {
		throw new DAOException(e);
	} catch (XmlDecodeException e) {
		throw new DAOException(e);
	}
	return messageElement;
}

	public MessageElement aceptarCardBlocking(LockUnlockCardsRequestTO lockUnlockCardsRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("opcion", lockUnlockCardsRequestTO.getOperacion());
		params.put("nombre", lockUnlockCardsRequestTO.getNombre());
		params.put("tarjeta", lockUnlockCardsRequestTO.getNumTarjeta());
		params.put("estado", lockUnlockCardsRequestTO.getEstadoTarjeta());
		params.put("method", "cancelar");
		
		if(lockUnlockCardsRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))) {
			params.put("tokencode", lockUnlockCardsRequestTO.getTokenCode());
		}else{			
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, lockUnlockCardsRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
				
		try {
			
			Connector connector = ConnectorManager.getConnector(lockUnlockCardsRequestTO.getUser());
			String xml = connector.sendRequest(BLOQUEAR_TARJETA, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);			

		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement estadoCuentaMenu(EstadoCuentaRequestTO estadoCuentaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
						
		Connector connector = ConnectorManager.getConnector(estadoCuentaRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(ESTADO_CUENTA_1_1, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement estadoCuenta(EstadoCuentaRequestTO estadoCuentaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
						
		Connector connector = ConnectorManager.getConnector(estadoCuentaRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(ESTADO_CUENTA_1_2, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}

	public MessageElement estadoCuentaCmd(EstadoCuentaRequestTO estadoCuentaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
						
		Connector connector = ConnectorManager.getConnector(estadoCuentaRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(ESTADO_CUENTA_1_3, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement consultaCuenta(EstadoCuentaRequestTO estadoCuentaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();

		params.put("cuenta", estadoCuentaRequestTO.getCuenta());
		params.put("periodo", estadoCuentaRequestTO.getPeriodo());
		params.put("carta", "0");
		params.put("method", estadoCuentaRequestTO.getMethod());
		params.put("anio", estadoCuentaRequestTO.getAnio());
		params.put("mes", estadoCuentaRequestTO.getMes());
		
		params.put("submit", "Mostrar Estado de Cuenta");
			
		
		Connector connector = ConnectorManager.getConnector(estadoCuentaRequestTO.getUser());

		try {
			String xml = connector.sendRequest(ESTADO_CUENTA_2_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;
	}
	
	public MessageElement consultaCuentaEjecutar(EstadoCuentaRequestTO estadoCuentaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("cuenta", estadoCuentaRequestTO.getCuenta());
		params.put("method", "ejecutar");
		params.put("carta", "0");
		params.put("mes", estadoCuentaRequestTO.getMes());
		params.put("anio", estadoCuentaRequestTO.getAnio());
		params.put("periodo", estadoCuentaRequestTO.getPeriodo());
		params.put("submit", "Mostrar Estado de Cuenta");		
		
		Connector connector = ConnectorManager.getConnector(estadoCuentaRequestTO.getUser());

		try {
			String xml = connector.sendRequest(ESTADO_CUENTA_3_1, params);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement invokeMisFinanzas(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try{			
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			
			String xml = connector.sendRequest(FINANZAS_MENU, null);
			xml = connector.sendRequest(MOVIMIENTOS_FECHA, null);
			xml = connector.sendRequest(MOVIMIENTOS_FECHA_PRE, null);
			
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement invokeMisFinanzasGrafica(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		try{
			Map<String, String> parametros = new HashMap<String, String>();
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			
			parametros.put("method", "graph");
			parametros.put("cuenta", balanceRequestTO.getIndexAcount());			
			
			if(balanceRequestTO.getRango() != null ){
				parametros.put("rango", balanceRequestTO.getRango());
				parametros.put("fechaIni", "");
				parametros.put("fechaFin", "");
			}else{
				parametros.put("fechaIni", balanceRequestTO.getFechaIni());
				parametros.put("fechaFin", balanceRequestTO.getFechaFin());
			}
			
			String xml = connector.sendRequest(FINANZAS_GRAFICA, parametros);			
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement consultarMisFinanzas(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try{
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			Map<String, String> parametros = new HashMap<String, String>();
			
			parametros.put("method", "search");
			parametros.put("cuenta", balanceRequestTO.getIndexAcount());
			
			if(balanceRequestTO.getRango() != null ){
				parametros.put("rango", balanceRequestTO.getRango());
				parametros.put("fechaIni", "");
				parametros.put("fechaFin", "");
			}else{
				parametros.put("fechaIni", balanceRequestTO.getFechaIni());
				parametros.put("fechaFin", balanceRequestTO.getFechaFin());				
			}
			parametros.put("submit", "Consultar");
			
			String xml = connector.sendRequest(CONSULTA_MIS_FINANZAS, parametros);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}		
		return messageElement;
	}
	
	public MessageElement getMovimientosGanareMas(MovimientosCuentaInversionRequest movimientosCuentaInversionRequest) throws SessionExpiredException, DAOException{
		MessageElement messageElement=null;
		
		try{
			
			Connector connector = ConnectorManager.getConnector(movimientosCuentaInversionRequest.getUsuario());
			Map<String, String> params = new Hashtable<String, String>();
			params.put("cuenta",movimientosCuentaInversionRequest.getIndiceCuenta());
			params.put("method", "inicio");
			
			connector.sendRequest("/consultas/cuentas.elite", null);
			connector.sendRequest("/consultas/cuentasPre.elite",null);
			
			String xml = connector.sendRequest(CuentasDAO.GET_MOVIMIENTOS_GANAREMAS_2, params);
			log.info("ganaremas: "+xml);			
		    xml = connector.sendRequest(CuentasDAO.GET_MOVIMIENTOS_GANAREMAS_1, params);
			log.info("ganaremas: "+xml);			
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}	
		return messageElement; 		
	}		
	
	public MessageElement getCuentasChequeraPresolicitud(ChequeraPreaperturaRequestTO chequeraRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method","goInit");
		
		try {
			Connector connector = ConnectorManager.getConnector(chequeraRequestTO.getUser());
			String xml = connector.sendRequest(CHEQUERA_PRESOLICITUD_1, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	
	public MessageElement validaCuentaSocioPlus(CuentaSocioPlusRequestTO infiniteDetalleCantidadesTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		
		try {
			Connector connector = ConnectorManager.getConnector(infiniteDetalleCantidadesTO.getUser());
			String xml = connector.sendRequestPost(VALIDA_APERTURA_SOCIO_PLUS_1, null);
			connector.sendRequestPost(VALIDA_APERTURA_SOCIO_PLUS_2, null);
			connector.sendRequestPost(VALIDA_APERTURA_SOCIO_PLUS_3, null);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}	
	
	public void validaToken(AlertsDataRequest request) throws DAOException, SessionExpiredException {
		try{
			Connector connector = ConnectorManager.getConnector(request.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION_CHANGE, null);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		}
	}
	
//Obtiene la carta de retenciones de cuenta socio plus
	
	public MessageElement consultarCartaRetenciones(RetencionesRequestTO retencionesRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "getCartaInfoRet");
		try {
			Connector connector = ConnectorManager.getConnector(retencionesRequestTO.getUser());
			connector.sendRequest(CARTA_RETENCION_MENU, null);
			String xml = connector.sendRequest(CARTA_RETENCION_INFORMACION, params);
			XMLDecode decode = new XMLDecode();
			messageElement = decode.buildMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}
		return messageElement;
	}
	
	public MessageElement consultarMovimientosFecha(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try{
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			Map<String, String> parametros = new HashMap<String, String>();
			
			//parametros.put("method", "search");
//			parametros.put("cuenta", balanceRequestTO.getIndexAcount());
			
			if(balanceRequestTO.getRango() != null ){
				parametros.put("rango", balanceRequestTO.getRango());
				parametros.put("fechaIni", "");
				parametros.put("fechaFin", "");
			}else{
				parametros.put("fechaIni", balanceRequestTO.getFechaIni());
				parametros.put("fechaFin", balanceRequestTO.getFechaFin());				
			}
				parametros.put("submit", "Consultar");	
				
						
			connector.sendRequest(CONSULTA_MOVIMIENTOS_SOCIO_PLUS_FECHA_INI, parametros);
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS_SOCIO_PLUS_FECHA_CMD, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}		
		return messageElement;
	}
	
	
	//Consulta de movimientos por fecha para cuentas de Nomina y Guardadito
	
	public MessageElement consultarMovimientosFechaOtrasCuentas(BalanceRequestTO balanceRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		try{
			Connector connector = ConnectorManager.getConnector(balanceRequestTO.getUser());
			Map<String, String> parametros = new HashMap<String, String>();
			

			if(balanceRequestTO.getRango() != null ){
				parametros.put("rango", balanceRequestTO.getRango());
				parametros.put("fechaIni", "");
				parametros.put("fechaFin", "");
			}else{
				parametros.put("fechaIni", balanceRequestTO.getFechaIni());
				parametros.put("fechaFin", balanceRequestTO.getFechaFin());				
			}
				parametros.put("submit", "Consultar");
				
			connector.sendRequest(CONSULTA_MOVIMIENTOS_FECHAS_NOMINA_GUARDADITO, parametros);
			String xml = connector.sendRequest(CONSULTA_MOVIMIENTOS_FECHAS_NOMINA_GUARDADITO_CMD, null);
				
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
		} catch (URISyntaxException e) {
			throw new DAOException(e);
		} catch (HttpException e) {
			throw new DAOException(e);
		} catch (IOException e) {
			throw new DAOException(e);
		} catch (XmlDecodeException e) {
			throw new DAOException(e);
		}		
		return messageElement;
	}
	
	
}
