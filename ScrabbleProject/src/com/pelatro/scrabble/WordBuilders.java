package com.pelatro.scrabble;

import java.io.*;
import java.net.Socket;

public class WordBuilders extends Thread {
	
	String word;
	public WordBuilders(String word) {
		this.word=word;
	}
	
	public void run() {
		try {
			Socket soc = new Socket("LOCALHOST",5556);
			PrintWriter pw = null;
			pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
			word="&"+word;
			pw.println(word);
			pw.close();
			soc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
