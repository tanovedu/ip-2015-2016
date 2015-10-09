package org.elsysbg.ip.java;

public class Room {
	
	// define fields
	private int width;
	private int height;

	// in Eclipse: Alt+Shift+S R to generate getters/setters
	// or right click > Source > Generate getters and setters
	// or start typing method name, e.g. setH and press Ctrl+Space
	// getters and setters:
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	// define method:
	public int calculateArea() {
		// return value
		return height * width;
	}
	
}
