import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enums.Couleur;
import model.Board;
import model.pieces.Obelisque;

class ObelisqueTest {
    @Test
    void testObelisqueConstructor() {
        // Arrange
        Board board = new Board();
        Couleur couleur = Couleur.BLANC;
        int x = 0;
        int y = 0;
        boolean isStacked = false;

        // Act
        Obelisque obelisque = new Obelisque(board, couleur, x, y, isStacked);

        // Assert
        assertEquals(board, obelisque.getBoard());
        assertEquals(couleur, obelisque.getCouleur());
        assertEquals(x, obellique.getX());
        assertEquals(y, obellique.getY());
        assertEquals(isStacked, obelisque.isStacked());
    }
}