package com.quikkly.codetest.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.quikkly.codetest.AnagramGame;
import com.quikkly.codetest.WordDictionary;
import com.quikkly.codetest.data.Value;
import com.quikkly.codetest.data.ValueOrderComparator;

public class AnagramGameImpl implements AnagramGame {
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private List<Value> list;
	
	private Alphabet letters;
	private WordDictionary dictionary;
	
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
		
		lock.readLock().lock();
		try {
			
			return list.get(position).getUser();
		} finally {
			
			lock.readLock().unlock();
		}
	}

	@Override
	public String getWordEntryAtPosition(int position) {
		
		lock.readLock().lock();
		try {
			
			return list.get(position).getWord();
		} finally {
			
			lock.readLock().unlock();
		}
	}

	@Override
	public Integer getScoreAtPosition(int position) {
		
		lock.readLock().lock();
		try {
			
			return list.get(position).getScore();
		} finally {
			
			lock.readLock().unlock();
		}
	}
	
	private int getScore(String word) {
		
		return word.length();
	}

	private void addToSet(Value v) {
		
		lock.writeLock().lock();
		try {
			
			if (list.size() < maxSet || v.getScore() > list.get(maxSet-1).getScore()) {
				
				list.add(v);
				Collections.sort(list, comparator);
				truncateToMax(list);
			}
			System.out.println("Size " + list.size());
		} finally {
			
			lock.writeLock().unlock();
		}
	}
	
	private boolean truncateToMax(@SuppressWarnings("rawtypes") List l) {
		
		if (l.size() > maxSet) {
			
			list.subList(maxSet, list.size()).clear();
			return true;
		} else {
			
			return false;
		}
	}
}
