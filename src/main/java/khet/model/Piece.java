/*
 * Cette classe fournit les propriétés de base et les méthodes abstraites
 * que toutes les pièces doivent implémenter. move(), rotate(),
 * et interactWithLaser() sont abstraites car chaque pièce aura un
 * comportement spécifique pour ces actions.
 */

package khet.model;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;

public abstract class Piece {
    //Attribut
    protected final Couleur couleur;
    protected Direction direction;
    protected TypeDePion type;
    protected int x, y; // Position sur le plateau
    protected boolean isAlive;

    //Constructeur pour le Pharaon et Obelisque
    public Piece(Couleur couleur, Direction direction, TypeDePion type, int x, int y, boolean isAlive) {
        this.couleur = couleur;
        this.direction = direction;
        this.type = type;
        this.x = x;
        this.y = y;
        this.isAlive = true; // Toutes les pièces commencent vivantes
    }

    //Constructeur pour le reste des pieces
    public Piece(Couleur couleur, Direction direction, TypeDePion type, int x, int y) {
        this.couleur = couleur;
        this.direction = direction;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public boolean isAlive() {
        return isAlive;
    }

    // Ajoutez une méthode pour "tuer" la pièce
    public void kill() {
        isAlive = false;
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

    public TypeDePion getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Déplace la pièce sur le plateau
    public abstract void move(int newX, int newY);

    // Tourne la pièce dans une direction donnée
    public abstract void rotate(boolean clockwise);

    // Interaction de la pièce avec le laser
    public abstract Direction interactWithLaser(Direction laserDirection);
}