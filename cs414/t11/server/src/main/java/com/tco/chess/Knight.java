package com.tco.chess;

import java.util.ArrayList;

import com.tco.misc.IllegalPositionException;

public class Knight extends ChessPiece {

	public Knight(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2658";
		}
		else if (this.color == Color.BLACK) {
			return "\u265E";
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
