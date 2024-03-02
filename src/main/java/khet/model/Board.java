package khet.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.model.pieces.Djed;
import khet.model.pieces.Horus;
import khet.model.pieces.Obelisque;
import khet.model.pieces.Pharaon;
import khet.model.pieces.Pyramide;

public class Board {
    private Piece[][] grid; // Tableau 2D pour stocker les pièces
    private final int width = 10; // Largeur du plateau
    private final int height = 8; // Hauteur du plateau

    public Board() {
        this.grid = new Piece[height][width];
        initializeBoard();
    }

    private void initializeBoard() {
        //this.grid[0][0] = new Pharaon(this, Couleur.ROUGE, 0, 0, true);
        //this.grid[7][9] = new Pharaon(this, Couleur.JAUNE, 9, 7, true);
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
            return false;
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
            return null;
        }
        return grid[y][x];
    }

    public void removePiece(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[y][x] = null;
        }
    }

    public void loadFromFile(String filename) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("boardConfig.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                for (int x = 0; x < line.length() && x < grid[y].length; x += 3) { // On incrémente de 3 pour sauter les espaces entre les codes
                    String pieceCode = line.substring(x, x+2); // Extrait le code de la pièce, par exemple "OJ"
                    char pieceType = pieceCode.charAt(0); // 'O', 'P', etc.
                    char colorCode = pieceCode.charAt(1); // 'R' ou 'J'

                    // Convertir le code couleur en enum Couleur
                    Couleur couleur = (colorCode == 'R') ? Couleur.ROUGE : Couleur.JAUNE;

                    Piece piece = null;

                    switch (pieceType) {
                        case 'O': // Obélisque
                            piece = new Obelisque(this, couleur, x / 3, y, false);
                            break;
                        case 'P': // Pyramide
                            piece = new Pyramide(this, couleur, Direction.NORD, x / 3, y); // Supposons que la direction soit nord par défaut
                            break;
                        case 'D': // Djed
                            piece = new Djed(this, couleur, x / 3, y);
                            break;
                        case 'H': // Horus
                            piece = new Horus(this, couleur, x / 3, y);
                            break;
                        case 'F': // Pharaon
                            piece = new Pharaon(this, couleur, x / 3, y, true);
                            break;
                        case 'V': // Case vide
                            continue; // Pas besoin de créer une pièce pour une case vide
                    }

                    if (piece != null) {
                        grid[y][x / 3] = piece; // Place la pièce dans la grille
                    }
                }

                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
