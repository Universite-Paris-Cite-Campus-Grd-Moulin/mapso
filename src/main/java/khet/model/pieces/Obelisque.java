package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.Piece;

public class Obelisque extends Piece {

    private boolean isStacked; // Indique si cet Obélisque fait partie d'un double

    public Obelisque(Board board, Couleur couleur, int x, int y) {
        super(board, couleur, Direction.NONE, TypeDePion.OBELISQUE, x, y);
        this.isStacked = false; // Initialisé à false, peut être modifié via setStacked
    }

    public boolean isStacked() {
        return isStacked;
    }

    public void setStacked(boolean isStacked) {
        this.isStacked = isStacked;
    }

    public void move(int newX, int newY) {
        // Vérification si le mouvement est valide
        if (isMoveValid(newX, newY)) {
            // Si le mouvement est valide, mettez à jour la position de la pièce
            this.x = newX;
            this.y = newY;
        } else {
            // Si le mouvement n'est pas valide, vous pourriez lancer une exception ou gérer l'erreur d'une autre manière
            throw new IllegalArgumentException("Mouvement invalide.");
        }
    }
    
    private boolean isMoveValid(int newX, int newY) {
        // Vérifiez les limites du plateau (en supposant un plateau de taille 10x8 pour cet exemple)
        if (newX < 0 || newX >= 10 || newY < 0 || newY >= 8) {
            return false; // Le mouvement est en dehors du plateau
        }
        // Vérifier si la case est occupée
        return !board.isOccupied(newX, newY);
    }

    @Override
    public void rotate(boolean clockwise) {
        // Les Obélisques ne tournent pas
    }

    @Override
    public Direction interactWithLaser(Direction laserDirection) {
        // Gère l'interaction de l'Obélisque avec le laser
        if (isStacked) {
            // Si cet Obélisque est la partie supérieure d'un double, il est retiré du jeu,
            // mais l'état isStacked doit être géré par la logique globale du jeu pour le reste de l'Obélisque.
            isStacked = false;
            // Signal à la logique globale du jeu que l'Obélisque supérieur a été retiré
            return null;
        } else {
            // Pour un Obélisque simple, il est simplement retiré du jeu.
            this.kill();
            return null;
        }
    }
}
