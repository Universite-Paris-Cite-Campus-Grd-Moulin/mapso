package model;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class Plateau {
    private Pion[][] grille;
    private int largeurDuPlateau = 10;
    private int hauteurDuPlateau = 8;
    private Couleur joueurActuel = Couleur.JAUNE; // Le joueur jaune commence

    private Laser red;
    private Laser yellow;

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
                initializeClassic();
        }
    }

    private void initializeClassic() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                // Obelisque
                if (i == 0 && j == 4 || i == 0 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 3 || i == 7 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.JAUNE);
                }
                // Pharaon
                if (i == 0 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.JAUNE);
                }
                // Horus
                if (i == 3 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.EST, Couleur.ROUGE);
                }
                if (i == 4 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.NORD, Couleur.JAUNE);
                }
                // Djed
                if (i == 3 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 4 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.JAUNE);
                }
                // Pyramide
                if (i == 0 && j == 7 || i == 3 && j == 7 || i == 4 && j == 0 || i == 5 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 2 || i == 4 && j == 2 || i == 3 && j == 9 || i == 2 && j == 3) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.SUD, Couleur.JAUNE);
                }
                if (i == 4 && j == 9 || i == 3 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.JAUNE);
                }
                if (i == 1 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.ROUGE);
                }
                if (i == 3 && j == 0 || i == 4 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.ROUGE);
                }
                if (i == 6 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.JAUNE);
                }
            }
        }

        red = new Laser(Couleur.JAUNE);
        yellow = new Laser(Couleur.ROUGE);
    }

    private void initializeImhotep() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                // Obelisque
                if (i == 0 && j == 4 || i == 0 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 3 || i == 7 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.JAUNE);
                }
                // Pharaon
                if (i == 0 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.JAUNE);
                }
                // Horus
                if (i == 3 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.EST, Couleur.ROUGE);
                }
                if (i == 4 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.NORD, Couleur.JAUNE);
                }
                // Djed
                if (i == 3 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 4 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.JAUNE);
                }
                // Pyramide
                if (i == 0 && j == 7 || i == 3 && j == 7 || i == 4 && j == 0 || i == 5 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 2 || i == 4 && j == 2 || i == 3 && j == 9 || i == 2 && j == 3) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.SUD, Couleur.JAUNE);
                }
                if (i == 4 && j == 9 || i == 3 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.JAUNE);
                }
                if (i == 1 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.ROUGE);
                }
                if (i == 3 && j == 0 || i == 4 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.ROUGE);
                }
                if (i == 6 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.JAUNE);
                }
            }
        }
        red = new Laser(Couleur.ROUGE);
        yellow = new Laser(Couleur.JAUNE);
    }

    private void initializeDynastie() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                // Obelisque
                if (i == 0 && j == 4 || i == 0 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 3 || i == 7 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.OBELISQUE, Direction.NORD, Couleur.JAUNE);
                }
                // Pharaon
                if (i == 0 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.PHARAON, Direction.NORD, Couleur.JAUNE);
                }
                // Horus
                if (i == 3 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.EST, Couleur.ROUGE);
                }
                if (i == 4 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.HORUS, Direction.NORD, Couleur.JAUNE);
                }
                // Djed
                if (i == 3 && j == 5) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 4 && j == 4) {
                    this.grille[i][j] = new Pion(TypeDePion.DJED, Direction.NORD, Couleur.JAUNE);
                }
                // Pyramide
                if (i == 0 && j == 7 || i == 3 && j == 7 || i == 4 && j == 0 || i == 5 && j == 6) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.NORD, Couleur.ROUGE);
                }
                if (i == 7 && j == 2 || i == 4 && j == 2 || i == 3 && j == 9 || i == 2 && j == 3) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.SUD, Couleur.JAUNE);
                }
                if (i == 4 && j == 9 || i == 3 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.JAUNE);
                }
                if (i == 1 && j == 2) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.EST, Couleur.ROUGE);
                }
                if (i == 3 && j == 0 || i == 4 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.ROUGE);
                }
                if (i == 6 && j == 7) {
                    this.grille[i][j] = new Pion(TypeDePion.PYRAMIDE, Direction.OUEST, Couleur.JAUNE);
                }
            }
        }
        red = new Laser(Couleur.ROUGE);
        yellow = new Laser(Couleur.JAUNE);
    }

    public boolean deplacerPion(int iDepart, int jDepart, int iArrivee, int jArrivee) {
        Pion movingPiece = grille[iDepart][jDepart];
        if (iDepart < 0 || iDepart >= grille.length || jDepart < 0 || jDepart >= grille[0].length ||
                iArrivee < 0 || iArrivee >= grille.length || jArrivee < 0 || jArrivee >= grille[0].length) {
            System.out.println("Erreur: Déplacement hors des limites.");
            return false;
        }
        if (grille[iArrivee][jArrivee] != null) {
            System.out.println("Erreur: La case de destination est occupée.");
            return false;
        }
        grille[iArrivee][jArrivee] = grille[iDepart][jDepart];
        grille[iDepart][jDepart] = null;
        movingPiece.setPosition(iArrivee, jArrivee); // Met à jour la position du pion
        System.out
                .println("Pion déplacé de (" + iDepart + ", " + jDepart + ") à (" + iArrivee + ", " + jArrivee + ").");
        return true;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (joueurActuel != grille[startY][startX].getCouleur()) {
            System.out.println("Ce n'est pas le tour du joueur " + grille[startY][startX].getCouleur());
            return false;
        }

        if (startX == endX && startY == endY) {
            System.out.println("Erreur: La position de départ est la même que la position d'arrivée.");
            return false;
        }

        if (!isCoordonneeValide(startX, startY) || !isCoordonneeValide(endX, endY)) {
            System.out.println("Erreur: Coordonnée hors limite.");
            return false;
        }

        Pion movingPiece = grille[startY][startX];
        Pion destinationPiece = grille[endY][endX];

        if (movingPiece == null || movingPiece.getType() == TypeDePion.NONE) {
            System.out.println("Erreur: Aucun pion valide à la position de départ.");
            return false;
        }

        if (Math.abs(startX - endX) > 1 || Math.abs(startY - endY) > 1) {
            System.out.println("Erreur: La case de destination n'est pas adjacente.");
            return false;
        }

        Couleur couleurCaseDestination = initCouleur(endY, endX);
        boolean isDestinationValid = (movingPiece.getCouleur() == couleurCaseDestination
                || couleurCaseDestination == Couleur.GRIS);

        // Vérifier si l'échange entre Djed/Horus et Pyramide/Obélisque est possible
        // dans les deux sens
        if ((movingPiece.getType() == TypeDePion.DJED || movingPiece.getType() == TypeDePion.HORUS) &&
                destinationPiece != null &&
                (destinationPiece.getType() == TypeDePion.PYRAMIDE
                        || destinationPiece.getType() == TypeDePion.OBELISQUE)) {
            grille[startY][startX] = destinationPiece;
            grille[endY][endX] = movingPiece;
            movingPiece.setPosition(endX, endY);
            destinationPiece.setPosition(startX, startY);
            System.out.println("Échange réussi entre le Djed/Horus et la Pyramide/Obélisque.");
            togglePlayer();
            return true;
        } else if ((destinationPiece != null) &&
                (destinationPiece.getType() == TypeDePion.DJED || destinationPiece.getType() == TypeDePion.HORUS) &&
                (movingPiece.getType() == TypeDePion.PYRAMIDE || movingPiece.getType() == TypeDePion.OBELISQUE)) {
            grille[startY][startX] = destinationPiece;
            grille[endY][endX] = movingPiece;
            movingPiece.setPosition(endX, endY);
            destinationPiece.setPosition(startX, startY);
            System.out.println("Échange réussi entre la Pyramide/Obélisque et le Djed/Horus.");
            togglePlayer();
            return true;
        }

        // Empiler deux obélisques pour former un double obélisque
        if (movingPiece.getType() == TypeDePion.OBELISQUE && destinationPiece != null &&
                destinationPiece.getType() == TypeDePion.OBELISQUE
                && movingPiece.getCouleur() == destinationPiece.getCouleur()) {
            grille[endY][endX] = new Pion(TypeDePion.DOUBLE_OBELISQUE, Direction.NONE, movingPiece.getCouleur());
            grille[startY][startX] = null;
            System.out.println(
                    "Deux obélisques empilés pour former un double obélisque en (" + endX + ", " + endY + ").");
            togglePlayer();
            return true;
        }

        if (!isDestinationValid) {
            System.out.println("Erreur: Mouvement non autorisé à cause de la règle de couleur des cases.");
            return false;
        }

        if (destinationPiece == null || destinationPiece.getType() == TypeDePion.NONE) {
            grille[startY][startX] = null;
            grille[endY][endX] = movingPiece;
            movingPiece.setPosition(endX, endY);
            System.out
                    .println("Déplacement réussi de (" + startX + ", " + startY + ") à (" + endX + ", " + endY + ").");
            togglePlayer();
            return true;
        } else {
            System.out.println("Erreur: La case de destination n'est pas vide.");
            return false;
        }
    }

    private void togglePlayer() {
        joueurActuel = (joueurActuel == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        System.out.println("C'est maintenant le tour de " + (joueurActuel == Couleur.JAUNE ? "Jaune" : "Rouge"));
    }

    private boolean isCoordonneeValide(int x, int y) {
        boolean valide = x >= 0 && x < largeurDuPlateau && y >= 0 && y < hauteurDuPlateau;
        System.out.println("Coordonnée (" + x + ", " + y + ") est valide: " + valide);
        return valide;
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

    boolean estDansLimites(int x, int y) {
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
            System.out.println("Pion déplacé à la nouvelle position: (" + newX + ", " + newY + ")");
        }
    }

    public void tournerPion(Pion pion, Direction nouvelleDirection) {
        pion.setDirection(nouvelleDirection);
        System.out.println("Pion tourné vers la direction: " + nouvelleDirection);
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
                    System.out.println("Deux obélisques empilés en un double obélisque à la position (" + newX + ", "
                            + newY + ")");
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
                System.out.println("Double obélisque séparé en deux obélisques simples à la position (" + newX + ", "
                        + newY + ")");
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
            // System.out.println("Récupération du pion à la position (" + x + ", " + y +
            // ")");
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
                System.out.print(grille[i][j] == null ? "." : grille[i][j].getCouleur().toString().charAt(0));
            }
            System.out.println();
        }
    }

    public boolean shootLaser(Couleur currentPlayer) {
        System.out.println("Tir de laser par le joueur " + currentPlayer);
        return false; // Simplement un placeholder
    }

    public boolean depilerDoubleObelisque(Pion doubleObelisque, int x, int y) {
        if (doubleObelisque.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            grille[y][x] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur());
            System.out.println("Double obélisque dépilé en un obélisque simple à la position (" + x + ", " + y + ")");

            for (Direction dir : Direction.values()) {
                int newX = x + dir.getDi();
                int newY = y + dir.getDj();
                if (newX >= 0 && newX < largeurDuPlateau && newY >= 0 && newY < hauteurDuPlateau
                        && grille[newY][newX] == null) {
                    grille[newY][newX] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur());
                    System.out.println(
                            "Placement du deuxième obélisque simple à la position (" + newX + ", " + newY + ")");
                    return true;
                }
            }
        }
        return false; // Échec si aucun emplacement adjacent n'est disponible
    }

    public boolean depilerDoubleObelisque(Pion doubleObelisque, int startX, int startY, int endX, int endY) {
        if (doubleObelisque.getType() == TypeDePion.DOUBLE_OBELISQUE && grille[endY][endX] == null) {
            grille[endY][endX] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur());
            grille[startY][startX] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur());
            System.out.println("Double obélisque séparé en deux obélisques simples de (" + startX + ", " + startY
                    + ") à (" + endX + ", " + endY + ")");
            return true;
        }
        return false;
    }

    public void setPharaonTouche(Couleur couleur) {
        System.out.println("Le pharaon de la couleur " + couleur + " a été touché.");
    }

    public Laser getRed() {
        return red;
    }

    public void setRed(Laser red) {
        this.red = red;
    }

    public Laser getYellow() {
        return yellow;
    }

    public void setYellow(Laser yellow) {
        this.yellow = yellow;
    }
}
