package com.tco.chess;

import java.util.ArrayList;

import com.tco.misc.IllegalPositionException;

public abstract class ChessPiece {
	
	public enum Color {WHITE, BLACK};
	protected ChessBoard board;			// The board it belongs to, default is null
	protected int row;					// The index of the horizontal rows in the range 0..7
	protected int column;				// The index of the vertical column in the range 0..7
	protected Color color;				// The color of the piece.
	
	public ChessPiece(ChessBoard board, Color color) {
		this.board = board;
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String getPosition() {
		char col_char = (char) (this.column + 97);
		String column_string = "" + col_char;
		
		return column_string + (this.row + 1);
	}
	
	public void setPosition(String position) throws IllegalPositionException {
		int col = position.charAt(0) - 97;
		int row = Character.getNumericValue(position.charAt(1)) - 1;
		
		if (row > 7 || row < 0 || col > 7 || col < 0) {
			throw new IllegalPositionException();
		}
		this.row = row;
		this.column = col;
	}
	
	abstract public String toString();
	
	abstract public ArrayList<String> legalMoves();
	
}
