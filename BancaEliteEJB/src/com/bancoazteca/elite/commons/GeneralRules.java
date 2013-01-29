package com.bancoazteca.elite.commons;

import org.apache.axis.message.MessageElement;

import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class GeneralRules extends EliteRules{

	protected GeneralRules(MessageElement messageElement) throws EliteDataException {
		super(messageElement);		
	}
	
	public static synchronized void validateGeneralRules(MessageElement messageElement) throws EliteDataException{
		new GeneralRules(messageElement);
	}
	
	

}
