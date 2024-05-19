    package main.java.model;

    import java.util.ArrayList;
    import java.util.List;

    import model.enums.Direction;
    import main.java.model.enums.TypeDePion;
    import model.enums.TypeInteraction;
    import main.java.model.Plateau;

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
        switch (orientationPiece) {
            case NORD:
                switch (directionLaser) {
                    case SUD:
                        return Direction.EST;
                    case EST:
                        return Direction.SUD;
                    case NORD:
                        return Direction.OUEST;
                    case OUEST:
                        return Direction.NORD;
                }
                break;
            case EST:
                switch (directionLaser) {
                    case OUEST:
                        return Direction.SUD;
                    case SUD:
                        return Direction.OUEST;
                    case EST:
                        return Direction.NORD;
                    case NORD:
                        return Direction.EST;
                }
                break;
            case SUD:
                switch (directionLaser) {
                    case NORD:
                        return Direction.OUEST;
                    case OUEST:
                        return Direction.NORD;
                    case SUD:
                        return Direction.EST;
                    case EST:
                        return Direction.SUD;
                }
                break;
            case OUEST:
                switch (directionLaser) {
                    case EST:
                        return Direction.NORD;
                    case NORD:
                        return Direction.EST;
                    case OUEST:
                        return Direction.SUD;
                    case SUD:
                        return Direction.OUEST;
                }
                break;
        }
        return directionLaser;  // Si aucune réflexion n'est possible, retourne la direction actuelle
    }

    private Direction calculerNouvelleDirectionPyramide(Direction directionLaser, Direction orientationPiece) {
        switch (orientationPiece) {
            case NORD:
                switch (directionLaser) {
                    case  NORD:  // Le laser vient du sud et frappe la face réfléchissante
                        return Direction.OUEST;
                    case EST:  // Le laser vient de l'est et frappe la face réfléchissante
                        return Direction.SUD;
                    default:
                        return null;  // Absorber le laser pour les autres directions
                }
            case SUD:
                switch (directionLaser) {
                    case SUD:  // Le laser vient du nord et frappe la face réfléchissante
                        return Direction.EST;
                    case OUEST:  // Le laser vient de l'ouest et frappe la face réfléchissante
                        return Direction.NORD;
                    default:
                        return null;  // Absorber le laser pour les autres directions
                }
            case EST:
                switch (directionLaser) {
                    case EST:  // Le laser vient de l'ouest et frappe la face réfléchissante
                        return Direction.NORD;
                    case SUD:  // Le laser vient du sud et frappe la face réfléchissante
                        return Direction.OUEST;
                    default:
                        return null;  // Absorber le laser pour les autres directions
                }
            case OUEST:
                switch (directionLaser) {
                    case OUEST:  // Le laser vient de l'est et frappe la face réfléchissante
                        return Direction.SUD;
                    case NORD :
                        return Direction.EST;
                    default:
                        return null;  // Absorber le laser pour les autres directions
                }
            default:
                return null;  // Absorber le laser si l'orientation est inconnue
        }
    }
    
    



        public void avancerLaser(Plateau plateau) {
            if (!plateau.estDansLimites(positionI, positionJ)) {
                System.out.println("Le laser a quitté le plateau");
                return;  // Stoppe la propagation si en dehors des limites
            }

            Pion pion = plateau.getPieceAt2(positionI, positionJ);
            if (pion != null) {
                System.out.println("Interaction avec : " + pion.getType());
                Direction nouvelleDirection;
                switch (pion.getType()) {
                    case PYRAMIDE:
                    nouvelleDirection = calculerNouvelleDirectionPyramide(this.direction, pion.getDirection());
                if (nouvelleDirection != null) { // Si la direction change, alors réflexion a eu lieu
                    System.out.println("Nouvelle direction après réflexion : " + nouvelleDirection);
                    ajouterSuccesseur(plateau, nouvelleDirection, TypeInteraction.REFLEXION);
                } else {
                    System.out.println("Pas de réflexion possible, le laser est absorbé.");
                    return; // Arrêter la propagation
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
                        if (nouvelleDirection != null) {
                            System.out.println("Division du laser par Horus, nouvelle direction : " + nouvelleDirection);
                            ajouterSuccesseur(plateau, nouvelleDirection, TypeInteraction.REFLEXION);
                            System.out.println("****************");
                            ajouterSuccesseur(plateau, this.direction, TypeInteraction.DIVISION);
                            System.out.println("bbbbbbbbbbbbbbb");
                        }
                        ajouterSuccesseur(plateau, this.direction, TypeInteraction.DIVISION);
                        System.out.println("33333");
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