package src.main.java.piece;

import src.main.java.enums.*;

public class Laser {
    private int positionI;
    private int positionJ;
    private Direction direction;

    public Laser(int positionI, int positionJ, Direction direction) {
        this.positionI = positionI;
        this.positionJ = positionJ;
        this.direction = direction;
    }

    public void changerDirection(Direction nouvelleDirection) {
        this.direction = nouvelleDirection;
    }

    // Getters et setters
    public int getPositionI() {
        return positionI;
    }

    public void setPositionI(int positionI) {
        this.positionI = positionI;
    }

    public int getPositionJ() {
        return positionJ;
    }

    public void setPositionJ(int positionJ) {
        this.positionJ = positionJ;
    }

    public Direction getDirection() {
        return direction;
    }

    // Méthodes pour déplacer le laser selon sa direction actuelle
    public void deplacer() {
        this.positionI += this.direction.getDi();
        this.positionJ += this.direction.getDj();
    }    
}
