package com.pelatro.scrabble;

import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class ClientHandler extends Thread{
	
	private String msg;
	private AlphabetRepo alphaRepo=null;
      
  
    // Constructor 
    public ClientHandler(AlphabetRepo alphabetRepo,String msg)  
    { 
      this.alphaRepo=alphabetRepo;
      this.msg=msg;
    } 
  
    @Override
    public void run()  
    { 	
        if(msg.startsWith("@")) {
        	msg=msg.substring(1);
        	connectToAlphabetSource(msg);
        }
        if(msg.startsWith("&")) {
        	msg=msg.substring(1);
        	connectToWordBuilder(msg);
        }
    }
    
    public void connectToAlphabetSource(String  msg) {
    	//System.out.println("Message from Client Handler1 "+msg);
    	alphaRepo.storeAlphabet(msg);
    	
    }
    
    public void connectToWordBuilder(String  msg) {
    	String status;
    //	System.out.println("Message from Client Handler "+msg);
    	if(alphaRepo.isWordFormed(msg)) {
    		status="Success";
    		System.out.println("Word is formed");
    	}else {
    		status="Failure";
    		System.out.println("Word cannot be formed");
    	}
    	updateDatabase(msg, status);
    	
    }
    synchronized public void updateDatabase(String word,String status)
	{
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		WordAttributes wa = new WordAttributes();
		Date date= new Date();
		long time = date. getTime();
		
		Timestamp ts = new Timestamp(time);
		
		wa.setStatus(status);
		wa.setTimeStamp(ts);
		wa.setWord(word);
		session.save(wa);
		
		session.getTransaction().commit();
		session.close();
	}
    
    
}
