package model;

public class Test {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        plateau.afficherPlateau(); // Afficher le plateau avant le déplacement
        plateau.deplacerPion(3, 2, 4, 6); // Déplacer un pion de la position (0, 0) à la position (3, 3)
        plateau.afficherPlateau(); // Afficher le plateau après le déplacement

    }
}
