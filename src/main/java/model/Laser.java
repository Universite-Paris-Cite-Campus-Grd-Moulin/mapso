package model;

import model.enums.Direction;
import model.enums.TypeInteraction;

public class Laser {
    private ArbreTrajectoire arbreTrajectoire;

    public Laser(Direction directionInitiale, int positionI, int positionJ) {
        this.arbreTrajectoire = new ArbreTrajectoire(
                new NoeudTrajectoire(directionInitiale, positionI, positionJ, TypeInteraction.NONE));
        // Initialisation des autres attributs...
    }

    // Méthode pour mettre à jour l'arbre de trajectoire en fonction des actions du
    // jeu
    public void mettreAJourTrajectoire(Direction nouvelleDirection, int nouvellePositionI, int nouvellePositionJ,
            TypeInteraction typeInteraction) {
        NoeudTrajectoire nouveauNoeud = new NoeudTrajectoire(nouvelleDirection, nouvellePositionI, nouvellePositionJ,
                typeInteraction);
        // Ici, vous devez déterminer le noeud parent correct à partir duquel ajouter le
        // nouveau noeud
        // ...
    }

    public ArbreTrajectoire getArbreTrajectoire() {
        return arbreTrajectoire;
    }

    public void setArbreTrajectoire(ArbreTrajectoire arbreTrajectoire) {
        this.arbreTrajectoire = arbreTrajectoire;
    }

    // Méthodes supplémentaires pour gérer le laser...
}
