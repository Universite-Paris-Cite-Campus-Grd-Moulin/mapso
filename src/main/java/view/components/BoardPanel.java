package view.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
<<<<<<< Updated upstream

=======
import java.awt.*;
>>>>>>> Stashed changes
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameController;
import model.Game;
<<<<<<< Updated upstream
=======
import model.NoeudTrajectoire;
import model.Observer;
>>>>>>> Stashed changes
import model.Pion;
import model.Plateau;
import model.enums.Direction;
import model.enums.TypeDePion;
import view.GameView;
<<<<<<< Updated upstream

public class BoardPanel extends JPanel implements MouseListener {
=======
import java.util.List;
>>>>>>> Stashed changes

public class BoardPanel extends JPanel implements MouseListener, Observer {
    private Plateau board;
    private int startX, startY;
    private GameController controller;
    private Game game;
    private Pion selectedPiece = null;
<<<<<<< Updated upstream

    public BoardPanel() {
        this(new Plateau("Classic"));
    }

    public BoardPanel(Plateau board) {
        this.board = board;
        setLayout(new GridLayout(8, 10));
        initBoard();
        addMouseListener(this);
        setPreferredSize(new Dimension(750, 600)); // 75 pixels * 10 columns wide and 75 pixels * 8 rows high
        revalidate();
    }

    // public BoardPanel(GameController controller) {
    //     this.controller = controller;
    //     addMouseListener(this);
    // }

    public BoardPanel(GameController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(750, 600));  // Taille préférée, ajustez selon vos besoins
    }

