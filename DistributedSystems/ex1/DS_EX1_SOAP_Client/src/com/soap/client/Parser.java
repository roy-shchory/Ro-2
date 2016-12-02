package com.soap.client;

import java.rmi.RemoteException;
import java.util.Arrays;
import com.soap.server.*;

public class Parser {
	public static String parseCmd(String cmd, MainDB mainDB) throws DatabaseException, RemoteException {
		String[] parts = cmd.split(" ");
		
		switch (parts[0]) {
		case "add":
			return cmd_add(parts, mainDB);
			
		case "get":
			return cmd_get(parts, mainDB);
			
		case "update":
			return cmd_update(parts, mainDB);
			
		case "delete":
			return cmd_delete(parts, mainDB);
			
		case "getAll":
			return cmd_getAll(parts, mainDB);
			
		case "getStoresOfProduct":
			return cmd_getStoresOfProduct(parts, mainDB);
			
		case "getProductsOfStore":
			return cmd_getProductsOfStore(parts, mainDB);
			
		case "getAvgRating":
			return cmd_getAvgRating(parts, mainDB);
			
		case "linkStoreToProduct":
			return cmd_linkStoreToProduct(parts, mainDB);
			
		case "addCart":
			return cmd_addCart(parts, mainDB);
			
		case "removeCart":
			return cmd_removeCart(parts, mainDB);
			
		case "payCart":
			return cmd_payCart(parts, mainDB);
			
		case "getCart":
			return cmd_getCart(parts, mainDB);
			
		case "getHistory":
			return cmd_getHistory(parts, mainDB);
			
		case "getHistoryProduct":
			return cmd_getHistoryProduct(parts, mainDB);

		default:
			break;
		}
		
		return null;
	}
	
