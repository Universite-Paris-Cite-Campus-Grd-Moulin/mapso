package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.NoeudTrajectoire;
import model.Observer;
import model.enums.Couleur;
import view.components.BoardPanel;
import view.components.GameNavigationListener;
import view.components.GameOverBoard;

public class GameView extends JPanel implements GameNavigationListener, Observer {
    private BoardPanel boardPanel;
    private JFrame mainFrame;
    private JLabel tour;
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft = 30;
    private boolean isPaused = false;

    public GameView(JFrame frame, String type) {
        this.mainFrame = frame;
        setPreferredSize(new Dimension(1000, 650));

        setLayout(new BorderLayout());

        boardPanel = new BoardPanel(type, mainFrame, this);

        boardPanel.getBoard().addObserver(this);

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
        }
    }

    private void showPauseMenu() {
        JDialog pauseMenu = new JDialog(mainFrame, "Pause", true);
        pauseMenu.setLayout(new GridLayout(4, 1));
        pauseMenu.setSize(200, 200);
        pauseMenu.setLocationRelativeTo(mainFrame);
        pauseMenu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                resumeTimer();
            }
        });
        addPauseMenuButtons(pauseMenu);
        pauseMenu.setVisible(true);
    }

    private void addPauseButton(JPanel panel) {
        JButton pauseButton = Menu.createCustomButton("Pause", new Color(255, 172, 250), new Color(255, 255, 255), new Font("Arial", Font.BOLD, 16));
        pauseButton.setPreferredSize(new Dimension(100, 40));

        pauseButton.addActionListener(e -> {
            pauseTimer();
            showPauseMenu();
        });

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

        continueButton.addActionListener(e -> {
            pauseMenu.dispose();
            resumeTimer();
        });

        restartButton.addActionListener(e -> {
            showGameOptions(e);
            pauseMenu.dispose();  // Fermer la fenêtre de pause après le redémarrage du jeu
            resumeTimer();
        });

        backToMenuButton.addActionListener(e -> {
            pauseMenu.dispose();
            mainFrame.getContentPane().removeAll();
            Menu menu = new Menu(mainFrame);
            mainFrame.setContentPane(menu);
            mainFrame.revalidate();
            mainFrame.repaint();
            resumeTimer();
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

    private void showGameOptions(ActionEvent event) {
        JDialog gameOptionsDialog = new JDialog(mainFrame, "Choose Game Mode", true);
        gameOptionsDialog.setLayout(new GridLayout(1, 0));
        gameOptionsDialog.setSize(400, 100);
        gameOptionsDialog.setLocationRelativeTo(mainFrame);
        gameOptionsDialog.setResizable(false);

        JButton classicButton = createCustomButton("Classic", new Color(70, 130, 180), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton imhotepButton = createCustomButton("Imhotep", new Color(239, 200, 127), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton dynastyButton = createCustomButton("Dynastie", new Color(255, 160, 122), Color.WHITE, new Font("Arial", Font.BOLD, 16));

        gameOptionsDialog.add(classicButton);
        gameOptionsDialog.add(imhotepButton);
        gameOptionsDialog.add(dynastyButton);

        classicButton.addActionListener(ev -> {
            openBoard("Classic");
            gameOptionsDialog.dispose();
        });
        imhotepButton.addActionListener(ev -> {
            openBoard("Imhotep");
            gameOptionsDialog.dispose();
        });
        dynastyButton.addActionListener(ev -> {
            openBoard("Dynastie");
            gameOptionsDialog.dispose();
        });
        gameOptionsDialog.setVisible(true);
    }

    static JButton createCustomButton(String text, Color background, Color foreground, Font font) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    private void openBoard(String type) {
        mainFrame.getContentPane().removeAll();
        // Création et ajout de la vue du jeu
        GameView jeu = new GameView(mainFrame, type);
        mainFrame.setContentPane(jeu);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void update() {
        if (boardPanel != null) {
            boardPanel.repaint();
        }
        repaint();
    }

    public void displayBoard(Object[][] boardData) {
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void onBackToMenuRequested() {
    }

    public void updateLaserPath(List<NoeudTrajectoire> cheminLaser, Couleur couleurLaser) {
        repaint();
    }

    public void showWinner(Couleur currentPlayer) {
        throw new UnsupportedOperationException("Unimplemented method 'showWinner'");
    }

    private void initializeTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0 && !isPaused) {
                    timeLeft--;
                } else if (timeLeft == 0) {
                    switchPlayer();  // Change de joueur lorsque le temps est écoulé
                }
                timerLabel.setText("Temps restant: " + timeLeft + "s");
            }
        });
        timer.start();
    }

    private void pauseTimer() {
        isPaused = true;
    }

    private void resumeTimer() {
        isPaused = false;
    }

    public void resetTimer() {
        timeLeft = 30;
        timerLabel.setText("Temps restant: " + timeLeft + "s");
    }

    public void switchPlayer() {
        boardPanel.getGame().switchPlayer();
        resetTimer();
        updateTourLabel();
    }

    public void updateTourLabel() {
        if (boardPanel.getCurrentColor() == Couleur.JAUNE) {
            tour.setText("Tour du Joueur Jaune");
            tour.setForeground(new Color(255, 160, 122));
        } else {
            tour.setText("Tour du Joueur Rouge");
            tour.setForeground(new Color(80, 198, 236));
        }
    }

    public void showGameOver(Couleur couleurGagnante) {
        System.out.println("showGameOver appelé avec couleurGagnante: " + couleurGagnante); // Message de débogage

        // Arrêter le timer
        timer.stop();

        // Afficher le message de fin de jeu avec un bouton OK
        String message = "Le Pharaon " + couleurGagnante + " a été touché! Fin du jeu.";
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        mainFrame.getContentPane().removeAll();
        System.out.println("Game Over enclenché");
        GameOverBoard gameOverBoard = new GameOverBoard(this);
        mainFrame.setContentPane(gameOverBoard);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    @Override
    public void updateGameOver(Couleur couleur) {
        showGameOver(couleur);
    }

    public int getCellSize() {
        return 75;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void restartGame(String type) {
        mainFrame.getContentPane().removeAll();
        GameView newGameView = new GameView(mainFrame, type);
        mainFrame.setContentPane(newGameView);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
