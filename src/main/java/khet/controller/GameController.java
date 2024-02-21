package khet.controller;

import khet.model.Board;
import khet.model.Game;

// Gère les interactions entre la vue et le modèle
public class GameController {
    private Game game;
    private Board board;

    public GameController() {
        this.game = new Game();
        this.board = game.getBoard();
    }

    // Initialise le jeu
    public void initGame() {
        // Implémentez la logique d'initialisation du jeu ici
    }

    // Gère les actions des joueurs, comme les mouvements des pièces
    public void handlePlayerAction(int x, int y) {
        // Implémentez la logique de traitement des actions du joueur ici
    }
}
