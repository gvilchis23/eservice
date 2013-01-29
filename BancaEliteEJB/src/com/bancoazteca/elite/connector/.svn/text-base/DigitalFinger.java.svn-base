package com.bancoazteca.elite.connector;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class DigitalFinger {
	
	private static final Logger log = Logger.getLogger(DigitalFinger.class);
	
	public static final String URL = "/seguridad/digitalFingerPartElite.elite";
	
	public static final String DIGITAL_FINGET_USER= "digitalFingerUser";
	public static final String DIGITAL_FINGET_OPTION = "getDigitaloOption";
	public static final String GET_DIGITAL_FINGET = "getDigitalFinger";
	public static final String DIGITAL_FINGET_VALUE = "digitalFingerValue";
	public static final String DIGITAL_FINGET_OBJECT = "digitalFingerObject";
	
	
	private final String REGISTER_DIGITAL_FINGER= "register";
	private final String ADD_DIGITAL_FINGER= "add";	
	private final String DIGITAL_FINGER_SIZE = "digitalFingerSize";
    private final String DIGITAL_FINGER_PARTS = "digitalFingerParts";
    private final String DIGITAL_FINGER_PART_VALUE = "digitalFingerPartValue";
    private final String DIGITAL_FINGER_PART = "digitalFingerPart";
	
	private final BigDecimal seed;
	private final BigDecimal digitalFingerLength;
	
	private final String digitalFinger;
	
	
	public DigitalFinger(String digitalFinger){
		this.seed = new BigDecimal(200);
		this.digitalFingerLength = new BigDecimal(digitalFinger.length());
		this.digitalFinger = digitalFinger;
	}
	
	public void sendDigitalFingerParts(Connector connector,String user) throws SessionExpiredException, URISyntaxException, HttpException, IOException{
		Map<Integer, String> parts = getDigitalFingerParts();
		Map<String, String> params = new HashMap<String, String>();		
		String partsSize = Integer.toString(getPartsSize());
		
		params.put(DIGITAL_FINGET_OPTION, REGISTER_DIGITAL_FINGER);
		params.put(DIGITAL_FINGET_USER, user);
		params.put(DIGITAL_FINGER_SIZE, digitalFingerLength.toString());
		params.put(DIGITAL_FINGER_PARTS, partsSize);				
		connector.sendRequest(URL, params);		
		params.clear();
		
		log.info("DigitalFinger.sendDigitalFingerParts()"+parts.size());
		
		for(Integer key :parts.keySet()){
			String part = parts.get(key);
			params.put(DIGITAL_FINGET_OPTION, ADD_DIGITAL_FINGER);
			params.put(DIGITAL_FINGET_USER, user);
			params.put(DIGITAL_FINGER_PART, key.toString());
			params.put(DIGITAL_FINGER_PART_VALUE, part);
			connector.sendRequest(URL, params);
			params.clear();
		}
	}
	
	public Map<Integer, String> getDigitalFingerParts(){
		Map<Integer, String> parts = new HashMap<Integer, String>();
		long size = getPartsSize();		
		int index =0;
		int length = digitalFingerLength.intValue();
		int seedInt = seed.intValue();
		
		for(int j=0;j<size;j++){		
			String part = "";
			if((index+seedInt)<length){		
				part = this.digitalFinger.substring(index,(index+seedInt));
			}else{				
				part = this.digitalFinger.substring(index);
			}
				
			log.info(j+1+" ----> "+part);
			parts.put(new Integer(j+1), part);
			index+=seedInt;
		}
		return parts;
	}
	
	private int getPartsSize(){			
		log.info("longitud de la huella digital "+digitalFingerLength);
		log.info("longitud de los fragmentos "+seed);	
		double div = digitalFingerLength.doubleValue()/seed.doubleValue();		
		int size = (int)div;
		if (div%1>0){
			size++;
		}
		log.info("size "+size);
		return size;	
	}
	
	public static void main(String[] args) {
		StringBuffer clave = new StringBuffer();
		for(int j=0;j<2000;j++){
			clave.append(j);
		}
		DigitalFinger digitalFinger = new DigitalFinger(clave.toString());
		digitalFinger.getDigitalFingerParts();
	}
}
