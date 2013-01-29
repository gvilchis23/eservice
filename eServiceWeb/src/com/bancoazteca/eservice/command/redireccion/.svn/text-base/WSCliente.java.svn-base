package com.bancoazteca.eservice.command.redireccion;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.MessageElement;

import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.Command;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.base.session.SessionManager;

public class WSCliente {
	
	private static String FILE="InstanciaAlterna.properties";
	private static String KEY_IP="instancia.ip";
	private static String KEY_PORT="instancia.port";
	private static String KEY_URL="instancia.url";
	private static String KEY_SERVICE="instancia.service";
	private static String TAG_REDIRECCION="redireccion";
	
	
	public static String callService(final String xml){
		Service servicio=new Service();
		PropertiesService propertiesService=PropertiesService.getInstance();
		String response=null;
		
		try {
			String endpoint=propertiesService.getPropertie(FILE, KEY_IP)+":"+propertiesService.getPropertie(FILE, KEY_PORT)+propertiesService.getPropertie(FILE, KEY_URL);
			System.out.println("instancia a llamar: "+endpoint);
			String serviceName=propertiesService.getPropertie(FILE, KEY_SERVICE);
			Call call=(Call)servicio.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(new QName(serviceName));
			
			response=(String)call.invoke(new Object[]{new String(xml)});
			System.out.println("Terminando el llamado");
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}
	
	public static String insertaTagIntercambio(final String xml){
		String xmlModificado=null;
		Element elemento=new Element(TAG_REDIRECCION);
		elemento.addAttribute(new Attribute("value","true"));
		Document document=null;
		Builder builder=new Builder();
		System.out.println("insertando el tag de intercambio");
		try {
			document=builder.build(xml, null);
			Nodes nodes=document.query("//request");
			if(nodes.size()>0){
				Node node=nodes.get(0);
				Element request=(Element)node;
				request.appendChild(elemento);
				document=request.getDocument();
				xmlModificado=toXml(document);
			}
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlModificado;
	}
	
	public static boolean existeTagIntercambio(final MessageElement messageElement){
		String xml=null;
		try {
			xml = messageElement.getAsString();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return existeTagIntercambio(xml);
	}
	
	
	public static boolean existeTagIntercambio(final String xml){
		boolean existe=false;
		Nodes nodos=realizaConsulta(xml, TAG_REDIRECCION);
		if(nodos.size()>0){
			existe=true;
		}
		return existe;
	}
	
	public static Nodes realizaConsulta(String xml,String tag){
		Builder builder=null;
		Document document=null;
		builder = new Builder();
		try {
			document = builder.build(xml,null);
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Nodes nodos=document.query("//"+tag);
		return nodos;
	}
	
	public static boolean verificaEstadoSesion(final String idSesion,final Command comando) throws Exception {

		boolean dejaloPasar=false;
		
		if(comando.isNotSession()){
			dejaloPasar=true;
		}else{
			Session session = SessionManager.getInstance().getSession(idSesion);
			if(session.getIdSession()!=null){
				dejaloPasar=true;
			}else{
				dejaloPasar=false;
			}
		}
		return dejaloPasar;
	}
	
	private static String toXml(Document document){
		String xmlTemp=null;
		xmlTemp=document.toXML();
		if(!xmlTemp.matches("encoding")){
			xmlTemp=xmlTemp.replace("?>", " encoding=\"iso-8859-1\"?>");
		 }
		 return xmlTemp.replace("?>\n", "?>");
	}
	
}
