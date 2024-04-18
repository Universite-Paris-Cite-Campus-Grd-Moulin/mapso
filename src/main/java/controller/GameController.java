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
    private int startX, startY; // Ajout pour stocker la position initiale lors du glisser

    public GameController() {
        this.board = new Plateau("Classic");
        this.game = new Game(board);
    }

    public GameController(GameView gameView) {
        this();
        this.gameView = gameView;
    }

    public void startGame() {
        game.start();
        if (gameView != null) {
            gameView.update();
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
        }
    }

    public void restartGame() {
        this.board = new Plateau("Classic");
        this.game = new Game(board);
        startGame();
    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        int cellSize = gameView.getCellSize();
//        int x = e.getX() / cellSize;
//        int y = e.getY() / cellSize;
//        if (x < BOARD_COLUMNS && y < BOARD_ROWS) {
//            if (selectedPiece == null) {
//                selectPiece(x, y);
//            } else {
//                moveSelectedPiece(x, y);
//            }
//        }
//    }

    private void selectPiece(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null && piece.getCouleur() == game.getCurrentPlayer()) {
            selectedPiece = piece;
            System.out.println("Pièce sélectionnée en " + x + ", " + y);
        } else {
            gameView.displayMessage("Sélection invalide. Sélectionnez une de vos pièces.");
        }
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
    public void mouseClicked(MouseEvent e) {

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

}

// Autre methode peut etre valable
/*
 * @Override
 * public void mouseClicked(MouseEvent e) {
 * int cellSize = gameView.getCellSize();
 * int x = e.getX() / cellSize;
 * int y = e.getY() / cellSize;
 * 
 * // Assurez-vous que le clic est dans les limites du plateau
 * if (x >= 0 && x < gameView.getBoardColumns() && y >= 0 && y <
 * gameView.getBoardRows()) {
 * // Si aucune pièce n'est actuellement sélectionnée
 * if (selectedPiece == null) {
 * Pion piece = board.getPieceAt(x, y);
 * // Si une pièce est présente à ces coordonnées
 * if (piece != null) {
 * // Vérifie si la pièce appartient au joueur actuel
 * if (piece.getCouleur() == game.getCurrentPlayer()) {
 * selectedPiece = piece;
 * System.out.println("Pièce sélectionnée en " + x + ", " + y);
 * } else {
 * // Informe l'utilisateur que la pièce sélectionnée n'appartient pas au joueur
 * // actuel
 * System.out.println("Cette pièce ne vous appartient pas.");
 * gameView.displayMessage(
 * "Cette pièce ne vous appartient pas. Veuillez sélectionner une de vos pièces."
 * );
 * }
 * }
 * } else {
 * // Tentative de déplacement de la pièce sélectionnée
 * if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
 * System.out.println("Pièce déplacée à " + x + ", " + y);
 * selectedPiece = null; // Désélectionner après le mouvement
 * gameView.update(); // Mise à jour de l'affichage
 * } else {
 * System.out.println("Déplacement invalide.");
 * gameView.displayMessage("Déplacement invalide. Veuillez essayer à nouveau.");
 * }
 * }
 * }
 * }
 */

/*
 * @Override
 * public void mouseClicked(MouseEvent e) {
 * int cellSize = gameView.getCellSize();
 * int x = e.getX() / cellSize;
 * int y = e.getY() / cellSize;
 * 
 * if (selectedPiece == null) {
 * // Select piece
 * selectedPiece = board.getPieceAt(x, y);
 * if (selectedPiece != null && selectedPiece.getCouleur() ==
 * game.getCurrentPlayer()) {
 * System.out.println("Piece selected at (" + x + ", " + y + ")");
 * } else {
 * selectedPiece = null;
 * gameView.
 * displayMessage("Invalid selection. Please select one of your pieces.");
 * }
 * } else {
 * // Move piece
 * if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
 * System.out.println("Piece moved to (" + x + ", " + y + ")");
 * selectedPiece = null; // Deselect piece after moving
 * gameView.update(); // Update view
 * } else {
 * System.out.println("Invalid move.");
 * gameView.displayMessage("Invalid move. Please try again.");
 * }
 * }
 * }
 */
