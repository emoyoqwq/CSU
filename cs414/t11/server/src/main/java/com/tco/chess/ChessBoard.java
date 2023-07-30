package com.tco.chess;

import java.util.ArrayList;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

public class ChessBoard {
	
	private ChessPiece[][] board;
	
	ChessBoard() {
		board = new ChessPiece[8][8];
	}
	
	public void initialize() {
		placePiece(new Rook(this, Color.BLACK), "a8");
		placePiece(new Knight(this, Color.BLACK), "b8");
		placePiece(new Bishop(this, Color.BLACK), "c8");
		placePiece(new Queen(this, Color.BLACK), "d8");
		placePiece(new King(this, Color.BLACK), "e8");
		placePiece(new Bishop(this, Color.BLACK), "f8");
		placePiece(new Knight(this, Color.BLACK), "g8");
		placePiece(new Rook(this, Color.BLACK), "h8");
		
		placePiece(new Pawn(this, Color.BLACK), "a7");
		placePiece(new Pawn(this, Color.BLACK), "b7");
		placePiece(new Pawn(this, Color.BLACK), "c7");
		placePiece(new Pawn(this, Color.BLACK), "d7");
		placePiece(new Pawn(this, Color.BLACK), "e7");
		placePiece(new Pawn(this, Color.BLACK), "f7");
		placePiece(new Pawn(this, Color.BLACK), "g7");
		placePiece(new Pawn(this, Color.BLACK), "h7");
		
		placePiece(new Rook(this, Color.WHITE), "a1");
		placePiece(new Knight(this, Color.WHITE), "b1");
		placePiece(new Bishop(this, Color.WHITE), "c1");
		placePiece(new Queen(this, Color.WHITE), "d1");
		placePiece(new King(this, Color.WHITE), "e1");
		placePiece(new Bishop(this, Color.WHITE), "f1");
		placePiece(new Knight(this, Color.WHITE), "g1");
		placePiece(new Rook(this, Color.WHITE), "h1");
		
		placePiece(new Pawn(this, Color.WHITE), "a2");
		placePiece(new Pawn(this, Color.WHITE), "b2");
		placePiece(new Pawn(this, Color.WHITE), "c2");
		placePiece(new Pawn(this, Color.WHITE), "d2");
		placePiece(new Pawn(this, Color.WHITE), "e2");
		placePiece(new Pawn(this, Color.WHITE), "f2");
		placePiece(new Pawn(this, Color.WHITE), "g2");
		placePiece(new Pawn(this, Color.WHITE), "h2");
	}
	
	public ChessPiece getPiece(String position) throws IllegalPositionException {
		int col = position.charAt(0) - 97;
		int row = Character.getNumericValue(position.charAt(1)) - 1;
		
		if (row > 7 || row < 0 || col > 7 || col < 0) {
			throw new IllegalPositionException();
		}
		
		return board[row][col];
	}
	
	public boolean placePiece(ChessPiece piece, String position) {
		int col = position.charAt(0) - 97;
		int row = Character.getNumericValue(position.charAt(1)) - 1;
		
		if (row > 7 || row < 0 || col > 7 || col < 0) {
			return false;
		}
		if (this.board[row][col] == null) {
			try {
				piece.setPosition(position);
			} catch (IllegalPositionException e) {}
			this.board[row][col] = piece;
			return true;
		}
		if (this.board[row][col].color == piece.color) {
			return false;
		}
		
		try {
			piece.setPosition(position);
		} catch (IllegalPositionException e) {}
		this.board[row][col] = piece;
		return true;
	}
	
	public void move(String fromPosition, String toPosition) throws IllegalMoveException {
		try {
			ChessPiece moving_piece = getPiece(fromPosition);
			if (moving_piece == null) {
				throw new IllegalPositionException();
			}
			ArrayList<String> legalMoves = moving_piece.legalMoves();
			if (legalMoves.contains(toPosition)) {
				int from_col = fromPosition.charAt(0) - 97;
				int from_row = Character.getNumericValue(fromPosition.charAt(1)) - 1;
				
				placePiece(moving_piece, toPosition);
				this.board[from_row][from_col] = null;
			}
			else {
				throw new IllegalMoveException();
			}
		} catch (IllegalPositionException e) {
			throw new IllegalMoveException();
		}
	}
	
	public String toString(){
	    String chess="";
	    String upperLeft = "\u250C";
	    String upperRight = "\u2510";
	    String horizontalLine = "\u2500";
	    String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
	    String verticalLine = "\u2502";
	    String upperT = "\u252C";
	    String bottomLeft = "\u2514";
	    String bottomRight = "\u2518";
	    String bottomT = "\u2534";
	    String plus = "\u253C";
	    String leftT = "\u251C";
	    String rightT = "\u2524";

	    String topLine = upperLeft;
	    for (int i = 0; i<7; i++){
	        topLine += horizontal3 + upperT;
	    }
	    topLine += horizontal3 + upperRight;

	    String bottomLine = bottomLeft;
	    for (int i = 0; i<7; i++){
	        bottomLine += horizontal3 + bottomT;
	    }
	    bottomLine += horizontal3 + bottomRight;
	    chess+=topLine + "\n";

	    for (int row = 7; row >=0; row--){
	        String midLine = "";
	        for (int col = 0; col < 8; col++){
	            if(board[row][col]==null) {
	                midLine += verticalLine + " \u3000 ";
	            } else {midLine += verticalLine + " "+board[row][col]+" ";}
	        }
	        midLine += verticalLine;
	        String midLine2 = leftT;
	        for (int i = 0; i<7; i++){
	            midLine2 += horizontal3 + plus;
	        }
	        midLine2 += horizontal3 + rightT;
	        chess+=midLine+ "\n";
	        if(row>=1)
	            chess+=midLine2+ "\n";
	    }

	    chess+=bottomLine;
	    return chess;
	}	
}
