package com.bancoazteca.eservice.command.base.session;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.connector.ConnectorManager;


public class SessionWatchThread extends Thread {
	
	Logger logger=Logger.getLogger(SessionWatchThread.class);
	
	
	private long tiempoVidaInactivo;
	private long tiempoChequeo;
	
	public void setTiempoVidaInactivo(long tiempoVidaInactivo) {
		this.tiempoVidaInactivo = tiempoVidaInactivo;
	}


	public void setTiempoChequeo(long tiempoChequeo) {
		this.tiempoChequeo = tiempoChequeo;
	}


	public void run(){
		try{
			while(true){
				Thread.sleep(tiempoChequeo);
				try{
					long init=System.currentTimeMillis();
					validaSessiones();
					long fin=System.currentTimeMillis();
					
					//logger.info("Tiempo tomado: "+(fin-init));
					
				}catch(Throwable exception){
					exception.printStackTrace();
					System.out.println("Ocurrio algun error recuperable al intentar eliminar una session...");
				}
			}
		}catch (InterruptedException ie){
				logger.error("Saliendo del thread para invalidar sessiones olvidadas");
		}
	}
	
	
	private void validaSessiones(){

		//logger.info("Checando las sessiones de eService.....");
		
		SessionManager sessionManager=SessionManager.getInstance();
		
		HashMap<String,Long>mapaConn=ConnectorManager.getTiempos();
		long currentTime=System.currentTimeMillis();
		
		synchronized (mapaConn) {
		
			//logger.info("Usuarios actuales: "+mapaConn.size());
			
			
			Iterator<String>iterator= mapaConn.keySet().iterator();			
			
			HashMap<String,Long>usersTimeTemp=new HashMap<String,Long>();
			
			
			usersTimeTemp.putAll(mapaConn);			
			
			
			for(String user:usersTimeTemp.keySet()){
				
				//System.out.println("Tiempos:"+(currentTime-mapaConn.get(user)+", "+user));
				if((currentTime-mapaConn.get(user))>=(tiempoVidaInactivo)){
					
					//logger.info("terminando la session inactiva de un usuario: "+user);
					
					ConnectorManager.removeConnector(user);
					//logger.info("Ok connector removido: "+user);
					
					try{
					
						String idSession=sessionManager.getIdSessionByUser(user);
					//	logger.info("Session de usuario: "+idSession);
						
						if(idSession!=null)
							sessionManager.invalidate(idSession,SessionManager.INVALIDATE_IDSESSION);
						
					}catch(ConcurrentModificationException concurrentModif){
						System.out.println("Accseso simultaneo...");
					}
				}
			}
			usersTimeTemp=null;
		}
	}	
	
}