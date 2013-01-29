package com.bancoazteca.ebanking.mail;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Calendar;
//import java.util.Enumeration;
//import javax.mail.Header;
import java.util.Properties;
import javax.mail.BodyPart;

import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.util.PropertiesService;


public class MailSender {
	private static Logger log = Logger.getLogger(MailSender.class);
	private static DataSource dataSource = null;

	private boolean mailBD = false;

	public boolean isMailBD() {
		return mailBD;
	}

	public void setMailBD(boolean mailBD) {
		this.mailBD = mailBD;
	}
	Object contenido;
	MimeMultipart mm = new MimeMultipart();
	Object atachments;
	String file_name = null;
	int tipo_archivo = 0;

	public void send( MailDO mailDO ) throws MailSenderException, IOException {
		PropertiesService configuracion = PropertiesService.getInstance();
		send( mailDO, configuracion.getProperties("mail.properties") );		
	}
	
	/**
	 * Envia un correo
	 * @param mailDO Los datos necesarios para enviar el correo.
	 * @throws MailSenderException en caso que ocurra un error.
	 */
	public void send( MailDO mailDO, Properties properties ) throws MailSenderException {
		log.info("ENTRO A MANDAR EMAIL");
		StringBuffer mail_to = new StringBuffer();
		StringBuffer mail_cc = new StringBuffer();
		StringBuffer mail_bcc = new StringBuffer();
		String subject;
		log.info("ANTES DE ENTRAR AL TRY");
		try {
			Session mSession = Session.getInstance( properties, null);
			Message message = new MimeMessage(mSession);
			message.setFrom( new InternetAddress( mailDO.getFrom() ) );
			Address dests[] = getAddresses(mailDO.getTORecipients());
			if (dests != null) {
				if (dests != null) {
					message.setRecipients(Message.RecipientType.TO, dests);
					for (int i = 0; i < dests.length; i++) {
						mail_to.append(((Address) dests[i]).toString() + " ,");
					}
				}
				dests = getAddresses(mailDO.getCCRecipients());
				if (dests != null) {
					message.setRecipients(Message.RecipientType.CC, dests);
					for (int i = 0; i < dests.length; i++) {
						mail_cc.append(((Address) dests[i]).toString() + " ,");
					}
				}
				dests = getAddresses(mailDO.getBCCRecipients());
				if (dests != null) {
					message.setRecipients(Message.RecipientType.BCC, dests);
					for (int i = 0; i < dests.length; i++) {
						mail_bcc.append(((Address) dests[i]).toString() + " ,");
					}
				}
				message.setSubject(mailDO.getSubject());
				if (mailDO.getContent() instanceof Multipart) {
					message.setContent((Multipart) mailDO.getContent());
					tipo_archivo = 1;
				} else {
					message.setContent(mailDO.getContent(), "text/plain");
					tipo_archivo = 0;
				}
				log.info("EMPIEZA A MANDAR ENLA BASE DE DATOS");
				if ( mailBD ) {
					log.info("Folio: M" + fecha() + instancia());
					String folio = "M" + fecha() + instancia();
					ResultSet rs = null;
					OutputStream os = null;
					Connection connection = null;
					java.sql.Statement st = null;
					java.sql.Statement stmt = null;
					PreparedStatement preparedstatement = null;
					double id_ebanking_mail = 0;
					mm = (MimeMultipart) mailDO.getContent();
					handleMessage2(mm);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					String tipo_contenido = new String(mm.getContentType());
					tipo_contenido = tipo_contenido.replaceAll("\\s", "");
					mm.writeTo(out);
					subject = message.getSubject();
					log.info("antes de entrar al segundo try");
					try {
						String Query;
						Query = "SELECT SEQ_EBANKING_MAIL.NEXTVAL FROM DUAL";
						connection = dataSource.getConnection();
						connection.setAutoCommit(false);
						stmt = connection.createStatement();
						rs = stmt.executeQuery(Query);
						if (rs.next()) {
							id_ebanking_mail = rs.getDouble(1);
						}
						rs.close();
						stmt.close();
						Query = "INSERT INTO EBANKING_MAIL (SUBJECT, MAIL_TO, MAIL_CC, MAIL_BCC, CONTENIDO, ID_EBANKING_MAIL, ENCABEZADO, FOLIO, ES_MULTIPARTE, ULTIMA_MODIFICACION, USUARIO_MODIFICO)" + "VALUES (?,?,?,?,EMPTY_BLOB(),?,?,?,?,SYSDATE,'EBANKING')";
						preparedstatement = connection.prepareStatement(Query);
						preparedstatement.setString(1, subject);
						String to = mail_to.toString();
						to = to.substring(0, to.length() - 2);
						String cc = mail_cc.toString();
						if (!cc.equals("")) {
							cc = cc.substring(0, cc.length() - 2);
						}
						String bcc = mail_bcc.toString();
						if (!bcc.equals("")) {
							bcc = bcc.substring(0, bcc.length() - 2);
						}
						preparedstatement.setString(2, to);
						preparedstatement.setString(3, cc);
						preparedstatement.setString(4, bcc);
						preparedstatement.setDouble(5, id_ebanking_mail);
						preparedstatement.setString(6, tipo_contenido.trim());
						preparedstatement.setString(7, folio);
						preparedstatement.setInt(8, tipo_archivo);
						preparedstatement.execute();
						preparedstatement.close();
						st = connection.createStatement();
						Query = "SELECT contenido FROM EBANKING.EBANKING_MAIL WHERE ID_EBANKING_MAIL=" + id_ebanking_mail + " FOR UPDATE ";

						rs = st.executeQuery(Query);
						if (rs.next()) {
							Blob info = rs.getBlob(1);
							os = ((oracle.sql.BLOB) info).getBinaryOutputStream();
							os.write(out.toByteArray());
							os.close();
						}
						connection.commit();
						connection.setAutoCommit(true);
						rs.close();
						st.close();
						connection.close();
						log.info("Se grabaran los datos del mail en la base de datos");
					} catch (SQLException exception) {
						log.error("cerrando conexion de BD en sqlexception");
						log.error( "", exception );
						try {
							connection.rollback();
							if (st != null) {
								st.close();
							}
							if (stmt != null) {
								stmt.close();
							}
							if (preparedstatement != null) {
								preparedstatement.close();
							}
							if (connection != null) {
								connection.close();
							}
						} catch (SQLException ex) {
							log.error( "Error al cerrar la conexion" );
							log.error( "", ex );
						}
					} catch (Exception exception) {
						log.error("hubo una exception en Exception");
						log.error( "", exception );
					} finally {
						log.info("cerrando conexion de BD");
						try {
							if (st != null) {
								st.close();
							}
							if (stmt != null) {
								stmt.close();
							}
							if (preparedstatement != null) {
								preparedstatement.close();
							}
							if (connection != null) {
								connection.close();
							}
						} catch (SQLException ex) {
							log.error("Error al cerrar la conexion");
							log.error( "", ex );
						}
					}
				} else {
					Transport.send(message);
				}
			}
		} catch (IOException ex) {
			log.error("IOException: " + ex.getMessage());
			throw new MailSenderException(ex.getMessage());
		} catch (MessagingException ex) {
			log.error("MessagingException: " , ex);
			throw new MailSenderException(ex.getMessage());
		}
	}

