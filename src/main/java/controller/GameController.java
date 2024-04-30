package controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import view.GameView;
import view.components.BoardPanel;

public class GameController implements MouseListener {
    private Game game;
    private Plateau board;
    private GameView gameView;
    private BoardPanel boardPanel;
    private Pion selectedPiece = null;

    public GameController(GameView gameView) {
        this.gameView = gameView;
        this.board = new Plateau("Classic");
        this.game = new Game(board);
        this.boardPanel = gameView.getBoardPanel();
        gameView.addMouseListener(this);
        startGame();
    }

    private void startGame() {
        game.start();
        gameView.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int cellSize = boardPanel.getCellSize();
        int x = e.getX() / cellSize;
        int y = e.getY() / cellSize;

        if (board.isCoordonneeValide(x, y)) {
            if (selectedPiece == null) {
                selectPiece(x, y);
            } else {
                moveSelectedPiece(x, y);
                selectedPiece = null;
            }
        }
    }

    private void selectPiece(int x, int y) {
        Pion piece = board.getPieceAt(x, y);
        if (piece != null && piece.getCouleur() == game.getCurrentPlayer()) {
            selectedPiece = piece;
            gameView.displayMessage("Pièce sélectionnée en " + x + ", " + y);
        } else {
            gameView.displayMessage("Sélection invalide. Sélectionnez une de vos pièces.");
        }
    }

    private void moveSelectedPiece(int x, int y) {
        if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), x, y)) {
            board.propagerLasers();
            gameView.update();
            checkEndGame();
        } else {
            gameView.displayMessage("Déplacement invalide.");
        }
    }

    private void checkEndGame() {
        if (game.isGameOver()) {
            gameView.showWinner(game.getCurrentPlayer());
        }
    }

    public void restartGame() {
        this.board = new Plateau("Classic");
        this.game = new Game(board);
        startGame();
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
