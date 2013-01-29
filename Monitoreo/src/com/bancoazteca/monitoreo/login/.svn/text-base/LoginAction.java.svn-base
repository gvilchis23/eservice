package com.bancoazteca.monitoreo.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bancoazteca.elite.monitoreo.db.dao.MonitoreoAdministradorResponseTO;
import com.bancoazteca.monitoreo.MonitoreoAction;
import com.bancoazteca.monitoreo.utileria.Constantes;

public class LoginAction extends MonitoreoAction {
	
	public ActionForward home(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		String forward = null;
		
		request.getSession().removeAttribute("errorLogin");
		
		MonitoreoAdministradorResponseTO monitor = new MonitoreoAdministradorResponseTO();
		LoginForm forma = (LoginForm)form;
		
		String usuario = "administrador";
		String passowrd = "adminadmin";
		
		if( ( forma.getUsuario().equalsIgnoreCase( usuario ) &&  ( forma.getContrasenia().equalsIgnoreCase( passowrd ) ) ) ){
			monitor.setNombre("Usuario Administrador del Monitor");
			monitor.setUsuario(usuario);
			
			request.getSession().setAttribute(Constantes.NOMBRE_USUARIO, monitor);
			request.getSession().removeAttribute(Constantes.ERROR);
			forward="success";
		}else{
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("login", "Usuario o Contraseña incorrectos");
			request.getSession().setAttribute(Constantes.ERROR, errors);
			forward="failed";
		}
		
//		try{	
//			monitor = getDelegate().obtieneUsuarioMonitoreo(forma.getUsuario(),forma.getContrasenia());
//			if(monitor!=null){
//				if(monitor.getUsuario().equals("administrador")){
//					forward="administrador";
//				}
//				else{
//					forward="success";
//				}
//				request.getSession().setAttribute(Constantes.SESSION_USUARIO, monitor);
//			}
//			else{
//				request.getSession().setAttribute("errorLogin", "Usuario o Contraseña incorrectos");
//				forward="failed";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return mapping.findForward(forward);
	}
}
