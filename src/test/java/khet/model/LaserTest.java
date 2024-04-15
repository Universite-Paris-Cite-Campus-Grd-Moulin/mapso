package test.java.khet.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ArbreTrajectoire;
import model.Laser;
import model.NoeudTrajectoire;
import model.enums.Direction;
import model.enums.TypeInteraction;

public class LaserTest {
    private Laser laser;

    @BeforeEach
    public void setUp() {
        // Initialisation du laser avec une direction et une position initiales
        laser = new Laser(Direction.NORD, 5, 5);
    }

    @Test
    public void testInitialTrajectoire() {
        // Vérification que l'arbre de trajectoire est correctement initialisé
        ArbreTrajectoire arbre = laser.getArbreTrajectoire();
        NoeudTrajectoire racine = arbre.getRacine();

        assertEquals(Direction.NORD, racine.getDirection());
        assertEquals(5, racine.getPositionI());
        assertEquals(5, racine.getPositionJ());
        assertEquals(TypeInteraction.NONE, racine.getTypeInteraction());
        assertTrue(racine.getSuccesseurs().isEmpty());
    }

    @Test
    public void testMettreAJourTrajectoire() {
        // Test de mise à jour de la trajectoire
        laser.mettreAJourTrajectoire(Direction.EST, 6, 5, TypeInteraction.ABSORPTION);
        NoeudTrajectoire racine = laser.getArbreTrajectoire().getRacine();
        NoeudTrajectoire successeur = racine.getSuccesseurs().get(0);

        assertEquals(Direction.EST, successeur.getDirection());
        assertEquals(6, successeur.getPositionI());
        assertEquals(5, successeur.getPositionJ());
    }
}
