package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void initializeBoard() {
    	//board.initializeBoard();
        assertEquals(10, board.getWidth());
        assertEquals(8, board.getHeight());
        // Check if the pieces are correctly placed on the board
        assertNull(board.getPiece(0, 0));
        assertNotNull(board.getPiece(9, 7));
    }
}