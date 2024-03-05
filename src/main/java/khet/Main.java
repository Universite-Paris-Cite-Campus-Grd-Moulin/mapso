import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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

        placeComponents(backgroundPanel, startButton, settingsButton, exitButton);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void placeComponents(BackgroundPanel panel, JButton start, JButton settings, JButton exit) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        gbc.insets = new Insets(20, 20, 20, 20);
        panel.add(start, gbc);
        panel.add(settings, gbc);
        panel.add(exit, gbc);
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
        gameOptionsDialog.setLayout(new GridLayout(0, 1));
        gameOptionsDialog.setSize(300, 200);
        gameOptionsDialog.setLocationRelativeTo(mainFrame);

        JButton classicButton = new JButton("Classique");
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
