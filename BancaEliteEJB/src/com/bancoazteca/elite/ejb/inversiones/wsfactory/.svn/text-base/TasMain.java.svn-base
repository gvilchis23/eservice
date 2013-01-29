package com.bancoazteca.elite.ejb.inversiones.wsfactory;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import org.apache.axis.message.MessageElement;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;

public class TasMain {

	public static void main(String[] args) throws MalformedURLException, ServiceException{
		
		EjecutaTransaccion ejecutaTransaccion=new EjecutaTransaccion();
		ejecutaTransaccion.setDatosEntrada("PORTAL=ELITE|ICONTRATO=30000001|IORDEN=129486|NUM_CUS=00866008");
		ejecutaTransaccion.setTransaccionTAS("TCP01");
		String respuesta=null;
		
		
		Map<String,String>mapa=new HashMap<String, String>();	
		mapa.put("WSDL_URL","http://10.51.202.39/TestTas/ReportosWS?wsdl");
		mapa.put("NAME_SPACE","http://ws.tas.mx.org/");
		mapa.put("SERVICE_NAME","ReportosWSService");
		mapa.put("PORT_BINDING","tns:ReportosWSBinding");
		mapa.put("PORT_NAME","ReportosWSPort");
		
		try {
			respuesta=WebServiceConectionFactory(mapa,ReportosWS.class,ejecutaTransaccion);
			System.out.println(respuesta);
			respuesta=respuesta.replaceAll("<Salida>","");
			respuesta=respuesta.replaceAll("</Salida>","");

			System.out.println(respuesta);

			MessageElement messageElement=null;

			try {
				messageElement = XMLDecode.buildXMLMessageElement(respuesta);
			} catch (XmlDecodeException e) {
				System.out.println("hubo error en la transaccion");
			}
			System.out.println(messageElement.item(0).getNodeValue());
			respuesta =messageElement.item(0).getNodeValue();
			String[]datos=respuesta.split("\\|");
			System.out.println(datos[0]);

			Matcher errorMatch = Pattern.compile("^E[A-Z][0-9]{3}").matcher(datos[0]);
			Matcher avisoMatch = Pattern.compile("^A[A-Z][0-9]{3}").matcher(datos[0]);
			System.out.println("Error: "+errorMatch.matches());
			System.out.println("Aviso: "+avisoMatch.matches());
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		
		mapa.put("WSDL_URL","http://10.50.166.223/conexion_tf/servicio_conexion.asmx?WSDL" );
		mapa.put("NAME_SPACE","http://tempuri.org/");
		mapa.put("SERVICE_NAME","Servicio_Conexion");
		mapa.put("PORT_BINDING","tns:Servicio_ConexionSoap");
		mapa.put("PORT_NAME","Servicio_ConexionSoap");
		
		
		
		try {
			System.out.println(WebServiceConectionFactory(mapa,Servicio_ConexionSoap.class,"MP93","USER/NETNET~BRANCH/4037~ENTITY/0127~TERMINAL/NKRL~FUNCKEY/01~CONTNUM/46245200003525~PERIOD/04-08~FISFOL/666666~BANKFOL/55555"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String WebServiceConectionFactory(Map<String,String>mapaConfig,Class clase,Object...parametros) throws ServiceException, MalformedURLException{
		ServiceFactory sf2 = ServiceFactory.newInstance();
		String wsdlURI2 =mapaConfig.get("WSDL_URL");
		URL wsdlURL2 = new URL(wsdlURI2);
		Service ots2 = sf2.createService(wsdlURL2,new QName(mapaConfig.get("NAME_SPACE"),mapaConfig.get("SERVICE_NAME")));
		Object port2 = ots2.getPort(new QName(mapaConfig.get("PORT_BINDING"), mapaConfig.get("PORT_NAME")),clase);
		System.out.println(port2.getClass().getCanonicalName());
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
			s=(String)m.invoke(port2,parametros);
		} catch (SecurityException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}