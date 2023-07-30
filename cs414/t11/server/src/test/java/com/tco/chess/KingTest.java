package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

class KingTest {

	@Test
	void testToString() {
		ChessBoard board = new ChessBoard();
		assertEquals("\u2654", new King(board, Color.WHITE).toString(), "King toString() Failed.");
		assertEquals("\u265A", new King(board, Color.BLACK).toString(), "King toString() Failed.");
	}

	@Test
	void testKing() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		assertTrue(king instanceof King, "King Constructor Failed.");
	}

	@Test
	void testChessPiece() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		assertTrue(king instanceof ChessPiece, "ChessPiece Constructor Failed.");
	}

	@Test
	void testGetColor() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		Color expected = Color.WHITE;
		Color actual = null;
		try {
			actual = board.getPiece("e1").getColor();
		} catch (IllegalPositionException e) {}
		assertEquals(expected, actual, "King getColor() Failed.");
	}

	@Test
	void testGetPosition() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		String actual = "";
		try {
			actual = board.getPiece("e1").getPosition();
		} catch (IllegalPositionException e) {}
		
		assertEquals("e1", actual, "King getPosition() Failed.");
	}

	@Test
	void testSetPosition() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new King(board, Color.WHITE);
		try {
			piece.setPosition("a1");
		} catch (IllegalPositionException e) {}
		assertEquals("a1", piece.getPosition(), "King setPosition() Failed.");
	}

	@Test
	void testKingMoveHorizontalPass() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		try {
			king.setPosition("b1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(king, "b1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("b1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "c1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("c1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "King Valid Horizontal Move Failed.");
	}
	
	@Test
	void testKingMoveHorizontalFail() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		ChessPiece queen = new Queen(board, Color.WHITE);
		try {
			king.setPosition("b1");
			queen.setPosition("c1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(king, "b1");
		board.placePiece(queen, "c1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("c1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "c1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("c1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "King Invalid Horizontal Move Succeeded.");
	}
	
	@Test
	void testKingMoveVerticalPass() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		try {
			king.setPosition("b1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(king, "b1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("b1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "b2");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b2");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "King Valid Vertical Move Failed.");
	}
	
	@Test
	void testKingMoveVerticalFail() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		ChessPiece queen = new Queen(board, Color.WHITE);
		try {
			king.setPosition("b1");
			queen.setPosition("b2");
		} catch (IllegalPositionException e1) {}
		board.placePiece(king, "b1");
		board.placePiece(queen, "b2");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("b2");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "b2");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b2");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "King Invalid Vertical Move Succeeded.");
	}
	
	@Test
	void testKingMoveDiagonalPass() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		try {
			king.setPosition("b1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(king, "b1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("b1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "c2");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("c2");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "King Valid Diagonal Move Failed.");
	}
	
	@Test
	void testKingMoveDiagonalFail() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		ChessPiece queen = new Queen(board, Color.WHITE);
		try {
			king.setPosition("b1");
			queen.setPosition("c2");
		} catch (IllegalPositionException e1) {}
		board.placePiece(king, "b1");
		board.placePiece(queen, "c2");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("c2");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "c2");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("c2");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "King Invalid Diagonal Move Succeeded.");
	}
	
	@Test
	void testKingCapturePass() {
		ChessBoard board = new ChessBoard();
		ChessPiece king = new King(board, Color.WHITE);
		ChessPiece queen = new Queen(board, Color.BLACK);
		try {
			king.setPosition("b1");
			queen.setPosition("c2");
		} catch (IllegalPositionException e1) {}
		board.placePiece(king, "b1");
		board.placePiece(queen, "c2");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("b1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "c2");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("c2");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "King Valid Capture Failed.");
	}

}
