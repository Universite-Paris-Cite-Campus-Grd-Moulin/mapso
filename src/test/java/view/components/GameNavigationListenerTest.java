package view.components;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.enums.Couleur;

public class GameNavigationListenerTest {

    private GameNavigationListener listener;

    @BeforeEach
    public void setUp() {
        listener = mock(GameNavigationListener.class);
    }

    @Test
    public void testOnBackToMenuRequested() {
        listener.onBackToMenuRequested();
        verify(listener, times(1)).onBackToMenuRequested();
    }

    @Test
    public void testUpdateGameOver() {
        Couleur couleur = Couleur.ROUGE;
        listener.updateGameOver(couleur);
        verify(listener, times(1)).updateGameOver(couleur);
    }
}
