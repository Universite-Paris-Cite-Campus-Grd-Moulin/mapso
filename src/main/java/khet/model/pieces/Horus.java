package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.model.Piece;

public class Horus extends Piece {
    public Horus(int x, int y, Couleur couleur, Direction direction) {
        super(x, y, couleur, direction);
    }

    @Override
    public void move(int newX, int newY) {
        // La logique de déplacement pour Horus, si applicable.
        // Horus peut se déplacer d'une case dans n'importe quelle direction mais cela
        // dépend des règles spécifiques de votre version du jeu.
    }

    @Override
    public void actionSpecifique() {
        // Pour Horus, l'action spécifique pourrait être sa capacité à réfléchir le
        // laser.
        // Cette méthode pourrait être utilisée pour implémenter cette capacité ou pour
        // gérer la rotation de la pièce.
        rotate(); // Exemple simple de rotation.
    }

    public void rotate() {
        // Implémentation de la rotation de Horus.
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
