package com.bancoazteca.eservice.command.mediospago.cambiosucursal;

import java.util.ArrayList;
import java.util.Collection;

import com.bancoazteca.elite.beans.CambioSucursalTO;
import com.bancoazteca.elite.beans.CancelacionElementTO;
import com.bancoazteca.elite.beans.ChangeBranchRequestTO;
import com.bancoazteca.elite.beans.ChangeBranchResponseTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.CodeTO;
import com.bancoazteca.elite.beans.ResultadoBusquedaSucursalTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Cambio_sucursal_tarjetaTO;
import com.bancoazteca.eservice.command.base.beans.Datos_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.Delegaciones_MuniciposTO;
import com.bancoazteca.eservice.command.base.beans.Detalle_TarjetaTO;
import com.bancoazteca.eservice.command.base.beans.EstadosTO;
import com.bancoazteca.eservice.command.base.beans.HuellaTO;
import com.bancoazteca.eservice.command.base.beans.Medios_pagoTO;
import com.bancoazteca.eservice.command.base.beans.SucursalTO;
import com.bancoazteca.eservice.command.base.beans.SucursalesTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.mediospago.Medios_PagoCommand;
import com.bancoazteca.eservice.command.response.Response;

public class CambioSucursalCommand extends CommandBase{

	public Response solicitud(Session session)throws Throwable{
		Response response=new Response();
		ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);
		
		ChangeBranchRequestTO changeBranchRequestTO = new ChangeBranchRequestTO();
		CambioSucursalTO changeBranchResponseTO = null;
		Collection<CancelacionElementTO> tarjetas = null;
		
		CambioSucursalForm cambioSucursalForm=(CambioSucursalForm)getFormBean();
		
		
		Medios_pagoTO mediosPagoTO=(Medios_pagoTO)session.getAttribute(Medios_PagoCommand.MEDIOS_PAGO_TARJETAS);
		
		if(mediosPagoTO==null){
			Medios_PagoCommand command=new Medios_PagoCommand();
			command.solicitud(session);
			mediosPagoTO=(Medios_pagoTO)session.getAttribute(Medios_PagoCommand.MEDIOS_PAGO_TARJETAS);
		}

		int i=0;
		changeBranchRequestTO.setIndice(-1+"");
		for(Detalle_TarjetaTO tarjetaTO :mediosPagoTO.getColeccion_medios_pago()){
			String tarjeta=tarjetaTO.getNumero_tarjeta();
			String tarjetaForm=cambioSucursalForm.getNumero_tarjeta();
			if(tarjeta.equals(tarjetaForm)){
				session.addAttribute("tarjetaModificada",tarjetaTO);
				changeBranchRequestTO.setIndice(i+"");
				break;
			}
			i++;
		}
		
		changeBranchRequestTO.setOpcionCambioSucursal("inicia");
		changeBranchRequestTO.setUser(clienteTO.getUserName());
		try {
			changeBranchResponseTO = getDelegate().getCambioSucursalInicio(changeBranchRequestTO);
			
			response.addAttribute(changeBranchResponseTO.getNoTarjeta());
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		return response;
	}
	
	public Response datos_tarjeta(Session session)throws Throwable{
		Response response=new Response();
		
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		try{
			CambioSucursalTO cambioSucursalTO=getDelegate().getDatosSucursalTarjeta(clienteTO.getUserName());
			
			Datos_TarjetaTO datosTarjetaTO=new Datos_TarjetaTO();
			
			datosTarjetaTO.setEstado_tarjeta(cambioSucursalTO.getEstadoTarjeta());
			datosTarjetaTO.setNumero_cuenta(cambioSucursalTO.getCuentaSucursal());
			datosTarjetaTO.setNumero_tarjeta(cambioSucursalTO.getTarjetaSucursal());
			
		    datosTarjetaTO.setCentro_actual_nombre(cambioSucursalTO.getCentroActual());
		    datosTarjetaTO.setCentro_actual_direccion(cambioSucursalTO.getCentroActualDir());
		    datosTarjetaTO.setSucursal_actual(cambioSucursalTO.getCentroActualName());
			
			datosTarjetaTO.setTipo_tarjeta(cambioSucursalTO.getTipoCuentaSucursal());
			datosTarjetaTO.setTitular(clienteTO.getNombreCompleto());
			response.addAttribute(datosTarjetaTO);
			
		}catch(EliteDataException exception){
			buildErrorResponse(exception, response);
		}
		return response;
	}
	
