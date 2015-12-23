/**
 * RealTimeResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class RealTimeResultType  implements java.io.Serializable {
    private java.lang.Long ID;

    private java.lang.String[] name;

    private java.lang.String date;

    private java.lang.String[] KPIs;

    private java.lang.Long relatedId;

    public RealTimeResultType() {
    }

    public RealTimeResultType(
           java.lang.Long ID,
           java.lang.String[] name,
           java.lang.String date,
           java.lang.String[] KPIs,
           java.lang.Long relatedId) {
           this.ID = ID;
           this.name = name;
           this.date = date;
           this.KPIs = KPIs;
           this.relatedId = relatedId;
    }


    /**
     * Gets the ID value for this RealTimeResultType.
     * 
     * @return ID
     */
    public java.lang.Long getID() {
        return ID;
    }


    /**
     * Sets the ID value for this RealTimeResultType.
     * 
     * @param ID
     */
    public void setID(java.lang.Long ID) {
        this.ID = ID;
    }


    /**
     * Gets the name value for this RealTimeResultType.
     * 
     * @return name
     */
    public java.lang.String[] getName() {
        return name;
    }


    /**
     * Sets the name value for this RealTimeResultType.
     * 
     * @param name
     */
    public void setName(java.lang.String[] name) {
        this.name = name;
    }

    public java.lang.String getName(int i) {
        return this.name[i];
    }

    public void setName(int i, java.lang.String _value) {
        this.name[i] = _value;
    }


    /**
     * Gets the date value for this RealTimeResultType.
     * 
     * @return date
     */
    public java.lang.String getDate() {
        return date;
    }


    /**
     * Sets the date value for this RealTimeResultType.
     * 
     * @param date
     */
    public void setDate(java.lang.String date) {
        this.date = date;
    }


    /**
     * Gets the KPIs value for this RealTimeResultType.
     * 
     * @return KPIs
     */
    public java.lang.String[] getKPIs() {
        return KPIs;
    }


    /**
     * Sets the KPIs value for this RealTimeResultType.
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


    /**
     * Gets the relatedId value for this RealTimeResultType.
     * 
     * @return relatedId
     */
    public java.lang.Long getRelatedId() {
        return relatedId;
    }


    /**
     * Sets the relatedId value for this RealTimeResultType.
     * 
     * @param relatedId
     */
    public void setRelatedId(java.lang.Long relatedId) {
        this.relatedId = relatedId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RealTimeResultType)) return false;
        RealTimeResultType other = (RealTimeResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              java.util.Arrays.equals(this.name, other.getName()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.KPIs==null && other.getKPIs()==null) || 
             (this.KPIs!=null &&
              java.util.Arrays.equals(this.KPIs, other.getKPIs()))) &&
            ((this.relatedId==null && other.getRelatedId()==null) || 
             (this.relatedId!=null &&
              this.relatedId.equals(other.getRelatedId())));
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getName(), i);
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
        if (getRelatedId() != null) {
            _hashCode += getRelatedId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RealTimeResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relatedId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
