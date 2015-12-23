/**
 * GetReportFileUrlResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class GetReportFileUrlResponse  implements java.io.Serializable {
    private java.lang.String reportFilePath;

    public GetReportFileUrlResponse() {
    }

    public GetReportFileUrlResponse(
           java.lang.String reportFilePath) {
           this.reportFilePath = reportFilePath;
    }


    /**
     * Gets the reportFilePath value for this GetReportFileUrlResponse.
     * 
     * @return reportFilePath
     */
    public java.lang.String getReportFilePath() {
        return reportFilePath;
    }


    /**
     * Sets the reportFilePath value for this GetReportFileUrlResponse.
     * 
     * @param reportFilePath
     */
    public void setReportFilePath(java.lang.String reportFilePath) {
        this.reportFilePath = reportFilePath;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetReportFileUrlResponse)) return false;
        GetReportFileUrlResponse other = (GetReportFileUrlResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reportFilePath==null && other.getReportFilePath()==null) || 
             (this.reportFilePath!=null &&
              this.reportFilePath.equals(other.getReportFilePath())));
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
        if (getReportFilePath() != null) {
            _hashCode += getReportFilePath().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetReportFileUrlResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportFileUrlResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportFilePath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportFilePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
