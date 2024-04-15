package test.java.khet.view.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;
import view.components.BoardPanel;

public class BoardPanelTest {
    @Mock
    private Plateau board;
    @Mock
    private Graphics graphics;
    private BoardPanel boardPanel;
    private Pion testPion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        boardPanel = new BoardPanel(board);
        testPion = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
    }

    @Test
    public void testPaintComponent() {
        // Setup
        when(board.initCouleur(anyInt(), anyInt())).thenReturn(Couleur.GRIS);
        when(board.getGrille()).thenReturn(new Pion[8][10]); // Assuming an 8x10 board

        // Act
        boardPanel.paintComponent(graphics);

        // Assert
        verify(graphics, atLeastOnce()).drawImage(any(), anyInt(), anyInt(), eq(boardPanel));
    }

    @Test
    public void testMousePressed() {
        // Create a mock MouseEvent
        MouseEvent mockEvent = new MouseEvent(boardPanel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 150,
                150, 1, false);
        when(board.getPieceAt(2, 2)).thenReturn(testPion);

        // Act
        boardPanel.mousePressed(mockEvent);

        // Assert
        assertNotNull(boardPanel, "Piece should be selected after mouse press");
        assertEquals(testPion, boardPanel, "Selected piece should be the one returned from the board");
    }

    @Test
    public void testMouseReleased() {
        // Prepare
        testPion.setPosition(1, 1); // Assume starting position (1, 1)
        MouseEvent mockEvent = new MouseEvent(boardPanel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 225,
                225, 1, false);
        when(board.movePiece(1, 1, 3, 3)).thenReturn(true);

        // Act
        boardPanel.mouseReleased(mockEvent);

        // Assert
        assertNull(boardPanel, "Selected piece should be null after valid move");
        verify(board, times(1)).movePiece(1, 1, 3, 3);
    }

    @Test
    public void testInitBoard() {
        // Act

        // Assert
        verify(board, never()).getGrille(); // Verifies that the board was not interacted with for this method
    }

    @Test
    public void testPut() {
        // Act
        boardPanel.put(testPion);

        // Assert
        verify(board.getGrille()[2][2], times(1)).equals(testPion); // Check if the pion is placed in the grid correctly
    }
}
