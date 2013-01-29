package com.bancoazteca.eservice.command.enviodineroexpress;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bancoazteca.elite.beans.AgenteDEXTO;
import com.bancoazteca.elite.beans.BeneficiarioDineroExpresTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CuentaTO;
import com.bancoazteca.elite.beans.CuentaVisibleDineroExpressTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressRequestTO;
import com.bancoazteca.elite.beans.EnvioDineroExpressResponseTO;
import com.bancoazteca.elite.beans.LocalidadDEXTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cuenta_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Cuentas_CargoTO;
import com.bancoazteca.eservice.command.base.beans.Envio_Dinero_Express_EjecucionTO;
import com.bancoazteca.eservice.command.base.beans.Envio_Dinero_Express_validacionTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.catalogos.AgentesDineroExpressCommand;
import com.bancoazteca.eservice.command.catalogos.CiudadesDineroExpressCommand;
import com.bancoazteca.eservice.command.catalogos.EstadosDineroExpresCommand;
import com.bancoazteca.eservice.command.catalogos.PaisesDineroExpressCommand;
import com.bancoazteca.eservice.command.catalogos.SucursalesDineroExpressCommand;
import com.bancoazteca.eservice.command.frecuentes.enviodineroexpress.ConsultaFrecuenteDineroExpressComand;
import com.bancoazteca.eservice.command.frecuentes.enviodineroexpress.ConsultaFrecuenteDineroExpressForm;
import com.bancoazteca.eservice.command.response.Response;

public class EnvioDineroExpressCommand extends CommandBase{
		
	   public static final String ERROR_MAPA = "-1";
	  
       public Response solicitud(Session session) throws Exception{
		
		Response response = new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		EnvioDineroExpressRequestTO dineroExpressRequestTO = new EnvioDineroExpressRequestTO();
		EnvioDineroExpressResponseTO dineroExpressResponseTO = new EnvioDineroExpressResponseTO();
		try {
			
			EnvioDineroExpressForm form=(EnvioDineroExpressForm)getFormBean();
			
			String flagAceptaContrato=form.getAceptar_contrato();
			dineroExpressRequestTO.setAceptarContrato(flagAceptaContrato);
			dineroExpressRequestTO.setUser(clienteTO.getUserName());
			ResourceFacadeSL facadeSL = getDelegate();
			dineroExpressResponseTO = facadeSL.envioDineroExpressInicio(dineroExpressRequestTO);
			
			Cuentas_CargoTO cuentas = new Cuentas_CargoTO();
			Collection <Cuenta_CargoTO> cuentaCollectionTO = new ArrayList <Cuenta_CargoTO>();
			for(CuentaVisibleDineroExpressTO cuentaVisibleTO: dineroExpressResponseTO.getCuentaVisibleDineroExpress()){
				Cuenta_CargoTO cuentaCargoTO = new Cuenta_CargoTO();		
				String cuenta=cuentaVisibleTO.getLabel();
				
				if (cuenta.contains(" ")){
					String[] ctaDEX=  cuenta.split(" ");
					cuenta=ctaDEX[0]+ctaDEX[1]+ctaDEX[2]+ctaDEX[3];
				}
				CuentaTO cuentaTO = (CuentaTO)super.getAccountsPrdicate(clienteTO.getCuentas(),cuenta);
				if (cuentaTO!=null){
					cuentaCargoTO.setNumero_cuenta(Formatter.removeSpaces(cuentaTO.getCuentaFormateada()));			
					cuentaCargoTO.setSaldo_disponible(cuentaTO.getDisponible().toString());
					cuentaCargoTO.setProducto(cuentaTO.getDescripcion());
					cuentaCollectionTO.add(cuentaCargoTO);
				}
			}
			session.addAttribute(PaisesDineroExpressCommand.PAISES_DEX,dineroExpressResponseTO.getPaisesDEX());
			cuentas.setColeccion_cuentas(cuentaCollectionTO);
			response.addAttribute(cuentas);

		}catch(EliteDataException e){			
			if(e.getMessage().equalsIgnoreCase("AceptarContratoDEX")){
				response.addAttribute("Debe aceptar el contrato de dinero express");
			}else
				buildErrorResponse(e, response);
	    }
		return response;
	}
       
