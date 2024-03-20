import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enums.Direction;
import model.LaserTrajectory;

public class LaserTrajectoryTest {

    @Test
    public void constructorTest() {
        Direction direction = Direction.NORD;
        int startX = 10;
        int startY = 20;
        LaserTrajectory laserTrajectory = new LaserTrajectory(direction, startX, startY);

        assertEquals(direction, laserTrajectory.getDirection());
        assertEquals(startX, laserTrajectory.getStartX());
        assertEquals(startY, laserTrajectory.getStartY());
    }

    @Test
    public void getSetMethodsTest() {
        Direction direction = Direction.NORD;
        int startX = 10;
        int startY = 20;
        LaserTrajectory laserTrajectory = new LaserTrajectory(direction, startX, startY);

        assertEquals(direction, laserTrajectory.getDirection());
        laserTrajectory.setDirection(Direction.SUD);
        assertEquals(Direction.SUD, laserTrajectory.getDirection());

        assertEquals(startX, laserTrajectory.getStartX());
        laserTrajectory.setStartX(30);
        assertEquals(30, laserTrajectory.getStartX());

        assertEquals(startY, laserTrajectory.getStartY());
        laserTrajectory.setStartY(40);
        assertEquals(40, laserTrajectory.getStartY());
    }
}