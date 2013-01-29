/**
 * Tienda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bancoazteca.elite.WsInformacionTiendas;

public class Tienda  extends com.bancoazteca.elite.WsInformacionTiendas.Municipio  implements java.io.Serializable {
    private int idPais;

    private int idCanal;

    private java.lang.String nombreCanal;

    private int idTienda;

    private java.lang.String nombreTienda;

    private java.lang.String calle;

    private java.lang.String colonia;

    private java.lang.String poblacion;

    private java.lang.String codigoPostal;

    private java.lang.String latitud;

    private java.lang.String longitud;

    private java.lang.String telefono;

    public Tienda() {
    }

    public Tienda(
           int idEstado,
           java.lang.String nombreEstado,
           int idMunicipio,
           java.lang.String nombreMunicipio,
           int idPais,
           int idCanal,
           java.lang.String nombreCanal,
           int idTienda,
           java.lang.String nombreTienda,
           java.lang.String calle,
           java.lang.String colonia,
           java.lang.String poblacion,
           java.lang.String codigoPostal,
           java.lang.String latitud,
           java.lang.String longitud,
           java.lang.String telefono) {
        super(
            idEstado,
            nombreEstado,
            idMunicipio,
            nombreMunicipio);
        this.idPais = idPais;
        this.idCanal = idCanal;
        this.nombreCanal = nombreCanal;
        this.idTienda = idTienda;
        this.nombreTienda = nombreTienda;
        this.calle = calle;
        this.colonia = colonia;
        this.poblacion = poblacion;
        this.codigoPostal = codigoPostal;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
    }


    /**
     * Gets the idPais value for this Tienda.
     * 
     * @return idPais
     */
    public int getIdPais() {
        return idPais;
    }


    /**
     * Sets the idPais value for this Tienda.
     * 
     * @param idPais
     */
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }


    /**
     * Gets the idCanal value for this Tienda.
     * 
     * @return idCanal
     */
    public int getIdCanal() {
        return idCanal;
    }


    /**
     * Sets the idCanal value for this Tienda.
     * 
     * @param idCanal
     */
    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }


    /**
     * Gets the nombreCanal value for this Tienda.
     * 
     * @return nombreCanal
     */
    public java.lang.String getNombreCanal() {
        return nombreCanal;
    }


    /**
     * Sets the nombreCanal value for this Tienda.
     * 
     * @param nombreCanal
     */
    public void setNombreCanal(java.lang.String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }


    /**
     * Gets the idTienda value for this Tienda.
     * 
     * @return idTienda
     */
    public int getIdTienda() {
        return idTienda;
    }


    /**
     * Sets the idTienda value for this Tienda.
     * 
     * @param idTienda
     */
    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }


    /**
     * Gets the nombreTienda value for this Tienda.
     * 
     * @return nombreTienda
     */
    public java.lang.String getNombreTienda() {
        return nombreTienda;
    }


    /**
     * Sets the nombreTienda value for this Tienda.
     * 
     * @param nombreTienda
     */
    public void setNombreTienda(java.lang.String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }


    /**
     * Gets the calle value for this Tienda.
     * 
     * @return calle
     */
    public java.lang.String getCalle() {
        return calle;
    }


    /**
     * Sets the calle value for this Tienda.
     * 
     * @param calle
     */
    public void setCalle(java.lang.String calle) {
        this.calle = calle;
    }


    /**
     * Gets the colonia value for this Tienda.
     * 
     * @return colonia
     */
    public java.lang.String getColonia() {
        return colonia;
    }


    /**
     * Sets the colonia value for this Tienda.
     * 
     * @param colonia
     */
    public void setColonia(java.lang.String colonia) {
        this.colonia = colonia;
    }


    /**
     * Gets the poblacion value for this Tienda.
     * 
     * @return poblacion
     */
    public java.lang.String getPoblacion() {
        return poblacion;
    }


    /**
     * Sets the poblacion value for this Tienda.
     * 
     * @param poblacion
     */
    public void setPoblacion(java.lang.String poblacion) {
        this.poblacion = poblacion;
    }


    /**
     * Gets the codigoPostal value for this Tienda.
     * 
     * @return codigoPostal
     */
    public java.lang.String getCodigoPostal() {
        return codigoPostal;
    }


    /**
     * Sets the codigoPostal value for this Tienda.
     * 
     * @param codigoPostal
     */
    public void setCodigoPostal(java.lang.String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }


    /**
     * Gets the latitud value for this Tienda.
     * 
     * @return latitud
     */
    public java.lang.String getLatitud() {
        return latitud;
    }


    /**
     * Sets the latitud value for this Tienda.
     * 
     * @param latitud
     */
    public void setLatitud(java.lang.String latitud) {
        this.latitud = latitud;
    }


    /**
     * Gets the longitud value for this Tienda.
     * 
     * @return longitud
     */
    public java.lang.String getLongitud() {
        return longitud;
    }


    /**
     * Sets the longitud value for this Tienda.
     * 
     * @param longitud
     */
    public void setLongitud(java.lang.String longitud) {
        this.longitud = longitud;
    }


    /**
     * Gets the telefono value for this Tienda.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this Tienda.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tienda)) return false;
        Tienda other = (Tienda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.idPais == other.getIdPais() &&
            this.idCanal == other.getIdCanal() &&
            ((this.nombreCanal==null && other.getNombreCanal()==null) || 
             (this.nombreCanal!=null &&
              this.nombreCanal.equals(other.getNombreCanal()))) &&
            this.idTienda == other.getIdTienda() &&
            ((this.nombreTienda==null && other.getNombreTienda()==null) || 
             (this.nombreTienda!=null &&
              this.nombreTienda.equals(other.getNombreTienda()))) &&
            ((this.calle==null && other.getCalle()==null) || 
             (this.calle!=null &&
              this.calle.equals(other.getCalle()))) &&
            ((this.colonia==null && other.getColonia()==null) || 
             (this.colonia!=null &&
              this.colonia.equals(other.getColonia()))) &&
            ((this.poblacion==null && other.getPoblacion()==null) || 
             (this.poblacion!=null &&
              this.poblacion.equals(other.getPoblacion()))) &&
            ((this.codigoPostal==null && other.getCodigoPostal()==null) || 
             (this.codigoPostal!=null &&
              this.codigoPostal.equals(other.getCodigoPostal()))) &&
            ((this.latitud==null && other.getLatitud()==null) || 
             (this.latitud!=null &&
              this.latitud.equals(other.getLatitud()))) &&
            ((this.longitud==null && other.getLongitud()==null) || 
             (this.longitud!=null &&
              this.longitud.equals(other.getLongitud()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += getIdPais();
        _hashCode += getIdCanal();
        if (getNombreCanal() != null) {
            _hashCode += getNombreCanal().hashCode();
        }
        _hashCode += getIdTienda();
        if (getNombreTienda() != null) {
            _hashCode += getNombreTienda().hashCode();
        }
        if (getCalle() != null) {
            _hashCode += getCalle().hashCode();
        }
        if (getColonia() != null) {
            _hashCode += getColonia().hashCode();
        }
        if (getPoblacion() != null) {
            _hashCode += getPoblacion().hashCode();
        }
        if (getCodigoPostal() != null) {
            _hashCode += getCodigoPostal().hashCode();
        }
        if (getLatitud() != null) {
            _hashCode += getLatitud().hashCode();
        }
        if (getLongitud() != null) {
            _hashCode += getLongitud().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tienda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Tienda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "IdPais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "IdCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "NombreCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTienda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "IdTienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTienda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "NombreTienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Calle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colonia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Colonia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("poblacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Poblacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPostal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "CodigoPostal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latitud");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Latitud"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("longitud");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Longitud"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
