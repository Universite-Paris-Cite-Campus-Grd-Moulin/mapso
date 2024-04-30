package view.components;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.Pion;
import model.Plateau;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

public class BoardPanel extends JPanel implements MouseListener {
    private Plateau board;
    private Pion selectedPiece;
    private JFrame parentFrame;
    private GameNavigationListener navigationListener;
    private List<Point> laserPathRed = new ArrayList<>();
    private List<Point> laserPathYellow = new ArrayList<>();

    public BoardPanel(Plateau board, JFrame parentFrame, GameNavigationListener listener) {
        this.board = board;
        this.parentFrame = parentFrame;
        this.navigationListener = listener;
        setupBoardPanel();
    }

    private void setupBoardPanel() {
        setLayout(new BorderLayout());
        addMouseListener(this);
        initBoard();
        addPauseButton();
    }

    private void initBoard() {
        removeAll();
        revalidate();
        repaint();
    }

    public void updateLaserPathAndRedraw() {
        // Mise à jour du chemin du laser rouge
        List<Point> cheminLaserRouge = board.getCheminLaser(Couleur.ROUGE);
        setLaserPathRed(cheminLaserRouge);
    
        // Mise à jour du chemin du laser jaune
        List<Point> cheminLaserJaune = board.getCheminLaser(Couleur.JAUNE);
        setLaserPathYellow(cheminLaserJaune);
    
        // Redessine le plateau avec les nouveaux chemins des lasers
        repaint();
    }
    
    public void setLaserPathRed(List<Point> path) {
        this.laserPathRed = path;
    }
    
    public void setLaserPathYellow(List<Point> path) {
        this.laserPathYellow = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawLaser(g);
    }

    private void drawBoard(Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                Pion pion = board.getGrille()[i][j] != null ? board.getGrille()[i][j] : new Pion(TypeDePion.NONE, Direction.NORD, board.initCouleur(i, j));
                g.drawImage(PiecePanel.draw(g, pion), j * 75, i * 75, this);
            }
        }
    }


    private void drawLaser(Graphics g) {
        drawLaserPath(g, laserPathRed, Color.RED);
        drawLaserPath(g, laserPathYellow, Color.YELLOW);
    }
    
    private void drawLaserPath(Graphics g, List<Point> cheminLaser, Color couleurLaser) {
        if (cheminLaser == null || cheminLaser.isEmpty()) {
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(couleurLaser);
        g2d.setStroke(new BasicStroke(2)); // Épaisseur du trait du laser
    
        Point prev = null;
        for (Point point : cheminLaser) {
            if (prev != null) {
                int x1 = prev.x * getCellSize() + getCellSize() / 2; // Centre de la cellule
                int y1 = prev.y * getCellSize() + getCellSize() / 2;
                int x2 = point.x * getCellSize() + getCellSize() / 2;
                int y2 = point.y * getCellSize() + getCellSize() / 2;
                g2d.drawLine(x1, y1, x2, y2);
            }
            prev = point;
        }
    }

    private void drawPion(Graphics g, Pion pion, int i, int j) {
        if (pion != null) {
            // Dessin du pion (adapté à votre implémentation)
            // Exemple: g.drawImage(PieceImage, j * 75, i * 75, this);
            g.fillRect(j * 75, i * 75, 75, 75); // Simplifié pour l'exemple
        }
    }

    private void showPauseMenu() {
        JDialog pauseMenu = new JDialog(parentFrame, "Pause", true);
        pauseMenu.setLayout(new GridLayout(4, 1));
        pauseMenu.setSize(200, 200);
        pauseMenu.setLocationRelativeTo(parentFrame);
        addPauseMenuButtons(pauseMenu);
        pauseMenu.setVisible(true);
    }

    private void addPauseButton() {
        JButton pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(new Dimension(100, 40));
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.WHITE);  // Assurez-vous que le texte est visible

        pauseButton.addActionListener(e -> showPauseMenu());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        topPanel.add(pauseButton);
        this.add(topPanel, BorderLayout.NORTH); // Assurez-vous que 'this' utilise BorderLayout comme layout manager

        revalidate(); // Revalide le layout après ajout
        repaint(); // Repeint le panel après modifications
    }

    private void addPauseMenuButtons(JDialog pauseMenu) {
        System.out.println();
        JButton continueButton = new JButton("Continue");
        JButton restartButton = new JButton("Restart");
        JButton backToMenuButton = new JButton("Back to Menu");
        JButton exitButton = new JButton("Exit");

        continueButton.addActionListener(e -> pauseMenu.dispose());
        //restartButton.addActionListener(this::restartGame);
        backToMenuButton.addActionListener(e -> navigationListener.onBackToMenuRequested());
        exitButton.addActionListener(e -> System.exit(0));

        pauseMenu.add(continueButton);
        pauseMenu.add(restartButton);
        pauseMenu.add(backToMenuButton);
        pauseMenu.add(exitButton);
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int cellSize = 75;
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        selectedPiece = board.getPieceAt(row, col);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectedPiece != null) {
            int cellSize = 75;
            int col = e.getX() / cellSize;
            int row = e.getY() / cellSize;
        if (board.movePiece(selectedPiece.getX(), selectedPiece.getY(), col, row)) {
            updateLaserPathAndRedraw();  // Met à jour et redessine les chemins pour les deux lasers
            selectedPiece = null;
            repaint();  // Assurez-vous de redessiner le plateau pour refléter les changements
        }
    }
}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test BoardPanel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            Plateau board = new Plateau("Classic"); // Assurez-vous que cela initialise correctement le plateau.
    
            // Utilisez une implémentation anonyme ou définissez une vraie classe si vous avez besoin de gérer la navigation.
            GameNavigationListener listener = new GameNavigationListener() {
                public void onBackToMenuRequested() {
                    System.out.println("Back to menu requested.");
                }
            };
    
            BoardPanel boardPanel = new BoardPanel(board, frame, listener);
            frame.setContentPane(boardPanel);
            frame.setSize(800, 640); // Taille adaptée à votre grille
            frame.setLocationRelativeTo(null); // Centrer la fenêtre
            frame.setVisible(true);
        });
    }

    public int getCellSize() {
        return 75;
    }
}
