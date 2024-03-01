package khet.model.pieces;

import java.util.ArrayList;
import java.util.List;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.LaserTrajectory;
import khet.model.Piece;

public class Horus extends Piece {

    public Horus(Board board, Couleur couleur, int x, int y) {
        super(board, couleur, Direction.NORD, TypeDePion.HORUS, x, y);
    }

    @Override
    public void move(int newX, int newY) {
        // Vérification et mise à jour de la position si le mouvement est valide
        if (isMoveValid(board, newX, newY)) {
            this.x = newX;
            this.y = newY;
        } else {
            throw new IllegalArgumentException("Mouvement invalide.");
        }
    }

    // Implémentation de la rotation de la pièce
    @Override
    public void rotate(boolean clockwise) {
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

    // Interaction spécifique de Horus avec le laser
    @Override
    public List<LaserTrajectory> interactWithLaser(Direction laserDirection, int startX, int startY) {
        List<LaserTrajectory> trajectories = new ArrayList<>();
        trajectories.add(new LaserTrajectory(laserDirection, startX, startY)); // Continuation
        Direction reflectedDirection = laserDirection.rotate90Degrees();
        trajectories.add(new LaserTrajectory(reflectedDirection, startX, startY)); // Réflexion
        return trajectories;
    }

    private boolean isMoveValid(Board board, int newX, int newY) {
        // Implémentez la logique de validation du mouvement ici
        return !board.isOccupied(newX, newY);
    }
}
