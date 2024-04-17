package view.components;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Pion;
import model.Plateau;
import model.enums.Direction;
import model.enums.TypeDePion;

public class BoardPanel extends JPanel implements MouseListener {

    private Plateau board;
    private Pion selectedPiece;
    private int startX, startY;

    public BoardPanel() {
        this(new Plateau("Classic"));
    }

    public BoardPanel(Plateau board) {
        this.board = board;
        setLayout(new GridLayout(8, 10));
        initBoard();
        addMouseListener(this);
    }

    private void initBoard() {
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(PiecePanel.draw(g, new Pion(TypeDePion.NONE, Direction.NORD, board.initCouleur(i, j))),
                        j * 75, i * 75, this);
                if (board.getGrille()[i][j] != null) {
                    g.drawImage(PiecePanel.draw(g, board.getGrille()[i][j]), j * 75, i * 75, this);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8); // Assure that the cell size adapts to panel size
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;

        if (col >= 0 && col < 10 && row >= 0 && row < 8) {
            Pion clickedPiece = board.getPieceAt(row, col);
            if (clickedPiece != null && clickedPiece.getType() != TypeDePion.NONE) {
                selectedPiece = clickedPiece;
                startX = col; // Save the start position
                startY = row;
                System.out.println("Souris appuyée sur la cellule (" + col + ", " + row + ")");
            } else {
                System.out.println("Aucune pièce valide dans la cellule (" + col + ", " + row + ")");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;

        if (selectedPiece != null && col >= 0 && col < 10 && row >= 0 && row < 8) {
            if (board.movePiece(startX, startY, col, row)) {
                selectedPiece = null;
                repaint();
                System.out
                        .println("Pièce déplacée de (" + startX + ", " + startY + ") vers (" + col + ", " + row + ")");
            } else {
                System.out.println(
                        "Échec du déplacement de la pièce de (" + startX + ", " + startY + ") vers (" + col + ", " + row
                                + ")");
            }
        } else {
            System.out.println("Mouse released out of bounds (" + col + ", " + row + ")");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plateau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BoardPanel());
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
}
