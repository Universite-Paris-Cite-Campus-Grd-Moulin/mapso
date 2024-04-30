package view.components;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.GameController;

public class GameOverBoard extends JPanel {
    private Image backgroundImage;
    private GameController gameController;

    public GameOverBoard() {
        try {
            backgroundImage = Toolkit.getDefaultToolkit().createImage("ressources/GameOver.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 300));

        JButton btnRestart = new JButton("Restart");
        JButton btnQuit = new JButton("Quit");
        JButton btnBackToMenu = new JButton("Back to Menu");

        btnRestart.addActionListener(this::showGameOptions);
        btnQuit.addActionListener(e -> System.exit(0));
        btnBackToMenu.addActionListener(e -> {
            // Code pour retourner au menu principal
        });

        add(btnRestart);
        add(btnQuit);
        add(btnBackToMenu);
    }

    public GameOverBoard(GameController gameController) {
        this.gameController = gameController;
        try {
            backgroundImage = Toolkit.getDefaultToolkit().createImage("ressources/GameOver.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 300));

        JButton btnRestart = new JButton("Restart");
        JButton btnQuit = new JButton("Quit");
        JButton btnBackToMenu = new JButton("Back to Menu");

        btnRestart.addActionListener(this::showGameOptions);
        btnQuit.addActionListener(e -> System.exit(0));
        btnBackToMenu.addActionListener(e -> {
            // Code pour retourner au menu principal
        });

        add(btnRestart);
        add(btnQuit);
        add(btnBackToMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void showGameOptions(ActionEvent e) {
        JDialog gameOptionsDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Choose Game Mode",
                true);
        gameOptionsDialog.setLayout(new GridLayout(3, 1));
        gameOptionsDialog.setSize(300, 200);
        gameOptionsDialog.setLocationRelativeTo(null);

        JButton classicButton = new JButton("Classique");
        JButton imhotepButton = new JButton("Imhotep");
        JButton dynastyButton = new JButton("Dynastie");

        classicButton.addActionListener(ev -> gameController.restartGame("Classique"));
        imhotepButton.addActionListener(ev -> gameController.restartGame("Imhotep"));
        dynastyButton.addActionListener(ev -> gameController.restartGame("Dynastie"));

        gameOptionsDialog.add(classicButton);
        gameOptionsDialog.add(imhotepButton);
        gameOptionsDialog.add(dynastyButton);

        gameOptionsDialog.setVisible(true);
    }

    private void openGameMode(String mode) {
        System.out.println("Starting " + mode + " mode");
        // Ici, vous pouvez ajouter le code pour initialiser et afficher le jeu dans le
        // mode choisi.
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 720);
        frame.setLocationRelativeTo(null);
        frame.add(new GameOverBoard());
        frame.setVisible(true);
    }
}
