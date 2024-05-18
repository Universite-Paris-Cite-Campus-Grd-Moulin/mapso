    package model;

    import java.util.ArrayList;
    import java.util.List;

    import model.enums.Direction;
    import model.enums.TypeDePion;
    import model.enums.TypeInteraction;

    // Un noeud générique pour l'arbre de trajectoire
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
        
        // À l'intérieur de la classe NoeudTrajectoire

    // Méthode pour calculer la nouvelle direction après réflexion
    private Direction calculerNouvelleDirection(Direction directionLaser, Direction orientationPiece) {
        // Cas des pyramides
        switch (orientationPiece) {
            case NORD:  // Pointe vers le nord
                if (directionLaser == Direction.SUD) return Direction.EST;  // Sud vers Est
                if (directionLaser == Direction.OUEST) return Direction.NORD; // Ouest vers Nord
                break;
            case EST:  // Pointe vers l'est
                if (directionLaser == Direction.OUEST) return Direction.SUD;  // Ouest vers Sud
                if (directionLaser == Direction.NORD) return Direction.EST; // Nord vers Est
                break;
            case SUD:  // Pointe vers le sud
                if (directionLaser == Direction.NORD) return Direction.OUEST;  // Nord vers Ouest
                if (directionLaser == Direction.EST) return Direction.SUD; // Est vers Sud
                break;
            case OUEST:  // Pointe vers l'ouest
                if (directionLaser == Direction.EST) return Direction.NORD;  // Est vers Nord
                if (directionLaser == Direction.SUD) return Direction.OUEST; // Sud vers Ouest
                break;
        }
        return directionLaser;  // Si aucune réflexion n'est possible, retourne la direction actuelle
    }



        public void avancerLaser(Plateau plateau) {
            if (!plateau.estDansLimites(positionI, positionJ)) {
                System.out.println("Le laser a quitté le plateau");
                return;  // Stoppe la propagation si en dehors des limites
            }

            System.out.println("Position actuelle : (" + positionI + ", " + positionJ + ")");
            System.out.println("Direction actuelle : " + this.direction);

            Pion pion = plateau.getPieceAt2(positionI, positionJ);
            if (pion != null) {
                System.out.println("Interaction avec : " + pion.getType());
                Direction nouvelleDirection;
                switch (pion.getType()) {
                    case PYRAMIDE:
                        nouvelleDirection = calculerNouvelleDirection(this.direction, pion.getDirection());
                        if (nouvelleDirection != this.direction) {  // Si la direction change, alors réflexion a eu lieu
                            System.out.println("Nouvelle direction après réflexion : " + nouvelleDirection);
                            ajouterSuccesseur(plateau, nouvelleDirection, TypeInteraction.REFLEXION);
                        } else {
                            System.out.println("Pas de réflexion possible, le laser est absorbé.");
                            // Ne rien faire pour arrêter la propagation
                        }
                        break;
                    case DJED:
                        // Djeds changent la direction du laser sans l'absorber
                        nouvelleDirection = calculerNouvelleDirection(this.direction, pion.getDirection());
                        System.out.println("Nouvelle direction après réflexion : " + nouvelleDirection);
                        ajouterSuccesseur(plateau, nouvelleDirection, TypeInteraction.REFLEXION);
                        break;
                    case HORUS:
                        // Horus divise le laser
                        nouvelleDirection = calculerNouvelleDirection(this.direction, pion.getDirection());
                        System.out.println("Division du laser par Horus, nouvelle direction : " + nouvelleDirection);
                        ajouterSuccesseur(plateau, nouvelleDirection, TypeInteraction.REFLEXION);
                        ajouterSuccesseur(plateau, this.direction, TypeInteraction.DIVISION);
                        break;
                    case OBELISQUE:
                    case PHARAON:
                        // Obélisque et Pharaon absorbent le laser
                        System.out.println(pion.getType() == TypeDePion.PHARAON ? "Le Pharaon a été touché !" : "Le laser est bloqué par un Obélisque.");
                        if (pion.getType() == TypeDePion.PHARAON) {
                            plateau.setPharaonTouche(pion.getCouleur());
                        }
                        // Pas d'appel à ajouterSuccesseur pour arrêter la propagation
                        break;
                    default:
                        System.out.println("Aucune interaction spécifique, continuation en ligne droite");
                        ajouterSuccesseur(plateau, this.direction, TypeInteraction.NONE);
                }
            } else {
                System.out.println("Aucun pion à cette position, continuation en ligne droite");
                ajouterSuccesseur(plateau, this.direction, TypeInteraction.NONE);
            }
        }
    
    

    public void ajouterSuccesseur(Plateau plateau, Direction direction, TypeInteraction interaction) {
        int nouveauI = positionI + direction.getDi();
        int nouveauJ = positionJ + direction.getDj();
        if (plateau.estDansLimites(nouveauI, nouveauJ)) {
            NoeudTrajectoire successeur = new NoeudTrajectoire(direction, nouveauI, nouveauJ, interaction);
            this.successeurs.add(successeur);
            successeur.avancerLaser(plateau);
        }
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