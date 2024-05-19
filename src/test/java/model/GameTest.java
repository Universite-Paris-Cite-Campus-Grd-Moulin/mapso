package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.enums.Couleur;
import model.enums.Direction;

public class GameTest {

    private Plateau plateau;
    private Game game;

    @BeforeEach
    public void setUp() {
        plateau = Mockito.mock(Plateau.class);
        game = new Game(plateau);
    }

    @Test
    public void testInitialPlayer() {
        assertEquals(Couleur.JAUNE, game.getCurrentPlayer());
    }

    @Test
    public void testSwitchPlayer() {
        game.switchPlayer();
        assertEquals(Couleur.ROUGE, game.getCurrentPlayer());
    }

    @Test
    public void testMovePiece() {
        Mockito.when(plateau.movePiece(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(true);
        assertTrue(game.movePiece(0, 0, 1, 1));
        Mockito.verify(plateau).movePiece(0, 0, 1, 1);
    }

    @Test
    public void testRotatePiece() {
        Pion pion = Mockito.mock(Pion.class);
        Mockito.when(pion.getDirection()).thenReturn(Direction.NORD);
        game.rotatePiece(pion);
        Mockito.verify(plateau).tournerPion(pion, Direction.EST);
    }

    @Test
    public void testSelectPiece() {
        Pion pion = new Pion(model.enums.TypeDePion.PHARAON, Direction.NORD, Couleur.JAUNE, 0, 0);
        Mockito.when(plateau.getPieceAt(0, 0)).thenReturn(pion);
        assertTrue(game.selectPiece(0, 0));
    }

    @Test
    public void testIsPlayerTurn() {
        assertTrue(game.isPlayerTurn(Couleur.JAUNE));
        assertFalse(game.isPlayerTurn(Couleur.ROUGE));
    }

    @Test
    public void testIsGameOver() {
        game.shootLaser();
        assertTrue(game.isGameOver());
    }
}
