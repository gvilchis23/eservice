package com.bancoazteca.elite.inversiones.db.dao;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.LoginConciliacionRequestTO;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.util.PropertiesService;

public class ConciliacionesDAO {
	
	Logger logger=Logger.getLogger(ConciliacionesDAO.class);

	public String getLoginConciliacion(LoginConciliacionRequestTO requestTO) throws DAOException {
		PropertiesService propertiesService = PropertiesService.getInstance();
		String tieneAcceso;
		try {
			tieneAcceso = propertiesService.getPropertie( "ConciliacionesUsuarios.properties" , "bancaprivada.reportos.conciliaciones." + requestTO.getIdUsuario() );
			if (tieneAcceso == null) 
				tieneAcceso = "false";
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return tieneAcceso;		
	}
}