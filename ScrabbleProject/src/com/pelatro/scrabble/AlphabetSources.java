package com.pelatro.scrabble;

import java.awt.List;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class AlphabetSources extends Thread {
	 int rate;
	 String letter;
	public AlphabetSources() {
		
	}
	public AlphabetSources(String letter,int rate) {
		// TODO Auto-generated constructor stub
		this.rate=rate;
		this.letter=letter;
		System.out.println("Rate "+rate);
		System.out.println("Alphabet "+letter);
	}
	
	
	public void run() {
		int i=0;
		char alphabet;
		String msg;
		//Socket soc=null;
	try {
		while(true) {
		alphabet=letter.charAt(i%letter.length());
		 Socket soc = new Socket("LOCALHOST", 5556);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
		//System.out.println("Connected to the Source");
		msg = "@"+alphabet;
		pw.println(msg);	
		pw.close();
		soc.close();
		i++;
		//System.out.println("Rate is "+rate);
		Thread.sleep(rate*1000);
		
		}
	//	System.out.println("Processing word");
	} catch (Exception e) {
		e.printStackTrace();
	}

	}
}
