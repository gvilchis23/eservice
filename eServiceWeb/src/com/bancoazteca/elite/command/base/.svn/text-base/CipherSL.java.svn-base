package com.bancoazteca.elite.command.base;

import java.io.IOException;

import com.bancoazteca.eservice.util.security.SecureDataUtil;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class CipherSL {
	
	private static final String TAG_CIFRADO="cipher";
	private Document DOCUMENT=null;

	public static String cifraResponse(String xmlResponse){
		CipherSL cipher=new CipherSL();
		Nodes listaNodos=cipher.doQuery(xmlResponse);
		cipher.cambiarCifrados(listaNodos, false);
		return cipher.toXml();
	}
	
	
	
	public static String cifraRequest(String xmlRequest) {
		CipherSL cipher=new CipherSL();
		Nodes listaNodos=cipher.doQuery(xmlRequest);
		cipher.cambiarCifrados(listaNodos, true);
		return cipher.toXml();
	}
	
	private Nodes doQuery(String xml){
		Builder parser = new Builder();
		xml=xml.replaceAll(">\\s*<", "><");
		try {
			this.DOCUMENT = parser.build(xml,null);
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Nodes listaNodos=this.DOCUMENT.query("//*[@"+TAG_CIFRADO+"]");
		return listaNodos;
	}
	
	private  void cambiarCifrados(Nodes listaNodos,boolean request){
		for(int i=0;i<listaNodos.size();i++){
			Node node=listaNodos.get(i);
			Element element=(Element)node;
			Attribute attribute=element.removeAttribute(element.getAttribute(TAG_CIFRADO));
			String cifrado=attribute.getValue();
			Attribute atributo=request?slTotripleDES(cifrado):tripleDEStoSl(cifrado);
			element.addAttribute(atributo);
		}
		return;
	}
	
	private Attribute slTotripleDES(String cifrado){
		Encriptador cipher=new Encriptador();
		String unCipher=cipher.Decrypt(cifrado);
		String nuevoCifrado=cifrar(unCipher);
		Attribute atributo=new Attribute("cipher",nuevoCifrado);
		return atributo;
	}
	
	private Attribute tripleDEStoSl(String cifrado){
		String unCipher=desCifrar(cifrado);
		Encriptador cipher=new Encriptador();
		String nuevoCifrado=cipher.Crypt(unCipher);
		Attribute atributo=new Attribute("cipher",nuevoCifrado);
		return atributo;
	}
	
	
	 private String cifrar(String texto){
		String ciphered = "";
		try{
			ciphered = SecureDataUtil.cryptData(SecureDataUtil.getKey(), SecureDataUtil.getCipherInstance(), texto);
		}catch(Exception e){
			System.out.println("Error al cifrar el texto "+texto);
		}
		return ciphered;
	}
	
	 private String desCifrar(String texto){
		String ciphered = "";
		try{
			ciphered = SecureDataUtil.decryptData(SecureDataUtil.getKey(), SecureDataUtil.getCipherInstance(), texto);
			
		}catch(Exception e){
			System.out.println("Error al descifrar el texto "+texto);
		}
		return ciphered;
	} 
	
	 
	 private String toXml(){
		 String xml=this.DOCUMENT.toXML();
		 if(!xml.matches("encoding")){
			 xml=xml.replace("?>", " encoding=\"iso-8859-1\"?>");
		 }
		 return xml.replace("?>\n", "?>");
	 }

}
