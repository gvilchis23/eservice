/**
 * 
 */
package com.bancoazteca.elite.ejb.transferencias;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Paul Edgar Diaz Islas
 *
 */
public class DBUtils {

	public static void cerrarConexion(Connection connection){
		try {
			if(connection!=null)
				connection.close();
			connection=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cerrarResultado(ResultSet rs){
		try {
			if(rs!=null)
				rs.close();
			rs=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cerrarStatement(PreparedStatement ps){
		try {
			if(ps!=null)
				ps.close();
			ps=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cerrarCallable(CallableStatement cs){
		if(cs!=null)
			try {
				cs.close();
				cs=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
