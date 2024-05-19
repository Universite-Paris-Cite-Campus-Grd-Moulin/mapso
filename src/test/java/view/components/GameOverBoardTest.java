package view.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.GameView;

public class GameOverBoardTest {

    private GameOverBoard gameOverBoard;
    private GameView gameView;
    private JFrame mainFrame;

    @BeforeEach
    public void setUp() {
        mainFrame = mock(JFrame.class);
        gameView = mock(GameView.class);
        when(gameView.getMainFrame()).thenReturn(mainFrame);
        gameOverBoard = new GameOverBoard(gameView);
    }

    @Test
    public void testInitialization() {
        assertNotNull(gameOverBoard);
    }

    @Test
    public void testButtonActions() {
        JButton btnRestart = mock(JButton.class);
        JButton btnBackToMenu = mock(JButton.class);
        JButton btnQuit = mock(JButton.class);

        gameOverBoard.add(btnRestart);
        gameOverBoard.add(btnBackToMenu);
        gameOverBoard.add(btnQuit);

        btnRestart.doClick();
        btnBackToMenu.doClick();
        btnQuit.doClick();

        verify(btnRestart, atLeastOnce()).doClick();
        verify(btnBackToMenu, atLeastOnce()).doClick();
        verify(btnQuit, atLeastOnce()).doClick();
    }
}
