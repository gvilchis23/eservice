package com.bancoazteca.elite.util;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.LocalidadDEXTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;

public class DineroExpressUtils {
	
	//Metodo para encontrar el ID de un Catalogo	
	public static final String COMISION = "tarifa";
	public static final String IMPUESTOS = "impuestos";
	public static final String DESCUENTOS = "descuentos";
	public static final String SUBTOTAL = "subtotal";
	public static final String TOTAL = "totalPagar";
	

	public static String findIdCatalogoDineroExpress(Collection<LocalidadDEXTO> listaCatalogo, String paisNombre) throws EliteDataException{
				
		 for(LocalidadDEXTO localidadDEXTO:listaCatalogo)
			if(localidadDEXTO.getValue().trim().equalsIgnoreCase(paisNombre))
				return localidadDEXTO.getId()+"";												
				
    	 Map<String, String> errors= new HashMap<String, String>();
		 String mensaje= "No se encontro '"+paisNombre+"' en el catalogo";
		 errors.put("Dinero_Express",mensaje);
		 throw new EliteDataException("Error", errors, EliteRules.LEVEL_ACTION_ERRORS);		
	}
	
    public static Map<String,String> getMapaCalculoComisiones(String xml) throws EliteDataException, Exception {
		
	    //XML:{error : false, tarifa : "100.00",impuestos : "16.00",descuentos : "0.00",descuentosCF : "0.0",subtotal : "1100.00",totalPagar : "1116.00"}		
		
	    Map<String, String> mapaComisiones= new HashMap<String, String>();
		JSONObject json;
		
		json = new JSONObject(xml);
		boolean error= new Boolean(json.getString("error"));
		if(error){
			Map<String, String> errors= new HashMap<String, String>();
			errors.put("Dinero_Express",json.getString("mensaje"));
			throw new EliteDataException("Error", errors, EliteRules.LEVEL_ACTION_ERRORS);				
		}else{
			mapaComisiones.put("comision", json.getString(COMISION));
			mapaComisiones.put("impuestos", json.getString(IMPUESTOS));
			mapaComisiones.put("descuentos", json.getString(DESCUENTOS));
			mapaComisiones.put("subtotal", json.getString(SUBTOTAL));
			mapaComisiones.put("montoTotal", json.getString(TOTAL));
			}

		return mapaComisiones;
		
	}
    
 public static EnvioDineroExpressResponseTO getCalculoComisiones(String xml) throws EliteDataException, ParseException {
		
	    //XML:{error : false, tarifa : "100.00",impuestos : "16.00",descuentos : "0.00",descuentosCF : "0.0",subtotal : "1100.00",totalPagar : "1116.00"}		
	 EnvioDineroExpressResponseTO responseTO = new EnvioDineroExpressResponseTO();
	    Map<String, String> mapaComisiones= new HashMap<String, String>();
		JSONObject json;
		
		json = new JSONObject(xml);
		boolean error= new Boolean(json.getString("error"));
		if(error){
			Map<String, String> errors= new HashMap<String, String>();
			errors.put("Dinero_Express",json.getString("mensaje"));
			throw new EliteDataException("Error", errors, EliteRules.LEVEL_ACTION_ERRORS);				
		}else{
			responseTO.setComision(json.getString(COMISION));
			responseTO.setImpuestos(json.getString(IMPUESTOS));
			responseTO.setDescuento(json.getString(DESCUENTOS));
			responseTO.setSubtotal(json.getString(SUBTOTAL));
			//responseTO.setMontoTotal(json.getString(TOTAL));
			}

		return responseTO;
		
	}
	
	
	
	
	// Metodo para Deserealizar cadena JSON a un MAPA de dinero express	
	public static Map<String,String> getMapaCatalogoDineroExpress(String xml) {
		
		//String xml= "{catalogo:[{optionValue:7, optionDisplay:'ARGENTINA'},{optionValue:2, optionDisplay:'GUATEMALA'},{optionValue:3, optionDisplay:'HONDURAS'},{optionValue:1, optionDisplay:'MEXICO'},{optionValue:5, optionDisplay:'PANAMA'},{optionValue:4, optionDisplay:'PERU'},]}";
		Map<String, String> mapaPaises= new HashMap<String, String>();
		JSONObject json;
		try {
			json = new JSONObject(xml);
			JSONArray arreglo= json.getJSONArray("catalogo"); 
			for(int i=0; i<arreglo.length(); i++){
				String llaveValor = arreglo.getString(i);
				JSONObject jsonLlaveValor = new JSONObject(llaveValor);
				mapaPaises.put(jsonLlaveValor.getString("optionValue"), jsonLlaveValor.getString("optionDisplay"));
				System.out.println("ID: "+jsonLlaveValor.getString("optionValue"));
				System.out.println("Value: "+jsonLlaveValor.getString("optionDisplay"));
			}

			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mapaPaises;
		
	}

}
