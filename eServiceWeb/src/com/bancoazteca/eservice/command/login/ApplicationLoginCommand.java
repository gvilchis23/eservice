package com.bancoazteca.eservice.command.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Login_AplicacionTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.base.session.SessionManager;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.utils.Cifrado;

public class ApplicationLoginCommand extends CommandBase {
	private static final Logger log= Logger.getLogger(ApplicationLoginCommand.class);
	public Response execute(Session session) throws Exception {
		
		log.info("ApplicationLoginCommand.login_aplicacion()");
		Response response = new Response();
		ApplicationLoginForm form = (ApplicationLoginForm) getFormBean();
		
		log.info("certificado recibido: " + form.getCertificado().toString());

		try{
			Login_AplicacionTO aplicacionTO = new Login_AplicacionTO();
			if(validatePassword(form)){//{authentication(form)){
				
				String id = SessionManager.getInstance().getValueSessionApplication(form.getAplicacion());
				
				if(Validator.isEmptyData(id)){
					id = SessionManager.getInstance().createApplicationSession(form.getAplicacion());
				}								
				
				aplicacionTO.setIdaplicacion(Cipher.valueOf(id));
				response.addAttribute(aplicacionTO);
			}else{
				response.setStatus(-1, "Existio un error al validar el certificado.", null);
			}
		}catch (InvalidKeyException ex) {
			log.info("La llave privada del certificado no es válida : " + ex.getMessage());
			response.setStatus(-1, "La llave privada del certificado no es válida.", null);
		} catch (NoSuchProviderException ex) {
			log.info("El proveedor del certificado no es una entidad CA autorizada : " + ex.getMessage());
			response.setStatus(-1, "El proveedor del certificado no es una entidad CA autorizada.", null);
		} catch (NoSuchAlgorithmException ex) {
			log.info("Error al abtener los algoritmos criptográficos necesarios: " + ex.getMessage());
			response.setStatus(-1, "Error al abtener los algoritmos criptográficos necesarios.", null);
		} catch (SignatureException ex) {
			log.info("La firma digital del certificado no es válida : " + ex.getMessage());
			response.setStatus(-1, "La firma digital del certificado no es válida.", null);
		} catch (CertificateExpiredException ex) {
			log.info("El certificado ha expirado: " + ex.getMessage());
			response.setStatus(-1, "El certificado ha expirado.", null);
		} catch (CertificateNotYetValidException ex) {
			log.info("El certificado ya no es válido o ha caducado: " + ex.getMessage());
			response.setStatus(-1, "El certificado ya no es válido o ha caducado.", null);
		} catch (IOException e) {
			log.info("Error en el certificado: " + e.getMessage());
			response.setStatus(-1, "Existio un error al validar el certificado.", null);
		} 
		
		return response;
	}

	public boolean authentication(ApplicationLoginForm form) throws IOException, CertificateException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException  {
	
		String companyCertificate = form.getCertificado().toString();
		//String companyName = form.getCompania();

		boolean validCertificate = false;
		File file = new File("x509.cer");
		FileOutputStream fop = new FileOutputStream(file);
		
		String inicioCertificado = "-----BEGIN CERTIFICATE-----";
		String finCertificado = "-----END CERTIFICATE-----";

		String companyCertificateFormated ="";
		
		if (file.exists()) {
			
			if(companyCertificate.regionMatches(0, inicioCertificado, 0, inicioCertificado.length())){
				log.info("Si contiene el inicio del certidficado");
				if(companyCertificate.regionMatches(companyCertificate.length()-finCertificado.length(), finCertificado, 0, finCertificado.length())){
					log.info("Si contiene el fin del certidficado");
					
					companyCertificateFormated = companyCertificate.replaceAll(inicioCertificado, inicioCertificado+"\n");
					companyCertificateFormated = companyCertificateFormated.replaceAll(finCertificado, "\n"+finCertificado);
					
				}
			}
			
			fop.write(companyCertificateFormated.getBytes());
			fop.flush();
			fop.close();
		}

		FileInputStream fr = new FileInputStream("x509.cer");
		CertificateFactory cf;

		cf = CertificateFactory.getInstance("X.509");
		X509Certificate c = (X509Certificate) cf.generateCertificate(fr);

		c.checkValidity();
		c.verify(c.getPublicKey());
		validCertificate = true;
		
		log.info("\tCertificado para: " + c.getSubjectDN());
		log.info("\tCertificado emitido por: " + c.getIssuerDN());
		log.info("\tEl certificado es valido desde " + c.getNotBefore() + " a " + c.getNotAfter());
		log.info("\tCertificado No.Serie# " + c.getSerialNumber());
		log.info("\tCertificado generado con el algoritmo " + c.getSigAlgName());

		//validCertificate = c.getSubjectDN().getName().indexOf(companyName) != -1;
		//String temp = "El certificado de la aplicacion cliente ";
		//temp += validCertificate ? "es válido." : "no es válido, ya que el nombre de la compañia ( " + companyName + " ) no coincide con el titular del certificado";

		return validCertificate;
	}	
	
	public boolean validatePassword(ApplicationLoginForm form) throws IOException, CertificateException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, Exception  {
		String md5CompanyCertificate=Cifrado.MD5(form.getCertificado().toString());
		String pass = PropertiesService.getInstance().getPropertie(PropertiesService.USERS, "user.proxyelite."+form.getAplicacion());
		boolean validCertificate = false;
		if (pass!=null && md5CompanyCertificate.equals(pass)){
			validCertificate=true;
		}
		return validCertificate;
	}	
}
