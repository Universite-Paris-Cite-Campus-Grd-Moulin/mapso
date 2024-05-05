package controller;

import java.awt.Cursor;
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

    public GameController(Game game) {
        this.game = game;
    }

    public GameController(String boardType) {
        this.board = new Plateau(boardType); // Create board with a specific type
        this.game = new Game(board);
    }

    public GameController(GameView gameView, String boardType) {
        this.board = new Plateau(boardType); // Create board with a specific type
        this.game = new Game(board);
        this.gameView = gameView;
    }

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void startGame() {
        game.start();
        if (gameView != null) {
            gameView.update();
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

        if (clickedPiece != null && clickedPiece.getCouleur() == game.getCurrentPlayer()) {
            selectedPiece = clickedPiece;
            validMoves = game.calculateValidMoves(selectedPiece, col, row);
            repaint();
        } else {
            System.out.println("Click not on current player's piece or no piece selected.");
        }
    }

    private void selectPiece(int x, int y) {
        if (game.getPieceAt(x, y) != null && game.getPieceAt(x, y).getCouleur() == game.getCurrentPlayer()) {
            System.out.println("Pièce sélectionnée : " + game.getPieceAt(x, y));
        } else {
            System.out.println("Sélection invalide ou ce n'est pas le tour du joueur.");
        }
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
        System.out.println("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released at (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gameView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gameView.setCursor(Cursor.getDefaultCursor());
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
                // Vérifiez que la nouvelle position n'est pas occupée par un pion de la même
                // couleur
                if (targetPion == null || targetPion.getCouleur() != pion.getCouleur()) {
                    // Ajoutez également une vérification pour s'assurer que le mouvement est valide
                    // en termes de couleur de la case
                    if (isMoveAllowedByColor(pion.getCouleur(), newX, newY)) {
                        validMoves.add(new Point(newX, newY));
                    }
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
