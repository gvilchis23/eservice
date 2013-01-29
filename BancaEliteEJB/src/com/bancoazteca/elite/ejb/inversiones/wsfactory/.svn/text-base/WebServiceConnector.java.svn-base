package com.bancoazteca.elite.ejb.inversiones.wsfactory;

import java.io.IOException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.Name;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.log4j.Logger;

public class WebServiceConnector {

	private String endPoint;
	private String methodName;
	
	Logger $_log=Logger.getLogger(WebServiceConnector.class);
	
	public WebServiceConnector(String endPoint, String methodName) {
		this.endPoint=endPoint;
		this.methodName=methodName;
	}
	
	public String callWebService(String... xmlRequest){
		String result = null;
		try{
			//String endpoint = "http://10.50.70.27:8080/eServiceWeb/services/EService";
			//String operationName = "eServices";
			
			Service service = new Service();
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new URL(endPoint));
			call.setOperationName(new QName(methodName));
			QName qname=new QName("http://tempuri.org/MTEjecutaTransaccion");
			SOAPHeaderElement header=new SOAPHeaderElement(qname);
			call.addHeader(header);
			Object[] objetos=new Object[xmlRequest.length];
			int i=0;
			for (String string : xmlRequest) {
				$_log.info(string);
				objetos[i]=string;
				i++;
			}
			
			result=(String)call.invoke(objetos);
			
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("IO");
		} catch (ServiceException e) {
			System.out.println("Service");
		}catch(Exception e)
		{
			System.out.println("Otra");
		}
		return result;
	}
}