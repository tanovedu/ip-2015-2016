package org.elsysbg.ip.java;

public class EqualsExample {
	
	private static final int WIDTH = 12;
	private static final int HEIGHT = 10;

	public static void main(String[] args) {
		final Room room1 = new Room();
		final Room room2 = new Room();
		
		room1.setHeight(HEIGHT);
		room1.setWidth(WIDTH);
		
		room2.setHeight(HEIGHT);
		room2.setWidth(WIDTH);
		
		System.out.println("room1 == room2");
		// false
		System.out.println(room1 == room2);

		System.out.println("room1.equals(room2)");
		// true
		System.out.println(room1.equals(room2));
		

		System.out.println("room1 == room1");
		// true
		System.out.println(room1 == room1);

		System.out.println("room1.equals(room1)");
		// true
		System.out.println(room1.equals(room1));
	}
}
