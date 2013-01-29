package com.bancoazteca.elite.ejb.inversiones;


import static com.bancoazteca.elite.ejb.inversiones.InversionesGenericException.LEVEL_WEB_SERVICES;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.db.beans.AltaClienteRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.B402_ActualizacionSaldoPorCuentaRequest;
import com.bancoazteca.elite.ejb.alnova.beans.B402_ActualizacionSaldoPorCuentaResponse;
import com.bancoazteca.elite.ejb.inversiones.beans.ComprobanteInversionReportosRequestTO;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ReportosPlazoTasaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaTransactions;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B011_BusquedaCentroContableRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B011_BusquedaCentroContableResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B520_TransaccionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B520_TransaccionResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B706_RegistroRetencionResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.B756_QuitarRetencionResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.BB02_AperturaCuentaEjeEliteRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.BB02_AperturaCuentasResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.P011_DatosClienteRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.wrappers.P011_DatosClienteResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosTransactions;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAltaClienteRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAperturaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosAperturaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosComprobanteInversionRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosConciliacionRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosFechaAperturaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosFechaAperturaResponseTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosPlazoTasaRequest;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosPlazoTasaResponse;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosResumenOperacionesRequestTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosValidaPlazoTasaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.wsfactory.WebServiceInterfaceFactory;
import com.bancoazteca.elite.inversiones.db.dao.InversionesDAO;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.Validator;

public class TransactionsManager {

	Logger log=Logger.getLogger(TransactionsManager.class);

	private static final String REPORTOS = "reportos";
	@SuppressWarnings("unused")
	private static final String SISDISI = "sisdisi";
	private static final String ALNOVA = "alnova";

	private static final String EXITO_ALTA_CLIENTE="AC001";
	private static final String CLIENTE_ALTA_ANTERIIOR="AC002";


	public B706_RegistroRetencionResponseTO registroRetencionAlnova(B706_RegistroRetencionRequestTO registroRetencionRequestTO) throws InversionesGenericException{
		B706_RegistroRetencionResponseTO registroRetencionResponseTO=null;
		AlnovaTransactions alnovaTransactions=(AlnovaTransactions)new WebServiceInterfaceFactory(ALNOVA).getInstance();
		AlnovaResponse alnovaResponse=null;
		Integer idRetencion=null;
		try{
			alnovaResponse=alnovaTransactions.ejecuta(registroRetencionRequestTO);
			registroRetencionResponseTO=new B706_RegistroRetencionResponseTO(alnovaResponse);
		}finally{
			
			InversionesDAO inversionesDAO=new InversionesDAO();
			idRetencion=inversionesDAO.registroRetencionAlnova(registroRetencionResponseTO, registroRetencionRequestTO, 
																		 alnovaTransactions.getResponse(),alnovaTransactions.getRequest(),
																		 alnovaTransactions.getStatus());
		}
		registroRetencionResponseTO.setIdRetencion(idRetencion.toString());
		return registroRetencionResponseTO;
	}
	
	public B402_ActualizacionSaldoPorCuentaResponse actualizacionSaldoCuentaAlnova(B402_ActualizacionSaldoPorCuentaRequest actualizacionSaldoRequest) throws InversionesGenericException{
		B402_ActualizacionSaldoPorCuentaResponse actualizacionSaldoResponse = null;
		
		AlnovaTransactions alnovaTransactions=(AlnovaTransactions)new WebServiceInterfaceFactory(ALNOVA).getInstance();
		AlnovaResponse alnovaResponse=null;
		
		alnovaResponse=alnovaTransactions.ejecuta(actualizacionSaldoRequest);
		actualizacionSaldoResponse=new B402_ActualizacionSaldoPorCuentaResponse(alnovaResponse);
		
		
		return actualizacionSaldoResponse;
	}


