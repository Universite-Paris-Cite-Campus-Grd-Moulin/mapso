//Realise par Paul
package khet.model;

// Modélise le plateau de jeu, en combinant les fonctionnalités des classes Board et Plateau
public class Board {
    private Piece[][] grille; // Utilisez Piece au lieu de Pion pour une cohérence avec le reste du projet

    public Board() {
        // Crée une grille de pièces, par exemple, de taille 10x8
        grille = new Piece[10][8];
        initialiserPlateau();
    }

    private void initialiserPlateau() {
        // Initialiser la grille avec les pièces de départ
        // Assurez-vous que les classes des pièces (comme Pharaon, Obelisque) sont correctement définies et intégrées

        // Exemple d'initialisation (les classes spécifiques des pièces doivent être définies ailleurs)
        // grille[0][0] = new Pharaon(0, 0, Couleur.JAUNE); // Remarque: Assurez-vous que Couleur.BLEU est changé en Couleur.JAUNE si c'est la couleur utilisée
        
        // Ajouter d'autres pièces selon la configuration initiale du jeu Khet

        // Exemple : Obélisque Rouge en (9, 7)
        // grille[9][7] = new Obelisque(9, 7, Couleur.ROUGE);
    }

    // Place une pièce sur le plateau
    public void placePiece(Piece piece, int x, int y) {
        grille[x][y] = piece;
    }

    // Déplace une pièce sur le plateau
    public void movePiece(int fromX, int fromY, int toX, int toY) {
        Piece piece = grille[fromX][fromY];
        grille[fromX][fromY] = null;
        grille[toX][toY] = piece;
    }

    // Affiche le plateau dans la console - Utile pour le débogage
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

    // Retourne la pièce située aux coordonnées (x, y)
    public Piece getPion(int x, int y) {
        return grille[x][y];
    }

    // Ajoutez d'autres méthodes nécessaires pour la logique spécifique du jeu
}
