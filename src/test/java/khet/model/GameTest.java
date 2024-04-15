package test.java.khet.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.Game;
import model.Plateau;
import model.enums.Couleur;

public class GameTest {
    @Mock
    private Plateau board;

    @InjectMocks
    private Game game;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        // Préparer un Plateau avec un type spécifique, ici pour les tests nous pouvons
        // simplement l'ignorer ou mocker
        this.game = new Game("Classic");
    }

    @Test
    public void testStartGame() {
        game.start();
        assertFalse(game.isGameOver());
        assertEquals(Couleur.ROUGE, game.getCurrentPlayer());
        verify(board, never()).shootLaser(any()); // Assurez-vous qu'aucun laser n'est tiré lors du démarrage du jeu.
    }

    @Test
    public void testNextTurnWithoutEndingGame() {
        when(board.shootLaser(any())).thenReturn(false); // Simuler qu'aucun coup n'est fatal
        game.start(); // Réinitialise le jeu
        game.nextTurn();
        verify(board).shootLaser(Couleur.ROUGE);
        assertEquals(Couleur.JAUNE, game.getCurrentPlayer()); // S'assurer que le joueur a changé
        assertFalse(game.isGameOver());
    }

    @Test
    public void testNextTurnEndsGame() {
        when(board.shootLaser(any())).thenReturn(true); // Simuler un coup fatal
        game.start();
        game.nextTurn();
        assertTrue(game.isGameOver());
        verify(board).shootLaser(Couleur.ROUGE);
    }

}
