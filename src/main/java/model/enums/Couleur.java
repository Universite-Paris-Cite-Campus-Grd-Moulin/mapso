package model.enums;

public enum Couleur {
    GRIS, // on suppose que GRIS ne retourne rien d'opposé puisqu'il s'agit d'une couleur
          // neutre
    ROUGE,
    JAUNE;

    public Couleur opposite() {
        switch (this) {
            case ROUGE:
                return JAUNE;
            case JAUNE:
                return ROUGE;
            default:
                return null; // GRIS ou cas non gérés ne retournent rien
        }
    }
}
