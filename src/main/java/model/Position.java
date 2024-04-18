package model;

import model.enums.Direction;

public class Position {
    int i;
    int j;

    public Position(int nouvelleI, int nouvelleJ) {
        this.i = nouvelleI;
        this.j = nouvelleJ;
    }

    Position next(Direction dir) {
        return new Position(i + dir.getDi(), j + dir.getDj());
    }
}
