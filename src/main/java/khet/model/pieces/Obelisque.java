package khet.model.pieces;

import khet.enums.Couleur;
import khet.model.Piece;

public class Obelisque extends Piece {
    private boolean isDouble; // Indique si l'Obélisque est double

    public Obelisque(int x, int y, Couleur couleur, boolean isDouble) {
        super(x, y, couleur, null); // Les Obélisques n'ont pas de direction
        this.isDouble = isDouble;
    }

    @Override
    public void move(int newX, int newY) {
        // Implémentation du déplacement de l'Obélisque
        // Généralement, l'Obélisque peut se déplacer d'une case dans n'importe quelle
        // direction
        // mais ne peut pas sauter d'autres pièces.
        // La logique de déplacement peut varier si l'Obélisque est double.
    }

    @Override
    public void actionSpecifique() {
        // L'Obélisque n'a pas d'action spécifique en termes de rotation ou d'effet sur
        // le laser,
        // mais vous pouvez ajouter ici des logiques spécifiques liées à l'empilage ou à
        // d'autres règles.
    }

    public boolean isDouble() {
        return isDouble;
    }

    public void setDouble(boolean isDouble) {
        this.isDouble = isDouble;
    }

    // Vous pouvez ajouter d'autres méthodes utiles ici, comme une méthode pour
    // gérer
    // l'empilage ou la séparation des Obélisques doubles.
}
