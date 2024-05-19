package main.java.model;

import model.enums.Couleur;

public interface Observer {
    void update();
    void updateGameOver(Couleur couleur);
}
