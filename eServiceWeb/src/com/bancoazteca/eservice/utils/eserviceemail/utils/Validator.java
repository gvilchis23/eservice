package com.bancoazteca.eservice.utils.eserviceemail.utils;

public class Validator {
	public static final boolean isEmpty(String cadena){
		if(cadena == null || cadena.trim().length()==0){
			return true;
		}
		return false;
	}
}
