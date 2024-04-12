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
        this.grille = new Pion[8][10]; // Création dune grille de 8x10
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

    public boolean deplacerPion(int iDepart, int jDepart, int iArrivee, int jArrivee) {
        // Vérifications pour ne pas sortir du tableau
        if (iDepart < 0 || iDepart >= grille.length || jDepart < 0 || jDepart >= grille[0].length ||
                iArrivee < 0 || iArrivee >= grille.length || jArrivee < 0 || jArrivee >= grille[0].length) {
            // Lune des positions est hors des limites du plateau
            return false;
        }

        // Vérification si la case darrivée est vide
        if (grille[iArrivee][jArrivee] != null) {
            // La case darrivée est déjà occupée
            return false;
        }

        // Déplacement du pion
        grille[iArrivee][jArrivee] = grille[iDepart][jDepart];
        grille[iDepart][jDepart] = null;
        return true;
    }

    public void afficherPlateau() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method afficherPlateau");
    }

    public boolean shootLaser(Couleur currentPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method shootLaser");
    }

    /*
     * public boolean movePiece(int startX, int startY, int endX, int endY) {
     * // Vérifie si les coordonnées de départ et darrivée sont valides
     * if (!isCoordonneeValide(startX, startY) || !isCoordonneeValide(endX, endY)) {
     * return false; // Coordonnées hors limites
     * }
     * 
     * Pion pieceADeplacer = grille[startY][startX];
     * 
     * // Vérifie si il y a bien un pion à déplacer et que la destination est valide
     * // selon les règles du jeu
     * if (pieceADeplacer != null && isDeplacementValide(pieceADeplacer, startX,
     * startY, endX, endY)) {
     * // Effectue le déplacement
     * grille[endY][endX] = pieceADeplacer;
     * grille[startY][startX] = null;
     * 
     * // Mise à jour de la position du pion (si les pions gardent trace de leur
     * // position)
     * pieceADeplacer.setPosition(endX, endY);
     * 
     * return true;
     * }
     * 
     * return false;
     * }
     */

    private boolean isCoordonneeValide(int x, int y) {
        return x >= 0 && x < grille[0].length && y >= 0 && y < grille.length;
    }

    /*
     * private boolean isDeplacementValide(Pion pion, int startX, int startY, int
     * endX, int endY) {
     * // Vérifie si le mouvement est hors limites
     * if (!estDansLimites(startX, startY) || !estDansLimites(endX, endY)) return
     * false;
     * 
     * // Vérifie si le mouvement est d'une seule case
     * if (!estMouvementDuneCase(startX, startY, endX, endY) && !pion.peutPivoter())
     * return false;
     * 
     * // Cas spécifique pour l'échange de position avec le Djed
     * if (pion.getType() == TypeDePion.DJED) {
     * return peutEchangerAvecDjed(pion, startX, startY, endX, endY);
     * }
     * 
     * // Cas spécifique pour l'empilement/dépilement des Obélisques
     * if (pion.getType() == TypeDePion.OBELISQUE || pion.getType() ==
     * TypeDePion.DOUBLE_OBELISQUE) {
     * return peutEmpilerOuDepilerObelisque(pion, startX, startY, endX, endY);
     * }
     * 
     * // Vérifie si la destination est occupée (sauf pour Djed)
     * if (grille[endY][endX] != null) return false;
     * 
     * // Vérification de la couleur des cases pour Rouge et Jaune
     * return estCaseValidePourCouleur(pion.getCouleur(), endX, endY);
     * }
     * 
     * private boolean estDansLimites(int x, int y) {
     * return x >= 0 && x < largeurDuPlateau && y >= 0 && y < hauteurDuPlateau;
     * }
     */

    private boolean estMouvementDuneCase(int startX, int startY, int endX, int endY) {
        return Math.abs(endX - startX) <= 1 && Math.abs(endY - startY) <= 1;
    }

    private boolean peutEchangerAvecDjed(Pion pion, int startX, int startY, int endX, int endY) {
        // Vérifie si le déplacement est d'une seule case
        if (!estMouvementDuneCase(startX, startY, endX, endY))
            return false;

        Pion pieceDestination = grille[endY][endX];

        // La destination doit être occupée par une pyramide ou un obélisque
        if (pieceDestination != null &&
                (pieceDestination.getType() == TypeDePion.PYRAMIDE
                        || pieceDestination.getType() == TypeDePion.OBELISQUE)) {
            // Vérifie que les pièces sont de couleurs différentes ou la même couleur (selon
            // les règles spécifiques de votre jeu)
            // Si les pièces Djed peuvent échanger avec n'importe quelle couleur, alors
            // cette vérification peut être omise ou ajustée
            return pion.getCouleur() == pieceDestination.getCouleur()
                    || pion.getCouleur() != pieceDestination.getCouleur();
        }

        return false; // Si la pièce de destination n'est pas une Pyramide ou un Obélisque, ou si la
                      // destination n'est pas adjacente
    }

    /*
     * private boolean peutEmpilerOuDepilerObelisque(Pion pion, int startX, int
     * startY, int endX, int endY) {
     * // Assurez-vous que la destination est adjacente et dans les limites du
     * plateau
     * if (!estMouvementDuneCase(startX, startY, endX, endY))
     * return false;
     * 
     * Pion pieceDestination = grille[endY][endX];
     * Pion pieceSource = grille[startY][startX];
     * 
     * // Cas d'empilement : la destination contient un Obélisque de même couleur
     * if (pieceDestination != null &&
     * pieceDestination.getType() == TypeDePion.OBELISQUE &&
     * pieceSource.getCouleur() == pieceDestination.getCouleur() &&
     * !pieceSource.estEmpile() && // Assurez-vous que l'Obélisque source n'est pas
     * déjà empilé
     * !pieceDestination.estEmpile()) { // et l'Obélisque destination n'est pas non
     * plus empilé
     * return true; // Empilement possible
     * }
     * 
     * // Cas de dépilement : si l'Obélisque source est empilé, il peut se déplacer
     * // seul à condition que la destination soit libre
     * if (pieceSource.estEmpile() && pieceDestination == null) {
     * return true; // Dépilement possible
     * }
     * 
     * // Déplacement normal : un Obélisque seul se déplace vers une case vide
     * return pieceDestination == null && !pieceSource.estEmpile();
     * }
     */

    private boolean estCaseValidePourCouleur(Couleur couleur, int x, int y) {
        // Supposons que tu as une matrice définissant les couleurs des cases du plateau
        Couleur couleurCase = obtenirCouleurCase(x, y);

        // Si la case n'a pas de couleur spécifique, toutes les pièces peuvent s'y
        // déplacer
        if (couleurCase == Couleur.GRIS) {
            return true;
        }

        // Sinon, la pièce peut se déplacer sur la case seulement si sa couleur
        // correspond
        return couleurCase == couleur;
    }

    private Couleur obtenirCouleurCase(int x, int y) {
        // Exemple basique où certaines cases sont spécifiquement colorées
        // Imaginons que la première ligne (y == 0) est colorée en Rouge
        // et la dernière ligne (y == 7) est colorée en Jaune
        if (y == 0) {
            return Couleur.ROUGE;
        } else if (y == 7) {
            return Couleur.JAUNE;
        }

        // Si tu as des zones spécifiques pour chaque joueur, tu peux étendre cette
        // logique
        // Exemple: les deux premières colonnes pour Rouge et les deux dernières pour
        // Jaune
        if (x < 2) {
            return Couleur.ROUGE;
        } else if (x >= 8) {
            return Couleur.JAUNE;
        }

        // Pour toutes les autres cases, elles sont considérées comme neutres (Gris)
        return Couleur.GRIS;
    }

    public void deplacerPion(Pion pion, Direction direction) {
        // Obtenez les coordonnées actuelles du pion
        int x = pion.getX();
        int y = pion.getY();

        // Calculez les nouvelles coordonnées en fonction de la direction
        int newX = x + direction.getDj();
        int newY = y + direction.getDi();

        // Assurez-vous que les nouvelles coordonnées sont dans les limites du plateau
        if (newX >= 0 && newX < grille[0].length && newY >= 0 && newY < grille.length && grille[newY][newX] == null) {
            // Déplacer le pion à la nouvelle position
            grille[y][x] = null;
            grille[newY][newX] = pion;
            pion.setPosition(newX, newY); // Mettre à jour les coordonnées du pion
        }
    }

    public void tournerPion(Pion pion, Direction nouvelleDirection) {
        // Mise à jour de la direction du pion
        pion.setDirection(nouvelleDirection);
    }

    public void empilerPion(Pion pion) {
        // Trouver une case adjacente avec un autre Obélisque de la même couleur
        for (Direction dir : Direction.values()) {
            int newX = pion.getX() + dir.getDj();
            int newY = pion.getY() + dir.getDi();

            // Vérifier les limites du plateau
            if (newX >= 0 && newX < grille[0].length && newY >= 0 && newY < grille.length) {
                Pion voisin = grille[newY][newX];
                if (voisin != null && voisin.getType() == TypeDePion.OBELISQUE
                        && voisin.getCouleur() == pion.getCouleur()) {
                    // Transformer les deux Obélisques en un Obélisque double
                    grille[pion.getY()][pion.getX()] = null; // Retirer l'Obélisque de la position actuelle
                    voisin.setType(TypeDePion.DOUBLE_OBELISQUE); // Transformer le voisin en Obélisque double
                    break;
                }
            }
        }
    }

    public void separerDoubleObelisque(Pion pion) {
        if (pion.getType() != TypeDePion.DOUBLE_OBELISQUE) {
            return; // Assurez-vous que la pièce est un Double Obélisque avant de séparer
        }

        // Trouver une case adjacente vide pour déplacer la partie supérieure de
        // l'Obélisque
        for (Direction dir : Direction.values()) {
            int newX = pion.getX() + dir.getDj();
            int newY = pion.getY() + dir.getDi();

            // Vérifier les limites du plateau et si la case est vide
            if (newX >= 0 && newX < grille[0].length && newY >= 0 && newY < grille.length
                    && grille[newY][newX] == null) {
                // Créer un nouvel Obélisque simple dans la case vide
                grille[newY][newX] = new Pion(TypeDePion.OBELISQUE, pion.getDirection(), pion.getCouleur(), newX, newY);
                // Réduire l'Obélisque double à un Obélisque simple
                pion.setType(TypeDePion.OBELISQUE);
                break;
            }
        }
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
        throw new UnsupportedOperationException("Unimplemented method estTourneeDemandee");
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
