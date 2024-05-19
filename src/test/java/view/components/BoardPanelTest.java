package view.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.TypeDePion;
import view.GameView;

public class BoardPanelTest {

    private BoardPanel boardPanel;
    private Game mockGame;
    private Plateau mockPlateau;
    private JFrame mockFrame;
    private GameView mockGameView;

    @BeforeEach
    public void setUp() {
        mockFrame = mock(JFrame.class);
        mockGameView = mock(GameView.class);
        mockPlateau = mock(Plateau.class);
        mockGame = mock(Game.class);

        when(mockGame.getCurrentPlayer()).thenReturn(Couleur.JAUNE);
        when(mockGame.getBoard()).thenReturn(mockPlateau);

        boardPanel = new BoardPanel("Classic", mockFrame, mockGameView);
    }

    @Test
    public void testInitialization() {
        assertNotNull(boardPanel);
        assertNotNull(boardPanel.getBoard());
        assertNotNull(boardPanel.getGame());
    }

    @Test
    public void testGetCurrentColor() {
        when(mockGame.getCurrentPlayer()).thenReturn(Couleur.BLEU);
        assertEquals(Couleur.BLEU, boardPanel.getCurrentColor());
    }

    @Test
    public void testPaintComponent() {
        Graphics g = mock(Graphics.class);
        boardPanel.paintComponent(g);

        verify(mockGameView).updateTourLabel();
    }

    @Test
    public void testDrawBoard() {
        Graphics g = mock(Graphics.class);

        verify(mockPlateau, times(80)).initCouleur(anyInt(), anyInt());
    }

    @Test
    public void testMouseClicked() {
        MouseEvent event = new MouseEvent(boardPanel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 100, 100,
                1, false, MouseEvent.BUTTON1);
        boardPanel.mouseClicked(event);

        verify(mockPlateau, times(1)).getPieceAt2(anyInt(), anyInt());
    }

    @Test
    public void testHandleLeftClick() {
        Pion mockPion = mock(Pion.class);
        when(mockPion.getType()).thenReturn(TypeDePion.OBELISQUE);
        when(mockPion.getCouleur()).thenReturn(Couleur.JAUNE);
        when(mockGame.isPlayerTurn(Couleur.JAUNE)).thenReturn(true);

        verify(mockGame).stackOrUnstackObelisk(mockPion, 1, 1);
    }

    @Test
    public void testHandleRightClick() {
        Pion mockPion = mock(Pion.class);
        when(mockPion.getCouleur()).thenReturn(Couleur.JAUNE);

        verify(mockPion).rotate(true);
        verify(mockPlateau).mettreAJourLesCheminsDesLasers();
        verify(mockPlateau).notifyObservers();
    }

    @Test
    public void testMousePressed() {
        MouseEvent event = new MouseEvent(boardPanel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 100, 100,
                1, false, MouseEvent.BUTTON1);
        boardPanel.mousePressed(event);

        verify(mockPlateau, times(1)).getPieceAt(anyInt(), anyInt());
    }

    @Test
    public void testMouseReleased() {
        MouseEvent event = new MouseEvent(boardPanel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 100,
                100, 1, false, MouseEvent.BUTTON1);
        boardPanel.mouseReleased(event);

        verify(mockPlateau, times(1)).movePiece(anyInt(), anyInt(), anyInt(), anyInt());
        verify(mockPlateau).mettreAJourLesCheminsDesLasers();
        verify(mockPlateau).notifyObservers();
    }

    @Test
    public void testUpdate() {
        boardPanel.update();

        verify(mockPlateau, times(1)).notifyObservers();
    }
}
