package model.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CouleurTest {

    @Test
    public void testCouleurValues() {
        Couleur[] expected = { Couleur.GRIS, Couleur.ROUGE, Couleur.JAUNE, Couleur.BLEU, Couleur.ORANGE };
        Couleur[] actual = Couleur.values();
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testCouleurValueOf() {
        assertEquals(Couleur.GRIS, Couleur.valueOf("GRIS"));
        assertEquals(Couleur.ROUGE, Couleur.valueOf("ROUGE"));
        assertEquals(Couleur.JAUNE, Couleur.valueOf("JAUNE"));
        assertEquals(Couleur.BLEU, Couleur.valueOf("BLEU"));
        assertEquals(Couleur.ORANGE, Couleur.valueOf("ORANGE"));
    }
}
