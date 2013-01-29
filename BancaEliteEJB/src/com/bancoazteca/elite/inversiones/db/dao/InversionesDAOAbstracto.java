package com.bancoazteca.elite.inversiones.db.dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.ConciliacionEliteResponseTO;
import com.bancoazteca.elite.db.JDNIConnection;
import com.bancoazteca.elite.db.beans.AltaClienteRequestTO;
import com.bancoazteca.elite.db.beans.InsertaCompraInversionTO;
import com.bancoazteca.elite.db.beans.InsertaPlazoTasaRequesTO;
import com.bancoazteca.elite.db.beans.InsertaTransaccionAlnovaTO;
import com.bancoazteca.elite.db.beans.InsertaTransaccionTO;
import com.bancoazteca.elite.ejb.inversiones.InversionesGenericException;
import com.bancoazteca.elite.ejb.inversiones.beans.InversionesExceptionTO;
import com.bancoazteca.elite.ejb.inversiones.beans.PlazoTasaTO;
import com.bancoazteca.elite.ejb.inversiones.utils.InversionesConstantes;
import com.bancoazteca.elite.util.Formatter;
import com.bancoazteca.elite.util.PropertiesService;


public class InversionesDAOAbstracto  implements InversionesConstantes {
	
	private Logger log=Logger.getLogger(InversionesDAOAbstracto.class);
	private final JDNIConnection connection=new JDNIConnection();
	public String USUARIO_MODIFICO_BD;

	public int busquedaCliente(String idAlnova) throws InversionesGenericException{

		Connection conn= null;
		CallableStatement statement= null;
		
		Integer idCliente = -1;

		try {
			conn = connection.getConnection();
			String sql = obtenerFirmaFuncion("busqueda_cliente(?)");
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			statement.setString(2, idAlnova);
			
			statement.execute();
			idCliente = ( Integer ) statement.getObject(1);
			
			
		}catch (Exception e) {
			log.error("Error en busquedaCliente: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. BusquedaCliente");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		return idCliente;
	}
	
	
	public int busquedaClienteSimple(String idAlnova) throws InversionesGenericException{

		Connection conn= null;
		CallableStatement statement= null;
		
		Integer idCliente = -1;

		try {
			conn = connection.getConnection();
			String sql = obtenerFirmaFuncion("busqueda_cliente_simple(?)");
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			statement.setString(2, idAlnova);
			
			statement.execute();
			idCliente = ( Integer ) statement.getObject(1);
			
			
		}catch (Exception e) {
			log.error("Error en busquedaClienteSimple: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. BusquedaCliente");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		return idCliente;
	}

	public int insertaMonitoreoPlazoTasa(InsertaPlazoTasaRequesTO insertaPlazoTasa) throws InversionesGenericException{
	
		Connection conn= null;
		CallableStatement statement= null;
		
		log.info(toString(insertaPlazoTasa));
		
		int exito=0;
		
		try{
			
			String sql = obtenerFirmaFuncion("INSERTAR_MONITOREO_TASA_PLAZO(?,?,?,?,?)");
			
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1,OracleTypes.INTEGER);
			statement.setString(2,insertaPlazoTasa.getTasa());
			statement.setString(3,insertaPlazoTasa.getPlazo());
			statement.setString(4,insertaPlazoTasa.getFecha());
			statement.setString(5,insertaPlazoTasa.getHora());
			statement.setString(6,USUARIO_MODIFICO_BD);
			statement.execute();
			exito = (Integer) statement.getObject(1);
			
			sql = "commit";
			statement=conn.prepareCall(sql);
			statement.execute();
			
		}catch (Exception e) {
			log.error("Error en insertaMonitoreoPlazoTasa: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. monitoreo tasa,plazo");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}	
		return exito;
	}
	
	
	public  int altaCliente(AltaClienteRequestTO altaClienteRequestTO) throws InversionesGenericException{
		
		Connection conn= null;
		CallableStatement statement= null;
		
		log.info(toString(altaClienteRequestTO));
		
		int exito=0;
		
		try {
			
			String sql = obtenerFirmaFuncion("INSERTAR_CLIENTE(?,?,?,?,?,?,?)");
			
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1,OracleTypes.INTEGER);
			
			statement.setString(2,altaClienteRequestTO.getIdAlnova());
			statement.setString(3,altaClienteRequestTO.getNombre());
			statement.setString(4,altaClienteRequestTO.getApellidoPaterno());
			statement.setString(5,altaClienteRequestTO.getApellidoMaterno());
			statement.setString(6,altaClienteRequestTO.getStatusActivo());
			statement.setString(7,altaClienteRequestTO.getCurp());
			statement.setString(8,USUARIO_MODIFICO_BD);
			statement.execute();
			exito = (Integer) statement.getObject(1);
			
			sql = "commit";
			statement=conn.prepareCall(sql);
			statement.execute();
			
		}catch (Exception e) {
			log.error("Error en altaCliente: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. Alta cliente");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}	
		return exito;
	}

