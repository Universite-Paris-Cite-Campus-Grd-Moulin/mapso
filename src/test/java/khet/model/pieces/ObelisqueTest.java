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
        Couleur couleur = Couleur.JAUNE;
        int x = 0;
        int y = 0;
        boolean isStacked = false;

        // Act
        Obelisque obelisque = new Obelisque(board, couleur, x, y, isStacked);
    }
}