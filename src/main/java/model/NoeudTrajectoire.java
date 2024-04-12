package model;

import java.util.ArrayList;
import java.util.List;

import model.enums.Direction;
import model.enums.TypeInteraction;

public class NoeudTrajectoire {
    private Direction direction;
    private int positionI;
    private int positionJ;
    private TypeInteraction typeInteraction;
    private List<NoeudTrajectoire> successeurs = new ArrayList<>();

    public NoeudTrajectoire(Direction direction, int positionI, int positionJ, TypeInteraction typeInteraction) {
        this.direction = direction;
        this.positionI = positionI;
        this.positionJ = positionJ;
        this.typeInteraction = typeInteraction;
    }

    public void ajouterSuccesseur(NoeudTrajectoire successeur) {
        this.successeurs.add(successeur);
    }

    public Direction getDirection() {
        return direction;
    }

    public int getPositionI() {
        return positionI;
    }

    public int getPositionJ() {
        return positionJ;
    }

    public TypeInteraction getTypeInteraction() {
        return typeInteraction;
    }

    public List<NoeudTrajectoire> getSuccesseurs() {
        return successeurs;
    }
}
