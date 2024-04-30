package model;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class Pion {
    protected TypeDePion type;
    protected Direction direction;
    protected Couleur couleur;
    private int x; // Coordonnée x sur le plateau
    private int y; // Coordonnée y sur le plateau
    private boolean isMarkedForSplitting; // Indicateur pour la séparation
    private boolean isRotationRequested; // Indicateur pour la rotation demandée
    private boolean estEmpile; // Indicateur si le pion est empilé

    public Pion(TypeDePion type, Direction direction, Couleur couleur) {
        this.type = type;
        this.direction = direction;
        this.couleur = couleur;
    }

    public Pion(TypeDePion type, Direction direction, Couleur couleur, int x, int y) {
        this(type, direction, couleur);
        setPosition(x, y);
    }

    // Getters et Setters
    public TypeDePion getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isMarkedForSplitting() {
        return isMarkedForSplitting;
    }

    public boolean isRotationRequested() {
        return isRotationRequested;
    }

    public boolean estEmpile() {
        return estEmpile;
    }

    public void setType(TypeDePion type) {
        this.type = type;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setMarkedForSplitting(boolean marked) {
        this.isMarkedForSplitting = marked;
    }

    public void setRotationRequested(boolean requested) {
        this.isRotationRequested = requested;
    }

    public void setEmpiled(boolean empile) {
        this.estEmpile = empile;
    }

    // Rotation du pion
    public void rotate(boolean clockwise) {
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

    public boolean peutPivoter() {
        return type == TypeDePion.DJED || type == TypeDePion.PYRAMIDE || type == TypeDePion.HORUS;
    }
}
