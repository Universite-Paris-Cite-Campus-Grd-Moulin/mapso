package model;

import java.io.IOException;

public class TestPlateau {
    public static void main(String[] args) {
        try {
            Plateau plateau = PlateauLoader.loadFromFile("src/main/java/model/game_board.txt");
            plateau.afficherPlateau();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du plateau : " + e.getMessage());
        }
    }
}

