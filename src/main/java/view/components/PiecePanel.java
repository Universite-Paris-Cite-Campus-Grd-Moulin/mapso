package view.components;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Pion;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

// PieceComponent pourrait ressembler à ceci
public class PiecePanel extends JPanel {

    private static BufferedImage khet;
    static {
        try {
            khet = ImageIO.read(new File("ressources/sprites_khet.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., initialize khet with a default image or log the
            // error
        }
    }

    private Pion pion; // MVC et pas en attribut
    private BufferedImage image; // image du pion extrait

    public PiecePanel(Pion p) {
        this.pion = p;
        // this.image = null ; static
    }

    public static BufferedImage draw(java.awt.Graphics g, Pion p) {
        int colonne = p.getType().ordinal();
        int ligne = p.getCouleur().ordinal();
        int rotation = p.getDirection().ordinal() * 90;
        // Extraire la sous-image correspondante au pion
        BufferedImage pieceImage = khet.getSubimage(colonne * 100, ligne * 100, 100, 100);

        // Taille désirée pour le redimensionnement
        int desiredWidth = 75; // ou 50 pour une taille encore plus petite
        int desiredHeight = 75; // ou 50 selon la largeur

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(rotation), desiredWidth / 2.0, desiredHeight / 2.0);
        BufferedImage resizedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gg = resizedImage.createGraphics();
        gg.setTransform(transform);
        // Redimensionner et dessiner l'image sur le nouveau BufferedImage
        gg.drawImage(pieceImage, 0, 0, desiredWidth, desiredHeight, null);
        gg.dispose();

        return resizedImage;
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        BufferedImage image = draw(g, pion);
        g.drawImage(image, 0, 0, null);
    }

    // Vous pouvez ajouter des méthodes pour mettre à jour l'image, etc.

    public static void main(String[] args) {
        Pion pion = new Pion(TypeDePion.PHARAON, Direction.EST, Couleur.JAUNE);
        PiecePanel panel = new PiecePanel(pion);
        SwingUtilities.invokeLater(() -> {
            panel.revalidate();
            panel.repaint();
        });
        JFrame j = new JFrame();
        j.setSize(new Dimension(300, 300));
        j.add(panel);
        j.setVisible(true);
    }
}