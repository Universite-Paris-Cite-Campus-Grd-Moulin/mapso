package view.components;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameTimer {
    private Timer timer;
    private int timeLeft;
    private List<TimerObserver> observers = new ArrayList<>();

    public GameTimer(int initialTime) {
        this.timeLeft = initialTime;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    notifyTimeChanged();
                } else {
                    notifyTimeUp();
                }
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset(int newTime) {
        timeLeft = newTime;
        notifyTimeChanged();
    }

    public void addObserver(TimerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TimerObserver observer) {
        observers.remove(observer);
    }

    private void notifyTimeChanged() {
        for (TimerObserver observer : observers) {
            observer.onTimeChanged(timeLeft);
        }
    }

    private void notifyTimeUp() {
        for (TimerObserver observer : observers) {
            observer.onTimeUp();
        }
    }

    public interface TimerObserver {
        void onTimeChanged(int timeLeft);
        void onTimeUp();
    }
}
