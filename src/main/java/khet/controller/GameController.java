package khet.controller;

import khet.model.Board;
import khet.model.Game;
import khet.model.Piece;

public class GameController {
    private Game game;
    private Board board;

    public GameController() {
        this.board = new Board();
        this.game = new Game(board);
    }

    public void startGame() {
        game.start();
    }

    public void handleUserAction(int startX, int startY, int endX, int endY) {
        boolean success = board.movePiece(startX, startY, endX, endY);
        if (success) {
            // Après un mouvement réussi, tirez le laser.
            shootLaser();
            // Mettez à jour la vue graphique §§§§§§§§§§
        } else {
            // Gérez l'échec de l'action (par exemple, mouvement invalide).§§§§§§§§§
        }
    }

    public void rotatePiece(int x, int y, boolean clockwise) {
        Piece piece = board.getPieceAt(x, y);
        if (piece != null) {
            piece.rotate(clockwise);
            // Après une rotation réussie, tirez le laser.
            shootLaser();
            // Mettez à jour la vue §§§§§§§§
        }
    }

    private void shootLaser() {
        // Assumons que la logique de tir du laser est implémentée dans Game ou Board.
        // Cette méthode simule le tir du laser à travers le plateau et gère les interactions.
        game.shootLaser();
        // Vérifiez les conditions de victoire après chaque tir de laser.
        checkWinConditions();
    }

    private void checkWinConditions() {
        // Implémentez la logique pour vérifier si le jeu est terminé (par exemple, si un Pharaon est touché).
        if (game.isGameOver()) {
            // Gérez la fin du jeu.
            // Par exemple, affichez le joueur gagnant et proposez de recommencer ou de quitter le jeu.
        }
    }
}
