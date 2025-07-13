package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */
/*
 * represents the logic where the board is updated on each step of the game
 * animation
 */
public abstract class GameModel {
	protected BoardCell[][] board;

	/**
	 * Creates a rectangular board of the specified size, filling it with
	 * BoardCell.EMPTY cells.
	 * 
	 * @param rows number of rows on the board
	 * @param cols number of columns on the board
	 */

	public GameModel(int rows, int cols) {
		/*
		 * instantiating the 2D board cell array and creating its dimensions the row and
		 * number of column by using the values passed in through the parameter
		 */
		this.board = new BoardCell[rows][cols];
		// looping thru rows of 2D array
		for (int row = 0; row < board.length; row++) {
			// looping thru columns of 2D array
			for (int col = 0; col < board[row].length; col++) {
				// instantiating each cell to be empty
				board[row][col] = BoardCell.EMPTY;
			}
		}

	}

	/**
	 * Returns the number of rows on the board
	 * 
	 * @return number of rows
	 */
	public int getRows() {
		return board.length;
	}

	/**
	 * Returns the number of columns on the board
	 * 
	 * @return number of columns
	 */
	public int getCols() {
		/*
		 * returning the number of columns in the first row since the board is a square,
		 * all the rows have the same number of columns
		 */
		return board[0].length;

	}

	/**
	 * Returns a reference to the board without making any kind of copy.
	 * 
	 * @return the board
	 */
	public BoardCell[][] getBoard() {
		return board; // This is done for you. Just return the reference.
	}

	/**
	 * Sets the cell at the specified location to value provided.
	 * 
	 * @param rowIndex  row to set
	 * @param colIndex  column to set
	 * @param boardCell value to assign to the board
	 */
	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		this.board[rowIndex][colIndex] = boardCell;
	}

	/**
	 * Returns the value at a given location of the board
	 * 
	 * @param rowIndex row to access
	 * @param colIndex column to access
	 * @return value of board at the specified location
	 */
	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return this.board[rowIndex][colIndex];
	}

	/**
	 * Provides a string representation of the board We have implemented this method
	 * for you.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Board(Rows: " + board.length + ", Cols: " + board[0].length + ")\n");
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++)
				buffer.append(board[row][col].getName());
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the selected
	 * cell.
	 * 
	 * @param rowIndex row that user has clicked
	 * @param colIndex column that user has clicked
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}