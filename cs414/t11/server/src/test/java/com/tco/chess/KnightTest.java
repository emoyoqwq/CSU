package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

class KnightTest {

	@Test
	void testToString() {
		ChessBoard board = new ChessBoard();
		assertEquals("\u2658", new Knight(board, Color.WHITE).toString(), "Knight toString() Failed.");
		assertEquals("\u265E", new Knight(board, Color.BLACK).toString(), "Knight toString() Failed.");
	}

	@Test
	void testKnight() {
		ChessBoard board = new ChessBoard();
		ChessPiece knight = new Knight(board, Color.WHITE);
		assertTrue(knight instanceof Knight, "Knight Constructor Failed.");
	}

	@Test
	void testChessPiece() {
		ChessBoard board = new ChessBoard();
		ChessPiece knight = new Knight(board, Color.WHITE);
		assertTrue(knight instanceof ChessPiece, "ChessPiece Constructor Failed.");
	}

	@Test
	void testGetColor() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		Color expected = Color.WHITE;
		Color actual = null;
		try {
			actual = board.getPiece("b1").getColor();
		} catch (IllegalPositionException e) {}
		assertEquals(expected, actual, "Knight getColor() Failed.");
	}

	@Test
	void testGetPosition() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		String actual = "";
		try {
			actual = board.getPiece("b1").getPosition();
		} catch (IllegalPositionException e) {}
		
		assertEquals("b1", actual, "Knight getPosition() Failed.");
	}

	@Test
	void testSetPosition() {
		ChessBoard board = new ChessBoard();
		ChessPiece piece = new Knight(board, Color.WHITE);
		try {
			piece.setPosition("a1");
		} catch (IllegalPositionException e) {}
		assertEquals("a1", piece.getPosition(), "Knight setPosition() Failed.");
	}
	
	@Test
	void testLegalMoves() {
		ChessBoard board = new ChessBoard();
		ArrayList<String> expected = new ArrayList<>();
		ChessPiece knight = new Knight(board, Color.WHITE);
		try {
			knight.setPosition("a1");
		} catch (IllegalPositionException e) {}
		board.placePiece(knight, "a1");
		ArrayList<String> actual = knight.legalMoves();
		assertEquals(expected, actual, "Knight legalMoves() Failed.");
	}

}
