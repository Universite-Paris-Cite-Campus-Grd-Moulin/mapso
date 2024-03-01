package khet.model.pieces;

import java.util.ArrayList;
import java.util.List;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.LaserTrajectory;
import khet.model.Piece;

public class Pyramide extends Piece {

    public Pyramide(Board board, Couleur couleur, Direction direction, int x, int y) {
        super(board, couleur, direction, TypeDePion.PYRAMIDE, x, y);
    }

    public void move(int newX, int newY) {
        // Vérification si le mouvement est valide
        if (super.isMoveValid(newX, newY)) {
            // Si le mouvement est valide, mettez à jour la position de la pièce
            this.x = newX;
            this.y = newY;
        } else {
            // Si le mouvement n'est pas valide, vous pourriez lancer une exception ou gérer l'erreur d'une autre manière
            throw new IllegalArgumentException("Mouvement invalide.");
        }
    }
    
    @Override
    public void rotate(boolean clockwise) {
        // Rotation de la Pyramide de +/- 90 degrés
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

        @Override
    public List<LaserTrajectory> interactWithLaser(Direction laserDirection, int startX, int startY) {
        List<LaserTrajectory> trajectories = new ArrayList<>();
        
        // Déterminer la nouvelle direction du laser basée sur l'orientation de la Pyramide et la direction du laser
        Direction newDirection = null;
        switch (this.direction) {
            case NORD:
                // Si le laser vient de l'ouest, il est réfléchi vers le sud, et vice versa
                if (laserDirection == Direction.OUEST) newDirection = Direction.SUD;
                if (laserDirection == Direction.SUD) newDirection = Direction.OUEST;
                break;
            case SUD:
                // Si le laser vient de l'est, il est réfléchi vers le nord, et vice versa
                if (laserDirection == Direction.EST) newDirection = Direction.NORD;
                if (laserDirection == Direction.NORD) newDirection = Direction.EST;
                break;
            case EST:
                // Si le laser vient du nord, il est réfléchi vers l'ouest, et vice versa
                if (laserDirection == Direction.NORD) newDirection = Direction.OUEST;
                if (laserDirection == Direction.OUEST) newDirection = Direction.NORD;
                break;
            case OUEST:
                // Si le laser vient du sud, il est réfléchi vers l'est, et vice versa
                if (laserDirection == Direction.SUD) newDirection = Direction.EST;
                if (laserDirection == Direction.EST) newDirection = Direction.SUD;
                break;
        }

        // Si le laser peut être réfléchi, ajoutez cette nouvelle trajectoire à la liste
        if (newDirection != null) {
            trajectories.add(new LaserTrajectory(newDirection, startX, startY));
        }
        
        return trajectories;
    }



}
