package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.InternacionalesBancosRequestTO;
import com.bancoazteca.elite.beans.InternacionalesBancosResponseTO;
import com.bancoazteca.elite.beans.InternacionalesCiudadTO;
import com.bancoazteca.elite.beans.InternacionalesPaisTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.PaisesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class CiudadesCommand extends CommandBase  {

	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		try {
		InternacionalesBancosRequestTO internacionalesBancosRequestTO = new InternacionalesBancosRequestTO();
		
		CiudadesForm ciudadesForm = (CiudadesForm)getFormBean();
		Collection<InternacionalesCiudadTO> ciudadesTO= null;
		ResourceFacadeSL bean = getDelegate();
		Collection<PaisesTO> collection_Pais;
		if(session.getAttribute(PAISES_TO)== null){
			PaisesCommand paises = new PaisesCommand();
			paises.ejecucion(session);
		}	

		collection_Pais = (Collection)session.getAttribute(PAISES_TO);
		PaisPredicate paisPredicate = new PaisPredicate();
		String pais= new String();
		
		if (session.getAttribute("PAISBANCO") == null){
			pais = ciudadesForm.getPais();			
		}else{
			pais = (String)session.getAttribute("PAISBANCO");
			session.addAttribute("PAISBANCO", null);
		
		}
		paisPredicate.setPais(pais.toUpperCase());
		InternacionalesPaisTO internacionalesPaisTO = (InternacionalesPaisTO) CollectionUtils.find(collection_Pais, paisPredicate);
		
		internacionalesBancosRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
		internacionalesBancosRequestTO.setPais(internacionalesPaisTO.getClavePais());
		
		InternacionalesBancosResponseTO internacionalesBancosResponseTO = bean.getInternacionalesCiudades(internacionalesBancosRequestTO);
		com.bancoazteca.eservice.command.base.beans.CiudadesTO ciudad = new com.bancoazteca.eservice.command.base.beans.CiudadesTO();

		Collection<String> collectionCiudad= new ArrayList<String>();
		String nombreCiudad = new String();
		
		ciudadesTO = internacionalesBancosResponseTO.getCiudades();
		Iterator<InternacionalesCiudadTO> i = (Iterator<InternacionalesCiudadTO>)ciudadesTO.iterator();
		
		while(i.hasNext()){
			InternacionalesCiudadTO ciudaddTO = (InternacionalesCiudadTO)i.next(); 			
			nombreCiudad = ciudaddTO.getNombreCiudad();
			collectionCiudad.add(nombreCiudad);
		}
		ciudad.setColeccion_ciudades(collectionCiudad);
		session.addAttribute(CIUDADES_TO, ciudadesTO);
		response.addAttribute(ciudad);
		
		}catch (EliteDataException e) {			
				buildErrorResponse(e, response);
		}
		
		
		return response;
	}
	
	private class PaisPredicate implements Predicate {

		private String pais;

		public boolean evaluate(Object obj) {
			InternacionalesPaisTO  paisTO = (InternacionalesPaisTO) obj;
			
			if (paisTO.getNombrePais().equalsIgnoreCase(pais)) {
				return true;
			} else {
				return false;
			}
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

	}
		
}

