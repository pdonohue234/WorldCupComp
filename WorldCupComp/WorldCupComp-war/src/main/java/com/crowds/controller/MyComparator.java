package com.crowds.controller;

import java.util.Comparator;

public class MyComparator implements Comparator<UserResult> {
	
	@Override
	public int compare(UserResult o1, UserResult o2) {
	    if (o1.getScore() > o2.getScore()) {
	        return -1;
	    } else if (o1.getScore() < o2.getScore()) {
	        return 1;
	    }
	    return 0;
	}

}