package model;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class Plateau {
    private Pion[][] grille;
    private int largeurDuPlateau = 10;
    private int hauteurDuPlateau = 8;

    public boolean Classic = false;
    public boolean Dynastie = false;
    public boolean Imhotep = false;

    public Pion[][] getGrille() {
        return grille;
    }

    public Plateau(String type) {
        this.grille = new Pion[hauteurDuPlateau][largeurDuPlateau];
        switch (type) {
            case "Classic":
                initializeClassic();
                break;
            case "Dynastie":
                initializeDynastie();
                break;
            case "Imhotep":
                initializeImhotep();
                break;
            default:
                initializeClassic(); // default to Classic if unspecified
        }
    }

    private void initializeClassic() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                // Configuration de base des cellules
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
    }

    private void initializeDynastie() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                // Set empty spaces and boundary conditions
                if (j == 0) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.ROUGE);
                } else if (j == 9) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.JAUNE);
                } else if (j == 1 && (i == 0 || i == 7)) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.JAUNE);
                } else if (j == 8 && (i == 0 || i == 7)) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.ROUGE);
                } else {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.GRIS);
                }

                // Position strategic pieces differently for the Dynastie setup
                // Double Obelisques and special guards for Pharaohs to emphasize the dynastic
                // power
                if ((i == 0 && j == 2) || (i == 0 && j == 8)) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
                }
                if ((i == 7 && j == 2) || (i == 7 && j == 8)) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.JAUNE);
                }
                if (i == 0 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.JAUNE);
                }
                if ((i == 1 && j == 1) || (i == 6 && j == 8)) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.EST,
                            (i == 1) ? Couleur.ROUGE : Couleur.JAUNE);
                }
                if ((i == 1 && j == 9) || (i == 6 && j == 0)) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.OUEST,
                            (i == 1) ? Couleur.ROUGE : Couleur.JAUNE);
                }
                // A dynamic central setup with Djed and Pyramids to control the middle of the
                // board
                if ((i == 4 && j == 5) || (i == 3 && j == 4)) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD,
                            (i == 4) ? Couleur.JAUNE : Couleur.ROUGE);
                }
                if ((i == 4 && j == 4) || (i == 3 && j == 5)) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.NORD,
                            (i == 4) ? Couleur.JAUNE : Couleur.ROUGE);
                }
            }
        }
    }

    private void initializeImhotep() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                // Set empty spaces and boundary conditions
                if (j == 0) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.ROUGE);
                } else if (j == 9) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.JAUNE);
                } else if (j == 1 && (i == 0 || i == 7)) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.JAUNE);
                } else if (j == 8 && (i == 0 || i == 7)) {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.ROUGE);
                } else {
                    this.grille[i][j] = new Pion(TypeDePion.NONE, Direction.NORD, Couleur.GRIS);
                }

                // Position strategic pieces differently for the Imhotep setup
                if ((i == 0 && j == 3) || (i == 0 && j == 7)) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && (j == 3 || j == 7)) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.JAUNE);
                }
                if (i == 1 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.SUD, Couleur.ROUGE);
                }
                if (i == 6 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.SUD, Couleur.JAUNE);
                }
                if ((i == 0 && j == 5) || (i == 7 && j == 4)) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD,
                            (i == 0) ? Couleur.ROUGE : Couleur.JAUNE);
                }
                if ((i == 2 && j == 2) || (i == 5 && j == 8)) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.EST,
                            (i == 2) ? Couleur.ROUGE : Couleur.JAUNE);
                }
            }
        }
    }

    public boolean deplacerPion(int iDepart, int jDepart, int iArrivee, int jArrivee) {
        if (iDepart < 0 || iDepart >= grille.length || jDepart < 0 || jDepart >= grille[0].length ||
                iArrivee < 0 || iArrivee >= grille.length || jArrivee < 0 || jArrivee >= grille[0].length) {
            return false;
        }
        if (grille[iArrivee][jArrivee] != null) {
            return false;
        }
        grille[iArrivee][jArrivee] = grille[iDepart][jDepart];
        grille[iDepart][jDepart] = null;
        return true;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (!isValidPosition(startX, startY) || !isValidPosition(endX, endY)) {
            System.out.println("Start or end position is out of bounds.");
            return false;
        }
        Pion movingPiece = grille[startY][startX];
        Pion destinationPiece = grille[endY][endX];

        if (movingPiece == null) {
            System.out.println("No piece at the starting position.");
            return false;
        }
        if (destinationPiece != null) {
            // Si la pièce de destination est un ennemi, permettre la capture
            if (destinationPiece.getCouleur() != movingPiece.getCouleur()) {
                System.out.println("Enemy piece captured.");
                grille[endY][endX] = movingPiece; // Capture the piece
                grille[startY][startX] = null;
                movingPiece.setPosition(endX, endY);
                return true;
            } else {
                System.out.println("End position is occupied by a friendly piece.");
                return false;
            }
        }

        // Si la case de destination est vide, simplement déplacer la pièce
        grille[endY][endX] = movingPiece;
        grille[startY][startX] = null;
        movingPiece.setPosition(endX, endY);
        System.out.println("Piece moved successfully.");
        return true;
    }

    private boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < largeurDuPlateau && y >= 0 && y < hauteurDuPlateau);
    }

    private boolean isCoordonneeValide(int x, int y) {
        return x >= 0 && x < largeurDuPlateau && y >= 0 && y < hauteurDuPlateau;
    }

    private boolean isDeplacementValide(Pion pion, int startX, int startY, int endX, int endY) {
        if (!estDansLimites(startX, startY) || !estDansLimites(endX, endY))
            return false;
        if (!estMouvementDuneCase(startX, startY, endX, endY) && !pion.peutPivoter())
            return false;
        if (pion.getType() == TypeDePion.DJED) {
            return peutEchangerAvecDjed(pion, startX, startY, endX, endY);
        }
        if (pion.getType() == TypeDePion.OBELISQUE || pion.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            return peutEmpilerOuDepilerObelisque(pion, startX, startY, endX, endY);
        }
        if (grille[endY][endX] != null)
            return false;
        return estCaseValidePourCouleur(pion.getCouleur(), endX, endY);
    }

    private boolean estDansLimites(int x, int y) {
        return x >= 0 && x < largeurDuPlateau && y >= 0 && y < hauteurDuPlateau;
    }

    private boolean estMouvementDuneCase(int startX, int startY, int endX, int endY) {
        return Math.abs(endX - startX) <= 1 && Math.abs(endY - startY) <= 1;
    }

    private boolean peutEchangerAvecDjed(Pion pion, int startX, int startY, int endX, int endY) {
        if (!estMouvementDuneCase(startX, startY, endX, endY))
            return false;
        Pion pieceDestination = grille[endY][endX];
        if (pieceDestination != null &&
                (pieceDestination.getType() == TypeDePion.PYRAMIDE
                        || pieceDestination.getType() == TypeDePion.OBELISQUE)) {
            return pion.getCouleur() == pieceDestination.getCouleur()
                    || pion.getCouleur() != pieceDestination.getCouleur();
        }

        return false;
    }

    private boolean peutEmpilerOuDepilerObelisque(Pion pion, int startX, int startY, int endX, int endY) {
        if (!estMouvementDuneCase(startX, startY, endX, endY))
            return false;
        Pion pieceDestination = grille[endY][endX];
        Pion pieceSource = grille[startY][startX];
        if (pieceDestination != null &&
                pieceDestination.getType() == TypeDePion.OBELISQUE &&
                pieceSource.getCouleur() == pieceDestination.getCouleur() &&
                !pieceSource.estEmpile() && // Assurez-vous que l'Obélisque source n'est pas déjà empilé
                !pieceDestination.estEmpile()) { // et l'Obélisque destination n'est pas non plus empilé
            return true;
        }
        if (pieceSource.estEmpile() && pieceDestination == null) {
            return true; // Dépilement possible
        }
        return pieceDestination == null && !pieceSource.estEmpile();
    }

    private boolean estCaseValidePourCouleur(Couleur couleur, int x, int y) {
        Couleur couleurCase = obtenirCouleurCase(x, y);
        if (couleurCase == Couleur.GRIS) {
            return true;
        }

        return couleurCase == couleur;
    }

    private Couleur obtenirCouleurCase(int x, int y) {
        if (y == 0) {
            return Couleur.ROUGE;
        } else if (y == 7) {
            return Couleur.JAUNE;
        }
        if (x < 2) {
            return Couleur.ROUGE;
        } else if (x >= 8) {
            return Couleur.JAUNE;
        }
        return Couleur.GRIS;
    }

    public void deplacerPion(Pion pion, Direction direction) {
        int x = pion.getX();
        int y = pion.getY();
        int newX = x + direction.getDj();
        int newY = y + direction.getDi();
        if (newX >= 0 && newX < grille[0].length && newY >= 0 && newY < grille.length && grille[newY][newX] == null) {
            grille[y][x] = null;
            grille[newY][newX] = pion;
            pion.setPosition(newX, newY);
        }
    }

    public void tournerPion(Pion pion, Direction nouvelleDirection) {
        pion.setDirection(nouvelleDirection);
    }

    public void empilerPion(Pion pion) {
        for (Direction dir : Direction.values()) {
            int newX = pion.getX() + dir.getDj();
            int newY = pion.getY() + dir.getDi();
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
            return;
        }
        for (Direction dir : Direction.values()) {
            int newX = pion.getX() + dir.getDj();
            int newY = pion.getY() + dir.getDi();
            if (newX >= 0 && newX < grille[0].length && newY >= 0 && newY < grille.length
                    && grille[newY][newX] == null) {
                grille[newY][newX] = new Pion(TypeDePion.OBELISQUE, pion.getDirection(), pion.getCouleur(), newX, newY);
                pion.setType(TypeDePion.OBELISQUE);
                break;
            }
        }
    }

    public boolean estEmpile(Pion pion) {
        return pion.getType() == TypeDePion.DOUBLE_OBELISQUE;
    }

    public boolean estSeparationDemandee(Pion pion) {
        return pion.isMarkedForSplitting();
    }

    public Pion getPieceAt(int x, int y) {
        if (x >= 0 && x < largeurDuPlateau && y >= 0 && y < hauteurDuPlateau) {
            return grille[y][x];
        }
        return null;
    }

    public boolean estTourneeDemandee(Pion pion) {
        return pion.isRotationRequested();
    }

    public Couleur initCouleur(int i, int j) {
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

    public void afficherPlateau() {
        for (int i = 0; i < hauteurDuPlateau; i++) {
            for (int j = 0; j < largeurDuPlateau; j++) {
                System.out.print(grille[i][j].getCouleur().toString().charAt(0) + " ");
            }
            System.out.println();
        }
    }

    public boolean shootLaser(Couleur currentPlayer) {
        return false; // Simplement un placeholder
    }

}
