package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import model.NoeudTrajectoire;
import model.Plateau;
import model.enums.Couleur;
import view.components.BoardPanel;
import view.components.GameNavigationListener;

public class GameView extends JPanel implements GameNavigationListener {
    private BoardPanel boardPanel; // le plateau
    private JFrame mainFrame;
    private JLabel tour ; // label pour le tour du joueur X
    private String type ; // type du jeu ( Classic , dynastie ... )
    private static final int SPRITE_WIDTH = 100;
    private static final int SPRITE_HEIGHT = 100;
    private static final int BOARD_COLUMNS = 10;
    private static final int BOARD_ROWS = 8;

    public GameView( JFrame frame,String type) {
        this.mainFrame = frame ;
        this.type = type;
        setPreferredSize(new Dimension(1000, 650));

        setLayout(new BorderLayout());

            boardPanel = new BoardPanel(type,mainFrame,this); // Plateau


            JPanel otherPanel = new JPanel(); // boutons pause et tour du joueur X
            otherPanel.setPreferredSize(new Dimension(250,650));
            // Création et personnalisation du JLabel
            tour = new JLabel("Tour du Joueur " + boardPanel.getCurrentColor());
            tour.setFont(new Font("Arial", Font.BOLD, 20));
            // Ajouter une marge supérieure de 200 pixels au JLabel
            tour.setBorder(BorderFactory.createEmptyBorder(200, 30, 0, 20));
            otherPanel.add(tour);

            // Ajouter un espace de 300 pixels entre le JLabel et le bouton
            otherPanel.add(Box.createRigidArea(new Dimension(0, 300)));

            // Ajout du bouton de pause
            addPauseButton(otherPanel);

        add(boardPanel,BorderLayout.CENTER);
        add(otherPanel,BorderLayout.EAST);
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
        // Background gradient
        Graphics2D g2d = (Graphics2D) g;

        Color startColor = new Color(80, 198, 236); // Light Blue
        Color endColor = new Color(255, 160, 122);   // Shrimp
        GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if ( boardPanel != null ) {
            if ( boardPanel.getCurrentColor() == Couleur.JAUNE){
                tour.setForeground(new Color(255, 160, 122)); // Jaune
            } else if ( boardPanel.getCurrentColor() == Couleur.ROUGE ) {
                tour.setForeground(new Color(80, 198, 236)); // le RED
            }
        }
    }

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

        restartButton.addActionListener( e -> {
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
        // Cette méthode ferme le plateau de jeu et revient au menu principal
        //setContentPane(mainMenuPanel);
        revalidate();
        repaint();
    }

    public void update() {
        if (boardPanel != null) {
            boardPanel.repaint();
        }
        repaint();
    }

    // Méthode pour afficher un plateau de jeu, potentiellement pour initialiser BoardPanel avec des données
    public void displayBoard(Object[][] boardData) {

    }

    // Méthodes pour gérer les actions comme l'affichage des messages
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void onBackToMenuRequested() {
        //TO-DO
    }

    public void updateLaserPath(List<NoeudTrajectoire> cheminLaser, Couleur couleurLaser) {
        repaint();  // Pour redessiner l'interface si nécessaire
    }


    public void showWinner(Couleur currentPlayer) {
        // IF ( GAME OVER BOOLEAN , bah tu affiche le seul jueur restant avec JpOptionmessage
        throw new UnsupportedOperationException("Unimplemented method 'showWinner'");
    }

    public int getCellSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getCellSize'");
    }
}
