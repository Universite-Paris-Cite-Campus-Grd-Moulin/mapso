package model;

import enums.TypeDePion;
import enums.Couleur;
import enums.Direction;
// rotation variable modulo 4 (en incrémentant r = (r + 1)%4)
public class Pion {
    protected TypeDePion type;
    protected Direction direction ;
    protected Couleur couleur;
    private int rotation ;

    public Pion(TypeDePion type, Direction direction, Couleur couleur) {
        this.type = type;
        this.direction = direction;
        this.couleur = couleur;
        this.rotation = 0 ;
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

    public int getRotation (){
        return this.rotation ;
    }

    public void setRotation( int rotation) {
        this.rotation = rotation;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    // Vous pouvez ajouter d'autres méthodes utiles ici
}

