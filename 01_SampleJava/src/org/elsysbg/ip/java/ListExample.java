package org.elsysbg.ip.java;

import java.util.LinkedList;
import java.util.List;

public class ListExample {
	public static void main(String[] args) {
		// create list that will hold String values
		final List<String> invited = new LinkedList<String>();
		
		// add elements to the list
		invited.add("Ivan");
		invited.add("Peter");
		
		// iterate through elements
		for (String name : invited) {
			System.out.println(name + " is invited");
		}

		// get size of the list
		System.out.println("All invited: " +
			invited.size());
		
		// remove element from the list
		invited.remove("Ivan");
		
		System.out.println("Ivan is no longer invited");
		
		for (String name : invited) {
			System.out.println(name + " is invited");
		}
		System.out.println("All invited: " +
			invited.size());
	}
}
