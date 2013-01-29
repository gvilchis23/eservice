package com.bancoazteca.elite.wsfotounica;

import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.client.Service;
import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.FotoUnicaRequestTO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.DAOException;

public class FotoUnicaDAO {
	
	private static final Logger log = Logger.getLogger(FotoUnicaDAO.class);
	
	public MessageElement consultaFotoUnicaDAO(FotoUnicaRequestTO fotoUnicaRequestTO)throws DAOException{
		MessageElement messageElement = null;
		XMLDecode decode = new XMLDecode();
		try{
			Service service = new Service();
			ServiceSoapStub serviceSoapStub = new ServiceSoapStub(new URL("http://10.63.150.116/wsCuentaFoto/Service.asmx"),service);
			BuscaFotoxCuentaResponseBuscaFotoxCuentaResult buscaFotoCuenta = serviceSoapStub.buscaFotoxCuenta(fotoUnicaRequestTO.getPstrIP(), fotoUnicaRequestTO.getPstrServerPort(), fotoUnicaRequestTO.getSucursal(), fotoUnicaRequestTO.getCuenta());
			
			org.apache.axis.message.MessageElement [] fotosCuentas = buscaFotoCuenta.get_any();
			Object fotoCuenta = null;
			for(int i=0; i<fotosCuentas.length; i++){
				fotoCuenta = fotosCuentas[i];
			}
			String xmlCadenaFoto = fotoCuenta.toString();
			log.info("xmlCadenaFoto.-"+xmlCadenaFoto);
			messageElement = decode.buildMessageElement(xmlCadenaFoto);
			
		}catch(RemoteException ex){
			throw new DAOException(ex);
		}catch(Exception ex){
			throw new DAOException(ex);
		} catch (XmlDecodeException ex) {
			throw new DAOException(ex);
		}
		return messageElement;
	}

}
