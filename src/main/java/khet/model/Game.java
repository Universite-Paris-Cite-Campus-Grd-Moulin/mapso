package model;

import enums.Couleur;

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
        this.currentPlayer = Couleur.ROUGE; //Déterminer le premier joueur
        this.isGameOver = false;
    }

    public void start() {
        while (!isGameOver) {
            // Implémentez la logique d'un tour de jeu ici
            // Passer au joueur suivant
            currentPlayer = (currentPlayer == Couleur.ROUGE) ? Couleur.JAUNE : Couleur.ROUGE;
            
            // À la fin de chaque tour, tirez le laser
            shootLaser();
            
            // Vérifiez si le jeu est terminé
            checkWinConditions();
        }
    }

    // Simule le tir du laser et vérifie les interactions avec les pièces
    public void shootLaser() {
        // Implémentation simplifiée.
        boolean hit = board.shootLaser(currentPlayer);
        if (hit) {
            // Si le Pharaon est touché, le jeu est terminé.
            isGameOver = true;
        }
    }

    // Vérifie si le jeu est terminé
    public boolean isGameOver() {
        return isGameOver;
    }

    private void checkWinConditions() {
        // Assumez que shootLaser retourne déjà un booléen indiquant si le Pharaon est touché
        boolean pharaohHit = board.shootLaser(currentPlayer);
        if (pharaohHit) {
            isGameOver = true;
        }
    }

    // Méthode pour obtenir le joueur actuel
    public Couleur getCurrentPlayer() {
        return this.currentPlayer;
    }
}
