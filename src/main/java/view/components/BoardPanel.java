package view.components;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class BoardPanel extends JPanel implements MouseListener {

    private Plateau board;
    private Pion selectedPiece;
    private int startX, startY;

    public BoardPanel() {
        setLayout(new GridLayout(8, 10));
        initBoard();
        addMouseListener(this);
    }

    private void initBoard() {
        // Initialise le plateau avec les PiecePanel pour chaque case
        removeAll(); // Enlève tous les composants précédemment ajoutés si nécessaire
        Plateau board = new Plateau();
        this.board = board;

    }

    @Override
    public void paintComponent(Graphics g) {
        // on inverse i et j car dans la vue le coordonnee x est prportionnel au nombre
        // de colonne donc j et le y est en relation avec le nombre
        // de lignes qui est i
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(PiecePanel.draw(g, new Pion(TypeDePion.NONE, Direction.NORD, board.initCouleur(i, j))),
                        j * 75, i * 75, this);
                g.drawImage(PiecePanel.draw(g, board.getGrille()[i][j]), j * 75, i * 75, this);
            }
        }
    }

    public void put(Pion p) {
        // placer dans le board
        // board.deplacerPion(p);*
        board.getGrille()[2][2] = new Pion(TypeDePion.DJED, Direction.SUD, Couleur.JAUNE);
        repaint();
    }

    // MouseListener methods need to be implemented, even if they do nothing
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int cellSize = 75;
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        selectedPiece = board.getPieceAt(row, col);
        System.out.println("Mouse pressed at cell (" + col + ", " + row + ")");
        // Implement logic to select a piece
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int cellSize = 75;
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        if (selectedPiece != null && board.movePiece(selectedPiece.getX(), selectedPiece.getY(), col, row)) {
            selectedPiece = null;
            repaint(); // Redraw the board to reflect the piece's new position
        }
        System.out.println("Mouse released at cell (" + col + ", " + row + ")");
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