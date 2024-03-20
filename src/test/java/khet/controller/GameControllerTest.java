package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GameController;
import model.Board;
import model.Game;
import model.Piece;

public class GameControllerTest {

    private GameController gameController;
    private Board board;
    private Game game;

    @BeforeEach
    public void init() {
        board = new Board();
        game = new Game(board);
        //gameController = new GameController(board, game);
    }

    @Test
    public void testStartGame() {
        gameController.startGame();
        //assertTrue(game.isGameStarted());
    }

    @Test
    public void testHandleUserAction() {
        //Piece piece = new Piece(Piece.Type.T, Piece.Couleur.ROUGE);
        //board.setPieceAt(0, 0, piece);
        int startX = 0;
        int startY = 0;
        int endX = 1;
        int endY = 1;
        gameController.handleUserAction(startX, startY, endX, endY);
        Piece movedPiece = board.getPieceAt(endX, endY);
        assertNotNull(movedPiece);
        //assertEquals(piece, movedPiece);
    }

    @Test
    public void testRotatePiece() {
        //Piece piece = new Piece(Piece.Type.T, Piece.Color.RED);
        //board.setPieceAt(0, 0, piece);
        int x = 0;
        int y = 0;
        boolean clockwise = true;
        gameController.rotatePiece(x, y, clockwise);
        Piece rotatedPiece = board.getPieceAt(x, y);
        assertNotNull(rotatedPiece);
        //assertEquals(piece, rotatedPiece);
    }

    @Test
    public void testRestartGame() {
        gameController.restartGame();
    }

}