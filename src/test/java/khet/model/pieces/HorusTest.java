package model.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Couleur;
import enums.Direction;
import enums.TypeDePion;
import model.Board;
import model.pieces.Horus;

public class HorusTest {

    private Board board;
    private Horus horus;

    @BeforeEach
    public void init() {
        board = new Board();
        horus = new Horus(board, Couleur.JAUNE, 0, 0);
    }

    @Test
    public void testConstructor() {
        assertNotNull(horus);
        //assertEquals(board, horus.getBoard());
        assertEquals(Couleur.JAUNE, horus.getCouleur());
        assertEquals(Direction.NORD, horus.getDirection());
        assertEquals(TypeDePion.HORUS, horus.getType());
        assertEquals(0, horus.getX());
        assertEquals(0, horus.getY());
    }

}