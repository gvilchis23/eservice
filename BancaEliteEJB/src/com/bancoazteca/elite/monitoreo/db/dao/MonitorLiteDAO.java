package com.bancoazteca.elite.monitoreo.db.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bancoazteca.elite.util.PropertiesService;

public class MonitorLiteDAO {
	
	private static final String DB_NAME_MONITOREO = "monitoreo.sqlite";
	private static final String DB_NAME_MONITOR_SERVICE = "monitorService.sqlite";
	
	protected Connection getMonitoreoSqliteConnection()throws UnsupportedEncodingException, ClassNotFoundException,SQLException {
		PropertiesService propertiesReader=PropertiesService.getInstance();
		String path;
		Connection conn;
		
		path="jdbc:sqlite:"+propertiesReader.getPath()+ propertiesReader.getSistemaAmbiente() + DB_NAME_MONITOREO;
		
		System.out.println("path getSqliteConnection: " + path);
		Class.forName("org.sqlite.JDBC");
		conn=DriverManager.getConnection(path);
		conn.setAutoCommit(true);
		return conn;
	}
	
	protected Connection getMonitorServiceSqliteConnection()throws UnsupportedEncodingException, ClassNotFoundException,SQLException {
		PropertiesService propertiesReader=PropertiesService.getInstance();
		String path;
		Connection conn;
		
		path="jdbc:sqlite:"+propertiesReader.getPath()+ propertiesReader.getSistemaAmbiente() + DB_NAME_MONITOR_SERVICE;
		
		System.out.println("path getSqliteConnection: " + path);
		Class.forName("org.sqlite.JDBC");
		conn=DriverManager.getConnection(path);
		conn.setAutoCommit(true);
		return conn;
	}
	
	protected void closeSqliteConnection(Connection conn, ResultSet resultSet,PreparedStatement statement) {
		try {
			if(resultSet != null){
				resultSet.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(statement != null){
				statement.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
