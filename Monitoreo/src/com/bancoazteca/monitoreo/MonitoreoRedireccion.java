package com.bancoazteca.monitoreo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MonitoreoRedireccion extends MonitoreoAction {

	public ActionForward redireccion(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		String forward = null;
		MonitoreoRedireccionForm forma = (MonitoreoRedireccionForm)form;
		
		if(forma.getOpcion().equalsIgnoreCase( "1" )){
			forward = "new";
		}else if(forma.getOpcion().equalsIgnoreCase( "2" )){
			forward = "old";
		}else{
			forward = "failed";
		}
		
		return mapping.findForward(forward);
	}
	
}
