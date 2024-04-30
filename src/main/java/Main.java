import javax.swing.SwingUtilities;

import controller.GameController;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView gameView = new GameView(); // Créez d'abord la vue
            GameController controller = new GameController(gameView); // Passez la vue au contrôleur
            gameView.setController(controller); // Assurez-vous que la vue a une référence au contrôleur
            gameView.setVisible(true); // Rendez la vue visible
        });
    }
}
