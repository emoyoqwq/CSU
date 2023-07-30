package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

class BishopTest {

	@Test
	void testToString() {
		ChessBoard board = new ChessBoard();
		
		assertEquals("\u2657", new Bishop(board, Color.WHITE).toString(), "Bishop toString() Failed.");
		assertEquals("\u265D", new Bishop(board, Color.BLACK).toString(), "Bishop toString() Failed.");
	}

	@Test
	void testBishop() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Bishop(board, Color.WHITE);
		assertTrue(piece instanceof Bishop, "Bishop Constructor Failed.");
	}

	@Test
	void testChessPiece() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Bishop(board, Color.WHITE);
		assertTrue(piece instanceof ChessPiece, "ChessPiece Constructor Failed.");
	}

	@Test
	void testGetColor() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		Color expected = Color.WHITE;
		Color actual = null;
		try {
			actual = board.getPiece("c1").getColor();
		} catch (IllegalPositionException e) {}
		assertEquals(expected, actual, "Bishop getColor() Failed.");
	}

	@Test
	void testGetPosition() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		String actual = "";
		try {
			actual = board.getPiece("c1").getPosition();
		} catch (IllegalPositionException e) {}
		
		assertEquals("c1", actual, "Bishop getPosition() Failed.");
	}

	@Test
	void testSetPosition() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Bishop(board, Color.WHITE);
		try {
			piece.setPosition("a1");
		} catch (IllegalPositionException e) {}
		assertEquals("a1", piece.getPosition(), "Bishop setPosition() Failed.");
	}
	
	@Test
	void testBishopMovePass() {
		ChessBoard board = new ChessBoard();
		ChessPiece bishop = new Bishop(board, Color.WHITE);
		try {
			bishop.setPosition("a1");
		} catch (IllegalPositionException e2) {}
		board.placePiece(bishop, "a1");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a1", "d4");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("d4");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Successful Bishop Move Failed.");
	}
	
	@Test
	void testBishopMoveFail() {
		ChessBoard board = new ChessBoard();
		ChessPiece bishop = new Bishop(board, Color.WHITE);
		ChessPiece blocker = new Pawn(board, Color.BLACK);
		try {
			bishop.setPosition("a1");
			blocker.setPosition("b2");
		} catch (IllegalPositionException e2) {}
		board.placePiece(bishop, "a1");
		board.placePiece(blocker, "b2");
		
		try {
			board.move("a1", "d4");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("d4");
		} catch (IllegalPositionException e) {}
		
		assertEquals(null, actual, "Unsuccessful Bishop Move Failed.");
	}
	
	@Test
	void testBishopCapturePass() {
		ChessBoard board = new ChessBoard();
		ChessPiece bishop = new Bishop(board, Color.WHITE);
		ChessPiece fodder = new Pawn(board, Color.BLACK);
		try {
			bishop.setPosition("a1");
			fodder.setPosition("d4");
		} catch (IllegalPositionException e2) {}
		board.placePiece(bishop, "a1");
		board.placePiece(fodder, "d4");
		
		ChessPiece expected = null;
		try {
			expected = board.getPiece("a1");
		} catch (IllegalPositionException e1) {}
		
		try {
			board.move("a1", "d4");
		} catch (IllegalMoveException e) {}
		
		ChessPiece actual = null;
		
		try {
			actual = board.getPiece("d4");
		} catch (IllegalPositionException e) {}
		
		assertEquals(expected, actual, "Successful Bishop Move Failed.");
	}

}
