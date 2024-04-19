package model.enums;

public enum Direction {
    NONE, NORD, EST, SUD, OUEST, NORD_EST, SUD_EST, SUD_OUEST, NORD_OUEST;

    private static final int[][] DELTAS = {
            { 0, 0 }, // NONE
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
            NONE, // perpendiculaire à NONE
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
            case NONE -> NONE;
        };
    }

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
            case NONE -> NONE;
        };
    }

    public Direction rotate90Degrees() {
        return nextClockwise();
    }

    public Direction perpendiculaire() {
        return PERPENDICULAIRES[this.ordinal()];
    }

    public Direction reflechirSurMiroir45() {
        return null;
    }

    public Direction reflechirSurMiroir135() {
        return null;
    }

    public Direction[] diviserSurHorus() {
        return new Direction[] { this.perpendiculaire(), this };
    }
}
