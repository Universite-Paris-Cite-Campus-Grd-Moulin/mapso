package src.main.java.enums;

public enum Direction {
    N, E, S, W;
    
    private static final int di[] = {-1, 0, 1, 0};
    private static final int dj[] = {0, 1, 0, -1};
    
    static abstract class Behavior {
        abstract void m();
    }

    static Behavior behaviors[] = {
        new Behavior() {
            void m() {

            }
        }
        //...
    };

    void m() {
        behaviors[this.ordinal()].m();
    }

    int di() {
        return di[this.ordinal()];
    }
    int dj() {
        return dj[this.ordinal()];
    }
}