/**
 * MainDBService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soap.server;

public interface MainDBService extends javax.xml.rpc.Service {
    public java.lang.String getMainDBPortAddress();

    public com.soap.server.MainDB getMainDBPort() throws javax.xml.rpc.ServiceException;

    public com.soap.server.MainDB getMainDBPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
