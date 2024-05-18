package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameController;
import model.Plateau;
import model.enums.Couleur;
import view.components.BoardPanel;
import view.components.GameNavigationListener;

public class GameView extends JFrame implements GameNavigationListener {
    private JPanel mainMenuPanel;
    private BoardPanel boardPanel;
    private GameController controller;

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    private JFrame mainFrame;
    private BufferedImage spriteSheet;
    private static final int SPRITE_WIDTH = 100;
    private static final int SPRITE_HEIGHT = 100;
    private static final int BOARD_COLUMNS = 10;
    private static final int BOARD_ROWS = 8;
    private final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Fond_Khet.png");
    private JButton startButton, settingsButton, exitButton;

    public GameView() {
        System.out.println("Initialisation de la vue du jeu.");
        setTitle("Khet Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setContentPane(new BackgroundPanel());
        // Utilisation de BackgroundPanel comme content pane
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        initMainMenu(); // Assurez-vous que cette méthode est appelée
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadSpriteSheet() {
        try {
            System.out.println("Chargement du sprite sheet.");
            spriteSheet = ImageIO.read(new File("src/main/resources/images/Fond_Khet.png"));
            System.out.println("Sprite sheet chargé avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du sprite sheet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initMainMenu() {
        System.out.println("Initialisation du menu principal.");
        mainMenuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_END;

        // Ajoute un espace pour pousser les composants vers le bas
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        gbc.weighty = 1;
        mainMenuPanel.add(spacer, gbc); // Le spacer est ajouté en premier

        // Réinitialise le poids y pour les boutons
        gbc.weighty = 0;

        startButton = createButton("src/main/resources/images/button_start.png");
        settingsButton = createButton("src/main/resources/images/button_setting.png");
        exitButton = createButton("src/main/resources/images/button_exit.png");

        startButton.addActionListener(e -> showGameOptions());
        settingsButton.addActionListener(e -> openSettings());
        exitButton.addActionListener(e -> System.exit(0));

        // Maintenant, ajoutez les boutons
        gbc.gridy = GridBagConstraints.RELATIVE; // Utilise RELATIVE pour une meilleure gestion des positions
        mainMenuPanel.add(startButton, gbc);
        mainMenuPanel.add(settingsButton, gbc);
        mainMenuPanel.add(exitButton, gbc);

        getContentPane().add(mainMenuPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private JButton createButton(String imagePath) {
        System.out.println("Création d'un bouton avec l'image: " + imagePath);
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image image = originalIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon buttonIcon = new ImageIcon(image);

        JButton button = new JButton(buttonIcon);
        button.setPreferredSize(new Dimension(100, 50));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    private void showGameOptions() {
        System.out.println("Affichage des options de jeu.");
        JDialog gameOptionsDialog = new JDialog(this, "Choose Game Mode", true);
        gameOptionsDialog.setLayout(new GridLayout(1, 0));
        gameOptionsDialog.setSize(300, 200);
        gameOptionsDialog.setLocationRelativeTo(this);

        JButton classicButton = new JButton("Classic");
        JButton imhotepButton = new JButton("Imhotep");
        JButton dynastyButton = new JButton("Dynastie");

        gameOptionsDialog.add(classicButton);
        gameOptionsDialog.add(imhotepButton);
        gameOptionsDialog.add(dynastyButton);

        classicButton.addActionListener(e -> {
            openBoard("Classic");
            gameOptionsDialog.dispose();
        });
        imhotepButton.addActionListener(e -> {
            openBoard("Imhotep");
            gameOptionsDialog.dispose();
        });
        dynastyButton.addActionListener(e -> {
            openBoard("Dynastie");
            gameOptionsDialog.dispose();
        });

        gameOptionsDialog.setVisible(true);
    }

    private void openSettings() {
        System.out.println("Ouverture des paramètres.");
        // Ajoutez la logique pour ouvrir les paramètres ici
    }

    private void openBoard(String type) {
        System.out.println("Ouverture du plateau de jeu de type: " + type);
        getContentPane().removeAll();
        // Création et ajout du plateau de jeu
        Plateau plateau = new Plateau(type);
        controller = new GameController(plateau);
        boardPanel = new BoardPanel(plateau, controller, this);
        setContentPane(boardPanel);
        revalidate();
        repaint();
    }

    public void closeBoard() {
        System.out.println("Fermeture du plateau de jeu et retour au menu principal.");
        // Cette méthode ferme le plateau de jeu et revient au menu principal
        setContentPane(mainMenuPanel);
        revalidate();
        repaint();
    }

    public void update() {
        System.out.println("Mise à jour de l'interface utilisateur.");
        // Ajoutez ici le code pour mettre à jour l'interface utilisateur si nécessaire
    }

    public void displayBoard(Object[][] boardData) {
        System.out.println("Affichage du plateau de jeu.");
        // Ajoutez ici le code pour afficher le plateau de jeu
    }

    public void displayMessage(String message) {
        System.out.println("Affichage d'un message: " + message);
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void onBackToMenuRequested() {
        System.out.println("Retour au menu principal demandé.");
        // Ajoutez ici la logique pour retourner au menu principal
    }

    public void updateLaserPath(List<Point> cheminLaser, Couleur couleurLaser) {
        System.out.println("Mise à jour du chemin du laser de couleur: " + couleurLaser);
        if (boardPanel != null) {
            // boardPanel.updateLaserPath(cheminLaser, couleurLaser);
            repaint(); // Pour redessiner l'interface si nécessaire
        } else {
            System.err.println("Erreur: boardPanel est null.");
        }
    }

    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                System.out.println("Peinture du panneau de fond.");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public void showWinner(Couleur currentPlayer) {
        System.out.println("Affichage du gagnant: " + currentPlayer);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showWinner'");
    }

    public int getCellSize() {
        System.out.println("Obtention de la taille des cellules.");
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCellSize'");
    }

    public static void main(String[] args) {
        GameView view = new GameView();
        view.setVisible(true);
    }
}
