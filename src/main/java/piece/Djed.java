package src.main.java.piece;

import src.main.java.enums.Couleur;
import src.main.java.enums.TypeDePion;

public class Djed extends Pion {
    public Djed(int positionX, int positionY, Couleur couleur) {
        super(TypeDePion.DJED, positionX, positionY, couleur);
    }

    @Override
    public void deplacer(int nouvelleX, int nouvelleY) {
        // Logique de déplacement spécifique au Djed
    }

    @Override
    public void actionSpecifique() {
        // Actions comme réfléchir ou bloquer le laser
    }
}
