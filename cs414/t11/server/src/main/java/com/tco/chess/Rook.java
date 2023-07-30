package com.tco.chess;

import java.util.ArrayList;

import com.tco.misc.IllegalPositionException;

public class Rook extends ChessPiece {

	public Rook(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2656";
		}
		else if (this.color == Color.BLACK) {
			return "\u265C";
		}
		else {
			return "[ERROR] Invalid Piece";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> legalMoves = new ArrayList<>();
		legalMoves.addAll(legalMovesUp());
		legalMoves.addAll(legalMovesDown());
		legalMoves.addAll(legalMovesLeft());
		legalMoves.addAll(legalMovesRight());
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesUp() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97) + (this.row + 1 + count);
			ChessPiece piece = null;
			try {
				piece =  this.board.getPiece(position);
			} catch (IllegalPositionException e) {
				break;
			}
			if (piece == null) {
				legalMoves.add(position);
			}
			else if (piece.color == this.color) {
				break;
			}
			else if (piece.color != this.color) {
				legalMoves.add(position);
				break;
			}
		}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesDown() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97) + (this.row + 1 - count);
			ChessPiece piece = null;
			try {
				piece =  this.board.getPiece(position);
			} catch (IllegalPositionException e) {
				break;
			}
			if (piece == null) {
				legalMoves.add(position);
			}
			else if (piece.color == this.color) {
				break;
			}
			else if (piece.color != this.color) {
				legalMoves.add(position);
				break;
			}
		}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesLeft() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97 - count) + (this.row + 1);
			ChessPiece piece = null;
			try {
				piece =  this.board.getPiece(position);
			} catch (IllegalPositionException e) {
				break;
			}
			if (piece == null) {
				legalMoves.add(position);
			}
			else if (piece.color == this.color) {
				break;
			}
			else if (piece.color != this.color) {
				legalMoves.add(position);
				break;
			}
		}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesRight() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97 + count) + (this.row + 1);
			ChessPiece piece = null;
			try {
				piece =  this.board.getPiece(position);
			} catch (IllegalPositionException e) {
				break;
			}
			if (piece == null) {
				legalMoves.add(position);
			}
			else if (piece.color == this.color) {
				break;
			}
			else if (piece.color != this.color) {
				legalMoves.add(position);
				break;
			}
		}
		
		return legalMoves;
	}

}