    public void setGame(Game game) {
        this.game = game;
        repaint();
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private void initBoard() {
=======
    private JFrame parentFrame;
    private view.components.GameNavigationListener navigationListener;
    private List<NoeudTrajectoire> cheminLaserRouge;
    private List<NoeudTrajectoire> cheminLaserJaune;

    public BoardPanel(String type, JFrame frame, GameView view) {
        this.board = new Plateau(type);
        this.game = new Game(board);
        view.addMouseListener(this);
        board.addObserver(this);
        this.parentFrame = frame;
        setupBoardPanel();
        initLasersAndDrawPaths();
        revalidate();
    }

    public Couleur getCurrentColor() {
        return this.game.getCurrentPlayer();
    }

    public Game getGame() {
        return this.game;
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
>>>>>>> Stashed changes
        removeAll();
        revalidate();
        repaint();
    }

    @Override
<<<<<<< Updated upstream
    public void paintComponent(Graphics g) {
=======
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        if (cheminLaserRouge != null && cheminLaserJaune != null) {
            drawLaserPath(g, cheminLaserRouge, Color.RED);
            drawLaserPath(g, cheminLaserJaune, Color.YELLOW);
        }
    }

    private void drawBoard(Graphics g) {
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    @Override
    public void mouseClicked(MouseEvent e) {
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        Pion clickedPiece = board.getPieceAt(col, row);

        // Action de déplacement ou de dépilement selon le bouton de la souris
        if (e.getButton() == MouseEvent.BUTTON1) { // Bouton gauche pour déplacer
            handleLeftClick(clickedPiece, col, row);
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Bouton droit pour dépiler
            handleRightClick(clickedPiece, col, row);
=======
    private void drawLaserPath(Graphics g, List<NoeudTrajectoire> cheminLaser, Color couleurLaser) {
        if (cheminLaser == null || cheminLaser.isEmpty()) {
            System.out.println("je retourne ici");
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        if (couleurLaser.equals(Color.YELLOW)) {
            g2d.setColor(new Color(9, 100, 131));
        } else if (couleurLaser.equals(Color.RED)) {
            g2d.setColor(new Color(245, 125, 77));
        }
        g2d.setStroke(new BasicStroke(4)); // Épaisseur du trait du laser

        NoeudTrajectoire prev = null;
        for (NoeudTrajectoire point : cheminLaser) {
            if (prev != null) {
                int x1 = prev.getPositionI() * getCellSize() + getCellSize() / 2; // Centre de la cellule
                int y1 = prev.getPositionJ() * getCellSize() + getCellSize() / 2;
                int x2 = point.getPositionI() * getCellSize() + getCellSize() / 2;
                int y2 = point.getPositionJ() * getCellSize() + getCellSize() / 2;
                g2d.drawLine(x1, y1, x2, y2);
            }
            prev = point;
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
>>>>>>> Stashed changes
        }
    }

    private void handleLeftClick(Pion clickedPiece, int col, int row) {
<<<<<<< Updated upstream
        if (clickedPiece != null && clickedPiece.getCouleur() == game.getCurrentPlayer()) {
            if (selectedPiece == null) {
                selectedPiece = clickedPiece;
                System.out.println("Pièce sélectionnée en (" + col + ", " + row + ")");
            } else {
                if (game.movePiece(selectedPiece.getX(), selectedPiece.getY(), col, row)) {
                    System.out.println("Mouvement réussi de (" + selectedPiece.getX() + ", " + selectedPiece.getY()
                            + ") à (" + col + ", " + row + ")");
                    selectedPiece = null;
                    gameView.update();
                } else {
                    System.out.println("Mouvement invalide.");
=======
        if (clickedPiece != null) {
            if (game.isPlayerTurn(clickedPiece.getCouleur())) {
                System.out.println("Handling action for " + clickedPiece.getCouleur());
                if (clickedPiece.getType() == TypeDePion.OBELISQUE) {
                    game.stackOrUnstackObelisk(clickedPiece, col, row);
                } else {
                    if (selectedPiece != null && selectedPiece == clickedPiece) {
                        game.rotatePiece(clickedPiece);
                    } else {
                        selectedPiece = clickedPiece;
                    }
>>>>>>> Stashed changes
                }
            } else {
                System.out.println("It's not " + clickedPiece.getCouleur() + "'s turn.");
            }
        } else {
<<<<<<< Updated upstream
            System.out.println("Ce n'est pas le tour de ce joueur ou la case est vide.");
=======
            System.out.println("No valid piece at clicked position.");
>>>>>>> Stashed changes
        }
    }

    private void handleRightClick(Pion clickedPiece, int col, int row) {
<<<<<<< Updated upstream
        if (clickedPiece != null && clickedPiece.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            if (board.depilerDoubleObelisque(clickedPiece, col, row)) {
                System.out
                        .println("Double obélisque décomposé en deux obélisques simples à (" + col + ", " + row + ")");
                repaint();
            } else {
                System.out.println("Dépilement impossible à (" + col + ", " + row + ")");
            }
=======
        System.out.println("Handling right click");
        if (clickedPiece != null && clickedPiece.getType() != TypeDePion.OBELISQUE
                && clickedPiece.getType() != TypeDePion.DOUBLE_OBELISQUE && clickedPiece.getType() != TypeDePion.NONE) {
            game.rotatePiece(clickedPiece);
            System.out.println("Piece rotated at (" + col + ", " + row + ")");

>>>>>>> Stashed changes
        } else {
            System.out.println("Action invalide pour le clic droit sur une case non-double obélisque ou vide.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        startX = e.getX() / cellSize;
        startY = e.getY() / cellSize;

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
        int cellSize = Math.min(getWidth() / 10, getHeight() / 8);
        int endX = e.getX() / cellSize;
        int endY = e.getY() / cellSize;
<<<<<<< Updated upstream

        if (e.getButton() == MouseEvent.BUTTON3 && selectedPiece != null
                && selectedPiece.getType() == TypeDePion.DOUBLE_OBELISQUE) {
            // Attempt to depile the double obelisk
            if (board.depilerDoubleObelisque(selectedPiece, startX, startY, endX, endY)) {
                System.out.println(
                        "Double obelisk separated at (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
            } else {
                System.out.println("Unable to separate double obelisk.");
            }
        } else if (e.getButton() == MouseEvent.BUTTON1 && selectedPiece != null) {
            // Handle the left click move or stack
            if (board.movePiece(startX, startY, endX, endY)) {
                System.out.println(
                        "Piece moved or stacked from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
            } else {
                System.out.println("Invalid move.");
=======
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
>>>>>>> Stashed changes
            }
        }
        repaint(); // Redraw the panel after action
    }

    @Override
    public void mouseEntered(MouseEvent e) {
<<<<<<< Updated upstream
=======
        // System.out.println("Mouse entered the component area.");
>>>>>>> Stashed changes
    }

    @Override
    public void mouseExited(MouseEvent e) {
<<<<<<< Updated upstream
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plateau");
        BoardPanel panel = new BoardPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack(); // Adjust the window size based on the content
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
=======
        // System.out.println("Mouse exited the component area.");
    }

    @Override
    public void update() {
        initLasersAndDrawPaths();
        repaint();
>>>>>>> Stashed changes
    }
}
