package controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.event.MouseEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;
import model.Plateau;
import model.enums.Couleur;
import view.GameView;
import view.components.BoardPanel;

public class GameControllerTest {
    private GameController gameController;
    private GameView gameView;
    private Game game;
    private Plateau board;
    private BoardPanel boardPanel;

    @BeforeEach
    public void setUp() {
        gameView = mock(GameView.class);
        game = mock(Game.class);
        board = mock(Plateau.class);
        boardPanel = mock(BoardPanel.class);

        when(gameView.getBoardPanel()).thenReturn(boardPanel);
        when(gameView.getCellSize()).thenReturn(75);

        gameController = new GameController(gameView, "Classic");
    }

    @Test
    public void testStartGame() {
        gameController.startGame();
        verify(gameView, times(1)).update();
    }

    @Test
    public void testPropagerLaser() {
        gameController.propagerLaser();
        verify(gameView, atLeastOnce()).updateLaserPath(anyList(), any(Couleur.class));
    }

    @Test
    public void testMouseClicked() {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getX()).thenReturn(150);
        when(event.getY()).thenReturn(150);
        when(event.getButton()).thenReturn(MouseEvent.BUTTON1);

        gameController.mouseClicked(event);
        verify(boardPanel, atLeastOnce()).repaint();
    }

    @Test
    public void testMousePressed() {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getX()).thenReturn(150);
        when(event.getY()).thenReturn(150);

        gameController.mousePressed(event);
        verify(board, atLeastOnce()).mettreAJourLesCheminsDesLasers();
        verify(board, atLeastOnce()).notifyObservers();
    }

    @Test
    public void testMouseReleased() {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getX()).thenReturn(150);
        when(event.getY()).thenReturn(150);
        when(event.getButton()).thenReturn(MouseEvent.BUTTON1);

        gameController.mouseReleased(event);
        verify(board, atLeastOnce()).mettreAJourLesCheminsDesLasers();
        verify(board, atLeastOnce()).notifyObservers();
    }

    @Test
    public void testMouseEntered() {
        MouseEvent event = mock(MouseEvent.class);
        gameController.mouseEntered(event);
        verify(boardPanel, atLeastOnce()).setCursor(any());
    }

    @Test
    public void testMouseExited() {
        MouseEvent event = mock(MouseEvent.class);
        gameController.mouseExited(event);
        verify(boardPanel, atLeastOnce()).setCursor(any());
    }

    @Test
    public void testBackToMenu() {
        gameController.backToMenu();
        verify(gameView.getMainFrame(), atLeastOnce()).getContentPane().removeAll();
        verify(gameView.getMainFrame(), atLeastOnce()).revalidate();
        verify(gameView.getMainFrame(), atLeastOnce()).repaint();
    }

    @Test
    public void testRestartGame() {
        gameController.restartGame("Classic");
        verify(board, atLeastOnce()).mettreAJourLesCheminsDesLasers();
        verify(board, atLeastOnce()).notifyObservers();
    }
}
