package view.components;

import javax.swing.JPanel;
import java.awt.GridLayout;

import model.Pion;

public class BoardPanel extends JPanel {

    public BoardPanel() {
        setLayout(new GridLayout(8, 10));
        initBoard();
    }

    private void initBoard() {
        // Initialise le plateau avec les PieceComponent pour chaque case
        removeAll(); // Enlève tous les composants précédemment ajoutés si nécessaire
        for (int i = 0; i < 80; i++) {
            add(new PieceComponent("ressources/sprites_khet.png"));
        }
    }

    public void updateBoard(Object[][] boardData) {
        removeAll(); // Enlève tous les composants avant de les ajouter à nouveau

        // Parcourez l'état actuel du plateau pour placer les sprites corrects
        for (int row = 0; row < boardData.length; row++) {
            for (int col = 0; col < boardData[row].length; col++) {
                // Obtenez l'image appropriée pour l'état de cette case
                String imagePath = getImagePathForPiece(boardData[row][col]);
                add(new PieceComponent(imagePath));
            }
        }
        
        revalidate(); // Indique au layout manager de rafraîchir le layout
        repaint(); // Indique à Swing de repeindre le panel
    }
    
    // Méthode helper pour obtenir le chemin de l'image basé sur l'objet pièce
    private String getImagePathForPiece(Object piece) {
        // Logique pour choisir l'image appropriée en fonction de l'objet pièce
        // par exemple:
        if (piece instanceof Pion) {
            return "ressources/sprites_khet.png";
        }
        // Vous pouvez étendre cette logique en fonction de l'implémentation de vos pièces
        return "ressources/sprites_khet.png"; // Une image par défaut si la pièce n'est pas reconnue
    }
}