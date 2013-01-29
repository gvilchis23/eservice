package com.bancoazteca.elite.preferencias.db.dao;

import java.io.IOException;
import java.util.Collection;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;

import com.bancoazteca.elite.beans.CuentaBloqueadaDBTO;
import com.bancoazteca.elite.beans.PreguntasFrecuentesResponseTO;
import com.bancoazteca.elite.commons.xml.XmlDecodeException;
import com.bancoazteca.elite.ejb.exception.DAOException;
import com.bancoazteca.elite.ejb.exception.SessionExpiredException;


public class PreferenciasDao {
	Logger logger=Logger.getLogger(PreferenciasDao.class);
		
	public Collection<CuentaBloqueadaDBTO> getCuentasOcultas(CuentaBloqueadaDBTO to) throws DAOException {
		logger.info("Entrando a método para obtener cuentas ocultas de usuario");
		PreferenciasDaoAbstracto preferenciasDao=new PreferenciasDaoAbstracto();
		Collection<CuentaBloqueadaDBTO> cuentasBloqueadas = preferenciasDao.getCuentasOcultas(to);
		return cuentasBloqueadas;
	}
	
	public int insertarCuenta(CuentaBloqueadaDBTO to) throws DAOException {
		logger.info("Entrando al método para insertar cuenta oculta");
		PreferenciasDaoAbstracto preferenciasDao=new PreferenciasDaoAbstracto();
		return preferenciasDao.insertarCuenta(to);
	}
	
	public int eliminarCuenta(CuentaBloqueadaDBTO to) throws DAOException {
		logger.info("Entrando al método para insertar cuenta oculta");
		PreferenciasDaoAbstracto preferenciasDao=new PreferenciasDaoAbstracto();
		return preferenciasDao.eliminarCuenta(to);
	}
	
	public String filtraCuentasOcultas(String xml, String idUsuario) throws DAOException{
		PreferenciasDaoAbstracto preferenciasDao=new PreferenciasDaoAbstracto();
		CuentaBloqueadaDBTO to=new CuentaBloqueadaDBTO();
		to.setIdUsuario(idUsuario);
		Collection<CuentaBloqueadaDBTO> cuentasBloqueadas = preferenciasDao.getCuentasOcultas(to);

		String xmlFinal= xml;
		for (CuentaBloqueadaDBTO cuenta : cuentasBloqueadas)  {
			xmlFinal = preferenciasDao.filtraCuentas(xmlFinal, cuenta.getNumero_cuenta());
		} 
		return xmlFinal; 
	}

	public PreguntasFrecuentesResponseTO getPreguntasFrecuentes() throws IOException, XmlDecodeException {
		logger.info("Entrando al método para obtener las preguntas frecuentes");
		PreferenciasDaoAbstracto preferenciasDao=new PreferenciasDaoAbstracto();
		return preferenciasDao.getPreguntasFrecuentes(); 
	}
	
	public MessageElement liberaTerminal(String user,String terminal)throws SessionExpiredException, DAOException{
		MessageElement messageElement=null;
		PreferenciasDaoAbstracto dao=new PreferenciasDaoAbstracto();
		messageElement=dao.liberaterminalAlnova(user, terminal);
		return messageElement;
	}
	
	public MessageElement getTerminalAlnova(String user) throws SessionExpiredException, DAOException{
		MessageElement messageElement=null;
		PreferenciasDaoAbstracto daoAbstracto=new PreferenciasDaoAbstracto();
		messageElement=daoAbstracto.getTerminalAlnova(user);
		return messageElement;
	}
}
