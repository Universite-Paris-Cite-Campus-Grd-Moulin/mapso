package khet;

import khet.controller.GameController;


// Point d'entrée principal pour le jeu Khet
public class Main {
    public static void main(String[] args) {
        // Créez une instance du contrôleur de jeu
        GameController gameController = new GameController();

        // Initialise et démarre le jeu
        gameController.initGame();

        // Si vous avez une interface graphique, vous pouvez l'initialiser et la rendre visible ici
        // Exemple : new GameView(gameController).setVisible(true);

        System.out.println("Le jeu Khet est démarré. Bon jeu !");
    }
}
