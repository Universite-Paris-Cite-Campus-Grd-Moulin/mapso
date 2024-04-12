package view.components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

}