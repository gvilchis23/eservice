package com.bancoazteca.elite.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.exception.SessionExpiredException;

/**
 * 
 * @author Jorge Bringas
 *
 */
public class DataTest {
	
	private static final Logger LOG = Logger.getLogger(DataTest.class);
	
	private static final String PATH = "dummy/";
	
	private static WeakHashMap<String, String> paths = new WeakHashMap<String, String>();
	
	static{
		paths.put("/logout", "/logout.xml");
		paths.put("/login", "/consultas/inicio.do");
		paths.put("/consultas/cuentasPre.elite", "login.xml");
		paths.put("/consultas/nec_movimientosTBACmd.elite", "infiniteMovimientos.xml");
		paths.put("/consultas/movimientosiniCmd.elite", "cuentasMov0.xml");
		paths.put("/consultas/movimientosiniCmd.elite?cuenta=0", "cuentasMov0.xml");
		paths.put("/consultas/movimientosiniCmd.elite?cuenta=1", "cuentasMov1.xml");
		paths.put("/consultas/movimientosiniCmd.elite?cuenta=1", "cuentasMov1.xml");
		paths.put("/traspasos/propiasConfirmar.elite", "TP1.xml");
		paths.put("/traspasos/propiasEjecutarCmd.elite", "TP2.xml");
		paths.put("/alertas/celular/acPre.elite","AlertasLista.xml");
		paths.put("/alertas/celular/modifica/acMP.elite","ModificaCuenta.xml");
		
		paths.put("/alertas/celular/modifica/acMMC.elite","CargaPaso2ModificaAlertas.xml");
		paths.put("/alertas/celular/modifica/acMM.elite","CargaPaso3ModificaAlertas.xml");		
		paths.put("/traspasos/tercerosPre.elite","transferenciasTerceros.xml");
		paths.put("/transferencias/tefsPre.elite","transferenciasTefPre.xml");
		paths.put("/transferencias/transPre.elite","transferenciasTef.xml");
		paths.put("/transferencias/speiPre.elite","transferenciasSpeiPre.xml");
		paths.put("/transferencias/transSpei.elite","transferenciasSpei.xml");		
		paths.put("/transferencias/transfInternalPre.elite","transferenciasInter.xml");
					
		paths.put("/transferencias/agendaCuentasPretercero.elite","cuentasFrecuentesListadoTercero.xml");
		paths.put("/transferencias/agendaCuentasPretef.elite","cuentasFrecuentesListadoTef.xml");
		paths.put("/transferencias/agendaCuentasPrespei.elite","cuentasFrecuentesListadoSpei.xml");
		paths.put("/transferencias/agendaCuentasPretransfInternal.elite","cuentasFrecuentesListadoInternal.xml");
		/*
		paths.put("/servicios/servicioDisponible.elite","pagotelmex1.xml");
		paths.put("/servicios/serviciosElegir.elite","pagotelmex2.xml");
		paths.put("/servicios/serviciosFill.elite","pagotelmex3.xml");
		paths.put("/servicios/confirmarSaldo.elite","pagotelmex4.xml");
		paths.put("/servicios/pagoServicioPassword.elite","pagotelmex5.xml");
		paths.put("/servicios/pagoServicioEjecutar.elite","pagotelmex6.xml");
		paths.put("/servicios/pagoServicioEjecutarCmd.elite","pagotelmex7.xml");
		*/
		paths.put("/servicios/servicioDisponible.elite","pagoluz1.xml");
		paths.put("/servicios/serviciosElegir.elite","pagoluz2.xml");
		paths.put("/servicios/confirmarSaldo.elite","pagoluz3.xml");
		paths.put("/servicios/pagoServicioPassword.elite","pagoluz4.xml");
		paths.put("/servicios/pagoServicioEjecutar.elite","pagoluz5.xml");
		paths.put("/servicios/pagoServicioEjecutarCmd.elite","pagoluz6.xml");
				
	}


	public static final String execute(String key) throws SessionExpiredException, URISyntaxException, HttpException, IOException{
		LOG.info(">> key: ---------------------"+key);
		return getFileStream(paths.get(key));
	}
	
	
	public static final String execute(String key, Map<String, String> params) throws SessionExpiredException, URISyntaxException, HttpException, IOException{
		
		
		if ( key != null && ( key.indexOf("agendaCuentasPre.elite") > 0) && params != null ){
			String tmpKey = null;
			Set<String> set = params.keySet();
			Object[] keys = set.toArray();
			for(int i = 0; i< params.size();i++){
				Object  value =params.get(keys[i]);
				try{
					tmpKey = key.substring( 0, ( key.indexOf(".") ) ) + value + key.substring( ( key.indexOf(".") ), key.length() );
					key = tmpKey;
				}catch( Exception e ){
					e.printStackTrace();
				}
			}
		}
		
		LOG.info(">> key: ---------------------"+key);
		return getFileStream(paths.get(key));
	}
	
	
	private synchronized static String getFileStream(String file)  throws IOException{
		StringBuffer  buffer = new StringBuffer();
		try{
			if (file.endsWith("xml")){
				LOG.info("#############file to load " + PATH+file +  " #############");
				InputStream inputStream = DataTest.class.getClassLoader().getResourceAsStream(PATH+file);			
				BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(inputStream));		
				while (bufferedReader.ready()){
					buffer.append(bufferedReader.readLine().trim());
				}
				inputStream.close();
			}else{
				buffer.append(file);
			}
		}catch(Throwable e){
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		LOG.info(buffer.toString());
		return buffer.toString();
	}
	
}
