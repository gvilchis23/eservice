package com.bancoazteca.elite.ejb.inversiones.transacciones.reportos;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mx.tas.ws.EjecutaTransaccion;
import org.mx.tas.ws.ReportosWS;
import org.mx.tas.ws.ReportosWSProxy;

import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.wrappers.ReportosEstadoCuentaResponseTO;


public class ReportosTransactions {

	private String request;
	private String response;
	private String status="FALLIDO";
	
	private Logger $_log=Logger.getLogger(ReportosTransactions.class);

	public ReportosTransactions(){
	}

	public ReportosResponse ejecuta(ReportosRequest reportosRequest) throws InversionesGenericException{
				
		$_log.info("@@@@@@@@@@@@@@@@@@@@ TAS @@@@@@@@@@@@@@@@@@@@");
				
		ReportosCodec reportosCodec=new ReportosCodec();
		request=reportosCodec.encode(reportosRequest.map(), reportosRequest.getKeysRequest());
		$_log.info("Peticion: "+request);
		$_log.info("Transaccion: "+reportosRequest.getTransaccion());
		
		EjecutaTransaccion ejecutaTransaccion=new EjecutaTransaccion();
		ejecutaTransaccion.setDatosEntrada(request);
		ejecutaTransaccion.setTransaccionTAS(reportosRequest.getTransaccion());
	
		ReportosWS reportosWS=new ReportosWSProxy().getReportosWS();
//		ReportosWS reportosWS=null;
		try {
			
			response=reportosWS.transaccionTAS(ejecutaTransaccion);
								
			$_log.info("Respuesta: "+response);
			if(reportosRequest.getTransaccion().equals("TV001")){				
				return new ReportosResponse(reportosCodec.decodeValidacionPlazo(response,reportosRequest.getKeysResponse()));	
			} else if(reportosRequest.getTransaccion().equals("TCP01")){
				return new ReportosResponse(reportosCodec.decodeComprobante(response,reportosRequest.getKeysResponse()));	
			} else if(reportosRequest.getTransaccion().equals("TCR01")){
				 List<Map<String,String>> arregloResumenes=reportosCodec.decodeResumen(response,reportosRequest.getKeysResponse());	
				 return new ReportosResponse(arregloResumenes);
			} else if(reportosRequest.getTransaccion().equals("TCR02")){
				 List<Map<String,String>> arregloResumenes=reportosCodec.decodeResumen(response,reportosRequest.getKeysResponse());	
				 return new ReportosResponse(arregloResumenes);
			} else if( reportosRequest.getTransaccion().equals( "TCC01" ) ){
				List<Map<String, String>> arregloResumenes=reportosCodec.decodeConciliacion( response, reportosRequest.getKeysResponse());
				return new ReportosResponse( arregloResumenes );
			} else if( reportosRequest.getTransaccion().equals( "TCE01" ) ){
				ReportosEstadoCuentaResponseTO estadoCuentaResponseTO = reportosCodec.decodeEstadoCuenta( response, reportosRequest.getKeysResponse());
				return estadoCuentaResponseTO;
			}
		} catch (RemoteException e) {
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_WEB_SERVICE_CONNECTION);
			exceptionTO.setMessage("Lo sentimos, existió un error de comunicación con el departamento de Tesorería.");
			response="El webservice TAS no se encuentra disponible.";
			throw new InversionesGenericException(exceptionTO);
		} catch (InversionesGenericException e) {
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
						
			exceptionTO = e.getInversionesExceptionTO();
			if( ( exceptionTO != null ) && ( !exceptionTO.getLevel().equalsIgnoreCase("AVISO") ) ) {
				exceptionTO.setMessage("Lo sentimos, existió un error al invocar el servicio de Tesorería.");
			}else if( exceptionTO == null ){
				exceptionTO=new InversionesExceptionTO();
				exceptionTO.setMessage("Lo sentimos, existió un error al invocar el servicio de Tesorería.");
			}
			
			exceptionTO.setLevel(InversionesGenericException.LEVEL_WEB_SERVICES);
			response="Se genero un error al invocar TAS.";
			throw new InversionesGenericException(exceptionTO);
		}
        
		$_log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Map<String,String> mapa=reportosCodec.decode(response,reportosRequest.getKeysResponse());
		status="EXITOSO";
		return new ReportosResponse(mapa);	
	}
	
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}