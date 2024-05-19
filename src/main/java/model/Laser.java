package main.java.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.java.model.enums.Couleur;
import main.java.model.enums.Direction;
import main.java.model.enums.TypeInteraction;

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
            this.direction = Direction.SUD; // Part du haut et va vers le bas
        } else if (couleur == Couleur.JAUNE) {
            this.startPosition = new Point(START_I_YELLOW, START_J_YELLOW);
            this.direction = Direction.NORD; // Part du bas et va vers le haut
        }
        this.racine = new NoeudTrajectoire(this.direction, startPosition.x, startPosition.y, TypeInteraction.NONE);
    }

     // Propage le laser à partir du noeud racine
    public void propagerLaser(Plateau plateau) {
        racine.avancerLaser(plateau);
    }
    // Calcule et renvoie le chemin complet du laser sous forme de liste de points
    public List<NoeudTrajectoire> obtenirCheminLaser() {
        List<NoeudTrajectoire> chemin = new ArrayList<>();
        NoeudTrajectoire courant = racine;
        while (courant != null) {
            chemin.add(new NoeudTrajectoire(courant.getDirection(),courant.getPositionJ(), courant.getPositionI(),courant.getTypeInteraction()));
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

    public String getPositionInitiale() {
        return "(" + startPosition.x + ", " + startPosition.y + ")";
    }

    public Direction getDirection() {
        return direction;
    }

    public static void main(String[] args) {
        Plateau plateau = new Plateau("Classic");  // Exemple d'instanciation de ton plateau
        Laser laserRouge = new Laser(Couleur.JAUNE);
        Laser laserJaune = new Laser(Couleur.ROUGE);
        laserRouge.propagerLaser(plateau);
        laserJaune.propagerLaser(plateau);

        System.out.println("Chemin du Laser Rouge:");
        imprimerCheminLaser(laserRouge.obtenirCheminLaser());

        System.out.println("Chemin du Laser Jaune:");
        imprimerCheminLaser(laserJaune.obtenirCheminLaser());
    }

    private static void imprimerCheminLaser(List<NoeudTrajectoire> chemin) {
        for (NoeudTrajectoire noeud : chemin) {
            System.out.println("Position: (" + noeud.getPositionI() + ", " + noeud.getPositionJ() + ") Direction: " + noeud.getDirection() + " Interaction: " + noeud.getTypeInteraction());
        }
    }

    public void reinitialiserChemin() {
        this.racine = new NoeudTrajectoire(this.direction, startPosition.x, startPosition.y, TypeInteraction.NONE);
    }
}
