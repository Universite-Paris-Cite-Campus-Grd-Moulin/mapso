package khet.model;

import java.util.List;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;

public abstract class Piece {
    protected Board board; // Référence au plateau de jeu
    protected final Couleur couleur;
    protected Direction direction;
    protected TypeDePion type;
    protected int x, y; // Position sur le plateau
    protected boolean isAlive = true;


    // Constructeur
    public Piece(Board board, Couleur couleur, Direction direction, TypeDePion type, int x, int y) {
        this.board = board;
        this.couleur = couleur;
        this.direction = direction;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    // Getter pour vérifier si la pièce est en vie
    public boolean isAlive() {
        return isAlive;
    }

    // Méthode pour "tuer" la pièce
    public void kill() {
        isAlive = false;
    }

    // Getters et setters
    public Couleur getCouleur() { return couleur; }
    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }
    public TypeDePion getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    // Méthodes abstraites
    public abstract void move(int newX, int newY);
    public abstract void rotate(boolean clockwise);
    public abstract List<LaserTrajectory> interactWithLaser(Direction laserDirection, int startX, int startY);

    public boolean isMoveValid(int newX, int newY) {
        if (newX < 0 || newX >= board.getWidth() || newY < 0 || newY >= board.getHeight()) {
            return false;
        }
        return true;
    }
}
