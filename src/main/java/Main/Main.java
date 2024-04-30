package Main;

import javax.swing.SwingUtilities;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView gameView = new GameView(); // Crée et affiche l'interface graphique principale.
            gameView.setVisible(true);
        });
    }
}