package model;

import java.util.ArrayList;
import java.util.List;

import model.enums.Direction;
import model.enums.TypeDePion;
import model.enums.TypeInteraction;

public class NoeudTrajectoire {

    private Direction direction;
    private int positionI;
    private int positionJ;
    private TypeInteraction typeInteraction;
    private List<NoeudTrajectoire> successeurs;

    public NoeudTrajectoire(Direction direction, int positionI, int positionJ, TypeInteraction typeInteraction) {
        this.direction = direction;
        this.positionI = positionI;
        this.positionJ = positionJ;
        this.typeInteraction = typeInteraction;
        this.successeurs = new ArrayList<>();
        System.out.println("NoeudTrajectoire created at (" + positionI + ", " + positionJ + ") with direction "
                + direction + " and interaction " + typeInteraction);
    }

    private Direction calculerNouvelleDirection(Direction directionLaser, Direction orientationMiroir) {
        if (directionLaser == null || orientationMiroir == null) {
            throw new IllegalArgumentException("Les directions ne peuvent pas être nulles.");
        }

        switch (directionLaser) {
            case NORD:
                return (orientationMiroir == Direction.NORD) ? Direction.EST : Direction.OUEST;
            case SUD:
                return (orientationMiroir == Direction.NORD) ? Direction.OUEST : Direction.EST;
            case EST:
                return (orientationMiroir == Direction.NORD) ? Direction.NORD : Direction.SUD;
            case OUEST:
                return (orientationMiroir == Direction.NORD) ? Direction.SUD : Direction.NORD;
            default:
                throw new IllegalStateException("Direction inconnue: " + directionLaser);
        }
    }

    public void avancerLaser(Plateau plateau) {
        System.out.println("Laser advancing from (" + positionI + ", " + positionJ + ") in direction " + direction);
        if (plateau == null) {
            throw new IllegalArgumentException("Le plateau ne peut pas être nul.");
        }
        if (!plateau.estDansLimites(positionI, positionJ)) {
            System.out.println("Out of bounds. Stopping propagation.");
            return;
        }

        Pion pion = plateau.getPieceAt(positionI, positionJ);
        if (pion != null && pion.getType() != TypeDePion.NONE) {
            System.out.println("Laser hit piece at (" + positionI + ", " + positionJ + ") of type " + pion.getType());
            switch (pion.getType()) {
                case PYRAMIDE:
                case DJED:
                    Direction nouvelleDirection = calculerNouvelleDirection(this.direction, pion.getDirection());
                    ajouterSuccesseur(plateau, nouvelleDirection, TypeInteraction.REFLEXION);
                    break;
                case HORUS:
                    ajouterSuccesseur(plateau, calculerNouvelleDirection(this.direction, pion.getDirection()),
                            TypeInteraction.REFLEXION);
                    ajouterSuccesseur(plateau, this.direction, TypeInteraction.DIVISION);
                    break;
                case PHARAON:
                    plateau.setPharaonTouche(pion.getCouleur());
                    System.out.println("Pharaoh hit! Game over.");
                    break;
                default:
                    ajouterSuccesseur(plateau, this.direction, TypeInteraction.NONE);
                    break;
            }
        } else {
            ajouterSuccesseur(plateau, this.direction, TypeInteraction.NONE);
        }
    }

    private void ajouterSuccesseur(Plateau plateau, Direction direction, TypeInteraction interaction) {
        int nouveauI = positionI + direction.getDi();
        int nouveauJ = positionJ + direction.getDj();
        if (plateau.estDansLimites(nouveauI, nouveauJ)) {
            NoeudTrajectoire successeur = new NoeudTrajectoire(direction, nouveauI, nouveauJ, interaction);
            this.successeurs.add(successeur);
            System.out.println("Added successor at (" + nouveauI + ", " + nouveauJ + ") with direction " + direction
                    + " and interaction " + interaction);
            if (interaction != TypeInteraction.ABSORPTION) {
                successeur.avancerLaser(plateau);
            }
        }
    }

    public void ajouterSuccesseur(NoeudTrajectoire successeur) {
        this.successeurs.add(successeur);
        System.out.println("Successor added: " + successeur);
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
}
