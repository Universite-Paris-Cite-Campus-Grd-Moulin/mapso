package model;

import enums.Couleur;
import enums.Direction;
import enums.TypeDePion;

public class Plateau {
    
    private final Pion[][] grille;

    public Plateau() {
        this.grille = new Pion[10][8];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        // Configuration initiale pour un côté du plateau
        initialiserCote(Couleur.ROUGE, 0, true);
        // Configuration symétrique pour l'autre côté du plateau
        initialiserCote(Couleur.JAUNE, grille.length - 1, false);
    }

    private void initialiserCote(Couleur couleur, int ligneDeBase, boolean estRouge) {
        int direction = estRouge ? 1 : -1; // Determine la direction de déplacement basée sur la couleur
        
        // Ajustez les positions selon la couleur et la direction
        int lignePharaon = estRouge ? ligneDeBase : ligneDeBase - direction * 9;
        int ligneDevantPharaon = lignePharaon + direction;
        
        grille[lignePharaon][0] = new Pion(TypeDePion.PHARAON, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][0] = new Pion(TypeDePion.HORUS, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][1] = new Pion(TypeDePion.PYRAMIDE, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][2] = new Pion(TypeDePion.DJED, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][0] = new Pion(TypeDePion.OBELISQUE, estRouge ? Direction.EST : Direction.OUEST, couleur);
    }

    public boolean deplacerPion(int iDepart, int jDepart, int iArrivee, int jArrivee) {
        // Vérifications pour ne pas sortir du tableau
        if (iDepart < 0 || iDepart >= grille.length || jDepart < 0 || jDepart >= grille[0].length ||
            iArrivee < 0 || iArrivee >= grille.length || jArrivee < 0 || jArrivee >= grille[0].length) {
            // L'une des positions est hors des limites du plateau
            return false;
        }

        // Vérification si la case d'arrivée est vide
        if (grille[iArrivee][jArrivee] != null) {
            // La case d'arrivée est déjà occupée
            return false;
        }

        // Déplacement du pion
        grille[iArrivee][jArrivee] = grille[iDepart][jDepart];
        grille[iDepart][jDepart] = null;
        return true;
    }
}
