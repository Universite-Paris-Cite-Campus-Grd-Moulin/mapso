package main.java.model;

import java.util.ArrayList;
import java.util.List;

import main.java.view.GameView;
import main.java.model.enums.Couleur;
import main.java.model.enums.Direction;
import main.java.model.enums.TypeDePion;
import main.java.model.Laser;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Plateau implements Observable{
    
    private List<Observer> observers = new ArrayList<>();
    private Pion[][] grille;
    private int largeurDuPlateau = 10;
    private int hauteurDuPlateau = 10;
    private boolean isGameOver = false; // Ajoutez cette ligne pour suivre l'état du jeu
    public boolean Classic = false;
    public boolean Dynastie = false;
    public boolean Imhotep = false;
    private List<Laser> lasers;
    private Couleur joueurActuel = Couleur.JAUNE; // Le joueur jaune commence
    public Pion[][] getGrille() {
        return grille;
    }

    public Plateau(String type) {
        this.grille = new Pion[hauteurDuPlateau][largeurDuPlateau];
        lasers = new ArrayList<>();
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

    public void initializeClassic() {
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

        lasers.add(new Laser(Couleur.JAUNE));
        lasers.add(new Laser(Couleur.ROUGE));
    }

    private void initializeImhotep() {
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
        lasers.add(new Laser(Couleur.ROUGE));
        lasers.add(new Laser(Couleur.JAUNE));
    }

    private void initializeDynastie() {
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
        lasers.add(new Laser(Couleur.ROUGE));
        lasers.add(new Laser(Couleur.JAUNE));
    }

    public boolean deplacerPion(int iDepart, int jDepart, int iArrivee, int jArrivee) {
        Pion movingPiece = grille[iDepart][jDepart];
        if (iDepart < 0 || iDepart >= grille.length || jDepart < 0 || jDepart >= grille[0].length ||
                iArrivee < 0 || iArrivee >= grille.length || jArrivee < 0 || jArrivee >= grille[0].length) {
            mettreAJourLesCheminsDesLasers();
            return false;
        }
        if (grille[iArrivee][jArrivee] != null) {
            mettreAJourLesCheminsDesLasers();
            return false;
        }
        grille[iArrivee][jArrivee] = grille[iDepart][jDepart];
        grille[iDepart][jDepart] = null;
        movingPiece.setPosition(iArrivee, jArrivee); // Met à jour la position du pion

        mettreAJourLesCheminsDesLasers();
        return true;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        if (joueurActuel != grille[startY][startX].getCouleur()) {
            System.out.println("Ce n'est pas le tour du joueur " + grille[startY][startX].getCouleur());
            return false;
        }


        if (startX == endX && startY == endY) {
            System.out.println("Erreur: La position de départ est la même que la position d'arrivée.");
            mettreAJourLesCheminsDesLasers();
            return false;
        }

        if (!isCoordonneeValide(startX, startY) || !isCoordonneeValide(endX, endY)) {
            System.out.println("Erreur: Coordonnée hors limite.");
            mettreAJourLesCheminsDesLasers();
            return false;
        }

        Pion movingPiece = grille[startY][startX];
        Pion destinationPiece = grille[endY][endX];

        if (movingPiece == null || movingPiece.getType() == TypeDePion.NONE) {
            System.out.println("Erreur: Aucun pion valide à la position de départ.");
            mettreAJourLesCheminsDesLasers();
            return false;
        }

        if (Math.abs(startX - endX) > 1 || Math.abs(startY - endY) > 1) {
            System.out.println("Erreur: La case de destination n'est pas adjacente.");
            mettreAJourLesCheminsDesLasers();
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
            mettreAJourLesCheminsDesLasers();
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
            mettreAJourLesCheminsDesLasers();
            return true;
        }

        // Empiler deux obélisques pour former un double obélisque
        if (movingPiece.getType() == TypeDePion.OBELISQUE && destinationPiece != null &&
                destinationPiece.getType() == TypeDePion.OBELISQUE
                && movingPiece.getCouleur() == destinationPiece.getCouleur()) {
            grille[endY][endX] = new Pion(TypeDePion.DOUBLE_OBELISQUE, Direction.NONE, movingPiece.getCouleur());
            grille[startY][startX] = null;
            System.out.println("Deux obélisques empilés pour former un double obélisque en (" + endX + ", " + endY + ").");
            togglePlayer();
            mettreAJourLesCheminsDesLasers();
            return true;
        }

        if (!isDestinationValid) {
            System.out.println("Erreur: Mouvement non autorisé à cause de la règle de couleur des cases.");
            mettreAJourLesCheminsDesLasers();
            return false;
        }

        if (destinationPiece == null || destinationPiece.getType() == TypeDePion.NONE) {
            grille[startY][startX] = null;
            grille[endY][endX] = movingPiece;
            movingPiece.setPosition(endX, endY);
            System.out.println("Déplacement réussi de (" + startX + ", " + startY + ") à (" + endX + ", " + endY + ").");
            togglePlayer();
            mettreAJourLesCheminsDesLasers();
            return true;
        } else {
            System.out.println("Erreur: La case de destination n'est pas vide.");
            mettreAJourLesCheminsDesLasers();
            return false;
        }
        
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

    public boolean estDansLimites(int x, int y) {
        return x >= 0 && x < largeurDuPlateau && y >= 0 && y < hauteurDuPlateau;
    }

    private boolean estMouvementDuneCase(int startX, int startY, int endX, int endY) {
        return Math.abs(endX - startX) <= 1 && Math.abs(endY - startY) <= 1;
    }

    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= 10 || startY < 0 || startY >= 10 ||
            endX < 0 || endX >= 10 || endY < 0 || endY >= 10) {
            return false;
        }
        
        if (this.getPieceAt(endX, endY) == null) {
            return true;
        } else {
            return false;
        }
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
        mettreAJourLesCheminsDesLasers();
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
            mettreAJourLesCheminsDesLasers();
            return true; // Dépilement possible
        }
        mettreAJourLesCheminsDesLasers();
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
        mettreAJourLesCheminsDesLasers();
    }

    public void tournerPion(Pion pion, Direction nouvelleDirection) {
        if (pion.peutPivoter()) {
            pion.setDirection(nouvelleDirection);
            mettreAJourLesCheminsDesLasers();
            notifyObservers(); // Notifie les observateurs après la rotation
            System.out.println("Pion tourné vers " + nouvelleDirection);
        }
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
                    notifyObservers(); // Notification après empilage
                    mettreAJourLesCheminsDesLasers();
                    break;
                }
            }
        }
        mettreAJourLesCheminsDesLasers();
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
        mettreAJourLesCheminsDesLasers();
    }

    public boolean estEmpile(Pion pion) {
        return pion.getType() == TypeDePion.DOUBLE_OBELISQUE;
    }

    public boolean estSeparationDemandee(Pion pion) {
        return pion.isMarkedForSplitting();
    }

    public Pion getPieceAt(int i, int j) {
        if (i >= 0 && i < largeurDuPlateau && j >= 0 && j < hauteurDuPlateau) {
            return grille[j][i];
        }
        return null;
    }

    public Pion getPieceAt2(int i, int j) {
        if (i >= 0 && i < largeurDuPlateau && j >= 0 && j < hauteurDuPlateau) {
            return grille[i][j];
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

    public boolean depilerDoubleObelisque(Pion doubleObelisque, int x, int y) {
        if (doubleObelisque.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            grille[y][x] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur()); // Place le
                                                                                                         // premier
                                                                                                         // obélisque

            // Recherche d'un emplacement pour le second obélisque
            for (Direction dir : Direction.values()) {
                int newX = x + dir.getDi();
                int newY = y + dir.getDj();
                if (newX >= 0 && newX < largeurDuPlateau && newY >= 0 && newY < hauteurDuPlateau
                        && grille[newY][newX] == null) {
                    grille[newY][newX] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur());
                    mettreAJourLesCheminsDesLasers();
                    return true;
                }
            }
            mettreAJourLesCheminsDesLasers();
        }
        mettreAJourLesCheminsDesLasers();
        return false; // Échec si aucun emplacement adjacent n'est disponible
    }

    public boolean depilerDoubleObelisque(Pion doubleObelisque, int startX, int startY, int endX, int endY) {
        // Ensure the piece is a double obelisk and the end position is valid and empty
        if (doubleObelisque.getType() == TypeDePion.DOUBLE_OBELISQUE && grille[endY][endX] == null) {
            // Place a new single obelisk at the destination
            grille[endY][endX] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur());
            // Change the original double obelisk to a single obelisk
            grille[startY][startX] = new Pion(TypeDePion.OBELISQUE, Direction.NONE, doubleObelisque.getCouleur());
            // //mettreAJourLesCheminsDesLasers();();
            return true;
        }
        mettreAJourLesCheminsDesLasers();
        return false;
    }

    public void mettreAJourLesCheminsDesLasers() {
        System.out.println("Mise à jour des chemins des lasers...");
        for (Laser laser : lasers) {
            laser.reinitialiserChemin();
            laser.propagerLaser(this);

            // Vérifier si un Pharaon est touché
            List<NoeudTrajectoire> cheminLaser = laser.obtenirCheminLaser();
            for (NoeudTrajectoire noeud : cheminLaser) {
                Pion pion = getPieceAt(noeud.getPositionI(), noeud.getPositionJ());
                if (pion != null && pion.getType() == TypeDePion.PHARAON) {
                    // Pharaon touché, fin du jeu
                    setPharaonTouche(pion.getCouleur());
                    return; // Sortir de la boucle car le jeu est terminé
                }
            }
        }
        System.out.println("Mise à jour terminée.");
        notifyObservers();
    }

    public void setPharaonTouche(Couleur couleur) {
        if (!isGameOver) { // Vérifiez si le jeu n'est pas déjà terminé
            System.out.println("Le Pharaon " + couleur + " a été touché!");
            isGameOver = true; // Marquez le jeu comme terminé

            // Délai de 1 seconde avant d'afficher le message
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((Timer)e.getSource()).stop(); // Arrêter le timer
                    System.out.println("Appel de notifyObserversGameOver avec couleur: " + couleur);
                    notifyObserversGameOver(couleur);
                }
            });
            timer.setRepeats(false); // Ne répète pas le timer
            timer.start(); // Démarre le timer
        } else {
            System.out.println("Jeu déjà terminé, pas d'action nécessaire.");
        }
    }

    private void notifyObserversGameOver(Couleur couleur) {
        System.out.println("notifyObserversGameOver appelé avec couleur: " + couleur);
        for (Observer observer : observers) {
            if (observer instanceof GameView) {
                System.out.println("Observer est une instance de GameView, appel de showGameOver");
                ((GameView) observer).showGameOver(couleur);
            } else {
                System.out.println("Observer n'est pas une instance de GameView");
            }
        }
    }



    public Laser getRed() {
        return lasers.get(0);
    }

    public void setRed(Laser red) {
        
    }

    public Laser getYellow() {
        return lasers.get(1);
    }

    public void setYellow(Laser yellow) {
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void togglePlayer() {
        joueurActuel = (joueurActuel == Couleur.JAUNE) ? Couleur.ROUGE : Couleur.JAUNE;
        System.out.println("C'est maintenant le tour de " + (joueurActuel == Couleur.JAUNE ? "Jaune" : "Rouge"));
    }

    public int getHauteurDuPlateau() {
        return this.hauteurDuPlateau;
    }

    public int getLargeurDuPlateau() {
        return this.largeurDuPlateau;
    }

    public int getDefaultTimeLeft(String gameType) {
        switch (gameType) {
            case "Classic":
                return 30;
            case "Imhotep":
                return 20;
            case "Dynastie":
                return 15;
            default:
                return 30;
        }
    }
}
