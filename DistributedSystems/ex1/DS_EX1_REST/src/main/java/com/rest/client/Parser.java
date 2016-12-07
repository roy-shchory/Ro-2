package com.rest.client;

import java.util.Arrays;
import java.util.Collection;

import com.rest.server.model.CustomerReview;
import com.rest.server.model.Product;
import com.rest.server.model.Store;
import com.rest.server.model.User;

public class Parser {
	public static String parseCmd(String cmd, MainDB_ClientHandler mainDB) throws ServerException {
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
	
	private static String cmd_add(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
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
	private static String cmd_get(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
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
	private static String cmd_update(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
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
	private static String cmd_delete(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
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
	private static String cmd_getAll(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		String ret = "getAll " + parts[1];
		
		switch (parts[1]) {
		case "products":
			Collection<Product> products = null;
			if (parts.length >= 3) {
				String queryName = parts[2];
				String queryParam = null;
				if (parts[2].contains("=")){
					queryName = parts[2].split("=")[0];
					queryParam = parts[2].split("=")[1];
				} else {
					queryParam = parts[4];
				}
				
				switch (queryName) {
				case "category":
					products = mainDB.getAllProductsByCategory(queryParam);
					break;
				case "maxPrice":
					products = mainDB.getAllProductsByMaxPrice(str2num(queryParam));
					break;
				}
			} else {
				products = mainDB.getAllProducts();
			}
			
			ret += collection2str(products);
			break;
			
		case "stores":
			ret += collection2str(mainDB.getAllStores());
			break;
			
		case "customerReviews":
			int productID = str2num(parts[2]);
			ret += " " + productID;
			ret += collection2str(mainDB.getAllCustomerReviews(productID));
			break;
			
		case "users":
			ret += collection2str(mainDB.getAllUsers());
			break;
			
		default:
			return null;
		}
		return ret;
	}
	private static String cmd_getStoresOfProduct(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int productID = str2num(parts[1]);
		return "StoresOfProduct " + productID + collection2str(mainDB.getAllStoresAndPricesBySpecificProduct(productID));
	}
	private static String cmd_getProductsOfStore(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int storeID = str2num(parts[1]);
		return "ProductsOfStore " + storeID + collection2str(mainDB.getAllProductsAndPricesInStore(storeID));
	}
	private static String cmd_getAvgRating(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int productID = str2num(parts[1]);
		return "getAvgRating " + productID + " " + mainDB.getAverageRatingOfProduct(productID);
	}
	private static String cmd_linkStoreToProduct(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int storeID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int price = str2num(parts[3]);
		mainDB.linkStoreAndProduct(storeID, productID, price);
		return "linkStoreToProduct " + storeID + " " + productID + " " + price;
	}
	private static String cmd_addCart(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int userID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int storeID = str2num(parts[3]);
		mainDB.addToCart(userID, productID, storeID);
		return "addCart " + userID + " " + productID + " " + storeID;
	}
	private static String cmd_removeCart(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int userID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int storeID = str2num(parts[3]);
		mainDB.deleteFromCart(userID, productID, storeID);
		return "removeCart " + userID + " " + productID + " " + storeID;
	}
	private static String cmd_payCart(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int userID = str2num(parts[1]);
		return "payCart " + userID + " " + mainDB.payForUserCart(userID);
	}
	private static String cmd_getCart(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int userID = str2num(parts[1]);
		return "getCart " + userID + collection2str(mainDB.getProductIDsAndStoreIDsFromCart(userID));
	}
	private static String cmd_getHistory(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int userID = str2num(parts[1]);
		return "getHistory " + userID + collection2str(mainDB.getProductIDsAndStoreIDsBought(userID));
	}
	private static String cmd_getHistoryProduct(String[] parts, MainDB_ClientHandler mainDB) throws ServerException {
		int productID = str2num(parts[1]);
		return "getHistoryProduct " + productID + collection2str(mainDB.getAllUserIDsThatBoughtTheProduct(productID));
	}
		
	private static String getStringFrom(String[] parts, int startIndex) {
		return String.join(" ", Arrays.copyOfRange(parts, 4, parts.length));
	}
	private static int str2num(String s) {
		return Integer.parseInt(s);
	}
	
	private static <T> String collection2str(Collection<T> c) {
		String ret = "";
		for (T t : c) {
			ret += " " + obj2str(t);
		}
		return ret;
	}
	
	private static String obj2str(Object u) {
		return u.toString();
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
}
