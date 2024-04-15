package test.java.khet.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.NoeudTrajectoire;
import model.enums.Direction;
import model.enums.TypeInteraction;

public class NoeudTrajectoireTest {
    private NoeudTrajectoire noeudTrajectoire;

    @BeforeEach
    public void setUp() {
        noeudTrajectoire = new NoeudTrajectoire(Direction.NORD, 5, 5, TypeInteraction.NONE);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(Direction.NORD, noeudTrajectoire.getDirection());
        assertEquals(5, noeudTrajectoire.getPositionI());
        assertEquals(5, noeudTrajectoire.getPositionJ());
        assertEquals(TypeInteraction.NONE, noeudTrajectoire.getTypeInteraction());
    }

    @Test
    public void testAjouterSuccesseur() {
        NoeudTrajectoire successeur = new NoeudTrajectoire(Direction.SUD, 6, 5, TypeInteraction.DIVISION);
        noeudTrajectoire.ajouterSuccesseur(successeur);

        assertTrue(noeudTrajectoire.getSuccesseurs().contains(successeur));
        assertEquals(1, noeudTrajectoire.getSuccesseurs().size());
    }
}
