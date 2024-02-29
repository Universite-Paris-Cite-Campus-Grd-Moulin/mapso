package khet.model;

public class Board {
    private Piece[][] board;

    public Board() {
        // Initialiser le plateau de jeu
    }

    public Piece getPieceAt(int x, int y) {
        // Retourner la pièce à une position spécifique
        return board[x][y];
    }

    public void setPieceAt(int x, int y, Piece piece) {
        // Placer une pièce sur le plateau
    }

    public boolean movePiece(int fromX, int fromY, int toX, int toY) {
        // Déplacer une pièce et retourner vrai si le mouvement est réussi
        return false;
    }
}
