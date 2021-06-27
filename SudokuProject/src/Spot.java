/*********************************************************************
Brandon Cobb
Comp 282 Monday/Wednesday 2pm
Assignment 1
September 12, 2018
This Spot class holds a row and a column (A spot that can be filled)
**********************************************************************/

public class Spot {
	
	private int row, col;
	
	public Spot(int row, int col) {
		setRow(row);
		setCol(col);
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return this.col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}