	private Address[] getAddresses(List<String> list) throws MessagingException {
		int size = list.size();
		if (size == 0) {
			return null;
		}
		Address address[] = new Address[size];
		Iterator<String> iterator = list.iterator();
		for (int idx = 0; iterator.hasNext(); idx++) {
			address[idx] = getAddress((String) iterator.next());
		}
		return address;
	}

	private Address getAddress(String address) throws AddressException {
		return new InternetAddress(address);
	}

	public void handleMessage(Message message) throws IOException, MessagingException {
		Object content = message.getContent();
		log.info("Entra a handleMessage");
		if (content instanceof String) {
			String tmp = (String) content;
			log.info("Contenido1:" + tmp);
			contenido = tmp;
		} else if (content instanceof Multipart) {
			log.info("handleMessage el content instanceof Multipart");
			Multipart mp = (Multipart) content;
			handleMultipart(mp);

		// handle multi part   
		}
	}

	public void handleMessage2(Multipart multipart) throws IOException, MessagingException {
		for (int i = 0; i < multipart.getCount(); i++) {
			try {
				BodyPart Bp = multipart.getBodyPart(i);
				//System.out.println("ContentTypeOri:"+i+" " +Bp.getContentType());
				//System.out.println("ContentTypeHn:"+i+" " +Bp.getDataHandler().getContentType());
				String content = Bp.getDataHandler().getContentType();
				Bp.addHeader("Content-Type", content);
				if (content.indexOf("application") >= 0) {
					Bp.addHeader("Content-Transfer-Encoding", "binary");
				} else if (content.indexOf("image") >= 0) {
					Bp.addHeader("Content-Transfer-Encoding", "binary");
					Bp.addHeader("Content-Disposition", "inline");
				} else {
					Bp.addHeader("Content-Transfer-Encoding", "8bit");
				}
				//Bp.addHeader("Content-Transfer-Encoding", MimeUtility.getEncoding(Bp.getDataHandler()));
				Object obj = Bp.getContent();
				if (obj instanceof Multipart) {
					handleMessage2((Multipart) obj);
				}
//				Enumeration<?> enume = Bp.getAllHeaders();
//				while (enume.hasMoreElements()) {
//					Header head = (Header) enume.nextElement();
//					System.out.println("name:"+i+" "+head.getName());
//					System.out.println("value:"+i+" "+head.getValue());
//				}
			} catch (Exception ex) {
				log.error( "next message" );
				log.error( "", ex );
			}
		}
	}

