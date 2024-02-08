package src.main.java.piece;

import src.main.java.enums.Couleur;
import src.main.java.enums.TypeDePion;

public class Obelisque extends Pion {
    public Obelisque(int positionX, int positionY, Couleur couleur) {
        super(TypeDePion.OBELISQUE, positionX, positionY, couleur);
    }

    @Override
    public void deplacer(int nouvelleX, int nouvelleY) {
        // Logique de déplacement spécifique à l'Obélisque
    }

    @Override
    public void actionSpecifique() {
        // Bloquer le laser, par exemple
    }
}
