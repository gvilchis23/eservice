package com.bancoazteca.eservice.command.base.alnova;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.commons.xml.XmlEncodeException;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaRequestTO;
import com.bancoazteca.elite.ejb.alnova.beans.EjbAlnovaResponseTO;
import com.bancoazteca.elite.ejb.alnova.utils.AlnovaException;
import com.bancoazteca.elite.ejb.facade.segundo.ResourceFacadeSegundoSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.eservice.beans.Cipher;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.StatusTransactionTO;
import com.bancoazteca.eservice.command.response.ResponseTransactionTO;
import com.bancoazteca.eservice.util.xml.XMLEncoderEService;
import com.bancoazteca.eservice.util.xml.XmlDecoderEService;
import com.bancoazteca.eservice.utils.Cifrado;


public class CommandServiceAlnova {

	private static final Logger log = Logger.getLogger(CommandServiceAlnova.class);
	private XMLEncoderEService encoder;
	private final String E_SERVICE = "eservices";
	private final String ROOT_TAG = "bancoazteca";

	public CommandServiceAlnova(){
		Collection<String> rules = new ArrayList<String>();
		rules.add("TO");
		encoder = new XMLEncoderEService(rules,null);

	}

	public String service(MessageElement messageElement) throws XmlEncodeException{
		log.info("----> CommandServiceAlnova <----");
		String xml = "";
		XmlDecoderEService decoder = new XmlDecoderEService();	
		String idaplicacion = (String)decoder.toDeserialize("idAplicacion", messageElement, null);
		Cipher certificado = (Cipher) decoder.toDeserialize("certificado", messageElement, null);

		ResponseTransactionTO responseTransaction=new ResponseTransactionTO();
		StatusTransactionTO statusTransactionTO=new StatusTransactionTO();
		String idtransaccion="";
		String descripcionTransaccion="";
		try {
			if(validate(idaplicacion, certificado)){	
				idtransaccion = (String)decoder.toDeserialize("idtransaccion", messageElement, null);
				descripcionTransaccion = (String)decoder.toDeserialize("descripcionTransaccion", messageElement, null);
				log.info("idtransaccion: "+ idtransaccion);
				log.info("descripcionTransaccion: "+ descripcionTransaccion);
				Object peticion=(Object)Class.forName("com.bancoazteca.elite.ejb.alnova.beans."+idtransaccion+descripcionTransaccion+"RequestTO");
				HashMap<String, String> mappedBeans = new HashMap<String, String>();
				mappedBeans.put("request", "com.bancoazteca.elite.ejb.alnova.beans."+idtransaccion+descripcionTransaccion+"RequestTO");
				peticion = (Object)decoder.toDeserialize("request", messageElement, mappedBeans);
				CommandBase commandBase = new CommandBase();

				EjbAlnovaRequestTO ejbAlnovaRequestTO=(EjbAlnovaRequestTO) peticion;
				ResourceFacadeSegundoSL facadeSegundoSL=commandBase.getDelegateSegundo();
				//Object response=(Object)Class.forName("com.bancoazteca.elite.ejb.alnova.beans."+idtransaccion+descripcionTransaccion+"ResponseTO");

				//response =(Object)facadeSegundoSL.ejecutaTransaccionAlnova(ejbAlnovaRequestTO);
				//EjbAlnovaResponseTO responseTO = (EjbAlnovaResponseTO)response;
				EjbAlnovaResponseTO responseTO = (EjbAlnovaResponseTO)facadeSegundoSL.ejecutaTransaccionAlnova(ejbAlnovaRequestTO);
				

				responseTransaction.setDataService(responseTO);

				statusTransactionTO.setDescripcion("Transaccion Exitosa");
				statusTransactionTO.setId("0");

				responseTransaction.setStatus(statusTransactionTO);
				
			}else{				
				statusTransactionTO.setDescripcion("Lo sentimos su usuario y/o certificado no son validos");
				statusTransactionTO.setId("-2");
				responseTransaction.setStatus(statusTransactionTO);			
			}
		}catch (AlnovaException e) {
			e.printStackTrace();
			statusTransactionTO.setDescripcion(e.getAlnovaExceptionTO().getDescripcionError());
			statusTransactionTO.setId("-4");
			responseTransaction.setStatus(statusTransactionTO);

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			statusTransactionTO.setDescripcion("Lo sentimos la transaccion que ha invocado es incorrecta o no se cuanta con su implementacion. "+idtransaccion+descripcionTransaccion);
			statusTransactionTO.setId("-3");
			responseTransaction.setStatus(statusTransactionTO);
		}catch (Exception e) {
			e.printStackTrace();
			statusTransactionTO.setDescripcion("Lo sentimos por el momento no podemos atenderle.(Error general)");
			statusTransactionTO.setId("-1");
			responseTransaction.setStatus(statusTransactionTO);
		}

		
		
			encoder.add(E_SERVICE, responseTransaction);
			xml = encoder.parseBean(ROOT_TAG);
		

		return xml;
	}

	public boolean validate(String idAplicacion, Cipher cifrado) throws NoSuchAlgorithmException, IOException {
		log.info("----> Validando..... <----");
		boolean validCertificate = false;
		if(!Validator.isEmptyData(idAplicacion) && cifrado != null ){
			log.info("IdAplicacion: "+ idAplicacion);
			log.info("Cifrado: "+ cifrado.toString());
			String md5CompanyCertificate=Cifrado.MD5(cifrado.toString());				
			String pass = PropertiesService.getInstance().getPropertie(PropertiesService.USERS, "user.proxyelite."+idAplicacion);

			if (pass!=null && md5CompanyCertificate.equals(pass)){
				validCertificate=true;
			}
		}
		return validCertificate;
	}

}
