package khet.enums;

public enum Direction {
    NORD(0, -1),
    SUD(0, 1),
    EST(1, 0),
    OUEST(-1, 0),
    NORD_EST(1, -1),
    NORD_OUEST(-1, -1),
    SUD_EST(1, 1),
    SUD_OUEST(-1, 1),
    NONE(0, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    // Retourne la direction suivante dans le sens horaire
    public Direction nextClockwise() {
        switch (this) {
            case NORD:
                return EST;
            case EST:
                return SUD;
            case SUD:
                return OUEST;
            case OUEST:
                return NORD;
            //Cas pour les directions diagonales
            case NORD_OUEST:
                return NORD_OUEST;
            case SUD_OUEST:
                return SUD_OUEST;
            case NORD_EST:
                return NORD_EST;
            case SUD_EST:
                return SUD_EST;
            default:
                return NONE;
        }
    }

    // Retourne la direction suivante dans le sens anti-horaire
    public Direction nextCounterClockwise() {
        switch (this) {
            case NORD:
                return OUEST;
            case OUEST:
                return SUD;
            case SUD:
                return EST;
            case EST:
                return NORD;
            //Cas pour les directions diagonales
            case NORD_OUEST:
                return NORD_OUEST;
            case SUD_OUEST:
                return SUD_OUEST;
            case NORD_EST:
                return NORD_EST;
            case SUD_EST:
                return SUD_EST;
            default:
                return NONE;
        }
    }

    // Retourne la direction après une rotation de 90 degrés, selon la direction du laser
    public Direction rotate90Degrees() {
        // Cette méthode pourrait être adaptée en fonction de votre logique spécifique de réflexion du laser
        return nextClockwise(); // Exemple simplifié : rotation de 90 degrés = prochaine direction dans le sens horaire
    }
}
