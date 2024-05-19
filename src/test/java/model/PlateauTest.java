package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class PlateauTest {

    private Plateau plateau;
    private final String classicBoardFile = "src/test/resources/initial_board_classic_test.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Créer un fichier de plateau de test
        try (FileWriter writer = new FileWriter(classicBoardFile)) {
            writer.write("0,5,PhR,N\n");
            writer.write("0,6,ObR,N\n");
            writer.write("1,2,PyR,E\n");
            writer.write("3,0,PyR,W\n");
            writer.write("3,4,HoR,E\n");
            writer.write("3,5,DjR,N\n");
            writer.write("3,7,PyR,N\n");
            writer.write("4,0,PyR,N\n");
            writer.write("4,2,PyJ,S\n");
            writer.write("4,4,DjJ,N\n");
            writer.write("4,5,HoJ,N\n");
            writer.write("4,7,PyR,W\n");
            writer.write("4,9,PyJ,E\n");
            writer.write("5,6,PyR,N\n");
            writer.write("6,7,PyJ,W\n");
            writer.write("7,2,PyJ,S\n");
            writer.write("7,3,ObJ,N\n");
            writer.write("7,4,PhJ,N\n");
            writer.write("7,5,ObJ,N\n");
            writer.write("9,9,PyJ,E\n");
        }
        plateau = new Plateau("Classic");
    }

    @Test
    public void testInitializeFromFile() {
        Pion pion = plateau.getPieceAt(0, 5);
        assertNotNull(pion);
        assertEquals(TypeDePion.PHARAON, pion.getType());
        assertEquals(Couleur.ROUGE, pion.getCouleur());
        assertEquals(Direction.NORD, pion.getDirection());

        pion = plateau.getPieceAt(4, 9);
        assertNotNull(pion);
        assertEquals(TypeDePion.PYRAMIDE, pion.getType());
        assertEquals(Couleur.JAUNE, pion.getCouleur());
        assertEquals(Direction.EST, pion.getDirection());
    }

    @Test
    public void testMovePiece() {
        Pion pion = plateau.getPieceAt(0, 5);
        assertTrue(plateau.movePiece(0, 5, 1, 5));
        assertNull(plateau.getPieceAt(0, 5));
        assertNotNull(plateau.getPieceAt(1, 5));
    }

    @Test
    public void testMovePieceInvalid() {
        assertFalse(plateau.movePiece(0, 5, 10, 10)); // Move to an invalid position
        assertFalse(plateau.movePiece(0, 5, 0, 5)); // Move to the same position
        assertFalse(plateau.movePiece(0, 5, 7, 4)); // Move too far away
    }

    @Test
    public void testNotifyObservers() {
        Observer observer = mock(Observer.class);
        plateau.addObserver(observer);
        plateau.notifyObservers();
        verify(observer, times(1)).update();
    }

    @Test
    public void testSetPharaonTouche() {
        Observer observer = mock(Observer.class);
        plateau.addObserver(observer);
        plateau.setPharaonTouche(Couleur.ROUGE);
        verify(observer, times(1)).updateGameOver(Couleur.ROUGE);
    }

    @Test
    public void testEmpilerObelisque() {
        plateau.empilerPion(plateau.getPieceAt(0, 6)); // Emplacement Obélisque
        Pion pion = plateau.getPieceAt(0, 6);
        assertEquals(TypeDePion.DOUBLE_OBELISQUE, pion.getType());
    }

    @Test
    public void testSeparDoubleObelisque() {
        Pion pion = plateau.getPieceAt(0, 6);
        plateau.empilerPion(pion);
        assertEquals(TypeDePion.DOUBLE_OBELISQUE, pion.getType());
        plateau.separerDoubleObelisque(pion);
        assertEquals(TypeDePion.OBELISQUE, pion.getType());
    }

    @Test
    public void testDepilerDoubleObelisque() {
        Pion pion = plateau.getPieceAt(0, 6);
        plateau.empilerPion(pion);
        assertEquals(TypeDePion.DOUBLE_OBELISQUE, pion.getType());
        assertTrue(plateau.depilerDoubleObelisque(pion, 0, 6));
    }
}
