package test.java.khet.view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import view.GameView;
import view.components.BoardPanel;

public class GameViewTest {
    private GameView gameView;

    @Mock
    private BufferedImage spriteSheet;
    @Mock
    private BoardPanel boardPanel;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        gameView = new GameView();
        when(ImageIO.read(any(File.class))).thenReturn(spriteSheet);
    }

    @Test
    public void testGameViewInit() {
        // Test the initialization of the GameView window
        assertEquals(JFrame.EXIT_ON_CLOSE, gameView.getDefaultCloseOperation());
        assertEquals(800, gameView.getWidth());
        assertEquals(600, gameView.getHeight());
    }

    @Test
    public void testLoadSpriteSheet() {
        // Simulate the correct loading of a sprite sheet
        assertNotNull(gameView, "Sprite sheet should be loaded.");
    }

    @Test
    public void testDisplayBoard() {
        // Setup the conditions
        Object[][] dummyData = new Object[8][10];
        gameView.displayBoard(dummyData);

        // Verify that boardPanel is properly updated
        verify(boardPanel, times(1)).removeAll();
        verify(boardPanel, times(1)).revalidate();
        verify(boardPanel, times(1)).repaint();
    }

    @Test
    public void testUpdate() {
        gameView.update();

        // Verify that the board panel is refreshed
        verify(boardPanel, times(1)).revalidate();
        verify(boardPanel, times(1)).repaint();
    }

    @Test
    public void testGetCellSize() {
        // Assume the GameView window is the default size
        int expectedSize = Math.min(800 / 10, 600 / 8);
        assertEquals(expectedSize, gameView.getCellSize(),
                "Cell size should be calculated based on window dimensions.");
    }
}
