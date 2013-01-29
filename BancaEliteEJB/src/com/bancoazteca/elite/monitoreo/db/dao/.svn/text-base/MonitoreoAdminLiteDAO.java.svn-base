package com.bancoazteca.elite.monitoreo.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MonitoreoAdminLiteDAO extends MonitorLiteDAO{
	public void insertaAdministrador(String usuario, String nombre, String contrasenia){
		try {
			Connection conn=getMonitorServiceSqliteConnection();
			conn.setAutoCommit(true);
			String sql="insert into Usuarios_registrados values(null,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.toLowerCase());
			statement.setString(2, contrasenia);
			statement.setString(3, nombre);
			statement.execute();
			closeSqliteConnection(conn, null, statement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MonitoreoAdministradorResponseTO obtieneUsuario(String usuario,String contrasenia){
		try{
			Connection conn=getMonitorServiceSqliteConnection();
			String sql="select * from Usuarios_Registrados where usuario=? and contrasenia=?";
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, usuario);
			statement.setString(2, contrasenia);
			statement.execute();
			ResultSet result=statement.getResultSet();
			if(result.next()){
				MonitoreoAdministradorResponseTO to=new MonitoreoAdministradorResponseTO();
				to.setId(result.getString("id"));
				to.setUsuario(result.getString("usuario"));
				to.setContrasenia(result.getString("contrasenia"));
				to.setNombre(result.getString("nombre"));
				return to;
			}
			closeSqliteConnection(conn, result, statement);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