	private static String cmd_add(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		String ret = "Added " + parts[1] + " ";
		
		switch (parts[1]) {
		case "user":
			ret += obj2str(mainDB.addNewUser(parts[2]));
			break;			
		case "store":
			ret += obj2str(mainDB.addNewStore(parts[2], parts[3]));
			break;			
		case "product":
			ret += obj2str(mainDB.addNewProduct(parts[2], parts[3], getStringFrom(parts, 4)));
			break;
		case "customerReview":
			ret += obj2str(mainDB.addNewCustomerReview(str2num(parts[2]), str2num(parts[3]), getStringFrom(parts, 4)));
			break;
		default:
			return null;
		}
		return ret;
	}
	private static String cmd_get(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		String ret = "got " + parts[1] + " ";
		int id = str2num(parts[2]);
		
		switch (parts[1]) {
		case "user":
			ret += obj2str(mainDB.getUserByID(id));
			break;			
		case "store":
			ret += obj2str(mainDB.getStoreByID(id));
			break;			
		case "product":
			ret += obj2str(mainDB.getProductByID(id));
			break;
		case "customerReview":
			ret += obj2str(mainDB.getCustomerReviewByID(id, str2num(parts[3])));
			break;
		default:
			return null;
		}
		return ret;
	}
	private static String cmd_update(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		String ret = "Updated " + parts[1] + " ";
		int id = str2num(parts[2]);
		
		switch (parts[1]) {
		case "user":
			ret += obj2str(mainDB.updateUser(id, parts[3]));
			break;			
		case "store":
			ret += obj2str(mainDB.updateStore(id, parts[3], parts[4]));
			break;			
		case "product":
			ret += obj2str(mainDB.updateProduct(id, parts[3], parts[4], getStringFrom(parts, 5)));
			break;
		case "customerReview":
			ret += obj2str(mainDB.updateCustomerReview(id, str2num(parts[3]), str2num(parts[4]), getStringFrom(parts, 5)));
			break;
		default:
			return null;
		}
		return ret;
	}
	private static String cmd_delete(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int id = str2num(parts[2]);
		
		String ret = "deleted " + parts[1] + " " + id;
		
		switch (parts[1]) {
		case "user":
			mainDB.deleteUser(id);
			break;			
		case "store":
			mainDB.deleteStore(id);
			break;			
		case "product":
			mainDB.deleteProduct(id);
			break;
		case "customerReview":
			int id2 = str2num(parts[3]);
			mainDB.deleteCustomerReview(id, id2);
			ret += " " + id2;
			break;
		default:
			return null;
		}
		return ret;
	}
	private static String cmd_getAll(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		String ret = "getAll " + parts[1];
		
		switch (parts[1]) {
		case "products":
			Product[] products = null;
			switch (parts[2]) {
			case "category":
				products = mainDB.getAllProductsByCategory(parts[4]);
				break;
			case "maxPrice":
				products = mainDB.getAllProductsByMaxPrice(str2num(parts[4]));
				break;
			}
			
			ret += array2str(products);
			break;
			
		case "stores":
			ret += array2str(mainDB.getAllStores());
			break;
			
		case "customerReviews":
			int productID = str2num(parts[2]);
			ret += " " + productID;
			ret += array2str(mainDB.getAllCustomerReviews(productID));
			break;
			
		case "users":
			ret += array2str(mainDB.getAllUsers());
			break;
			
		default:
			return null;
		}
		return ret;
	}
	private static String cmd_getStoresOfProduct(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int productID = str2num(parts[1]);
		return "StoresOfProduct " + productID + array2str(mainDB.getAllStoresAndPricesBySpecificProduct(productID));
	}
	private static String cmd_getProductsOfStore(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int storeID = str2num(parts[1]);
		return "ProductsOfStore " + storeID + array2str(mainDB.getAllProductsAndPricesInStore(storeID));
	}
	private static String cmd_getAvgRating(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int productID = str2num(parts[1]);
		return "getAvgRating " + productID + " " + mainDB.getAverageRatingOfProduct(productID);
	}
	private static String cmd_linkStoreToProduct(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int storeID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int price = str2num(parts[3]);
		mainDB.linkStoreAndProduct(storeID, productID, price);
		return "linkStoreToProduct " + storeID + " " + productID + " " + price;
	}
	private static String cmd_addCart(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int storeID = str2num(parts[3]);
		mainDB.addToCart(userID, productID, storeID);
		return "addCart " + userID + " " + productID + " " + storeID;
	}
	private static String cmd_removeCart(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int storeID = str2num(parts[3]);
		mainDB.deleteFromCart(userID, productID, storeID);
		return "removeCart " + userID + " " + productID + " " + storeID;
	}
	private static String cmd_payCart(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		return "payCart " + userID + " " + mainDB.payForUserCart(userID);
	}
	private static String cmd_getCart(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		return "getCart " + userID + " " + array2str(mainDB.getProductIDsAndStoreIDsFromCart(userID));
	}
	private static String cmd_getHistory(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		return "getHistory " + userID + " " + array2str(mainDB.getProductIDsAndStoreIDsBought(userID));
	}
	private static String cmd_getHistoryProduct(String[] parts, MainDB mainDB) throws RemoteException, DatabaseException {
		int productID = str2num(parts[1]);
		return "getHistoryProduct " + productID + " " + array2str(mainDB.getAllUserIDsThatBoughtTheProduct(productID));
	}
		
	private static String getStringFrom(String[] parts, int startIndex) {
		return String.join(" ", Arrays.copyOfRange(parts, 4, parts.length));
	}
	private static int str2num(String s) {
		return Integer.parseInt(s);
	}
	
	private static String array2str(int[] ts) {
		String ret = "";
		for (int i = 0; i < ts.length; i++) {
			ret += " " + ts[i];
		}
		return ret;
	}
	private static <T> String array2str(T[] ts) {
		String ret = "";
		for (T t : ts) {
			ret += " " + obj2str(t);
		}
		return ret;
	}
	
	private static String obj2str(Object o) {
		return o.toString();
	}
	private static String obj2str(User u) {
		return "user " + u.getId() + " " + u.getUser_name();
	}
	private static String obj2str(Store s) {
		return "store " + s.getId() + " " + s.getName() + " " + s.getPhone_number();
	}
	private static String obj2str(Product p) {
		return "product " + p.getId() + " " + p.getName() + " " + p.getCategory() + " " + p.getDescription();
	}
	private static String obj2str(CustomerReview cr) {
		return "customerReviews " + cr.getProductID() + " " + cr.getId() + " " + cr.getRating() + " " + cr.getReview();
	}
	private static String obj2str(Pair p) {
		return obj2str(p.getLeft()) + " = " + obj2str(p.getRight()); 
	}
}
