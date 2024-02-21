//Realise par Paul
package khet.model;

import khet.enums.Couleur;
import khet.enums.TypeDePion;

// Représente une pièce générique sur le plateau de jeu
public abstract class Piece {
    protected TypeDePion type;
    protected int x, y; // Position sur le plateau
    protected Couleur couleur; // Couleur de la pièce

    // Constructeur unifié qui inclut la couleur de la pièce
    public Piece(TypeDePion type, int x, int y, Couleur couleur) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }

    // Méthode abstraite pour déplacer la pièce. Cette méthode doit être implémentée par les classes dérivées
    public abstract void move(int newX, int newY);

    // Méthode abstraite pour les actions spécifiques à chaque type de pièce
    public abstract void actionSpecifique();

    // Getters pour accéder aux propriétés de la pièce
    public TypeDePion getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    // Setters si nécessaire, pour permettre la modification des propriétés de la pièce
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Vous pouvez ajouter d'autres méthodes utiles ici selon les besoins du jeu
}
