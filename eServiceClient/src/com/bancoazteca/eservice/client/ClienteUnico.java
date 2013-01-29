package com.bancoazteca.eservice.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.bancoazteca.eservice.util.security.SecureDataUtil;

public class ClienteUnico {

	public String callWebService(String xmlRequest) {
		Object result = "";

		try {
			String endpoint = "http://10.50.16.97:8080/eServiceWeb/services/EService";
			
			String operationName = "eServices";
			System.out.println("********** A invocar el Web Service con url: " + endpoint + " y con el metodo " + operationName);
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(endpoint));
			call.setOperationName(new QName(operationName));
			System.out.println("********** Xml que se envia... " + xmlRequest);
			result = call.invoke(new Object[] { xmlRequest });
			System.out.println("********** Xml de respuesta... " + result);
			
			if( result != null ){
				String idAplicacion = getTagContent(result.toString(), "idaplicacion");
				String idSession = getTagContent(result.toString(), "idsesion");
				if( idAplicacion != null ){
					System.out.println("\nidAplicacion: " + idAplicacion);
				}
				if( idSession != null ){
					System.out.println("\nidSession: " + idSession);
				}
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return result + "";
	}

	public static String getTagContent(String response, String tag) {
		try {
			String idsesion = response.substring(response.indexOf(tag));
			StringTokenizer str = new StringTokenizer(idsesion, "\"");
			str.nextToken();
			return str.nextToken();
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {

		 probarCifrado("");

		 probarDesCifrado("");
		 
		ClienteUnico clienteUnico = new ClienteUnico();

		String URL_FILE = "C:\\PortalSilverlight\\eServiceDesarrollo\\eServiceClient\\src\\com\\bancoazteca\\eservice\\client\\xml\\";		
		
//		File file = new File( URL_FILE + "loginAplicacion.xml" );
//		File file = new File( URL_FILE + "RecuperaPassword_1.xml" );
//		File file = new File( URL_FILE + "RecuperaPassword_2.xml" );
//		File file = new File( URL_FILE + "RecuperaPassword_3.xml" );
//		File file = new File( URL_FILE + "RecuperaPassword_4.xml" );
//		File file = new File( URL_FILE + "LOGINCLIENTE.xml" );
		//File file = new File( URL_FILE + "OperacionesFrecuentes.xml" );
//		File file = new File( URL_FILE + "Saldos.xml" );
		
//		File file = new File( URL_FILE + "PagoServicios_1.xml" );
		
//		File file = new File( URL_FILE + "LiberacionCheque1.xml" );
//		File file = new File( URL_FILE + "LiberacionCheque2.xml" );
//		File file = new File( URL_FILE + "LiberacionCheque3.xml" );
//		File file = new File( URL_FILE + "LiberacionCheque4.xml" );
//		File file = new File( URL_FILE + "LiberacionCheque5.xml" );
		
		

//		File file = new File( URL_FILE + "Movimientos.xml" );
//		File file = new File( URL_FILE + "FrecuentesTransferencias.xml" );
//		File file = new File( URL_FILE + "Propias_1.xml" );
//		File file = new File( URL_FILE + "Propias_2.xml" );
//		File file = new File( URL_FILE + "Terceros_1.xml" );
//		File file = new File( URL_FILE + "Terceros_2.xml" );
//		File file = new File( URL_FILE + "Terceros_3.xml" );
//		File file = new File( URL_FILE + "Spei_1.xml" );
//		File file = new File( URL_FILE + "Spei_2.xml" );
//		File file = new File( URL_FILE + "Spei_3.xml" );
//		File file = new File( URL_FILE + "Logout.xml" );
//		File file = new File( URL_FILE + "LiberacionNomina_1.xml" );
//		File file = new File( URL_FILE + "LiberacionNomina_2.xml" );
//		File file = new File( URL_FILE + "LiberacionNomina_3.xml" );
		
//		File file = new File( URL_FILE + "EdoCuentaReportos_1.xml" );
//		File file = new File( URL_FILE + "EdoCuentaReportos_2.xml" );
//		File file = new File( URL_FILE + "EdoCuentaReportos_3.xml" );
//		File file = new File( URL_FILE + "PlazoTasa.xml" );
		File file = new File( URL_FILE + "OperacionesFrecuentes.xml" );
		
		FileReader fileReader;

		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String xmlRequest = "";
			while (bufferedReader.ready()) {
				xmlRequest += bufferedReader.readLine().trim();
			}

			String response = clienteUnico.callWebService(xmlRequest);

		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}

	public static String probarCifrado(String textoClaro){
		System.out.println("\n************** Cifrado ***************\n");
		String ciphered = "";
		try{
			ciphered = new SecureDataUtil().cryptData(SecureDataUtil.getKey(), SecureDataUtil.getCipherInstance(), textoClaro);
			System.out.println("El cifrado de "+textoClaro+" es: "+ciphered);
			
		}catch(Exception e){
			System.out.println("Error al cifrar el texto "+textoClaro);
		}
		System.out.println("\n************** Cifrado ***************\n");
		return ciphered;
	}
 public static String probarDesCifrado(String textoClaro){
		System.out.println("\n************** Cifrado ***************\n");
		String ciphered = "";
		try{
			ciphered = new SecureDataUtil().decryptData(SecureDataUtil.getKey(), SecureDataUtil.getCipherInstance(), textoClaro);
			System.out.println("El descifrado de "+textoClaro+" es: "+ciphered);
			
		}catch(Exception e){
			System.out.println("Error al descifrar el texto "+textoClaro);
		}
		System.out.println("\n************** Cifrado ***************\n");
		return ciphered;
	}

}
