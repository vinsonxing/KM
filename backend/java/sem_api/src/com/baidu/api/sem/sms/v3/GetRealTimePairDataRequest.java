/**
 * GetRealTimePairDataRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class GetRealTimePairDataRequest  implements java.io.Serializable {
    private com.baidu.api.sem.sms.v3.RealTimeRequestType realTimePairRequestTypes;

    public GetRealTimePairDataRequest() {
    }

    public GetRealTimePairDataRequest(
           com.baidu.api.sem.sms.v3.RealTimeRequestType realTimePairRequestTypes) {
           this.realTimePairRequestTypes = realTimePairRequestTypes;
    }


    /**
     * Gets the realTimePairRequestTypes value for this GetRealTimePairDataRequest.
     * 
     * @return realTimePairRequestTypes
     */
    public com.baidu.api.sem.sms.v3.RealTimeRequestType getRealTimePairRequestTypes() {
        return realTimePairRequestTypes;
    }


    /**
     * Sets the realTimePairRequestTypes value for this GetRealTimePairDataRequest.
     * 
     * @param realTimePairRequestTypes
     */
    public void setRealTimePairRequestTypes(com.baidu.api.sem.sms.v3.RealTimeRequestType realTimePairRequestTypes) {
        this.realTimePairRequestTypes = realTimePairRequestTypes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRealTimePairDataRequest)) return false;
        GetRealTimePairDataRequest other = (GetRealTimePairDataRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.realTimePairRequestTypes==null && other.getRealTimePairRequestTypes()==null) || 
             (this.realTimePairRequestTypes!=null &&
              this.realTimePairRequestTypes.equals(other.getRealTimePairRequestTypes())));
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
        if (getRealTimePairRequestTypes() != null) {
            _hashCode += getRealTimePairRequestTypes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRealTimePairDataRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimePairDataRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("realTimePairRequestTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "realTimePairRequestTypes"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeRequestType"));
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
