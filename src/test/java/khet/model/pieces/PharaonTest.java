import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enums.Couleur;
import enums.Direction;
import enums.TypeDePion;
import model.Board;
import model.pieces.Pharaon;

class PharaonTest {

    @Test
    void testPharaonConstructor() {
        // Arrange
        Board board = new Board();
        Couleur couleur = Couleur.JAUNE;
        int x = 0;
        int y = 0;
        boolean isAlive = true;

        // Act
        Pharaon pharaon = new Pharaon(board, couleur, x, y, isAlive);

        // Assert
        //assertEquals(board, pharaon.getBoard());
        assertEquals(couleur, pharaon.getCouleur());
        assertEquals(x, pharaon.getX());
        assertEquals(y, pharaon.getY());
        assertEquals(isAlive, pharaon.isAlive());
        assertEquals(Direction.NORD, pharaon.getDirection());
        assertEquals(TypeDePion.PHARAON, pharaon.getType());
    }

}