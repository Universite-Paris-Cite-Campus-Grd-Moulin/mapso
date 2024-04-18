package model;

import model.enums.Direction;
import model.enums.TypeInteraction;

public class Laser {
    private NoeudTrajectoire NoeudTrajectoire;
    private NoeudTrajectoire racine;

    public Laser(Direction directionInitiale, int positionI, int positionJ, NoeudTrajectoire racine) {
        this.NoeudTrajectoire = new NoeudTrajectoire(directionInitiale, positionI, positionJ, TypeInteraction.NONE);
        this.racine = racine;
        
    }

    // Méthode pour mettre à jour l'arbre de trajectoire en fonction des actions du jeu
    public void mettreAJourTrajectoire(Direction nouvelleDirection, int nouvellePositionI, int nouvellePositionJ, TypeInteraction typeInteraction, NoeudTrajectoire racine) {
        NoeudTrajectoire nouveauNoeud = new NoeudTrajectoire(nouvelleDirection, nouvellePositionI, nouvellePositionJ, typeInteraction);
        // Ici, vous devez déterminer le noeud parent correct à partir duquel ajouter le nouveau noeud
        // ...
    }

    public NoeudTrajectoire getNoeudTrajectoire() {
        return NoeudTrajectoire;
    }

    public void setNoeudTrajectoire(NoeudTrajectoire NoeudTrajectoire, NoeudTrajectoire racine) {
        this.NoeudTrajectoire = NoeudTrajectoire;
        this.racine = racine;
    }

    // Méthodes supplémentaires pour gérer le laser...
}
