package khet.model;

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
}
