package controller;

import model.enums.Couleur;
import model.enums.Direction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.Pion;
import model.Plateau;

public class GameController implements MouseListener {
    private static final int Direction = 0;
    private Game game;
    private Plateau board;

    public GameController() {
        this.board = new Plateau();
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
        Pion piece = board.getPieceAt(Direction, y);
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
        this.board = new Plateau();
        this.game = new Game(board);
        startGame();
        // Assurez-vous également de réinitialiser l'affichage de l'interface utilisateur ici.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

}
