package com.bancoazteca.eservice.utils.eserviceemail.plantillas;

import java.io.File;
import java.util.HashMap;
import java.util.WeakHashMap;

import com.bancoazteca.eservice.utils.eserviceemail.utils.ReadFiles;;

public class AdministradorPlantillasSession {
	
	
	private static AdministradorPlantillasSession instance; 
	private HashMap<String, String> plantillasSession;
	private String EXT=".html";
	
	private AdministradorPlantillasSession(){
		plantillasSession = new HashMap<String, String>();
	}
	
	public static AdministradorPlantillasSession getInstance(){
		if( instance == null ){
			instance= new AdministradorPlantillasSession();
		}
		return instance;
	}
	
	public String getPlantilla(String nombrePlantilla){
		String path=AdministradorPlantillasSession.class.getClassLoader().getResource("plantillas"+File.separator+nombrePlantilla+File.separator+nombrePlantilla+EXT).getPath();
		File file = new File(path);
		String plantilla = plantillasSession.get(nombrePlantilla);
		
		if( plantilla == null || plantilla.equals("")){
			plantilla= new ReadFiles(file.getAbsolutePath()).readFileString();
			plantillasSession.put(nombrePlantilla, plantilla);
		}
		return plantilla;
	}
}
