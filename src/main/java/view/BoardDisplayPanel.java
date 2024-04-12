package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardDisplayPanel extends JPanel {
    private final int boardWidth = 10; // 10 colonnes
    private final int boardHeight = 8; // 8 lignes
    private Color[][] boardState = new Color[boardHeight][boardWidth];

    public BoardDisplayPanel() {
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (j == 0) {
                    boardState[i][j] = Color.RED;
                } else if (j == boardWidth - 1) {
                    boardState[i][j] = Color.YELLOW;
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
        int cellSize = Math.min(getWidth() / boardWidth, getHeight() / boardHeight);
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                int x = j * cellSize;
                int y = i * cellSize;
                g.setColor(boardState[i][j]);
                g.fillRect(x, y, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BoardDisplayPanel());
        frame.setSize(800, 640);
        frame.setVisible(true);
    }
}
