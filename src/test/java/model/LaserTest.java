package model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.enums.Couleur;
import model.enums.Direction;

public class LaserTest {

    private Laser laser;
    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        plateau = Mockito.mock(Plateau.class);
    }

    @Test
    public void testLaserInitializationRed() {
        laser = new Laser(Couleur.ROUGE);
        assertEquals(Couleur.ROUGE, laser.getCouleur());
        assertEquals(new Point(0, 0), laser.getStartPosition());
        assertEquals(Direction.SUD, laser.getDirection());
    }

    @Test
    public void testLaserInitializationYellow() {
        laser = new Laser(Couleur.JAUNE);
        assertEquals(Couleur.JAUNE, laser.getCouleur());
        assertEquals(new Point(7, 9), laser.getStartPosition());
        assertEquals(Direction.NORD, laser.getDirection());
    }

    @Test
    public void testPropagerLaser() {
        laser = new Laser(Couleur.ROUGE);
        laser.propagerLaser(plateau);
        Mockito.verify(plateau).mettreAJourLesCheminsDesLasers();
    }

    @Test
    public void testObtenirCheminLaser() {
        laser = new Laser(Couleur.ROUGE);
        laser.propagerLaser(plateau);
        List<NoeudTrajectoire> chemin = laser.obtenirCheminLaser();
        assertNotNull(chemin);
        assertFalse(chemin.isEmpty());
    }

    @Test
    public void testReinitialiserChemin() {
        laser = new Laser(Couleur.ROUGE);
        laser.reinitialiserChemin();
        assertEquals(Direction.SUD, laser.getRacine().getDirection());
    }
}
