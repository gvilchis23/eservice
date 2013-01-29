package com.bancoazteca.eservice.command.frecuentes.enviodineroexpress;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

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

public class BusquedaFrecuenteDineroExpressComand extends CommandBase {
	
	public Response ejecucion(Session session) throws Exception{
		 
		Response response = new Response();
		BeneficiarioDineroExpressRequestTO requestTO = new BeneficiarioDineroExpressRequestTO();
		BeneficiarioDineroExpressResponseTO responseTO = null;
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		BusquedaFrecuenteDineroExpressForm busquedaForm = (BusquedaFrecuenteDineroExpressForm) getFormBean();
		try{
			ResourceFacadeSL resourceFacadeSL = getDelegate();
	
			requestTO.setApellidoMaterno( busquedaForm.getApellido_materno() );
			requestTO.setApellidoPaterno( busquedaForm.getApellido_paterno() );
			requestTO.setFechaNacimiento( busquedaForm.getFecha_nacimiento() );
			requestTO.setNombreBeneficiario( busquedaForm.getNombre_beneficiario() );
			requestTO.setUser( clienteTO.getUserName() );
			requestTO.setClienteId( busquedaForm.getCliente_id() );
			requestTO.setNuevoBeneficiario( "true" );
			requestTO.setSubmitValue( "Buscar" );
			requestTO.setMethod( "buscarClientes" );
			
			responseTO = resourceFacadeSL.setEnvioDineroExpressConsultarFrecuentesEjecutar( requestTO );
		     
		     Beneficiarios_Dinero_ExpressTO beneficiarios = new Beneficiarios_Dinero_ExpressTO();
		     Collection<Beneficiario_Dinero_ExpressTO> coleccion_beneficiarios = new ArrayList<Beneficiario_Dinero_ExpressTO>();
		     Beneficiario_Dinero_ExpressTO beneficiario;
		     Properties properties;
		     for( Object obj : responseTO.getClientes() ){
		    	 properties = ( Properties ) obj;
		    	 beneficiario = new Beneficiario_Dinero_ExpressTO();
		    	 beneficiario.setApellido_materno( properties.getProperty( "apellidoMaterno" ) );
		    	 beneficiario.setApellido_paterno( properties.getProperty( "apellidoPaterno" ) );
		    	 beneficiario.setFecha_nacimiento( properties.getProperty( "fechaNacimiento" ) );
		    	 beneficiario.setId_beneficiario( properties.getProperty( "clienteId" ) );
		    	 beneficiario.setNombre_beneficiario( properties.getProperty( "nombre" ) );
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
