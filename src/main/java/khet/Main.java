import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        JFrame frame = new JFrame("Game Menu");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panneau pour le fond d'écran et les boutons
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);

        // Boutons avec images
        JButton startButton = createButton("src/main/resources/images/button_start.png");
        JButton settingsButton = createButton("src/main/resources/images/button_setting.png");
        JButton exitButton = createButton("src/main/resources/images/button_exit.png");

        // Écouteurs pour les boutons
        startButton.addActionListener(e -> startGame());
        settingsButton.addActionListener(e -> openSettings());
        exitButton.addActionListener(e -> System.exit(0));

        // Positionnement des boutons horizontalement au milieu et un peu plus haut dans la partie inférieure de la fenêtre
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, frame.getHeight() / 4, 10); // Ajustez cette ligne pour l'espace vertical
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.SOUTH; // Aligner au sud (en bas)
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;
        gbc.weighty = 0.5; // Poids y pour pousser vers le haut

        // Ajouter un espace invisible à gauche pour centrer les boutons
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(Box.createHorizontalGlue(), gbc);

        // Position pour startButton
        gbc.gridx = 1;
        backgroundPanel.add(startButton, gbc);

        // Position pour settingsButton
        gbc.gridx = 2;
        backgroundPanel.add(settingsButton, gbc);

        // Position pour exitButton
        gbc.gridx = 3;
        backgroundPanel.add(exitButton, gbc);

        // Ajouter un espace invisible à droite pour centrer les boutons
        gbc.gridx = 4;
        backgroundPanel.add(Box.createHorizontalGlue(), gbc);

        frame.setLocationRelativeTo(null); // Centre la fenêtre
        frame.setVisible(true);
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
        // Créez une nouvelle fenêtre pour les options de jeu
        JDialog gameOptionsDialog = new JDialog(mainFrame, "Choose Game Mode", true);
        gameOptionsDialog.setSize(400, 300);
        gameOptionsDialog.setLayout(new FlowLayout());

        // Bouton pour le mode classique
        JButton classicButton = new JButton("Classique");
        classicButton.addActionListener(e -> {
            // Logique pour démarrer le mode classique
            gameOptionsDialog.dispose(); // Ferme la fenêtre des options
        });

        // Bouton pour le mode Imhotep
        JButton imhotepButton = new JButton("Imhotep");
        imhotepButton.addActionListener(e -> {
            // Logique pour démarrer le mode Imhotep
            gameOptionsDialog.dispose(); // Ferme la fenêtre des options
        });

        // Bouton pour le mode Dynastie
        JButton dynastyButton = new JButton("Dynastie");
        dynastyButton.addActionListener(e -> {
            // Logique pour démarrer le mode Dynastie
            gameOptionsDialog.dispose(); // Ferme la fenêtre des options
        });

        // Ajoutez les boutons au dialogue
        gameOptionsDialog.add(classicButton);
        gameOptionsDialog.add(imhotepButton);
        gameOptionsDialog.add(dynastyButton);

        // Affichez la fenêtre des options au centre de la fenêtre principale
        gameOptionsDialog.setLocationRelativeTo(mainFrame);
        gameOptionsDialog.setVisible(true);
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

    static class BackgroundPanel extends JPanel {
        private final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Fond_Khet.jpeg");

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private void startGame() {
        // Logique pour démarrer le jeu
    }

    private void openSettings() {
        // Logique pour ouvrir les paramètres du jeu
    }
}
