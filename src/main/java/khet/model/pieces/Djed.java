package model.pieces;

import java.util.ArrayList;
import java.util.List;

import enums.Couleur;
import enums.Direction;
import enums.TypeDePion;
import model.Board;
import model.LaserTrajectory;
import model.Piece;

public class Djed extends Piece {

    public Djed(Board board, Couleur couleur, int x, int y) {
        super(board, couleur, Direction.NORD, TypeDePion.DJED, x, y);
    }

    @Override
    public void move(int newX, int newY) {
        if (super.isMoveValid(newX, newY)) {
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

    @Override
    public void rotate(boolean clockwise) {
        // Rotation logique
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

    @Override
    public List<LaserTrajectory> interactWithLaser(Direction laserDirection, int startX, int startY) {
        // Implémentation pour interagir avec le laser
        List<LaserTrajectory> trajectories = new ArrayList<>();
        Direction reflectedDirection = this.direction == Direction.NORD || this.direction == Direction.SUD
                                       ? laserDirection.rotate90Degrees()
                                       : laserDirection;
        trajectories.add(new LaserTrajectory(reflectedDirection, startX, startY));
        return trajectories;
    }

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
