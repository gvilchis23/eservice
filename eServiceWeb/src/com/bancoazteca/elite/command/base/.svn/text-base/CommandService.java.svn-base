package com.bancoazteca.elite.command.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.commons.xml.NewXMLEncoder;
import com.bancoazteca.elite.commons.xml.XMLEncoder;
import com.bancoazteca.elite.commons.xml.XmlEncodeException;
import com.bancoazteca.eservice.ws.EService;

public class CommandService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger log_$ = Logger.getLogger(CommandService.class);
	private static final String TAG_HUELLA="[huella_capturada]";
	private static final String TAG_PARTES_HUELLA="huella_partes";
//	private static final Map<String, Map<String,String>> huellasDigitales=new HashMap<String, Map<String,String>> ();
	private static final Map<String, Map<String,String>> huellasDigitales=new Hashtable<String, Map<String,String>> ();
	
	private static final String SWITCH_SECURITY_CIPHER = "switch_security";
	private static final String VALUE_SWITCH_SECURITY_CIPHER = "true";
	
	private String indice;
	private String fragmentoHuella;
	private String idSession;
	private XMLEncoder encoder;

	@Override
	public void init() throws ServletException {
		Collection<String> beans = new ArrayList<String>();
		beans.add("TO");
		beans.add("Alnova");		
		encoder =  new NewXMLEncoder( beans, new ArrayList<String>() );
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String respuesta = null;
		try {
			respuesta = eService( req, resp );
			respuesta=cryptSilverLight(respuesta,req.getParameter(SWITCH_SECURITY_CIPHER));
			encoder.add( "result" , respuesta );
			output( respuesta, resp );
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
	private String cryptSilverLight(String xml, String opcionSeguridad){
		if(VALUE_SWITCH_SECURITY_CIPHER.equals(opcionSeguridad)){
			//System.out.println("XML response antes: "+xml);
			xml=CipherSL.cifraResponse(xml);
			//System.out.println("XML response despues: "+xml);
		}
		return xml;
	}
	
	private void output(Object result, HttpServletResponse response ) throws IOException{
		String xml = "";
		try {
			xml=encoder.parseBean("elite");
		} catch (XmlEncodeException e) {		
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		ServletOutputStream stream = response.getOutputStream();
		stream.println(xml);
		stream.flush();
		stream.close();
	}


	public String eService(HttpServletRequest request,HttpServletResponse response) throws CommandException {
		 
		String regresa = null;
		try {
			
			String xml = getXmlRequest(request);
			
			if(isSpei(xml)){
				xml=anadeIPTag(xml,request);
			}
			
			String opcionSeguridad=request.getParameter("switch_security");
			if(VALUE_SWITCH_SECURITY_CIPHER.equals(opcionSeguridad)){
				//System.out.println("xml antes:  "+xml);
				xml=CipherSL.cifraRequest(xml);
				//System.out.println("xml despues:  "+xml);
			}
				
			xml = xml.replaceAll("¦", "+");
			xml = xml.replaceAll("\\|", "+");
			
			if(containsDigitalFingerParts(xml)){
				setData(xml);
				addHuellaPart(xml);
				regresa="<?xml version='1.0' encoding='iso-8859-1'?><bancoazteca><eservices><response><data_service/><status><idsesion cipher='"+idSession+"' /><descripcion_codigo value='Transaccion Exitosa' /><idservicio value='HUELLA_PARTES' /><codigo_operacion value='0' /><error_sistema /><tipo_operacion /></status></response></eservices></bancoazteca>";
			}else{
				
				xml = replaceHuellaCompleta( xml );
				
				xml = xml.replaceAll("<request>", "<request><idportal value='app_banca_elite' />");
				
				log_$.info("CommandService.eService request: "+xml);
				EService clienteUnico = null;
				synchronized( this ){
					clienteUnico = new EService();
				}
				String xmlResponse = clienteUnico.eServices(xml);
				
				regresa=xmlResponse.replaceAll("\"", "'");
				
				log_$.info("CommandService.eService response: "+ regresa );
									
			}
		}  catch (EJBException e) {
			e.printStackTrace();
		
		} catch(IOException e){
			e.printStackTrace();
		}
		
		return regresa;
	}
	

	private synchronized void setData(String xml) {
		this.indice=getTagContent(xml, "indice");
		this.idSession=getTagContent(xml, "idsesion");
		this.fragmentoHuella=getTagContent(xml, "huella_parte");
		log_$.info("[indice: "+indice+", fragmentoHuella: "+fragmentoHuella+"]");
	}
	
	private synchronized String replaceHuellaCompleta(String xml) {
		if(xml.indexOf(TAG_HUELLA) != -1 || xml.indexOf(TAG_HUELLA.toUpperCase()) != -1){
			setData(xml);
			//se setea para minusculas y mayusculas
			String huella=getHuella(huellasDigitales.get(idSession));
			xml=xml.replace(TAG_HUELLA,huella); 
			xml=xml.replace(TAG_HUELLA.toUpperCase(),huella);
			return xml;
		}else{
			return xml;
		}
	}

	private synchronized String getHuella(Map<String,String> list) {
		StringBuffer buffer= new StringBuffer();
		log_$.info("contenido del mapa: "+ list);
		for(int i=0;i<list.size();i++){
			buffer.append(list.get(String.valueOf(i)));
		}
		huellasDigitales.remove(idSession);
		log_$.info("huella capturada es: "+ buffer.toString());
		return buffer.toString();
	}

	private synchronized void addHuellaPart(String xml) {
		Map<String, String> fragmentosHuella =huellasDigitales.get(idSession);
		if( fragmentosHuella == null ){
//			fragmentosHuella= new HashMap<String,String>();
			fragmentosHuella= new Hashtable<String,String>();
		}
		fragmentosHuella.put(indice,fragmentoHuella );
		huellasDigitales.put(idSession, fragmentosHuella);
	}

	private boolean containsDigitalFingerParts(String xml) {
		if(xml!= null && xml.toLowerCase().indexOf(TAG_PARTES_HUELLA) != -1){
			return true;
		}
		return false;
	}
	
	public static String getTagContent(String response, String tag){
		try{
			String idtocken = response.substring(response.indexOf(tag));
			StringTokenizer str = new StringTokenizer(idtocken,"\'");
			str.nextToken();
			return str.nextToken();
		}catch (Exception e) {
			return null;
		}
	}
	
//	public static FormBean getFormBean(String response){
//		FormBean formBean = new FormBean();
//		try{
//			XmlDecoderEService decoder = new XmlDecoderEService();
//			MessageElement messageElement = null;
//			messageElement = XMLDecode.buildXMLMessageElement(response);
//			HashMap<String, String> mapped = new HashMap<String, String>();
//			mapped.put("request", formBean.getClass().getName());
//			formBean = (FormBean) decoder.toDeserialize("request",messageElement, mapped);
//			return formBean;
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		} catch (XmlDecodeException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	
	private String getXmlRequest(HttpServletRequest request) throws IOException {
		InputStream inputStream=request.getInputStream();
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		byte bytes[]=new byte[1024];
		int longitud=0;
		while((longitud=inputStream.read(bytes))>0){
			byteArray.write(bytes,0,longitud);
		}
		String xml=new String(byteArray.toByteArray());
		xml=xml.substring(0, xml.lastIndexOf(">")+1);
		xml=getNodeValue(xml);
		return xml;
	}
	
	
	private String getNodeValue(final String xml){
		Builder parser = new Builder();
		try {
			Reader reader=new StringReader(xml);
			Document document = parser.build(reader);
			Element child=(Element)document.getChild(0).getChild(0);
			String xmlRequest=child.getValue();
			return xmlRequest;
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private boolean isSpei(String xml){
		String valorOperacion=null;
		
		Builder parser=new Builder();
		Document document=null;
		try {
			document = parser.build(xml, null);
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Nodes nodes=document.query("//tipo_operacion/@value");
		
		for(int i=0;i<nodes.size();i++){
			Node node=nodes.get(i);
			Attribute attribute=(Attribute)node;
			valorOperacion=attribute.getValue();
		}
		
		if("spei".equalsIgnoreCase(valorOperacion)){
			return true;
		}
		return false;
	}
	
	private String anadeIPTag(String xml, HttpServletRequest request) {
		String ip=request.getRemoteAddr();
		Builder parser=new Builder();
		Document document=null;
		try {
			document = parser.build(xml, null);
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Nodes nodes=document.query("//request");
		
		if(nodes.size()==1){
			Node node=nodes.get(0);
			Element element=(Element)node;
			
			Element ipTagElement=new Element("ip_cliente");
			Attribute ipAttribute=new Attribute("value",ip);
			ipTagElement.addAttribute(ipAttribute);
			element.appendChild(ipTagElement);
			document=element.getDocument();
			xml=document.toXML();
		}
		
		return xml;
	}
	
}
