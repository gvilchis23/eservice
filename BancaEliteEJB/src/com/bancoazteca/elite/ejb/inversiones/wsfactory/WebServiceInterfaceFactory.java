package com.bancoazteca.elite.ejb.inversiones.wsfactory;

import static com.bancoazteca.elite.ejb.inversiones.InversionesGenericException.*;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.util.PropertiesService;

public class WebServiceInterfaceFactory {

	private static Logger $_log=Logger.getLogger(WebServiceInterfaceFactory.class);
	private String fullyQualifiedName; 
	private String webServiceName;
	public WebServiceInterfaceFactory(String webServiceName) throws InversionesGenericException
	{
		this.webServiceName=webServiceName;
		PropertiesService propertiesService=PropertiesService.getInstance();
		String packageName;
		try {
			
			packageName=propertiesService.getPropertie("CuentasInversion.properties","webservice.generic.packagename");
			packageName=packageName+webServiceName+".";
			
			webServiceName = webServiceName.toLowerCase();
			String caracter=webServiceName.charAt(0)+"";
			webServiceName=caracter.toUpperCase()+webServiceName.substring(1);
			fullyQualifiedName=packageName+webServiceName+"Transactions";
			
		} catch (IOException e) {
			e.printStackTrace();
			InversionesExceptionTO inversionesExceptionTO=new InversionesExceptionTO();
			inversionesExceptionTO.setLevel(LEVEL_WEB_SERVICES);
			inversionesExceptionTO.setMessage("El archivo de configuracion no se encuentra");
			throw new InversionesGenericException(inversionesExceptionTO);
		}
	}
	
	public Object getInstance() throws InversionesGenericException{
		
			$_log.info("Instanciar a: "+fullyQualifiedName);
			try {
				
				return Class.forName(fullyQualifiedName).newInstance();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				InversionesExceptionTO inversionesExceptionTO=new InversionesExceptionTO();
				inversionesExceptionTO.setLevel(LEVEL_WEB_SERVICES);
				inversionesExceptionTO.setMessage("Error el servicio web "+webServiceName+" no existe");
				throw new InversionesGenericException(inversionesExceptionTO);
			}catch(Exception e){
				e.printStackTrace();
				InversionesExceptionTO inversionesExceptionTO=new InversionesExceptionTO();
				inversionesExceptionTO.setLevel(LEVEL_WEB_SERVICES);
				inversionesExceptionTO.setMessage("Error en el llamado al servicio web:"+webServiceName);
				throw new InversionesGenericException(inversionesExceptionTO);
			}
	}
}