	public ReportosAperturaResponseTO compraInversionReportos(ReportosAperturaRequestTO aperturaRequestTO) throws InversionesGenericException{

		ReportosTransactions reportosTransactions=(ReportosTransactions)new WebServiceInterfaceFactory(REPORTOS).getInstance();
		ReportosResponse reportosResponse=null;
		ReportosAperturaResponseTO aperturaResponse =null;
		try{
			reportosResponse=reportosTransactions.ejecuta(aperturaRequestTO);
			aperturaResponse = new ReportosAperturaResponseTO(reportosResponse.getParametros());
			if(aperturaResponse!=null){
				if(Validator.isEmptyData(aperturaResponse.getFolioOperacion())){
					reportosTransactions.setStatus("FALLIDO");
					InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
					exceptionTO.setLevel(LEVEL_WEB_SERVICES);
					exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
					throw new InversionesGenericException(exceptionTO);
				}
			}
		}finally{
			InversionesDAO inversionesDAO=new InversionesDAO();
			inversionesDAO.registroCompraInversionReportos(aperturaRequestTO, aperturaResponse,reportosTransactions.getResponse(),reportosTransactions.getRequest(),reportosTransactions.getStatus());
		}
		return aperturaResponse;
	}


	public ReportosPlazoTasaResponse plazoTasaReportos(ReportosPlazoTasaRequestTO  reportosPlazoTasaRequestTO) throws InversionesGenericException{

		ReportosTransactions reportosTransactions=(ReportosTransactions)new WebServiceInterfaceFactory(reportosPlazoTasaRequestTO.getType()).getInstance();
		ReportosPlazoTasaRequest reportosRequest=new ReportosPlazoTasaRequest();	
		reportosRequest.setFechaRegistro(reportosPlazoTasaRequestTO.getFechaRegistro());

		ReportosResponse response=reportosTransactions.ejecuta(reportosRequest);
		ReportosPlazoTasaResponse plazoTasaResponse=new ReportosPlazoTasaResponse(response.getParametros());
		return plazoTasaResponse;

	}

	public P011_DatosClienteResponse datosClienteAlnova(P011_DatosClienteRequest clienteRequest) throws InversionesGenericException{

		AlnovaTransactions alnovaTransactions=(AlnovaTransactions)new WebServiceInterfaceFactory(ALNOVA).getInstance();

		AlnovaResponse alnovaResponse = alnovaTransactions.ejecuta(clienteRequest);

		String mensajeAlnova=alnovaResponse.getMessage();

		if(mensajeAlnova.toLowerCase().contains("error")){
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(LEVEL_WEB_SERVICES);
			String codigo = Formatter.getCodigoErrorAlnova(mensajeAlnova);
			exceptionTO.setMessage("Lo sentimos, ha ocurrido un error en el proceso. "+codigo);
			throw new InversionesGenericException(exceptionTO);
		}

		P011_DatosClienteResponse clienteResponse=new P011_DatosClienteResponse(alnovaResponse);

		return clienteResponse;
	}


	public void altaClienteReportos(ReportosAltaClienteRequestTO altaClienteRequestTO) throws InversionesGenericException{
		
		ReportosTransactions reportosTransactions=(ReportosTransactions)new WebServiceInterfaceFactory(REPORTOS).getInstance();
		try{
			reportosTransactions.ejecuta(altaClienteRequestTO);
		}catch(InversionesGenericException e){
			String error=e.getInversionesExceptionTO().getMessage();
			if(!error.contains(EXITO_ALTA_CLIENTE)&& !error.contains(CLIENTE_ALTA_ANTERIIOR)){
				throw new InversionesGenericException(e.getInversionesExceptionTO());
			}if(error.contains(CLIENTE_ALTA_ANTERIIOR)){
				reportosTransactions.setResponse(reportosTransactions.getResponse());
			}
		}finally{
			InversionesDAO inversionesDAO =new InversionesDAO();
			inversionesDAO.altaClienteReportos(altaClienteRequestTO, reportosTransactions.getRequest(),reportosTransactions.getResponse(),reportosTransactions.getStatus());
		}
	}

	public List<Map<String,String>> reportosResumenOperaciones(ReportosResumenOperacionesRequestTO operacionesRequestTO) throws InversionesGenericException {
		ReportosTransactions reportosTransactions = (ReportosTransactions) new WebServiceInterfaceFactory(REPORTOS).getInstance();
		ReportosResponse reportosResponse= reportosTransactions.ejecuta(operacionesRequestTO);	
		return reportosResponse.getResumenes();
	}
	
