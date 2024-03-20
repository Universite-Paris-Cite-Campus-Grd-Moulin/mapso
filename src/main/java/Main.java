import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Main {
    private JFrame mainFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        mainFrame = new JFrame("Game Menu");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        mainFrame.setContentPane(backgroundPanel);

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

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
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

        classicButton.addActionListener(e -> {/* Start Classique mode */ gameOptionsDialog.dispose();});
        imhotepButton.addActionListener(e -> {/* Start Imhotep mode */ gameOptionsDialog.dispose();});
        dynastyButton.addActionListener(e -> {/* Start Dynastie mode */ gameOptionsDialog.dispose();});

        gameOptionsDialog.setVisible(true);
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
}
