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
        this.currentPlayer = Couleur.JAUNE;
        this.isGameOver = false;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (currentPlayer == board.getPieceAt(startX, startY).getCouleur()) {
            if (board.movePiece(startX, startY, endX, endY)) {
                togglePlayer(); // Changer de joueur seulement après un mouvement valide
                return true;
            }
        }
        return false;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
    }

    public Couleur getCurrentPlayer() {
        return currentPlayer;
    }

    public Plateau getBoard() {
        return board;
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
    }

    public boolean selectPiece(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null && piece.getCouleur() == currentPlayer) {
            // Logique pour gérer la sélection, peut-être stocker la pièce sélectionnée ici
            return true;
        }
        return false;
    }

    public Pion getPieceAt(int x, int y) {
        return board.getPieceAt(x, y);
    }

    public void start() {
        isGameOver = false;
        currentPlayer = Couleur.JAUNE;
        nextTurn();
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

    public boolean isGameOver() {
        return isGameOver;
    }

}
