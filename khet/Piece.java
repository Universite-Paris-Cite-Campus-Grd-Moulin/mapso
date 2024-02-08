package khet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece {
    String piece;     			  // Le type de pièce (Djed/Pyramide...)
    Boolean hasMoved = false;     // Un indicateur pour savoir si la pièce a déjà été déplacée.
    ImageIcon pieceIcon;
    
    public Piece(String piece) {
        Image icon = new ImageIcon("/ressource/"+piece+".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);//700 x 300
        this.piece = piece;
        this.pieceIcon = new ImageIcon(icon);
    }
    
    public String toString() {
        return this.piece;
    }
    
    public String getPiece() {
        return this.piece;
    }
    
    public ImageIcon getIcon() {
        return this.pieceIcon;
    }
    
    public boolean hasMoved() {
        return this.hasMoved;
    }
    
    public void setMoved(boolean condition) {
        this.hasMoved = condition;
    }
}
