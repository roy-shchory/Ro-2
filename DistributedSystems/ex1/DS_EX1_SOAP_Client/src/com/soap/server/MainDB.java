/**
 * MainDB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soap.server;

public interface MainDB extends java.rmi.Remote {
    public com.soap.server.CustomerReview addNewCustomerReview(int arg0, int arg1, java.lang.String arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.CustomerReview getCustomerReviewByID(int arg0, int arg1) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.CustomerReview updateCustomerReview(int arg0, int arg1, int arg2, java.lang.String arg3) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Product[] getAllProductsByCategory(java.lang.String arg0) throws java.rmi.RemoteException;
    public double getAverageRatingOfProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Product[] getAllProductsByMaxPrice(int arg0) throws java.rmi.RemoteException;
    public void deleteCustomerReview(int arg0, int arg1) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Pair[] getProductIDsAndStoreIDsBought(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.CustomerReview[] getAllCustomerReviews(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public void linkStoreAndProduct(int arg0, int arg1, int arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Pair[] getProductIDsAndStoreIDsFromCart(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Pair[] getAllProductsAndPricesInStore(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.User updateUser(int arg0, java.lang.String arg1) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public void deleteProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.User[] getAllUsers() throws java.rmi.RemoteException;
    public com.soap.server.User getUserByID(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Product getProductByID(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Product addNewProduct(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public com.soap.server.Product updateProduct(int arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public void deleteFromCart(int arg0, int arg1, int arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Store[] getAllStores() throws java.rmi.RemoteException;
    public com.soap.server.Store getStoreByID(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public int payForUserCart(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Store updateStore(int arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Store addNewStore(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public void deleteStore(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public void addToCart(int arg0, int arg1, int arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.User addNewUser(java.lang.String arg0) throws java.rmi.RemoteException;
    public void deleteUser(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public com.soap.server.Pair[] getAllStoresAndPricesBySpecificProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
    public int[] getAllUserIDsThatBoughtTheProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException;
}
