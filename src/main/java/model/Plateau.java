package model;

import model.enums.Couleur;
import model.enums.Direction;
import model.enums.TypeDePion;
import model.enums.TypeInteraction;

public class Plateau {
    private  Pion[][] grille;

    public Pion[][] getGrille(){
        return grille;
    }

    public Plateau() {
        this.grille = new Pion[8][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                this.grille[i][j] = new Pion(TypeDePion.NONE,Direction.NORD,Couleur.GRIS);
            }
        }
        //initialiserPlateau();
    }

    private void initialiserPlateau() {
        // Configuration initiale pour un côté du plateau
        initialiserCote(Couleur.ROUGE, 0, true);
        // Configuration symétrique pour l'autre côté du plateau
        initialiserCote(Couleur.JAUNE, grille.length - 1, false);
    }

    private void initialiserCote(Couleur couleur, int ligneDeBase, boolean estRouge) {
        int direction = estRouge ? 1 : -1; // Determine la direction de déplacement basée sur la couleur
        
        // Ajustez les positions selon la couleur et la direction
        int lignePharaon = estRouge ? ligneDeBase : ligneDeBase - direction * 9;
        int ligneDevantPharaon = lignePharaon + direction;
        
        grille[lignePharaon][0] = new Pion(TypeDePion.PHARAON, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][0] = new Pion(TypeDePion.HORUS, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][1] = new Pion(TypeDePion.PYRAMIDE, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][2] = new Pion(TypeDePion.DJED, estRouge ? Direction.EST : Direction.OUEST, couleur);
        grille[ligneDevantPharaon][0] = new Pion(TypeDePion.OBELISQUE, estRouge ? Direction.EST : Direction.OUEST, couleur);
    }

    public boolean deplacerPion(int iDepart, int jDepart, int iArrivee, int jArrivee) {
        // Vérifications pour ne pas sortir du tableau
        if (iDepart < 0 || iDepart >= grille.length || jDepart < 0 || jDepart >= grille[0].length ||
            iArrivee < 0 || iArrivee >= grille.length || jArrivee < 0 || jArrivee >= grille[0].length) {
            // L'une des positions est hors des limites du plateau
            return false;
        }

        // Vérification si la case d'arrivée est vide
        if (grille[iArrivee][jArrivee] != null) {
            // La case d'arrivée est déjà occupée
            return false;
        }

        // Déplacement du pion
        grille[iArrivee][jArrivee] = grille[iDepart][jDepart];
        grille[iDepart][jDepart] = null;
        return true;
    }

    public void afficherPlateau() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afficherPlateau'");
    }

    public boolean shootLaser(Couleur currentPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shootLaser'");
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movePiece'");
    }

    public void deplacerPion(Pion pion, Direction direction) {
        // Logique pour déplacer le pion
    }

    public void tournerPion(Pion pion, Direction direction) {
        // Logique pour tourner le pion
    }

    public void empilerPion(Pion pion) {
        // Logique pour empiler le pion
    }

    public void separerDoubleObelisque(Pion pion) {
        // Logique pour séparer un Double Obélisque
    }

    public boolean estEmpile(Pion pion) {
        // Déterminer si le pion est empilé
        return false; // Exemple de valeur de retour
    }

    public boolean estSeparationDemandee(Pion pion) {
        // Déterminer si une séparation est demandée
        return false; // Exemple de valeur de retour
    }


    public Pion getPieceAt(int x, int y) {
        if (x < 0 || x >= grille.length || y < 0 || y >= grille[0].length) {
            return null; // Position hors du plateau
        }
        return grille[x][y];
    }
    

    public boolean estTourneeDemandee(Pion pion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'estTourneeDemandee'");
    }

    public NoeudTrajectoire lancerLaser(int i, int j, Direction direction) {
    // Vérifier si le laser est hors du plateau
    if (!estDansLePlateau(i, j)) {
        return null; // Le laser est hors du plateau, fin de la récursivité
    }

    // Obtenir la pièce à la position actuelle
    Pion piece = getPieceAt(i, j);
    
    // Si la pièce interagit avec le laser
    if (piece != null) {
        TypeInteraction interaction = determinerInteraction(piece, direction);
        
        // Traitement basé sur le type d'interaction
        switch (interaction) {
            case ABSORPTION:
                // Le laser est absorbé, fin de la trajectoire
                return new NoeudTrajectoire(direction, i, j, interaction);
            case REFLEXION:
                // Calculer la nouvelle direction après réflexion
                Direction nouvelleDirection = calculerNouvelleDirection(direction, piece);
                // Continuer la trajectoire dans la nouvelle direction
                return new NoeudTrajectoire(direction, i, j, interaction, lancerLaser(i, j, nouvelleDirection));
            case DIVISION:
            Direction directionContinue = direction; // Le laser continue dans sa direction actuelle
            Direction directionReflechie = calculerNouvelleDirection(direction, piece); // Réflexion à 90°

            // Créer les noeuds pour chaque direction après division
            NoeudTrajectoire noeudContinue = lancerLaser(i, j, directionContinue);
            NoeudTrajectoire noeudReflechi = lancerLaser(i, j, directionReflechie);

            // Créer un noeud parent pour la division qui contient les deux directions comme successeurs
            NoeudTrajectoire noeudDivision = new NoeudTrajectoire(direction, i, j, interaction, null);
            noeudDivision.ajouterSuccesseur(noeudContinue);
            noeudDivision.ajouterSuccesseur(noeudReflechi);

            return noeudDivision;
        }
    }

    // Si pas d'interaction ou interaction spécifique permettant de continuer, calculer la prochaine position
    int[] nouvellePosition = calculerProchainePosition(i, j, direction);
    return new NoeudTrajectoire(direction, i, j, TypeInteraction.NONE, lancerLaser(nouvellePosition[0], nouvellePosition[1], direction));
}

    private boolean estDansLePlateau(int i, int j) {
        return i >= 0 && i < 8 && j >= 0 && j < 10;
    }


    private TypeInteraction determinerInteraction(Pion piece, Direction direction) {
        switch (piece.getType()) {
            case PHARAON :
                return TypeInteraction.ABSORPTION;
            case PYRAMIDE :
                return TypeInteraction.REFLEXION;
            case DJED :
                return TypeInteraction.REFLEXION;
            case OBELISQUE :
                return TypeInteraction.ABSORPTION;
            case HORUS :
                return TypeInteraction.DIVISION;
            default :
                return TypeInteraction.NONE;
        }
    }

    private Direction calculerNouvelleDirection(Direction direction, Pion piece) {
        // Calculer et retourner la nouvelle direction du laser après réflexion
        return Direction.NORD;
    }

    public  int[] calculerProchainePosition(int i, int j, Direction direction) {
        // Utiliser getDi() et getDj() pour obtenir le delta de déplacement dans la direction actuelle
        int di = direction.getDi();
        int dj = direction.getDj();

        // Calculer les nouvelles coordonnées
        int nouvelleI = i + di;
        int nouvelleJ = j + dj;

        return new int[]{nouvelleI, nouvelleJ};
    }


}
