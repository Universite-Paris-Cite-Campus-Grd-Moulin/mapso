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
        System.out.println("Création d'un PieceComponent avec le chemin d'image : " + imagePath);
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) {
        try {
            File file = new File(imagePath);
            if (file.exists()) {
                System.out.println("Chargement de l'image : " + imagePath);
                image = ImageIO.read(file);
                setIcon(new ImageIcon(image));
            } else {
                System.out.println("Image non trouvée, chargement de l'image par défaut.");
                handleMissingImage();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
            handleMissingImage();
        }
    }

    private void handleMissingImage() {
        System.err.println("Fichier d'image non trouvé, affichage de l'image par défaut.");
        try {
            image = ImageIO.read(getClass().getResource("/path/to/default/image.png"));
            setIcon(new ImageIcon(image));
        } catch (IOException e) {
            System.err.println("Échec du chargement de l'image par défaut : " + e.getMessage());
            this.setText("Image non disponible");
        }
    }
}
