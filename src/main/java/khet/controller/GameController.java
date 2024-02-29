package khet.controller;

import khet.model.Board;
import khet.model.Game;

public class GameController {
    private Game game;
    private Board board;

    public GameController() {
        this.board = new Board();
        this.game = new Game(board);
    }

    public void startGame() {
        // Commence le jeu
        game.start();
    }

    public void handleUserAction(int startX, int startY, int endX, int endY) {
        // Traite une action de l'utilisateur, comme déplacer une pièce
        boolean success = board.movePiece(startX, startY, endX, endY);
        if (success) {
            // Mettez à jour la vue si nécessaire
        } else {
            // Gérez l'échec de l'action (par exemple, mouvement invalide)
        }
    }
}
