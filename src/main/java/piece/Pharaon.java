package src.main.java.piece;

import src.main.java.enums.Couleur;
import src.main.java.enums.TypeDePion;

public class Pharaon extends Pion {
    public Pharaon(int positionX, int positionY, Couleur couleur) {
        super(TypeDePion.PHARAON, positionX, positionY, couleur);
    }

    @Override
    public void deplacer(int nouvelleX, int nouvelleY) {
        // Logique de déplacement spécifique au Pharaon
    }

    @Override
    public void actionSpecifique() {
        // Actions spécifiques au Pharaon, si nécessaire
    }
}
