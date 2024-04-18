package model;

import java.util.ArrayList;
import java.util.List;

import model.enums.Direction;
import model.enums.TypeInteraction;

// Un noeud générique pour l'arbre de trajectoire
public class NoeudTrajectoire {
    private Direction direction; // La direction du laser à ce point
    private int positionI; // Coordonnée i sur le plateau
    private int positionJ; // Coordonnée j sur le plateau
    //private Position position; // Remplacer i et j
    private TypeInteraction typeInteraction; // Le type d'interaction à ce point
    private List<NoeudTrajectoire> successeurs; // Les successeurs de ce noeud dans l'arbre

    // Constructeur
    public NoeudTrajectoire(Direction direction, int positionI, int positionJ, TypeInteraction typeInteraction) {
        this.direction = direction;
        this.positionI = positionI;
        this.positionJ = positionJ;
        this.typeInteraction = typeInteraction;
        this.successeurs = new ArrayList<>();
       // this.position = pos;
    }
    

    // Ajoute un successeur au noeud
    public void ajouterSuccesseur(NoeudTrajectoire successeur) {
        this.successeurs.add(successeur);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

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

    public TypeInteraction getTypeInteraction() {
        return typeInteraction;
    }

    public void setTypeInteraction(TypeInteraction typeInteraction) {
        this.typeInteraction = typeInteraction;
    }

    public List<NoeudTrajectoire> getSuccesseurs() {
        return successeurs;
    }

    public void setSuccesseurs(List<NoeudTrajectoire> successeurs) {
        this.successeurs = successeurs;
    }


    /* 
    public Position getPosition() {
        return position;
    }


    public void setPosition(Position position) {
        this.position = position;
    }
   */
}