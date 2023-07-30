package com.tco.chess;

import java.util.ArrayList;

import com.tco.misc.IllegalPositionException;

public class King extends ChessPiece {

	public King(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if (this.color == Color.WHITE) {
			return "\u2654";
		}
		else if (this.color == Color.BLACK) {
			return "\u265A";
		}
		else {
			return null;
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> legalMoves = new ArrayList<String>();
		
		legalMoves.addAll(legalMovesHorizontal());
		legalMoves.addAll(legalMovesVertical());
		legalMoves.addAll(legalMovesDiagonal());
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesHorizontal() {
		ArrayList<String> legalMoves = new ArrayList<String>();
		
		String west_position = "" + (char)(this.column + 97 - 1) + (this.row + 1);
		ChessPiece west_piece = null;
		try {
			west_piece =  this.board.getPiece(west_position);
			if (west_piece == null) {
				legalMoves.add(west_position);
			}
			else if (west_piece.color != this.color) {
				legalMoves.add(west_position);
			}
		} catch (IllegalPositionException e) {}
		
		String east_position = "" + (char)(this.column + 97 + 1) + (this.row + 1);
		ChessPiece east_piece = null;
		try {
			east_piece =  this.board.getPiece(east_position);
			if (east_piece == null) {
				legalMoves.add(east_position);
			}
			else if (east_piece.color != this.color) {
				legalMoves.add(east_position);
			}
		} catch (IllegalPositionException e) {}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesVertical() {
		ArrayList<String> legalMoves = new ArrayList<String>();
		
		String north_position = "" + (char)(this.column + 97) + (this.row + 1 - 1);
		ChessPiece north_piece = null;
		try {
			north_piece =  this.board.getPiece(north_position);
			if (north_piece == null) {
				legalMoves.add(north_position);
			}
			else if (north_piece.color != this.color) {
				legalMoves.add(north_position);
			}
		} catch (IllegalPositionException e) {}
		
		String south_position = "" + (char)(this.column + 97) + (this.row + 1 + 1);
		ChessPiece south_piece = null;
		try {
			south_piece =  this.board.getPiece(south_position);
			if (south_piece == null) {
				legalMoves.add(south_position);
			}
			else if (south_piece.color != this.color) {
				legalMoves.add(south_position);
			}
		} catch (IllegalPositionException e) {}
		
		return legalMoves;
	}
	
	private ArrayList<String> legalMovesDiagonal() {
		ArrayList<String> legalMoves = new ArrayList<String>();
		
		String NW_position = "" + (char)(this.column + 97 - 1) + (this.row + 1 + 1);
		ChessPiece NW_piece = null;
		try {
			NW_piece =  this.board.getPiece(NW_position);
			if (NW_piece == null) {
				legalMoves.add(NW_position);
			}
			else if (NW_piece.color != this.color) {
				legalMoves.add(NW_position);
			}
		} catch (IllegalPositionException e) {}
		
		String NE_position = "" + (char)(this.column + 97 + 1) + (this.row + 1 + 1);
		ChessPiece NE_piece = null;
		try {
			NE_piece =  this.board.getPiece(NE_position);
			if (NE_piece == null) {
				legalMoves.add(NE_position);
			}
			else if (NE_piece.color != this.color) {
				legalMoves.add(NE_position);
			}
		} catch (IllegalPositionException e) {}
		
		String SW_position = "" + (char)(this.column + 97 - 1) + (this.row + 1 - 1);
		ChessPiece SW_piece = null;
		try {
			SW_piece =  this.board.getPiece(SW_position);
			if (SW_piece == null) {
				legalMoves.add(SW_position);
			}
			else if (SW_piece.color != this.color) {
				legalMoves.add(SW_position);
			}
		} catch (IllegalPositionException e) {}
		
		String SE_position = "" + (char)(this.column + 97 + 1) + (this.row + 1 - 1);
		ChessPiece SE_piece = null;
		try {
			SE_piece =  this.board.getPiece(SE_position);
			if (SE_piece == null) {
				legalMoves.add(SE_position);
			}
			else if (SE_piece.color != this.color) {
				legalMoves.add(SE_position);
			}
		} catch (IllegalPositionException e) {}
		
		return legalMoves;
	}

}
