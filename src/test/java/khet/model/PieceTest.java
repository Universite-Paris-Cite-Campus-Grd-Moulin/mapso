import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enums.Couleur;
import enums.Direction;
import enums.TypeDePion;
import model.Board;
import model.Piece;

class PieceTest {
    @Test
    void testPieceConstructor() {
        // Arrange
        //Board board = new Board(10, 10);
        Couleur couleur = Couleur.JAUNE;
        Direction direction = Direction.NORD;
        TypeDePion type = TypeDePion.DJED;
        int x = 5;
        int y = 5;

        // Act
        //Piece piece = new Piece(board, couleur, direction, type, x, y);

    }
}