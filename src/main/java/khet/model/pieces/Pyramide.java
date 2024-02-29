package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Piece;

public class Pyramide extends Piece {

    public Pyramide(Couleur couleur, Direction direction, int x, int y) {
        super(couleur, direction, TypeDePion.PYRAMIDE, x, y);
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
        
        // Ecrire la suite au cas la case est reserve par un autre piece ou limitation de mvt
    
        return true; // Le mouvement est valide
    }

    @Override
    public void rotate(boolean clockwise) {
        // Rotation de la Pyramide de +/- 90 degrés
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

    @Override
    public Direction interactWithLaser(Direction laserDirection) {
        // Exemple de réflexion basique, à ajuster selon l'orientation réelle et la direction d'arrivée du laser.
        if (this.direction == Direction.NORD || this.direction == Direction.SUD) {
            // Si la direction est nord ou sud, supposons que le laser venant de l'est ou de l'ouest est réfléchi vers le haut ou le bas.
            if (laserDirection == Direction.EST || laserDirection == Direction.OUEST) {
                return laserDirection.rotate90Degrees();
            }
        } else if (this.direction == Direction.EST || this.direction == Direction.OUEST) {
            // Si la direction est est ou ouest, supposons que le laser venant du nord ou du sud est réfléchi vers la gauche ou la droite.
            if (laserDirection == Direction.NORD || laserDirection == Direction.SUD) {
                return laserDirection.rotate90Degrees();
            }
        }
        // Si le laser ne rencontre pas le miroir de manière à être réfléchi, il continue dans sa direction initiale (ce cas peut varier selon votre logique de jeu).
        return laserDirection;
    }
}
