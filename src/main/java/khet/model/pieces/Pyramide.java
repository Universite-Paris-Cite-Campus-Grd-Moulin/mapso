package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.model.Piece;

public class Pyramide extends Piece {
    public Pyramide(int x, int y, Couleur couleur, Direction direction) {
        super(x, y, couleur, direction);
    }

    @Override
    public void move(int newX, int newY) {
        // Implémentez la logique pour déplacer la Pyramide
        // Note : Les pyramides ne se déplacent que dans les cases adjacentes sans
        // sauter
    }

    @Override
    public void actionSpecifique() {
        // La Pyramide a une capacité unique de rotation
        rotate();
    }

    public void rotate() {
        // Implémentez la logique de rotation ici
        // Exemple : rotation dans le sens horaire
        switch (this.direction) {
            case N:
                this.direction = Direction.E;
                break;
            case E:
                this.direction = Direction.S;
                break;
            case S:
                this.direction = Direction.W;
                break;
            case W:
                this.direction = Direction.N;
                break;
            default:
                // Gérer une éventuelle erreur ou cas non prévu
                break;
        }
    }
}
