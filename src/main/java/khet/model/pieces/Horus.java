package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.Piece;

public class Horus extends Piece {

    public Horus(Board board, Couleur couleur, int x, int y) {
        super(board, couleur, Direction.NORD, TypeDePion.HORUS, x, y);
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
        // Tourne la pièce de +/- 90 degrés.
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

    @Override
    public Direction interactWithLaser(Direction laserDirection) {
        // Supposons que l'Horus est orienté de manière à réfléchir le laser vers la gauche ou la droite lorsqu'il arrive de l'avant ou de l'arrière,
        // et vers le haut ou le bas lorsqu'il arrive des côtés.
        if (this.direction == Direction.NORD || this.direction == Direction.SUD) {
            // Si le laser arrive de l'est ou de l'ouest, il continue tout droit.
            // Si le laser arrive du nord ou du sud, il est réfléchi à gauche ou à droite.
            if (laserDirection == Direction.EST || laserDirection == Direction.OUEST) {
                // Le laser continue en ligne droite.
                return laserDirection;
            } else {
                // Le laser est réfléchi à 90 degrés.
                return laserDirection.rotate90Degrees();
            }
        } else {
            // Si le laser arrive du nord ou du sud, il continue tout droit.
            // Si le laser arrive de l'est ou de l'ouest, il est réfléchi à gauche ou à droite.
            if (laserDirection == Direction.NORD || laserDirection == Direction.SUD) {
                // Le laser continue en ligne droite.
                return laserDirection;
            } else {
                // Le laser est réfléchi à 90 degrés.
                return laserDirection.rotate90Degrees();
            }
        }
    }

}
