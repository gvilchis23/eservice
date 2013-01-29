package com.bancoazteca.elite.WsInformacionTiendas;

public class InformacionTiendasSoapProxy implements com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap {
  private String _endpoint = null;
  private com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap informacionTiendasSoap = null;
  
  public InformacionTiendasSoapProxy() {
    _initInformacionTiendasSoapProxy();
  }
  
  public InformacionTiendasSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initInformacionTiendasSoapProxy();
  }
  
  private void _initInformacionTiendasSoapProxy() {
    try {
      informacionTiendasSoap = (new com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasLocator()).getInformacionTiendasSoap();
      if (informacionTiendasSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)informacionTiendasSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)informacionTiendasSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (informacionTiendasSoap != null)
      ((javax.xml.rpc.Stub)informacionTiendasSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap getInformacionTiendasSoap() {
    if (informacionTiendasSoap == null)
      _initInformacionTiendasSoapProxy();
    return informacionTiendasSoap;
  }
  
  public com.bancoazteca.elite.WsInformacionTiendas.Estado[] obtenerEstados(int idPais) throws java.rmi.RemoteException{
    if (informacionTiendasSoap == null)
      _initInformacionTiendasSoapProxy();
    return informacionTiendasSoap.obtenerEstados(idPais);
  }
  
  public com.bancoazteca.elite.WsInformacionTiendas.Municipio[] obtenerMunicipios(int idPais, int idEstado, java.lang.String canales) throws java.rmi.RemoteException{
    if (informacionTiendasSoap == null)
      _initInformacionTiendasSoapProxy();
    return informacionTiendasSoap.obtenerMunicipios(idPais, idEstado, canales);
  }
  
  public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTiendas(int idPais, int idEstado, int idMunicipio, java.lang.String canales) throws java.rmi.RemoteException{
    if (informacionTiendasSoap == null)
      _initInformacionTiendasSoapProxy();
    return informacionTiendasSoap.obtenerInformacionTiendas(idPais, idEstado, idMunicipio, canales);
  }
  
  public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTienda(int idPais, int numeroTienda) throws java.rmi.RemoteException{
    if (informacionTiendasSoap == null)
      _initInformacionTiendasSoapProxy();
    return informacionTiendasSoap.obtenerInformacionTienda(idPais, numeroTienda);
  }
  
  public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTiendaPorCp(int idPais, java.lang.String codigoPostal, java.lang.String canales) throws java.rmi.RemoteException{
    if (informacionTiendasSoap == null)
      _initInformacionTiendasSoapProxy();
    return informacionTiendasSoap.obtenerInformacionTiendaPorCp(idPais, codigoPostal, canales);
  }
  
  public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTiendaPorPalabra(int idPais, java.lang.String palabra, java.lang.String canales) throws java.rmi.RemoteException{
    if (informacionTiendasSoap == null)
      _initInformacionTiendasSoapProxy();
    return informacionTiendasSoap.obtenerInformacionTiendaPorPalabra(idPais, palabra, canales);
  }
  
  
}