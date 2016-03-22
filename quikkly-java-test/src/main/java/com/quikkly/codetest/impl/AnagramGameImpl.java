package com.quikkly.codetest.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.quikkly.codetest.AnagramGame;
import com.quikkly.codetest.WordDictionary;
import com.quikkly.codetest.data.Value;
import com.quikkly.codetest.data.ValueOrderComparator;

public class AnagramGameImpl implements AnagramGame {
	
	private Object lock = new Object();
	private List<Value> list;
	
	private Alphabet letters;
	private WordDictionary dictionary;
	
	private int lowest = 0;
	private int maxSet = 10;
	
	private Comparator<Value> comparator;
	
	public AnagramGameImpl(String word, WordDictionary dictionary) {
		
		this.letters = new Alphabet(word.toCharArray());
		this.dictionary = dictionary;
		
		list = new ArrayList<Value>();
		this.comparator = new ValueOrderComparator();
	}

	@Override
	public int submitWord(String username, String word) {
		
		int score = 0;
		if (letters.isContained(word) && dictionary.contains(word)) {
			
			score = getScore(word);
			addToSet(new Value(username, word, score));
		}
		
		return score;
	}

	@Override
	public String getUserNameAtPosition(int position) {
		
		return list.get(position).getUser();
	}

	@Override
	public String getWordEntryAtPosition(int position) {
		
		return list.get(position).getWord();
	}

	@Override
	public Integer getScoreAtPosition(int position) {
		
		return list.get(position).getScore();
	}
	
	private int getScore(String word) {
		
		return word.length();
	}

	private void addToSet(Value v) {
		
		synchronized(lock) {
			
			if (list.size() < maxSet || v.getScore() > lowest) {
				
				lowest = v.getScore();
				list.add(v);
				Collections.sort(list, comparator);
			}
		}
	}
}
