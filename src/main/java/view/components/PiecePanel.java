package view.components;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Pion;
import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;

// PieceComponent pourrait ressembler à ceci
public class PiecePanel extends JPanel {

    private static BufferedImage khet;
    static {
        try {
            khet = ImageIO.read(new File("ressources/sprites_khet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pion pion; // MVC et pas en attribut
    private BufferedImage image; // image du pion extrait
    private static final BufferedImage[] images = new BufferedImage[TypeDePion.values().length];

    public PiecePanel(Pion p) {
        this.pion = p;
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

    public static void main(String[] args) {
        Pion pion = new Pion(TypeDePion.NONE, Direction.EST, Couleur.JAUNE);
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

    private static void loadImages() {
        // Supposons que les images soient stockées dans un dossier "images/"
        try {
            for (TypeDePion type : TypeDePion.values()) {
                images[type.ordinal()] = ImageIO.read(new File("images/" + type.name() + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }
}