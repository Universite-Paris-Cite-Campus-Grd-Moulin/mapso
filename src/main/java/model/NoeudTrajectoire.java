package model;

import java.util.ArrayList;
import java.util.List;

import model.enums.Direction;
import model.enums.TypeDePion;
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
    }
    
    // À l'intérieur de la classe NoeudTrajectoire

// Méthode pour calculer la nouvelle direction après réflexion
private Direction calculerNouvelleDirection(Direction directionLaser, Direction orientationMiroir) {
    // Vérifie que la direction et l'orientation du miroir sont valides
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


// Méthode pour avancer le laser à partir du noeud actuel
public void avancerLaser(Plateau plateau) {
    if (plateau == null) {
        throw new IllegalArgumentException("Le plateau ne peut pas être nul.");
    }
    if (!plateau.estDansLimites(positionI, positionJ)) {
        return; // Stoppe la propagation si en dehors des limites
    }

    Pion pion = plateau.getPieceAt(positionI, positionJ);
    if (pion != null && pion.getType() != TypeDePion.NONE) {
        switch (pion.getType()) {
            case PYRAMIDE:
            case DJED:
                // Réflexion du laser
                Direction nouvelleDirection = calculerNouvelleDirection(this.direction, pion.getDirection());
                ajouterSuccesseur(plateau, nouvelleDirection, TypeInteraction.REFLEXION);
                break;
            case HORUS:
                // Division du laser
                ajouterSuccesseur(plateau, calculerNouvelleDirection(this.direction, pion.getDirection()), TypeInteraction.REFLEXION);
                ajouterSuccesseur(plateau, this.direction, TypeInteraction.DIVISION);
                break;
            case PHARAON:
                // Le jeu est terminé si un Pharaon est touché
                plateau.setPharaonTouche(pion.getCouleur());
                break;
            default:
                ajouterSuccesseur(plateau, this.direction, TypeInteraction.NONE);
                break;
        }
    } else {
        // Continue tout droit si pas de pion
        ajouterSuccesseur(plateau, this.direction, TypeInteraction.NONE);
    }
}

private void ajouterSuccesseur(Plateau plateau, Direction direction, TypeInteraction interaction) {
    int nouveauI = positionI + direction.getDi();
    int nouveauJ = positionJ + direction.getDj();
    if (plateau.estDansLimites(nouveauI, nouveauJ)) {
        NoeudTrajectoire successeur = new NoeudTrajectoire(direction, nouveauI, nouveauJ, interaction);
        this.successeurs.add(successeur);
        // Continue la propagation du laser si l'interaction n'est pas une absorption ou une terminaison
        if (interaction != TypeInteraction.NONE) {
            successeur.avancerLaser(plateau);
        }
    }
}

private void ajouterSuccesseurToutDroit(Plateau plateau) {
    // Calculer la nouvelle position basée sur la direction actuelle
    int nouveauI = positionI;
    int nouveauJ = positionJ;
    
    // Ajustez nouveauI et nouveauJ selon la direction du laser
    switch (this.direction) {
        case NORD:
            nouveauI -= 1; // Déplacement vers le haut dans la grille
            break;
        case SUD:
            nouveauI += 1; // Déplacement vers le bas dans la grille
            break;
        case EST:
            nouveauJ += 1; // Déplacement vers la droite dans la grille
            break;
        case OUEST:
            nouveauJ -= 1; // Déplacement vers la gauche dans la grille
            break;
        default:
            // Gestion des erreurs ou des cas inattendus
            throw new IllegalStateException("Direction inconnue: " + this.direction);
    }
    
    if (plateau.estDansLimites(nouveauI, nouveauJ)) {
        NoeudTrajectoire successeur = new NoeudTrajectoire(this.direction, nouveauI, nouveauJ, TypeInteraction.NONE);
        this.successeurs.add(successeur);
        successeur.avancerLaser(plateau); // Propagation récursive
    }
}


private void ajouterSuccesseurReflexion(Plateau plateau, Direction nouvelleDirection) {
    int nouveauI = positionI + nouvelleDirection.getDi();
    int nouveauJ = positionJ + nouvelleDirection.getDj();
    if (plateau.estDansLimites(nouveauI, nouveauJ)) {
        NoeudTrajectoire successeur = new NoeudTrajectoire(nouvelleDirection, nouveauI, nouveauJ, TypeInteraction.REFLEXION);
        this.successeurs.add(successeur);
        successeur.avancerLaser(plateau); // Propagation récursive
    }
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
}