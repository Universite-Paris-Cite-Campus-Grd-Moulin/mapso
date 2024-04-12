package model;

public class ArbreTrajectoire {
    private NoeudTrajectoire racine;

    public ArbreTrajectoire(NoeudTrajectoire racine) {
        this.racine = racine;
    }

    public NoeudTrajectoire getRacine() {
        return racine;
    }

    public void setRacine(NoeudTrajectoire racine) {
        this.racine = racine;
    }
}