       public Response validacion(Session session) throws Exception{
    	   Response response = new Response();
   		   ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
    	   EnvioDineroExpressForm envioDineroExpressForm= (EnvioDineroExpressForm)getFormBean();
    	   Map<String, String> errors= new HashMap<String, String>();
    	   ResourceFacadeSL facadeSL = getDelegate();
    	   EnvioDineroExpressRequestTO dineroExpressRequestTO = new EnvioDineroExpressRequestTO();
   		   EnvioDineroExpressResponseTO dineroExpressResponseTO = new EnvioDineroExpressResponseTO(); 
    	   
   		   try{
   			   
   			   
				Collection<BeneficiarioDineroExpresTO> beneficiarioDineroExpresTOs=(Collection<BeneficiarioDineroExpresTO>)session.getAttribute(ConsultaFrecuenteDineroExpressComand.BENEFICIARIOS_DEX);
				
				if(beneficiarioDineroExpresTOs==null){
					
					ConsultaFrecuenteDineroExpressComand consultaFrecuenteDineroExpressComand=new ConsultaFrecuenteDineroExpressComand();										
					ConsultaFrecuenteDineroExpressForm consultaFrecuenteDineroExpressForm=new ConsultaFrecuenteDineroExpressForm();
					
					consultaFrecuenteDineroExpressComand.setFormBean(consultaFrecuenteDineroExpressForm);
					consultaFrecuenteDineroExpressComand.ejecucion(session);
					beneficiarioDineroExpresTOs=(Collection<BeneficiarioDineroExpresTO>)session.getAttribute(ConsultaFrecuenteDineroExpressComand.BENEFICIARIOS_DEX);
				}
			   	
   			   for (BeneficiarioDineroExpresTO beneficiarioDineroExpresTO : beneficiarioDineroExpresTOs){
   				   if(beneficiarioDineroExpresTO.getApellidoMaterno().equalsIgnoreCase(envioDineroExpressForm.getApellido_materno())
   				      && beneficiarioDineroExpresTO.getApellidoPaterno().equalsIgnoreCase(envioDineroExpressForm.getApellido_paterno())
   				      && beneficiarioDineroExpresTO.getNombre().equalsIgnoreCase(envioDineroExpressForm.getNombre_beneficiario())){
   					   					dineroExpressRequestTO.setIdBeneficiario(beneficiarioDineroExpresTO.getClienteId());
   					   					break;
   				      }
   			   }
   			   
   			   
				dineroExpressRequestTO.setApellido_materno(envioDineroExpressForm.getApellido_materno());
		   		dineroExpressRequestTO.setApellido_paterno(envioDineroExpressForm.getApellido_paterno());
		   		dineroExpressRequestTO.setNombre_beneficiario(envioDineroExpressForm.getNombre_beneficiario());
		
				Collection<CuentaTO> cargoTOs=clienteTO.getCuentas();
				CuentaTO cuentaTO=getAccountsPrdicate(cargoTOs, envioDineroExpressForm.getCuenta_cargo());
				
				
		   		dineroExpressRequestTO.setCuenta_cargo(cuentaTO.getNumero());
		   	
		   		dineroExpressRequestTO.setEstadoNombre(envioDineroExpressForm.getEstado());
		   		
		   		List<LocalidadDEXTO> listaEdos=(List<LocalidadDEXTO>)session.getAttribute(EstadosDineroExpresCommand.MAPA_ESTADOS);		  
		   		if(listaEdos!=null)
			   		if(listaEdos!=null){		   	
				   		for (LocalidadDEXTO localidadDEXTO : listaEdos) {
							if(localidadDEXTO.getValue().equalsIgnoreCase(envioDineroExpressForm.getEstado())){
									dineroExpressRequestTO.setEstado(localidadDEXTO.getId()+"");
									break;
							}
						}
			   		}else{
			   			dineroExpressRequestTO.setEstado("");
			   		}
		   		
		   		List<LocalidadDEXTO> listPaises=(List<LocalidadDEXTO>)session.getAttribute(PaisesDineroExpressCommand.PAISES_DEX);

		   		for(LocalidadDEXTO dexto:listPaises)
		   			if(dexto.getValue().trim().equals(envioDineroExpressForm.getPais())){
		   				dineroExpressRequestTO.setPais(dexto.getId()+"");
		   				break;
		   			}
		   		
		   		
		   		List<AgenteDEXTO>listaAgentes=(List<AgenteDEXTO>)session.getAttribute(AgentesDineroExpressCommand.AGENTES_DEX);
		   		if(listaAgentes!=null)
			   		for (AgenteDEXTO agenteDEXTO : listaAgentes) {
						if(agenteDEXTO.getValue().equalsIgnoreCase(envioDineroExpressForm.getAgente())){
							dineroExpressRequestTO.setAgente(agenteDEXTO.getId()+"");
							break;
						}
					}
		   		
		   		List<LocalidadDEXTO>listaSucursales=(List<LocalidadDEXTO>)session.getAttribute(SucursalesDineroExpressCommand.SUCURSALES_DEX);
		   		if(listaSucursales!=null)
			   		for (LocalidadDEXTO localidadDEXTO : listaSucursales) {									
						if(localidadDEXTO.getValue().equalsIgnoreCase(envioDineroExpressForm.getSucursal())){
							dineroExpressRequestTO.setSucursal(localidadDEXTO.getId()+"");
							break;
						}
					}
		   		
		   		List<LocalidadDEXTO>listaCiudades=(List<LocalidadDEXTO>)session.getAttribute(CiudadesDineroExpressCommand.LISTA_CIUDADES_DEX);
		   		if(listaCiudades!=null)
			   		for (LocalidadDEXTO localidadDEXTO : listaCiudades){
						if(localidadDEXTO.getValue().equalsIgnoreCase(envioDineroExpressForm.getCiudad())){
							dineroExpressRequestTO.setCiudad(localidadDEXTO.getId()+"");
							break;
						}
					}
		   		
		   		dineroExpressRequestTO.setPaisNombre(envioDineroExpressForm.getPais());
		   		dineroExpressRequestTO.setMonto_enviar(envioDineroExpressForm.getMonto_enviar());
		   		dineroExpressRequestTO.setUser(clienteTO.getUserName());
		   		
		   		
		   		dineroExpressResponseTO = facadeSL.envioDineroExpressValidacion(dineroExpressRequestTO);
		  		
		  	   Envio_Dinero_Express_validacionTO envio=new Envio_Dinero_Express_validacionTO();
		  	   DecimalFormat decimalFormat=new DecimalFormat("###,###,#0.00");
		  	   
		  	   envio.setApellido_mat_beneficiario(dineroExpressResponseTO.getaMaterno());
		  	   envio.setApellido_pat_beneficiario(dineroExpressResponseTO.getaPaterno());
		  	   envio.setComision(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getComision())));
			   envio.setDescuento(dineroExpressResponseTO.getDescuento());
		   	   envio.setEstado_nombre(dineroExpressResponseTO.getEstadoNombre());
		   	   envio.setImpuestos(dineroExpressResponseTO.getImpuestos());
		   	   envio.setMonto_enviar(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getMonto())));
		   	   envio.setMonto_total(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getTotalPagar())));
		   	   envio.setNombre_beneficiario(dineroExpressResponseTO.getNombre());
     		   envio.setPais_nombre(dineroExpressResponseTO.getPaisNombre());
     		   envio.setSubtotal(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getSubtotal())));
     		   envio.setRemitente(clienteTO.getNombreCompleto());
     		   envio.setDescuento("0.0");
     		   envio.setSubtotal("0.0");
     		   
     		   envio.setAgente_nombre(dineroExpressResponseTO.getAgenteName());
     		   envio.setSucursal_nombre(dineroExpressResponseTO.getSucursalName());
     		   envio.setCiudad_nombre(dineroExpressResponseTO.getCiudadNombre());
     		   
     		   
     		   envio.setNombre_corto_divisa(dineroExpressResponseTO.getNomCortoDivisa());
     		   envio.setNombre_divisa(dineroExpressResponseTO.getDivisa());
     		   envio.setTipo_cambio(dineroExpressResponseTO.getTipoCambio());
     		   envio.setMonto_recibir(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getMontoRecibir())));
     		   
		  	   session.addAttribute("Envio_Dinero_ExpressTO", envio);
	   		   response.addAttribute(envio);
	   		   HuellaTO huella = new HuellaTO();
	   		   huella.setLlave_publica(dineroExpressResponseTO.getLlavePublica());
	   		   huella.setLongitud_huella(dineroExpressResponseTO.getLongitudHuella());
	   		    response.addAttribute(huella);
	
	   	 }catch(EliteDataException e){
	    	 buildErrorResponse(e, response);
	     }
    	   return response;
       }
       
       public Response ejecucion(Session session) throws Exception{
    	   
    	   Response response = new Response();
   		   ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
    	   EnvioDineroExpressForm envioDineroExpressForm= new EnvioDineroExpressForm();
    	   envioDineroExpressForm=(EnvioDineroExpressForm)getFormBean();
    	   ResourceFacadeSL facadeSL = getDelegate();
    	   EnvioDineroExpressRequestTO dineroExpressRequestTO = new EnvioDineroExpressRequestTO();
   		   EnvioDineroExpressResponseTO dineroExpressResponseTO = new EnvioDineroExpressResponseTO();
   		   try{
   			   
		   		if(envioDineroExpressForm.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
		   			dineroExpressRequestTO.setOpcion_seguridad(OPCION_TOKEN);
		   			dineroExpressRequestTO.setTokenCode(envioDineroExpressForm.getClave_seguridad().toString());
				}else if (envioDineroExpressForm.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
					dineroExpressRequestTO.setOpcion_seguridad(OPCION_HUELLA);
					dineroExpressRequestTO.setClave_seguridad(envioDineroExpressForm.getHuella_seguridad().toString());
				}
		   		dineroExpressRequestTO.setUser(clienteTO.getUserName());
		   		dineroExpressResponseTO = facadeSL.envioDineroExpressEjecucion(dineroExpressRequestTO);
	    	    
	    	    Envio_Dinero_Express_EjecucionTO envio=new Envio_Dinero_Express_EjecucionTO();
	    	    DecimalFormat decimalFormat=new DecimalFormat("###,###,##0.00");

	    	    envio.setApellido_materno(dineroExpressResponseTO.getaMaterno());
	    	    envio.setApellido_paterno(dineroExpressResponseTO.getaPaterno());
	    	    envio.setComision(dineroExpressResponseTO.getComision());
	    	    envio.setRemitente(clienteTO.getNombreCompleto());
	    	    envio.setDescuento(dineroExpressResponseTO.getDescuento());
	    	    envio.setEstado(dineroExpressResponseTO.getEstadoNombre());
	    	    envio.setFolio_envio(dineroExpressResponseTO.getFolioTransferencia());
	    	    envio.setImpuestos(dineroExpressResponseTO.getImpuestos());
	    	    envio.setMonto_enviar(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getMonto())));
	    	    envio.setNombre_beneficiario(dineroExpressResponseTO.getNombre());
	    	    envio.setPais(dineroExpressResponseTO.getPaisNombre());
	    	    envio.setSubtotal(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getSubtotal())));
	    	    envio.setTotal_pagar(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getTotalPagar())));
	    	    
	    	    envio.setNombre_corto_divisa(dineroExpressResponseTO.getNomCortoDivisa());
	     		envio.setNombre_divisa(dineroExpressResponseTO.getDivisa());
	            envio.setTipo_cambio(dineroExpressResponseTO.getTipoCambio());
	       	    envio.setMonto_recibir(decimalFormat.format(Double.parseDouble(dineroExpressResponseTO.getMontoRecibir())));
	     		
	       	    envio.setCiudad(dineroExpressResponseTO.getCiudadNombre());
	       	    envio.setAgente_nombre(dineroExpressResponseTO.getAgenteName());
	       	    envio.setSucursal_nombre(dineroExpressResponseTO.getSucursalName());
	    	    
	    	    envio.setFolio_envio(dineroExpressResponseTO.getFolioTransferencia());
	   		    response.addAttribute(envio);
	   		    
   		   }catch(EliteDataException e){
   			 buildErrorResponse(e, response);
   			   
   		   }
    	   
    	   return response;
       }

}
