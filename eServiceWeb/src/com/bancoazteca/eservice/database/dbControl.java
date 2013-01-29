package com.bancoazteca.eservice.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import org.apache.log4j.Logger;

import com.bancoazteca.eservice.command.base.beans.MonitoreoTO;

public class dbControl {
	private static final Logger log = Logger.getLogger(dbControl.class);
    /**
     * Queries utilizado para la insercion.
     */
  	protected static final String SEGUIMIENTO_INSERT_SQL = "INSERT INTO monitoreo (usuario, fecha, descripcionlarga, xmlresponse, xmlrequest, idsistema, idservicio, status, idsessionapp, idsessioncliente) VALUES (?, CURDATE(), ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     *  Query utilizado para la busqueda.
     */
	protected static final String SEGUIMIENTO_SEARCH_SQL = "SELECT * FROM monitoreo WHERE 1=1 ";

	static String basededatos="monitoreodb";	
	private static Connection conn=null;	
	public dbControl(){
		
		

		if (conn==null){
	    try {
			
				Class.forName("org.hsqldb.jdbcDriver");
				conn = DriverManager.getConnection("jdbc:hsqldb:" + basededatos, "sa", "");     
				creabase();
				System.out.print("conectado a "+basededatos);
	
	      } catch (Exception ex1) {
	          ex1.printStackTrace();   
	          System.out.print(ex1);
               
	      }
		}
	}
	
	public Connection conexion() throws ClassNotFoundException{
		return conn;		
	}
	
	public void shutdown() throws SQLException {
		Statement st = conn.createStatement();
		st.execute("SHUTDOWN");
		conn.close();  
		conn=null;
    }
	
	

	
    public synchronized void query(String expression) throws SQLException {

        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
            log.info("db error : " + expression);
        }

        st.close();
    }    

    
    public synchronized List<MonitoreoTO> rquery(String and_condiciones) throws Exception {
		

		MonitoreoTO outSearchMonitoreoTO = null;

		ResultSet rs = null;
		PreparedStatement prepStmt = null;
		ArrayList<MonitoreoTO> list = new ArrayList<MonitoreoTO>();

		StringBuffer selectStatement = new StringBuffer();
		
		selectStatement.append(SEGUIMIENTO_SEARCH_SQL);
		selectStatement.append(and_condiciones);

		try {

			
			 //st = conn.createStatement();
			//conn = getConnection();

			prepStmt = conn.prepareStatement(selectStatement.toString());
			rs = prepStmt.executeQuery();

			while(rs.next()) {

				outSearchMonitoreoTO = new MonitoreoTO();

				
				try{
					outSearchMonitoreoTO.setIdTracking(new Integer(rs.getInt(1)));
				}catch(Exception e){
					outSearchMonitoreoTO.setIdTracking(null);
				}

				try{
					outSearchMonitoreoTO.setUsuario(new String(rs.getString(2)));
				}catch(Exception e){
					outSearchMonitoreoTO.setUsuario(null);
				}

				try{
					outSearchMonitoreoTO.setFecha(rs.getDate(3));
				}catch(Exception e){
					outSearchMonitoreoTO.setFecha(null);
				}
				
				try{
					outSearchMonitoreoTO.setDescripcionLarga(new String(rs.getString(4)));
				}catch(Exception e){
					outSearchMonitoreoTO.setDescripcionLarga(null);
				}
				
				try{
					outSearchMonitoreoTO.setXmlResponse(new String(rs.getString(5)));
				}catch(Exception e){
					outSearchMonitoreoTO.setXmlResponse(null);
				}
				
				try{
					outSearchMonitoreoTO.setXmlRequest(new String(rs.getString(6)));
				}catch(Exception e){
					outSearchMonitoreoTO.setXmlRequest(null);
				}

				try{
					outSearchMonitoreoTO.setIdSistema(new Integer(rs.getInt(7)));
				}catch(Exception e){
					outSearchMonitoreoTO.setIdSistema(null);
				}

				try{
					outSearchMonitoreoTO.setIdServicio(new Integer(rs.getInt(8)));
				}catch(Exception e){
					outSearchMonitoreoTO.setIdServicio(null);
				}

				try{
					outSearchMonitoreoTO.setStatus(new String(rs.getString(9)));
				}catch(Exception e){
					outSearchMonitoreoTO.setStatus(null);
				}

				try{
					outSearchMonitoreoTO.setIdSessionApp(new Integer(rs.getInt(10)));
				}catch(Exception e){
					outSearchMonitoreoTO.setIdSessionApp(null);
				}

				try{
					outSearchMonitoreoTO.setIdSessionCliente(new Integer(rs.getInt(11)));
				}catch(Exception e){
					outSearchMonitoreoTO.setIdSessionCliente(null);
				}

				list.add(outSearchMonitoreoTO);
				
			}
			
		} catch (SQLException sqle) {
			StringBuffer sb = new StringBuffer();
			sb.append("==> SQLException: ");
			while (sqle != null) {
				sb.append("Message:   " + sqle.getMessage());
				sb.append(", ");
				sb.append("SQLState:  " + sqle.getSQLState());
				sb.append(", ");
				sb.append("ErrorCode: " + sqle.getErrorCode());
				sqle = sqle.getNextException();
			}
			throw new Exception(sb.toString());
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer();
			sb.append("==> Exception: ");
			sb.append("Message:   " + e.getMessage());
			throw new Exception(sb.toString());
		} finally {
			// close connection
			//closeAll(con, prepStmt, rs);

		}

		return list;
    }    
    
    
    public synchronized void creabase() throws SQLException {
        try {
        	
        	query("CREATE TABLE sistema ( idsistema INTEGER IDENTITY PRIMARY KEY, sistema VARCHAR(50), descripcion VARCHAR(100))");
          	query("CREATE TABLE servicio ( idservicio INTEGER IDENTITY PRIMARY KEY, servicio VARCHAR(50), descripcion VARCHAR(100))");
          	query("CREATE TABLE monitoreo ( idtracking INTEGER IDENTITY, usuario VARCHAR(50), fecha DATE, descripcionlarga VARCHAR(50), xmlresponse VARCHAR(10000), xmlrequest VARCHAR(10000), idsistema INTEGER, idservicio INTEGER, status VARCHAR(100), idsessionapp INTEGER, idsessioncliente INTEGER, FOREIGN KEY (idsistema) REFERENCES sistema(idsistema), FOREIGN KEY (idservicio) REFERENCES servicio(idservicio))");
            query("INSERT INTO sistema (sistema, descripcion) values('sistema uno','este es el sistema uno')");
            query("INSERT INTO servicio (servicio, descripcion) values('servicio uno','este es el servicio uno')");
           
            System.out.print("se crearon las tablas monitoreo, sistema y servicios");
        } catch (SQLException ex) {
        	System.out.print("la base de datos ya existe\n");
        	return;
        }
    } 
       
	
}