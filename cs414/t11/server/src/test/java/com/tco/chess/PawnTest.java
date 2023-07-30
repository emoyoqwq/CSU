package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

class PawnTest {

	@Test
	void testToString() {
		ChessBoard board = new ChessBoard();
		
		assertEquals("\u2659", new Pawn(board, Color.WHITE).toString(), "Pawn toString invalid.");
		assertEquals("\u265F", new Pawn(board, Color.BLACK).toString(), "Pawn toString invalid.");
	}

	@Test
	void testPawnInitialMoveTwoSuccess() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a2");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a2", "a4");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("a4");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Pawn legalMoves() Failed.");
	}
	
	@Test
	void testPawnInitialMoveTwoFail() {
		ChessBoard board = new ChessBoard();
		ChessPiece blocker1 = new Pawn(board, Color.BLACK);
		ChessPiece blocker2 = new Pawn(board, Color.BLACK);
		try {
			blocker1.setPosition("a3");
			blocker2.setPosition("b4");
		} catch (IllegalPositionException e1) {}
		board.placePiece(blocker1, "a3");
		board.placePiece(blocker2, "b4");
		
		try {
			board.move("a2", "a4");
			board.move("b2", "b4");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual1 = null;
		ChessPiece actual2 = null;
		
		try {
			actual1 = board.getPiece("a4");
			actual2 = board.getPiece("b4");
		} catch (IllegalPositionException e) {}
		
		assertEquals(null, actual1, "Pawn legalMoves() Failed.");
		assertEquals(blocker2, actual2, "Pawn legalMoves() Failed.");
	}
	
	@Test
	void testPawnMoveOnePass() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a2");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a2", "a3");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("a3");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Pawn legalMoves() Failed.");
	}
	
	@Test
	void testPawnMoveOneFail() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		ChessPiece blocker = new Pawn(board, Color.BLACK);
		try {
			blocker.setPosition("a3");
		} catch (IllegalPositionException e2) {}
		board.placePiece(blocker, "a3");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a3");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a2", "a3");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("a3");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Pawn legalMoves() Failed.");
	}
	
	@Test
	void testPawnCapturePass() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		
		ChessPiece blocker = new Pawn(board, Color.BLACK);
		try {
			blocker.setPosition("b3");
		} catch (IllegalPositionException e2) {}
		board.placePiece(blocker, "b3");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a2");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a2", "b3");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b3");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Pawn legalMoves() Failed.");
	}
	
	@Test
	void testPawnCaptureFail() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		
		ChessPiece blocker = new Rook(board, Color.WHITE);
		try {
			blocker.setPosition("b3");
		} catch (IllegalPositionException e2) {}
		board.placePiece(blocker, "b3");
		
		ChessPiece expected = blocker;
		
		try {
			board.move("a2", "b3");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("b3");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Pawn legalMoves() Failed.");
	}

	@Test
	void testPawn() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Pawn(board, Color.WHITE);
		assertTrue(piece instanceof Pawn, "Pawn Constructor Failed.");
	}

	@Test
	void testChessPiece() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Pawn(board, Color.WHITE);
		assertTrue(piece instanceof ChessPiece, "ChessPiece Constructor Failed.");
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
		assertEquals(expected, actual, "Pawn getColor() Failed.");
	}

	@Test
	void testGetPosition() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		String actual = "";
		try {
			actual = board.getPiece("a1").getPosition();
		} catch (IllegalPositionException e) {}
		
		assertEquals("a1", actual, "Pawn getPosition() Failed.");
	}

	@Test
	void testSetPosition() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Pawn(board, Color.WHITE);
		try {
			piece.setPosition("a1");
		} catch (IllegalPositionException e) {}
		assertEquals("a1", piece.getPosition(), "Pawn setPosition() Failed.");
	}

}
