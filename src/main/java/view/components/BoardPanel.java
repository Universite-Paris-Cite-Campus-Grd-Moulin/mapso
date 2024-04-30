package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import model.Game;
import model.Pion;
import model.Plateau;
import model.enums.Direction;
import model.enums.TypeDePion;
import view.GameView;

public class BoardPanel extends JPanel implements MouseListener {

    private Plateau board;
    private int startX, startY;
    private GameController controller;
    private Game game;
    private GameView gameView;
    private Pion selectedPiece = null;

    public BoardPanel() {
        this(new Plateau("Classic"));
    }

    public BoardPanel(Plateau board) {
        this.board = board;
        setLayout(new GridLayout(8, 10));
        initBoard();
        addMouseListener(this);
        setPreferredSize(new Dimension(750, 600)); // 75 pixels * 10 columns wide and 75 pixels * 8 rows high
        revalidate();
    }

    // public BoardPanel(GameController controller) {
    //     this.controller = controller;
    //     addMouseListener(this);
    // }

    public BoardPanel(GameController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(750, 600));  // Taille préférée, ajustez selon vos besoins
    }

    public void setGame(Game game) {
        this.game = game;
        repaint();
    }

    public void setController(GameController controller) {
        this.controller = controller;
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
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        Pion clickedPiece = board.getPieceAt(col, row);

        // Action de déplacement ou de dépilement selon le bouton de la souris
        if (e.getButton() == MouseEvent.BUTTON1) { // Bouton gauche pour déplacer
            handleLeftClick(clickedPiece, col, row);
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Bouton droit pour dépiler
            handleRightClick(clickedPiece, col, row);
        }
    }

    private void handleLeftClick(Pion clickedPiece, int col, int row) {
        if (clickedPiece != null && clickedPiece.getCouleur() == game.getCurrentPlayer()) {
            if (selectedPiece == null) {
                selectedPiece = clickedPiece;
                System.out.println("Pièce sélectionnée en (" + col + ", " + row + ")");
            } else {
                if (game.movePiece(selectedPiece.getX(), selectedPiece.getY(), col, row)) {
                    System.out.println("Mouvement réussi de (" + selectedPiece.getX() + ", " + selectedPiece.getY()
                            + ") à (" + col + ", " + row + ")");
                    selectedPiece = null;
                    gameView.update();
                } else {
                    System.out.println("Mouvement invalide.");
                }
            }
        } else {
            System.out.println("Ce n'est pas le tour de ce joueur ou la case est vide.");
        }
    }

    private void handleRightClick(Pion clickedPiece, int col, int row) {
        if (clickedPiece != null && clickedPiece.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            if (board.depilerDoubleObelisque(clickedPiece, col, row)) {
                System.out
                        .println("Double obélisque décomposé en deux obélisques simples à (" + col + ", " + row + ")");
                repaint();
            } else {
                System.out.println("Dépilement impossible à (" + col + ", " + row + ")");
            }
        } else {
            System.out.println("Action invalide pour le clic droit sur une case non-double obélisque ou vide.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        startX = e.getX() / cellSize;
        startY = e.getY() / cellSize;

        if (startX >= 0 && startX < 10 && startY >= 0 && startY < 8) {
            selectedPiece = board.getPieceAt(startX, startY);
            if (selectedPiece != null && selectedPiece.getType() != TypeDePion.NONE) {
                System.out.println(
                        "Mouse pressed at (" + startX + ", " + startY + ") with piece " + selectedPiece.getType());
            } else {
                System.out.println("No valid piece at cell (" + startX + ", " + startY + ")");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int endX = e.getX() / cellSize;
        int endY = e.getY() / cellSize;

        if (e.getButton() == MouseEvent.BUTTON3 && selectedPiece != null
                && selectedPiece.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            // Attempt to depile the double obelisk
            if (board.depilerDoubleObelisque(selectedPiece, startX, startY, endX, endY)) {
                System.out.println(
                        "Double obelisk separated at (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
            } else {
                System.out.println("Unable to separate double obelisk.");
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            // Handle the left click move or stack
            if (board.movePiece(startX, startY, endX, endY)) {
                System.out.println(
                        "Piece moved or stacked from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
            } else {
                System.out.println("Invalid move.");
            }
        }
        repaint(); // Redraw the panel after action
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plateau");
        BoardPanel panel = new BoardPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack(); // Adjust the window size based on the content
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }
}
