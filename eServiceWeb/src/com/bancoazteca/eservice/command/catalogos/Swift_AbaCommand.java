package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.InternacionalesBancoTO;
import com.bancoazteca.elite.beans.InternacionalesBancosRequestTO;
import com.bancoazteca.elite.beans.InternacionalesBancosResponseTO;
import com.bancoazteca.elite.beans.InternacionalesCiudadTO;
import com.bancoazteca.elite.beans.InternacionalesPaisTO;
import com.bancoazteca.elite.commons.EliteRules;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.CiudadesTO;
import com.bancoazteca.eservice.command.base.beans.MensajesTO;
import com.bancoazteca.eservice.command.base.beans.PaisesTO;
import com.bancoazteca.eservice.command.base.beans.Banco_InternacionalTO;
import com.bancoazteca.eservice.command.base.beans.Codigo_Swift_AbaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;
import com.bancoazteca.eservice.validator.Errors;

public class Swift_AbaCommand extends CommandBase {
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		
		InternacionalesBancosRequestTO internacionalesBancosRequestTO = new InternacionalesBancosRequestTO();
		
		Swift_AbaForm swift_AabaForm = (Swift_AbaForm)getFormBean();
		Collection<InternacionalesBancoTO> bancoTO= null;
		Collection<InternacionalesBancoTO> swiftTO= null;
		ResourceFacadeSL bean = getDelegate();
		try{
			Collection<Codigo_Swift_AbaTO> collection_Banco = (Collection)session.getAttribute(SWIFT_ABA_TO);
			Collection<CiudadesTO>  collection_Ciudad;
			if(session.getAttribute(CIUDADES_TO)== null){
				CiudadesCommand ciudades = new CiudadesCommand();				
				String pais = swift_AabaForm.getPais();
				session.addAttribute("PAISBANCO", pais);
				ciudades.ejecucion(session);
			}	
			Collection<PaisesTO> collection_Pais = (Collection)session.getAttribute(PAISES_TO);
			collection_Ciudad = (Collection)session.getAttribute(CIUDADES_TO);
			PaisPredicate paisPredicate = new PaisPredicate();
			paisPredicate.setPais(swift_AabaForm.getPais());
			InternacionalesPaisTO internacionalesPaisTO = (InternacionalesPaisTO) CollectionUtils.find(collection_Pais, paisPredicate);
			
			CiudadPredicate ciudadPredicate = new CiudadPredicate();
			ciudadPredicate.setCiudad(swift_AabaForm.getCiudad());
			InternacionalesCiudadTO internacionalesCiudadTO = (InternacionalesCiudadTO) CollectionUtils.find(collection_Ciudad, ciudadPredicate);
			
			internacionalesBancosRequestTO.setUser(((ClienteTO)session.getAttribute(CLIENTE_TO)).getUserName());
			internacionalesBancosRequestTO.setPais(internacionalesPaisTO.getClavePais());
			internacionalesBancosRequestTO.setCiudad(internacionalesCiudadTO.getClaveCiudad());
			
			InternacionalesBancosResponseTO internacionalesBancosResponseTO = bean.getInternacionalesBancos(internacionalesBancosRequestTO);
			com.bancoazteca.eservice.command.base.beans.Codigo_Swift_AbaTO swift_aba = new com.bancoazteca.eservice.command.base.beans.Codigo_Swift_AbaTO();
	
			Collection<Banco_InternacionalTO> collectionSwift= new ArrayList<Banco_InternacionalTO>();
			Banco_InternacionalTO banco_InternacionalTO = null;
			
			bancoTO = internacionalesBancosResponseTO.getBancos();

			Iterator<InternacionalesBancoTO> i = (Iterator<InternacionalesBancoTO>)bancoTO.iterator();
			
			while(i.hasNext()){
				InternacionalesBancoTO bancTO = (InternacionalesBancoTO)i.next(); 
				banco_InternacionalTO = new Banco_InternacionalTO();
				
				banco_InternacionalTO.setNombre_banco(bancTO.getNombreBanco());
				banco_InternacionalTO.setCodigo_swift_aba(bancTO.getClaveBanco());
				banco_InternacionalTO.setTipo_clave(bancTO.getTipoClave());
				collectionSwift.add(banco_InternacionalTO);
			}
			swift_aba.setColeccion_bancos(collectionSwift);
			session.addAttribute(SWIFT_ABA_TO, bancoTO);
			response.addAttribute(swift_aba);
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
	private class CiudadPredicate implements Predicate {

		private String ciudad;

		public boolean evaluate(Object obj) {
			InternacionalesCiudadTO ciudadTO = (InternacionalesCiudadTO) obj;
			
			if (ciudadTO.getNombreCiudad().equals(ciudad)) {
				return true;
			} else {
				return false;
			}
		}

		public String getCiudad() {
			return ciudad;
		}

		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}

	}
}
