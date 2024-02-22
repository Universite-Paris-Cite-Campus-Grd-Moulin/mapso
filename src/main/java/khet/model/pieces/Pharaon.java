package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.model.Piece;

public class Pharaon extends Piece {
    public Pharaon(int x, int y, Couleur couleur) {
        super(x, y, couleur, Direction.NONE); // Pharaon n'a pas d'orientation
    }

    @Override
    public void move(int newX, int newY) {
        // Logique pour déplacer le Pharaon
    }

    @Override
    public void actionSpecifique() {
        // Le Pharaon n'a pas d'action spécifique autre que d'être la cible du jeu
    }
}
