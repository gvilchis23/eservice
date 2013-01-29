package com.bancoazteca.eservice.command.conexionfinanciera;

import java.math.BigDecimal;

import com.bancoazteca.elite.ejb.alnova.beans.TC84_ConsultaValorPlataAlnovaRequest;
import com.bancoazteca.elite.ejb.alnova.beans.TC84_ConsultaValorPlataAlnovaResponseTO;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Compra_venta_plataTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ConexionFinancieraCommand extends CommandBase {

	
	public Response solicitud(Session session)throws Exception{
		Response response=new Response();
		

		TC84_ConsultaValorPlataAlnovaRequest alnovaRequest=new TC84_ConsultaValorPlataAlnovaRequest();
		
		alnovaRequest.setCentro("4037");
		alnovaRequest.setCodent("0127");
		alnovaRequest.setFunckey("00");
		alnovaRequest.setCodfcc("OLP");    
		alnovaRequest.setFlgbcc("B");
		
		alnovaRequest.setIdAplicacion("app_banca_elite");
		
		alnovaRequest.setIdtransaccion("TC84");
		alnovaRequest.setDescripcionTransaccion("_ConsultaValorPlataAlnova");
		
		TC84_ConsultaValorPlataAlnovaResponseTO alnovaResponse=(TC84_ConsultaValorPlataAlnovaResponseTO)getDelegateSegundo().ejecutaTransaccionAlnova(alnovaRequest);

		Compra_venta_plataTO compraVentaPlataTO=new Compra_venta_plataTO();
		
		compraVentaPlataTO.setPrecio_compra(convertMontoTC84(alnovaResponse.getBDRT01()).toString());
		compraVentaPlataTO.setPrecio_venta(convertMontoTC84(alnovaResponse.getOFFRT01()).toString());
		
		response.addAttribute(compraVentaPlataTO);
		
		return response;
	}
	
	
	private static BigDecimal convertMontoTC84( String vTC ) {
        
        BigDecimal tc = null;
        int entero = 0;
        int decimal = 0;
        int longDecimal = 0;
        String cad = null;
        String cad2 = null;
        try{ 
            entero = 6;
            decimal = 6; 
            longDecimal = 2;
                        
            cad = vTC.substring(0, entero); //toma la parte que corresponde a los enteros
            cad2 = vTC.substring(entero, vTC.length()); // la parte sobrante debe ser de los decimales
            if(cad2.length() != decimal)
                System.out.println("Error: La longitud de los decimales no concuerda");
            
            cad2 = cad2.substring(0, decimal); //toma la parte que corresponde a los decimales
            cad2 = cad2.substring(0, longDecimal); //toma los decimales segun el properties
            
            tc = new BigDecimal(cad + "." + cad2);
                        
            System.out.println("Tipo de cambio: " + tc.toString());
        }catch(Exception e){
        	System.out.println("Error al dar formato al tipo de cambio: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        
        return tc;
    }
	
}