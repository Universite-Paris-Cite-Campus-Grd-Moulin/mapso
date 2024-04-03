package model;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class Plateau {
    private Pion[][] grille;

    public Pion[][] getGrille() {
        return grille;
    }

    public Plateau() {
        this.grille = new Pion[8][10]; // Création d'une grille de 8x10
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.ROUGE);
                } else if (j == 9) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.JAUNE);
                } else if (j == 1 && i == 0 || j == 1 && i == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.JAUNE);
                } else if (j == 8 && i == 0 || j == 8 && i == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.ROUGE);
                } else {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.GRIS);
                }

                // Les pieces
                if (i == 0 && j == 4 || i == 0 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 0 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 3 || i == 7 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.JAUNE);
                }
                if (i == 7 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.JAUNE);
                }
                if (i == 0 && j == 7 || i == 3 && j == 7 || i == 4 && j == 0 || i == 5 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 2 || i == 4 && j == 2 || i == 3 && j == 9 || i == 2 && j == 3) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.SUD, Couleur.JAUNE);
                }
                if (i == 3 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.EST, Couleur.ROUGE);
                }
                if (i == 4 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.NORD, Couleur.JAUNE);
                }
                if (i == 3 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 4 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.JAUNE);
                }
                if (i == 4 && j == 9 || i == 3 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.JAUNE);
                }
                if (i == 3 && j == 0 || i == 4 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.ROUGE);
                }
                if (i == 1 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.ROUGE);
                }
                if (i == 6 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.JAUNE);
                }
            }
        }
        // initialiserPlateau();
    }

    // private void initialiserPlateau() {
    // // Configuration initiale pour un côté du plateau
    // initialiserCote(Couleur.ROUGE, 0, true);
    // // Configuration symétrique pour l'autre côté du plateau
    // initialiserCote(Couleur.JAUNE, grille.length - 1, false);
    // }

    // private void initialiserCote(Couleur couleur, int ligneDeBase, boolean
    // estRouge) {
    // int direction = estRouge ? 1 : -1; // Determine la direction de déplacement
    // basée sur la couleur

    // // Ajustez les positions selon la couleur et la direction
    // int lignePharaon = estRouge ? ligneDeBase : ligneDeBase - direction * 9;
    // int ligneDevantPharaon = lignePharaon + direction;

    // grille[lignePharaon][0] = new Pion(TypeDePion.PHARAON, estRouge ?
    // Direction.EST : Direction.OUEST, couleur);
    // grille[ligneDevantPharaon][0] = new Pion(TypeDePion.HORUS, estRouge ?
    // Direction.EST : Direction.OUEST, couleur);
    // grille[ligneDevantPharaon][1] = new Pion(TypeDePion.PYRAMIDE, estRouge ?
    // Direction.EST : Direction.OUEST, couleur);
    // grille[ligneDevantPharaon][2] = new Pion(TypeDePion.DJED, estRouge ?
    // Direction.EST : Direction.OUEST, couleur);
    // grille[ligneDevantPharaon][0] = new Pion(TypeDePion.OBELISQUE, estRouge ?
    // Direction.EST : Direction.OUEST, couleur);
    // }

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

    public Couleur initCouleur(int i, int j) {
        // i represente la ligne et i la colonne
        if (j == 0) {
            return Couleur.ROUGE;
        } else if (j == 9) {
            return Couleur.JAUNE;
        } else if (j == 1 && i == 0 || j == 1 && i == 7) {
            return Couleur.JAUNE;
        } else if (j == 8 && i == 0 || j == 8 && i == 7) {
            return Couleur.ROUGE;
        } else {
            return Couleur.GRIS;
        }
    }
}