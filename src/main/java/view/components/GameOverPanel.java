package main.java.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.view.GameView;

public class GameOverPanel extends JPanel {
    private Image backgroundImage;
    private JButton okButton;
    private GameView gameView;

    public GameOverPanel(GameView gameView, String loserColor, Runnable okAction) {
        this.gameView = gameView;

        try {
            Image originalImage = Toolkit.getDefaultToolkit().createImage("src/main/resources/images/GameOverPhoto.png");
            backgroundImage = originalImage.getScaledInstance(1100, 650, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 50, 20, 50));

        String winnerColor = (loserColor.equals("BLEU")) ? "ORANGE" : "BLEU";
        String colorText = (winnerColor.equals("BLEU")) ? "<font color='blue'>" + winnerColor + "</font>" : "<font color='orange'>" + winnerColor + "</font>";
        String message = "<html>Le joueur " + colorText + " a gagné! Fin du jeu.</html>";

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Créer le bouton personnalisé
        okButton = createCustomButton("Continuer", new Color(58, 126, 234), Color.WHITE, new Font("Times New Roman", Font.BOLD, 30));
        okButton.setAlignmentX(CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            okAction.run();
            gameView.showGameOverBoard();
        });

        add(Box.createVerticalStrut(20));
        add(messageLabel);
        add(Box.createVerticalGlue());
        add(okButton);
        add(Box.createRigidArea(new Dimension(0, 50)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    // Méthode pour créer un bouton personnalisé
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
}

