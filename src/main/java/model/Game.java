package model;

import model.enums.Couleur;

public class Game {
    private Plateau board;
    private Couleur currentPlayer;
    private boolean isGameOver;

    public Game() {
        this(new Plateau());
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
            shootLaser();
            checkWinConditions();
            togglePlayer();
        }
    }

    public void shootLaser() {
        boolean hit = board.shootLaser(currentPlayer);
        if (hit) {
            isGameOver = true;
        }
    }

    private void checkWinConditions() {
        boolean pharaohHit = board.shootLaser(currentPlayer);
        if (pharaohHit) {
            isGameOver = true;
        }
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
