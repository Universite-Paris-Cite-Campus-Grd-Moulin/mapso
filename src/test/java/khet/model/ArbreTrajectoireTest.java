package test.java.khet.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.ArbreTrajectoire;
import model.NoeudTrajectoire;

class ArbreTrajectoireTest {

    @Test
    void testGetRacine() {
        // Arrange
        NoeudTrajectoire racine = new NoeudTrajectoire(null, 0, 0, null);
        ArbreTrajectoire arbre = new ArbreTrajectoire(racine);

        // Act
        NoeudTrajectoire result = arbre.getRacine();

        // Assert
        assertEquals(racine, result);
    }

}