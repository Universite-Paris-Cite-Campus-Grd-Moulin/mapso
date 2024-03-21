package model;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class Pion {
    protected TypeDePion type;
    protected Direction direction ;
    protected Couleur couleur;

    public Pion(TypeDePion type, Direction direction, Couleur couleur) {
        this.type = type;
        this.direction = direction;
        this.couleur = couleur;
    }

    // Méthodes abstraites pour les actions spécifiques à chaque pion
    

    // Getters et Setters
    public TypeDePion getType() {
        return type;
    }

    public Couleur getCouleur() {
        return couleur;
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

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public void getPieceAt(Direction a){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPieceAt'");
    }

    public void rotate(boolean clockwise) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rotate'");
    }

    // Vous pouvez ajouter d'autres méthodes utiles ici
}

