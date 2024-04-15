package test.java.khet.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class PlateauTest {

    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        plateau = new Plateau("Classic");
    }

    @Test
    public void testInitialisationClassic() {
        assertNotNull(plateau.getGrille());
        assertEquals(Couleur.ROUGE, plateau.getGrille()[0][0].getCouleur());
        assertEquals(TypeDePion.PHARAON, plateau.getGrille()[0][5].getType());
    }

    @Test
    public void testDeplacerPionValid() {
        Pion pion = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
        plateau.getGrille()[2][2] = pion;
        assertTrue(plateau.deplacerPion(2, 2, 3, 3));
        assertNull(plateau.getGrille()[2][2]);
        assertEquals(pion, plateau.getGrille()[3][3]);
    }

    @Test
    public void testDeplacerPionInvalid() {
        Pion pion = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
        plateau.getGrille()[2][2] = pion;
        assertFalse(plateau.deplacerPion(2, 2, 8, 8)); // Out of bounds
        assertEquals(pion, plateau.getGrille()[2][2]);
    }

    @Test
    public void testEmpilerPion() {
        Pion pion1 = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
        Pion pion2 = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
        plateau.getGrille()[2][2] = pion1;
        plateau.getGrille()[2][3] = pion2;
        plateau.empilerPion(pion1);
        assertNull(plateau.getGrille()[2][2]);
        assertEquals(TypeDePion.DOUBLE_OBELISQUE, plateau.getGrille()[2][3].getType());
    }

    @Test
    public void testShootLaserPlaceholder() {
        assertFalse(plateau.shootLaser(Couleur.ROUGE));
    }

    @Test
    public void testGetPieceAt() {
        Pion pion = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
        plateau.getGrille()[1][1] = pion;
        assertEquals(pion, plateau.getPieceAt(1, 1));
        assertNull(plateau.getPieceAt(8, 8)); // Test out of bounds
    }
}
