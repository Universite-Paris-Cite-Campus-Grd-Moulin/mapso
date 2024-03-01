package khet.model.pieces;

import java.util.List;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.LaserTrajectory;
import khet.model.Piece;

public class Obelisque extends Piece {

    private boolean isStacked; // Indique si cet Obélisque fait partie d'un double

    public Obelisque(Board board, Couleur couleur, int x, int y, boolean isStacked) {
        super(board, couleur, Direction.NONE, TypeDePion.OBELISQUE, x, y);
        this.isStacked = isStacked;
    }

    public boolean isStacked() {
        return isStacked;
    }

    public void setStacked(boolean isStacked) {
        this.isStacked = isStacked;
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
        // Les Obélisques ne tournent pas
    }

    @Override
    public List<LaserTrajectory> interactWithLaser(Direction laserDirection, int startX, int startY) {        // Gère l'interaction de l'Obélisque avec le laser
        if (isStacked) {
            // Si cet Obélisque est la partie supérieure d'un double, il est retiré du jeu,
            // mais l'état isStacked doit être géré par la logique globale du jeu pour le reste de l'Obélisque.
            isStacked = false;
            // Signal à la logique globale du jeu que l'Obélisque supérieur a été retiré
            return null;
        } else {
            // Pour un Obélisque simple, il est simplement retiré du jeu.
            this.kill();
            return null;
        }
    }
}
