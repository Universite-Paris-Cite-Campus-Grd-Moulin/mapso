package main.java.view.components;

import main.java.model.enums.*;;

public interface GameNavigationListener {
    void onBackToMenuRequested();

    void updateGameOver(Couleur couleur);
}