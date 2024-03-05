package model.pieces;

import java.util.List;

import enums.Couleur;
import enums.Direction;
import enums.TypeDePion;
import model.Board;
import model.LaserTrajectory;
import model.Piece;

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
    public List<LaserTrajectory> interactWithLaser(Direction laserDirection, int startX, int startY) {
        // Le Pharaon est retiré du jeu s'il est touché par le laser
        // Donc on retourne null (ou une direction spéciale indiquant la fin du jeu)
        return null;
    }
}
