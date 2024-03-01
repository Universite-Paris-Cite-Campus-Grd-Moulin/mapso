package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.Piece;

public class Pyramide extends Piece {

    public Pyramide(Board board, Couleur couleur, Direction direction, int x, int y) {
        super(board, couleur, direction, TypeDePion.PYRAMIDE, x, y);
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
        // Rotation de la Pyramide de +/- 90 degrés
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

    @Override
public Direction interactWithLaser(Direction laserDirection) {
    switch (this.direction) {
        case NORD:
            if (laserDirection == Direction.OUEST) return Direction.SUD;
            if (laserDirection == Direction.SUD) return Direction.OUEST;
            break;
        case SUD:
            if (laserDirection == Direction.EST) return Direction.NORD;
            if (laserDirection == Direction.NORD) return Direction.EST;
            break;
        case EST:
            if (laserDirection == Direction.NORD) return Direction.OUEST;
            if (laserDirection == Direction.OUEST) return Direction.NORD;
            break;
        case OUEST:
            if (laserDirection == Direction.SUD) return Direction.EST;
            if (laserDirection == Direction.EST) return Direction.SUD;
            break;
    }
    return null;
}



}
