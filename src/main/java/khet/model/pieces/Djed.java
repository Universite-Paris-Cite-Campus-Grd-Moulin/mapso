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

    public Direction interactWithLaser(Direction laserDirection) {
        // Détermine si le laser frappe le miroir ou le dos de la Pyramide.
        boolean hitsMirror = false;
    
        switch (this.direction) {
            case NORD:
                // Le miroir réfléchit le laser venant de l'EST vers le SUD, et de l'OUEST vers le NORD.
                if (laserDirection == Direction.EST) {
                    return Direction.SUD;
                } else if (laserDirection == Direction.OUEST) {
                    return Direction.NORD;
                }
                break;
            case SUD:
                // Le miroir réfléchit le laser venant de l'OUEST vers le SUD, et de l'EST vers le NORD.
                if (laserDirection == Direction.OUEST) {
                    return Direction.SUD;
                } else if (laserDirection == Direction.EST) {
                    return Direction.NORD;
                }
                break;
            case EST:
                // Le miroir réfléchit le laser venant du NORD vers l'EST, et du SUD vers l'OUEST.
                if (laserDirection == Direction.NORD) {
                    return Direction.EST;
                } else if (laserDirection == Direction.SUD) {
                    return Direction.OUEST;
                }
                break;
            case OUEST:
                // Le miroir réfléchit le laser venant du SUD vers l'EST, et du NORD vers l'OUEST.
                if (laserDirection == Direction.SUD) {
                    return Direction.EST;
                } else if (laserDirection == Direction.NORD) {
                    return Direction.OUEST;
                }
                break;
        }
        return null; // Ou une valeur spéciale indiquant la destruction de la pièce.
    }
}
