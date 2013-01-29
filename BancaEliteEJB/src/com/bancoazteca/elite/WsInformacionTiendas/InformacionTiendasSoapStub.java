/**
 * InformacionTiendasSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bancoazteca.elite.WsInformacionTiendas;

public class InformacionTiendasSoapStub extends org.apache.axis.client.Stub implements com.bancoazteca.elite.WsInformacionTiendas.InformacionTiendasSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerEstados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idPais"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfEstado"));
        oper.setReturnClass(com.bancoazteca.elite.WsInformacionTiendas.Estado[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerEstadosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Estado"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerMunicipios");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idPais"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idEstado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "canales"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfMunicipio"));
        oper.setReturnClass(com.bancoazteca.elite.WsInformacionTiendas.Municipio[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerMunicipiosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Municipio"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerInformacionTiendas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idPais"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idEstado"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idMunicipio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "canales"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfTienda"));
        oper.setReturnClass(com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTiendasResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerInformacionTienda");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idPais"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "numeroTienda"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfTienda"));
        oper.setReturnClass(com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTiendaResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerInformacionTiendaPorCp");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idPais"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "codigoPostal"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "canales"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfTienda"));
        oper.setReturnClass(com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTiendaPorCpResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ObtenerInformacionTiendaPorPalabra");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "idPais"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "palabra"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "canales"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfTienda"));
        oper.setReturnClass(com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTiendaPorPalabraResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

    }

    public InformacionTiendasSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public InformacionTiendasSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public InformacionTiendasSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfEstado");
            cachedSerQNames.add(qName);
            cls = com.bancoazteca.elite.WsInformacionTiendas.Estado[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Estado");
            qName2 = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Estado");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfMunicipio");
            cachedSerQNames.add(qName);
            cls = com.bancoazteca.elite.WsInformacionTiendas.Municipio[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Municipio");
            qName2 = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Municipio");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ArrayOfTienda");
            cachedSerQNames.add(qName);
            cls = com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda");
            qName2 = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Estado");
            cachedSerQNames.add(qName);
            cls = com.bancoazteca.elite.WsInformacionTiendas.Estado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Municipio");
            cachedSerQNames.add(qName);
            cls = com.bancoazteca.elite.WsInformacionTiendas.Municipio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda");
            cachedSerQNames.add(qName);
            cls = com.bancoazteca.elite.WsInformacionTiendas.Tienda.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.bancoazteca.elite.WsInformacionTiendas.Estado[] obtenerEstados(int idPais) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.elektra.com.mx/WsInformacionTiendas/ObtenerEstados");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerEstados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idPais)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bancoazteca.elite.WsInformacionTiendas.Estado[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bancoazteca.elite.WsInformacionTiendas.Estado[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.bancoazteca.elite.WsInformacionTiendas.Estado[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bancoazteca.elite.WsInformacionTiendas.Municipio[] obtenerMunicipios(int idPais, int idEstado, java.lang.String canales) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.elektra.com.mx/WsInformacionTiendas/ObtenerMunicipios");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerMunicipios"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idPais), new java.lang.Integer(idEstado), canales});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bancoazteca.elite.WsInformacionTiendas.Municipio[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bancoazteca.elite.WsInformacionTiendas.Municipio[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.bancoazteca.elite.WsInformacionTiendas.Municipio[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTiendas(int idPais, int idEstado, int idMunicipio, java.lang.String canales) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.elektra.com.mx/WsInformacionTiendas/ObtenerInformacionTiendas");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTiendas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idPais), new java.lang.Integer(idEstado), new java.lang.Integer(idMunicipio), canales});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTienda(int idPais, int numeroTienda) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.elektra.com.mx/WsInformacionTiendas/ObtenerInformacionTienda");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTienda"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idPais), new java.lang.Integer(numeroTienda)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTiendaPorCp(int idPais, java.lang.String codigoPostal, java.lang.String canales) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.elektra.com.mx/WsInformacionTiendas/ObtenerInformacionTiendaPorCp");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTiendaPorCp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idPais), codigoPostal, canales});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bancoazteca.elite.WsInformacionTiendas.Tienda[] obtenerInformacionTiendaPorPalabra(int idPais, java.lang.String palabra, java.lang.String canales) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.elektra.com.mx/WsInformacionTiendas/ObtenerInformacionTiendaPorPalabra");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "ObtenerInformacionTiendaPorPalabra"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idPais), palabra, canales});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bancoazteca.elite.WsInformacionTiendas.Tienda[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.bancoazteca.elite.WsInformacionTiendas.Tienda[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
