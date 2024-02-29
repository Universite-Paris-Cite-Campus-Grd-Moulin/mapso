package khet.controller;

import khet.model.Game;

public class GameController {
    private Game game;

    public GameController() {
        this.game = new Game();
    }

    public void startGame() {
        // Initialiser et démarrer le jeu
    }

    public void movePiece(int fromX, int fromY, int toX, int toY) {
        // Déplacer une pièce sur le plateau
    }

    public void rotatePiece(int x, int y, boolean clockwise) {
        // Faire pivoter une pièce sur le plateau
    }
}
