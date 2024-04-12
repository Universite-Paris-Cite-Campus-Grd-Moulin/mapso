package model;

import model.enums.Direction;
import model.enums.TypeInteraction;

public class Laser {
    private ArbreTrajectoire arbreTrajectoire;

    public Laser(Direction directionInitiale, int positionI, int positionJ) {
        this.arbreTrajectoire = new ArbreTrajectoire(
                new NoeudTrajectoire(directionInitiale, positionI, positionJ, TypeInteraction.NONE));
    }

    public void mettreAJourTrajectoire(Direction nouvelleDirection, int nouvellePositionI, int nouvellePositionJ,
            TypeInteraction typeInteraction) {
        NoeudTrajectoire nouveauNoeud = new NoeudTrajectoire(nouvelleDirection, nouvellePositionI, nouvellePositionJ,
                typeInteraction);
        arbreTrajectoire.getRacine().ajouterSuccesseur(nouveauNoeud); // Assurez-vous que cela est logique selon votre
                                                                      // structure de jeu
    }

    public ArbreTrajectoire getArbreTrajectoire() {
        return arbreTrajectoire;
    }

    public void setArbreTrajectoire(ArbreTrajectoire arbreTrajectoire) {
        this.arbreTrajectoire = arbreTrajectoire;
    }
}
