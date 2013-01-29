package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.bancoazteca.elite.beans.BancoSpeiTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiRequestTO;
import com.bancoazteca.elite.beans.TransferenciasSpeiResponseTO;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.PropertiesService;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Frecuente_Tiempo_AireTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;


public class Bancos_SpeiCommand extends CommandBase{

	
	
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		
		TransferenciasSpeiRequestTO speiRequestTO = new TransferenciasSpeiRequestTO();
		TransferenciasSpeiResponseTO speiResponseTO = new TransferenciasSpeiResponseTO();
		
		Collection <BancoSpeiTO> bancTO = null;
		ResourceFacadeSL bean = getDelegate();
		
		speiRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
		speiResponseTO = bean.getTransferenciaSpeiInvocacion(speiRequestTO);
		
		PropertiesService propertiesService = PropertiesService.getInstance();
		
		String cantidadBancosString = propertiesService.getPropertie( COLECCION_BANCOS_ORDENADOS , CANTIDAD_BANCOS_A_ORDENAR );
		bancTO =  speiResponseTO.getBancos();
		int cantidadBancos = Integer.parseInt(cantidadBancosString);
		String [] bancosAOrdenar = new String[cantidadBancos];
		for(int i=1; i<=cantidadBancos ; i++){
			bancosAOrdenar[i-1] = propertiesService.getPropertie( COLECCION_BANCOS_ORDENADOS , CLAVE_BANCOS_A_ORDENAR+i );			
		}
		
		com.bancoazteca.eservice.command.base.beans.BancosTO bnco = new com.bancoazteca.eservice.command.base.beans.BancosTO();
		bancTO =  speiResponseTO.getBancos();
		Iterator<BancoSpeiTO> i = (Iterator<BancoSpeiTO>)bancTO.iterator();
		
		Collection<String> bancosSpei = new ArrayList<String>();		
		Collection<BancoSpeiTO> bancosSpeiPrimerosTO = new ArrayList<BancoSpeiTO>();
		Collection<String> bancosSpeiResto = new ArrayList<String>();
		boolean flag = false;
		while(i.hasNext()){ 
			BancoSpeiTO bncoTO = (BancoSpeiTO)i.next();
			//cambio para el ordenamiento de los bancos
			for(int j=0; j<cantidadBancos;j++){
				if(bncoTO.getKey().equals(bancosAOrdenar[j])){
					bancosSpeiPrimerosTO.add(bncoTO);
					flag = true;
				}
			}
			if(!flag){
				bancosSpeiResto.add(bncoTO.getDescription());
			}else{
				flag=false;
			}
		}
		
		bancosSpei = ordenaPrimerosBancos(bancosSpeiPrimerosTO, bancosAOrdenar, bancosSpeiResto);
		
		bnco.setColeccion_bancos(bancosSpei);
		//Descomentar para ordenar primero los 10 bancos mas importantes
		//response.addAttribute(bnco);
		response.addAttribute(bancTO);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<String> ordenaPrimerosBancos(Collection<BancoSpeiTO> listaBancosPrincipales, String [] bancosAOrdenar, Collection<String> bancosSpeiResto){
		Collection<String> lista = new ArrayList<String>();		
		for(int i=0; i<bancosAOrdenar.length; i++){
			for(BancoSpeiTO bncoTO : listaBancosPrincipales){
				if(bancosAOrdenar[i].equals(bncoTO.getKey())){
					lista.add(bncoTO.getDescription());
				}
			}
		}
		for(String banco : bancosSpeiResto){
			lista.add(banco);
		}
		return lista;
	}
}
