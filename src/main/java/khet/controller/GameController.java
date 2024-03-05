package khet.controller;

import khet.enums.Couleur;
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
            // Mettre à jour la vue graphique §§§§§§§§§§
        } else {
            // Gérez l'échec de l'action (par exemple, mouvement invalide).§§§§§§§§§
        }
    }

    public void rotatePiece(int x, int y, boolean clockwise) {
        Piece piece = board.getPieceAt(x, y);
        if (piece != null) {
            piece.rotate(clockwise);
            // Après une rotation réussie, tirer le laser.
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
        if (game.isGameOver()) {
            // Déterminez le joueur gagnant
            // Note : Cette implémentation suppose que vous avez une manière de déterminer le joueur actuel et le joueur adverse.
            String winningPlayer = (game.getCurrentPlayer() == Couleur.ROUGE) ? "Jaune" : "Rouge";

            // Affichez le résultat du jeu
            //ui.displayGameOver(winningPlayer);                                               Grapiquement
            System.out.println("Le jeu est terminé. Le joueur " + winningPlayer + " gagne !"); //Termmianl

            // Option pour recommencer ou quitter le jeu
            // int response = ui.promptForRestart();
            // if (response == RESTART) {
            //     restartGame();
            // } else {
            //     System.exit(0);
            // }
        }
    }

    public void restartGame() {
        // Réinitialisez le plateau et commencez un nouveau jeu
        this.board = new Board();
        this.game = new Game(board);
        startGame();
        // Assurez-vous également de réinitialiser l'affichage de l'interface utilisateur ici.
    }

}
