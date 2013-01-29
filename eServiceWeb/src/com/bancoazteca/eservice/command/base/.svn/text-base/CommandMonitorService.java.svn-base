package com.bancoazteca.eservice.command.base;

import com.bancoazteca.elite.beans.DetalleMonitoreoTO;
import com.bancoazteca.elite.beans.UsuarioOperacionesTO;
import com.bancoazteca.elite.beans.UsuariosTO;
import com.bancoazteca.elite.util.PropertiesService;

public class CommandMonitorService {

	
	
public void insertaUsuarioOperacion( UsuarioOperacionesTO usuarioOperacion){
		
		CommandBase commandBase = new CommandBase();
		try{
			String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
			if("true".equalsIgnoreCase(realizaMonitoreo)){
				commandBase.getDelegate().insertaUsuarioOperacion(usuarioOperacion);
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}
		
	}
	
public void insertaUsuarios( UsuariosTO usuarios){
		
		CommandBase commandBase = new CommandBase();
		try{
			String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
			if("true".equalsIgnoreCase(realizaMonitoreo)){
				commandBase.getDelegate().insertaUsuarios(usuarios);
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}
		
	}

public void insertError( UsuarioOperacionesTO usuarioOperacion,DetalleMonitoreoTO detalle){
	
	CommandBase commandBase = new CommandBase();
	try{
		String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
		if("true".equalsIgnoreCase(realizaMonitoreo)){
			commandBase.getDelegate().insertError(usuarioOperacion,detalle);
		}
	}catch(Exception exception){
		exception.printStackTrace();
	}
	
}
	


public Integer getIdUsuario(String userName, String aplicacion){
	
	CommandBase commandBase = new CommandBase();
	Integer idUsuario=0;
	try{
		String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
		if("true".equalsIgnoreCase(realizaMonitoreo)){
			idUsuario=commandBase.getDelegate().getIdUsuario(userName,aplicacion);
		}
	}catch(Exception exception){
		exception.printStackTrace();
	}
	return idUsuario;
	
}

public Integer getIdTracking(String idUsuarioOperacion){
	
	CommandBase commandBase = new CommandBase();
	Integer idTracking=0;
	try{
		String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
		if("true".equalsIgnoreCase(realizaMonitoreo)){
			idTracking=commandBase.getDelegate().getIdTracking(idUsuarioOperacion);
		}
	}catch(Exception exception){
		exception.printStackTrace();
	}
	return idTracking;
	
}	



public Integer getIdUsuarioOperacion(String userName, String aplicacion){
	
	CommandBase commandBase = new CommandBase();
	Integer idUsuarioOperacion=0;
	try{
		String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
		if("true".equalsIgnoreCase(realizaMonitoreo)){
			idUsuarioOperacion=commandBase.getDelegate().getIdUsuarioOperacion(userName,aplicacion);
		}
	}catch(Exception exception){
		exception.printStackTrace();
	}
	return idUsuarioOperacion;
	
}	

public void insertXml(DetalleMonitoreoTO detalle){
	
	CommandBase commandBase = new CommandBase();
	
	try{
		String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
		if("true".equalsIgnoreCase(realizaMonitoreo)){
			commandBase.getDelegate().insertXml(detalle);
		}
	}catch(Exception exception){
		exception.printStackTrace();
	}
	
	
}

public void insertMapa(Integer idMapa, Integer idTracking,String campo, String valor){
	
	CommandBase commandBase = new CommandBase();
	
	try{
		String realizaMonitoreo=PropertiesService.getInstance().getPropertie("Monitoreo.properties", "realiza.monitoreo.login");
		if("true".equalsIgnoreCase(realizaMonitoreo)){
			commandBase.getDelegate().insertMapa(idMapa,idTracking,campo,valor);
		}
	}catch(Exception exception){
		exception.printStackTrace();
	}
	
	
}
	
	
}
