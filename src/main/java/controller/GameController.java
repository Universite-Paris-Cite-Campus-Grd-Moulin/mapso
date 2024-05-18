package controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
<<<<<<< Updated upstream

import model.Game;
=======
import java.util.List;

import model.Game;
import model.Laser;
import model.NoeudTrajectoire;
>>>>>>> Stashed changes
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import view.GameView;
import view.components.BoardPanel;
import model.enums.TypeDePion;

public class GameController implements MouseListener {
    private Game game;
    private Plateau board;
    private GameView gameView;
    private BoardPanel panel;
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
        this.panel = gameView.getBoardPanel();
        this.panel.addMouseListener(this); // Ajout du MouseListener
    }

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        this.panel = gameView.getBoardPanel();
        this.panel.addMouseListener(this); // Ajout du MouseListener
    }

    public void startGame() {
        game.start();
        if (gameView != null || board != null) {
            gameView.update();
        }
    }

<<<<<<< Updated upstream
    public void restartGame(String mode) {
        this.board = new Plateau(mode);
        this.game = new Game(board);
        startGame();
=======
    public Couleur getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public void propagerLaser() {
        Couleur couleurLaser = game.getCurrentPlayer();
        Laser laser = new Laser(couleurLaser);
        laser.propagerLaser(board);
        List<NoeudTrajectoire> cheminLaser = laser.obtenirCheminLaser();
        gameView.updateLaserPath(cheminLaser, couleurLaser);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        if (game.getCurrentPlayer() == game.getPieceAt(x, y).getCouleur()) {
            if (userAction.equals("move")) {
                game.movePiece(startX, startY, x, y);
            } else if (userAction.equals("rotate")) {
                game.rotatePiece(x, y, clockwise);
=======
        Pion piece = board.getPieceAt(x, y);

        if (piece != null && piece.getCouleur() == game.getCurrentPlayer()) {
            if (selectedPiece == null) {
                selectedPiece = piece; // Sélection de la pièce
                System.out.println("Pièce sélectionnée");
            } else {
                if (game.movePiece(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
                    gameView.update();
                    selectedPiece = null; // Désélectionner après un mouvement
                } else {
                    System.out.println("Mouvement invalide");
                }
>>>>>>> Stashed changes
            }
            gameView.update(); // Mettre à jour la vue après chaque action
        }
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
        int cellSize = gameView.getCellSize();
        startX = e.getX() / cellSize;
        startY = e.getY() / cellSize;
        System.out.println("Start position set to (" + startX + ", " + startY + ")");

        if (startX >= 0 && startX < BOARD_COLUMNS && startY >= 0 && startY < BOARD_ROWS) {
            selectedPiece = board.getPieceAt(startX, startY);
            if (selectedPiece != null && selectedPiece.getType() != TypeDePion.NONE) {
                board.mettreAJourLesCheminsDesLasers();
                board.notifyObservers();
                System.out.println("Mouse pressed at (" + startX + ", " + startY + ") with piece " + selectedPiece.getType());
            } else {
                board.mettreAJourLesCheminsDesLasers();
                board.notifyObservers();
                System.out.println("No valid piece at cell (" + startX + ", " + startY + ")");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released at (" + e.getX() + ", " + e.getY() + ")");
        int cellSize = gameView.getCellSize();
        int endX = e.getX() / cellSize;
        int endY = e.getY() / cellSize;
        System.out.println("End position set to (" + endX + ", " + endY + ")");

        Pion endPiece = board.getPieceAt(endX, endY);

        if (e.getButton() == MouseEvent.BUTTON3 && selectedPiece != null
                && selectedPiece.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            if (endPiece == null || endPiece.getType() != TypeDePion.DOUBLE_OBELISQUE) {
                if (board.depilerDoubleObelisque(selectedPiece, startX, startY, endX, endY)) {
                    System.out.println("Double obelisk separated at (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
                    board.mettreAJourLesCheminsDesLasers();
                    board.notifyObservers();
                } else {
                    System.out.println("Unable to separate double obelisk.");
                    board.mettreAJourLesCheminsDesLasers();
                    board.notifyObservers();
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            if (endPiece == null || !endPiece.equals(selectedPiece)) {
                if (board.movePiece(startX, startY, endX, endY)) {
                    System.out.println("Piece moved or stacked from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
                    board.mettreAJourLesCheminsDesLasers();
                    board.notifyObservers();
                } else {
                    System.out.println("Invalid move.");
                    board.mettreAJourLesCheminsDesLasers();
                    board.notifyObservers();
                }
            }
        }
        gameView.getBoardPanel().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.setCursor(Cursor.getDefaultCursor());
    }
}
