package khet.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

    // Méthode shootLaser simulant le tir du laser et retournant vrai si un Pharaon est touché
public boolean shootLaser(Couleur couleurLaser) {
    int startX, startY;
    Direction directionLaser;

    // Déterminez le point de départ du laser basé sur la couleur du joueur actuel
    if (couleurLaser == Couleur.ROUGE) {
        startX = 0; // Supposons que le laser rouge commence à gauche du plateau
        startY = 0; // Supposons une position Y arbitraire pour le début
        directionLaser = Direction.EST; // Le laser rouge se déplace vers la droite
    } else {
        startX = width - 1; // Supposons que le laser jaune commence à droite du plateau
        startY = height - 1; // Supposons une position Y arbitraire pour le début
        directionLaser = Direction.OUEST; // Le laser jaune se déplace vers la gauche
    }

    int x = startX;
    int y = startY;
    boolean pharaohHit = false;

    while (true) {
        // Calculez la nouvelle position du laser après un mouvement
        x += directionLaser.getDeltaX();
        y += directionLaser.getDeltaY();

        // Vérifiez si le laser sort du plateau
        if (x < 0 || x >= width || y < 0 || y >= height) {
            break; // Le laser sort du plateau
        }

        // Obtenez la pièce à la nouvelle position
        Piece piece = getPieceAt(x, y);
        if (piece != null) {
            // Interagissez avec la pièce
            List<LaserTrajectory> trajectories = piece.interactWithLaser(directionLaser, x, y);
            if (trajectories.isEmpty() || piece instanceof Pharaon) {
                // Si le laser touche le Pharaon, le jeu est terminé
                pharaohHit = true;
                break;
            } else {
                // Changez la direction du laser en fonction de la première trajectoire retournée
                directionLaser = trajectories.get(0).getDirection();
            }
        }
    }

    return pharaohHit;
}

public boolean isPharaohHit() {
    // Parcourez toutes les pièces sur le plateau
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            Piece piece = grid[y][x];
            if (piece instanceof Pharaon && !piece.isAlive()) {
                return true; // Un Pharaon a été touché et n'est plus en vie
            }
        }
    }
    return false; // Aucun Pharaon n'a été touché
}

}
