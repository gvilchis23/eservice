package com.bancoazteca.eservice.command.bibliotecarecibos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.bancoazteca.elite.beans.BibliotecaRecibosRequestTO;
import com.bancoazteca.elite.beans.BibliotecaRecibosTO;
import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.elite.beans.DatosPdfBibliotecaServiciosTO;
import com.bancoazteca.elite.beans.ReciboBibliotecaTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.ejb.usuario.UsuarioException;
import com.bancoazteca.elite.ejb.usuario.UsuarioRule;
import com.bancoazteca.elite.util.ServiceLocatorException;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.beans.Biblioteca_Recibos_SolicitudTO;
import com.bancoazteca.eservice.command.base.beans.Biblioteca_recibosTO;
import com.bancoazteca.eservice.command.base.beans.ReciboTO;
import com.bancoazteca.eservice.command.base.beans.Recibo_ImpuestosTO;
import com.bancoazteca.eservice.command.base.beans.Recibo_ServicioTO;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class BibliotecaRecibosCommand extends CommandBase {
	
	@SuppressWarnings("unchecked")
	public Response solicitud(Session session)throws ServiceLocatorException, SessionExpiredException, UsuarioException, ParseException{
		Response response=new Response();
		
		try{
			
			BibliotecaRecibosRequestTO requestTO=new BibliotecaRecibosRequestTO();
			ClienteTO cliente=(ClienteTO)session.getAttribute(CLIENTE_TO);
			requestTO.setUsuario(cliente.getUserName());
		
			BibliotecaRecibosTO bibliotecaRecibosTO=getDelegate().getServiciosBibliotecaRecibos(requestTO);
			
			Biblioteca_Recibos_SolicitudTO bibliotecaRecibosSolicitudTO=new Biblioteca_Recibos_SolicitudTO();
		
			Map<String,String>mapaServ=bibliotecaRecibosTO.getMapaServicios();
			
			Collection<String>coleccionServicios=new ArrayList<String>();
			
			session.addAttribute("MapaServicios",mapaServ);
			for (String key : mapaServ.keySet()) {
				coleccionServicios.add(key);
			}
			
			bibliotecaRecibosSolicitudTO.setColeccion_servicios(coleccionServicios);
			response.addAttribute(bibliotecaRecibosSolicitudTO);
			
		}catch (EliteDataException e) {
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	

	public Response ejecucion(Session session) throws ServiceLocatorException, SessionExpiredException, UsuarioException, ParseException{
		
		ClienteTO cliente=(ClienteTO)session.getAttribute(CLIENTE_TO);
		BibliotecaRecibosForm form=(BibliotecaRecibosForm)getFormBean();		
		Response response=new Response();
		try{
			ResourceFacadeSL delegate = getDelegate();			
			
			BibliotecaRecibosRequestTO request=new BibliotecaRecibosRequestTO();
			request.setFechaFinal(form.getFecha_final());
			request.setFechaInicio(form.getFecha_inicio());
			Map<String, String> map=(Map<String, String>)session.getAttribute("MapaServicios");
			
			if(!form.getId_servicio().equals("*")){
				request.setIdServicio(map.get(form.getId_servicio().toUpperCase()));
			}else{
				request.setIdServicio("*");
			}
			
			request.setUsuario(cliente.getUserName());
			
			BibliotecaRecibosTO respuesta = delegate.getBibliotecaRecibos(request);
			
			
			Biblioteca_recibosTO recibos=new Biblioteca_recibosTO();
			
			Collection<ReciboTO>coleccionRecibos=new ArrayList<ReciboTO>();
			
			Date date=null;
			String fechaPago, fechaAplicacion;
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMM yyyy");			
			if(respuesta!=null && respuesta.getBiblioVO()!=null){
				int i=0;
				for(ReciboBibliotecaTO reciboBibliotecaTO: respuesta.getBiblioVO()) {
					ReciboTO reciboTO=new ReciboTO();										
					
					date=dateFormat.parse(reciboBibliotecaTO.getFechaPago());								
					fechaPago=simpleDateFormat.format(date);
					
					date=dateFormat.parse(reciboBibliotecaTO.getFechaAplicacion());
					fechaAplicacion = simpleDateFormat.format(date);
					
					reciboTO.setFecha_pago(fechaPago);
					reciboTO.setFecha_aplicacion(fechaAplicacion);
					reciboTO.setNumero_operacion(reciboBibliotecaTO.getNumeroOperacion());
					reciboTO.setReferencia(reciboBibliotecaTO.getReferencia());
					reciboTO.setServicio(reciboBibliotecaTO.getServicio());
					reciboTO.setTotal(reciboBibliotecaTO.getTotal());
					reciboTO.setIndice_recibo(i+"");
					//reciboTO.setIndice_recibo(reciboBibliotecaTO.get);
					coleccionRecibos.add(reciboTO);
					i++;
				}
			}
			
			
			dateFormat=new SimpleDateFormat("dd-MM-yyyy");
			
			date=dateFormat.parse(respuesta.getFechaFinal());
			String fechaFin=simpleDateFormat.format(date);
			
			date=dateFormat.parse(respuesta.getFechaInicio());
			String fechaInicio=simpleDateFormat.format(date);
			
			recibos.setFecha_final(fechaFin);
			recibos.setFecha_inicio(fechaInicio);
			recibos.setColleccion_recibos(coleccionRecibos);
			session.addAttribute("MaxIndiceRecibo",(coleccionRecibos.size()-1)+"");
			response.addAttribute(recibos);
			
		}catch(EliteDataException dataException){
			buildErrorResponse(dataException,response);
		}
		return response;
	}
	
	
	public Response datospdf(Session session) throws ServiceLocatorException, SessionExpiredException, UsuarioException, ParseException{
		Response response=new Response();
		
		BibliotecaRecibosForm form=(BibliotecaRecibosForm)getFormBean();
		
		try{
			
			Integer maxIndex=Integer.parseInt((String)session.getAttribute("MaxIndiceRecibo"));
			
			if(maxIndex==null || maxIndex<Integer.parseInt(form.getIndice_recibo())){
				Map<String,String>errorData=new HashMap<String, String>();
				errorData.put("indice_recibo","El indice del recibo no existe.");
				throw new EliteDataException("Error", errorData, UsuarioRule.LEVEL_ACTION_ERRORS);				
			}
			
			ClienteTO clienteTO=(ClienteTO)session.getAttribute(CLIENTE_TO);
			
			BibliotecaRecibosRequestTO requestTO=new BibliotecaRecibosRequestTO();
			requestTO.setReciboIndex(form.getIndice_recibo());
			requestTO.setUsuario(clienteTO.getUserName());
			
			BibliotecaRecibosTO bibliotecaRecibosTO=getDelegate().getDatosPDFBibliotecaRecibos(requestTO);
			Recibo_ImpuestosTO impuestosTO=new Recibo_ImpuestosTO();
			if(bibliotecaRecibosTO.getCollectionReciboImpuestos()!=null){
				
				ArrayList coll=(ArrayList)bibliotecaRecibosTO.getCollectionReciboImpuestos();
				
				impuestosTO.setTitulo_pago((String)coll.get(0));
				
				impuestosTO.setNombre((String)((ArrayList)coll.get(1)).get(0));
				impuestosTO.setCalle((String)((ArrayList)coll.get(1)).get(1));
				impuestosTO.setColonia((String)((ArrayList)coll.get(1)).get(2));
				impuestosTO.setCP((String)((ArrayList)coll.get(1)).get(3));
				impuestosTO.setMunicipio((String)((ArrayList)coll.get(1)).get(4));
				impuestosTO.setCiudad((String)((ArrayList)coll.get(1)).get(5));
				
				impuestosTO.setConcepto((String)((ArrayList)coll.get(2)).get(0));
				impuestosTO.setEjercicio((String)((ArrayList)coll.get(2)).get(1));
				impuestosTO.setBimestre((String)((ArrayList)coll.get(2)).get(2));
				impuestosTO.setCuenta((String)((ArrayList)coll.get(2)).get(3));
				
				impuestosTO.setBanco((String)((ArrayList)coll.get(3)).get(0));
				impuestosTO.setNumero_autorizacion((String)((ArrayList)coll.get(3)).get(1));
				impuestosTO.setFecha_pago((String)((ArrayList)coll.get(3)).get(2));
				
				impuestosTO.setLinea_captura((String)coll.get(4));
				impuestosTO.setSello_digital((String)coll.get(5));
				impuestosTO.setTotal_pagado((String)coll.get(6));
				
				response.addAttribute(impuestosTO);
				
			}
			
			if(bibliotecaRecibosTO.getReciboServicios()!=null){
					System.out.println("Es uno de servicios");	
					DatosPdfBibliotecaServiciosTO serviciosTO=bibliotecaRecibosTO.getReciboServicios();
					Recibo_ServicioTO servicioTO=new Recibo_ServicioTO();
					
					servicioTO.setActividad(serviciosTO.getActividad());
					servicioTO.setComision(serviciosTO.getComision());
					servicioTO.setCuenta_cargo(serviciosTO.getCuentaCargo());
					servicioTO.setDigito_verificador(serviciosTO.getDigitoVerificador());
					servicioTO.setFecha(serviciosTO.getFecha());
					servicioTO.setFecha_aplicacion(serviciosTO.getFechaAplicacion());
					servicioTO.setFecha_aplicacion_alnova(serviciosTO.getFechaAplicacionAlnova());
					servicioTO.setFecha_pago(serviciosTO.getFechaPago());
					servicioTO.setFecha_vencimiento(serviciosTO.getFechaVencimiento());
					servicioTO.setFolio(serviciosTO.getFolio());
					servicioTO.setImporte(serviciosTO.getImporte());
					servicioTO.setIva(serviciosTO.getIva());
					//servicioTO.setNip(serviciosTO.getNip());
					servicioTO.setNumero_operacion(serviciosTO.getNumeroOperacion());
					servicioTO.setOperacion(serviciosTO.getOperacionv());
					servicioTO.setOperacion_iusacell(serviciosTO.getOperacionIusacell());
					servicioTO.setSello_digital(serviciosTO.getSelloDigital());
					servicioTO.setTipo_pago(serviciosTO.getTipoPago());
					HashMap<String,String> mapaServ=(HashMap<String, String>)session.getAttribute("MapaServicios");
					
					for (Entry<String,String> entry : mapaServ.entrySet()) {
						if(entry.getValue().equals(serviciosTO.getTipoServicio())){
							servicioTO.setTipo_servicio(entry.getKey());
							break;
						}
					}
					
					servicioTO.setTipo_tarjeta(serviciosTO.getTipoTarjeta());
					servicioTO.setTotal(serviciosTO.getTotal());
					response.addAttribute(servicioTO);
					
			}
			
		}catch(EliteDataException e)
		{
			buildErrorResponse(e, response);
		}
		
		return response;
	}
	
	
	public static void main(String[] args) {
		System.out.printf("basura %f %b",Math.PI,Math.E);
	}
	
}