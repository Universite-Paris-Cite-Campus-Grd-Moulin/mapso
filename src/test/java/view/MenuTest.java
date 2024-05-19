package view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import view.Menu;

public class MenuTest {

    private JFrame mainFrame;
    private Menu menuPanel;

    @BeforeEach
    public void setUp() {
        mainFrame = mock(JFrame.class);
        menuPanel = new Menu(mainFrame);
    }

    @Test
    public void testMenuInitialization() {
        // Vérifie que le panneau contient les trois boutons
        assertEquals(3, menuPanel.getComponentCount());

        // Vérifie que les boutons sont correctement initialisés
        JButton startButton = (JButton) menuPanel.getComponent(0);
        assertNotNull(startButton);

        JButton settingsButton = (JButton) menuPanel.getComponent(1);
        assertNotNull(settingsButton);

        JButton exitButton = (JButton) menuPanel.getComponent(2);
        assertNotNull(exitButton);
    }

    @Test
    public void testStartButtonAction() {
        JButton startButton = (JButton) menuPanel.getComponent(0);
        startButton.doClick();

        ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor.forClass(ActionListener.class);
        verify(startButton).addActionListener(actionListenerCaptor.capture());

        ActionListener actionListener = actionListenerCaptor.getValue();
        ActionEvent actionEvent = mock(ActionEvent.class);

        doAnswer(invocation -> {
            actionListener.actionPerformed(actionEvent);
            return null;
        }).when(actionEvent).getSource();

        actionListener.actionPerformed(actionEvent);

        verify(mainFrame, times(1)).getContentPane();
        verify(mainFrame, times(1)).revalidate();
        verify(mainFrame, times(1)).repaint();
    }

    @Test
    public void testSettingsButtonAction() {
        JButton settingsButton = (JButton) menuPanel.getComponent(1);
        settingsButton.doClick();

        ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor.forClass(ActionListener.class);
        verify(settingsButton).addActionListener(actionListenerCaptor.capture());

        ActionListener actionListener = actionListenerCaptor.getValue();
        ActionEvent actionEvent = mock(ActionEvent.class);

        doAnswer(invocation -> {
            actionListener.actionPerformed(actionEvent);
            return null;
        }).when(actionEvent).getSource();

        actionListener.actionPerformed(actionEvent);

        verify(mainFrame, times(1)).getContentPane();
        verify(mainFrame, times(1)).revalidate();
        verify(mainFrame, times(1)).repaint();
    }

    @Test
    public void testExitButtonAction() {
        JButton exitButton = (JButton) menuPanel.getComponent(2);
        exitButton.doClick();

        ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor.forClass(ActionListener.class);
        verify(exitButton).addActionListener(actionListenerCaptor.capture());

        ActionListener actionListener = actionListenerCaptor.getValue();
        ActionEvent actionEvent = mock(ActionEvent.class);

        doAnswer(invocation -> {
            actionListener.actionPerformed(actionEvent);
            return null;
        }).when(actionEvent).getSource();

        actionListener.actionPerformed(actionEvent);

        verify(mainFrame, times(0)).dispose(); // System.exit() is not callable in unit tests
    }
}
