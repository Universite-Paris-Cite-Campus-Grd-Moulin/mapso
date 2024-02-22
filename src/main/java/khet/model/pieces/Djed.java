package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.model.Piece;

public class Djed extends Piece {
    public Djed(int x, int y, Couleur couleur, Direction direction) {
        super(x, y, couleur, direction);
    }

    @Override
    public void move(int newX, int newY) {
        // La logique de déplacement spécifique au Djed, si applicable.
        // Généralement, le Djed peut se déplacer d'une case dans n'importe quelle
        // direction
        // mais cela dépend des règles spécifiques de votre version du jeu.
    }

    @Override
    public void actionSpecifique() {
        // Pour le Djed, l'action spécifique pourrait être de pivoter
        // lui-même et/ou d'autres pièces adjacentes.
        rotate(); // Exemple simple de rotation de lui-même.
    }

    public void rotate() {
        // Implémentation de la rotation du Djed.
        // Ici, nous pivotons juste le Djed lui-même comme exemple.
        // Vous devrez ajuster cette méthode pour gérer la rotation d'autres pièces si
        // nécessaire.
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

    // Vous pouvez ajouter ici d'autres méthodes pour gérer la rotation des pièces
    // adjacentes,
    // selon les règles de votre jeu.
}
