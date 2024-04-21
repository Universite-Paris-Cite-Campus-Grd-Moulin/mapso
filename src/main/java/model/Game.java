package model;

import model.enums.Couleur;

public class Game {
    private Plateau board;
    private Couleur currentPlayer;
    private boolean isGameOver;

    public Game(Plateau board) {
        this.board = board;
        this.currentPlayer = Couleur.JAUNE; // Le jeu commence toujours par le joueur Jaune
    }

    public Couleur getCurrentPlayer() {
        return currentPlayer;
    }

    public void movePiece(int startX, int startY, int endX, int endY) {
        if (board.movePiece(startX, startY, endX, endY)) {
            switchPlayer(); // Change de joueur après un mouvement réussi
        }
    }

    public void rotatePiece(Pion pion) {
        board.tournerPion(pion, pion.getDirection().nextClockwise()); // Rotation à droite par exemple
        switchPlayer(); // Change de joueur après une rotation
    }

    public void stackOrUnstackObelisk(Pion pion, int targetX, int targetY) {
        // Ici, ajoutez la logique pour empiler ou dépiler
        switchPlayer(); // Change de joueur après l'empilement/dépilement
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
    }

    public boolean isPlayerTurn(Couleur playerColor) {
        return currentPlayer == playerColor;
    }

    // Modification du constructeur pour accepter un type de plateau
    public Game(String type) {
        this(new Plateau(type)); // Crée un plateau du type spécifié
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
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
