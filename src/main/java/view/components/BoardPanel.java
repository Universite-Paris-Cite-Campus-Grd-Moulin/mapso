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

    public BoardPanel(GameController controller) {
        this.controller = controller;
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
        System.out.println("Mouse Clicked Event Triggered");
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        Pion clickedPiece = board.getPieceAt(col, row);
        System.out.println("Clicked position: (" + col + ", " + row + ")");

        if (e.getButton() == MouseEvent.BUTTON1) { // Bouton gauche pour déplacer ou sélectionner
            System.out.println("Left click detected");
            handleLeftClick(clickedPiece, col, row);
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Bouton droit pour la rotation ou d'autres actions spéciales
            System.out.println("Right click detected");
            handleRightClick(clickedPiece, col, row);
        }
    }

    private void handleLeftClick(Pion clickedPiece, int col, int row) {
        System.out.println("Handling left click");
        if (clickedPiece != null) {
            System.out.println("Clicked piece is not null, type: " + clickedPiece.getType());
            if (clickedPiece.getCouleur() == game.getCurrentPlayer()) {
                System.out.println("It's the current player's turn");
                if (clickedPiece.getType() == TypeDePion.OBELISQUE) {
                    System.out.println("Clicked piece is an Obelisk");
                    if (selectedPiece != null && selectedPiece == clickedPiece) {
                        System.out.println("Obelisk already selected. No movement performed.");
                    } else {
                        selectedPiece = clickedPiece;
                        System.out.println("Obelisk selected for potential stacking or action.");
                    }
                } else {
                    if (selectedPiece == null) {
                        selectedPiece = clickedPiece;
                        System.out.println("Piece selected at (" + col + ", " + row + ")");
                    } else {
                        if (game.movePiece(selectedPiece.getX(), selectedPiece.getY(), col, row)) {
                            System.out.println("Successful movement from (" + selectedPiece.getX() + ", "
                                    + selectedPiece.getY() + ") to (" + col + ", " + row + ")");
                            selectedPiece = null;
                            gameView.update();
                        } else {
                            System.out.println("Invalid movement.");
                            selectedPiece = null;
                        }
                    }
                }
            } else {
                System.out.println("Not the current player's turn.");
                selectedPiece = null;
            }
        } else {
            System.out.println("No piece at clicked position.");
        }
    }

    private void handleRightClick(Pion clickedPiece, int col, int row) {
        System.out.println("Handling right click");
        if (clickedPiece != null && clickedPiece.getType() != TypeDePion.OBELISQUE
                && clickedPiece.getType() != TypeDePion.DOUBLE_OBELISQUE && clickedPiece.getType() != TypeDePion.NONE) {
            clickedPiece.rotate(true);
            System.out.println("Piece rotated at (" + col + ", " + row + ")");
            gameView.update();
        } else {
            System.out.println("Invalid action for right-click on this type of piece or empty cell.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        startX = e.getX() / cellSize;
        startY = e.getY() / cellSize;
        System.out.println("Start position set to (" + startX + ", " + startY + ")");

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
        System.out.println("Mouse released");
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int endX = e.getX() / cellSize;
        int endY = e.getY() / cellSize;
        System.out.println("End position set to (" + endX + ", " + endY + ")");

        Pion endPiece = board.getPieceAt(endX, endY); // Get piece at the end position

        if (e.getButton() == MouseEvent.BUTTON3 && selectedPiece != null
                && selectedPiece.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            if (endPiece == null || endPiece.getType() != TypeDePion.DOUBLE_OBELISQUE) {
                if (board.depilerDoubleObelisque(selectedPiece, startX, startY, endX, endY)) {
                    System.out.println(
                            "Double obelisk separated at (" + startX + ", " + startY + ") to (" + endX + ", " + endY
                                    + ")");
                } else {
                    System.out.println("Unable to separate double obelisk.");
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            if (endPiece == null || !endPiece.equals(selectedPiece)) {
                if (board.movePiece(startX, startY, endX, endY)) {
                    System.out.println(
                            "Piece moved or stacked from (" + startX + ", " + startY + ") to (" + endX + ", " + endY
                                    + ")");
                } else {
                    System.out.println("Invalid move.");
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered the component area.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited the component area.");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plateau");
        BoardPanel panel = new BoardPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
