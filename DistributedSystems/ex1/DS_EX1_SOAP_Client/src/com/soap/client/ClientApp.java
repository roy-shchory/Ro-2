package com.soap.client;

import java.rmi.RemoteException;
import java.util.Scanner;

import javax.xml.rpc.ServiceException;

import com.soap.server.*;

public class ClientApp {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		try {
			MainDB mainDB = new MainDBServiceLocator().getMainDBPort();
			
			String output = null;
			
			System.out.print(">> ");
			String userInput = reader.nextLine();
			while (!"quit".equals(userInput)) {
				try {
					output = Parser.parseCmd(userInput, mainDB);
					if (output == null) {
						output = "## Invalid input";
					} 
					System.out.println(output);
				} catch (DatabaseException e) {
					System.out.println("## From Server: " + e.getMessage1());
				} catch (RemoteException e) {
					System.out.println("## RemoteException: " + e.getMessage());
				} 
				
				System.out.print(">> ");
				userInput = reader.nextLine();
			}
			System.out.println("## Bye");
			
		} catch (ServiceException e) {
			System.out.println("## ServiceException: " + e.getMessage());
		} finally {
			reader.close();
		}
	}

}
