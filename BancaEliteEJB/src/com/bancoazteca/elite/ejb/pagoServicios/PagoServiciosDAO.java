package com.bancoazteca.elite.ejb.pagoServicios;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;
//import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.BeneficiarioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.DonativosRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.FrecuentesRequestTO;
import com.bancoazteca.elite.beans.FrecuentesTiempoAireRequestTO;
import com.bancoazteca.elite.beans.PagoServicioAztecaWebRequestTO;
import com.bancoazteca.elite.beans.PagoServicioLuzRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosAvicolaRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosIusacellRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosMaxiGasRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosMovistarRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosParametrizableRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosSkyRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosTelmexRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosTiempoAireRequestTO;
import com.bancoazteca.elite.beans.PagoServiciosToditoCardRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaCreditoRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaOtrosBancosRequestTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiRequestTO;
import com.bancoazteca.elite.commons.EliteDAO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.connector.DigitalFinger;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.util.DineroExpressUtils;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaRequestTO;
import com.bancoazteca.elite.beans.PagoTarjetaAztecaResponseTO;
import com.bancoazteca.elite.beans.FrecuentesAztecaRequestTO;

/**
 * En la clase PagoServiciosDAO se declaran todos los paths y los parámetros,<br/> 
 * necesarios para que el servlet del conector elite invoque las acciones correspondientes a los pagos de servicios desde el portal como CFE, AXTEL, telcel, etc.<br/>
 * @return <b>org.apache.axis.message.MessageElement </b><br/>
 * 			En la invocación de las acciones se tiene como respuesta un MessageElement con la información recopilada.
 */

public class PagoServiciosDAO extends EliteDAO {
	
//	private static final Logger LOG = Logger.getLogger(PagoServiciosDAO.class);
	
	private final String PAGO_SERVICIO_TEMEX_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_TEMEX_1_1 = "/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_TEMEX_1_2 = "/servicios/serviciosFill.elite";	
	private final String PAGO_SERVICIO_TEMEX_2 = "/servicios/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_TEMEX_3 = "/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_TEMEX_3_1 = "/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_TEMEX_3_2 = "/servicios/pagoServicioEjecutarCmd.elite";
	 
	private final String PAGO_SERVICIO_IUSACELL_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_IUSACELL_1_1 = "/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_IUSACELL_1_2 = "/servicios/serviciosFill.elite";
	private final String PAGO_SERVICIO_IUSACELL_2 = "/servicios/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_IUSACELL_3_1 = "/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_IUSACELL_3_2 = "/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_IUSACELL_3_3 = "/servicios/pagoServicioEjecutarCmd.elite";
	
	private final String PAGO_SERVICIO_AVICOLA_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_AVICOLA_1_1="/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_AVICOLA_1_2="/servicios/serviciosFill.elite";
	private final String PAGO_SERVICIO_AVICOLA_2 = "/servicios/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_AVICOLA_3_1 = "/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_AVICOLA_3_2 = "/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_AVICOLA_3_3 = "/servicios/pagoServicioEjecutarCmd.elite";
	
	private final String PAGO_SERVICIO_LUZ_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_LUZ_1_1 = "/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_LUZ_1_2 = "/servicios/serviciosFill.elite";
	private final String PAGO_SERVICIO_LUZ_2 = "/servicios/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_LUZ_3 = "/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_LUZ_3_1 = "/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_LUZ_3_2 = "/servicios/pagoServicioEjecutarCmd.elite";
	private final String PAGO_SERVICIO_LUZ_4 = "/servicios/pagoTerminado.elite";

	private final String TIEMPO_AIRE_1_1 = "/servicios/tiempoAireMenu.elite";
	private final String TIEMPO_AIRE_1_2 = "/servicios/proveedoresTiempoAire.elite";
	private final String TIEMPO_AIRE_1_3 = "/servicios/proveedoresTiempoAirePre.elite";
	private final String TIEMPO_AIRE_2 = "/servicios/tiempoAire.elite";
	private final String TIEMPO_AIRE_3 = "/servicios/tiempoAire.elite";
	private final String TIEMPO_AIRE_4_1 = "/servicios/esperaConfirmacion.elite";
	private final String TIEMPO_AIRE_4_2 = "/servicios/esperaConfirmacion.elite";
	private final String TIEMPO_AIRE_4_3 = "/servicios/esperaConfirmacionCmd.elite";
	
	private final String CUENTAS_FRECUENTES_BORRAR_1_1 = "/servicios/eraseTelephone.elite";
	private final String CUENTAS_FRECUENTES_BORRAR_1_2 = "/servicios/eraseFrequentTelephone.elite";
	private final String CUENTAS_FRECUENTES_AIRE_MODIFICA_1_1 = "/servicios/modificaTelephone.elite";
	private final String CUENTAS_FRECUENTES_AIRE_MODIFICA_1_2 = "/servicios/modificaFrequentTelephone.elite";
	
	private final String CUENTAS_FRECUENTES_CONSULTA_1_1 = "/servicios/consultaReferencias.elite";
	private final String CUENTAS_FRECUENTES_CONSULTA_1_2 = "/servicios/consultaReferenciasPre.elite";
	private final String CUENTAS_FRECUENTES_AGREGA = "/servicios/agregaFrecuente.elite";
	private final String CUENTAS_FRECUENTES_ELIMINA = "/servicios/eliminaFrecuente.elite";
	private final String CUENTAS_FRECUENTES_MODIFICA = "/servicios/modificaFrecuente.elite";

	private final String PAGO_TARJETA_CREDITO_1_1 = "/pagoTarjetaAzteca/tarjetaInfiniteMenu.elite";
	private final String PAGO_TARJETA_CREDITO_1_2 = "/pagoTarjetaAzteca/inicio.elite";
	private final String PAGO_TARJETA_CREDITO_1_3 = "/pagoTarjetaAzteca/inicioPre.elite";
	private final String PAGO_TARJETA_CREDITO_1_4 = "/pagoTarjetaAzteca/datos.elite";
	private final String PAGO_TARJETA_CREDITO_2 = "/pagoTarjetaAzteca/confirmar.elite";
	private final String PAGO_TARJETA_CREDITO_3_1 = "/pagoTarjetaAzteca/pagarTarjetaAzteca.elite";
	private final String PAGO_TARJETA_CREDITO_3_2 = "/pagoTarjetaAzteca/pagarTarjetaAztecaPre.elite";

	private final String PAGO_TARJETA_OTROS_BANCOS_INVOCACION_1 = "/pagoTarjetas/otrasTarjetasMenu.elite";
	private final String PAGO_TARJETA_OTROS_BANCOS_INVOCACION_2 = "/pagoTarjetas/inicio.elite";
	private final String PAGO_TARJETA_OTROS_BANCOS_INVOCACION_3 = "/pagoTarjetas/inicioPre.elite";
	private final String PAGO_TARJETA_OTROS_BANCOS_INVOCACION_4 = "/pagoTarjetas/datos.elite";
	private final String PAGO_TARJETA_OTROS_BANCOS_CONFIRMAR = "/pagoTarjetas/confirmar.elite";
	private final String PAGO_TARJETA_OTROS_BANCOS_EJECUTAR = "/pagoTarjetas/pagar.elite";
	private final String PAGO_TARJETA_OTROS_BANCOS_ENVIO_MAIL = "/pagoTarjetas/sendMail.elite";
	private final String INICIAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_OTROS_BANCOS = "/transferencias/addfrequentAccount.elite";
	private final String PREPARAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_OTROS_BANCOS = "/transferencias/modificafrequentAccounts.elite";
	private final String EJECUTAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_OTROS_BANCOS = "/transferencias/frequentAccountsPre.elite";
	
	private final String PAGO_SERVICIO_SKY_INVOCACION_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_SKY_INVOCACION_2 = "/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_SKY_INVOCACION_3 = "/servicios/serviciosFill.elite";
	private final String PAGO_SERVICIO_SKY_CONFIRMACION_1 = "/servicios/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_SKY_EJECUCION_1 = "/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_SKY_EJECUCION_2 = "/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_SKY_EJECUCION_3 = "/servicios/pagoServicioEjecutarCmd.elite";

	private final String PAGO_SERVICIO_MOVISTAR_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_MOVISTAR_1_1 = "/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_MOVISTAR_1_2 = "/servicios/serviciosFill.elite";
	private final String PAGO_SERVICIO_MOVISTAR_2 = "/servicios/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_MOVISTAR_3_1 = "/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_MOVISTAR_3_2 = "/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_MOVISTAR_3_3 = "/servicios/pagoServicioEjecutarCmd.elite";

	private final String CUENTAS_FRECUENTES_TARJETAS_OTRO_BANCO_1_1 = "/transferencias/frequentAccounts.elite";
	private final String CUENTAS_FRECUENTES_TARJETAS_OTRO_BANCO_1_2 = "/transferencias/frequentAccountsPre.elite";
	private final String CUENTAS_FRECUENTES_TARJETAS_OTRO_BANCO_2 = "/transferencias/addfrequentAccount.elite";
	
	private final String CUENTAS_FRECUENTES_TIEMPO_AIRE_1_1 = "/servicios/frequentTelephones.elite";
	private final String CUENTAS_FRECUENTES_TIEMPO_AIRE_1_2 = "/servicios/frequentTelephonesPre.elite";
	private final String CUENTAS_FRECUENTES_TIEMPO_AIRE_2 = "/servicios/addfrequentTelephone.elite";
	private final String CUENTAS_FRECUENTES_BORRAR = "/servicios/eraseFrequentTelephone.elite";
	private final String CUENTAS_FRECUENTES_AIRE_MODIFICA= "/servicios/modificaFrequentTelephone.elite";

	
	private final String PAGO_SERVICIO_MAXIGAS_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_MAXIGAS_1_1 = "/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_MAXIGAS_1_2 = "/servicios/serviciosFill.elite";
	private final String PAGO_SERVICIO_MAXIGAS_2 = "/servicios/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_MAXIGAS_3 = "/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_MAXIGAS_3_1 = "/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_MAXIGAS_3_2 = "/servicios/pagoServicioEjecutarCmd.elite";
	
	// es necesario que se ponga nuevamente si es que ya esta declarado.
	private final String PAGO_SERVICIO_AZTECAWEB_1="/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_AZTECAWEB_1_1="/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_AZTECAWEB_1_2="/servicios/serviciosFill.elite";
	private final String PAGO_SERVICIO_AZTECAWEB_2="/servicios/confirmarSaldo.elite";  
	private final String PAGO_SERVICIO_AZTECAWEB_3="/servicios/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_AZTECAWEB_3_1="/servicios/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_AZTECAWEB_3_2="/servicios/pagoServicioEjecutarCmd.elite";
	
	//PATHS DONATIVOS
	
	private final String DONATIVO_1="/donativos/inicioMenu.elite";
	private final String DONATIVO_2="/donativos/inicio.elite";
	private final String DONATIVO_2_1="/donativos/jugeton.elite";
	private final String DONATIVO_2_1_2="/donativos/jugeton.jsp";
	private final String DONATIVO_2_1_1="/donativos/jugetonPre.elite";
	private final String DONATIVO_3="/donativos/jugetonConfirmar.elite";
	private final String DONATIVO_4="/donativos/jugetonEjecutar.elite";
	private final String DONATIVO_4_1="/donativos/jugetonEjecutarCmd.elite";
	
	//DINERO EXPRESS
public static final String DEX_ENV_NODIRIGIDO="DineroExpressEnvioAction.DEX_ENV_NODIRIGIDO";	
	
