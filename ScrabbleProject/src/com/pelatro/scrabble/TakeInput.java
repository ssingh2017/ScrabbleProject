package com.pelatro.scrabble;

import java.util.Scanner;

public class TakeInput {

	public String getInput() {
		System.out.println("Enter a word");
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
}
