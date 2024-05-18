package view;

<<<<<<< Updated upstream
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import controller.GameController;
import model.Game;
=======
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.Timer;

import model.NoeudTrajectoire;
import model.Plateau;
import model.enums.Couleur;
>>>>>>> Stashed changes
import view.components.BoardPanel;

<<<<<<< Updated upstream
public class GameView extends JFrame {
    private BoardPanel boardPanel;
    private BufferedImage spriteSheet;
    private final int SPRITE_WIDTH = 100; // Ajustez en fonction de la largeur de vos sprites
    private final int SPRITE_HEIGHT = 100; // Ajustez en fonction de la hauteur de vos sprites
    private final int BOARD_COLUMNS = 10; // Nombre de colonnes du plateau
    private final int BOARD_ROWS = 8; // Nombre de lignes du plateau

    public GameView() {
        setTitle("Khet Game");
        setSize(800, 600); // Ajustez selon vos besoins
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadSpriteSheet();
        initUI();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                update(); // Mettre à jour l'affichage chaque fois que la fenêtre est redimensionnée
            }
        });
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(new File("path/to/your/sprites_khet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getSprite(int x, int y) {
        if (spriteSheet == null) {
            throw new IllegalStateException("Sprite sheet not loaded.");
=======
public class GameView extends JPanel implements GameNavigationListener {
    private BoardPanel boardPanel;
    private JFrame mainFrame;
    private JLabel tour;
    private JLabel timerLabel;  // Ajout du JLabel pour le timer
    private String type;
    private static final int SPRITE_WIDTH = 100;
    private static final int SPRITE_HEIGHT = 100;
    private static final int BOARD_COLUMNS = 10;
    private static final int BOARD_ROWS = 8;

    private Timer timer;  // Timer pour le compte à rebours
    private int timeLeft = 30;  // Temps restant en secondes pour chaque joueur

    public GameView(JFrame frame, String type) {
        this.mainFrame = frame;
        this.type = type;
        setPreferredSize(new Dimension(1000, 650));

        setLayout(new BorderLayout());

        boardPanel = new BoardPanel(type, mainFrame, this);

        JPanel otherPanel = new JPanel();
        otherPanel.setPreferredSize(new Dimension(250, 650));

        tour = new JLabel("Tour du Joueur " + boardPanel.getCurrentColor());
        tour.setFont(new Font("Arial", Font.BOLD, 20));
        tour.setBorder(BorderFactory.createEmptyBorder(200, 30, 0, 20));
        otherPanel.add(tour);

        timerLabel = new JLabel("Temps restant: " + timeLeft + "s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        otherPanel.add(timerLabel);

        otherPanel.add(Box.createRigidArea(new Dimension(0, 300)));

        addPauseButton(otherPanel);

        add(boardPanel, BorderLayout.CENTER);
        add(otherPanel, BorderLayout.EAST);

        initializeTimer();
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    private void initializeTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                } else {
                    switchPlayer();  // Change de joueur lorsque le temps est écoulé
                }
                timerLabel.setText("Temps restant: " + timeLeft + "s");
            }
        });
        timer.start();
    }

    public void resetTimer() {
        timeLeft = 30;
        timerLabel.setText("Temps restant: " + timeLeft + "s");
    }

    private void switchPlayer() {
        boardPanel.getGame().switchPlayer();
        resetTimer();
        updateTourLabel();
    }

    private void updateTourLabel() {
        if (boardPanel.getCurrentColor() == Couleur.JAUNE) {
            tour.setText("Tour du Joueur Jaune");
            tour.setForeground(new Color(255, 160, 122));
        } else {
            tour.setText("Tour du Joueur Rouge");
            tour.setForeground(new Color(80, 198, 236));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Color startColor = new Color(80, 198, 236);
        Color endColor = new Color(255, 160, 122);
        GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (boardPanel != null) {
            updateTourLabel();
>>>>>>> Stashed changes
        }
        return spriteSheet.getSubimage(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

<<<<<<< Updated upstream
    private void initUI() {
        boardPanel = new BoardPanel();
        add(boardPanel);
    }

     public void setGame(Game game) {
        boardPanel.setGame(game);  // Méthode à ajouter dans BoardPanel pour relier le jeu au panneau
        update();  // Mise à jour immédiate de l'affichage
    }

    public void setController(GameController controller) {
        boardPanel.setController(controller); // Set controller in BoardPanel
    }

    public void displayBoard(Object[][] boardData) {
        boardPanel.removeAll();
        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                BufferedImage sprite = getSprite(0, 0);
                JLabel label = new JLabel(new ImageIcon(sprite));
                boardPanel.add(label);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void update() {
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void createAndShowGUI() {
        setVisible(true);
    }

    // Cette méthode calcule et renvoie la taille d'une cellule du plateau
    public int getCellSize() {
        // Calcul basé sur la taille actuelle de GameView et le nombre de cellules
        int cellWidth = this.getWidth() / BOARD_COLUMNS;
        int cellHeight = this.getHeight() / BOARD_ROWS;
        // Retourne la plus petite dimension pour s'assurer que les cellules restent
        // carrées
        return Math.min(cellWidth, cellHeight);
    }

    public int getBoardColumns() {
        return BOARD_COLUMNS;
    }

    public int getBoardRows() {
        return BOARD_ROWS;
=======
    private void showPauseMenu() {
        JDialog pauseMenu = new JDialog(mainFrame, "Pause", true);
        pauseMenu.setLayout(new GridLayout(4, 1));
        pauseMenu.setSize(200, 200);
        pauseMenu.setLocationRelativeTo(mainFrame);
        addPauseMenuButtons(pauseMenu);
        pauseMenu.setVisible(true);
    }

    private void addPauseButton(JPanel panel) {
        JButton pauseButton = Menu.createCustomButton("Pause", new Color(255, 172, 250), new Color(255, 255, 255), new Font("Arial", Font.BOLD, 16));
        pauseButton.setPreferredSize(new Dimension(100, 40));

        pauseButton.addActionListener(e -> showPauseMenu());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(pauseButton);

        panel.add(buttonPanel);
    }

    private void addPauseMenuButtons(JDialog pauseMenu) {
        JButton continueButton = Menu.createCustomButton("Back To Game", new Color(70, 130, 180), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton restartButton = Menu.createCustomButton("Restart", new Color(50, 142, 224), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton backToMenuButton = Menu.createCustomButton("Back To Menu", new Color(16, 76, 126), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton exitButton = Menu.createCustomButton("Exit", new Color(11, 43, 66), Color.WHITE, new Font("Arial", Font.BOLD, 16));

        continueButton.addActionListener(e -> pauseMenu.dispose());

        restartButton.addActionListener(e -> {
            boardPanel.initBoard();
            pauseMenu.dispose();
            repaint();
        });

        backToMenuButton.addActionListener(e -> {
            pauseMenu.dispose();
            mainFrame.getContentPane().removeAll();
            Menu menu = new Menu(mainFrame);
            mainFrame.setContentPane(menu);
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        exitButton.addActionListener(e -> System.exit(0));

        pauseMenu.add(continueButton);
        pauseMenu.add(restartButton);
        pauseMenu.add(backToMenuButton);
        pauseMenu.add(exitButton);
    }

    public void closeBoard() {
        revalidate();
        repaint();
    }

    public void update() {
        if (boardPanel != null) {
            boardPanel.repaint();
        }
        repaint();
    }

    public void displayBoard(Object[][] boardData) {
>>>>>>> Stashed changes
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

<<<<<<< Updated upstream
=======
    @Override
    public void onBackToMenuRequested() {
        // TODO
    }

    public void updateLaserPath(List<NoeudTrajectoire> cheminLaser, Couleur couleurLaser) {
        repaint();
    }

    public void showWinner(Couleur currentPlayer) {
        throw new UnsupportedOperationException("Unimplemented method 'showWinner'");
    }

    public int getCellSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getCellSize'");
    }
>>>>>>> Stashed changes
}
