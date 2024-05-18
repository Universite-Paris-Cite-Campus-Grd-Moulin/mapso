package view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private JButton startButton, settingsButton, exitButton;
    private JFrame mainFrame;
    private final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Fond_Khet.png");

    public Menu(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_END;

        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        gbc.weighty = 1;
        add(spacer, gbc);

        gbc.weighty = 0;

        startButton = createButton("src/main/resources/images/button_start.png");
        settingsButton = createButton("src/main/resources/images/Settings.png");
        exitButton = createButton("src/main/resources/images/button_exit.png");

        startButton.addActionListener(e -> showGameOptions());
        settingsButton.addActionListener(e -> openSettings());
        exitButton.addActionListener(e -> System.exit(0));

        gbc.gridy = GridBagConstraints.RELATIVE;
        add(startButton, gbc);
        add(settingsButton, gbc);
        add(exitButton, gbc);

        mainFrame.add(this);
    }

    private JButton createButton(String imagePath) {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    private void showGameOptions() {
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

    private void openSettings() {
        mainFrame.getContentPane().removeAll();
        Settings settingsPanel = new Settings(mainFrame);
        mainFrame.setContentPane(settingsPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void openBoard(String type) {
        mainFrame.getContentPane().removeAll();
        GameView jeu = new GameView(mainFrame, type);
        mainFrame.setContentPane(jeu);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
