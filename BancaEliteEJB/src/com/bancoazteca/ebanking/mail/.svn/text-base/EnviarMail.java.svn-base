package com.bancoazteca.ebanking.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.util.PropertiesService;


public class EnviarMail {
	private static Logger log = Logger.getLogger( EnviarMail.class );

	public static void enviaMail( Hashtable<String,String> parametros, PropertiesService configuracion ) throws MailSenderException{
		EnviarMail.enviaMail( parametros, configuracion, null, null, null, "" );
	}
	public static void enviaMail( Hashtable<String,String> parametros, PropertiesService configuracion, List<String> to ) throws MailSenderException{
		EnviarMail.enviaMail( parametros, configuracion, to, null, null, "" );
	}
	public static void enviaMail( Hashtable<String,String> parametros, PropertiesService configuracion, List<String> to, List<String> cc ) throws MailSenderException{
		EnviarMail.enviaMail( parametros, configuracion, to, cc, null, "" );
	}
	public static void enviaMail( Hashtable<String,String> parametros, PropertiesService configuracion, List<String> to, List<String> cc, List<String> bcc ) throws MailSenderException{
		EnviarMail.enviaMail( parametros, configuracion, to, cc, bcc, "" );
	}
	
	public static void enviaMail( Hashtable<String,String> parametros, PropertiesService configuracion, List<String> to, List<String> cc, List<String> bcc, String prekey ) throws MailSenderException{
		String fileName="mailspei.properties";
		try{
			if( prekey != null && prekey.trim().length() > 0 ){
				if( !prekey.endsWith( "." ) )
					prekey = prekey.trim() + ".";
				else prekey = prekey.trim();
			}
			else prekey = "";
			
			MailDO mailDO = new MailDO();
			mailDO.setFrom( configuracion.getPropertie(fileName, prekey + "mail.from" ) );
			if( to != null )
				for( int i=0; i< to.size(); i++ )
					mailDO.addTORecipient( to.get( i ) );
			List<String> recipient = configuracion.getPropertyAsList(fileName, prekey + "mail.to" );
			if( recipient != null ){
				for( int i=0; i< recipient.size(); i++ )
					mailDO.addTORecipient( recipient.get( i ) );
			}
			if( cc != null )
				for( int i=0; i< cc.size(); i++ )
					mailDO.addCCRecipient( cc.get( i ) );
			recipient = configuracion.getPropertyAsList(fileName, prekey + "mail.cc" );
			if( recipient != null ){
				for( int i=0; i< recipient.size(); i++ )
					mailDO.addCCRecipient( recipient.get( i ) );
			}
			if( bcc != null )
				for( int i=0; i< bcc.size(); i++ )
					mailDO.addTORecipient( bcc.get( i ) );
			recipient = configuracion.getPropertyAsList(fileName, prekey + "mail.bcc" );
			if( recipient != null ){
				for( int i=0; i< recipient.size(); i++ )
					mailDO.addBCCRecipient( recipient.get( i ) );
			}
			mailDO.setSubject( configuracion.getPropertie(fileName, prekey + "mail.subject" ) );

			MimeMultipart mpRoot = new MimeMultipart("mixed");
			MimeMultipart mpContent = new MimeMultipart("related");
			MimeBodyPart contentPartRoot = new MimeBodyPart();
			contentPartRoot.setContent(mpContent);
			mpRoot.addBodyPart(contentPartRoot);
			
			String contenido = getContenido( PropertiesService.getResourceUrl( configuracion.getPropertie(fileName, prekey + "mail.html" )) );
			Enumeration<String> keys = parametros.keys();
			String key;
			String value;
			while( keys.hasMoreElements() ){
				key = keys.nextElement();
				value = parametros.get( key );
				value = value.replaceAll( "\\\\", "\\\\\\\\" );
				value = value.replaceAll( "\\$", "\\\\\\$" );
				contenido = contenido.replaceAll( key, value );
			}
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent( contenido, "text/html");
			mpContent.addBodyPart(messageBodyPart);

			List<String> cids = configuracion.getPropertyAsList( fileName, prekey + "mail.cid" );
			if( cids != null ){
				for( int i=0; i<cids.size(); i++ ){
					messageBodyPart = new MimeBodyPart();
					messageBodyPart.setHeader( "Content-ID", "<" + cids.get( i ) + ">" );
					messageBodyPart.setDataHandler( new DataHandler( PropertiesService.getResourceUrl( configuracion.getPropertie(fileName, prekey + "mail." + cids.get( i ) ) ) ) );
					mpContent.addBodyPart( messageBodyPart );
				}
			}
			mailDO.setContent(mpRoot);
			MailSender mailSender = new MailSender();
			String savedb = configuracion.getPropertie(fileName, prekey + "mail.savedb" );
			savedb = savedb.trim().equals( "" ) ? "false" : savedb;
			mailSender.setMailBD( Boolean.parseBoolean( savedb ) );
			log.info("Enviando correo");
			mailSender.send( mailDO, configuracion.getProperties("mail.properties") );
		}
		catch (Exception e) {
			log.error( "Error al enviar el correo: " , e );
			throw new MailSenderException();
		}
	}

	private static String getContenido( URL url ){
		StringBuffer contenido = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader( new InputStreamReader( url.openStream() ) );
			String str;
			while ( (str = in.readLine() ) != null ) {
				contenido.append( str );
			}
			in.close();
		} catch (Exception e) {}
		return contenido.toString();
	}
}
