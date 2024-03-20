package src.main.java.trajectoire;

import java.util.ArrayList;
import java.util.List;
import src.main.java.core.Coordonnees;

public class NoeudTrajectoire {
    private Coordonnees coordonnees; // Les coordonnées de la case atteinte
    private TypeInteraction typeInteraction; // Le type d'interaction (Réflexion, Division, etc.)
    private List<NoeudTrajectoire> enfants; // Liste des enfants (0, 1 ou 2 selon le type d'interaction)

    public NoeudTrajectoire(Coordonnees coordonnees, TypeInteraction typeInteraction) {
        this.coordonnees = coordonnees;
        this.typeInteraction = typeInteraction;
        this.enfants = new ArrayList<>();
    }

    // Ajoute un enfant au noeud courant
    public void ajouterEnfant(NoeudTrajectoire enfant) {
        if (enfants.size() < 2) { // Assure qu'on ne dépasse pas deux enfants
            enfants.add(enfant);
        }
    }
    
    //méthode de parcours récursif de  l'ensemble de l'arbre de trajectoire, en gérant correctement les cas où le laser est divisé par un pion Horus
    public void parcourir() {
        // Affiche les informations du noeud courant, par exemple
        System.out.println("Visite noeud: " + this.coordonnees + ", Interaction: " + this.typeInteraction);
    
        // Gestion des bifurcations : si le noeud a exactement deux enfants, cela indique une bifurcation
        if (this.enfants.size() == 2 && this.typeInteraction == TypeInteraction.DIVISION_HORUS) {
            System.out.println("Bifurcation détectée à " + this.coordonnees);
            // Parcours récursif de chaque direction de la bifurcation
            for (NoeudTrajectoire enfant : enfants) {
                enfant.parcourir(); // Parcourir chaque enfant récursivement
            }
        } else {
            // Cas général ou pas de bifurcation : parcours récursif standard
            for (NoeudTrajectoire enfant : enfants) {
                enfant.parcourir();
            }
        }
    }
    
    // Méthode pour ajouter des enfants lors d'une interaction avec un pion Horus
    public void interactionHorus(Coordonnees coordonneesGauche, Coordonnees coordonneesDroite) {
        this.enfants.add(new NoeudTrajectoire(coordonneesGauche, TypeInteraction.DIVISION_HORUS));
        this.enfants.add(new NoeudTrajectoire(coordonneesDroite, TypeInteraction.DIVISION_HORUS));
    }



    /*Cette méthode miseAJourTrajectoireLaser serait appelée lors de la mise à jour du jeu, après chaque mouvement du laser, pour ajouter de nouveaux nœuds à l'arbre de trajectoire. 
    Elle vérifie le type de pion rencontré et, si c'est un pion Horus, utilise interactionHorus pour ajouter deux directions de bifurcation à l'arbre
    A AJOUTER DANS LA CLASSE QUI GERE LA LOGIQUE GLOBALE DU jeu
    
    public void miseAJourTrajectoireLaser(NoeudTrajectoire noeudActuel, Coordonnees nouvellePosition, TypePion typePion) {
    if (typePion == TypePion.HORUS) {
        // Supposons que calculerNouvellesCoordonneesGaucheDroite() retourne un tuple de nouvelles coordonnées
        // pour les directions gauche et droite après la bifurcation par Horus
        Pair<Coordonnees, Coordonnees> nouvellesCoordonnees = calculerNouvellesCoordonneesGaucheDroite(nouvellePosition);
        
        noeudActuel.interactionHorus(nouvellesCoordonnees.getLeft(), nouvellesCoordonnees.getRight());
    } else {
        // Pour les autres types de pions, la logique de mise à jour de l'arbre de trajectoire continue normalement
    }
}
 */
    // Getters et Setters...
}

public enum TypeInteraction {
    REFLEXION_SIMPLE,
    DIVISION_HORUS
}

public class ArbreTrajectoire {
    private NoeudTrajectoire racine;

    public ArbreTrajectoire(NoeudTrajectoire racine) {
        this.racine = racine;
    }

    // Méthodes pour manipuler l'arbre...
}