	public Response datos_busqueda_sucursales(Session session)throws Throwable{
		Response response=new Response();
		
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		try{
			CambioSucursalTO cambioSucursalTO=getDelegate().getDatosBusquedaSucursalesTarjeta(clienteTO.getUserName());
			
			EstadosTO estadosTO=new EstadosTO();
			Collection<String>collection=new ArrayList<String>();
			for (String edo:cambioSucursalTO.getEstados()) {
				collection.add(edo);
			}
			estadosTO.setColeccion_estados(collection);
			response.addAttribute(estadosTO);
			session.addAttribute("estadosCambioScucrsal",estadosTO);
		}catch(EliteDataException exception){
			buildErrorResponse(exception, response);
		}
		return response;
	}
	
	public Response busqueda_delegacion_municipio(Session session)throws Throwable{
		Response response=new Response();
		
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		try{
			ChangeBranchRequestTO changeBranchRequestTO=new ChangeBranchRequestTO();
			
			
			CambioSucursalForm form=(CambioSucursalForm)getFormBean();
			
			changeBranchRequestTO.setUser(clienteTO.getUserName());
			changeBranchRequestTO.setTipoBusqueda(1);
			
			int i=0;
			EstadosTO estadosTO=(EstadosTO)session.getAttribute("estadosCambioScucrsal");
			changeBranchRequestTO.setEstado(-1);
			if(estadosTO!=null)
				for(String edo:estadosTO.getColeccion_estados()){					
					if(form.getEstado().equalsIgnoreCase(edo)){
						changeBranchRequestTO.setEstado(i);
						break;
					}
					i++;
				}
			
			
			changeBranchRequestTO.setMunicipio(-1);
			
			changeBranchRequestTO.setEsBusquedaMunicipio(true);
			ChangeBranchResponseTO cambioSucursalTO = getDelegate().buscarCentrosCambioSucursal(changeBranchRequestTO);
			
			Collection<CodeTO> codeTOs=cambioSucursalTO.getMunicipios();
			Collection<String> municip_deleg=new ArrayList<String>();
			
			for (CodeTO codeTO : codeTOs) {
				municip_deleg.add(codeTO.getDescripcion());
			}
			session.addAttribute("municipioDelegacion",codeTOs);
			
			Delegaciones_MuniciposTO delegacionesMuniciposTO=new Delegaciones_MuniciposTO();
			
			delegacionesMuniciposTO.setColeccion_municipios_delegaciones(municip_deleg);
			
			response.addAttribute(delegacionesMuniciposTO);
			
		}catch(EliteDataException exception){
			buildErrorResponse(exception, response);
		}
		return response;
	}
	
