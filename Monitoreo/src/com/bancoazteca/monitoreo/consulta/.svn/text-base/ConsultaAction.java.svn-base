package com.bancoazteca.monitoreo.consulta;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bancoazteca.elite.beans.ConsultaMonitoreoRequestTO;
import com.bancoazteca.elite.beans.MonitoreoResponseTO;
import com.bancoazteca.elite.ejb.facade.ResourceFacadeSL;
import com.bancoazteca.elite.monitoreo.db.dao.ConsultaMonitoreoTO;
import com.bancoazteca.monitoreo.MonitoreoAction;

public class ConsultaAction extends MonitoreoAction{
	public ActionForward realizaMonitoreo(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception{
        
		ResourceFacadeSL resourceFacadeSL = getDelegate();
		MonitoreoResponseTO temp = new MonitoreoResponseTO();
		ConsultaMonitoreoRequestTO fechasBusqueda = new ConsultaMonitoreoRequestTO();
		String forward = null;
		ConsultaForm formulario = (ConsultaForm)form;
		Collection<ConsultaMonitoreoTO> listaConsultaMonitoreoTO = new ArrayList<ConsultaMonitoreoTO>();
		Collection<UsuariosAplicacionTO> lista = new ArrayList<UsuariosAplicacionTO>();
		
		String opcion = formulario.getConsulta();
		if(!formulario.isConFechas()){
			String fechaInicio = formulario.getAnioInicial()+"-"+formulario.getMesInicial()+"-"+formulario.getDiaInicial();
			String fechaFin = formulario.getAnioFinal()+"-"+formulario.getMesFinal()+"-"+formulario.getDiaFinal();
			fechasBusqueda.setConsultaFechaInicio(fechaInicio);
			fechasBusqueda.setConsultaFechaFin(fechaFin);
			fechasBusqueda.setConsultaPorFecha(true);
		}
		else{
			String fechaInicio = null;
			String fechaFin = null;
			fechasBusqueda.setConsultaFechaInicio(fechaInicio);
			fechasBusqueda.setConsultaFechaFin(fechaFin);
			fechasBusqueda.setConsultaPorFecha(false);
		}
		try{

			if (opcion.equals("1")){
				forward="aplicacion";
				temp = resourceFacadeSL.getDatosMonitoreoPorAplicacion(fechasBusqueda);
				listaConsultaMonitoreoTO = temp.getResultadoConsulta();
				lista = EJBtoTO(listaConsultaMonitoreoTO);
				
				request.setAttribute("lista", lista);
			}
			else{
				forward="usuario";
				temp = resourceFacadeSL.getDatosMonitoreoPorUsuario(fechasBusqueda);
				listaConsultaMonitoreoTO = temp.getResultadoConsulta();
				lista = EJBtoTO(listaConsultaMonitoreoTO);
				request.setAttribute("lista", lista);
			}
		}catch(Exception e){}
		return mapping.findForward(forward);
	}
	
	public Collection<UsuariosAplicacionTO> EJBtoTO(Collection<ConsultaMonitoreoTO> listaUsuariosAplicacion){
		Collection<UsuariosAplicacionTO> lista = new ArrayList<UsuariosAplicacionTO>();
		for (ConsultaMonitoreoTO ConsultaMonitoreoTO : listaUsuariosAplicacion) {
			UsuariosAplicacionTO usuariosAplicacionTo=new UsuariosAplicacionTO();
			usuariosAplicacionTo.setAplicacion(ConsultaMonitoreoTO.getAplicacion());
			usuariosAplicacionTo.setNombre(ConsultaMonitoreoTO.getNombre());
			usuariosAplicacionTo.setNumero_conexiones(ConsultaMonitoreoTO.getTotalUsuarios());
			lista.add(usuariosAplicacionTo);
		}
		return lista;
	}
}
