/**
 * Estado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bancoazteca.elite.WsInformacionTiendas;

public class Estado  implements java.io.Serializable {
    private int idEstado;

    private java.lang.String nombreEstado;

    public Estado() {
    }

    public Estado(
           int idEstado,
           java.lang.String nombreEstado) {
           this.idEstado = idEstado;
           this.nombreEstado = nombreEstado;
    }


    /**
     * Gets the idEstado value for this Estado.
     * 
     * @return idEstado
     */
    public int getIdEstado() {
        return idEstado;
    }


    /**
     * Sets the idEstado value for this Estado.
     * 
     * @param idEstado
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }


    /**
     * Gets the nombreEstado value for this Estado.
     * 
     * @return nombreEstado
     */
    public java.lang.String getNombreEstado() {
        return nombreEstado;
    }


    /**
     * Sets the nombreEstado value for this Estado.
     * 
     * @param nombreEstado
     */
    public void setNombreEstado(java.lang.String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Estado)) return false;
        Estado other = (Estado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idEstado == other.getIdEstado() &&
            ((this.nombreEstado==null && other.getNombreEstado()==null) || 
             (this.nombreEstado!=null &&
              this.nombreEstado.equals(other.getNombreEstado())));
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
        _hashCode += getIdEstado();
        if (getNombreEstado() != null) {
            _hashCode += getNombreEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Estado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "Estado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "IdEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.elektra.com.mx/WsInformacionTiendas/", "NombreEstado"));
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
