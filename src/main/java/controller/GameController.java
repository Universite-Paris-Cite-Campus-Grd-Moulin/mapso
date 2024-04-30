package controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import view.GameView;

public class GameController implements MouseListener {
    private Game game;
    private Plateau board;
    private GameView gameView;
    private Pion selectedPiece = null;
    private static final int BOARD_COLUMNS = 10; // Nombre de colonnes du plateau
    private static final int BOARD_ROWS = 8; // Nombre de lignes du plateau

    private String userAction = ""; // move, rotate
    private int startX = 0;
    private int startY = 0;
    private boolean clockwise = true;

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

    public void restartGame(String mode) {
        this.board = new Plateau(mode);
        this.game = new Game(board);
        startGame();
    }
    

    // @Override
    // public void mouseClicked(MouseEvent e) {
    //     int x = e.getX() / gameView.getCellSize();
    //     int y = e.getY() / gameView.getCellSize();
    //     Pion piece = game.getPieceAt(x, y);

    //     if (piece != null && piece.getCouleur() == game.getCurrentPlayer()) {
    //         if (selectedPiece == null) {
    //             selectedPiece = piece; // Sélection de la pièce
    //             System.out.println("Pièce sélectionnée");
    //         } else {
    //             if (game.movePiece(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
    //                 gameView.update();
    //                 selectedPiece = null; // Désélectionner après un mouvement
    //             } else {
    //                 System.out.println("Mouvement invalide");
    //             }
    //         }
    //     } else {
    //         System.out.println("Ce n'est pas le tour de ce joueur ou aucune pièce à sélectionner.");
    //     }
    // }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / gameView.getCellSize();
        int y = e.getY() / gameView.getCellSize();
        if (game.getCurrentPlayer() == game.getPieceAt(x, y).getCouleur()) {
            if (userAction.equals("move")) {
                game.movePiece(startX, startY, x, y);
            } else if (userAction.equals("rotate")) {
                game.rotatePiece(x, y, clockwise);
            }
            gameView.update(); // Mettre à jour la vue après chaque action
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
}
