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

package model;

import java.util.List;

import enums.Direction;

public class Laser {
    private final Board board;

    public Laser(Board board) {
        this.board = board;
    }

    public void shootLaser(int startX, int startY, Direction direction) {
        int x = startX;
        int y = startY;

        while (true) {
            x += direction.getDj();
            y += direction.getDi();

            if (x < 0 || x >= board.getWidth() || y < 0 || y >= board.getHeight()) {
                // Vérifiez si le laser sort du plateau
                break;
            }

            Piece piece = board.getPiece(x, y);
            if (piece != null) {
                List<LaserTrajectory> newTrajectories = piece.interactWithLaser(direction, x, y);
                // Gérez l'interaction du laser avec la pièce et vérifiez si la pièce est toujours en vie
                if (!piece.isAlive()) {
                    // La pièce est touchée et doit être retirée
                    board.removePiece(x, y);
                    break;
                }

                // Sinon, mettez à jour la direction du laser selon les nouvelles trajectoires, si nécessaire
                // Note: Ceci est une simplification. Vous devrez adapter cette logique pour gérer correctement les multiples trajectoires, etc.
                direction = newTrajectories.get(0).getDirection(); // Exemple simplifié
            }
        }
    }
}
