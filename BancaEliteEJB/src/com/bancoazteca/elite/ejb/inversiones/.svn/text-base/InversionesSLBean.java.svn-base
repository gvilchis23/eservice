package com.bancoazteca.elite.ejb.inversiones;

import static com.bancoazteca.elite.ejb.inversiones.InversionesGenericException.LEVEL_WEB_SERVICES;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ActualizacionSaldoPorCuentaRequestTO;
import com.bancoazteca.elite.beans.ActualizacionSaldoPorCuentaResponseTO;
import com.bancoazteca.elite.beans.BeneficiarioTO;
import com.bancoazteca.elite.beans.ConciliacionEliteResponseTO;
import com.bancoazteca.elite.beans.ConciliacionRequestTO;
import com.bancoazteca.elite.beans.ConciliacionTASResponseTO;
import com.bancoazteca.elite.beans.Conciliacion_Concentrado_ResponseTO;
import com.bancoazteca.elite.beans.Conciliacion_ResponseTO;
import com.bancoazteca.elite.beans.LoginConciliacionRequestTO;
import com.bancoazteca.elite.beans.LoginConciliacionResponseTO;
import com.bancoazteca.elite.ejb.alnova.beans.B402_ActualizacionSaldoPorCuentaRequest;
import com.bancoazteca.elite.ejb.alnova.beans.B402_ActualizacionSaldoPorCuentaResponse;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.inversiones.beans.AltaClienteReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentaEjeEliteEntradaTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentaEjeEliteRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentaReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentasEntradaTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentasRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AperturaCuentasResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.AsignacionesComprobanteTO;
import com.bancoazteca.elite.ejb.inversiones.beans.CompraInversionReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.CompraInversionReportosResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ComprobanteInversionReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ComprobanteInversionReportosResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.FechaAperturaReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.FechaAperturaReportosResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosPlazoTasaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosPlazoTasaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosValidaPlazoTasaReponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosValidaPlazoTasaTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ResumenOperacionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ResumenOperacionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ResumenResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B011_BusquedaCentroContableRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B011_BusquedaCentroContableResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B520_TransaccionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.BB02_AperturaCuentaEjeEliteRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.BB02_AperturaCuentasResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.P011_DatosClienteRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.P011_DatosClienteResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.AsignacionesComprobanteInversionReportosResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAltaClienteRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAperturaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAperturaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosComprobanteInversionResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosConciliacionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosConciliacionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosFechaAperturaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosFechaAperturaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosPlazoTasaResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosResumenOperacionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosResumenOperacionesResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosValidaPlazoTasaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.utils.InversionRule;
import com.bancoazteca.elite.ejb.inversiones.utils.ValidacionReportos;
import com.bancoazteca.elite.inversiones.db.dao.ConciliacionesDAO;
import com.bancoazteca.elite.inversiones.db.dao.InversionesDAOAbstracto;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;

public class InversionesSLBean implements SessionBean {

	private static Logger $log=Logger.getLogger(InversionesSLBean.class);
	private static final Map<String,String> mapaActividadEconomica=new HashMap<String,String>();
	private static final PropertiesService properties=PropertiesService.getInstance();
	private static final String ALNOVA_PROPERTIES="Alnova.properties";
	public static final String OPERATION_706_RETENCION_ALNOVA = "160";  
	public static final String COD_OPERACION_TRANSFERENCIA_CARGO			=	"169";
	public static final String COD_OPERACION_TRANSFERENCIA_DEPOSITO			=	"160";

	static{
		iniciaMapaActividadEconomica();
	}

	private static final long serialVersionUID = 1L;
	/** The session context */

	@SuppressWarnings("unused")
	private SessionContext context;

	public InversionesSLBean() {
		// TODO Auto-generated constructor stub
	}

	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	public void setSessionContext(SessionContext newContext)
	throws EJBException {
		context = newContext;
	}

	public void ejbCreate() throws CreateException {

	}


