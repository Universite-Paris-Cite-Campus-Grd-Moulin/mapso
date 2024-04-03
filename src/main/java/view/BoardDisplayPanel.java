package view;

import javax.swing.*;
import java.awt.*;

public class BoardDisplayPanel extends JPanel {
    // Dimensions du plateau ajustées selon les besoins
    private final int boardWidth = 10; // 10 colonnes
    private final int boardHeight = 8; // 8 lignes

    // Tableau pour stocker l'état des cases du plateau (utilisé ici uniquement pour les couleurs des colonnes)
    private Color[][] boardState = new Color[boardHeight][boardWidth];

    public BoardDisplayPanel() {
        // Initialisation du plateau avec les couleurs spécifiées
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                // La première colonne est rouge
                if (j == 0) {
                    boardState[i][j] = Color.RED;
                // La dernière colonne est jaune
                } else if (j == boardWidth - 1) {
                    boardState[i][j] = Color.YELLOW;
                // Le reste du plateau est gris
                } else {
                    boardState[i][j] = Color.GRAY;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        // Calcul de la taille de chaque case en fonction de la taille du JPanel
        int cellSize = Math.min(getWidth() / boardWidth, getHeight() / boardHeight);

        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                int x = j * cellSize;
                int y = i * cellSize;
                // Dessiner les cases avec les couleurs spécifiées
                g.setColor(boardState[i][j]);
                g.fillRect(x, y, cellSize, cellSize);

                // Dessiner les bordures de chaque case
                g.setColor(Color.BLACK); // Couleur de la bordure
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        BoardDisplayPanel boardDisplayPanel = new BoardDisplayPanel();
        frame.setContentPane(boardDisplayPanel);
        frame.setSize(800, 640); // Ajusté pour mieux correspondre à la taille du plateau
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
