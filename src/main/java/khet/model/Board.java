package khet.model;

import khet.enums.Couleur;
import khet.model.pieces.Pharaon;

public class Board {
    private Piece[][] grid; // Tableau 2D pour stocker les pièces
    private final int width = 10; // Largeur du plateau
    private final int height = 8; // Hauteur du plateau

    public Board() {
        this.grid = new Piece[height][width];
        // Initialiser le plateau avec les pièces dans leur position de départ
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialisez ici le plateau avec les pièces dans leurs positions de départ
        this.grid[0][0] = new Pharaon(this, Couleur.ROUGE, 0, 0, true);
        this.grid[7][9] = new Pharaon(this, Couleur.JAUNE, 9, 7, true);
    }
    

    public boolean isOccupied(int x, int y) {
        return grid[y][x] != null;
    }

    public Piece getPieceAt(int x, int y) {
        return grid[y][x];
    }


    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= width || startY < 0 || startY >= height ||
            endX < 0 || endX >= width || endY < 0 || endY >= height) {
            return false; // Vérifie si les coordonnées sont hors limites
        }
    
        Piece piece = grid[startY][startX];
        if (piece != null && piece.isMoveValid(endX, endY)) {
            grid[endY][endX] = piece;
            grid[startY][startX] = null;
            piece.setX(endX);
            piece.setY(endY);
            return true;
        }
    
        return false;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null; // Hors limites
        }
        return grid[y][x];
    }

    public void removePiece(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[y][x] = null; // Supprime la pièce de la grille
        }
    }
    
}
