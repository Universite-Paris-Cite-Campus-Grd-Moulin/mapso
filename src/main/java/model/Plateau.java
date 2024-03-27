package model;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class Plateau {
    private  Pion[][] grille;

    public Pion[][] getGrille(){
        return grille;
    }

    public Plateau() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                this.grille[i][j] = new Pion(TypeDePion.NONE,Direction.NORD,Couleur.GRIS);
            }
        }
        //initialiserPlateau();
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

    public void afficherPlateau() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afficherPlateau'");
    }

    public boolean shootLaser(Couleur currentPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shootLaser'");
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movePiece'");
    }

    public void deplacerPion(Pion pion, Direction direction) {
        // Logique pour déplacer le pion
    }

    public void tournerPion(Pion pion, Direction direction) {
        // Logique pour tourner le pion
    }

    public void empilerPion(Pion pion) {
        // Logique pour empiler le pion
    }

    public void separerDoubleObelisque(Pion pion) {
        // Logique pour séparer un Double Obélisque
    }

    public boolean estEmpile(Pion pion) {
        // Déterminer si le pion est empilé
        return false; // Exemple de valeur de retour
    }

    public boolean estSeparationDemandee(Pion pion) {
        // Déterminer si une séparation est demandée
        return false; // Exemple de valeur de retour
    }


    public Pion getPieceAt(int x, int y) {
        if (x < 0 || x >= grille.length || y < 0 || y >= grille[0].length) {
            return null; // Position hors du plateau
        }
        return grille[x][y];
    }
    

    public boolean estTourneeDemandee(Pion pion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'estTourneeDemandee'");
    }
}
