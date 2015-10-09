package org.elsysbg.ip.java;

public class Variables {

	// to automatically create constant using Eclipse:
	// select constant value in the code, press Ctrl+1
	// select "Extract to constant"
	// define constant
	private static final String HELLO_WORLD = "Hello world";

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
		String b = HELLO_WORLD;
	}
	// error:
	// a = 0;

}
