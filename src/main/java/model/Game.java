package model;

import model.enums.Couleur;

public class Game {
    private Plateau board;
    private Couleur currentPlayer;
    private boolean isGameOver;
    
    // Constructeur par défaut
    public Game() {
        this.board = new Plateau();
        this.currentPlayer = Couleur.JAUNE; // Jaune commence
        this.isGameOver = false;
        initializeGame();
    }

    // Initialise le jeu avec les pièces dans leurs positions de départ
    private void initializeGame() {
        
    }

    // Commence le jeu
    public void start() {
        while (!isGameOver) {
            playTurn();
        }
        // Annoncez le vainqueur ou le match nul ici
    }

    // Joue un tour
    private void playTurn() {
        // Implémentation à faire - gérer les actions du joueur actuel et tirer le laser
        changeTurn();
    }

    // Change au joueur suivant
    private void changeTurn() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
    }

    // Vérifie si le jeu est terminé
    private void checkWinConditions() {
        // Implémentation à faire - vérifier les conditions de victoire
    }

    // Retourne si le jeu est terminé
    public boolean isGameOver() {
        return isGameOver;
    }

    // Retourne le joueur actuel
    public Couleur getCurrentPlayer() {
        return currentPlayer;
    }

    // Méthode principale pour tester le jeu
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
