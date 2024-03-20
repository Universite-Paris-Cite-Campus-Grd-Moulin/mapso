import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Direction;
import model.Board;
import model.Laser;

class LaserTest {

    private Laser laser;
    private Board board;

    @BeforeEach
    void init() {
        //board = new Board(10, 10);
        laser = new Laser(board);
    }

    @Test
    void testShootLaser() {
        // Arrange
        int startX = 0;
        int startY = 0;
        Direction direction = Direction.EST;

        // Act
        laser.shootLaser(startX, startY, direction);

        // Assert
        // Verify that the laser is shot in the correct direction
        //assertEquals(direction, board.getLaser().getDirection());
    }

}