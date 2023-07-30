package com.tco.chess;

import java.util.ArrayList;

import com.tco.misc.IllegalPositionException;

public class Bishop extends ChessPiece {

	public Bishop(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2657";
		}
		else if (this.color == Color.BLACK) {
			return "\u265D";
		}
		return null;
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		legalMoves.addAll(legalMovesNorthWest());
		legalMoves.addAll(legalMovesNorthEast());
		legalMoves.addAll(legalMovesSouthWest());
		legalMoves.addAll(legalMovesSouthEast());
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesNorthWest() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97 - count) + (this.row + 1 + count);
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
			}
		}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesNorthEast() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97 + count) + (this.row + 1 + count);
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
	
	private ArrayList<String> legalMovesSouthWest() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97 - count) + (this.row + 1 - count);
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
			}
		}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesSouthEast() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		int count = 0;
		while (true) {
			count++;
			String position = "" + (char)(this.column + 97 + count) + (this.row + 1 - count);
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
			}
		}
		
		return legalMoves;
	}

}
