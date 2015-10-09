package org.elsysbg.ip.java;

public class ControlFlowStatements {

	public static void main(String[] args) {
		// in Eclipse: select statements that should be
		// extracted to method, press Ctrl+1
		// ifExample();
		// in Eclipse - type forExample() and press Ctrl + 1
		// to automatically create method definition
		forExample();
	}

	private static void forExample() {
		for (int i = 0; i < 10; i++) {
			// 0 to 9
			System.out.println(i);
		}
	}

	private static void ifExample() {
		// if without else
		if (true) {
			System.out.println("called");
		}
		
		// if else
		if (1>2) {
			System.out.println("not called");
		} else {
			System.out.println("called");
		}
	}

}
