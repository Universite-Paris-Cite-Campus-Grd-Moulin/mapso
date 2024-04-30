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
        this.currentPlayer = Couleur.JAUNE; // Commence le jeu avec le joueur Jaune
        this.isGameOver = false;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (isGameOver) return false; // Empêche les mouvements si le jeu est terminé
        if (currentPlayer == getPieceAt(startX, startY).getCouleur()) {
            if (board.movePiece(startX, startY, endX, endY)) {
                togglePlayer(); // Change de joueur après un mouvement valide
                return true;
            }
        }
        return false;
    }

    public boolean rotatePiece(int x, int y, boolean clockwise) {
        if (isGameOver) return false; // Empêche la rotation si le jeu est terminé
        if (currentPlayer == getPieceAt(x, y).getCouleur()) {
            board.rotatePiece(x, y, clockwise);
            togglePlayer(); // Change de joueur après une rotation valide
            return true;
        }
        return false;
    }

    public Pion getPieceAt(int x, int y) {
        return board.getPieceAt(x, y);
    }

    public Plateau getPlateau() {
        return board;
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

    public void start() {
        isGameOver = false;
        currentPlayer = Couleur.JAUNE; // Commence toujours par le joueur Jaune
        // Initialisation ou réinitialisation du plateau ici si nécessaire
    }

    public void shootLaser() {
        if (board.shootLaser(currentPlayer)) {
            isGameOver = true; // Met fin au jeu si le pharaon est touché
        }
    }

    private void checkWinConditions() {
        if (board.checkForPharaohHit(currentPlayer)) {
            isGameOver = true; // Vérifie si un pharaon a été touché
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
