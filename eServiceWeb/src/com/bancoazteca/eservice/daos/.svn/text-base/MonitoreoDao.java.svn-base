package com.bancoazteca.eservice.daos;

import com.bancoazteca.eservice.command.base.beans.MonitoreoTO;
import com.bancoazteca.eservice.database.dbControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

//import java.sql.DriverManager;
//import java.sql.Statement;

public class MonitoreoDao{

		Connection conn;
        /**
         * Queries utilizado para la insercion.
         */
	  	protected static final String SEGUIMIENTO_INSERT_SQL = "INSERT INTO monitoreo (usuario, fecha, descripcionlarga, xmlresponse, xmlrequest, idsistema, idservicio, status, idsessionapp, idsessioncliente) VALUES (?, CURDATE(), ?, ?, ?, ?, ?, ?, ?, ?)";
	
        /**
         *  Query utilizado para la busqueda.
         */
		protected static final String SEGUIMIENTO_SEARCH_SQL = "SELECT * FROM monitoreo WHERE 1=1 ";
	
		public MonitoreoDao()throws Exception {
			dbControl db= new dbControl();
			conn = db.conexion();        	
        }
	
        

		public void cerrar(){
			
		}
        
	/**
         *  Metodo que realiza la insercion.
	 * @throws SQLException 
         * 
         */
	public boolean insert(MonitoreoTO inInsertMonitoreoTO) throws SQLException, Exception {

		PreparedStatement prepStmt = null;
		int rowCount = 0;
		try {

			prepStmt = conn.prepareStatement(SEGUIMIENTO_INSERT_SQL);

			if ( inInsertMonitoreoTO.getUsuario() != null ) {
				prepStmt.setString(1, inInsertMonitoreoTO.getUsuario());
			} else {
				throw new Exception("Algunos valores no pueden ser nulos : " + inInsertMonitoreoTO);
				//prepStmt.setNull(1, java.sql.Types.VARCHAR);
			}

			if ( inInsertMonitoreoTO.getDescripcionLarga() != null ) {
				
				prepStmt.setString(2, inInsertMonitoreoTO.getDescripcionLarga());
			} else {
				prepStmt.setNull(2, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getXmlResponse() != null ) {
				prepStmt.setString(3, inInsertMonitoreoTO.getXmlResponse());
			} else {
				prepStmt.setNull(3, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getXmlRequest() != null ) {
				prepStmt.setString(4, inInsertMonitoreoTO.getXmlRequest());
			} else {
				prepStmt.setNull(4, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getIdSistema() != null ) {
				prepStmt.setInt(5, inInsertMonitoreoTO.getIdSistema().intValue());
			} else {
				throw new Exception("Algunos valores no pueden ser nulos : " + inInsertMonitoreoTO);
				//prepStmt.setNull(5, java.sql.Types.INTEGER);
			}
			if ( inInsertMonitoreoTO.getIdServicio() != null ) {
				prepStmt.setInt(6, inInsertMonitoreoTO.getIdServicio().intValue());
			} else {
				throw new Exception("Algunos valores no pueden ser nulos : " + inInsertMonitoreoTO);
				//prepStmt.setNull(6, java.sql.Types.INTEGER);
			}

			if ( inInsertMonitoreoTO.getStatus() != null ) {
				prepStmt.setString(7, inInsertMonitoreoTO.getStatus());
			} else {
				prepStmt.setNull(7, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getIdSessionApp() != null ) {
				prepStmt.setInt(8, inInsertMonitoreoTO.getIdSessionApp().intValue());
			} else {
				throw new Exception("Algunos valores no pueden ser nulos : " + inInsertMonitoreoTO);
				//prepStmt.setNull(8, java.sql.Types.INTEGER);
			}
			if ( inInsertMonitoreoTO.getIdSesionCliente() != null ) {
				prepStmt.setInt(9, inInsertMonitoreoTO.getIdSesionCliente().intValue());
			} else {
				throw new Exception("Algunos valores no pueden ser nulos : " + inInsertMonitoreoTO);
				//prepStmt.setNull(9, java.sql.Types.INTEGER);
			}
			rowCount = prepStmt.executeUpdate();
			if(rowCount == 0 ) {
				throw new Exception("Algunos valores no pueden ser nulos : " + inInsertMonitoreoTO);
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
			prepStmt.close();
		//	closeAll(conn, prepStmt, null);
		}

		return ( rowCount > 0 );

	}

        /**
         *  Metodo que realiza la busqueda.
         */
	public List<MonitoreoTO> search(MonitoreoTO inSearchMonitoreoTO) throws Exception {
		

		MonitoreoTO outSearchMonitoreoTO = null;

		ResultSet rs = null;
		PreparedStatement prepStmt = null;
		ArrayList<MonitoreoTO> list = new ArrayList<MonitoreoTO>();

		StringBuffer selectStatement = new StringBuffer();
		
		selectStatement.append(SEGUIMIENTO_SEARCH_SQL);


		if (inSearchMonitoreoTO.getUsuario() != null ) {
			selectStatement.append(" AND usuario = '" + inSearchMonitoreoTO.getUsuario()+"'");
		}

		if (inSearchMonitoreoTO.getFecha() != null ) {
			selectStatement.append(" AND fecha = '" + inSearchMonitoreoTO.getFecha()+"'");
		}
		
		if (inSearchMonitoreoTO.getDescripcionLarga() != null ) {
			selectStatement.append(" AND descripcionlarga = '" + inSearchMonitoreoTO.getDescripcionLarga()+"'");
		}

		if (inSearchMonitoreoTO.getXmlResponse() != null ) {
			selectStatement.append(" AND xmlresponse = '" + inSearchMonitoreoTO.getXmlResponse()+"'");
		}

		if (inSearchMonitoreoTO.getXmlRequest() != null ) {
			selectStatement.append(" AND xmlrequest = '" + inSearchMonitoreoTO.getXmlRequest()+"'");
		}

		if (inSearchMonitoreoTO.getIdSistema() != null ) {
			selectStatement.append(" AND idsistema = " + inSearchMonitoreoTO.getIdSistema());
		}

		if (inSearchMonitoreoTO.getIdServicio() != null ) {
			selectStatement.append(" AND idservicio = " + inSearchMonitoreoTO.getIdServicio());
		}

		if (inSearchMonitoreoTO.getIdTracking() != null ) {
			selectStatement.append(" AND idtracking = " + inSearchMonitoreoTO.getIdTracking());
		}

		if (inSearchMonitoreoTO.getStatus() != null ) {
			selectStatement.append(" AND status = '" + inSearchMonitoreoTO.getStatus()+"'");
		}

		if (inSearchMonitoreoTO.getIdSessionApp() != null ) {
			selectStatement.append(" AND idsessionapp = " + inSearchMonitoreoTO.getIdSessionApp());
		}

		if (inSearchMonitoreoTO.getIdSesionCliente() != null ) {
			selectStatement.append(" AND idsessioncliente = " + inSearchMonitoreoTO.getIdSesionCliente());
		}

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


	public boolean insertAll(List<MonitoreoTO> InsertMonitoreoTO) throws SQLException, Exception {

		PreparedStatement prepStmt = null;
		int rowCount = 0;
		for(int i=0;i<InsertMonitoreoTO.size();i++)
		try {

			prepStmt = conn.prepareStatement(SEGUIMIENTO_INSERT_SQL);
			MonitoreoTO inInsertMonitoreoTO=new MonitoreoTO(InsertMonitoreoTO.get(i));
			if ( inInsertMonitoreoTO.getUsuario() != null ) {
				prepStmt.setString(1, inInsertMonitoreoTO.getUsuario());
			} else {
				prepStmt.setNull(1, java.sql.Types.VARCHAR);
			}

			if ( inInsertMonitoreoTO.getDescripcionLarga() != null ) {
				prepStmt.setString(3, inInsertMonitoreoTO.getDescripcionLarga());
			} else {
				prepStmt.setNull(3, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getXmlResponse() != null ) {
				prepStmt.setString(4, inInsertMonitoreoTO.getXmlResponse());
			} else {
				prepStmt.setNull(4, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getXmlRequest() != null ) {
				prepStmt.setString(5, inInsertMonitoreoTO.getXmlRequest());
			} else {
				prepStmt.setNull(5, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getIdSistema() != null ) {
				prepStmt.setInt(6, inInsertMonitoreoTO.getIdSistema().intValue());
			} else {
				prepStmt.setNull(6, java.sql.Types.INTEGER);
			}
			if ( inInsertMonitoreoTO.getIdServicio() != null ) {
				prepStmt.setInt(7, inInsertMonitoreoTO.getIdServicio().intValue());
			} else {
				prepStmt.setNull(7, java.sql.Types.INTEGER);
			}

			if ( inInsertMonitoreoTO.getStatus() != null ) {
				prepStmt.setString(8, inInsertMonitoreoTO.getStatus());
			} else {
				prepStmt.setNull(8, java.sql.Types.VARCHAR);
			}
			if ( inInsertMonitoreoTO.getIdSessionApp() != null ) {
				prepStmt.setInt(9, inInsertMonitoreoTO.getIdSessionApp().intValue());
			} else {
				prepStmt.setNull(9, java.sql.Types.INTEGER);
			}
			if ( inInsertMonitoreoTO.getIdSesionCliente() != null ) {
				prepStmt.setInt(10, inInsertMonitoreoTO.getIdSesionCliente().intValue());
			} else {
				prepStmt.setNull(10, java.sql.Types.INTEGER);
			}
			rowCount = prepStmt.executeUpdate();
			if(rowCount == 0 ) {
				throw new Exception("Insert Error : " + inInsertMonitoreoTO);
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
			prepStmt.close();
		//	closeAll(conn, prepStmt, null);
		}

		return ( rowCount > 0 );

	}
	
}