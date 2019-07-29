package com.pelatro.scrabble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class AlphabetRepo extends Thread {

	Hashtable<Character, Integer> hm = new Hashtable<Character,Integer>();
	
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(5556);
			AlphabetRepo ar = new AlphabetRepo();
			
			while(true) {
			Socket clientSoc = ss.accept();//blocking method untill client is not connected
			//System.out.println("Client connected");
			BufferedReader br  = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
			String msgClient = br.readLine();
			//storeAlphabet("Message client is "+msgClient);
			clientSoc.close();
			Thread clientHandler = new ClientHandler(ar,msgClient);
			clientHandler.start();
			
			//System.out.println("Client msg "+msgClient);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void addGeneratedAlphabet(String letter) {
//		for(char c : letter.toCharArray()) {
//			hm.put(c, hm.get(c)+1);
//		}
//	}
	public void storeAlphabet(String letter ) {
	//c = sc.next().charAt(0);
	
		for(char c : letter.toCharArray()) {
			if(hm.get(c)==null)
				hm.put(c, 1);
			else
				hm.put(c, hm.get(c)+1);
		//System.out.println("Maker"+hm);
		}
		
	}
	
	synchronized public boolean isWordFormed(String msg) {
		String word="";
		for(char c : msg.toCharArray()) {
			if(hm.get(c)==null) {
				try {
					wait(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(hm.get(c)==null) {
					if(word=="") {
						//System.out.println("word is null");
						return false;
					}
					//System.out.println("Word is "+word);
					for(char s : word.toCharArray()) {
						if(hm.get(s)==null)
							hm.put(s,1);
						else
							hm.put(s, hm.get(s)+1);
						
					//	System.out.println("Return "+hm);
					}
					return false;
				}
			} if(hm.get(c)>1) {
				hm.put(c, hm.get(c)-1);
				word=word+c;
			}if(hm.get(c)==1) {
				hm.remove(c);
				word=word+c;
			}
		}
		return true;
	}
	
	
	
	
}
