package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.Plateau;
import view.components.BoardPanel;
import view.components.GameNavigationListener;

public class GameView extends JFrame implements GameNavigationListener {
    private JPanel mainMenuPanel;
    private BoardPanel boardPanel;
    private JFrame mainFrame;
    private BufferedImage spriteSheet;
    private static final int SPRITE_WIDTH = 100;
    private static final int SPRITE_HEIGHT = 100;
    private static final int BOARD_COLUMNS = 10;
    private static final int BOARD_ROWS = 8;
    private final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Fond_Khet.jpeg");
    private JButton startButton, settingsButton, exitButton;
    public GameView() {
        setTitle("Khet Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800, 1024);
        setLayout(new BorderLayout());
        setContentPane(new BackgroundPanel());
        initMainMenu(); // Assurez-vous que cette méthode est appelée
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(new File("ressources/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMainMenu() {
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

        startButton.addActionListener(e -> openBoard("Classic"));
        settingsButton.addActionListener(e -> openSettings());
        exitButton.addActionListener(e -> System.exit(0));

        // Maintenant, ajoutez les boutons
        gbc.gridy = GridBagConstraints.RELATIVE; // Utilise RELATIVE pour une meilleure gestion des positions
        mainMenuPanel.add(startButton, gbc);
        mainMenuPanel.add(settingsButton, gbc);
        mainMenuPanel.add(exitButton, gbc);

        setContentPane(mainMenuPanel); // Ajoute le mainMenuPanel au JFrame
        revalidate();
        repaint();
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

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.contains("start")) {
            // Logique pour démarrer le jeu
        } else if (command.contains("setting")) {
            // Logique pour ouvrir les paramètres
        } else if (command.contains("exit")) {
            System.exit(0);
        }
    }

    private void placeComponents(BackgroundPanel panel, JButton start, JButton settings, JButton exit) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1; // set gridwidth to 1 for each button
        gbc.gridx = 0; // start with first column
        gbc.gridy = 0; // all in the first row
        gbc.weightx = 1; // distribute space
        gbc.fill = GridBagConstraints.NONE; // do not resize the button
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.PAGE_END; // anchor buttons to the bottom of the screen

        panel.add(Box.createHorizontalGlue(), gbc);

        gbc.gridx++;
        panel.add(start, gbc);

        gbc.gridx++;
        panel.add(settings, gbc);

        gbc.gridx++;
        panel.add(exit, gbc);

        gbc.gridx++;
        panel.add(Box.createHorizontalGlue(), gbc);
    }

    private void layoutComponents() {
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());

        JButton startButton = createButton("src/main/resources/images/button_start.png");
        JButton settingsButton = createButton("src/main/resources/images/button_setting.png");
        JButton exitButton = createButton("src/main/resources/images/button_exit.png");

        startButton.addActionListener(e -> showGameOptions());
        settingsButton.addActionListener(e -> openSettings());
        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        backgroundPanel.add(Box.createVerticalGlue(), gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        backgroundPanel.add(Box.createHorizontalGlue(), gbc);

        gbc.gridx++;
        backgroundPanel.add(startButton, gbc);

        gbc.gridx++;
        backgroundPanel.add(settingsButton, gbc);

        gbc.gridx++;
        backgroundPanel.add(exitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weighty = 0.333;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        backgroundPanel.add(Box.createVerticalGlue(), gbc);

        mainFrame.setContentPane(backgroundPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    private void showGameOptions() {
        JDialog gameOptionsDialog = new JDialog(mainFrame, "Choose Game Mode", true);
        gameOptionsDialog.setLayout(new GridLayout(1, 0));
        gameOptionsDialog.setSize(300, 200);
        gameOptionsDialog.setLocationRelativeTo(mainFrame);

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
    }

    private void openBoard(String type) {
        getContentPane().removeAll();
        // Création et ajout du plateau de jeu
        BoardPanel boardPanel = new BoardPanel(new Plateau(type), this,this);
        setContentPane(boardPanel);
        revalidate();
        repaint();
    }

    public void closeBoard() {
        // Cette méthode ferme le plateau de jeu et revient au menu principal
        setContentPane(mainMenuPanel);
        revalidate();
        repaint();
    }

    public void update() {
        // ... code pour mettre à jour l'UI
    }

    // Méthode pour afficher un plateau de jeu, potentiellement pour initialiser BoardPanel avec des données
    public void displayBoard(Object[][] boardData) {

    }

    // La méthode main pour lancer l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView view = new GameView();
            view.setVisible(true);
            // ... peut-être un code pour afficher le plateau initial ou autre
        });
    }

    // Méthodes pour gérer les actions comme l'affichage des messages
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void onBackToMenuRequested() {


        //TO-DO
    }


    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }



}
