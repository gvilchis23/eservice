package com.bancoazteca.elite.wsfotounica;

public class ServiceSoapProxy implements com.bancoazteca.elite.wsfotounica.ServiceSoap {
  private String _endpoint = null;
  private com.bancoazteca.elite.wsfotounica.ServiceSoap serviceSoap = null;
  
  public ServiceSoapProxy() {
    _initServiceSoapProxy();
  }
  
  public ServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceSoapProxy();
  }
  
  private void _initServiceSoapProxy() {
    try {
      serviceSoap = (new com.bancoazteca.elite.wsfotounica.ServiceLocator()).getServiceSoap();
      if (serviceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceSoap != null)
      ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bancoazteca.elite.wsfotounica.ServiceSoap getServiceSoap() {
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap;
  }
  
  public com.bancoazteca.elite.wsfotounica.BuscaFotoxCuentaResponseBuscaFotoxCuentaResult buscaFotoxCuenta(java.lang.String pstrIP, java.lang.String pstrServerPort, java.lang.String pfiid_sucgestora, java.lang.String pfccuenta) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.buscaFotoxCuenta(pstrIP, pstrServerPort, pfiid_sucgestora, pfccuenta);
  }
  
  
}