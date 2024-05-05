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
    private Set<Point> validMoves = new HashSet<>(); // To store valid move positions
    private BufferedImage spriteSheet;
    private BufferedImage spriteGreen;

    public BoardPanel() {
        this(new Plateau("Classic"));
        loadImages();
    }

    public BoardPanel(Plateau board) {
        this.board = board;
        setLayout(new GridLayout(8, 10));
        loadImages();
        initBoard();
        addMouseListener(this);
        setPreferredSize(new Dimension(750, 600)); // 75 pixels * 10 columns wide and 75 pixels * 8 rows high
        revalidate();
    }

    public BoardPanel(GameController controller) {
        this.controller = controller;
        addMouseListener(this);
    }

    public BoardPanel(GameController controller, Game game, GameView gameView) {
        this.controller = controller;
        this.game = game;
        this.gameView = gameView;
        this.board = game.getBoard();
        setLayout(new GridLayout(8, 10));
        setPreferredSize(new Dimension(750, 600));
        loadImages();
        addMouseListener(this);
        initBoard();
    }

    private BufferedImage chooseImageForCell(Pion pion, int x, int y) {
        if (pion != null && validMoves.contains(new Point(x, y))) {
            return spriteGreen; // Assurez-vous que spriteGreen est l'image teintée en vert.
        } else if (pion != null) {
            return spriteSheet; // L'image normale du pion.
        } else {
            return null; // Pas d'image si la case est vide.
        }
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

        if (game == null) {
            System.err.println("Game instance is not initialized.");
            return; // Arrêter le traitement si game est null
        }

        if (clickedPiece != null && clickedPiece.getCouleur() == game.getCurrentPlayer()) {
            selectedPiece = clickedPiece;
            validMoves = calculateValidMoves(selectedPiece, col, row); // Mettre à jour validMoves
            repaint();
        }
        if (e.getButton() == MouseEvent.BUTTON1) { // Bouton gauche pour déplacer ou sélectionner
            if (clickedPiece != null && clickedPiece.getCouleur() == game.getCurrentPlayer()) {
                selectedPiece = clickedPiece;
                calculateValidMoves(selectedPiece, col, row);
                repaint();
            }
            System.out.println("Left click detected");
            handleLeftClick(clickedPiece, col, row);
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Bouton droit pour la rotation ou d'autres actions spéciales
            System.out.println("Right click detected");
            handleRightClick(clickedPiece, col, row);
        }
    }

    public Set<Point> calculateValidMoves(Pion pion, int col, int row) {
        Set<Point> validMoves = new HashSet<>();
        // Assumer que chaque pion peut se déplacer à une case adjacente pour simplifier
        int[] dx = { -1, 0, 1, 0 }; // Déplacements horizontaux et verticaux
        int[] dy = { 0, -1, 0, 1 }; // Déplacements verticaux et horizontaux

        for (int direction = 0; direction < dx.length; direction++) {
            int newX = col + dx[direction];
            int newY = row + dy[direction];
            // Vérifiez que la nouvelle position est dans les limites du plateau
            if (newX >= 0 && newX < 10 && newY >= 0 && newY < 8) {
                Pion targetPion = board.getPieceAt(newX, newY);
                // Vérifier si la case est vide ou contient une pièce que l'on peut capturer
                if (targetPion == null || targetPion.getCouleur() != pion.getCouleur()) {
                    validMoves.add(new Point(newX, newY));
                }
            }
        }

        return validMoves;
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
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
