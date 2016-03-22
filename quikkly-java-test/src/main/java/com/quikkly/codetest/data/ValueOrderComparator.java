package com.quikkly.codetest.data;

import java.util.Comparator;

/**
 * This comparator implementation ensures that greater value will be closer to 0.
 * 
 * @author eli
 *
 */
public class ValueOrderComparator implements Comparator<Value> {

	@Override
	public int compare(Value o1, Value o2) {
		
		if (o1.getScore() >= o2.getScore()) {
			
			return -1;
		}
		
		return 1;
	}
}
