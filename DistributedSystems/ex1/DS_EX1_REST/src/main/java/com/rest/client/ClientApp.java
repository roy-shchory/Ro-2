package com.rest.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClientApp {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		if(args.length==1){
			try {
				reader = new Scanner(new File(args[0]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		String basePath = "http://localhost:8080/DS_EX1_REST/webapi/app";
		MainDB_ClientHandler mainDB = new MainDB_ClientHandler(basePath); 
		
		
		PrintWriter writer = null;
		String output = "";
		
		try {
			writer = new PrintWriter(new FileWriter("ds-ex1-log.txt"));
			
			System.out.print(">> ");
			String userInput = reader.nextLine();
			
			while (!"quit".equals(userInput)) {
				try {
					output = Parser.parseCmd(userInput, mainDB);
					if (output == null) {
						output = "## Invalid input";
					}
				} catch (ServerException e) {
					output = e.getMessage();
				}
				System.out.println(output);
				writer.println(output);
				
				System.out.print(">> ");
				if(!reader.hasNextLine())
					break;
				userInput = reader.nextLine();
			}
			System.out.println("## Bye");
			
		} catch (IOException e) {
			System.out.println("## Can't write to log file: " + e.getMessage());
		} finally {
			reader.close();
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

}
