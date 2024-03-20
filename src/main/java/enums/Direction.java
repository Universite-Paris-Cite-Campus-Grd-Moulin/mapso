package src.main.java.enums;

public enum Direction {
    NORD(-1, 0) {
        @Override
        public Direction perpendiculaire() {
            return EST;
        }
    },
    EST(0, 1) {
        @Override
        public Direction perpendiculaire() {
            return SUD;
        }
    },
    SUD(1, 0) {
        @Override
        public Direction perpendiculaire() {
            return OUEST;
        }
    },
    OUEST(0, -1) {
        @Override
        public Direction perpendiculaire() {
            return NORD;
        }
    },
    NORD_EST(-1, 1) {
        @Override
        public Direction perpendiculaire() {
            return SUD_EST;
        }
    },
    SUD_EST(1, 1) {
        @Override
        public Direction perpendiculaire() {
            return SUD_OUEST;
        }
    },
    SUD_OUEST(1, -1) {
        @Override
        public Direction perpendiculaire() {
            return NORD_OUEST;
        }
    },
    NORD_OUEST(-1, -1) {
        @Override
        public Direction perpendiculaire() {
            return NORD_EST;
        }
    };

    private final int di;
    private final int dj;

    Direction(int di, int dj) {
        this.di = di;
        this.dj = dj;
    }

    public int getDi() {
        return di;
    }

    public int getDj() {
        return dj;
    }

    public abstract Direction perpendiculaire();
}