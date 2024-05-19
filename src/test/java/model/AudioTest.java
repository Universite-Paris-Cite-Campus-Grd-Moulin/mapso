package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AudioTest {

    @Test
    public void testPlaySound() {
        assertDoesNotThrow(() -> Audio.playSound("src/main/resources/audio/click.wav"));
    }

    @Test
    public void testStopSound() {
        assertDoesNotThrow(() -> Audio.stopSound());
    }
}