	private final String ENVIO_DINERO_EXPRESS_INICIO=  "/transferencias/enviodex/inicio.elite";	
	private final String ENVIO_DEX_PAIS="/transferencias/enviodex/paso1.elite";
	private final String ENVIO_DINERO_EXPRESS= "/transferencias/enviodex/paso2.elite";			
	private final String ENVIO_DINERO_EXPRESS_PASO3="/transferencias/enviodex/paso3Pre.elite";	
	private final String ENVIO_DINERO_EXPRESS_ESTADOS_CIUDAD_AGENTE_SUCURSAL="/transferencias/enviodex/paso2.elite";
	private final String ENVIO_DINERO_EXPRESS_MENSAJE_CORREO="/transferencias/enviodex/envioCorreo.elite";
	private final String DINERO_EXPRESS_FRECUENTES_ALTA_1_1     = "/transferencias/agendaDineroExpressPre.elite";													           
	private final String DINERO_EXPRESS_FRECUENTES_ALTA_1_3     = "/transferencias/agendaDineroExpressAgregar.elite";
	private final String DINERO_EXPRESS_FRECUENTES_ALTA_1_4     = "/transferencias/agendaDineroExpressAgregarCmd.elite";
	private final String DINERO_EXPRESS_TELEFONOS_CLIENTE="/alertas/serviciosPre.elite";	
	private final String DINERO_EXPRESS_CONSULTA_BENEFICIARIOS     = "/transferencias/agendaDineroExpressPre.elite";
	
	private final String PAGO_SERVICIO_PARAMETRIZABLE_INICIAL="/servicios/servicios.jsp";
	private final String PAGO_SERVICIO_PARAMETRIZABLE_1_1 = "/servicios/servicioDisponible.elite";
	private final String PAGO_SERVICIO_PARAMETRIZABLE_1_2 = "/servicios/serviciosElegir.elite";
	private final String PAGO_SERVICIO_PARAMETRIZABLE_1_3 = "/servicios/parametrizable/serviciosFill.elite";
	private final String PAGO_SERVICIO_PARAMETRIZABLE_2_1 = "/servicios/parametrizable/confirmarSaldo.elite";
	private final String PAGO_SERVICIO_PARAMETRIZABLE_3_1 = "/servicios/parametrizable/pagoServicioPassword.elite";
	private final String PAGO_SERVICIO_PARAMETRIZABLE_3_2 = "/servicios/parametrizable/pagoServicioEjecutar.elite";
	private final String PAGO_SERVICIO_PARAMETRIZABLE_3_3 = "/servicios/parametrizable/pagoServicioEjecutarCmd.elite";
	private final String VALIDA_NIVELES_SEGURIDAD="/transferencias/transSpei.elite";
	private final String AGREGA_PARAMETROS_REFERENCIA_FRECUENTES = "/servicios/pagoServicioParam.jsp";
	
	// Pago de Tarjeta Azteca

	private final String PAGO_TARJETA_AZTECA_MENU = "/pagoTarjetaAzteca/tarjetaAztecaMenu.elite";
	private final String PAGO_TARJETA_AZTECA_INICIO = "/pagoTarjetasAzteca/inicio.elite";
	private final String PAGO_TARJETA_AZTECA_INICIO_PRE = "/pagoTarjetasAzteca/inicioPre.elite";
	private final String PAGO_TARJETA_AZTECA_CONFIRMAR = "/pagoTarjetasAzteca/confirmar.elite";
	private final String PAGO_TARJETA_AZTECA_PAGAR = "/pagoTarjetasAzteca/pagar.elite";
	private final String PAGO_TARJETA_MENU = "/consultas/inicioMenu.elite";

	private final String PAGO_TARJETA_AZTECA_MAIL = "/pagoTarjetasAzteca/sendMail.elite";
	private final String PAGO_TARJETA_AZTECA_DATOS = "/pagoTarjetaAzteca/datos.elite";
	private final String PAGO_TARJETA_AZTECA_JSP = "/pagoTarjetas/tarjetaAzteca.jsp";

	// Cuentas frecuentes para pago Tarjeta Azteca

	private final String PAGO_TARJETA_AZTECA_FRECUENTE = "/transferencias/frequentAccountsAzteca.elite";
	private final String PAGO_TARJETA_AZTECA_FRECUENTE_PRE = "/transferencias/frequentAccountsAztecaPre.elite";

	// Actualizacion de cuentas frecuentes Azteca

	private final String AGREGAR_CUENTAS_FRECUENTES = "/transferencias/addfrequentAccountAzteca.elite";
	private final String PREPARAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA = "/transferencias/modificafrequentAccountsAzteca.elite";
	private final String EJECUTAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA = "/transferencias/frequentAccountsAztecaPre.elite";

	
	public MessageElement validaNivelSeguridad(String user)throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		HashMap<String, String> parametros= new HashMap<String, String>();
		parametros.put("method","inicio");
		try {
				Connector connector = ConnectorManager.getConnector(user);
				String xml = connector.sendRequest(VALIDA_NIVELES_SEGURIDAD, parametros);
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
	public MessageElement setInitialTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00001");
		Connector connector = ConnectorManager.getConnector(pagoServiciosTelmexRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_TEMEX_1, params);
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
	
	public MessageElement setInitialServiceTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00001");
		Connector connector = ConnectorManager.getConnector(pagoServiciosTelmexRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_TEMEX_1_1, params);
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
	
	public MessageElement setStartInitialServiceTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00001");
		Connector connector = ConnectorManager.getConnector(pagoServiciosTelmexRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_TEMEX_1_2, params);
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
	
	
	
	public MessageElement setDataTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", pagoServiciosTelmexRequestTO.getNumeroTelefonico());
		params.put("digitoVerificador", pagoServiciosTelmexRequestTO.getDigitoVerificador());
		params.put("cuentaCargo",pagoServiciosTelmexRequestTO.getCuentaCargo());
		params.put("tipoServicio",pagoServiciosTelmexRequestTO.getTipoServicio());
		params.put("importe", pagoServiciosTelmexRequestTO.getImporte());
		params.put("submit", "Continuar");
		
		Connector connector = ConnectorManager.getConnector(pagoServiciosTelmexRequestTO.getUser());
	
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_TEMEX_2, params);
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
	
