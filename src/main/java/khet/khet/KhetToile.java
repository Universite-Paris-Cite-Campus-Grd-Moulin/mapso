package khet.khet;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;



class KhetToile extends JButton {
    //Regler les dimenssionde la toile
    private Pos position;
    
    public KhetToile(Pos position) {
        this.position = position;
        Dimension size = new Dimension(950, 275); // Taille de la case calculée apres plusieur essaie
        setPreferredSize(size);
        setIcon(resizeIcon(new ImageIcon(getClass().getResource("/ressources/Gris.png")), size.width, size.height));
        setMargin(new Insets(0, 0, 0, 0));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    
    private static Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}