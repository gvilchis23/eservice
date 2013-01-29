package com.bancoazteca.eservice.command.noticias.conexionfinanciera;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ejb.EJBException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class NoticiasConexionFinancieraCommand extends CommandBase {
	public static final String NOTICIAS_PROPERTIES="Noticias.properties";
	public static final String CONEXION_FINANCIERA="servlet.conexionfinanciera";
	
	public Response solicitud(Session session)throws EJBException, SessionExpiredException, EliteDataException,UsuarioException {
		Response response= new Response();
		String respoString="Empty Response";
		try {
			respoString=peticionHttp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.addAttribute(respoString);
		return response;
	}
	
	/*
	 * Método que realiza una peticion HTTP hacia la dirección del path.
	 * Posteriormente realiza una consula del mismo path.
	 */
	public String peticionHttp()throws Exception{
		PropertiesService propertiesService= PropertiesService.getInstance();
		String PATH = propertiesService.getPropertie(NOTICIAS_PROPERTIES, CONEXION_FINANCIERA);
		String path=PATH+"?metodo=general";
		HttpPost post=new HttpPost(path);
		DefaultHttpClient cliente=new DefaultHttpClient();
		HttpResponse httpResponse=cliente.execute(post);
		HttpEntity entidad=httpResponse.getEntity();
		BufferedReader in=new BufferedReader(new InputStreamReader(entidad.getContent()));
		String linea="";
		String salida="";
		while((linea=in.readLine())!=null){
			salida+=linea;
		}
		return salida;
		
	}
}
