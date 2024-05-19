package main.java.view.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.java.view.GameView;
import main.java.view.Menu;

public class GameOverBoard extends JPanel {
    private Image backgroundImage;
    private GameView gameView;

    public GameOverBoard() {
        initialize(null);
    }

    public GameOverBoard(GameView gameView) {
        this.gameView = gameView;
        initialize(gameView);
    }

    private void initialize(GameView gameView) {
        try {
            backgroundImage = Toolkit.getDefaultToolkit().createImage("src/main/resources/images/Fond_Khet.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(200, 50, 200, 50)); // Ajouter des marges

        JButton btnRestart = createCustomButtonWithIcon("src/main/resources/images/button_restart.png");
        JButton btnBackToMenu = createCustomButtonWithIcon("src/main/resources/images/button_back.png");
        JButton btnQuit = createCustomButtonWithIcon("src/main/resources/images/button_exit.png");

        btnRestart.setAlignmentX(CENTER_ALIGNMENT);
        btnBackToMenu.setAlignmentX(CENTER_ALIGNMENT);
        btnQuit.setAlignmentX(CENTER_ALIGNMENT);

        btnRestart.setMaximumSize(new Dimension(200, btnRestart.getMinimumSize().height));
        btnBackToMenu.setMaximumSize(new Dimension(200, btnBackToMenu.getMinimumSize().height));
        btnQuit.setMaximumSize(new Dimension(200, btnQuit.getMinimumSize().height));

        btnRestart.addActionListener(e -> showGameOptions(e));
        btnBackToMenu.addActionListener(e -> {
            if (gameView != null) {
                gameView.getMainFrame().getContentPane().removeAll();
                Menu menu = new Menu(gameView.getMainFrame());
                gameView.getMainFrame().setContentPane(menu);
                gameView.getMainFrame().revalidate();
                gameView.getMainFrame().repaint();
            }
        });
        btnQuit.addActionListener(e -> System.exit(0));

        add(Box.createVerticalGlue()); // Ajouter un espace flexible avant les boutons
        add(btnRestart);
        add(Box.createVerticalStrut(10)); // Ajouter un espace fixe entre les boutons
        add(btnBackToMenu); // Ajouter btnBackToMenu avant btnQuit
        add(Box.createVerticalStrut(10)); // Ajouter un espace fixe entre les boutons
        add(btnQuit); // Ajouter btnQuit après btnBackToMenu
        add(Box.createVerticalGlue()); // Ajouter un espace flexible après les boutons
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void showGameOptions(ActionEvent e) {
        JDialog gameOptionsDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Choose Game Mode", true);
        gameOptionsDialog.setLayout(new GridLayout(1, 0));
        gameOptionsDialog.setSize(400, 100);
        gameOptionsDialog.setLocationRelativeTo(null);
        gameOptionsDialog.setResizable(false);

        JButton classicButton = createCustomButton("Classic", new Color(70, 130, 180), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton imhotepButton = createCustomButton("Imhotep", new Color(239, 200, 127), Color.WHITE, new Font("Arial", Font.BOLD, 16));
        JButton dynastyButton = createCustomButton("Dynastie", new Color(255, 160, 122), Color.WHITE, new Font("Arial", Font.BOLD, 16));

        gameOptionsDialog.add(classicButton);
        gameOptionsDialog.add(imhotepButton);
        gameOptionsDialog.add(dynastyButton);

        classicButton.addActionListener(ev -> {
            if (gameView != null) {
                gameView.restartGame("Classique");
            }
            gameOptionsDialog.dispose();
        });
        imhotepButton.addActionListener(ev -> {
            if (gameView != null) {
                gameView.restartGame("Imhotep");
            }
            gameOptionsDialog.dispose();
        });
        dynastyButton.addActionListener(ev -> {
            if (gameView != null) {
                gameView.restartGame("Dynastie");
            }
            gameOptionsDialog.dispose();
        });

        gameOptionsDialog.setVisible(true);
    }

    public static JButton createCustomButton(String text, Color bgColor, Color fgColor, Font font) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        return button;
    }

    private static JButton createCustomButtonWithIcon(String imagePath) {
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
}
