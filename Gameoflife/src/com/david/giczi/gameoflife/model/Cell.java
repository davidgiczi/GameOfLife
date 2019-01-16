package com.david.giczi.gameoflife.model;

public class Cell {

	
	private int x;
	private int y;
	private boolean isAlive=false;
	
	
	public Cell(int x, int y) {
	
		this.x = x;
		this.y = y;
	}
	
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	

	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public boolean isAlive() {
		return isAlive;
	}


	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAlive ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (isAlive != other.isAlive)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", isAlive=" + isAlive + "]";
	}
	
	
	
	
}
