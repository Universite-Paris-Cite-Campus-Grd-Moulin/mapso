package src.main.java.piece;

import java.util.Scanner;

import src.main.java.enums.Couleur;

public class Plateau {
    private Pion[][] grille;

    public Plateau() {
        //cree une grille de pion par exemple de taille 10*8
        grille = new Pion[10][8];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        // Initialiser la grille avec les pièces de départ
        // Exemple : Pharaon Jaune en (0, 0)
        grille[0][0] = new Pharaon(0, 0, Couleur.BLEU);
        
        // Ajouter d'autres pièces selon la configuration initiale
        

        // Exemple : Obélisque Rouge en (9, 7)
        grille[9][7] = new Obelisque(9, 7, Couleur.ROUGE);
    }

    public void afficherPlateau() {
        // Afficher la grille (peut être utilisé à des fins de débogage)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (grille[i][j] != null) {
                    System.out.print(grille[i][j].getType().name().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void jouer() {
        Scanner scanner = new Scanner(System.in);
        boolean finDuJeu = false;

        while (!finDuJeu) {
            afficherPlateau();

            // Demander au joueur de sélectionner une pièce à déplacer
            System.out.println("Sélectionnez la pièce à déplacer (format : x y) : ");
            int xDepart = scanner.nextInt();
            int yDepart = scanner.nextInt();
            Pion pieceSelectionnee = getPion(xDepart, yDepart);

            // Valider si la pièce appartient au joueur actif et si elle peut être déplacée
            if (pieceSelectionnee != null && pieceSelectionnee.getCouleur() == Couleur.BLEU) {
                // Demander au joueur où déplacer la pièce
                System.out.println("Déplacez la pièce (format : x y) : ");
                int xArrivee = scanner.nextInt();
                int yArrivee = scanner.nextInt();

                // Valider si le mouvement est légal
                if (mouvementLegal(xDepart, yDepart, xArrivee, yArrivee)) {
                    // Déplacer la pièce sur le plateau
                    grille[xArrivee][yArrivee] = pieceSelectionnee;
                    grille[xDepart][yDepart] = null;
                    pieceSelectionnee.deplacer(xArrivee, yArrivee);
                } else {
                    System.out.println("Mouvement non autorisé !");
                }
            } else {
                System.out.println("Sélectionnez une pièce valide !");
            }

            // Vérifier si une condition de fin de jeu est atteinte
            // Exemple : si le Pharaon adverse est menacé
            // finDuJeu = conditionDeFinDeJeu();
        }

        scanner.close();
    }

    private boolean mouvementLegal(int xDepart, int yDepart, int xArrivee, int yArrivee) {
        // Implémenter la logique de validation des mouvements légaux
        // Vérifier si le mouvement respecte les règles du jeu
        // Par exemple, pour un pion donné, vérifiez s'il peut se déplacer à la position spécifiée
        return true; // Temporairement, retourne true pour tous les mouvements
    }

    // Ajoute d'autres méthodes pour gérer les actions du plateau, les lasers, etc.
    // ...

    /*Exemple de méthode pour déplacer une pièce sur le plateau
    public void deplacerPiece(int ancienX, int ancienY, int nouveauX, int nouveauY) {
        Pion piece = grille[ancienX][ancienY];
        grille[ancienX][ancienY] = null;
        grille[nouveauX][nouveauY] = piece;
        piece.deplacer(nouveauX, nouveauY);
    }
*/
    public Pion getPion(int x, int y) {
        return grille[x][y];
    }

    // Ajouter d'autres méthodes nécessaires selon les besoins du jeu
    // ...
}
