package src.main.java.piece;

import src.main.java.enums.Couleur;
import src.main.java.enums.TypeDePion;

public class Horus extends Pion {
    public Horus(int positionX, int positionY, Couleur couleur) {
        super(TypeDePion.HORUS, positionX, positionY, couleur);
    }

    @Override
    public void deplacer(int nouvelleX, int nouvelleY) {
        // Logique de déplacement spécifique à Horus
    }

    @Override
    public void actionSpecifique() {
        // Protéger contre le laser dans une direction spécifique
    }
}
