package khet;

import khet.controller.GameController;
import khet.view.GameView;

// Point d'entrée principal pour le jeu Khet
public class Main {
    public static void main(String[] args) {
        // Créez une instance du contrôleur de jeu
        GameController gameController = new GameController();

        // Initialise et démarre le jeu
        gameController.initGame();

        // Initialise et rend visible l'interface graphique
        new GameView(gameController).setVisible(true);

        System.out.println("Le jeu Khet est démarré. Bon jeu !");

    }
}
