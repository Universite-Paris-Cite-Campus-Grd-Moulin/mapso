package model;

import model.enums.Couleur;

public class Game {
    private Plateau board;
    private Couleur currentPlayer;
    private boolean isGameOver;

    // Modification du constructeur pour accepter un type de plateau
    public Game(String type) {
        this(new Plateau(type)); // Crée un plateau du type spécifié
    }

    public Game(Plateau board) {
        this.board = board;
        this.currentPlayer = Couleur.ROUGE;
        this.isGameOver = false;
    }

    public void start() {
        isGameOver = false;
        currentPlayer = Couleur.ROUGE;
        nextTurn();
    }

    public void nextTurn() {
        if (!isGameOver) {
            togglePlayer();
        }
    }

    public void shootLaser() {
        
    }
   
    private void togglePlayer() {
        currentPlayer = (currentPlayer == Couleur.ROUGE) ? Couleur.JAUNE : Couleur.ROUGE;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Couleur getCurrentPlayer() {
        return currentPlayer;
    }
}
