package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game,
 * specifically.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class ClearCellGameModel extends GameModel {
	// variable to represent the player's score
	private int score;
	// variable to represent the random number generator
	private Random randNum;

	/* Include whatever instance variables you think are useful. */

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board.
	 * 
	 * @param rows   number of rows in board
	 * @param cols   number of columns in board
	 * @param random random number generator to be used during game when rows are
	 *               randomly created
	 */
	public ClearCellGameModel(int rows, int cols, Random random) {
		// creating a board object using constructor in the Game Model class
		super(rows, cols);
		// assigning the Random instance variable to the parameter
		this.randNum = random;
	}

	/**
	 * The game is over when the last row (the one with index equal to
	 * board.length-1) contains at least one cell that is not empty.
	 */
	public boolean isGameOver() {
		/*
		 * variable to keep track of whether we find at least one cell that isn't empty
		 */
		int notEmpty = 0;
		// looping thru rows of thru 2D array
		for (int row = 0; row < board.length; row++) {
			/* if the loop is on the last row */
			if (row == board.length - 1) {
				// looping thru the columns in the last row
				for (int col = 0; col < board[row].length; col++) {
					/* if the cell is not empty, we increment the counter variable */
					if (board[row][col] != BoardCell.EMPTY) {
						notEmpty++;
					}
				}
			}

		}
		/* if counter is > or = to 1, the game is over, so we return true */
		if (notEmpty >= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the player's score. The player should be awarded one point for each
	 * cell that is cleared.
	 * 
	 * @return player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * This method must do nothing in the case where the game is over.
	 * 
	 * As long as the game is not over yet, this method will do the following:
	 * 
	 * 1. Shift the existing rows down by one position. 2. Insert a row of random
	 * BoardCell objects at the top of the board. The row will be filled from left
	 * to right with cells obtained by calling
	 * BoardCell.getNonEmptyRandomBoardCell(). (The Random number generator passed
	 * to the constructor of this class should be passed as the argument to this
	 * method call.)
	 */

	public void nextAnimationStep() {
		if (!isGameOver()) { // will only run when the game is not over
			// temp board to move rows down one position
			BoardCell[][] temp = new BoardCell[getRows()][getCols()];
			// looping thru rows of board
			for (int row = 0; row < board.length; row++) {
				// looping thru columns of the board
				for (int col = 0; col < board[row].length; col++) {
					/*
					 * if we are on the first row, we are assigning each cell to random board cell
					 * objects
					 */
					if (row == 0) {
						temp[row][col] = BoardCell.getNonEmptyRandomBoardCell(randNum);
					} else {
						/*
						 * if the current row we are on in the loop is not the first row, we shift its
						 * position down one and add it to the temp board
						 */
						temp[row][col] = board[row - 1][col];
					}
				}
			}
			// reassigning the new adjusted temp board to the game board
			this.board = temp;
		}
	}

	/**
	 * This method is called when the user clicks a cell on the board. If the
	 * selected cell is not empty, it will be set to BoardCell.EMPTY, along with any
	 * adjacent cells that are the same color as this one. (This includes the cells
	 * above, below, to the left, to the right, and all in all four diagonal
	 * directions.)
	 * 
	 * If any rows on the board become empty as a result of the removal of cells
	 * then those rows will "collapse", meaning that all non-empty rows beneath the
	 * collapsing row will shift upward.
	 * 
	 * @throws IllegalArgumentException with message "Invalid row index" for invalid
	 *                                  row or "Invalid column index" for invalid
	 *                                  column. We check for row validity first.
	 */

	public void processCell(int rowIndex, int colIndex) {

		if (rowIndex > getCols() || rowIndex < 0) {
			throw new IllegalArgumentException("Invalid row index");
		}
		if (colIndex > getCols() || colIndex < 0) {
			throw new IllegalArgumentException("Invalid column index");
		}

		if (board[rowIndex][colIndex] != BoardCell.EMPTY) {
			/*
			 * variable to store the color of the cell that was clicked so that we can
			 * compare the other surrounding cells to see if we need to make those empty as
			 * well
			 */
			BoardCell temp = board[rowIndex][colIndex];
			score += 1;
			board[rowIndex][colIndex] = BoardCell.EMPTY;

			if (rowIndex - 1 >= 0 && rowIndex < getRows()) {
				/* checking if the cell directly above is the same color */
				if (board[rowIndex - 1][colIndex] == temp) {
					board[rowIndex - 1][colIndex] = BoardCell.EMPTY;
					score += 1;
				}
			}

			if (rowIndex - 1 >= 0 && rowIndex < getRows() && colIndex - 1 >= 0 && colIndex < getCols()) {
				/*
				 * checking if cell above and diagonal left of clicked cell is the same color
				 */
				if (board[rowIndex - 1][colIndex - 1] == temp) {
					board[rowIndex - 1][colIndex - 1] = BoardCell.EMPTY;
					score += 1;
				}
			}

			if (rowIndex + 1 < getRows() && colIndex < getCols()) {
				/* checking if the cell directly below is the same color */
				if (board[rowIndex + 1][colIndex] == temp) {
					board[rowIndex + 1][colIndex] = BoardCell.EMPTY;
					score += 1;
				}
			}
			if (colIndex - 1 >= 0) {
				/* checking if cell directly to the left is the same color */
				if (board[rowIndex][colIndex - 1] == temp) {
					board[rowIndex][colIndex - 1] = BoardCell.EMPTY;
					score += 1;
				}
			}

			if (colIndex + 1 < getCols()) {
				/* checking if cell directly to the left is the same color */
				if (board[rowIndex][colIndex + 1] == temp) {
					board[rowIndex][colIndex + 1] = BoardCell.EMPTY;
					score += 1;
				}
			}
			if (rowIndex - 1 >= 0 && colIndex + 1 < getCols()) {
				/*
				 * checking if cell above and diagonal right of clicked cell is the same color
				 */
				if (board[rowIndex - 1][colIndex + 1] == temp) {
					board[rowIndex - 1][colIndex + 1] = BoardCell.EMPTY;
					score += 1;
				}
			}

			if (rowIndex + 1 < getRows() && colIndex - 1 >= 0 && colIndex - 1 < getCols()) {
				/*
				 * checking if cell below and diagonal left of clicked cell is the same color
				 */
				if (board[rowIndex + 1][colIndex - 1] == temp) {
					board[rowIndex + 1][colIndex - 1] = BoardCell.EMPTY;
					score += 1;
				}
			}

			if (rowIndex + 1 < getRows() && colIndex + 1 < getCols()) {
				/*
				 * checking if cell below and diagonal right of clicked cell is the same color
				 */
				if (board[rowIndex + 1][colIndex + 1] == temp) {
					board[rowIndex + 1][colIndex + 1] = BoardCell.EMPTY;
					score += 1;
				}
			}

			for (int row = rowIndex - 1; row <= rowIndex + 1; row++) {
				if (row >= 0 && row < getRows() && isRowEmpty(row)) {
					shiftRowsUp(row);
				}
			}
		}
	}

	/*
	 * private helper method that returns true or false if the row that is passed in
	 * is empty
	 */
	private boolean isRowEmpty(int rowIndex) {
		/*
		 * counter variable to keep track of the number of empty cells we find in the
		 * row
		 */
		int emptyCell = 0;

		// looping thru the columns in the row
		for (int col = 0; col < getCols(); col++) {
			/*
			 * checking if each cell is empty. if the cell is empty we increment the counter
			 */
			if (board[rowIndex][col] == BoardCell.EMPTY) {
				emptyCell++;
			}
		}
		/*
		 * if the counter = the total number of columns then we will get true meaning
		 * the row is empty, otherwise it returns false
		 */
		return emptyCell == getCols();
	}

	/*
	 * private helper method to shift rows upward once if the current row is empty
	 */
	private void shiftRowsUp(int startRow) {
		/*
		 * temporary board in order to shift row positions and then reassign to the
		 * baord
		 */
		BoardCell[][] tempBoard = new BoardCell[getRows()][getCols()];
		/*
		 * this loop is copying all the rows from the current board before the empty row
		 * and pasting them into the temp board
		 */
		for (int row = 0; row < startRow; row++) {
			tempBoard[row] = this.board[row];
		}
		/*
		 * looping from the empty row to the last row shifting the rows up one in the
		 * temp board
		 */
		for (int row = startRow; row < getRows() - 1; row++) {
			tempBoard[row] = this.board[row + 1];
		}
		/* looping thru the columns in the last row and making all the cells empty */
		for (int col = 0; col < getCols(); col++) {
			tempBoard[getRows() - 1][col] = BoardCell.EMPTY;
		}
		// reassigning the new shifted temp board to the game board
		this.board = tempBoard;
	}

}