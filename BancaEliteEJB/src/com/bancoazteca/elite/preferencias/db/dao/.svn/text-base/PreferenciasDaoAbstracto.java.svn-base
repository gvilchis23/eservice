package com.bancoazteca.elite.preferencias.db.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.XPathException;

import org.apache.axis.message.MessageElement;
import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;

import com.bancoazteca.elite.beans.CuentaBloqueadaDBTO;
import com.bancoazteca.elite.beans.PreguntasFrecuentesResponseTO;
import com.bancoazteca.elite.commons.xml.XMLDecode;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.commons.xml.XmlDecoder;
import com.bancoazteca.elite.connector.Connector;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.db.JDNIConnection;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.util.PropertiesService;

public class PreferenciasDaoAbstracto {

 	public static final String TYPE_COLLECTION = "collection";
	public static final String TYPE_BEAN = "bean";
	private final JDNIConnection connection=new JDNIConnection();
	private String USUARIO_MODIFICO_BD;
	
	private final String PATH_OBTIENE_TERMINAL_ALNOVA="/seguridad/obtieneTerminalAlnova.elite";
	private final String PATH_LIBERA_TERMINAL_ALNOVA="/seguridad/liberaterminalAlnova.elite";
	
	Logger logger=Logger.getLogger(PreferenciasDaoAbstracto.class);

	public Collection<CuentaBloqueadaDBTO> getCuentasOcultas(CuentaBloqueadaDBTO to) throws DAOException {
		Connection conn= null;
		CallableStatement statement= null;
		Collection<CuentaBloqueadaDBTO> cuentasBloqueadas = new ArrayList<CuentaBloqueadaDBTO>();
		
		try {
			conn=connection.getConnection("BaseDatosCuentas.properties", "bd.jndi");
			String sql=obtenerFirmaFuncion("GET_CUENTAS(?)");
			statement=conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.setString(2, to.getIdUsuario());
			statement.execute();
			
			ResultSet rs = (ResultSet)statement.getObject(1);
			while (rs.next()) {
				String idUsuario = rs.getString(1); 
			    String  numero_cuenta=   rs.getString(2);
			    
				CuentaBloqueadaDBTO cuenta = new CuentaBloqueadaDBTO();
				cuenta.setIdUsuario(idUsuario);
				cuenta.setNumero_cuenta(numero_cuenta);
				
				cuentasBloqueadas.add(cuenta);
			}
			logger.info("Finalizó con éxito la consulta, usuario=" + to.getIdUsuario());
			return cuentasBloqueadas;
		} catch (InversionesGenericException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeConnection(statement, conn);
		}
		return cuentasBloqueadas;
	}
	
	public int insertarCuenta(CuentaBloqueadaDBTO to) throws DAOException  {
		Integer idUsuario=Integer.valueOf("0");
		Connection conn= null;
		CallableStatement statement= null;
		try {
			conn=connection.getConnection("BaseDatosCuentas.properties", "bd.jndi");
			String sql=obtenerFirmaFuncion("INSERTAR_CUENTAOCULTA(?, ?, ?)");
			statement=conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			statement.setString(2, to.getIdUsuario());
			statement.setString(3, to.getNumero_cuenta());
			statement.setString(4, this.USUARIO_MODIFICO_BD);
			statement.execute();
			idUsuario = ( Integer ) statement.getObject(1);
			logger.info("Finalizó con éxito la inserción, cuenta="+to.getNumero_cuenta());
			return idUsuario.intValue();
		} catch (InversionesGenericException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} catch(IOException e){
			e.printStackTrace();
			throw new DAOException(e);
		} catch(SQLException e){
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			closeConnection(statement, conn);
		}
	}
	
