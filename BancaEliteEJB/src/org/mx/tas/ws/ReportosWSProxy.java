package org.mx.tas.ws;

public class ReportosWSProxy implements org.mx.tas.ws.ReportosWS {
  private String _endpoint = null;
  private org.mx.tas.ws.ReportosWS reportosWS = null;
  
  public ReportosWSProxy() {
    _initReportosWSProxy();
  }
  
  public ReportosWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initReportosWSProxy();
  }
  
  private void _initReportosWSProxy() {
    try {
      reportosWS = (new org.mx.tas.ws.ReportosWSServiceLocator()).getReportosWSPort();
      if (reportosWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)reportosWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)reportosWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (reportosWS != null)
      ((javax.xml.rpc.Stub)reportosWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.mx.tas.ws.ReportosWS getReportosWS() {
    if (reportosWS == null)
      _initReportosWSProxy();
    return reportosWS;
  }
  
  public java.lang.String transaccionTAS(org.mx.tas.ws.EjecutaTransaccion ejecutaTransaccion) throws java.rmi.RemoteException{
    if (reportosWS == null)
      _initReportosWSProxy();
    return reportosWS.transaccionTAS(ejecutaTransaccion);
  }
  
  
}