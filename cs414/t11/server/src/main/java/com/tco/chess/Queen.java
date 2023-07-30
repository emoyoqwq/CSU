package com.tco.chess;

import java.util.ArrayList;

import com.tco.misc.IllegalPositionException;

public class Queen extends ChessPiece {

	public Queen(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2655";
		}
		else if (this.color == Color.BLACK) {
			return "\u265B";
		}
		else {
			return "[ERROR] Invalid Piece";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		return new ArrayList<String>();
	}

}
