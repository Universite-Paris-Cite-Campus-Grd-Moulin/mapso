/*
 * Laser.java

    1) Rôle et Fonctionnement : La classe Laser est principalement responsable de la gestion
    globale du laser dans le jeu, y compris son tir depuis une position initiale, son
    interaction avec différentes pièces sur le plateau, et le suivi de sa trajectoire
    jusqu'à ce qu'elle atteigne une condition d'arrêt (comme sortir du plateau ou
    toucher une pièce non réfléchissante).
    2) Logique de Simulation : Elle contient la logique pour simuler le parcours du
    laser à travers le plateau, déterminant comment il interagit avec les pièces
    qu'il rencontre (par exemple, s'il est réfléchi, absorbé, ou si la pièce est
    retirée).
 */

package khet.model;

import java.util.ArrayList;
import java.util.List;

import khet.enums.Direction;

public class Laser {
    private final Board board;

    public Laser(Board board) {
        this.board = board;
    }

    public void shootLaser(int startX, int startY, Direction direction) {
        int x = startX;
        int y = startY;

        while (true) {
            x += direction.getDeltaX();
            y += direction.getDeltaY();

            // Vérifiez si le laser sort du plateau
            if (x < 0 || x >= board.getWidth() || y < 0 || y >= board.getHeight()) {
                break;
            }

            Piece piece = board.getPiece(x, y);
            if (piece != null) {
                // Gérez l'interaction du laser avec la pièce
                Direction newDirection = piece.interactWithLaser(direction);
                if (newDirection == null) {
                    // La pièce est touchée et potentiellement retirée
                    break;
                }
                direction = newDirection; // Change la direction du laser si réfléchi
            }
        }
    }

    public void shootLaser2(int startX, int startY, Direction direction) {
        List<LaserTrajectory> trajectories = new ArrayList<>();
        trajectories.add(new LaserTrajectory(direction, startX, startY)); // Trajectoire initiale

        for (LaserTrajectory trajectory : trajectories) {
            // Ici, vous simuleriez le mouvement du laser pour chaque trajectoire
            // et ajouteriez de nouvelles trajectoires au besoin (par exemple, après interaction avec Horus).
        }
    }
}
