package org.mql.java.lang.ui;

public class ClassPosition {

	private String name; 
	private int x ; 
	private int y; 
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public ClassPosition() {
		
	}
	public ClassPosition(String name, int x, int y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
}
