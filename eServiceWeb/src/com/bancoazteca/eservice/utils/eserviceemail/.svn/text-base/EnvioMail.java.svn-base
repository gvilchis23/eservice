package com.bancoazteca.eservice.utils.eserviceemail;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.beans.DestinoMailTO;
import com.bancoazteca.eservice.utils.eserviceemail.exception.ConfigurationFileNotFoundException;
import com.bancoazteca.eservice.utils.eserviceemail.utils.Validator;


public class EnvioMail {
	private static String host;
	private static String port;
	private static String from;
	private static String fromDesc;
	private static String hostName;
	
	public EnvioMail() {
		
			try {
				
				host=(String)PropertiesService.getInstance().getPropertie("eMail.properties", "mail.smtp.host");
				port=(String)PropertiesService.getInstance().getPropertie("eMail.properties", "port");
				from=(String)PropertiesService.getInstance().getPropertie("eMail.properties", "mail.from");
				fromDesc=(String)PropertiesService.getInstance().getPropertie("eMail.properties", "mail.from.descripcion");
				hostName=host+(!Validator.isEmpty(port)?(":"+port):"");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		
	}	
	@SuppressWarnings("unchecked")
	public void sendMail(String subject, String mensajeHtml, List<DestinoMailTO> correosDestino,String plantilla){
		try{
			Properties props = PropertiesService.getInstance().getProperties("eMail.properties");
			Session session = Session.getDefaultInstance(props, null);
			
			MimeMultipart rootMultiParte = new MimeMultipart( "mixed" );
			MimeMessage message = new MimeMessage(session);
			message.setFrom( new InternetAddress( from ) );
			message.setSubject( subject );
			message.setContent( rootMultiParte );
			for(DestinoMailTO obj:correosDestino){
				if (obj.getEmail() != null && obj.getNombreDestinatario() != null) {
					message.addRecipient( Message.RecipientType.TO, new InternetAddress( obj.getEmail(),obj.getNombreDestinatario() ) );
				} else if (obj.getEmail() != null) {
					message.addRecipient( Message.RecipientType.TO, new InternetAddress( obj.getEmail() ) );
				}
			}
	    	
			
			
			MimeMultipart multiParte = new MimeMultipart( "related" );
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent( multiParte );
			rootMultiParte.addBodyPart( bodyPart );
			
			bodyPart = new MimeBodyPart();
			bodyPart.setDataHandler( new DataHandler( new FileDataSource( mensajeHtml ) ) );
			String contenido = mensajeHtml;
			bodyPart.setContent( contenido , "text/html" );
			multiParte.addBodyPart( bodyPart );
			try{
				
				String embebidos=PropertiesService.getInstance().getPropertie("eMailPlantillas.properties", "enbed."+plantilla);				
				if(embebidos!=null){
					String[] splited=embebidos.split("\\|");				
					ArrayList<String> attachmentsEnbebidos=new ArrayList<String>();
					for (String string : splited) {
						attachmentsEnbebidos.add(string);
					}
					
					String[] aux;
					File file; 
					if(attachmentsEnbebidos!= null)
						for(String enbeds:attachmentsEnbebidos){
							aux = enbeds.split(" ");
							String sss="plantillas"+File.separator+plantilla+File.separator+aux[1];
							URL path=EnvioMail.class.getClassLoader().getResource(sss);
							file = new File(path.getPath());
							bodyPart = new MimeBodyPart();
							bodyPart.setHeader( "Content-ID", "<" + aux[0] + ">" );
							bodyPart.setDataHandler( new DataHandler( new FileDataSource( file.getAbsolutePath() ) ) );
							multiParte.addBodyPart( bodyPart );
						}
				}
			}catch(ClassCastException e){
				String attachmentsEnbebidos=(String)PropertiesService.getInstance().
				getPropertie("eMailPlantillas.properties", "enbed."+plantilla);
				
				String[] aux;
				File file; 
				if(!Validator.isEmpty(attachmentsEnbebidos)){
					aux = attachmentsEnbebidos.split(" ");
					String sss="plantillas"+File.separator+plantilla+File.separator+aux[1];
					String path=EnvioMail.class.getClassLoader().getResource(sss).getPath();
					file = new File(path);
					bodyPart = new MimeBodyPart();
					bodyPart.setHeader( "Content-ID", "<" + aux[0] + ">" );
					bodyPart.setDataHandler( new DataHandler( new FileDataSource( file.getAbsolutePath() ) ) );
					multiParte.addBodyPart( bodyPart );
				}
				
			}
			
			
			try{
				
				String attaches=PropertiesService.getInstance().getPropertie("eMailPlantillas.properties", "attachment."+plantilla);
				
				if(attaches!=null){
					String[] attachm=attaches.split("\\|");
					ArrayList<String> attachments=new ArrayList<String>();
					
					for (String string : attachm) {
						attachments.add(string);
					}
					String[] aux;
					File file; 
					if(attachments!= null){
						for(String attach : attachments){
							aux = attach.split(" ");
							String fileStirng="plantillas"+File.separator+plantilla+File.separator+aux[0];
							String path=EnvioMail.class.getClassLoader().getResource(fileStirng).getPath();
							URL url = new File(path).toURL();
							BodyPart adjunto = new MimeBodyPart();
							adjunto.setDataHandler( new DataHandler( url ) );
							adjunto.setFileName( aux[1] );
							rootMultiParte.addBodyPart( adjunto );
						}
					}
				}
			}catch(ClassCastException e){
				String attachments=(String)PropertiesService.getInstance().
				getPropertie("eMailPlantillas.properties", "attachment."+plantilla);
				String[] aux;
				File file; 
				if(!Validator.isEmpty(attachments)){
					aux = attachments.split(" ");
					
					String fileStirng="plantillas"+File.separator+plantilla+File.separator+aux[0];
					String path=EnvioMail.class.getClassLoader().getResource(fileStirng).getPath();
					URL url = new File(path).toURL();
					BodyPart adjunto = new MimeBodyPart();
					adjunto.setDataHandler( new DataHandler( url ) );
					adjunto.setFileName( aux[1] );
					rootMultiParte.addBodyPart( adjunto );
				}
			}
			
			
			Transport.send( message );
		}
		catch (Exception e)
		{e.printStackTrace();
		}
	}
	
}
