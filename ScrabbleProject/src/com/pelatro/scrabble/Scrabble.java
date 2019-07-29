package com.pelatro.scrabble;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Scrabble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AlphabetSources[] mySource=loadConfigurationFile();
		
		AlphabetRepo ar= new AlphabetRepo();
		ar.start();
		
//		Thread AlphaRepoThread = new Thread(ar);
//		AlphaRepoThread.start();
		
		for(int i=0;i<mySource.length;i++) {
			mySource[i].start();
//			Thread AlphaSourceThread=new Thread(mySource[i]);
//			AlphaSourceThread.start();
		}
		
	//	Scanner sc = new Scanner(System.in);
		TakeInput ti = new TakeInput();
		String inputedWord="z";
		while(!inputedWord.equalsIgnoreCase("Exit")) {
			//System.out.println("Enter the word ");
			inputedWord=ti.getInput();
			WordBuilders wb = new WordBuilders(inputedWord);
			//Thread wbThread = new Thread(wb);
			wb.start();
	}
		System.exit(0);
	}
	
	public static AlphabetSources[] loadConfigurationFile() {
		BufferedReader br =null;
		
		int i=0;
		int noOfSource=0;
		final String CONFFILE="/home/shubhamsingh/conf";
		//String SourceData[]=new String[2];
		try {
			br = new BufferedReader(new FileReader(CONFFILE));
		}catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		
		try {
//			
			String[] sourceData = br.readLine().split(":",2);
			noOfSource = Integer.parseInt(sourceData[1]);
			System.out.println("Number of Source "+noOfSource);
			//System.out.println(br.readLine());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] line =new String[noOfSource+1];
			try {
				while((line[i]=br.readLine())!=null) {
					i++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
					AlphabetSources[] as = new AlphabetSources[noOfSource];
					
					for(int k=1;k<=noOfSource;k++) {
						String[] xyz=line[k-1].split(":",3);
						String alphabet=xyz[1];
						int rate =Integer.parseInt(xyz[2]);
						
						as[k-1]=new AlphabetSources(alphabet,rate);
				
				
				}
		return as;
	}
	
	
}
