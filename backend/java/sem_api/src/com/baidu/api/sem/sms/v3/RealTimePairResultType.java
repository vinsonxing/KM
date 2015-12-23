/**
 * RealTimePairResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class RealTimePairResultType  implements java.io.Serializable {
    private java.lang.Long keywordId;

    private java.lang.Long creativeId;

    private java.lang.String[] pairInfo;

    private java.lang.String date;

    private java.lang.String[] KPIs;

    public RealTimePairResultType() {
    }

    public RealTimePairResultType(
           java.lang.Long keywordId,
           java.lang.Long creativeId,
           java.lang.String[] pairInfo,
           java.lang.String date,
           java.lang.String[] KPIs) {
           this.keywordId = keywordId;
           this.creativeId = creativeId;
           this.pairInfo = pairInfo;
           this.date = date;
           this.KPIs = KPIs;
    }


    /**
     * Gets the keywordId value for this RealTimePairResultType.
     * 
     * @return keywordId
     */
    public java.lang.Long getKeywordId() {
        return keywordId;
    }


    /**
     * Sets the keywordId value for this RealTimePairResultType.
     * 
     * @param keywordId
     */
    public void setKeywordId(java.lang.Long keywordId) {
        this.keywordId = keywordId;
    }


    /**
     * Gets the creativeId value for this RealTimePairResultType.
     * 
     * @return creativeId
     */
    public java.lang.Long getCreativeId() {
        return creativeId;
    }


    /**
     * Sets the creativeId value for this RealTimePairResultType.
     * 
     * @param creativeId
     */
    public void setCreativeId(java.lang.Long creativeId) {
        this.creativeId = creativeId;
    }


    /**
     * Gets the pairInfo value for this RealTimePairResultType.
     * 
     * @return pairInfo
     */
    public java.lang.String[] getPairInfo() {
        return pairInfo;
    }


    /**
     * Sets the pairInfo value for this RealTimePairResultType.
     * 
     * @param pairInfo
     */
    public void setPairInfo(java.lang.String[] pairInfo) {
        this.pairInfo = pairInfo;
    }

    public java.lang.String getPairInfo(int i) {
        return this.pairInfo[i];
    }

    public void setPairInfo(int i, java.lang.String _value) {
        this.pairInfo[i] = _value;
    }


    /**
     * Gets the date value for this RealTimePairResultType.
     * 
     * @return date
     */
    public java.lang.String getDate() {
        return date;
    }


    /**
     * Sets the date value for this RealTimePairResultType.
     * 
     * @param date
     */
    public void setDate(java.lang.String date) {
        this.date = date;
    }


    /**
     * Gets the KPIs value for this RealTimePairResultType.
     * 
     * @return KPIs
     */
    public java.lang.String[] getKPIs() {
        return KPIs;
    }


    /**
     * Sets the KPIs value for this RealTimePairResultType.
     * 
     * @param KPIs
     */
    public void setKPIs(java.lang.String[] KPIs) {
        this.KPIs = KPIs;
    }

    public java.lang.String getKPIs(int i) {
        return this.KPIs[i];
    }

    public void setKPIs(int i, java.lang.String _value) {
        this.KPIs[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RealTimePairResultType)) return false;
        RealTimePairResultType other = (RealTimePairResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.keywordId==null && other.getKeywordId()==null) || 
             (this.keywordId!=null &&
              this.keywordId.equals(other.getKeywordId()))) &&
            ((this.creativeId==null && other.getCreativeId()==null) || 
             (this.creativeId!=null &&
              this.creativeId.equals(other.getCreativeId()))) &&
            ((this.pairInfo==null && other.getPairInfo()==null) || 
             (this.pairInfo!=null &&
              java.util.Arrays.equals(this.pairInfo, other.getPairInfo()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.KPIs==null && other.getKPIs()==null) || 
             (this.KPIs!=null &&
              java.util.Arrays.equals(this.KPIs, other.getKPIs())));
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
        if (getKeywordId() != null) {
            _hashCode += getKeywordId().hashCode();
        }
        if (getCreativeId() != null) {
            _hashCode += getCreativeId().hashCode();
        }
        if (getPairInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPairInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPairInfo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getKPIs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKPIs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKPIs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RealTimePairResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimePairResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keywordId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keywordId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creativeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creativeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pairInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pairInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KPIs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KPIs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