	public MessageElement setConfimTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", pagoServiciosTelmexRequestTO.getNumeroTelefonico());
		params.put("digitoVerificador", pagoServiciosTelmexRequestTO.getDigitoVerificador());
		params.put("cuentaCargo",pagoServiciosTelmexRequestTO.getCuentaCargo());
		params.put("tipoServicio",pagoServiciosTelmexRequestTO.getTipoServicio());
		params.put("importe", pagoServiciosTelmexRequestTO.getImporte());
		params.put("importe", pagoServiciosTelmexRequestTO.getImporte());
		params.put("fechaAplicacion", pagoServiciosTelmexRequestTO.getFechaAplicacion());
		params.put("total", pagoServiciosTelmexRequestTO.getTotal());
		params.put("comision", "0.0");
		params.put("iva", "0.0");
		//validacion del dispositivo de seguridad
		if(pagoServiciosTelmexRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", pagoServiciosTelmexRequestTO.getTokenCode());
		}else{
			params.put("clave", pagoServiciosTelmexRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, pagoServiciosTelmexRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(pagoServiciosTelmexRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_TEMEX_3, params);
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
	
	public MessageElement setExecuteWaitTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
						
		Connector connector = ConnectorManager.getConnector(pagoServiciosTelmexRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_TEMEX_3_1, null);
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
	
	public MessageElement setExecuteTelmexPayment(PagoServiciosTelmexRequestTO pagoServiciosTelmexRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");				
		Connector connector = ConnectorManager.getConnector(pagoServiciosTelmexRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_TEMEX_3_2, params);
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
	
	
	public MessageElement setInitialAvicolaPayment(PagoServiciosAvicolaRequestTO avicolaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00037");
		Connector connector = ConnectorManager.getConnector(avicolaRequestTO.getUser());
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_1, params);
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
	
	
	
	public MessageElement setInitialServiceAvicolaPayment(PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00037");
		Connector connector = ConnectorManager.getConnector(pagoServiciosAvicolaRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_1_1, params);
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
	
	public MessageElement setStartInitialServiceAvicolaPayment(PagoServiciosAvicolaRequestTO pagoServiciosAvicolaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00037");
		Connector connector = ConnectorManager.getConnector(pagoServiciosAvicolaRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_1_2, params);
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
	
	
	public MessageElement setFormDataAvicolaPayment(PagoServiciosAvicolaRequestTO avicolaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", avicolaRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", avicolaRequestTO.getCuentaCargo());
		params.put("tipoServicio", avicolaRequestTO.getTipoServicio());
		params.put("importe", avicolaRequestTO.getImporte());
		params.put("digitoVerificador", avicolaRequestTO.getDigitoVerificador());
		params.put("submit", "Continuar");
		Connector connector = ConnectorManager.getConnector(avicolaRequestTO.getUser());
		
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_2, params);
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
	
	public MessageElement setTokenAvicolaPayment(PagoServiciosAvicolaRequestTO avicolaRequestTO) throws DAOException, SessionExpiredException {
		
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", avicolaRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", avicolaRequestTO.getCuentaCargo());
		params.put("tipoServicio", avicolaRequestTO.getTipoServicio());
		params.put("fechaAplicacion", avicolaRequestTO.getFechaAplicacion());
		params.put("importe", avicolaRequestTO.getImporte());
		params.put("comision", avicolaRequestTO.getComision());
		params.put("iva", avicolaRequestTO.getIva());
		params.put("total", avicolaRequestTO.getTotal());
		params.put("digitoVerificador", avicolaRequestTO.getDigitoVerificador());
		
		//validacion del dispositivo de seguridad
		if(avicolaRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", avicolaRequestTO.getTokenCode());
		}else{
			params.put("clave", avicolaRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, avicolaRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(avicolaRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_3_1, params);
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
	
	

	public MessageElement setConfimAvicolaPayment(PagoServiciosAvicolaRequestTO avicolaRequestTO) throws DAOException, SessionExpiredException {
		
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", avicolaRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", avicolaRequestTO.getCuentaCargo());
		params.put("tipoServicio", avicolaRequestTO.getTipoServicio());
		params.put("fechaAplicacion", avicolaRequestTO.getFechaAplicacion());
		params.put("importe", avicolaRequestTO.getImporte());
		params.put("comision", avicolaRequestTO.getComision());
		params.put("iva", avicolaRequestTO.getIva());
		params.put("total", avicolaRequestTO.getTotal());
		params.put("digitoVerificador", avicolaRequestTO.getDigitoVerificador());
		//validacion del dispositivo de seguridad
		if(avicolaRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", avicolaRequestTO.getTokenCode());
		}else{
			params.put("clave", avicolaRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, avicolaRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(avicolaRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_3_1, params);
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
	
	
	public MessageElement setExecuteWaitAvicolaPayment(PagoServiciosAvicolaRequestTO avicolaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(avicolaRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_3_2, params);
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
	
	
	
	public MessageElement setExecuteAvicolaPayment(PagoServiciosAvicolaRequestTO avicolaRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(avicolaRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AVICOLA_3_3, params);
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
	
	
	public MessageElement setInitialIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00027");
		Connector connector = ConnectorManager.getConnector(iusacellRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_IUSACELL_1, params);
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
	
	
	
	
	public MessageElement setInitialServiceIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00027");
		Connector connector = ConnectorManager.getConnector(iusacellRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_IUSACELL_1_1, params);
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
	
	public MessageElement setStartInitialServiceIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00027");
		Connector connector = ConnectorManager.getConnector(iusacellRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_IUSACELL_1_2, params);
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
	
	public MessageElement setFormDataIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", iusacellRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", iusacellRequestTO.getCuentaCargo());
		params.put("tipoServicio", iusacellRequestTO.getTipoServicio());
		params.put("importe", iusacellRequestTO.getImporte());
		params.put("digitoVerificador", iusacellRequestTO.getDigitoVerificador());
		params.put("submit", "Continuar");

		Connector connector = ConnectorManager.getConnector(iusacellRequestTO.getUser());
		
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_IUSACELL_2, params);
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
	
	public MessageElement setTokenIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", iusacellRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", iusacellRequestTO.getCuentaCargo());
		params.put("tipoServicio", iusacellRequestTO.getTipoServicio());
		params.put("fechaAplicacion", iusacellRequestTO.getFechaAplicacion());
		params.put("importe", iusacellRequestTO.getImporte());
		params.put("comision", iusacellRequestTO.getComision());
		params.put("iva", iusacellRequestTO.getIva());
		params.put("total", iusacellRequestTO.getTotal());
		params.put("digitoVerificador", iusacellRequestTO.getDigitoVerificador());
		//validacion del dispositivo de seguridad
		if(iusacellRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", iusacellRequestTO.getTokenCode());
		}else{
			params.put("clave", iusacellRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, iusacellRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(iusacellRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_IUSACELL_3_1, params);
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
	
	public MessageElement setExecuteIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(iusacellRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_IUSACELL_3_2, params);
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
	
	public MessageElement setExecuteWaitIusacellPayment(PagoServiciosIusacellRequestTO iusacellRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(iusacellRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_IUSACELL_3_3, params);
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
	
	public MessageElement setInitialLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00022");
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_1, params);
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
	
	public MessageElement setStartInitialLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO ) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00022");
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_1_1, params);
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
	
	
	public MessageElement setStartInitialServiceLuzPaymen(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00022");
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_1_2, params);
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
	
	
	public MessageElement setDataLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("method", "confirmarSaldo");
		params.put("submit", "Continuar");
		params.put("tipoServicio", "LUZ Y FUERZA CENTRO");
		params.put("cuentaReferencia", pagoServicioLuzRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", pagoServicioLuzRequestTO.getDigitoVerificador());
		params.put("fechaVencimiento", pagoServicioLuzRequestTO.getFechaVencimiento());
		params.put("cuentaCargo", pagoServicioLuzRequestTO.getCuentaCargo());
		params.put("importe", pagoServicioLuzRequestTO.getImporte());		
		
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());
		
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_2, params);
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
	
	public MessageElement setConfimLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("method", "passwordCheck");		
		params.put("tipoServicio", "LUZ Y FUERZA CENTRO");
		params.put("cuentaReferencia", pagoServicioLuzRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", pagoServicioLuzRequestTO.getDigitoVerificador());
		params.put("fechaAplicacion", pagoServicioLuzRequestTO.getFechaVencimiento());
		params.put("cuentaCargo", pagoServicioLuzRequestTO.getCuentaCargo());
		params.put("importe", pagoServicioLuzRequestTO.getImporte());
		params.put("comision", pagoServicioLuzRequestTO.getComision());	
		params.put("iva", pagoServicioLuzRequestTO.getIva());	
		params.put("total", pagoServicioLuzRequestTO.getTotal());
		//validacion del dispositivo de seguridad
		if(pagoServicioLuzRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", pagoServicioLuzRequestTO.getToken());
		}else{
			params.put("clave", pagoServicioLuzRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, pagoServicioLuzRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_3, params);
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
	
	public MessageElement setWaitExecuteLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("method", "ejecutar");			
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_3_1, params);
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
	
	public MessageElement setExecuteLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("method", "ejecutar");			
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_3_2, params);
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
	
	public MessageElement setEndLuzPayment(PagoServicioLuzRequestTO pagoServicioLuzRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("method", "terminar");
		params.put("guardar", "false");	
		params.put("submit", "Otro Pago");	
		Connector connector = ConnectorManager.getConnector(pagoServicioLuzRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_LUZ_4, params);
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

	public MessageElement setInitialSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00004");
		Connector connector = ConnectorManager.getConnector(skyRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_SKY_INVOCACION_1, params);
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
	
	public MessageElement setInitialServiceSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00004");
		Connector connector = ConnectorManager.getConnector(skyRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_SKY_INVOCACION_2, params);
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
	
	public MessageElement setStartInitialServiceSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00004");
		Connector connector = ConnectorManager.getConnector(skyRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_SKY_INVOCACION_3, params);
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
	
	
	
	public MessageElement setDataSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", skyRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", skyRequestTO.getDigitoVerificador());
		params.put("cuentaCargo", skyRequestTO.getCuentaCargo());
		params.put("tipoServicio", skyRequestTO.getTipoServicio());
		params.put("importe", skyRequestTO.getImporte());
		params.put("submit", "Continuar");
		
		Connector connector = ConnectorManager.getConnector(skyRequestTO.getUser());
	
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_SKY_CONFIRMACION_1, params);
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
	
	public MessageElement setConfirmSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", skyRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", skyRequestTO.getDigitoVerificador());
		params.put("cuentaCargo", skyRequestTO.getCuentaCargo());
		params.put("tipoServicio", skyRequestTO.getTipoServicio());
		params.put("importe", skyRequestTO.getImporte());
		params.put("comision", skyRequestTO.getComision());
		params.put("iva", skyRequestTO.getIva());
		params.put("total", skyRequestTO.getTotal());
		//validacion del dispositivo de seguridad
		if(skyRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", skyRequestTO.getTokenCode());
		}else{
			params.put("clave", skyRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, skyRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(skyRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_SKY_EJECUCION_1, params);
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
	
	public MessageElement setExecuteWaitSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
						
		Connector connector = ConnectorManager.getConnector(skyRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_SKY_EJECUCION_2, null);
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
	
	public MessageElement setExecuteSkyPayment(PagoServiciosSkyRequestTO skyRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");				
		Connector connector = ConnectorManager.getConnector(skyRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_SKY_EJECUCION_3, params);
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
	
	
	
	public MessageElement setInitialMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00008");
		Connector connector = ConnectorManager.getConnector(movistarRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MOVISTAR_1, params);
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

	public MessageElement setInitialServiceMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00008");
		Connector connector = ConnectorManager.getConnector(movistarRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MOVISTAR_1_1, params);
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
	
	public MessageElement setStartInitialServiceMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00008");
		Connector connector = ConnectorManager.getConnector(movistarRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MOVISTAR_1_2, params);
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
	
	public MessageElement setFormDataMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", movistarRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", movistarRequestTO.getCuentaCargo());
		params.put("tipoServicio", movistarRequestTO.getTipoServicio());
		params.put("importe", movistarRequestTO.getImporte());
		params.put("digitoVerificador", movistarRequestTO.getDigitoVerificador());
		params.put("submit", "Continuar");

		Connector connector = ConnectorManager.getConnector(movistarRequestTO.getUser());
		
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_MOVISTAR_2, params);
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
	
	public MessageElement setTokenMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", movistarRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", movistarRequestTO.getCuentaCargo());
		params.put("tipoServicio", movistarRequestTO.getTipoServicio());
		params.put("fechaAplicacion", movistarRequestTO.getFechaAplicacion());
		params.put("importe", movistarRequestTO.getImporte());
		params.put("comision", movistarRequestTO.getComision());
		params.put("iva", movistarRequestTO.getIva());
		params.put("total", movistarRequestTO.getTotal());
		params.put("digitoVerificador", movistarRequestTO.getDigitoVerificador());
		//validacion del dispositivo de seguridad
		if(movistarRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", movistarRequestTO.getTokencode());
		}else{
			params.put("clave", movistarRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, movistarRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(movistarRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MOVISTAR_3_1, params);
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
	
	public MessageElement setExecuteMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(movistarRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MOVISTAR_3_2, params);
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
	
	public MessageElement setExecuteWaitMovistarPayment(PagoServiciosMovistarRequestTO movistarRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(movistarRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MOVISTAR_3_3, params);
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




	public MessageElement setMenuTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(TIEMPO_AIRE_1_1, null);
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
	
	public MessageElement setMenuServiceTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(TIEMPO_AIRE_1_2, null);
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

	public MessageElement setStartMenuTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(TIEMPO_AIRE_1_3, null);
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
	
	public MessageElement setInitialTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "inicio");
		params.put("carrier", tiempoAireRequestTO.getCarrier());
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(TIEMPO_AIRE_2, params);
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
	
	public MessageElement setDataTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "comprar");
		params.put("carrier", tiempoAireRequestTO.getCarrier());
		params.put("idCuenta", tiempoAireRequestTO.getIdCuenta());
		params.put("telefono", tiempoAireRequestTO.getTelefono());
		params.put("monto", tiempoAireRequestTO.getMonto());
		params.put("submit", "Continuar");
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(TIEMPO_AIRE_3, params);
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
	
	public MessageElement setConfirmTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		//validacion del dispositivo de seguridad
		if(tiempoAireRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", tiempoAireRequestTO.getTokencode());
		}else{
			params.put("clave", tiempoAireRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, tiempoAireRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "tiempoAireForm");
		}
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(TIEMPO_AIRE_4_1, params);
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
	
	public MessageElement setExecuteTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(TIEMPO_AIRE_4_2, params);
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
	
	public MessageElement setExecuteWaitTiempoAirePayment(PagoServiciosTiempoAireRequestTO tiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		Connector connector = ConnectorManager.getConnector(tiempoAireRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(TIEMPO_AIRE_4_3, params);
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

public MessageElement setInitialFrecuentes(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();		
		try {
			Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
			connector.sendJSPRequest(AGREGA_PARAMETROS_REFERENCIA_FRECUENTES, params);
			params.put("method", "consultaReferencias");
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_CONSULTA_1_1, params);
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
	
	
	public MessageElement setStartInitialFrecuentes(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "consultaReferencias");
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_CONSULTA_1_2, params);
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
	
	public MessageElement setAgregarFrecuenteParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try {
			
			Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			
			params.put("paso", frecuentesRequestTO.getPaso());
			params.put("referenciaSeleccionada", frecuentesRequestTO.getReferencia());
			params.put("servicio", frecuentesRequestTO.getServicio());
			params.put("user_id", frecuentesRequestTO.getUserId());
			params.put("method", frecuentesRequestTO.getMethod());
			params.put("servicio", frecuentesRequestTO.getServicio());
			params.put(frecuentesRequestTO.getTipoServicio(), frecuentesRequestTO.getReferenciaServicio());
			
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_AGREGA, params);

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
	
	public MessageElement setAgregarFrecuenteEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try {
			Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());	
			params.put("method", frecuentesRequestTO.getMethod());

			//validacion del dispositivo de seguridad
			if(frecuentesRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
				params.put("tokencode", frecuentesRequestTO.getTokencode());	
			}else{
				params.put("clave", frecuentesRequestTO.getClave());
				params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
				params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
				params.put(DigitalFinger.DIGITAL_FINGET_USER, frecuentesRequestTO.getUser());
				params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "transferenciasTefsForm");
			}
			
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_AGREGA, params);
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
	
	public MessageElement setInitialTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(pagoTarjetaCreditoRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_TARJETA_CREDITO_1_1, params);
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
	
	public MessageElement setInitialServiceTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(pagoTarjetaCreditoRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_TARJETA_CREDITO_1_2, params);
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
	
	public MessageElement setStartInitialTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(pagoTarjetaCreditoRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_TARJETA_CREDITO_1_3, params);
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
	
	public MessageElement setInitialWaitTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "inicio");
		Connector connector = ConnectorManager.getConnector(pagoTarjetaCreditoRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_TARJETA_CREDITO_1_4, params);
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
	
	public MessageElement setDataTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		params.put("cuenta", pagoTarjetaCreditoRequestTO.getCuenta());
		params.put("tarjeta", pagoTarjetaCreditoRequestTO.getTarjeta());
		params.put("importe",pagoTarjetaCreditoRequestTO.getImporte());
		Connector connector = ConnectorManager.getConnector(pagoTarjetaCreditoRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_TARJETA_CREDITO_2, params);
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

	public MessageElement setConfirmTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "pagar");
		params.put("cuenta", pagoTarjetaCreditoRequestTO.getCuenta());
		params.put("tarjeta", pagoTarjetaCreditoRequestTO.getTarjeta());
		params.put("importe",pagoTarjetaCreditoRequestTO.getImporte());
		params.put("comision",pagoTarjetaCreditoRequestTO.getComision());
		Connector connector = ConnectorManager.getConnector(pagoTarjetaCreditoRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_TARJETA_CREDITO_3_1, params);
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
	
	public MessageElement setConfirmWaitTarjetaCreditoPayment(PagoTarjetaCreditoRequestTO pagoTarjetaCreditoRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "pagar");
		Connector connector = ConnectorManager.getConnector(pagoTarjetaCreditoRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_TARJETA_CREDITO_3_2, params);
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

	public MessageElement setInitialMenuTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = "";
		try {
			Connector connector = ConnectorManager.getConnector(tarjetaOtrosBancosRequestTO.getUser());
			
			xml = connector.sendRequest(PAGO_TARJETA_OTROS_BANCOS_INVOCACION_1, null);
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
	
	public MessageElement setWaitInitialTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = "";
		try {
			Connector connector = ConnectorManager.getConnector(tarjetaOtrosBancosRequestTO.getUser());
			
			xml = connector.sendRequest(PAGO_TARJETA_OTROS_BANCOS_INVOCACION_2, null);
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
	
	public MessageElement getInitialTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = "";
		try {
			Connector connector = ConnectorManager.getConnector(tarjetaOtrosBancosRequestTO.getUser());
			
			xml = connector.sendRequest(PAGO_TARJETA_OTROS_BANCOS_INVOCACION_3, null);
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
	
	public MessageElement getStartServiceTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "inicio");
		String xml = "";
		try {
			Connector connector = ConnectorManager.getConnector(tarjetaOtrosBancosRequestTO.getUser());
						
			xml = connector.sendRequest(PAGO_TARJETA_OTROS_BANCOS_INVOCACION_4, params);
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
	
	public MessageElement getDataTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = "";
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		params.put("origen", tarjetaOtrosBancosRequestTO.getOrigen());
		params.put("tarjeta", tarjetaOtrosBancosRequestTO.getTarjeta());
		params.put("importe", tarjetaOtrosBancosRequestTO.getImporte());
		params.put("submit", "Pagar");
//		params.put("xmlEncoderChangeOption", "old");

		try {
			Connector connector = ConnectorManager.getConnector(tarjetaOtrosBancosRequestTO.getUser());
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);		
			
			xml = connector.sendRequest(PAGO_TARJETA_OTROS_BANCOS_CONFIRMAR, params);
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
	
	public MessageElement getConfirmTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = "";
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "pagar");
		params.put("importe", tarjetaOtrosBancosRequestTO.getImporte());
		params.put("origen", tarjetaOtrosBancosRequestTO.getOrigen());
		params.put("tarjeta", tarjetaOtrosBancosRequestTO.getTarjeta());
		params.put("comision", tarjetaOtrosBancosRequestTO.getComision());
		//validacion del dispositivo de seguridad
		if(tarjetaOtrosBancosRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", tarjetaOtrosBancosRequestTO.getTokencode());
		}else{
			params.put("clave", tarjetaOtrosBancosRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, tarjetaOtrosBancosRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoTarjetaOtrosBancosForm");
		}	
		
		try {
			Connector connector = ConnectorManager.getConnector(tarjetaOtrosBancosRequestTO.getUser());
			
			xml = connector.sendRequest(PAGO_TARJETA_OTROS_BANCOS_EJECUTAR, params);
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
	
	public MessageElement setSendMailTarjetaPaymentOthersBank(PagoTarjetaOtrosBancosRequestTO tarjetaOtrosBancosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = "";
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", "dbcamacho@bancoazteca.com.mx");
		params.put("mensaje", "pagoTarejeta");
		params.put("submit", "Enviar");
						
		try {
			Connector connector = ConnectorManager.getConnector(tarjetaOtrosBancosRequestTO.getUser());
			
			xml = connector.sendRequest(PAGO_TARJETA_OTROS_BANCOS_ENVIO_MAIL, params);
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
	

	public MessageElement setConsultaCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tipo", "tdc");
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_TARJETAS_OTRO_BANCO_1_1, params);
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
	
	public MessageElement setStartConsultaCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tipo", "tdc");
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_TARJETAS_OTRO_BANCO_1_2, params);
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
	
	public MessageElement setAgregaCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("refresh", "false");
		params.put("method", "addFrequentAccount");
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());
		params.put("submit", "Agregar");
		if(frecuentesRequestTO.getOptionDispositive().equals("1")){
			params.put("tokencode", frecuentesRequestTO.getTokencode());
		}else if(frecuentesRequestTO.getOptionDispositive().equals("2")){
			params.put("clave", frecuentesRequestTO.getClave());
		}
		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_TARJETAS_OTRO_BANCO_2, params);
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
	
	public MessageElement setAgregaWaitCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tipo", "tdc");
		params.put("method", "addFrequentAccount");
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_TARJETAS_OTRO_BANCO_1_2, params);
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
	
	public MessageElement iniciarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("refresh", "false");
		params.put("claveSwift", "");
		params.put("beneficiario", "");			
		params.put("destino", frecuentesRequestTO.getTarjeta());
				
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(INICIAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_OTROS_BANCOS, params);
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
	
	public MessageElement prepararEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("refresh", "false");
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());			
		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		
		try{
			String xml = connector.sendRequest(PREPARAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_OTROS_BANCOS, params);
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
	
	public MessageElement ejecutarEditarEliminarCuentasFrecuentesTarjetasOtrosBancos(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params =  frecuentesRequestTO.getParametros();
		params.put("tipo", "tdc");		
		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		
		try{
			String xml = connector.sendServletRequest(EJECUTAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_OTROS_BANCOS, params);
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
	
	public MessageElement setConsultaCuentasFrecuentesTiempoAire(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "start");
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_TIEMPO_AIRE_1_1, params);
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
	
	public MessageElement setStartConsultaCuentasFrecuentesTiempoAire(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "start");
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_TIEMPO_AIRE_1_2, params);
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
	
	public MessageElement setModificaReferenciasFrecuentesTiempoAireValidacion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("indice", frecuentesTiempoAireRequestTO.getIndex());
		try {
			Connector connector = ConnectorManager.getConnector(frecuentesTiempoAireRequestTO.getUser());
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_AIRE_MODIFICA_1_1, params);
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
	
	public MessageElement setModificaReferenciasFrecuentesTiempoAireEjecucion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "modificaFrequentTelephone");
		params.put("indice", frecuentesTiempoAireRequestTO.getIndex());
		params.put("telefono", frecuentesTiempoAireRequestTO.getTelefono());
		params.put("referencia", frecuentesTiempoAireRequestTO.getAlias());
		params.put("submit", "Modificar Tel&eacute;fono");

		try {
			Connector connector = ConnectorManager.getConnector(frecuentesTiempoAireRequestTO.getUser());
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_AIRE_MODIFICA_1_2, params);
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
	
	public MessageElement setBorraReferenciasFrecuentesTiempoAireValidacion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("indice", frecuentesTiempoAireRequestTO.getIndex());
		Connector connector = ConnectorManager.getConnector(frecuentesTiempoAireRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_BORRAR_1_1, params);
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

	public MessageElement setBorraReferenciasFrecuentesTiempoAireEjecucion(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("indice", frecuentesTiempoAireRequestTO.getIndex());
		params.put("method", "deleteFrequentTelephone");
		params.put("cancel", "");
		params.put("submit", "Borrar Teléfono");			
			
		try {
			Connector connector = ConnectorManager.getConnector(frecuentesTiempoAireRequestTO.getUser());
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_BORRAR_1_2, params);
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
	
	public MessageElement setAgregaReferenciasFrecuentesTiempoAire(FrecuentesTiempoAireRequestTO frecuentesTiempoAireRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "addFrequentTelephone");
		params.put("telefono", frecuentesTiempoAireRequestTO.getTelefono());
		params.put("referencia", frecuentesTiempoAireRequestTO.getAlias());
		params.put("submit", "Agregar Teléfono");
		Connector connector = ConnectorManager.getConnector(frecuentesTiempoAireRequestTO.getUser());
		try {
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_TIEMPO_AIRE_2, params);
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
	
	public MessageElement setInitialMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws DAOException, SessionExpiredException{
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00033");
		Connector connector = ConnectorManager.getConnector(pagoServiciosMaxiGasRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MAXIGAS_1, params);
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

	public MessageElement setInitialServiceMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00033");
		Connector connector = ConnectorManager.getConnector(pagoServiciosMaxiGasRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MAXIGAS_1_1, params);
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
	
	public MessageElement setStartInitialServiceMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00033");
		Connector connector = ConnectorManager.getConnector(pagoServiciosMaxiGasRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MAXIGAS_1_2, params);
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
	
	public MessageElement setDataMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", pagoServiciosMaxiGasRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", pagoServiciosMaxiGasRequestTO.getDigitoVerificador());
		params.put("cuentaCargo",pagoServiciosMaxiGasRequestTO.getCuentaCargo());
		params.put("tipoServicio",pagoServiciosMaxiGasRequestTO.getTipoServicio());
		params.put("importe", pagoServiciosMaxiGasRequestTO.getImporte());
		params.put("submit", "Continuar");
		
		Connector connector = ConnectorManager.getConnector(pagoServiciosMaxiGasRequestTO.getUser());
	
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_MAXIGAS_2, params);
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
	
	public MessageElement setConfimMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", pagoServiciosMaxiGasRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", pagoServiciosMaxiGasRequestTO.getDigitoVerificador());
		params.put("cuentaCargo",pagoServiciosMaxiGasRequestTO.getCuentaCargo());
		params.put("tipoServicio",pagoServiciosMaxiGasRequestTO.getTipoServicio());
		params.put("importe", pagoServiciosMaxiGasRequestTO.getImporte());
		params.put("fechaAplicacion", pagoServiciosMaxiGasRequestTO.getFechaAplicacion());
		params.put("total", pagoServiciosMaxiGasRequestTO.getTotal());
		params.put("comision", pagoServiciosMaxiGasRequestTO.getComision());
		params.put("iva", pagoServiciosMaxiGasRequestTO.getIva());
		
		//validacion del dispositivo de seguridad
		if(pagoServiciosMaxiGasRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", pagoServiciosMaxiGasRequestTO.getTokenCode());
		}else{
			params.put("clave", pagoServiciosMaxiGasRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, pagoServiciosMaxiGasRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(pagoServiciosMaxiGasRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MAXIGAS_3, params);
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
	
	public MessageElement setExecuteWaitMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
						
		Connector connector = ConnectorManager.getConnector(pagoServiciosMaxiGasRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MAXIGAS_3_1, null);
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
	
	public MessageElement setExecuteMaxiGasPayment(PagoServiciosMaxiGasRequestTO pagoServiciosMaxiGasRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");				
		Connector connector = ConnectorManager.getConnector(pagoServiciosMaxiGasRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_MAXIGAS_3_2, params);
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
		
	public MessageElement setInitialAztecaWebPayment (PagoServicioAztecaWebRequestTO servicioAztecaWebRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00025");
		Connector connector = ConnectorManager.getConnector(servicioAztecaWebRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AZTECAWEB_1, params);
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
	
	public MessageElement setInitialServiceAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00025");
		Connector connector = ConnectorManager.getConnector(pagoServicioAztecaWebRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AZTECAWEB_1_1, params);
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
	
	public MessageElement setStartInitialServiceAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", "00025");
		Connector connector = ConnectorManager.getConnector(pagoServicioAztecaWebRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AZTECAWEB_1_2, params);
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
	
	public MessageElement setDataAztecaWebPayment(PagoServicioAztecaWebRequestTO servicioAztecaWebRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", servicioAztecaWebRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", servicioAztecaWebRequestTO.getDigitoVerificador());
		params.put("cuentaCargo",servicioAztecaWebRequestTO.getCuentaCargo());
		params.put("tipoServicio",servicioAztecaWebRequestTO.getTipoServicio());
		params.put("importe", servicioAztecaWebRequestTO.getImporte());
		//hiden
		params.put("comision", servicioAztecaWebRequestTO.getComision());
		params.put("iva", servicioAztecaWebRequestTO.getIva());
		params.put("total",servicioAztecaWebRequestTO.getTotal());
		
		params.put("submit", "Continuar");
		
		Connector connector = ConnectorManager.getConnector(servicioAztecaWebRequestTO.getUser());
	
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_SERVICIO_AZTECAWEB_2, params);
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
		
	public MessageElement setConfimAztecaWebPayment(PagoServicioAztecaWebRequestTO servicioAztecaWebRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", servicioAztecaWebRequestTO.getCuentaReferencia());
		params.put("digitoVerificador", servicioAztecaWebRequestTO.getDigitoVerificador());
		params.put("cuentaCargo",servicioAztecaWebRequestTO.getCuentaCargo());
		params.put("tipoServicio",servicioAztecaWebRequestTO.getTipoServicio());
		params.put("importe", servicioAztecaWebRequestTO.getImporte());
		params.put("fechaAplicacion", servicioAztecaWebRequestTO.getFechaAplicacion());
		params.put("total", servicioAztecaWebRequestTO.getTotal());
		params.put("comision", servicioAztecaWebRequestTO.getComision());
		params.put("iva", servicioAztecaWebRequestTO.getIva());
		
		//validacion del dispositivo de seguridad
		if(servicioAztecaWebRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", servicioAztecaWebRequestTO.getTokenCode());
		}else if (servicioAztecaWebRequestTO.getOptionDispositive().equals(String.valueOf(FINGER_DISPOSITIVE))){
			params.put("clave", servicioAztecaWebRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, servicioAztecaWebRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(servicioAztecaWebRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AZTECAWEB_3, params);
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
	
	public MessageElement setExecuteWaitAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
						
		Connector connector = ConnectorManager.getConnector(pagoServicioAztecaWebRequestTO.getUser());
	
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AZTECAWEB_3_1, null);
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
	
	public MessageElement setExecuteAztecaWebPayment(PagoServicioAztecaWebRequestTO pagoServicioAztecaWebRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");				
		Connector connector = ConnectorManager.getConnector(pagoServicioAztecaWebRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_AZTECAWEB_3_2, params);
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
	
	
	public MessageElement setEliminarFrecuentesEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();		

		try {
			Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
			
			params.put("paso", frecuentesRequestTO.getPaso());
			params.put("referenciaSeleccionada", frecuentesRequestTO.getReferencia());
			params.put("servicio", frecuentesRequestTO.getServicio());
			params.put("user_id", frecuentesRequestTO.getUserId());
			params.put("method", "elimina");
			params.put("servicio", frecuentesRequestTO.getServicio());
			
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_ELIMINA, params);
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
	
	public MessageElement setModificarFrecuentesParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();		
		try {
			Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
			
			params.put("paso", frecuentesRequestTO.getPaso());
			params.put("referenciaSeleccionada", frecuentesRequestTO.getReferencia());
			params.put("servicio", frecuentesRequestTO.getServicio());
			params.put("user_id", frecuentesRequestTO.getUserId());
			params.put("method", frecuentesRequestTO.getMethod());
			params.put("servicio", frecuentesRequestTO.getServicio());
			params.put(frecuentesRequestTO.getTipoServicio(), frecuentesRequestTO.getReferenciaServicio());
			
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_AGREGA, params);			
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
	
	public MessageElement setModificarFrecuentesValidacionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();		
		try {
			Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
			
			params.put("paso", frecuentesRequestTO.getPaso());
			params.put("referenciaSeleccionada", frecuentesRequestTO.getReferencia());
			params.put("servicio", frecuentesRequestTO.getServicio());
			params.put("user_id", frecuentesRequestTO.getUserId());
			params.put("method", frecuentesRequestTO.getMethod());
			params.put(frecuentesRequestTO.getTipoServicio(), frecuentesRequestTO.getReferenciaServicio());
			
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_MODIFICA, params);
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
	
	public MessageElement setModificarFrecuentesEjecucionParametrizado(FrecuentesRequestTO frecuentesRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		try {
			Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());	
			params.put("method", frecuentesRequestTO.getMethod());

			//validacion del dispositivo de seguridad
			if(frecuentesRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
				params.put("tokencode", frecuentesRequestTO.getTokencode());	
			}else{
				params.put("clave", frecuentesRequestTO.getClave());
				params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
				params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
				params.put(DigitalFinger.DIGITAL_FINGET_USER, frecuentesRequestTO.getUser());
				params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "transferenciasTefsForm");
			}
			
			String xml = connector.sendRequest(CUENTAS_FRECUENTES_MODIFICA, params);
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
	
	public void setInicioDonativo(DonativosRequestTO donativosRequestTO) throws DAOException, SessionExpiredException {
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(donativosRequestTO.getUser());

		try {
			connector.sendRequest(DONATIVO_1, params);
			//connector.sendJSPRequest("/donativos/inicio.jsp", params);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public MessageElement setDonativo(DonativosRequestTO donativosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(donativosRequestTO.getUser());

		try {
			String xml = connector.sendRequest(DONATIVO_2, params);
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

	public MessageElement setDonativoPre(DonativosRequestTO donativosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(donativosRequestTO.getUser());

		try {
			connector.sendRequest(DONATIVO_2_1, params);			
			String xml = connector.sendRequest(DONATIVO_3, params);
			//connector.sendJSPRequest(DONATIVO_2_1_2, null);
			
			xml = connector.sendRequest(DONATIVO_2_1_1, params);
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

	public MessageElement setDonativoConfirmar(DonativosRequestTO donativosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("importe", donativosRequestTO.getImporte());
		params.put("origen", donativosRequestTO.getCuentaCargo());
		params.put("submit", "Continuar");
		
		Connector connector = ConnectorManager.getConnector(donativosRequestTO.getUser());

		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(DONATIVO_3, params);
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
	
	public MessageElement setDonativoEjecutar(DonativosRequestTO donativosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(donativosRequestTO.getUser());
		
		if(donativosRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", donativosRequestTO.getTokenCode());
		}else{
			params.put("clave", donativosRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, donativosRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "jugetonForm");
		}
		
		try {
			String xml = connector.sendRequest(DONATIVO_4, params);
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
	
	public MessageElement setDonativoEjecutarCmd(DonativosRequestTO donativosRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(donativosRequestTO.getUser());

		if(donativosRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", donativosRequestTO.getTokenCode());
		}else{
			params.put("clave", donativosRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, donativosRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "jugetonForm");
		}
		
		try {
			String xml = connector.sendRequest(DONATIVO_4_1, params);
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
	

	public MessageElement setDataEnvioDineroExpressAltaFrecuente(BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put( "method","confirmar");
		
		params.put( "nuevoBeneficiario","true");
		//params.put( "clienteId","");
		params.put( "nombre", frecuenteRequestTO.getNombreBeneficiario() );
		params.put( "apellidoPaterno", frecuenteRequestTO.getApellidoPaterno() );
		params.put( "apellidoMaterno", frecuenteRequestTO.getApellidoMaterno() );
		params.put( "nombreCorto", frecuenteRequestTO.getNombrecorto() );
		params.put( "fechaNacimiento", frecuenteRequestTO.getFechaNacimiento());
		//params.put( "submit", frecuenteRequestTO.getSubmitValue() );
		
		Connector connector = ConnectorManager.getConnector(frecuenteRequestTO.getUser());
		
		try {
			//connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest( DINERO_EXPRESS_FRECUENTES_ALTA_1_1, params );
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

//	public MessageElement setConfirmarEnvioDineroExpressAltaFrecuente(BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws DAOException, SessionExpiredException {
//		MessageElement messageElement = null;
//		Map<String, String> params = new HashMap<String, String>();
//		params.put( "method", "confirmar" );
//		params.put( "method", frecuenteRequestTO.getMethod() );
//		Connector connector = ConnectorManager.getConnector(frecuenteRequestTO.getUser());
//		
//		try {
//			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
//			String xml = connector.sendRequest( DINERO_EXPRESS_CONSULTA_BENEFICIARIOS, params );
//			messageElement = XMLDecode.buildXMLMessageElement(xml);
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//			throw new DAOException(e);
//		} catch (HttpException e) {			
//			e.printStackTrace();
//			throw new DAOException(e);
//		} catch (IOException e) {			
//			e.printStackTrace();
//			throw new DAOException(e);
//		} catch (XmlDecodeException e) {
//			e.printStackTrace();
//			throw new DAOException(e);
//		}
//		return messageElement;
//	}
	
	public MessageElement setEnvioDineroExpressAltaFrecuenteEjecutar(BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put( "method", "acepta" );
		Connector connector = ConnectorManager.getConnector(frecuenteRequestTO.getUser());
		
		if(frecuenteRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", frecuenteRequestTO.getTokenCode());
		}else{
			params.put("clave", frecuenteRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, frecuenteRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "altaFrecuentesDineroExpressForm");
		}
		
		try {
			String xml = connector.sendRequest( DINERO_EXPRESS_FRECUENTES_ALTA_1_3, params );
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


	public MessageElement setEnvioDineroExpressAltaFrecuenteEjecutarCmd(BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put( "method", "acepta" );
		Connector connector = ConnectorManager.getConnector(frecuenteRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest( DINERO_EXPRESS_FRECUENTES_ALTA_1_4, params );
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
		
	public MessageElement setEnvioDineroExpressConsultarFrecuentesEjecutar(BeneficiarioDineroExpressRequestTO frecuenteRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put( "method", "inicio" );
		if( frecuenteRequestTO.getIndice() != null )
			params.put( "idxAlfabetico", frecuenteRequestTO.getIndice() );
		Connector connector = ConnectorManager.getConnector(frecuenteRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest( DINERO_EXPRESS_CONSULTA_BENEFICIARIOS, params );
			messageElement = XMLDecode.buildXMLMessageElement(xml);
			
			try{
				System.out.println(messageElement.getAsString());
			}catch(Exception es){es.printStackTrace();}
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

	
	public MessageElement setEnvioDineroExpressInicio(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method","inicio");
		try {
			String xml = connector.sendRequest(ENVIO_DINERO_EXPRESS_INICIO, params);
			System.out.println("XXXXXXXXXXXXXXXXXXXInicioDEXXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
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
	
	public MessageElement setEnvioDineroExpressSolicitud(EnvioDineroExpressRequestTO dineroExpressRequestTO,boolean banderaContrato) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "pais");
		
		if(banderaContrato && (dineroExpressRequestTO.getAceptarContrato()!=null && dineroExpressRequestTO.getAceptarContrato().trim().equalsIgnoreCase("si")))
			params.put("contratoFlag","true");
		
		try {
			String xml = connector.sendRequest(ENVIO_DEX_PAIS, params);
			System.out.println("XXXXXXXXXXXXXXXXXXXSolicitudDEXXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
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
	
	
	public MessageElement setEnvioDineroExpressValidacion(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "paso2");
		params.put("cuentaCargo", dineroExpressRequestTO.getCuenta_cargo() );
		params.put("nombre",  dineroExpressRequestTO.getNombre_beneficiario() );
		params.put("aPaterno",  dineroExpressRequestTO.getApellido_paterno() );
		params.put("aMaterno",  dineroExpressRequestTO.getApellido_materno() );
		
		params.put("pais", dineroExpressRequestTO.getPais());
		params.put("estado", dineroExpressRequestTO.getEstado() );
		params.put("ciudad",dineroExpressRequestTO.getCiudad());
		params.put("agente",dineroExpressRequestTO.getAgente());
		params.put("sucursal", dineroExpressRequestTO.getSucursal());
		
		params.put("idBeneficiario",dineroExpressRequestTO.getIdBeneficiario());
		params.put("montoFormateado", dineroExpressRequestTO.getMonto_enviar() );
		
		try {
			
//			Map<String, String>secLevelParams=new HashMap<String, String>();
//			secLevelParams.put("sessionAttributeName", DEX_ENV_NODIRIGIDO);
//			secLevelParams.put("sessionAttributeValue", "noDirigido");
//		    secLevelParams.put("sessionAttributeType", String.class.getCanonicalName());
			//String xml=connector.sendRequest(SESSION_PARAMETER, secLevelParams);		
			//System.out.println("Sessionparam>... "+xml);
		    
			String xml= connector.sendRequest(ENVIO_DINERO_EXPRESS, params);
			System.out.println("XXXXXXXXXXXXXXXXXXValidacionDEXXXXXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
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
	
	public MessageElement setEnvioDineroExpressEjecucion(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {

		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "paso3");
		
		if(dineroExpressRequestTO.getOpcion_seguridad().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", dineroExpressRequestTO.getTokenCode());
		}else{
			params.put("clave", dineroExpressRequestTO.getClave_seguridad());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, dineroExpressRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		try {
			
			String xmltelsCliente= connector.sendRequest(DINERO_EXPRESS_TELEFONOS_CLIENTE,null);
			
			System.out.println(xmltelsCliente);
			
			
			
			String xml = connector.sendRequest(ENVIO_DINERO_EXPRESS_PASO3, params);
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);


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
		}catch (XmlDecodeException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		return messageElement;


		
	}
	
	
	
	
	
	
/*	public MessageElement setEnvioDineroExpressPaises(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "buscarPaises");
		MessageElement messageElement;
		try {
			String xml = connector.sendRequest(PATH_UPLOAD_VAR_SESSION,null);
			System.out.println("XXXXXXXXXXXXXXXXXXPaisesDEXXXXXXXXXXXXXXXXXXXx");
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
		} catch(XmlDecodeException decodeException){
			throw new DAOException(decodeException);
		}

		return messageElement;
	}*/
	
	
	public MessageElement  setEnvioDineroExpressEstados(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "estado");
		params.put("pais", dineroExpressRequestTO.getPais());
		try {
			String xml = connector.sendRequest(ENVIO_DINERO_EXPRESS_ESTADOS_CIUDAD_AGENTE_SUCURSAL, params);
			System.out.println("XXXXXXXXXXXXXXXXXXXEstadosDEXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
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
		} catch(XmlDecodeException decodeException){
			decodeException.printStackTrace();
			throw new DAOException(decodeException);			
		}

		return messageElement;
	}
	
	public MessageElement  setEnvioDineroExpressCiudades(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "ciudad");
		params.put("pais", dineroExpressRequestTO.getPais());
		params.put("estado", dineroExpressRequestTO.getEstado());
		try {
			String xml = connector.sendRequest(ENVIO_DINERO_EXPRESS_ESTADOS_CIUDAD_AGENTE_SUCURSAL, params);
			System.out.println("XXXXXXXXXXXXXXXXXXXEstadosDEXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
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
		} catch(XmlDecodeException decodeException){
			decodeException.printStackTrace();
			throw new DAOException(decodeException);			
		}

		return messageElement;
	}

	public MessageElement  setEnvioDineroExpressAgente(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "agente");
		params.put("pais", dineroExpressRequestTO.getPais());
		params.put("estado", dineroExpressRequestTO.getEstado());
		params.put("ciudad", dineroExpressRequestTO.getCiudad());
		try {
			String xml = connector.sendRequest(ENVIO_DINERO_EXPRESS_ESTADOS_CIUDAD_AGENTE_SUCURSAL, params);
			System.out.println("XXXXXXXXXXXXXXXXXXXEstadosDEXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
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
		} catch(XmlDecodeException decodeException){
			decodeException.printStackTrace();
			throw new DAOException(decodeException);			
		}

		return messageElement;
	}
	
	public MessageElement  setEnvioDineroExpressSucursal(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "sucursal");
		params.put("pais", dineroExpressRequestTO.getPais());
		params.put("estado", dineroExpressRequestTO.getEstado());
		params.put("ciudad", dineroExpressRequestTO.getCiudad());
		params.put("agente", dineroExpressRequestTO.getAgente());
		try {
			String xml = connector.sendRequest(ENVIO_DINERO_EXPRESS_ESTADOS_CIUDAD_AGENTE_SUCURSAL, params);
			System.out.println("XXXXXXXXXXXXXXXXXXXEstadosDEXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
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
		} catch(XmlDecodeException decodeException){
			decodeException.printStackTrace();
			throw new DAOException(decodeException);			
		}

		return messageElement;
	}
	
	public MessageElement enviaCorreoConfirmacionDineroExpress(EnvioDineroExpressRequestTO dineroExpressRequestTO)throws DAOException,SessionExpiredException{
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		MessageElement messageElement=new MessageElement();
		params.put("cuentaCorreo",dineroExpressRequestTO.getEmail());
		params.put("mensajeCorreo",dineroExpressRequestTO.getMensajeMail());
		params.put("method","enviaCorreo");
		
		try{
			
			String xml=connector.sendRequest(ENVIO_DINERO_EXPRESS_MENSAJE_CORREO, params);
			
			System.out.println("//////////////////Correo de confirmacion DEX////////////////");
			messageElement = XMLDecode.buildXMLMessageElement(xml);			
			
		}catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		}catch(XmlDecodeException decodeException){
			decodeException.printStackTrace();
			throw new DAOException(decodeException);			
		}
		return messageElement;		
	}

	public EnvioDineroExpressResponseTO setEnvioDineroExpressCalculoComision(EnvioDineroExpressRequestTO dineroExpressRequestTO) throws DAOException, SessionExpiredException {
		
		EnvioDineroExpressResponseTO dineroExpressTO = new EnvioDineroExpressResponseTO();
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(dineroExpressRequestTO.getUser());
		params.put("method", "cotizar");
		params.put("idPais", dineroExpressRequestTO.getPais());
		params.put("idEstado", dineroExpressRequestTO.getEstado());
		params.put("idCiudad", dineroExpressRequestTO.getCiudad());
		params.put("montoAEnviar", dineroExpressRequestTO.getMonto_enviar());
		
		try {
			String xml = connector.sendRequest(ENVIO_DINERO_EXPRESS, params);
			System.out.println("XXXXXXXXXXXXXXXXCalculoComisionXXXXXXXXXXXXXXXXXXXXx");
			System.out.println("XML:"+xml);
			dineroExpressTO = DineroExpressUtils.getCalculoComisiones(xml);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (HttpException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {			
			e.printStackTrace();
			throw new DAOException(e);
		} catch (EliteDataException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} 
		
		return dineroExpressTO;
	}

	public MessageElement setInitialParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		String servicio = parametrizableRequestTO.getTipoServicio();
		params.put("servicio", servicio);
		Connector connector = ConnectorManager.getConnector(parametrizableRequestTO.getUser());
		
		try {
			connector.sendJSPRequest(PAGO_SERVICIO_PARAMETRIZABLE_INICIAL, null);
			
			String xml = connector.sendRequest(PAGO_SERVICIO_PARAMETRIZABLE_1_1, params);
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
	
	public MessageElement setInitialServiceParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		String servicio = parametrizableRequestTO.getTipoServicio();
		params.put("servicio", servicio);
		Connector connector = ConnectorManager.getConnector(parametrizableRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_PARAMETRIZABLE_1_2, params);
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
	
	public MessageElement setStartInitialServiceParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("servicio", parametrizableRequestTO.getTipoServicio());
		Connector connector = ConnectorManager.getConnector(parametrizableRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_PARAMETRIZABLE_1_3, params);
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

	public MessageElement setFormDataParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmarSaldo");
		params.put("cuentaReferencia", parametrizableRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", parametrizableRequestTO.getCuentaCargo());
		params.put("tipoServicio", parametrizableRequestTO.getTipoServicio());
		params.put("importe", parametrizableRequestTO.getImporte());
		params.put("digitoVerificador", parametrizableRequestTO.getDigitoVerificador());
		StringBuilder cadena = new StringBuilder();
		for(int i=parametrizableRequestTO.getId_servicio().length(); i<5;i++){
			cadena.append("0");
		}
		cadena.append(parametrizableRequestTO.getId_servicio());
		System.out.println("empresaCode.-"+cadena.toString());
		params.put("empresaCode", cadena.toString());
		params.put("submit", "Continuar");

		Connector connector = ConnectorManager.getConnector(parametrizableRequestTO.getUser());
		
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest("/servicios/parametrizable/elektraps/confirmarSaldo.elite", params);
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
	
	public MessageElement setTokenParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "passwordCheck");
		params.put("cuentaReferencia", parametrizableRequestTO.getCuentaReferencia());
		params.put("cuentaCargo", parametrizableRequestTO.getCuentaCargo());
		params.put("tipoServicio", parametrizableRequestTO.getTipoServicio());
		params.put("fechaAplicacion", parametrizableRequestTO.getFechaAplicacion());
		params.put("importe", parametrizableRequestTO.getImporte());
		params.put("comision", parametrizableRequestTO.getComision());
		params.put("iva", parametrizableRequestTO.getIva());
		params.put("total", parametrizableRequestTO.getTotal());
		params.put("digitoVerificador", parametrizableRequestTO.getDigitoVerificador());
		//validacion del dispositivo de seguridad
		if(parametrizableRequestTO.getOptionDispositive().equals(String.valueOf(TOKEN_DISPOSITIVE))){
			params.put("tokencode", parametrizableRequestTO.getTokencode());
		}else{
			params.put("clave", parametrizableRequestTO.getClave());
			params.put(DigitalFinger.DIGITAL_FINGET_OPTION,DigitalFinger.GET_DIGITAL_FINGET);
			params.put(DigitalFinger.DIGITAL_FINGET_VALUE, "clave");
			params.put(DigitalFinger.DIGITAL_FINGET_USER, parametrizableRequestTO.getUser());
			params.put(DigitalFinger.DIGITAL_FINGET_OBJECT, "pagoServicioForm");
		}
		
		Connector connector = ConnectorManager.getConnector(parametrizableRequestTO.getUser());
		
		try {
			//String xml = connector.sendRequest(PAGO_SERVICIO_PARAMETRIZABLE_3_1, params);
			String xml = connector.sendRequest("/servicios/parametrizable/elektraps/validaPassword.elite", params);
			
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
	
	public MessageElement setExecuteParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(parametrizableRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest(PAGO_SERVICIO_PARAMETRIZABLE_3_2, params);
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
	
	public MessageElement setExecuteWaitParametrizablePayment(PagoServiciosParametrizableRequestTO parametrizableRequestTO) throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "ejecutar");
		Connector connector = ConnectorManager.getConnector(parametrizableRequestTO.getUser());
		
		try {
			//String xml = connector.sendRequest(PAGO_SERVICIO_PARAMETRIZABLE_3_3, params);
			String xml = connector.sendRequest("/servicios/parametrizable/ejecutarCmd.elite", params);
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
	
	public MessageElement getInitialToditoCardDao(PagoServiciosToditoCardRequestTO toditoCardRequestTO)throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager.getConnector(toditoCardRequestTO.getUser());
		
		try {
			String xml = connector.sendRequest("/servicios/toditocardMenu.elite", params);
			xml = connector.sendRequest("/servicios/toditocard.elite", params);
			xml = connector.sendRequest("/servicios/toditocardCmd.elite", params);
//			String xml = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><elite><javax.servlet.request.cipher_suite value=\"SSL_RSA_WITH_RC4_128_MD5\" /><com.sun.enterprise.http.sessionTracker value=\"org.apache.coyote.tomcat5.SessionTracker@8400840\" /><time value=\"1300138830912\" /><saldosAccounts type=\"collection\"><CuentaVO type=\"bean\"><movimientos type=\"collection\" /><tipoCambioVenta /><totalNPesos /><clabe value=\"127180013178102840\" /><apertura value=\"2008-08-27\" /><nombreMoneda /><numero value=\"01270172481317810284\" /><disponible value=\"2.19\" /><subproducto value=\"0017\" /><comisionServChequera value=\"0.0\" /><autorizados /><chequeras /><descripcion value=\"GUARDADITO VISTA\" /><unidadesComp value=\"0.0\" /><cotitulares type=\"collection\" /><totalNMonedas /><corte value=\"2011-03-25\" /><inversion value=\"false\" /><chequera /><producto value=\"13\" /><comisionLibCheques value=\"0.0\" /><tipoCambioCompra /><beneficiarios type=\"collection\" /><balance value=\"2.19\" /><contrato value=\"false\" /><retenido value=\"0.00\" /><moneda value=\"MXP\" /><sucursal value=\"0172\" /></CuentaVO></saldosAccounts><ebanking_web_ClienteVO type=\"bean\"><tienePlus value=\"false\" /><creditos /><privilegios value=\"1\" /><tieneOtrosCreditos value=\"false\" /><docudes value=\"PASAPORTE\" /><email value=\"cmarquezmx@gmail.com\" /><ganareMas /><inversiones type=\"collection\"><CuentaInversionVO type=\"bean\"><diasFaltantes value=\"0\" /><movimientos type=\"collection\" /><tipoCambioVenta /><montoInvertido /><totalNPesos /><plazoInversion /><tipo /><indice value=\"0\" /><clabe /><apertura /><nombreMoneda /><numero /><interesNetoGanar /><ganare value=\"false\" /><disponible value=\"0\" /><subproducto /><comisionServChequera value=\"0.0\" /><autorizados /><chequeras /><descripcion /><instruccion /><unidadesComp value=\"0.0\" /><cotitulares type=\"collection\" /><totalNMonedas /><fechaVencimiento /><numeroCuenta value=\"\" /><diasTranscurridos value=\"0\" /><corte /><fechaInicioInversion /><interesGeneradoSAFecha /><tasaBruta /><inversion value=\"false\" /><chequera /><producto /><comisionLibCheques value=\"0.0\" /><tipoCambioCompra /><balance value=\"0\" /><beneficiarios type=\"collection\" /><contrato value=\"false\" /><retenido /><tasaNeta /><moneda /><inversionAzteca value=\"false\" /><interesBrutoGanar /><sucursal /></CuentaInversionVO></inversiones><tarjetacorporativa /><numero value=\"25231473\" /><dineroExpressId value=\"0\" /><recargaTarjetas value=\"false\" /><telefono value=\"55906916\" /><ultimaActualizacionCuentas value=\"1300138833506\" /><nacimiento value=\"1976-11-25\" /><socioPlus value=\"false\" /><alias value=\"maestrodelweb\" /><apellidos value=\"MARQUEZ LOPEZ\" /><cuentas type=\"collection\"><CuentaVO type=\"bean\"><movimientos type=\"collection\" /><tipoCambioVenta /><totalNPesos /><clabe value=\"127180013178102840\" /><apertura value=\"2008-08-27\" /><nombreMoneda /><numero value=\"01270172481317810284\" /><disponible value=\"2.19\" /><subproducto value=\"0017\" /><comisionServChequera value=\"0.0\" /><autorizados /><chequeras /><descripcion value=\"GUARDADITO VISTA\" /><unidadesComp value=\"0.0\" /><cotitulares type=\"collection\" /><totalNMonedas /><corte value=\"2011-03-25\" /><inversion value=\"false\" /><chequera /><producto value=\"13\" /><comisionLibCheques value=\"0.0\" /><tipoCambioCompra /><beneficiarios type=\"collection\" /><balance value=\"2.19\" /><contrato value=\"false\" /><retenido value=\"0.00\" /><moneda value=\"MXP\" /><sucursal value=\"0172\" /></CuentaVO></cuentas><debitoCorp /><tarjetasBaseCredimax /><tieneInversiones value=\"false\" /><socPlusInfo value=\"true\" /><passwordStatus type=\"bean\"><updatedPasswordConfirmacion value=\"true\" /><passwordInicioExpired value=\"false\" /><confirmChangeDate value=\"2008-10-23\" /><passwordConfirmExpired value=\"false\" /><status value=\"7\" /><changeDate value=\"2009-04-30\" /><updatedPasswords value=\"true\" /></passwordStatus><nombre value=\"CARLOS CHRISTIAN\" /><securityTemp type=\"bean\"><validadorHuellaDigital /><maxDefaultPorDia value=\"13916.84\" /><secLevelVO type=\"bean\"><nivelFrecuentes value=\"0\" /><securityLevel value=\"6\" /><huellaMuerta type=\"bean\"><dedo value=\"0\" /><nombreDedo value=\"\" /><mano value=\"0\" /><localizada value=\"false\" /><huella /><biometrico value=\"0101017229803\" /><nombreMano value=\"\" /></huellaMuerta><nivelTransferencias value=\"1\" /><tokenstatus value=\"1\" /><nivelMovimientos value=\"6\" /></secLevelVO><validadorHuellaDigitalOperacion /><maxDiaByLevelAsDescripcion value=\"13916.84\" /><montosVO type=\"bean\"><maxInternacionalesAsString value=\"\" /><maxInternacionalesSugerido value=\"10000\" /><fechaUltimoMov value=\"Mon Mar 14 16:40:33 CDT 2011\" /><internacionales /><pagos value=\"0\" /><sumMaxDiarioCalculado value=\"13816.84\" /><maxTercerosSugerido value=\"11316.84\" /><vacio value=\"true\" /><maxPagosSugerido value=\"500\" /><maxPagos value=\"500\" /><maxInterbancarioAsString value=\"2,000.00\" /><terceros value=\"0\" /><diario value=\"0\" /><sumDiarioCalculado value=\"0\" /><maxDiarioSugerido value=\"0\" /><maxDiario value=\"13916.84\" /><maxInterbancarioSugerido value=\"2000\" /><maxPagosAsString value=\"500.00\" /><maxTercerosAsString value=\"11,316.84\" /><interbancario value=\"0\" /><maxInternacionales /><maxInterbancario value=\"2000\" /><maxTerceros value=\"11316.84\" /></montosVO><cliente value=\"25231473\" /><amountsVO type=\"bean\"><maxInternacionalesAsString value=\"\" /><maxInternacionalesSugerido value=\"10000\" /><fechaUltimoMov value=\"Mon Mar 14 16:40:33 CDT 2011\" /><internacionales /><pagos value=\"0\" /><sumMaxDiarioCalculado value=\"13816.84\" /><maxTercerosSugerido value=\"11316.84\" /><vacio value=\"true\" /><maxPagosSugerido value=\"500\" /><maxPagos value=\"500\" /><maxInterbancarioAsString value=\"2,000.00\" /><terceros value=\"0\" /><diario value=\"0\" /><sumDiarioCalculado value=\"0\" /><maxDiarioSugerido value=\"0\" /><maxDiario value=\"13916.84\" /><maxInterbancarioSugerido value=\"2000\" /><maxPagosAsString value=\"500.00\" /><maxTercerosAsString value=\"11,316.84\" /><interbancario value=\"0\" /><maxInternacionales /><maxInterbancario value=\"2000\" /><maxTerceros value=\"11316.84\" /></amountsVO></securityTemp><paterno value=\"MARQUEZ\" /><nominaExterna value=\"false\" /><securityData type=\"bean\"><validadorHuellaDigital /><maxDefaultPorDia value=\"13916.84\" /><secLevelVO type=\"bean\"><nivelFrecuentes value=\"0\" /><securityLevel value=\"6\" /><huellaMuerta type=\"bean\"><dedo value=\"0\" /><nombreDedo value=\"\" /><mano value=\"0\" /><localizada value=\"false\" /><huella /><biometrico value=\"0101017229803\" /><nombreMano value=\"\" /></huellaMuerta><nivelTransferencias value=\"1\" /><tokenstatus value=\"1\" /><nivelMovimientos value=\"6\" /></secLevelVO><validadorHuellaDigitalOperacion /><maxDiaByLevelAsDescripcion value=\"13916.84\" /><montosVO type=\"bean\"><maxInternacionalesAsString value=\"100.00\" /><maxInternacionalesSugerido value=\"100\" /><fechaUltimoMov value=\"Mon Mar 14 16:41:05 CDT 2011\" /><internacionales value=\"0\" /><pagos value=\"0\" /><sumMaxDiarioCalculado value=\"13916.84\" /><maxTercerosSugerido value=\"11316.84\" /><vacio value=\"false\" /><maxPagosSugerido value=\"500\" /><maxPagos value=\"500\" /><maxInterbancarioAsString value=\"2,000.00\" /><terceros value=\"0\" /><diario value=\"0\" /><sumDiarioCalculado value=\"0\" /><maxDiarioSugerido value=\"0\" /><maxDiario value=\"13916.84\" /><maxInterbancarioSugerido value=\"2000\" /><maxPagosAsString value=\"500.00\" /><maxTercerosAsString value=\"11,316.84\" /><interbancario value=\"0\" /><maxInternacionales value=\"100\" /><maxInterbancario value=\"2000\" /><maxTerceros value=\"11316.84\" /></montosVO><cliente value=\"25231473\" /><amountsVO type=\"bean\"><maxInternacionalesAsString value=\"100.00\" /><maxInternacionalesSugerido value=\"100\" /><fechaUltimoMov value=\"Mon Mar 14 16:41:05 CDT 2011\" /><internacionales value=\"0\" /><pagos value=\"0\" /><sumMaxDiarioCalculado value=\"13916.84\" /><maxTercerosSugerido value=\"11316.84\" /><vacio value=\"false\" /><maxPagosSugerido value=\"500\" /><maxPagos value=\"500\" /><maxInterbancarioAsString value=\"2,000.00\" /><terceros value=\"0\" /><diario value=\"0\" /><sumDiarioCalculado value=\"0\" /><maxDiarioSugerido value=\"0\" /><maxDiario value=\"13916.84\" /><maxInterbancarioSugerido value=\"2000\" /><maxPagosAsString value=\"500.00\" /><maxTercerosAsString value=\"11,316.84\" /><interbancario value=\"0\" /><maxInternacionales value=\"100\" /><maxInterbancario value=\"2000\" /><maxTerceros value=\"11316.84\" /></amountsVO></securityData><fechaRegistro /><nombreCompleto value=\"CARLOS CHRISTIAN MARQUEZ LOPEZ\" /><tarjetasBaseAlnova /><fiar value=\"false\" /><materno value=\"LOPEZ\" /><idkey value=\"00390033491\" /></ebanking_web_ClienteVO><typeName value=\"userpruebas\" /><EncoderManager type=\"bean\"><notEncodeBeans value=\"SecurityAmountsVO,MESSAGE,LOCALE,PropertyMessageResources,ActionMapping,ModuleConfigImpl,Locale\" /><changeEncoderOption /><notEncodeBeansAsCollection type=\"collection\"><item itemValue=\"SecurityAmountsVO\" /><item itemValue=\"MESSAGE\" /><item itemValue=\"LOCALE\" /><item itemValue=\"PropertyMessageResources\" /><item itemValue=\"ActionMapping\" /><item itemValue=\"ModuleConfigImpl\" /><item itemValue=\"Locale\" /></notEncodeBeansAsCollection><encodeBeansAsCollection type=\"collection\"><item itemValue=\"VO\" /><item itemValue=\"LO\" /><item itemValue=\"TO\" /><item itemValue=\"Bean\" /><item itemValue=\"Form\" /><item itemValue=\"ResultadoBusqueda\" /><item itemValue=\"ActionErrors\" /><item itemValue=\"ActionError\" /><item itemValue=\"PeriodoTarjetaBaseAlnova\" /><item itemValue=\"Element\" /><item itemValue=\"Celular\" /><item itemValue=\"Retenciones\" /><item itemValue=\"PasswordStatus\" /><item itemValue=\"Recibos\" /><item itemValue=\"Recibo\" /><item itemValue=\"ReciboDetalle\" /><item itemValue=\"ReciboCabecera\" /><item itemValue=\"ReciboMensaje\" /><item itemValue=\"Nomina\" /><item itemValue=\"ResponseConsultaRecibos\" /><item itemValue=\"ResponseConsultaNominaRetenciones\" /><item itemValue=\"CuentaLO\" /><item itemValue=\"NominaLO\" /><item itemValue=\"Response\" /><item itemValue=\"Carrier\" /><item itemValue=\"EncoderManager\" /><item itemValue=\"BaseAlnova\" /><item itemValue=\"ReciboDetalleOrdenado\" /><item itemValue=\"CompaniaView\" /></encodeBeansAsCollection><encodeBeans value=\"VO,LO,TO,Bean,Form,ResultadoBusqueda,ActionErrors,ActionError,PeriodoTarjetaBaseAlnova,Element,Celular,Retenciones,PasswordStatus,Recibos,Recibo,ReciboDetalle,ReciboCabecera,ReciboMensaje,Nomina,ResponseConsultaRecibos,ResponseConsultaNominaRetenciones,CuentaLO,NominaLO,Response,Carrier,EncoderManager,BaseAlnova,ReciboDetalleOrdenado,CompaniaView\" /><status value=\"bean rules  29, not load bean rules 7\" /><rulesSize value=\"29\" /><notLoadRulesSize value=\"7\" /><encoderOption /><XMLEncoder value=\"com.bancoazteca.elite.commons.xml.NewXMLEncoder@426c426c\" /></EncoderManager><com.bancoazteca.ebanking.web.security.session.LoginServlet_credentialsKey value=\"1300138830581\" /><com.bancoazteca.ebanking.web.security.session.LoginServlet_pageKey value=\"/eBanking/consultas/inicioMenu.do\" /><javax.servlet.request.key_size value=\"128\" /><bannerPromocion value=\"ganare\" /><sessionManagerForm type=\"bean\"><accion value=\"abrir\" /><servletWrapper value=\"org.apache.struts.action.ActionServletWrapper@4fc24fc2\" /><multipartRequestHandler /></sessionManagerForm><test value=\"true\" /><DatosEliteVO type=\"bean\"><jspForward value=\"/servicios/servicioDisponible.do?servicio=00023\" /><errorDescription value=\"\" /><errorCode value=\"0\" /></DatosEliteVO></elite>";
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
	
	// Pago de Tarjeta Azteca

	public MessageElement solicitudPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO)throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = null;
		Map<String, String> params = new HashMap<String, String>();
		Connector connector = ConnectorManager
				.getConnector(pagoTarjetaAztecaRequestTO.getUser());
		params.put("method", "inicio");

		try {

			connector.sendRequest(PAGO_TARJETA_AZTECA_MENU, null);
			connector.sendRequest(PAGO_TARJETA_AZTECA_INICIO, null);
			connector.sendRequest(PAGO_TARJETA_AZTECA_INICIO_PRE, null);
			xml = connector.sendRequest(PAGO_TARJETA_AZTECA_DATOS, params);
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

	public MessageElement validacionPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO)throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "confirmar");
		params.put("origen", pagoTarjetaAztecaRequestTO.getOrigen());
		params.put("tarjeta", pagoTarjetaAztecaRequestTO.getTarjeta());
		params.put("importe", pagoTarjetaAztecaRequestTO.getImporte());
		params.put("submit", "Pagar");

		Connector connector = ConnectorManager
				.getConnector(pagoTarjetaAztecaRequestTO.getUser());

		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(PAGO_TARJETA_AZTECA_CONFIRMAR,params);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageElement;
	}

	public MessageElement confirmacionPagoTarjetaAzteca(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO)throws DAOException, SessionExpiredException {
		
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("method", "pagar");
		params.put("importe", pagoTarjetaAztecaRequestTO.getImporte());
		params.put("origen", pagoTarjetaAztecaRequestTO.getOrigen());
		params.put("tarjeta", pagoTarjetaAztecaRequestTO.getTarjeta());
		params.put("comision", "0");
		params.put("tokencode", pagoTarjetaAztecaRequestTO.getTokenCode());

		Connector connector = ConnectorManager
				.getConnector(pagoTarjetaAztecaRequestTO.getUser());

		try {
			String xml = connector.sendRequest(PAGO_TARJETA_AZTECA_PAGAR,params);
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

	public MessageElement consultaCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,SessionExpiredException {
		MessageElement messageElement = null;
		String xml = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("tipo", "tdc");
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());
		try {
			connector.sendRequest(PAGO_TARJETA_AZTECA_FRECUENTE, params);
			xml = connector.sendRequest(PAGO_TARJETA_AZTECA_FRECUENTE_PRE,params);
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

	// Envio e email para pago de tarjeta Azteca

	public MessageElement envioMail(PagoTarjetaAztecaRequestTO pagoTarjetaAztecaRequestTO)throws DAOException, SessionExpiredException {
		MessageElement messageElement = null;
		String xml = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", pagoTarjetaAztecaRequestTO.getEmail());
		params.put("mensaje", pagoTarjetaAztecaRequestTO.getMensaje());
		params.put("submit", "Enviar");
		Connector connector = ConnectorManager
				.getConnector(pagoTarjetaAztecaRequestTO.getUser());
		try {
			xml = connector.sendRequest(PAGO_TARJETA_AZTECA_MAIL, params);
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
	
	
	
	
	public MessageElement AgregaCuentasFrecuentesTarjetasAzteca(FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();

		params.put("refresh", "false");
		params.put("method", "verificaNueva");
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias",frecuentesRequestTO.getAlias());
		params.put("submit","Agregar");

		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());
		try {
			String xml = connector.sendRequest(AGREGAR_CUENTAS_FRECUENTES,params);
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

	public MessageElement iniciarEditarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("refresh", "false");
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());
		
		params.put("claveSwift","" );
		params.put("beneficiario", "");

		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendRequest(AGREGAR_CUENTAS_FRECUENTES,params);
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

	public MessageElement prepararEjecutarEditarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("refresh", "false");
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());
		params.put("submit","Modificar");

		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendServletRequest(PREPARAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA,params);
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

	public MessageElement ejecutarEditarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());
		params.put("tokencode", frecuentesRequestTO.getTokenCode());

		

		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendServletRequest(PREPARAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA,params);
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
	
	
	public MessageElement ejecutarExtraEditarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("tipo", "tdc");
		
		

		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendServletRequest(EJECUTAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA,params);
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
	
	public MessageElement EjecutarAgregaCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("refresh", "true");
		params.put("method", "addFrequentAccount");
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias",frecuentesRequestTO.getAlias());
		params.put("index","");
		params.put("tokencode", frecuentesRequestTO.getTokenCode());
		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());
		try {
			String xml = connector.sendRequest(
					AGREGAR_CUENTAS_FRECUENTES, params);
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

	//Eliminacion de tarjetas frecuentes
	
	public MessageElement iniciarEliminarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("refresh", "false");
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());
		
		params.put("claveSwift","" );
		params.put("beneficiario", "");

		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendRequest(AGREGAR_CUENTAS_FRECUENTES,params);
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
	
	
	public MessageElement prepararEjecutarEliminarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("refresh", "false");
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());

		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendServletRequest(PREPARAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA,params);
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

	public MessageElement ejecutarEliminarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("tipo", "tdc");
		

		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendServletRequest(EJECUTAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA,params);
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
	
	
	public MessageElement ejecutarExtraEliminarCuentasFrecuentesTarjetasAzteca(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("tipo", "tdc");
		
		

		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			String xml = connector.sendServletRequest(EJECUTAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA,params);
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

	
	public MessageElement validarAltaCuentasFrecuentes(
			FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = new HashMap<String, String>();

		params.put("refresh", "false");
		params.put("method", "verificaNueva");
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias",frecuentesRequestTO.getAlias());
		params.put("submit","Agregar");

		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO.getUser());
		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendRequest(AGREGAR_CUENTAS_FRECUENTES,params);
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

	
	
	public MessageElement validarEdicionCuentasFrecuentes(FrecuentesAztecaRequestTO frecuentesRequestTO) throws DAOException,
			SessionExpiredException {
		MessageElement messageElement = null;
		Map<String, String> params = frecuentesRequestTO.getParametros();
		params.put("refresh", "false");
		params.put("index", frecuentesRequestTO.getIndex());
		params.put("destino", frecuentesRequestTO.getTarjeta());
		params.put("alias", frecuentesRequestTO.getAlias());
		params.put("submit","Modificar");

		
		Connector connector = ConnectorManager.getConnector(frecuentesRequestTO
				.getUser());

		try {
			connector.sendRequest(PATH_UPLOAD_VAR_SESSION, null);
			String xml = connector.sendServletRequest(PREPARAR_EDITAR_CUENTAS_FRECUENTES_PAGO_TARJETA_AZTECA,params);
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

}
