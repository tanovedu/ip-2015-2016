package org.elsysbg.ip.java;

public class ControlFlowStatements {

	public static void main(String[] args) {
		// in Eclipse: select statements that should be
		// extracted to method, press Ctrl+1
		ifExample();
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
