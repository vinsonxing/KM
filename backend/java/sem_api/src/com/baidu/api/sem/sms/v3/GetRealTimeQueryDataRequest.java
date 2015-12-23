/**
 * GetRealTimeQueryDataRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class GetRealTimeQueryDataRequest  implements java.io.Serializable {
    private com.baidu.api.sem.sms.v3.RealTimeQueryRequestType realTimeQueryRequestTypes;

    public GetRealTimeQueryDataRequest() {
    }

    public GetRealTimeQueryDataRequest(
           com.baidu.api.sem.sms.v3.RealTimeQueryRequestType realTimeQueryRequestTypes) {
           this.realTimeQueryRequestTypes = realTimeQueryRequestTypes;
    }


    /**
     * Gets the realTimeQueryRequestTypes value for this GetRealTimeQueryDataRequest.
     * 
     * @return realTimeQueryRequestTypes
     */
    public com.baidu.api.sem.sms.v3.RealTimeQueryRequestType getRealTimeQueryRequestTypes() {
        return realTimeQueryRequestTypes;
    }


    /**
     * Sets the realTimeQueryRequestTypes value for this GetRealTimeQueryDataRequest.
     * 
     * @param realTimeQueryRequestTypes
     */
    public void setRealTimeQueryRequestTypes(com.baidu.api.sem.sms.v3.RealTimeQueryRequestType realTimeQueryRequestTypes) {
        this.realTimeQueryRequestTypes = realTimeQueryRequestTypes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRealTimeQueryDataRequest)) return false;
        GetRealTimeQueryDataRequest other = (GetRealTimeQueryDataRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.realTimeQueryRequestTypes==null && other.getRealTimeQueryRequestTypes()==null) || 
             (this.realTimeQueryRequestTypes!=null &&
              this.realTimeQueryRequestTypes.equals(other.getRealTimeQueryRequestTypes())));
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
        if (getRealTimeQueryRequestTypes() != null) {
            _hashCode += getRealTimeQueryRequestTypes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRealTimeQueryDataRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeQueryDataRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("realTimeQueryRequestTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "realTimeQueryRequestTypes"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeQueryRequestType"));
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
