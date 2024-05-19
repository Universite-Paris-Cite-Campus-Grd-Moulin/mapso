package model.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TypeInteractionTest {

    @Test
    public void testTypeInteractionValues() {
        TypeInteraction[] expected = { TypeInteraction.NONE, TypeInteraction.REFLEXION, TypeInteraction.DIVISION,
                TypeInteraction.ABSORPTION };
        TypeInteraction[] actual = TypeInteraction.values();
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testTypeInteractionValueOf() {
        assertEquals(TypeInteraction.NONE, TypeInteraction.valueOf("NONE"));
        assertEquals(TypeInteraction.REFLEXION, TypeInteraction.valueOf("REFLEXION"));
        assertEquals(TypeInteraction.DIVISION, TypeInteraction.valueOf("DIVISION"));
        assertEquals(TypeInteraction.ABSORPTION, TypeInteraction.valueOf("ABSORPTION"));
    }
}
