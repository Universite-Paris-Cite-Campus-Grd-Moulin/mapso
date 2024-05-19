package main.java.controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import main.java.model.Game;
import main.java.model.Laser;
import main.java.model.NoeudTrajectoire;
import main.java.model.Pion;
import main.java.model.Plateau;
import main.java.view.GameView;
import main.java.view.Menu;
import main.java.view.components.BoardPanel;
import main.java.view.components.GameOverBoard;
import model.enums.Couleur;
import main.java.model.enums.TypeDePion;

public class GameController implements MouseListener {
    private Game game;
    private Plateau board;
    private GameView gameView;
    private BoardPanel panel;
    private Pion selectedPiece = null;
    private static final int BOARD_COLUMNS = 10; // Nombre de colonnes du plateau
    private static final int BOARD_ROWS = 8; // Nombre de lignes du plateau
    private int startX, startY; // Ajout pour stocker la position initiale lors du glisser

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

    public Couleur getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public void propagerLaser() {
        Couleur couleurLaser = game.getCurrentPlayer();
        Laser laser = new Laser(couleurLaser);
        laser.propagerLaser(board);
        List<NoeudTrajectoire> cheminLaser = laser.obtenirCheminLaser();
        gameView.updateLaserPath(cheminLaser, couleurLaser);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / gameView.getCellSize();
        int y = e.getY() / gameView.getCellSize();
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
            }
        } else {
            System.out.println("Ce n'est pas le tour de ce joueur ou aucune pièce à sélectionner.");
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

    public void backToMenu() {
        gameView.getMainFrame().getContentPane().removeAll();
        Menu menu = new Menu(gameView.getMainFrame());
        gameView.getMainFrame().setContentPane(menu);
        gameView.getMainFrame().revalidate();
        gameView.getMainFrame().repaint();
    }

    public void restartGame(String boardType) {
        this.board = new Plateau(boardType); // Recrée un nouveau plateau avec le type spécifié
        this.game = new Game(board); // Recrée un nouveau jeu avec le nouveau plateau
        this.gameView.setBoardPanel(new BoardPanel(boardType,gameView.getMainFrame(),gameView)); // Met à jour le panneau de jeu dans la vue du jeu
        this.panel = gameView.getBoardPanel(); // Réinitialise le panneau pour écouter les événements de la souris
        this.panel.addMouseListener(this); // Ajoute le MouseListener au nouveau panneau

        gameView.update(); // Met à jour la vue du jeu
    }
}
