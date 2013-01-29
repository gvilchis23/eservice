package com.bancoazteca.monitoreo.Logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bancoazteca.monitoreo.MonitoreoAction;
import com.bancoazteca.monitoreo.utileria.Constantes;

public class LogoutAction extends MonitoreoAction{
	public ActionForward home(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		request.getSession().removeAttribute(Constantes.NOMBRE_USUARIO);
		request.getSession().invalidate();
		
		return mapping.findForward("success");
	}

}
