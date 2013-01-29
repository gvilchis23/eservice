package com.bancoazteca.monitoreo.servlet;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;

import com.bancoazteca.elite.monitoreo.db.dao.MonitoreoAdministradorResponseTO;
import com.bancoazteca.monitoreo.utileria.Constantes;

public class MonitoreoActionServlet extends ActionServlet {

	private static final long serialVersionUID = 1L;
	private static Set<String> accionesPermitidas=new TreeSet<String>();
	static{
		accionesPermitidas.add("LoginAction");
//		accionesPermitidas.add("RegistroAction");
//		accionesPermitidas.add("ConsultaAction");
	}
	
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String url = request.getRequestURI();
		url = url.substring(url.lastIndexOf('/') + 1);
		url=url.substring(0, url.indexOf("."));
		if(accionesPermitidas.contains(url)){
			super.service(request, response);
		}else{
			MonitoreoAdministradorResponseTO to=(MonitoreoAdministradorResponseTO)request.getSession().getAttribute(Constantes.NOMBRE_USUARIO);
			if(!accionesPermitidas.contains(url)&& to==null){
				request.getSession().invalidate();
				request.getRequestDispatcher("/LoginAction.mt?accion=home").forward(request, response);
			}else{
				super.service(request, response);
			}
		}
		
	}
}