	public int eliminarCuenta(CuentaBloqueadaDBTO to) throws DAOException {
		Integer idUsuario=Integer.valueOf("0");
		Connection conn= null;
		CallableStatement statement= null;
		try {
			conn=connection.getConnection("BaseDatosCuentas.properties", "bd.jndi");
			String sql=obtenerFirmaProcedure("ELIMINAR_CUENTAOCULTA(?, ?)");
			statement=conn.prepareCall(sql);
			statement.setString(1, to.getIdUsuario());
			statement.setString(2, to.getNumero_cuenta());
			statement.execute();
			idUsuario = Integer.valueOf("1");
			logger.info("Finalizó con éxito la eliminación, cuenta="+to.getNumero_cuenta());
			return idUsuario.intValue();
		} catch (InversionesGenericException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} catch(IOException e){
			e.printStackTrace();
			throw new DAOException(e);
		} catch(SQLException e){
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			closeConnection(statement, conn);
		}
	}

//	public String filtraCuentasString(String xml, String cuenta) throws DAOException {
//		String query = "let $cuentas := //numero_cuenta[@value="+cuenta+"]/parent::node() return <query>{$cuentas}</query>";
//		String cuentaOculta = doXQuery(xml, query);
//		
//		cuentaOculta = cuentaOculta.replace("<query>","");
//		cuentaOculta = cuentaOculta.replace("</query>","");
//		
//		Pattern p = Pattern.compile("\\s*/>");
//		Matcher m = p.matcher(xml);
//        StringBuffer sb = new StringBuffer();
//	    while(m.find()) {
//	          m.appendReplacement(sb,"/>");
//	    }
//        m.appendTail(sb);
//        xml = sb.toString();
//		String salida = xml.replace(cuentaOculta, "");
//		
//		return salida;
//	}
	
	public String filtraCuentas(String xml, String cuenta) throws DAOException {
		String xmlSalida = xml;
		Builder builder = new Builder();     
	    try {
	      Document doc = builder.build(xml, null);
	      Element root = doc.getRootElement();
	      Nodes nodoCuentas = doc.query("//numero_cuenta[@value="+cuenta+"]/parent::node()"); 
	      if (nodoCuentas.size()>0) {
	    	  Node nodoCuenta = nodoCuentas.get(0);
	    	  nodoCuenta.detach();
	      }
	      else { logger.error("La cuenta " + cuenta + " no existe para el usuario logueado.");  }
	      xmlSalida = root.toXML();
	      return xmlSalida;
	    }catch (XPathException e) {
			e.printStackTrace();
			throw new DAOException(e);
	    }
	    catch (ParsingException e) { 
			e.printStackTrace();
			throw new DAOException(e);
	    }  
	    catch (IOException e) { 
			e.printStackTrace();
			throw new DAOException(e);
	    }
	}
	
	public MessageElement liberaterminalAlnova(String user,String terminal)throws SessionExpiredException,DAOException{
		Connector connector=null;
		connector=ConnectorManager.getConnector(user);
		MessageElement messageElement=null;
		try{
			HashMap<String, String> params=new HashMap<String, String>();
			params.put("terminal", terminal);
			String xml=connector.sendRequest(PATH_LIBERA_TERMINAL_ALNOVA, params);
			messageElement=XMLDecode.buildXMLMessageElement(xml);
		}  catch (URISyntaxException e) {
			throw new DAOException(e.getMessage());
		} catch (HttpException e) {
			throw new DAOException(e.getMessage());
		} catch (IOException e) {
			throw new DAOException(e.getMessage());
		} catch (XmlDecodeException e) {
			throw new DAOException(e.getMessage());
		}
		return messageElement;
	}
	
	public MessageElement getTerminalAlnova(String user) throws SessionExpiredException,DAOException{
		Connector connector=null;
		connector=ConnectorManager.getConnector(user);
		MessageElement messageElement=null;
		try {
			String xml=connector.sendRequest(PATH_OBTIENE_TERMINAL_ALNOVA, null);
			messageElement = XMLDecode.buildXMLMessageElement(xml);
		} catch (URISyntaxException e) {
			throw new DAOException(e.getMessage());
		} catch (HttpException e) {
			throw new DAOException(e.getMessage());
		} catch (IOException e) {
			throw new DAOException(e.getMessage());
		} catch (XmlDecodeException e) {
			throw new DAOException(e.getMessage());
		}
		
		return messageElement;
	}
	
