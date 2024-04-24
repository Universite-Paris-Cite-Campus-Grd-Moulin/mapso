package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeInteraction;

public class Laser {
    private NoeudTrajectoire racine;
    private Couleur couleur;
    private Point startPosition;
    private Direction direction;

    // Constantes pour les positions de départ
    private static final int START_I_RED = 0;
    private static final int START_J_RED = 0;
    private static final int START_I_YELLOW = 7;
    private static final int START_J_YELLOW = 9;

    // Constructeur de la classe Laser
    public Laser(Couleur couleur) {
        this.couleur = couleur;
        if (couleur == Couleur.ROUGE) {
            this.startPosition = new Point(START_I_RED, START_J_RED);
            this.direction = Direction.SUD;
        } else if (couleur == Couleur.JAUNE) {
            this.startPosition = new Point(START_I_YELLOW, START_J_YELLOW);
            this.direction = Direction.NORD;
        }
    
        this.racine = new NoeudTrajectoire(this.direction, startPosition.x, startPosition.y, TypeInteraction.NONE);
    }

    // Propage le laser à partir du noeud racine
    public void propagerLaser(Plateau plateau) {
        racine.avancerLaser(plateau);
    }

    // Calcule et renvoie le chemin complet du laser sous forme de liste de points
    public List<Point> obtenirCheminLaser() {
        List<Point> chemin = new ArrayList<>();
        NoeudTrajectoire courant = racine;
        while (courant != null) {
            chemin.add(new Point(courant.getPositionJ(), courant.getPositionI()));
            if (!courant.getSuccesseurs().isEmpty()) {
                courant = courant.getSuccesseurs().get(0);
            } else {
                courant = null;
            }
        }
        return chemin;
    }

    // Getters et setters si nécessaire
    public NoeudTrajectoire getRacine() {
        return racine;
    }

    public void setRacine(NoeudTrajectoire racine) {
        this.racine = racine;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public Direction getDirection() {
        return direction;
    }
}
