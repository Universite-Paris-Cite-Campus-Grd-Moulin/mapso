package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Board;
import khet.model.Piece;

public class Pharaon extends Piece {

    public Pharaon(Board board, Couleur couleur, int x, int y, boolean isAlive) {
        super(board, couleur, Direction.NORD, TypeDePion.PHARAON, x, y);
    }

    @Override
    public void move(int newX, int newY) {
        // Le Pharaon ne peut pas se déplacer;
        // Donc elle reste vide
    }

    @Override
    public void rotate(boolean clockwise) {
        // Le Pharaon ne tourne pas;
        // Donc elle reste vide
    }

    @Override
    public Direction interactWithLaser(Direction laserDirection) {
        // Le Pharaon est retiré du jeu s'il est touché par le laser
        // Donc on retourne null (ou une direction spéciale indiquant la fin du jeu)
        return null;
    }
}
