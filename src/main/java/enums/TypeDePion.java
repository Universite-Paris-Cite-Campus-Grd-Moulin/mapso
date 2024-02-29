package src.main.java.enums;

import src.main.java.piece.*;
import src.main.java.enums.Direction;

public enum TypeDePion {

    PHARAON {
        @Override
        public void Deplacer(Direction direction, Plateau plateau) {
            // La logique de déplacement ici doit être ajustée car `piece` n'est plus disponible
        }
    },
    
    PYRAMIDE {
        @Override
        public void Deplacer(Direction direction, Plateau plateau) {
            // La logique de déplacement ici doit être ajustée
        }
    },

    DJED {
        @Override
        public void Deplacer(Direction direction, Plateau plateau) {
            // La logique de déplacement ici doit être ajustée
        }
    },

    HORUS {
        @Override
        public void Deplacer(Direction direction, Plateau plateau) {
            // La logique de déplacement ici doit être ajustée
        }
    },

    OBELISQUE {
        @Override
        public void Deplacer(Direction direction, Plateau plateau) {
            // La logique de déplacement ici doit être ajustée
        }
    };

    public abstract void Deplacer(Direction direction, Plateau plateau);
}
