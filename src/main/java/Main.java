
import javax.swing.*;

import view.GameView;
import view.Menu;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Le JFRAME
            JFrame mainFrame = new JFrame();
            mainFrame.setTitle("Khet Game");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(1100, 650);
            mainFrame.setResizable(false);
            mainFrame.setLayout(new BorderLayout());

            // le Menu
            Menu game = new Menu(mainFrame); // Le menu
            mainFrame.setVisible(true);

        });
    }
}