package com.tco.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tco.chess.ChessPiece.Color;
import com.tco.misc.IllegalPositionException;
import com.tco.misc.IllegalMoveException;

class ChessBoardTest {

	@Test
	void testChessBoard() {
		ChessBoard board = new ChessBoard();
		
		assertTrue(board instanceof ChessBoard, "ChessBoard Creation Failed.");
	}

	@Test
	void testInitialize() {
		ChessBoard board = new ChessBoard();
		board.initialize();
		
		ChessPiece piece = null;
		try {
			piece = board.getPiece("a1");
		} catch (IllegalPositionException e) {}
		
		assertTrue(piece instanceof Rook, "ChessBoard Initialization Failed (A1 Piece Wrong Type).");
		assertEquals(Color.WHITE, piece.color, "ChessBoard Initialization Failed (A1 Piece Wrong Color).");
		assertEquals("a1", piece.getPosition(), "ChessBoard Initialization Failed (A1 Piece Wrong Position).");
	}

}
