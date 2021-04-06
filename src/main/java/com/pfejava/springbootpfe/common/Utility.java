package com.pfejava.springbootpfe.common;

public class Utility {

	public static int IterableCount(Iterable<?> data) {
		try {
			int counter = 0;
			for (@SuppressWarnings("unused") Object i : data) {
			    counter++;
			}
			return counter;

		} catch (Exception e) {			
			return 0;
		}
	}
}
