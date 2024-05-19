package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class PionTest {

    private Pion pion;

    @BeforeEach
    public void setUp() {
        pion = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE, 0, 0);
    }

    @Test
    public void testPionCreation() {
        assertEquals(TypeDePion.PHARAON, pion.getType());
        assertEquals(Direction.NORD, pion.getDirection());
        assertEquals(Couleur.ROUGE, pion.getCouleur());
        assertEquals(0, pion.getX());
        assertEquals(0, pion.getY());
    }

    @Test
    public void testSetPosition() {
        pion.setPosition(5, 5);
        assertEquals(5, pion.getX());
        assertEquals(5, pion.getY());
    }

    @Test
    public void testRotationClockwise() {
        pion.rotate(true);
        assertEquals(Direction.EST, pion.getDirection());
        pion.rotate(true);
        assertEquals(Direction.SUD, pion.getDirection());
        pion.rotate(true);
        assertEquals(Direction.OUEST, pion.getDirection());
        pion.rotate(true);
        assertEquals(Direction.NORD, pion.getDirection());
    }

    @Test
    public void testRotationCounterClockwise() {
        pion.rotate(false);
        assertEquals(Direction.OUEST, pion.getDirection());
        pion.rotate(false);
        assertEquals(Direction.SUD, pion.getDirection());
        pion.rotate(false);
        assertEquals(Direction.EST, pion.getDirection());
        pion.rotate(false);
        assertEquals(Direction.NORD, pion.getDirection());
    }

    @Test
    public void testSetMarkedForSplitting() {
        pion.setMarkedForSplitting(true);
        assertTrue(pion.isMarkedForSplitting());
        pion.setMarkedForSplitting(false);
        assertFalse(pion.isMarkedForSplitting());
    }

    @Test
    public void testSetRotationRequested() {
        pion.setRotationRequested(true);
        assertTrue(pion.isRotationRequested());
        pion.setRotationRequested(false);
        assertFalse(pion.isRotationRequested());
    }

    @Test
    public void testSetEmpiled() {
        pion.setEmpiled(true);
        assertTrue(pion.estEmpile());
        pion.setEmpiled(false);
        assertFalse(pion.estEmpile());
    }

    @Test
    public void testPeutPivoter() {
        Pion pyramide = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.ROUGE);
        assertTrue(pyramide.peutPivoter());

        Pion djed = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.ROUGE);
        assertTrue(djed.peutPivoter());

        Pion horus = new Pion(TypeDePion.HORUS, Direction.NORD, Couleur.ROUGE);
        assertTrue(horus.peutPivoter());

        Pion obelisque = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
        assertFalse(obelisque.peutPivoter());
    }

    @Test
    public void testSetType() {
        pion.setType(TypeDePion.OBELISQUE);
        assertEquals(TypeDePion.OBELISQUE, pion.getType());
    }

    @Test
    public void testSetDirection() {
        pion.setDirection(Direction.EST);
        assertEquals(Direction.EST, pion.getDirection());
    }

    @Test
    public void testSetCouleur() {
        pion.setCouleur(Couleur.JAUNE);
        assertEquals(Couleur.JAUNE, pion.getCouleur());
    }
}
