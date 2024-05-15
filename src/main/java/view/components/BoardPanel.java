package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
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
    private Set<Point> validMoves = new HashSet<>();
    private BufferedImage spriteSheet;
    private BufferedImage spriteGreen;

    public BoardPanel(Plateau board, GameController controller, GameView gameView) {
        this.board = board;
        this.controller = controller;
        this.game = controller.getGame(); // Obtenez le jeu du contrôleur
        this.gameView = gameView;
        setLayout(new GridLayout(8, 10));
        setPreferredSize(new Dimension(750, 600));
        loadImages();
        addMouseListener(this);
        initBoard();
    }

    private void loadImages() {
        try {
            spriteSheet = ImageIO.read(new File("ressources/sprites_khet.png"));
            spriteGreen = ImageIO.read(new File("ressources/sprites_khet_vert.png"));
        } catch (IOException e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    private void initBoard() {
        removeAll();
        validate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = calculateCellSize(); // Utiliser la taille des cellules calculée
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                Pion currentPion = board.getPieceAt(j, i);
                boolean isSelected = validMoves.contains(new Point(j, i));
                BufferedImage backgroundImage = PiecePanel.draw(g,
                        new Pion(TypeDePion.NONE, Direction.NORD, board.initCouleur(i, j)), isSelected);
                g.drawImage(backgroundImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                if (currentPion != null) {
                    BufferedImage pionImage = PiecePanel.draw(g, currentPion, isSelected);
                    g.drawImage(pionImage, j * cellSize, i * cellSize, cellSize, cellSize, this);
                }
            }
        }
    }

    private int calculateCellSize() {
        return Math.min(getWidth() / 10, getHeight() / 8);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int cellSize = calculateCellSize();
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        Pion clickedPiece = game.getPieceAt(col, row);

        if (clickedPiece != null && clickedPiece.getCouleur() == game.getCurrentPlayer()) {
            selectedPiece = clickedPiece;
            validMoves = controller.calculateValidMoves(selectedPiece, col, row);
            repaint();
        } else {
            System.out.println("Clicked on non-current player's piece or no piece selected.");
        }

        if (clickedPiece != null) {
            System.out.println("Clicked Piece Info:");
            System.out.println("Type: " + clickedPiece.getType());
            System.out.println("Color: " + clickedPiece.getCouleur());
            System.out.println("Direction: " + clickedPiece.getDirection());
        } else {
            System.out.println("Clicked on empty cell.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
        int cellSize = calculateCellSize();
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
        int cellSize = calculateCellSize();
        int endX = e.getX() / cellSize;
        int endY = e.getY() / cellSize;
        System.out.println("End position set to (" + endX + ", " + endY + ")");

        Pion endPiece = board.getPieceAt(endX, endY); // Get piece at the end position

        if (e.getButton() == MouseEvent.BUTTON3 && selectedPiece != null) {
            if (endPiece != null && endPiece.getType() != TypeDePion.NONE) {
                endPiece.rotate(true); // Rotate the piece 90 degrees clockwise
                System.out.println("Piece rotated at (" + endX + ", " + endY + ")");
                gameView.update(); // Update the game view
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            if (endPiece == null || !endPiece.equals(selectedPiece)) {
                if (board.movePiece(startX, startY, endX, endY)) {
                    System.out.println(
                            "Piece moved or stacked from (" + startX + ", " + startY + ") to (" + endX + ", " + endY
                                    + ")");
                    selectedPiece = null; // Clear the selected piece after the move
                    validMoves.clear(); // Clear valid moves after moving the piece
                } else {
                    System.out.println("Invalid move.");
                }
            }
        }
        repaint(); // Redraw the board with the original images
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plateau");
        BoardPanel panel = new BoardPanel(new Plateau("Classic"), new GameController(new Plateau("Classic")),
                new GameView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
