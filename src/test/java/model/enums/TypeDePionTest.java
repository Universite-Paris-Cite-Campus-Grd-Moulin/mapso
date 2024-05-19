package model.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Pion;
import model.Plateau;

public class TypeDePionTest {

    private Plateau plateau;
    private Pion pion;

    @BeforeEach
    public void setUp() {
        plateau = Mockito.mock(Plateau.class);
        pion = Mockito.mock(Pion.class);
    }

    @Test
    public void testPharaonDeplacer() {
        TypeDePion.PHARAON.deplacer(Direction.NORD, plateau, pion);
        Mockito.verify(plateau).deplacerPion(pion, Direction.NORD);
    }

    @Test
    public void testPyramideDeplacer() {
        TypeDePion.PYRAMIDE.deplacer(Direction.EST, plateau, pion);
        Mockito.verify(plateau).tournerPion(pion, Direction.EST);
    }

    @Test
    public void testDjedDeplacer() {
        Mockito.when(plateau.estTourneeDemandee(pion)).thenReturn(true);
        TypeDePion.DJED.deplacer(Direction.SUD, plateau, pion);
        Mockito.verify(plateau).tournerPion(pion, Direction.SUD);

        Mockito.when(plateau.estTourneeDemandee(pion)).thenReturn(false);
        TypeDePion.DJED.deplacer(Direction.SUD, plateau, pion);
        Mockito.verify(plateau).deplacerPion(pion, Direction.SUD);
    }

    @Test
    public void testHorusDeplacer() {
        Mockito.when(plateau.estTourneeDemandee(pion)).thenReturn(true);
        TypeDePion.HORUS.deplacer(Direction.SUD, plateau, pion);
        Mockito.verify(plateau).tournerPion(pion, Direction.SUD);

        Mockito.when(plateau.estTourneeDemandee(pion)).thenReturn(false);
        TypeDePion.HORUS.deplacer(Direction.SUD, plateau, pion);
        Mockito.verify(plateau).deplacerPion(pion, Direction.SUD);
    }

    @Test
    public void testObelisqueDeplacer() {
        Mockito.when(plateau.estEmpile(pion)).thenReturn(false);
        TypeDePion.OBELISQUE.deplacer(Direction.SUD, plateau, pion);
        Mockito.verify(plateau).deplacerPion(pion, Direction.SUD);
    }

    @Test
    public void testDoubleObelisqueDeplacer() {
        Mockito.when(plateau.estSeparationDemandee(pion)).thenReturn(true);
        TypeDePion.DOUBLE_OBELISQUE.deplacer(Direction.SUD, plateau, pion);
        Mockito.verify(plateau).separerDoubleObelisque(pion);

        Mockito.when(plateau.estSeparationDemandee(pion)).thenReturn(false);
        TypeDePion.DOUBLE_OBELISQUE.deplacer(Direction.SUD, plateau, pion);
        Mockito.verify(plateau).deplacerPion(pion, Direction.SUD);
    }

    @Test
    public void testUnsupportedOperations() {
        assertThrows(UnsupportedOperationException.class,
                () -> TypeDePion.NONE.deplacer(Direction.NORD, plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.NONE.empiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.NONE.depiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.PHARAON.empiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.PHARAON.depiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.PYRAMIDE.empiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.PYRAMIDE.depiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.DJED.empiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.DJED.depiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.HORUS.empiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.HORUS.depiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.OBELISQUE.depiler(plateau, pion));
        assertThrows(UnsupportedOperationException.class, () -> TypeDePion.DOUBLE_OBELISQUE.empiler(plateau, pion));
    }
}
