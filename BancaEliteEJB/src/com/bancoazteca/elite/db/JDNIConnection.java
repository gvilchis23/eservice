package com.bancoazteca.elite.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.util.PropertiesService;

public class JDNIConnection {

	public final static String BASE_DATOS_PROPERTIES = "BaseDatos.properties";
	
	public Connection getConnection() throws InversionesGenericException{
		Connection connection = null;
		Context context=null;
		try{
			context = new InitialContext();
			PropertiesService propertiesService=PropertiesService.getInstance();
			String jdniBaseDatos=propertiesService.getPropertie(BASE_DATOS_PROPERTIES,"bd.jndi");
						
			connection = getDataSource(context, jdniBaseDatos);

		}catch(Exception e){
			try {
				context.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos.");
			throw new InversionesGenericException(exceptionTO);
		}finally{
			try {
				context.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	public Connection getConnection(String propertiesFileName, String jndi) throws InversionesGenericException{
		Connection connection = null;
		Context context=null;
		try{
			context = new InitialContext();
			PropertiesService propertiesService=PropertiesService.getInstance();
			String jdniBaseDatos=propertiesService.getPropertie(propertiesFileName,jndi);
						
			connection = getDataSource(context, jdniBaseDatos);

		}catch(Exception e){
			try {
				context.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos.");
			throw new InversionesGenericException(exceptionTO);
		}finally{
			try {
				context.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	private Connection getDataSource(Context context, String jdniBaseDatos)throws NamingException, SQLException {
		Connection connection;
		DataSource dataSource = (DataSource) context.lookup(jdniBaseDatos);
		context.getEnvironment();
		connection = dataSource.getConnection();
		return connection;
	}
}
