package com.bancoazteca.eservice.command.frecuentes.enviodineroexpress;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.elite.beans.BeneficiarioDineroExpresTO;
import com.bancoazteca.elite.beans.BeneficiarioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.BeneficiarioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Beneficiario_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.beans.Beneficiarios_Dinero_ExpressTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class ConsultaFrecuenteDineroExpressComand extends CommandBase{
	
	public static final String BENEFICIARIOS_DEX="ConsultaFrecuenteDineroExpressComand.BeneficiariosDEX";
	
	public Response ejecucion(Session session) throws Exception{
		 
		Response response = new Response();
		BeneficiarioDineroExpressRequestTO requestTO = new BeneficiarioDineroExpressRequestTO();
		BeneficiarioDineroExpressResponseTO responseTO = null;
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		ConsultaFrecuenteDineroExpressForm busquedaForm = ( ConsultaFrecuenteDineroExpressForm ) getFormBean();
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
	
			requestTO.setUser( clienteTO.getUserName() );
			requestTO.setIndice( busquedaForm.getIndice_alfabetico() );
			
			responseTO = resourceFacadeSL.setEnvioDineroExpressConsultarFrecuentesEjecutar( requestTO );		     
			Beneficiarios_Dinero_ExpressTO beneficiarios = new Beneficiarios_Dinero_ExpressTO();
		    Collection<Beneficiario_Dinero_ExpressTO> coleccion_beneficiarios = new ArrayList<Beneficiario_Dinero_ExpressTO>();
		    Beneficiario_Dinero_ExpressTO beneficiario;
		    BeneficiarioDineroExpresTO properties;
		    session.addAttribute(BENEFICIARIOS_DEX,responseTO.getBeneficiarios());
		    for( BeneficiarioDineroExpresTO obj :(Collection<BeneficiarioDineroExpresTO>) responseTO.getBeneficiarios() ){
		    	properties = ( BeneficiarioDineroExpresTO ) obj;
		    	beneficiario = new Beneficiario_Dinero_ExpressTO();
		    	beneficiario.setApellido_materno( properties.getApellidoMaterno());
		    	beneficiario.setApellido_paterno( properties.getApellidoPaterno());
		    	beneficiario.setFecha_nacimiento( properties.getFechaNacimiento());
		    	beneficiario.setId_beneficiario( properties.getClienteId() );
		    	beneficiario.setNombre_beneficiario( properties.getNombre() );
		    	beneficiario.setNombre_corto(properties.getNombreCorto());
		    	coleccion_beneficiarios.add( beneficiario );
		    }
		    beneficiarios.setColeccion_beneficiarios(coleccion_beneficiarios);
	        response.addAttribute(beneficiarios);
		} catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
	     return response;
	 }
}