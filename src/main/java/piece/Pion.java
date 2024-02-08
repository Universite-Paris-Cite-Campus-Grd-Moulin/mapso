package src.main.java.piece;

import src.main.java.enums.Couleur;
import src.main.java.enums.TypeDePion;

public abstract class Pion {
    protected TypeDePion type;
    protected int positionX;
    protected int positionY;
    protected Couleur couleur;

    public Pion(TypeDePion type, int positionX, int positionY, Couleur couleur) {
        this.type = type;
        this.positionX = positionX;
        this.positionY = positionY;
        this.couleur = couleur;
    }

    // Méthodes abstraites pour les actions spécifiques à chaque pion
    public abstract void deplacer(int nouvelleX, int nouvelleY);
    public abstract void actionSpecifique();

    // Getters et Setters
    public TypeDePion getType() {
        return type;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    // Vous pouvez ajouter d'autres méthodes utiles ici
}

