package khet.model.pieces;

import khet.enums.Couleur;
import khet.enums.Direction;
import khet.enums.TypeDePion;
import khet.model.Piece;

public class Obelisque extends Piece {

    private boolean isStacked; //Obelisque Double

    public Obelisque(Couleur couleur, int x, int y, boolean isStacked) {
        super(couleur, Direction.NONE, TypeDePion.OBELISQUE, x, y); // Direction.NONE si la direction n'est pas pertinente
        this.isStacked = isStacked;
    }

    // Getter et Setter pour isStacked
    public boolean isStacked() {
        return isStacked;
    }

    public void setStacked(boolean isStacked) {
        this.isStacked = isStacked;
    }

    @Override
public void move(int newX, int newY) {
    // Vérification si le mouvement est valide
    if (isMoveValid(newX, newY)) {
        // Si le mouvement est valide, mettez à jour la position de la pièce
        this.x = newX;
        this.y = newY;
    } else {
        // Si le mouvement n'est pas valide, vous pourriez lancer une exception ou gérer l'erreur d'une autre manière
        throw new IllegalArgumentException("Mouvement invalide.");
    }
}

private boolean isMoveValid(int newX, int newY) {
    // Vérifiez les limites du plateau (en supposant un plateau de taille 10x8 pour cet exemple)
    if (newX < 0 || newX >= 10 || newY < 0 || newY >= 8) {
        return false; // Le mouvement est en dehors du plateau
    }
    
    // Ecrire la suite au cas la case est reserve par un autre piece
    //Mais normalement il est tjrs true car il joue le role de la reine

    return true; // Le mouvement est valide
}

    @Override
    public void rotate(boolean clockwise) {
        // Les Obélisques ne tournent pas
    }

    @Override
    public Direction interactWithLaser(Direction laserDirection) {
        // Si c'est un Obélisque double, seule la partie supérieure est affectée.
        if (this.isStacked) {
            // Supposons que cette instance représente la partie supérieure d'un Obélisque double.
            // Marquez cette pièce comme retirée du jeu.
            this.kill(); // Change l'état de isAlive à false.
            // Pour simplifier, on retourne null. Dans votre jeu, vous pourriez avoir besoin de gérer le désempilage.
            return null;
        } else {
            // Pour un Obélisque simple, il est simplement retiré du jeu.
            this.kill();
            return null;
        }
    }
}
