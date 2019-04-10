package finalproject;

import java.util.Arrays;

import cse131.NotYetImplementedException;

/**
 * @author Mariah Yelenick and Adam Kern
 * @version 11/18/18
 */
public class Ship {
	
	/**
	 * Create an instance of the ship class, recording all necessary information into
	 * any instance variables you choose to create
	 * 
	 * @param topLeftX the x coordinate of the ship's uppermost, leftmost space
	 * @param topLeftY the y coordinate of the ship's uppermost, leftmost space
	 * @param length the number of spaces the ship occupies
	 * @param isHorizontal if true, the ship is placed horizontally, else the ship is placed vertically
	 */
	
	final private int tLX, tLY, length;
	final private boolean isH;
	final private int[][] hit;
	final private int[][]occupy;
	/**
	 * occupy(row, colunm)
	 * @param topLeftX
	 * @param topLeftY
	 * @param length
	 * @param isHorizontal
	 */
	public Ship(int topLeftX, int topLeftY, int length, boolean isHorizontal) {
		this.tLX = topLeftX;
		this.tLY = topLeftY;
		this.length = length;
		this.isH = isHorizontal;
		this.hit = new int [length][2];
		
		this.occupy= new int [length][2];
		for(int r = 0; r < length; ++r) {
			if(this.isH) {
				this.occupy[r][1] = tLX + r;
				this.occupy[r][0] = tLY;
			}
			else {
				this.occupy[r][1] = tLX;
				this.occupy[r][0] = tLY + r;
			}
			this.hit[r][1] = -1;
			this.hit[r][0] = -1;
		}
	}
	
	/**
	 * Check if the ship has been sunk
	 * This should only be true if the ship was hit in all the spaces it occupies
	 * 
	 * @return true if the ship has been sunk
	 */
	public boolean isSunk() {
		for (int i = 0; i < length; ++i) {
			if (this.hit[i][0] == -1 && this.hit[i][1] == -1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if the ship occupies a space at (x, y)
	 * Note: Be sure to update the hits array so that you can check if the ship is sunk!
	 * 
	 * @param x the x coordinate to shoot at
	 * @param y the y coordinate to shoot at
	 * @return true if this ship occupies that spot (hit), false otherwise (miss)
	 */
	public boolean isHit(int x, int y) {
		for(int r = 0; r < this.occupy.length; ++r) {
			if(x == this.occupy[r][1] && y == this.occupy[r][0]) {
				this.hit[r][0] = y;
				this.hit[r][1] = x;
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isH ? 1231 : 1237);
		result = prime * result + length;
		result = prime * result + tLX;
		result = prime * result + tLY;
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
		Ship other = (Ship) obj;
		if (isH != other.isH)
			return false;
		if (length != other.length)
			return false;
		if (tLX != other.tLX)
			return false;
		if (tLY != other.tLY)
			return false;
		for(int i = 0; i < length; ++i) {
			if(this.hit[i][0] != other.getHit()[i][0] && this.hit[i][1] != other.getHit()[i][1]) {
				return false;
			}
		}
		return true;
	}

	public int gettLX() {
		return tLX;
	}

	public int gettLY() {
		return tLY;
	}
	
	public boolean getisH() {
		return this.isH;
	}
	
	public int getLength() {
		return length;
	}

	public boolean isH() {
		return isH;
	}

	public int[][] getHit() {
		return hit;
	}

	public int[][] getOccupy() {
		return occupy;
	}
	
	public void setOccupy(int x, int y, int v) {
		this.occupy[x][y] = v;
	}
	@Override
	public String toString() {
		return "Ship [tLX=" + tLX + ", tLY=" + tLY + ", length=" + length + ", isH=" + isH + ", hit="
				+ Arrays.toString(hit) + ", occupy=" + Arrays.toString(occupy) + "]";
	}
	
}
