package khet.view;

import javax.swing.JFrame;

import khet.model.Board;

public class GameView extends JFrame {
    private BoardPanel boardPanel;

    public GameView(Board board) {
        setTitle("Jeu Khet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        boardPanel = new BoardPanel(board);
        add(boardPanel);
    }

    // Méthode pour mettre à jour l'affichage du plateau
    public void refreshBoard() {
        boardPanel.repaint();
    }
}
