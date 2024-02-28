package khet.model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Piece {

    private int rotation;
    private boolean NE, SE, SW, NW;
    private Image image;

    public static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    public static final boolean YELLOW = true, RED = false;
    public static final int PYRAMID = 0, PHARAOH = 1, ANUBIS = 2, SPHINX = 3, SCARAB = 4;

    private final boolean COLOR;
    private final int TYPE;

    public Piece(int type, boolean color, int rotation) {
        this.TYPE = type;
        this.COLOR = color;
        this.rotation = rotation;
        loadImage();
        initializeReflections();
    }

    private void loadImage() {
        String colorName = COLOR ? "Yellow" : "Red"; // Charge les images en fonction de la couleur Jaune ou Rouge
        String imageName = String.format("/%s_%s.png", getPieceName().toLowerCase(), colorName.toLowerCase());
        this.image = new ImageIcon(this.getClass().getResource(imageName)).getImage();
    }

    private void initializeReflections() {
        NE = SE = SW = NW = false;
        if (TYPE == SCARAB) {
            NE = SE = SW = NW = true;
        }
        // Finir les autres
    }

    public void turnLeft() {
        rotation = (rotation == NORTH) ? WEST : rotation - 1;
    }

    public void turnRight() {
        rotation = (rotation == WEST) ? NORTH : rotation + 1;
    }

    public String getColorString() {
        return COLOR ? "YELLOW" : "Red";
    }

    public String getPieceName() {
        switch (TYPE) {
            case PYRAMID:
                return "Pyramid";
            case PHARAOH:
                return "Pharaoh";
            case ANUBIS:
                return "Anubis";
            case SPHINX:
                return "Sphinx";
            case SCARAB:
                return "Scarab";
            default:
                return "Unknown";
        }
    }

    public boolean reflects(int incomingDirection) {
        switch (rotation) {
            case NORTH:
                return (incomingDirection == NORTH && SW) || (incomingDirection == SOUTH && NE)
                        || (incomingDirection == EAST && NW) || (incomingDirection == WEST && SE);
            // Finir les autres
        }
        return false;
    }

    public boolean isYellow() {
        return COLOR == YELLOW;
    }

    public int getType() {
        return TYPE;
    }

    public void setRotation(int rotation) {
        if (rotation >= 0 && rotation <= 3) {
            this.rotation = rotation;
        }
    }

    public int getRotation() {
        return rotation;
    }

    public boolean canReflectFromDirection(int direction) {
        switch (direction) {
            case NORTH:
                return NE;
            case EAST:
                return SE;
            case SOUTH:
                return SW;
            case WEST:
                return NW;
            default:
                return false;
        }
    }

    public void setReflection(boolean ne, boolean se, boolean sw, boolean nw) {
        NE = ne;
        SE = se;
        SW = sw;
        NW = nw;
    }

    public Image getImage() {
        return image;
    }
}
