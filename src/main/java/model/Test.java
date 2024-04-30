package model;

public class Test {
    public static void main(String[] args) {
        // Specify the type of plateau when creating it
        Plateau plateau = new Plateau("Dynastie"); // Example: Use "Dynastie" configuration
        plateau.afficherPlateau(); // Display the board before the move

        // Attempt to move a piece from (3, 2) to (4, 6)
        boolean moved = plateau.deplacerPion(3, 2, 4, 6);
        if (moved) {
            System.out.println("Move successful.");
        } else {
            System.out.println("Move failed.");
        }

        plateau.afficherPlateau(); // Display the board after the move
    }
}
