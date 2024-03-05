/* LaserTrajectory.java

    1) Rôle et Fonctionnement : La classe LaserTrajectory, d'autre part, serait utilisée
    pour représenter une seule trajectoire du laser, y compris sa direction et son
    point de départ. Cette abstraction permet de gérer plus facilement les cas où
    un laser peut se diviser ou changer de direction de manière dynamique au cours
    de son parcours.
    2) Gestion des Trajectoires Multiples : Elle fournit une structure de données
    pour stocker et manipuler les informations spécifiques à une trajectoire
    individuelle, ce qui est particulièrement utile dans des situations complexes
    comme avec l'Horus, où un laser peut à la fois continuer dans sa direction
    actuelle et être réfléchi dans une nouvelle direction.
 */
package model;

import enums.Direction;

public class LaserTrajectory {
    private Direction direction;
    private int startX, startY;

    public LaserTrajectory(Direction direction, int startX, int startY) {
        this.direction = direction;
        this.startX = startX;
        this.startY = startY;
    }

    // Getters
    public Direction getDirection() {
        return direction;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    // Setters, si nécessaire
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
}