	protected int insertaTransaccionAlnova(InsertaTransaccionAlnovaTO insertaTransaccionAlnovaTO) throws InversionesGenericException{
		Connection conn= null;
		CallableStatement statement= null;
		
		log.info(toString(insertaTransaccionAlnovaTO));
		
		int exito=0;
		
		try {
			
			String sql = obtenerFirmaFuncion("INSERTAR_TRANSACCIONES_ALNOVA(?,?,?,?,?)");
			
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1,OracleTypes.INTEGER);
			
			statement.setString(2,insertaTransaccionAlnovaTO.getIdTransaccion());
			statement.setString(3,insertaTransaccionAlnovaTO.getCodigoOperacion());
			statement.setString(4,insertaTransaccionAlnovaTO.getFolioOperacion());
			statement.setString(5,"*");
			statement.setString(6,USUARIO_MODIFICO_BD);
			
			statement.execute();
			exito = (Integer) statement.getObject(1);
			
			sql = "commit";
			statement=conn.prepareCall(sql);
			statement.execute();
			
		}catch (Exception e) {
			log.error("Error en insertaTransaccionAlnova: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. Retencion");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}	
		return exito;
	}

	protected int InsertaCompraInversion(InsertaCompraInversionTO compraInversionTO) throws InversionesGenericException{
		Connection conn= null;
		CallableStatement statement= null;
		
		log.info(toString(compraInversionTO));
		
		int exito=0;
		
		try {
			
			String sql = obtenerFirmaFuncion("INSERTAR_COMPRA_INVERSIONES(?,?,?,?,?,?,?,?)");
			
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1,OracleTypes.INTEGER);
			
			statement.setString(2,compraInversionTO.getFolioOperacion());
			statement.setString(3,compraInversionTO.getMontoInversion());
			statement.setString(4,compraInversionTO.getPlazo());
			statement.setString(5,compraInversionTO.getTasa());
			statement.setInt(6,Integer.parseInt(compraInversionTO.getIdTransaccion()));
			statement.setInt(7,Integer.parseInt(compraInversionTO.getIdTransaccionAlnova()));
			statement.setString(8,compraInversionTO.getNumeroContrato());
			statement.setString(9,USUARIO_MODIFICO_BD);
			
			statement.execute();
			exito = (Integer) statement.getObject(1);
			
			sql = "commit";
			statement=conn.prepareCall(sql);
			statement.execute();
			
		}catch (Exception e) {
			log.error("Error en InsertaCompraInversion: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. Inversion");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}	
		return exito;
	}
	
	public int busquedaEmpresa(String empresa) throws InversionesGenericException{

		Connection conn= null;
		CallableStatement statement= null;
		int idEmpresa=0;
		try {
			
			String sql = obtenerFirmaFuncion("BUSQUEDA_CATALOGO_EMPRESAS(?)");
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			statement.setString(2,empresa);
			statement.execute();
			idEmpresa = ( Integer ) statement.getObject(1);
			
		}catch (Exception e) {
			log.error("Error en busquedaEmpresa: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. Busqueda Empresa");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		return idEmpresa;
	}
	
	public int busquedaOperacion(String operacion) throws InversionesGenericException{

		Connection conn= null;
		CallableStatement statement= null;

		int idOperacion=0;
		
		try {
			
			String sql = obtenerFirmaFuncion("BUSQUEDA_CATALOGO_OPERACIONES(?)");
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			statement.setString(2,operacion);
			statement.execute();
			idOperacion = ( Integer ) statement.getObject(1);
			
		}catch (Exception e) {
			log.error("Error en busquedaOperacion: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. Busqueda opreacion");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		return idOperacion;
	}
	
	public int insertaTransaccion(InsertaTransaccionTO insertaTransaccionTO) throws InversionesGenericException{
	
		Connection conn= null;
		CallableStatement statement= null;

		log.info(toString(insertaTransaccionTO));
		
		int idOperacion=0;
		
		try {
			
			
			String sql = obtenerFirmaFuncion("INSERTAR_TRANSACCION_INVERSION(?,?,?,?,?,?,?,?,?,?)");
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			
			statement.setString(2,insertaTransaccionTO.getStatus());
			statement.setString(3,insertaTransaccionTO.getFechaOperacion());
			statement.setString(4,insertaTransaccionTO.getPeticion());
			statement.setString(5,insertaTransaccionTO.getRespuesta());
			statement.setInt(6,Integer.parseInt(insertaTransaccionTO.getIdCliente()));
			statement.setInt(7,Integer.parseInt(insertaTransaccionTO.getIdOperacion()));
			statement.setInt(8,Integer.parseInt(insertaTransaccionTO.getIdEmpresa()));
			statement.setString(9,insertaTransaccionTO.getNombreAplicacion());
			statement.setString(10,insertaTransaccionTO.getNumeroCuenta());
			statement.setString(11,USUARIO_MODIFICO_BD);
			statement.execute();
			idOperacion = ( Integer ) statement.getObject(1);
			
			sql = "commit";
			statement=conn.prepareCall(sql);
			statement.execute();
			
		}catch (Exception e) {
			log.error("Error en insertaTransaccion: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. Busqueda opreacion");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		return idOperacion;
		
	}
	
	public int busquedaNumeroContrato(String numero_contrato) throws InversionesGenericException{

		Connection conn= null;
		CallableStatement statement= null;

		int nuemroContrato=0;
		
		try {
			
			String sql = obtenerFirmaFuncion("BUSQUEDA_NUMERO_CONTRATO(?)");
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			statement.setString(2,numero_contrato);
			statement.execute();
			nuemroContrato = ( Integer ) statement.getObject(1);
			
		}catch (Exception e) {
			log.error("Error en busquedaNumeroContrato: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos, reporte averias gracias. Busqueda numero de contrato");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		return nuemroContrato;
	}
	
	
	private void closeConnection(ResultSet resultSet, CallableStatement statement,Connection connection){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				log.info("Ocurrio un problema al cerrar el resultSet para la base de datos");
			}
		}
		
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				log.info("Ocurrio un problema al cerrar el statement para la base de datos");
			}
		}
		
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				log.info("Ocurrio un problema al cerrar la conexion para la base de datos");
			}
		}
		
	}
	
	private String toString(Object objeto){
		StringBuffer string = new StringBuffer();
		try {
			
			Class cls = Class.forName(objeto.getClass().getCanonicalName());
			Field declaredFieldList[] = cls.getDeclaredFields();
			
			String campo;
			String firstCharacter;
			String metodo=null;
			String value;
			
			
			string.append("/*--------------------  "+cls.getSimpleName()+"  -----------------------*/\n");
			for (Field field : declaredFieldList) {
				try{
					campo = field.getName();
					firstCharacter=campo.substring(0,1).toUpperCase();
					metodo = campo.substring(1);
					metodo=firstCharacter+metodo;
					Method mtd = cls.getMethod("get"+metodo,(Class[])null);            	   
					value = (String)mtd.invoke(objeto,(Object[])null);
					string.append(campo+"=");
					string.append(value+"\n");
				}catch(Exception e){}
			}
			string.append("--------------------------------------------------------------------\n");
		}
		catch (Throwable e){
			System.out.println("Error");
		}
		return string.toString();
	}
	
	private String obtenerFirmaFuncion(String firmaFuncion) throws IOException{
		String BASE_DATOS_PROPERTIES = "BaseDatos.properties";
		PropertiesService propertiesService=PropertiesService.getInstance();
		USUARIO_MODIFICO_BD = propertiesService.getPropertie(BASE_DATOS_PROPERTIES,"bd.usuario");
		String ESQUEMA_BD = propertiesService.getPropertie(BASE_DATOS_PROPERTIES,"bd.esquema");
		
		String resultado = "{ ? = call "+ESQUEMA_BD+"."+firmaFuncion+" }";
		
		return resultado;
	}
	
	
	
	public Collection<PlazoTasaTO> busquedaPlazoTasa() throws InversionesGenericException{
		log.info("-------> Entra a busquedaPlazoTasa en Base de Datos <--------");
		
		Connection conn= null;
		CallableStatement statement= null;
		ResultSet rs = null;
		Collection<PlazoTasaTO>plazoTasa=null;
		Integer idCliente = -1;

		try {
			conn = connection.getConnection();
			String sql = obtenerFirmaFuncion("Busqueda_Tasa_Plazo()");
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.CURSOR);

			
			statement.execute();
			rs = (ResultSet)statement.getObject(1);
			
			while (rs.next()) {
				String tasa= rs.getString(1); 
			    String  plazo=   rs.getString(2);
				String[] plazos=plazo.split(",");
				String[] tasas=tasa.split(",");

				plazoTasa=new ArrayList<PlazoTasaTO>();
				
				for (int i=0;i<plazos.length;i++) {
					plazos[i] = Formatter.removeSpacesLeftRight(plazos[i]);
					tasas[i] = Formatter.removeSpacesLeftRight(tasas[i]);
					String plazoTemp[]=plazos[i].split(" ");
					PlazoTasaTO plazoTasaTO = new PlazoTasaTO();
					plazoTasaTO.setPlazo(Formatter.removeSpacesLeftRight(plazoTemp[0]));
					plazoTasaTO.setTasa(Formatter.removeSpacesLeftRight(tasas[i].replace("%","")));
					plazoTasaTO.setTipoPlazo(Formatter.removeSpacesLeftRight(plazoTemp[1]));
					plazoTasa.add(plazoTasaTO);
					
				}
			}
			
			
		}catch (Exception e) {
			log.error("Error en busquedaPlazoTasa: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. BusquedaPlazoTasa");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(rs, statement, conn);
		}
		return plazoTasa;
	}

	public String busquedaCuentaInversionCliente(String idAlnova){

		Connection conn= null;
		CallableStatement statement= null;

		String numeroCuenta=null;
		
		try {
			
			String sql = obtenerFirmaFuncion("Busqueda_Numero_Cuenta_Cliente(?)");
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.VARCHAR);
			statement.setString(2,idAlnova);
			statement.execute();
			numeroCuenta = ( String ) statement.getObject(1);
			
		}catch (Exception e) {
			log.error("Error en busquedaCuentaInversionCliente: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. Busqueda cuenta cliente.");
			//throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		return numeroCuenta;
	}
	
	public Collection<ConciliacionEliteResponseTO> busquedaConciliacionRango(String fechaInicial,String fechaFinal) throws InversionesGenericException{
		log.info("-------> Entra a busquedaConciliacionRango en Base de Datos <--------");
		
		Connection conn= null;
		CallableStatement statement= null;
		ResultSet rs = null;
		Collection<ConciliacionEliteResponseTO>resultadoBusquedaRango=null;		

		try {
			conn = connection.getConnection();
			String sql = obtenerFirmaFuncion("Busqueda_Conciliacion_Rango(?,?)");
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.setString(2,fechaInicial);
			statement.setString(3,fechaFinal);
			
			statement.execute();
			rs = (ResultSet)statement.getObject(1);
			resultadoBusquedaRango=new ArrayList<ConciliacionEliteResponseTO>();
			
			while (rs.next()) {
				String nombre = rs.getString(1); 
			    String apellidoPaterno = rs.getString(2);
			    String apellidoMaterno = rs.getString(3);
			    String folioOperacion = rs.getString(4);
			    String tasa = rs.getString(5);
			    String plazo = rs.getString(6);
			    String montoInversion = rs.getString(8);
			    String hora = rs.getString(9);
			    String folioAlnova = rs.getString(10);

			    ConciliacionEliteResponseTO conciliacionResponseTO = new ConciliacionEliteResponseTO();
			    conciliacionResponseTO.setNombre_elite(nombre + " " + apellidoPaterno + " " + apellidoMaterno);
			    conciliacionResponseTO.setFolio_elite(folioOperacion);
				conciliacionResponseTO.setTasa_elite(tasa);
				conciliacionResponseTO.setPlazo_elite(plazo);
				conciliacionResponseTO.setMonto_elite(montoInversion);
				conciliacionResponseTO.setHora_elite(hora);
				
				resultadoBusquedaRango.add(conciliacionResponseTO);
			}
			
			
		}catch (Exception e) {
			log.error("Error en busquedaConciliacionRango: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. busquedaConciliacionRango");
			throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(rs, statement, conn);
		}
		return resultadoBusquedaRango;
	}
	
	public Collection<ConciliacionEliteResponseTO> busquedaConciliacionFecha(String fecha) throws InversionesGenericException{
		log.info("-------> Entra a busquedaConciliacionFecha en Base de Datos <--------");
		
		Connection conn= null;
		CallableStatement statement= null;
		ResultSet rs = null;
		Collection<ConciliacionEliteResponseTO>resultadoBusquedaRango=null;		

		try {
			conn = connection.getConnection();
			String sql = obtenerFirmaFuncion("BUSQUEDA_CONCILIACION_FECHA(?)");
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.CURSOR);
			statement.setString(2,fecha);			
			
			statement.execute();
			rs = (ResultSet)statement.getObject(1);
			resultadoBusquedaRango=new ArrayList<ConciliacionEliteResponseTO>();
			
			while (rs.next()) {
				String nombre = rs.getString(1); 
			    String apellidoPaterno = rs.getString(2);
			    String apellidoMaterno = rs.getString(3);
			    String folioOperacion = rs.getString(4);
			    String tasa = rs.getString(5);
			    String plazo = rs.getString(6);
			    String montoInversion = rs.getString(8);
			    String hora = rs.getString(9);
			    String folioAlnova = rs.getString(10);

			    ConciliacionEliteResponseTO conciliacionResponseTO = new ConciliacionEliteResponseTO();
			    conciliacionResponseTO.setNombre_elite(nombre + " " + apellidoPaterno + " " + apellidoMaterno);
			    conciliacionResponseTO.setFolio_elite(folioOperacion);
				conciliacionResponseTO.setTasa_elite(tasa);
				conciliacionResponseTO.setPlazo_elite(plazo);
				conciliacionResponseTO.setMonto_elite(montoInversion);
				conciliacionResponseTO.setHora_elite(hora);
				conciliacionResponseTO.setFolio_retencion(folioAlnova);
				
				resultadoBusquedaRango.add(conciliacionResponseTO);
			}
			
			
		}catch (Exception e) {
			log.error("Error en busquedaConciliacionFecha: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. busquedaConciliacionFecha");
			throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(rs, statement, conn);
		}
		return resultadoBusquedaRango;
	}	
	
	public String busquedaFechaApertura(String idAlnova) throws InversionesGenericException{
		log.info("-------> Entra a busquedaFechaApertura en Base de Datos <--------");
		
		Connection conn= null;
		CallableStatement statement= null;
		String fechaApertura=null;
		
		try {			
			String sql = obtenerFirmaFuncion("Busqueda_Fecha_Apertura(?)");
			conn = connection.getConnection();
			statement = conn.prepareCall(sql);
			statement.registerOutParameter(1, OracleTypes.VARCHAR);
			statement.setString(2,idAlnova);
			statement.execute();
			fechaApertura = ( String ) statement.getObject(1);
			
		}catch (Exception e) {
			log.error("Error en busquedaFechaApertura: ", e);
			InversionesExceptionTO exceptionTO=new InversionesExceptionTO();
			exceptionTO.setLevel(InversionesGenericException.LEVEL_BASE_DATOS);
			exceptionTO.setMessage("Comunicacion con base de datos. busquedaConciliacionFecha");
			throw new InversionesGenericException(exceptionTO);
		}finally{
			closeConnection(null, statement, conn);
		}
		log.info(" ----- Fecha de Apertura ----- " + fechaApertura);
		return fechaApertura;
	}
}