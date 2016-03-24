package com.quikkly.codetest.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Set of allowed letters.
 * 
 * @author Pawel
 *
 */
public class Alphabet {
	
	private Map<Character, Integer> letters = new HashMap<Character, Integer>();

	public Alphabet (char[] lettersChars) {
		
		for (char c : lettersChars) {
			
			if (letters.get(c) == null) {
				
				 letters.put(c, 1);
			} else {
				
				letters.put(c, letters.get(c) + 1);
			}
		}
	}
	
	private int getOccurences(char c) {
		
		if (letters.get(c) == null) {
			
			return 0;
		} else {
			
			return letters.get(c);
		}
	}
	
	public boolean isContained(String word) {
		
		Map<Character, Integer> chars = new HashMap<Character, Integer>();
		
		for (char c : word.toCharArray()) {
			
			if (chars.get(c) == null) {
				
				chars.put(c, 1);
			} else {
				
				chars.put(c, chars.get(c) + 1);
			}
			
			if (getOccurences(c) - chars.get(c) < 0) {
				
				return false;
			}
		}
		
		return true;
	}
}
