package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.Piece;

public class Djed extends Piece {

    public Djed(Board board, Couleur couleur, int x, int y) {
        super(board, couleur, Direction.NORD, TypeDePion.DJED, x, y);
    }

    @Override
    public void move(int newX, int newY) {
        if (isMoveValid(board, newX, newY)) {
            Piece targetPiece = board.getPieceAt(newX, newY);
            if (targetPiece != null) {
                // Échangez les positions du Djed et de la pièce cible.
                int tempX = this.x;
                int tempY = this.y;
                this.x = newX;
                this.y = newY;
                targetPiece.setX(tempX);
                targetPiece.setY(tempY);
            } else {
                // Si la case est vide, déplacez simplement le Djed.
                this.x = newX;
                this.y = newY;
            }
        } else {
            throw new IllegalArgumentException("Mouvement invalide.");
        }
    }

    private boolean isMoveValid(int newX, int newY) {
        // Vérifiez les limites du plateau (en supposant un plateau de taille 10x8 pour cet exemple)
        if (newX < 0 || newX >= 10 || newY < 0 || newY >= 8) {
            return false; // Le mouvement est en dehors du plateau
        }
        // Vérifiez si la case cible est occupée par une autre pièce.
        Piece targetPiece = board.getPieceAt(newX, newY);
        if (targetPiece != null) {
            // Si la pièce cible existe et n'est pas de la même couleur, le Djed peut échanger de place avec elle.
            return this.couleur != targetPiece.getCouleur();
        }

        // Si la case cible n'est pas occupée, le mouvement est valide.
        return true;
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
