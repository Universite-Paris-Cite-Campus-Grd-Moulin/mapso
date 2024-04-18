package view.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Pion;
import model.Plateau;
import model.enums.Direction;
import model.enums.TypeDePion;

public class BoardPanel extends JPanel implements MouseListener {
    private Plateau board;
    private Pion selectedPiece;
    private JFrame parentFrame;
    private GameNavigationListener navigationListener;

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                Pion pion = board.getGrille()[i][j] != null ? board.getGrille()[i][j] : new Pion(TypeDePion.NONE, Direction.NORD, board.initCouleur(i, j));
                g.drawImage(PiecePanel.draw(g, pion), j * 75, i * 75, this);
            }
        }
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

    private void showPauseMenu() {
        JDialog pauseMenu = new JDialog(parentFrame, "Pause", true);
        pauseMenu.setLayout(new GridLayout(4, 1));
        pauseMenu.setSize(200, 200);
        pauseMenu.setLocationRelativeTo(parentFrame);
        addPauseMenuButtons(pauseMenu);
        pauseMenu.setVisible(true);
    }

    private void addPauseMenuButtons(JDialog pauseMenu) {
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

    private void restartGame() {
        // Code pour redémarrer le jeu
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

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
                selectedPiece = null;
                repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame testFrame = new JFrame("Test du BoardPanel avec bouton Pause");
                testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Plateau board = new Plateau("Classic"); // Assurez-vous que cela crée un plateau initialisé.
                GameNavigationListener listener = new GameNavigationListener() {
                    @Override
                    public void onBackToMenuRequested() {
                        System.out.println("Retour au menu.");
                    }
                };

                BoardPanel boardPanel = new BoardPanel(board, testFrame, listener);
                testFrame.add(boardPanel);
                testFrame.setSize(800, 600); // Taille de la fenêtre
                testFrame.setVisible(true);
            }
        });
    }


}
