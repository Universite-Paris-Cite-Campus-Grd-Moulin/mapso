package khet;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        // Utilisez SwingUtilities.invokeLater pour assurer que l'interface graphique est créée dans le thread de distribution d'événements (EDT).
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Khet Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            KhetBoard khetBoard = new KhetBoard();     //Instancier le plateau de jeu.
            frame.add(khetBoard);                      // Ajouter le plateau de jeu à la fenêtre principale.
            frame.pack();                               // Adapter la taille de la fenêtre principale aux composants qu'elle contient.
            frame.setLocationRelativeTo(null);        // Centrer la fenêtre principale par rapport à l'écran.
            frame.setVisible(true);                   // Rendre la fenêtre principale visible.

        });
    }
}
