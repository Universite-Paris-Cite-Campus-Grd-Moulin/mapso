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
        this.type = type;
        this.direction = direction;
        this.couleur = couleur;
        this.x = x;
        this.y = y;
    }

    // Méthodes abstraites pour les actions spécifiques à chaque pion

    // Getters et Setters
    public TypeDePion getType() {
        return type;
    }

    public void setType(TypeDePion type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

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

    public void getPieceAt(Direction a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPieceAt'");
    }

    // Méthode pour définir de nouvelles coordonnées
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Rotation du pion
    public void rotate(boolean clockwise) {
        this.direction = clockwise ? this.direction.nextClockwise() : this.direction.nextCounterClockwise();
    }

    public boolean peutPivoter() {
        // Ajouter la logique pour déterminer si le pion peut pivoter. Par exemple:
        return this.type == TypeDePion.DJED || this.type == TypeDePion.PYRAMIDE || this.type == TypeDePion.HORUS;
    }

    public boolean isMarkedForSplitting() {
        return this.isMarkedForSplitting;
    }

    public void setMarkedForSplitting(boolean marked) {
        this.isMarkedForSplitting = marked;
    }

    public boolean isRotationRequested() {
        return this.isRotationRequested;
    }

    public void setRotationRequested(boolean requested) {
        this.isRotationRequested = requested;
    }

    public boolean estEmpile() {
        return estEmpile;
    }

    public void setEmpiled(boolean empile) {
        estEmpile = empile;
    }

}
