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
        grille[0][0] = new Pharaon(0, 0, Couleur.JAUNE);
        
        // Ajouter le reste des pièces dans la config initiale

        // Exemple : Obélisque Rouge en (9, 7)
        grille[9][7] = new Obelisque(9, 7, Couleur.ROUGE);
    }

    public void afficherPlateau() {
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
            // couleur par défaut JAUNE, implémenter le getcouleur de l'utilisateur
            if (pieceSelectionnee != null && pieceSelectionnee.getCouleur() == Couleur.JAUNE) {
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

            // vérifier si une fin de jeu est atteinte
        }

        scanner.close();
    }

    private boolean mouvementLegal(int xDepart, int yDepart, int xArrivee, int yArrivee) {
        // Implémenter la logique de validation des mouvements légaux
        // Vérifier si le mouvement respecte les règles du jeu
        return true; // Temporairement, retourne true pour tous les mouvements
    }

    public Pion getPion(int x, int y) {
        return grille[x][y];
    }

}
