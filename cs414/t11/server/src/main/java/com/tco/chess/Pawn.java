package com.tco.chess;

import java.util.ArrayList;

import com.tco.misc.IllegalPositionException;

public class Pawn extends ChessPiece {

	public Pawn(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2659";
		}
		else if (this.color == Color.BLACK) {
			return "\u265F";
		}
		else {
			return null;
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		legalMoves.addAll(legalMovesInitial());
		legalMoves.addAll(legalMovesStraight());
		legalMoves.addAll(legalMovesDiagonal());
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesInitial() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		if (this.color == Color.WHITE) {
			if (this.row == 1) {
				String first_position = "" + (char)(this.column + 97) + (this.row + 1 + 1);
				try {
					ChessPiece first_piece = this.board.getPiece(first_position);
					if (first_piece == null) {
						String second_position = "" + (char)(this.column + 97) + (this.row + 1 + 2);
						ChessPiece second_piece = this.board.getPiece(second_position);
						if (second_piece == null) {
							legalMoves.add(second_position);
						}
					}
				} catch (IllegalPositionException e) {}
			}
		}
		else if (this.color == Color.BLACK) {
			if (this.row == 6) {
				String first_position = "" + (char)(this.column + 97) + (this.row + 1 - 1);
				try {
					ChessPiece first_piece = this.board.getPiece(first_position);
					if (first_piece == null) {
						String second_position = "" + (char)(this.column + 97) + (this.row + 1 - 2);
						ChessPiece second_piece = this.board.getPiece(second_position);
						if (second_piece == null) {
							legalMoves.add(second_position);
						}
					}
				} catch (IllegalPositionException e) {}
			}
		}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesStraight() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		if (this.color == Color.WHITE) {
			String position = "" + (char)(this.column + 97) + (this.row + 1 + 1);
			try {
				ChessPiece piece = this.board.getPiece(position);
				if (piece == null) {
					legalMoves.add(position);
				}
			} catch (IllegalPositionException e) {}
		}
		else if (this.color == Color.BLACK) {
			String position = "" + (char)(this.column + 97) + (this.row + 1 - 1);
			try {
				ChessPiece piece = this.board.getPiece(position);
				if (piece == null) {
					legalMoves.add(position);
				}
			} catch (IllegalPositionException e) {}
		}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesDiagonal() {
		ArrayList<String> legalMoves = new ArrayList<>();
		
		if (this.color == Color.WHITE) {
			String west_position = "" + (char)(this.column + 97 - 1) + (this.row + 1 + 1);
			try {
				ChessPiece piece = this.board.getPiece(west_position);
				if (piece != null && piece.color == Color.BLACK) {
					legalMoves.add(west_position);
				}
			} catch (IllegalPositionException e) {}
			
			String east_position = "" + (char)(this.column + 97 + 1) + (this.row + 1 + 1);
			try {
				ChessPiece piece = this.board.getPiece(east_position);
				if (piece != null && piece.color == Color.BLACK) {
					legalMoves.add(east_position);
				}
			} catch (IllegalPositionException e) {}
		}
		else if (this.color == Color.BLACK) {
			String west_position = "" + (char)(this.column + 97 - 1) + (this.row + 1 - 1);
			try {
				ChessPiece piece = this.board.getPiece(west_position);
				if (piece != null && piece.color == Color.WHITE) {
					legalMoves.add(west_position);
				}
			} catch (IllegalPositionException e) {}
			
			String east_position = "" + (char)(this.column + 97 + 1) + (this.row + 1 - 1);
			try {
				ChessPiece piece = this.board.getPiece(east_position);
				if (piece != null && piece.color == Color.WHITE) {
					legalMoves.add(east_position);
				}
			} catch (IllegalPositionException e) {}
		}
		
		return legalMoves;
	}

}
