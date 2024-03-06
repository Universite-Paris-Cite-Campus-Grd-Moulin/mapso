package model.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Couleur;
import enums.Direction;
import enums.TypeDePion;
import model.Board;
import model.pieces.Pyramide;

public class PyramideTest {

    private Board board;
    private Pyramide pyramide;

    @BeforeEach
    public void init() {
        board = new Board();
        pyramide = new Pyramide(board, Couleur.BLANC, Direction.NORD, 0, 0);
    }

    @Test
    public void testConstructor() {
        assertEquals(board, pyramide.getBoard());
        assertEquals(Couleur.BLANC, pyramide.getCouleur());
        assertEquals(Direction.NORD, pyramide.getDirection());
        assertEquals(TypeDePion.PYRAMIDE, pyramide.getType());
        assertEquals(0, pyramide.getX());
        assertEquals(0, pyramide.getY());
    }

}