	public void handleMultipart(Multipart mp) throws MessagingException, IOException {
		int count = mp.getCount();
		String tmp;
		System.out.println("entra a handleMultipart");
		for (int i = 0; i < count; i++) {
			BodyPart bp = mp.getBodyPart(i);
			Object content = bp.getContent();
			if (content instanceof String) {
				tmp = (String) content;
				System.out.println("Contenido2:" + tmp);
				contenido = tmp;
			} else if (content instanceof InputStream) {
				if (bp.getFileName() != null) {
					log.info("Existen algunos atachments..");
					atachments = (InputStream) content;
					file_name = (bp.getFileName());
					System.out.println("atachments" + ((InputStream) atachments).available());
					System.out.println("nombre del archivo" + bp.getFileName());

				}
			} else if (content instanceof Message) {
				System.out.println("handleMultipart tiene content instanceof Message");
				Message message = (Message) content;
				handleMessage(message);
			} else if (content instanceof Multipart) {
				System.out.println("handleMultipart tiene content instanceof Multipart");
				Multipart mp2 = (Multipart) content;
				handleMultipart(mp2);
			}
		}
	}

	public String fecha() {

//		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");

		Calendar calendar = Calendar.getInstance();
		String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String ano = String.valueOf(calendar.get(Calendar.YEAR));
		String hora = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String min = String.valueOf(calendar.get(Calendar.MINUTE));
		String seg = String.valueOf(calendar.get(Calendar.SECOND));

		if (dia.length() < 2) {
			dia = "0" + dia;
		}
		if (mes.length() < 2) {
			mes = "0" + mes;
		}
		if (hora.length() < 2) {
			hora = "0" + hora;
		}
		if (min.length() < 2) {
			min = "0" + min;
		}
		if (seg.length() < 2) {
			seg = "0" + seg;
		}

		String hoy = dia + mes + ano + hora + min + seg;
		return hoy;
	}

	public String instancia() {
		String instance = System.getProperty("sistema.nombre.instancia");
		if (instance == null || instance.trim().length() == 0) {
			instance = "ebankingl";
		}
		return instance;
	}
	public static final String MAILFOOTPRINT = "<tr><td colspan='2'>" +
			"<font size='1' face='Verdana, Arial, Helvetica, sans-serif'>" +
			"<br>AVISO IMPORTANTE:<br><br>" +
			"<a href='http://www.bancoazteca.com.mx'>Banco Azteca S.A.</a>" +
			"NO ENV&Iacute;A mensajes de correo electr&oacute;nico a sus " +
			"clientes solicitandoles informaci&oacute;n confidencial." +
			"</font></td></tr><tr><td colspan='2' align='center'>" +
			"<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>" +
			"<br>Por favor, NO SE DEJE ENGA&Ntilde;AR<br><br>" +
			"<hr color='AB1520' size='0'></font></td></tr><td colspan='2'>" +
			"<font size='1' face='Verdana, Arial, Helvetica, sans-serif'>" +
			"<br>Este correo se constituye como una referencia de los t&eacute;rminos " +
			"en que la operaci&oacute;n se realiz&oacute;, el &uacute;nico comprobante " +
			"oficial es el estado de cuenta que emite <a href='http://www.bancoazteca.com.mx'>" +
			"Banco Azteca S.A.</a><br><br>Favor de no reenviar este documento." +
			"</font> </td> </tr>	";
}
