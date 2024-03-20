package model;

public class ArbreTrajectoire {
    private NoeudTrajectoire racine; // La racine de l'arbre de trajectoire

    // Constructeur
    public ArbreTrajectoire(NoeudTrajectoire racine) {
        this.racine = racine;
    }

    public NoeudTrajectoire getRacine() {
        return racine;
    }

    public void setRacine(NoeudTrajectoire racine) {
        this.racine = racine;
    }

    // Méthodes pour manipuler l'arbre
    // ...
}
