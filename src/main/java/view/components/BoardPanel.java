package view.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.*;

import controller.GameController;
import model.Game;
import model.NoeudTrajectoire;
import model.Observer;
import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;
import view.GameView;
import java.util.List;
import view.components.GameNavigationListener;

public class BoardPanel extends JPanel implements MouseListener, Observer {
    private Plateau board;
    private int startX, startY;
    private GameController controller;
    private Game game;
    private Pion selectedPiece = null;
    private JFrame parentFrame;
    private GameNavigationListener navigationListener;
    private List<NoeudTrajectoire> cheminLaserRouge;
    private List<NoeudTrajectoire> cheminLaserJaune;
    private GameView gameView;

    public BoardPanel(String type, JFrame frame, GameView view){
        this.board = new Plateau(type);
        Game jeu = new Game(board);
        this.game = jeu;
        this.gameView = view;
        view.addMouseListener(this);
        board.addObserver(this);
        this.parentFrame = frame;

        setupBoardPanel();
        initLasersAndDrawPaths();
        revalidate();
    }

    public Couleur getCurrentColor(){
        return this.game.getCurrentPlayer();
    }

    public int getCellSize() {
        return 75;
    }

    private void setupBoardPanel() {
        setLayout(new BorderLayout());
        addMouseListener(this);
        initBoard();
    }

    public void initBoard() {
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        if (cheminLaserRouge != null && cheminLaserJaune != null) {
            drawLaserPath(g, cheminLaserRouge, Color.RED);
            drawLaserPath(g, cheminLaserJaune, Color.YELLOW);
        }
        gameView.updateTourLabel();
    }

    private void drawBoard(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawImage(view.components.PiecePanel.draw(g, new Pion(TypeDePion.NONE, Direction.NORD, board.initCouleur(i, j))),
                        j * 75, i * 75, this);
                if (board.getGrille()[i][j] != null) {
                    g.drawImage(view.components.PiecePanel.draw(g, board.getGrille()[i][j]), j * 75, i * 75, this);
                }
            }
        }
    }

    private void drawLaserPath(Graphics g, List<NoeudTrajectoire> cheminLaser, Color couleurLaser) {
        if (cheminLaser == null || cheminLaser.isEmpty()) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(couleurLaser);
        g2d.setStroke(new BasicStroke(4));

        if (couleurLaser.equals(Color.YELLOW)) {
            g2d.setColor(new Color(8, 75, 101));
        } else if (couleurLaser.equals(Color.RED)) {
            g2d.setColor(new Color(173, 78, 32));
        }

        for (int i = 0; i < cheminLaser.size() - 1; i++) {
            NoeudTrajectoire point = cheminLaser.get(i);
            NoeudTrajectoire nextPoint = cheminLaser.get(i + 1);

            int x1 = point.getPositionI() * getCellSize() + getCellSize() / 2;
            int y1 = point.getPositionJ() * getCellSize() + getCellSize() / 2;
            int x2 = nextPoint.getPositionI() * getCellSize() + getCellSize() / 2;
            int y2 = nextPoint.getPositionJ() * getCellSize() + getCellSize() / 2;

            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public void initLasersAndDrawPaths() {
        board.getRed().propagerLaser(board);
        board.getYellow().propagerLaser(board);
        List<NoeudTrajectoire> cheminLaserRouge = board.getRed().obtenirCheminLaser();
        List<NoeudTrajectoire> cheminLaserJaune = board.getYellow().obtenirCheminLaser();
        updateLaserPath(cheminLaserRouge, Couleur.ROUGE);
        updateLaserPath(cheminLaserJaune, Couleur.JAUNE);
        repaint();
    }

    public void updateLaserPath(List<NoeudTrajectoire> cheminLaser, Couleur couleurLaser) {
        cheminLaserRouge = board.getRed().obtenirCheminLaser();
        cheminLaserJaune = board.getYellow().obtenirCheminLaser();
        if (couleurLaser == Couleur.ROUGE) {
            this.cheminLaserRouge = cheminLaser;
        } else {
            this.cheminLaserJaune = cheminLaser;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked Event Triggered");

        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;

        if (col >= 0 && col < 10 && row >= 0 && row < 8) {
            Pion clickedPiece = board.getPieceAt2(row, col);
            System.out.println("Piece at clicked position: " + (clickedPiece != null ? clickedPiece.getType() : "null"));

            if (e.getButton() == MouseEvent.BUTTON1) {
                System.out.println("Left click detected");
                handleLeftClick(clickedPiece, col, row);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                System.out.println("Right click detected");
                handleRightClick(clickedPiece, col, row);
            }
        } else {
            System.out.println("Clicked position out of bounds: (" + col + ", " + row + ")");
        }
    }

    private void handleLeftClick(Pion clickedPiece, int col, int row) {
        if (clickedPiece != null) {
            if (game.isPlayerTurn(clickedPiece.getCouleur())) {
                System.out.println("Handling action for " + clickedPiece.getCouleur());
                if (clickedPiece.getType() == TypeDePion.OBELISQUE) {
                    game.stackOrUnstackObelisk(clickedPiece, col, row);
                } else {
                    if (selectedPiece != null && selectedPiece == clickedPiece) {
                        game.rotatePiece(clickedPiece);
                        gameView.resetTimer();
                        gameView.switchPlayer();
                    } else {
                        selectedPiece = clickedPiece;
                    }
                }
            } else {
                System.out.println("It's not " + clickedPiece.getCouleur() + "'s turn.");
            }
        } else {
            System.out.println("No valid piece at clicked position.");
        }
    }

    private void handleRightClick(Pion clickedPiece, int col, int row) {
        System.out.println("Handling right click");
        if (clickedPiece != null && clickedPiece.getType() != TypeDePion.OBELISQUE
                && clickedPiece.getType() != TypeDePion.DOUBLE_OBELISQUE && clickedPiece.getType() != TypeDePion.NONE) {
            clickedPiece.rotate(true);
            board.mettreAJourLesCheminsDesLasers();
            board.notifyObservers();
            gameView.resetTimer();
            gameView.switchPlayer();
            System.out.println("Piece rotated at (" + col + ", " + row + ")");
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
        System.out.println("Mouse released");
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
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
                    gameView.resetTimer();
                    gameView.switchPlayer();
                } else {
                    System.out.println("Invalid move.");
                    board.mettreAJourLesCheminsDesLasers();
                    board.notifyObservers();
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

    @Override
    public void update() {
        initLasersAndDrawPaths();
        repaint();
    }

    @Override
    public void updateGameOver(Couleur couleur) {

    }

    public Plateau getBoard() {
        return this.board;
    }

    public Game getGame() {
        return game;
    }
}
