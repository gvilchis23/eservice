package com.bancoazteca.elite.ejb.alnova.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.transacciones.alnova.AlnovaResponse;
import com.bancoazteca.elite.util.Formatter;

public class AlnovaTransactions{

	private String request;
	private String response;
	private String status;
	
	Logger $_log=Logger.getLogger(AlnovaTransactions.class);
	
	private AlnovaConnection alnovaBean;
	
	public AlnovaTransactions(EjbAlnovaRequestTO ejbAlnovaRequestTO) throws IOException{
		alnovaBean= new AlnovaConnection(ejbAlnovaRequestTO);
	}
	
	public AlnovaResponse ejecuta(EjbAlnovaRequestTO ejbAlnovaRequestTO) throws AlnovaException{
		AlnovaResponse response=null;
			
		try{
			response=alnovaBean.execute(ejbAlnovaRequestTO);
			this.response=response.alnovaResponse();
			this.request=response.alnovaRequest();
			status="EXITOSO";
		}catch(Exception ex)
		{
			this.request=alnovaBean.getRequest();
			this.response="Lo sentimos, ocurrió un error durante la comunicación con los procesos";
			AlnovaExceptionTO exceptionTO=new AlnovaExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_WEB_SERVICE_CONNECTION);
			exceptionTO.setMessage(this.response);
			status="FALLIDO";
			throw new AlnovaException(exceptionTO);
		}finally{
			$_log.info( "#################### ALNOVA ####################");
			$_log.info("Request: "+this.request);
			$_log.info("Response: "+this.response);
			$_log.info( "################################################");
		}

		String mensajeAlnova = response.getMessage();
		if(mensajeAlnova.toLowerCase().contains("error")){
			$_log.info("Error en la transaccion de alnova: "+ejbAlnovaRequestTO.nombre());
			$_log.info("Mensage:" + mensajeAlnova);
			String codigo = Formatter.getCodigoErrorAlnova(mensajeAlnova);
			$_log.info("Error alnova: " + codigo);	
			AlnovaExceptionTO exceptionTO=new AlnovaExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_WEB_SERVICES);
			exceptionTO.setMessage("Lo sentimos, ha ocurrido un error en el proceso. "+codigo);
			exceptionTO.setDescripcionError(mensajeAlnova);
			status="FALLIDO";
			throw new AlnovaException(exceptionTO);
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