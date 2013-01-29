package com.bancoazteca.eservice.filters;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.bancoazteca.elite.ejb.exception.EliteDataException;
import com.bancoazteca.elite.util.PropertiesService;

public class FilterManager {
	private static final Logger log = Logger.getLogger(FilterManager.class);
	private HashMap<String, Object> sessionFilter = new HashMap<String, Object>(); 
	private Vector<Filter> filtros = new Vector<Filter>();

	
	public FilterManager(HashMap<String, Object> configuracion) {
		sessionFilter = configuracion;		
		Resource fileSystemResource = null;
		BeanFactory beanFactory = null;
		try{
			String path = (String)configuracion.get("path");
			log.info( "#######################################################################################################" );
			log.info( "path:  " + PropertiesService.getInstance().getPropertie(ConstantesFiltro.FILTRO_PROPERTIES, path ));
			log.info( "idAplicacion: " + (String) configuracion.get(ConstantesFiltro.ID_APLICACION) );
			fileSystemResource = new FileSystemResource( PropertiesService.getInstance().getPropertie(
														ConstantesFiltro.FILTRO_PROPERTIES, path ) );
			beanFactory = new XmlBeanFactory(fileSystemResource);
			filtros.addAll( (Collection<? extends Filter>) beanFactory.getBean( (String) configuracion.get(ConstantesFiltro.ID_APLICACION) ) );
			log.info( "Se encontraron "+ filtros.size() + " filtros para la aplicación: " + (String) configuracion.get(ConstantesFiltro.ID_APLICACION) );
		}catch (Exception e) {
//			e.printStackTrace();
			log.info( "Filtro --------------> No se encontraron filtros para la aplicación: " + (String) configuracion.get(ConstantesFiltro.ID_APLICACION) );
		}
	}
	
	public void ejecutaFiltros() throws CuentaFilterException, AlnovaRedMovilFilterException, EliteDataException, Exception {
		log.info( "Por ejecutar los filtros ");
		Iterator<Filter> it = filtros.iterator();
		Filter filtro = null;
		while(it.hasNext()){
			filtro = it.next();
			filtro.ejecutar( sessionFilter );
		}		
	}
	
	public boolean filtrarAplicacion(String idAplicacion){
		boolean respuesta = false;
		if(filtros.size() > 0){
			respuesta = true;
		}
		log.info("Se filtrara la aplicación " + idAplicacion + "?: " + respuesta);
		return respuesta;
	}

}
