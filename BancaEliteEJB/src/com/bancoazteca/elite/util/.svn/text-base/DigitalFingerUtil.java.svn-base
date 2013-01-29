package com.bancoazteca.elite.util;

import java.io.IOException;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.Element;

import com.bancoazteca.elite.beans.DispositivoHuellaTO;
import com.bancoazteca.elite.beans.HuellaDigitalTO;
import com.bancoazteca.elite.commons.XMLFinder;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;

public class DigitalFingerUtil {
	
	public DispositivoHuellaTO getDigitalFingerUtil(MessageElement messageElement) throws XmlDecodeException, IOException{
		DispositivoHuellaTO dispositivoHuellaTO = new DispositivoHuellaTO();
		final String querySecurityTemp = "for $a in //elite/ebanking_web_ClienteVO/securityTemp  return $a";
		Element securityTempXML = XMLFinder.getElement(messageElement.toString(), querySecurityTemp);
		MessageElement messageSecurityTemp = new MessageElement(securityTempXML);
		XMLDecode decode = new XMLDecode();
		HuellaDigitalTO huellaMuerta = (HuellaDigitalTO)decode.buildBean(HuellaDigitalTO.class, messageSecurityTemp, "huellaMuerta");
		String lop = decode.getString(messageElement, "lop");
		String llpub = decode.getString(messageElement, "llpub");
		
		dispositivoHuellaTO.setLongitudHuella(lop);
		dispositivoHuellaTO.setLlavePublica(llpub);
		dispositivoHuellaTO.setHuellaMuerta(huellaMuerta);
		
		return dispositivoHuellaTO;
	}
	
	
}
