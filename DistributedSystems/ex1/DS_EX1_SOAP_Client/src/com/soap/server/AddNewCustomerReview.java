/**
 * AddNewCustomerReview.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soap.server;

public class AddNewCustomerReview  implements java.io.Serializable {
    private int arg0;

    private int arg1;

    private java.lang.String arg2;

    public AddNewCustomerReview() {
    }

    public AddNewCustomerReview(
           int arg0,
           int arg1,
           java.lang.String arg2) {
           this.arg0 = arg0;
           this.arg1 = arg1;
           this.arg2 = arg2;
    }


    /**
     * Gets the arg0 value for this AddNewCustomerReview.
     * 
     * @return arg0
     */
    public int getArg0() {
        return arg0;
    }


    /**
     * Sets the arg0 value for this AddNewCustomerReview.
     * 
     * @param arg0
     */
    public void setArg0(int arg0) {
        this.arg0 = arg0;
    }


    /**
     * Gets the arg1 value for this AddNewCustomerReview.
     * 
     * @return arg1
     */
    public int getArg1() {
        return arg1;
    }


    /**
     * Sets the arg1 value for this AddNewCustomerReview.
     * 
     * @param arg1
     */
    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }


    /**
     * Gets the arg2 value for this AddNewCustomerReview.
     * 
     * @return arg2
     */
    public java.lang.String getArg2() {
        return arg2;
    }


    /**
     * Sets the arg2 value for this AddNewCustomerReview.
     * 
     * @param arg2
     */
    public void setArg2(java.lang.String arg2) {
        this.arg2 = arg2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddNewCustomerReview)) return false;
        AddNewCustomerReview other = (AddNewCustomerReview) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.arg0 == other.getArg0() &&
            this.arg1 == other.getArg1() &&
            ((this.arg2==null && other.getArg2()==null) || 
             (this.arg2!=null &&
              this.arg2.equals(other.getArg2())));
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
        _hashCode += getArg0();
        _hashCode += getArg1();
        if (getArg2() != null) {
            _hashCode += getArg2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddNewCustomerReview.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.soap.com/", "addNewCustomerReview"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arg0");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arg0"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arg1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arg1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arg2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arg2"));
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
