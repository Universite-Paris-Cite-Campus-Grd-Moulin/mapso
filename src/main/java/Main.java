import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.Menu;

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
