package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

class RookTest {

	@Test
	void testToString() {
		ChessBoard board = new ChessBoard();
		assertEquals("\u2656", new Rook(board, Color.WHITE).toString(), "Rook toString() Failed.");
		assertEquals("\u265C", new Rook(board, Color.BLACK).toString(), "Rook toString() Failed.");
	}

	@Test
	void testRook() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		assertTrue(rook instanceof Rook, "Rook Constructor Failed.");
	}

	@Test
	void testChessPiece() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		assertTrue(rook instanceof ChessPiece, "ChessPiece Constructor Failed.");
	}

	@Test
	void testGetColor() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		Color expected = Color.WHITE;
		Color actual = null;
		try {
			actual = board.getPiece("a1").getColor();
		} catch (IllegalPositionException e) {}
		assertEquals(expected, actual, "Rook getColor() Failed.");
	}

	@Test
	void testGetPosition() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		String actual = "";
		try {
			actual = board.getPiece("a1").getPosition();
		} catch (IllegalPositionException e) {}
		
		assertEquals("a1", actual, "Rook getPosition() Failed.");
	}

	@Test
	void testSetPosition() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Rook(board, Color.WHITE);
		try {
			piece.setPosition("a1");
		} catch (IllegalPositionException e) {}
		assertEquals("a1", piece.getPosition(), "Rook setPosition() Failed.");
	}
	
	@Test
	void testLegalMoveNorth() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		try {
			rook.setPosition("b1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "b1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("b1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b1", "b5");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b5");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Legal Move North Failed.");
	}
	
	@Test
	void testIllegalMoveNorth() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		ChessPiece blocker = new Pawn(board, Color.BLACK);
		try {
			rook.setPosition("b1");
			blocker.setPosition("b3");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "b1");
		board.placePiece(blocker, "b3");
		
		ChessPiece expected = null;
		
		try {
			board.move("b1", "b5");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b5");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Illegal Move North Succeeded.");
	}
	
	@Test
	void testLegalMoveSouth() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		try {
			rook.setPosition("b5");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "b5");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("b5");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("b5", "b1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Legal Move South Failed.");
	}
	
	@Test
	void testIllegalMoveSouth() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		ChessPiece blocker = new Pawn(board, Color.BLACK);
		try {
			rook.setPosition("b5");
			blocker.setPosition("b3");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "b5");
		board.placePiece(blocker, "b3");
		
		ChessPiece expected = null;
		
		try {
			board.move("b5", "b1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Illegal Move South Succeeded.");
	}
	
	@Test
	void testLegalMoveWest() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		try {
			rook.setPosition("d1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "d1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("d1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("d1", "a1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("a1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Legal Move West Failed.");
	}
	
	@Test
	void testIllegalMoveWest() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		ChessPiece blocker = new Pawn(board, Color.BLACK);
		try {
			rook.setPosition("d1");
			blocker.setPosition("b1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "d1");
		board.placePiece(blocker, "b1");
		
		ChessPiece expected = null;
		
		try {
			board.move("d1", "a1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("a1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Illegal Move West Succeeded.");
	}
	
	@Test
	void testLegalMoveEast() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		try {
			rook.setPosition("a1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "a1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a1", "d1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("d1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Legal Move East Failed.");
	}
	
	@Test
	void testIllegalMoveEast() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		ChessPiece blocker = new Pawn(board, Color.BLACK);
		try {
			rook.setPosition("a1");
			blocker.setPosition("b1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "a1");
		board.placePiece(blocker, "b1");
		
		ChessPiece expected = null;
		
		try {
			board.move("a1", "d1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("d1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Illegal Move East Succeeded.");
	}
	
	@Test
	void testLegalCapture() {
		ChessBoard board = new ChessBoard();
		ChessPiece rook = new Rook(board, Color.WHITE);
		ChessPiece pawn = new Pawn(board, Color.BLACK);
		try {
			rook.setPosition("a1");
			pawn.setPosition("c1");
		} catch (IllegalPositionException e1) {}
		board.placePiece(rook, "a1");
		board.placePiece(pawn, "c1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a1", "c1");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("c1");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Rook Legal Capture Failed.");
	}

}
