package main.java.model;

import main.java.model.enums.Couleur;

public interface Observer {
    void update();
    void updateGameOver(Couleur couleur);
}
