package src.main.java.piece;

import src.main.java.enums.Couleur;
import src.main.java.enums.TypeDePion;
import src.main.java.enums.Direction;

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

    // Vous pouvez ajouter d'autres méthodes utiles ici
}

