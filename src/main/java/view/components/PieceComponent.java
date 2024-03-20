package view.components;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

// PieceComponent pourrait ressembler à ceci
public class PieceComponent extends JLabel {
    private BufferedImage image;

    public PieceComponent(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'exception comme vous le souhaitez ici
        }
    }
    
    // Vous pouvez ajouter des méthodes pour mettre à jour l'image, etc.
}