	public PreguntasFrecuentesResponseTO getPreguntasFrecuentes() throws IOException, XmlDecodeException {
		PropertiesService propertiesService = PropertiesService.getInstance();
		String xml = readXML(propertiesService.getPath() + "preguntas_frecuentes.xml");	
		XmlDecoder decode = new XmlDecoder();
		MessageElement messageElement = XMLDecode.buildXMLMessageElement(xml);
		
		HashMap<String, String> mappedBeans = new HashMap<String, String>();
		mappedBeans.put("preguntas_frecuentes", "com.bancoazteca.elite.beans.PreguntasFrecuentesResponseTO");	
		mappedBeans.put("coleccion_preguntas_frecuentes", "java.util.ArrayList<com.bancoazteca.elite.beans.Pregunta_FrecuenteTO>");
		mappedBeans.put("pregunta_frecuente", "com.bancoazteca.elite.beans.Pregunta_FrecuenteTO");
		PreguntasFrecuentesResponseTO preguntas_frecuentes = (PreguntasFrecuentesResponseTO)decode.toDeserialize("preguntas_frecuentes", messageElement, mappedBeans);
		return preguntas_frecuentes;
	}
	
	private String readXML(String archivo) {
		File file = new File(archivo);
		String xml = "";
		try {
    		FileReader fileReader = new FileReader(file);
    		BufferedReader bufferedReader = new BufferedReader(fileReader);
    		while (bufferedReader.ready()){
    			xml += bufferedReader.readLine().trim();
    		}
    		HashMap<String,String> tagsList = new HashMap<String,String>();	
    		tagsList.put("coleccion_preguntas_frecuentes", TYPE_COLLECTION);
    		tagsList.put("preguntas_frecuentes", TYPE_BEAN);
    		tagsList.put("pregunta_frecuente", TYPE_BEAN);
    		
    		 Iterator<Entry<String, String>> iteratorListTag = tagsList.entrySet().iterator();			
    			while(iteratorListTag.hasNext()){
    				Map.Entry<String, String> entry = (Map.Entry<String, String>)iteratorListTag.next();
    				xml =  xml.replaceAll("<"+entry.getKey()+">" , "<"+entry.getKey() +" type=\""+entry.getValue()+"\">");	
    			}
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return xml;
	}
	
	private String obtenerFirmaFuncion(String firmaFuncion) throws IOException{
		String BASE_DATOS_PROPERTIES = "BaseDatosCuentas.properties";
		PropertiesService propertiesService=PropertiesService.getInstance();
		USUARIO_MODIFICO_BD = propertiesService.getPropertie(BASE_DATOS_PROPERTIES,"bd.usuario");
		String ESQUEMA_BD = propertiesService.getPropertie(BASE_DATOS_PROPERTIES,"bd.esquema");
		
		String resultado = "{ ? = call "+ESQUEMA_BD+"."+firmaFuncion+" }";
		
		return resultado;
	}
	
	private String obtenerFirmaProcedure(String firmaProcedure) throws IOException{
		String BASE_DATOS_PROPERTIES = "BaseDatosCuentas.properties";
		PropertiesService propertiesService=PropertiesService.getInstance();
		USUARIO_MODIFICO_BD = propertiesService.getPropertie(BASE_DATOS_PROPERTIES,"bd.usuario");
		String ESQUEMA_BD = propertiesService.getPropertie(BASE_DATOS_PROPERTIES,"bd.esquema");
		
		String resultado = "{ call "+ESQUEMA_BD+"."+firmaProcedure+" }";
		
		return resultado;
	}
	
	private void closeConnection(CallableStatement statement,Connection connection){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				logger.info("Ocurrió un problema al cerrar el statement para la base de datos (Estatus cuentas)");
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			logger.info("Ocurrió un problema al cerrar la conexión para la base de datos (Estatus cuentas)");
		}
	}
}
