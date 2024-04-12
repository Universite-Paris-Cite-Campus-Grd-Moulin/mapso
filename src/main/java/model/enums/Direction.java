package model.enums;

public enum Direction {
    NORD, EST, SUD, OUEST, NORD_EST, SUD_EST, SUD_OUEST, NORD_OUEST;

    private static final int[][] DELTAS = {
            { -1, 0 }, // NORD
            { 0, 1 }, // EST
            { 1, 0 }, // SUD
            { 0, -1 }, // OUEST
            { -1, 1 }, // NORD_EST
            { 1, 1 }, // SUD_EST
            { 1, -1 }, // SUD_OUEST
            { -1, -1 } // NORD_OUEST
    };

    private static final Direction[] PERPENDICULAIRES = {
            EST, // perpendiculaire à NORD
            SUD, // perpendiculaire à EST
            OUEST, // perpendiculaire à SUD
            NORD, // perpendiculaire à OUEST
            SUD_EST, // perpendiculaire à NORD_EST
            SUD_OUEST, // perpendiculaire à SUD_EST
            NORD_OUEST, // perpendiculaire à SUD_OUEST
            NORD_EST // perpendiculaire à NORD_OUEST
    };

    public int getDi() {
        return DELTAS[this.ordinal()][0];
    }

    public int getDj() {
        return DELTAS[this.ordinal()][1];
    }

    public Direction perpendiculaire() {
        return PERPENDICULAIRES[this.ordinal()];
    }

    // Vous pouvez ajouter d'autres méthodes pour gérer la réflexion du laser
    // Par exemple, pour retourner une direction réfléchie en fonction du type de
    // miroir rencontré
    public Direction reflechirSurMiroir45() {
        return null;
        // Logique de réflexion sur un miroir à 45 degrés
        // Cela dépendra de la règle exacte de réflexion pour votre jeu
    }

    public Direction reflechirSurMiroir135() {
        return null;
        // Logique de réflexion sur un miroir à 135 degrés
        // Cela dépendra de la règle exacte de réflexion pour votre jeu
    }

    // Une méthode pour gérer la division du laser en rencontrant un Horus
    public Direction[] diviserSurHorus() {
        // Retourne les deux nouvelles directions après la division
        return new Direction[] { this.perpendiculaire(), this }; // exemple simplifié
    }

    // Retourne la direction suivante dans le sens horaire
    public Direction nextClockwise() {
        return switch (this) {
            case NORD -> EST;
            case EST -> SUD;
            case SUD -> OUEST;
            case OUEST -> NORD;
            case NORD_EST -> SUD_EST;
            case SUD_EST -> SUD_OUEST;
            case SUD_OUEST -> NORD_OUEST;
            case NORD_OUEST -> NORD_EST;
        };
    }

    // Retourne la direction suivante dans le sens anti-horaire
    public Direction nextCounterClockwise() {
        return switch (this) {
            case NORD -> OUEST;
            case OUEST -> SUD;
            case SUD -> EST;
            case EST -> NORD;
            case NORD_EST -> NORD_OUEST;
            case NORD_OUEST -> SUD_OUEST;
            case SUD_OUEST -> SUD_EST;
            case SUD_EST -> NORD_EST;
        };
    }

    // Retourne la direction après une rotation de 90 degrés, selon la direction du
    // laser
    public Direction rotate90Degrees() {
        // Cette méthode pourrait être adaptée en fonction de votre logique spécifique
        // de réflexion du laser
        return nextClockwise(); // Exemple simplifié : rotation de 90 degrés = prochaine direction dans le sens
                                // horaire
    }
}
