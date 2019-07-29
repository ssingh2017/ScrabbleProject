package com.pelatro.scrabble;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
public class WordAttributes {

	@Id @GeneratedValue
	private int wordId;
	private String word;
	private String status;
	private Timestamp timeStamp;
	
	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Id :"+wordId+"  Word : "+word+"  Timestamp : "+timeStamp+"  Status : "+status;
	}
	
}
