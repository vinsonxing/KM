/**
 * RealTimeQueryResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class RealTimeQueryResultType  implements java.io.Serializable {
    private java.lang.String query;

    private java.lang.Long keywordId;

    private java.lang.Long creativeId;

    private java.lang.String[] queryInfo;

    private java.lang.String date;

    private java.lang.String[] KPIs;

    public RealTimeQueryResultType() {
    }

    public RealTimeQueryResultType(
           java.lang.String query,
           java.lang.Long keywordId,
           java.lang.Long creativeId,
           java.lang.String[] queryInfo,
           java.lang.String date,
           java.lang.String[] KPIs) {
           this.query = query;
           this.keywordId = keywordId;
           this.creativeId = creativeId;
           this.queryInfo = queryInfo;
           this.date = date;
           this.KPIs = KPIs;
    }


    /**
     * Gets the query value for this RealTimeQueryResultType.
     * 
     * @return query
     */
    public java.lang.String getQuery() {
        return query;
    }


    /**
     * Sets the query value for this RealTimeQueryResultType.
     * 
     * @param query
     */
    public void setQuery(java.lang.String query) {
        this.query = query;
    }


    /**
     * Gets the keywordId value for this RealTimeQueryResultType.
     * 
     * @return keywordId
     */
    public java.lang.Long getKeywordId() {
        return keywordId;
    }


    /**
     * Sets the keywordId value for this RealTimeQueryResultType.
     * 
     * @param keywordId
     */
    public void setKeywordId(java.lang.Long keywordId) {
        this.keywordId = keywordId;
    }


    /**
     * Gets the creativeId value for this RealTimeQueryResultType.
     * 
     * @return creativeId
     */
    public java.lang.Long getCreativeId() {
        return creativeId;
    }


    /**
     * Sets the creativeId value for this RealTimeQueryResultType.
     * 
     * @param creativeId
     */
    public void setCreativeId(java.lang.Long creativeId) {
        this.creativeId = creativeId;
    }


    /**
     * Gets the queryInfo value for this RealTimeQueryResultType.
     * 
     * @return queryInfo
     */
    public java.lang.String[] getQueryInfo() {
        return queryInfo;
    }


    /**
     * Sets the queryInfo value for this RealTimeQueryResultType.
     * 
     * @param queryInfo
     */
    public void setQueryInfo(java.lang.String[] queryInfo) {
        this.queryInfo = queryInfo;
    }

    public java.lang.String getQueryInfo(int i) {
        return this.queryInfo[i];
    }

    public void setQueryInfo(int i, java.lang.String _value) {
        this.queryInfo[i] = _value;
    }


    /**
     * Gets the date value for this RealTimeQueryResultType.
     * 
     * @return date
     */
    public java.lang.String getDate() {
        return date;
    }


    /**
     * Sets the date value for this RealTimeQueryResultType.
     * 
     * @param date
     */
    public void setDate(java.lang.String date) {
        this.date = date;
    }


    /**
     * Gets the KPIs value for this RealTimeQueryResultType.
     * 
     * @return KPIs
     */
    public java.lang.String[] getKPIs() {
        return KPIs;
    }


    /**
     * Sets the KPIs value for this RealTimeQueryResultType.
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
        if (!(obj instanceof RealTimeQueryResultType)) return false;
        RealTimeQueryResultType other = (RealTimeQueryResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.query==null && other.getQuery()==null) || 
             (this.query!=null &&
              this.query.equals(other.getQuery()))) &&
            ((this.keywordId==null && other.getKeywordId()==null) || 
             (this.keywordId!=null &&
              this.keywordId.equals(other.getKeywordId()))) &&
            ((this.creativeId==null && other.getCreativeId()==null) || 
             (this.creativeId!=null &&
              this.creativeId.equals(other.getCreativeId()))) &&
            ((this.queryInfo==null && other.getQueryInfo()==null) || 
             (this.queryInfo!=null &&
              java.util.Arrays.equals(this.queryInfo, other.getQueryInfo()))) &&
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
        if (getQuery() != null) {
            _hashCode += getQuery().hashCode();
        }
        if (getKeywordId() != null) {
            _hashCode += getKeywordId().hashCode();
        }
        if (getCreativeId() != null) {
            _hashCode += getCreativeId().hashCode();
        }
        if (getQueryInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQueryInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQueryInfo(), i);
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
        new org.apache.axis.description.TypeDesc(RealTimeQueryResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeQueryResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("", "query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("queryInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "queryInfo"));
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
