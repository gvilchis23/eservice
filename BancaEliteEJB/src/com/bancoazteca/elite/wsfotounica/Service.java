/**
 * Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bancoazteca.elite.wsfotounica;

public interface Service extends javax.xml.rpc.Service {
    public java.lang.String getServiceSoapAddress();

    public com.bancoazteca.elite.wsfotounica.ServiceSoap getServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.bancoazteca.elite.wsfotounica.ServiceSoap getServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getServiceSoap12Address();

    public com.bancoazteca.elite.wsfotounica.ServiceSoap getServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.bancoazteca.elite.wsfotounica.ServiceSoap getServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
