package khet.model;

import java.util.Scanner;

import khet.enums.Couleur;

// Modélise le plateau de jeu, intégrant la gestion des interactions utilisateur pour jouer
public class Board {
    private Piece[][] grille; // Utilisation de Piece au lieu de Pion pour une cohérence

    public Board() {
        grille = new Piece[10][8]; // Initialisation avec la taille souhaitée
        initialiserPlateau(); // Assurez-vous d'implémenter cette méthode pour configurer le plateau initial
    }

    // Méthode pour initialiser le plateau avec les pièces de départ
    private void initialiserPlateau() {
        // Initialisation des pièces sur le plateau
        // Par exemple : grille[0][0] = new Pharaon(0, 0, Couleur.JAUNE);
    }

    public void jouer() {
        Scanner scanner = new Scanner(System.in);
        boolean finDuJeu = false;

        while (!finDuJeu) {
            afficherPlateau();

            System.out.println("Sélectionnez la pièce à déplacer (format : x y) : ");
            int xDepart = scanner.nextInt();
            int yDepart = scanner.nextInt();
            Piece pieceSelectionnee = getPion(xDepart, yDepart);

            if (pieceSelectionnee != null && pieceSelectionnee.getCouleur() == Couleur.BLEU) {
                System.out.println("Déplacez la pièce (format : x y) : ");
                int xArrivee = scanner.nextInt();
                int yArrivee = scanner.nextInt();

                if (mouvementLegal(xDepart, yDepart, xArrivee, yArrivee)) {
                    deplacerPiece(xDepart, yDepart, xArrivee, yArrivee);
                } else {
                    System.out.println("Mouvement non autorisé !");
                }
            } else {
                System.out.println("Sélectionnez une pièce valide !");
            }

            // finDuJeu = conditionDeFinDeJeu(); // Implémentez selon les règles spécifiques de votre jeu
        }

        scanner.close();
    }

    // Méthode pour afficher l'état actuel du plateau
    public void afficherPlateau() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (grille[i][j] != null) {
                    System.out.print(grille[i][j].getType().name().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    // Méthode pour récupérer une pièce à une position spécifique
    public Piece getPion(int x, int y) {
        return grille[x][y];
    }

    // Méthode pour déplacer une pièce
    public void deplacerPiece(int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Piece piece = grille[xDepart][yDepart];
        grille[xDepart][yDepart] = null;
        grille[xArrivee][yArrivee] = piece;
        // piece.move(xArrivee, yArrivee); // Assurez-vous que la méthode move soit implémentée dans Piece
    }

    private boolean mouvementLegal(int xDepart, int yDepart, int xArrivee, int yArrivee) {
        // Implémentez la logique de validation du mouvement ici
        return true; // Simplification pour l'exemple
    }

    // Ajoutez d'autres méthodes utiles pour la gestion du jeu
}
