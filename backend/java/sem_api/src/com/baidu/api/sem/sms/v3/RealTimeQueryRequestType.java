/**
 * RealTimeQueryRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class RealTimeQueryRequestType  implements java.io.Serializable {
    private java.lang.String[] performanceData;

    private java.util.Calendar startDate;

    private java.util.Calendar endDate;

    private java.lang.Integer levelOfDetails;

    private com.baidu.api.sem.sms.v3.AttributeType[] attributes;

    private int reportType;

    private long[] statIds;

    private java.lang.Integer statRange;

    private java.lang.Integer unitOfTime;

    private java.lang.Integer number;

    private java.lang.Integer device;

    public RealTimeQueryRequestType() {
    }

    public RealTimeQueryRequestType(
           java.lang.String[] performanceData,
           java.util.Calendar startDate,
           java.util.Calendar endDate,
           java.lang.Integer levelOfDetails,
           com.baidu.api.sem.sms.v3.AttributeType[] attributes,
           int reportType,
           long[] statIds,
           java.lang.Integer statRange,
           java.lang.Integer unitOfTime,
           java.lang.Integer number,
           java.lang.Integer device) {
           this.performanceData = performanceData;
           this.startDate = startDate;
           this.endDate = endDate;
           this.levelOfDetails = levelOfDetails;
           this.attributes = attributes;
           this.reportType = reportType;
           this.statIds = statIds;
           this.statRange = statRange;
           this.unitOfTime = unitOfTime;
           this.number = number;
           this.device = device;
    }


    /**
     * Gets the performanceData value for this RealTimeQueryRequestType.
     * 
     * @return performanceData
     */
    public java.lang.String[] getPerformanceData() {
        return performanceData;
    }


    /**
     * Sets the performanceData value for this RealTimeQueryRequestType.
     * 
     * @param performanceData
     */
    public void setPerformanceData(java.lang.String[] performanceData) {
        this.performanceData = performanceData;
    }

    public java.lang.String getPerformanceData(int i) {
        return this.performanceData[i];
    }

    public void setPerformanceData(int i, java.lang.String _value) {
        this.performanceData[i] = _value;
    }


    /**
     * Gets the startDate value for this RealTimeQueryRequestType.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this RealTimeQueryRequestType.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the endDate value for this RealTimeQueryRequestType.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this RealTimeQueryRequestType.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the levelOfDetails value for this RealTimeQueryRequestType.
     * 
     * @return levelOfDetails
     */
    public java.lang.Integer getLevelOfDetails() {
        return levelOfDetails;
    }


    /**
     * Sets the levelOfDetails value for this RealTimeQueryRequestType.
     * 
     * @param levelOfDetails
     */
    public void setLevelOfDetails(java.lang.Integer levelOfDetails) {
        this.levelOfDetails = levelOfDetails;
    }


    /**
     * Gets the attributes value for this RealTimeQueryRequestType.
     * 
     * @return attributes
     */
    public com.baidu.api.sem.sms.v3.AttributeType[] getAttributes() {
        return attributes;
    }


    /**
     * Sets the attributes value for this RealTimeQueryRequestType.
     * 
     * @param attributes
     */
    public void setAttributes(com.baidu.api.sem.sms.v3.AttributeType[] attributes) {
        this.attributes = attributes;
    }

    public com.baidu.api.sem.sms.v3.AttributeType getAttributes(int i) {
        return this.attributes[i];
    }

    public void setAttributes(int i, com.baidu.api.sem.sms.v3.AttributeType _value) {
        this.attributes[i] = _value;
    }


    /**
     * Gets the reportType value for this RealTimeQueryRequestType.
     * 
     * @return reportType
     */
    public int getReportType() {
        return reportType;
    }


    /**
     * Sets the reportType value for this RealTimeQueryRequestType.
     * 
     * @param reportType
     */
    public void setReportType(int reportType) {
        this.reportType = reportType;
    }


    /**
     * Gets the statIds value for this RealTimeQueryRequestType.
     * 
     * @return statIds
     */
    public long[] getStatIds() {
        return statIds;
    }


    /**
     * Sets the statIds value for this RealTimeQueryRequestType.
     * 
     * @param statIds
     */
    public void setStatIds(long[] statIds) {
        this.statIds = statIds;
    }

    public long getStatIds(int i) {
        return this.statIds[i];
    }

    public void setStatIds(int i, long _value) {
        this.statIds[i] = _value;
    }


    /**
     * Gets the statRange value for this RealTimeQueryRequestType.
     * 
     * @return statRange
     */
    public java.lang.Integer getStatRange() {
        return statRange;
    }


    /**
     * Sets the statRange value for this RealTimeQueryRequestType.
     * 
     * @param statRange
     */
    public void setStatRange(java.lang.Integer statRange) {
        this.statRange = statRange;
    }


    /**
     * Gets the unitOfTime value for this RealTimeQueryRequestType.
     * 
     * @return unitOfTime
     */
    public java.lang.Integer getUnitOfTime() {
        return unitOfTime;
    }


    /**
     * Sets the unitOfTime value for this RealTimeQueryRequestType.
     * 
     * @param unitOfTime
     */
    public void setUnitOfTime(java.lang.Integer unitOfTime) {
        this.unitOfTime = unitOfTime;
    }


    /**
     * Gets the number value for this RealTimeQueryRequestType.
     * 
     * @return number
     */
    public java.lang.Integer getNumber() {
        return number;
    }


    /**
     * Sets the number value for this RealTimeQueryRequestType.
     * 
     * @param number
     */
    public void setNumber(java.lang.Integer number) {
        this.number = number;
    }


    /**
     * Gets the device value for this RealTimeQueryRequestType.
     * 
     * @return device
     */
    public java.lang.Integer getDevice() {
        return device;
    }


    /**
     * Sets the device value for this RealTimeQueryRequestType.
     * 
     * @param device
     */
    public void setDevice(java.lang.Integer device) {
        this.device = device;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RealTimeQueryRequestType)) return false;
        RealTimeQueryRequestType other = (RealTimeQueryRequestType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.performanceData==null && other.getPerformanceData()==null) || 
             (this.performanceData!=null &&
              java.util.Arrays.equals(this.performanceData, other.getPerformanceData()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.levelOfDetails==null && other.getLevelOfDetails()==null) || 
             (this.levelOfDetails!=null &&
              this.levelOfDetails.equals(other.getLevelOfDetails()))) &&
            ((this.attributes==null && other.getAttributes()==null) || 
             (this.attributes!=null &&
              java.util.Arrays.equals(this.attributes, other.getAttributes()))) &&
            this.reportType == other.getReportType() &&
            ((this.statIds==null && other.getStatIds()==null) || 
             (this.statIds!=null &&
              java.util.Arrays.equals(this.statIds, other.getStatIds()))) &&
            ((this.statRange==null && other.getStatRange()==null) || 
             (this.statRange!=null &&
              this.statRange.equals(other.getStatRange()))) &&
            ((this.unitOfTime==null && other.getUnitOfTime()==null) || 
             (this.unitOfTime!=null &&
              this.unitOfTime.equals(other.getUnitOfTime()))) &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.device==null && other.getDevice()==null) || 
             (this.device!=null &&
              this.device.equals(other.getDevice())));
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
        if (getPerformanceData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPerformanceData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPerformanceData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getLevelOfDetails() != null) {
            _hashCode += getLevelOfDetails().hashCode();
        }
        if (getAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getReportType();
        if (getStatIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStatRange() != null) {
            _hashCode += getStatRange().hashCode();
        }
        if (getUnitOfTime() != null) {
            _hashCode += getUnitOfTime().hashCode();
        }
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getDevice() != null) {
            _hashCode += getDevice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RealTimeQueryRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeQueryRequestType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("performanceData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "performanceData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("levelOfDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "levelOfDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "AttributeType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statIds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statRange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statRange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitOfTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unitOfTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("device");
        elemField.setXmlName(new javax.xml.namespace.QName("", "device"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
