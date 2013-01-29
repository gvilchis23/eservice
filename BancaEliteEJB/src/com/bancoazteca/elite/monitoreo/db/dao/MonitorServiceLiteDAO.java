package com.bancoazteca.elite.monitoreo.db.dao;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import com.bancoazteca.elite.beans.MonitoreoRequestTO;
import com.bancoazteca.elite.util.Validator;
import com.bancoazteca.elite.beans.ConsultaMonitoreoRequestTO;
import com.bancoazteca.elite.beans.DetalleMonitoreoTO;
import com.bancoazteca.elite.beans.UsuarioOperacionesTO;
import com.bancoazteca.elite.beans.UsuariosTO;

public class MonitorServiceLiteDAO extends MonitorLiteDAO{
	
	public Collection<ConsultaMonitoreoTO> getTotalUsuariosPorAplicacion(){
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, count(u.alias) as Total_Usuarios " +
						"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua " +
						"Where rua.idUsuario = u.id  AND " +
						" rua.idAplicacion = ca.id " +
						"Group by ca.descripcion ";
			System.out.println( "getTotalUsuariosPorAplicacion: " + sql );
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setTotalUsuarios(resultSet.getString("Total_Usuarios"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getTotalUsuariosPorAplicacionFecha(String fechaInicio, String fechaFin){
		System.out.println("******** getTotalUsuariosPorAplicacionFecha ");
		System.out.println(">>>>>fechaInicio: " + fechaInicio );
		System.out.println(">>>>>fechaFin: " + fechaFin );
		
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, count(u.alias) as Total_Usuarios " +
						"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua " +
						"Where rua.idUsuario = u.id  AND " +
						" rua.idAplicacion = ca.id  AND " +
						" rua.fechaHora BETWEEN date(?) AND date(?) " +
						"Group by ca.descripcion ";
			System.out.println( "getTotalUsuariosPorAplicacionFecha: " + sql );
			statement=conn.prepareStatement(sql);
			statement.setString(1, fechaInicio);
			statement.setString(2, fechaFin);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setTotalUsuarios(resultSet.getString("Total_Usuarios"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getUsuariosPorAplicacion(){
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario " +
						"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua " +
						"Where rua.idUsuario = u.id  AND " +
						" rua.idAplicacion = ca.id " +
						"Group by u.alias ,ca.id ";
			System.out.println( "getUsuariosPorAplicacion: " + sql );
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getUsuariosPorAplicacionFecha(String fechaInicio, String fechaFin){
		System.out.println("******** getUsuariosPorAplicacionFecha ");
		System.out.println(">>>>>fechaInicio: " + fechaInicio );
		System.out.println(">>>>>fechaFin: " + fechaFin );

		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario " +
						"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua " +
						"Where rua.idUsuario = u.id  AND " +
						" rua.idAplicacion = ca.id  AND " +
						" rua.fechaHora BETWEEN date(?) AND date(?) " +
						"Group by u.alias ,ca.id ";
			System.out.println( "getUsuariosPorAplicacionFecha: " + sql );
			statement=conn.prepareStatement(sql);
			statement.setString(1, fechaInicio);
			statement.setString(2, fechaFin);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getTrackingUsuariosPorAplicacion(){
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario, LOWER(co.descripcion) as Operacion_Realizada " +
						"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua, Catalogo_Operaciones co, Tracking tk " +
						"Where rua.idUsuario = u.id  AND " +
						" rua.idAplicacion = ca.id  AND " +
						" tk.idOperacion = co.id  AND " +
						" tk.idRelacion = rua.idRelacion ";
			System.out.println( "getTrackingUsuariosPorAplicacion: " + sql );
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				monitorTO.setTransaccion(resultSet.getString("Operacion_Realizada"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getTrackingUsuariosPorAplicacionFecha(String fechaInicio, String fechaFin){
		System.out.println("******** getTrackingUsuariosPorAplicacionFecha ");
		System.out.println(">>>>>fechaInicio: " + fechaInicio );
		System.out.println(">>>>>fechaFin: " + fechaFin );
		
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario, LOWER(co.descripcion) as Operacion_Realizada " +
						"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua, Catalogo_Operaciones co, Tracking tk " +
						"Where rua.idUsuario = u.id  AND " +
						" rua.idAplicacion = ca.id  AND " +
						" tk.idOperacion = co.id  AND " +
						" tk.idRelacion = rua.idRelacion  AND " +
						" tk.fechaHoraOperacion BETWEEN date(?) AND date(?) ";
			System.out.println( "getTrackingUsuariosPorAplicacionFecha: " + sql );
			statement=conn.prepareStatement(sql);
			statement.setString(1, fechaInicio);
			statement.setString(2, fechaFin);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				monitorTO.setTransaccion(resultSet.getString("Operacion_Realizada"));
				result.add(monitorTO);
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
		
	public Collection<ConsultaMonitoreoTO> getTotalOperacionesUsuario(){
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario, count(u.alias) as Numero_Operaciones " +
					"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua, Tracking tk " +
					"Where rua.idUsuario = u.id  AND " +
					" rua.idAplicacion = ca.id  AND " +
					" tk.idRelacion = rua.idRelacion " +
					" Group by u.alias ,ca.id ";
			System.out.println( "getTotalOperacionesUsuario: " + sql );
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				monitorTO.setNumeroOperaciones(resultSet.getString("Numero_Operaciones"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getTotalOperacionesUsuarioFecha( String fechaInicio, String fechaFin ){
		System.out.println("******** getTotalOperacionesUsuarioFecha ");
		System.out.println(">>>>>fechaInicio: " + fechaInicio );
		System.out.println(">>>>>fechaFin: " + fechaFin );

		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario, count(u.alias) as Numero_Operaciones " +
					"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua, Tracking tk " +
					"Where rua.idUsuario = u.id  AND " +
					" rua.idAplicacion = ca.id  AND " +
					" tk.idRelacion = rua.idRelacion AND " +
					" tk.fechaHoraOperacion BETWEEN date(?) AND date(?) " +
					" Group by u.alias ,ca.id ";
			System.out.println( "getTotalOperacionesUsuarioFecha: " + sql );
			statement=conn.prepareStatement(sql);
			statement.setString(1, fechaInicio);
			statement.setString(2, fechaFin);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				monitorTO.setNumeroOperaciones(resultSet.getString("Numero_Operaciones"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getTotalConexionesUsuario(){
		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario, count(u.alias) as Numero_Conexiones " +
					"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua, Tracking tk, Catalogo_Operaciones co " +
					"Where rua.idUsuario = u.id  AND " +
					" rua.idAplicacion = ca.id  AND " +
					" tk.idRelacion = rua.idRelacion AND " +
					" tk.idOperacion = co.id  AND " +
					" co.descripcion like 'login%' " +
					"Group by u.alias ,ca.id ";
			System.out.println( "getTotalConexionesUsuario: " + sql );
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				monitorTO.setNumeroConexiones(resultSet.getString("Numero_Conexiones"));
				result.add(monitorTO);
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
	
	public Collection<ConsultaMonitoreoTO> getTotalConexionesUsuarioFecha( String fechaInicio, String fechaFin ){
		System.out.println("******** getTotalConexionesUsuarioFecha ");
		System.out.println(">>>>>fechaInicio: " + fechaInicio );
		System.out.println(">>>>>fechaFin: " + fechaFin );

		Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="Select LOWER(ca.descripcion) as Aplicacion, LOWER(u.nombre) as Usuario, count(u.alias) as Numero_Conexiones " +
					"From Catalogo_Aplicaciones ca, Usuarios u, Relacion_Usuario_Aplicacion rua, Tracking tk, Catalogo_Operaciones co " +
					"Where rua.idUsuario = u.id  AND " +
					" rua.idAplicacion = ca.id  AND " +
					" tk.idRelacion = rua.idRelacion AND " +
					" tk.idOperacion = co.id  AND " +
					" co.descripcion like 'login%'  AND " +
					" tk.fechaHoraOperacion BETWEEN date(?) AND date(?) " +
					"Group by u.alias ,ca.id ";
			System.out.println( "getTotalConexionesUsuario: " + sql );
			statement=conn.prepareStatement(sql);
			statement.setString(1, fechaInicio);
			statement.setString(2, fechaFin);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
				monitorTO.setAplicacion(resultSet.getString("Aplicacion"));
				monitorTO.setNombre(resultSet.getString("Usuario"));
				monitorTO.setNumeroConexiones(resultSet.getString("Numero_Conexiones"));
				result.add(monitorTO);
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
	
	/*
	public ArrayList<MonitorResultTO> obtieneUsuariosPorAplicacion(){
		ArrayList<MonitorResultTO> result=new ArrayList<MonitorResultTO>();
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="select Aplicaciones.descripcion, count(Usuarios.alias) " +
					"from Aplicaciones " +
					"inner join Tracking on Tracking.idAplicacion = Aplicaciones.id " +
					"inner join Usuarios on Tracking.idUsuario= Usuarios.id " +
					"group by  Aplicaciones.id";
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				MonitorResultTO to=new MonitorResultTO();
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setNumeroSesiones(resultSet.getString("count(Usuarios.alias)"));
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
		
	
	public ArrayList<MonitorResultTO> obtieneUsuariosPorAplicacionFecha(String fechaInicial, String fechaFinal){
		fechaInicial+=" 00:00:00";
		fechaFinal+=" 23:59:59";
		ArrayList<MonitorResultTO> result=new ArrayList<MonitorResultTO>();
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitorServiceSqliteConnection();
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
				MonitorResultTO to=new MonitorResultTO();
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setNumeroSesiones(resultSet.getString("count(Usuarios.alias)"));
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
	
	
	public ArrayList<MonitorResultTO> obtieneUsuariosPorSession(){
		ArrayList<MonitorResultTO> result=new ArrayList<MonitorResultTO>();
		
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="select lower(Usuarios.alias) as alias, Usuarios.nombre, Aplicaciones.descripcion, count(Usuarios.alias)  " +
					"from Usuarios " +
					"inner join Tracking on Tracking.idUsuario = Usuarios.id " +
					"inner join Aplicaciones on Tracking.idAplicacion = Aplicaciones.id " +
					"group by lower(Usuarios.alias) ,Aplicaciones.id";
			statement=conn.prepareStatement(sql);
			statement.execute();
			resultSet=statement.getResultSet();
			while(resultSet.next()){
				MonitorResultTO to=new MonitorResultTO();
				to.setNombre(resultSet.getString("nombre"));
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setNumeroSesiones(resultSet.getString("count(Usuarios.alias)"));
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

	

	
	
	public ArrayList<MonitorResultTO> obtieneUsuariosPorSessionFecha(String fechaInicial, String fechaFinal){
		fechaInicial+=" 00:00:00";
		fechaFinal+=" 23:59:59";
		ArrayList<MonitorResultTO> result=new ArrayList<MonitorResultTO>();
		Connection conn=null;
		ResultSet resultSet=null;
		PreparedStatement statement=null;
		try {
			conn = getMonitorServiceSqliteConnection();
			String sql="select lower(Usuarios.alias) as alias, LOWER(Usuarios.nombre) as nombre, Aplicaciones.descripcion, count(Usuarios.alias)  " +
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
				MonitorResultTO to=new MonitorResultTO();
				to.setNombre(resultSet.getString("nombre"));
				to.setAplicacion(resultSet.getString("descripcion"));
				to.setNumeroSesiones(resultSet.getString("count(Usuarios.alias)"));
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
	
	*/
	public void insertaUsuarios(UsuariosTO usuarios) {
		DataSource dataSource =null;
		Connection conn=null;
		PreparedStatement statement = null;
		
		
		try {
			Context contexto = new InitialContext();
			dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
			conn=dataSource.getConnection();
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
			String sql="CALL EBANKING.insertUsuarios(?,?,?,?)";
			statement=conn.prepareCall(sql);
			statement.setString(1, usuarios.getUserName());
			statement.setString(2, usuarios.getAplicacion());
			statement.setString(3, usuarios.getNombreCompleto());
			statement.setString(4, usuarios.getUserAlnova());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} finally{
			try {
				if(statement!=null){
					statement.close();
						
				}if(conn!=null){
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//closeSqliteConnection(dataSource, null, statement);
			
		}
		
	}
	
	public void insertaUsuarioOperacion(UsuarioOperacionesTO usuarioOperacion){
		DataSource dataSource =null;
		Connection conn=null;
		PreparedStatement statement = null;
		
		
		try {
			
			Context contexto = new InitialContext();
			dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
			conn=dataSource.getConnection();
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
			String sql="CALL EBANKING.insertUsuariosOp(?,?,?)";
			statement=conn.prepareCall(sql);
			statement.setInt(1, usuarioOperacion.getIdUsuarioOperacion());
			statement.setString(2, usuarioOperacion.getIdUsuario());
			statement.setString(3, usuarioOperacion.getOperacion());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} finally{
			try {
				if(statement!=null){
					statement.close();
				}if(conn!=null){
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void insertaOperacion(MonitoreoRequestTO requestTO){
		int idRelacion;
		int idTransaccion;

		try {
			idRelacion = insertaRelacionUsuarioAplicacion( requestTO );
			idTransaccion = getIdTransaccion( requestTO.getTransaccion() );

			if( idRelacion < 0 || idTransaccion < 0 ){
				throw new SQLException();
			}
			
			requestTO.setIdTransaccion(idTransaccion);
			requestTO.setIdRelacionUsuarioAplicacion(idRelacion);
			
			insertTracking(requestTO);

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	

public void insertXml(DetalleMonitoreoTO detalle){
	
	DataSource dataSource =null;
	Connection conn = null;
	PreparedStatement statement = null;
	
	
	try {
		
			Context contexto = new InitialContext();
			 dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
		conn=dataSource.getConnection();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
		String sql="CALL EBANKING.insertTracking(?,?,?,?)";
		statement=conn.prepareCall(sql);
		statement.setInt(1,detalle.getIdDetalle());
		statement.setString(2, detalle.getIdUsuarioOperacion()); 
		statement.setInt(3, detalle.getCodigoOperacion());
		statement.setString(4, detalle.getError());
		statement.execute();
		
		
		

	} catch (SQLException e) {
		e.printStackTrace();
		
	} catch (NamingException e) {
		e.printStackTrace();
	} finally{
		try {
			if(statement!=null){
				statement.close();
			}if(conn!=null){
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

public void insertMapa(Integer idMapa,Integer idTracking,String campo,String valor) {
	
	DataSource dataSource = null;
	Connection conn=null;
	PreparedStatement statement = null;
	
	
	try {
		
			Context contexto = new InitialContext();
			dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
		conn=dataSource.getConnection();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
		String sql="CALL EBANKING.insertTrackingDe(?,?,?,?)";
		statement=conn.prepareCall(sql);
		statement.setInt(1,idMapa);
		statement.setInt(2, idTracking); 
		statement.setString(3, campo); 
		statement.setString(4, valor); 
		statement.execute();
		
		
		

	} catch (SQLException e) {
		e.printStackTrace();
		
	} catch (NamingException e) {
		e.printStackTrace();
	} finally{
		try {
			if(statement!=null){
				statement.close();
			}if(conn!=null){
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}


public void insertError(UsuarioOperacionesTO usuarioOperacion, DetalleMonitoreoTO detalle) {
	
	DataSource dataSource = null;
	Connection conn=null;
	PreparedStatement statement = null;
	
	
	try {
		
			Context contexto = new InitialContext();
			dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
		conn=dataSource.getConnection();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
		String sql="CALL EBANKING.insertError(?,?,?,?,?,?)";
		statement=conn.prepareCall(sql);
		statement.setInt(1,usuarioOperacion.getIdUsuarioOperacion());
		statement.setString(2, usuarioOperacion.getIdUsuario()); 
		statement.setString(3, usuarioOperacion.getOperacion()); 
		statement.setInt(4, detalle.getIdDetalle());
		//statement.setString(5, detalle.getIdUsuarioOperacion()); 
		statement.setInt(5, detalle.getCodigoOperacion()); 
		statement.setString(6, detalle.getError()); 
		statement.execute();
		
		
		

	} catch (SQLException e) {
		e.printStackTrace();
		
	} catch (NamingException e) {
		e.printStackTrace();
	} finally{
		try {
			if(statement!=null){
				statement.close();
			}if(conn!=null){
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}



public Integer getIdUsuarioMonitoreo(String userName,String aplicacion) {
	
	 DataSource dataSource =null;
	 Connection conn=null;
	 CallableStatement call =null;
	Integer idUsuario=0;
	ResultSet rs=null;
	
	try {
		
			Context contexto = new InitialContext();
			 dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
		conn=dataSource.getConnection();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
		
		call = conn.prepareCall("{CALL EBANKING.getIdUsuario(?,?,?)}");
		call.setObject(1, userName);
		call.setObject(2, aplicacion);
		call.registerOutParameter(3, OracleTypes.CURSOR);
		call.execute();
		rs=(ResultSet)call.getObject(3);
		if(rs!=null){
			while(rs.next()){
				idUsuario=Integer.valueOf(rs.getString("IDUSUARIO"));
			}
		}else{
			idUsuario=0;
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
		
	} catch (NamingException e) {
		e.printStackTrace();
	} finally{
		try {
			if(rs!=null){
			rs.close();
			}if(call!=null){
				call.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	return idUsuario;
	
}


public Integer getIdTracking(String idUsuarioOperacion){
	
	 DataSource dataSource =null;
	 Connection conn=null;
	 CallableStatement call =null;
	 //Statement statement=null;
	Integer idTracking=0;
	ResultSet rs=null;
	
	try {
		
			Context contexto = new InitialContext();
			dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
		conn=dataSource.getConnection();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
		//call = conn.prepareCall("{CALL EBANKING.getIdTracking(?,?)}");
		//call = conn.prepareCall("{?=call EBANKING.getIdTracking(?)}");
		call = conn.prepareCall("{call EBANKING.getIdTracking(?,?)}");
		call.setObject(1, idUsuarioOperacion);
		call.registerOutParameter(2, OracleTypes.CURSOR);
		call.execute();
		rs=(ResultSet)call.getObject(2);
		if(rs!=null){
			while(rs.next()){
				idTracking=Integer.valueOf(rs.getString("idTRACKING"));
			}
		}else{
			idTracking=0;
		}
		
	
	} catch (SQLException e) {
		e.printStackTrace();
		
	} catch (NamingException e) {
		e.printStackTrace();
	} finally{
		try {
			if(rs!=null){
				rs.close();
			}if(call!=null){
				call.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	return idTracking;
	
}



public Integer getIdUsuarioOperacion(String userName,String aplicacion){
	
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	Integer idUsuarioOperacion=0;
	ResultSet rs=null;
	
	try {
		
			Context contexto = new InitialContext();
			dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
		conn=dataSource.getConnection();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//connect = DriverManager.getConnection("jdbc:oracle:thin:@10.50.70.125:1521:EBANKGUA","MONITOR_SERVICE","MONITOR_SERVICE");  
		call = conn.prepareCall("{CALL EBANKING.getIdUsuarioOper(?,?,?)}");
		call.setObject(1, userName);
		call.setObject(2, aplicacion);
		call.registerOutParameter(3, OracleTypes.CURSOR);
		call.execute();
		rs=(ResultSet)call.getObject(3);
		if(rs!=null){
			while(rs.next()){
				idUsuarioOperacion=Integer.valueOf(rs.getString("IDUSUARIOOPERACION"));
			}
		}else{
			idUsuarioOperacion=0;
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
		
	} catch (NamingException e) {
		e.printStackTrace();
	} finally{
		try {
			if(rs!=null){
				rs.close();
			}if(call!=null){
				call.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}

	return idUsuarioOperacion;
	
}

public Collection<ConsultaMonitoreoTO> getTotalUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getAppUser(?,?)}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.setString(2, requestTO.getConsultaFechaInicio());
	call.setString(3, requestTO.getConsultaFechaFin());
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setTotalUsuarios(rs.getString("total"));
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

public Collection<ConsultaMonitoreoTO> getAllTotalUsuariosAplicacion(){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getAllAppUser}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setTotalUsuarios(rs.getString("total"));
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

public Collection<ConsultaMonitoreoTO> getUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getLoginUser(?,?)}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.setString(2, requestTO.getConsultaFechaInicio());
	call.setString(3, requestTO.getConsultaFechaFin());
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setNombre(rs.getString("nombrecompleto"));
			monitorTO.setNumeroConexiones(rs.getString("total"));
			
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

public Collection<ConsultaMonitoreoTO> getAllUsuariosAplicacion(){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getAllLoginUser}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setNombre(rs.getString("nombrecompleto"));
			monitorTO.setNumeroConexiones(rs.getString("total"));
			
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

public Collection<ConsultaMonitoreoTO> getTotalOperacionesByUsuarios(ConsultaMonitoreoRequestTO requestTO){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getTotalOper(?,?)}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.setString(2, requestTO.getConsultaFechaInicio());
	call.setString(3, requestTO.getConsultaFechaFin());
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setNombre(rs.getString("nombrecompleto"));
			monitorTO.setNumeroOperaciones(rs.getString("total"));
			
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

public Collection<ConsultaMonitoreoTO> getAllTotalOperaByUser(){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getAllTotalOper}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setNombre(rs.getString("nombrecompleto"));
			monitorTO.setNumeroOperaciones(rs.getString("total"));
			
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}


public Collection<ConsultaMonitoreoTO> getTrackingUsuariosAplicacion(ConsultaMonitoreoRequestTO requestTO){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getTrackUser(?,?)}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.setString(2, requestTO.getConsultaFechaInicio());
	call.setString(3, requestTO.getConsultaFechaFin());
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setNombre(rs.getString("nombrecompleto"));
			monitorTO.setTransaccion(rs.getString("operacion"));
			
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

public Collection<ConsultaMonitoreoTO> getAllTrackingUserAplica(){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getAllTrackUser}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setNombre(rs.getString("nombrecompleto"));
			monitorTO.setTransaccion(rs.getString("operacion"));
			
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

public Collection<ConsultaMonitoreoTO> getOperacionMonto(ConsultaMonitoreoRequestTO requestTO){
	Collection<ConsultaMonitoreoTO> result = new ArrayList<ConsultaMonitoreoTO>();
	DataSource dataSource =null;
	Connection conn=null;
	CallableStatement call =null;
	ResultSet rs= null;
	try {
		Context contexto = new InitialContext();
		dataSource = ((DataSource) contexto.lookup("java:/monitoreoDS"));
	conn=dataSource.getConnection();
	call = conn.prepareCall("{?=call EBANKING.getOperMonto(?,?)}");
	call.registerOutParameter(1, OracleTypes.CURSOR);
	call.setString(2, requestTO.getConsultaFechaInicio());
	call.setString(3, requestTO.getConsultaFechaFin());
	call.execute();
	rs=(ResultSet)call.getObject(1);
		while(rs.next()){
			ConsultaMonitoreoTO monitorTO =new ConsultaMonitoreoTO();
			monitorTO.setAplicacion(rs.getString("Aplicacion"));
			monitorTO.setNombre(rs.getString("nombrecompleto"));
			monitorTO.setTransaccion(rs.getString("operacion"));
			monitorTO.setValor(rs.getString("monto"));
			monitorTO.setFecha(rs.getString("fecha_operacion"));
			
			result.add(monitorTO);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(call!=null){
				call.close();
			}if(rs!=null){
				rs.close();
			}if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	return result;
}

//	private void insertTracking(Connection conn,int idAplicacion, int idUsuario) throws SQLException{
//		Date fechaActual = new Date();
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String cadenaFecha = formato.format(fechaActual);
//		String sql="INSERT INTO Tracking VALUES (null,?,?,?)";
//		PreparedStatement statement=conn.prepareStatement(sql);
//		statement=conn.prepareStatement(sql);
//		statement.setInt(1, idAplicacion);
//		statement.setInt(2, idUsuario);
//		statement.setString(3,cadenaFecha);
//		statement.execute();
//		closeSqliteConnection(null, null, statement);
//	}
	
//	private void usuarioAplicacion(Connection conn, MonitoreoRequestTO requestTO)throws SQLException  {
//		
//
//	}
	
	private void insertTracking(MonitoreoRequestTO requestTO) throws SQLException{
	
		Connection connect=null;
		PreparedStatement statement = null;
		
		try {
			connect = getMonitorServiceSqliteConnection();
			String sql="INSERT INTO Tracking  ( idRelacion, idOperacion, importeOperacion, Estatus, descripcionEstatus, idSesion, fechaHoraOperacion ) VALUES (lower(?),lower(?),lower(?),lower(?),lower(?),lower(?),datetime('now','localtime'))";
			statement=connect.prepareStatement(sql);
			statement.setInt(1, requestTO.getIdRelacionUsuarioAplicacion());
			statement.setInt(2, requestTO.getIdTransaccion());
			statement.setDouble(3, requestTO.getImporte());
			statement.setString(4,requestTO.getEstatusTransaccion());
			statement.setString(5, requestTO.getDescripcionEstatus());
			statement.setString(6, requestTO.getIdSesion());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			closeSqliteConnection(connect, null, statement);
			connect=null; 
		}
		
	}
	
	private int getIdUsuario(String nombre, String alias) throws SQLException{
		int idUsuario = -1;
		
		Connection connect=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connect = getMonitorServiceSqliteConnection();
			String sql="SELECT id FROM Usuarios WHERE lower(alias) = lower(?)";
			statement=connect.prepareStatement(sql);
			statement.setString(1, alias);
			statement.execute();
			resultSet=statement.getResultSet();
			if(resultSet.next()){
				idUsuario=resultSet.getInt("id");
			}
			if(Validator.isEmptyData( String.valueOf( idUsuario ) ) || idUsuario == -1 ){
				sql="INSERT INTO Usuarios (nombre, alias) VALUES (lower(?),lower(?))";
				statement=connect.prepareStatement(sql);
				statement.setString(1, nombre);
				statement.setString(2, alias);
				statement.execute();
				
				sql="SELECT last_insert_rowid()";
				statement=connect.prepareStatement(sql);
				statement.execute();
				resultSet=statement.getResultSet();
				idUsuario=resultSet.getInt("last_insert_rowid()");
								
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			closeSqliteConnection(connect, resultSet, statement);
			connect=null; 
		}
			
		return idUsuario;
	}
	

	private int getIdAplicacion(String aplicacion) throws SQLException {
		int idAplicacion=0;
		Connection connect=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connect = getMonitorServiceSqliteConnection();
			String sql="SELECT id FROM Catalogo_Aplicaciones WHERE lower(descripcion)=lower(?)";
			statement=connect.prepareStatement(sql);
			statement.setString(1, aplicacion);
			statement.execute();
			resultSet=statement.getResultSet();
			idAplicacion=resultSet.getInt("id");
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			closeSqliteConnection(connect, resultSet, statement);
			connect=null; 
		}
		
		return idAplicacion;
	}
	
	
	private int getIdTransaccion(String transaccion) throws SQLException {
		Connection connect=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		int idNomTrans = -1;
		
		try {
			connect = getMonitorServiceSqliteConnection();
			String sql="SELECT id FROM Catalogo_Operaciones WHERE lower(descripcion)=lower(?)";
			statement=connect.prepareStatement(sql);
			statement.setString(1, transaccion);
			statement.execute();
			resultSet=statement.getResultSet();
			resultSet.next();
			idNomTrans=resultSet.getInt("id");
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{			
			closeSqliteConnection(connect, resultSet, statement);
			connect=null; 
		}

		return idNomTrans;
	}
	
	private int insertaRelacionUsuarioAplicacion(MonitoreoRequestTO requestTO) throws SQLException {
		
		Connection connect=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		int idRelacion = -1;
		
		try {
			int idUsuario=getIdUsuario(requestTO.getNombre(), requestTO.getUsuario());
			int idAplicacion=getIdAplicacion(requestTO.getAplicacion());
			
			if( idUsuario < 0 || idAplicacion < 0 ){
				throw new SQLException();
			}
			
			connect = getMonitorServiceSqliteConnection();
			String sql="SELECT idRelacion FROM Relacion_Usuario_Aplicacion WHERE idUsuario = ? and idAplicacion = ?";
			statement=connect.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			statement.setInt(2, idAplicacion);
			statement.execute();
			resultSet=statement.getResultSet();
			if(resultSet.next()){
				idRelacion=resultSet.getInt("idRelacion");
			}
			if(Validator.isEmptyData( String.valueOf( idRelacion ) ) || idRelacion == -1 ){
				sql="INSERT INTO Relacion_Usuario_Aplicacion (idUsuario, idAplicacion, fechaHora) VALUES (lower(?),lower(?),datetime('now','localtime'))";
				statement=connect.prepareStatement(sql);
				statement.setString( 1, String.valueOf( idUsuario ) );
				statement.setString( 2, String.valueOf( idAplicacion ) );
				statement.execute();
				
				sql="SELECT last_insert_rowid()";
				statement=connect.prepareStatement(sql);
				statement.execute();
				resultSet = statement.getResultSet();

				idRelacion = resultSet.getInt("last_insert_rowid()");
					
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{			
			closeSqliteConnection(connect, resultSet, statement);
			connect=null; 
		}
				
		return idRelacion;
	}
		
}
