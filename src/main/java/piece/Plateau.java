package src.main.java.piece;

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
