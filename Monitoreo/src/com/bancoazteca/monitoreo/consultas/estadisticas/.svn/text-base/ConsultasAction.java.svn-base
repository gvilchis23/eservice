package com.bancoazteca.monitoreo.consultas.estadisticas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bancoazteca.elite.beans.ConsultaMonitoreoRequestTO;
import com.bancoazteca.elite.beans.MonitoreoResponseTO;
import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.monitoreo.db.dao.ConsultaMonitoreoTO;
import com.bancoazteca.monitoreo.MonitoreoAction;
import com.bancoazteca.monitoreo.utileria.Constantes;


public class ConsultasAction extends MonitoreoAction{
	
	public ActionForward realizaMonitoreo(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception{
        
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		MonitoreoResponseTO monitoreoResponseTO = new MonitoreoResponseTO();
		ConsultaMonitoreoRequestTO monitoreoRequestTO = new ConsultaMonitoreoRequestTO();
		String forward = null;
		ConsultasForm formulario = (ConsultasForm)form;
		Collection<ConsultaMonitoreoTO> resultadosConsulta = new ArrayList<ConsultaMonitoreoTO>();
		String rangoFechasConsulta = "";
		Date date = null;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				
		String opcion = formulario.getConsulta();
		String fechaInicial = null;
		String fechaFinal = null;
		if(formulario.isConFechas()){
			fechaInicial = formulario.getFechaInicial().replaceAll("-", "/");
			fechaFinal = formulario.getFechaFinal().replaceAll("-", "/");
			monitoreoRequestTO.setConsultaFechaInicio(fechaInicial);
			monitoreoRequestTO.setConsultaFechaFin(fechaFinal);
			monitoreoRequestTO.setConsultaPorFecha(true);
		}else{
			monitoreoRequestTO.setConsultaFechaInicio(fechaInicial);
			monitoreoRequestTO.setConsultaFechaFin(fechaFinal);
			monitoreoRequestTO.setConsultaPorFecha(false);
		}
		
		try{
			if (opcion.equals("1")){
				System.out.println("Total de usuarios registrados por aplicacion");
				forward="totalUsuariosAplicacion";
				if(fechaInicial!=null){
					
					date = format.parse(fechaInicial);
					if(date.compareTo(format.parse("15/02/2011"))>0){
						monitoreoResponseTO = resourceFacadeSL.getTotalUsuariosAplicacion(monitoreoRequestTO);
						resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}else{
						monitoreoResponseTO = resourceFacadeSL.getTotalUsuariosPorAplicacion(monitoreoRequestTO);
						resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}
				}else{
					monitoreoResponseTO = resourceFacadeSL.getAllTotalUsuariosAplicacion();
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
				}
				}else if (opcion.equals("2")){
				System.out.println("Lista de usuarios registrados");
				forward="listaUsuariosRegistrados";
				if(fechaInicial!=null){
					date = format.parse(fechaInicial);
					if(date.compareTo(format.parse("15/02/2011"))>0){
						monitoreoResponseTO = resourceFacadeSL.getUsuariosAplicacion(monitoreoRequestTO);
						resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}else{
					monitoreoResponseTO = resourceFacadeSL.getUsuariosPorAplicacion(monitoreoRequestTO);
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}
				}else{
					monitoreoResponseTO = resourceFacadeSL.getAllUsuariosAplicacion();
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
				}
				
				
			}else if (opcion.equals("3")){
				System.out.println("Total de conexiones realizadas por usuario");
				forward="totalConexionesUsuario";
				if(fechaInicial!=null){
					date = format.parse(fechaInicial);
					if(date.compareTo(format.parse("15/02/2011"))>0){
						monitoreoResponseTO = resourceFacadeSL.getUsuariosAplicacion(monitoreoRequestTO);
						resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}else{
					monitoreoResponseTO = resourceFacadeSL.getTotalConexionesUsuarios(monitoreoRequestTO);
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}
				}else{
					monitoreoResponseTO = resourceFacadeSL.getAllUsuariosAplicacion();
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
				}
				
				
			}else if (opcion.equals("4")){
				System.out.println("Total de operaciones realizadas");
				forward="totalOperacionesUsuario";
				if(fechaInicial!=null){
					date = format.parse(fechaInicial);
					if(date.compareTo(format.parse("15/02/2011"))>0){
						monitoreoResponseTO = resourceFacadeSL.getTotalOperacionesByUsuarios(monitoreoRequestTO);
						resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}else{
					monitoreoResponseTO = resourceFacadeSL.getTotalOperacionesUsuarios(monitoreoRequestTO);
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}
				}else{
					monitoreoResponseTO = resourceFacadeSL.getAllTotalOperaByUser();
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
				}
				
				
			}else if (opcion.equals("5")){
				System.out.println("Lista de operaciones realizadas por usuario");
				forward="listaOperacionesRealizadas";
				if(fechaInicial!=null){
					date = format.parse(fechaInicial);
					if(date.compareTo(format.parse("15/02/2011"))>0){
						monitoreoResponseTO = resourceFacadeSL.getTrackingUsuariosAplicacion(monitoreoRequestTO);
						resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}else{
					monitoreoResponseTO = resourceFacadeSL.getTrackingUsuariosPorAplicacion(monitoreoRequestTO);
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
					}
				}else{
					monitoreoResponseTO = resourceFacadeSL.getAllTrackingUserAplica();
					resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
				}
			}else if (opcion.equals("6")){
				System.out.println("Lista de operaciones realizadas con monto");
				forward="listaOperacionesMonto";
				monitoreoResponseTO = resourceFacadeSL.getOperacionMonto(monitoreoRequestTO);
				resultadosConsulta = monitoreoResponseTO.getResultadoConsulta();
				
				
			}else {
				System.out.println("Error consulta no definida");
				Map<String, String> errors = new HashMap<String, String>();
				errors.put("login", "Lo sentimos, opción no valida");
				request.getSession().setAttribute(Constantes.ERROR, errors);
				forward="failed";
			}
						
		}catch(EliteDataException e){
			System.out.println("EliteDataException");
			request.setAttribute( Constantes.ERROR, e.getErrorData() );
			forward="failed";
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("Exception");
			request.setAttribute( Constantes.ERROR, "Lo sentimos, existio un error al consultar" );
			forward="failed";
			e.printStackTrace();
		}
		
		request.setAttribute( Constantes.RESULTADOS_CONSULTA, resultadosConsulta );
		
		return mapping.findForward(forward);
	}
	
}
