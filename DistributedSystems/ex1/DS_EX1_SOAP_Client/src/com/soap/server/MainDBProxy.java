package com.soap.server;

public class MainDBProxy implements com.soap.server.MainDB {
  private String _endpoint = null;
  private com.soap.server.MainDB mainDB = null;
  
  public MainDBProxy() {
    _initMainDBProxy();
  }
  
  public MainDBProxy(String endpoint) {
    _endpoint = endpoint;
    _initMainDBProxy();
  }
  
  private void _initMainDBProxy() {
    try {
      mainDB = (new com.soap.server.MainDBServiceLocator()).getMainDBPort();
      if (mainDB != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mainDB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mainDB)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mainDB != null)
      ((javax.xml.rpc.Stub)mainDB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.soap.server.MainDB getMainDB() {
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB;
  }
  
  public com.soap.server.CustomerReview addNewCustomerReview(int arg0, int arg1, java.lang.String arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.addNewCustomerReview(arg0, arg1, arg2);
  }
  
  public com.soap.server.CustomerReview getCustomerReviewByID(int arg0, int arg1) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getCustomerReviewByID(arg0, arg1);
  }
  
  public com.soap.server.CustomerReview updateCustomerReview(int arg0, int arg1, int arg2, java.lang.String arg3) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.updateCustomerReview(arg0, arg1, arg2, arg3);
  }
  
  public com.soap.server.Product[] getAllProductsByCategory(java.lang.String arg0) throws java.rmi.RemoteException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllProductsByCategory(arg0);
  }
  
  public double getAverageRatingOfProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAverageRatingOfProduct(arg0);
  }
  
  public com.soap.server.Product[] getAllProductsByMaxPrice(int arg0) throws java.rmi.RemoteException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllProductsByMaxPrice(arg0);
  }
  
  public void deleteCustomerReview(int arg0, int arg1) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    mainDB.deleteCustomerReview(arg0, arg1);
  }
  
  public com.soap.server.Pair[] getProductIDsAndStoreIDsBought(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getProductIDsAndStoreIDsBought(arg0);
  }
  
  public com.soap.server.CustomerReview[] getAllCustomerReviews(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllCustomerReviews(arg0);
  }
  
  public void linkStoreAndProduct(int arg0, int arg1, int arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    mainDB.linkStoreAndProduct(arg0, arg1, arg2);
  }
  
  public com.soap.server.Pair[] getProductIDsAndStoreIDsFromCart(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getProductIDsAndStoreIDsFromCart(arg0);
  }
  
  public com.soap.server.Pair[] getAllProductsAndPricesInStore(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllProductsAndPricesInStore(arg0);
  }
  
  public com.soap.server.User updateUser(int arg0, java.lang.String arg1) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.updateUser(arg0, arg1);
  }
  
  public void deleteProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    mainDB.deleteProduct(arg0);
  }
  
  public com.soap.server.User[] getAllUsers() throws java.rmi.RemoteException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllUsers();
  }
  
  public com.soap.server.User getUserByID(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getUserByID(arg0);
  }
  
  public com.soap.server.Product getProductByID(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getProductByID(arg0);
  }
  
  public com.soap.server.Product addNewProduct(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.addNewProduct(arg0, arg1, arg2);
  }
  
  public com.soap.server.Product updateProduct(int arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.updateProduct(arg0, arg1, arg2, arg3);
  }
  
  public void deleteFromCart(int arg0, int arg1, int arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    mainDB.deleteFromCart(arg0, arg1, arg2);
  }
  
  public com.soap.server.Store[] getAllStores() throws java.rmi.RemoteException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllStores();
  }
  
  public com.soap.server.Store getStoreByID(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getStoreByID(arg0);
  }
  
  public int payForUserCart(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.payForUserCart(arg0);
  }
  
  public com.soap.server.Store updateStore(int arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.updateStore(arg0, arg1, arg2);
  }
  
  public com.soap.server.Store addNewStore(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.addNewStore(arg0, arg1);
  }
  
  public void deleteStore(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    mainDB.deleteStore(arg0);
  }
  
  public void addToCart(int arg0, int arg1, int arg2) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    mainDB.addToCart(arg0, arg1, arg2);
  }
  
  public com.soap.server.User addNewUser(java.lang.String arg0) throws java.rmi.RemoteException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.addNewUser(arg0);
  }
  
  public void deleteUser(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    mainDB.deleteUser(arg0);
  }
  
  public com.soap.server.Pair[] getAllStoresAndPricesBySpecificProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllStoresAndPricesBySpecificProduct(arg0);
  }
  
  public int[] getAllUserIDsThatBoughtTheProduct(int arg0) throws java.rmi.RemoteException, com.soap.server.DatabaseException{
    if (mainDB == null)
      _initMainDBProxy();
    return mainDB.getAllUserIDsThatBoughtTheProduct(arg0);
  }
  
  
}