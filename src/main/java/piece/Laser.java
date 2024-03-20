package src.main.java.piece;
import java.util.ArrayList;
import src.main.java.trajectoire.NoeudTrajectoire;
import src.main.java.enums.*;

public class Laser {
    private int positionI;
    private int positionJ;
    private Direction direction;
    private ArrayList<NoeudTrajectoire> trajectoire; // Arbre de trajectoire du laser

    public Laser(int positionI, int positionJ, Direction direction) {
        this.positionI = positionI;
        this.positionJ = positionJ;
        this.direction = direction;
        this.trajectoire = new ArrayList<>();

    }

    public void changerDirection(Direction nouvelleDirection) {
        this.direction = nouvelleDirection;
    }

    // Getters et setters
    public int getPositionI() {
        return positionI;
    }

    public void setPositionI(int positionI) {
        this.positionI = positionI;
    }

    public int getPositionJ() {
        return positionJ;
    }

    public void setPositionJ(int positionJ) {
        this.positionJ = positionJ;
    }

    public Direction getDirection() {
        return direction;
    }

    // Méthodes pour déplacer le laser selon sa direction actuelle
    public void deplacer() {
        // Déplacer le laser selon sa direction actuelle
        this.positionI += this.direction.getDi();
        this.positionJ += this.direction.getDj();

        // Ajouter un nouveau nœud à l'arbre de trajectoire
        NoeudTrajectoire nouveauNoeud = new NoeudTrajectoire(this.positionI, this.positionJ, TypePion.LASER);
        this.trajectoire.add(nouveauNoeud);
    } 
    public void gererInteractionPion(TypePion typePion) {
        switch (typePion) {
            case MIRROIR_45:
                // Réflexion à 45 degrés
                this.renverserDirection();
                break;
            case MIRROIR_135:
                // Réflexion à 135 degrés
                this.renverserDirection();
                break;
            case HORUS:
                // Division du laser en deux directions
                this.diviserLaser();
                break;
            // Gérer d'autres types de pions selon les règles du jeu
            default:
                // Aucune action par défaut
                break;
        }
    }
    
    private void renverserDirection() {
        // Code pour renverser la direction du laser (réflexion)
        // Exemple: Inverser les composantes de la direction (di et dj)
        this.direction = new Direction(-this.direction.getDi(), -this.direction.getDj());
    }
    
    private void diviserLaser() {
        // Code pour diviser le laser en deux directions (bifurcation)
        // Exemple: Ajouter deux nouveaux lasers avec des directions différentes
        Laser nouveauLaser1 = new Laser(this.positionI, this.positionJ, nouvelleDirection1);
        Laser nouveauLaser2 = new Laser(this.positionI, this.positionJ, nouvelleDirection2);
        // Ajouter les nouveaux lasers à une liste de lasers actifs
    }
        
}
