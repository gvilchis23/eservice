package com.bancoazteca.eservice.command.catalogos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.InternacionalesBancosRequestTO;
import com.bancoazteca.elite.beans.InternacionalesBancosResponseTO;
import com.bancoazteca.elite.beans.InternacionalesPaisTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class PaisesCommand extends CommandBase {
	public Response ejecucion(Session session) throws Exception {
		Response response = new Response();
		try {
			InternacionalesBancosRequestTO internacionalesBancosRequestTO = new InternacionalesBancosRequestTO();
			InternacionalesBancosResponseTO internacionalesBancosResponseTO = new InternacionalesBancosResponseTO();

			Collection<InternacionalesPaisTO> paisesTO = null;

			ResourceFacadeSL bean = getDelegate();

			internacionalesBancosRequestTO.setUser(((ClienteTO) session
					.getAttribute(CLIENTE_TO)).getUserName());
			internacionalesBancosResponseTO = bean
					.getInternacionalesPaises(internacionalesBancosRequestTO);

			com.bancoazteca.eservice.command.base.beans.PaisesTO pais = new com.bancoazteca.eservice.command.base.beans.PaisesTO();
			Collection<String> collectionPais = new ArrayList<String>();
			String nombrePais = new String();

			paisesTO = internacionalesBancosResponseTO.getPaises();

			Iterator<InternacionalesPaisTO> i = (Iterator<InternacionalesPaisTO>) paisesTO
					.iterator();

			while (i.hasNext()) {
				InternacionalesPaisTO paissTO = (InternacionalesPaisTO) i
						.next();
				nombrePais = paissTO.getNombrePais();
				collectionPais.add(nombrePais);
			}
			pais.setColeccion_paises(collectionPais);
			session.addAttribute(PAISES_TO, paisesTO);

			response.addAttribute(pais);

		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}

}
