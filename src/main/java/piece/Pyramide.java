package src.main.java.piece;

import src.main.java.enums.Couleur;
import src.main.java.enums.TypeDePion;

public class Pyramide extends Pion {
    public Pyramide(int positionX, int positionY, Couleur couleur) {
        super(TypeDePion.PYRAMIDE, positionX, positionY, couleur);
    }

    @Override
    public void deplacer(int nouvelleX, int nouvelleY) {
        // Logique de déplacement spécifique à la Pyramide
    }

    @Override
    public void actionSpecifique() {
        // Réfléchir le laser, par exemple
    }
}
