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
        return switch (this) {
            case NORD -> EST;
            case EST -> SUD;
            case SUD -> OUEST;
            case OUEST -> NORD;
            case NORD_EST -> SUD_EST;
            case SUD_EST -> SUD_OUEST;
            case SUD_OUEST -> NORD_OUEST;
            case NORD_OUEST -> NORD_EST;
            case NONE -> NONE; // Aucune rotation pour NONE
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
            case NONE -> NONE; // Aucune rotation pour NONE
        };
    }

    // Retourne la direction après une rotation de 90 degrés, selon la direction du laser
    public Direction rotate90Degrees() {
        // Cette méthode pourrait être adaptée en fonction de votre logique spécifique de réflexion du laser
        return nextClockwise(); // Exemple simplifié : rotation de 90 degrés = prochaine direction dans le sens horaire
    }
}
