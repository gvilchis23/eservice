package com.bancoazteca.elite.ejb.inversiones.wsfactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import org.apache.log4j.Logger;
import com.bancoazteca.elite.util.PropertiesService;

public class WSConnectionFactory {
	
	private Logger logger=Logger.getLogger(WSConnectionFactory.class);
	
	public static final String ALNOVA="alnova";
	public static final String REPORTOS="reportos";
	
	private String serviceName;
	private String nameSpace;
	private String portName;
	private String wsdlUrl;
	private String portBinding;
	private String wsInterface;
	
	public WSConnectionFactory(String serviceName) throws IOException{
		PropertiesService propertiesService=PropertiesService.getInstance();
		wsdlUrl=propertiesService.getPropertie("CuentasInversion.properties","webservice."+serviceName+".wsdl");
		nameSpace=propertiesService.getPropertie("CuentasInversion.properties","webservice."+serviceName+".namespace");
		portName=propertiesService.getPropertie("CuentasInversion.properties","webservice."+serviceName+".portname");
		this.serviceName=propertiesService.getPropertie("CuentasInversion.properties","webservice."+serviceName+".servicename");
		portBinding=propertiesService.getPropertie("CuentasInversion.properties","webservice."+serviceName+".portbinding");
		wsInterface = propertiesService.getPropertie("CuentasInversion.properties","webservice."+serviceName+".interface");		
		logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		logger.info("servicio: "+this.serviceName);
		logger.info("name space: "+nameSpace);
		logger.info("puerto: "+portName);
		logger.info("wsdl: "+wsdlUrl);
		logger.info("port binding: "+portBinding);
		logger.info("ws Interface: "+wsInterface);
	}
	
	public String ejecuta(Object... param) throws ClassNotFoundException, MalformedURLException, ServiceException{
		Class clase=Class.forName(wsInterface);
		return WebServiceConectionFactory(clase,param);
	}
	
	private String WebServiceConectionFactory(Class clase,Object... parametros) throws ServiceException, MalformedURLException{
		ServiceFactory sf2 = ServiceFactory.newInstance();
		String wsdlURI =wsdlUrl;
		URL wsdlURL = new URL(wsdlURI);
		Service ots2 = sf2.createService(wsdlURL,new QName(nameSpace,serviceName));
		Object port2 = ots2.getPort(new QName(portBinding,portName),clase);
		logger.info(port2.getClass().getCanonicalName());
		Method[] metodos=clase.getMethods();
		String nombre=metodos[0].getName();
		Class[] clases=new Class[parametros.length];
		int i=0;
		String s="";
		for (Object objeto : parametros){
			clases[i]=objeto.getClass();
			i++;
		}
		try {		
			Method m=port2.getClass().getMethod(nombre,clases);
			logger.info("nombre de metodo: "+nombre);
			for (Class class1 : clases) {
				logger.info("param"+class1.getCanonicalName());
			}
			s=(String)m.invoke(port2, parametros);
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static void main(String[] args){
		
		EjecutaTransaccion ejecutaTransaccion=new EjecutaTransaccion();
		ejecutaTransaccion.setDatosEntrada("TAC01");
		ejecutaTransaccion.setTransaccionTAS("TAC01");
		
		WSConnectionFactory connectionFactory;
		try {
			connectionFactory = new WSConnectionFactory(WSConnectionFactory.REPORTOS);
			connectionFactory.ejecuta(ejecutaTransaccion);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
}
