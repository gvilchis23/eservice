/**
 * InformacionTiendasLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bancoazteca.elite.WsInformacionTiendas;

public class InformacionTiendasLocator extends org.apache.axis.client.Service implements com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendas {

    public InformacionTiendasLocator() {
    }


    public InformacionTiendasLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InformacionTiendasLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InformacionTiendasSoap
    private java.lang.String InformacionTiendasSoap_address = "http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx";

    public java.lang.String getInformacionTiendasSoapAddress() {
        return InformacionTiendasSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InformacionTiendasSoapWSDDServiceName = "InformacionTiendasSoap";

    public java.lang.String getInformacionTiendasSoapWSDDServiceName() {
        return InformacionTiendasSoapWSDDServiceName;
    }

    public void setInformacionTiendasSoapWSDDServiceName(java.lang.String name) {
        InformacionTiendasSoapWSDDServiceName = name;
    }

    public com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap getInformacionTiendasSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InformacionTiendasSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInformacionTiendasSoap(endpoint);
    }

    public com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap getInformacionTiendasSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoapStub _stub = new com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoapStub(portAddress, this);
            _stub.setPortName(getInformacionTiendasSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInformacionTiendasSoapEndpointAddress(java.lang.String address) {
        InformacionTiendasSoap_address = address;
    }


    // Use to get a proxy class for InformacionTiendasSoap12
    private java.lang.String InformacionTiendasSoap12_address = "http://www.elektra.com.mx/WsInformacionTiendas/InformacionTiendas.asmx";

    public java.lang.String getInformacionTiendasSoap12Address() {
        return InformacionTiendasSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InformacionTiendasSoap12WSDDServiceName = "InformacionTiendasSoap12";

    public java.lang.String getInformacionTiendasSoap12WSDDServiceName() {
        return InformacionTiendasSoap12WSDDServiceName;
    }

    public void setInformacionTiendasSoap12WSDDServiceName(java.lang.String name) {
        InformacionTiendasSoap12WSDDServiceName = name;
    }

    public com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap getInformacionTiendasSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InformacionTiendasSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInformacionTiendasSoap12(endpoint);
    }

    public com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap getInformacionTiendasSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap12Stub _stub = new com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap12Stub(portAddress, this);
            _stub.setPortName(getInformacionTiendasSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInformacionTiendasSoap12EndpointAddress(java.lang.String address) {
        InformacionTiendasSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoapStub _stub = new com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoapStub(new java.net.URL(InformacionTiendasSoap_address), this);
                _stub.setPortName(getInformacionTiendasSoapWSDDServiceName());
                return _stub;
            }
            if (com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap12Stub _stub = new com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap12Stub(new java.net.URL(InformacionTiendasSoap12_address), this);
                _stub.setPortName(getInformacionTiendasSoap12WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InformacionTiendasSoap".equals(inputPortName)) {
            return getInformacionTiendasSoap();
        }
        else if ("InformacionTiendasSoap12".equals(inputPortName)) {
            return getInformacionTiendasSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "InformacionTiendas");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "InformacionTiendasSoap"));
            ports.add(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "InformacionTiendasSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InformacionTiendasSoap".equals(portName)) {
            setInformacionTiendasSoapEndpointAddress(address);
        }
        else 
if ("InformacionTiendasSoap12".equals(portName)) {
            setInformacionTiendasSoap12EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
