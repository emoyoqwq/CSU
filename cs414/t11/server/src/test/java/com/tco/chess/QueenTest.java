package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

class QueenTest {

	@Test
	void testToString() {
		ChessBoard board = new ChessBoard();
		assertEquals("\u2655", new Queen(board, Color.WHITE).toString(), "Queen toString() Failed.");
		assertEquals("\u265B", new Queen(board, Color.BLACK).toString(), "Queen toString() Failed.");
	}

	@Test
	void testQueen() {
		ChessBoard board = new ChessBoard();
		ChessPiece queen = new Queen(board, Color.WHITE);
		assertTrue(queen instanceof Queen, "Queen Constructor Failed.");
	}

	@Test
	void testChessPiece() {
		ChessBoard board = new ChessBoard();
		ChessPiece queen = new Queen(board, Color.WHITE);
		assertTrue(queen instanceof ChessPiece, "ChessPiece Constructor Failed.");
	}

	@Test
	void testGetColor() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		Color expected = Color.WHITE;
		Color actual = null;
		try {
			actual = board.getPiece("d1").getColor();
		} catch (IllegalPositionException e) {}
		assertEquals(expected, actual, "Queen getColor() Failed.");
	}

	@Test
	void testGetPosition() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		String actual = "";
		try {
			actual = board.getPiece("d1").getPosition();
		} catch (IllegalPositionException e) {}
		
		assertEquals("d1", actual, "Queen getPosition() Failed.");
	}

	@Test
	void testSetPosition() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Queen(board, Color.WHITE);
		try {
			piece.setPosition("a1");
		} catch (IllegalPositionException e) {}
		assertEquals("a1", piece.getPosition(), "Queen setPosition() Failed.");
	}
	
	@Test
	void testLegalMoves() {
		ChessBoard board = new ChessBoard();
		ArrayList<String> expected = new ArrayList<>();
		ChessPiece queen = new Queen(board, Color.WHITE);
		try {
			queen.setPosition("a1");
		} catch (IllegalPositionException e) {}
		board.placePiece(queen, "a1");
		ArrayList<String> actual = queen.legalMoves();
		assertEquals(expected, actual, "Queen legalMoves() Failed.");
	}

}
