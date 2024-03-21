package view.components;

import java.awt.GridLayout;

import javax.swing.JPanel;


public class BoardPanel extends JPanel {

    public BoardPanel() {
        // Supposons un plateau 8x10 pour l'exemple
        setLayout(new GridLayout(8, 10));
        initBoard();
    }

    public void updateBoard() {
        // Mettre à jour l'affichage du plateau
    }

    private void initBoard() {
        // Ajoutez des PieceComponent avec des chemins d'images valides
        for (int i = 0; i < 80; i++) {
            // Exemple : ajouter un PieceComponent avec une image spécifique
            add(new PieceComponent("src/main/resources/images/Gris.png"));
        }
    }
}
