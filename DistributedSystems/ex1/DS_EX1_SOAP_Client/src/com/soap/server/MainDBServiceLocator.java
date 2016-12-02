/**
 * MainDBServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soap.server;

public class MainDBServiceLocator extends org.apache.axis.client.Service implements com.soap.server.MainDBService {

    public MainDBServiceLocator() {
    }


    public MainDBServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MainDBServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MainDBPort
    private java.lang.String MainDBPort_address = "http://ron-laptop:8080/DS_EX1_SOAP_Server/MainDBService";

    public java.lang.String getMainDBPortAddress() {
        return MainDBPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MainDBPortWSDDServiceName = "MainDBPort";

    public java.lang.String getMainDBPortWSDDServiceName() {
        return MainDBPortWSDDServiceName;
    }

    public void setMainDBPortWSDDServiceName(java.lang.String name) {
        MainDBPortWSDDServiceName = name;
    }

    public com.soap.server.MainDB getMainDBPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MainDBPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMainDBPort(endpoint);
    }

    public com.soap.server.MainDB getMainDBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.soap.server.MainDBPortBindingStub _stub = new com.soap.server.MainDBPortBindingStub(portAddress, this);
            _stub.setPortName(getMainDBPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMainDBPortEndpointAddress(java.lang.String address) {
        MainDBPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.soap.server.MainDB.class.isAssignableFrom(serviceEndpointInterface)) {
                com.soap.server.MainDBPortBindingStub _stub = new com.soap.server.MainDBPortBindingStub(new java.net.URL(MainDBPort_address), this);
                _stub.setPortName(getMainDBPortWSDDServiceName());
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
        if ("MainDBPort".equals(inputPortName)) {
            return getMainDBPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://server.soap.com/", "MainDBService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://server.soap.com/", "MainDBPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MainDBPort".equals(portName)) {
            setMainDBPortEndpointAddress(address);
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
