public enum Direction {
    NORD(-1, 0), EST(0, 1), SUD(1, 0), OUEST(0, -1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    // Exemple de méthode pour changer la direction à gauche ou à droite basée sur l'orientation actuelle
    public static Direction gaucheOuDroite(Direction a) {
        switch (a) {
            case NORD:
                return OUEST; // Exemple de changement
            case EST:
                return NORD; // Exemple de changement
            case SUD:
                return EST; // Exemple de changement
            case OUEST:
                return SUD; // Exemple de changement
            default:
                return a;
        }
    }

    // Exemple de méthode pour changer la direction vers le haut ou le bas
    public static Direction hautOuBas(Direction a) {
        switch (a) {
            case NORD:
                return EST; // Exemple de changement
            case EST:
                return SUD; // Exemple de changement
            case SUD:
                return OUEST; // Exemple de changement
            case OUEST:
                return NORD; // Exemple de changement
            default:
                return a;
        }
    }
}
