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
        // Vérifiez si la case cible est occupée par une autre pièce.(djed ou pharaon)

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
        switch (this.direction) {
            case NORD, SUD:
                if (laserDirection == Direction.OUEST) return Direction.SUD;
                if (laserDirection == Direction.SUD) return Direction.OUEST;
                if (laserDirection == Direction.EST) return Direction.NORD;
                if (laserDirection == Direction.NORD) return Direction.EST;
                break;
            case EST, OUEST:
                if (laserDirection == Direction.NORD) return Direction.OUEST;
                if (laserDirection == Direction.OUEST) return Direction.NORD;
                if (laserDirection == Direction.SUD) return Direction.EST;
                if (laserDirection == Direction.EST) return Direction.SUD;
                break;
        }
        return null;
    }
}
