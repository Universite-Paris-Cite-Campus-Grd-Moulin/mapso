package view.components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PieceComponent extends JLabel {
    private BufferedImage image;

    public PieceComponent(String imagePath) {
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) {
        try {
            File file = new File(imagePath);
            if (file.exists()) {
                image = ImageIO.read(file);
                setIcon(new ImageIcon(image));
            } else {
                handleMissingImage();
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            handleMissingImage();
        }
    }

    private void handleMissingImage() {
        // Log an error or display a default image
        System.err.println("Image file not found, displaying default image.");
        try {
            image = ImageIO.read(getClass().getResource("/path/to/default/image.png"));
            setIcon(new ImageIcon(image));
        } catch (IOException e) {
            System.err.println("Failed to load default image: " + e.getMessage());
            this.setText("Image not available");
        }
    }
}
