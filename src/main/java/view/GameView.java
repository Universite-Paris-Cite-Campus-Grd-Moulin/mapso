package view;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.GameController;
import model.Plateau;
import view.components.BoardPanel;

public class GameView extends JFrame {
    private BoardPanel boardPanel;
    private BufferedImage spriteSheet;
    private final int SPRITE_WIDTH = 100; // Ajustez en fonction de la largeur de vos sprites
    private final int SPRITE_HEIGHT = 100; // Ajustez en fonction de la hauteur de vos sprites
    private final int BOARD_COLUMNS = 10; // Nombre de colonnes du plateau
    private final int BOARD_ROWS = 8; // Nombre de lignes du plateauù
    private GameController gameController;

    public GameView() {
        setTitle("Khet Game");
        setSize(800, 600); // Ajustez selon vos besoins
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadSpriteSheet();
        createAndShowGUI();
        boardPanel = new BoardPanel();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                update(); // Mettre à jour l'affichage chaque fois que la fenêtre est redimensionnée
            }
        });
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(new File("ressources/sprites_khet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage getSprite(int x, int y) {
        if (spriteSheet == null) {
            throw new IllegalStateException("Sprite sheet not loaded.");
        }
        return spriteSheet.getSubimage(x * SPRITE_WIDTH, y * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    private void createAndShowGUI() {

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        JButton startButton = createButton("src/main/resources/images/button_start.png");
        JButton settingsButton = createButton("src/main/resources/images/button_setting.png");
        JButton exitButton = createButton("src/main/resources/images/button_exit.png");

        startButton.addActionListener(e -> showGameOptions());
        settingsButton.addActionListener(e -> openSettings());
        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0); // pas de marge extérieure

        // espace supérieur qui pousse les boutons vers le bas
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // prenez tout l'espace restant
        gbc.weighty = 1; // poids en Y élevé pour pousser les composants vers le bas
        gbc.fill = GridBagConstraints.VERTICAL; // étendre verticalement
        backgroundPanel.add(Box.createVerticalGlue(), gbc);

        // Réinitialiser le weighty pour les boutons
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE; // ne pas étendre les boutons
        gbc.gridwidth = 1; // chaque bouton dans une seule cellule de grille

        // Boutons centrés
        gbc.gridy++; // passer à la ligne suivante pour les boutons
        gbc.anchor = GridBagConstraints.CENTER; // centrer les boutons

        // Ajouter un espace horizontal invisible pour centrer les boutons
        gbc.weightx = 1;
        backgroundPanel.add(Box.createHorizontalGlue(), gbc);

        // Ajouter les boutons
        gbc.gridx++;
        backgroundPanel.add(startButton, gbc);

        gbc.gridx++;
        backgroundPanel.add(settingsButton, gbc);

        gbc.gridx++;
        backgroundPanel.add(exitButton, gbc);

        // espace inférieur qui prend 1/4 de l'espace
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weighty = 0.333; // poids y réduit pour occuper moins d'espace
        gbc.gridwidth = GridBagConstraints.REMAINDER; // étendre sur toute la largeur restante
        backgroundPanel.add(Box.createVerticalGlue(), gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String imagePath) {
        ImageIcon buttonIcon = new ImageIcon(imagePath);
        JButton button = new JButton(buttonIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    private void showGameOptions() {
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
            if (gameController != null) {
                gameController.changeGameMode("Classic");
                gameOptionsDialog.dispose();
            } else {
                System.out.println("GameController is null. Cannot change game mode.");
            }
        });
    
        imhotepButton.addActionListener(e -> {
            if (gameController != null) {
                gameController.changeGameMode("Imhotep");
                gameOptionsDialog.dispose();
            } else {
                System.out.println("GameController is null. Cannot change game mode.");
            }
        });
    
        dynastyButton.addActionListener(e -> {
            if (gameController != null) {
                gameController.changeGameMode("Dynastie");
                gameOptionsDialog.dispose();
            } else {
                System.out.println("GameController is null. Cannot change game mode.");
            }
        });
    
        gameOptionsDialog.setVisible(true);
    }

    

    public void displayNewBoard(Plateau plateau) {
        boardPanel.setPlateau(plateau); // Méthode hypothétique pour changer le plateau
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void openSettings() {
        // Logic to open game settings
    }

    class BackgroundPanel extends JPanel {
        private final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Fond_Khet.jpeg");

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
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
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setController(GameController controller) {
       this.gameController = controller;
    }

    
}
