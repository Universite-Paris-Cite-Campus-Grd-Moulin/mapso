package model.enums;

import model.Pion;
import model.Plateau;
import model.enums.Direction;


public enum TypeDePion {

    NONE {
        @Override
        public void deplacer(Direction direction, Plateau plateau, Pion pion) {
            // Aucun Pion ne peut se déplacer
            throw new UnsupportedOperationException("Aucun Pion ne peut se déplacer.");
        }

        @Override
        public void empiler(Plateau plateau, Pion pion) {
            // Aucun Pion ne peut être empilé
            throw new UnsupportedOperationException("Aucun Pion ne peut être empilé.");
        }

        @Override
        public void depiler(Plateau plateau, Pion pion) {
            // Le Pharaon ne peut pas être dépilé
            throw new UnsupportedOperationException("Le Pharaon ne peut pas être dépilé.");
        }
    },

    PHARAON {
        @Override
        public void deplacer(Direction direction, Plateau plateau, Pion pion) {
            // Le Pharaon peut se déplacer d'une case dans n'importe quelle direction
            plateau.deplacerPion(pion, direction);
        }

        @Override
        public void empiler(Plateau plateau, Pion pion) {
            // Le Pharaon ne peut pas être empilé
            throw new UnsupportedOperationException("Le Pharaon ne peut pas être empilé.");
        }

        @Override
        public void depiler(Plateau plateau, Pion pion) {
            // Le Pharaon ne peut pas être dépilé
            throw new UnsupportedOperationException("Le Pharaon ne peut pas être dépilé.");
        }
    },

    PYRAMIDE {
        @Override
        public void deplacer(Direction direction, Plateau plateau, Pion pion) {
            // La Pyramide peut tourner sur elle-même
            plateau.tournerPion(pion, direction);
        }

        @Override
        public void empiler(Plateau plateau, Pion pion) {
            // La Pyramide ne peut pas être empilée
            throw new UnsupportedOperationException("La Pyramide ne peut pas être empilée.");
        }

        @Override
        public void depiler(Plateau plateau, Pion pion) {
            // La Pyramide ne peut pas être dépilée
            throw new UnsupportedOperationException("La Pyramide ne peut pas être dépilée.");
        }
    },

    DJED {
        @Override
        public void deplacer(Direction direction, Plateau plateau, Pion pion) {
            // Le Djed peut tourner ou se déplacer d'une case
            // La logique doit déterminer si le Djed doit tourner ou se déplacer
            if (plateau.estTourneeDemandee(pion)) {
                plateau.tournerPion(pion, direction);
            } else {
                plateau.deplacerPion(pion, direction);
            }
        }

        @Override
        public void empiler(Plateau plateau, Pion pion) {
            // Le Djed ne peut pas être empilé
            throw new UnsupportedOperationException("Le Djed ne peut pas être empilé.");
        }

        @Override
        public void depiler(Plateau plateau, Pion pion) {
            // Le Djed ne peut pas être dépilé
            throw new UnsupportedOperationException("Le Djed ne peut pas être dépilé.");
        }
    },

    HORUS {
        @Override
        public void deplacer(Direction direction, Plateau plateau, Pion pion) {
            // L'Horus peut se déplacer d'une case ou tourner sur lui-même
            if (plateau.estTourneeDemandee(pion)) {
                plateau.tournerPion(pion, direction);
            } else {
                plateau.deplacerPion(pion, direction);
            }
        }

        @Override
        public void empiler(Plateau plateau, Pion pion) {
            // L'Horus ne peut pas être empilé
            throw new UnsupportedOperationException("L'Horus ne peut pas être empilé.");
        }

        @Override
        public void depiler(Plateau plateau, Pion pion) {
            // L'Horus ne peut pas être dépilé
            throw new UnsupportedOperationException("L'Horus ne peut pas être dépilé.");
        }
    },

    OBELISQUE {
        @Override
        public void deplacer(Direction direction, Plateau plateau, Pion pion) {
            // L'Obélisque peut être déplacé s'il n'est pas empilé
            if (!plateau.estEmpile(pion)) {
                plateau.deplacerPion(pion, direction);
            }
        }

        @Override
        public void empiler(Plateau plateau, Pion pion) {
            // Un Obélisque peut être empilé sur un autre Obélisque
            plateau.empilerPion(pion);
        }

        @Override
        public void depiler(Plateau plateau, Pion pion) {
            // L'Obélisque ne peut pas être dépilé car il ne s'empile pas sur d'autres types
            // de pions
            throw new UnsupportedOperationException("L'Obélisque ne peut pas être dépilé.");
        }
    },

    DOUBLE_OBELISQUE {
        @Override
        public void deplacer(Direction direction, Plateau plateau, Pion pion) {
            // Le Double Obélisque, une fois séparé, peut être déplacé
            if (plateau.estSeparationDemandee(pion)) {
                this.depiler(plateau, pion);
            } else {
                plateau.deplacerPion(pion, direction);
            }
        }

        @Override
        public void empiler(Plateau plateau, Pion pion) {
            // Un Double Obélisque ne peut pas être empilé davantage
            throw new UnsupportedOperationException("Le Double Obélisque ne peut pas être empilé.");
        }

        @Override
        public void depiler(Plateau plateau, Pion pion) {
            // Séparer le Double Obélisque en deux Obélisques simples
            plateau.separerDoubleObelisque(pion);
        }
    };

    public abstract void deplacer(Direction direction, Plateau plateau, Pion pion);

    public abstract void empiler(Plateau plateau, Pion pion);

    public abstract void depiler(Plateau plateau, Pion pion);
}
