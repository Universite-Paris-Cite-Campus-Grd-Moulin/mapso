package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class Plateau {
    private Pion[][] grille;
    private int largeurDuPlateau = 10;
    private int hauteurDuPlateau = 8;
    private Couleur joueurActuel = Couleur.JAUNE;

    private Laser red;
    private Laser yellow;

    public Pion[][] getGrille() {
        return grille;
    }

    public Plateau(String type) {
        this.grille = new Pion[hauteurDuPlateau][largeurDuPlateau];
        switch (type) {
            case "Classic":
                initializeFromFile("ressources/initial_board_classic.txt");
                break;
            case "Dynastie":
                initializeFromFile("ressources/initial_board_dynastie.txt");
                break;
            case "Imhotep":
                initializeFromFile("ressources/initial_board_imhotep.txt");
                break;
            default:
                initializeFromFile("ressources/initial_board_classic.txt");
        }
    }

    private void initializeFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row >= 0 && row < hauteurDuPlateau && col >= 0 && col < largeurDuPlateau) {
                    TypeDePion type = parseType(parts[2]);
                    Couleur couleur = parseCouleur(parts[2]);
                    Direction direction = parseDirection(parts[3]);
                    grille[row][col] = new Pion(type, direction, couleur, row, col);
                } else {
                    System.err.println("Erreur: Coordonnées (" + row + ", " + col + ") hors limites.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TypeDePion parseType(String code) {
        switch (code.substring(0, 2)) {
            case "Ob":
                return TypeDePion.OBELISQUE;
            case "Ph":
                return TypeDePion.PHARAON;
            case "Ho":
                return TypeDePion.HORUS;
            case "Dj":
                return TypeDePion.DJED;
            case "Py":
                return TypeDePion.PYRAMIDE;
            default:
                throw new IllegalArgumentException("Type de pion inconnu: " + code);
        }
    }

    private Couleur parseCouleur(String code) {
        switch (code.charAt(2)) {
            case 'R':
                return Couleur.ROUGE;
            case 'J':
                return Couleur.JAUNE;
            default:
                throw new IllegalArgumentException("Couleur de pion inconnue: " + code);
        }
    }

    private Direction parseDirection(String code) {
        switch (code) {
            case "N":
                return Direction.NORD;
            case "E":
                return Direction.EST;
            case "S":
                return Direction.SUD;
            case "W":
                return Direction.OUEST;
            default:
                throw new IllegalArgumentException("Direction de pion inconnue: " + code);
        }
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
        movingPiece.setPosition(iArrivee, jArrivee);
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
        return false;
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
