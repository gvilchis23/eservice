package com.bancoazteca.monitoreo.registro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bancoazteca.elite.beans.MonitoreoAdministradorRequestTO;
import com.bancoazteca.monitoreo.MonitoreoAction;

public class RegistroAction extends MonitoreoAction {

	public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		RegistroForm forma=(RegistroForm)form;
		System.out.println(forma.getContrasenia());
		System.out.println(forma.getNombre());
		System.out.println(forma.getUsuario());
		try {
			MonitoreoAdministradorRequestTO administradorRequestTO = new MonitoreoAdministradorRequestTO();
			administradorRequestTO.setNombre( forma.getNombre() );
			administradorRequestTO.setUsuario( forma.getUsuario() );
			administradorRequestTO.setPassword( forma.getContrasenia() );
			
			getDelegate().insertaUsuariosAdministrador( administradorRequestTO );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}
