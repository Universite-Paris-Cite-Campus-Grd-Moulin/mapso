public class Jeu {
    private ArbreTrajectoire arbreTrajectoire;

    public Jeu() {
        // Initialisation de l'arbre de trajectoire
        // Supposons que racine est la position initiale du laser
        NoeudTrajectoire racine = new NoeudTrajectoire(new Coordonnees(x, y), TypeInteraction.INITIAL);
        this.arbreTrajectoire = new ArbreTrajectoire(racine);
    }

    public void mouvementLaser() {
        // Logique de déplacement du laser et de mise à jour de l'arbre de trajectoire
        
        // Après la mise à jour, parcourir l'arbre pour visualiser la trajectoire
        this.arbreTrajectoire.parcourir();
    }
}
