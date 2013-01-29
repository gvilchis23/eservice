package com.bancoazteca.elite.monitoreo.db.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MonitoreoLiteDAO extends MonitorLiteDAO{
	
	public ArrayList<ConsultaMonitoreoTO> obtieneUsuariosPorSession(){
		ArrayList<ConsultaMonitoreoTO> result=new ArrayList<ConsultaMonitoreoTO>();
		
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitoreoSqliteConnection();
			String sql="select lower(Usuarios.alias) as alias, Usuarios.nombre, Aplicaciones.descripcion, count(Usuarios.alias)  " +
					"from Usuarios " +
					"inner join Tracking on Tracking.idUsuario = Usuarios.id " +
					"inner join Aplicaciones on Tracking.idAplicacion = Aplicaciones.id " +
					"group by lower(Usuarios.alias) ,Aplicaciones.id";
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO to=new ConsultaMonitoreoTO();
				to.setNombre(resultSet.getString("nombre"));
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setTotalUsuarios(resultSet.getString("count(Usuarios.alias)"));
				result.add(to);
		}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeSqliteConnection(conn, resultSet, statement);
			conn=null; resultSet=null; statement=null;
		}
		return result;
	}

	

	
	
	public ArrayList<ConsultaMonitoreoTO> obtieneUsuariosPorSessionFecha(String fechaInicial, String fechaFinal){
		fechaInicial+=" 00:00:00";
		fechaFinal+=" 23:59:59";
		ArrayList<ConsultaMonitoreoTO> result=new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitoreoSqliteConnection();
			String sql="select lower(Usuarios.alias) as alias, upper(Usuarios.nombre) as nombre, Aplicaciones.descripcion, count(Usuarios.alias)  " +
					"from Usuarios " +
					"inner join Tracking on Tracking.idUsuario = Usuarios.id " +
					"inner join Aplicaciones on Tracking.idAplicacion = Aplicaciones.id " +
					"where strftime(\"%J\",?)>strftime(\"%J\",Tracking.fecha) AND strftime(\"%J\",?)<strftime(\"%J\",Tracking.fecha) " +
					"group by lower(Usuarios.alias),Aplicaciones.id";
			statement=conn.prepareStatement(sql);
			statement.setString(1, fechaFinal);
			statement.setString(2, fechaInicial);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO to=new ConsultaMonitoreoTO();
				to.setNombre(resultSet.getString("nombre"));
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setTotalUsuarios(resultSet.getString("count(Usuarios.alias)"));
				result.add(to);
		}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeSqliteConnection(conn, resultSet, statement);
			conn=null; resultSet=null; statement=null;
		}
		return result;
	}
	
	
	public ArrayList<ConsultaMonitoreoTO> obtieneUsuariosPorAplicacion(){
		ArrayList<ConsultaMonitoreoTO> result=new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitoreoSqliteConnection();
			String sql="select Aplicaciones.descripcion, count(Usuarios.alias) " +
					"from Aplicaciones " +
					"inner join Tracking on Tracking.idAplicacion = Aplicaciones.id " +
					"inner join Usuarios on Tracking.idUsuario= Usuarios.id " +
					"group by  Aplicaciones.id";
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO to=new ConsultaMonitoreoTO();
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setTotalUsuarios(resultSet.getString("count(Usuarios.alias)"));
				result.add(to);
		}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeSqliteConnection(conn, resultSet, statement);
			conn=null; resultSet=null; statement=null;
		}
		return result;
	}
	
	
	
	public ArrayList<ConsultaMonitoreoTO> obtieneUsuariosPorAplicacionFecha(String fechaInicial, String fechaFinal){
		fechaInicial+=" 00:00:00";
		fechaFinal+=" 23:59:59";
		ArrayList<ConsultaMonitoreoTO> result=new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitoreoSqliteConnection();
			String sql="select Aplicaciones.descripcion, count(Usuarios.alias) " +
					"from Aplicaciones " +
					"inner join Tracking on Tracking.idAplicacion = Aplicaciones.id " +
					"inner join Usuarios on Tracking.idUsuario= Usuarios.id " +
					"where strftime(\"%J\",?)>strftime(\"%J\",Tracking.fecha) AND strftime(\"%J\",?)<strftime(\"%J\",Tracking.fecha) " +
					"group by  Aplicaciones.id";
			statement=conn.prepareStatement(sql);
			statement.setString(1, fechaFinal);
			statement.setString(2, fechaInicial);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO to=new ConsultaMonitoreoTO();
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setTotalUsuarios(resultSet.getString("count(Usuarios.alias)"));
				result.add(to);
		}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeSqliteConnection(conn, resultSet, statement);
			conn=null; resultSet=null; statement=null;
		}
		return result;
	}
	
	
	
	public void insertaUsuario(String alias,String aplicacion, String nombre){
		int idUsuario;
		int idAplicacion;
 		Connection conn=null;
		try {
			conn = getMonitoreoSqliteConnection();
			idUsuario=getIdUsuario(conn, nombre, alias);
			idAplicacion=getIdAplicacion(conn,aplicacion);
			insertTracking(conn, idAplicacion, idUsuario);
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			closeSqliteConnection(conn, null, null);
			conn=null; 
		}
	}

	private void insertTracking(Connection conn,int idAplicacion, int idUsuario) throws SQLException{
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String cadenaFecha = formato.format(fechaActual);
		String sql="INSERT INTO Tracking VALUES (null,?,?,?)";
		PreparedStatement statement=conn.prepareStatement(sql);
		statement=conn.prepareStatement(sql);
		statement.setInt(1, idAplicacion);
		statement.setInt(2, idUsuario);
		statement.setString(3,cadenaFecha);
		statement.execute();
		closeSqliteConnection(null, null, statement);
	}
	
	private int getIdUsuario(Connection conn,String nombre, String alias) throws SQLException{
		int idUsuario;
		String sql="SELECT id FROM Usuarios WHERE lower(alias)=lower(?)";
		PreparedStatement statement=conn.prepareStatement(sql);
		statement.setString(1, alias);
		statement.execute();
		ResultSet resultSet=statement.getResultSet();
		if(resultSet.next()){
			idUsuario=resultSet.getInt("id");
		}else{
			sql="INSERT INTO Usuarios VALUES (null,?,lower(?))";
			statement=conn.prepareStatement(sql);
			statement.setString(1, nombre);
			statement.setString(2, alias);
			statement.execute();
			
			sql="SELECT last_insert_rowid()";
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			idUsuario=resultSet.getInt("last_insert_rowid()");
		}
		closeSqliteConnection(null, resultSet, statement);
		return idUsuario;
	}
	
	private int getIdAplicacion(Connection conn,String descripcion) throws SQLException {
		int idAplicacion=0;
		String sql="SELECT id FROM Aplicaciones WHERE descripcion=?";
		PreparedStatement statement=conn.prepareStatement(sql);
		statement.setString(1, descripcion);
		statement.execute();
		ResultSet resultSet=statement.getResultSet();
		resultSet.next();
		idAplicacion=resultSet.getInt("id");
		closeSqliteConnection(null, resultSet, statement);
		return idAplicacion;
	}
}
