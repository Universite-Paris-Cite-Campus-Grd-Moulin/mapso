package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import view.components.BoardPanel;
import view.components.GameOverBoard;

public class GameViewTest {

    private JFrame mainFrame;
    private GameView gameView;
    private BoardPanel boardPanel;

    @BeforeEach
    public void setUp() {
        mainFrame = mock(JFrame.class);
        gameView = new GameView(mainFrame, "Classic");
        boardPanel = gameView.getBoardPanel();
    }

    @Test
    public void testGameViewInitialization() {
        // Vérifie que le panneau de jeu est initialisé correctement
        assertNotNull(gameView.getBoardPanel());

    }

    @Test
    public void testPauseButtonAction() {
        JPanel otherPanel = (JPanel) gameView.getComponent(1);
        JButton pauseButton = (JButton) ((JPanel) otherPanel.getComponent(2)).getComponent(0);
        pauseButton.doClick();

        ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor.forClass(ActionListener.class);
        verify(pauseButton).addActionListener(actionListenerCaptor.capture());

        ActionListener actionListener = actionListenerCaptor.getValue();
        ActionEvent actionEvent = mock(ActionEvent.class);

        doAnswer(invocation -> {
            actionListener.actionPerformed(actionEvent);
            return null;
        }).when(actionEvent).getSource();

        actionListener.actionPerformed(actionEvent);

    }

    @Test
    public void testShowGameOver() {
        gameView.showGameOver(mock(model.enums.Couleur.class));
        // Vérifie que le panneau de fin de jeu est affiché
        verify(mainFrame).getContentPane();
        verify(mainFrame).setContentPane(any(GameOverBoard.class));
        verify(mainFrame).revalidate();
        verify(mainFrame).repaint();
    }

    @Test
    public void testSwitchPlayer() {
        gameView.switchPlayer();

        // Vérifie que le tour du joueur est mis à jour
        verify(boardPanel.getGame()).switchPlayer();
    }
}
