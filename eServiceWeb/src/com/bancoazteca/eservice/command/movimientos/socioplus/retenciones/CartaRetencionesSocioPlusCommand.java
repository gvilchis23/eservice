package com.bancoazteca.eservice.command.movimientos.socioplus.retenciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import com.bancoazteca.elite.beans.Carta_RetencionesTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.RetencionesRequestTO;
import com.bancoazteca.elite.beans.RetencionesResponseTO;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class CartaRetencionesSocioPlusCommand  extends CommandBase{


public Response ejecucion(Session session) throws Exception{
		

		RetencionesRequestTO retencionesRequestTO = new RetencionesRequestTO();
		RetencionesResponseTO retencionesResponseTO = new RetencionesResponseTO();
		Response response = new Response();		 

		ClienteTO clienteTO = (ClienteTO)session.getAttribute(CLIENTE_TO);
		
		String userName = clienteTO.getUserName();
		
		
		retencionesRequestTO.setUser(userName);
		ResourceFacadeSL facadeSL = getDelegate();
		retencionesResponseTO = facadeSL.consultarCartaRetenciones(retencionesRequestTO);
		
		
		Collection <Carta_RetencionesTO> coleccion_tarjetas_frecuentesTO = creaColeccionFrecuentes(retencionesResponseTO.getTarjetasFrecuentes());
		
		response.addAttribute(coleccion_tarjetas_frecuentesTO);	
		
		return response;
	}

		private Collection <Carta_RetencionesTO> creaColeccionFrecuentes(HashMap<String,String> mapaTarjetasFrecuentes){
			Collection <Carta_RetencionesTO> coleccion_tarjetas_frecuentesTO = new ArrayList <Carta_RetencionesTO>();
			Set<Entry<String, String>> entrySet = mapaTarjetasFrecuentes.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			Carta_RetencionesTO carta_RetencionesTO = new Carta_RetencionesTO();
			
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				
				if(entry.getKey().equalsIgnoreCase("name")){
					carta_RetencionesTO.setNombre_cliente(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("street")){
					carta_RetencionesTO.setCalle(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("neighborhood")){
					carta_RetencionesTO.setColonia(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("zipcode")){
					carta_RetencionesTO.setCp(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("rfc")){
					carta_RetencionesTO.setRfc_cliente(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("period")){
					carta_RetencionesTO.setPeriodo(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("account")){
					carta_RetencionesTO.setNumero_cuenta(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("datetime")){
					carta_RetencionesTO.setFecha_hora_emision(entry.getValue());
				}
				
				carta_RetencionesTO.setSucursal("Banca por Internet");
				carta_RetencionesTO.setRetenedor_azteca("BANCO AZTECA SA INSTITUCION DE BANCA MULTIPLE ");
				carta_RetencionesTO.setRfc_banco("BAIO205236Y8");
				
				if(entry.getKey().equalsIgnoreCase("IntNomBaz")){
					carta_RetencionesTO.setInteres_nominal_banco(entry.getValue());
				}
				

				if(entry.getKey().equalsIgnoreCase("IntRealBaz")){
					carta_RetencionesTO.setInteres_real_banco(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("IsrBaz")){
					carta_RetencionesTO.setIsr_banco(entry.getValue());
				}
				
				carta_RetencionesTO.setRetenedor_arrendadora("ARRENDADORA INTERNACIONAL AZTECA S.A. DE C.V.");
				carta_RetencionesTO.setRfc_arrendadora("AIA05092196A");
				
				if(entry.getKey().equalsIgnoreCase("IntNomArrendadora")){
					carta_RetencionesTO.setInteres_nominal_arrendadora(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("IntRealArrendadora")){
					carta_RetencionesTO.setInteres_real_arrendadora(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("IsrArrendadora")){
					carta_RetencionesTO.setIsr_arrendadora(entry.getValue());
				}
				carta_RetencionesTO.setRetenedor_iusacell("IUSACELL S.A. DE C.V. ");
				carta_RetencionesTO.setRfc_iusacell("IUS890616H6");
				
				
				if(entry.getKey().equalsIgnoreCase("IntNomIusacel")){
					carta_RetencionesTO.setInteres_nominal_iusacell(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("IntRealIusacel")){
					carta_RetencionesTO.setInteres_real_iusacell(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("IsrIusacel")){
					carta_RetencionesTO.setIsr_iusacell(entry.getValue());
				}
				
				carta_RetencionesTO.setRetenedor_tvazteca("TV AZTECA SAB DE C.V.");
				carta_RetencionesTO.setRfc_tvazteca("TAZ960904V78");
				
				
				if(entry.getKey().equalsIgnoreCase("IntNomTVAzteca")){
					carta_RetencionesTO.setInteres_nominal_tvazteca(entry.getValue());
				}
				
				if(entry.getKey().equalsIgnoreCase("IntRealTVAzteca")){
					carta_RetencionesTO.setInteres_real_tvazteca(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("IsrTVAzteca")){
					carta_RetencionesTO.setIsr_tvazteca(entry.getValue());
				}
					
				if(entry.getKey().equalsIgnoreCase("ImporteGastos")){
					carta_RetencionesTO.setImporte_gastos(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("IvaGastos")){
					carta_RetencionesTO.setIva_gastos(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("ImporteHonorarios")){
					carta_RetencionesTO.setImporte_honorarios(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("IvaHonorarios")){
					carta_RetencionesTO.setIva_honorarios(entry.getValue());
				}		
				
				if(entry.getKey().equalsIgnoreCase("totalIntNom")){
					carta_RetencionesTO.setTotal_interes_nominal(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("totalIntReal")){
					carta_RetencionesTO.setTotal_interes_real(entry.getValue());
				}
				if(entry.getKey().equalsIgnoreCase("totalIntArr")){
					carta_RetencionesTO.setTotal_isr_retenido_acreditable(entry.getValue());
				}
			}
			coleccion_tarjetas_frecuentesTO.add(carta_RetencionesTO);
			return coleccion_tarjetas_frecuentesTO;
			}	
}