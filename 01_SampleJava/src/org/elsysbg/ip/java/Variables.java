package org.elsysbg.ip.java;

public class Variables {

	public static void main(String[] args) {
		// scope of the variable is from definition
		// to the end of the block, e.g. }
		// error:
		// a = 0;
		// define local variable
		int a;

		// assign value
		a = 0;
		
		// init variable value
		String b = "Hello world";
	}
	// error:
	// a = 0;

}
