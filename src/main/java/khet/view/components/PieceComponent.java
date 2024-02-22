package khet.view.components;

import javax.swing.JComponent;

import khet.model.Piece;

public class PieceComponent extends JComponent {
    private Piece piece;

    public PieceComponent(Piece piece) {
        this.piece = piece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessinez la pièce en utilisant g.drawImage ou une méthode similaire
        // L'image à dessiner dépendra du type et de la couleur de 'piece'
    }
}
