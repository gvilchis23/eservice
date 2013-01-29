package com.bancoazteca.eservice.command.base.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class SessionValidator extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Logger logger=Logger.getLogger(SessionValidator.class);
	
	@Override
	public void init() throws ServletException {
		super.init();
		logger.info("Se arranco el chequeo de sessiones en eService");
		
		String tiempo=getInitParameter("TiempoChequeoMS");
		String tiempoVidaSessionInactiva=getInitParameter("TiempoVidaSesionInactivaMS");
		String deamon=getInitParameter("SetDeamon");
		logger.info("params: "+tiempo+", "+tiempoVidaSessionInactiva+", "+deamon);
		
		logger.info("Tiempo de vida inactivo: "+(Long.parseLong(tiempoVidaSessionInactiva)/1000)+" segundos.");
		logger.info("Tiempo de vida inactivo: "+(Long.parseLong(tiempoVidaSessionInactiva)/1000/60)+" minutos.");
		
		logger.info("Tiempo de repeticion: "+(Long.parseLong(tiempo)/1000)+" segundos.");
		logger.info("Tiempo de repeticion: "+(Long.parseLong(tiempo)/1000/60)+" minutos.");
		
		
		
		SessionWatchThread sessionWatchThread=new SessionWatchThread();		
		sessionWatchThread.setTiempoChequeo(Long.parseLong(tiempo));
		sessionWatchThread.setTiempoVidaInactivo(Long.parseLong(tiempoVidaSessionInactiva));
		sessionWatchThread.setDaemon(Boolean.parseBoolean(deamon));
		sessionWatchThread.start();
		logger.info("Ok continua...");
	}
}