package khet.model;

import khet.enums.Couleur;
import khet.enums.Direction;

// Représente une pièce générique sur le plateau de jeu
public abstract class Piece {
    protected int x, y; // Position sur le plateau
    protected Couleur couleur;
    protected Direction direction; // Ajout de l'attribut direction

    // Constructeur ajusté pour inclure la direction
    public Piece(int x, int y, Couleur couleur, Direction direction) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        this.direction = direction; // Initialisation de la direction
    }

    public abstract void move(int newX, int newY);

    public abstract void actionSpecifique();

    // Getters et setters ajustés pour inclure la direction
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
