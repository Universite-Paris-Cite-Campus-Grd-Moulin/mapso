package test.java.khet.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.GameController;
import model.Pion;
import model.Plateau;
import view.GameView;

public class GameControllerTest {
    @Mock
    private GameView gameView;

    @Mock
    private Plateau board;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(board.movePiece(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true); // Supposons que le déplacement
                                                                                        // est toujours valide
        when(gameView.getCellSize()).thenReturn(75); // Supposons que la taille de cellule est toujours 75
    }

    @Test
    public void testStartGame() {
        gameController.startGame();
        verify(gameView, times(1)).update(); // Vérifie que la méthode update() est appelée
    }

    @Test
    public void testHandleUserActionValidMove() {
        gameController.handleUserAction(0, 0, 1, 1);
        verify(gameView, times(1)).update();
        verify(gameView, never()).displayMessage("Mouvement invalide. Veuillez essayer à nouveau.");
    }

    @Test
    public void testRotatePiece() {
        Pion pion = new Pion(null, null, null);
        when(board.getPieceAt(anyInt(), anyInt())).thenReturn(pion);

        gameController.rotatePiece(1, 1, true);
        verify(board, times(1)).getPieceAt(1, 1);
        assertTrue(pion.isRotationRequested()); // Supposons qu'il y a une méthode isRotated() pour vérifier si le pion
                                                // a été
        // tourné
    }

    @Test
    public void testRestartGame() {
        gameController.restartGame();
        verify(board, times(2)).movePiece(anyInt(), anyInt(), anyInt(), anyInt()); // Suppose que movePiece est appelé
                                                                                   // lors de l'initialisation et du
                                                                                   // redémarrage
        verify(gameView, atLeastOnce()).update();
    }
}
