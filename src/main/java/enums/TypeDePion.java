package enums;

import model.Pion.*;
import enums.Direction;
import model.Plateau;

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
        @Override
        public void Tourner() {
            this.rotation = this.rotation + this;
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

        @Override
        public void Empiler() {
            // La logique d'empiler ici doit être ajustée
        }

        @Override
        public void Dépiler() {
            // La logique de dépiler ici doit être ajustée
        }

      
    },

    OBELISQUE {
        @Override
        public void Deplacer(Direction direction, Plateau plateau) {
            // La logique de déplacement ici doit être ajustée
        }
    };

    public abstract void Deplacer(Direction direction, Plateau plateau);
    public abstract void Empiler();
    public abstract void Dépiler();
    public abstract void Tourner();
}
