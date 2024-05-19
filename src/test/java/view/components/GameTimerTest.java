package view.components;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.components.GameTimer.TimerObserver;

public class GameTimerTest {

    private GameTimer gameTimer;
    private TimerObserver observer;

    @BeforeEach
    public void setUp() {
        gameTimer = new GameTimer(30); // Initialiser avec 30 secondes
        observer = mock(TimerObserver.class);
        gameTimer.addObserver(observer);
    }

    @Test
    public void testStart() {
        gameTimer.start();
        verify(observer, never()).onTimeUp(); // Assurez-vous que le temps n'est pas écoulé au démarrage
    }

    @Test
    public void testStop() {
        gameTimer.start();
        gameTimer.stop();
        verify(observer, never()).onTimeUp(); // Assurez-vous que le temps n'est pas écoulé après l'arrêt
    }

    @Test
    public void testReset() {
        gameTimer.reset(20); // Réinitialiser à 20 secondes
        verify(observer).onTimeChanged(20);
    }

    @Test
    public void testTimeChangedNotification() {
        gameTimer.start();
        try {
            Thread.sleep(2000); // Attendre 2 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(observer, atLeastOnce()).onTimeChanged(anyInt());
    }

    @Test
    public void testTimeUpNotification() {
        gameTimer = new GameTimer(2); // Initialiser avec 2 secondes pour un test rapide
        gameTimer.addObserver(observer);
        gameTimer.start();
        try {
            Thread.sleep(3000); // Attendre 3 secondes pour s'assurer que le temps est écoulé
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(observer, atLeastOnce()).onTimeUp();
    }

    @Test
    public void testAddObserver() {
        TimerObserver newObserver = mock(TimerObserver.class);
        gameTimer.addObserver(newObserver);
        gameTimer.reset(25);
        verify(newObserver).onTimeChanged(25);
    }

    @Test
    public void testRemoveObserver() {
        gameTimer.removeObserver(observer);
        gameTimer.reset(25);
        verify(observer, never()).onTimeChanged(25);
    }
}
