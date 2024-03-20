package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteDimensions {

    public static void main(String[] args) {
        try {
            // Charger l'image complète de sprites
            BufferedImage spriteSheet = ImageIO.read(new File("/ressources/sprites_khet.png"));
            
            // Afficher les dimensions de l'image entière
            System.out.println("Dimensions de l'image entière: " + spriteSheet.getWidth() + "x" + spriteSheet.getHeight());

            // Si vous connaissez le nombre de sprites en largeur et en hauteur, divisez pour obtenir la taille d'un sprite
            int spritesHorizontal = 10; // Nombre de sprites horizontalement
            int spritesVertical = 2;    // Nombre de sprites verticalement

            int spriteWidth = spriteSheet.getWidth() / spritesHorizontal;
            int spriteHeight = spriteSheet.getHeight() / spritesVertical;

            System.out.println("Dimensions d'un sprite: " + spriteWidth + "x" + spriteHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
