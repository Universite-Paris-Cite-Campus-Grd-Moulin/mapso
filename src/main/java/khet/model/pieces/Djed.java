package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Piece;

public class Djed extends Piece {

    public Djed(Couleur couleur, int x, int y) {
        super(couleur, Direction.NORD, TypeDePion.DJED, x, y);
    }

    @Override
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
    
    // Ecrire la suite au cas la case est reserve par un autre piece
    //Mais normalement il est tjrs true car il joue le role de la reine

    return true; // Le mouvement est valide
}


    @Override
    public void rotate(boolean clockwise) {
        // Tourne la pièce de +/- 90 degrés.
        if (clockwise) {
            this.direction = this.direction.nextClockwise();
        } else {
            this.direction = this.direction.nextCounterClockwise();
        }
    }

    @Override
    public Direction interactWithLaser(Direction laserDirection) {
        // Réfléchit le laser à un angle de +/- 90 degrés.
        // Cette logique simplifiée suppose que le Djed réfléchit toujours le laser perpendiculairement.
        // La logique réelle dépendra de la direction du Djed et de la direction d'où vient le laser.
        if (laserDirection == Direction.NORD || laserDirection == Direction.SUD) {
            return laserDirection.rotate90Degrees();
        } else {
            return laserDirection.rotate90Degrees();
        }
    }
}
