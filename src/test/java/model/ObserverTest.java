package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.enums.Couleur;

public class ObserverTest {

    private class TestObserver implements Observer {
        private boolean updated = false;
        private Couleur gameOverColor = null;

        @Override
        public void update() {
            updated = true;
        }

        @Override
        public void updateGameOver(Couleur couleur) {
            gameOverColor = couleur;
        }

        public boolean isUpdated() {
            return updated;
        }

        public Couleur getGameOverColor() {
            return gameOverColor;
        }
    }

    @Test
    public void testUpdate() {
        TestObserver observer = new TestObserver();
        observer.update();
        assertTrue(observer.isUpdated(), "Observer should be updated");
    }

    @Test
    public void testUpdateGameOver() {
        TestObserver observer = new TestObserver();
        Couleur couleur = Couleur.ROUGE;
        observer.updateGameOver(couleur);
        assertEquals(couleur, observer.getGameOverColor(), "Observer should receive game over color");
    }
}
