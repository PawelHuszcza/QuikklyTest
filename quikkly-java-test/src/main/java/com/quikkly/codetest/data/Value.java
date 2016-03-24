package com.quikkly.codetest.data;
/**
 * Object representing a value of a single submission.
 * 
 * @author Pawel Huszcza
 *
 */
public class Value {

	private String username;
	private String word;
	private int score;
	
	public Value(String username, String word, int score) {
		
		this.username = username;
		this.word = word;
		this.score = score;
	}
	
	public String getWord() {
		
		return word;
	}

	public int getScore() {
		
		return score;
	}
	
	public String getUser() {
		
		return username;
	}
}