	public List<Map<String,String>> conciliacion(ReportosConciliacionRequestTO conciliacionRequestTO) throws InversionesGenericException {
		ReportosTransactions reportosTransactions = (ReportosTransactions) new WebServiceInterfaceFactory(REPORTOS).getInstance();
		ReportosResponse reportosResponse=reportosTransactions.ejecuta(conciliacionRequestTO);

		return reportosResponse.getResumenes();
	}
	
	public List<Map<String,String>> comprobanteInversionReportos(ComprobanteInversionReportosRequestTO  comprobanteInversionRequestTO)throws InversionesGenericException{

		ReportosTransactions reportosTransactions=(ReportosTransactions)new WebServiceInterfaceFactory(REPORTOS).getInstance();
		ReportosComprobanteInversionRequest reportosComprobanteInversion = new ReportosComprobanteInversionRequest();
		reportosComprobanteInversion.setFolioOperacion(comprobanteInversionRequestTO.getFolio_operacion());
		reportosComprobanteInversion.setIdAlnova(comprobanteInversionRequestTO.getIdAlnova());
		reportosComprobanteInversion.setNumeroContrato(comprobanteInversionRequestTO.getNumero_contrato());
		reportosComprobanteInversion.setPortal(comprobanteInversionRequestTO.getPortalSolicitante());

		ReportosResponse response = reportosTransactions.ejecuta(reportosComprobanteInversion);
		
		return response.getResumenes();
	}

	public B756_QuitarRetencionResponse quitarRetencionAlnova(B756_QuitarRetencionRequest request,String idAlnova,String portal) throws InversionesGenericException{

		AlnovaTransactions alnovaTransactions=(AlnovaTransactions)new WebServiceInterfaceFactory(ALNOVA).getInstance();		
		AlnovaResponse alnovaResponse;
		B756_QuitarRetencionResponse  response=null;
		try{
			alnovaResponse=alnovaTransactions.ejecuta(request);
			response=new B756_QuitarRetencionResponse(alnovaResponse);
		}finally{
			InversionesDAO inversionesDAO=new InversionesDAO();
			inversionesDAO.insertaQuitaRetencion(response,request,idAlnova,portal,alnovaTransactions.getRequest(),alnovaTransactions.getResponse(),alnovaTransactions.getStatus());
		}
		return response;
	}
	
	public BB02_AperturaCuentasResponse aperturaCuentas(BB02_AperturaCuentaEjeEliteRequest request,String idAlnova,String portal) throws InversionesGenericException{
    	
		AlnovaTransactions alnovaTransactions=(AlnovaTransactions)new WebServiceInterfaceFactory(ALNOVA).getInstance();
		AlnovaResponse alnovaResponse=null;
		BB02_AperturaCuentasResponse response=null;
		try{
			alnovaResponse=alnovaTransactions.ejecuta(request);
			response=new BB02_AperturaCuentasResponse(alnovaResponse);
		}finally{
			InversionesDAO inversionesDAO=new InversionesDAO();
			inversionesDAO.insertaAperturaCuentasAlnova(request,response,alnovaTransactions.getRequest(),alnovaTransactions.getResponse(),alnovaTransactions.getStatus(),idAlnova,portal);
			
			
		}
		return response;
    }
    
    public B520_TransaccionResponse transferencia(B520_TransaccionRequest request,String idAlnova,String portal) throws InversionesGenericException{
    	
    	AlnovaTransactions alnovaTransactions=(AlnovaTransactions)new WebServiceInterfaceFactory(ALNOVA).getInstance();
    	B520_TransaccionResponse response=null;
    	try{
    		AlnovaResponse alnovaResponse=alnovaTransactions.ejecuta(request);
    		response=new B520_TransaccionResponse(alnovaResponse);
    	}finally{
    		InversionesDAO inversionesDAO=new InversionesDAO();
    		inversionesDAO.registroTransferencia(response,request,alnovaTransactions.getRequest(),alnovaTransactions.getResponse(),alnovaTransactions.getStatus(),idAlnova,portal);
    	}
		
		return response;
	}


