package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;
import model.enums.TypeInteraction;

public class NoeudTrajectoireTest {

    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        plateau = Mockito.mock(Plateau.class);
    }

    @Test
    public void testAvancerLaser() {
        NoeudTrajectoire noeud = new NoeudTrajectoire(Direction.NORD, 0, 0, TypeInteraction.NONE);
        Pion pion = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.ROUGE, 0, 0);

        Mockito.when(plateau.estDansLimites(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);
        Mockito.when(plateau.getPieceAt2(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pion);

        noeud.avancerLaser(plateau);

        assertFalse(noeud.getSuccesseurs().isEmpty());
        assertEquals(Direction.OUEST, noeud.getSuccesseurs().get(0).getDirection());
        assertEquals(TypeInteraction.REFLEXION, noeud.getSuccesseurs().get(0).getTypeInteraction());
    }

    @Test
    public void testAjouterSuccesseur() {
        NoeudTrajectoire noeud = new NoeudTrajectoire(Direction.NORD, 0, 0, TypeInteraction.NONE);

        Mockito.when(plateau.estDansLimites(Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        noeud.ajouterSuccesseur(plateau, Direction.EST, TypeInteraction.NONE);

        assertFalse(noeud.getSuccesseurs().isEmpty());
        assertEquals(Direction.EST, noeud.getSuccesseurs().get(0).getDirection());
    }
}
