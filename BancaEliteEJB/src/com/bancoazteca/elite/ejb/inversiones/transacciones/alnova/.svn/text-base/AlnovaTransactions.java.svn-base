package com.bancoazteca.elite.ejb.inversiones.transacciones.alnova;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.util.Formatter;

public class AlnovaTransactions{

	private String request;
	private String response;
	private String status;
	
	Logger $_log=Logger.getLogger(AlnovaTransactions.class);
	
	private AlnovaConnection alnovaBean;
	
	public AlnovaTransactions() throws IOException{
		alnovaBean= new AlnovaConnection();
	}
	
	public AlnovaResponse ejecuta(AlnovaRequest alnovaRequest) throws InversionesGenericException{
		AlnovaResponse response=null;
			
		try{
			response=alnovaBean.execute(alnovaRequest);
			this.response=response.alnovaResponse();
			this.request=response.alnovaRequest();
			status="EXITOSO";
		}catch(Exception ex)
		{
			this.request=alnovaBean.getRequest();
			this.response="Lo sentimos, ocurrió un error durante la comunicación con los procesos";
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_WEB_SERVICES);
			exceptionTO.setMessage(this.response);
			status="FALLIDO";
			throw new InversionesGenericException(exceptionTO);
		}finally{
			$_log.info( "#################### ALNOVA ####################");
			$_log.info("Request: "+this.request);
			$_log.info("Response: "+this.response);
			$_log.info( "################################################");
		}

		String mensajeAlnova = response.getMessage();
		if(mensajeAlnova.toLowerCase().contains("error")){
			$_log.info("Error en la transaccion de alnova: "+alnovaRequest.nombre());
			$_log.info("Mensage:" + mensajeAlnova);
			String codigo = Formatter.getCodigoErrorAlnova(mensajeAlnova);
			$_log.info("Error alnova: " + codigo);	
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_WEB_SERVICES);
			exceptionTO.setMessage("Lo sentimos, ha ocurrido un error en el proceso. "+codigo);
			status="FALLIDO";
			throw new InversionesGenericException(exceptionTO);
		}
		
		return response;
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
}