	public B011_BusquedaCentroContableResponse obtenerCentroGestor(B011_BusquedaCentroContableRequest busquedaSucursalRequest) throws InversionesGenericException {
		
		AlnovaTransactions alnovaTransactions=(AlnovaTransactions)new WebServiceInterfaceFactory(ALNOVA).getInstance();
		AlnovaResponse alnovaResponse = alnovaTransactions.ejecuta(busquedaSucursalRequest);
		B011_BusquedaCentroContableResponse busquedaSucursalresponse=new B011_BusquedaCentroContableResponse(alnovaResponse);
		
		return busquedaSucursalresponse;
	}
	
	public ReportosResponse validaPlazoTaza(ReportosValidaPlazoTasaRequestTO plazoTasaRequestTO) throws InversionesGenericException{
		
		ReportosTransactions reportosTransactions=(ReportosTransactions)new WebServiceInterfaceFactory(REPORTOS).getInstance();
		ReportosResponse response=reportosTransactions.ejecuta(plazoTasaRequestTO);
		return response;
		
	}

	public int altaClienteMonitoreo(String idAlnova) throws InversionesGenericException {
		InversionesDAO inversionesDAO=new InversionesDAO();
		
		int idCliente=inversionesDAO.busquedaClienteSimple(idAlnova);
		if(idCliente==-1){
			P011_DatosClienteRequest clienteRequest=new P011_DatosClienteRequest();
			clienteRequest.setNumeroCliente(idAlnova);
			P011_DatosClienteResponse clienteResponse;

			clienteResponse=datosClienteAlnova(clienteRequest);

			AltaClienteRequestTO altaClienteRequestTO=new AltaClienteRequestTO();
			altaClienteRequestTO.setApellidoMaterno(clienteResponse.getApellidoMaterno());
			altaClienteRequestTO.setApellidoPaterno(clienteResponse.getApellidoPaterno());
			altaClienteRequestTO.setCurp(clienteResponse.getCURP());
			altaClienteRequestTO.setIdAlnova(idAlnova);
			altaClienteRequestTO.setNombre(clienteResponse.getNombre());
			altaClienteRequestTO.setStatusActivo("1");
			altaClienteRequestTO.setUsuarioModifico(idAlnova);
			idCliente=inversionesDAO.altaCliente(altaClienteRequestTO);
		}
		return idCliente;
	}
	
	public ReportosEstadoCuentaResponseTO getReportosEstadoCuenta(ReportosEstadoCuentaRequestTO  requestTO ) throws InversionesGenericException{
		ReportosTransactions reportosTransactions = ( ReportosTransactions ) new WebServiceInterfaceFactory( REPORTOS ).getInstance();
		ReportosResponse response=reportosTransactions.ejecuta( requestTO );
		return ( ReportosEstadoCuentaResponseTO ) response;
	}
	
	public ReportosFechaAperturaResponseTO getFechaAperturaReportos(ReportosFechaAperturaRequestTO fechaAperturaRequest) throws InversionesGenericException{

		ReportosTransactions reportosTransactions=(ReportosTransactions)new WebServiceInterfaceFactory(REPORTOS).getInstance();
		ReportosResponse reportosResponse=null;
		ReportosFechaAperturaResponseTO fechaAperturaResponse = null;
		
		reportosResponse=reportosTransactions.ejecuta(fechaAperturaRequest);
		fechaAperturaResponse = new ReportosFechaAperturaResponseTO(reportosResponse.getParametros());
		if(fechaAperturaResponse!=null){
			if(Validator.isEmptyData(fechaAperturaResponse.getFechaInicioInversion())){
				reportosTransactions.setStatus("FALLIDO");
				InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
				exceptionTO.setLevel(LEVEL_WEB_SERVICES);
				exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
				throw new InversionesGenericException(exceptionTO);
			}
		}
		
		return fechaAperturaResponse;
	}
}