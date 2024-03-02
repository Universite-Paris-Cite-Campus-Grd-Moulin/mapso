package khet.view.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PieceComponent extends JLabel {

    public PieceComponent(String imagePath) {
        // Tente de charger l'image
        java.net.URL imgUrl = getClass().getClassLoader().getResource(imagePath);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            setIcon(icon);
        } else {
            System.err.println("Le fichier d'image n'a pas été trouvé: " + imagePath);
        }
    }
}
