package com.soap.client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.xml.rpc.ServiceException;

import com.soap.server.*;

public class ClientApp {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		PrintWriter writer = null;
		String output = "";
		
		try {
			writer = new PrintWriter(new FileWriter("ds-ex1-log.txt"));
			MainDB mainDB = new MainDBServiceLocator().getMainDBPort();
			
			System.out.print(">> ");
			String userInput = reader.nextLine();
			
			while (!"quit".equals(userInput)) {
				try {
					output = Parser.parseCmd(userInput, mainDB);
					if (output == null) {
						output = "## Invalid input";
					}
				} catch (DatabaseException e) {
					output = "## From Server: " + e.getMessage1();
				} catch (RemoteException e) {
					output = "## RemoteException: " + e.getMessage();
				}
				System.out.println(output);
				writer.println(output);
				
				System.out.print(">> ");
				userInput = reader.nextLine();
			}
			System.out.println("## Bye");
			
		} catch (IOException e) {
			System.out.println("## Can't write to log file: " + e.getMessage());
		} catch (ServiceException e) {
			output = "## ServiceException: " + e.getMessage();
			System.out.println(output);
			writer.println(output);
		} finally {
			reader.close();
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

}
