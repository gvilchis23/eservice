/**
 * ReportosWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mx.tas.ws;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.bancoazteca.elite.ejb.inversiones.transacciones.reportos.ReportosTransactions;
import com.bancoazteca.elite.util.PropertiesService;

public class ReportosWSServiceLocator extends org.apache.axis.client.Service implements org.mx.tas.ws.ReportosWSService {

	private Logger $_log=Logger.getLogger(ReportosTransactions.class);
	
	private static final PropertiesService properties=PropertiesService.getInstance();
	private static final String TESORERIA="Tesoreria.properties";
	private String ReportosWSPort_address;
	
    public ReportosWSServiceLocator() {
    	getReportosAddress();
    }


    public ReportosWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReportosWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReportosWSPort
    
    
    private void getReportosAddress(){
    	String URL_TAS = null;
		try {
			URL_TAS = properties.getPropertie(TESORERIA, "webservice.reportos.address");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ReportosWSPort_address = URL_TAS;
		$_log.info("Direccion Tesoreria: " + ReportosWSPort_address);
    }

    public java.lang.String getReportosWSPortAddress() {
        return ReportosWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReportosWSPortWSDDServiceName = "ReportosWSPort";

    public java.lang.String getReportosWSPortWSDDServiceName() {
        return ReportosWSPortWSDDServiceName;
    }

    public void setReportosWSPortWSDDServiceName(java.lang.String name) {
        ReportosWSPortWSDDServiceName = name;
    }

    public org.mx.tas.ws.ReportosWS getReportosWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReportosWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReportosWSPort(endpoint);
    }

    public org.mx.tas.ws.ReportosWS getReportosWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.mx.tas.ws.ReportosWSBindingStub _stub = new org.mx.tas.ws.ReportosWSBindingStub(portAddress, this);
            _stub.setPortName(getReportosWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReportosWSPortEndpointAddress(java.lang.String address) {
        ReportosWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.mx.tas.ws.ReportosWS.class.isAssignableFrom(serviceEndpointInterface)) {
                org.mx.tas.ws.ReportosWSBindingStub _stub = new org.mx.tas.ws.ReportosWSBindingStub(new java.net.URL(ReportosWSPort_address), this);
                _stub.setPortName(getReportosWSPortWSDDServiceName());
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
        if ("ReportosWSPort".equals(inputPortName)) {
            return getReportosWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.tas.mx.org/", "ReportosWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.tas.mx.org/", "ReportosWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReportosWSPort".equals(portName)) {
            setReportosWSPortEndpointAddress(address);
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
