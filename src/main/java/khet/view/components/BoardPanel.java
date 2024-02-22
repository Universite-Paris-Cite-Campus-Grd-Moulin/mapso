package khet.view.components;

import java.awt.Graphics;

import javax.swing.JPanel;

import khet.model.Board;

public class BoardPanel extends JPanel {
    private Board board;

    public BoardPanel(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessinez ici le plateau de jeu et les pièces en utilisant les attributs de
        // 'board'
        // Vous pouvez utiliser g.drawRect, g.drawImage, etc., pour dessiner le plateau
        // et les pièces
    }
}
