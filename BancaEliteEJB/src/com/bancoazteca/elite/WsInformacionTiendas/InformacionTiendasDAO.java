package com.bancoazteca.elite.WsInformacionTiendas;

import java.net.URL;
import java.rmi.RemoteException;

import javax.ejb.EJBException;

import com.bancoazteca.elite.beans.LocalizaSucursalRequestTO;
import com.bancoazteca.elite.connector.ConnectorManager;
import com.bancoazteca.elite.ejb.exception.DAOException;

import org.apache.axis.client.Service;

public class InformacionTiendasDAO {
	
	public Estado[] getObtieneEstadosDAO(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws DAOException{
		Estado []estados = null;
		try{
			Service service = new Service();
			InformacionTiendasSoapStub informacionTiendasSoapStub = new InformacionTiendasSoapStub(new URL("http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx"),service);

			//obtenerEstados
			estados = informacionTiendasSoapStub.obtenerEstados(localizaSucursalRequestTO.getIdPais());
			System.out.println("estados.-"+estados);
		
		}catch(RemoteException ex){
			throw new DAOException(ex);
		}catch(Exception ex){
			throw new DAOException(ex);
		}
		return estados;
	}
	
	public Municipio[] getObtieneMunicipiosDAO(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws DAOException{
		Municipio []municipios = null;
		try{
			Service service = new Service();
			InformacionTiendasSoapStub informacionTiendasSoapStub = new InformacionTiendasSoapStub(new URL("http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx"),service);

			//obtenerEstados
			municipios = informacionTiendasSoapStub.obtenerMunicipios(localizaSucursalRequestTO.getIdPais(), localizaSucursalRequestTO.getIdEstado(), localizaSucursalRequestTO.getCanales());
			System.out.println("municipios.-"+municipios);
		}catch(RemoteException ex){
			throw new DAOException(ex);
		}catch(Exception ex){
			throw new DAOException(ex);
		}
		return municipios;
	}
	
	public Tienda[] getObtieneTiendaDAO(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws DAOException{
		Tienda []tienda = null;
		try{
			Service service = new Service();
			InformacionTiendasSoapStub informacionTiendasSoapStub = new InformacionTiendasSoapStub(new URL("http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx"),service);

			//obtenerTienda
			tienda = informacionTiendasSoapStub.obtenerInformacionTienda(localizaSucursalRequestTO.getIdPais(), localizaSucursalRequestTO.getNumeroTienda());
			System.out.println("getObtieneTiendaDAO.-"+tienda);
		}catch(RemoteException ex){
			throw new DAOException(ex);
		}catch(Exception ex){
			throw new DAOException(ex);
		}
		return tienda;
	}
	
	public Tienda[] getObtieneTiendaCPDAO(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws DAOException{
		Tienda []tienda = null;
		try{
			Service service = new Service();
			InformacionTiendasSoapStub informacionTiendasSoapStub = new InformacionTiendasSoapStub(new URL("http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx"),service);

			//obtenerTiendaCP
			tienda = informacionTiendasSoapStub.obtenerInformacionTiendaPorCp(localizaSucursalRequestTO.getIdPais(), localizaSucursalRequestTO.getCodigoPostal(), localizaSucursalRequestTO.getCanales());
			System.out.println("getObtieneTiendaCPDAO.-"+tienda);
		}catch(RemoteException ex){
			throw new DAOException(ex);
		}catch(Exception ex){
			throw new DAOException(ex);
		}
		return tienda;
	}
	
	public Tienda[] getObtieneTiendaPalabraDAO(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws DAOException{
		Tienda []tienda = null;
		try{
			Service service = new Service();
			InformacionTiendasSoapStub informacionTiendasSoapStub = new InformacionTiendasSoapStub(new URL("http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx"),service);

			//obtenerTiendaPalabra
			tienda = informacionTiendasSoapStub.obtenerInformacionTiendaPorPalabra(localizaSucursalRequestTO.getIdPais(), localizaSucursalRequestTO.getPalabra(), localizaSucursalRequestTO.getCanales());
			System.out.println("getObtieneTiendaPalabraDAO.-"+tienda);
		}catch(RemoteException ex){
			throw new DAOException(ex);
		}catch(Exception ex){
			throw new DAOException(ex);
		}
		return tienda;
	}
	
	public Tienda[] getObtieneTiendasDAO(LocalizaSucursalRequestTO localizaSucursalRequestTO) throws DAOException{
		Tienda []tienda = null;
		try{
			Service service = new Service();
			InformacionTiendasSoapStub informacionTiendasSoapStub = new InformacionTiendasSoapStub(new URL("http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx"),service);

			//obtenerTiendaPalabra
			tienda = informacionTiendasSoapStub.obtenerInformacionTiendas(localizaSucursalRequestTO.getIdPais(), localizaSucursalRequestTO.getIdEstado(), localizaSucursalRequestTO.getIdMunicipio(), localizaSucursalRequestTO.getCanales());
			System.out.println("getObtieneTiendasDAO.-"+tienda);
		}catch(RemoteException ex){
			throw new DAOException(ex);
		}catch(Exception ex){
			throw new DAOException(ex);
		}
		return tienda;
	}
}
