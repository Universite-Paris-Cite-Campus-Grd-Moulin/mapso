package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import model.Game;
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.TypeDePion;
import view.GameView;

public class GameController extends JPanel implements MouseListener {
    private Game game;
    private Plateau board;
    private GameView gameView;
    private Pion selectedPiece = null;
    private static final int BOARD_COLUMNS = 10; // Nombre de colonnes du plateau
    private static final int BOARD_ROWS = 8; // Nombre de lignes du plateau
    private int startX, startY; // Ajout pour stocker la position initiale lors du glisser
    private Set<Point> validMoves = new HashSet<>(); // To store valid move positions

    public Game getGame() {
        return game;
    }

    public GameController(Game game) {
        this.game = game;
        System.out.println("GameController initialized with game.");
    }

    public GameController(Plateau board) {
        this.board = board;
        this.game = new Game(board);
        System.out.println("GameController initialized with board.");
    }

    public GameController(String boardType) {
        this.board = new Plateau(boardType); // Create board with a specific type
        this.game = new Game(board);
        System.out.println("GameController initialized with board type: " + boardType);
    }

    public GameController(GameView gameView, String boardType) {
        this.board = new Plateau(boardType); // Create board with a specific type
        this.game = new Game(board);
        this.gameView = gameView;
        System.out.println("GameController initialized with game view and board type: " + boardType);
    }

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        System.out.println("GameController initialized with game and game view.");
    }

    public void startGame() {
        game.start();
        if (gameView != null) {
            gameView.update();
            System.out.println("Game started and game view updated.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game == null) {
            System.err.println("Game instance is not initialized.");
            return;
        }
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        Pion clickedPiece = game.getPieceAt(col, row);

        if (clickedPiece != null) {
            System.out.println("Clicked Piece Info:");
            System.out.println("Type: " + clickedPiece.getType());
            System.out.println("Color: " + clickedPiece.getCouleur());
            System.out.println("Direction: " + clickedPiece.getDirection());
        } else {
            System.out.println("Clicked on empty cell.");
        }

        if (clickedPiece != null && clickedPiece.getCouleur() == game.getCurrentPlayer()) {
            selectedPiece = clickedPiece;
            validMoves = calculateValidMoves(selectedPiece, col, row);
            System.out.println("Valid moves calculated: " + validMoves);
            gameView.repaint();
        } else {
            System.out.println("Click not on current player's piece or no piece selected.");
        }
    }

    private int calculateCellSize() {
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        System.out.println("Calculated cell size: " + cellSize);
        return cellSize;
    }

    public void handleUserAction(int startX, int startY, int endX, int endY) {
        if (board.movePiece(startX, startY, endX, endY)) {
            shootLaser();
            gameView.update();
        } else {
            System.out.println("Mouvement invalide");
            gameView.displayMessage("Mouvement invalide. Veuillez essayer à nouveau.");
        }
    }

    public void rotatePiece(int x, int y, boolean clockwise) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null) {
            piece.rotate(clockwise);
            shootLaser();
            gameView.update();
        }
    }

    private void shootLaser() {
        game.shootLaser();
        checkWinConditions();
    }

    private void checkWinConditions() {
        if (game.isGameOver()) {
            String winner = game.getCurrentPlayer() == Couleur.ROUGE ? "Jaune" : "Rouge";
            System.out.println("Le jeu est terminé. Le joueur " + winner + " gagne !");
            restartGame();
        }
    }

    public void restartGame() {
        this.board = new Plateau("Classic"); // or any other default or selected type
        this.game = new Game(board);
        startGame();
    }

    private void moveSelectedPiece(int newX, int newY) {
        if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), newX, newY)) {
            selectedPiece = null; // Désélectionner la pièce après le mouvement
            gameView.update();
        } else {
            gameView.displayMessage("Déplacement invalide.");
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
                game.switchPlayer();
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            if (endPiece == null || !endPiece.equals(selectedPiece)) {
                game.movePieceAndSwitchPlayer(startX, startY, endX, endY);
                selectedPiece = null; // Clear the selected piece after the move
                validMoves.clear(); // Clear valid moves after moving the piece
            } else {
                System.out.println("Invalid move.");
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

    private void handleMouseClick(int x, int y) {
        int cellSize = gameView.getCellSize();
        int col = x / cellSize;
        int row = y / cellSize;

        if (selectedPiece == null) {
            // Select piece
            selectedPiece = board.getPieceAt(row, col);
            if (selectedPiece != null && selectedPiece.getCouleur() != game.getCurrentPlayer()) {
                selectedPiece = null; // Ensure that player can only move their own pieces
                gameView.displayMessage("Invalid selection. Please select your own piece.");
            }
        } else {
            // Move piece
            if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), col, row)) {
                gameView.update(); // Update the view to reflect the new board state
                selectedPiece = null; // Deselect piece after move
            } else {
                gameView.displayMessage("Invalid move. Try again.");
            }
        }
    }

    public Set<Point> calculateValidMoves(Pion pion, int col, int row) {
        Set<Point> validMoves = new HashSet<>();
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int direction = 0; direction < dx.length; direction++) {
            int newX = col + dx[direction];
            int newY = row + dy[direction];
            if (newX >= 0 && newX < 10 && newY >= 0 && newY < 8) {
                Pion targetPion = board.getPieceAt(newX, newY);
                if (targetPion == null) {
                    validMoves.add(new Point(newX, newY));
                }
            }
        }
        return validMoves;
    }

    private boolean isMoveAllowedByColor(Couleur couleurPion, int x, int y) {
        // Implémentez la logique qui vérifie si le pion peut se déplacer sur la case
        // basée sur la couleur
        Couleur couleurCase = board.initCouleur(x, y);
        return couleurPion != couleurCase; // Les pions rouges ne peuvent pas aller sur les cases jaunes et vice versa
    }
}
