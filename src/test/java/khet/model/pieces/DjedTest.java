import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enums.Couleur;
import model.Board;
import model.Piece;
import model.pieces.Djed;

class DjedTest {
    @Test
    void testMove() {
        // Arrange
        Board board = new Board();
        Djed djed = new Djed(board, Couleur.JAUNE, 0, 0);
        //Piece otherPiece = new Piece(board, Couleur.ROUGE, 1, 1);
        //board.setPieceAt(1, 1, otherPiece);

        // Act
        djed.move(1, 1);
    }

    @Test
    void testMoveInvalid() {
        // Arrange
        Board board = new Board();
        Djed djed = new Djed(board, Couleur.JAUNE, 0, 0);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> djed.move(1, -1));
    }
}