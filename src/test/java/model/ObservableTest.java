package model;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObservableTest {

    private class TestObservable implements Observable {
        private List<Observer> observers = new ArrayList<>();

        @Override
        public void addObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    private TestObservable observable;
    private Observer observer;

    @BeforeEach
    public void setUp() {
        observable = new TestObservable();
        observer = mock(Observer.class);
    }

    @Test
    public void testAddObserver() {
        observable.addObserver(observer);
        observable.notifyObservers();
        verify(observer, times(1)).update();
    }

    @Test
    public void testRemoveObserver() {
        observable.addObserver(observer);
        observable.removeObserver(observer);
        observable.notifyObservers();
        verify(observer, never()).update();
    }

    @Test
    public void testNotifyObservers() {
        Observer observer1 = mock(Observer.class);
        Observer observer2 = mock(Observer.class);
        observable.addObserver(observer1);
        observable.addObserver(observer2);

        observable.notifyObservers();

        verify(observer1, times(1)).update();
        verify(observer2, times(1)).update();
    }
}
