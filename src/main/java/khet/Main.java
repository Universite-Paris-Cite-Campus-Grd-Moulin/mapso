package khet;

import javax.swing.SwingUtilities;

import khet.view.GameView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView gameView = new GameView();
            gameView.setVisible(true);
        });
    }
}
