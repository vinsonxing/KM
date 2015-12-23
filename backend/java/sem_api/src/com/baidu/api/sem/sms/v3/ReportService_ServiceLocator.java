/**
 * ReportService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class ReportService_ServiceLocator extends org.apache.axis.client.Service implements com.baidu.api.sem.sms.v3.ReportService_Service {

    public ReportService_ServiceLocator() {
    }


    public ReportService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReportService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReportService
    private java.lang.String ReportService_address = "https://api.baidu.com/sem/sms/v3/ReportService";

    public java.lang.String getReportServiceAddress() {
        return ReportService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReportServiceWSDDServiceName = "ReportService";

    public java.lang.String getReportServiceWSDDServiceName() {
        return ReportServiceWSDDServiceName;
    }

    public void setReportServiceWSDDServiceName(java.lang.String name) {
        ReportServiceWSDDServiceName = name;
    }

    public com.baidu.api.sem.sms.v3.ReportService_PortType getReportService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReportService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReportService(endpoint);
    }

    public com.baidu.api.sem.sms.v3.ReportService_PortType getReportService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.baidu.api.sem.sms.v3.ReportServiceSOAPStub _stub = new com.baidu.api.sem.sms.v3.ReportServiceSOAPStub(portAddress, this);
            _stub.setPortName(getReportServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReportServiceEndpointAddress(java.lang.String address) {
        ReportService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.baidu.api.sem.sms.v3.ReportService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.baidu.api.sem.sms.v3.ReportServiceSOAPStub _stub = new com.baidu.api.sem.sms.v3.ReportServiceSOAPStub(new java.net.URL(ReportService_address), this);
                _stub.setPortName(getReportServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ReportService".equals(inputPortName)) {
            return getReportService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "ReportService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "ReportService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReportService".equals(portName)) {
            setReportServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
