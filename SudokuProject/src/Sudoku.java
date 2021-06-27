/*********************************************************************
Brandon Cobb
Comp 282 Monday/Wednesday 2pm
Assignment 1
September 12, 2018
This class contains a sudoku board, and all methods that work on a
sudoku board
**********************************************************************/

class sudoku {
	
	private int board [][] = new int[9][9];									// New sudoku board
	
	// Construct a new Sudoku puzzle board from a string
	public sudoku(String str[]) {
		
		int strElement = 0, boardRow = 0, boardCol = 0;						// Counters for Rows & Columns for the String str[] and the board[]
		int sRSC = 0;														// A counter for the amount of substrings a str[] is split into
		String sudokuRowStrArray[];											// A sub-String array made from the splitting of String str[]
		
		// Process the elements in the String (the rows in the board) and insert them into board[][]
		while (strElement <= 8) {													// Dont process more rows than a sudoku board has
			sudokuRowStrArray = str[strElement].split(" ");							// Split the current str[] into sub strings
			
			while (sRSC <= 2) {															// Dont go over the amount of substrings in sudokuRowStrArray
				int num = Integer.parseInt(sudokuRowStrArray[sRSC]);					// Turn a substring of 3 numbers into an int 
				
				
				board [boardRow][boardCol] = (num / 100);					// Get 100s place, place number in board
				boardCol++;													// Get ready to place next number in next column
				
				num = num - ((num / 100) * 100);							// Subtract 100s place from the rest of the num so that I am able to take the 10s place
				board [boardRow][boardCol] = (num / 10);					// Get 10s place, place number in board
				boardCol++;													// Get ready to place next number in next column
				
				num = num - ((num / 10) * 10);								// Subtract the 10s place from the rest of the num so that im able to take the 1s place
				board [boardRow][boardCol] = num -((num / 10) * 10);		// Get 1s place
				boardCol++;													// Get ready to place next number in next column
				
				
				if (boardCol == 9) {										// If end of row is reached then reset boardCol and advance the boardRow
					boardCol = 0;
					boardRow++;
				}
				sRSC++;														// Get ready to turn the next string into an int
			}
			strElement++;															// Move to next element in the String (next sudoku row)
			sRSC = 0;																	// Get ready to read first str element in the next sudoku row
		}
	}
	
	
	// Copy Constructor
	public sudoku(sudoku p) {
		
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				board[row][col]	= p.board[row][col];
			}
		}
	}
	
	
	// Does the current board satisfy all of the sudoku rules?
	public boolean isValid() {
		boolean tf = true;
		
		// Check if any rows have duplicate values
		for (int row = 0; row <= 8; row++) {
			for (int numOfValues = 0, val = 1; val <= 9; val++, numOfValues = 0) {
				for (int col = 0; col <= 8; col++) {
					if (board[row][col] == val) {
						numOfValues++;
					}
				}
				
				if (numOfValues > 1) {
					tf = false;
				}
			}
		}
		
		// Check if any columns have duplicate values
		for (int col = 0; col <= 8; col++) {
			for (int numOfValues = 0, val = 1; val <= 9; val++, numOfValues = 0) {
				for (int row = 0; row <= 8; row++) {
					if (board[row][col] == val) {
						numOfValues++;
					}
				}
				
				if (numOfValues > 1) {
					tf = false;
				}
			}
		}
		
		// Check if any boxes have duplicate values
		for (int rowBox = 0; rowBox <= 6; rowBox += 3) {									// Selects the top row for each box
			for (int colBox = 0; colBox <= 6; colBox += 3) {								// Selects the left most column for each box in the sudoku board
				for (int numOfValues = 0, val = 1; val <= 9; val++, numOfValues = 0) {		// Which value are we looking for in the selected box?
					for (int i = 0; i <= 2; i++) {											// Increments the spot in each column 
						for (int j = 0; j <= 2; j++) {										// Increments the spot in each row
							if (board[rowBox + i][colBox + j] == val) {						// If the current spot in the box is == val then increment val
								numOfValues++;
							}
						}
					}
					
					if (numOfValues > 1) {													// If there is more than 1 identical value in a box then sudoku is not valid
						tf = false;
					}
				}
			}
		}
		return tf;
	}
	
	
	// Is this a solved Sudoku?
	public boolean isComplete() {
		boolean tf = true;
		
		// Check if any spots on the board are == 0
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				if (board[row][col] == 0) {
					tf = false;		// If any spot == 0 then the board isnt complete
				}					
			}
		}
		return tf;	// Returns true or false
	}
	
	
	// Does the current row contain the current value?
	private boolean doesRowContain(int row, int val) {
		boolean tf = false;
		
		for (int col = 0; col <= 8; col++) {
			if (board[row][col] == val) {
				tf = true;
			}
		}
		return tf;
	}
	
	
	// Does the current column contain the current value?
	private boolean doesColContain(int col, int val) {
		boolean tf = false;
		for (int row = 0; row <= 8; row++) {
			if (board[row][col] == val) {
				tf = true;
			}
		}
		return tf;
	}
	
	
	// Does the current box contain the current value?
	private boolean doesBoxContain(int row, int col, int val) {
		boolean tf = false;
		int box = 0;
		
		// Which box are we talking about?
		// I should have divided by 3 then multiplied by 3 to find the top left spot
		if (row <= 2) {								// Its a box in the top row
			if (col <= 2) {							// Its box 1 (top left box)
				box = 1;
			} else if (col >= 3 && col <= 5) {		// Is it box 2? (middle top box)
				box = 2;
			} else									// Ok then its box 3 for sure (top right box)
				box = 3;
		}
		
		if (row >= 3 && row <= 5) {					// Its a box in the middle row
			if (col <= 2) {							// Its box 4 (middle left box)
				box = 4;
			} else if (col >= 3 && col <= 5) {		// Is it box 5? (center box)
				box = 5;
			} else									// Ok then its box 6 for sure (middle right box)
				box = 6;
		}

		if (row >= 6 && row <= 8) {					// Its a box in the bottom row
			if (col <= 2) {							// Its box 7 (bottom left box)
				box = 7;
			} else if (col >= 3 && col <= 5) {		// Is it box 8? (bottom middle box)
				box = 8;
			} else									// Ok then its box 9 for sure (bottom right box)
				box = 9;
		}
		
		// Now we know which box to search. So lets search the box to see if val exists in it
		switch(box) {
		
			case 1:
				for (int y = 0; y <= 2; y++) {
					for (int x = 0; x <= 2; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 2:
				for (int y = 0; y <= 2; y++) {
					for (int x = 3; x <= 5; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 3:
				for (int y = 0; y <= 2; y++) {
					for (int x = 6; x <= 8; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 4:
				for (int y = 3; y <= 5; y++) {
					for (int x = 0; x <= 2; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 5:
				for (int y = 3; y <= 5; y++) {
					for (int x = 3; x <= 5; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 6:
				for (int y = 3; y <= 5; y++) {
					for (int x = 6; x <= 8; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 7:
				for (int y = 6; y <= 8; y++) {
					for (int x = 0; x <= 2; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 8:
				for (int y = 6; y <= 8; y++) {
					for (int x = 3; x <= 5; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
				
			case 9:
				for (int y = 6; y <= 8; y++) {
					for (int x = 6; x <= 8; x++) {
						if (board[y][x] == val) {
							tf = true;
						}
					}
				}
				return tf;
		}
		return tf;	// returns false
	}
	
	
	// Return a valid spot if only one possibility for val in row, return null otherwise
	private Spot rowFill(int row, int val) {
		int validSpaces = 0;																					// Number of valid spaces for val in the current row
		Spot s = null;																							// Create a spot just in case we find valid place to put val
		if(!doesRowContain(row, val)) {																			// If the row doesnt contain the value...
			for (int col = 0; col <= 8; col++) {																// As long as we dont try to check non existing columns
				if (board[row][col] == 0 && !doesColContain(col, val) && !doesBoxContain(row, col, val)) {		// If the element on board doesnt have a value & neither the box or column contains val...
					validSpaces++;																				// We found a valid space to put val
				}
				
				if (validSpaces == 1 && s == null) {															// If only 1 spot available for val, then initialize s and set "row" & "col"
					s = new Spot(row, col);
				} else if (validSpaces > 1)																		// If there is more than one valid space then I cant put val anywhere in the row
					s = null;
			}
		}
		return s;
	}
	
	
	// Return a valid spot if only one possibility for val in col, return null otherwise
	private Spot colFill(int col, int val)	{
		int validSpaces = 0;																					// Tracks the number of valid spaces for val
		Spot s = null;																							// Create a spot just in case we find valid place to put val
		if (!doesColContain(col, val)) {																		// If the col doesnt contain the value
			for (int row = 0; row <= 8; row++) {																// As long as we dont try to check non existing rows
				if (board[row][col] == 0 && !doesRowContain(row, val) && !doesBoxContain(row, col, val)) {		// If the element on board doesnt have a value & neither the box or column contains val...
					validSpaces++;																				// We found a valid space to put val
				}
				
				if (validSpaces == 1 && s == null) {															// If only 1 spot available for val, then initialize s and set "row" & "col"
					s = new Spot(row, col);
				} else if (validSpaces > 1) {																	// If there is more than one valid space then I cant put val anywhere in the col
					s = null;
				}
			}
		}
		return s;
	}
	
	
	// Return a valid spot if only 1 possibility for val in the box, return null otherwise
	private Spot boxFill(int rowBox,int colBox, int val) {
		int validSpaces = 0;
		Spot s = null;
		if (!doesBoxContain(rowBox, colBox, val) ) {
			for (int i = 0; i <= 2; i++) {
				for (int j = 0; j <= 2; j++) {
					if (board[rowBox + i][colBox + j] == 0 && !doesRowContain(rowBox + i, val) && !doesColContain(colBox + j, val)) {
						validSpaces++;
					}
					
					if(validSpaces == 1 && s == null) {
						s = new Spot(rowBox + i, colBox + j);
					} else if (validSpaces > 1) {
						s = null;
					}
				}
			}
		}
		return s;
	}
	
	
	// Return int n if n is the only possible value for this spot, return 0 otherwise
	private int fillSpot(Spot sq) {
		int numOfValidValues = 0, validValue = 0;
		for (int val = 1; val <= 9; val++) {											// Check all values for current spot
			if (!doesRowContain(sq.getRow(), val))										// Does the row contain val?
				if (!doesColContain(sq.getRow(), val))									// Does the col contain val?
					if(!doesBoxContain(sq.getRow(), sq.getCol(), val)) {				// Does the box contain val?
						validValue = val;												// This value is valid for this spot
						numOfValidValues++;												// Total number of valid values for this spot
					}
		}
		
		if (numOfValidValues > 1)														// If there is more than 1 valid value then i cant fill the spot with any val
			validValue = 0;
		
		return validValue;
	}
	
	
	// Attempt to solve the entire sudoku puzzle
	public void solve() {
		boolean changes = false;											// Tracks any changes made to the board
														
		do {
			changes = false;
			// RowFill
			for (int row = 0; row <= 8; row++) {
				for (int val = 1; val <= 9; val++) {						// val = number to be input into the row
					Spot s = rowFill(row, val);								// If rowFill finds a spot to put val then return the spot, else return null
					if (s != null) {										// If rowFill found a spot, then set the spot == val
						changes = true;										// Changes were made to the board
						board[s.getRow()][s.getCol()] = val;				// insert the value into the board
					}
				}
			}
			
			// colFill
			for (int col = 0; col <= 8; col++) {
				for (int val = 1; val <= 9; val++) {
					Spot s = colFill(col, val);
					if (s != null) {
						changes = true;
						board[s.getRow()][s.getCol()] = val;
					}
				}
			}
			
			// boxFill
			for (int rowBox = 0; rowBox <= 6; rowBox += 3) {
				for (int colBox = 0; colBox <= 6; colBox += 3) {
					for (int val = 1; val <= 9; val++) {
						Spot s = boxFill(rowBox, colBox, val);
						if (s != null) {
							changes = true;
							board[s.getRow()][s.getCol()] = val;
						}
					}
				}
			}
		} while (changes == true);											// If no changes are made to the board then its considered solved
	}
	
	
	
	// Prints the sudoku board in board format
	public String toString() {
		String result = new String();
		System.out.println("\n");
		for (int y = 0; y <= 8; y++) {
			if (y == 3 || y == 6)
						result = result + "---------------\n";
			for (int x = 0; x <= 8; x++) {
				result = result + String.valueOf(board[y][x]);
				if (x == 2 || x == 5)
					result = result + " | " ;
			}
			result = result + "\n";
		}
		return result;
	}
	
	
	// Prints the values of the sudoku board in a single line string
	public String toString2() {
		String result = new String();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				result = result + String.valueOf(board[row][col]);            
			}
		}
		return result;    
	}

	
	// Prints my name
	public static String myName() {
		return "Brandon Cobb";
	}
}