	public InversionesResponseTO compra_inversion_reportos(InversionesRequestTO request) throws InversionesGenericException, IOException{

		$log.info("--------------------- Compra Inversion Reportos ---------------------");

		CompraInversionReportosRequestTO compraInversionReportosRequestTO=(CompraInversionReportosRequestTO)request;
		TransactionsManager transactionsManager=new TransactionsManager();
		ValidacionReportos validacionReportos=new ValidacionReportos();
		validacionReportos.apertura(compraInversionReportosRequestTO);

		/*********************************** Retencion Alnova *****************************************/

		B706_RegistroRetencionRequestTO registroRetencionRequestTO=new B706_RegistroRetencionRequestTO();
		registroRetencionRequestTO.setCodigoCuentaCliente(compraInversionReportosRequestTO.getCuentaCargo());
		try {
			registroRetencionRequestTO.setDiasRetencion(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.dias.retencion"));
		} catch (IOException e1) {
			InversionesExceptionTO exceptionTO = new InversionesExceptionTO();
			exceptionTO.setMessage("Alnova.properties DO NOT EXIST webservice.alnova.config.dias.retencion ");
			throw new InversionesGenericException(exceptionTO);
		}		
		registroRetencionRequestTO.setMonto(InversionRule.formateaMontoRetencion(compraInversionReportosRequestTO.getMonto()));
		registroRetencionRequestTO.setPortal(compraInversionReportosRequestTO.getPortalSolicitante());
		registroRetencionRequestTO.setIdAlnova(compraInversionReportosRequestTO.getIdAlnova());

		B706_RegistroRetencionResponseTO retencionAlnovaResponseTO=retencion_alnova(registroRetencionRequestTO);
		String mensajeAlnova=retencionAlnovaResponseTO.getMensaje();
		$log.info("Numero Retencion: "+retencionAlnovaResponseTO.getNumeroRetencion());
		if(mensajeAlnova.toLowerCase().contains("error")){
			String codigo = Formatter.getCodigoErrorAlnova(mensajeAlnova);
			$log.info("Error alnova: " + codigo);			
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);
			exceptionTO.setMessage("Lo sentimos, ha ocurrido un error en el proceso. "+codigo);
			throw new InversionesGenericException(exceptionTO);
		}

		/*********************************** Inversion Reportos *****************************************/
		ReportosAperturaResponseTO aperturaResponseTO=null;
		CompraInversionReportosResponseTO response=null;
		try {
			ReportosAperturaRequestTO reportosAperturaTO=new ReportosAperturaRequestTO();
			reportosAperturaTO.setCuentaCargo(compraInversionReportosRequestTO.getCuentaCargo());
			reportosAperturaTO.setFechaOperacion(retencionAlnovaResponseTO.getFechaOperacion());
			reportosAperturaTO.setFolioRetencionAlnova(retencionAlnovaResponseTO.getNumeroRetencion());
			reportosAperturaTO.setNumeroIdentificacionAlnova(compraInversionReportosRequestTO.getIdAlnova());
			reportosAperturaTO.setMontoInversion(compraInversionReportosRequestTO.getMonto());
			reportosAperturaTO.setPlazo(compraInversionReportosRequestTO.getPlazo());
			reportosAperturaTO.setPortal(compraInversionReportosRequestTO.getPortalSolicitante());
			String tasaB=validacionReportos.formatoTasaBruta(compraInversionReportosRequestTO.getTasaBruta());
			reportosAperturaTO.setTasaBruta(tasaB);
			reportosAperturaTO.setIdRetencion(retencionAlnovaResponseTO.getIdRetencion());
			aperturaResponseTO= transactionsManager.compraInversionReportos(reportosAperturaTO);
			response=new CompraInversionReportosResponseTO();
			response.setFolioOperacion(aperturaResponseTO.getFolioOperacion());
			response.setIdAlnova(aperturaResponseTO.getIdAlnova());
			response.setNumeroDeContrato(aperturaResponseTO.getNumeroContrato());

			$log.info("Folio de operacion: "+response.getFolioOperacion());
			$log.info("Id Alnova: "+response.getIdAlnova());
			$log.info("Numero de contrato: "+response.getNumeroDeContrato());
		}catch(InversionesGenericException e){
			if(e.getInversionesExceptionTO().getLevel().equals(InversionesGenericException.LEVEL_WEB_SERVICE_CONNECTION)
				||e.getInversionesExceptionTO().getLevel().equals(InversionesGenericException.LEVEL_WEB_SERVICES)
				){
				if(e.getInversionesExceptionTO().getLevel().equals(InversionesGenericException.LEVEL_WEB_SERVICE_CONNECTION)){
					$log.info("Por el momento no esta disponible el servicio de Reportos");
				}else if (e.getInversionesExceptionTO().getLevel().equals(InversionesGenericException.LEVEL_WEB_SERVICES)){
					$log.info("La respuesta de reportos contiene errores");
				}
				$log.info("Eliminando Retencion "+retencionAlnovaResponseTO.getNumeroRetencion()+" ...");

				B756_QuitarRetencionRequest requestRetencion;

				requestRetencion= new B756_QuitarRetencionRequest();
				requestRetencion.setCodigoCuentaCliente(compraInversionReportosRequestTO.getCuentaCargo());
				requestRetencion.setCodigoMonedaExtranjera(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.codigo.moneda.extrangera"));
				requestRetencion.setFree(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.free"));
				requestRetencion.setMonto(InversionRule.formateaMontoRetencion(compraInversionReportosRequestTO.getMonto()));
				requestRetencion.setNumeroRetencion(retencionAlnovaResponseTO.getNumeroRetencion());
				requestRetencion.setPapel(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.papel"));
				requestRetencion.setRemueveRetencionParcialTotal(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.codigo.remove"));

				B756_QuitarRetencionResponse quitarRetencionResponse= quitar_retencion(requestRetencion,compraInversionReportosRequestTO.getIdAlnova(),compraInversionReportosRequestTO.getPortalSolicitante());

				$log.info("Retencion "+ retencionAlnovaResponseTO.getNumeroRetencion()+" status");
				$log.info("Codigo Mensaje: "+quitarRetencionResponse.getCodigoMensaje());
				$log.info("Mensaje: "+quitarRetencionResponse.getMensaje());
				$log.info("Mensaje Real: "+quitarRetencionResponse.getMensajeReal());
			}

			throw e;
		}

		return response;
	}

	public InversionesResponseTO plazo_tasa_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException
	{
		$log.info("--------------------- Obtencion de plazo y tasa -----------------------");

		ReportosPlazoTasaRequestTO plazoTasaRequestTO=(ReportosPlazoTasaRequestTO)inversionesRequestTO;
		ValidacionReportos validacionReportos= new ValidacionReportos();
		validacionReportos.plazoTasa(plazoTasaRequestTO);

		TransactionsManager transactionsManager=new TransactionsManager();
		ReportosPlazoTasaResponse plazoTasaResponse= transactionsManager.plazoTasaReportos(plazoTasaRequestTO);
		ReportosPlazoTasaResponseTO plazoTasaResponseTO=new ReportosPlazoTasaResponseTO();

		plazoTasaResponseTO.setFechaRegistro(plazoTasaResponse.getFechaRegistro());
		plazoTasaResponseTO.setPlazo(plazoTasaResponse.getPazo());
		plazoTasaResponseTO.setTasaBruta(plazoTasaResponse.getTasa());

		return plazoTasaResponseTO;
	}


	public void alta_cliente_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException	
	{

		$log.info("--------------------- Alta de cliente en reportos -----------------------");

		AltaClienteReportosRequestTO altaClienteRequestTO=(AltaClienteReportosRequestTO)inversionesRequestTO;
		TransactionsManager transactionsManager=new TransactionsManager();

		P011_DatosClienteRequest clienteRequest=new P011_DatosClienteRequest();
		clienteRequest.setNumeroCliente(altaClienteRequestTO.getIdAlnova());
		P011_DatosClienteResponse clienteResponse;
		clienteResponse=transactionsManager.datosClienteAlnova(clienteRequest);

		ReportosAltaClienteRequestTO altaClienteReportosRequestTO=new ReportosAltaClienteRequestTO();
		altaClienteReportosRequestTO.setActividadEconomica(mapaActividadEconomica.get(clienteResponse.getActividadEconomica().trim()));
		altaClienteReportosRequestTO.setApellidoMaterno(clienteResponse.getApellidoMaterno());
		altaClienteReportosRequestTO.setApellidoPaterno(clienteResponse.getApellidoPaterno());
		altaClienteReportosRequestTO.setCalleNumero(clienteResponse.getCalle()+" "+clienteResponse.getNumeroDireccion());
		altaClienteReportosRequestTO.setCiudad(clienteResponse.getCiudad());
		altaClienteReportosRequestTO.setCodigoPostal(clienteResponse.getCodigoPostal());
		altaClienteReportosRequestTO.setColonia(clienteResponse.getColonia());
		altaClienteReportosRequestTO.setCorreoElectronico(clienteResponse.getCorreoElectronico());
		altaClienteReportosRequestTO.setCuentaCargo(altaClienteRequestTO.getCuentaCargo());
		altaClienteReportosRequestTO.setCuentaClabe(altaClienteRequestTO.getCuentaClabe());
		altaClienteReportosRequestTO.setCurp(clienteResponse.getCURP());
		altaClienteReportosRequestTO.setDelegacion(clienteResponse.getDelegacion());

		String fecha=clienteResponse.getFechaRegistro();
		ValidacionReportos validacionReportos=new ValidacionReportos();
		fecha=validacionReportos.formatoFechaAlnovaReportosAltaCliente(fecha);
		altaClienteReportosRequestTO.setFechaAlta(fecha);
		altaClienteReportosRequestTO.setNombre(clienteResponse.getNombre());
		altaClienteReportosRequestTO.setRfc(clienteResponse.getRFC());
		altaClienteReportosRequestTO.setNumeroIdentificacionAlnova(altaClienteRequestTO.getIdAlnova());
		altaClienteReportosRequestTO.setPortal(altaClienteRequestTO.getPortalSolicitante());

		transactionsManager.altaClienteReportos(altaClienteReportosRequestTO);

	}

	public B706_RegistroRetencionResponseTO retencion_alnova(B706_RegistroRetencionRequestTO registroRetencionRequestTO) throws InversionesGenericException, IOException{
		$log.info("--------------------- Retencion de saldo alnova -----------------------");	

		registroRetencionRequestTO.setAutorizacion(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.atorizacion"));		
		registroRetencionRequestTO.setCodigoMonedaExtranjera(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.codigo.moneda.extrangera"));
		registroRetencionRequestTO.setCodigoOperacion(OPERATION_706_RETENCION_ALNOVA);		
		registroRetencionRequestTO.setFechaVencimiento(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.retencion.fecha.vencimiento"));
		registroRetencionRequestTO.setFree(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.free"));		
		registroRetencionRequestTO.setObservaciones("RETENCION");
		registroRetencionRequestTO.setPapel(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.papel"));
		registroRetencionRequestTO.setSecuritPurch(properties.getPropertie(ALNOVA_PROPERTIES,"webservice.alnova.config.security.purch"));

		TransactionsManager transactionsManager=new TransactionsManager();
		B706_RegistroRetencionResponseTO retencionAlnovaResponseTO=transactionsManager.registroRetencionAlnova(registroRetencionRequestTO);

		return retencionAlnovaResponseTO;
	}

	public InversionesResponseTO comprobante_inversion_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException
	{

		$log.info("###############   Comprobante Inversion Reportos ###############");
		ComprobanteInversionReportosRequestTO comprobanteInversionRequestTO=(ComprobanteInversionReportosRequestTO)inversionesRequestTO;
		ValidacionReportos validacionReportos= new ValidacionReportos();
		validacionReportos.comprobanteInversion(comprobanteInversionRequestTO);

		TransactionsManager transactionsManager=new TransactionsManager();
		List<Map<String, String>> listaMapas = transactionsManager.comprobanteInversionReportos(comprobanteInversionRequestTO);
		ReportosComprobanteInversionResponse comprobanteInversionResponse=new ReportosComprobanteInversionResponse(listaMapas.get(0));

		ComprobanteInversionReportosResponseTO comprobanteInversionReportosResponseTO=new ComprobanteInversionReportosResponseTO();

		comprobanteInversionReportosResponseTO.setCiudad(comprobanteInversionResponse.getCiudad());
		comprobanteInversionReportosResponseTO.setCodigo_postal(comprobanteInversionResponse.getCodigoPostal());
		comprobanteInversionReportosResponseTO.setColonia(comprobanteInversionResponse.getColonia());
		comprobanteInversionReportosResponseTO.setDireccion(comprobanteInversionResponse.getDireccion());
		comprobanteInversionReportosResponseTO.setFecha_concentracion(Formatter.formatoFechaReportos(comprobanteInversionResponse.getFechaConcertacion()));
		comprobanteInversionReportosResponseTO.setFecha_liquidacion(Formatter.formatoFechaReportos(comprobanteInversionResponse.getFechaLiquidacion()));
		comprobanteInversionReportosResponseTO.setFechaOperacion(Formatter.formatoFechaReportos(comprobanteInversionResponse.getFechaOperacion()));
		comprobanteInversionReportosResponseTO.setFolio_orden(comprobanteInversionResponse.getNumeroOrden());
		comprobanteInversionReportosResponseTO.setImporte_inversion(Formatter.formatMontoPesos(Formatter.getMontoTruncado(comprobanteInversionResponse.getImporte(),2)));
		comprobanteInversionReportosResponseTO.setInteres_premio(Formatter.formatMontoPesos(Formatter.getMontoTruncado(comprobanteInversionResponse.getInteres(),2)));
		comprobanteInversionReportosResponseTO.setIsr(Formatter.formatMontoPesos(Formatter.getMontoTruncado(comprobanteInversionResponse.getISR(),2)));
		comprobanteInversionReportosResponseTO.setMonto_al_vencimiento(Formatter.formatMontoPesos(Formatter.getMontoTruncado(comprobanteInversionResponse.getMontoVencimiento(),2)));
		comprobanteInversionReportosResponseTO.setNombre_cliente(comprobanteInversionResponse.getNombreCliente());
		comprobanteInversionReportosResponseTO.setNumero_contrato(comprobanteInversionResponse.getNumeroContrato());
		comprobanteInversionReportosResponseTO.setPlazo(comprobanteInversionResponse.getPlazo()+" días");
		comprobanteInversionReportosResponseTO.setPromotor(comprobanteInversionResponse.getNombrePromotor());
		comprobanteInversionReportosResponseTO.setRfc(comprobanteInversionResponse.getRfc());		
		comprobanteInversionReportosResponseTO.setTasa(comprobanteInversionResponse.getTasa() + "%");
		comprobanteInversionReportosResponseTO.setValor_letra(comprobanteInversionResponse.getImporteLetra());
		comprobanteInversionReportosResponseTO.setTotal_importe(Formatter.formatMonto(comprobanteInversionResponse.getImporteTotal()));
		comprobanteInversionReportosResponseTO.setTotal_titulos(comprobanteInversionResponse.getTotalTitulos());
		comprobanteInversionReportosResponseTO.setFecha_vencimiento(Formatter.formatoFechaReportos(comprobanteInversionResponse.getFechaVencimieno()));
		comprobanteInversionReportosResponseTO.setEmpresa_reportado(comprobanteInversionResponse.getNombreReportado());
		comprobanteInversionReportosResponseTO.setConcepto_inversion(comprobanteInversionResponse.getConceptoInversion());

		List<AsignacionesComprobanteTO> listaAsignaciones=new ArrayList<AsignacionesComprobanteTO>();
		AsignacionesComprobanteTO asignasionesComprobanteTO=null;
		for (int i = 1; i < listaMapas.size(); i++) {
			AsignacionesComprobanteInversionReportosResponse asignacion=new AsignacionesComprobanteInversionReportosResponse(listaMapas.get(i));
			asignasionesComprobanteTO=new AsignacionesComprobanteTO();

			asignasionesComprobanteTO.setEmisora(asignacion.getEmisora());
			asignasionesComprobanteTO.setMonto(Formatter.formatMonto(asignacion.getMonto()));
			asignasionesComprobanteTO.setPrecio(asignacion.getPrecio());
			asignasionesComprobanteTO.setSerie(asignacion.getSerie());
			asignasionesComprobanteTO.setTasaOperativa(asignacion.getTasaOperativa());
			asignasionesComprobanteTO.setTipoCambio(asignacion.getTipoCambio());
			asignasionesComprobanteTO.setTipoValor(asignacion.getTipoValor());
			asignasionesComprobanteTO.setTitulos(asignacion.getTitulos());
			asignasionesComprobanteTO.setTipoOperacion(asignacion.getTipoOperacion());
			asignasionesComprobanteTO.setMoneda(asignacion.getMoneda());
			asignasionesComprobanteTO.setEmisoraIndeval(asignacion.getEmisoraIndeval());
			asignasionesComprobanteTO.setValorNominal(asignacion.getValorNominal());

			listaAsignaciones.add(asignasionesComprobanteTO);
		}

		comprobanteInversionReportosResponseTO.setColeccionAsignaciones(listaAsignaciones);

//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();		
//		try {
//			outputStream =GeneradorPDF.generadorPDFComprobanteReportos(outputStream,comprobanteInversionReportosResponseTO);
//			comprobanteInversionReportosResponseTO.setOutputStreamPDF(outputStream);
//		} catch (IOException e1) {
//			InversionesExceptionTO exceptionTO = new InversionesExceptionTO();
//			exceptionTO.setMessage("Lo sentimos no se pudo generar el archivo ");
//			e1.printStackTrace();
//			throw new InversionesGenericException(exceptionTO);
//		} catch (DocumentException e1) {
//			InversionesExceptionTO exceptionTO = new InversionesExceptionTO();
//			exceptionTO.setMessage("Lo sentimos no se pudo generar el archivo ");
//			e1.printStackTrace();
//			throw new InversionesGenericException(exceptionTO);
//		}		

		return comprobanteInversionReportosResponseTO;
	}

	public InversionesResponseTO resumen_operaciones_vigentes_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException{

		$log.info("###############   Resumen Operaciones Vigentes Reportos ###############");	
		ResumenResponseTO resumenResponseTO = new ResumenResponseTO();

		Collection<ResumenOperacionesResponseTO> resumenOperaciones = new ArrayList<ResumenOperacionesResponseTO>();
		resumenOperaciones = ejecucionResumenesOperacionesReportos(inversionesRequestTO, "TCR01");	
		resumenResponseTO.setResumenOperaciones(resumenOperaciones);

		return resumenResponseTO;
	}

	public InversionesResponseTO resumen_operaciones_historicas_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException{

		$log.info("###############   Resumen Operaciones Historicas Reportos ###############");	

		ResumenResponseTO resumenResponseTO = new ResumenResponseTO();
		Collection<ResumenOperacionesResponseTO> resumenOperaciones = new ArrayList<ResumenOperacionesResponseTO>();
		resumenOperaciones = ejecucionResumenesOperacionesReportos(inversionesRequestTO, "TCR02");	
		resumenResponseTO.setResumenOperaciones(resumenOperaciones);

		return resumenResponseTO;
	}

	private Collection<ResumenOperacionesResponseTO> ejecucionResumenesOperacionesReportos(InversionesRequestTO inversionesRequestTO, String codigoTransaccion) throws InversionesGenericException{

		ResumenOperacionesRequestTO operacionesRequestTO = (ResumenOperacionesRequestTO)inversionesRequestTO;
		TransactionsManager transactionsManager=new TransactionsManager();

		ReportosResumenOperacionesRequestTO resumenOperacionesRequestTO = new ReportosResumenOperacionesRequestTO(codigoTransaccion);

		resumenOperacionesRequestTO.setPortal(operacionesRequestTO.getPortalSolicitante());
		resumenOperacionesRequestTO.setNumeroContrato(operacionesRequestTO.getNumeroContrato());
		resumenOperacionesRequestTO.setFechaInicioResumen(operacionesRequestTO.getFechaInicioResumen());
		resumenOperacionesRequestTO.setFechaFinResumen(operacionesRequestTO.getFechaFinResumen());
		resumenOperacionesRequestTO.setNumeroIdentificacionAlnova(operacionesRequestTO.getIdAlnova());

		Collection<ResumenOperacionesResponseTO> resumenOperaciones = new ArrayList<ResumenOperacionesResponseTO>();	 
		List <Map<String,String>> resumenes = transactionsManager.reportosResumenOperaciones(resumenOperacionesRequestTO);
		Iterator<Map<String,String>> res =resumenes.iterator();
		while(res.hasNext()){
			ReportosResumenOperacionesResponseTO reportosResumenOperacionesResponseTO = new ReportosResumenOperacionesResponseTO(res.next());
			ResumenOperacionesResponseTO resumenOperacionesResponseTO = new ResumenOperacionesResponseTO();
			resumenOperacionesResponseTO.setFolio(reportosResumenOperacionesResponseTO.getFolio());
			resumenOperacionesResponseTO.setFechaLiquidacion(Formatter.formatoFechaReportos(reportosResumenOperacionesResponseTO.getFechaLiquidacion()));		
			resumenOperacionesResponseTO.setFechaVencimiento(Formatter.formatoFechaReportos(reportosResumenOperacionesResponseTO.getFechaVencimiento()));
			double monto=Double.parseDouble(Formatter.getMontoTruncado(reportosResumenOperacionesResponseTO.getMonto(),2));
			resumenOperacionesResponseTO.setMonto(monto);
			resumenOperacionesResponseTO.setPlazo(reportosResumenOperacionesResponseTO.getPlazo());
			resumenOperacionesResponseTO.setTasa(reportosResumenOperacionesResponseTO.getTasa());
			double interes=Double.parseDouble(Formatter.getMontoTruncado(reportosResumenOperacionesResponseTO.getInteresGenerado(),2));
			resumenOperacionesResponseTO.setInteresGenerados(interes);
			double retencion=Double.parseDouble(Formatter.getMontoTruncado(reportosResumenOperacionesResponseTO.getRetencionISR(),2));
			resumenOperacionesResponseTO.setRetencion(retencion);
			double montoTotal=Double.parseDouble(Formatter.getMontoTruncado(reportosResumenOperacionesResponseTO.getImporteTotal(),2));
			resumenOperacionesResponseTO.setMontoTotal(montoTotal);
			resumenOperacionesResponseTO.setEstatus(reportosResumenOperacionesResponseTO.getEstatus());
			resumenOperaciones.add(resumenOperacionesResponseTO);			
		}
		return resumenOperaciones;
	}

	public CompraInversionReportosResponseTO apertura_cuenta_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException, IOException{

		$log.info("#########  Entra a apertura cuenta Reportos #########");

		AperturaCuentaReportosRequestTO aperturaCuentaReportosRequestTO=(AperturaCuentaReportosRequestTO) inversionesRequestTO;

		AltaClienteReportosRequestTO altaClienteReportosRequestTO=new AltaClienteReportosRequestTO();

		altaClienteReportosRequestTO.setCuentaCargo(aperturaCuentaReportosRequestTO.getCuentaCargo());
		altaClienteReportosRequestTO.setCuentaClabe(aperturaCuentaReportosRequestTO.getCuentaClabe());
		altaClienteReportosRequestTO.setIdAlnova(aperturaCuentaReportosRequestTO.getIdAlnova());
		altaClienteReportosRequestTO.setPortalSolicitante(aperturaCuentaReportosRequestTO.getPortalSolicitante());
		altaClienteReportosRequestTO.setOperation(aperturaCuentaReportosRequestTO.getOperation());
		altaClienteReportosRequestTO.setType(aperturaCuentaReportosRequestTO.getType());

		alta_cliente_reportos(altaClienteReportosRequestTO);


		CompraInversionReportosRequestTO compraInversionReportosRequestTO=new CompraInversionReportosRequestTO();
		compraInversionReportosRequestTO.setCuentaCargo(aperturaCuentaReportosRequestTO.getCuentaCargo());
		compraInversionReportosRequestTO.setIdAlnova(aperturaCuentaReportosRequestTO.getIdAlnova());
		compraInversionReportosRequestTO.setMonto(aperturaCuentaReportosRequestTO.getMonto());
		compraInversionReportosRequestTO.setPlazo(aperturaCuentaReportosRequestTO.getPlazo());
		compraInversionReportosRequestTO.setPortalSolicitante(aperturaCuentaReportosRequestTO.getPortalSolicitante());
		compraInversionReportosRequestTO.setTasaBruta(aperturaCuentaReportosRequestTO.getTasaBruta());
		compraInversionReportosRequestTO.setType(aperturaCuentaReportosRequestTO.getType());
		compraInversionReportosRequestTO.setOperation(aperturaCuentaReportosRequestTO.getOperation());
		CompraInversionReportosResponseTO compraInversionReportosResponseTO=(CompraInversionReportosResponseTO)compra_inversion_reportos(compraInversionReportosRequestTO);

		return compraInversionReportosResponseTO;
	}

	private static void iniciaMapaActividadEconomica(){
		PropertiesService properties=PropertiesService.getInstance();
		String file="CuentasInversion.properties";
		try {
			String numero=properties.getPropertie(file,"inversiones.datos.actividadEconomica.numero");
			int num=Integer.parseInt(numero);
			String propiedad;
			for (Integer i = 1; i<=num; i++) {
				propiedad=properties.getPropertie(file,"inversiones.datos.actividadEconomica."+i);
				mapaActividadEconomica.put(i.toString(),propiedad);
			}
		} catch (IOException e) {
			$log.info("*********************** ERROR GRAVE **************************");
			$log.info("           Error grave al leer el archivo properties");
		}catch(NumberFormatException ex){
			$log.info("*********************** ERROR GRAVE **************************");
			$log.info("Error en el properties CuentasInversion en la propiedad inversiones.datos.actividadEconomica.numero");
		}
	}
	/**
	 * quitar_retencion<br>
	 * =====================================================
	 * Elimina la retencion 
	 * @param B756_QuitarRetencionRequest extends AlnovaRequest
	 * @return B756_QuitarRetencionResponse
	 * @throws InversionesGenericException
	 */
	public B756_QuitarRetencionResponse quitar_retencion(B756_QuitarRetencionRequest requestWrapper,String idAlnova,String portal) throws InversionesGenericException{
		$log.info("##############   Entra a quitar retencion  ################");	
		TransactionsManager transactionsManager;

		transactionsManager=new TransactionsManager();
		B756_QuitarRetencionResponse responseWrapper=transactionsManager.quitarRetencionAlnova(requestWrapper,idAlnova,portal);

		return responseWrapper;

	}

	public AperturaCuentasResponseTO apertura_cuenta_eje_elite(InversionesRequestTO request) throws InversionesGenericException{

		$log.info("#########  Entra a apertura cuenta eje elite #########");

		PropertiesService propertiesService=PropertiesService.getInstance();

		String producto="13";
		String subProducto="0003";
		try {
			producto=propertiesService.getPropertie(ALNOVA_PROPERTIES,"alnova.transaccion.bb02.producto");
			subProducto=propertiesService.getPropertie(ALNOVA_PROPERTIES,"alnova.transaccion.bb02.sub_producto");
		} catch (IOException e) {
			$log.info("#########  Error al leer del archivo Alnova.properties");
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_IO_EXCEPTION);
		}

		AperturaCuentaEjeEliteRequestTO aperturaCuentaEjeEliteRequestTO=(AperturaCuentaEjeEliteRequestTO)request;
		AperturaCuentasRequestTO aperturaCuentasRequestTO=new AperturaCuentasRequestTO();

		aperturaCuentasRequestTO.setCuentaCargo(aperturaCuentaEjeEliteRequestTO.getCuentaCargo());
		AperturaCuentaEjeEliteEntradaTO entradaEjeElite = aperturaCuentaEjeEliteRequestTO.getEntrada();
		AperturaCuentasEntradaTO aperturaCuentasEntradaTO=new AperturaCuentasEntradaTO();

		aperturaCuentasEntradaTO.setBeneficiarios(entradaEjeElite.getBeneficiarios());
		aperturaCuentasEntradaTO.setCodigoProducto(producto);
		aperturaCuentasEntradaTO.setMoneda(entradaEjeElite.getMoneda());
		aperturaCuentasEntradaTO.setMonto(entradaEjeElite.getMonto());
		aperturaCuentasEntradaTO.setNumeroCliente(entradaEjeElite.getNumeroCliente());
		aperturaCuentasEntradaTO.setSubProducto(subProducto);


		aperturaCuentasRequestTO.setIdAlnova(request.getIdAlnova());
		aperturaCuentasRequestTO.setPortalSolicitante(request.getPortalSolicitante());
		aperturaCuentasRequestTO.setEntrada(aperturaCuentasEntradaTO);
		aperturaCuentasRequestTO.setMontoAperturaCuenta(aperturaCuentaEjeEliteRequestTO.getMontoAperturaCuenta());

		TransactionsManager transactionsManager=new TransactionsManager();
		transactionsManager.altaClienteMonitoreo(request.getIdAlnova());

		aperturaCuentasRequestTO.setType(aperturaCuentaEjeEliteRequestTO.getType());
		aperturaCuentasRequestTO.setOperation(aperturaCuentaEjeEliteRequestTO.getOperation());

		AperturaCuentasResponseTO response = aperturaCuentas(aperturaCuentasRequestTO);

		return response;

	}


	private String obtenerCentroGestor(String cuenta) throws InversionesGenericException{
		$log.info("*************  Obtener Centro Gestor ***************");
		String cuentaObtenida = cuenta.substring(4, 14);
		String sucursalDeCuenta = cuenta.substring(0,4);

		B011_BusquedaCentroContableRequest busquedaSucursalRequest=new B011_BusquedaCentroContableRequest();
		busquedaSucursalRequest.setCuenta(cuentaObtenida);
		busquedaSucursalRequest.setSucursal(sucursalDeCuenta);

		TransactionsManager manager=new TransactionsManager();
		B011_BusquedaCentroContableResponse busquedaSucursalresponse=manager.obtenerCentroGestor(busquedaSucursalRequest);

		return busquedaSucursalresponse.getCentroContable();
	}



	private AperturaCuentasResponseTO aperturaCuentas(InversionesRequestTO request) throws InversionesGenericException{

		$log.info("#########  Entra a apertura cuentas #########");
		AperturaCuentasRequestTO peticion=(AperturaCuentasRequestTO)request;

		PropertiesService propertiesService=PropertiesService.getInstance();

		String entidad="";
		String empleado="";
		String funcion="";
		String channel="";
		String codigo_aplicacion="";

		try {
			entidad=propertiesService.getPropertie(ALNOVA_PROPERTIES,"alnova.transaccion.bb02.entidad");
			empleado=propertiesService.getPropertie(ALNOVA_PROPERTIES,"alnova.transaccion.bb02.empleado");
			funcion=propertiesService.getPropertie(ALNOVA_PROPERTIES,"alnova.transaccion.bb02.funcion");
			channel=propertiesService.getPropertie(ALNOVA_PROPERTIES,"alnova.transaccion.bb02.channel");
			codigo_aplicacion=propertiesService.getPropertie(ALNOVA_PROPERTIES,"alnova.transaccion.bb02.codigo_aplicacion");
		} catch (IOException e) {
			$log.info("#########  Error al leer del archivo Alnova.properties");
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_IO_EXCEPTION);
		}

		TransactionsManager transactionsManager=new TransactionsManager();



		BB02_AperturaCuentaEjeEliteRequest requestWrapper= new BB02_AperturaCuentaEjeEliteRequest();
		requestWrapper.setEntidad(entidad);

		String sucursal=obtenerCentroGestor(Formatter.removeSpaces(Formatter.formatSplittedCuenta(peticion.getCuentaCargo())));

		requestWrapper.setSucursal(sucursal);

		requestWrapper.setEmpleado(empleado);
		requestWrapper.setParentesco(" ");
		requestWrapper.setFuncion(funcion);
		requestWrapper.setChanel(channel);
		requestWrapper.setClienteTutor(" ");
		requestWrapper.setCodigoAplicacion(codigo_aplicacion);
		requestWrapper.setCodigoProducto(peticion.getEntrada().getCodigoProducto());
		requestWrapper.setSubproducto(peticion.getEntrada().getSubProducto());
		requestWrapper.setMonto("0000000000000000");
		requestWrapper.setMoneda(peticion.getEntrada().getMoneda());
		requestWrapper.setNumeroCliente(peticion.getEntrada().getNumeroCliente());
		requestWrapper.setCuentaAsociada(" ");
		String ref=peticion.getEntrada().getNumeroCliente()+(new Date().getTime());
		requestWrapper.setReferencia(ref);

		for(int i=0;i<peticion.getEntrada().getBeneficiarios().size();i++){
			BeneficiarioTO beneficiario= peticion.getEntrada().getBeneficiarios().get(i);
			switch (i){
			case 0: 
				requestWrapper.setBeneficiario1(beneficiario.getNombreCompleto());
				requestWrapper.setPorcentajeBeneficiario1(InversionRule.formateaPorcentaje(beneficiario.getPorcentaje()));
				requestWrapper.setContadorBeneficiario1("0"+(i+1));
				break;
			case 1: 
				requestWrapper.setBeneficiario2(beneficiario.getNombreCompleto());
				requestWrapper.setPorcentajeBeneficiario2(InversionRule.formateaPorcentaje(beneficiario.getPorcentaje()));
				requestWrapper.setContadorBeneficiario2("0"+(i+1));
				break;
			case 2: 
				requestWrapper.setBeneficiario3(beneficiario.getNombreCompleto());
				requestWrapper.setPorcentajeBeneficiario3(InversionRule.formateaPorcentaje(beneficiario.getPorcentaje()));
				requestWrapper.setContadorBeneficiario3("0"+(i+1));
				break;
			case 3: 
				requestWrapper.setBeneficiario4(beneficiario.getNombreCompleto());
				requestWrapper.setPorcentajeBeneficiario4(InversionRule.formateaPorcentaje(beneficiario.getPorcentaje()));
				requestWrapper.setContadorBeneficiario4("0"+(i+1));
				break;
			}
		}

		requestWrapper.setEntrada();

		BB02_AperturaCuentasResponse responseWrapper=transactionsManager.aperturaCuentas(requestWrapper,request.getIdAlnova(),request.getPortalSolicitante());

		B520_TransaccionRequest transaccionRequest= new B520_TransaccionRequest();

		transaccionRequest.setDescripcionOperacion("La cuenta "+ responseWrapper.getNumeroCuenta()+ " con referencia "+
				ref+", monto $"+peticion.getMontoAperturaCuenta()+ " a nombre de " + responseWrapper.getNombreBeneficiario());
		transaccionRequest.setDescripcionOperacion("traspaso entre cuentas");

		transaccionRequest.setMonto(InversionRule.formateaMontoRetencion(peticion.getMontoAperturaCuenta()));
		transaccionRequest.setCuentaCargo(peticion.getCuentaCargo());
		transaccionRequest.setReferenciaCuentaCargo("Apertura de cuenta EJE ELITE");
		transaccionRequest.setCodigoOperacionCuentaCargo(COD_OPERACION_TRANSFERENCIA_CARGO);
		transaccionRequest.setTitularCuentaCargo(responseWrapper.getNombreBeneficiario());
		transaccionRequest.setCuentaDeposito(Formatter.removeSpaces(responseWrapper.getNumeroCuenta()));		

		transaccionRequest.setReferenciaCuentaDeposito("Apertura de cuenta EJE ELITE ");
		transaccionRequest.setCodigoOperacionCuentaDeposito(COD_OPERACION_TRANSFERENCIA_DEPOSITO);

		transaccionRequest.setTitularCuentaDeposito(responseWrapper.getNombreBeneficiario());

		transaccionRequest.setMoneda(peticion.getEntrada().getMoneda());


		transactionsManager.transferencia(transaccionRequest,peticion.getEntrada().getNumeroCliente(),request.getPortalSolicitante());

		AperturaCuentasResponseTO response= new AperturaCuentasResponseTO();

		response.setClabe(responseWrapper.getCuentaClabe());
		response.setCuentaEje(responseWrapper.getNumeroCuenta());
		response.setFolioOperacion(responseWrapper.getNumeroMovimiento());
		String monto=responseWrapper.getMonto();
		Double montoDouble=Double.parseDouble(monto);
		response.setSaldo(montoDouble.toString());

		return response;
	}
	
	public InversionesResponseTO valida_plazo_tasa_reportos(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException{
		
		ReportosValidaPlazoTasaTO validaPlazoTasaRequestTO=(ReportosValidaPlazoTasaTO)inversionesRequestTO;
		ReportosValidaPlazoTasaRequestTO validaPlazoTasaReportos=new ReportosValidaPlazoTasaRequestTO();
		

		ValidacionReportos validacionReportos=new ValidacionReportos();
		validacionReportos.validaDatosValidacionPlazoTasa(validaPlazoTasaRequestTO);
		
		
		
		validaPlazoTasaReportos.setFechaOperacion(validaPlazoTasaRequestTO.getFechaOperacion());
		
		validaPlazoTasaReportos.setMontoOperacion(InversionRule.agragaCerosIzq(Formatter.getMontoTruncado(validaPlazoTasaRequestTO.getMontoOrden(),2),12));
		validaPlazoTasaReportos.setPlazo(InversionRule.agragaCerosIzq(validaPlazoTasaRequestTO.getPlazo(),8));
		validaPlazoTasaReportos.setTasa(InversionRule.agragaCerosIzq(validacionReportos.formatoTasaBruta(validaPlazoTasaRequestTO.getTasa()),8));
		
		validaPlazoTasaReportos.setPortal(validaPlazoTasaRequestTO.getPortal());
		
		
		TransactionsManager manager=new TransactionsManager();
		ReportosResponse response=manager.validaPlazoTaza(validaPlazoTasaReportos);
		
		String respuesta= response.getAttribute("RESPUESTA");
		String codigoResp=response.getAttribute("codigo");
		ReportosValidaPlazoTasaReponseTO plazoTasaReponseTO=new ReportosValidaPlazoTasaReponseTO();
		
		if (respuesta.equalsIgnoreCase("true"))
			plazoTasaReponseTO.setOk(true);
		
		plazoTasaReponseTO.setMensaje(respuesta);
		plazoTasaReponseTO.setCodigoMensaje(codigoResp);
		
		return plazoTasaReponseTO;
		
	}
	 
	public InversionesResponseTO busqueda_cuenta_inversion_cliente(InversionesRequestTO inversionesRequestTO){
		String idAlnova=inversionesRequestTO.getIdAlnova();
		InversionesResponseTO response = new InversionesResponseTO();
		response.setMessage(new InversionesDAOAbstracto().busquedaCuentaInversionCliente(idAlnova));
		return response; 
	}
	
	public LoginConciliacionResponseTO getLoginConciliacion(LoginConciliacionRequestTO loginConciliacionRequestTO) throws InversionesGenericException, SessionExpiredException, EliteDataException {
		LoginConciliacionResponseTO responseTO = new LoginConciliacionResponseTO();
		ConciliacionesDAO conciliacionesDAO=new ConciliacionesDAO();
		String tieneAcceso = "";
		try {
			tieneAcceso = conciliacionesDAO.getLoginConciliacion(loginConciliacionRequestTO);
		} catch (DAOException e) {
			throw new InversionesGenericException();
		}
		responseTO.setTieneAcceso(tieneAcceso);
		return responseTO;
	}
	
	public Conciliacion_ResponseTO conciliacionOperacionesMercadoDinero(ConciliacionRequestTO conciliacionRequestTO) throws InversionesGenericException{
		Conciliacion_ResponseTO response = new Conciliacion_ResponseTO();		
		$log.info("#########  ConciliacionResponseTO conciliacionOperacionesMercadoDinero(ConciliacionRequestTO conciliacionRequestTO) #########");
		Collection<ConciliacionEliteResponseTO> eliteResponse = new ArrayList<ConciliacionEliteResponseTO>();
				
		eliteResponse = new InversionesDAOAbstracto().busquedaConciliacionFecha(conciliacionRequestTO.getFechaInicial());
				
		TransactionsManager transactionsManager=new TransactionsManager();
			
		ReportosConciliacionRequestTO conciliacionRequest = new ReportosConciliacionRequestTO();
				
		conciliacionRequest.setPortal(conciliacionRequestTO.getPortalSolicitante());		
		conciliacionRequest.setFechaOperacion(new ValidacionReportos().formatoFechaAlnovaReportosAltaCliente(conciliacionRequestTO.getFechaInicial()));

		Collection<ConciliacionTASResponseTO> listaConciliacionResponse = new ArrayList<ConciliacionTASResponseTO>();	 
		List <Map<String,String>> resumenes = transactionsManager.conciliacion(conciliacionRequest);
		Iterator<Map<String,String>> res =resumenes.iterator();
		while(res.hasNext()){
			ReportosConciliacionResponseTO reportosConciliacionResponseTO = new ReportosConciliacionResponseTO(res.next());
			ConciliacionTASResponseTO conciliacionTASResponseTO = new ConciliacionTASResponseTO();
			conciliacionTASResponseTO.setFolio_cargo(reportosConciliacionResponseTO.getFolioCargo());
			conciliacionTASResponseTO.setCodigo(reportosConciliacionResponseTO.getCodigo());
			conciliacionTASResponseTO.setDescripcion(reportosConciliacionResponseTO.getDescripcion());
			String temp = reportosConciliacionResponseTO.getMonto()==null?"":reportosConciliacionResponseTO.getMonto();
			double monto=Double.parseDouble(Formatter.getMontoTruncado(temp,2));
			conciliacionTASResponseTO.setMonto(new Double(monto).toString());
			conciliacionTASResponseTO.setFecha_operacion(reportosConciliacionResponseTO.getFechaOperacion());
			conciliacionTASResponseTO.setEstatus(reportosConciliacionResponseTO.getEstatus());
			temp = reportosConciliacionResponseTO.getMontoCargo()==null?"":reportosConciliacionResponseTO.getMontoCargo();
			double montoCargo=Double.parseDouble(Formatter.getMontoTruncado(temp,2));
			conciliacionTASResponseTO.setMonto_cargo(new Double(montoCargo).toString());
			conciliacionTASResponseTO.setFolio_retencion(reportosConciliacionResponseTO.getFolioRetencion());
			conciliacionTASResponseTO.setMonto_retencion(reportosConciliacionResponseTO.getMontoRetencion());
			conciliacionTASResponseTO.setFolio_orden(reportosConciliacionResponseTO.getFolio());
			
			listaConciliacionResponse.add(conciliacionTASResponseTO);			
		}				
		Collection<Conciliacion_Concentrado_ResponseTO> respuesta = concentraRespuesta(eliteResponse,listaConciliacionResponse);
		
		if ( ( respuesta == null ) || ( respuesta.size() < 1 ) ) {
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setMessage("Lo sentimos, no existen datos a conciliar");
			exceptionTO.setLevel("AVISO");
			throw new InversionesGenericException(exceptionTO);
		}
		
		response.setColeccion_conciliacion(respuesta);
		return response;
	}
	
	public ReportosEstadoCuentaResponseTO estadoCuentaMercadoDineroEjecucion(ReportosEstadoCuentaRequestTO requestTO) throws InversionesGenericException, SessionExpiredException, EliteDataException {
		TransactionsManager transactionsManager=new TransactionsManager();
		ReportosEstadoCuentaResponseTO response = transactionsManager.getReportosEstadoCuenta(requestTO);
		return response;
	}
	
	private Collection<Conciliacion_Concentrado_ResponseTO> concentraRespuesta(Collection<ConciliacionEliteResponseTO> eliteResponse,Collection<ConciliacionTASResponseTO> tasResponse){
		Collection<Conciliacion_Concentrado_ResponseTO> respuesta = new ArrayList<Conciliacion_Concentrado_ResponseTO>();
		
		if (eliteResponse != null){
			for (ConciliacionEliteResponseTO elite : eliteResponse){
				Conciliacion_Concentrado_ResponseTO respuestaConcentrada = new Conciliacion_Concentrado_ResponseTO();
				respuestaConcentrada.setFolio_elite(elite.getFolio_elite());
				respuestaConcentrada.setNombre_elite(elite.getNombre_elite());
				respuestaConcentrada.setTasa_elite(elite.getTasa_elite());
				respuestaConcentrada.setPlazo_elite(elite.getPlazo_elite());
				respuestaConcentrada.setHora_elite(elite.getHora_elite());
				respuestaConcentrada.setMonto_elite(elite.getMonto_elite());
				
				if (tasResponse != null){
					for(ConciliacionTASResponseTO tas : tasResponse){
						if (tas.getFolio_orden() != null && tas.getFolio_orden().equalsIgnoreCase(elite.getFolio_elite())){
							respuestaConcentrada.setFolio_orden(tas.getFolio_orden()==null?"":tas.getFolio_orden());
							respuestaConcentrada.setFecha_operacion(tas.getFecha_operacion()==null?"":tas.getFecha_operacion());
							respuestaConcentrada.setMonto(tas.getMonto()==null?"":tas.getMonto());
							respuestaConcentrada.setEstatus(tas.getEstatus()==null?"":tas.getEstatus());
							respuestaConcentrada.setFolio_retencion(tas.getFolio_retencion()==null?"":tas.getFolio_retencion());
							respuestaConcentrada.setMonto_retencion(tas.getMonto_retencion()==null?"":tas.getMonto_retencion());
							respuestaConcentrada.setFolio_cargo(tas.getFolio_cargo()==null?"":tas.getFolio_cargo());
							respuestaConcentrada.setMonto_cargo(tas.getMonto_cargo()==null?"":tas.getMonto_cargo());
							respuestaConcentrada.setCodigo(tas.getCodigo()==null?"":tas.getCodigo());
							respuestaConcentrada.setDescripcion(tas.getDescripcion()==null?"":tas.getDescripcion());
						}
					}
				}
				respuesta.add(respuestaConcentrada);
			}
		}
		return respuesta;
	}
	
	public ActualizacionSaldoPorCuentaResponseTO actualizar_saldo_cuenta_alnova(InversionesRequestTO inversionesRequestTO) throws InversionesGenericException {
		$log.info("--------------------- B402 Actualización de saldo por cuenta alnova -----------------------");	
		ActualizacionSaldoPorCuentaRequestTO actualizacionSaldoRequestTO = (ActualizacionSaldoPorCuentaRequestTO)inversionesRequestTO;
		ActualizacionSaldoPorCuentaResponseTO actualizacionSaldoResponseTO = new ActualizacionSaldoPorCuentaResponseTO();
		
		B402_ActualizacionSaldoPorCuentaRequest B402_ActualizacionSaldoRequest = new B402_ActualizacionSaldoPorCuentaRequest();
		B402_ActualizacionSaldoRequest.setOpcion("R");
		B402_ActualizacionSaldoRequest.setNumeroCuenta(actualizacionSaldoRequestTO.getNumeroCuenta());
		
		TransactionsManager transactionsManager=new TransactionsManager();
		B402_ActualizacionSaldoPorCuentaResponse B402_ActualizacionSaldoResponse = transactionsManager.actualizacionSaldoCuentaAlnova(B402_ActualizacionSaldoRequest);
		
		double saldoDisponible = 0.0;
		double saldoRetenido = 0.0;

		
		try{
			if(B402_ActualizacionSaldoResponse!=null){
				saldoDisponible = Formatter.conversionMontoAlnovaJava(B402_ActualizacionSaldoResponse.getSaldoDisponible()) ;
				saldoRetenido = Formatter.conversionMontoAlnovaJava(B402_ActualizacionSaldoResponse.getSaldoRetenido()) ;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
		actualizacionSaldoResponseTO.setNumeroCuenta(actualizacionSaldoRequestTO.getNumeroCuenta());
		actualizacionSaldoResponseTO.setSaldoDisponible(saldoDisponible);
		actualizacionSaldoResponseTO.setSaldoRetenido(saldoRetenido);
//		actualizacionSaldoResponseTO.setSaldoTotal(B402_ActualizacionSaldoResponse);
		
		return actualizacionSaldoResponseTO;
	}

	public InversionesResponseTO fecha_apertura_reportos(InversionesRequestTO request) throws InversionesGenericException{
		FechaAperturaReportosRequestTO aperturaReportosRequestTO = (FechaAperturaReportosRequestTO) request;
		FechaAperturaReportosResponseTO aperturaReportosResponseTO = new FechaAperturaReportosResponseTO();
		
		ReportosFechaAperturaRequestTO reportosRequest = new ReportosFechaAperturaRequestTO();
		reportosRequest.setIdAlnova(aperturaReportosRequestTO.getIdAlnova());
		reportosRequest.setNumeroContrato(aperturaReportosRequestTO.getNumeroContrato());
		reportosRequest.setPortalSolicitante(aperturaReportosRequestTO.getPortalSolicitante());
		
		TransactionsManager transactionsManager=new TransactionsManager();
		ReportosFechaAperturaResponseTO reportosResponse = transactionsManager.getFechaAperturaReportos(reportosRequest);
		
		aperturaReportosResponseTO.setFechaApertura(reportosResponse.getFechaInicioInversion());
		
		return aperturaReportosResponseTO;
	}

	public InversionesResponseTO consulta_periodo_anios_reportos(InversionesRequestTO request) throws InversionesGenericException{
		FechaAperturaReportosResponseTO aperturaReportosResponseTO = new FechaAperturaReportosResponseTO();
		Collection<String> anios = new ArrayList<String>();
		aperturaReportosResponseTO = (FechaAperturaReportosResponseTO) fecha_apertura_reportos(request);
		
		if ( ( aperturaReportosResponseTO != null ) || ( aperturaReportosResponseTO.getFechaApertura() != null ) ) {
			anios = obtenerAnios(aperturaReportosResponseTO.getFechaApertura());
			
			aperturaReportosResponseTO.setFechaApertura(aperturaReportosResponseTO.getFechaApertura());
			aperturaReportosResponseTO.setAnios(anios);
		}
		
		return aperturaReportosResponseTO;
	}
	
	public InversionesResponseTO consulta_periodo_meses_reportos(InversionesRequestTO request) throws InversionesGenericException{
		FechaAperturaReportosRequestTO aperturaReportosRequestTO = (FechaAperturaReportosRequestTO) request;
		FechaAperturaReportosResponseTO aperturaReportosResponseTO = new FechaAperturaReportosResponseTO();
		Collection<String> meses = new ArrayList<String>();
				
		if ( ( aperturaReportosRequestTO != null ) || ( aperturaReportosRequestTO.getAnioPeriodo() != null ) ) {
			meses = obtenerMeses(aperturaReportosRequestTO.getAnioPeriodo());
			
			aperturaReportosResponseTO.setMeses(meses);
		}
		
		return aperturaReportosResponseTO;
	}
	
	
	private Collection<String> obtenerAnios(String fechaApertura) throws InversionesGenericException {
		Collection<String> anios = new ArrayList<String>();
		
		Locale LOCALE_MX = new Locale("es","mx");
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", LOCALE_MX);
				
		try {

			String[] tokenDate = fechaApertura.split("/");
			if( tokenDate.length == 3){
				fechaApertura = tokenDate[2] + "/" + tokenDate[1] + "/" +tokenDate[0];
			}else{
				throw new InversionesGenericException();
			}
			
			Calendar fechaInicio = Calendar.getInstance();
			fechaInicio.setTime(dateFormat.parse(fechaApertura));
			Calendar fechaActual = Calendar.getInstance();		
			
			if( fechaInicio.get( Calendar.YEAR ) >= fechaActual.get( Calendar.YEAR ) ){
				anios.add( String.valueOf( fechaActual.get ( Calendar.YEAR ) ) );
			}else{
				anios.add( String.valueOf( fechaActual.get ( Calendar.YEAR ) -1 ) );
				anios.add( String.valueOf( fechaActual.get ( Calendar.YEAR ) ) );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setMessage("Lo sentimos, existió un problema al consultar los periodos.");
			exceptionTO.setLevel("AVISO");
			throw new InversionesGenericException(exceptionTO);
		}	
			
		return anios;
	}
	
	private Collection<String> obtenerMeses(String anio) throws InversionesGenericException {
		Collection<String> meses = new ArrayList<String>();
		
		Locale LOCALE_MX = new Locale("es","mx");
		SimpleDateFormat dateFormatMonth = new SimpleDateFormat( "MMMM", LOCALE_MX );
		Calendar calendar = Calendar.getInstance();
		anio = Formatter.removeSpaces(anio);
		
		try {
			int anioPeriodo = Integer.parseInt(anio);
			int totalMeses = 11;
			Calendar fechaActual = Calendar.getInstance();		
			if ( anioPeriodo >= fechaActual.get( Calendar.YEAR ) ) {
				totalMeses = fechaActual.get( Calendar.MONTH ) - 1;
			}

			for (int i = 0; i <= totalMeses; i++ ) {
				calendar.set( Calendar.MONTH, i );
				String mes = dateFormatMonth.format( calendar.getTime() );
				meses.add(mes.toUpperCase());
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setMessage("Lo sentimos, existió un problema al consultar los periodos.");
			exceptionTO.setLevel("AVISO");
			throw new InversionesGenericException(exceptionTO);
		}	
		
		return meses;
	}
	
}
