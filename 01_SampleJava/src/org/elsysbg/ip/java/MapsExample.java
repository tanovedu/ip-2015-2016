package org.elsysbg.ip.java;

import java.util.HashMap;
import java.util.Map;

public class MapsExample {
	public static void main(String[] args) {
		// create map that holds String to Integer relation
		final Map<String, Integer> nameToCountMap =
			new HashMap<String, Integer>();
		
		// add element to map (key: Ivan, value: 1)
		nameToCountMap.put("Ivan", 1);
		
		// get from map by key
		// print 1
		System.out.println(nameToCountMap.get("Ivan"));

		// when there is no match - null is returned
		if (nameToCountMap.get("Unknown") == null) {
			System.out.println("Not found");
		}
	}
}
