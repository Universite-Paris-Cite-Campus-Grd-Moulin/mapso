package test.java.khet.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Pion;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class PionTest {
    private Pion pion;

    @BeforeEach
    public void setUp() {
        pion = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(TypeDePion.PHARAON, pion.getType());
        assertEquals(Direction.NORD, pion.getDirection());
        assertEquals(Couleur.ROUGE, pion.getCouleur());
    }

    @Test
    public void testSetters() {
        pion.setType(TypeDePion.OBELISQUE);
        pion.setDirection(Direction.SUD);
        pion.setCouleur(Couleur.JAUNE);
        pion.setPosition(2, 3);
        pion.setMarkedForSplitting(true);
        pion.setRotationRequested(true);
        pion.setEmpiled(true);

        assertEquals(TypeDePion.OBELISQUE, pion.getType());
        assertEquals(Direction.SUD, pion.getDirection());
        assertEquals(Couleur.JAUNE, pion.getCouleur());
        assertEquals(2, pion.getX());
        assertEquals(3, pion.getY());
        assertTrue(pion.isMarkedForSplitting());
        assertTrue(pion.isRotationRequested());
        assertTrue(pion.estEmpile());
    }

    @Test
    public void testRotate() {
        pion.setDirection(Direction.EST);
        pion.rotate(true); // Rotation horaire
        assertEquals(Direction.SUD, pion.getDirection());

        pion.rotate(false); // Rotation anti-horaire
        assertEquals(Direction.EST, pion.getDirection());
    }

    @Test
    public void testPeutPivoter() {
        // DJED and PYRAMIDE can rotate, PHARAON cannot
        pion.setType(TypeDePion.DJED);
        assertTrue(pion.peutPivoter());

        pion.setType(TypeDePion.PHARAON);
        assertFalse(pion.peutPivoter());

        pion.setType(TypeDePion.PYRAMIDE);
        assertTrue(pion.peutPivoter());
    }
}
