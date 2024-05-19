package model.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void testGetDi() {
        assertEquals(0, Direction.NONE.getDi());
        assertEquals(-1, Direction.NORD.getDi());
        assertEquals(1, Direction.SUD.getDi());
    }

    @Test
    public void testGetDj() {
        assertEquals(0, Direction.NONE.getDj());
        assertEquals(1, Direction.EST.getDj());
        assertEquals(-1, Direction.OUEST.getDj());
    }

    @Test
    public void testNextClockwise() {
        assertEquals(Direction.EST, Direction.NORD.nextClockwise());
        assertEquals(Direction.SUD, Direction.EST.nextClockwise());
        assertEquals(Direction.OUEST, Direction.SUD.nextClockwise());
        assertEquals(Direction.NORD, Direction.OUEST.nextClockwise());
    }

    @Test
    public void testNextCounterClockwise() {
        assertEquals(Direction.OUEST, Direction.NORD.nextCounterClockwise());
        assertEquals(Direction.NORD, Direction.EST.nextCounterClockwise());
        assertEquals(Direction.EST, Direction.SUD.nextCounterClockwise());
        assertEquals(Direction.SUD, Direction.OUEST.nextCounterClockwise());
    }

    @Test
    public void testRotate90Degrees() {
        assertEquals(Direction.EST, Direction.NORD.rotate90Degrees());
    }

    @Test
    public void testPerpendiculaire() {
        assertEquals(Direction.EST, Direction.NORD.perpendiculaire());
    }
}