	public Response busqueda_sucursales(Session session)throws Throwable{
		Response response=new Response();
		
		ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
		
		try{
			ChangeBranchRequestTO changeBranchRequestTO=new ChangeBranchRequestTO();
			
			
			CambioSucursalForm form=(CambioSucursalForm)getFormBean();
			
			changeBranchRequestTO.setUser(clienteTO.getUserName());
			changeBranchRequestTO.setCodigoPostal(form.getCodigo_postal());
			
			changeBranchRequestTO.setMunicipio(-1);
			changeBranchRequestTO.setEstado(-1);
			
			changeBranchRequestTO.setTipoBusqueda(form.getTipo_busqueda().equalsIgnoreCase("CP")?0:1);
			
			if(changeBranchRequestTO.getTipoBusqueda()==1){
				Collection<CodeTO>municipiosTOs=(Collection<CodeTO>)session.getAttribute("municipioDelegacion");
				EstadosTO estadosTO=(EstadosTO)session.getAttribute("estadosCambioScucrsal");
				
				int indexMunicipio=0;
				
				if(municipiosTOs!=null)
					for(CodeTO municipio:municipiosTOs){
						if(form.getMunicipio().equalsIgnoreCase(municipio.getDescripcion())){
							changeBranchRequestTO.setMunicipio(indexMunicipio);
							break;
						}
						indexMunicipio++;
					}
				
				int indexEstado=0;
				if(estadosTO!=null){
					for(String edo:estadosTO.getColeccion_estados()){						
						if(edo.equalsIgnoreCase(form.getEstado())){
							changeBranchRequestTO.setEstado(indexEstado);
							break;
						}
						indexEstado++;
					}
				}
			}
			
			ChangeBranchResponseTO cambioSucursalTO = getDelegate().buscarCentrosCambioSucursal(changeBranchRequestTO);
			
			Collection<ResultadoBusquedaSucursalTO>camBusquedaSucursalTOs=cambioSucursalTO.getSucursales();
			
			SucursalesTO sucursalesTO=new SucursalesTO();
			Collection<SucursalTO>coll=new ArrayList<SucursalTO>();
			SucursalTO sucursalTO=null;
			for (ResultadoBusquedaSucursalTO resultadoBusquedaSucursalTO : camBusquedaSucursalTOs) {
				sucursalTO=new SucursalTO();
				sucursalTO.setDescripcion(resultadoBusquedaSucursalTO.getNombre());
				sucursalTO.setDireccion(resultadoBusquedaSucursalTO.getDireccion());
				sucursalTO.setNum_sucursal(resultadoBusquedaSucursalTO.getNoCentro());
				coll.add(sucursalTO);
			}
			session.addAttribute("sucursalesCambioSucursal",coll);
			sucursalesTO.setColeccion_sucursales(coll);
			response.addAttribute(sucursalesTO);
			
			
			HuellaTO huella = new HuellaTO();
	   		huella.setLlave_publica(cambioSucursalTO.getLlavePublica());
	   		huella.setLongitud_huella(cambioSucursalTO.getLongitudHuella());
			
	   		response.addAttribute(huella);
			
			
		}catch(EliteDataException exception){
			buildErrorResponse(exception, response);
		}
		return response;
	}
	
	
	public Response ejecucion(Session session)throws Throwable{
		Response response=new Response();
		
		ChangeBranchRequestTO branchRequestTO=new ChangeBranchRequestTO();
		CambioSucursalForm form=(CambioSucursalForm)getFormBean();
		
		try{
		
			Collection<SucursalTO>sucursalTOs=(ArrayList<SucursalTO>)session.getAttribute("sucursalesCambioSucursal");	
			
			branchRequestTO.setIndiceSucursal("-1");
			
			if(form.getOpcion_seguridad().equalsIgnoreCase(TAG_TOKEN)){
				branchRequestTO.setOpcion_seguridad(OPCION_TOKEN);
				branchRequestTO.setTokenCode(form.getClave_seguridad().toString());
			}else if (form.getOpcion_seguridad().equalsIgnoreCase(TAG_HUELLA)) {
				branchRequestTO.setOpcion_seguridad(OPCION_HUELLA);
				branchRequestTO.setClave_seguridad(form.getHuella_seguridad().toString());
			}
			
			int i=0;
			for (SucursalTO sucursalTO : sucursalTOs) {
				if(form.getNumero_sucursal_nueva().equals(sucursalTO.getNum_sucursal())){
					branchRequestTO.setIndiceSucursal(i+"");
					break;
				}
			}
			
			ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
			branchRequestTO.setUser(clienteTO.getUserName());
			
			CambioSucursalTO cambioSucursalTO=getDelegate().confirmaCambioSucursalTarjeta(branchRequestTO);
			
			Detalle_TarjetaTO detalleTarjetaTO=(Detalle_TarjetaTO)session.getAttribute("tarjetaModificada");
			
			Cambio_sucursal_tarjetaTO cambioSucursalTarjetaTO=new Cambio_sucursal_tarjetaTO();
			
			cambioSucursalTarjetaTO.setTipo_tarjeta(detalleTarjetaTO.getTipo_cuenta());
			cambioSucursalTarjetaTO.setCentro_actual_direccion(cambioSucursalTO.getCentroActualDir());
			cambioSucursalTarjetaTO.setCentro_actual_nombre(cambioSucursalTO.getCentroActualName());
			cambioSucursalTarjetaTO.setCentro_actual(cambioSucursalTO.getCentroActual());
			
			cambioSucursalTarjetaTO.setCentro_nuevo(cambioSucursalTO.getCentroNuevo());
			cambioSucursalTarjetaTO.setCentro_nuevo_direccion(cambioSucursalTO.getCentroNuevoDir());
			cambioSucursalTarjetaTO.setCentro_nuevo_nombre(cambioSucursalTO.getCentroNuevoName());

			cambioSucursalTarjetaTO.setNumero_cuenta(detalleTarjetaTO.getNumero_cuenta());
			cambioSucursalTarjetaTO.setNumero_tarjeta(detalleTarjetaTO.getNumero_tarjeta());
			
			response.addAttribute(cambioSucursalTarjetaTO);
			
		}catch(EliteDataException dataException){
			buildErrorResponse(dataException, response);
		}
		return response;
	}
	
	
	
}