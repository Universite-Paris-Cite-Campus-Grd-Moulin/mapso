package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
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

public class PiecePanel extends JPanel {

    private static BufferedImage khet;
    private static BufferedImage khetVert;

    static {
        try {
            khet = ImageIO.read(new File("ressources/sprites_khet.png"));
            khetVert = ImageIO.read(new File("ressources/sprites_khet_vert.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pion pion;
    private boolean isSelected;
    private BufferedImage image; // image du pion extrait
    private static final BufferedImage[] images = new BufferedImage[TypeDePion.values().length];

    public PiecePanel(Pion p) {
        this.pion = p;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        repaint();
    }

    public static BufferedImage draw(Graphics g, Pion p, boolean isSelected) {
        BufferedImage sourceImage = isSelected ? khetVert : khet;
        int colonne = p.getType().ordinal();
        int ligne = p.getCouleur().ordinal();
        int rotation = p.getDirection().ordinal() * 90;
        BufferedImage pieceImage = sourceImage.getSubimage(colonne * 100, ligne * 100, 100, 100);

        int desiredWidth = 75;
        int desiredHeight = 75;

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(rotation), desiredWidth / 2.0, desiredHeight / 2.0);
        BufferedImage resizedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gg = resizedImage.createGraphics();
        gg.setTransform(transform);
        gg.drawImage(pieceImage, 0, 0, desiredWidth, desiredHeight, null);
        gg.dispose();

        return resizedImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = draw(g, pion, isSelected);
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {
        Pion pion = new Pion(TypeDePion.DOUBLE_OBELISQUE, Direction.EST, Couleur.JAUNE);
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