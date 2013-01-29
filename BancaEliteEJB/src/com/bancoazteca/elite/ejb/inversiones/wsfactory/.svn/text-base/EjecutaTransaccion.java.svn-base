/**
 * EjecutaTransaccion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bancoazteca.elite.ejb.inversiones.wsfactory;

import java.io.Serializable;

public class EjecutaTransaccion implements Serializable{

	private static final long serialVersionUID = 1L;
	private java.lang.String datosEntrada;
    private java.lang.String datosSalida;
    private java.lang.String transaccionTAS;

    public EjecutaTransaccion() {
    }

    public EjecutaTransaccion(
           java.lang.String datosEntrada,
           java.lang.String datosSalida,
           java.lang.String transaccionTAS) {
           this.datosEntrada = datosEntrada;
           this.datosSalida = datosSalida;
           this.transaccionTAS = transaccionTAS;
    }


    /**
     * Gets the datosEntrada value for this EjecutaTransaccion.
     * 
     * @return datosEntrada
     */
    public java.lang.String getDatosEntrada() {
        return datosEntrada;
    }


    /**
     * Sets the datosEntrada value for this EjecutaTransaccion.
     * 
     * @param datosEntrada
     */
    public void setDatosEntrada(java.lang.String datosEntrada) {
        this.datosEntrada = datosEntrada;
    }


    /**
     * Gets the datosSalida value for this EjecutaTransaccion.
     * 
     * @return datosSalida
     */
    public java.lang.String getDatosSalida() {
        return datosSalida;
    }


    /**
     * Sets the datosSalida value for this EjecutaTransaccion.
     * 
     * @param datosSalida
     */
    public void setDatosSalida(java.lang.String datosSalida) {
        this.datosSalida = datosSalida;
    }


    /**
     * Gets the transaccionTAS value for this EjecutaTransaccion.
     * 
     * @return transaccionTAS
     */
    public java.lang.String getTransaccionTAS() {
        return transaccionTAS;
    }


    /**
     * Sets the transaccionTAS value for this EjecutaTransaccion.
     * 
     * @param transaccionTAS
     */
    public void setTransaccionTAS(java.lang.String transaccionTAS) {
        this.transaccionTAS = transaccionTAS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EjecutaTransaccion)) return false;
        EjecutaTransaccion other = (EjecutaTransaccion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosEntrada==null && other.getDatosEntrada()==null) || 
             (this.datosEntrada!=null &&
              this.datosEntrada.equals(other.getDatosEntrada()))) &&
            ((this.datosSalida==null && other.getDatosSalida()==null) || 
             (this.datosSalida!=null &&
              this.datosSalida.equals(other.getDatosSalida()))) &&
            ((this.transaccionTAS==null && other.getTransaccionTAS()==null) || 
             (this.transaccionTAS!=null &&
              this.transaccionTAS.equals(other.getTransaccionTAS())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDatosEntrada() != null) {
            _hashCode += getDatosEntrada().hashCode();
        }
        if (getDatosSalida() != null) {
            _hashCode += getDatosSalida().hashCode();
        }
        if (getTransaccionTAS() != null) {
            _hashCode += getTransaccionTAS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EjecutaTransaccion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.tas.mx.org/", "ejecutaTransaccion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datosEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosSalida");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datosSalida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaccionTAS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transaccionTAS"));
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
