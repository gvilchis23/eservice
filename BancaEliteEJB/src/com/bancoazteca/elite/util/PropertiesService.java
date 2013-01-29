package com.bancoazteca.elite.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class PropertiesService {
	
	private static final Logger log = Logger.getLogger(PropertiesService.class);
		
	private final String ISO = "ISO-8859-1";	
	
	public static final String EBANKING = "eBanking.properties";
	public static final String DUMMY = "dummy.properties";
	public static final String USERS = "eServiceUsers.properties";
	
	private final String CERTIFICATE = "elite.certificate";
	private final String ENVIROMENT_KEY = "sistema.ambiente";
	private final String ELITE_HOME = "elite.home";
	private final String PROPERTIES_CACHE = "properties.cache";
	
	private static PropertiesService instance;		
	private String enviroment;	 		
	private Map<String, Properties> cache; 
	
	public static final PropertiesService getInstance(){
		if (instance==null){
			instance = new PropertiesService();
		}
		return instance;
	}
	
	private PropertiesService(){
		this.cache = new WeakHashMap<String, Properties>();
		enviroment = System.getProperty(ENVIROMENT_KEY);
		enviroment = "config"+File.separator+enviroment+File.separator;
		log.info("################"+enviroment+"##################");
	}
	

	public Properties getProperties(String file) throws IOException{
		Properties properties = (Properties) this.cache.get(file);
		if (properties==null){
			properties = loadProperties(file);
		}
		return properties;
	}
	
	public String getPropertie(String file,String key) throws IOException{
		Properties properties = getProperties(file);
		String value = properties.getProperty(key);
		return value;
	}
	
	public List<String> getPropertyAsList(String archivo, String llave ) throws IOException{
		List<String> lista = new Vector<String>();
			String valor = this.getPropertie( archivo,llave );
			if( valor != null ){
				String[] valores = valor.split( "," );
				lista = Arrays.asList( valores );
			}
		return lista;
	}
	
	public static URL getResourceUrl( String name ){
		URL url = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if( classLoader == null )
			classLoader = PropertiesService.class.getClassLoader();
		if( classLoader != null )
			url = classLoader.getResource( name );
		if( url == null )
			url = PropertiesService.class.getResource( "/" + name );
		return url;
	}
	
	private Properties loadProperties(final String file) throws IOException{				
		String path = getPath(file);				
		File input = new File(path);		
		FileInputStream inputStream = new FileInputStream(input);
		Properties properties = new Properties();
		properties.load(inputStream);
		String cache = properties.getProperty(PROPERTIES_CACHE);
		if (cache!=null && cache.equals("false")){
			this.cache.remove(file);
		}else{
			this.cache.put(file, properties);
		}
		return properties;
	}
	
	public InputStream getCertificateStream() throws IOException{
		PropertiesService propertiesService = PropertiesService.getInstance();
		String file = propertiesService.getPropertie(EBANKING,CERTIFICATE);					
		String path = getPath(file);		
//		String path = URLDecoder.decode(file,"ISO");
		FileInputStream fileInputStream = new FileInputStream(path);
		return fileInputStream;
	}
	
	private String getPath (final String file) throws UnsupportedEncodingException{
		String path = null;
		String contextFile = enviroment+file;
		String ambiente = System.getProperty(ENVIROMENT_KEY);
		
		if(ambiente.equalsIgnoreCase("produccion")){
			//TODO Direcciones de los properties	
			// 86: path = "/export/home/cmarquez/eliteProperties/"+file;
			// 106: path = "/home/elite/jboss-4.0.2/server/default/conf/elite/produccion/produccion/"+file;
			
			path = "/home/portalsl/jboss-4.0.2/server/default/conf/elite/produccion/produccion/"+file;
			
		}else{
			try{
				path = getPath() + contextFile;
//				ClassLoader classLoader = PropertiesService.class.getClassLoader();
//				path = URLDecoder.decode(classLoader.getResource(contextFile).getPath(),ISO);
			}catch(Throwable e){
				path = URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource(".."+File.separator+contextFile).getPath(),ISO);
			}
		}
		
		System.out.println("Path Obtenido del properties: " + path);
		
		return path;
	}
	
	public String getPath() throws UnsupportedEncodingException{
		String ambiente = System.getProperty(ENVIROMENT_KEY);
		String path = null;
		if(ambiente.equalsIgnoreCase("produccion")){
			path = "/home/portalsl/jboss-4.0.2/server/default/conf/elite/produccion/produccion/";
		}else{
			path = System.getProperty( ELITE_HOME );
		}
		return path;
	}
	
	public String getSistemaAmbiente(){
		String variableAmbiente = enviroment; 
		return variableAmbiente;
	}

}
