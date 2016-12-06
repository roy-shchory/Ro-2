package com.rest.client;

import com.rest.server.exceptions.*;
import com.rest.server.model.*;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Parser {
	
	private static Client client = ClientBuilder.newClient();
	private static WebTarget baseTarget = client.target("http://localhost:8080/DS_EX1_REST/webapi/app");
	
	private static WebTarget definePath(String name,String type){
		WebTarget messagesTarget = baseTarget.path(name+"s");
		if(type=="add"){
			return messagesTarget;
		}
		return messagesTarget.path("{"+name+"Id}");	
	}
	
	private static Object getReq(String name,Class c,WebTarget target,int id) throws ResourceNotFoundException{
		@SuppressWarnings("unchecked")
		Response resp = target
		.resolveTemplate(name+"Id", id)
		.request(MediaType.APPLICATION_JSON)
		.get(c);
		if(resp.getStatus()!=201){
			throw new ResourceNotFoundException();
		}
		return resp.getEntity();
	}
	
	@SuppressWarnings({"unchecked"})
	private static Object putObj(String name,Object o,WebTarget target,int id){
		Response res = target
		.resolveTemplate(name+"Id", id)
		.request(MediaType.APPLICATION_JSON)
		.put((Entity<?>) o);
		if(res.getStatus()!=201){
			throw new DatabaseException();
		}
		return res.getEntity();
	}
	
	public static String parseCmd(String cmd) throws DatabaseException, RemoteException {
		String[] parts = cmd.split(" ");
		
		switch (parts[0]) {
		case "add":
			return cmd_add(parts);
			
		case "get":
			return cmd_get(parts);
/*			
		case "update":
			return cmd_update(parts);
			
		case "delete":
			return cmd_delete(parts);
			
		case "getAll":
			return cmd_getAll(parts);
			
		case "getStoresOfProduct":
			return cmd_getStoresOfProduct(parts);
			
		case "getProductsOfStore":
			return cmd_getProductsOfStore(parts);
			
		case "getAvgRating":
			return cmd_getAvgRating(parts);
			
		case "linkStoreToProduct":
			return cmd_linkStoreToProduct(parts);
			
		case "addCart":
			return cmd_addCart(parts);
			
		case "removeCart":
			return cmd_removeCart(parts);
			
		case "payCart":
			return cmd_payCart(parts);
			
		case "getCart":
			return cmd_getCart(parts);
			
		case "getHistory":
			return cmd_getHistory(parts);
			
		case "getHistoryProduct":
			return cmd_getHistoryProduct(parts);
*/
		default:
			break;
		}
		
		return null;
	}
	
	private static String cmd_add(String[] parts) throws RemoteException, DatabaseException {
		String ret = "Added " + parts[1] + " ";
		WebTarget target = definePath(parts[1],"add");
		Response postResponse;
		switch (parts[1]) {
		case "user":
			postResponse = target
			.request()
			.post(Entity.json(new User(parts[2],0)));
			if (postResponse.getStatus() != 201) {
				throw new DatabaseException();
			}
			ret += obj2str(postResponse.readEntity(User.class));
			break;			
		case "store":
			postResponse = target
			.request()
			.post(Entity.json(new Store(parts[2],0,parts[3])));
			if (postResponse.getStatus() != 201) {
				throw new DatabaseException();
			}
			ret += obj2str(postResponse.readEntity(Store.class));
			break;			
		case "product":
			postResponse = target
			.request()
			.post(Entity.json(new Product(0 , parts[2], parts[3], getStringFrom(parts, 4))));
			if (postResponse.getStatus() != 201) {
				throw new DatabaseException();
			}
			ret += obj2str(postResponse.readEntity(Product.class));
			break;
		case "customerReview":
			postResponse = target
			.request()
			.post(Entity.json(new CustomerReview(0,str2num(parts[2]), getStringFrom(parts,4), str2num(parts[3]))));
			if (postResponse.getStatus() != 201) {
				throw new DatabaseException();
			}
			ret += obj2str(postResponse.readEntity(CustomerReview.class));
			break;
		default:
			return null;
		}
		return ret;
	}
	private static String cmd_get(String[] parts) throws RemoteException, DatabaseException {
		String ret = "got " + parts[1] + " ";
		int id = str2num(parts[2]);
		WebTarget target = definePath(parts[1],"get");
		try {
			switch (parts[1]) {
			case "user":
					ret += obj2str(getReq(parts[1],User.class,target,id));
				break;			
			case "store":
				ret += obj2str(getReq(parts[1],Store.class,target,id));
				break;			
			case "product":
				ret += obj2str(getReq(parts[1],Product.class,target,id));
				break;
			case "customerReview":
				ret += obj2str(getReq(parts[1],CustomerReview.class,target,id));
				break;
			default:
				return null;
			}
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	private static String cmd_update(String[] parts) throws RemoteException, DatabaseException {
		String ret = "Updated " + parts[1] + " ";
		int id = str2num(parts[2]);
		WebTarget target = definePath(parts[1],"add");
		switch (parts[1]) {
		case "user":
			ret += obj2str(putObj(parts[1], new User(parts[3],id), target, id));
			break;			
		case "store":
			ret += obj2str(putObj(parts[1],(new Store(parts[3], id, parts[4]),target,id));
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
	/*
	private static String cmd_delete(String[] parts) throws RemoteException, DatabaseException {
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
	private static String cmd_getAll(String[] parts) throws RemoteException, DatabaseException {
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
	private static String cmd_getStoresOfProduct(String[] parts) throws RemoteException, DatabaseException {
		int productID = str2num(parts[1]);
		return "StoresOfProduct " + productID + array2str(mainDB.getAllStoresAndPricesBySpecificProduct(productID));
	}
	private static String cmd_getProductsOfStore(String[] parts) throws RemoteException, DatabaseException {
		int storeID = str2num(parts[1]);
		return "ProductsOfStore " + storeID + array2str(mainDB.getAllProductsAndPricesInStore(storeID));
	}
	private static String cmd_getAvgRating(String[] parts) throws RemoteException, DatabaseException {
		int productID = str2num(parts[1]);
		return "getAvgRating " + productID + " " + mainDB.getAverageRatingOfProduct(productID);
	}
	private static String cmd_linkStoreToProduct(String[] parts) throws RemoteException, DatabaseException {
		int storeID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int price = str2num(parts[3]);
		mainDB.linkStoreAndProduct(storeID, productID, price);
		return "linkStoreToProduct " + storeID + " " + productID + " " + price;
	}
	private static String cmd_addCart(String[] parts) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int storeID = str2num(parts[3]);
		mainDB.addToCart(userID, productID, storeID);
		return "addCart " + userID + " " + productID + " " + storeID;
	}
	private static String cmd_removeCart(String[] parts) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		int productID = str2num(parts[2]);
		int storeID = str2num(parts[3]);
		mainDB.deleteFromCart(userID, productID, storeID);
		return "removeCart " + userID + " " + productID + " " + storeID;
	}
	private static String cmd_payCart(String[] parts) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		return "payCart " + userID + " " + mainDB.payForUserCart(userID);
	}
	private static String cmd_getCart(String[] parts) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		return "getCart " + userID + " " + array2str(mainDB.getProductIDsAndStoreIDsFromCart(userID));
	}
	private static String cmd_getHistory(String[] parts) throws RemoteException, DatabaseException {
		int userID = str2num(parts[1]);
		return "getHistory " + userID + " " + array2str(mainDB.getProductIDsAndStoreIDsBought(userID));
	}
	private static String cmd_getHistoryProduct(String[] parts) throws RemoteException, DatabaseException {
		int productID = str2num(parts[1]);
		return "getHistoryProduct " + productID + " " + array2str(mainDB.getAllUserIDsThatBoughtTheProduct(productID));
	}
	*/	
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
