package khet.model;

import khet.enums.Couleur;

public class Game {
    private Board board;
    private Couleur currentPlayer;
    private boolean isGameOver;

    public Game() {
        this.board = new Board();
        this.currentPlayer = Couleur.ROUGE; // Rouge commence
        this.isGameOver = false;
    }

    public Game(Board board) {
        this.board = board;
        this.currentPlayer = Couleur.ROUGE; // Ou autre logique pour déterminer le premier joueur
        this.isGameOver = false;
    }

    public void start() {
        while (!isGameOver) {
            // Implémentez la logique d'un tour de jeu ici
            // Par exemple, demander au joueur courant de choisir un mouvement, exécuter le mouvement, vérifier les conditions de victoire, etc.

            // Passer au joueur suivant
            currentPlayer = (currentPlayer == Couleur.ROUGE) ? Couleur.JAUNE : Couleur.ROUGE;
        }
    }

    private void checkWinConditions() {
        // Vérifiez ici les conditions de victoire, par exemple, si le Pharaon d'un joueur a été capturé
        // Si une condition de victoire est remplie, définissez isGameOver à true
    }
}
